layui.use(['laydate', 'jquery'], function() {
    var $ = layui.jquery;
    var laydate = layui.laydate;

    var ajaxData = {}

    //绑定搜索按钮点击事件
    $("#searchBtn").click(function() {
        //将一些基本的查询数据填写到请求数据中
        ajaxData.deptId = $("#deptId").val();
        ajaxData.dateType = $("#idDateType").val();
        ajaxData.thisDateString = $("#idDateSelect").val();
        if(ajaxData.deptId == "" || ajaxData.dateType == "" || ajaxData.dateString == "") { //检查前面的请求数据都填写好了么
            layer.msg("请将参数填写完整");
            return;
        }
        /*
        这里是要把选中的时间往前推一年,拿到前一年的时间,然后传到后台,好生成同比数据
         */
        var myDate = new Date(ajaxData.thisDateString);//选择的时间
        var temp;
        temp = myDate.getFullYear()- 1;
        //前一年的时间放进请求数据
        ajaxData.dateString = temp;

        //请求数据
        $.ajax({
            type: 'GET',
            url: Feng.ctxPath + "/carvolumecount/getChartData",
            data: ajaxData,
            success: function(data) {
                if(data.code == 200) {
                    //成功之后对数据进行处理,方便后面的表格展示
                    dealData(data);
                } else {
                    layer.msg(data.msg);
                }

            },
            error: function() {
                Feng.error("接口请求出错");
            }
        });
    });

    /**
     * 处理传过来的数据,并将其展示到页面
     * @param data
     */
    function dealData(data) {
        initCharts();
        //判断日期类型是否为天,如果是则只显示那天的流量
        if(data.data.dateType == "date") {
            $(".dayFlowBox").show();
            $(".flowBox").hide();
            $(".axleBox").hide();
            $(".areaBox").hide();
        }else {
            $(".dayFlowBox").hide();
            $(".flowBox").show();
            $(".axleBox").show();
            $(".areaBox").show();
        }

        //把选中的时间和前一年的数据传进去,显示图表
        setDataToFlowChart(data.data.dateType, data.data.thatFlowData,data.data.thisFlowData);
        setDataToAxleChart(data.data.dateType, data.data.thatAxleData,data.data.thisAxleData);
        setDataToAreaChart(data.data.dateType, data.data.thatAreaData,data.data.thisAreaData);
    }

    /**
     * 数组是否存在该元素,存在则返回该元素的下标（优化二分查找？）
     * @param arr
     * @param obj
     * @returns {number}
     */
    function contains(arr, obj) {
        var i = arr.length;
        while (i--) {
            if (arr[i] === obj) {
                return i;
            }
        }
        return -1;
    }

    //将要显示的数据按X轴排序
    function mySort(seriesArr) {

        //将要显示的数据按X轴排序
        var length = 1;
        var tempArr = [];
        var tempIndex = 0;
        var tempValue = 0;
        var tempSwop = [];

        //选择排序,魔改版
        for(var z = 0; z < seriesArr.length; z++) {
            tempArr = seriesArr[z];
            length = tempArr.length;
            if(tempArr.length < 1) {
                continue;
            }
            for(var i = 0; i < length; i++) {
                tempIndex = i;
                for(var j = i; j < length; j++) {
                    tempValue = tempArr[tempIndex][0];
                    if(tempValue > tempArr[j][0]) {
                        tempIndex = j;
                    }
                }
                if(tempIndex != i) {
                    tempSwop = tempArr[tempIndex];
                    tempArr[tempIndex] = tempArr[i];
                    tempArr[i] = tempSwop;
                }
            }
        }
    }



    /**
     * 将车籍信息展示到页面
     * @param dateType
     * @param thatAreaData
     * @param thisAreaData
     */
    function setDataToAreaChart(dateType, thatAreaData,thisAreaData) {
        if(dateType == "date") {        //当时间类型为date,直接返回
            return;
        }
        if(thatAreaData.length == 0 && thisAreaData.length == 0) {   //当所有数据为空的时候,直接返回
            return;
        }
        var seriesArr = [];             //储存几个用于显示的数据
        for(var i = 0; i < 4; i++) {    //初始化四个记录
            seriesArr[i] = []
        }
        var xAxisArr = [];              //用于存储图表的X轴
        var thisYear = /^\d{4}/g.exec(ajaxData.thisDateString)[0];  //把今年的年份取出来
        var thatYear = /^\d{4}/g.exec(ajaxData.dateString)[0];      //把选择的那年的年份取出来

        //先把所有的区域拿出来,作为X轴坐标
        for(var i in thatAreaData) {
            var temp = thatAreaData[i];
            if(typeof temp.areaName1 == 'undefined') {
                continue;
            }
            xAxisArr.push();
        }
        for(var i in thisAreaData) {
            var temp = thisAreaData[i];
            if(typeof temp.areaName1 == 'undefined') {
                continue;
            }
            var tempArea = temp.areaName1;
            if(contains(xAxisArr, tempArea) == -1) {
                xAxisArr.push(temp.areaName1);
            }
        }

        //开始往seriesArr添加数据
        var thatLength = thatAreaData.length
        if(thatLength > 0) {
            for(var i = 0; i < thatLength; i++) {
                var temp = thatAreaData[i];
                var tempArea = temp.areaName1;
                var index = contains(xAxisArr, tempArea);
                seriesArr[0].push([index, temp.countFlow]);
                seriesArr[2].push([index, temp.countOver]);
            }
        }
        var thislength = thisAreaData.length;
        if(thislength > 0) {
            for(var i = 0; i < thislength; i++) {
                var temp = thisAreaData[i];
                var tempArea = temp.areaName1;
                var index = contains(xAxisArr, tempArea);
                seriesArr[1].push([index, temp.countFlow]);
                seriesArr[3].push([index, temp.countOver]);
            }
        }
        mySort(seriesArr);

        //清空areaBox下的元素,以免后面报错
        $(".areaBox").empty();
        //给flowBox下面添加用于展示图表的元素
        $(".areaBox").append($('<div id="areaChart" style="height: 400px"></div>'));
        //设置元素显示
        $(".areaBox").show();
        //设置几个数据头标识
        var titleArr = [thatYear+'年车流量',thisYear+'年车流量',thatYear+'年超限数量',
            thisYear+'年超限数量'];
        //最后将所有数据添加到图表中
        setEchart(document.getElementById("areaChart"), ajaxData.dateString + "与" + ajaxData.thisDateString + "车籍分布图", titleArr, xAxisArr, seriesArr);



    }


    /**
     * 将车轴统计信息展示到数据图表
     * @param dateType
     * @param thatAxleData
     * @param thisAxleData
     */
    function setDataToAxleChart(dateType, thatAxleData,thisAxleData) {
        if(dateType == "date") {        //当时间类型为date,直接返回
            return;
        }
        if(thatAxleData.length == 0 && thisAxleData.length == 0) {   //当所有数据为空的时候,直接返回
            return;
        }
        var seriesArr = [];             //储存几个用于显示的数据
        for(var i = 0; i < 4; i++) {    //初始化四个记录
            seriesArr[i] = []
        }
        var xAxisArr = [];              //用于存储图表的X轴
        var thisYear = /^\d{4}/g.exec(ajaxData.thisDateString)[0];  //把今年的年份取出来
        var thatYear = /^\d{4}/g.exec(ajaxData.dateString)[0];      //把选择的那年的年份取出来
        xAxisArr = ['2轴','3轴','4轴','5轴','6轴','7轴','8轴'];   //设置X轴坐标

        //开始往seriesArr添加数据
        if(thatAxleData.length > 0) {
            for(var i in thatAxleData) {
                var temp = thatAxleData[i];
                seriesArr[0].push([temp.axlesum - 2,temp.countFlow]);
                seriesArr[2].push([temp.axlesum - 2,temp.countOver]);
            }
        }
        if(thisAxleData.length > 0) {
            for(var i in thisAxleData) {
                var temp = thisAxleData[i];
                seriesArr[1].push([temp.axlesum - 2, temp.countFlow]);
                seriesArr[3].push([temp.axlesum - 2, temp.countOver]);
            }
        }

        //mySort(seriesArr);

        //清空axleBox下的元素,以免后面报错
        $(".axleBox").empty();
        //给flowBox下面添加用于展示图表的元素
        $(".axleBox").append($('<div id="axleChart" style="height: 400px"></div>'));
        //设置元素显示
        $(".axleBox").show();
        //设置几个数据头标识
        var titleArr = [thatYear+'年车型数量',thisYear+'年车型数量',thatYear+'年超限数量',
            thisYear+'年超限数量'];
        //最后将所有数据添加到图表中
        setEchart(document.getElementById("axleChart"), ajaxData.dateString + "与" + ajaxData.thisDateString + "车型分析图", titleArr, xAxisArr, seriesArr);


    }

    /**
     * 将车流量数据展示到车流量数据图表
     * @param thatFlowData 那年的数据
     * @param thisFlowData 今年的数据
     */
    function setDataToFlowChart(dateType, thatFlowData,thisFlowData) {
        if(thatFlowData.length == 0 && thisFlowData.length == 0) {   //当所有数据为空的时候,直接返回
            return;
        }

        var seriesArr = [];             //储存几个用于显示的数据
        for(var i = 0; i < 4; i++) {    //初始化四个记录
            seriesArr[i] = []
        }
        var xAxisArr = [];              //用于存储图表的X轴

        var thisYear = /^\d{4}/g.exec(ajaxData.thisDateString)[0];  //把今年的年份取出来
        var thatYear = /^\d{4}/g.exec(ajaxData.dateString)[0];      //把选择的那年的年份取出来


        if(dateType == "date") {

            if(thatFlowData.length > 0) {
                for(var i in thatFlowData) {
                    var temp = thatFlowData[i];
                    seriesArr[0].push([temp.hour1, temp.countFlow]);
                    seriesArr[1].push([temp.hour1, temp.countOver]);
                }
            }


            //清空flowBox下的元素,以免后面报错
            $(".dayFlowBox").empty();
            //给flowBox下面添加用于展示图表的元素
            $(".dayFlowBox").append($('<div id="dayFlowChart" style="height: 400px"></div>'));
            //设置元素显示
            $(".dayFlowBox").show();

            setDayEchart(document.getElementById("dayFlowChart"), ajaxData.dateString + "车流量与超限数量分析图", seriesArr);
            return;
        }

        if(dateType == "year") {
            //当选择的时间类型是year时,设置X轴为十二个月份
            xAxisArr = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
            //如果选择的那年的有数据,则开始往seriesArr添加数据
            if(thatFlowData.length > 0) {
                for(var i in thatFlowData) {
                    var temp = thatFlowData[i];
                    seriesArr[0].push([temp.month1 - 1,temp.countFlow]);
                    seriesArr[2].push([temp.month1 - 1,temp.countOver]);
                }

            }

            if(thisFlowData.length > 0) {
                for(var i in thisFlowData) {
                    var temp = thisFlowData[i];
                    seriesArr[1].push([temp.month1 - 1, temp.countFlow]);
                    seriesArr[3].push([temp.month1 - 1, temp.countOver]);

                }

            }
        }

        if(dateType == "quarter") {     //季度
            xAxisArr = ['第一季度', '第二季度', '第三季度', '第四季度'];

            if(thatFlowData.length > 0) {
                for(var i in thatFlowData) {
                    var temp = thatFlowData[i];
                    seriesArr[0].push([temp.quarter1 - 1,temp.countFlow]);
                    seriesArr[2].push([temp.quarter1 - 1,temp.countOver]);
                }

            }

            if(thisFlowData.length > 0) {
                for(var i in thisFlowData) {
                    var temp = thisFlowData[i];
                    seriesArr[1].push([temp.quarter1 - 1, temp.countFlow]);
                    seriesArr[3].push([temp.quarter1 - 1, temp.countOver]);

                }

            }
        }

        if(dateType == "month") {     //月份
            //新建一个日期对象,用于后面创立X轴坐标
            var myDate = new Date();
            //将选中的时间分割出来
            var tempArr = ajaxData.dateString.split("-");
            //给myDate设置时间
            myDate.setFullYear(tempArr[0]);
            myDate.setMonth(tempArr[1]);
            //获取这个月最大的天数
            myDate.setDate(0);
            var maxDay = myDate.getDate();
            xAxisArr = [];
            for(var i = 0; i < maxDay; i++) {
                xAxisArr.push((i + 1) + "");
            }

            if(thatFlowData.length > 0) {
                for(var i in thatFlowData) {
                    var temp = thatFlowData[i];
                    seriesArr[0].push([temp.day1 - 1,temp.countFlow]);
                    seriesArr[2].push([temp.day1 - 1,temp.countOver]);
                }
            }

            if(thisFlowData.length > 0) {
                for(var i in thisFlowData) {
                    var temp = thisFlowData[i];
                    seriesArr[1].push([temp.day1 - 1, temp.countFlow]);
                    seriesArr[3].push([temp.day1 - 1, temp.countOver]);
                }
            }
        }

        mySort(seriesArr);

        //清空flowBox下的元素,以免后面报错
        $(".flowBox").empty();
        //给flowBox下面添加用于展示图表的元素
        $(".flowBox").append($('<div id="flowChart" style="height: 400px"></div>'));
        //设置元素显示
        $(".flowBox").show();
        //设置几个数据头标识
        var titleArr = [thatYear+'年车流量',thisYear+'年车流量',thatYear+'年超限数量',
            thisYear+'年超限数量'];
        //最后将所有数据添加到图表中
        setEchart(document.getElementById("flowChart"), ajaxData.dateString + "与" + ajaxData.thisDateString + "车流量与超限数量分析图", titleArr, xAxisArr, seriesArr);
    }



    /*
     * 给站点选择框绑定点击事件
     * 当选择之后,在页面上显示选中的部门,
     * 并在隐藏域中写入当前部门的id
     */
    $("#stationidInput").click(function() {
        Ning.Tree.createFixedsiteSelect(this,function(node) {
            $("#deptId").val(node.id);
            if(1 == 1 ) {
                $("#stationidInput").val(node.name);
            }
        })
    });

    /**
     * 设置时间选择器的类型
     * @param ele
     * @param dateType
     */
    function setDateType(ele, dateType) {
        laydate.render({
            elem: ele
            ,type: dateType
            ,max: new Date().getTime()
        });
    }

    /**
     * 根据时间类型下拉框改变右边的时间选择框
     */
    $("#idDateType").change(function() {
        var optionValue = $("#idDateType").val();
        optionValue = (optionValue === "quarter")? 'year': optionValue;
        var selectBox = $("#selectBox");
        var idDateSelect = $("<input id='idDateSelect' class='form-control text-center' />")
        selectBox.empty().append(idDateSelect);
        if(optionValue === "0") {
            idDateSelect.css("disabled", "disabled");
        } else {
            setDateType("#idDateSelect", optionValue);
        }

    });

    /**
     * 根据传进来的数组求其平均数,
     * 并返回一个所有值为平均数,
     * 长度与原数组相同的数组,
     * @param arr
     */
    function getAvgArr(arr) {
        var resultArr = [];
        var tempAvg = 0;
        var sum = 0;
        var length = arr.length;
        for(var i = 0; i < length; i++) {
            sum += arr[i];
        }
        tempAvg = parseInt(sum / length);
        for(var i = 0; i < length; i++) {
            resultArr[i] = tempAvg;
        }
        return resultArr;
    }

    /**
     * 根据传进来的数组求其平均数
     * @param arr
     * @returns {number}
     */
    function getArrAvg(arr) {
        var tempAvg = 0;
        var sum = 0;
        var length = arr.length;
        for(var i = 0; i < length; i++) {
            sum += parseInt(arr[i]);
        }
        tempAvg = parseInt(sum / length);
        return tempAvg;
    }


    /**
     * 设置图表()
     * @param ele 用于显示的元素
     * @param title 图表的名字
     * @param titleArr 图表的每项的名字
     * @param xAxisArr 横坐标名称
     * @param seriesArr 数据数组
     */
    function setEchart(ele, title, titleArr, xAxisArr, seriesArr) {
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
                    markLine : {
                        data : [
                            {type : 'average'}
                        ]
                    }
                },
                {
                    name:titleArr[2],
                    type:'line',
                    data:seriesArr[2]
                },
                {
                    name:titleArr[3],
                    type:'line',
                    data:seriesArr[3],
                    markLine : {
                        data : [
                            {type : 'average'}
                        ]
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        tempChart.setOption(option);
    }

    /**
     * 设置每日车流量图表
     * @param ele 用于展示的元素
     * @param seriesArr 数据数组
     */
    function setDayEchart(ele, title1, seriesArr) {
        // 初始化 echarts 实例
        var tempChart = echarts.init(ele);

        //设置基本配置项
        var option = {
            title: {
                text: title1
            },
            legend: {
                data: ['车流量', '超限车流量']
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    saveAsImage: {show: true}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['00:00', '01:00', '02:00', '03:00', '04:00','05:00', '06:00',
                    '07:00', '08:00', '09:00', '10:00', '11:00', '12:00',
                    '13:00', '14:00', '15:00', '16:00', '17:00', '18:00',
                    '19:00', '20:00', '21:00', '22:00', '23:00', '24:00']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name:'车流量',
                data: seriesArr[0],
                type: 'line',
                areaStyle: {
                    color: '#66c3d5'
                },
                lineStyle: {
                    color: '#66c3d5'
                },
                itemStyle: {
                    color: '#66c3d5'
                },
                markLine : {
                    data : [
                        {type : 'average'}
                    ]
                }
            },{
                name: '超限车流量',
                type: 'line',
                data: seriesArr[1],
                markLine : {
                    data : [
                        {type : 'average'}
                    ]
                }
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        tempChart.setOption(option);

    }

    //这里是默认初始化表格
    function initCharts() {



        //清空图表
        $(".dayFlowBox").empty();
        $(".flowBox").empty();
        $(".axleBox").empty();
        $(".areaBox").empty();

        //插入元素
        $(".dayFlowBox").append($('<div id="dayFlowChart" style="height: 400px"></div>'));
        $(".flowBox").append($('<div id="flowChart" style="height: 400px"></div>'));
        $(".axleBox").append($('<div id="axleChart" style="height: 400px"></div>'));
        $(".areaBox").append($('<div id="areaChart" style="height: 400px"></div>'));

        //显示图表
        $(".dayFlowBox").show();
        $(".flowBox").show();
        $(".axleBox").show();
        $(".areaBox").show();

        var seriesArr3 = [
            // [820, 932, 901, 934, 1290, 1330, 1320, 655, 825, 911, 1562, 656, 555, 1666,232],
            // [54, 15, 65, 95, 31, 45, 56,65,122,156,326,151,122,56,95]
        ];
        setDayEchart(document.getElementById("dayFlowChart"), "日车流量信息-暂无数据", seriesArr3);

        var titleArr = ['车流量1','车流量2','超限数量1','超限数量2'];
        var xAxisArr = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
        var seriesArr = [
            // [820, 932, 901, 934, 1290, 1330, 1320, 655, 825, 911, 1562, 656],
            // [780, 432, 801, 734, 990, 830, 920, 755, 625, 511, 562, 1656],
            // [520, 532, 501, 534, 290, 330, 320, 455, 425, 411, 562, 356],
            // [420, 232, 201, 434, 490, 330, 320, 415, 425, 151, 462, 256]
        ];
        setEchart(document.getElementById("flowChart"), "车流量与超限数量分析图-暂无数据", titleArr, xAxisArr, seriesArr);

        var titleArr1 = ['车型数量1','车型数量2','超限车型数量1','超限车型数量2'];
        var xAxisArr1 = ['2轴','3轴','4轴','5轴','6轴','7轴','8轴'];
        var seriesArr1 = [
            // [300,500,600,820,800,592,564],
            // [390,850,860,560,696,665,664],
            // [150,160,390,220,560,262,364],
            // [100,200,150,260,120,552,264]
        ];
        setEchart(document.getElementById("axleChart"), "车型分析图-暂无数据", titleArr1, xAxisArr1, seriesArr1);

        var titleArr2 = ['车流量1','车流量2','超限数量1','超限数量2'];
        var xAxisArr2 = ['某省1','某省2','某省3','某省4','某省5','某省6','某省7'];
        var seriesArr2 = [
            // [300,500,600,820,800,592,564],
            // [390,850,860,560,696,665,664],
            // [150,160,390,220,560,262,364],
            // [100,200,150,260,120,552,264]
        ];
        setEchart(document.getElementById("areaChart"), "车籍分布图-暂无数据", titleArr2, xAxisArr2, seriesArr2);

    }
    $(function() {

        initCharts();
    });
});

