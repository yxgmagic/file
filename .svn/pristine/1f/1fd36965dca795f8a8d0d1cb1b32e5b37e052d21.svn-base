// 初始化 echarts 实例
var carAreaChart = echarts.init(document.getElementById('carArea'));

//表格初始化
var AreaCaseCount = {
    id: "AreaCaseCountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
AreaCaseCount.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '排名', field: 'indexid', visible: true, align: 'center', valign: 'middle'
        	,formatter: function (value, row, index) {  
            return index+1;  
        }},
        {title: '站点', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
        {title: '案件数', field: 'Number', visible: true, align: 'center', valign: 'middle'}
            
    ];
};

AreaCaseCount.search = function(){
	var fctime = $("#casetime").val();
	var level = $("#level").val();

	if (fctime == '' || fctime == null) {
		fctime = Yi.components.getNowMonthArea();
	}

    console.log(Yi.config.siteCode)
	$.ajax({
			method: "GET",
			url: Feng.ctxPath + "/areaCaseCount/areaOverRun",
			data: {
				'fctime': fctime,
				'level': level,
                'stationids': Yi.config.siteCode
			},
			success: function(result){
				console.log(result);
				var xData = [];
				var yData = [];
				if (result){
					for (var i=0; i<result.length; i++) {
						xData.push(result[i].sitename);
						yData.push(result[i].Number);
					}
					
					// 指定图表的配置项和数据
					var options = {
						title: {
							text: "违法车辆所属区域统计"
						},
						tooltip: {},
						legend: {
							data: ["案件数"]
						},
						toolbox: {
							feature: {
								saveAsImage: {
									pixelRatio: 2
								}
							}
						},
						xAxis: {
							data: xData,
							silent: false,
							splitLine: {
								show: false
							}
						},
						yAxis: {},
						series: [{
							name: '案件数',
							type: 'bar',
							data: yData,
							animationDelay: function(idx){
								return idx * 10;
							}
						}],
						animationEasing: 'elasticOut',
					    animationDelayUpdate: function (idx) {
					        return idx * 5;
					    }
					};

					// 使用刚指定的配置项和数据显示图表。
					carAreaChart.setOption(options);
				}
			}
			
		})

	$("#AreaCaseCountTable").bootstrapTable('refresh', {url: Feng.ctxPath + "/areaCaseCount/areaOverRun?fctime=" + fctime + "&level=" + level + '&stationids[]=' + Yi.config.siteCode});
	
}

$(function(){
    var defaultColunms = AreaCaseCount.initColumn();
    var table = new BSTable(AreaCaseCount.id, "/areaCaseCount/areaOverRun?init=init&fctime=''&level=''&stationids[]=[]", defaultColunms);
    table.setPaginationType("client");
    AreaCaseCount.table = table.init();
    
    $('#'+AreaCaseCount.id).on('all.bs.table', function (){
		$(".bs-checkbox").attr('style',"display:none");
	});

    Ning.Tree.createAnjianSelect(document.getElementById("site"), function (node) {
		Yi.config.siteCode = [];
        $('#site').val(node.name);
        if (node.type != 'dept'){
            Yi.config.siteCode.push(node.sitecode);
		} else {
            forEachNode(node);
		}
    });

});

function forEachNode (node) {
    node.children.forEach(item => {
        if (Yi.components.judgeNodeChildren(item)) {
            forEachNode(item)
        } else {
        	if (item.sitecode !== undefined) {
                Yi.config.siteCode.push(item.name)
			}
        }
    })
}

layui.use('laydate', function(){
	var laydate = layui.laydate;
  	laydate.render({ 
  	  elem: '#casetime'
  	  ,type: 'datetime'
  	  ,range: '~' //或 range: '~' 来自定义分割字符
  	  ,max: ''+new Date()
  	});
});





