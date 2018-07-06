/**
 * 路网信息管理初始化
 */
var Road = {
    id: "RoadTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Road.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
		{  
			field : 'Number',  
			title : '序号',  
			formatter: function (value, row, index) {  
				

				return  index + 1;  
			}
		},
            {title: '道路编码', field: 'roadcode', visible: true, align: 'center', valign: 'middle'},
            {title: '道路名称', field: 'roadname', visible: true, align: 'center', valign: 'middle'},
            {title: '道路位置', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '所属行政区域编码', field: 'areacode', visible: false, align: 'center', valign: 'middle'},
            {title: '所属行政区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Road.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Road.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加路网信息
 */
Road.openAddRoad = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加路网信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/road/road_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看路网信息详情
 */
Road.openRoadDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '路网信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/road/road_update/' + Road.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除路网信息
 */
Road.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/road/delete", function (data) {
	            Feng.success("删除成功!");
	            Road.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("roadId",Road.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询路网信息列表
 */
Road.search = function () {
    var queryData = {};
    queryData['roadname'] = $("#roadname").val();
    queryData['areacode'] = $("#areacode").val();
    Road.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Road.initColumn();
    var table = new BSTable(Road.id, "/road/list", defaultColunms);
    table.setPaginationType("client");
    Road.table = table.init();
    
    
    $('#'+Road.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});
});
