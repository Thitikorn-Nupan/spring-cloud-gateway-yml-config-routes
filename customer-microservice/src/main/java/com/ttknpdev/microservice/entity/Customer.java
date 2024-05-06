package com.ttknpdev.microservice.entity;

public class Customer {
    private Long cid;
    private String fullname;
    private Short age;
    private Character level;

    public Customer(Long cid, String fullname, Short age, Character level) {
        this.cid = cid;
        this.fullname = fullname;
        this.age = age;
        this.level = level;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Character getLevel() {
        return level;
    }

    public void setLevel(Character level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", fullname='" + fullname + '\'' +
                ", age=" + age +
                ", level=" + level +
                '}';
    }
}
