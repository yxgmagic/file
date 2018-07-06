package com.zhichao.beans.guns;

import java.io.Serializable;

import java.sql.Blob;
import java.util.Arrays;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 存储营业执照,汽车图片等字段
 * </p>
 *
 * @author imyzt
 * @since 2018-01-02
 */
@TableName("bs_image")
public class Image extends Model<Image> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	private Integer id;
    /**
     * 文件名
     */
	private String imgname;
    /**
     * 文件
     */
	private String img;
    /**
     * 文件类型
     */
	private String imgext;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImgext() {
		return imgext;
	}
	public void setImgext(String imgext) {
		this.imgext = imgext;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", imgname=" + imgname + ", img=" + img + ", imgext=" + imgext + "]";
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
