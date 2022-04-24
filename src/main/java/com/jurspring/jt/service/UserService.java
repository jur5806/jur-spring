package com.jurspring.jt.service;

import com.jurspring.jt.dao.AdminUserRoleDAO;
import com.jurspring.jt.dao.UserDAO;
import com.jurspring.jt.dto.UserDTO;
import com.jurspring.jt.home.AdminRole;
import com.jurspring.jt.home.AdminUserRole;
import com.jurspring.jt.home.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<UserDTO> list() {
        List<User> users = userDAO.findAll();
//        if(!"".equals(username)){
//            userDAO.findByUsernameContaining(username);
//        }else{
//            users = userDAO.findAll();
//        }
        // Find all roles in DB to enable JPA persistence context.
//        List<AdminRole> allRoles = adminRoleService.findAll();

        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOS;
    }

    public boolean isExist(String username) {
        User user = userDAO.findByUsername(username);
        return null != user;
    }

    public User findById(int userId) {

        return userDAO.findById(userId);
    }
    public User findByUsername(String userName) {

        return userDAO.findByUsername(userName);
    }

    public User get(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public int register(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(true);
        if (username.equals("") || password.equals("")) {
            return 0;
        }

        boolean exist = isExist(username);

        if (exist) {
            return 2;
        }

        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //得到hash算法迭代次数
        int times = 2;
        //得到hash后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userDAO.save(user);
        User userInDB = userDAO.findByUsername(user.getUsername());
        AdminUserRole adminUserRole = new AdminUserRole();
        adminUserRole.setRid(3);
        adminUserRole.setUid(userInDB.getId());
        adminUserRoleDAO.save(adminUserRole);


        return 1;
    }


    public void updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        userDAO.save(userInDB);
    }

    public User resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String password = user.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userDAO.save(userInDB);
    }

    public void editUser(User user) {
        log.info("-----个人信息修改"+user);
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userInDB.setSex(user.getSex());
        userInDB.setBirthDate(user.getBirthDate());
        userDAO.save(userInDB);
        if(user.getRoles() != null){
            adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
        }

    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }


}
