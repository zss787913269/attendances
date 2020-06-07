package com.hjy.repository;

import com.hjy.entity.attendance;
import com.hjy.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface attendanceRepository extends JpaRepository<attendance,Object> {



//    attendance findByPasswordAndNum(String password, String num);

        attendance findByWorktimeAndEid(String worktime, int eid);


    @Query("select u from attendance u where u.eid = ?1")
    List<attendance> findByEid(int id);

}
