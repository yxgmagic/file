// this is a loading
var mapIndex = layer.open({type:3});
// init pages
$(function(){
    $("#gdMap").css("height", ($(window).height()-116) + 'px');

    var ln = $("#lng").val() || '';
    var la = $("#lat").val() || '';
    var st = $("#stype").val() || '';


    if (ln && la && st) {
        gisFeng.setRightTopBoxInfo(ln, la, st, null)
        console.log($(".site" + st))
        $(".site" + st).click();
    }
});
$(window).resize(function(){
    $("#gdMap").css("height", ($(window).height()-116) + 'px');
});
// init map
var amap = new AMap.Map('gdMap', { zoom: gisFeng.zoom, center: gisFeng.center, zooms: [3, 18], mapStyle: gisFeng.mapStyle, complete: mapSuccess() });

function mapSuccess () {
  setTimeout(function () {
     layer.close(mapIndex);
  },2000);
};



AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.OverView'], function(){
    amap.addControl(new AMap.ToolBar());
    amap.addControl(new AMap.Scale({
        showZoomBar: true,
        showControlButton: true
    }));
});

try{
    AMapUI.loadUI(['geo/DistrictExplorer'], function (DistrictExplorer) {
        districtExplorer(DistrictExplorer);
    });
} catch (e) {
    Feng.GetFrame('/gismonitor').frameElement.contentWindow.location.reload(true);
}

// Draw administrative area

function districtExplorer(DistrictExplorer) {
    var districtExplorer = new DistrictExplorer({ map: amap });
    var adcode = gisFeng.adcode;
    districtExplorer.loadAreaNode(adcode, function (error, areaNode) {
        if (error){
            console.log(error);
            return;
        }
        renderAreaNode(districtExplorer, areaNode);
    });
    var countryCode = [100000];
    districtExplorer.loadMultiAreaNodes(countryCode, function(error, areaNodes){
        var countryNode = areaNodes[0];
        var cityNodes = areaNodes.slice(1);
        var path = [];
        path.push(getLongestRing(countryNode.getParentFeature()));
        var isArea = true;
        if (isArea) {
            path.push.apply(path, getAllRings(countryNode.getSubFeatureByAdcode(430000)));
        } else {
            path.push.apply(path, getAllRings(cityNodes[i].getParentFeature()));
        }

        var polygon = new AMap.Polygon({
            bubble: true,
            lineJoin: 'round',
            strokeColor: 'red',
            strokeOpacity: 1,
            strokeWeight: 1,
            fillColor: 'black',
            fillOpacity: 0.95,
            map: amap,
            path: path
        })
    })
}
function renderAreaNode(districtExplorer, areaNode){

    districtExplorer.clearFeaturePolygons();

    var colors = gisFeng.colors;

    districtExplorer.renderSubFeatures(areaNode, function (feature, i) {

        var fillColor = colors[i % colors.length];
        var strokeColor = colors[colors.length - 1 - i % colors.length];

        return {
            cursor: 'default',
            bubble: true,
            strokeColor: strokeColor, //线颜色
            strokeOpacity: 1, //线透明度
            strokeWeight: 1, //线宽
            fillColor: fillColor, //填充色
            fillOpacity: 0.35 //填充透明度
        };

    });

    districtExplorer.renderParentFeature(areaNode, {
        cursor: 'default',
        bubble: true,
        strokeColor: 'black', //线颜色
        fillColor: null,
        strokeWeight: 3 //线宽
    });


}
function getAllRings(feature) {

    var coords = feature.geometry.coordinates,
        rings = [];

    for (var i = 0, len = coords.length; i < len; i++) {
        rings.push(coords[i][0]);
    }

    return rings;
}
function getLongestRing(feature) {
    var rings = getAllRings(feature);

    rings.sort(function(a, b) {
        return b.length - a.length;
    });

    return rings[0];
}

window.aMap = amap;

function markInfoWindow(e) {
    gisFeng.markerInfoWindow(e);
}

// 点击固定治超站
$(".site1").bind("click", function(){
    var _rgb = "" + $(".site1").css("background-color");
    if (_rgb == "rgb(228, 228, 228)"){
        $(".site1").css({
            "background-color": "#1ab394",
            "color": "#ffffff"
        });
        gisFeng.setMarker('selfixedsite');
    } else {
        $(".site1").css({
            "background-color": "#e4e4e4",
            "color": "#ffffff"
        });
        if ($("#sitetype")[0] && $("#sitetype")[0].innerText == '1') {
            $("#mapDiv").css("display", "none");
        }
        gisFeng.removeMarker(gisFeng.markerFixed, 1);
    }


});
// 点击预检站
$(".site2").bind("click", function(){
    var _rgb = "" + $(".site2").css("background-color");
    if (_rgb == "rgb(228, 228, 228)"){
        $(".site2").css({
            "background-color": "#1ab394",
            "color": "#ffffff"
        });
        gisFeng.setMarker('selpresite');
    } else {
        $(".site2").css({
            "background-color": "#e4e4e4",
            "color": "#fff"
        });
        if ($("#sitetype")[0] && $("#sitetype")[0].innerText == '2') {
            $("#mapDiv").css("display", "none");
        }
        gisFeng.removeMarker(gisFeng.markerPre, 2);
    }
});
// 点击高速公路出入口
$(".site3").bind('click', function () {
    var _rgb = "" + $(".site3").css("background-color");
    if (_rgb == "rgb(228, 228, 228)"){
        $(".site3").css({ "background-color": "#1ab394", "color": "#ffffff" });
        gisFeng.setMarker('selHsway');
    } else {
        $(".site3").css({ "background-color": "#e4e4e4", "color": "#fff" });
        if ($("#sitetype")[0] && $("#sitetype")[0].innerText == '3') {
            $("#mapDiv").css("display", "none");
        }
        gisFeng.removeMarker(gisFeng.markerHsway, 3);
    }
});
// 点击移动执法车
$(".site4").bind('click', function () {
    var _rgb = "" + $(".site4").css("background-color");
    if (_rgb == "rgb(228, 228, 228)"){
        $(".site4").css({ "background-color": "#1ab394", "color": "#ffffff" });
        gisFeng.setMarker('sellawCar');
    } else {
        $(".site4").css({ "background-color": "#e4e4e4", "color": "#fff" });
        if ($("#sitetype")[0] && $("#sitetype")[0].innerText == '4') {
            $("#mapDiv").css("display", "none");
        }
        gisFeng.removeMarker(gisFeng.markerLawCar, 4);
    }
});
// 点击移动单兵
$(".site5").bind('click', function () {
    var _rgb = "" + $(".site5").css("background-color");
    if (_rgb == "rgb(228, 228, 228)"){
        $(".site5").css({ "background-color": "#1ab394", "color": "#ffffff" });
        gisFeng.setMarker('sellawMan');
    } else {
        $(".site5").css({ "background-color": "#e4e4e4", "color": "#fff" });
        if ($("#sitetype")[0] && $("#sitetype")[0].innerText == '6') {
            $("#mapDiv").css("display", "none");
        }
        gisFeng.removeMarker(gisFeng.markerLawMan, 6);
    }
});
// 点击源头企业
$(".site6").bind('click', function () {
    var _rgb = "" + $(".site6").css("background-color");
    if (_rgb == "rgb(228, 228, 228)"){
        $(".site6").css({
            "background-color": "#1ab394",
            "color": "#ffffff"
        });
        gisFeng.setMarker('selCrop');
    } else {
        $(".site6").css({
            "background-color": "#e4e4e4",
            "color": "#fff"
        });
        if ($("#sitetype")[0] && $("#sitetype")[0].innerText == '5') {
            $("#mapDiv").css("display", "none");
        }
        gisFeng.removeMarker(gisFeng.markerCorp, 5);
    }
});

/**
 * v2 - 跳转站点数据管理
 */
$("#sitename").on("click", function(){

});

/**
 * v2 - 搜索功能
 */
var flag = false;
$(".seach-text").focus(function () {
    flag = true;
    $(".seach-text").bind("input propertychange", function () {
        var textlength = $(".seach-text").val().length;
        if(textlength == 0){
            $(".seach-item")[0].innerHTML = "";
        }
        seachInfo();
    })
});

function seachInfo() {
    if(flag){
        var sitename = $(".seach-text").val() || "";
        if(sitename != "" && sitename != null){
            $.ajax({
                method: "GET",
                data: { sitename: sitename },
                url: Feng.ctxPath + "/gismonitor/seach",
                success: function (result) {
                    // console.log(result);
                    var dataItem = "";
                    if(result.length > 0){
                        for(var i = 0; i < result.length; i++ ){
                            switch (result[i].sitetype) {
                                case '1':
                                    dataItem += "<li class='seachItem' ><img src="+Feng .ctxPath+"'/static/img/bin/fixedmarker_D.png'/><span class='siteitem'>"+result[i].sitename+"</span><span class='disableLng'>"+result[i].longitude+"</span><span class='disableLat'>"+result[i].latitude+"</span></li>";
                                    break;
                                case '2':
                                    dataItem += "<li class='seachItem' ><img src="+Feng .ctxPath+"'/static/img/bin/premarker_D.png'/><span class='siteitem'>"+result[i].sitename+"</span><span class='disableLng'>"+result[i].longitude+"</span><span class='disableLat'>"+result[i].latitude+"</span></li>";
                                    break;
                                case '3':
                                    dataItem += "<li class='seachItem' ><img src="+Feng .ctxPath+"'/static/img/bin/hswaymarker_D.png'/><span class='siteitem'>"+result[i].sitename+"</span><span class='disableLng'>"+result[i].longitude+"</span><span class='disableLat'>"+result[i].latitude+"</span></li>";
                                    break;
                                case '4':
                                    dataItem += "<li class='seachItem' ><img src="+Feng .ctxPath+"'/static/img/bin/LawCar_D.png'/><span class='siteitem'>"+result[i].sitename+"</span><span class='disableLng'>"+result[i].longitude+"</span><span class='disableLat'>"+result[i].latitude+"</span></li>";
                                    break;
                                case '5':
                                    dataItem += "<li class='seachItem' ><img src="+Feng .ctxPath+"'/static/img/bin/corpmarker_D.png'/><span class='siteitem'>"+result[i].sitename+"</span><span class='disableLng'>"+result[i].longitude+"</span><span class='disableLat'>"+result[i].latitude+"</span></li>";
                                    break;
                                case '6':
                                    dataItem += "<li class='seachItem' ><img src="+Feng .ctxPath+"'/static/img/bin/LawMan_D.png'/><span class='siteitem'>"+result[i].sitename+"</span><span class='disableLng'>"+result[i].longitude+"</span><span class='disableLat'>"+result[i].latitude+"</span></li>";
                                    break;
                            }
                        }
                        $(".seach-item")[0].innerHTML = dataItem;

                    }
                },
                error: function () {
                    seachInfo();
                }
            })
        }
    } else {
        flag = true;
        $(".seach-item")[0].innerHTML = "";
    }
}

/**
 * 监听键盘事件
 */
var num = 1;
$(document).keydown(function (event) {
    // if($(".seachItem").length > 0){
    // 向上箭头
    if (num > $(".seachItem").length){
        num = $(".seachItem").length;
    }
    // console.log(num);
    if(num <= $(".seachItem").length){
        if(event.keyCode == 38){

            $(".seach-item li:nth-child("+(num-1)+")").addClass("seachItemActive");
            $(".seach-item li:nth-child("+num+")").removeClass("seachItemActive");
        }
        // 向下箭头
        if(event.keyCode == 40){
            if(num>1){
                $(".seach-item li:nth-child("+(num-1)+")").removeClass("seachItemActive");
            }
            $(".seach-item li:nth-child("+num+")").addClass("seachItemActive");
            num = num + 1;
        }
        // 回车
        if(event.keyCode == 13){
            var text = $(".seach-text").val() || "";
            if(text != "" && text != null){
                if(text != "" && text != null){
                    $.ajax({
                        method: "GET",
                        data: { sitename: text },
                        url: Feng.ctxPath + "/gismonitor/seach",
                        success: function (result) {
                            // console.log(result);
                            if(result.length > 0){
                                gisFeng.seachMarkerInfoWindow(result[0].longitude, result[0].latitude);
                                flag = false;
                                seachInfo();
                            } else {
                                Feng.info("未查找到相关站点！")
                            }
                        },
                        error: function () {
                            seachInfo();
                        }
                    })
                }
            }

            if($(".seachItemActive").length > 0){
                $(".seach-text").val($(".seachItemActive")[0].innerText);
                $(".seach-item")[0].innerHTML = "";

                text = $(".seach-text").val() || "";
                if(text != "" && text != null){
                    $.ajax({
                        method: "GET",
                        data: { sitename: text },
                        url: Feng.ctxPath + "/gismonitor/seach",
                        success: function (result) {
                            // console.log(result);
                            if(result.length > 0){
                                gisFeng.seachMarkerInfoWindow(result[0].longitude, result[0].latitude);
                                flag = false;
                                seachInfo();
                            } else {
                                Feng.info("未查找到相关站点！")
                            }
                        },
                        error: function () {
                            seachInfo();
                        }
                    })
                }
            }
        }
    }

});

/**
 * 点击搜索键
 */
$(".menu-seach-btn").on("click", function () {
    var sitename = $(".seach-text").val() || "";
    if (sitename != "" && sitename != null){
        $.ajax({
            method: "GET",
            data: { sitename: sitename },
            url: Feng.ctxPath + "/gismonitor/seach",
            success: function (result) {
                // console.log(result);
                if(result.length > 0){
                    gisFeng.seachMarkerInfoWindow(result[0].longitude, result[0].latitude);
                    flag = false;
                    seachInfo();
                } else {
                    Feng.info("未查找到相关站点！")
                }
            }
        });
    }
});

/**
 * 点击搜索项
 */

$(".seach-item").on('click','.seachItem', function(){
    console.log($(this)[0].children);
    $(".seach-text").val($(this)[0].children[1].innerText);
    var lng = $(this)[0].children[2].innerText;
    var lat = $(this)[0].children[3].innerText;
    var text = $(".seach-text").val() || "";
    if(text != "" && text != null){
        if(text != "" && text != null){
            $.ajax({
                method: "GET",
                data: { sitename: text },
                url: Feng.ctxPath + "/gismonitor/seach",
                success: function (result) {
                    // console.log(result);
                    if(result.length > 0){
                        gisFeng.seachMarkerInfoWindow(lng, lat);
                        flag = false;
                        seachInfo();
                    } else {
                        Feng.info("未查找到相关站点！")
                    }
                },
                error: function () {
                    seachInfo();
                }
            })
        }
    }
});


function videoView(){
    Feng.showVideo("实时监控", 23, 0, 1, 0, '');
}

function toInfo() {
    var sitetype = $("#sitetype")[0].innerHTML || "";
    var sitecode = $("#sitecode")[0].innerHTML || "";
    // console.log(sitetype);
    if (sitetype == "" || sitetype == null) {
        return;
    } else{
        top.layer.open({
            title: false,
            content: '<div class="yiload">是否查看此站点的详细数据</div>'+
            '<a class="J_menuItem btn-primary yiloadbtn btn" id="toLcsInfo" onclick="toLcsInfo()" '+
            'name="tabMenuItem">确定<span style="display:none;" '+
            'class="sitecode">'+sitecode+'</span>'+
            '<p class="sitetype" style="display:none;">'+sitetype+'</p></a>',
            type: 1,
            skin: 'layui-layer-rim',
            area: ['200px', '92px'],
            shade : 0.3
        });
    }
}

