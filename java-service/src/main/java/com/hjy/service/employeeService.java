package com.hjy.service;

import com.hjy.entity.employee;
import com.hjy.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class employeeService {
    @Autowired
    private EmployeeRepository EmployeeRepository;




//登录接口

    public Map login(String password, String num)
    {

        employee emp = EmployeeRepository.findByPasswordAndNum(password,num);

        System.out.println(password);
        System.out.println(num);
            Map<Object, Object> map = new HashMap<>();
                if(emp != null){
                    int id = emp.getId();
                    String username = emp.getUsername();
                    String department = emp.getDepartment();
                    map.put("code","200");
                    map.put("msg","登录成功");
                    map.put("id",id);
                    map.put("name",username);
                    map.put("department",department);
                }else{
                    map.put("code","400");
                    map.put("msg","登录失败");
                }


        return map;
    }

//    个人信息
    public employee getMsg(int id){

        employee res = EmployeeRepository.findById(id).get();

        return res;
    }


//    修改或者添加个人信息
    public Map editOrAddMsg(employee body){
        Map<Object, Object> map = new HashMap<>();


        employee emp = new employee();
        emp.setId(body.getId());
        emp.setDepartment(body.getDepartment());//部门 1
        emp.setGender(body.getGender());//性别 2
        emp.setNum(body.getNum());//登录账号 员工编号 3
        emp.setPassword(body.getPassword());//登录密码 4
        emp.setPosition(body.getPosition());//职位 5
        emp.setRole(body.getRole());//角色 6
        emp.setUsername(body.getUsername());//姓名 7
        emp.setPhone(body.getPhone());//电话号码 8
        emp.setAddress(body.getAddress());//地址 9
        emp.setEmail(body.getEmail());//邮件 10
        emp.setJointime(body.getJointime());//入职时间 11
        emp.setAge(body.getAge()); //年龄



        employee emp2 = EmployeeRepository.save(emp);


        map.put("msg","成功");
        map.put("code","200");
        return map;

    }

//  展示所有员工信息

    public List<employee> showEmployee (){

     List<employee> obj = EmployeeRepository.findAll();
        return  obj;
    }

//    按照名字搜索某个员工
    public Map findByUsernameLike (String name){
        Map<Object, Object> map = new HashMap<>();

        List<employee>   emp= EmployeeRepository.findAllByUsernameLike(name);

        if(emp != null){
            map.put("code","200");
            map.put("data",emp);
        }else{
            map.put("code","400");
            map.put("data","查询失败");
        }
        return  map;
    }


//   删除员工
    public Map deleteByIds(int id) {
        Map<Object, Object> map = new HashMap<>();
        EmployeeRepository.deleteById(id);
        map.put("msg","删除成功");
        map.put("code","200");
        return map;
    }


}
