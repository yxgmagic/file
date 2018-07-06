/**
 * 初始化源头企业检测数据管理详情对话框
 */
var CorpinfoInfoDlg = {
    corpinfoInfoData : {}
};

/**
 * 清除数据
 */
CorpinfoInfoDlg.clearData = function() {
    this.corpinfoInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CorpinfoInfoDlg.set = function(key, val) {
    this.corpinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CorpinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
CorpinfoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/corpinfo");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Corpinfo.layerIndex);
	}
};

/**
 * 收集数据
 */
CorpinfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('corpid')
    .set('corptime')
    .set('stationid')
    .set('vehicleid')
    .set('vehicletype')
    .set('axlesum')
    .set('speed')
    .set('laneno')
    .set('totalweight')
    .set('overlimited')
    .set('axle1')
    .set('axle2')
    .set('axle3')
    .set('axle4')
    .set('axle5')
    .set('axle6')
    .set('axle7')
    .set('axle8')
    .set('vehicleimage')
    .set('vehicleimage1')
    .set('vehicleimage2')
    .set('prostatus');
};

$(function(){

	//初始化图片
	Yang.Tools.banner('/corpinfo/getVehicleImages',$("#id").val())
});
