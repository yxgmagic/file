/**
 * 初始化精检站治超数据详情对话框
 */
var LscinfoInfoDlg = {
    lscinfoInfoData : {}
};

/**
 * 清除数据
 */
LscinfoInfoDlg.clearData = function() {
    this.lscinfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LscinfoInfoDlg.set = function(key, val) {
    this.lscinfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LscinfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LscinfoInfoDlg.close = function() {
	var winOpen=Feng.GetFrame("/lscinfo");
	if (winOpen!=undefined){
		top.layer.close(winOpen.Lscinfo.layerIndex);
	}
}

/**
 * 收集数据
 */
LscinfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('vehicleid')
    .set('axlesum')
    .set('weightlimited')
    .set('ratedtotalweight')
    .set('ratedloading')
    .set('overlimited')
    .set('overload')
    .set('fctime')
    .set('fclaneno')
    .set('fctotalweight')
    .set('fcoper')
    .set('fcaxle1')
    .set('fcaxle2')
    .set('fcaxle3')
    .set('fcaxle4')
    .set('fcaxle5')
    .set('fcaxle6')
    .set('fcaxle7')
    .set('fcaxle8')
    .set('fclength')
    .set('fcwidth')
    .set('fcheight')
    .set('overlength')
    .set('overwidth')
    .set('overheight')
    .set('rctime')
    .set('rclaneno')
    .set('rctotalweight')
    .set('rcoper')
    .set('rcaxle1')
    .set('rcaxle2')
    .set('rcaxle3')
    .set('rcaxle4')
    .set('rcaxle5')
    .set('rcaxle6')
    .set('rcaxle7')
    .set('rcaxle8')
    .set('rclength')
    .set('rcwidth')
    .set('rcheight')
    .set('offload')
    .set('fcvehicleimage')
    .set('rcvehicleimage')
    .set('stationid')
    .set('prostatus')
    .set('rtimeString')
        .set('ftimeString')
    .set('sitetype')
    .set('sitecode')
    .set('vehicletype')
        .set('trucksRuleName')
        .set('picplate')
        .set('picside')
        .set('picside2')
        .set('pictotal')
        .set('picview')
        .set('picviewback')
        .set('video1')
        .set('video2')
        .set('checkno')
        .set('weightlimited');


}

/**
 * 提交添加
 */
LscinfoInfoDlg.addSubmit = function() {
	
    this.clearData();
    this.collectData();

    if(this.get('ftimeString').length < 1) {
        layer.msg("请先获取数据再保存");
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lscinfo/add", function(data){
        Feng.success("添加成功!");
        
        var winOpen=Feng.GetFrame("/lscinfo");
        if (winOpen!=undefined){
        	winOpen.Lscinfo.table.refresh();
        	top.layer.close(winOpen.Lscinfo.layerIndex);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lscinfoInfoData);
    ajax.start();
}

LscinfoInfoDlg.addSubmitSimulate = function() {
	
    this.clearData();
    this.collectData();
 
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lscinfo/updateSimulate", function(data){
        Feng.success("添加成功!");
        
  
    },function(data){
        Feng.error("添加失败!" + data+ "!");
    });
    ajax.set(this.lscinfoInfoData);
    ajax.start();
}

//复检从地磅获取数据
$("#idGetLoadometerData").click(function() {
    layer.msg("正在从地磅获取数据");
    var quanshu1 = $("#fctotalweight").val() - $("#weightlimited").val();
    var floatValue;
    var rctotalweightValue;
    if(quanshu1 > 0) {
        floatValue = Math.random() * quanshu1;
        rctotalweightValue = $("#weightlimited").val() - (quanshu1 / 2) + floatValue;
    } else {
        rctotalweightValue = $("#fctotalweight").val();
    }

	//var rctotalweightValue = $("#weightlimited").val() - 2500 + floatValue;
	$("#rctotalweight").val(parseInt(rctotalweightValue));
    $("#rtimeString").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
    LscinfoInfoDlg.set("prostatus","1");

});

/**
 * 提交修改
 */
LscinfoInfoDlg.editSubmit = function() {
		

	//console.log(new Date().Format("yyyy-MM-dd HH:mm:ss"));
	
	//return;
	
    this.clearData();
    this.collectData();
    var rctotalweight = this.get("rctotalweight");
    if(rctotalweight == null || rctotalweight == ''){
    	layer.msg("没有复检,请从地磅获取!");
    	return ;
    }
    var rctotalweightValue = parseInt(rctotalweight);
	var weightlimitedValue = parseInt(this.get("weightlimited"));
	if(rctotalweightValue > weightlimitedValue) {
		layer.msg("违法状态还未清除");
        this.set("prostatus","0");
		return;
	} else {

	    var fctotalweightValue = parseInt(this.get("fctotalweight"));
	    var weightlimitedValue = parseInt(this.get("weightlimited"));
	    var temp = fctotalweightValue - weightlimitedValue;
	    if(rctotalweightValue == null ){
	    	return ;
	    }
	    if(temp > 0) {
            this.set("prostatus","1");
        } else {
            this.set("prostatus","0");
        }

	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lscinfo/update", function(data){
        Feng.success("修改成功!");
        
        var winOpen=Feng.GetFrame("/lscinfo");
        if (winOpen!=undefined){
        	winOpen.Lscinfo.table.refresh();
        	top.layer.close(winOpen.Lscinfo.layerIndex);
        } else
        {
            var index = parent.layer.getFrameIndex(window.name)//获取子窗口索引
            parent.layer.close(index);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lscinfoInfoData);
    ajax.start();
}
$("#vehicletypename").click(function() {
	var index = layer.open({
        type: 2,
        title: '选择车型',
        area: ['790px', '490px'], //宽高
        fix: true, //不固定
        content: Feng.ctxPath + '/punishmentRules',
    });
	layer.full(index);
	
});
$(function() {

	
	/**
	 * 获取需要显示的车头信息
	 */
	layui.use([ 'carousel', 'form' ], function() {
		var carousel = layui.carousel, form = layui.form;
		
		$.ajax({
			type : "POST",
			url : Feng.ctxPath+"/lscinfo/getVehicleImages",
			data : {"id" : $("#id").val()},
			success : function(data) {
				if($("#img_box").length > 0) {
                    $("#img_box").empty();
					if(data.length > 0) {
						for (var i = 0; i < data.length; i++) {
							$("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
							// $("#img" + i)[0].src = "data:image/png;base64,"+data[i];
							$("#img" + i)[0].src = 'http://192.168.10.136:8080/' + data[i];
						}
					} else {
						for (var i = 0; i < 2; i++) {
							$("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
							$("#img" + i)[0].src = Feng.ctxPath + "/static/Resource/Images/truck.jpg";
						}
					}

					carousel.render({
						elem : '#photo',
						arrow : 'always'
					});
                }
			},
			error : function() {
				Feng.error("初始化数据出错,请重试!");
			}
		});
		
	});
	
});

//点击打印磅单时弹出预览页面
$("#idPrintPreview").click(function() {

    // if($("#rtimeString").val().length < 1) {
    //     layer.msg("请复检之后保存再打印磅单");
    //     return;
    // }

	window.open(Feng.ctxPath + '/lscinfo/lscinfo_preview/' + $("#id").val());

});

//简单封装的get请求
function getGet(url) {
    var areajson =null;
    var ajax = new $ax(Feng.ctxPath + url, function (data) {
        areajson=data;
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");

    });
    ajax.start();
    return areajson;

};

//初检从地磅获取信息
function getFirstLoadometerData() {
    layer.msg("正在从地磅获取数据");
	var punishmentRulesList;
    punishmentRulesList =getGet("/lscinfo/getLoadometer");
    var tempDate = punishmentRulesList[0];
    $("#trucksRuleName").val(tempDate.ruleName);
    $("#axlesum").val(tempDate.trucksAxles);

    var tempLscinfoDate = punishmentRulesList[1];
    var photoArr = [];
    var picAttr = ['picplate', 'picside', 'picside2', 'pictotal', 'picview', 'picviewback'];
    var videoAttr = ['video1', 'video2'];
    for (var i = 0; i < picAttr.length; i++) {
        var tempAttr = picAttr[i];
        var tempValue = tempLscinfoDate[tempAttr];
        $("#" + tempAttr).val(tempValue);
        photoArr.push(tempValue);
    }
    for (var i = 0; i < videoAttr.length; i++) {
        var tempAttr = videoAttr[i];
        var tempValue = tempLscinfoDate[tempAttr];
        $("#" + tempAttr).val(tempValue);
    }
    setPhoto(photoArr);
    $("#photo").show();

    var weightLimitValue = parseInt(tempDate.weightLimit) * 1000;
    $("#weightlimited").val(weightLimitValue);
    var floatValue = Math.random() * 5000;
    var fctotalweightValue = parseInt(weightLimitValue - 1500 + floatValue);
    $("#fctotalweight").val(parseInt(fctotalweightValue));
    $("#ftimeString").val(new Date().Format("yyyy-MM-dd HH:mm:ss"));

    var overLimitedValue = parseInt(fctotalweightValue - weightLimitValue);
    if( overLimitedValue > 0) {
        $("#overlimited").val(overLimitedValue);
    } else {
        $("#overlimited").val(0);
    }
    $("#vehicleid").val(Yi.components.carPlate());




}

function setPhoto(data) {
    if($("#img_box").length > 0) {
        $("#img_box").empty();
        if(data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                $("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
                // $("#img" + i)[0].src = "data:image/png;base64,"+data[i];
                $("#img" + i)[0].src = 'http://192.168.10.136:8080/' + data[i];
            }
        } else {
            for (var i = 0; i < 2; i++) {
                $("#img_box").append("<img src='' alt='车头照片' id='img"+i+"'/>");
                $("#img" + i)[0].src = Feng.ctxPath + "/static/Resource/Images/truck.jpg";
            }
        }
        layui.carousel.render({
            elem : '#photo',
            arrow : 'always'
        });
    }
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


