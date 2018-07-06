package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据流转链与节点路径关系
 * </p>
 *
 * @author zqf
 * @since 2018-02-28
 */
@TableName("dms_server_chain")
public class ServerChain extends Model<ServerChain> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据服务链
     */
	private Integer chainid;
    /**
     * 关联服务节点
     */
	private Integer serverid;
    /**
     * 数据流向类型
     */
	private String type;
    /**
     * 路径顺序
     */
	private Integer nums;


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

	public Integer getServerid() {
		return serverid;
	}

	public void setServerid(Integer serverid) {
		this.serverid = serverid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServerChain{" +
			"id=" + id +
			", chainid=" + chainid +
			", serverid=" + serverid +
			", type=" + type +
			", nums=" + nums +
			"}";
	}
}
