/**
 * 时间选择器控件
 */
layui.use('laydate', function(){
	var laydate = layui.laydate;
  	laydate.render({ 
  	  elem: '#casetime'
  	  ,type: 'month'
  	  ,range: '~' //或 range: '~' 来自定义分割字符
        ,value: '2018-05-01'
        ,max: ''+new Date()
  	});
});
    
//时间选择器控件
layui.use('laydate', function(){
	var laydate = layui.laydate;
  	laydate.render({ 
  	  elem: '#casetime1'
  	  ,type: 'date'
  	  ,range: '~' //或 range: '~' 来自定义分割字符
  	  ,max: ''+new Date()
  	});
});

/**
 * 页面加载完成之后请求的待办事项数据
 */
var notices = new Array();
//数据统计信息数据
var statisticsData = {};

$(function() {

	//给主页几个统计数据设置显示图片
    $(".num-bg1").css("background", "url('" + Feng.ctxPath + "/static/Resource/Images/chaozaiche.png') no-repeat 20px");
    $(".num-bg1").css("color", "#ff6483");
    $(".num-bg1").css("background-size", "12%");

    $(".num-bg2").css("background", "url('" + Feng.ctxPath + "/static/Resource/Images/dangqiancheliu.png') no-repeat 20px");
    $(".num-bg2").css("color", "#47d195");
    $(".num-bg2").css("background-size", "12%");

    $(".num-bg3").css("background", "url('" + Feng.ctxPath + "/static/Resource/Images/chufache.png') no-repeat 20px");
    $(".num-bg3").css("color", "#4ec1f8");
    $(".num-bg3").css("background-size", "12%");

    $(".num-bg4").css("background", "url('" + Feng.ctxPath + "/static/Resource/Images/xiehuo.png') no-repeat 20px");
    $(".num-bg4").css("color", "#f7b82d");
    $(".num-bg4").css("background-size", "12%");


    //拿到今年的时间,用于后面的查询
    var myDate = new Date();
    var thisMonth = myDate.getMonth() + 1;

    $("#noteBox").html("注:数据统计时间为"+myDate.getFullYear() +"年"+thisMonth+"月1日至今");

	$.ajax({
        type : "POST",
        url : Feng.ctxPath + "/indexData/getIndexData",
        data : {month: thisMonth},
        success : function(data) {
            notices = data.notices;
            statisticsData = data.statisticsData;

            //显示统计信息
            var numArr = $(".numberShow");
            numArr[0].innerHTML = statisticsData.overloadCount;
            numArr[1].innerHTML = statisticsData.totalFlow;
            numArr[2].innerHTML = statisticsData.handleCount;
            numArr[3].innerHTML = statisticsData.unloadWeight;

            //循环遍历,将所有的待办事项添加到页面
            for(var i = 0; i < notices.length; i++) {
                $("#noticeBox").append(
                	$('<div class="item">' +
                    '<div class="tu"></div>' +
                    '<span>'+ notices[i].title +'</span>' +
					'<span class="dt">' + notices[i].createtime + '</span>' +
                    '</div>')
				);
			}

        },
        error : function() {
            Feng.error("初始化数据出错,请重试!");
        }
    });

	//默认设置为当前月至今的时间的车流量信息
    //2018-05-02 ~ 2018-05-08
    var mydate = new Date();
    var year1 = mydate.getFullYear();
    var month1 = mydate.getMonth() + 1;
    var day1 = mydate.getDate();
    dateString = year1 + "-" + month1 + "-01~" + year1 + "-" + month1 + "-" + day1;

    //将时间区域信息带入,查询车流量信息
    getTrafficFlow("",dateString,"down");
});

var deptarr = [];
var dateString = ""

//点击查询方法
function selTrafficFlow() {
    if($("#casetime1").val() == "") {
        return;
    }
    dateString = $("#casetime1").val();
    getTrafficFlow("",dateString,"down");
}



/**
 * 请求后台获取车流量信息
 * @param deptId    需要查询的部门id
 * @param dataArea  查询的时间区域
 * @param type      是向上钻取还是向下钻取
 */
function getTrafficFlow(deptId,dateArea,type) {

	$.ajax({
		type: "POST",
		url: Feng.ctxPath + "/indexData/getTrafficFlow",
        data : {"dataArea" : dateArea, "thatDeptid":deptId, "type": type},
		success: function(data) {
			var siteName = [];
			var siteData = [];
            deptarr = [];
            siteData.push([]);
            siteData.push([]);
			for(var i = data.length - 1; i >= 0; i--) {
				var tempData = data[i];
                siteName.push(tempData.simplename);
                var sum1 = parseInt(tempData.sum);
                var oversum1 = parseInt(tempData.oversum == "" ? 0 : tempData.oversum);
                siteData[0].push(sum1);
                siteData[1].push(oversum1)
                deptarr.push(tempData.id)
			}

            //刷新车流量图表
            refreshFlowChart(document.getElementById("time"), dateArea + "流量统计", ["车流量","超限车流量"] , siteName, siteData);
		},
        error : function() {
            Feng.error("获取数据出错,请重试!");
        }

	});
}


/**
 * 更新图表的数据(车流量)
 * @param ele 用于显示的元素
 * @param title 图表的名字
 * @param titleArr 图表的每项的名字
 * @param xAxisArr 横坐标名称
 * @param seriesArr 数据数组    这里只有车流量和超载量,所以只要传一个二维数组作为数据源
 * @returns
 */
function refreshFlowChart(ele, title, titleArr, xAxisArr, seriesArr) {

    // console.log($(ele).empty());

    //在这里的option配置,将标题和x轴的数据利用数组传进来,
    // echarts是支持使用数组作为图像数据展示的

    // 初始化 echarts 实例
    var tempChart = echarts.init(ele);

    //设置基本配置项
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        title: {
            left: 'center',
            text: title
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            bottom: 10,
            type: 'scroll',
            data: titleArr
        },
        xAxis: [
            {
                type: 'category',
                data: xAxisArr,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '流量',
                axisLabel: {
                    formatter: '{value} 辆'
                }
            }
        ],
        series: [
            {
                name:titleArr[0],
                type:'bar',
                data:seriesArr[0]
            },
            {
                name:titleArr[1],
                type:'bar',
                data:seriesArr[1],

            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    tempChart.setOption(option);

    //防止重复绑定,每次绑定的时候,移除其绑定事件
    tempChart.off("click");
    tempChart.off("dblclick");
    //因为要实现点击数据下钻,双击数据上调
    //在这里进行绑定
    // down ,代表下钻, upper 代表上调
    //绑定单击事件
    tempChart.on('click',function(params) {
        var deptid = deptarr[params.dataIndex];
        getTrafficFlow(deptid,dateString,"down");
    });
    //dblclick 双击事件
    tempChart.on('dblclick',function(params) {
        var deptid = deptarr[params.dataIndex];
        getTrafficFlow(deptid,dateString,"upper");
    });

}


 