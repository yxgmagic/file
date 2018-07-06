 /**
 * 员工管理初始化
 */
var Emp = {
    id: "EmpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Emp.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {
        	field : 'Number',
        	title : '序号',
        	formatter : function (value, row, index){
        		return index + 1 ;
        	}
        },
        {title: '员工编号', field: 'empno', visible: true, align: 'center', valign: 'middle'},
        {title: '员工姓名', field: 'ename', visible: true, align: 'center', valign: 'middle'},
        {title: '工作职位', field: 'job', visible: true, align: 'center', valign: 'middle'},
        {title: '入职时间', field: 'hiredate', visible: true, align: 'center', valign: 'middle',
        	formatter : function (value, row, index){
        		return new Date(value).Format('yyyy-MM-dd');
        	}
        },
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
        	formatter : function (value,row,index){
        		if(value == 1){
        			return "启用";
        		}else{
        			return "禁用";
        		}
        	}
        }
    ];
};

/**
 * 检查是否选中
 */
Emp.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Emp.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加员工信息
 */
Emp.openAddEmp = function () {
    var index = top.layer.open({
        type: 2,
        title: '添加员工信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/employee/emp_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看员工信息详情
 */
Emp.openEmpDetail = function () {
	
	console.log(JSON.stringify(Emp.seItem));
    if (this.check()) {
        var index = top.layer.open({
            type: 2,
            title: '员工信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/employee/emp_update/' + Emp.seItem.empno
        });
        console.log(index);
        this.layerIndex = index;
    }
};

/**
 * 删除员工信息
 */
Emp.delete = function () {
    if (this.check()) {
     	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/employee/delete", function (data) {
	            Feng.success("删除成功!");
	            Emp.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("empno",Emp.seItem.empno);
	        ajax.start();
        }
        Feng.confirm("确认要删除吗?", operation);
    }
};

/**
 * 查询员工信息列表
 */
Emp.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Emp.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Emp.initColumn();
    var table = new BSTable(Emp.id, "/employee/list", defaultColunms);
    table.setPaginationType("client");
    Emp.table = table.init();
    
    //隐藏选择列
    $('#'+Emp.id).on('all.bs.table', function (){
    		$(".bs-checkbox").attr('style',"display:none");
		}
    );
});
