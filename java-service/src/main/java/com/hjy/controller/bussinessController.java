package com.hjy.controller;


import com.hjy.entity.attendance;
import com.hjy.entity.bussiness;
import com.hjy.service.bussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bussiness") //加前缀
@CrossOrigin
public class bussinessController {

    @Autowired
    private bussinessService bussnessService;


    //    插入出差记录

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Map insert(bussiness body) {
        return bussnessService.insert(body);
    }

//    查询所有出差记录

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<bussiness> findAll() {
        return bussnessService.findAll();
    }

    //    根据传过来的Id 查询出差记录

    @RequestMapping(value = "getIdfindBussiness", method = RequestMethod.POST)
    public Map getIdfindBussiness(int id) {

        return bussnessService.getIdfindBussiness(id);
    }
}