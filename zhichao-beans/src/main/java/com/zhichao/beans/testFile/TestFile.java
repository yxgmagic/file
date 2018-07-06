package com.zhichao.beans.testFile;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author yanxingui123
 * @since 2018-06-26
 */
@TableName("test_file")
public class TestFile extends Model<TestFile> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 执法文书编号
     */
    private String ldcode;
    /**
     * 执法文书名称
     */
    private String ldname;
    /**
     * 执法文书类型
     */
    private String ldtype;
    /**
     * 执法文书状态
     */
    private String ldstatus;
    /**
     * 序号
     */
    private Integer ldno;
    /**
     * 创建人
     */
    private Integer crtuserid;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtdate;
    /**
     * 模板文件路径
     */
    private String ldfileurl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLdcode() {
        return ldcode;
    }

    public void setLdcode(String ldcode) {
        this.ldcode = ldcode;
    }

    public String getLdname() {
        return ldname;
    }

    public void setLdname(String ldname) {
        this.ldname = ldname;
    }

    public String getLdtype() {
        return ldtype;
    }

    public void setLdtype(String ldtype) {
        this.ldtype = ldtype;
    }

    public String getLdstatus() {
        return ldstatus;
    }

    public void setLdstatus(String ldstatus) {
        this.ldstatus = ldstatus;
    }

    public Integer getLdno() {
        return ldno;
    }

    public void setLdno(Integer ldno) {
        this.ldno = ldno;
    }

    public Integer getCrtuserid() {
        return crtuserid;
    }

    public void setCrtuserid(Integer crtuserid) {
        this.crtuserid = crtuserid;
    }

    public Date getCrtdate() {
        return crtdate;
    }

    public void setCrtdate(Date crtdate) {
        this.crtdate = crtdate;
    }

    public String getLdfileurl() {
        return ldfileurl;
    }

    public void setLdfileurl(String ldfileurl) {
        this.ldfileurl = ldfileurl;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TestFile{" +
        "id=" + id +
        ", ldcode=" + ldcode +
        ", ldname=" + ldname +
        ", ldtype=" + ldtype +
        ", ldstatus=" + ldstatus +
        ", ldno=" + ldno +
        ", crtuserid=" + crtuserid +
        ", crtdate=" + crtdate +
        ", ldfileurl=" + ldfileurl +
        "}";
    }
}
