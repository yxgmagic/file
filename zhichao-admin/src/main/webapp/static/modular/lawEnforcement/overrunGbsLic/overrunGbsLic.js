/**
 * 超限货物许可证管理初始化
 */
var OverrunGbsLic = {
    id: "OverrunGbsLicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OverrunGbsLic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'corpid', visible: true, align: 'center', formatter: function (value, row, index) {return  index + 1}},
            {title: '许可证号', field: 'licid', visible: true, align: 'center', valign: 'middle'},
            {title: '车牌号码', field: 'carid', visible: true, align: 'center', valign: 'middle'},
            {title: '源头企业', field: 'sourcename', visible: true, align: 'center', valign: 'middle'},
            {title: '车主姓名', field: 'drivername', visible: true, align: 'center', valign: 'middle'},
            {title: '联系方式', field: 'driverphone', visible: true, align: 'center', valign: 'middle'},
            {title: '道路运输证号', field: 'wayid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
OverrunGbsLic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OverrunGbsLic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加超限货物许可证
 */
OverrunGbsLic.openAddOverrunGbsLic = function () {
    var index = parent.layer.open({
        type: 2,
        title: '添加超限货物许可证',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/overrunGbsLic/overrunGbsLic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看超限货物许可证详情
 */
OverrunGbsLic.openOverrunGbsLicDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '超限货物许可证详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/overrunGbsLic/overrunGbsLic_update/' + OverrunGbsLic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除超限货物许可证
 */
OverrunGbsLic.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/overrunGbsLic/delete", function (data) {
	            Feng.success("删除成功!");
	            OverrunGbsLic.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("overrunGbsLicId",OverrunGbsLic.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询超限货物许可证列表
 */
OverrunGbsLic.search = function () {
    var queryData = {};
    queryData['licid'] = $("#licid").val();
    queryData['carid'] = $("#carid").val();
    queryData['sourcename'] = $("#sourcename").val();
    queryData['drivername'] = $("#drivername").val();
    queryData['driverphone'] = $("#driverphone").val();
    queryData['wayid'] = $("#wayid").val();
    OverrunGbsLic.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OverrunGbsLic.initColumn();
    var table = new BSTable(OverrunGbsLic.id, "/overrunGbsLic/list", defaultColunms);
    table.setPaginationType("client");
    OverrunGbsLic.table = table.init();
    
    //隐藏选择列
    $('#'+OverrunGbsLic.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
