/**
 * 管理初始化
 */
var SeqGenerate = {
		id: "SeqGenerateTable",	//表格id
		seItem: null,		//选中的条目
		table: null,
		layerIndex: -1
};

/**
 * 初始化表格的列
 */
SeqGenerate.initColumn = function () {
	return [
		{field: 'selectItem', radio: true},

		{title: '编码序列类型', field: 'seqtype'  

		},
		{title: '规则顺序号', field: 'seqnum', visible: true, align: 'center', valign: 'middle'},
		{title: '规则列名', field: 'seqcol', visible: true, align: 'center', valign: 'middle'},
		{title: '长度', field: 'seqlen', visible: true, align: 'center', valign: 'middle'}
		];
};


/**
 * 合并单元格
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称
 * @param colspan   合并列
 * @param target    目标表格对象
 */
SeqGenerate.mergeCellst = function(data,fieldName,colspan,target){
	 
	//声明一个map计算相同属性值在data对象出现的次数和
	var sortMap = {};
	for(var i = 0 ; i < data.length ; i++){
		for(var prop in data[i]){
			if(prop == fieldName){
				var key = data[i][prop]
				if(sortMap.hasOwnProperty(key)){
					sortMap[key] = sortMap[key] * 1 + 1;
				} else {
					sortMap[key] = 1;
				}
				break;
			} 
		}
	}
	for(var prop in sortMap){
		console.log(prop,sortMap[prop])
	}
	var index = 0;
	for(var prop in sortMap){
		var count = sortMap[prop] * 1;
		$(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
		index += count;
	}
}



/**
 * 检查是否选中
 */
SeqGenerate.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	if(selected.length == 0){
		Feng.info("请先选中表格中的某一记录！");
		return false;
	}else{
		SeqGenerate.seItem = selected[0];
		return true;
	}
};

/**
 * 点击添加
 */
SeqGenerate.openAddSeqGenerate = function () {
	var index = top.layer.open({
		type: 2,
		title: '添加',
		area: ['800px', '420px'], //宽高
		fix: false, //不固定
		maxmin: true,
		content: Feng.ctxPath + '/seqGenerate/seqGenerate_add'
	});
	this.layerIndex = index;
};

/**
 * 打开查看详情
 */
SeqGenerate.openSeqGenerateDetail = function () {
	if (this.check()) {
		var index = top.layer.open({
			type: 2,
			title: '详情',
			area: ['800px', '420px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/seqGenerate/seqGenerate_update/' + SeqGenerate.seItem.id
		});
		this.layerIndex = index;
	}
};

/**
 * 删除
 */
SeqGenerate.delete = function () {
	if (this.check()) {
		var operation = function(){
			var ajax = new $ax(Feng.ctxPath + "/seqGenerate/delete", function (data) {
				Feng.success("删除成功!");
				SeqGenerate.table.refresh();
			}, function (data) {
				Feng.error("删除失败!" + data.responseJSON.message + "!");
			});
			ajax.set("seqGenerateId",SeqGenerate.seItem.id);
			ajax.start();
		}
		Feng.confirm("确认要删除吗?", operation);
	}
};

/**
 * 查询列表
 */
SeqGenerate.search = function () {
	var queryData = {};
	queryData['condition'] = $("#condition").val();
	SeqGenerate.table.refresh({query: queryData});
	 
};
 
$(function () {
	var defaultColunms = SeqGenerate.initColumn();
	var table = new BSTable(SeqGenerate.id, "/seqGenerate/list", defaultColunms);
	table.setPaginationType("client");
	SeqGenerate.table = table.init();
	
	 console.log(($('#SeqGenerateTable').bootstrapTable('getOptions')) ); 
 console.log(($('#SeqGenerateTable').bootstrapTable('getOptions')).classes);
  
 var b =($('#SeqGenerateTable').bootstrapTable('getOptions')).columns;
 var a =($('#SeqGenerateTable').bootstrapTable('getData'));
 console.log(b);
	 console.log(a);
 
});
