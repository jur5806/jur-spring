package com.jurspring.jt.service;

import com.jurspring.jt.dao.AdminMenuDAO;
import com.jurspring.jt.dao.PointVeiwDAO;
import com.jurspring.jt.dao.UserDAO;
import com.jurspring.jt.home.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    PointVeiwDAO pointVeiwDAO;

    public List<AdminMenu> getAllByParentId(int parentId) {
        List<User> Users = userDAO.findAll();
        Users.forEach(u ->{
            List<PointVeiw> pointview = pointVeiwDAO.findAllByuserId(u.getId());
            //求列表pointview，PointsNum字段总和
            int total = pointview.stream().mapToInt(PointVeiw::getPointsNum).sum();
            u.setSumPoints(total);
            User userInDB = userDAO.findByUsername(u.getUsername());
            userDAO.save(userInDB);
        });
        return adminMenuDAO.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByCurrentUser() {
        // Get current user in DB.
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);

        // Get roles' ids of current user.
        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        // Get menu items of these roles.
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the menu.
        handleMenus(menus);
        return menus;
    }

    /**
     * 获取某个角色的权限
     * @param rid
     * @return
     */
    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }

    /**
     * 形成权限树
     *
     */
    public void handleMenus(List<AdminMenu> menus) {
        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });
        menus.removeIf(m -> m.getParentId() != 0);
    }
}