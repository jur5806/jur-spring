package com.jurspring.jt.dao;

import com.jurspring.jt.home.Resumeinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeInfoDAO extends JpaRepository<Resumeinfo,Integer> {

    Resumeinfo findByresumeId(Integer resumeId);
}
