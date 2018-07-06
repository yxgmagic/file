package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 源头企业货运人员信息表
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
@TableName("bs_vehicle_driver")
public class VehicleDriver extends Model<VehicleDriver> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 驾驶证号
     */
	private String driverid;
    /**
     * 身份证号
     */
	private String idcard;
    /**
     * 姓名
     */
	private String drivername;
    /**
     * 民族
     */
	private String race;
    /**
     * 性别
     */
	private String sex;
    /**
     * 从业资格证
     */
	private String qualificationid;
    /**
     * 从业资格证类别
     */
	private String certtype;
    /**
     * 地址
     */
	private String address;
    /**
     * 邮编
     */
	private String postcode;
    /**
     * 发证机关
     */
	private String grantorg;
    /**
     * 有效期至
     */
	private String certendtime;
    /**
     * 企业名称
     */
	private String corpcode;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQualificationid() {
		return qualificationid;
	}

	public void setQualificationid(String qualificationid) {
		this.qualificationid = qualificationid;
	}

	public String getCerttype() {
		return certtype;
	}

	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getGrantorg() {
		return grantorg;
	}

	public void setGrantorg(String grantorg) {
		this.grantorg = grantorg;
	}

	public String getCertendtime() {
		return certendtime;
	}

	public void setCertendtime(String certendtime) {
		this.certendtime = certendtime;
	}

	public String getCorpcode() {
		return corpcode;
	}

	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "VehicleDriver{" +
			"id=" + id +
			", driverid=" + driverid +
			", idcard=" + idcard +
			", drivername=" + drivername +
			", race=" + race +
			", sex=" + sex +
			", qualificationid=" + qualificationid +
			", certtype=" + certtype +
			", address=" + address +
			", postcode=" + postcode +
			", grantorg=" + grantorg +
			", certendtime=" + certendtime +
			", corpcode=" + corpcode +
			"}";
	}
}
