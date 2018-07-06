/**
 * 初始化路网信息详情对话框
 */
var RoadInfoDlg = {
    roadInfoData : {},
    validateFields: {
        areacode: {
            validators: {
                notEmpty: {
                    message: '所属区域不能为空'
                }
            }
        },
 
 
    }
};

/**
 * 清除数据
 */
RoadInfoDlg.clearData = function() {
    this.roadInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoadInfoDlg.set = function(key, val) {
    this.roadInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}
RoadInfoDlg.validate = function () {
	 
    $('#roadInfoForm').data("bootstrapValidator").resetForm();
    $('#roadInfoForm').bootstrapValidator('validate');
    return $("#roadInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoadInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RoadInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/road");
	top.layer.close(winOpen.Road.layerIndex);
}

/**
 * 收集数据
 */
RoadInfoDlg.collectData = function() {
    this
    .set('id')
    .set('roadcode')
    .set('roadname')
    .set('address')
    .set('areacode');
}

/**
 * 提交添加
 */
RoadInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(this.get("roadname").length==0){
    	$("#roadname").focus();
    	 Feng.error("没有填写名称!");
    	return false;
    }
 
    var ac=this.get("areacode");
    if(ac.length<6){
    	$("#areacode").focus();
    	 Feng.error("没有选择具体所属行政区域!");
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/road/add", function(data){
 
    	if( "undefined"==data.code||data.code!='200'){Feng.error("新增失败！"+data.ERROR);return;}
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/road");
        winOpen.Road.table.refresh();
        top.layer.close(winOpen.Road.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roadInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RoadInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    if(this.get("roadname").length==0){
    	$("#roadname").focus();
    	 Feng.error("没有填写名称!");
    	return false;
    }
 
    var ac=this.get("areacode");
    if(ac.length<6){
    	$("#areacode").focus();
    	 Feng.error("没有选择具体所属行政区域!");
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/road/update", function(data){
    
    	if( "undefined"==data.code||data.code!='200'){Feng.error("修改失败！"+data.ERROR);return;}
        Feng.success("修改成功!");
        var winOpen=Feng.GetFrame("/road");
        winOpen.Road.table.refresh();
        top.layer.close(winOpen.Road.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roadInfoData);
    ajax.start();
}

$(function() {
	 
	 Feng.initValidator("roadInfoForm", RoadInfoDlg.validateFields);
});
