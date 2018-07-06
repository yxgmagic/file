package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 指标管理表
 * </p>
 *
 * @author imyzt
 * @since 2018-02-27
 */
@TableName("pe_indicator")
public class Indicator extends Model<Indicator> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 指标名称
     */
	private String indicatorName;
    /**
     * 指标类别
     */
	private String indicatorCategory;
    /**
     * 指标要素
     */
	private String indicatorDescription;
    /**
     * 评分规则
     */
	private String judgeRules;
    /**
     * 分值
     */
	private Integer score;
    /**
     * 指标评分方法
     */
	private String scoreMethod;
    /**
     * notes
     */
	private String notes;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getIndicatorCategory() {
		return indicatorCategory;
	}

	public void setIndicatorCategory(String indicatorCategory) {
		this.indicatorCategory = indicatorCategory;
	}

	public String getIndicatorDescription() {
		return indicatorDescription;
	}

	public void setIndicatorDescription(String indicatorDescription) {
		this.indicatorDescription = indicatorDescription;
	}

	public String getJudgeRules() {
		return judgeRules;
	}

	public void setJudgeRules(String judgeRules) {
		this.judgeRules = judgeRules;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getScoreMethod() {
		return scoreMethod;
	}

	public void setScoreMethod(String scoreMethod) {
		this.scoreMethod = scoreMethod;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Indicator{" +
			"id=" + id +
			", indicatorName=" + indicatorName +
			", indicatorCategory=" + indicatorCategory +
			", indicatorDescription=" + indicatorDescription +
			", judgeRules=" + judgeRules +
			", score=" + score +
			", scoreMethod=" + scoreMethod +
			", notes=" + notes +
			"}";
	}
}
