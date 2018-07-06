package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 非现场执法信息表
 * </p>
 *
 * @author imyzt
 * @since 2018-03-29
 */
@TableName("bs_osesite")
public class BsOsesite extends Model<BsOsesite> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 站点编码
     */
	private String sitecode;
    /**
     * 站点名称
     */
	private String sitename;
    /**
     * 经度
     */
	private BigDecimal longitude;
    /**
     * 纬度
     */
	private BigDecimal latitude;
    /**
     * 地址
     */
	private String address;
    /**
     * 行政区域编码
     */
	private String areacode;
    /**
     * 道路编码
     */
	private String roadcode;
    /**
     * 站点等级
     */
	private Integer sitelevel;
    /**
     * 限高
     */
	private Integer heightlimited;
    /**
     * 限长
     */
	private Integer longlimited;
    /**
     * 限宽
     */
	private Integer widthlimited;
    /**
     * 限重
     */
	private Integer weightlimited;
    /**
     * 精确度
     */
	private BigDecimal precision;
    /**
     * 车道宽度
     */
	private BigDecimal roadwidth;
    /**
     * 管理部门
     */
	private Integer deptid;
    /**
     * 负责人姓名
     */
	private String manager;
    /**
     * 联系方式
     */
	private String managertel;
    /**
     * 检测方式
     */
	private Integer checkmode;
    /**
     * 检测方向
     */
	private String checkdirection;
    /**
     * 桩号
     */
	@TableField("stake_number")
	private String stakeNumber;
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
	 * 建站时间
	 */
	private String buildTime;

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

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getSitelevel() {
		return sitelevel;
	}

	public void setSitelevel(Integer sitelevel) {
		this.sitelevel = sitelevel;
	}

	public Integer getHeightlimited() {
		return heightlimited;
	}

	public void setHeightlimited(Integer heightlimited) {
		this.heightlimited = heightlimited;
	}

	public Integer getLonglimited() {
		return longlimited;
	}

	public void setLonglimited(Integer longlimited) {
		this.longlimited = longlimited;
	}

	public Integer getWidthlimited() {
		return widthlimited;
	}

	public void setWidthlimited(Integer widthlimited) {
		this.widthlimited = widthlimited;
	}

	public Integer getWeightlimited() {
		return weightlimited;
	}

	public void setWeightlimited(Integer weightlimited) {
		this.weightlimited = weightlimited;
	}

	public BigDecimal getPrecision() {
		return precision;
	}

	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}

	public BigDecimal getRoadwidth() {
		return roadwidth;
	}

	public void setRoadwidth(BigDecimal roadwidth) {
		this.roadwidth = roadwidth;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
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

	public Integer getCheckmode() {
		return checkmode;
	}

	public void setCheckmode(Integer checkmode) {
		this.checkmode = checkmode;
	}

	public String getCheckdirection() {
		return checkdirection;
	}

	public void setCheckdirection(String checkdirection) {
		this.checkdirection = checkdirection;
	}

	public String getStakeNumber() {
		return stakeNumber;
	}

	public void setStakeNumber(String stakeNumber) {
		this.stakeNumber = stakeNumber;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
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
	public String toString() {
		return "BsOsesite{" +
				"id=" + id +
				", sitecode='" + sitecode + '\'' +
				", sitename='" + sitename + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", address='" + address + '\'' +
				", areacode='" + areacode + '\'' +
				", roadcode='" + roadcode + '\'' +
				", sitelevel=" + sitelevel +
				", heightlimited=" + heightlimited +
				", longlimited=" + longlimited +
				", widthlimited=" + widthlimited +
				", weightlimited=" + weightlimited +
				", precision=" + precision +
				", roadwidth=" + roadwidth +
				", deptid=" + deptid +
				", manager='" + manager + '\'' +
				", managertel='" + managertel + '\'' +
				", checkmode=" + checkmode +
				", checkdirection='" + checkdirection + '\'' +
				", stakeNumber='" + stakeNumber + '\'' +
				", certificate='" + certificate + '\'' +
				", certificateUrl='" + certificateUrl + '\'' +
				", certificateValidityPeriod='" + certificateValidityPeriod + '\'' +
				", buildTime=" + buildTime +
				", dueBank='" + dueBank + '\'' +
				", bankAddress='" + bankAddress + '\'' +
				", bankAccount='" + bankAccount + '\'' +
				", bankAccountName='" + bankAccountName + '\'' +
				", judicialOrgan='" + judicialOrgan + '\'' +
				'}';
	}
}