/**
 * 初始化超载货物处理详情对话框
 */
var CargoHandlingInfoDlg = {
    cargoHandlingInfoData : {}
};

/**
 * 清除数据
 */
CargoHandlingInfoDlg.clearData = function() {
    this.cargoHandlingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CargoHandlingInfoDlg.set = function(key, val) {
    this.cargoHandlingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CargoHandlingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CargoHandlingInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/cargoHandling");
	if (winOpen!=undefined){
		top.layer.close(winOpen.CargoHandling.layerIndex);
	}
}

/**
 * 收集数据
 */
CargoHandlingInfoDlg.collectData = function() {
    this
    .set('id')
    .set('withholdno')
    .set('unloadno')
    .set('stationid')
    .set('vehicleid')
    .set('unloadtime')
    .set('ulloadcode')
    .set('caseno')
    .set('carowner')
    .set('carownertel')
    .set('unloadweight')
    .set('cargotype')
    .set('rtnumber')
    .set('cpqnumber')
    .set('corpname')
    .set('weightlimited')
    .set('fctotalweight')
    .set('unloadtimeString');
}

/**
 * 提交添加
 */
CargoHandlingInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cargoHandling/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/cargoHandling");
        if (winOpen!=undefined){
        	winOpen.CargoHandling.table.refresh();
        	top.layer.close(winOpen.CargoHandling.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cargoHandlingInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CargoHandlingInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cargoHandling/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/cargoHandling");
        if (winOpen!=undefined){
        	winOpen.CargoHandling.table.refresh();
        	top.layer.close(winOpen.CargoHandling.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cargoHandlingInfoData);
    ajax.start();
}

/**
 * 绑定卸货场弹框
 */
$("#ulloadname").click(function() {
	top.layer.open({
        type: 2,
        title: '选择卸货场',
        area: ['1000px', '600px'], //宽高
        fix: true, //不固定
        content: Feng.ctxPath + '/cargoHandling/unloading_popups',
    });
	// layer.full(index);
	
});

//站点input被点击时的事件
/**
* 被点击时展示站点的树形菜单,
* 并给body绑定单击事件,
* 用来判断用户是否在操作树形菜单
*/
$("#fixedsitename").click(function() {
	// Ning.Tree.setStree("#idStationStree", "fixedsite");
	Ning.Tree.bindOnClick(
   		function(node) {

   			if(node.type == "fixedsite") {
   				$("#fixedsitename").val(node.name).change();
       			$("#stationid").val(node.sitecode).change();
   			} else {
   				$("#fixedsitename").val("").change();
       			$("#stationid").val("").change();
   			}
   			
   		}
   	);
	$("#idStationStree").slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
});

//body点击事件
/**
* 判断点击的元素的父元素中是否含有#idStationStree,
* 如果没有则代表用户点击到了树形菜单以外的区域,
* 需要将树形菜单隐藏,且取消页面点击事件的绑定
*/
function onBodyDown(event) {
  if(!$(event.target).parents("#idStationStree").length == 1) {
  	$("#idStationStree").fadeOut("fast");
  	$("body").unbind("mousedown", onBodyDown);
  }
}

/**
 * 初始化表单验证
 */
function initCargoHandlingValidate() {
	$("#idCargoHandlingForm").bootstrapValidator({
		feedbackIcons : {
			valid: 'glyphicon glyphicon-ok',
			validating: 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],
		fields : {
	        vehicleid: {
	        	trigger:"change",
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        carowner: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        ulloadname: {
	        	trigger:"change",
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        weightlimited: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        fctotalweight: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        unloadweight: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        unloadtime: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        withholdno: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        fixedsitename: {
	        	trigger:"change",
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        caseno: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                }
	            }
	        },
	        carownertel: {
	            validators: {
	                notEmpty: {
	                    message: '不能为空'
	                },
					regexp: {
						regexp: '^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,2,5-9]))\\d{8}$',
						message: '手机号码格式错误!'
					}
	            }
	        }
		}
	});
}

/**
 * 验证数据是否为空
 */
CargoHandlingInfoDlg.validate = function () {
    $('#idCargoHandlingForm').data("bootstrapValidator").resetForm();
    $('#idCargoHandlingForm').bootstrapValidator('validate');
    return $("#idCargoHandlingForm").data('bootstrapValidator').isValid();
}

//获取货物类型下拉菜单信息
var cargoType = null;
function getCargoType() {
	var ajax = new $ax(Feng.ctxPath + "/cargoHandling/getCargoType", function (data) {
		cargoType = data;
    }, function (data) {
        Feng.error("从后台获取数据失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

//给货物类型数据添加数据
function addCargoToMenu(downMenu) {
	var length = cargoType.length;
	var i = 0;
	for(i = 0; i < length; i++) {
		var temp = cargoType[i];
		var option = "<option value='"+temp.num+"'>"+temp.name+"</option>";
		downMenu.append(option);
	}
}

var cpindex;
//弹出车牌号选择弹框
$("#vehicleid").click(function() {
	if($("#stationid").val() != "") {
        cpindex = top.layer.open({
	        type: 2,
	        title: '选择车牌号',
	        area: ['1000px', '600px'], //宽高
	        fix: true, //不固定
	        content: Feng.ctxPath + '/cargoHandling/liscinfo_popups/' + $("#stationid").val(),
	    });
	} else {
		layer.msg("请先选择站点");
	}
	
});

//根站点数据Id自动填写其他数据
function autoFill() {
	var infoData = null;
	
	$.ajax({
		type: 'POST',
		url: Feng.ctxPath + "/cargoHandling/getInfoByStationid",
		data: {
			'id': $("#caseno").val()
		},
		success: function(data) {
			infoData = data;
			$("#ulloadname").val(infoData.ulname);
			$("#ulloadcode").val(infoData.ulcode);
			$("#weightlimited").val(infoData.weightlimited);
			$("#fctotalweight").val(infoData.fctotalweight);
			$("#unloadweight").val(infoData.unloadweight);
			$("#corpname").val(infoData.corpName);
		},
	});
	
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
	
	
	initCargoHandlingValidate();
	getCargoType();
	addCargoToMenu($("#cargotype"));
	
	if($("#unloadtimeString").val() == "") {
		$("#unloadtimeString").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
	}

	//设置下拉站点选择框
    Ning.Tree.setStree("#idStationStree", "fixedsite");
	
});


