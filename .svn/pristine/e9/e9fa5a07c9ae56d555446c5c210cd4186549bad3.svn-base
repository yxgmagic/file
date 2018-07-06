/**
 * 初始化执法人员信息详情对话框
 */
var LawEnforceManInfoDlg = {
    lawEnforceManInfoData : {}
};

/**
 * 清除数据
 */
LawEnforceManInfoDlg.clearData = function() {
    this.lawEnforceManInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LawEnforceManInfoDlg.set = function(key, val) {
    this.lawEnforceManInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LawEnforceManInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LawEnforceManInfoDlg.close = function() {
    var winOpen=Feng.GetFrame("/lawEnforceMan");
	top.layer.close(winOpen.LawEnforceMan.layerIndex);
}

/**
 * 收集数据 //被ning魔改了一下
 */
LawEnforceManInfoDlg.collectData = function() {
    this
    .set('id')
    .set('lemName')
    .set('lemSex')
    .set('lemBirthday')
    .set('lemEduBg')
    .set('lemPoliticsStatus')
    .set('lemIdCardNum')
    .set('lawEnforcementAgencies')
    .set('lemContact')
    .set('lemDuty')
    .set('lemGrade')
    .set('lemNum')
    .set('areacode')
    .set('img_id')
    .set('imagetype');
}

/**
 * 提交添加
 */
LawEnforceManInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/add", function(data){
        Feng.success("添加成功!");
       
        var winOpen=Feng.GetFrame("/lawEnforceMan");
        winOpen.LawEnforceMan.table.refresh();
        top.layer.close(winOpen.LawEnforceMan.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lawEnforceManInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LawEnforceManInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/update", function(data){
        Feng.success("修改成功!");
        var winOpen=Feng.GetFrame("/lawEnforceMan");
        winOpen.LawEnforceMan.table.refresh();
        top.layer.close(winOpen.LawEnforceMan.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lawEnforceManInfoData);
    ajax.start();
}

$(function() {

	
	lawEnforceManinit();
	
});

//下面是我新加的代码

//初始化一些东西
function lawEnforceManinit() {
	getSexList();
	getEduBgList();
	getPolCodeList();
	getLemGradeList();
	getLemDutyList();

	dictToMenu(sexData, $("#lemSex"));
    dictToMenu(eduBgData, $("#lemEduBg"));
    dictToMenu(polCodeData, $("#lemPoliticsStatus"));
    dictToMenu(lemGradeData, $("#lemGrade"));
    dictToMenu(lemDutyData, $("#lemDuty"));

    $("#lemSex").val(lemSexValue);
    $("#lemEduBg").val(lemEduBgValue);
    $("#lemPoliticsStatus").val(lemPoliticsStatusValue);
    $("#lemDuty").val(lemDutyValue);
    $("#lemGrade").val(lemGradeValue);
	
	initLawEnforceManValidate();
	
	Feng.getImage($("#id").val(), $("#imagetype").val());
	//监听上传按钮的改变事件
	$("#img_hidden").change(function() {
		//隐藏图片(input=file)id,显示图片id,上传图片后图片id
		Feng.ajaxFileUploadEntityImage();
	});
	
	
}




/**
 * 验证数据是否为空
 */
LawEnforceManInfoDlg.validate = function () {
    $('#idLawEnforceManForm').data("bootstrapValidator").resetForm();
    $('#idLawEnforceManForm').bootstrapValidator('validate');
    return $("#idLawEnforceManForm").data('bootstrapValidator').isValid();
}


//初始化lawEnforceMan表单验证
function initLawEnforceManValidate() {
	//idLawEnforceManForm
	$('#idLawEnforceManForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields : {
			lemName: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        lemContact: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
					regexp: {
						regexp: '^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$',
						message: '手机号码格式错误!'
					}
	            }
	        },
	        lemNum: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
	                callback: {
						message: '编号已存在',  
						callback: function(value, validator) {
							
							var res = true;
							if(value.length >= 1) {	
								$.ajax({
									url: Feng.ctxPath + '/lawEnforceMan/hasPerson',//验证地址
									type: 'POST',//请求方式
									dataType: 'json',
									async: false,
									data: {
										lemNum: value,
										lemId: $("#id").val()
										},
									success: function(data) {
										res = data.valid
									}
									
								});
							}
							return res;
						}
	                }
	            }
	        },
	        Select2: {
	            validators: {
	                notEmpty: {
	                    message: '请选择区域'
	                }
	            }
	        },
	        lemIdCardNum: {
	        	validators: {
	                notEmpty: {
	                    message: '请输入身份证号码'
	                },
	                regexp: {
						regexp: /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/,
						message: '身份证号码格式错误!'
					},
					callback: {
						message: '身份证号码已存在',  
						callback: function(value, validator) {
							
							var res = true;
							if(value.length > 17) {
								$.ajax({
									url: Feng.ctxPath + '/lawEnforceMan/hasPerson',//验证地址
									type: 'POST',//请求方式
									dataType: 'json',
									async: false,
									data: {
										lemIdCardNum: value,
										lemId: $("#id").val()
										},
									success: function(data) {
										res = data.valid
									}
									
								});
							}
							
							return res;
						}
						
					}
                   
	            }
	        },
	        lemSex: {
	            validators: {
	                notEmpty: {
	                    message: '请选择性别'
	                }
	            }
	        },
	        lemEduBg: {
	            validators: {
	                notEmpty: {
	                    message: '请选择学历'
	                }
	            }
	        },
	        lemPoliticsStatus: {
	            validators: {
	                notEmpty: {
	                    message: '请选择政治面貌'
	                }
	            }
	        },
	        lemDuty: {
	            validators: {
	                notEmpty: {
	                    message: '请选择执法人员职责'
	                }
	            }
	        },
	        lemGrade: {
	            validators: {
	                notEmpty: {
	                    message: '请选择执法人员等级'
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

//获取性别列表
var sexData = [];
function getSexList() {
	var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/getSexList", function (data) {
		//听说不要性别显示出来,所以我把它剔除了
		for(var i = 0; i < data.length; i++) {
			var temp = data[i];
			if(temp.name != "性别") {
                sexData.push(temp)
			}
		}
    }, function (data) {
        Feng.error("获取信息失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//获取学历列表 getEduBgList
var eduBgData;
function getEduBgList() {
	var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/getEduBgList", function (data) {
		eduBgData = data;
    }, function (data) {
        Feng.error("获取信息失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//获取政治面貌列表 getPolCodeList
var polCodeData;
function getPolCodeList() {
	var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/getPolCodeList", function (data) {
		polCodeData = data;
    }, function (data) {
        Feng.error("获取信息失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//获取执法人员等级列表getLemGradeList
var lemGradeData;
function getLemGradeList() {
	var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/getLemGradeList", function (data) {
		lemGradeData = data;
    }, function (data) {
        Feng.error("获取信息失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//获取执法人员职务列表getLemDutyList
var lemDutyData;
function getLemDutyList() {
	var ajax = new $ax(Feng.ctxPath + "/lawEnforceMan/getLemDutyList", function (data) {
		lemDutyData = data;
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



