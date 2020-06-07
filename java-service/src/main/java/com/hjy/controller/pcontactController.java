package com.hjy.controller;

import com.hjy.entity.User;
import com.hjy.entity.employee;
import com.hjy.entity.pcontact;
import com.hjy.service.pcontactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contact") //加前缀
@CrossOrigin
public class pcontactController {

    @Autowired
    private pcontactService pcontactService;

    //查找全部
    @RequestMapping(value = "/hello",method = RequestMethod.GET )
    public String findAll(){
        return pcontactService.hello();
    }

    //    查询员工信息信息
    @RequestMapping(value = "getMsg",method = RequestMethod.POST)
    public pcontact getMsg(int id){
        return pcontactService.getMsg(id);
    }

    //    修改员工信息
    @RequestMapping(value = "editOrAddMsg",method = RequestMethod.POST)
    public Map editOrAddMsg(pcontact body){
        return pcontactService.editOrAddMsg(body);
    }
    //    增加员工信息
//    @RequestMapping(value = "createMsg",method = RequestMethod.POST)
//    public  Map createMsg(employee body){
//        return employeeService.editMsg(body);
//    }
//    展示所有员工信息
    @RequestMapping(value = "showEmployee",method = RequestMethod.GET)
    public  List<pcontact>  showEmployee(){
        return  pcontactService.showEmployee();
    }

    //    根据id删除多个或者单个员工
    @RequestMapping(value = "deleteByIds",method = RequestMethod.POST)
    public Map deleteEmployee(int id){
        return pcontactService.deleteByIds(id);
    }

    //    根据名字模糊查询
    @RequestMapping(value = "findByNameLike",method = RequestMethod.POST)
    public Map findByUsernameLike(String name){
        return pcontactService.findByUsernameLike(name);
    }
}
