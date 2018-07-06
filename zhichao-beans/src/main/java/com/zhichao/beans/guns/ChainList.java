package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据服务链列表
 * </p>
 *
 * @author zqf
 * @since 2018-03-05
 */
@TableName("dms_chain_list")
public class ChainList extends Model<ChainList> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据服务链别名
     */
	private String chainalias;
    /**
     * 数据服务链描述
     */
	private String chaindesc;
    /**
     * 接收服务
     */
	private String inchainclass;
    /**
     * 推送服务
     */
	private String outchainclass;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChainalias() {
		return chainalias;
	}

	public void setChainalias(String chainalias) {
		this.chainalias = chainalias;
	}

	public String getChaindesc() {
		return chaindesc;
	}

	public void setChaindesc(String chaindesc) {
		this.chaindesc = chaindesc;
	}

	public String getInchainclass() {
		return inchainclass;
	}

	public void setInchainclass(String inchainclass) {
		this.inchainclass = inchainclass;
	}

	public String getOutchainclass() {
		return outchainclass;
	}

	public void setOutchainclass(String outchainclass) {
		this.outchainclass = outchainclass;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ChainList{" +
			"id=" + id +
			", chainalias=" + chainalias +
			", chaindesc=" + chaindesc +
			", inchainclass=" + inchainclass +
			", outchainclass=" + outchainclass +
			"}";
	}
}
