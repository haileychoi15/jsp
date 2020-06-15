package com.hailey.web.entity;

public class Member {

    private String userid;
    private String passwd;
    private String name;
    private String mobile;
    private int point;
    private int status;
    private String email;
    private String birthday;
    private String gender;

    //기본 생성자
    public Member(){

    }

    // 오버로드 생성자
    public Member(String userid, String passwd, String name, String mobile, int point, int status, String email, String birthday, String gender) {
        this.userid = userid;
        this.passwd = passwd;
        this.name = name;
        this.mobile = mobile;
        this.point = point;
        this.status = status;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
