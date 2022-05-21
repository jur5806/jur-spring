package com.jurspring.jt.service;

import com.jurspring.jt.dao.*;
import com.jurspring.jt.home.*;
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
    @Autowired
    IntegeralDAO integeralDAO;
    @Autowired
    ReasonDAO reasonDAO;

    public List<PointVeiw> list() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "pointsId");
        return pointVeiwDAO.findAll();
    }

    public List<PointVeiw>  findAllByuserId(int userId) {
        return pointVeiwDAO.findAllByuserId(userId);
    }
    public int add(PointVeiw pointVeiw) {
        int point = 0;
        Integeral integeralInDB = integeralDAO.findByintegralSetingId(1);
        if(pointVeiw.getEventType() == 1){
            point = integeralInDB.getResumePassValue();
        }else if(pointVeiw.getEventType() == 2){
            point = integeralInDB.getViewPassValue();
        }
        else if(pointVeiw.getEventType() == 3){
            point = integeralInDB.getSuccessInValue();
        }else{
            point = pointVeiw.getPointsNum();
        }
        //查用户积分记录
        pointVeiw.setPointsNum(point);
        User userInDB = userDAO.findById(pointVeiw.getUserId());
        if(pointVeiw.getChangeType() == 1){
            userInDB.setSumPoints(userInDB.getSumPoints()+point);
        }else if(pointVeiw.getChangeType() == 0) {
            if(userInDB.getSumPoints()<point){
                return 0;
            }
            int a = userInDB.getSumPoints()-point;
            userInDB.setSumPoints(userInDB.getSumPoints()-point);
        }
        pointVeiw.setName(userInDB.getName());
        pointVeiw.setPhone(userInDB.getPhone());
        if("0".equals(String.valueOf(pointVeiw.getResumeId()))){
            pointVeiw.setResumeId(0);
        }else{
            Resumeinfo resumeinfo = resumeinfoDAO.findByresumeId(pointVeiw.getResumeId());
            resumeinfo.setApprovalState(pointVeiw.getEventType());
            resumeinfoDAO.save(resumeinfo);
        }
        if("0".equals(String.valueOf(pointVeiw.getReasonId()))){
            pointVeiw.setReasonId(0);
        }else{
        Reason reasonInDB = reasonDAO.findByreasonId(pointVeiw.getReasonId());
            pointVeiw.setReasonName(reasonInDB.getReasonName());
        }
        pointVeiw.setEventTime(new Date());
        userDAO.save(userInDB);
        pointVeiwDAO.save(pointVeiw);
        return 1;
    }

}
