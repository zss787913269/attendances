package com.hjy.repository;

import com.hjy.entity.attendance;
import com.hjy.entity.bussiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface bussinessRepository  extends JpaRepository<bussiness,Object> {

    @Query("select u from bussiness u where u.uid = ?1")
    List<bussiness> findByUid(int id);
}
