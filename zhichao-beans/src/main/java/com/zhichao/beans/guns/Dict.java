package com.zhichao.beans.guns;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author zhichao
 * @since 2017-07-11
 */
@TableName("sys_dict")
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 排序
     */
	private String num;
    /**
     * 父级字典
     */
	private Integer pid;
    /**
     * 名称
     */
	private String name;
    /**
     * 提示
     */
	private String tips;
    /**
     * 列名
     */
	private String pname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPname() {
		return pname;
	}
 
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Dict{" +
			"id=" + id +
			", num=" + num +
			", pid=" + pid +
			", name=" + name +
			", pname=" + pname +
			", tips=" + tips +
			"}";
	}
}
