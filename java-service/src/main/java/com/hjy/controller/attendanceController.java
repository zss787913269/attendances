package com.hjy.controller;

import com.hjy.entity.attendance;
import com.hjy.entity.employee;
import com.hjy.service.attendceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance") //加前缀
@CrossOrigin

public class attendanceController {


    @Autowired
    private attendceService attendceService;


//    查找所有考勤记录

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public List<attendance> findAll(){
        return  attendceService.findAll();
    }

//    插入考勤记录

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public  Map insert(attendance  body){
        return attendceService.insert(body);
    }

//    根据传过来的Id 查询考勤记录

    @RequestMapping(value = "getIdfindAttendance",method = RequestMethod.POST)
    public  Map getIdfindAttendance(int id){

        return attendceService.getIdfindAttendance(id);
    }
//    根据日期与id 修改员工的考勤记录
    @RequestMapping(value = "findByWorktimeAndEid",method = RequestMethod.POST)
    public  Map findByWorktimeAndEid(String worktime,int eid,String type){

        return attendceService.findByWorktimeAndEid(worktime,eid,type);
    }

    @RequestMapping(value = "emloyeeIfSign",method = RequestMethod.POST)
    public  Map emloyeeIfSign(String worktime,int eid){

        return attendceService.emloyeeIfSign(worktime,eid);
    }


    @RequestMapping(value = "getAllempAndAcc",method = RequestMethod.GET)
    public  Map getAllempAndAcc(){
        return attendceService.getAllempAndAcc();
    }



}
