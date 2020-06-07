package com.hjy.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "employee")
public class employee {



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //    用户名 1
    private String username;
//    密码 2
    private String password;
//    性别 3
    private Integer gender;
//    部门 4
    private String department;
//    职位 5
    private  String position;
//    身份 // 0 是员工 1 是老板 2是管理员 6
    private Integer role;
//    编号 7
    private  String num;

//  手机号码 8
    private  String phone;
//   入职时间 9
    private String jointime;

    //    邮件 10
    private  String email;
//    籍贯 11
    private  String address;
//  年龄 12
    private  String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }







}
