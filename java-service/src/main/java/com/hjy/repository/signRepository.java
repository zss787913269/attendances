package com.hjy.repository;

import com.hjy.entity.eleave;
import com.hjy.entity.sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface signRepository extends JpaRepository<sign,Object> {
    @Query("select u from sign u where u.uid = ?1")
    List<sign> findByUid(int id);
}
