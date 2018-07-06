﻿/**
 * 数据服务器参数管理管理初始化
 */
var ServerPara = {
    id: "ServerParaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ServerPara.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '数据服务', field: 'authid', visible: true, align: 'center', valign: 'middle'},
            {title: '参数名', field: 'paraid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ServerPara.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ServerPara.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据服务器参数管理
 */
ServerPara.openAddServerPara = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加数据服务器参数管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/serverPara/serverPara_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据服务器参数管理详情
 */
ServerPara.openServerParaDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '数据服务器参数管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/serverPara/serverPara_update/' + ServerPara.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据服务器参数管理
 */
ServerPara.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/serverPara/delete", function (data) {
	            Feng.success("删除成功!");
	            ServerPara.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("serverParaId",ServerPara.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询数据服务器参数管理列表
 */
ServerPara.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ServerPara.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ServerPara.initColumn();
    var table = new BSTable(ServerPara.id, "/serverPara/list", defaultColunms);
    table.setPaginationType("client");
    ServerPara.table = table.init();
});