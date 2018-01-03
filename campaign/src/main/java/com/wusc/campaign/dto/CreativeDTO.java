package com.wusc.campaign.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * create by wusc on 2017/12/21
 */
public class CreativeDTO {
    @Length(min=1,max=10)
    private String name;
    @Length(min=5,max=100)
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
