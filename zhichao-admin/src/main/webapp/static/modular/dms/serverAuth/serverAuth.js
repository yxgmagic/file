/**
 * 数据服务注册管理初始化
 */
var ServerAuth = {
    id: "ServerAuthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServerAuth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '服务代码', field: 'alias', visible: true, align: 'center', valign: 'middle'},
            {title: '服务别名', field: 'aliasName', visible: true, align: 'center', valign: 'middle'},
            {title: '网络地址', field: 'url', visible: true, align: 'center', valign: 'middle'},
            {title: '电脑名称', field: 'computerName', visible: true, align: 'center', valign: 'middle'},
            {title: '硬盘号', field: 'diskno', visible: true, align: 'center', valign: 'middle'},
            {title: '网卡地址', field: 'macno', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'begindt', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'enddt', visible: true, align: 'center', valign: 'middle'},
            {title: '注册码', field: 'license', visible: true, align: 'center', valign: 'middle'},
            {title: 'CDKEY', field: 'cdkey', visible: true, align: 'center', valign: 'middle'},
            {title: '有效状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态', field: 'auditStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '用户编码', field: 'usercode', visible: true, align: 'center', valign: 'middle'},
            {title: '用户密码', field: 'userpassword', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'creatdt', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'creatid', visible: true, align: 'center', valign: 'middle'},
            {title: '审核时间', field: 'auditdt', visible: true, align: 'center', valign: 'middle'},
            {title: '审核人', field: 'auditid', visible: true, align: 'center', valign: 'middle'},
            {title: '所属部门', field: 'deptid', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updt', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'upid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServerAuth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServerAuth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务注册
 */
ServerAuth.openAddServerAuth = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务注册',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serverAuth/serverAuth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务注册详情
 */
ServerAuth.openServerAuthDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务注册详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serverAuth/serverAuth_update/' + ServerAuth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务注册
 */
ServerAuth.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/serverAuth/delete", function (data) {
	            Feng.success("删除成功!");
	            ServerAuth.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("serverAuthId",ServerAuth.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务注册列表
 */
ServerAuth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ServerAuth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServerAuth.initColumn();
    var table = new BSTable(ServerAuth.id, "/serverAuth/list", defaultColunms);
    table.setPaginationType("client");
    ServerAuth.table = table.init();
});
