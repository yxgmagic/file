/**
 * 初始化货运从业人员管理详情对话框
 */
var VehicleDriverInfoDlg = {
    vehicleDriverInfoData : {}
};

/**
 * 清除数据
 */
VehicleDriverInfoDlg.clearData = function() {
    this.vehicleDriverInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VehicleDriverInfoDlg.set = function(key, val) {
    this.vehicleDriverInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 选择企业
 */
$("#corpcode").on("click", function(){
    var index = top.layer.open({
        type: 2,
        title: '请选择源头企业',
        area: ['900px', '400px'],
        maxmin: true,
        content: Feng.ctxPath + '/vehicleDriver/company'
    });
    this.layerIndex = index;
});

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VehicleDriverInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VehicleDriverInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/vehicleDriver");
    winOpen.VehicleDriver.table.refresh();
    top.layer.close(winOpen.VehicleDriver.layerIndex);
}

/**
 * 收集数据 //被ning魔改了一下
 */
VehicleDriverInfoDlg.collectData = function() {
    this
    .set('id')
    .set('driverid')
    .set('idcard')
    .set('drivername')
    .set('race')
    .set('sex')
    .set('qualificationid')
    .set('certtype')
    .set('address')
    .set('postcode')
    .set('grantorg')
    .set('certendtime')
    .set('corpcode');
}

/**
 * 提交添加
 */
VehicleDriverInfoDlg.addSubmit = function() {
    $(".classVehicleDriver").data("bootstrapValidator").validate()
    this.clearData();
    this.collectData();
    
    var isValid = $(".classVehicleDriver").data('bootstrapValidator').isValid();
    if (isValid) {
      //提交信息
      var ajax = new $ax(Feng.ctxPath + "/vehicleDriver/add", function(data){
          Feng.success("添加成功!");
          var winOpen=Feng.GetFrame("/vehicleDriver");
          winOpen.VehicleDriver.table.refresh();
          top.layer.close(winOpen.VehicleDriver.layerIndex);
      },function(data){
          Feng.error("添加失败!" + data.responseJSON.message + "!");
      });
      ajax.set(this.vehicleDriverInfoData);
      ajax.start();
    } else {
    	Feng.info("请填写完整!")
    }


}

/**
 * 提交修改
 */
VehicleDriverInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    $('.vehicleDriverEditor').data("bootstrapValidator").resetForm();
    $('.vehicleDriverEditor').bootstrapValidator('validate');
    var isValids = $(".vehicleDriverEditor").data('bootstrapValidator').isValid();
    
    if (isValids) {
    	//提交信息
        var ajax = new $ax(Feng.ctxPath + "/vehicleDriver/update", function(data){
            Feng.success("修改成功!");
            var winOpen=Feng.GetFrame("/vehicleDriver");
            winOpen.VehicleDriver.table.refresh();
            top.layer.close(winOpen.VehicleDriver.layerIndex);
        },function(data){
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.vehicleDriverInfoData);
        ajax.start();
    }
    
}

$(function() {
	/**
	 * 添加货运从业人员表单验证
	 */
	$('.classVehicleDriver').bootstrapValidator({
        feedbackIcons: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            corpcode: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    }
                }
            },
            drivername: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    }
                }
            },
            idcard: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    },
                    regexp: {
                        regexp: /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                        message: '身份证格式错误!'
                    },
                    callback: {
                        message: '身份证已存在',
                        callback: function(value, validator){
                            var id = $("#id").val();
                            var idcard = $("#idcard").val().replace(/(^\s*)|(\s*$)/g, '');
                            // 校验身份证
                            var res = true;
                            if (idcard != null && idcard != '') {
                                $.ajax({
                                    url: Feng.ctxPath + '/vehicleDriver/dataIsExist',
                                    type: 'POST',
                                    dataType: 'json',
                                    async: false,
                                    data: {
                                        "id": id,
                                        "idcard": idcard
                                    },
                                    success: function(data){
                                        console.log(data);
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
            driverid: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    },
                    callback: {
                        message: '驾驶证号已存在',
                        callback: function(value, validator){
                            var id = $("#id").val();
                            var driverid = $("#driverid").val().replace(/(^\s*)|(\s*$)/g, '');
                            // 驾驶证号
                            var res = true;
                            if (driverid != null && driverid != '') {
                                $.ajax({
                                    url: Feng.ctxPath + '/vehicleDriver/dataIsExist',
                                    type: 'POST',
                                    dataType: 'json',
                                    async: false,
                                    data: {
                                        "id": id,
                                        "driverid": driverid
                                    },
                                    success: function(data){
                                        console.log(data);
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
            qualificationid: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    },
                    callback: {
                        message: '从业资格证已存在',
                        callback: function(value, validator){
                            var id = $("#id").val();
                            var qualificationid = $("#qualificationid").val().replace(/(^\s*)|(\s*$)/g, '');
                            // 驾驶证号
                            var res = true;
                            if (qualificationid != null && qualificationid != '') {
                                $.ajax({
                                    url: Feng.ctxPath + '/vehicleDriver/dataIsExist',
                                    type: 'POST',
                                    dataType: 'json',
                                    async: false,
                                    data: {
                                        "id": id,
                                        "qualificationid": qualificationid
                                    },
                                    success: function(data){
                                        console.log(data);
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
        }
    })

    /**
     * 修改货运从业人员表单验证
     */
    $('.vehicleDriverEditor').bootstrapValidator({
        feedbackIcons: {
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            corpcode: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    }
                }
            },
            drivername: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    }
                }
            },
            idcard: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    },
                    regexp: {
                        regexp: /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                        message: '身份证格式错误!'
                    },
                    callback: {
                        message: '身份证已存在',
                        callback: function(value, validator){
                            var id = $("#id").val();
                            var idcard = $("#idcard").val().replace(/(^\s*)|(\s*$)/g, '');
                            // 校验身份证
                            var res = true;
                            if (idcard != null && idcard != '') {
                                $.ajax({
                                    url: Feng.ctxPath + '/vehicleDriver/dataIsExist',
                                    type: 'POST',
                                    dataType: 'json',
                                    async: false,
                                    data: {
                                        "id": id,
                                        "idcard": idcard
                                    },
                                    success: function(data){
                                        console.log(data);
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
            driverid: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    },
                    callback: {
                        message: '驾驶证号已存在',
                        callback: function(value, validator){
                            var id = $("#id").val();
                            var driverid = $("#driverid").val().replace(/(^\s*)|(\s*$)/g, '');
                            // 驾驶证号
                            var res = true;
                            if (driverid != null && driverid != '') {
                                $.ajax({
                                    url: Feng.ctxPath + '/vehicleDriver/dataIsExist',
                                    type: 'POST',
                                    dataType: 'json',
                                    async: false,
                                    data: {
                                        "id": id,
                                        "driverid": driverid
                                    },
                                    success: function(data){
                                        console.log(data);
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
            qualificationid: {
                validators: {
                    notEmpty: {
                        message: '该项为必填项!'
                    },
                    callback: {
                        message: '从业资格证已存在',
                        callback: function(value, validator){
                            var id = $("#id").val();
                            var qualificationid = $("#qualificationid").val().replace(/(^\s*)|(\s*$)/g, '');
                            // 驾驶证号
                            var res = true;
                            if (qualificationid != null && qualificationid != '') {
                                $.ajax({
                                    url: Feng.ctxPath + '/vehicleDriver/dataIsExist',
                                    type: 'POST',
                                    dataType: 'json',
                                    async: false,
                                    data: {
                                        "id": id,
                                        "qualificationid": qualificationid
                                    },
                                    success: function(data){
                                        console.log(data);
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
			}
		}
	})
	
});
