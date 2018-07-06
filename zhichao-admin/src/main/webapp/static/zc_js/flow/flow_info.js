 /**
 * 初始化流程管理详情对话框
 */
var FlowInfoDlg = {
    flowInfoData : {}
};

/**
 * 清除数据
 */
FlowInfoDlg.clearData = function() {
    this.flowInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowInfoDlg.set = function(key, val) {
    this.flowInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FlowInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/flow");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Flow.layerIndex);
	}
}

/**
 * 收集数据
 */
FlowInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('flowJson')
    .set('createBy')
    .set('createDate')
    .set('updataBy')
    .set('updataDate')
    .set('remark')
    .set('state');
}

/**
 * 提交添加
 */
FlowInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flow/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/flow");
        if (winOpen!=undefined){
        	winOpen.Flow.table.refresh();
        	top.layer.close(winOpen.Flow.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FlowInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flow/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/flow");
        if (winOpen!=undefined){
        	winOpen.Flow.table.refresh();
        	top.layer.close(winOpen.Flow.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowInfoData);
    ajax.start();
}

$(function() {

});
