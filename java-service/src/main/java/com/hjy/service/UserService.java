package com.hjy.service;

import com.hjy.entity.User;
import com.hjy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;


    public String inster(User body){

        User user = new User();
        user.setName(body.getName());
        user.setPassword(body.getPassword());
        user.setStuNo(body.getStuNo());

        userRepository.save(user);
        return "插入成功";
    }

    public List<User> findAll(){
        List<User> res = userRepository.findAll();
        return res;
    }

    public User findOne(int id){


        User res = userRepository.findById(id).get();
        return res;


    }


}
