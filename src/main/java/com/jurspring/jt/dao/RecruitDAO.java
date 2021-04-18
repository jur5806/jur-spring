package com.jurspring.jt.dao;

import com.jurspring.jt.home.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitDAO extends JpaRepository<Recruit,Integer> {

    Recruit findByRecruitId(Integer RecruitId);
//    Page<Recruit> findByRecruitId(String )

}
