/**
 * 预检站信息管理初始化
 */
var Presite = {
    id: "PresiteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Presite.initColumn = function () {
	return [
		{field: 'selectItem', radio: true},
		{  
			field : 'Number',  
			title : '序号',  
			formatter: function (value, row, index) {  
//				var page = $('#PresiteTable').bootstrapTable("getPage"); 
//				return page.pageSize * (page.pageNumber - 1) + index + 1;  
				return	index + 1;  
			}
		},

	 
		{title: '站点编码', field: 'sitecode', visible: true, align: 'center', valign: 'middle'},
		{title: '站点名称', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
		{title: '经度', field: 'longitude', visible: false, align: 'center', valign: 'middle'},
		{title: '纬度', field: 'latitude', visible: false, align: 'center', valign: 'middle'},
		{title: '地址', field: 'address', visible: false, align: 'center', valign: 'middle'},
		{title: '所属区域编码', field: 'areacode', visible: false, align: 'center', valign: 'middle'},
		{title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'},
		{title: '所属道路编码', field: 'roadcode', visible: false, align: 'center', valign: 'middle'},
		{title: '所属道路', field: 'roadname', visible: true, align: 'center', valign: 'middle'},
		 
		{title: '站点等级', field: 'sitelevelname', visible: true, align: 'center', valign: 'middle'},
		{title: '限高', field: 'heightlimited', visible: true, align: 'center', valign: 'middle'},
		{title: '限长', field: 'longlimited', visible: true, align: 'center', valign: 'middle'},
		{title: '限宽', field: 'widthlimited', visible: true, align: 'center', valign: 'middle'},
		{title: '限重', field: 'weightlimited', visible: true, align: 'center', valign: 'middle'},
		{title: '精确度', field: 'precision', visible: true, align: 'center', valign: 'middle'},
		{title: '车道宽度', field: 'roadwidth', visible: false, align: 'center', valign: 'middle'},
		{title: '管理部门编码', field: 'deptid', visible: false, align: 'center', valign: 'middle'},
		{title: '管理部门', field: 'simplename', visible: true, align: 'center', valign: 'middle'},
		{title: '负责人姓名', field: 'manager', visible: false, align: 'center', valign: 'middle'},
		{title: '联系方式', field: 'managertel', visible: false, align: 'center', valign: 'middle'},

		{title: '检测方式', field: 'checkmodename', visible: true, align: 'center', valign: 'middle'},
		{title: '检测方向', field: 'checkdirection', visible: false, align: 'center', valign: 'middle'}
		];
};

/**
 * 检查是否选中
 */
Presite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Presite.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加预检站信息
 */
Presite.openAddPresite = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加预检站信息',
        area: ['1200px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/presite/presite_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看预检站信息详情
 */
Presite.openPresiteDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '预检站信息详情',
            area: ['1200px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/presite/presite_update/' + Presite.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除预检站信息
 */
Presite.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/presite/delete", function (data) {
	            Feng.success("删除成功!");
	            Presite.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("presiteId",Presite.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询预检站信息列表
 */
Presite.search = function () {
    var queryData = {};
    queryData['areacode'] = $("#areacode").val();
    queryData['sitename'] = $("#sitename").val();
    Presite.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Presite.initColumn();
    var table = new BSTable(Presite.id, "/presite/list", defaultColunms);
    table.setPaginationType("client");
    
    Presite.table = table.init();
 
  //隐藏选择列
    $('#'+Presite.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
