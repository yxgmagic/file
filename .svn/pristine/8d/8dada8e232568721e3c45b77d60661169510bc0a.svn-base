/**
 * 初始化非现场执法检测站详情对话框
 */
var BsOsesiteInfoDlg = {
    bsOsesiteInfoData : {}, zTreeDept : null
};

/**
 * 清除数据
 */
BsOsesiteInfoDlg.clearData = function() {
    this.bsOsesiteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BsOsesiteInfoDlg.set = function(key, val) {
    this.bsOsesiteInfoData[key] = (typeof val === "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BsOsesiteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BsOsesiteInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/bsOsesite");
	if (winOpen!==undefined){
		top.layer.close(winOpen.BsOsesite.layerIndex);
	}
}

/**
 * 收集数据
 */
BsOsesiteInfoDlg.collectData = function() {
    this
        .set('id')
        .set('img_id')
        .set('imagetype')
        .set('sitename')
        .set('longitude')
        .set('latitude')
        .set('buildTimeString')
        .set('manager')
        .set('deptid')
        .set('sitecode')
        .set('stakeNumber')
        .set('managertel')
        .set('areacode')
        .set('roadcode')
        .set('checkmode')
        .set('precision')
        .set('certificate')
        .set('sitelevel')
        .set('certificateValidityPeriod');
};

BsOsesiteInfoDlg.refresh = function () {
    var winOpen=Feng.GetFrame("/bsOsesite");
    winOpen.BsOsesite.table.refresh();
    top.layer.close(winOpen.BsOsesite.layerIndex);
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

BsOsesiteInfoDlg.validate = function () {
    this.clearData();
    this.collectData();
    if(this.get("certificate").length==0) {
        $("#certificateA").text("请上传检定证书!!!");
        $("#certificateA").css("color","red");
        return false;
    }
    if(!Yang.Tools.bsValidator('oseform')){
        return false;
    }

    return true;
}

/**
 * 提交添加
 */
BsOsesiteInfoDlg.addSubmit = function() {

    if(!BsOsesiteInfoDlg.validate()){
        return;
    }

    //利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.bsOsesiteInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/bsOsesite/add?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            Feng.success("添加成功!");
            BsOsesiteInfoDlg.refresh();
        },
        error : function() {
            Feng.error("添加失败!");
        }
    });
};

/**
 * 提交修改
 */
BsOsesiteInfoDlg.editSubmit = function() {

    if(!BsOsesiteInfoDlg.validate()){
        return;
    }

    //利用FormData进行上传信息
    var formData = new FormData();

    //将文件放到FormData对象中
    formData.append("file", certificateFile);

    //将前面获取到的值放进去
    var infoData = this.bsOsesiteInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/bsOsesite/update?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            BsOsesiteInfoDlg.refresh();
            Feng.success("修改成功!");
        },
        error : function() {
            Feng.error("修改失败!");
        }
    });
};

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

BsOsesiteInfoDlg.showDeptSelectTree = function() {
    Feng.showInputTree("deptName", "parentDeptMenu");
};
BsOsesiteInfoDlg.onClickDept = function(e, treeId, treeNode) {
    $("#deptName").attr("value", BsOsesiteInfoDlg.zTreeDept.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

//监听上传按钮的改变事件
$("#img_hidden").change(function() {
    Feng.ajaxFileUploadEntityImage();
});

$(function() {

    //部门
    var ztreeDept = new $ZTree("parentDeptMenuTree", "/dept/tree");
    ztreeDept.bindOnClick(BsOsesiteInfoDlg.onClickDept);
    ztreeDept.init();
    BsOsesiteInfoDlg.zTreeDept = ztreeDept;

    //设置证书有效期限时间框
    Yang.Tools.date_interval('certificateValidityPeriod','date','~','1970-01-01 00:00:00','2099-12-12 23:59:59');
    Yang.Tools.date('buildTimeString','date');

    Feng.getImage($('#id').val(),'ose');
});

$('#oseform').bootstrapValidator({
    message: '这个值没有被验证',
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
        sitename: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        longitude: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                },
                regexp: {
                    regexp: /(^[1-9]\d*$)|(^\d+\.\d+$)/,
                    message: '经度信息格式不正确'
                }
            }
        },
        latitude: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                },
                regexp: {
                    regexp: /(^[1-9]\d*$)|(^\d+\.\d+$)/,
                    message: '纬度信息格式不正确'
                }
            }
        },
        buildTimeString: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        manager: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        deptid: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        sitelevel: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        stakeNumber: {
            validators: {
                notEmpty: {
                    message: '桩号为必填项'
                },
                regexp: {
                    regexp: /(^[1-9]\d*$)|(^\d+\.\d+$)/,
                    message: '桩号格式不正确'
                }
            }
        },
        managertel: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                },
                regexp: {
                    regexp: /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/,
                    message: '手机号码格式不正确'
                }
            }
        },
        roadcode: {
            trigger:"change",
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        checkmode: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        precision: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        certificate: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        },
        certificateValidityPeriod: {
            validators: {
                notEmpty: {
                    message: '该项为必填项'
                }
            }
        }
    }
});
