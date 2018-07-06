package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 源头企业货运车辆信息表
 * </p>
 *
 * @author imyzt
 * @since 2018-01-02
 */
@TableName("bs_vehicle")
public class Vehicle extends Model<Vehicle> {


	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 车牌号码
     */
	private String vehicleid;
    /**
     * 车型代码
     */
	private Integer vehicletype;
    /**
     * 源头企业编码
     */
	private String corpcode;
    /**
     * 所有人证件类型
     */
	private String owneridtype;
    /**
     * 所有人证件号码
     */
	private String ownerid;
    /**
     * 所有人名称
     */
	private String owername;
    /**
     * 所有人联系方式
     */
	private String owertel;
    /**
     * 品牌型号
     */
	private String model;
    /**
     * 发动机号
     */
	private String engineid;
    /**
     * 车辆识别代码
     */
	private String vin;
    /**
     * 道路运输证号
     */
	private String bizcertid;
    /**
     * 总质量
     */
	private Integer ratedtotalweight;
    /**
     * 核定载质量
     */
	private Integer ratedloading;
    /**
     * 车长
     */
	private Integer length;
    /**
     * 车宽
     */
	private Integer width;
    /**
     * 车高
     */
	private Integer height;
    /**
     * 轴数
     */
	private Integer axlesum;
    /**
     * 车辆所属标记
     */
	private String markVehicle;
    /**
     * 整备质量
     */
	private String curbQuality;

	/**
	 * 源头企业名称
	 */
	@TableField(exist=false)
	private String corpname;


	@Override
	protected Serializable pkVal() {
		return null;
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

	public Integer getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(Integer vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getCorpcode() {
		return corpcode;
	}

	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}

	public String getOwneridtype() {
		return owneridtype;
	}

	public void setOwneridtype(String owneridtype) {
		this.owneridtype = owneridtype;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwername() {
		return owername;
	}

	public void setOwername(String owername) {
		this.owername = owername;
	}

	public String getOwertel() {
		return owertel;
	}

	public void setOwertel(String owertel) {
		this.owertel = owertel;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEngineid() {
		return engineid;
	}

	public void setEngineid(String engineid) {
		this.engineid = engineid;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBizcertid() {
		return bizcertid;
	}

	public void setBizcertid(String bizcertid) {
		this.bizcertid = bizcertid;
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

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getAxlesum() {
		return axlesum;
	}

	public void setAxlesum(Integer axlesum) {
		this.axlesum = axlesum;
	}

	public String getMarkVehicle() {
		return markVehicle;
	}

	public void setMarkVehicle(String markVehicle) {
		this.markVehicle = markVehicle;
	}

	public String getCurbQuality() {
		return curbQuality;
	}

	public void setCurbQuality(String curbQuality) {
		this.curbQuality = curbQuality;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"id=" + id +
				", vehicleid='" + vehicleid + '\'' +
				", vehicletype=" + vehicletype +
				", corpcode='" + corpcode + '\'' +
				", owneridtype='" + owneridtype + '\'' +
				", ownerid='" + ownerid + '\'' +
				", owername='" + owername + '\'' +
				", owertel='" + owertel + '\'' +
				", model='" + model + '\'' +
				", engineid='" + engineid + '\'' +
				", vin='" + vin + '\'' +
				", bizcertid='" + bizcertid + '\'' +
				", ratedtotalweight=" + ratedtotalweight +
				", ratedloading=" + ratedloading +
				", length=" + length +
				", width=" + width +
				", height=" + height +
				", axlesum=" + axlesum +
				", markVehicle='" + markVehicle + '\'' +
				", curbQuality='" + curbQuality + '\'' +
				", corpname='" + corpname + '\'' +
				'}';
	}
}
