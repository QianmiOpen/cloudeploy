package com.qianmi.form;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实例查询结果
 */
public class InstanceStatus {
    /**
     * 实例ID
     */
    private String instanceId;
    /**
     * 实例状态
     * Running
     * Stopped
     *
     */
    private String status;

    /**
     * 公网ip
     */
    private String publicIp;

    /**
     * 内网ip
     */
    private String innerIp;

    public InstanceStatus(String instanceId, String status, String publicIp, String innerIp) {
        this.instanceId = instanceId;
        this.status = status;
        this.publicIp = publicIp;
        this.innerIp = innerIp;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getInnerIp() {
        return innerIp;
    }

    public void setInnerIp(String innerIp) {
        this.innerIp = innerIp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("instanceId", instanceId)
                .append("status", status)
                .append("publicIp", publicIp)
                .append("innerIp", innerIp)
                .toString();
    }
}
