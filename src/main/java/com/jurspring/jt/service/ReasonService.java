package com.jurspring.jt.service;

import com.jurspring.jt.dao.ReasonDAO;
import com.jurspring.jt.home.Integeral;
import com.jurspring.jt.home.PointVeiw;
import com.jurspring.jt.home.Reason;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReasonService {
    @Autowired
    ReasonDAO reasonDAO;

    public List<Reason> findAllList() {
        return reasonDAO.findAll();
    }

    public void updata(Reason reason){
        reasonDAO.save(reason);

    }

    public void deleteById(int id) {
        reasonDAO.deleteById(id);
    }
}
