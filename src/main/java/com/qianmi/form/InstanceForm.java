package com.qianmi.form;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实例接口参数
 */
public class InstanceForm {

    /**
     * 用户
     */
    private String user;
    /**
     * 平台
     * aliyun,ucloud
     */
    private String platform;
    /**
     * 区域
     */
    private String region;
    /**
     * 操作系统名称
     * centos
     */
    private String osName;
    /**
     * 操作系统位数(64,32)
     * 64
     */
    private String osBit;
    /**
     * 操作系统版本(6.5)
     */
    private String osVersion;
    /**
     * cpu数量
     * 1,2,4,8,16
     */
    private String cpu;
    /**
     * 内存大小
     * 1,2,4,8,16,32,64
     */
    private String memory;
    /**
     * 机器密码
     * pwd123
     */
    private String password;
    /**
     * 计费方式
     * 按流量计费
     */
    private String internetChargeType;
    /**
     * 出口宽带大小
     * 1~200,默认1M,最大支持到20M.
     */
    private String internetMaxBandwidthOut;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsBit() {
        return osBit;
    }

    public void setOsBit(String osBit) {
        this.osBit = osBit;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInternetChargeType() {
        return internetChargeType;
    }

    public void setInternetChargeType(String internetChargeType) {
        this.internetChargeType = internetChargeType;
    }

    public String getInternetMaxBandwidthOut() {
        return internetMaxBandwidthOut;
    }

    public void setInternetMaxBandwidthOut(String internetMaxBandwidthOut) {
        this.internetMaxBandwidthOut = internetMaxBandwidthOut;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .append("platform", platform)
                .append("region", region)
                .append("osName", osName)
                .append("osBit", osBit)
                .append("osVersion", osVersion)
                .append("cpu", cpu)
                .append("memory", memory)
                .append("password", password)
                .append("internetChargeType", internetChargeType)
                .append("internetMaxBandwidthOut", internetMaxBandwidthOut)
                .toString();
    }
}
