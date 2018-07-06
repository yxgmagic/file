package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据服务链步骤表
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@TableName("dms_chain_action")
public class ChainAction extends Model<ChainAction> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 步骤执行顺序
     */
	private Integer nums;
    /**
     * 服务链
     */
	private Integer serverchainid;
    /**
     * 步骤
     */
	private Integer actid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Integer getServerchainid() {
		return serverchainid;
	}

	public void setServerchainid(Integer serverchainid) {
		this.serverchainid = serverchainid;
	}

	public Integer getActid() {
		return actid;
	}

	public void setActid(Integer actid) {
		this.actid = actid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ChainAction{" +
			"id=" + id +
			", nums=" + nums +
			", serverchainid=" + serverchainid +
			", actid=" + actid +
			"}";
	}
}
