package com.zhichao.beans.node;

/**
 * 
 * jquery ztree 插件的节点
 * 
 * @author zjl
 * @date 2018年1月4日
 */
public class ZTreeNodeArea {

	private Long id;	//节点id
	
	private Long pId;//父节点id
	
	private String name;//节点名称
	
	private String areaCode;
	private String areaName;
	
	private Boolean open;//是否打开节点
	
	private Boolean checked;//是否被选中

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	
	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getIsOpen() {
		return open;
	}

	public void setIsOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public static ZTreeNodeArea createParent(){
		ZTreeNodeArea zTreeNode = new ZTreeNodeArea();
		zTreeNode.setChecked(true);
		zTreeNode.setId(0L);
		zTreeNode.setName("顶级");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(0L);
		return zTreeNode;
	}
}