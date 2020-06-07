package com.hjy.repository;

import com.hjy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Object> {


    List<User> findByNameAndPassword(String name, String password);
}
