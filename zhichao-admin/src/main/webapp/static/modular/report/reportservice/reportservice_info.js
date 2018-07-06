/**
 * 初始化reportservice详情对话框
 */
var ReportserviceInfoDlg = {
    reportserviceInfoData : {}
	,validateFields: {
		reportName: {
			validators: {
				notEmpty: {
					message: '对象名称不能为空'
				}
			}
		},reportAddress: {
			validators: {
				notEmpty: {
					message: '接口地址不能为空'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
ReportserviceInfoDlg.clearData = function() {
    this.reportserviceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportserviceInfoDlg.set = function(key, val) {
    this.reportserviceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ReportserviceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ReportserviceInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/reportservice");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Reportservice.layerIndex);
	}
}

/**
 * 收集数据
 */
ReportserviceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('reportName')
    .set('reportAddress')
    .set('reportPort')
    .set('remarks');
}

/**
 * 提交添加
 */
ReportserviceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportservice/add", function(data){
        var result = $.parseJSON(data);
        if(result.flag){
        	Feng.success(result.msg);
        	var winOpen=Feng.GetFrame("/reportservice");
        	if (winOpen!=undefined){
        		winOpen.Reportservice.table.refresh();
        		top.layer.close(winOpen.Reportservice.layerIndex);
        	}
        }else{
        	Feng.error(result.msg);
        }
        
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportserviceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ReportserviceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/reportservice/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/reportservice");
        if (winOpen!=undefined){
        	winOpen.Reportservice.table.refresh();
        	top.layer.close(winOpen.Reportservice.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.reportserviceInfoData);
    ajax.start();
}

/**
 * 验证数据是否为空
 */
ReportserviceInfoDlg.validate = function () {
	 $('#reportserviceForm').data("bootstrapValidator").resetForm();
	 $('#reportserviceForm').bootstrapValidator('validate');
	 return $("#reportserviceForm").data('bootstrapValidator').isValid();
}

$(function() {
	Feng.initValidator("reportserviceForm", ReportserviceInfoDlg.validateFields);
	
});
