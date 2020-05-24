package com.ziroom.zftools.entity;

// 没对接ehr，暂时不支持部门功能
public class Department {

    // 序号
    private int id;
    // 部门编号
    private String departmentId;
    // 部门名称
    private String departmentName;

    public Department() {
    }

    public Department(int id, String departmentId, String departmentName) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

}
