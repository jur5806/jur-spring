package com.jurspring.jt.dao;

import com.jurspring.jt.home.Reason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonDAO extends JpaRepository<Reason,Integer> {
    Reason findByreasonId(int reasonId);

}
