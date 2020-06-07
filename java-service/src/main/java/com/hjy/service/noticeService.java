package com.hjy.service;

import com.hjy.entity.bussiness;
import com.hjy.entity.employee;
import com.hjy.entity.notice;
import com.hjy.repository.EmployeeRepository;
import com.hjy.repository.noticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class noticeService {

    @Autowired
    private noticeRepository noticeRepository;

    @Autowired
    private EmployeeRepository EmployeeRepository;

    //    发布通知 修改通知
    public Map insert(notice body){
        Map map = new HashMap();
        notice res = new notice();
        res.setId(body.getId());
        res.setTime(body.getTime());
        res.setBid(body.getBid());
        res.setMsg(body.getMsg());
        res.setTitle(body.getTitle());

        noticeRepository.save(res);
        map.put("msg","插入成功");
        map.put("code","200");
        return map;
    }
    //查找全部通知记录
    public List<notice> findAll(){
        List<notice> res = noticeRepository.findAll();



        for(notice i: res) {
            Optional<employee> optional = EmployeeRepository.findById(i.getBid());
            i.setName(optional.orElse(new employee()).getUsername());
        }


        return res;
    }

//    查询自己发布的通知
    public  Map getIdfindNotice(int id) {
        System.out.println(id);
        Map map = new HashMap();
        employee emp = EmployeeRepository.findById(id).get();
        List<notice> res = noticeRepository.findByBid(emp.getId());
        map.put("msg","查询成功");
        map.put("code","200");
        map.put("data",res);

        return map;
    }

//    删除发布的通知
    public Map deleteNotice(int id){
        Map map = new HashMap();
        noticeRepository.deleteById(id);

        map.put("msg","删除成功");
        map.put("code","200");

        return map;
    }



}
