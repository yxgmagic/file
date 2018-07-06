package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zqf
 * @since 2018-01-24
 */
@TableName("bs_oefull_lawdoc")
public class OefullLawdoc extends Model<OefullLawdoc> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer oefullid;
	private Integer lawdocid;
    /**
     * 业务数据
     */
	private String lawdocjson;
    /**
     * 补充手打输入数据
     */
	private String inputjson;
    /**
     * 处理状态
     */
	private String procstatus;
    /**
     * 文书类型
     */
	private String ldtype;
	private String fileurl;
	private String unfilled;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOefullid() {
		return oefullid;
	}

	public void setOefullid(Integer oefullid) {
		this.oefullid = oefullid;
	}

	public Integer getLawdocid() {
		return lawdocid;
	}

	public void setLawdocid(Integer lawdocid) {
		this.lawdocid = lawdocid;
	}

	public String getLawdocjson() {
		return lawdocjson;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getUnfilled() {
		return unfilled;
	}
	public void setUnfilled(String unfilled) {
		this.unfilled = unfilled;
	}
	public void setLawdocjson(String lawdocjson) {
		this.lawdocjson = lawdocjson;
	}

	public String getInputjson() {
		return inputjson;
	}

	public void setInputjson(String inputjson) {
		this.inputjson = inputjson;
	}

	public String getProcstatus() {
		return procstatus;
	}

	public void setProcstatus(String procstatus) {
		this.procstatus = procstatus;
	}

	public String getLdtype() {
		return ldtype;
	}

	public void setLdtype(String ldtype) {
		this.ldtype = ldtype;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OefullLawdoc{" +
			"id=" + id +
			", oefullid=" + oefullid +
			", lawdocid=" + lawdocid +
			", lawdocjson=" + lawdocjson +
			", inputjson=" + inputjson +
			", procstatus=" + procstatus +
			", ldtype=" + ldtype +
			"}";
	}
}
