/**
 * 行政区域管理管理初始化
 */
var Area = {
    id: "AreaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Area.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        	{title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
            {title: '行政区域代码', field: 'areacode', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区域名称', field: 'areaname', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区域字母码', field: 'arealetter', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区类别', field: 'areatypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Area.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Area.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加行政区域管理
 */
Area.openAddArea = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加行政区域管理',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/arc/area_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看行政区域管理详情
 */
Area.openAreaDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '行政区域管理详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/arc/area_update/' + Area.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除行政区域管理
 */
Area.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/arc/delete", function (data) {
                Feng.success("删除成功!");
                Area.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("areaId", Area.seItem.id);
            ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }      
};

/**
 * 查询行政区域管理列表
 */
Area.search = function () {
    var queryData = {};
    queryData['pid'] = $("#pid").val();
    queryData['areatype'] = $("#areatype").val();
    queryData['areaname'] = $("#areaname").val();
    queryData['areacode'] = $("#areacode").val();
    queryData['address'] = $("#address").val();
    Area.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Area.initColumn();
    var table = new BSTable(Area.id, "/arc/list", defaultColunms);
    table.setPaginationType("client");
    Area.table = table.init({showRefresh:false, showColumns:false});
    
    //隐藏选择列
    $('#'+Area.id).on('all.bs.table', function (){
    	$(".bs-checkbox").attr('style',"display:none");
    });
});
