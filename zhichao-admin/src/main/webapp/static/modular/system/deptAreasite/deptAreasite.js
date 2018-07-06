/**
 * 区域站点权限管理初始化
 */
var DeptAreasite = {
    id: "DeptAreasiteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    ztreeDept:null
};

/**
 * 初始化表格的列
 */
DeptAreasite.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
		{  
			field : 'Number',  
			title : '序号',  
			formatter: function (value, row, index) {  
  
				return	index + 1;  
			}
		},
		 {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '部门编号', field: 'deptid', visible: true, align: 'center', valign: 'middle'},
            {title: '区域或站点编号', field: 'areasitecode', visible: true, align: 'center', valign: 'middle'},
            {title: '区域或站点类型', field: 'areasitetype', visible: true, align: 'center', valign: 'middle'},
            {title: '新增权限', field: 'authadd', visible: true, align: 'center', valign: 'middle'},
            {title: '删除权限', field: 'authdelete', visible: true, align: 'center', valign: 'middle'},
            {title: '查询权限', field: 'authselect', visible: true, align: 'center', valign: 'middle'},
            {title: '修改权限', field: 'authupdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'crtdate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'crtuserid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DeptAreasite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DeptAreasite.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加区域站点权限
 */
DeptAreasite.openAddDeptAreasite = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加区域站点权限',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/deptAreasite/deptAreasite_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看区域站点权限详情
 */
DeptAreasite.openDeptAreasiteDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '区域站点权限详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/deptAreasite/deptAreasite_update/' + DeptAreasite.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除区域站点权限
 */
DeptAreasite.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/deptAreasite/delete", function (data) {
	            Feng.success("删除成功!");
	            DeptAreasite.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("deptAreasiteId",DeptAreasite.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询区域站点权限列表
 */
DeptAreasite.search = function () {
    var queryData = {};
    queryData['areasitecode'] = $("#areacode").val();
    queryData['deptid'] = $("#deptid").val();
    DeptAreasite.table.refresh({query: queryData});
};
DeptAreasite.showDeptSelectTree = function() {
	Feng.showInputTree("deptName", "parentDeptMenu");
}
DeptAreasite.onClickDept = function(e, treeId, treeNode) {
	$("#deptName").attr("value", DeptAreasite.zTreeDept.getSelectedVal());
	$("#deptid").attr("value", treeNode.id);
}
$(function () {
    var defaultColunms = DeptAreasite.initColumn();
    var table = new BSTable(DeptAreasite.id, "/deptAreasite/list", defaultColunms);
    table.setPaginationType("client");
    DeptAreasite.table = table.init();
    

	//部门
	var ztreeDept = new $ZTree("parentDeptMenuTree", "/dept/tree");
	ztreeDept.bindOnClick(DeptAreasite.onClickDept);
	ztreeDept.init();
	DeptAreasite.zTreeDept = ztreeDept;

});
