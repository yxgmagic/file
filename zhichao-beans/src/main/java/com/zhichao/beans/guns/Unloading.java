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
 * @author imyzt
 * @since 2018-01-02
 */
@TableName("bs_unloading")
public class Unloading extends Model<Unloading> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 编码
     */
	private String ulcode;
    /**
     * 名称
     */
	private String ulname;
    /**
     * 位置
     */
	private String address;
    /**
     * 所属站点
     */
	private String sitecode;
    /**
     * 负责人
     */
	private String manager;
    /**
     * 联系电话
     */
	private String managertel;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUlcode() {
		return ulcode;
	}

	public void setUlcode(String ulcode) {
		this.ulcode = ulcode;
	}

	public String getUlname() {
		return ulname;
	}

	public void setUlname(String ulname) {
		this.ulname = ulname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagertel() {
		return managertel;
	}

	public void setManagertel(String managertel) {
		this.managertel = managertel;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Unloading{" +
			"id=" + id +
			", ulcode=" + ulcode +
			", ulname=" + ulname +
			", address=" + address +
			", sitecode=" + sitecode +
			", manager=" + manager +
			", managertel=" + managertel +
			"}";
	}
}
