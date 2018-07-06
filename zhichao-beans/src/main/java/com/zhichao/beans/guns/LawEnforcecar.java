package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 执法车信息
 * </p>
 *
 * @author fengshuonan
 * @since 2018-03-27
 */
@TableName("bs_law_enforcecar")
public class LawEnforcecar extends Model<LawEnforcecar> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 执法车辆牌号
     */
	private String vehicleid;
    /**
     * 执法车编号
     */
	private String vehicleno;
    /**
     * 车型
     */
	private Integer vehicletype;
    /**
     * 联系人
     */
	private String manager;
    /**
     * 联系方式
     */
	private String managertel;
    /**
     * 管理部门
     */
	private Integer deptid;
    /**
     * 所属区域
     */
	private String areacode;
    /**
     * gps设备
     */
	private String gpsdeviceid;
    /**
     * 检定证书名字
     */
	private String certificate;
    /**
     * 检定证书存储地址
     */
	@TableField("certificate_url")
	private String certificateUrl;
    /**
     * 检定证书有效期
     */
	@TableField("certificate_validity_period")
	private String certificateValidityPeriod;
    /**
     * 收款银行
     */
	@TableField("due_bank")
	private String dueBank;
    /**
     * 银行地址
     */
	@TableField("bank_address")
	private String bankAddress;
    /**
     * 银行账号
     */
	@TableField("bank_account")
	private String bankAccount;
    /**
     * 银行户名
     */
	@TableField("bank_account_name")
	private String bankAccountName;
    /**
     * 审判机关
     */
	@TableField("judicial_organ")
	private String judicialOrgan;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public Integer getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(Integer vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagertel() {
		return managertel;
	}

	public void setManagertel(String managertel) {
		this.managertel = managertel;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getGpsdeviceid() {
		return gpsdeviceid;
	}

	public void setGpsdeviceid(String gpsdeviceid) {
		this.gpsdeviceid = gpsdeviceid;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getCertificateUrl() {
		return certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}

	public String getCertificateValidityPeriod() {
		return certificateValidityPeriod;
	}

	public void setCertificateValidityPeriod(String certificateValidityPeriod) {
		this.certificateValidityPeriod = certificateValidityPeriod;
	}

	public String getDueBank() {
		return dueBank;
	}

	public void setDueBank(String dueBank) {
		this.dueBank = dueBank;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getJudicialOrgan() {
		return judicialOrgan;
	}

	public void setJudicialOrgan(String judicialOrgan) {
		this.judicialOrgan = judicialOrgan;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LawEnforcecar{" +
			"id=" + id +
			", vehicleid=" + vehicleid +
			", vehicleno=" + vehicleno +
			", vehicletype=" + vehicletype +
			", manager=" + manager +
			", managertel=" + managertel +
			", deptid=" + deptid +
			", areacode=" + areacode +
			", gpsdeviceid=" + gpsdeviceid +
			", certificate=" + certificate +
			", certificateUrl=" + certificateUrl +
			", certificateValidityPeriod=" + certificateValidityPeriod +
			", dueBank=" + dueBank +
			", bankAddress=" + bankAddress +
			", bankAccount=" + bankAccount +
			", bankAccountName=" + bankAccountName +
			", judicialOrgan=" + judicialOrgan +
			"}";
	}
}
