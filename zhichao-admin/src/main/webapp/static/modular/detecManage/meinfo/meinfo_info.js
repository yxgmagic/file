/**
 * 初始化流动执法车数据管理详情对话框
 */
var MeinfoInfoDlg = {
    meinfoInfoData : {}
};

/**
 * 清除数据
 */
MeinfoInfoDlg.clearData = function() {
    this.meinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MeinfoInfoDlg.set = function(key, val) {
    this.meinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MeinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MeinfoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/meinfo");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Meinfo.layerIndex);
	}
}

/**
 * 收集数据
 */
MeinfoInfoDlg.collectData = function() {
    this
        .set('id')
        .set('checkno')
        .set('drivername')
        .set('vehicleid')
        .set('axlesum')
        .set('weightlimited')
        .set('ratedtotalweight')
        .set('ratedloading')
        .set('overlimited')
        .set('fctime')
        .set('overload')
        .set('fclaneno')
        .set('fctotalweight')
        .set('fcoper')
        .set('fcaxle1')
        .set('fcaxle2')
        .set('fcaxle3')
        .set('fcaxle4')
        .set('fcaxle5')
        .set('fcaxle6')
        .set('fcaxle7')
        .set('fcaxle8')
        .set('fclength')
        .set('fcwidth')
        .set('fcheight')
        .set('overlength')
        .set('overwidth')
        .set('overheight')
        .set('offload')
        .set('vehicleno')
        .set('prostatus')
        .set('result')
        .set('message')
        .set('rctotalweight');
}

/**
 * 提交添加
 */
MeinfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    $('#meinfoForm').bootstrapValidator('validate');
	var bootstrapValidator = $("#meinfoForm").data('bootstrapValidator');
	bootstrapValidator.validate();

	//验证信息正确性
	if(bootstrapValidator.isValid()){
    
	   //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/meinfo/add", function(data){
	        Feng.success("添加成功!");
	        var winOpen=Feng.GetFrame("/meinfo");
	        if (winOpen!=undefined){
	        	winOpen.Meinfo.table.refresh();
	        	top.layer.close(winOpen.Meinfo.layerIndex);
	        }
	    },function(data){
	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.meinfoInfoData);
	    ajax.start();
	}else{
		return false;
	}
}

/**
 * 提交修改
 */
MeinfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    $("#meinfoForm").bootstrapValidator('validate');
	var bootstrapValidator = $("#meinfoForm").data('bootstrapValidator');
	bootstrapValidator.validate();

	//验证信息正确性
	if(bootstrapValidator.isValid()){

	    //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/meinfo/update", function(data){
	        Feng.success("修改成功!");
	        
	        var winOpen=Feng.GetFrame("/meinfo");
	        if (winOpen!=undefined){
	        	winOpen.Meinfo.table.refresh();
	        	top.layer.close(winOpen.Meinfo.layerIndex);
	        }
	    },function(data){
	        Feng.error("修改失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.meinfoInfoData);
	    ajax.start();
	}else{
		return false;
	}
}

/**
 * 点击选择流动执法车
 */
$("#vehicleno_name").click(function(){
	var index = layer.open({
        type: 2,
        title: '请选择执法车',
        area: ['900px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/meinfo/openLawEnforcecar'
    });
    this.layerIndex = index;
});

//点击打印磅单时弹出预览页面
MeinfoInfoDlg.preview = function () {
	window.open(Feng.ctxPath + '/meinfo/meinfo_preview/' + $("#id").val());
}

$(function() {

    //表单验证
	$('#meinfoForm').bootstrapValidator({
		message: '这个值没有被验证',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
            vehicleid: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,
                        message: '车牌号格式不合法'
                    }
                }
            },
            vehicleno_name: {
                trigger:"change",
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    }
                }
            },
            drivername: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    }
                }
            },
            axlesum: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '轴数格式不合法'
                    }
                }
            },
            ratedloading: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            ratedtotalweight: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            fctotalweight: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            overlimited: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            overload: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            weightlimited: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            fctime: {
                trigger:"change",
                validators: {
                    notEmpty: {
                        message: '该项为必填项'
                    }
                }
            },
            fcwidth: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            overwidth: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            overheight: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            fcheight: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            overlength: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            },
            fclength: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '格式不正确'
                    }
                }
            }
        }
	});
	
});
