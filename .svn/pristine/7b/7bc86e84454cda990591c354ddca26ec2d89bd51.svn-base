package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 服务器参数列表
 * </p>
 *
 * @author zqf
 * @since 2018-02-26
 */
@TableName("dms_server_para")
public class ServerPara extends Model<ServerPara> {

    private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据服务
     */
	private Integer authid;
    /**
     * 参数名
     */
	private Integer paraid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthid() {
		return authid;
	}

	public void setAuthid(Integer authid) {
		this.authid = authid;
	}

	public Integer getParaid() {
		return paraid;
	}

	public void setParaid(Integer paraid) {
		this.paraid = paraid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServerPara{" +
			"id=" + id +
			", authid=" + authid +
			", paraid=" + paraid +
			"}";
	}
}
