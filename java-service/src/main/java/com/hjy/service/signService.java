package com.hjy.service;

import com.hjy.entity.eleave;
import com.hjy.entity.employee;
import com.hjy.entity.sign;
import com.hjy.repository.EmployeeRepository;
import com.hjy.repository.eleaveRepository;
import com.hjy.repository.signRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class signService {


    @Autowired
    private signRepository signRepository; //补签表

    @Autowired
    private EmployeeRepository EmployeeRepository; //员工表



    //员工补签申请
    public Map create(sign body){
        Map map = new HashMap();
        sign res = new sign();
        res.setId(body.getId());//补签数据Id
        res.setReason(body.getReason());//补签理由
        res.setStatus(body.getStatus());//补签状态 0 未审批 默认是0   1是同意 2是不同意
        res.setTimeStart(body.getTimeStart());//补签时间
        res.setUid(body.getUid());//补签员工
        res.setCreatetime(body.getCreatetime());//提交时间
        sign save = signRepository.save(res);
        map.put("code",200);
        map.put("msg","插入成功");
        return map;
    }


    //根据Id查找请假列表
    public Map getSignMsg(int id){
        Map map = new HashMap();

//        找到员工id
        employee emp = EmployeeRepository.findById(id).get();

        System.out.println(emp.getId());
        List<sign> res = signRepository.findByUid(emp.getId());

        map.put("code",200);
        map.put("data",res);
        return map;

    }

    public List<sign> findAll(){

        List<sign> res = signRepository.findAll();
        for(sign i: res) {
            Optional<employee> optional = EmployeeRepository.findById(i.getUid());
            i.setName(optional.orElse(new employee()).getUsername());
        }

        return res;
    }

}
