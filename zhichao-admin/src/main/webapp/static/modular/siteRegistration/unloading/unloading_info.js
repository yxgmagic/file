/**
 * 初始化卸货场管理详情对话框
 */
var UnloadingInfoDlg = {
    unloadingInfoData : {}
};

/**
 * 清除数据
 */
UnloadingInfoDlg.clearData = function() {
    this.unloadingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UnloadingInfoDlg.set = function(key, val) {
    this.unloadingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UnloadingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UnloadingInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/unloading");
    winOpen.Unloading.table.refresh();
    top.layer.close(winOpen.Unloading.layerIndex);
}

/**
 * 收集数据 //被ning魔改了一下
 */
UnloadingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ulcode')
    .set('ulname')
    .set('address')
    .set('sitecode')
    .set('manager')
    .set('managertel');
}

/**
 * 提交添加
 */
UnloadingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    var isValid = $(".classUnloading").data('bootstrapValidator').isValid();
    
    if (isValid) {
    	//提交信息
        var ajax = new $ax(Feng.ctxPath + "/unloading/add", function(data){
            Feng.success("添加成功!");
            var winOpen=Feng.GetFrame("/unloading");
            winOpen.Unloading.table.refresh();
            top.layer.close(winOpen.Unloading.layerIndex);
        },function(data){
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.unloadingInfoData);
        ajax.start();
    } else {
    	Feng.info("请填写完整!");
    }

    
}

/**
 * 提交修改
 */
UnloadingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    $('.classUnloadingEditor').data("bootstrapValidator").resetForm();
    $('.classUnloadingEditor').bootstrapValidator('validate');
    
    var isValids = $(".classUnloadingEditor").data('bootstrapValidator').isValid();
    console.log(isValids);
    if (isValids) {
    	//提交信息
        var ajax = new $ax(Feng.ctxPath + "/unloading/update", function(data){
            Feng.success("修改成功!");
            var winOpen=Feng.GetFrame("/unloading");
            winOpen.Unloading.table.refresh();
            top.layer.close(winOpen.Unloading.layerIndex);
        },function(data){
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.unloadingInfoData);
        ajax.start();
    }
    
    
}

$(function() {
	/**
	 * 添加卸货场信息表单验证
	 */
	$('.classUnloading').bootstrapValidator({
		feedbackIcons: {
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			ulname: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					}
				}
			},
			address: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					}
				}
			},
			manager: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					}
				}
			},
			managertel: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					},
					regexp: {
						regexp: /^1[34578]\d{9}$/,
						message: '手机号码格式错误!'
					}
				}
			}
		}
	})
	
	
	/**
	 * 编辑卸货场信息表单验证
	 */
	$('.classUnloadingEditor').bootstrapValidator({
		feedbackIcons: {
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			ulname: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					}
				}
			},
			address: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					}
				}
			},
			manager: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					}
				}
			},
			managertel: {
				validators: {
					notEmpty: {
						message: '该项为必填项!'
					},
					regexp: {
						regexp: /^1[34578]\d{9}$/,
						message: '手机号码格式错误!'
					}
				}
			}
		}
	})
	
	
});
