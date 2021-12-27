package com.jurspring.jt.service;

import com.jurspring.jt.dao.RecruitDAO;
import com.jurspring.jt.dao.ResumeInfoDAO;
import com.jurspring.jt.dto.UserDTO;
import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.home.Resumeinfo;
import com.jurspring.jt.util.DateUtilJava8;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecruitService {
    @Autowired
    RecruitDAO recruitDAO;
    @Autowired
    ResumeInfoDAO resumeinfoDAO;

    /**
     * 获取职位列表
     * @return
     */
    public List<Recruit> list(String name,Integer id) {
        if ("".equals(name) && id != 0 ) {
            return recruitDAO.findByPositionTypeId(id);
        } else if (!"".equals(name) && id != 0){
            log.info("2");
            return recruitDAO.findAllByStationNameContainingOrWorkPlaceContainingAndPositionTypeId(name, name, id);
        } else if (!"".equals(name) && id == 0){
            log.info("2");
            return recruitDAO.findAllByStationNameContainingOrWorkPlaceContaining(name, name);
        } else {
            log.info("全部");
            Sort sort = Sort.by(Sort.Direction.DESC, "recruitId");
            return recruitDAO.findAll(sort);

        }
//        return recruitDAO.findAll();
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

    public List<Recruit> getByHrId(int hrId) {

        List<Recruit> recruits= recruitDAO.findByHrId(hrId);
        recruits.forEach(r -> {
            int id = r.getRecruitId();
            List<Resumeinfo> resumeinfo= resumeinfoDAO.findAllByRecruitId(r.getRecruitId());
            r.setDepartmentState(resumeinfo.size());
            recruitDAO.save(r);
        });
        return recruitDAO.findByHrId(hrId);
    }

    public Recruit LookById(int recruitId) {
        return recruitDAO.findByRecruitId(recruitId);
    }
}
