//表格初始化
var SiteYoyCount = {
    id: "SiteYoyCountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
SiteYoyCount.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'indexid', visible: true, align: 'center', valign: 'middle'
            ,formatter: function (value, row, index) {
            return index+1;
        }},
        {title: '站点', field: 'sitename', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号', field: 'vehicleid', visible: true, align: 'center', valign: 'middle'},
        {title: '立案时间', field: 'casetime', visible: true, align: 'center', valign: 'middle'},
        {title: '超限量', field: 'overlimited', visible: true, align: 'center', valign: 'middle'},
        {title: '罚款金额', field: 'punishamount', visible: true, align: 'center', valign: 'middle'},
        {title: '赔偿金额', field: 'damageamount', visible: true, align: 'center', valign: 'middle'}
    ];
};

$(function () {
    Yang.Tools.date_interval('casetime','datetime');
    Yang.Tools.date_interval('times','datetime');
    Yang.Tools.date_interval('fctime','datetime');
    Yang.Tools.date_interval('car-go-type', 'datetime');

    var defaultColunms = SiteYoyCount.initColumn();
    var table = new BSTable(SiteYoyCount.id, "/siteYoyAnalysis/listTable", defaultColunms);
    table.setPaginationType("client");
    SiteYoyCount.table = table.init({showColumns:false,showRefresh:false});

    $('#'+SiteYoyCount.id).on('all.bs.table', function (){
        $(".bs-checkbox").attr('style',"display:none");
    });

    axle.showLoading();
    $.ajax({
        method: "GET",
        url: Feng.ctxPath + "/siteYoyAnalysis/list",
        data: {
            casetime: Yi.components.getNowMonthArea()
        },
        success: function (result) {
            // axle
            var dataAxle = result[0];
            var _axlesum = [], _axlesumNum = [];
            for (var i in dataAxle) {
                _axlesum[i] = dataAxle[i].axlesum + "轴";
                _axlesumNum[i] = dataAxle[i].number;
            }
            // site
            var dataSite = result[1];
            var _siteName = [], _siteNameNum = [];
            for (var j in dataSite) {
                var _siteObj = {value: 0,name: ''}
                _siteName[j] = dataSite[j].sitename;
                _siteObj.name = dataSite[j].sitename;
                _siteObj.value = dataSite[j].number;
                _siteNameNum[j] = _siteObj;
            }
            axle.hideLoading();
            axle.setOption({
                xAxis : [
                    {
                        data : _axlesum
                    }
                ],
                series : [
                    {
                        data: _axlesumNum
                    }
                ]
            });
            site.setOption({
                legend: {
                    data: _siteName
                },
                series : [
                    {
                        data: _siteNameNum
                    }
                ]
            })
        }
    });
    $.ajax({
        method: "GET",
        url: Feng.ctxPath + '/siteYoyAnalysis/carnumber',
        data: {
            period: Yi.components.getNowMonthArea()
        },
        success: function (data) {
            if (data.length > 0 && data[0].number > 0) {
                var _data = {}, carData = [], numName = [];
                for (var i in data) {
                    _data['value'] = data[i].number;
                    _data['name'] = data[i].sitename;
                    carData.push(_data);
                    _data = {};
                    numName[i] = data[i].sitename;
                }
                carNumber.setOption({
                    legend: { data: numName },
                    series: [
                        { data: carData }
                    ]
                })
            }
        },
        error: function (err) {
            console.error(err);
        }
    });
    $.ajax({
        method: "GET",
        url: Feng.ctxPath + '/siteYoyAnalysis/selectoverrun',
        data: {
            fctime: Yi.components.getNowMonthArea()
        },
        success: function (data) {
            if(data.length <= 0){
                overRun.setOption({
                    title: { text: '超限量分析-无数据'},
                    legend: { data: [] },
                    series: [
                        { data: [] }
                    ]
                })
            } else {
                var _over = {}, overrun = [], name = [];
                for (var i in data){
                    _over['value'] = data[i].number;
                    _over['name'] = data[i].sitename;
                    overrun.push(_over);
                    _over = {};
                    name[i] = data[i].sitename;
                }
                overRun.setOption({
                    title: { text: '超限量分析'},
                    legend: { data: name },
                    series: [ { data: overrun } ]
                })
            }
        },
        error: function (err) {
            console.error(err);
        }
    });

    $.ajax({
        method: "GET",
        url: Feng.ctxPath + '/siteYoyAnalysis/selectcargotype',
        data: {
            fctime: Yi.components.getNowMonthArea()
        },
        success: function (data) {
            if(data !== null && data !== ''){
                var _cargo = {}, cargo = [], cargoname = [];
                for (var i in data){
                    _cargo['value'] = data[i].number;
                    _cargo['name'] = data[i].name;
                    cargo.push(_cargo);
                    _cargo = {};
                    cargoname[i] = data[i].name;
                }
                carGoType.setOption({
                    title: { text: '超限货物类型分析'},
                    legend: { data: cargoname },
                    series: [ { data: cargo } ]
                })
            } else {
                carGoType.setOption({
                    title: { text: '超限货物类型-时间段内无数据' },
                    legend: { data: [] },
                    series: [ { data: []} ]
                })
            }
        },
        error: function (err) {
            console.log(err);
        }
    })
});

$("#siteSearch").on("click", function () {
    var _time = $("#casetime").val() || "";
    var _level = $("#level").val() || "";
    var queryData = {};

    $.ajax({
        method: "GET",
        url: Feng.ctxPath + "/siteYoyAnalysis/list",
        data: {
            casetime: _time,
            violationlevel: _level
        },
        success: function (result) {
            var dataAxle = result[0];
            var _axlesum = [], _axlesumNum = [];
            for (var i in dataAxle) {
                _axlesum[i] = dataAxle[i].axlesum + "轴";
                _axlesumNum[i] = dataAxle[i].number;
            }
            // site
            var dataSite = result[1];
            var _siteName = [], _siteNameNum = [];
            for (var j in dataSite) {
                var _siteObj = {value: 0,name: ''};
                _siteName[j] = dataSite[j].sitename;
                _siteObj.name = dataSite[j].sitename;
                _siteObj.value = dataSite[j].number;
                _siteNameNum[j] = _siteObj;
            }
            console.log(_siteName);
            axle.hideLoading();
            axle.setOption({
                xAxis : [
                    {
                        data : _axlesum
                    }
                ],
                series : [
                    {
                        data: _axlesumNum
                    }
                ]
            });
            site.setOption({
                legend: {
                    data: _siteName
                },
                series : [
                    {
                        data: _siteNameNum
                    }
                ]
            })
        }
    });

    queryData['casetime'] = _time;
    queryData['violationlevel'] = _level;
    SiteYoyCount.table.refresh({query: queryData});
});

// 车流量
$("#timesSearch").on("click", function () {
    var period = $("#times").val() || "";
    if(period !== ''){
        $.ajax({
            method: "GET",
            url: Feng.ctxPath + '/siteYoyAnalysis/carnumber',
            data: {
                period: period
            },
            success: function (data) {
                if (data.length > 0 && data[0].number > 0) {
                    var _data = {}, carData = [], numName = [];
                    for (var i in data) {
                        _data['value'] = data[i].number;
                        _data['name'] = data[i].sitename;
                        carData.push(_data);
                        _data = {};
                        numName[i] = data[i].sitename;
                    }
                    carNumber.setOption({
                        legend: { data: numName },
                        series: [
                            { data: carData }
                        ]
                    })
                }
            },
            error: function (err) {
                console.error(err);
            }
        })
    }
});

// 超限量
$("#fctimeSearch").on("click", function () {
    var fctime = $("#fctime").val() || "";
    if (fctime !== ''){
        $.ajax({
            method: "GET",
            url: Feng.ctxPath + '/siteYoyAnalysis/selectoverrun',
            data: {
                fctime: fctime
            },
            success: function (data) {
                if(data.length <= 0){
                    overRun.setOption({
                        title: { text: '超限量分析-无数据'},
                        legend: { data: [] },
                        series: [
                            { data: [] }
                        ]
                    })
                } else {
                    var _over = {}, overrun = [], name = [];
                    for (var i in data){
                        _over['value'] = data[i].number;
                        _over['name'] = data[i].sitename;
                        overrun.push(_over);
                        _over = {};
                        name[i] = data[i].sitename;
                    }
                    overRun.setOption({
                        title: { text: '超限量分析'},
                        legend: { data: name },
                        series: [ { data: overrun } ]
                    })
                }
            },
            error: function (err) {
                console.error(err);
            }
        })
    }
});

// 超限货物类型
$("#carGoTypeSearch").on("click", function () {
    var cargotype = $("#car-go-type").val() || "";
    if (fctime !== ''){
        $.ajax({
            method: "GET",
            url: Feng.ctxPath + '/siteYoyAnalysis/selectcargotype',
            data: {
                fctime: cargotype
            },
            success: function (data) {
                if(data !== null && data !== ''){
                    var _cargo = {}, cargo = [], cargoname = [];
                    for (var i in data){
                        _cargo['value'] = data[i].number;
                        _cargo['name'] = data[i].name;
                        cargo.push(_cargo);
                        _cargo = {};
                        cargoname[i] = data[i].name;
                    }
                    carGoType.setOption({
                        title: { text: '超限货物类型分析'},
                        legend: { data: cargoname },
                        series: [ { data: cargo } ]
                    })
                } else {
                    carGoType.setOption({
                        title: { text: '超限货物类型-时间段内无数据' },
                        legend: { data: [] },
                        series: [ { data: []} ]
                    })
                }
            },
            error: function (err) {
                console.error(err);
            }
        })
    }
});

// 违法车辆车轴数分析
var axle = echarts.init(document.getElementById('axle'));
var axleOptions = {
    title: {
        text: '违法车辆车轴数分析',
        subtext: '',
        x: 'center'
    },
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {
            type : 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : [],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'违法数',
            type:'bar',
            barWidth: '60%',
            data:[]
        }
    ]
};
axle.setOption(axleOptions);

// 按站点分析
var site = echarts.init(document.getElementById('site'));
var siteOptions = {
    title : {
        text: '站点违章分析',
        subtext: '',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: []
    },
    series : [
        {
            name: '违章数量',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                { value:10.50, name:'长沙检测站' },
                { value:10.20, name:'株洲检测站' },
                { value:10.30, name:'衡阳检测站' },
                { value:10, name:'湘潭检测站' },
                { value:60, name:'宜春检测站' }
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
site.setOption(siteOptions);

// 车流量统计
var carNumber = echarts.init(document.getElementById('carNumber'));
var carNumberOptions = {
    title: { text: '车流量分析', subtext: '', x: 'center' },
    tooltip: { trigger: 'item', formatter: '{a} <br/>{b} : {c} ({d}%)' },
    legend: { orient: 'vertical', left: 'left', data: [] },
    series: [
        { name: '车流量', type: 'pie', radius: '55%', center: [ '50%', '60%' ], data: [
            { value: 20, name: '检测站一' },
            { value: 30, name: '检测站二' },
            { value: 40, name: '检测站三' },
            { value: 10, name: '检测站四' }
        ], itemStyle: { emphsais: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0,0,0,0.5)' } } }
    ]
};
carNumber.setOption(carNumberOptions);

// 超限量统计
var overRun = echarts.init(document.getElementById('overRun'));
var overRunOptions = {
    title: { text: '超限量分析', subtext: '', x: 'center' },
    tooltip: { trigger: 'item', formatter: '{a} <br/>{b} : {c} ({d}%)' },
    legend: { orient: 'vertical', left: 'left', data: [] },
    series: [
        { name: '超限量', type: 'pie', radius: '55%', center: [ '50%', '60%' ], data: [ ], itemStyle: { emphsais: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0,0,0,0.5)' } } }
    ]
};
overRun.setOption(overRunOptions);

// 超限货物类型统计
var carGoType = echarts.init(document.getElementById('carGotype'));
var carGoTypeOptions = {
    title: { text: '超限货物类型分析', subtext: '', x: 'center' },
    tooltip: { trigger: 'item', formatter: '{a} <br/>{b} : {c} ({d}%)' },
    legend: { orient: 'vertical', left: 'left', data: [] },
    series: [
        { name: '超限货物类型', type: 'pie', radius: '55%', center: [ '50%', '60%' ], data: [ ], itemStyle: { emphsais: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0,0,0,0.5)' } } }
    ]
};
carGoType.setOption(carGoTypeOptions);
