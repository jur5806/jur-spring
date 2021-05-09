package com.jurspring.jt.dao;



import com.jurspring.jt.home.PointVeiw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointVeiwDAO extends JpaRepository<PointVeiw,Integer> {
    PointVeiw findBypointsId(int pointsId);
    List<PointVeiw> findAllByuserId(int userId);
    List<PointVeiw> findAllByname(String keyword1);
}
