package com.zhichao.beans.guns;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 抄告信息反馈表
 * </p>
 *
 * @author zjl
 * @since 2018-03-22
 */
@TableName("bs_reportsend")
public class Reportsend extends Model<Reportsend> {

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
     * 部门编号
     */
	private Integer departmentId;
    /**
     * 反馈内容
     */
	private String feedback;
    /**
     * 备注
     */
	private String remarks;
    /**
     * 时间
     */
	private Date addtime;


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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Reportsend{" +
			"id=" + id +
			", reportId=" + reportId +
			", departmentId=" + departmentId +
			", feedback=" + feedback +
			", remarks=" + remarks +
			", addtime=" + addtime +
			"}";
	}
}
