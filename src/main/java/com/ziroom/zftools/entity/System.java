package com.ziroom.zftools.entity;

public class System {

    // 系统编号
    private int sysId;
    // 系统名称
    private String sysName;
    // 系统域名（测试）
    private String tSysAddress;
    // 系统ip地址（测试）
    private String tSysIp;
    // 系统域名（准生产）
    private String qSysAddress;
    // 系统ip地址（准生产）
    private String qSysIp;
    // 系统关联数据库的id
    private int dbId;
    // 关联Database对象
    private Database database;

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String gettSysAddress() {
        return tSysAddress;
    }

    public void settSysAddress(String tSysAddress) {
        this.tSysAddress = tSysAddress;
    }

    public String gettSysIp() {
        return tSysIp;
    }

    public void settSysIp(String tSysIp) {
        this.tSysIp = tSysIp;
    }

    public String getqSysAddress() {
        return qSysAddress;
    }

    public void setqSysAddress(String qSysAddress) {
        this.qSysAddress = qSysAddress;
    }

    public String getqSysIp() {
        return qSysIp;
    }

    public void setqSysIp(String qSysIp) {
        this.qSysIp = qSysIp;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public System() {
    }

    public System(int sysId, String sysName, String tSysAddress, String tSysIp, String qSysAddress, String qSysIp, int dbId, Database database) {
        this.sysId = sysId;
        this.sysName = sysName;
        this.tSysAddress = tSysAddress;
        this.tSysIp = tSysIp;
        this.qSysAddress = qSysAddress;
        this.qSysIp = qSysIp;
        this.dbId = dbId;
        this.database = database;
    }

    @Override
    public String toString() {
        return "System{" +
                "sysId=" + sysId +
                ", sysName='" + sysName + '\'' +
                ", tSysAddress='" + tSysAddress + '\'' +
                ", tSysIp='" + tSysIp + '\'' +
                ", qSysAddress='" + qSysAddress + '\'' +
                ", qSysIp='" + qSysIp + '\'' +
                ", dbId=" + dbId +
                ", database=" + database +
                '}';
    }

}
