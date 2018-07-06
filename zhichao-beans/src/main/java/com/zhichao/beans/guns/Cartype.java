package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 车型分类信息表
 * </p>
 *
 * @author zjl
 * @since 2018-01-09
 */
@TableName("bs_cartype")
public class Cartype extends Model<Cartype> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 车型代码
     */
	private Integer carTypeCode;
    /**
     * 车型名称
     */
	private String carTypeName;
    /**
     * 备注
     */
	private String memo;
    /**
     * 分类1
     */
	private String class1;
    /**
     * 分类2
     */
	private String class2;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarTypeCode() {
		return carTypeCode;
	}

	public void setCarTypeCode(Integer carTypeCode) {
		this.carTypeCode = carTypeCode;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getClass2() {
		return class2;
	}

	public void setClass2(String class2) {
		this.class2 = class2;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Cartype{" +
			"id=" + id +
			", carTypeCode=" + carTypeCode +
			", carTypeName=" + carTypeName +
			", memo=" + memo +
			", class1=" + class1 +
			", class2=" + class2 +
			"}";
	}
}
