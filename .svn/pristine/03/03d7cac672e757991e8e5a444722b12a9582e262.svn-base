package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 执法人员信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-08
 */
@TableName("bs_law_enforce_man")
public class LawEnforceMan extends Model<LawEnforceMan> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 执法人员姓名
     */
	@TableField("lem_name")
	private String lemName;
    /**
     * 执法人员性别
     */
	@TableField("lem_sex")
	private String lemSex;
    /**
     * 执法人员生日
     */
	@TableField("lem_birthday")
	private String lemBirthday;
    /**
     * 执法人员学历
     */
	@TableField("lem_edu_bg")
	private String lemEduBg;
    /**
     * 执法人员政治面貌
     */
	@TableField("lem_politics_status")
	private String lemPoliticsStatus;
    /**
     * 执法人员省份证号
     */
	@TableField("lem_id_card_num")
	private String lemIdCardNum;
    /**
     * 执法单位
     */
	@TableField("law_enforcement_agencies")
	private String lawEnforcementAgencies;
    /**
     * 执法人员联系方式
     */
	@TableField("lem_contact")
	private String lemContact;
    /**
     * 执法人员职务
     */
	@TableField("lem_duty")
	private String lemDuty;
    /**
     * 执法人员级别
     */
	@TableField("lem_grade")
	private Integer lemGrade;
    /**
     * 执法人员工作编号
     */
	@TableField("lem_num")
	private String lemNum;
    /**
     * 所属区域
     */
	private String areacode;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLemName() {
		return lemName;
	}

	public void setLemName(String lemName) {
		this.lemName = lemName;
	}

	public String getLemSex() {
		return lemSex;
	}

	public void setLemSex(String lemSex) {
		this.lemSex = lemSex;
	}

	public String getLemBirthday() {
		return lemBirthday;
	}

	public void setLemBirthday(String lemBirthday) {
		this.lemBirthday = lemBirthday;
	}

	public String getLemEduBg() {
		return lemEduBg;
	}

	public void setLemEduBg(String lemEduBg) {
		this.lemEduBg = lemEduBg;
	}

	public String getLemPoliticsStatus() {
		return lemPoliticsStatus;
	}

	public void setLemPoliticsStatus(String lemPoliticsStatus) {
		this.lemPoliticsStatus = lemPoliticsStatus;
	}

	public String getLemIdCardNum() {
		return lemIdCardNum;
	}

	public void setLemIdCardNum(String lemIdCardNum) {
		this.lemIdCardNum = lemIdCardNum;
	}

	public String getLawEnforcementAgencies() {
		return lawEnforcementAgencies;
	}

	public void setLawEnforcementAgencies(String lawEnforcementAgencies) {
		this.lawEnforcementAgencies = lawEnforcementAgencies;
	}

	public String getLemContact() {
		return lemContact;
	}

	public void setLemContact(String lemContact) {
		this.lemContact = lemContact;
	}

	public String getLemDuty() {
		return lemDuty;
	}

	public void setLemDuty(String lemDuty) {
		this.lemDuty = lemDuty;
	}

	public Integer getLemGrade() {
		return lemGrade;
	}

	public void setLemGrade(Integer lemGrade) {
		this.lemGrade = lemGrade;
	}

	public String getLemNum() {
		return lemNum;
	}

	public void setLemNum(String lemNum) {
		this.lemNum = lemNum;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LawEnforceMan{" +
			"id=" + id +
			", lemName=" + lemName +
			", lemSex=" + lemSex +
			", lemBirthday=" + lemBirthday +
			", lemEduBg=" + lemEduBg +
			", lemPoliticsStatus=" + lemPoliticsStatus +
			", lemIdCardNum=" + lemIdCardNum +
			", lawEnforcementAgencies=" + lawEnforcementAgencies +
			", lemContact=" + lemContact +
			", lemDuty=" + lemDuty +
			", lemGrade=" + lemGrade +
			", lemNum=" + lemNum +
			", areacode=" + areacode +
			"}";
	}
}
