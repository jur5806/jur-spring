package com.jurspring.jt.dao;

import com.jurspring.jt.home.Integeral;
import com.jurspring.jt.home.PointVeiw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegeralDAO extends JpaRepository<Integeral,Integer> {
    Integeral findByintegralSetingId(int integralSetingId);
}
