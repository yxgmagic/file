/**
 * 初始化数据服务器日志上传管理详情对话框
 */
var DmsParaInfoDlg = {
    dmsParaInfoData : {}
};

/**
 * 清除数据
 */
DmsParaInfoDlg.clearData = function() {
    this.dmsParaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DmsParaInfoDlg.set = function(key, val) {
    this.dmsParaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DmsParaInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DmsParaInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/dmsPara");
	top.layer.close(winOpen.DmsPara.layerIndex);
}

/**
 * 收集数据
 */
DmsParaInfoDlg.collectData = function() {
    this
    .set('id')
    .set('paracode')
    .set('paraname')
    .set('paravalue')
    .set('ramark');
}

/**
 * 提交添加
 */
DmsParaInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dmsPara/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/dmsPara");
        winOpen.DmsPara.table.refresh();
        top.layer.close(winOpen.DmsPara.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dmsParaInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DmsParaInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dmsPara/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/dmsPara");
        winOpen.DmsPara.table.refresh();
        top.layer.close(winOpen.DmsPara.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dmsParaInfoData);
    ajax.start();
}

$(function() {

});
