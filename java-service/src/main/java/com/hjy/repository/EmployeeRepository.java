package com.hjy.repository;

import com.hjy.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<employee, Object> {

    employee findByPasswordAndNum(String password, String num);


//    模糊查询
    @Query("select u from employee u where u.username like %?1%") //模糊查询
    List<employee> findAllByUsernameLike(@Param("username") String username);


}
