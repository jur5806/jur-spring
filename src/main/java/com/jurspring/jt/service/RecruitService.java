package com.jurspring.jt.service;

import com.jurspring.jt.dao.RecruitDAO;
import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.util.DateUtilJava8;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class RecruitService {
    @Autowired
    RecruitDAO recruitDAO;

    /**
     * 获取职位列表
     * @return
     */
    public List<Recruit> list() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "recruit_id");
        return recruitDAO.findAll();
    }
    /**
     * 添加更新职位
     */
    public void  addOrUpdate(Recruit recruit) {
        recruit.setRcStartTime(DateUtilJava8.getNow());
        recruitDAO.save(recruit);}
    /**
     * 删除
     */
    public void deleteById(int recruitId) {
        recruitDAO.deleteById(recruitId);
    }
    /**
     * 更新状态
     */

    public void updateStatus(Recruit recruit) {
        Recruit recruitInDB = recruitDAO.findByRecruitId(recruit.getRecruitId());
        log.info("===="+recruitInDB);
        recruitInDB.setEnabled(false);
        recruitDAO.save(recruitInDB);
    }
}
