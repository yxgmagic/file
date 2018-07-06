/**
 * 初始化自定义报表模块管理详情对话框
 */
var TemplatesInfoDlg = {
    templatesInfoData : {}
};

/**
 * 清除数据
 */
TemplatesInfoDlg.clearData = function() {
    this.templatesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TemplatesInfoDlg.set = function(key, val) {
    this.templatesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TemplatesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TemplatesInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/templates");
	top.layer.close(winOpen.Templates.layerIndex);
}

/**
 * 收集数据
 */
TemplatesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('rfcode')
    .set('rfname')
    .set('rfdesc')
    .set('rfsrc');
}

/**
 * 提交添加
 */
TemplatesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/templates/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/templates");
        winOpen.Templates.table.refresh();
        top.layer.close(winOpen.Templates.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.templatesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TemplatesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/templates/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/templates");
        winOpen.Templates.table.refresh();
        top.layer.close(winOpen.Templates.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.templatesInfoData);
    ajax.start();
}



TemplatesInfoDlg.ajaxFileUploadDoc= function() {
	this.clearData();
	this.collectData();
	if(this.get("rfdesc")==''){
		$("#rfdesc").focus();
		Feng.error("没有填写报表说明!");
		return false;
	}
	if(this.get("rfcode")==''){
		$("#rfcode").focus();
		Feng.error("没有填写报表代码!");
		return false;
	}
	if(this.get("rfname")==''){
		$("#rfname").focus();
		Feng.error("没有填写报表名称!");
		return false;
	}

	var index = layer.load(1, {
		shade : [ 0.1, '#fff' ] //0.1透明度的白色背景
	});

	Array.prototype.contains = function(item) {
		return RegExp("\\b" + item + "\\b").test(this);
	};
	var formData = new FormData();
	//从隐藏的file input中取值
	formData.append("file", document.getElementById('img_hidden').files[0]);
	formData.append("rfcode", this.get("rfcode"));
	formData.append("rfname", this.get("rfname"));
	formData.append("rfdesc", this.get("rfdesc"));
	formData.append("id", document.getElementById('id').value);
	var fileName = $("#img_hidden").val();
	var suffix = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
	var ext = [ 'jasper', 'INEEDAV' ];
	//设置遮罩等待图片上传完成
	if (ext.contains(suffix)) {
		$.ajax({
			type : "POST",
			url : Feng.ctxPath + "/templates/reportUpload",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {
				if (data.code == 200) {
					document.getElementById('rfsrc').value = data.msg;
				} else {
					Feng.info(data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown, data) {
				Feng.error("上传失败,请重试");
			}
		});
		//删除图片遮罩

	} else {
		Feng.info("仅支持doc,docx格式,请重新选择");
		layer.close(index);
		return false;
	}
	layer.close(index);
}
$(function() {

});
