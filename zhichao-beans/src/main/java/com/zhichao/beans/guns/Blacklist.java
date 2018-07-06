package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 黑白名单
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-23
 */
@TableName("b_blacklist")
public class Blacklist extends Model<Blacklist> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 车牌号
     */
	private String vehicleid;
    /**
     * 驾驶人姓名
     */
	private String drivername;
    /**
     * 企业名称
     */
	private String corpname;
    /**
     * 超重站号
     */
	private String stationid;
    /**
     * 车辆类型
     */
	private String cartype;
    /**
     * 车轴数
     */
	private String caraxles;
    /**
     * 核定载重量(kg)
     */
	private Integer ratedloading;
    /**
     * 超重数(kg)
     */
	private Integer overload;
    /**
     * 罚金
     */
	private Integer fine;
    /**
     * 是否完成惩罚
     */
	private String ispunitive;
    /**
     * 是否进入黑名单
     */
	private String isblack;
    /**
     * 备注
     */
	private String remarks;
	/**
	 * 车头照片
	 */
	private String vehicleimage;
	private String vehicleimage1;
	private String vehicleimage2;

	public String getVehicleimage() {
		return vehicleimage;
	}

	public void setVehicleimage(String vehicleimage) {
		this.vehicleimage = vehicleimage;
	}

	public String getVehicleimage1() {
		return vehicleimage1;
	}

	public void setVehicleimage1(String vehicleimage1) {
		this.vehicleimage1 = vehicleimage1;
	}

	public String getVehicleimage2() {
		return vehicleimage2;
	}

	public void setVehicleimage2(String vehicleimage2) {
		this.vehicleimage2 = vehicleimage2;
	}

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

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCaraxles() {
		return caraxles;
	}

	public void setCaraxles(String caraxles) {
		this.caraxles = caraxles;
	}

	public Integer getRatedloading() {
		return ratedloading;
	}

	public void setRatedloading(Integer ratedloading) {
		this.ratedloading = ratedloading;
	}

	public Integer getOverload() {
		return overload;
	}

	public void setOverload(Integer overload) {
		this.overload = overload;
	}

	public Integer getFine() {
		return fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}

	public String getIspunitive() {
		return ispunitive;
	}

	public void setIspunitive(String ispunitive) {
		this.ispunitive = ispunitive;
	}

	public String getIsblack() {
		return isblack;
	}

	public void setIsblack(String isblack) {
		this.isblack = isblack;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Blacklist [id=" + id + ", vehicleid=" + vehicleid + ", drivername=" + drivername + ", corpname="
				+ corpname + ", stationid=" + stationid + ", cartype=" + cartype + ", caraxles=" + caraxles
				+ ", ratedloading=" + ratedloading + ", overload=" + overload + ", fine=" + fine + ", ispunitive="
				+ ispunitive + ", isblack=" + isblack + ", remarks=" + remarks + ", vehicleimage=" + vehicleimage
				+ ", vehicleimage1=" + vehicleimage1 + ", vehicleimage2=" + vehicleimage2 + "]";
	}


}
