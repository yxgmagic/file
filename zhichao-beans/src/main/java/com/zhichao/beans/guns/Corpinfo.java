package com.zhichao.beans.guns;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 源头企业检测数据表
 * </p>
 *
 * @author imyzt
 * @since 2018-01-19
 */
@TableName("b_corpinfo")
public class Corpinfo extends Model<Corpinfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 预检序号
     */
	private String corpid;
    /**
     * 检测时间
     */
	private Date corptime;
	/**
     * 检测时间,字符串
     */
	@TableField(exist = false)
	private String strTime;
    /**
     * 企业编号
     */
	private String corpcode;
    /**
     * 车牌号码
     */
	private String vehicleid;
    /**
     * 货车类型
     */
	private String vehicletype;
    /**
     * 轴数
     */
	private Integer axlesum;
    /**
     * 车速
     */
	private Integer speed;
    /**
     * 车道
     */
	private Integer laneno;
    /**
     * 总重
     */
	private Integer totalweight;
    /**
     * 超限量
     */
	private Integer overlimited;
    /**
     * 轴重1
     */
	private Integer axle1;
    /**
     * 轴重2
     */
	private Integer axle2;
    /**
     * 轴重3
     */
	private Integer axle3;
    /**
     * 轴重4
     */
	private Integer axle4;
    /**
     * 轴重5
     */
	private Integer axle5;
    /**
     * 轴重6
     */
	private Integer axle6;
    /**
     * 轴重7
     */
	private Integer axle7;
    /**
     * 轴重8
     */
	private Integer axle8;
    /**
     * 车头照片
     */
	private String vehicleimage;
    /**
     * 车头照片1
     */
	private String vehicleimage1;
    /**
     * 车头照片2
     */
	private String vehicleimage2;
    /**
     * 处理状态
     */
	private String prostatus;

	/**
	 * 企业名称
	 * @return
	 */
	@TableField(exist = false)
	private String corpname;

	/**
	 * 场景图
	 */
	private String picview;
	/**
	 * 车牌图
	 */
	private String picplate;
	/**
	 * 车尾图
	 */
	private String picviewback;
	/**
	 * 侧1场景图
	 */
	private String picside;
	/**
	 * 侧2场景图
	 */
	private String picside2;
	/**
	 * 全景图
	 */
	private String pictotal;
	/**
	 * 视频1
	 */
	private String video1;
	/**
	 * 视频2
	 */
	private String video2;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public Date getCorptime() {
		return corptime;
	}

	public void setCorptime(Date corptime) {
		this.corptime = corptime;
	}
	
	public String getStrTime() {
		return strTime;
	}
	
	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public String getCorpcode() {
		return corpcode;
	}
	
	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public Integer getAxlesum() {
		return axlesum;
	}

	public void setAxlesum(Integer axlesum) {
		this.axlesum = axlesum;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getLaneno() {
		return laneno;
	}

	public void setLaneno(Integer laneno) {
		this.laneno = laneno;
	}

	public Integer getTotalweight() {
		return totalweight;
	}

	public void setTotalweight(Integer totalweight) {
		this.totalweight = totalweight;
	}

	public Integer getOverlimited() {
		return overlimited;
	}

	public void setOverlimited(Integer overlimited) {
		this.overlimited = overlimited;
	}

	public Integer getAxle1() {
		return axle1;
	}

	public void setAxle1(Integer axle1) {
		this.axle1 = axle1;
	}

	public Integer getAxle2() {
		return axle2;
	}

	public void setAxle2(Integer axle2) {
		this.axle2 = axle2;
	}

	public Integer getAxle3() {
		return axle3;
	}

	public void setAxle3(Integer axle3) {
		this.axle3 = axle3;
	}

	public Integer getAxle4() {
		return axle4;
	}

	public void setAxle4(Integer axle4) {
		this.axle4 = axle4;
	}

	public Integer getAxle5() {
		return axle5;
	}

	public void setAxle5(Integer axle5) {
		this.axle5 = axle5;
	}

	public Integer getAxle6() {
		return axle6;
	}

	public void setAxle6(Integer axle6) {
		this.axle6 = axle6;
	}

	public Integer getAxle7() {
		return axle7;
	}

	public void setAxle7(Integer axle7) {
		this.axle7 = axle7;
	}

	public Integer getAxle8() {
		return axle8;
	}

	public void setAxle8(Integer axle8) {
		this.axle8 = axle8;
	}

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

	public String getProstatus() {
		return prostatus;
	}

	public void setProstatus(String prostatus) {
		this.prostatus = prostatus;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPicview() {
		return picview;
	}

	public void setPicview(String picview) {
		this.picview = picview;
	}

	public String getPicplate() {
		return picplate;
	}

	public void setPicplate(String picplate) {
		this.picplate = picplate;
	}

	public String getPicviewback() {
		return picviewback;
	}

	public void setPicviewback(String picviewback) {
		this.picviewback = picviewback;
	}

	public String getPicside() {
		return picside;
	}

	public void setPicside(String picside) {
		this.picside = picside;
	}

	public String getPicside2() {
		return picside2;
	}

	public void setPicside2(String picside2) {
		this.picside2 = picside2;
	}

	public String getPictotal() {
		return pictotal;
	}

	public void setPictotal(String pictotal) {
		this.pictotal = pictotal;
	}

	public String getVideo1() {
		return video1;
	}

	public void setVideo1(String video1) {
		this.video1 = video1;
	}

	public String getVideo2() {
		return video2;
	}

	public void setVideo2(String video2) {
		this.video2 = video2;
	}

	@Override
	public String toString() {
		return "Corpinfo{" +
				"id=" + id +
				", corpid='" + corpid + '\'' +
				", corptime=" + corptime +
				", strTime='" + strTime + '\'' +
				", corpcode='" + corpcode + '\'' +
				", vehicleid='" + vehicleid + '\'' +
				", vehicletype=" + vehicletype +
				", axlesum=" + axlesum +
				", speed=" + speed +
				", laneno=" + laneno +
				", totalweight=" + totalweight +
				", overlimited=" + overlimited +
				", axle1=" + axle1 +
				", axle2=" + axle2 +
				", axle3=" + axle3 +
				", axle4=" + axle4 +
				", axle5=" + axle5 +
				", axle6=" + axle6 +
				", axle7=" + axle7 +
				", axle8=" + axle8 +
				", vehicleimage='" + vehicleimage + '\'' +
				", vehicleimage1='" + vehicleimage1 + '\'' +
				", vehicleimage2='" + vehicleimage2 + '\'' +
				", prostatus='" + prostatus + '\'' +
				", corpname='" + corpname + '\'' +
				", picview='" + picview + '\'' +
				", picplate='" + picplate + '\'' +
				", picviewback='" + picviewback + '\'' +
				", picside='" + picside + '\'' +
				", picside2='" + picside2 + '\'' +
				", pictotal='" + pictotal + '\'' +
				", video1='" + video1 + '\'' +
				", video2='" + video2 + '\'' +
				'}';
	}
}
