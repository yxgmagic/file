package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 数据服务步骤列表
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@TableName("dms_action_list")
public class ActionList extends Model<ActionList> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 步骤别名
     */
	private String actalias;
    /**
     * 步骤描述
     */
	private String actdesc;
    /**
     * 步骤实现类
     */
	private String actclass;
    /**
     * 入口方法
     */
	private String actmethod;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActalias() {
		return actalias;
	}

	public void setActalias(String actalias) {
		this.actalias = actalias;
	}

	public String getActdesc() {
		return actdesc;
	}

	public void setActdesc(String actdesc) {
		this.actdesc = actdesc;
	}

	public String getActclass() {
		return actclass;
	}

	public void setActclass(String actclass) {
		this.actclass = actclass;
	}

	public String getActmethod() {
		return actmethod;
	}

	public void setActmethod(String actmethod) {
		this.actmethod = actmethod;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ActionList{" +
			"id=" + id +
			", actalias=" + actalias +
			", actdesc=" + actdesc +
			", actclass=" + actclass +
			", actmethod=" + actmethod +
			"}";
	}
}
