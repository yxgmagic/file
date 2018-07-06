/**
 * 处罚规则管理初始化
 */
var PunishmentRules = {
    id: "PunishmentRulesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PunishmentRules.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
        	{title: '车型编码', field: 'ruleName', visible: true, align: 'center', valign: 'middle'},
            {title: '车型', field: 'trucksTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '车轴数目', field: 'trucksAxles', visible: false, align: 'center', valign: 'middle'},
            {title: '车轴数目', field: 'trucksAxlesName', visible: true, align: 'center', valign: 'middle'},
            {title: '核定总重（吨）', field: 'weightLimit', visible: true, align: 'center', valign: 'middle'},
            {
            	title: '---图例---', 
            	field: 'imgEntity', 
            	visible: true, 
            	align: 'center', 
            	valign: 'middle',
            	formatter: operateFormatter
           }
    ];
};

function operateFormatter(value, row, index) {
	var imgSrc = getImgSrcById(row.id);
	var result = '<img alt="暂无图片" src="' + imgSrc + '" width="167px;" height="44px" class="">';
    return [
    	result,
     ].join('');
 }

/**
 * 给表格的每行加上图片
 */
function getImgSrcById(id) {

	var itype;
	var imgSrc = "";
	itype = 'punishmentRules';

	$.ajax({
		type : "POST",
		url : Feng.ctxPath + "/image/findImg",
		data : {
			"entityId":id,
			"imagetype":itype
		},
		async : false,
		cache : false,
		success : function(data) {
			if(data.code == 200){
				imgSrc = "data:image/png;base64,"+data.msg.img;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown, data) {
			
		}
	});

	return imgSrc;
	
}

/**
 * 检查是否选中
 */
PunishmentRules.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PunishmentRules.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加处罚规则
 */
PunishmentRules.openAddPunishmentRules = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加处罚规则',
        area: ['800px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/punishmentRules/punishmentRules_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看处罚规则详情
 */
PunishmentRules.openPunishmentRulesDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '处罚规则详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/punishmentRules/punishmentRules_update/' + PunishmentRules.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除处罚规则
 */
PunishmentRules.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/punishmentRules/delete", function (data) {
	            Feng.success("删除成功!");
	            PunishmentRules.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("punishmentRulesId",PunishmentRules.seItem.id);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询处罚规则列表
 */
PunishmentRules.search = function () {
	
    var queryData = {};
    queryData['trucksType'] = $("#trucksType").val();
    queryData['trucksAxles'] = $("#trucksAxles").val();
    PunishmentRules.table.refresh({query: queryData});
    
};

$(function () {
    var defaultColunms = PunishmentRules.initColumn();
    var table = new BSTable(PunishmentRules.id, "/punishmentRules/list", defaultColunms);
    table.setPaginationType("client");
    PunishmentRules.table = table.init();

    
    getTrucksTypeList()
    getTrucksAxlesList()
    
    dictToMenu(trucksTypeData, $("#trucksType"));
    dictToMenu(trucksAxlesData, $("#trucksAxles"));
   
    $('#'+PunishmentRules.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});
    
});


/**
 * 获取车型字典列表 /getTrucksTypeList
 */
var trucksTypeData;
function getTrucksTypeList() {
	var ajax = new $ax(Feng.ctxPath + "/punishmentRules/getTrucksTypeList", function (data) {
		trucksTypeData = data;
    }, function (data) {
        Feng.error("获取信息失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

/**
 * 获取车轴数字典列表 getTrucksAxlesList
 */
var trucksAxlesData;
function getTrucksAxlesList() {
	var ajax = new $ax(Feng.ctxPath + "/punishmentRules/getTrucksAxlesList", function (data) {
		trucksAxlesData = data;
    }, function (data) {
        Feng.error("获取信息失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//将将字典的值设置到下拉菜单
function dictToMenu(distData, downMenu) {
	var tempData = distData;
	var length = tempData.length;
	var i;
	var tempArr = new Array();
	for(i = 0; i < length; i++) {
		var temp = tempData[i];
		var option = "<option value='"+temp.num+"'>"+temp.name+"</option>";
		downMenu.append(option);
	}
}