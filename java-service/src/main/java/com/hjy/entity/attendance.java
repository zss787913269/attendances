package com.hjy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class attendance {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private int id;
            private int eid;//员工Id
            private String workup; //上班时间
            private String workdown; //下班时间
            private String upaddress; //上班地点
            private String downaddress; //下班地点
            private String worktime; //签到日期

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;//状态 0是正常 1是请假 2是打卡异常

    @Override
    public String toString() {
        return "attendanceRepository{" +
                "id=" + id +
                ", eid=" + eid +
                ", workup='" + workup + '\'' +
                ", workdown='" + workdown + '\'' +
                ", upaddress='" + upaddress + '\'' +
                ", downaddress='" + downaddress + '\'' +
                ", worktime='" + worktime + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getWorkup() {
        return workup;
    }

    public void setWorkup(String workup) {
        this.workup = workup;
    }

    public String getWorkdown() {
        return workdown;
    }

    public void setWorkdown(String workdown) {
        this.workdown = workdown;
    }

    public String getUpaddress() {
        return upaddress;
    }

    public void setUpaddress(String upaddress) {
        this.upaddress = upaddress;
    }

    public String getDownaddress() {
        return downaddress;
    }

    public void setDownaddress(String downaddress) {
        this.downaddress = downaddress;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }



}
