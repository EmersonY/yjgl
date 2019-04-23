var BaseLogList={
	//初始化启动
	init:function(){
		BaseLogList.bindEvent();
		BaseLogList.loadData();
	},

	//绑定事件
	bindEvent:function(){
		
		$('#log_reset_button').bind('click', function(){
			$('#log_search_form').form('reset');
		});
		
		$('#log_search_button').bind('click', function(){
			var timeBegin= $("#log_search_logtimeStart").datebox("getValue");
			var logtimeEnd= $("#log_search_logtimeEnd").datebox("getValue");
			if(timeBegin != "" && logtimeEnd != "" && timeBegin >= logtimeEnd){
				$.messager.alert('系统消息','开始时间不能晚于或等于结束时间！'); 
				return false;
			}
			var queryParams ={
					"username":$("#log_search_username").val(),
					"module":$("#log_search_module").combobox('getValue'),
					"result":$("#log_search_result").val(),
					"content":$("#log_search_content").val(),
					"logtimeStart":timeBegin,
					"logtimeEnd":logtimeEnd
			};
			$('#_log_list').datagrid('load',queryParams);
		});
		
		$('#log_view_button').bind('click', function(){
			var data=$('#_log_list').datagrid('getSelections');
			if(validateOneRecord(data)){
				$('#_view_window').window('open');
				var e = data[0];
				$('#_view_module').html(e.module);
				$('#_view_logtime').html(e.logtime);
				$('#_view_result').html(e.result);
				$('#_view_content').html(e.content);
				$('#_view_username').html(e.username);
			}
		});
		
	},
	
	//加载数据
	loadData:function(){
		initToken();
		$('#_log_list').datagrid({
		url:"../TblBaseLogAction/list",
		queryParams:{},
		columns:[[
		    /*{field:'ck',checkbox:true},*/
			{hidden:false, align:'center', width:110, field:'username', title:'操作用户'},
			{hidden:false, align:'center', width:200, field:'logtime', title:'操作时间'},
			{hidden:false, align:'center', width:110, field:'module', title:'操作模块'},
			{hidden:false, align:'center', width:110, field:'result', title:'操作结果'},
			{hidden:false, align:'left', width:600, field:'content', title:'操作内容'},
			{hidden:true, align:'left', width:100, field:'logid', title:'主键ID'},
			{hidden:true, align:'left', width:100, field:'userid', title:'操作人ID'}
		]],
		rownumbers:true,
		pagination:true,
		nowrap:true,
		pageSize:20,
		singleSelect:true,
	});
	$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
	$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	}
}

$(function(){
	BaseLogList.init();
});
