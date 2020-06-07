package com.hjy.repository;

import com.hjy.entity.bussiness;
import com.hjy.entity.notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface noticeRepository  extends JpaRepository<notice,Object> {

    @Query("select u from notice u where u.bid = ?1")
    List<notice> findByBid(int id);
}
