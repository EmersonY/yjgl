var BaseTypeList={
	//初始化启动
	init:function(){
		BaseTypeList.bindEvent();
		BaseTypeList.loadData();
		BaseTypeList.initType();
	},
	
	initType:function(){
		BaseType.initSelectBox('_base_type_list_ssmk','ZDSSMK', true, false, 105);
		BaseType.initSelectBox('_base_type_list_add_ssmk','ZDSSMK', false, false, 85);
		BaseType.initSelectBox('_base_type_list_edit_ssmk','ZDSSMK', false, false, 85);
		BaseType.init('ZDSSMK');
	},
	
	bindEvent:function(){
		//新增数据字典信息
		$('#_base_type_list_add_button').click(function(){
			$('#_base_type_list_add_window').window('open');
			$('#_base_type_list_add_ssmk').combobox('setValues','1');
		});
		
		//新增数据字典信息---取消按钮
		$('#_base_type_list_add_cancel_button').click(function(){
			$('#_base_type_list_add_window').window('close');
		});
		
		//新增数据字典信息---查询按钮
		$('#_base_type_list_search_button').click(function(){
			var queryParams ={
				"type":'0',
				"module":$("#_base_type_list_ssmk").combobox('getValue')
			};
			$('#_base_type_west_list').datagrid('load',queryParams);
		});
		
		//新增数据字典信息---保存按钮
		$('#_base_type_list_add_save_button').click(function(){
			$('#_base_type_list_add_form').form('submit',{
				onSubmit: function(param){
					return $(this).form('validate');
			    },
				success:function(r){
					data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_list_add_window').window('close');
						$('#_base_type_list_add_form').form('reset');
		   	    		$('#_base_type_west_list').datagrid('reload');
		   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','保存失败!'); 					
					}
				}
			});
		});
		
		//编辑数据字典信息
		$('#_base_type_list_edit_button').click(function(){
			var data = $('#_base_type_west_list').datagrid('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			$('#_base_type_list_edit_window').window('open');
			$("#_base_type_list_edit_basetypeid").val(data.basetypeid);
			$("#_base_type_list_edit_name").textbox("setValue", data.name);
			$('#_base_type_list_edit_state').combobox('setValues',data.state.toString());
			$('#_base_type_list_edit_ssmk').combobox('setValues',data.module);
		});
		
		//编辑数据字典信息---取消按钮
		$('#_base_type_list_edit_cancel_button').click(function(){
			$('#_base_type_list_edit_window').window('close');
		});
		
		//编辑数据字典信息---保存按钮
		$('#_base_type_list_edit_save_button').click(function(){
			$('#_base_type_list_edit_form').form('submit',{
				onSubmit: function(param){
					return $(this).form('validate');
			    },
				success:function(r){
					data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_list_edit_window').window('close');
		   	    		$('#_base_type_west_list').datagrid('reload');
		   	    		$('#_base_type_center_list').datagrid('reload');
		   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','保存失败!'); 					
					}
				}
			});
		});
		
		//刷新版本数据字典信息
		$('#_base_type_list_refresh_button').click(function(){
			var data = $('#_base_type_west_list').datagrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			}
			$.ajax({
				url:"../TblBaseTypeAction/updateVerByCode",
				data:{'code':data[0].code,'type':0},
				type:"post",
				dataType:"json",
				success: function(data){
					if(data.sec && data.rows > 0){
						$('#_base_type_west_list').datagrid('reload');
						$('#_base_type_center_list').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'刷新数据版本成功！',showType:'slide'});
					}else{
						$.messager.alert('系统消息','刷新数据版本失败！');
					}
				}	
			});
		});
		
		//删除数据字典信息
		$('#_base_type_list_del_button').click(function(){
			var data = $('#_base_type_west_list').datagrid('getSelections');
			if(validateMoreRecord(data)){
				$.messager.confirm('确认删除','确认删除选择的记录？',function(r){
					if(r){
						$.ajax({
							url:"../TblBaseTypeAction/deletesByPid",
							data:{'basetypepid':data[0].basetypeid},
							type:"post",
							dataType:"json",
							success: function(r){
								if(r.result == 1 && r.rows > 0){
									$('#_base_type_west_list').datagrid('reload');
						    		$('#_base_type_center_list').datagrid('loadData',{ "total":"0",rows:[] });
						    		$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
								}else if(r.result == 2){
									$.messager.alert('警告','该字典信息含有字典项，请先删除字典项！');  
								}else{
									$.messager.alert('警告','删除记录失败！');
								}
							}
						});
					}
				});
			}
		});
		
		//新增数据字典项
		$('#_base_type_center_add_button').click(function(){
			var data = $('#_base_type_west_list').datagrid('getSelections');
			if(validateMoreRecord(data,"请至少选择左边(字典信息)列表一条记录！")){
				$('#_base_type_center_add_pid').val(data[0].basetypeid);
				$('#_base_type_center_add_code').val(data[0].code);
				$('#_base_type_center_add_window').window('open');
			}
			
		});
		
		//新增数据字典项---关闭按钮
		$('#_base_type_center_add_cancel').click(function(){
			$('#_base_type_center_add_window').window('close');
		});
		
		//新增数据字典项---保存按钮
		$('#_base_type_center_add_save').click(function(){
			$('#_base_type_center_add_form').form('submit', {
				success:function(r){
					data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_center_add_window').window('close');
						$('#_base_type_center_add_form').form('reset');
						$('#_base_type_center_list').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('系统消息','保存数据失败！');
					}
				}
			});
		});
		
		//编辑数据字典项
		$('#_base_type_center_edit_button').click(function(){
			var data = $('#_base_type_center_list').datagrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			} else if(data.length > 1 ){
				$.messager.alert('警告','一次只能编辑一条记录！');
				return;
			} else{
				$('#_base_type_center_edit_window').window('open');
				$("#_base_type_center_edit_basetypeid").val(data[0].basetypeid);
				$("#_base_type_center_edit_name").textbox("setValue", data[0].name);
				$("#_base_type_center_edit_value").textbox("setValue", data[0].value);
				$('#_base_type_center_edit_state').combobox('setValues',data[0].state.toString());
			} 
		});
		
		//编辑数据字典项---关闭按钮
		$('#_base_type_center_edit_cancel').click(function(){   
			$('#_base_type_center_edit_window').window('close');
		}); 
		
		//编辑数据字典项---保存按钮
		$('#_base_type_center_edit_save').click(function(){
			$('#_base_type_center_edit_form').form('submit', {
				success:function(r){ 
					data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_center_edit_window').window('close');
						$('#_base_type_center_list').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('系统消息','保存数据失败！');
					}
					
				}   
			});  
		});
		
		//编辑数据字典项---删除按钮
		$('#_base_type_center_del_button').click(function(){
			var data = $('#_base_type_center_list').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length;i++)
					str += "&idArray=" + data[i].basetypeid;
				var url = "../TblBaseTypeAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.rows > 0 && result.sec){
						$('#_base_type_center_list').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});
		
		//编辑数据字典项---上移按钮
		$('#_base_type_center_up_button').click(function(){
			var data = $('#_base_type_center_list').datagrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			} else if(data.length > 1 ){
				$.messager.alert('警告','一次只能编辑一条记录！');
				return;
			} 
			var rowIndex = $('#_base_type_center_list').datagrid('getRowIndex', data[0]);
			var datas = $('#_base_type_center_list').datagrid('getData').rows;
			if(rowIndex == 0){
				$.messager.alert('警告','该记录为最顶条，无法上移！');
				return;
			}
			$.ajax({
				url:"../TblBaseTypeAction/moveUpOrDown",
				data:{'type':0,'checkId':data[0].basetypeid,'checkSequ':data[0].sequ,'targetId':datas[rowIndex-1].basetypeid,'targetSequ':datas[rowIndex-1].sequ},
				type:"post",
				dataType:"json",
				async: false,
				success: function(result){ 
					if(result.rows > 0 && result.sec){
						$('#_base_type_center_list').datagrid('reload');
						$('#_base_type_west_list').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'上移成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','移动失败！');
					}
				}	
			});
		});
		
		//编辑数据字典项---下移按钮
		$('#_base_type_center_down_button').click(function(){
			var data = $('#_base_type_center_list').datagrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			} else if(data.length > 1 ){
				$.messager.alert('警告','一次只能编辑一条记录！');
				return;
			} 
			var rowIndex = $('#_base_type_center_list').datagrid('getRowIndex', data[0]);
			var datas = $('#_base_type_center_list').datagrid('getData').rows;
			if(rowIndex == datas.length-1){
				$.messager.alert('警告','该记录为最底条，无法下移！');
				return;
			}
			$.ajax({
				url:"../TblBaseTypeAction/moveUpOrDown",
				data:{'type':0,'checkId':data[0].basetypeid,'checkSequ':data[0].sequ,'targetId':datas[rowIndex+1].basetypeid,'targetSequ':datas[rowIndex+1].sequ},
				type:"post",
				dataType:"json",
				async: false,
				success: function(result){ 
					if(result.rows > 0 && result.sec){
						$('#_base_type_center_list').datagrid('reload');
						$('#_base_type_west_list').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'下移成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','移动失败！');
					}
				}	
			});
		});
	},
	
	loadData:function(){
		initToken();
		$('#_base_type_west_list').datagrid({
			url:'../TblBaseTypeAction/selectMenu',
			queryParams:{'type':'0'},
			columns:[[
			    {field:'ck',checkbox:true},
				{align:'left',halign:'left',width:120,field:'name',title:'名称'},
				{align:'left',halign:'left',width:120,field:'code',title:'代码'},
				{align:'left',halign:'left',width:60,field:'ver',title:'版本'},
				{align:'left',halign:'left',field:'state',title:'状态',width:60,formatter: function(state){
					return (state == 1)?"启用":"禁用";
				}},
				{align:'left',halign:'left',field:'module',title:'所属模块',width:120,
					formatter:function(value,row,index){
						return BaseType.getNameByValue("ZDSSMK", value);
					},
				},
				{field:'basetypeid',title:'编号',hidden:true},
				{field:'value',title:'数值',width:150,hidden:true}
			]],
			onSelect: function(index,data){
	        	var queryParams ={'code':data.code};
	    		$('#_base_type_center_list').datagrid('load',queryParams);
			},
			rownumbers:true,
			singleSelect:true,
			nowrap:true,
			fit:true,
		});
		
		$('#_base_type_center_list').datagrid({
			url:'../TblBaseTypeAction/selectAllByCode',
			columns:[[
			    {field:'ck',checkbox:true},
				{align:'left',halign:'left',field:'name',title:'名称',width:350},
				{align:'left',halign:'left',field:'value',title:'值',width:180},
				{align:'left',halign:'left',field:'state',title:'状态',width:60,formatter: function(status){
					return (status == 1)?"启用":"禁用";
				}},
				{hidden:true,field:'id',title:'编号',width:120},
				{hidden:true,field:'code',title:'代码',width:120},
				{hidden:true,field:'updateTime',title:'更新时间',width:200}
			]],
			queryParams: {'code':'' },
			rownumbers:true,
			nowrap:true,
			fit:true,
		});
	}
}

$(function(){
	BaseTypeList.init();
});
