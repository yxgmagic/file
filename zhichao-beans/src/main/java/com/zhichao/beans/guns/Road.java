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
 * @author zhangqf
 * @since 2017-12-26
 */
@TableName("bs_road")
public class Road extends Model<Road> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 道路编码
     */
	private String roadcode;
    /**
     * 道路名称
     */
	private String roadname;
    /**
     * 道路位置
     */
	private String address;
    /**
     * 所属行政区域
     */
	private String areacode;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoadcode() {
		return roadcode;
	}

	public void setRoadcode(String roadcode) {
		this.roadcode = roadcode;
	}

	public String getRoadname() {
		return roadname;
	}

	public void setRoadname(String roadname) {
		this.roadname = roadname;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Road{" +
			"id=" + id +
			", roadcode=" + roadcode +
			", roadname=" + roadname +
			", address=" + address +
			", areacode=" + areacode +
			"}";
	}
}