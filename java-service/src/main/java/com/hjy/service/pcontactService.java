package com.hjy.service;

import com.hjy.entity.employee;
import com.hjy.entity.pcontact;
import com.hjy.repository.pcontactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class pcontactService {

    @Autowired
    private pcontactRepository  pcontactRepository;


    public String  hello(){

        return "hellO";
    }

    //    个人信息
    public pcontact getMsg(int id){

        pcontact res = pcontactRepository.findById(id).get();

        return res;
    }


    //    修改个人信息
    public Map editOrAddMsg(pcontact body){
        Map<Object, Object> map = new HashMap<>();
        pcontact emp = new pcontact();
        emp.setId(body.getId());
        emp.setGender(body.getGender());//性别 1
        emp.setPosition(body.getPosition());//职位 2
        emp.setName(body.getName());//姓名 3
        emp.setPhone(body.getPhone());//电话号码 4
        emp.setCompany(body.getCompany());//公司姓名5
        emp.setAddress(body.getAddress());//公司地址6
        emp.setPs(body.getPs());//备注7
        emp.setEmail(body.getEmail());//邮件 8
        emp.setNickname(body.getNickname());//昵称9
        emp.setAge(body.getAge()); //年龄10
        emp.setWechat(body.getWechat());//微信

        pcontact emp2 = pcontactRepository.save(emp);
        map.put("msg","成功");
        map.put("code","200");
        return map;
    }

//  展示所有员工信息

    public List<pcontact> showEmployee (){

        List<pcontact> obj = pcontactRepository.findAll();
        return  obj;
    }

    //    按照名字搜索某个员工
    public Map findByUsernameLike (String name){
        Map<Object, Object> map = new HashMap<>();

        List<pcontact>   emp= pcontactRepository.findAllByUsernameLike(name);

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
        System.out.println(id);
        Map<Object, Object> map = new HashMap<>();
        pcontactRepository.deleteById(id);
        map.put("msg","删除成功");
        map.put("code","200");
        return map;
    }

}

