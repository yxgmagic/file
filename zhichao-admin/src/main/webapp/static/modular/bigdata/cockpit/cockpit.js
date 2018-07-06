$(window).resize(function(){
    $(".row").css("height", ($(window).height()-42)+"px");
});

$(function () {
    $(".row").css("height", ($(window).height()-42)+"px");
});

var setFn = setTimeout(function () {
    setTime();
}, 2000);

function setTime() {
    top.layer.open({
        title: false,
        type: 2,
        content: Feng.ctxPath + '/cockpit/fly',
        area: ['100%', '100%'],
        end: function () {
            $('.cockpit-box img')[0].src = '';
            $('.cockpit-box div')[0].innerHTML = '<div class="fly-box">加载驾驶舱模式</div>';
            $('.fly-box').bind('click', function () {
                setTime()
            });
        }
    });
}



