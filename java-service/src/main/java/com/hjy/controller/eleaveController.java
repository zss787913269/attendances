package com.hjy.controller;

import com.hjy.entity.eleave;
import com.hjy.service.eleaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/leave") //加前缀
@CrossOrigin
public class eleaveController {

    @Autowired
    private eleaveService eleaveService;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public Map showEmployee(eleave body){
        return  eleaveService.create(body);
    }


    @RequestMapping(value = "getLeaveMsg",method = RequestMethod.POST)
    public Map getLeaveMsg(int id){
        return  eleaveService.getLeaveMsg(id);
    }


    @RequestMapping(value = "getEmployeeLeaveMsg",method = RequestMethod.GET)
    public Map getEmployeeLeaveMsg(){
        return  eleaveService.getEmployeeLeaveMsg();
    }


}
