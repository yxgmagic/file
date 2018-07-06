package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 照片与照片内容实体关系表
 * </p>
 *
 * @author imyzt
 * @since 2018-01-08
 */
@TableName("bs_image_entity")
public class BsImageEntity extends Model<BsImageEntity> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 图片索引
     */
	private Integer imageid;
    /**
     * 照片实体索引
     */
	private Integer entityid;
    /**
     * 照片来源类型
     */
	private String imagetype;
    /**
     * 图片上传操作员
     */
	private Integer userid;
    /**
     * 有效标记
     * 0:无效
     * 1:有效
     */
	private String validflag;
    /**
     * 图片上传日期
     */
	private Date opdate;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getImageid() {
		return imageid;
	}

	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}

	public Integer getEntityid() {
		return entityid;
	}

	public void setEntityid(Integer entityid) {
		this.entityid = entityid;
	}

	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getValidflag() {
		return validflag;
	}

	public void setValidflag(String validflag) {
		this.validflag = validflag;
	}

	public Date getOpdate() {
		return opdate;
	}

	public void setOpdate(Date opdate) {
		this.opdate = opdate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BsImageEntity{" +
			"id=" + id +
			", imageid=" + imageid +
			", entityid=" + entityid +
			", imagetype=" + imagetype +
			", userid=" + userid +
			", validflag=" + validflag +
			", opdate=" + opdate +
			"}";
	}
}
