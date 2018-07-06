/**
 * 初始化执法文书详情对话框
 */
var LawDocInfoDlg = {
		lawDocInfoData : {}
};

/**
 * 清除数据
 */
LawDocInfoDlg.clearData = function() {
	this.lawDocInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LawDocInfoDlg.set = function(key, val) {
	this.lawDocInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
	return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LawDocInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LawDocInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/lawDoc");
	top.layer.close(winOpen.LawDoc.layerIndex);
}

/**
 * 收集数据
 */
LawDocInfoDlg.collectData = function() {
	this
	.set('id')
	.set('ldcode')
	.set('ldname')
	.set('ldtype')
	.set('ldstatus')
	.set('ldno')
	.set('ldfileurl');

}

/**
 * 提交添加
 */
LawDocInfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();
	if(this.get("ldtype")==''){
		$("#ldtype").focus();
		Feng.error("没有选择文书类型!");
		return false;
	}
	if(this.get("ldname")==''){
		$("#ldname").focus();
		Feng.error("没有填写文书名称!");
		return false;
	}
	if(this.get("ldstatus")==''){
		$("#ldstatus").focus();
		Feng.error("没有选择文书状态!");
		return false;
	}

	//验证信息正确性

	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/lawDoc/add", function(data){
		Feng.success("添加成功!");

		var winOpen=Feng.GetFrame("/lawDoc");
		winOpen.LawDoc.table.refresh();
		top.layer.close(winOpen.LawDoc.layerIndex);
	},function(data){
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.lawDocInfoData);
	ajax.start();	

}

/**
 * 提交修改
 */
LawDocInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();
	if(this.get("ldtype")==''){
		$("#ldtype").focus();
		Feng.error("没有选择文书类型!");
		return false;
	}
	if(this.get("ldstatus")==''){
		$("#ldstatus").focus();
		Feng.error("没有选择文书状态!");
		return false;
	}
	$('#lawdocform').bootstrapValidator('validate');
	var bootstrapValidator = $("#lawdocform").data('bootstrapValidator');
	bootstrapValidator.validate();

	//验证信息正确性
	if(bootstrapValidator.isValid()){
		//提交信息
		var ajax = new $ax(Feng.ctxPath + "/lawDoc/update", function(data){
			Feng.success("修改成功!");

			var winOpen=Feng.GetFrame("/lawDoc");
			winOpen.LawDoc.table.refresh();
			top.layer.close(winOpen.LawDoc.layerIndex);
		},function(data){
			Feng.error("修改失败!" + data.responseJSON.message + "!");
		});
		ajax.set(this.lawDocInfoData);
		ajax.start();
	}else{
		return false;
	}
}

LawDocInfoDlg.ajaxFileUploadDoc= function() {
	this.clearData();
	this.collectData();
	if(this.get("ldtype")==''){
		$("#ldtype").focus();
		Feng.error("没有选择文书类型!");
		return false;
	}
	if(this.get("ldname")==''){
		$("#ldname").focus();
		Feng.error("没有填写文书名称!");
		return false;
	}
	if(this.get("ldstatus")==''){
		$("#ldstatus").focus();
		Feng.error("没有选择文书状态!");
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
	console.log(formData.get('file'));
	formData.append("ldname", this.get("ldname"));
 

	formData.append("ldtype", this.get("ldtype"));

	formData.append("id", document.getElementById('id').value);
	var fileName = $("#img_hidden").val();
	var suffix = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
	var ext = [ 'doc', 'docx', 'wps' ];
	console.log(JSON.stringify(formData));
	//设置遮罩等待图片上传完成
	if (ext.contains(suffix)) {

		$.ajax({
			type : "POST",
			url : Feng.ctxPath + "/lawDoc/lawDocUpload",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(data) {

				if (data.code == 200) {



					var testid=document.getElementById('id').value
					if(testid!="-1"){
						document.getElementById('ldstatus').value = "2";
						$("#ensure").attr('disabled','true')
						var winOpen=Feng.GetFrame("/lawDoc");
						winOpen.LawDoc.table.refresh();
					}else {document.getElementById('ldfileurl').value = data.msg;}



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

	$('#lawdocform').bootstrapValidator({
		message: '这个值没有被验证',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			ldname: {
				validators: {
					notEmpty: {
						message: '该项为必填项'
					}
				}
			},



			ldtype: {
				validators: {
					notEmpty: {
						message: '该项为必选项'
					},

				}
			},
			ldstatus: {
				validators: {
					notEmpty: {
						message: '该项为必选项'
					}
				}
			}



		}
	});

});

