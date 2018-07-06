package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 抄告报表关联表
 * </p>
 *
 * @author zjl
 * @since 2018-03-22
 */
@TableName("bs_reportcontent")
public class Reportcontent extends Model<Reportcontent> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	private Integer id;
    /**
     * 关联抄告ID
     */
	private Integer reportId;
    /**
     * 报表编号ID
     */
	private Integer reportInfoId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getReportInfoId() {
		return reportInfoId;
	}

	public void setReportInfoId(Integer reportInfoId) {
		this.reportInfoId = reportInfoId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Reportcontent{" +
			"id=" + id +
			", reportId=" + reportId +
			", reportInfoId=" + reportInfoId +
			"}";
	}
}
