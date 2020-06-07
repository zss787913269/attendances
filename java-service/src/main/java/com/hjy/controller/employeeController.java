package com.hjy.controller;

import com.hjy.entity.employee;
import com.hjy.repository.EmployeeRepository;
import com.hjy.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee") //加前缀
@CrossOrigin

public class employeeController {

    @Autowired
    private employeeService  employeeService;

    @Autowired
    private EmployeeRepository EmployeeRepository;



//    用户登录   /login
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(String password, String num){

        return employeeService.login(password, num);
    }

//    查询员工信息信息
        @RequestMapping(value = "getMsg",method = RequestMethod.POST)
        public  employee getMsg(int id){
            return employeeService.getMsg(id);
    }

    //    修改或者添加员工信息
    @RequestMapping(value = "editOrAddMsg",method = RequestMethod.POST)
    public  Map editOrAddMsg(employee body){
        return employeeService.editOrAddMsg(body);
    }
    //    增加员工信息
//    @RequestMapping(value = "createMsg",method = RequestMethod.POST)
//    public  Map createMsg(employee body){
//        return employeeService.editMsg(body);
//    }
//    展示所有员工信息
    @RequestMapping(value = "showEmployee",method = RequestMethod.GET)
    public  List<employee>  showEmployee(){
        return  employeeService.showEmployee();
    }

//    根据id删除多个或者单个员工
    @RequestMapping(value = "deleteByIds",method = RequestMethod.POST)
    public Map deleteEmployee(int id){
        return employeeService.deleteByIds(id);
    }

//    根据名字模糊查询
    @RequestMapping(value = "findByNameLike",method = RequestMethod.POST)
    public Map findByUsernameLike(String username){
        return employeeService.findByUsernameLike(username);
    }


}
