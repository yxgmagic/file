/**
 * 初始化处罚规则详情对话框
 */
var PunishmentRulesInfoDlg = {
    punishmentRulesInfoData : {}
};

/**
 * 清除数据
 */
PunishmentRulesInfoDlg.clearData = function() {
    this.punishmentRulesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PunishmentRulesInfoDlg.set = function(key, val) {
    this.punishmentRulesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PunishmentRulesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PunishmentRulesInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/punishmentRules");
	top.layer.close(winOpen.PunishmentRules.layerIndex);
}

/**
 * 收集数据
 */
PunishmentRulesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ruleName')
    .set('trucksType')
    .set('trucksAxles')
    .set('weightLimit')
    .set('imgId')
    .set('img_id')
    .set('imagetype');
}

/**
 * 提交添加
 */
PunishmentRulesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/punishmentRules/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/punishmentRules");
        winOpen.PunishmentRules.table.refresh();
        top.layer.close(winOpen.PunishmentRules.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.punishmentRulesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PunishmentRulesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/punishmentRules/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/punishmentRules");
        winOpen.PunishmentRules.table.refresh();
        top.layer.close(winOpen.PunishmentRules.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.punishmentRulesInfoData);
    ajax.start();
}

$(function() {

	punishmentRulesInit();
	
});

//我新增的一些代码

//初始化代码
function punishmentRulesInit() {
	
	
	getTrucksTypeList()
    getTrucksAxlesList()
    
    dictToMenu(trucksTypeData, $("#trucksType"));
    dictToMenu(trucksAxlesData, $("#trucksAxles"));
    
    $("#trucksType").val(trucksTypeValue);
    $("#trucksAxles").val(trucksAxlesValue);
    
    initPunishmentRulesValidate();
    
    
	
	//Feng.getImage($("#id").val(), $("#imagetype").val());
	
	
	$.ajax({
		type : "POST",
		url : Feng.ctxPath + "/image/findImg",
		data : {
			"entityId":$("#id").val(),
			"imagetype":$("#imagetype").val()
		},
		async : false,
		cache : false,
		success : function(data) {
			if(data.code == 200){
				$("#img_show")[0].src = "data:image/png;base64,"+data.msg.img;
			}else{
				$("#img_show")[0].src = Feng.ctxPath+'/static/img/notfoundbl.png';
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown, data) {
			
		}
	});
	
	
	
	//监听上传按钮的改变事件
	$("#img_hidden").change(function() {
		//隐藏图片(input=file)id,显示图片id,上传图片后图片id
		Feng.ajaxFileUploadEntityImage();
	});
}




/**
 * 验证数据是否为空
 */
PunishmentRulesInfoDlg.validate = function () {
    $('#idPunishmentRulesForm').data("bootstrapValidator").resetForm();
    $('#idPunishmentRulesForm').bootstrapValidator('validate');
    return $("#idPunishmentRulesForm").data('bootstrapValidator').isValid();
}

/**
 * 初始化表单验证代码
 */
function initPunishmentRulesValidate() {
	$('#idPunishmentRulesForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields : {
			trucksType: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        trucksAxles: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        weightLimit: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        }
		}
		
	});
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


//图片预览
	$("#img_show").click(function() {
		layer.open({
			  type: 1,
			  title: '图片预览',
			  closeBtn: 1,
			  area: ['700px', '450px'],
			  skin: 'layui-layer-nobg', //没有背景色
			  shadeClose: true,
			  content: "<img id='openImg' width='694px' heigth='500px' src='"+$("#img_show")[0].src+"'>"
			});
	});