/**
 * 初始化考核计划详情对话框
 */
var PeAssessplanInfoDlg = {
    peAssessplanInfoData : {}
};

/**
 * 清除数据
 */
PeAssessplanInfoDlg.clearData = function() {
    this.peAssessplanInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PeAssessplanInfoDlg.set = function(key, val) {
    this.peAssessplanInfoData[key] = (typeof val === "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PeAssessplanInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PeAssessplanInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/assessplan");
	if (winOpen!==undefined){
		top.layer.close(winOpen.PeAssessplan.layerIndex);
	}
}

/**
 * 收集数据
 */
PeAssessplanInfoDlg.collectData = function() {
    this
        .set('id')
        .set('assessName')
        .set('assessTime')
        .set('assessObjHidden')
        .set('assessIndicHidden')
        .set('notes');
}

/**
 * 提交添加
 */
PeAssessplanInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //验证信息正确性
    if(Yang.Tools.checkFormDate('peAssess')) {
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/assessplan/add", function(data){
            Feng.success("添加成功!");

            var winOpen=Feng.GetFrame("/assessplan");
            if (winOpen!==undefined){
                winOpen.PeAssessplan.table.refresh();
                top.layer.close(winOpen.PeAssessplan.layerIndex);
            }
        },function(data){
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.peAssessplanInfoData);
        ajax.start();
    }
}

/**
 * 提交修改
 */
PeAssessplanInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //验证信息正确性
    if(Yang.Tools.checkFormDate('peAssess')) {
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/assessplan/update", function(data){
            Feng.success("修改成功!");

            var winOpen=Feng.GetFrame("/assessplan");
            if (winOpen!==undefined){
                winOpen.PeAssessplan.table.refresh();
                top.layer.close(winOpen.PeAssessplan.layerIndex);
            }
        },function(data){
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.peAssessplanInfoData);
        ajax.start();
    }
}


/**
 * 打开页面选择
 * @param tp 站点 & 指标
 * @param page addPage & editPage
 * @param id 如果是addPage,id=-1,如果是editPage,id={item.id}
 */
function openChoosePlugin(tp,page,id) {
    var index = top.layer.open({
        type: 2,
        title: '请选择',
        area: ['640px', '455px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/assessplan/openChoosePlugin/' + tp + '/' + page + '/' + id
    });
    this.layerIndex = index;
}

$(function() {

    //设置时间区间控件
    Yang.Tools.date_interval('assessTime','datetime')


    $('#peAssess').bootstrapValidator({
        message: '这个值没有被验证',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            assessName: {
                validators: {
                    notEmpty: {
                        message: '考核名称为必填项!'
                    }
                }
            }
        }
    });
});
