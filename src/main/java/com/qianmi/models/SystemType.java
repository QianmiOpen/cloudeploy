package com.qianmi.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 平台机器配置类型
 */
public class SystemType implements java.io.Serializable {
    /**
     * cpu个数
     */
    private int cpu;
    /**
     * 内存大小
     */
    private int memory;

    /**
     * 平台(aliyun,ucloud)
     */
    private String platform;

    /**
     * 规格类型代码
     */
    private String code;

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cpu", cpu)
                .append("memory", memory)
                .append("platform", platform)
                .append("code", code)
                .toString();
    }
}
