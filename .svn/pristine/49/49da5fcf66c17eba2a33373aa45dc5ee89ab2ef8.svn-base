/**
 * 初始化执法车信息详情对话框
 */
var LawEnforcecarInfoDlg = {
    lawEnforcecarInfoData : {}
	,zTreeDept : null
	,zTreeArea : null
	,zTreeCar:null
	,validateFields: {
		vehicleid: {
            validators: {
                notEmpty: {
                    message: '执法车辆牌号不能为空'
                }
            }
        },
        vehicleno: {
            validators: {
                notEmpty: {
                    message: '执法车编号不能为空'
                }
            }
        },
        carTypeName: {
            validators: {
                notEmpty: {
                    message: '车型不能为空'
                }
            }
        },
        manager: {
            validators: {
                notEmpty: {
                    message: '联系人不能为空'
                }
            }
        },
        managertel: {
            validators: {
                notEmpty: {
                    message: '联系方式不能为空'
                },
                regexp: {
                    regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
                    message: '请输入正确的手机号码'
                }
            }
        },
        deptName: {
            validators: {
                notEmpty: {
                    message: '管理部门不能为空'
                }
            }
        },
        areaName: {
            validators: {
                notEmpty: {
                    message: '所属区域不能为空'
                }
            }
        },
        certificate: {
            trigger:"change",
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        judicialOrgan: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        bankAccountName: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        bankAccount: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        dueBank: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        bankAddress: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
LawEnforcecarInfoDlg.clearData = function() {
    this.lawEnforcecarInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LawEnforcecarInfoDlg.set = function(key, val) {
    this.lawEnforcecarInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LawEnforcecarInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LawEnforcecarInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/lawEnforcecar");
	if (winOpen!=undefined){
		top.layer.close(winOpen.LawEnforcecar.layerIndex);
	}
}

/**
 * 收集数据
 */
LawEnforcecarInfoDlg.collectData = function() {
    this
    .set('id')
    .set('img_id')
	.set('imagetype')
    .set('vehicleid')
    .set('vehicleno')
    .set('vehicletype')
    .set('manager')
    .set('managertel')
    .set('deptid')
    .set('areacode')
    .set('gpsdeviceid')
        .set('certificate')
        .set('certificateValidityPeriod')
        .set('judicialOrgan')
        .set('bankAccountName')
        .set('bankAccount')
        .set('dueBank')
        .set('bankAddress');
}

/**
 * 提交添加
 */
LawEnforcecarInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    if(this.get("certificate").length==0) {
        $("#certificateA").text("请上传检定证书!!!");
        $("#certificateA").css("color","red");
        return false;
    }
    if(this.get("certificateValidityPeriod").length==0){
        $("#certificateValidityPeriod").focus();
        Feng.error("没有填写检定证书有效期!");
        return false;
    }

    //利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.lawEnforcecarInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/lawEnforcecar/add?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            Feng.success("添加成功!");
            var winOpen=Feng.GetFrame("/lawEnforcecar");
            winOpen.LawEnforcecar.table.refresh();
            top.layer.close(winOpen.LawEnforcecar.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("添加失败!");
        }
    });
    
    //提交信息
    // var ajax = new $ax(Feng.ctxPath + "/lawEnforcecar/add", function(data){
    // 	var result = $.parseJSON(data);
    //     if(result.flag){
    //     	Feng.success(result.msg);
	 //        var winOpen=Feng.GetFrame("/lawEnforcecar");
	 //        if (winOpen!=undefined){
	 //        	winOpen.LawEnforcecar.table.refresh();
	 //        	top.layer.close(winOpen.LawEnforcecar.layerIndex);
	 //        }
    //     }else{
    //     	Feng.error(result.msg);
    //     }
    // },function(data){
    //     Feng.error("添加失败!" + data.responseJSON.message + "!");
    // });
    // ajax.set(this.lawEnforcecarInfoData);
    // ajax.start();
}

/**
 * 提交修改
 */
LawEnforcecarInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    if(this.get("certificate").length==0) {
        $("#certificateA").text("请上传检定证书!!!");
        $("#certificateA").css("color","red");
        return false;
    }

    if(this.get("certificateValidityPeriod").length==0){
        $("#certificateValidityPeriod").focus();
        Feng.error("没有填写检定证书有效期!");
        return false;
    }

    //利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.lawEnforcecarInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/lawEnforcecar/update?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            Feng.success("修改成功!");
            var winOpen=Feng.GetFrame("/lawEnforcecar");
            winOpen.LawEnforcecar.table.refresh();
            top.layer.close(winOpen.LawEnforcecar.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("修改失败!");
        }
    });
    
    // //提交信息
    // var ajax = new $ax(Feng.ctxPath + "/lawEnforcecar/update", function(data){
    //     Feng.success("修改成功!");
    //
    //     var winOpen=Feng.GetFrame("/lawEnforcecar");
    //     if (winOpen!=undefined){
    //     	winOpen.LawEnforcecar.table.refresh();
    //     	top.layer.close(winOpen.LawEnforcecar.layerIndex);
    //     }
    // },function(data){
    //     Feng.error("修改失败!" + data.responseJSON.message + "!");
    // });
    // ajax.set(this.lawEnforcecarInfoData);
    // ajax.start();
}

/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
LawEnforcecarInfoDlg.onClickDept = function(e, treeId, treeNode) {
    $("#deptName").attr("value", LawEnforcecarInfoDlg.zTreeDept.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
    $("#parentDeptMenu").toggle();
}
LawEnforcecarInfoDlg.onClickArea = function(e, treeId, treeNode) {
    $("#areaName").attr("value", LawEnforcecarInfoDlg.zTreeArea.getSelectedVal());
    $("#areacode").attr("value", treeNode.areaCode);
    $("#parentAreaMenu").toggle();
}
LawEnforcecarInfoDlg.onClickCar = function(e, treeId, treeNode) {
    $("#carTypeName").attr("value", LawEnforcecarInfoDlg.zTreeCar.getSelectedVal());
    $("#vehicletype").attr("value", treeNode.id);
    $("#parentCarMenu").toggle();
}
/**
 * 显示部门选择的树
 *
 * @returns
 */
LawEnforcecarInfoDlg.showDeptSelectTree = function() {
	Feng.showInputTree("deptName", "parentDeptMenu");
}

/**
 * 显示区域选择的树
 */
LawEnforcecarInfoDlg.showAreaSelectTree = function() {
	Feng.showInputTree("areaName", "parentAreaMenu");
}
/**
 * 显示车型选择的树
 */
LawEnforcecarInfoDlg.showCarTypeSelectTree = function() {
	Feng.showInputTree("carTypeName", "parentCarMenu");
}
/**
 * 验证数据是否为空
 */
LawEnforcecarInfoDlg.validate = function () {
    $('#lawEnforcecarFrom').data("bootstrapValidator").resetForm();
    $('#lawEnforcecarFrom').bootstrapValidator('validate');
    return $("#lawEnforcecarFrom").data('bootstrapValidator').isValid();
}

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

$(function() {

    //设置证书有效期限时间框
    Yang.Tools.date_interval('certificateValidityPeriod','date','~','1970-01-01 00:00:00','2099-12-12 23:59:59');

    Feng.getImage($("#id").val(), $("#imagetype").val());
	//监听上传按钮的改变事件
	$("#img_hidden").change(function() {
		//隐藏图片(input=file)id,显示图片id,上传图片后图片id
		Feng.ajaxFileUploadEntityImage();
	});
    //部门
    var ztreeDept = new $ZTree("parentDeptMenuTree", "/dept/tree");
    ztreeDept.bindOnClick(LawEnforcecarInfoDlg.onClickDept);
    ztreeDept.init();
    LawEnforcecarInfoDlg.zTreeDept = ztreeDept;
    //区域
    var ztreeArea = new $ZTree("parentAreaMenuTree", "/area/tree");
    ztreeArea.bindOnClick(LawEnforcecarInfoDlg.onClickArea);
    ztreeArea.init();
    LawEnforcecarInfoDlg.zTreeArea = ztreeArea;
    //车型
    var ztreeCar = new $ZTree("parentCarMenuTree", "/cartype/tree");
    ztreeCar.bindOnClick(LawEnforcecarInfoDlg.onClickCar);
    ztreeCar.init();
    LawEnforcecarInfoDlg.zTreeCar = ztreeCar;
    
    Feng.initValidator("lawEnforcecarFrom", LawEnforcecarInfoDlg.validateFields);
});
