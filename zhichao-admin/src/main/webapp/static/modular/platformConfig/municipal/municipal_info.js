/**
 * 初始化行政区域管理详情对话框
 */
var AreaInfoDlg = {
    areaInfoData : {},
    validateFields : {
    	areaname: {
            validators: {
                notEmpty: {
                    message: '行政区域名称不能为空'
                }
            }
        },
        areacode: {
            validators: {
                notEmpty: {
                    message: '行政区域代码不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
AreaInfoDlg.clearData = function() {
    this.areaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaInfoDlg.set = function(key, val) {
    this.areaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AreaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 * parent.layer.close(window.parent.Area.layerIndex);
 */
AreaInfoDlg.close = function() {    
    var winOpen=Feng.GetFrame("/municipal");
	top.layer.close(winOpen.Area.layerIndex);
}

/**
 * 收集数据 //被ning魔改了一下
 */
AreaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pid')
    .set('areacode')
    .set('areaname')
    .set('arealetter')
    .set('areatype')
    .set('address');
}

/**
 * 提交添加
 */
AreaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/municipal/add", function(data){
        Feng.success("添加成功!");
        var winOpen=Feng.GetFrame("/municipal");
        winOpen.Area.table.refresh();
        top.layer.close(winOpen.Area.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.areaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AreaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/municipal/update", function(data){
        Feng.success("修改成功!");
        var winOpen=Feng.GetFrame("/municipal");
        winOpen.Area.table.refresh();
        top.layer.close(winOpen.Area.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.areaInfoData);
    ajax.start();
}

$(function() {
	
	getAreaTypeList();
	getArealist();
	init1();
	
	Feng.initValidator("municipalForm", AreaInfoDlg.validateFields);
});


//下面是我另外新增的js代码

/**
 * 验证数据是否为空
 */
AreaInfoDlg.validate = function () {
    $('#municipalForm').data("bootstrapValidator").resetForm();
    $('#municipalForm').bootstrapValidator('validate');
    return $("#municipalForm").data('bootstrapValidator').isValid();
}

//行政区域所有信息 json (市，区 两级的数据)
var json =null;
var areaData = null;
function getArealist() {
    var ajax = new $ax(Feng.ctxPath + "/municipal/list", function (data) {
       // Feng.success("成功!");
           json=data;
    }, function (data) {
        Feng.error("从后台获取数据失败!" + data.responseJSON.message + "!");
    });
 ajax.set("pid","");
 ajax.set("areatype","");
 ajax.start();
};

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

//存储区域类别和所属区域的下拉菜单对象
var typeSelectMenu;
var pidSelectMenu;

function init1() {
	
	//获取区域类别和所属区域的下拉菜单对象
	typeSelectMenu = $("#areatype");
	pidSelectMenu = $("#pid");
	
	//将获取到的行政区域类型列表添加到区域类别下拉菜单
	var option = "";
	var tempData = areaTypeData;
	var index;
	for(index = 1; index < tempData.length; index++) {
		var temp = tempData[index];
		option = "<option value='"+temp.num+"'>"+temp.name+"</option>";
		typeSelectMenu.append(option);
	}
	
	//获取到需要显示的下拉框的值，将值赋值到下拉菜单
	if(typeof(typeOptionValue) == "undefined" || typeof(pidOptionValue) == "undefined" ) { //如这两个信息没有，不需要给下拉菜单赋值
		typeOptionValue = 2;
		pidOptionValue = 1;
	} 
	
	
	areaData = json;
	
	//根据行政类别来决定需要显示哪些所属区域
	/*
	 * 思路：
	 * 先到数据里面找，找到一个行政区域与当前选中类型相同的行政区域，
	 * 再找这个行政区域的父级行政区域，
	 * 再根据父级的行政类型来获取同类型的行政区域
	 * 将这些区域设置到下拉菜单当中
	 */
	
	var areaList = getAreaByType(typeOptionValue);
	
	//将这些区域设置到下拉菜单当中
	pidSelectMenu.children().remove();
	for(var i = 0; i < areaList.length; i++) {
		var temp = tempData[i];
		option = "<option value='"+areaList[i][0]+"'>"+areaList[i][1]+"</option>";
		pidSelectMenu.append(option);
	}
	
	//设置下拉框选中值
	$("#areatype").val(typeOptionValue);
	$("#pid").val(pidOptionValue);
	
}

//行政区域类别 下拉菜单被改变时触发的事件
$("#areatype").change(function(){
	var tempData = areaTypeData;
	var option=$(this).children('option:selected').val();//这就是selected的值
	var areaList = getAreaByType(option);
	//将这些区域设置到下拉菜单当中
	pidSelectMenu.children().remove();
	for(var i = 0; i < areaList.length; i++) {
		var temp = tempData[i];
		option = "<option value='"+areaList[i][0]+"'>"+areaList[i][1]+"</option>";
		pidSelectMenu.append(option);
	}
})

//根据行政类别来决定需要显示哪些所属区域
	/*
	 * 思路：
	 * 先到数据里面找，找到一个行政区域与当前选中类型相同的行政区域，
	 * 再找这个行政区域的父级行政区域，
	 * 再根据父级的行政类型来获取同类型的行政区域
	 * 将这些区域设置到下拉菜单当中
	 */
function getAreaByType(typeValue) {
	
	//找到找到一个行政区域与当前选中类型相同的行政区域
	var areaId = 0;
	var tempArea = null;
	for(var i = 0; i < json.length; i++) {
		if(json[i].areatype == typeValue) {
			tempArea = json[i];
			break;
		}
	}
	
	//再找这个行政区域的父级行政区域的行政类型
	var tempType = 0;
	for(var i = 0; i < json.length; i++) {
		if(json[i].id == tempArea.pid) {
			tempType = json[i].areatype;
			break;
		}
	}
	
	//再根据父级的行政类型来获取同类型的行政区域
	var areaList = new Array();
	
	for(var i = 0; i < json.length; i++) {
		if(json[i].areatype == tempType) {
			areaList.push([json[i].id, json[i].areaname]);
		}
	}
	
	//返回areaList
	return areaList;
	
}

