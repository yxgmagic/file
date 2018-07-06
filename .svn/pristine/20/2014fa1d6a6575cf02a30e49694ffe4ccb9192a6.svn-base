package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 编码单号实时记录表
 * </p>
 *
 * @author zqf
 * @since 2018-01-10
 */
@TableName("bs_sequence")
public class Sequence extends Model<Sequence> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	private String seqname;
    /**
     * 编码序列类型
     */
	private String seqtype;
    /**
     * 字符串
     */
	private String seqstr;
    /**
     * 流水生成日期
     */
	private String seqdate;
    /**
     * 当前最大流水号
     */
	private Integer seqmax;
    /**
     * 流水号长度
     */
	private Integer seqlen;
    /**
     * 取数列
     */
	private String seqcol;
    /**
     * 取数表
     */
	private String seqtab;
    /**
     * 部门
     */
	private String seqdept;
    /**
     * 车道号
     */
	private Integer roadnum;
    /**
     * 流水号是否连续
     */
	private Integer isseries;
    /**
     * 编码单号
     */
	private String sequence;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeqname() {
		return seqname;
	}

	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	public String getSeqtype() {
		return seqtype;
	}

	public void setSeqtype(String seqtype) {
		this.seqtype = seqtype;
	}

	public String getSeqstr() {
		return seqstr;
	}

	public void setSeqstr(String seqstr) {
		this.seqstr = seqstr;
	}

	public String getSeqdate() {
		return seqdate;
	}

	public void setSeqdate(String seqdate) {
		this.seqdate = seqdate;
	}

	public Integer getSeqmax() {
		return seqmax;
	}

	public void setSeqmax(Integer seqmax) {
		this.seqmax = seqmax;
	}

	public Integer getSeqlen() {
		return seqlen;
	}

	public void setSeqlen(Integer seqlen) {
		this.seqlen = seqlen;
	}

	public String getSeqcol() {
		return seqcol;
	}

	public void setSeqcol(String seqcol) {
		this.seqcol = seqcol;
	}

	public String getSeqtab() {
		return seqtab;
	}

	public void setSeqtab(String seqtab) {
		this.seqtab = seqtab;
	}

	public String getSeqdept() {
		return seqdept;
	}

	public void setSeqdept(String seqdept) {
		this.seqdept = seqdept;
	}

	public Integer getRoadnum() {
		return roadnum;
	}

	public void setRoadnum(Integer roadnum) {
		this.roadnum = roadnum;
	}

	public Integer getIsseries() {
		return isseries;
	}

	public void setIsseries(Integer isseries) {
		this.isseries = isseries;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Sequence{" +
			"id=" + id +
			", seqname=" + seqname +
			", seqtype=" + seqtype +
			", seqstr=" + seqstr +
			", seqdate=" + seqdate +
			", seqmax=" + seqmax +
			", seqlen=" + seqlen +
			", seqcol=" + seqcol +
			", seqtab=" + seqtab +
			", seqdept=" + seqdept +
			", roadnum=" + roadnum +
			", isseries=" + isseries +
			", sequence=" + sequence +
			"}";
	}
}
