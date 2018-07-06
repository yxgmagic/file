package com.zhichao.beans.constant;

/**
 * 枚举
 *
 * @author lee
 * @version V1.0.0
 * @date 2017/12/18
 */
public class Enumeration {
    private String code;
    private String name;

    public Enumeration() {
    }

    public Enumeration(String code) {
        this(code, (String) null);
    }

    public Enumeration(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public int hashCode() {
        return this.code == null ? super.hashCode() : this.code.hashCode();
    }

    public boolean equals(Object other) {
        return other == null ? false : (other == this ? true : (this.code != null && !"".equals(this.code.trim()) ? (other instanceof String ? this.code.equals(other) : (other instanceof Enumeration ? (((Enumeration) other).getCode() != null && !"".equals(((Enumeration) other).getCode().trim()) ? this.code.equals(((Enumeration) other).getCode()) : false) : (this.getClass().getPackage() != other.getClass().getPackage() ? false : this.hashCode() == other.hashCode()))) : false));
    }

    public String toString() {
        return this.getCode();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
