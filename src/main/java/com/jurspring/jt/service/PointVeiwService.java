package com.jurspring.jt.service;

import com.jurspring.jt.dao.PointVeiwDAO;
import com.jurspring.jt.dao.ResumeInfoDAO;
import com.jurspring.jt.dao.UserDAO;
import com.jurspring.jt.home.Book;
import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Resumeinfo;
import com.jurspring.jt.home.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PointVeiwService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PointVeiwDAO pointVeiwDAO;
    @Autowired
    ResumeInfoDAO resumeinfoDAO;

    public List<PointVeiw> list() {
        return pointVeiwDAO.findAll();
    }

    public List<PointVeiw>  findAllByuserId(int userId) {
        return pointVeiwDAO.findAllByuserId(userId);
    }
    public void add(PointVeiw pointVeiw) {
        int point = pointVeiw.getPointsNum();
        User userInDB = userDAO.findById(pointVeiw.getUserId());
        if(pointVeiw.getChangeType() == 1){
            userInDB.setSumPoints(userInDB.getSumPoints()+point);
        }else if(pointVeiw.getChangeType() == 0) {
            userInDB.setSumPoints(userInDB.getSumPoints()-point);
        }
        pointVeiw.setName(userInDB.getName());
        pointVeiw.setPhone(userInDB.getPhone());
        pointVeiw.setEventTime(new Date());
        userDAO.save(userInDB);
        Resumeinfo resumeinfo = resumeinfoDAO.findByresumeId(pointVeiw.getResumeId());
        resumeinfo.setApprovalState(pointVeiw.getEventType());
        resumeinfoDAO.save(resumeinfo);
        pointVeiwDAO.save(pointVeiw);
    }

}
