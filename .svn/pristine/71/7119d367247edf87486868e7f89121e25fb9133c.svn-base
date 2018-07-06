package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 处罚规则车辆信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-11
 */
@TableName("bs_punishment_rules")
public class PunishmentRules extends Model<PunishmentRules> {

    private static final long serialVersionUID = 1L;

    /**
     * 处罚规则id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 规则名称
     */
	@TableField("rule_name")
	private String ruleName;
    /**
     * 车型
     */
	@TableField("trucks_type")
	private String trucksType;
    /**
     * 车轴数目
     */
	@TableField("trucks_axles")
	private String trucksAxles;
    /**
     * 核定总重
     */
	@TableField("weight_limit")
	private Integer weightLimit;
    /**
     * 显示图片的id（预留字段）
     */
	@TableField("img_id")
	private Integer imgId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getTrucksType() {
		return trucksType;
	}

	public void setTrucksType(String trucksType) {
		this.trucksType = trucksType;
	}

	public String getTrucksAxles() {
		return trucksAxles;
	}

	public void setTrucksAxles(String trucksAxles) {
		this.trucksAxles = trucksAxles;
	}

	public Integer getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Integer weightLimit) {
		this.weightLimit = weightLimit;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PunishmentRules{" +
			"id=" + id +
			", ruleName=" + ruleName +
			", trucksType=" + trucksType +
			", trucksAxles=" + trucksAxles +
			", weightLimit=" + weightLimit +
			", imgId=" + imgId +
			"}";
	}
}
