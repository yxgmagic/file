/**
 * 行政区域信息管理管理初始化
 */
var Area = {
    id: "municipalTable",	//表格id
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
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  //给每一条记录都加上序号
            return index+1;  
        }},
            {title: '行政区域代码', field: 'areacode', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区域名称', field: 'areaname', visible: true, align: 'center', valign: 'middle'},
            {title: '行政区类别', field: 'areatypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '地理位置', field: 'address', visible: true, align: 'center', valign: 'middle'}
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
 * 点击添加市级信息管理
 */
Area.openAddArea = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加市级信息',
        area: ['800px', '390px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/municipal/municipal_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看市级信息详情
 */
Area.openAreaDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '行政区域信息管理详情',
            area: ['800px', '390px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/municipal/municipal_update/' + Area.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除市级信息
 */
Area.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/municipal/delete", function (data) {
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
 * 查询市级信息列表
 */
Area.search = function () {
    var queryData = {};
    queryData['areaname'] = $("#areaname").val();
    queryData['areacode'] = $("#areacode").val();
    queryData['areatype'] = $("#areatype").val();
    Area.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Area.initColumn();
    var table = new BSTable(Area.id, "/municipal/list", defaultColunms);
    table.setPaginationType("client");
    Area.table = table.init({showColumns:false,showRefresh: false});
    
    //隐藏选择列
    $('#'+Area.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
    
    getAreaTypeList();
    init1();
});



//下面是我魔改的代码
var areaTypeData = null;
//获取行政区域类型列表
function getAreaTypeList() {
  var ajax = new $ax(Feng.ctxPath + "/municipal/getAreaTypeList", function (data) {
     // Feng.success("成功!");
  	areaTypeData = data;
  }, function (data) {
      Feng.error("从后台获取数据失败!" + data.responseJSON.message + "!");
  });

ajax.start();
};

//初始化,我写的
function init1() {
	
	//获取区域类别和所属区域的下拉菜单对象
	var typeSelectMenu = $("#areatype");
	
	//将获取到的行政区域类型列表添加到区域类别下拉菜单
	var option = "";
	var tempData = areaTypeData;
	var index;
	for(index = 2; index < tempData.length; index++) {
		var temp = tempData[index];
		option = "<option value='"+temp.num+"'>"+temp.name+"</option>";
		typeSelectMenu.append(option);
	}
	
	
}
