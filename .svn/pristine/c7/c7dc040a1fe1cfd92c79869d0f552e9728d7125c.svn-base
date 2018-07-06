/**
 * 初始化预检站信息详情对话框
 */
var PresiteInfoDlg = {
		presiteInfoData : {},zTreeDept : null,
		validateFields: {
			longlimited: {
				validators: {
					notEmpty: {
						message: 'longlimited不能为空'
					}
				}
			},
			weightlimited: {
				validators: {
					notEmpty: {
						message: 'weightlimited不能为空'
					}
				}
			},
            sitename: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            }




		}
};

/**
 * 清除数据
 */
PresiteInfoDlg.clearData = function() {
	this.presiteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PresiteInfoDlg.set = function(key, val) {
	this.presiteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
	return this;
}
//监听上传按钮的改变事件
$("#img_hidden").change(function() {
	//隐藏图片(input=file)id,显示图片id,上传图片后图片id
	Feng.ajaxFileUploadEntityImage('img_hidden','img_show','img_id');
});

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PresiteInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PresiteInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/presite");
	top.layer.close(winOpen.Presite.layerIndex);
}

/**
 * 收集数据
 */
PresiteInfoDlg.collectData = function() {
	this
	.set('id')
	.set('sitecode')
	.set('sitename')
	.set('img_id')
	.set('imagetype')
	.set('longitude')
	.set('latitude')
	.set('address')
	.set('areacode')
	.set('roadcode')
	.set('sitelevel')
	.set('heightlimited')
	.set('longlimited')
	.set('widthlimited')
	.set('weightlimited')
	.set('precision')
	.set('roadwidth')
	.set('deptid')
	.set('manager')
	.set('managertel')
	.set('checkmode')
	.set('checkdirection')
		.set('stakeNumber')
		.set('certificate')
		.set('certificateValidityPeriod');
}



/**
 * 验证数据是否为空
 */
PresiteInfoDlg.validate01 = function () {

    if(this.get("certificate").length==0) {
        $("#certificateA").text("请上传检定证书!!!");
        $("#certificateA").css("color","red");
        return false;
    }

	if(this.get("sitename").length==0){
		$("#sitename").focus();

		Feng.error("没有填写站点名称!");
		return false;
	}

    if(this.get("stakeNumber").length==0){
        $("#stakeNumber").focus();
        Feng.error("没有填写桩号!");
        return false;
    }
    if(this.get("certificate").length==0){
        $("#certificate").focus();
        Feng.error("没有选择检定证书!");
        return false;
    }
    if(this.get("certificateValidityPeriod").length==0){
        $("#certificateValidityPeriod").focus();
        Feng.error("没有填写检定证书有效期!");
        return false;
    }
 
	if(this.get("sitelevel")==''){
		$("#sitelevel").focus();
		Feng.error("没有选择站点等级!"+this.get("sitelevel"));
		return false;
	}
	if(this.get("checkmode")==''){
		$("#checkmode").focus();
		Feng.error("没有选择检测方式!"+this.get("checkmode"));
		return false;
	}
	if(this.get("areacode").length<6){
		$("#areacode").focus();
		Feng.error("没有选择具体管理路段!");
		return false;
	}
	if(this.get("deptid").length<1){
		Feng.error("没有选择管理部门!");
		return false;
	}
	if(this.get("roadcode").length<6){
		$("#roadcode").focus();
		Feng.error("没有选择具体所属路网!");
		return false;
	}
	var jd=this.get("longitude");
	if(jd.length == 0){
		$("#longitude").focus();
		Feng.error("经度不可为空");
		return false;
	}
	var wd=this.get("latitude");
	if(wd.length==0){
		$("#latitude").focus();
		Feng.error("纬度不可为空");
		return false;
	}
	var lm=this.get("longlimited");
	if(lm.length==0||lm<1000||lm>24000){
		$("#longlimited").focus();
		Feng.error("限长(毫米)取值范围为1000毫米到24000毫米之间!");
		return false;
	}

    var jqd=this.get("precision");
    if(jqd.length==0){
        $("#precision").focus();
        Feng.error("误差不可为空!");
        return false;
    }

	// var jqd=this.get("precision");
	// if(jqd.length==0||jqd<0||jqd>1){
	// 	$("#precision").focus();
	// 	Feng.error("精确度(误差)取值范围为0到0.99999之间!");
	// 	return false;
	// }
	var hm=this.get("heightlimited");
	if(hm.length==0||hm<1000||hm>6000){
		$("#heightlimited").focus();
		Feng.error("限高(毫米)取值范围为1000毫米到6000毫米之间!");
		return false;
	}
	var wm=this.get("widthlimited");
	if(wm.length==0||wm<1000||wm>6000){
		$("#widthlimited").focus();
		Feng.error("限宽(毫米)取值范围为1000毫米到6000毫米之间!");
		return false;
	}
	var kg=this.get("weightlimited");
	if(kg.length==0||kg<1000||kg>100000){
		$("#weightlimited").focus();
		Feng.error("限重(千克)取值范围为1000千克到100000千克之间!");
		return false;
	}
	return true;
};

/**
 * 获取地图坐标
 */
$(".getcoor").on("click", function () {
    // lng, lat, src, lngId, latId
    var lng = $("#longitude").val() || "";
    var lat = $("#latitude").val() || "";
    var src = "/presite/presite_add";
    var lngId = "longitude";
    var latId = "latitude";
    var options = {
        lng: lng,
        lat: lat,
        src: src,
        lngId: lngId,
        latId: latId
    };
    Yi.components.setMapMarker(options);
});

/**
 * 获取地图坐标
 */
$(".getcoor2").on("click", function () {
    // lng, lat, src, lngId, latId
    var lng = $("#longitude").val() || "";
    var lat = $("#latitude").val() || "";
    var src = "/presite/presite_update";
    var lngId = "longitude";
    var latId = "latitude";
    var options = {
        lng: lng,
        lat: lat,
        src: src,
        lngId: lngId,
        latId: latId
    };
    Yi.components.setMapMarker(options);
});



//当上传完文件之后改变左边检定证书的名字
var certificateFile = null;	//需要上传的证书文件
$("#file_input").change(function() {
    if(this.files.length > 0) {
        var file = this.files[0];
        certificateFile = file;
        var certificateName = file.name.replace(/(\.\w+$)/,"");
        $("#certificate").val(certificateName).change();
        $("#certificateA").text(certificateName);
        $("#certificateA").css("color","#00B83F");
    } else {
        $("#certificate").val("").change();
        $("#certificateA").text("请点击上传按钮上传");
        $("#certificateA").css("color","#00B83F");
    }
});
//展示检定证书
function showCertificate() {
    var files = $("#file_input")[0].files;
    var fileCount = $("#file_input")[0].files.length
    if(fileCount > 0) {
        Ning.Tools.showPage(Ning.Tools.getObjectURL(files[0]));
        return;
    }
    if(certificateUrl.length > 0) {
        Ning.Tools.showPage(Feng.ctxPath + "/tempController/fileDownload?isDownload=0&fileUrl=" + certificateUrl);

        return;
    }
    return;
}

/**
 * 提交添加
 */
PresiteInfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();
	if(!this.validate()){
		return;
	}


	//利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.presiteInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/presite/add?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            if( "undefined"==data.code||data.code!='200'){Feng.error("新增失败！"+data.ERROR);return;}
            Feng.success("添加成功!");
            var winOpen=Feng.GetFrame("/presite");
            winOpen.Presite.table.refresh();
            top.layer.close(winOpen.Presite.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("添加失败!");
        }
    });




	//提交信息
	// var ajax = new $ax(Feng.ctxPath + "/presite/xxx", function(data){
    //
    	// if( "undefined"==data.code||data.code!='200'){Feng.error("新增失败！"+data.ERROR);return;}
	// 	Feng.success("添加成功!");
    //
	// 	var winOpen=Feng.GetFrame("/presite");
	// 	winOpen.Presite.table.refresh();
	// 	top.layer.close(winOpen.Presite.layerIndex);
	// },function(data){
	// 	Feng.error("添加失败!" + data.responseJSON.message + "!");
	// });
	// ajax.set(this.presiteInfoData);
	// //ajax.set(formData);
	// ajax.start();
}


/**
 * 提交修改
 */
PresiteInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();
	if(!this.validate()){
		return;
	}


    //利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.presiteInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/presite/update?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            if( "undefined"==data.code||data.code!='200'){Feng.error("新增失败！"+data.ERROR);return;}
            Feng.success("添加成功!");
            var winOpen=Feng.GetFrame("/presite");
            winOpen.Presite.table.refresh();
            top.layer.close(winOpen.Presite.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("添加失败!");
        }
    });

	// //提交信息
	// var ajax = new $ax(Feng.ctxPath + "/presite/update", function(data){
	// 	if( "undefined"==data.code||data.code!='200'){Feng.error("修改失败！"+data.ERROR);return;}
	// 	Feng.success("修改成功!");
    //
	// 	var winOpen=Feng.GetFrame("/presite");
	// 	winOpen.Presite.table.refresh();
	// 	top.layer.close(winOpen.Presite.layerIndex);
	// },function(data){
	// 	Feng.error("修改失败!" + data.responseJSON.message + "!");
	// });
	// ajax.set(this.presiteInfoData);
	// ajax.start();
}
PresiteInfoDlg.showDeptSelectTree = function() {
	Feng.showInputTree("deptName", "parentDeptMenu");
}
PresiteInfoDlg.onClickDept = function(e, treeId, treeNode) {
	$("#deptName").attr("value", PresiteInfoDlg.zTreeDept.getSelectedVal());
	$("#deptid").attr("value", treeNode.id);
}
$(function() {
    //设置证书有效期限时间框
    Yang.Tools.date_interval('certificateValidityPeriod','date','~','1970-01-01 00:00:00','2099-12-12 23:59:59');

	//部门
	var ztreeDept = new $ZTree("parentDeptMenuTree", "/dept/tree");
	ztreeDept.bindOnClick(PresiteInfoDlg.onClickDept);
	ztreeDept.init();
	PresiteInfoDlg.zTreeDept = ztreeDept;


    presiteInitValidate();

});


/**
 * 验证数据是否为空
 */
PresiteInfoDlg.validate = function () {
    $('#idPresiteForm').data("bootstrapValidator").resetForm();
    $('#idPresiteForm').bootstrapValidator('validate');

    var flag = true;

    if(this.get("certificate").length==0) {
        $("#certificateA").text("请上传检定证书!!!");
        $("#certificateA").css("color","red");
        flag = false;
    }

    if(this.get("checkmode")==''){
        $("#checkmode").focus();
        Feng.error("没有选择检测方式!"+this.get("checkmode"));
        flag = false;
    }
    if(this.get("sitelevel")==''){
        $("#sitelevel").focus();
        Feng.error("没有选择站点等级!"+this.get("sitelevel"));
        flag = false;
    }
    if(this.get("areacode").length<6){
        $("#areacode").focus();
        Feng.error("没有选择具体管理路段!");
        flag = false;
    }
    if(this.get("deptid").length<1){
        Feng.error("没有选择管理部门!");
        flag = false;
    }

    // if(this.get("certificateValidityPeriod").length==0){
    //     $("#certificateValidityPeriod").focus();
    //     Feng.error("没有填写检定证书有效期!");
    //
    //     return false;
    // }


    flag = $("#idPresiteForm").data('bootstrapValidator').isValid()? flag: false;
    return flag;
}

/**
 * 初始化表单验证
 */
function presiteInitValidate() {
    $('#idPresiteForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields : {
            sitename: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            longitude: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            latitude: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            longlimited: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            heightlimited: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            checkmode: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            certificateValidityPeriod: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            stakeNumber: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            precision: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            sitelevel: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            widthlimited: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            weightlimited: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            roadcode: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            deptid: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            }
        }
    });
}