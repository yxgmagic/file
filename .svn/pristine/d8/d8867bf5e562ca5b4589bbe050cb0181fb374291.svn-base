package com.zhichao.beans.node;

/**
 * 
 * jquery ztree 插件的节点
 * 
 * @author zjl
 * @date 2018年1月4日
 */
public class ZTreeNodeEntity {

	private String id;	//节点id
	
	private String pId;//父节点id
	
	private String name;//节点名称
	
	private String entityCode;
	private String entityName;
	
	private Boolean open;//是否打开节点
	
	private Boolean checked;//是否被选中

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	
	public String getEntityCode() {
		return entityCode;
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
	
	public static ZTreeNodeEntity createParent(){
		ZTreeNodeEntity zTreeNode = new ZTreeNodeEntity();
		zTreeNode.setChecked(true);
		zTreeNode.setId("0L");
		zTreeNode.setName("顶级");
		zTreeNode.setOpen(true);
		zTreeNode.setpId("0L");
		return zTreeNode;
	}
}