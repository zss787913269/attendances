package com.hjy.repository;

import com.hjy.entity.attendance;
import com.hjy.entity.eleave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface eleaveRepository extends JpaRepository<eleave,Object> {


    @Query("select u from eleave u where u.uid = ?1")
    List<eleave> findByUid(int id);

}
