package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据服务参数列表
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@TableName("dms_para_list")
public class ParaList extends Model<ParaList> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 参数别名
     */
	private String paracode;
    /**
     * 参数名称
     */
	private String paraname;
    /**
     * 参数值
     */
	private String paravalue;
    /**
     * 参数描述
     */
	private String ramark;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParacode() {
		return paracode;
	}

	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	public String getParaname() {
		return paraname;
	}

	public void setParaname(String paraname) {
		this.paraname = paraname;
	}

	public String getParavalue() {
		return paravalue;
	}

	public void setParavalue(String paravalue) {
		this.paravalue = paravalue;
	}

	public String getRamark() {
		return ramark;
	}

	public void setRamark(String ramark) {
		this.ramark = ramark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ParaList{" +
			"id=" + id +
			", paracode=" + paracode +
			", paraname=" + paraname +
			", paravalue=" + paravalue +
			", ramark=" + ramark +
			"}";
	}
}
