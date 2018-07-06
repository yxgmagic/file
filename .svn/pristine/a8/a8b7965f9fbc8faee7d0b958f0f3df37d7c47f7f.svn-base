
/**
 * 初始化源头企业管理详情对话框
 */
var BsCorpInfoDlg = {
		bsCorpInfoData : {}
};

/**
 * 清除数据
 */
BsCorpInfoDlg.clearData = function() {
	this.bsCorpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BsCorpInfoDlg.set = function(key, val) {
	this.bsCorpInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
	return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BsCorpInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BsCorpInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/bsCorp");
	top.layer.close(winOpen.BsCorp.layerIndex);
}

/**
 * 收集数据
 */
BsCorpInfoDlg.collectData = function() {
    this
        .set('id')
        .set('img_id')
        .set('imagetype')
        .set('corpcode')
        .set('corpname')
        .set('areacode')
        .set('roadcode')
        .set('manager')
        .set('managerid')
        .set('managertel')
        .set('address')
        .set('longitude')
        .set('latitude')
        .set('turnvolumes')
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
BsCorpInfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();

    $('#bsCorpForm').bootstrapValidator('validate');
    var bootstrapValidator = $("#bsCorpForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    //验证信息正确性
    if(!bootstrapValidator.isValid()) {
        return false;
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
    var infoData = this.bsCorpInfoData;
    for (var i in infoData) {
        formData.append(i, infoData[i]);
    }

    $.ajax({
        type : "POST",
        url : Feng.ctxPath + "/bsCorp/add?jstime=" + new Date().getTime(),
        data : formData,
        async : false,
        cache : false,
        contentType : false,
        processData : false,
        success : function(data) {
            Feng.success("添加成功!");
            var winOpen=Feng.GetFrame("/bsCorp");
            winOpen.BsCorp.table.refresh();
            top.layer.close(winOpen.BsCorp.layerIndex);
        },
        error : function(XMLHttpRequest, textStatus, errorThrown, data) {
            Feng.error("添加失败!");
        }
    });
}

/**
 * 提交修改
 */
BsCorpInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	$('#bsCorpForm').bootstrapValidator('validate');
	var bootstrapValidator = $("#bsCorpForm").data('bootstrapValidator');
	bootstrapValidator.validate();

	//验证信息正确性
	if(bootstrapValidator.isValid()){
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
        var infoData = this.bsCorpInfoData;
        for (var i in infoData) {
            formData.append(i, infoData[i]);
        }

        $.ajax({
            type : "POST",
            url : Feng.ctxPath + "/bsCorp/update?jstime=" + new Date().getTime(),
            data : formData,
            async : false,
            cache : false,
            contentType : false,
            processData : false,
            success : function(data) {
                Feng.success("修改成功!");
                var winOpen=Feng.GetFrame("/bsCorp");
                winOpen.BsCorp.table.refresh();
                top.layer.close(winOpen.BsCorp.layerIndex);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown, data) {
                Feng.error("修改失败!");
            }
        });
	}else{
		return false;
	}
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
		Feng.ajaxFileUploadEntityImage();
		
	});
	
	$('#bsCorpForm').bootstrapValidator({
		message: '这个值没有被验证',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			corpname: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					}
				}
			},
			managerid: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					},
					regexp: {
						regexp: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/,
						message: '身份证不合法'
					},
					callback: {
						message: '企业负责人身份证号已存在',
						callback: function (value, validator) {
							var id = $("#id").val();
							var managerid = $("#managerid").val().replace(/(^\s*)|(\s*$)/g, '');
							//填写身份证校验算法
							var res = true;
							if (managerid != null && managerid != '') {
								$.ajax({
									url: Feng.ctxPath + '/bsCorp/corpIsExist',
									type: 'post',
									dataType: 'json',
									async: false,
									data: { "id": id, "managerid": managerid },
									success: function (data) {
										console.log(data)
										if (data != '0') {
											res = false;
										}
									}
								});
							}
							return res;
						}
					}
				}
			},
			vans: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					},
					regexp: {
						regexp: /(^[1-9]\d*$)/,
						message: '货运车辆必须为整数'
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
			turnvolumes: {
				validators: {
					regexp: {
						regexp: /(^[1-9]\d*$)|(^\d+\.\d+$)/,
						message: '年货运吞吐量格式不正确'
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
			address: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					}
				}
			},
			corpcode: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					},
					callback: {
						message: '营业执照已存在',
						callback: function (value, validator) {
							var id = $("#id").val();
							var corpcode = $("#corpcode").val().replace(/(^\s*)|(\s*$)/g, '');
							//填写营业执照校验算法
							var res = true;
							if (corpcode != null && corpcode != '') {
								$.ajax({
									url: Feng.ctxPath + '/bsCorp/corpIsExist',
									type: 'post',
									dataType: 'json',
									async: false,
									data: { "id": id, "corpcode": corpcode },
									success: function (data) {
										console.log(data)
										if (data != '0') {
											res = false;
										}
									}
								});
							}
							return res;
						}
					}
				}
			},
			Select1: {
				validators: {
					notEmpty: {
						message: '请选择市'
					}
				}
			},
			Select2: {
				validators: {
					notEmpty: {
						message: '请选择区县'
					}
				}
			},
			Select3: {
				validators: {
					notEmpty: {
						message: '请选择路网'
					}
				}
			}
		}
	});

});

