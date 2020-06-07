package com.hjy.controller;

import com.hjy.entity.bussiness;
import com.hjy.entity.notice;
import com.hjy.entity.pcontact;
import com.hjy.service.noticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice") //加前缀
@CrossOrigin
public class noticeController {

    @Autowired
    private noticeService noticeService;


    //插入通知
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Map insert(notice body) {
        return noticeService.insert(body);
    }

//    全部通知
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<notice> findAll() {
        return noticeService.findAll();
    }

    //    根据传过来的Id 查询公告
    @RequestMapping(value = "getIdfindNotice", method = RequestMethod.POST)
    public Map getIdfindNotice(int id) {

        return noticeService.getIdfindNotice(id);
    }

    @RequestMapping(value = "deleteNotice", method = RequestMethod.POST)
    public Map deleteNotice(int id) {

        return noticeService.deleteNotice(id);
    }


}
