/**
 * 高速出入口信息管理初始化
 */
var Hsway = {
    id: "HswayTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Hsway.initColumn = function () {
    return [
        	{field: 'selectItem', radio: true},
	        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle',formatter: function (value, row, index) {return index+1;}},
            {title: '出入口名称', field: 'hswayname', visible: true, align: 'center', valign: 'middle'},
            {title: '位置', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '检测设备厂商', field: 'devicefirm', visible: true, align: 'center', valign: 'middle'},
            {title: '是否可联网', field: 'isnetName', visible: true, align: 'center', valign: 'middle'},
            {title: '是否有车牌识别', field: 'isdistingName', visible: true, align: 'center', valign: 'middle'},
            {title: '车道宽度', field: 'roadwidth', visible: true, align: 'center', valign: 'middle'},
            {title: '设置时间', field: 'setbegindate', visible: true, align: 'center', valign: 'middle'},
            {title: '最大测重', field: 'maxweight', visible: true, align: 'center', valign: 'middle'},
            {title: '摄像头个数', field: 'cameras', visible: true, align: 'center', valign: 'middle'},
            {title: '摄像头品牌', field: 'camerabrand', visible: true, align: 'center', valign: 'middle'},
            {title: '检测方向', field: 'checkdirection', visible: true, align: 'center', valign: 'middle'},
            {title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'},
            // {title: '所属道路', field: 'roadname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Hsway.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Hsway.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加高速出入口信息
 */
Hsway.openAddHsway = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加高速出入口信息',
        area: ['1300px', '490px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hsway/hsway_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看高速出入口信息详情
 */
Hsway.openHswayDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '高速出入口信息详情',
            area: ['1300px', '490px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hsway/hsway_update/' + Hsway.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除高速出入口信息
 */
Hsway.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/hsway/delete", function (data) {
	            Feng.success("删除成功!");
	            Hsway.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("hswayId",Hsway.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询高速出入口信息列表
 */
Hsway.search = function () {
    var queryData = {};
    queryData['hsRoadName'] = $("#hsRoadName").val();
    queryData['hsWayName'] = $("#hsWayName").val();
    Hsway.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Hsway.initColumn();
    var table = new BSTable(Hsway.id, "/hsway/list", defaultColunms);
    table.setPaginationType("client");
    Hsway.table = table.init({showColumns:true,showRefresh:true});

    //隐藏选择列
    $('#'+Hsway.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
