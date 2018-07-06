package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报表模板
 * </p>
 *
 * @author zqf
 * @since 2018-03-14
 */
@TableName("rf__templates")
public class Templates extends Model<Templates> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 报表代码
     */
	private String rfcode;
    /**
     * 报表名称
     */
	private String rfname;
    /**
     * 报表说明
     */
	private String rfdesc;
    /**
     * 报表模板路径
     */
	private String rfsrc;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfcode() {
		return rfcode;
	}

	public void setRfcode(String rfcode) {
		this.rfcode = rfcode;
	}

	public String getRfname() {
		return rfname;
	}

	public void setRfname(String rfname) {
		this.rfname = rfname;
	}

	public String getRfdesc() {
		return rfdesc;
	}

	public void setRfdesc(String rfdesc) {
		this.rfdesc = rfdesc;
	}

	public String getRfsrc() {
		return rfsrc;
	}

	public void setRfsrc(String rfsrc) {
		this.rfsrc = rfsrc;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Templates{" +
			"id=" + id +
			", rfcode=" + rfcode +
			", rfname=" + rfname +
			", rfdesc=" + rfdesc +
			", rfsrc=" + rfsrc +
			"}";
	}
}
