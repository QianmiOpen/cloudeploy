package com.qianmi.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 区域
 */
public class Region {
    /**
     * 区域id
     */
    private String id;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 平台名称
     */
    private String platform;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .append("id", id)
                .append("name", name)
                .append("platform", platform)
                .toString();
    }
}
