package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 抄告内容信息表
 * </p>
 *
 * @author zjl
 * @since 2018-03-22
 */
@TableName("bs_reportinfo")
public class Reportinfo extends Model<Reportinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 标题
     */
	private String reportTitle;
    /**
     * 内容
     */
	private String reportContent;
    /**
     * 状态
     */
	private Integer flag;
    /**
     * 反馈
     */
	private Integer feedback;
    /**
     * 日期
     */
	private Date addtime;
    /**
     * 审核
     */
	private Integer verify;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getFeedback() {
		return feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Reportinfo{" +
			"id=" + id +
			", reportTitle=" + reportTitle +
			", reportContent=" + reportContent +
			", flag=" + flag +
			", feedback=" + feedback +
			", addtime=" + addtime +
			", verify=" + verify +
			"}";
	}
}
