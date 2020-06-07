package com.hjy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hjy.entity.User;
import com.hjy.repository.UserRepository;
import com.hjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/employee") //加前缀
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private  UserRepository userRepository;


    //插入
    @RequestMapping(value = "/inster",method = RequestMethod.POST)
    public String  inster( User user, @RequestHeader(value = "token") String token) throws JsonProcessingException {


        return userService.inster(user);

    }

    //查找全部
    @RequestMapping(value = "/findAll",method = RequestMethod.GET )
    public List<User> findAll(){
        return userService.findAll();
    }


//    根据Id查找单个
    @RequestMapping(value = "findOne",method = RequestMethod.POST)
    public  List<User>  findOne()
    {
        String name = "张";
        String password = "180";
        List<User> use = userRepository.findByNameAndPassword(name,password);

        return use;
    }





}
