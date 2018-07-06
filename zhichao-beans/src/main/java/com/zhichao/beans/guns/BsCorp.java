package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 源头企业信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-03-27
 */
@TableName("bs_corp")
public class BsCorp extends Model<BsCorp> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 营业执照
     */
	private String corpcode;
    /**
     * 企业名称
     */
	private String corpname;
    /**
     * 所属区域
     */
	private String areacode;
    /**
     * 道路编号
     */
	private String roadcode;
    /**
     * 企业负责人/联系人
     */
	private String manager;
    /**
     * 身份证号码
     */
	private String managerid;
    /**
     * 联系方式
     */
	private String managertel;
    /**
     * 地址
     */
	private String address;
    /**
     * 经度
     */
	private BigDecimal longitude;
    /**
     * 纬度
     */
	private BigDecimal latitude;
    /**
     * 货运车辆数
     */
	private Integer vans;
    /**
     * 年货运吞吐量（万吨）
     */
	private BigDecimal turnvolumes;
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

	public String getCorpcode() {
		return corpcode;
	}

	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getRoadcode() {
		return roadcode;
	}

	public void setRoadcode(String roadcode) {
		this.roadcode = roadcode;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public String getManagertel() {
		return managertel;
	}

	public void setManagertel(String managertel) {
		this.managertel = managertel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Integer getVans() {
		return vans;
	}

	public void setVans(Integer vans) {
		this.vans = vans;
	}

	public BigDecimal getTurnvolumes() {
		return turnvolumes;
	}

	public void setTurnvolumes(BigDecimal turnvolumes) {
		this.turnvolumes = turnvolumes;
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
		return "BsCorp{" +
			"id=" + id +
			", corpcode=" + corpcode +
			", corpname=" + corpname +
			", areacode=" + areacode +
			", roadcode=" + roadcode +
			", manager=" + manager +
			", managerid=" + managerid +
			", managertel=" + managertel +
			", address=" + address +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", vans=" + vans +
			", turnvolumes=" + turnvolumes +
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
