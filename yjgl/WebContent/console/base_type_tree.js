var BaseTypeTree={
	//初始化启动
	init:function(){
		BaseTypeTree.bindEvent();
		BaseTypeTree.loadData();
		BaseTypeTree.initType();
	},
	
	//数据字典初始化
	initType:function(){
		
	},

	bindEvent:function(){
		//新增数据字典信息
		$('#_base_type_west_add_button').click(function(){
			$('#_base_type_west_add_window').window('open');
		});
		
		//新增数据字典信息---保存按钮
		$('#_base_type_west_add_save_button').click(function(){
			$('#_base_type_west_add_form').form('submit',{
				success:function(r){
					data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_west_add_window').window('close');
						$('#_base_type_west_add_form').form('reset');
		   	    		$('#_base_type_west_table').datagrid('reload');
		   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','保存失败!'); 					
					}
				}
			});
		});
		
		//新增数据字典信息---取消按钮
		$('#_base_type_west_add_cancel_button').click(function(){
			$('#_base_type_west_add_window').window('close');
		});
		
		//编辑数据字典信息
		$('#_base_type_west_edit_button').click(function(){
			var data = $('#_base_type_west_table').datagrid('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			$('#_base_type_west_edit_window').window('open');
			$("#_base_type_west_edit_basetypeid").val(data.basetypeid);
			$("#_base_type_west_edit_name").textbox("setValue", data.name);
			$('#_base_type_west_edit_state').combobox('setValues',data.state.toString());
		});
		
		//编辑数据字典信息---关闭按钮
		$('#_base_type_west_edit_cancel_button').click(function(){
			$('#_base_type_west_edit_window').window('close');
		});
		
		//编辑数据字典信息---保存按钮
		$('#_base_type_west_edit_save_button').click(function(){
			$('#_base_type_west_edit_form').form('submit',{
				success:function(r){
					data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_west_edit_window').window('close');
		   	    		$('#_base_type_west_table').datagrid('reload');
		   	    		$('#_base_type_center_treegrid').treegrid('reload');
		   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','保存失败!'); 					
					}
				}
			});
		});
		
		//刷新版本数据字典信息
		$('#_base_type_west_refresh_button').click(function(){
			var data = $('#_base_type_west_table').datagrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			}
			$.ajax({
				url:"../TblBaseTypeAction/updateVerByCode",
				data:{'code':data[0].code,'type':1},
				type:"post",
				dataType:"json",
				success: function(data){
					if(data.sec && data.rows > 0){
						$('#_base_type_west_table').datagrid('reload');
						$('#_base_type_center_treegrid').datagrid('reload');
						$.messager.show({title:'系统消息',msg:'刷新数据版本成功！',showType:'slide'});
					}else{
						$.messager.alert('系统消息','刷新数据版本失败！');
					}
				}	
			});
		});
		
		//删除数据字典信息
		$('#_base_type_west_del_button').click(function(){
			var data = $('#_base_type_west_table').datagrid('getSelections');
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
									$('#_base_type_west_table').datagrid('reload');
						    		$('#_base_type_center_treegrid').datagrid('loadData',{ "total":"0",rows:[] });
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
		$('#_base_type_tree_add_button').click(function(){
			var data = $('#_base_type_west_table').datagrid('getSelections');
			var datatree = $('#_base_type_center_treegrid').treegrid('getSelections');
			if(datatree.length>1){
				$.messager.alert('警告','字典项列表最多只能选择一条进行新增！');
				return;
			}
			if(validateMoreRecord(data,"请至少选择左边(字典信息)列表一条记录！")){
				if(datatree.length > 0){
					$('#_base_type_tree_add_pid').val(datatree[0].basetypeid);
				}else{
					$('#_base_type_tree_add_pid').val(data[0].basetypeid);
				}
				
				$('#_base_type_tree_add_code').val(data[0].code);
				$('#_base_type_tree_add_window').window('open');
			};
		});
		
		//新增数据字典项---关闭按钮
		$('#_base_type_tree_add_cancel').click(function(){
			$('#_base_type_tree_add_window').window('close');
		});
		
		//新增数据字典项---保存按钮
		$('#_base_type_tree_add_save').click(function(){
			$('#_base_type_tree_add_form').form('submit', {
				success:function(r){
					var data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_tree_add_window').window('close');
						$('#_base_type_tree_add_form').form('reset');
						$('#_base_type_center_treegrid').treegrid('reload');
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('系统消息','保存数据失败！');
					}
				}
			});
		});
		
		//编辑数据字典项
		$('#_base_type_tree_edit_button').click(function(){
			var data = $('#_base_type_center_treegrid').datagrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			} else if(data.length > 1 ){
				$.messager.alert('警告','一次只能编辑一条记录！');
				return;
			} else{
				$("#_base_type_tree_edit_basetypeid").val(data[0].basetypeid);
				$("#_base_type_tree_edit_name").textbox("setValue", data[0].name);
				$("#_base_type_tree_edit_value").textbox("setValue", data[0].value);
				$('#_base_type_tree_edit_window').window('open');
				$('#_base_type_tree_code_edit_status').combobox('setValues',data[0].stateStr.toString());
			}
		});
		
		//编辑数据字典项---关闭按钮
		$('#_base_type_tree_edit_cancel').click(function(){
			$('#_base_type_tree_edit_window').window('close');
		});
		
		//编辑数据字典项---保存按钮
		$('#_base_type_tree_edit_save').click(function(){
			$('#_base_type_tree_edit_form').form('submit', {
				success:function(r){
					var data = eval('(' + r + ')');
					if(data.sec && data.rows > 0){
						$('#_base_type_tree_edit_window').window('close');
						$('#_base_type_tree_edit_form').form('reset');
						$('#_base_type_center_treegrid').treegrid('reload');
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					}else{
						$.messager.alert('系统消息','保存数据失败！');
					}
				}
			});
		});
		
		//删除数据字典项
		$('#_base_type_tree_del_button').click(function(){
			var data = $('#_base_type_center_treegrid').treegrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length;i++)
					str += "&idArray=" + data[i].basetypeid;
				var url = "../TblBaseTypeAction/deleteBatchLoop" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.rows > 0 && result.sec){
						$('#_base_type_center_treegrid').treegrid('clearChecked');
						$('#_base_type_center_treegrid').treegrid('reload');
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});
		
		//上移字典项
		$('#_base_type_tree_up_button').click(function(){
			var previous = null;
			var data = $('#_base_type_center_treegrid').treegrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			} else if(data.length > 1 ){
				$.messager.alert('警告','一次只能编辑一条记录！');
			 	return;
			} else{
				var parent = $('#_base_type_center_treegrid').treegrid('getParent', data[0].id);
				if(parent!=null){
					var childrens = parent.children;
					if(childrens.length == 1){
						$.messager.alert('系统消息','没有同级节点，无法上移！');
						return;
					}else{
						for(var i = 0;i<childrens.length;i++){
							if(childrens[i].id == data[0].id){
								if(i == 0){
									$.messager.alert('系统消息','该节点已为最顶层节点，无法移动。');
									return;
								}
								previous = childrens[i-1];
								break;
							}
						}
					}
				}else{
					var roots = $('#_base_type_center_treegrid').treegrid('getRoots');
					for(var i = 0;i<roots.length;i++){
						if(roots[i].id == data[0].id){
							if(i == 0){
								$.messager.alert('系统消息','该节点已为最顶层节点，无法移动。');
								return;
							}
							previous = roots[i-1];
							break;
						}
					}
				}
				BaseTypeTree.moveUpOrDown(data,previous);
			}
		});
		
		//下移字典项
		$('#_base_type_tree_down_button').click(function(){
			var next = null;
			var data = $('#_base_type_center_treegrid').treegrid('getSelections');
			if(data.length < 1 ) {
				$.messager.alert('警告','请选择一条数据！');
				return;
			} else if(data.length > 1 ){
				$.messager.alert('警告','一次只能编辑一条记录！');
				return;
			} else{
				var parent = $('#_base_type_center_treegrid').treegrid('getParent', data[0].id);
				if(parent != null){
					var childrens = parent.children;
					if(childrens.length == 1){
						$.messager.alert('系统消息','没有同级节点，无法下移！');
						return;
					}else{
						for(var i = 0;i<childrens.length;i++){
							if(childrens[i].id == data[0].id){
								if(i == childrens.length-1){
									$.messager.alert('系统消息','该节点已为最底层节点，无法移动。');   
									return;
								}
								next = childrens[i+1];
								break;
							}
						}
					}
				}else{
					var roots = $('#_base_type_center_treegrid').treegrid('getRoots');
					for(var i = 0;i<roots.length;i++){
						if(roots[i].id == data[0].id){
							if(i == roots.length-1){
								$.messager.alert('系统消息','该节点已为最底层节点，无法移动。');
								return;
							}
							next = roots[i+1];
							break;
						}
					}
				}
				BaseTypeTree.moveUpOrDown(data,next);
			}
		});
	},
	
	//上移下移
	moveUpOrDown:function(data,previous){
		initToken();
		$.ajax({
			url:"../TblBaseTypeAction/moveUpOrDown",
			data:{'type':1,'checkId':data[0].id,'checkSequ':data[0].sequ,'targetId':previous.id,'targetSequ':previous.sequ},
			type:"post",
			dataType:"json",
			async: false,
			success: function(result){
				if(result.sec){
					$('#_base_type_center_treegrid').treegrid('reload').treegrid('select',data[0].id);
					$('#_base_type_west_table').datagrid('reload');
					$.messager.show({title:'系统消息',msg:'移动成功！',showType:'slide'});
				}else{
					$.messager.alert('系统消息','移动失败！');
				}
			}
		});
	},
	
	//加载初始化数据
	loadData:function(){
		$('#_base_type_west_table').datagrid({
			url:'../TblBaseTypeAction/selectMenu',
			queryParams:{'type':'1'},
			columns:[[
				{align:'left',halign:'left',width:200,field:'name',title:'名称'},
				{align:'left',halign:'left',width:120,field:'code',title:'代码'},
				{align:'left',halign:'left',width:60,field:'ver',title:'版本'},
				{align:'left',halign:'left',field:'state',title:'状态',width:60,formatter: function(state){
					return (state == 1)?"启用":"禁用";
				}},
				{field:'basetypeid',title:'编号',hidden:true},
				{field:'value',title:'数值',width:150,hidden:true},
				{field:'updateTime',title:'更新时间',width:200,hidden:true}
			]],
			onSelect: function(index,data){
	        	var queryParams ={'code':data.code};
	    		$('#_base_type_center_treegrid').treegrid('unselectAll').treegrid('load',queryParams);
			},
			rownumbers:true,
			singleSelect:true,
			nowrap:true,
			fit:true,
		});
		
		$('#_base_type_center_treegrid').treegrid({
			url:'../TblBaseTypeAction/selectAllByCode',
			idField:'id',
		    treeField:'name',
		    parentField:'pid',
		    singleSelect:false,
			columns:[[
			    {field:'ck',checkbox:true},
			    {align:'left',halign:'left',field:'name',title:'名称',width:350},
			    {align:'left',halign:'left',field:'value',title:'值',width:180},
			    {align:'left',halign:'left',field:'stateStr',title:'状态',width:60,formatter: function(status){
			    	return (status == 1)?"启用":"禁用";
				}},
				{hidden:true,field:'basetypeid',title:'编号',width:120},
				{hidden:true,field:'code',title:'代码',width:120},
				{hidden:true,field:'updateTime',title:'更新时间',width:200}
			]],
			queryParams: {'code':''},
			rownumbers:true,
			nowrap:true,
			fit:true,
		});
	}
}

$(function(){
	BaseTypeTree.init();
});
