package com.jurspring.jt.service;

import com.jurspring.jt.dao.IntegeralDAO;
import com.jurspring.jt.home.Integeral;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class IntegeralService {
    @Autowired
    IntegeralDAO integeralDAO;

    public List<Integeral> list() {

        return integeralDAO.findAll();
    }

    public void updateIntegeralSet(Integeral integeral){
        log.info(String.valueOf(integeral));
        integeralDAO.save(integeral);

    }
}
