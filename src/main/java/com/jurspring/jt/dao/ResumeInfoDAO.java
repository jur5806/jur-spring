package com.jurspring.jt.dao;

import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Recruit;
import com.jurspring.jt.home.Resumeinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeInfoDAO extends JpaRepository<Resumeinfo,Integer> {

    Resumeinfo findByresumeId(int resumeId);
    List<Resumeinfo> findAllByTjId(int userId);
    List<Resumeinfo> findByHrIdAndRecruitId(Integer hrId, Integer recruitId);
    List<Resumeinfo> findAllByRecruitId(int recruitId);
    List<Resumeinfo> findByHrIdAndRecruitIdAndAndApprovalState(Integer hrId, Integer recruitId, Integer state);

}
