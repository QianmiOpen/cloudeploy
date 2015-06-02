package com.qianmi.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户实例关系
 */
public class UserInstance {
    /**
     * 用户标示
     */
    private String user;
    /**
     * 实例ID
     */
    private String instanceId;
    /**
     * 实例IP
     */
    private String instanceIp;
    /**
     * 平台名称
     */
    private String platform;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceIp() {
        return instanceIp;
    }

    public void setInstanceIp(String instanceIp) {
        this.instanceIp = instanceIp;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .append("instanceId", instanceId)
                .append("instanceIp", instanceIp)
                .append("platform", platform)
                .toString();
    }
}
