package com.hjy.service;

import com.hjy.entity.attendance;
import com.hjy.entity.employee;
import com.hjy.repository.EmployeeRepository;
import com.hjy.repository.attendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class attendceService {
    @Autowired
    private attendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository EmployeeRepository;

    public List<attendance> findAll(){
        List<attendance> res = attendanceRepository.findAll();
        return  res;
    }


    public Map insert(attendance body) {

        Map map = new HashMap();

        attendance res = new attendance();

        System.out.println(body);

        res.setId(body.getId());//本人Id
        res.setEid(body.getEid()); //员工Id
        res.setUpaddress(body.getUpaddress()); //上班地点
        res.setDownaddress(body.getDownaddress());//下班地点
        res.setWorkup(body.getWorkup());//上班时间
        res.setWorkdown(body.getWorkdown());//下班时间
        res.setWorktime(body.getWorktime()); //上班日期
        res.setStatus(body.getStatus());




        attendance res2 = attendanceRepository.save(res);



        map.put("msg","插入成功");
        map.put("code","200");
        map.put("id",res.getId());

        return map;
    }

    public Map getIdfindAttendance(int id) {
        Map map = new HashMap();


//        登录进去 得到员工Id 根据员工Id 查找考勤信息

        employee emp = EmployeeRepository.findById(id).get();



        List<attendance> res = attendanceRepository.findByEid(emp.getId());

        map.put("msg","查询成功");
        map.put("code","200");
        map.put("data",res);
        map.put("empdata",emp);

        return map;
    }



//    根据时间与员工Id来查找考勤表中的信息 然后修改考勤状态
     public Map findByWorktimeAndEid(String worktime,int eid,String type){

         Map map = new HashMap();
         attendance res = attendanceRepository.findByWorktimeAndEid(worktime,eid);
         attendance obj = new attendance();
         obj.setId(res.getId());
         obj.setEid(res.getEid());
         obj.setWorktime(res.getWorktime());
         obj.setWorkdown(res.getWorkdown());
         obj.setWorkup(res.getWorkup());
         obj.setDownaddress(res.getDownaddress());
         obj.setUpaddress(res.getUpaddress());
         obj.setStatus(type);
         attendanceRepository.save(obj);
         map.put("msg","查询成功");
         map.put("code","200");
         map.put("data",res);
         return map;
     }

    public Map emloyeeIfSign(String worktime,int eid){

        Map map = new HashMap();
        attendance res = attendanceRepository.findByWorktimeAndEid(worktime,eid);

        map.put("msg","查询成功");
        map.put("code","200");
        map.put("data",res);
        return map;
    }



//    暂留
    public Map  getAllempAndAcc(){



//        查询所有员工的信息 所有员工的信息再查询考勤记录

        List<employee> emp = EmployeeRepository.findAll();

        System.out.println( emp.getClass());


        Map map = new HashMap();

        map.put("msg","查询成功");
        map.put("code","200");
        map.put("data",emp);

        return map;
    }


}
