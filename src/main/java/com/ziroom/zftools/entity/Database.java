package com.ziroom.zftools.entity;

public class Database {

    // 数据库编号
    private int id;
    // 数据库种类
    private String dbCategory;
    // 数据库ip地址（测试）
    private String tDbIp;
    // 数据库登录名（测试）
    private String tDbUsername;
    // 数据库登录密码（测试）
    private String tDBPassword;
    // 数据库ip地址（准生产）
    private String qDbIp;
    // 数据库登录名（准生产）
    private String qDbUsername;
    // 数据库登录密码（准生产）
    private String qDbPassword;
    // 数据库关联系统的id
    private String sysId;
    // 关联System对象
    private System system;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbCategory() {
        return dbCategory;
    }

    public void setDbCategory(String dbCategory) {
        this.dbCategory = dbCategory;
    }

    public String gettDbIp() {
        return tDbIp;
    }

    public void settDbIp(String tDbIp) {
        this.tDbIp = tDbIp;
    }

    public String gettDbUsername() {
        return tDbUsername;
    }

    public void settDbUsername(String tDbUsername) {
        this.tDbUsername = tDbUsername;
    }

    public String gettDBPassword() {
        return tDBPassword;
    }

    public void settDBPassword(String tDBPassword) {
        this.tDBPassword = tDBPassword;
    }

    public String getqDbIp() {
        return qDbIp;
    }

    public void setqDbIp(String qDbIp) {
        this.qDbIp = qDbIp;
    }

    public String getqDbUsername() {
        return qDbUsername;
    }

    public void setqDbUsername(String qDbUsername) {
        this.qDbUsername = qDbUsername;
    }

    public String getqDbPassword() {
        return qDbPassword;
    }

    public void setqDbPassword(String qDbPassword) {
        this.qDbPassword = qDbPassword;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Database() {
    }

    public Database(int id, String dbCategory, String tDbIp, String tDbUsername, String tDBPassword, String qDbIp, String qDbUsername, String qDbPassword, String sysId, System system) {
        this.id = id;
        this.dbCategory = dbCategory;
        this.tDbIp = tDbIp;
        this.tDbUsername = tDbUsername;
        this.tDBPassword = tDBPassword;
        this.qDbIp = qDbIp;
        this.qDbUsername = qDbUsername;
        this.qDbPassword = qDbPassword;
        this.sysId = sysId;
        this.system = system;
    }

    @Override
    public String toString() {
        return "Database{" +
                "id=" + id +
                ", dbCategory='" + dbCategory + '\'' +
                ", tDbIp='" + tDbIp + '\'' +
                ", tDbUsername='" + tDbUsername + '\'' +
                ", tDBPassword='" + tDBPassword + '\'' +
                ", qDbIp='" + qDbIp + '\'' +
                ", qDbUsername='" + qDbUsername + '\'' +
                ", qDbPassword='" + qDbPassword + '\'' +
                ", sysId='" + sysId + '\'' +
                ", system=" + system +
                '}';
    }

}
