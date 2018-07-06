/**
 * 执法人员信息管理初始化
 */
var LawEnforceMan = {
    id: "LawEnforceManTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LawEnforceMan.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
            {title: '执法人员姓名', field: 'lemName', visible: true, align: 'center', valign: 'middle'},
            {title: '执法人员性别', field: 'lemSexName', visible: false, align: 'center', valign: 'middle'},
            {title: '执法人员生日', field: 'lemBirthday', visible: false, align: 'center', valign: 'middle'},
            {title: '执法人员学历', field: 'lemEduBgName', visible: false, align: 'center', valign: 'middle'},
            {title: '执法人员政治面貌', field: 'lemPoliticsStatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '执法人员身份证号', field: 'lemIdCardNum', visible: false, align: 'center', valign: 'middle'},
            {title: '执法单位', field: 'lawEnforcementAgencies', visible: true, align: 'center', valign: 'middle'},
            {title: '执法人员联系方式', field: 'lemContact', visible: true, align: 'center', valign: 'middle'},
            {title: '执法人员职务', field: 'lemDutyName', visible: false, align: 'center', valign: 'middle'},
            {title: '执法人员级别', field: 'lemGradeName', visible: false, align: 'center', valign: 'middle'},
            {title: '执法人员工作编号', field: 'lemNum', visible: true, align: 'center', valign: 'middle'},
            {title: '所属区域', field: 'areaname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LawEnforceMan.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LawEnforceMan.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加执法人员信息
 */
LawEnforceMan.openAddLawEnforceMan = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加执法人员信息',
        area: ['1100px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lawEnforceMan/lawEnforceMan_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看执法人员信息详情
 */
LawEnforceMan.openLawEnforceManDetail = function () {
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '执法人员信息详情',
            area: ['1100px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/lawEnforceMan/lawEnforceMan_update/' + LawEnforceMan.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除执法人员信息
 */
LawEnforceMan.delete = function () {
    if (this.check()) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/delete", function (data) {
                Feng.success("删除成功!");
                LawEnforceMan.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("lawEnforceManId", LawEnforceMan.seItem.id);
            ajax.start();
    	}
    	Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询执法人员信息列表
 */
LawEnforceMan.search = function () {
    var queryData = {};
    queryData['areacode'] = $("#areacode").val();
    queryData['lemName'] = $("#lemName").val();
    queryData['lawEnforcementAgencies'] = $("#lawEnforcementAgencies").val();
    LawEnforceMan.table.refresh({query: queryData});
};

/**
 * 上传模板到后台,并弹框
 * 
 */
function uploadXls(file) {
	var fileName = file.name;
	if(!/\.(xls)$/g.test(fileName)) {
		layer.msg("文件格式不对");
		return;
	}
	layer.msg("OK,等待上传");
	//利用FormData对象上传文件
	var formData = new FormData();
	
	//将文件放到FormData对象中
	formData.append("file", file);
	formData.append("fileName", fileName);
	$.ajax({
		type : "POST",
		url : Feng.ctxPath + "/lawEnforceMan/uploadXls?jstime=" + new Date().getTime(),
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			if (data.code == 200) {
				layer.msg("上传数据成功");
                LawEnforceMan.table.refresh();

			} else {
                showErrorBox(data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown, data) {
			Feng.error("上传失败,请检查文件数据是否损坏");
		}
	});
	$("#file_hidden").val("");
}

function showErrorBox(data) {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shadeClose: true,
        content: errContent(data.data)
    });
}


function errContent(data) {
    var i = 0;
    var content = '<div style="overflow: scroll;height: 200px;width: 300px;padding-top: 20px; overflow-x: hidden; overflow-y: auto;" class="text-center">';
    content = content + "<h4 style='color: red'>提交的数据有问题</h4>";
    for(i = 0; i < data.length; i++) {
        var itemValue = data[i];
        content = content + "<p>" + itemValue + "</p>";
    }
    content = content + "</div>";

    return content;
}

$(function () {
    var defaultColunms = LawEnforceMan.initColumn();
    var table = new BSTable(LawEnforceMan.id, "/lawEnforceMan/list", defaultColunms);
    table.setPaginationType("client");
    LawEnforceMan.table = table.init({showColumns:false,showRefresh: false});
    
  //隐藏选择列
    $('#'+LawEnforceMan.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
