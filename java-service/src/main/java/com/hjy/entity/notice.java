package com.hjy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;//公告标题 会议

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;//公告标题 会议

    @Override
    public String toString() {
        return "notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", bid='" + bid + '\'' +
                '}';
    }

    private String msg;//公告内容   今天下午十点大家开个会
    private String time;//公告创建时间  提交的时候 会自动生成提交的时间
    private int bid;//公告创建者的id 只有老板才能发布公告 员工只能查看全部公告内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public  int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }



}
