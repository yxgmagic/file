/**
 * 初始化货物转运处理详情对话框
 */
var MakeCargoInfoDlg = {
    makeCargoInfoData : {}
};

/**
 * 清除数据
 */
MakeCargoInfoDlg.clearData = function() {
    this.makeCargoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MakeCargoInfoDlg.set = function(key, val) {
    this.makeCargoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MakeCargoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MakeCargoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/makeCargo");
	if (winOpen!=undefined){
		top.layer.close(winOpen.MakeCargo.layerIndex);
	}
}

/**
 * 收集数据
 */
MakeCargoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('makecargono')
    .set('makevehicleid')
    .set('makevehicleman')
    .set('makevehicletel')
    .set('makecargoweight')
    .set('makecargodate')
    .set('withholdno')
    .set('makecargodateString');
}

/**
 * 提交添加
 */
MakeCargoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/makeCargo/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/makeCargo");
        if (winOpen!=undefined){
        	winOpen.MakeCargo.table.refresh();
        	top.layer.close(winOpen.MakeCargo.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.makeCargoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MakeCargoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/makeCargo/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/makeCargo");
        if (winOpen!=undefined){
        	winOpen.MakeCargo.table.refresh();
        	top.layer.close(winOpen.MakeCargo.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.makeCargoInfoData);
    ajax.start();
}

/**
 * 卸货单号绑定单击事件,
 * 弹出卸货管理的主页面,
 * 用于选择卸货单
 */
$("#withholdno").click(function() {
	var index = layer.open({
        type: 2,
        title: '选择卸货信息',
        area: ['790px', '490px'], //宽高
        fix: true, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/makeCargo/cargoHandling_popups',
        end: function() {
        	var withholdnoValue = $("#withholdno").val();
        	if(withholdnoValue != "") {
        		var ajax = new $ax(Feng.ctxPath + "/cargoHandling/getCargoHandlingByWithholdno/" + withholdnoValue, function(data){
            		initCarGoData(data);
                },function(data){
                    Feng.error("读取内容失败!" + data.responseJSON.message + "!");
                });
                ajax.start();
        	}
        }
    });
	layer.full(index);
});

function initCarGoData(data) {
	$("#withholdno").val(data.withholdno).change();
	$("#cargotype").val(data.cargotype);
	$("#carowner").val(data.carowner);
	$("#carownertel").val(data.carownertel);
	$("#caseno").val(data.caseno);
	$("#corpname").val(data.corpname);
	$("#cpqnumber").val(data.cpqnumber);
	$("#fctotalweight").val(data.fctotalweight);
	$("#rtnumber").val(data.rtnumber);
	$("#stationid").val(data.stationid);
	$("#ulloadcode").val(data.ulloadcode);
	$("#unloadno").val(data.unloadno);
	$("#unloadtimeString").val(data.unloadtime);
	$("#unloadweight").val(data.unloadweight);
	$("#vehicleid").val(data.vehicleid);
	$("#weightlimited").val(data.weightlimited);
	$("#ulloadname").val(data.ulloadname);
	$("#fixedsitename").val(data.fixedsitename);
	
	$("#makecargoweight").val(data.unloadweight);
}



/**
 * 初始化表单验证
 */
function initMakeCargoValidate() {
	$("#idMakeCargoForm").bootstrapValidator({
		feedbackIcons : {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],
		fields : {
			withholdno: {
				trigger:"change",
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        makecargono: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        makevehicleid: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        makevehicleman: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        makevehicletel: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
					regexp: {
						regexp: '^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$',
						message: '手机号码格式错误!'
					}
	            }
	        },
	        makecargoweight: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
	                callback: {
	                	callback : function(value) {
	                		var unloadweightValue = parseInt($("#unloadweight").val());
	                		var value1 = parseInt(value);
		                	if(unloadweightValue < value) {
		                		return {
		                			valid: false,
		                			message: '接货重量不可大于卸货重量'
		                		}
		                	}
		                	return true;
		                	
		                }
	                }
	            }
	        }
		}
	});
}

/**
 * 验证数据是否为空
 */
MakeCargoInfoDlg.validate = function () {
    $('#idMakeCargoForm').data("bootstrapValidator").resetForm();
    $('#idMakeCargoForm').bootstrapValidator('validate');
    return $("#idMakeCargoForm").data('bootstrapValidator').isValid();
}

//时间转换
Date.prototype.Format = function (fmt) { //author: meizz
	  var o = {
	    "M+": this.getMonth() + 1, //月份
	    "d+": this.getDate(), //日
	    "H+": this.getHours(), //小时
	    "m+": this.getMinutes(), //分
	    "s+": this.getSeconds(), //秒
	    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
	    "S": this.getMilliseconds() //毫秒
	  };
	  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	  for (var k in o)
	  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	  return fmt;
	}



$(function() {
	//makecargodateString
	if($("#makecargodateString").val() == "") {
		$("#makecargodateString").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
	}
	
	$("#cargotype").attr('disabled',true);
	
	initMakeCargoValidate();
});
