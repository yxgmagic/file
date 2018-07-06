package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据服务终端与数据服务链关系表
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@TableName("dms_server_auth_chain")
public class ServerAuthChain extends Model<ServerAuthChain> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据服务链
     */
	private Integer chainid;
    /**
     * 数据服务服务端
     */
	private Integer authid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChainid() {
		return chainid;
	}

	public void setChainid(Integer chainid) {
		this.chainid = chainid;
	}

	public Integer getAuthid() {
		return authid;
	}

	public void setAuthid(Integer authid) {
		this.authid = authid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServerAuthChain{" +
			"id=" + id +
			", chainid=" + chainid +
			", authid=" + authid +
			"}";
	}
}
