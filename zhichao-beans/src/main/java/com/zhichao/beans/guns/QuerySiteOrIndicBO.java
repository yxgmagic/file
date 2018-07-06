package com.zhichao.beans.guns;

import java.io.Serializable;

/**
 * 站点或指标返回BO类
 */
public class QuerySiteOrIndicBO implements Serializable{

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QuerySiteOrIndicBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
