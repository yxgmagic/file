/**
 * 固定治超站管理初始化
 */
var Fixedsite = {
    id: "FixedsiteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Fixedsite.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
            {title: '站点编号', field: 'sitecode', visible: true, align: 'center', valign: 'middle'},
            {title: '固定治超检测站名称', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
            {title: '治超站等级', field: 'sitelevel', visible: false, align: 'center', valign: 'middle'},
            {title: '经度', field: 'longitude', visible: false, align: 'center', valign: 'middle'},
            {title: '维度', field: 'latitude', visible: false, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: false, align: 'center', valign: 'middle'},
            {title: '规划面积', field: 'planarea', visible: false, align: 'center', valign: 'middle'},
            {title: '卸货厂面积', field: 'unloadarea', visible: false, align: 'center', valign: 'middle'},
            {title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'},
            {title: '所属道路', field: 'roadcode', visible: false, align: 'center', valign: 'middle'},
            {title: '管理部门', field: 'simplename', visible: true, align: 'center', valign: 'middle'},
            {title: '法定代表人', field: 'lawperson', visible: false, align: 'center', valign: 'middle'},
            {title: '法定代表人联系电话', field: 'lawpersontel', visible: false, align: 'center', valign: 'middle'},
            {title: '设置时间', field: 'setbegindate', visible: false, align: 'center', valign: 'middle'},
            {title: '卸货场', field: 'unloadcode', visible: false, align: 'center', valign: 'middle'},
            {title: '卸货场联系人', field: 'unloadperson', visible: false, align: 'center', valign: 'middle'},
            {title: '卸货场联系人电话', field: 'unloadpersontel', visible: false, align: 'center', valign: 'middle'},
            {title: '站点负责人', field: 'manager', visible: true, align: 'center', valign: 'middle'},
            {title: '站点负责人联系方式', field: 'managertel', visible: true, align: 'center', valign: 'middle'},
            {title: '核定编制', field: 'approves', visible: false, align: 'center', valign: 'middle'},
            {title: '实配人数', field: 'actuals', visible: false, align: 'center', valign: 'middle'},
            {title: '检测人数', field: 'checks', visible: false, align: 'center', valign: 'middle'},
            {title: '合同制人数', field: 'contracts', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Fixedsite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Fixedsite.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加固定治超站
 */
Fixedsite.openAddFixedsite = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加固定治超站',
        area: ['1100px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/fixedsite/fixedsite_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看固定治超站详情
 */
Fixedsite.openFixedsiteDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '固定治超站详情',
            area: ['1100px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/fixedsite/fixedsite_update/' + Fixedsite.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除固定治超站
 */
Fixedsite.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/fixedsite/delete", function (data) {
                Feng.success("删除成功!");
                Fixedsite.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("fixedsiteId",Fixedsite.seItem.id);
            ajax.start();
    	}
    	Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询固定治超站列表
 */
Fixedsite.search = function () {
    var queryData = {};
    queryData['areacode'] = $("#areacode").val();
    queryData['sitename'] = $("#sitename").val();
    queryData['manager'] = $("#manager").val();
    queryData['managertel'] = $("#managertel").val();
    queryData['roadcode'] = $("#roadcode").val();
    Fixedsite.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Fixedsite.initColumn();
    var table = new BSTable(Fixedsite.id, "/fixedsite/list", defaultColunms);
    table.setPaginationType("client");
    Fixedsite.table = table.init();
    
    $('#'+Fixedsite.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});
});


