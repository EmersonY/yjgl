var Holidays={
	//初始化启动
	init:function(){
		Holidays.initType();
		Holidays.loadData();
		Holidays.bindEvent();
	},	
	
	//数据字典初始化
	initType:function(){
		BaseType.initSelectBox('_base_holidays_search_holiday','JJR', true, false, 105);
		BaseType.initSelectBox('_base_holidays_add_holiday','JJR', false, false, 105);
		BaseType.initSelectBox('_base_holidays_add_holidaytype','JJRLX', false, false, 60);
		BaseType.initSelectBox('_base_holidays_add_isholiday','SFBS', false, false, 60);
		BaseType.initSelectBox('_base_holidays_edit_holidayname','JJR', false, false, 105);
		BaseType.initSelectBox('_base_holidays_edit_holidaytype','JJRLX', false, false, 60);
		BaseType.initSelectBox('_base_holidays_edit_isholiday','SFBS', false, false, 60);
		BaseType.initSelectBox('_base_holidays_view_isholiday','SFBS', false, false, 60);
		BaseType.initSelectBox('_base_holidays_view_holidaytype','JJRLX', false, false, 60);
		BaseType.initSelectBox('_base_holidays_view_holidayname','JJR', false, false, 60);

		BaseType.init('JJR');
		BaseType.init('JJRLX');
	},
	
	//加载数据
	loadData:function(){
		$('#_base_holidays_table').datagrid({
			url:"../TblBaseHolidaysAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:100, field:'year', title:'年份'},
				{hidden:false, align:'center', width:100, field:'holidayname', title:'节假日名称',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("JJR", value);
					},	
				},
				{hidden:false, align:'center', width:100, field:'holidaydate', title:'节假日日期'},
				{hidden:false, align:'center', width:200, field:'createtime', title:'创建时间'},
				{hidden:false, align:'center', width:100, field:'holidaytype', title:'节假日类型 ',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("JJRLX", value);
					},
				},
				{hidden:false, align:'center', width:100, field:'isholiday', title:'是否节假日当日',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SFBS", value);
					},
				},
				{hidden:false, align:'center', width:200, field:'remark', title:'备注'},
				{hidden:true, align:'center', width:100, field:'createperson', title:'创建人'},
				{hidden:true, align:'center', width:100, field:'updateperson', title:'更新人'},
				{hidden:true, align:'center', width:100, field:'updatetime', title:'更新时间'},
				{hidden:true, align:'center', width:100, field:'holidaysid', title:'节假日安排ID'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	
	bindEvent:function(){
		//条件查询按钮事件
		$('#_base_holidays_search_button').bind('click', function(){ 
			var queryParams ={
				'year': $('#_base_holidays_search_year').numberspinner("getValue"),
				'holidayname':$('#_base_holidays_search_holiday').combobox("getValue")
			};

			$('#_base_holidays_table').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_base_holidays_reset_button').bind('click', function(){
			$('#_base_holidays_search_form').form('reset');
			$('#_base_holidays_search_holiday').combobox('setValues','-1');
		}); 
		
		//增加页面按钮事件
		$('#_base_holidays_add_button').bind('click', function(){ 
			$('#_base_holidays_add_window').window('open');
			$('#_base_holidays_add_holiday').combobox('setValues','1');
			$('#_base_holidays_add_holidaytype').combobox('setValues','1');
			$('#_base_holidays_add_isholiday').combobox('setValues','1');
			$('#_base_holidays_add_year').numberspinner('setValue', 2018); 
		});

		//增加页面取消按钮事件
		$('#_base_holidays_add_cancel_button').bind('click', function(){
			$('#_base_holidays_add_window').window('close');	
		});  
		
		//增加页面保存按钮事件
		$('#_base_holidays_add_save_button').bind('click', function(){   
			$('#_base_holidays_add_form').form('submit', {   
				url:"../TblBaseHolidaysAction/add",
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_base_holidays_add_window').window('close');
						$('#_base_holidays_table').datagrid('reload');
					}else{
						$.messager.alert('警告','保存失败！'); 
					}
		   	    } 
			});  
		}); 
		
		//编辑页面按钮事件
		$('#_base_holidays_edit_button').bind('click', function(){ 
			var data=$('#_base_holidays_table').datagrid('getSelections');
			if(data.length < 1)
			{
				$.messager.alert('警告','请选择一条数据！');
				return;
			}
			if(data.length > 1)
			{
				$.messager.alert('警告','一次只能选择一条数据进行编辑！');
				return;
			} 
			$('#_base_holidays_edit_window').window('open');
			$('#_base_holidays_edit_form').form('reset');
			initFormData($('#_base_holidays_edit_form'),data[0]);
			setDefaultFormData($('#_base_holidays_edit_form'));
		});
		 
		//编辑页面取消按钮事件
		$('#_base_holidays_edit_cancel_button').bind('click', function(){
			$('#_base_holidays_edit_window').window('close');
		});  
		 
		//编辑页面保存按钮事件
		$('#_base_holidays_edit_save_button').bind('click', function(){
			$('#_base_holidays_edit_form').form('submit', {   
				url:"../TblBaseHolidaysAction/edit",
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_base_holidays_edit_window').window('close');
						$('#_base_holidays_table').datagrid('reload');
					}else{
						$.messager.alert('警告','编辑失败！'); 
					}
		   	    } 
			});  
		}); 

		//删除按钮
		$('#_base_holidays_delete_button').bind('click', function(){
			var data=$('#_base_holidays_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length; i++)
					str += "&idArray=" + data[i].holidaysid;
				var url = "../TblBaseHolidaysAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_base_holidays_table').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});  
		
		//查看按钮
		$('#_base_holidays_view_button').bind('click', function(){
			var data=$('#_base_holidays_table').datagrid('getSelections');
			if(validateOneRecord(data)){
			$('#_base_holidays_view_window').window('open');
			initFormData($('#_base_holidays_view_form'),data[0]);
			setDefaultFormData($('#_base_holidays_view_form'));
			}
		}); 
	}
}

$(function(){
	Holidays.init();
});