// 初始化 echarts 实例
var carAreaChart = echarts.init(document.getElementById('carArea'));

// 表格初始化
var CarAreaCount = {
    id: "CarAreaCountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
CarAreaCount.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '排名', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
        {title: '所属区域', field: 'shenName', visible: true, align: 'center', valign: 'middle'},
        {title: '案件数', field: 'number', visible: true, align: 'center', valign: 'middle'}
            
    ];
};

$("#CarAreaCountSearch").on("click", function(){
	var fctime = $("#casetime").val() || "";
	var level = $("#level").val() || "";
    var sitecode = $("#sitecode").text() || '';
	console.log(sitecode);
	$.ajax({
		url: Feng.ctxPath + "/carAreaCount/getOverRun",
		data: {
			fctime: fctime,
			level: level,
            stationid: sitecode
		},
		success: function(result){
			console.log(result);
			var area = [];
			var num = [];
			if (result) {
				for (var i = 0; i <result.length; i ++) {
					area.push(result[i].shenName);
					num.push(result[i].number)
				}
				console.log(area);
				console.log(num);
				var options = {
					backgroundColor: '#eee',
					title: {
						text: "违法车辆所属区域统计"
					},
					tooltip: {},
					legend: {
						data: ["案件数"],
						align: 'left'
					},
					toolbox: {
						feature: {
							saveAsImage: {
								pixelRatio: 2
							}
						}
					},
					xAxis: {
						data: area,
						silent: false,
						splitLine: {
							show: false
						},
						z: 10,
						type: 'category',
						nameRotate: 10
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						name: '案件数',
						type: 'bar',
						data: num,
						animationDelay: function(idx){
							return idx * 10;
						}
					}],
					animationEasing: 'elasticOut',
				    animationDelayUpdate: function (idx) {
				        return idx * 5;
				    }
				}
				// 使用刚指定的配置项和数据显示图表。
				carAreaChart.setOption(options);
			}
		}
	})
	$("#CarAreaCountTable").bootstrapTable('refresh', {url: Feng.ctxPath + "/carAreaCount/getOverRun?fctime=" + fctime + "&level=" + level + "&stationid=" + sitecode});
	
	
})


$(function(){
	var defaultColunms = CarAreaCount.initColumn();
    var table = new BSTable(CarAreaCount.id, "/carAreaCount/getOverRun", defaultColunms);
    table.setPaginationType("client");
    CarAreaCount.table = table.init();
    
    $('#'+CarAreaCount.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});

    Ning.Tree.createAnjianSelect(document.getElementById("site"), function (node) {

		if (node.type != "dept") {
            $('#sitecode').text(node.roadcode)
			$('#site').val(node.name)
		} else {
            // console.log(node);
            $('#site').val('')
            $('#sitecode').text('')
		}
    });
});

layui.use('laydate', function(){
	var laydate = layui.laydate;
  	laydate.render({ 
  	  elem: '#casetime'
  	  ,type: 'datetime'
  	  ,range: '~' //或 range: '~' 来自定义分割字符
  	  ,max: ''+new Date()
  	});
});

