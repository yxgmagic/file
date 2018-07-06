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
 * 超限货物处理
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-25
 */
@TableName("b_cargo_handling")
public class CargoHandling extends Model<CargoHandling> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 暂扣单号
     */
	private String withholdno;
    /**
     * 卸货单号
     */
	private String unloadno;
    /**
     * 检测站点
     */
	private String stationid;
    /**
     * 车牌号
     */
	private String vehicleid;
    /**
     * 卸货日期
     */
	private Date unloadtime;
    /**
     * 卸货场编号
     */
	private String ulloadcode;
    /**
     * 案件号
     */
	private String caseno;
    /**
     * 车辆联系人
     */
	private String carowner;
    /**
     * 车辆联系人联系方式
     */
	private String carownertel;
    /**
     * 卸货重量
     */
	private Integer unloadweight;
    /**
     * 货物种类
     */
	private String cargotype;
    /**
     * 道路运输证号
     */
	private String rtnumber;
    /**
     * 从业资格证号
     */
	private String cpqnumber;
    /**
     * 车辆所属企业
     */
	private String corpname;
    /**
     * 车货限重
     */
	private Integer weightlimited;
    /**
     * 卸货前总质量
     */
	private Integer fctotalweight;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWithholdno() {
		return withholdno;
	}

	public void setWithholdno(String withholdno) {
		this.withholdno = withholdno;
	}

	public String getUnloadno() {
		return unloadno;
	}

	public void setUnloadno(String unloadno) {
		this.unloadno = unloadno;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public Date getUnloadtime() {
		return unloadtime;
	}

	public void setUnloadtime(Date unloadtime) {
		this.unloadtime = unloadtime;
	}

	public String getUlloadcode() {
		return ulloadcode;
	}

	public void setUlloadcode(String ulloadcode) {
		this.ulloadcode = ulloadcode;
	}

	public String getCaseno() {
		return caseno;
	}

	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}

	public String getCarowner() {
		return carowner;
	}

	public void setCarowner(String carowner) {
		this.carowner = carowner;
	}

	public String getCarownertel() {
		return carownertel;
	}

	public void setCarownertel(String carownertel) {
		this.carownertel = carownertel;
	}

	public Integer getUnloadweight() {
		return unloadweight;
	}

	public void setUnloadweight(Integer unloadweight) {
		this.unloadweight = unloadweight;
	}

	public String getCargotype() {
		return cargotype;
	}

	public void setCargotype(String cargotype) {
		this.cargotype = cargotype;
	}

	public String getRtnumber() {
		return rtnumber;
	}

	public void setRtnumber(String rtnumber) {
		this.rtnumber = rtnumber;
	}

	public String getCpqnumber() {
		return cpqnumber;
	}

	public void setCpqnumber(String cpqnumber) {
		this.cpqnumber = cpqnumber;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public Integer getWeightlimited() {
		return weightlimited;
	}

	public void setWeightlimited(Integer weightlimited) {
		this.weightlimited = weightlimited;
	}

	public Integer getFctotalweight() {
		return fctotalweight;
	}

	public void setFctotalweight(Integer fctotalweight) {
		this.fctotalweight = fctotalweight;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CargoHandling{" +
			"id=" + id +
			", withholdno=" + withholdno +
			", unloadno=" + unloadno +
			", stationid=" + stationid +
			", vehicleid=" + vehicleid +
			", unloadtime=" + unloadtime +
			", ulloadcode=" + ulloadcode +
			", caseno=" + caseno +
			", carowner=" + carowner +
			", carownertel=" + carownertel +
			", unloadweight=" + unloadweight +
			", cargotype=" + cargotype +
			", rtnumber=" + rtnumber +
			", cpqnumber=" + cpqnumber +
			", corpname=" + corpname +
			", weightlimited=" + weightlimited +
			", fctotalweight=" + fctotalweight +
			"}";
	}
}
