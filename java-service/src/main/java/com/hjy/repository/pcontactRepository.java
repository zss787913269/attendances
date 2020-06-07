package com.hjy.repository;

import com.hjy.entity.employee;
import com.hjy.entity.pcontact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface pcontactRepository extends JpaRepository<pcontact,Object> {
    //    模糊查询
    @Query("select u from pcontact u where u.name like %?1%") //模糊查询
    List<pcontact> findAllByUsernameLike(@Param("name") String username);
}
