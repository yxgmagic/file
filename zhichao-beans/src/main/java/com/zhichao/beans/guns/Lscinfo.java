package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 精检站数据表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-04-08
 */
@TableName("b_lscinfo")
public class Lscinfo extends Model<Lscinfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 检测单号
     */
	private String checkno;
    /**
     * 车牌号
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
     * 初检车道号
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
     * 初检轴重1
     */
	private Integer fcaxle1;
    /**
     * 初检轴重2
     */
	private Integer fcaxle2;
    /**
     * 初检轴重3
     */
	private Integer fcaxle3;
    /**
     * 初检轴重4
     */
	private Integer fcaxle4;
    /**
     * 初检轴重5
     */
	private Integer fcaxle5;
    /**
     * 初检轴重6
     */
	private Integer fcaxle6;
    /**
     * 初检轴重7
     */
	private Integer fcaxle7;
    /**
     * 初检轴重8
     */
	private Integer fcaxle8;
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
     * 复检轴重1
     */
	private Integer rcaxle1;
    /**
     * 复检轴重2
     */
	private Integer rcaxle2;
    /**
     * 复检轴重3
     */
	private Integer rcaxle3;
    /**
     * 复检轴重4
     */
	private Integer rcaxle4;
    /**
     * 复检轴重5
     */
	private Integer rcaxle5;
    /**
     * 复检轴重6
     */
	private Integer rcaxle6;
    /**
     * 复检轴重7
     */
	private Integer rcaxle7;
    /**
     * 复检轴重8
     */
	private Integer rcaxle8;
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
     * 初检车头图片1
     */
	private String fcvehicleimage1;
    /**
     * 初检车头图片2
     */
	private String fcvehicleimage2;
    /**
     * 复检车头图片
     */
	private String rcvehicleimage;
    /**
     * 复检车头图片1
     */
	private String rcvehicleimage1;
    /**
     * 复检车头图片2
     */
	private String rcvehicleimage2;
    /**
     * 检测站号
     */
	private String stationid;
    /**
     * 处理状态,0未违规,1以上为违规状态
     */
	private String prostatus;
    /**
     * 上传返回代码
     */
	private Integer result;
    /**
     * 上传返回信息
     */
	private String message;
    /**
     * 是否显示
     */
	private Integer isshow;
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
	/**
	 * 车辆车型信息
	 */
	private String trucksRuleName;
	/**
	 * 初检时间String
	 */
	@TableField(exist = false)
	private String fctimeString;
	/**
	 * 复检时间String
	 */
	@TableField(exist = false)
	private String rctimeString;

	public String getTrucksRuleName() {
		return trucksRuleName;
	}

	public void setTrucksRuleName(String trucksRuleName) {
		this.trucksRuleName = trucksRuleName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getFctimeString() {
		return fctimeString;
	}

	public void setFctimeString(String fctimeString) {
		this.fctimeString = fctimeString;
	}

	public String getRctimeString() {
		return rctimeString;
	}

	public void setRctimeString(String rctimeString) {
		this.rctimeString = rctimeString;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCheckno() {
		return checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
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

	public Integer getFcaxle1() {
		return fcaxle1;
	}

	public void setFcaxle1(Integer fcaxle1) {
		this.fcaxle1 = fcaxle1;
	}

	public Integer getFcaxle2() {
		return fcaxle2;
	}

	public void setFcaxle2(Integer fcaxle2) {
		this.fcaxle2 = fcaxle2;
	}

	public Integer getFcaxle3() {
		return fcaxle3;
	}

	public void setFcaxle3(Integer fcaxle3) {
		this.fcaxle3 = fcaxle3;
	}

	public Integer getFcaxle4() {
		return fcaxle4;
	}

	public void setFcaxle4(Integer fcaxle4) {
		this.fcaxle4 = fcaxle4;
	}

	public Integer getFcaxle5() {
		return fcaxle5;
	}

	public void setFcaxle5(Integer fcaxle5) {
		this.fcaxle5 = fcaxle5;
	}

	public Integer getFcaxle6() {
		return fcaxle6;
	}

	public void setFcaxle6(Integer fcaxle6) {
		this.fcaxle6 = fcaxle6;
	}

	public Integer getFcaxle7() {
		return fcaxle7;
	}

	public void setFcaxle7(Integer fcaxle7) {
		this.fcaxle7 = fcaxle7;
	}

	public Integer getFcaxle8() {
		return fcaxle8;
	}

	public void setFcaxle8(Integer fcaxle8) {
		this.fcaxle8 = fcaxle8;
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

	public Integer getRcaxle1() {
		return rcaxle1;
	}

	public void setRcaxle1(Integer rcaxle1) {
		this.rcaxle1 = rcaxle1;
	}

	public Integer getRcaxle2() {
		return rcaxle2;
	}

	public void setRcaxle2(Integer rcaxle2) {
		this.rcaxle2 = rcaxle2;
	}

	public Integer getRcaxle3() {
		return rcaxle3;
	}

	public void setRcaxle3(Integer rcaxle3) {
		this.rcaxle3 = rcaxle3;
	}

	public Integer getRcaxle4() {
		return rcaxle4;
	}

	public void setRcaxle4(Integer rcaxle4) {
		this.rcaxle4 = rcaxle4;
	}

	public Integer getRcaxle5() {
		return rcaxle5;
	}

	public void setRcaxle5(Integer rcaxle5) {
		this.rcaxle5 = rcaxle5;
	}

	public Integer getRcaxle6() {
		return rcaxle6;
	}

	public void setRcaxle6(Integer rcaxle6) {
		this.rcaxle6 = rcaxle6;
	}

	public Integer getRcaxle7() {
		return rcaxle7;
	}

	public void setRcaxle7(Integer rcaxle7) {
		this.rcaxle7 = rcaxle7;
	}

	public Integer getRcaxle8() {
		return rcaxle8;
	}

	public void setRcaxle8(Integer rcaxle8) {
		this.rcaxle8 = rcaxle8;
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

	public String getFcvehicleimage1() {
		return fcvehicleimage1;
	}

	public void setFcvehicleimage1(String fcvehicleimage1) {
		this.fcvehicleimage1 = fcvehicleimage1;
	}

	public String getFcvehicleimage2() {
		return fcvehicleimage2;
	}

	public void setFcvehicleimage2(String fcvehicleimage2) {
		this.fcvehicleimage2 = fcvehicleimage2;
	}

	public String getRcvehicleimage() {
		return rcvehicleimage;
	}

	public void setRcvehicleimage(String rcvehicleimage) {
		this.rcvehicleimage = rcvehicleimage;
	}

	public String getRcvehicleimage1() {
		return rcvehicleimage1;
	}

	public void setRcvehicleimage1(String rcvehicleimage1) {
		this.rcvehicleimage1 = rcvehicleimage1;
	}

	public String getRcvehicleimage2() {
		return rcvehicleimage2;
	}

	public void setRcvehicleimage2(String rcvehicleimage2) {
		this.rcvehicleimage2 = rcvehicleimage2;
	}

	public String getStationid() {
		return stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getProstatus() {
		return prostatus;
	}

	public void setProstatus(String prostatus) {
		this.prostatus = prostatus;
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

	public Integer getIsshow() {
		return isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
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
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Lscinfo{" +
			"id=" + id +
			", checkno=" + checkno +
			", vehicleid=" + vehicleid +
			", axlesum=" + axlesum +
			", weightlimited=" + weightlimited +
			", ratedtotalweight=" + ratedtotalweight +
			", ratedloading=" + ratedloading +
			", overlimited=" + overlimited +
			", overload=" + overload +
			", fctime=" + fctime +
			", fclaneno=" + fclaneno +
			", fctotalweight=" + fctotalweight +
			", fcoper=" + fcoper +
			", fcaxle1=" + fcaxle1 +
			", fcaxle2=" + fcaxle2 +
			", fcaxle3=" + fcaxle3 +
			", fcaxle4=" + fcaxle4 +
			", fcaxle5=" + fcaxle5 +
			", fcaxle6=" + fcaxle6 +
			", fcaxle7=" + fcaxle7 +
			", fcaxle8=" + fcaxle8 +
			", fclength=" + fclength +
			", fcwidth=" + fcwidth +
			", fcheight=" + fcheight +
			", overlength=" + overlength +
			", overwidth=" + overwidth +
			", overheight=" + overheight +
			", rctime=" + rctime +
			", rclaneno=" + rclaneno +
			", rctotalweight=" + rctotalweight +
			", rcoper=" + rcoper +
			", rcaxle1=" + rcaxle1 +
			", rcaxle2=" + rcaxle2 +
			", rcaxle3=" + rcaxle3 +
			", rcaxle4=" + rcaxle4 +
			", rcaxle5=" + rcaxle5 +
			", rcaxle6=" + rcaxle6 +
			", rcaxle7=" + rcaxle7 +
			", rcaxle8=" + rcaxle8 +
			", rclength=" + rclength +
			", rcwidth=" + rcwidth +
			", rcheight=" + rcheight +
			", offload=" + offload +
			", fcvehicleimage=" + fcvehicleimage +
			", fcvehicleimage1=" + fcvehicleimage1 +
			", fcvehicleimage2=" + fcvehicleimage2 +
			", rcvehicleimage=" + rcvehicleimage +
			", rcvehicleimage1=" + rcvehicleimage1 +
			", rcvehicleimage2=" + rcvehicleimage2 +
			", stationid=" + stationid +
			", prostatus=" + prostatus +
			", result=" + result +
			", message=" + message +
			", isshow=" + isshow +
			", picview=" + picview +
			", picplate=" + picplate +
			", picviewback=" + picviewback +
			", picside=" + picside +
			", picside2=" + picside2 +
			", pictotal=" + pictotal +
			", video1=" + video1 +
			", video2=" + video2 +
			"}";
	}
}
