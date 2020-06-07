package com.hjy.service;

import com.hjy.entity.attendance;
import com.hjy.entity.bussiness;
import com.hjy.entity.eleave;
import com.hjy.entity.employee;
import com.hjy.repository.EmployeeRepository;
import com.hjy.repository.eleaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class eleaveService {

    @Autowired
    private eleaveRepository  eleaveRepository; //请假表

    @Autowired
    private EmployeeRepository EmployeeRepository; //员工表



    //员工请假申请
    public  Map create(eleave body){
        Map map = new HashMap();
        eleave res = new eleave();
        res.setId(body.getId());//请假Id
        res.setHowLong(body.getHowLong());//请假时长
        res.setTimeStart(body.getTimeStart());//请假开始时间
        res.setTimeEnd(body.getTimeEnd());//请假结束时间
        res.setUid(body.getUid());//员工id
        res.setLeaveType(body.getLeaveType());//请假类型
        res.setReason(body.getReason());//请假理由
        res.setReply(body.getReply());//请假回复
        res.setStatus(body.getStatus());//请假条状态 0审批中 1同意 2拒绝
        res.setCreateTime(body.getCreateTime());//创建时间
        res.setDeal(body.getDeal());
        eleave save = eleaveRepository.save(res);
        map.put("code",200);
        map.put("msg","插入成功");
        return map;
    }

    //根据Id查找请假列表
    public Map getLeaveMsg(int id){
        Map map = new HashMap();


//        找到员工id
        employee emp = EmployeeRepository.findById(id).get();

        System.out.println(emp.getId());
        List<eleave> res = eleaveRepository.findByUid(emp.getId());

        map.put("code",200);
        map.put("msg","查找成功");
        map.put("data",res);
        return map;

    }

    //查找员工的请假记录  -- 暂留
    public Map getEmployeeLeaveMsg(){

        Map map = new HashMap();



        List<eleave> res = eleaveRepository.findAll();

        for(eleave i: res) {
            Optional<employee> optional = EmployeeRepository.findById(i.getUid());
            i.setName(optional.orElse(new employee()).getUsername());
        }


        map.put("code",200);
        map.put("data",res);

        return map;

    }
}
