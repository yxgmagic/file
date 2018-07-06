package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 行政区域信息
 * </p>
 *
 * @author yice
 * @since 2018-01-02
 */
@TableName("bs_area")
public class Area extends Model<Area> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer pid;
    /**
     * 行政区域代码
     */
	private String areacode;
    /**
     * 行政区域名称
     */
	private String areaname;
    /**
     * 行政区域字母码
     */
	private String arealetter;
    /**
     * 行政区类别
     */
	private String areatype;
    /**
     * 地址
     */
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getArealetter() {
		return arealetter;
	}

	public void setArealetter(String arealetter) {
		this.arealetter = arealetter;
	}

	public String getAreatype() {
		return areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Area{" +
			"id=" + id +
			", pid=" + pid +
			", areacode=" + areacode +
			", areaname=" + areaname +
			", arealetter=" + arealetter +
			", areatype=" + areatype +
			", address=" + address +
			"}";
	}
}
