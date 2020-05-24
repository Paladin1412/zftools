package com.ziroom.zftools.entity;

public class User {

    // 序号
    private int id;
    // 用户编号
    private String uid;
    // 用户名称
    private String username;
    // 用户密码
    private String password;
    // 部门编号
    private String departmentId;

    public User() {
    }

    public User(int id, String uid, String username, String password, String departmentId) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }

}
