package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 编码生成规则表
 * </p>
 *
 * @author zqf
 * @since 2018-01-08
 */
@TableName("bs_seq_generate")
public class SeqGenerate extends Model<SeqGenerate> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 编码序列类型
     */
	private String seqtype;
    /**
     * 规则顺序号
     */
	private Integer seqnum;
    /**
     * 规则列名
     */
	private String seqcol;
    /**
     * 长度
     */
	private Integer seqlen;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeqtype() {
		return seqtype;
	}

	public void setSeqtype(String seqtype) {
		this.seqtype = seqtype;
	}

	public Integer getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public String getSeqcol() {
		return seqcol;
	}

	public void setSeqcol(String seqcol) {
		this.seqcol = seqcol;
	}

	public Integer getSeqlen() {
		return seqlen;
	}

	public void setSeqlen(Integer seqlen) {
		this.seqlen = seqlen;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SeqGenerate{" +
			"id=" + id +
			", seqtype=" + seqtype +
			", seqnum=" + seqnum +
			", seqcol=" + seqcol +
			", seqlen=" + seqlen +
			"}";
	}
}
