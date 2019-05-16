package com.mmall.demo2.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Permission {
    private Integer pid;
    private String name;
    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pid", pid)
                .append("name", name)
                .append("url", url)
                .toString();
    }
}
