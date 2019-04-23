var SjwhLssjglList={
	//初始化启动
	init:function(){
		SjwhLssjglList.initType();
		SjwhLssjglList.bindEvent();
		SjwhLssjglList.loadData();
	},
	//数据字典初始化
	initType:function(){
		BaseType.initSelectEditBox('_sjwh_lssjgl_list_search_hisjglx','JGLX', true, false , 155, true);
		BaseType.initSelectBox('_sjwh_lssjgl_list_search_hisssdl','SSDL', false,true , 130);
		$("#_sjwh_lssjgl_list_search_jglx").combobox({editable: true});
	},
	//绑定事件
	bindEvent:function(){
		
		//查看按钮
		$('#_sjwh_lssjgl_list_view_button').bind('click', function(){
			var data=$('#_sjwh_lssjgl_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
			$('#_sjwh_lssjgl_list_view_window').window('open');
			initFormData($('#_sjwh_lssjgl_list_view_form'),data[0]);
			setDefaultFormData($('#_sjwh_lssjgl_list_view_form'));
			}
		}); 
		
		//条件查询按钮事件
		$('#_sjwh_lssjgl_list_search_button').bind('click', function(){ 
			var timeStart = $("#_sjwh_lssjgl_list_search_timeStart").datebox("getValue");
			var timeEnd = $("#_sjwh_lssjgl_list_search_timeEnd").datebox("getValue");
			if(timeStart != "" && timeEnd != "" && timeStart >= timeEnd){
				$.messager.alert('系统消息','操作时间的开始时间不能晚于或等于结束时间！'); 
				return false;
			}
			var queryParams ={
				"hisjglx":$("#_sjwh_lssjgl_list_search_hisjglx").combobox('getValue'), 
				"hisssdl":$("#_sjwh_lssjgl_list_search_hisssdl").combobox('getValues').toString(), 
				"hisczr":$("#_sjwh_lssjgl_list_search_hisczr").val(),
				"hisjgbh":$("#_sjwh_lssjgl_list_search_hisjgbh").val(),
				"timeStart":timeStart,
				"timeEnd":timeEnd,
			};
			$('#_sjwh_lssjgl_list_table').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_sjwh_lssjgl_list_reset_button').bind('click', function(){
			$('#_sjwh_lssjgl_list_search_form').form('clear');	
			$("#_sjwh_lssjgl_list_search_hisjglx").combobox('setValue','-1');
		}); 
	},
	
	loadData:function(){
		initToken();
		$('#_sjwh_lssjgl_list_table').datagrid({
			url:"../YjgHisjgxxAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{hidden:false, align:'center', width:150, field:'hisjgbh', title:'井盖编号'},
				{hidden:false, align:'center', width:100, field:'hisjglx', title:'井盖类型'},
				{hidden:false, align:'center', width:100, field:'hisjngj', title:'井内管径'},
				{hidden:false, align:'center', width:100, field:'hisjgcz', title:'井盖材质'},
				{hidden:false, align:'center', width:100, field:'hisjggg', title:'井盖规格'},
				{hidden:false, align:'center', width:100, field:'hisjgxz', title:'井盖形状'},
				{hidden:false, align:'center', width:100, field:'hisjgzt', title:'井盖状态'},
				{hidden:false, align:'center', width:100, field:'hissfzw', title:'防坠网状态'},
				{hidden:false, align:'center', width:200, field:'hisgldw', title:'管理单位'},
				{hidden:false, align:'center', width:200, field:'hisqsdw', title:'权属单位'},
				{hidden:false, align:'center', width:100, field:'hisssdl', title:'所属道路'},
				{hidden:false, align:'center', width:200, field:'hisdljssj', title:'道路建设时间'},
				{hidden:false, align:'center', width:200, field:'hisczsj', title:'推入历史时间'},
				{hidden:false, align:'center', width:120, field:'hisczr', title:'推入历史操作人'},
				{hidden:true, align:'center', width:100, field:'hisxzb', title:'X坐标'},
				{hidden:true, align:'center', width:100, field:'hisyzb', title:'Y坐标'},
				{hidden:true, align:'center', width:100, field:'hisjgid', title:'历史井盖ID'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	}
}
$(function(){
	SjwhLssjglList.init();
});