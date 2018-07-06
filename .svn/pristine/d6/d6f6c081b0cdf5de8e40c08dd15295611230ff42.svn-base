/**
 * 初始化高速出入口信息详情对话框
 */
var HswayInfoDlg = {
    hswayInfoData : {}
	,zTreeArea : null
	,zTreeRoad:null
	,validateFields: {
		hswayname: {
			validators: {
				notEmpty: {
					message: '出入口名称不能为空'
				}
			}
		}
		,setbegindate: {
			validators: {
				notEmpty: {
					message: '设置时间不能为空'
				}
			}
		}
		,cameras:{
			validators: {
				regexp: {
                    regexp: /^[1-9]/,
                    message: '摄像头个数>0'
                }
			}
		},areaName: {
            validators: {
                notEmpty: {
                    message: '所属区域不能为空'
                }
            }
        },roadName: {
            validators: {
                notEmpty: {
                    message: '所属道路不能为空'
                }
            }
        },stakeNumber: {
            validators: {
                notEmpty: {
                    message: '桩号不能为空'
                }
            }
        },bankAccount: {
            validators: {
                notEmpty: {
                    message: '银行账号不能为空'
                }
            }
        },dueBank: {
            validators: {
                notEmpty: {
                    message: '收款银行不能为空'
                }
            }
        },judicialOrgan: {
            validators: {
                notEmpty: {
                    message: '审判机关不能为空'
                }
            }
        },bankAccountName: {
            validators: {
                notEmpty: {
                    message: '银行户名不能为空'
                }
            }
        },bankAddress: {
            validators: {
                notEmpty: {
                    message: '银行地址不能为空'
                }
            }
        },maxweight: {
            validators: {
                notEmpty: {
                    message: '最大测重不能为空'
                },regexp: {
                    regexp: /^[1-9][0-9]{0,5}$/,
                    message: '最大测重(1-999999kg)格式不正确'
                }
            }
        }
	}
};

/**
 * 清除数据
 */
HswayInfoDlg.clearData = function() {
    this.hswayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HswayInfoDlg.set = function(key, val) {
    this.hswayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HswayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HswayInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/hsway");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Hsway.layerIndex);
	}
}

/**
 * 收集数据
 */
HswayInfoDlg.collectData = function() {
    this
    .set('id')
    .set('img_id')
	.set('imagetype')
    .set('hswayname')
    .set('address')
    .set('lng')
    .set('lat')
    .set('devicefirm')
    .set('isnet')
    .set('isdisting')
    .set('roadwidth')
    .set('setbegindate')
    .set('maxweight')
    .set('cameras')
    .set('camerabrand')
    .set('checkdirection')
    .set('areacode')
    .set('roadcode')
        .set('stakeNumber')
        .set('bankAccount')
        .set('dueBank')
        .set('judicialOrgan')
        .set('bankAccountName')
        .set('bankAddress')
    ;
}

/**
 * 提交添加
 */
HswayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
   
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hsway/add", function(data){
    	var result = $.parseJSON(data);
        if(result.flag){
        	Feng.success(result.msg);
        	var winOpen=Feng.GetFrame("/hsway");
        	if (winOpen!=undefined){
        		winOpen.Hsway.table.refresh();
        		top.layer.close(winOpen.Hsway.layerIndex);
        	}
        }else{
        	Feng.error(result.msg);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hswayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HswayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hsway/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/hsway");
        if (winOpen!=undefined){
        	winOpen.Hsway.table.refresh();
        	top.layer.close(winOpen.Hsway.layerIndex);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hswayInfoData);
    ajax.start();
}

HswayInfoDlg.onClickArea = function(e, treeId, treeNode) {
    $("#areaName").attr("value", HswayInfoDlg.zTreeArea.getSelectedVal());
    $("#areacode").attr("value", treeNode.areaCode);
    $("#parentAreaMenu").toggle();
}
HswayInfoDlg.onClickRoad = function(e, treeId, treeNode) {
    $("#roadName").attr("value", HswayInfoDlg.zTreeRoad.getSelectedVal());
    $("#roadcode").attr("value", treeNode.entityCode);
    $("#parentRoadMenu").toggle();
}
/**
 * 显示区域选择的树
 */
HswayInfoDlg.showAreaSelectTree = function() {
	Feng.showInputTree("areaName", "parentAreaMenu");
}
/**
 * 显示道路选择的树
 */
HswayInfoDlg.showRoadSelectTree = function() {
	Feng.showInputTree("roadName", "parentRoadMenu");
}
/**
 * 验证数据是否为空
 */
HswayInfoDlg.validate = function () {
	 $('#hswayForm').data("bootstrapValidator").resetForm();
	 $('#hswayForm').bootstrapValidator('validate');
	 return $("#hswayForm").data('bootstrapValidator').isValid();
}

$(function() {
	Feng.initValidator("hswayForm", HswayInfoDlg.validateFields);
	
	Feng.getImage($("#id").val(), $("#imagetype").val());
	//监听上传按钮的改变事件
	$("#img_hidden").change(function() {
		//隐藏图片(input=file)id,显示图片id,上传图片后图片id
		Feng.ajaxFileUploadEntityImage();
	});



    //图片预览
	/*$("#img_show").click(function() {
		layer.open({
			type: 1,
			title: '图片预览',
			closeBtn: 1,
			area: ['700px', '450px'],
			skin: 'layui-layer-nobg', //没有背景色
			shadeClose: true,
			content: "<img id='openImg' width='694px' heigth='500px' src='"+$("#img_show")[0].src+"'>"
		});
	});*/
	
    //区域
    var ztreeArea = new $ZTree("parentAreaMenuTree", "/area/tree");
    ztreeArea.bindOnClick(HswayInfoDlg.onClickArea);
    ztreeArea.init();
    HswayInfoDlg.zTreeArea = ztreeArea;
    //道路
    var ztreeRoad = new $ZTree("parentRoadMenuTree", "/road/tree");
    ztreeRoad.bindOnClick(HswayInfoDlg.onClickRoad);
    ztreeRoad.init();
    HswayInfoDlg.zTreeRoad = ztreeRoad;
    
    //初始化是否
    if($("#isnetValue").val() == undefined){
        $("#isnet").val();
    }else{
        $("#isnet").val($("#isnetValue").val());
    }
    
   //初始化是否
    if($("#isdistingValue").val() == undefined){
        $("#isdisting").val();
    }else{
        $("#isdisting").val($("#isdistingValue").val());
    }
});




// 地图标注
$('#btnGis').bind("click", function () {
    Yi.components.setMapMarker({
        lng: '',
        lat: '',
        src: '/hsway/hsway_add',
        lngId: 'lng',
        latId: 'lat'
    })
});