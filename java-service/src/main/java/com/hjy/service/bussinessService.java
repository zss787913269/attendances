package com.hjy.service;

import com.hjy.entity.attendance;
import com.hjy.entity.bussiness;
import com.hjy.entity.employee;
import com.hjy.entity.notice;
import com.hjy.repository.EmployeeRepository;
import com.hjy.repository.bussinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class bussinessService {

    @Autowired
    private bussinessRepository bussinessRepository;

    @Autowired
    private EmployeeRepository EmployeeRepository;

//    出差登记
    public  Map insert( bussiness body){
        Map map = new HashMap();
        bussiness res = new bussiness();
        res.setTimeStart(body.getTimeStart());
        res.setTimeEnd(body.getTimeEnd());
        res.setReason(body.getReason());
        res.setUid(body.getUid());
        res.setId(body.getId());
        res.setAddress(body.getAddress());

        bussinessRepository.save(res);


        map.put("msg","插入成功");
        map.put("code","200");

        return map;

    }

    //查找全部出差记录
    public List<bussiness> findAll(){
        List<bussiness> res = bussinessRepository.findAll();
        for(bussiness i: res) {
            Optional<employee> optional = EmployeeRepository.findById(i.getUid());
            i.setName(optional.orElse(new employee()).getUsername());
        }

        return  res;
    }

    public  Map getIdfindBussiness(int id) {
        Map map = new HashMap();



        employee emp = EmployeeRepository.findById(id).get();

        List<bussiness> res = bussinessRepository.findByUid(emp.getId());

        map.put("msg","查询成功");
        map.put("code","200");
        map.put("data",res);
        map.put("empdata",emp);

        return map;
    }

}
