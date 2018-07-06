package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 抄告对象信息表
 * </p>
 *
 * @author zjl
 * @since 2018-03-16
 */
@TableName("bs_reportservice")
public class Reportservice extends Model<Reportservice> {

    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 对象名称
     */
	private String reportName;
    /**
     * 接口地址
     */
	private String reportAddress;
	
	/**
	 * 端口
	 */
	private String reportPort;
	
    /**
     * 备注
     */
	private String remarks;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportAddress() {
		return reportAddress;
	}

	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress;
	}

	public String getReportPort() {
		return reportPort;
	}

	public void setReportPort(String reportPort) {
		this.reportPort = reportPort;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Reportservice{" +
			"id=" + id +
			", reportName=" + reportName +
			", reportAddress=" + reportAddress +
			", reportPort=" + reportPort +
			", remarks=" + remarks +
			"}";
	}
}
