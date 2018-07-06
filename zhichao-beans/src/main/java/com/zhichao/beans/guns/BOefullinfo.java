package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 违章详细信息
 * </p>
 *
 * @author imyzt
 * @since 2018-02-01
 */
@TableName("b_oefullinfo")
public class BOefullinfo extends Model<BOefullinfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 案号
     */
	private String caseno;
    /**
     * 案由
     */
	private String casegist;
    /**
     * 执法依据
     */
	private String accordance;
    /**
     * 立案时间
     */
	private Date casetime;
	/**
	 * 立案时间String
	 */
	@TableField(exist = false)
	private String caseTimeString;
    /**
     * 处理状态
     */
	private String prostatus;
    /**
     * 检测单号
     */
	private String checkno;
    /**
     * 执法序号
     */
	private String oeid;
    /**
     * 车牌号码
     */
	private String vehicleid;
    /**
     * 轴数
     */
	private Integer axlesum;
    /**
     * 车货限重
     */
	private Integer weightlimited;
    /**
     * 车辆标记总质量
     */
	private Integer ratedtotalweight;
    /**
     * 核定载质量
     */
	private Integer ratedloading;
    /**
     * 超限量
     */
	private Integer overlimited;
    /**
     * 超载量
     */
	private Integer overload;
    /**
     * 初检时间
     */
	private Date fctime;
    /**
     * 复检车道号
     */
	private Integer fclaneno;
    /**
     * 初检总重量
     */
	private Integer fctotalweight;
    /**
     * 初检操作员
     */
	private String fcoper;
    /**
     * 初检车货总长
     */
	private Integer fclength;
    /**
     * 初检车货总宽
     */
	private Integer fcwidth;
    /**
     * 初检车货总高
     */
	private Integer fcheight;
    /**
     * 超长量
     */
	private Integer overlength;
    /**
     * 超宽量
     */
	private Integer overwidth;
    /**
     * 超高量
     */
	private Integer overheight;
    /**
     * 复检时间
     */
	private Date rctime;
    /**
     * 复检车道号
     */
	private Integer rclaneno;
    /**
     * 复检总重量
     */
	private Integer rctotalweight;
    /**
     * 复检操作员
     */
	private String rcoper;
    /**
     * 复检车货总长
     */
	private Integer rclength;
    /**
     * 复检车货总宽
     */
	private Integer rcwidth;
    /**
     * 复检车货总高
     */
	private Integer rcheight;
    /**
     * 已卸载量
     */
	private Integer offload;
    /**
     * 初检车头图片
     */
	private String fcvehicleimage;
    /**
     * 复检车头图片
     */
	private String rcvehicleimage;
    /**
     * 检测站号
     */
	private String stationid;
    /**
     * 道路运输证号
     */
	private String bizcertid;
    /**
     * 承运人名称
     */
	private String crarriename;
    /**
     * 承运人地址
     */
	private String carrieraddr;
    /**
     * 承运人邮编
     */
	private String carrierpost;
    /**
     * 法人代表姓名
     */
	private String lawpersonname;
    /**
     * 证件类型
     */
	private String lawpersonidtype;
    /**
     * 法人代表证件号码
     */
	private String lawpersonid;
    /**
     * 司机姓名
     */
	private String drivername;
    /**
     * 司机身份证号
     */
	private String driveridcard;
    /**
     * 司机年龄
     */
	private Integer driverage;
    /**
     * 司机手机号码
     */
	private String driverphone;
    /**
     * 司机性别
     */
	private String drivergender;
    /**
     * 司机邮编
     */
	private String driverpostcode;
    /**
     * 驾驶证号
     */
	private String driverid;
    /**
     * 从业资格证号
     */
	private String qualificationid;
    /**
     * 司机户籍
     */
	private String driverres;
    /**
     * 行驶证号
     */
	private String drivinglicense;
    /**
     * 车架号
     */
	private String vinnum;
    /**
     * 营运证号
     */
	private String bcnum;
    /**
     * 货车类型
     */
	private String vehicletype;
    /**
     * 货车品牌
     */
	private String vehiclebrand;
    /**
     * 发动机号
     */
	private String engine;
    /**
     * 货物名称
     */
	private String cargoname;
    /**
     * 货物类别
     */
	private String cargotype;
    /**
     * 货主名称
     */
	private String cargoownername;
    /**
     * 货主法人代表
     */
	private String cargolawperson;
    /**
     * 出发地区
     */
	private String departure;
    /**
     * 目的地区
     */
	private String destination;
    /**
     * 装货地址
     */
	private String loadingaddr;
    /**
     * 处罚书号
     */
	private String punishno;
    /**
     * 处罚时间
     */
	private Date punishtime;
    /**
     * 违规等级
     */
	private String violationlevel;
    /**
     * 应收罚款
     */
	private String shouldpay;
    /**
     * 罚款金额
     */
	private Float punishamount;
    /**
     * 赔偿金额
     */
	private Float damageamount;
    /**
     * 执法人1
     */
	private String enforcername1;
    /**
     * 执法人1证件号码
     */
	private String enforcerid1;
    /**
     * 执法人2
     */
	private String enforcername2;
    /**
     * 执法人2证件号码
     */
	private String enforcerid2;
    /**
     * 是否非法改装
     */
	private String ismodified;
    /**
     * 非法改装说明
     */
	private String modifydesc;
    /**
     * 是否非标准车辆
     */
	private String isnonstandard;
    /**
     * 非标准车辆说明
     */
	private String nonstandarddesc;
    /**
     * 是否鲜活农产品
     */
	private String isgreen;
    /**
     * 备注
     */
	private String notes;
    /**
     * 上传返回代码
     */
	private Integer result;
    /**
     * 上传返回信息
     */
	private String message;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseno() {
		return caseno;
	}

	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}

	public String getCasegist() {
		return casegist;
	}

	public void setCasegist(String casegist) {
		this.casegist = casegist;
	}

	public String getAccordance() {
		return accordance;
	}

	public void setAccordance(String accordance) {
		this.accordance = accordance;
	}

	public Date getCasetime() {
		return casetime;
	}

	public void setCasetime(Date casetime) {
		this.casetime = casetime;
	}

	public String getProstatus() {
		return prostatus;
	}

	public void setProstatus(String prostatus) {
		this.prostatus = prostatus;
	}

	public String getCheckno() {
		return checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
	}

	public String getOeid() {
		return oeid;
	}

	public void setOeid(String oeid) {
		this.oeid = oeid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public Integer getAxlesum() {
		return axlesum;
	}

	public void setAxlesum(Integer axlesum) {
		this.axlesum = axlesum;
	}

	public Integer getWeightlimited() {
		return weightlimited;
	}

	public void setWeightlimited(Integer weightlimited) {
		this.weightlimited = weightlimited;
	}

	public Integer getRatedtotalweight() {
		return ratedtotalweight;
	}

	public void setRatedtotalweight(Integer ratedtotalweight) {
		this.ratedtotalweight = ratedtotalweight;
	}

	public Integer getRatedloading() {
		return ratedloading;
	}

	public void setRatedloading(Integer ratedloading) {
		this.ratedloading = ratedloading;
	}

	public Integer getOverlimited() {
		return overlimited;
	}

	public void setOverlimited(Integer overlimited) {
		this.overlimited = overlimited;
	}

	public Integer getOverload() {
		return overload;
	}

	public void setOverload(Integer overload) {
		this.overload = overload;
	}

	public Date getFctime() {
		return fctime;
	}

	public void setFctime(Date fctime) {
		this.fctime = fctime;
	}

	public Integer getFclaneno() {
		return fclaneno;
	}

	public void setFclaneno(Integer fclaneno) {
		this.fclaneno = fclaneno;
	}

	public Integer getFctotalweight() {
		return fctotalweight;
	}

	public void setFctotalweight(Integer fctotalweight) {
		this.fctotalweight = fctotalweight;
	}

	public String getFcoper() {
		return fcoper;
	}

	public void setFcoper(String fcoper) {
		this.fcoper = fcoper;
	}

	public Integer getFclength() {
		return fclength;
	}

	public void setFclength(Integer fclength) {
		this.fclength = fclength;
	}

	public Integer getFcwidth() {
		return fcwidth;
	}

	public void setFcwidth(Integer fcwidth) {
		this.fcwidth = fcwidth;
	}

	public Integer getFcheight() {
		return fcheight;
	}

	public void setFcheight(Integer fcheight) {
		this.fcheight = fcheight;
	}

	public Integer getOverlength() {
		return overlength;
	}

	public void setOverlength(Integer overlength) {
		this.overlength = overlength;
	}

	public Integer getOverwidth() {
		return overwidth;
	}

	public void setOverwidth(Integer overwidth) {
		this.overwidth = overwidth;
	}

	public Integer getOverheight() {
		return overheight;
	}

	public void setOverheight(Integer overheight) {
		this.overheight = overheight;
	}

	public Date getRctime() {
		return rctime;
	}

	public void setRctime(Date rctime) {
		this.rctime = rctime;
	}

	public Integer getRclaneno() {
		return rclaneno;
	}

	public void setRclaneno(Integer rclaneno) {
		this.rclaneno = rclaneno;
	}

	public Integer getRctotalweight() {
		return rctotalweight;
	}

	public void setRctotalweight(Integer rctotalweight) {
		this.rctotalweight = rctotalweight;
	}

	public String getRcoper() {
		return rcoper;
	}

	public void setRcoper(String rcoper) {
		this.rcoper = rcoper;
	}

	public Integer getRclength() {
		return rclength;
	}

	public void setRclength(Integer rclength) {
		this.rclength = rclength;
	}

	public Integer getRcwidth() {
		return rcwidth;
	}

	public void setRcwidth(Integer rcwidth) {
		this.rcwidth = rcwidth;
	}

	public Integer getRcheight() {
		return rcheight;
	}

	public void setRcheight(Integer rcheight) {
		this.rcheight = rcheight;
	}

	public Integer getOffload() {
		return offload;
	}

	public void setOffload(Integer offload) {
		this.offload = offload;
	}

	public String getFcvehicleimage() {
		return fcvehicleimage;
	}

	public void setFcvehicleimage(String fcvehicleimage) {
		this.fcvehicleimage = fcvehicleimage;
	}

	public String getRcvehicleimage() {
		return rcvehicleimage;
	}

	public void setRcvehicleimage(String rcvehicleimage) {
		this.rcvehicleimage = rcvehicleimage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getBizcertid() {
		return bizcertid;
	}

	public void setBizcertid(String bizcertid) {
		this.bizcertid = bizcertid;
	}

	public String getCrarriename() {
		return crarriename;
	}

	public void setCrarriename(String crarriename) {
		this.crarriename = crarriename;
	}

	public String getCarrieraddr() {
		return carrieraddr;
	}

	public void setCarrieraddr(String carrieraddr) {
		this.carrieraddr = carrieraddr;
	}

	public String getCarrierpost() {
		return carrierpost;
	}

	public void setCarrierpost(String carrierpost) {
		this.carrierpost = carrierpost;
	}

	public String getLawpersonname() {
		return lawpersonname;
	}

	public void setLawpersonname(String lawpersonname) {
		this.lawpersonname = lawpersonname;
	}

	public String getLawpersonidtype() {
		return lawpersonidtype;
	}

	public void setLawpersonidtype(String lawpersonidtype) {
		this.lawpersonidtype = lawpersonidtype;
	}

	public String getLawpersonid() {
		return lawpersonid;
	}

	public void setLawpersonid(String lawpersonid) {
		this.lawpersonid = lawpersonid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDriveridcard() {
		return driveridcard;
	}

	public void setDriveridcard(String driveridcard) {
		this.driveridcard = driveridcard;
	}

	public Integer getDriverage() {
		return driverage;
	}

	public void setDriverage(Integer driverage) {
		this.driverage = driverage;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getDrivergender() {
		return drivergender;
	}

	public void setDrivergender(String drivergender) {
		this.drivergender = drivergender;
	}

	public String getDriverpostcode() {
		return driverpostcode;
	}

	public void setDriverpostcode(String driverpostcode) {
		this.driverpostcode = driverpostcode;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getQualificationid() {
		return qualificationid;
	}

	public void setQualificationid(String qualificationid) {
		this.qualificationid = qualificationid;
	}

	public String getDriverres() {
		return driverres;
	}

	public void setDriverres(String driverres) {
		this.driverres = driverres;
	}

	public String getDrivinglicense() {
		return drivinglicense;
	}

	public void setDrivinglicense(String drivinglicense) {
		this.drivinglicense = drivinglicense;
	}

	public String getVinnum() {
		return vinnum;
	}

	public void setVinnum(String vinnum) {
		this.vinnum = vinnum;
	}

	public String getBcnum() {
		return bcnum;
	}

	public void setBcnum(String bcnum) {
		this.bcnum = bcnum;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getVehiclebrand() {
		return vehiclebrand;
	}

	public void setVehiclebrand(String vehiclebrand) {
		this.vehiclebrand = vehiclebrand;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getCargotype() {
		return cargotype;
	}

	public void setCargotype(String cargotype) {
		this.cargotype = cargotype;
	}

	public String getCargoownername() {
		return cargoownername;
	}

	public void setCargoownername(String cargoownername) {
		this.cargoownername = cargoownername;
	}

	public String getCargolawperson() {
		return cargolawperson;
	}

	public void setCargolawperson(String cargolawperson) {
		this.cargolawperson = cargolawperson;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getLoadingaddr() {
		return loadingaddr;
	}

	public void setLoadingaddr(String loadingaddr) {
		this.loadingaddr = loadingaddr;
	}

	public String getPunishno() {
		return punishno;
	}

	public void setPunishno(String punishno) {
		this.punishno = punishno;
	}

	public Date getPunishtime() {
		return punishtime;
	}

	public void setPunishtime(Date punishtime) {
		this.punishtime = punishtime;
	}

	public String getViolationlevel() {
		return violationlevel;
	}

	public void setViolationlevel(String violationlevel) {
		this.violationlevel = violationlevel;
	}

	public String getShouldpay() {
		return shouldpay;
	}

	public void setShouldpay(String shouldpay) {
		this.shouldpay = shouldpay;
	}

	public Float getPunishamount() {
		return punishamount;
	}

	public void setPunishamount(Float punishamount) {
		this.punishamount = punishamount;
	}

	public Float getDamageamount() {
		return damageamount;
	}

	public void setDamageamount(Float damageamount) {
		this.damageamount = damageamount;
	}

	public String getEnforcername1() {
		return enforcername1;
	}

	public void setEnforcername1(String enforcername1) {
		this.enforcername1 = enforcername1;
	}

	public String getEnforcerid1() {
		return enforcerid1;
	}

	public void setEnforcerid1(String enforcerid1) {
		this.enforcerid1 = enforcerid1;
	}

	public String getEnforcername2() {
		return enforcername2;
	}

	public void setEnforcername2(String enforcername2) {
		this.enforcername2 = enforcername2;
	}

	public String getEnforcerid2() {
		return enforcerid2;
	}

	public void setEnforcerid2(String enforcerid2) {
		this.enforcerid2 = enforcerid2;
	}

	public String getIsmodified() {
		return ismodified;
	}

	public void setIsmodified(String ismodified) {
		this.ismodified = ismodified;
	}

	public String getModifydesc() {
		return modifydesc;
	}

	public void setModifydesc(String modifydesc) {
		this.modifydesc = modifydesc;
	}

	public String getIsnonstandard() {
		return isnonstandard;
	}

	public void setIsnonstandard(String isnonstandard) {
		this.isnonstandard = isnonstandard;
	}

	public String getNonstandarddesc() {
		return nonstandarddesc;
	}

	public void setNonstandarddesc(String nonstandarddesc) {
		this.nonstandarddesc = nonstandarddesc;
	}

	public String getIsgreen() {
		return isgreen;
	}

	public void setIsgreen(String isgreen) {
		this.isgreen = isgreen;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getCaseTimeString() {
		return caseTimeString;
	}
	public void setCaseTimeString(String caseTimeString) {
		this.caseTimeString = caseTimeString;
	}

	@Override
	public String toString() {
		return "BOefullinfo [id=" + id + ", caseno=" + caseno + ", casegist=" + casegist + ", accordance=" + accordance
				+ ", casetime=" + casetime + ", caseTimeString=" + caseTimeString + ", prostatus=" + prostatus
				+ ", checkno=" + checkno + ", oeid=" + oeid + ", vehicleid=" + vehicleid + ", axlesum=" + axlesum
				+ ", weightlimited=" + weightlimited + ", ratedtotalweight=" + ratedtotalweight + ", ratedloading="
				+ ratedloading + ", overlimited=" + overlimited + ", overload=" + overload + ", fctime=" + fctime
				+ ", fclaneno=" + fclaneno + ", fctotalweight=" + fctotalweight + ", fcoper=" + fcoper + ", fclength="
				+ fclength + ", fcwidth=" + fcwidth + ", fcheight=" + fcheight + ", overlength=" + overlength
				+ ", overwidth=" + overwidth + ", overheight=" + overheight + ", rctime=" + rctime + ", rclaneno="
				+ rclaneno + ", rctotalweight=" + rctotalweight + ", rcoper=" + rcoper + ", rclength=" + rclength
				+ ", rcwidth=" + rcwidth + ", rcheight=" + rcheight + ", offload=" + offload + ", fcvehicleimage="
				+ fcvehicleimage + ", rcvehicleimage=" + rcvehicleimage + ", stationid=" + stationid + ", bizcertid="
				+ bizcertid + ", crarriename=" + crarriename + ", carrieraddr=" + carrieraddr + ", carrierpost="
				+ carrierpost + ", lawpersonname=" + lawpersonname + ", lawpersonidtype=" + lawpersonidtype
				+ ", lawpersonid=" + lawpersonid + ", drivername=" + drivername + ", driveridcard=" + driveridcard
				+ ", driverage=" + driverage + ", driverphone=" + driverphone + ", drivergender=" + drivergender
				+ ", driverpostcode=" + driverpostcode + ", driverid=" + driverid + ", qualificationid="
				+ qualificationid + ", driverres=" + driverres + ", drivinglicense=" + drivinglicense + ", vinnum="
				+ vinnum + ", bcnum=" + bcnum + ", vehicletype=" + vehicletype + ", vehiclebrand=" + vehiclebrand
				+ ", engine=" + engine + ", cargoname=" + cargoname + ", cargotype=" + cargotype + ", cargoownername="
				+ cargoownername + ", cargolawperson=" + cargolawperson + ", departure=" + departure + ", destination="
				+ destination + ", loadingaddr=" + loadingaddr + ", punishno=" + punishno + ", punishtime=" + punishtime
				+ ", violationlevel=" + violationlevel + ", shouldpay=" + shouldpay + ", punishamount=" + punishamount
				+ ", damageamount=" + damageamount + ", enforcername1=" + enforcername1 + ", enforcerid1=" + enforcerid1
				+ ", enforcername2=" + enforcername2 + ", enforcerid2=" + enforcerid2 + ", ismodified=" + ismodified
				+ ", modifydesc=" + modifydesc + ", isnonstandard=" + isnonstandard + ", nonstandarddesc="
				+ nonstandarddesc + ", isgreen=" + isgreen + ", notes=" + notes + ", result=" + result + ", message="
				+ message + "]";
	}
	
}
