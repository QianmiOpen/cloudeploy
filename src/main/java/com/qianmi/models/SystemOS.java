package com.qianmi.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 平台操作系统配置
 */
public class SystemOS implements java.io.Serializable {
    /**
     * 操作系统
     * CentOS
     */
    private String name;
    /**
     * 操作系统位数(64,32)
     * 64
     */
    private String bit;
    /**
     * 操作系统版本(14.04)
     */
    private String version;

    /**
     * 平台(aliyun,ucloud)
     */
    private String platform;

    /**
     * 平台的image名称
     */
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("bit", bit)
                .append("version", version)
                .append("platform", platform)
                .append("image", image)
                .toString();
    }
}
