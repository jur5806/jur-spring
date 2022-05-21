package com.jurspring.jt.dao;

import com.jurspring.jt.home.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitDAO extends JpaRepository<Recruit,Integer> {

    Recruit findByRecruitId(Integer RecruitId);
//    Page<Recruit> findByRecruitId(String )
    List<Recruit> findByHrId(Integer hrId);
    List<Recruit> findAllByStationNameContainingOrWorkPlaceContaining(String name,String work);
    List<Recruit> findByPositionTypeId(Integer id);
    List<Recruit> findAllByStationNameContainingOrWorkPlaceContainingAndPositionTypeId(String name,String work,Integer id);


}
