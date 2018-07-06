/**
 * 初始化非现场执法数据管理详情对话框
 */
var BOseinfoInfoDlg = {
    bOseinfoInfoData : {}
};

/**
 * 清除数据
 */
BOseinfoInfoDlg.clearData = function() {
    this.bOseinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BOseinfoInfoDlg.set = function(key, val) {
    this.bOseinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BOseinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BOseinfoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/bOseinfo");
	if (winOpen!=undefined){
		top.layer.close(winOpen.BOseinfo.layerIndex);
	}
}

/**
 * 收集数据
 */
BOseinfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('oseid')
    .set('osetime')
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
    .set('videourl')
    .set('prostatus')
    .set('result')
    .set('message')
    .set('isshow');
}

BOseinfoInfoDlg.openVideo = function (videoUrl) {

    var content1 = ""
    if(videoUrl == "") {
        layer.msg("暂无视频")
    } else {
        content1 = '<video id="my-video" class="video-js" controls preload="auto" width="640" ' +
            'height="360" data-setup="{}">' +
            '    <source src="'+'http://192.168.10.136:8080/' + videoUrl+'" type="video/mp4">' +
            '</video>';

        layer.open({
            type: 1,
            title: false,
            shadeClose: true,
            closeBtn: 0,
            area: ['630px', '354px'], //宽高
            content: content1
        });
    }
};

$(function() {
//初始化图片
    Yang.Tools.banner('/bOseinfo/getVehicleImages',$("#id").val());
});
