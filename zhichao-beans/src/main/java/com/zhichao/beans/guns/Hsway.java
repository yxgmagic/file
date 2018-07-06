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
 * 高速出入口信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-03-27
 */
@TableName("bs_hsway")
public class Hsway extends Model<Hsway> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 出入口名称
     */
	private String hswayname;
    /**
     * 位置
     */
	private String address;
    /**
     * 检测设备厂商
     */
	private String devicefirm;
    /**
     * 是否可联网
     */
	private Integer isnet;
    /**
     * 是否有车牌识别
     */
	private Integer isdisting;
    /**
     * 车道宽度
     */
	private Integer roadwidth;
    /**
     * 设置时间
     */
	private String setbegindate;
    /**
     * 最大测重
     */
	private Integer maxweight;
    /**
     * 摄像头个数
     */
	private Integer cameras;
    /**
     * 摄像头品牌
     */
	private String camerabrand;
    /**
     * 检测方向
     */
	private String checkdirection;
    /**
     * 所属区域
     */
	private String areacode;
    /**
     * 所属道路
     */
	private String roadcode;
    /**
     * 经度
     */
	private String lng;
    /**
     * 纬度
     */
	private String lat;
    /**
     * 桩号
     */
	@TableField("stake_number")
	private String stakeNumber;
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

	public String getHswayname() {
		return hswayname;
	}

	public void setHswayname(String hswayname) {
		this.hswayname = hswayname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDevicefirm() {
		return devicefirm;
	}

	public void setDevicefirm(String devicefirm) {
		this.devicefirm = devicefirm;
	}

	public Integer getIsnet() {
		return isnet;
	}

	public void setIsnet(Integer isnet) {
		this.isnet = isnet;
	}

	public Integer getIsdisting() {
		return isdisting;
	}

	public void setIsdisting(Integer isdisting) {
		this.isdisting = isdisting;
	}

	public Integer getRoadwidth() {
		return roadwidth;
	}

	public void setRoadwidth(Integer roadwidth) {
		this.roadwidth = roadwidth;
	}

	public String getSetbegindate() {
		return setbegindate;
	}

	public void setSetbegindate(String setbegindate) {
		this.setbegindate = setbegindate;
	}

	public Integer getMaxweight() {
		return maxweight;
	}

	public void setMaxweight(Integer maxweight) {
		this.maxweight = maxweight;
	}

	public Integer getCameras() {
		return cameras;
	}

	public void setCameras(Integer cameras) {
		this.cameras = cameras;
	}

	public String getCamerabrand() {
		return camerabrand;
	}

	public void setCamerabrand(String camerabrand) {
		this.camerabrand = camerabrand;
	}

	public String getCheckdirection() {
		return checkdirection;
	}

	public void setCheckdirection(String checkdirection) {
		this.checkdirection = checkdirection;
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

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getStakeNumber() {
		return stakeNumber;
	}

	public void setStakeNumber(String stakeNumber) {
		this.stakeNumber = stakeNumber;
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
		return "Hsway{" +
			"id=" + id +
			", hswayname=" + hswayname +
			", address=" + address +
			", devicefirm=" + devicefirm +
			", isnet=" + isnet +
			", isdisting=" + isdisting +
			", roadwidth=" + roadwidth +
			", setbegindate=" + setbegindate +
			", maxweight=" + maxweight +
			", cameras=" + cameras +
			", camerabrand=" + camerabrand +
			", checkdirection=" + checkdirection +
			", areacode=" + areacode +
			", roadcode=" + roadcode +
			", lng=" + lng +
			", lat=" + lat +
			", stakeNumber=" + stakeNumber +
			", dueBank=" + dueBank +
			", bankAddress=" + bankAddress +
			", bankAccount=" + bankAccount +
			", bankAccountName=" + bankAccountName +
			", judicialOrgan=" + judicialOrgan +
			"}";
	}
}
