package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 执法文书模板
 * </p>
 *
 * @author zqf
 * @since 2018-01-19
 */
@TableName("bs_law_doc")
public class LawDoc extends Model<LawDoc> {

    private static final long serialVersionUID = 1L;

    /**
     * Law enforcement documents
     */
	@TableId(value="id", type= IdType.AUTO)
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
     * 模板文件路径
     */
	private String ldfileurl;

	private Integer crtuserid;
	
	public Integer getCrtuserid() {
		return crtuserid;
	}
	public Integer getId() {
		return id;
	}

	public void setCrtuserid(Integer crtuserid) {
		this.crtuserid = crtuserid;
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
		return "LawDoc{" +
			"id=" + id +
			",crtuserid="+crtuserid+
			", ldcode=" + ldcode +
			", ldname=" + ldname +
			", ldtype=" + ldtype +
			", ldstatus=" + ldstatus +
			", ldno=" + ldno +
			", ldfileurl=" + ldfileurl +
			"}";
	}
}
