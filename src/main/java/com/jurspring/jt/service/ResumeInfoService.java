package com.jurspring.jt.service;

import com.jurspring.jt.dao.ResumeInfoDAO;
import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.home.Resumeinfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ResumeInfoService {
    @Autowired
    ResumeInfoDAO resumeinfoDAO;
    public List<Resumeinfo> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "resumeId");
        return resumeinfoDAO.findAll(sort);
    }
    /**
     * 添加简历
     */
    public void  addOrUpdate(Resumeinfo resumeinfo) {
        //更新提交时间
        resumeinfo.setSubmitTime(new Date());
        resumeinfoDAO.save(resumeinfo);
    }
    /**
     * 删除
     */
    public void deleteById(int id) {
        resumeinfoDAO.deleteById(id);
    }
    /**
     * 更新状态,进入下一个状态为HR待审核
     */
    public void updateApprovalState(Resumeinfo resumeinfo){
        Resumeinfo resumeinfoInDB = resumeinfoDAO.findByresumeId(resumeinfo.getResumeId());
        resumeinfoDAO.save(resumeinfoInDB);

    }


    /**
     * 我的推荐
     */
    public List<Resumeinfo>  findAllByuserId(int getResumeByUserId) {
        return resumeinfoDAO.findAllByTjId(getResumeByUserId);
    }

    public List<Resumeinfo> getByHrId(int hrId, int recruitId,int state) {
        if(state != 4){
            return resumeinfoDAO.findByHrIdAndRecruitIdAndAndApprovalState(hrId,recruitId,state);
        }else{
            return resumeinfoDAO.findByHrIdAndRecruitId(hrId,recruitId);
        }
    }
}
