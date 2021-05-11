package com.jurspring.jt.service;

import com.jurspring.jt.dao.ResumeInfoDAO;
import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.home.Resumeinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeInfoService {
    @Autowired
    ResumeInfoDAO resumeinfoDAO;
    public List<Resumeinfo> list() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "recruit_id");
        return resumeinfoDAO.findAll();
    }
    /**
     * 添加简历
     */
    public void  addOrUpdate(Resumeinfo resumeinfo) { resumeinfoDAO.save(resumeinfo);}
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
        resumeinfoInDB.setApprovalState(2);
        resumeinfoDAO.save(resumeinfoInDB);
    }


    /**
     * 我的推荐
     */
    public List<Resumeinfo>  findAllByuserId(int getResumeByUserId) {
        return resumeinfoDAO.findAllByTjId(getResumeByUserId);
    }
}
