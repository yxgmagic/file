/**
 * 初始化指标管理详情对话框
 */
var IndicatorInfoDlg = {
    indicatorInfoData : {}
};

/**
 * 清除数据
 */
IndicatorInfoDlg.clearData = function() {
    this.indicatorInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IndicatorInfoDlg.set = function(key, val) {
    this.indicatorInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IndicatorInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
IndicatorInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/indicator");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Indicator.layerIndex);
	}
}

/**
 * 收集数据
 */
IndicatorInfoDlg.collectData = function() {
    this
    .set('id')
    .set('indicatorName')
    .set('indicatorCategory')
    .set('indicatorDescription')
    .set('judgeRules')
    .set('score')
    .set('scoreMethod')
    .set('notes');
}

/**
 * 提交添加
 */
IndicatorInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    $('#indic').bootstrapValidator('validate');
    var bootstrapValidator = $("#indic").data('bootstrapValidator');

    //验证信息正确性
    if(bootstrapValidator.isValid()) {

        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/indicator/add", function (data) {
            Feng.success("添加成功!");

            var winOpen = Feng.GetFrame("/indicator");
            if (winOpen != undefined) {
                winOpen.Indicator.table.refresh();
                top.layer.close(winOpen.Indicator.layerIndex);
            }
        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.indicatorInfoData);
        ajax.start();
    }
}

/**
 * 提交修改
 */
IndicatorInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    $('#indic').bootstrapValidator('validate');
    var bootstrapValidator = $("#indic").data('bootstrapValidator');

    //验证信息正确性
    if(bootstrapValidator.isValid()) {
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/indicator/update", function(data){
            Feng.success("修改成功!");

            var winOpen=Feng.GetFrame("/indicator");
            if (winOpen!==undefined){
                winOpen.Indicator.table.refresh();
                top.layer.close(winOpen.Indicator.layerIndex);
            }
        },function(data){
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.indicatorInfoData);
        ajax.start();
    }
}

$(function() {

    //表单验证
    $('#indic').bootstrapValidator({
        message: '这个值没有被验证',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            indicatorName: {
                validators: {
                    notEmpty: {
                        message: '指标名称为必填项!'
                    },
                    callback: {
                        message: '非法字符(逗号)',
                        callback: function(value, validator) {
                            return !((value.indexOf(',') !== -1) || (value.indexOf('，') !== -1))
                        }
                    }
                }
            },
            judgeRules: {
                validators: {
                    notEmpty: {
                        message: '评分规则为必填项!'
                    }
                }
            },
            indicatorCategory: {
                validators: {
                    notEmpty: {
                        message: '指标类别为必填项!'
                    }
                }
            },
            indicatorDescription: {
                validators: {
                    notEmpty: {
                        message: '指标要素为必填项'
                    }
                }
            },
            score: {
                validators: {
                    notEmpty: {
                        message: '必须填写分值!'
                    },
                    regexp: {
                        regexp: /^([1-9]{1}|[1-9]\d{1}|100)$/,
                        message: '分值区间(1-100)'
                    }
                }
            }
        }
    });

});
