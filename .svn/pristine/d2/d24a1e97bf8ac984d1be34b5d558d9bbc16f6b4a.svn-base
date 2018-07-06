package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 考核计划表
 * </p>
 *
 * @author imyzt
 * @since 2018-02-28
 */
@TableName("pe_assessplan")
public class PeAssessplan extends Model<PeAssessplan> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 考核名称
     */
	private String assessName;
    /**
     * 开始时间
     */
	private Date startTime;
    /**
     * 结束时间
     */
	private Date endTime;
    /**
     * 备注
     */
	private String notes;


	/**
	 * 考评模板对象数
	 */
	@TableField(exist = false)
	private Integer objNum;

	/**
	 * 考核对象
	 */
	@TableField(exist = false)
	private String assessObj;

	/**
	 * 指标
	 */
	@TableField(exist = false)
	private String assessIndic;

	/**
	 * 指标数
	 */
	@TableField(exist = false)
	private Integer indicNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssessName() {
		return assessName;
	}

	public void setAssessName(String assessName) {
		this.assessName = assessName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public Integer getObjNum() {
		return objNum;
	}

	public void setObjNum(Integer objNum) {
		this.objNum = objNum;
	}

	public String getAssessObj() {
		return assessObj;
	}

	public void setAssessObj(String assessObj) {
		this.assessObj = assessObj;
	}

	public String getAssessIndic() {
		return assessIndic;
	}

	public void setAssessIndic(String assessIndic) {
		this.assessIndic = assessIndic;
	}

	public Integer getIndicNum() {
		return indicNum;
	}

	public void setIndicNum(Integer indicNum) {
		this.indicNum = indicNum;
	}

	@Override
	public String toString() {
		return "PeAssessplan{" +
				"id=" + id +
				", assessName='" + assessName + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", notes='" + notes + '\'' +
				", objNum=" + objNum +
				", assessObj='" + assessObj + '\'' +
				", assessIndic='" + assessIndic + '\'' +
				", indicNum=" + indicNum +
				'}';
	}
}
