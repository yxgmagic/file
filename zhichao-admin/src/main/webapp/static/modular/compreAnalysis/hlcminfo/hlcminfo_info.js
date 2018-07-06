/**
 * 初始化检测记录详情对话框
 */
var HlcminfoInfoDlg = {
    hlcminfoInfoData : {}
};

/**
 * 清除数据
 */
HlcminfoInfoDlg.clearData = function() {
    this.hlcminfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HlcminfoInfoDlg.set = function(key, val) {
    this.hlcminfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HlcminfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HlcminfoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/hlcminfo");
	top.layer.close(winOpen.Hlcminfo.layerIndex);
}

/**
 * 收集数据
 */
HlcminfoInfoDlg.collectData = function() {
    this
    .set('fctime')
    .set('areacode')
    .set('stationid')
    .set('sitename')
    .set('sitetype')
    .set('checkmode')
    .set('vehicleid')
    .set('axlesum')
    .set('fctotalweight')
    .set('overlimited')
    .set('overrate')
    .set('isoverlimit');
}

/**
 * 提交添加
 */
HlcminfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hlcminfo/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/hlcminfo");
        winOpen.Hlcminfo.table.refresh();
        top.layer.close(winOpen.Hlcminfo.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hlcminfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HlcminfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hlcminfo/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/hlcminfo");
        winOpen.Hlcminfo.table.refresh();
        top.layer.close(winOpen.Hlcminfo.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hlcminfoInfoData);
    ajax.start();
}

$(function() {

});
