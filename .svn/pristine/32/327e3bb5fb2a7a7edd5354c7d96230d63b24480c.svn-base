package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhichao
 * @since 2018-01-22
 */
@TableName("bs_overrun_gbs_lic")
public class OverrunGbsLic extends Model<OverrunGbsLic> {

    private static final long serialVersionUID = 1L;

    /**
     * 超限许可证查询表id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 许可证号
     */
	private String licid;
    /**
     * 车牌号码
     */
	private String carid;
    /**
     * 源头企业
     */
	private String sourcename;
    /**
     * 车主姓名
     */
	private String drivername;
    /**
     * 联系方式
     */
	private String driverphone;
    /**
     * 道路运输证号
     */
	private String wayid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLicid() {
		return licid;
	}

	public void setLicid(String licid) {
		this.licid = licid;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDriverphone() {
		return driverphone;
	}

	public void setDriverphone(String driverphone) {
		this.driverphone = driverphone;
	}

	public String getWayid() {
		return wayid;
	}

	public void setWayid(String wayid) {
		this.wayid = wayid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "OverrunGbsLic{" +
			"id=" + id +
			", licid=" + licid +
			", carid=" + carid +
			", sourcename=" + sourcename +
			", drivername=" + drivername +
			", driverphone=" + driverphone +
			", wayid=" + wayid +
			"}";
	}
}
