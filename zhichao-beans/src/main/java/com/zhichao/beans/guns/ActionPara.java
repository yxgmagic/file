package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据服务步骤参数列表
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@TableName("dms_action_para")
public class ActionPara extends Model<ActionPara> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据服务步骤
     */
	private Integer actid;
    /**
     * 参数类型0新建，1引用，2替换值
     */
	private String paratype;
    /**
     * 功能参数
     */
	private Integer paraid;
    /**
     * 参数名
     */
	private String paracode;
    /**
     * 参数替换值
     */
	private String paravalue;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActid() {
		return actid;
	}

	public void setActid(Integer actid) {
		this.actid = actid;
	}

	public String getParatype() {
		return paratype;
	}

	public void setParatype(String paratype) {
		this.paratype = paratype;
	}

	public Integer getParaid() {
		return paraid;
	}

	public void setParaid(Integer paraid) {
		this.paraid = paraid;
	}

	public String getParacode() {
		return paracode;
	}

	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	public String getParavalue() {
		return paravalue;
	}

	public void setParavalue(String paravalue) {
		this.paravalue = paravalue;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ActionPara{" +
			"id=" + id +
			", actid=" + actid +
			", paratype=" + paratype +
			", paraid=" + paraid +
			", paracode=" + paracode +
			", paravalue=" + paravalue +
			"}";
	}
}
