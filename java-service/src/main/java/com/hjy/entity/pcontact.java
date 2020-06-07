package com.hjy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class pcontact {


    @Override
    public String toString() {
        return "pcontactRepository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                ", ps='" + ps + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", wechat='" + wechat + '\'' +
                ", nickname='" + nickname + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;//姓名 必填 1
    private String gender;//性别 可选2
    private String phone;//电话号码 可选3
    private String company;//单位名称 可选4
    private String ps;//备注 可选5
    private String age;//年龄 可选6
    private String email;//电子邮件7
    private String address;//公司地址8
    private String wechat;//微信号码9
    private String nickname;//昵称 小赵10
    private String position;//职务 销售主管 11

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



}
