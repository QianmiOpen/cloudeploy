package com.qianmi.form;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实例创建结果
 */
public class InstanceResult {

    /**
     * 实例ID
     */
    private String instanceId;

    /**
     * 实例IP
     */
    private String ip;

    public InstanceResult(String instanceId, String ip) {
        this.instanceId = instanceId;
        this.ip = ip;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("instanceId", instanceId)
                .append("ip", ip)
                .toString();
    }
}
