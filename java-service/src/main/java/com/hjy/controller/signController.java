package com.hjy.controller;


import com.hjy.entity.eleave;
import com.hjy.entity.sign;
import com.hjy.service.eleaveService;
import com.hjy.service.signService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sign") //加前缀 192.168.31.95:8880/sign/create
@CrossOrigin
public class signController {

    @Autowired
    private signService signService;

//    补签申请
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Map showEmployee(sign body){
        return  signService.create(body);
    }


    //根据员工Id查找属于自己的补签单
    @RequestMapping(value = "getSignMsg",method = RequestMethod.POST)
    public Map getLeaveMsg(int id){
        return  signService.getSignMsg(id);
    }

//    老板查找所有的补签申请单
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public List<sign> findAll(){
        return  signService.findAll();
    }






}
