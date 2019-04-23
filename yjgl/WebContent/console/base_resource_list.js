var BaseResourceList={
		
	//初始化启动
	init:function(){
		BaseResourceList.initType();
		BaseResourceList.bindEvent();
		BaseResourceList.loadDataGird();
	},
	
	//数据字典初始化
	initType:function(){
		BaseType.init('SDZT');
	},
	
	//绑定事件
	bindEvent:function(){
		//条件查询按钮事件
		$('#_base_resource_list_search_button').bind('click', function(){ 
			var queryParams ={
				"resourceno":$("#_resource_search_resourceno").val(),
				"name":$("#_resource_search_name").val(),
			};
			$('#_base_resource_list_table').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_base_resource_list_reset_button').bind('click', function(){
			$('#_base_resource_list_search_form').form('reset');	
		}); 
		
		//增加页面按钮事件
		$('#_base_resource_list_add_button').bind('click', function(){ 
			BaseType.initSelectBox('_base_resource_list_add_status','SDZT', false, false, 60);
			$('#_base_resource_list_add_status').combobox('setValues','1');
			BaseResourceList.initCombotree();
			$('#_base_resource_list_add_window').window('open');
		});

		//增加页面取消按钮事件
		$('#_base_resource_list_add_cancel_button').bind('click', function(){
			$('#_base_resource_list_add_window').window('close');	
		});  
		
		//增加页面保存按钮事件
		$('#_base_resource_list_add_save_button').bind('click', function(){   
			$('#_base_resource_list_add_form').form('submit', {   
				url:"../TblBaseResourceAction/add",
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_base_resource_list_add_window').window('close');
						$('#_base_resource_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','保存失败！'); 
					}
		   	    } 
			});  
		}); 
		
		//编辑页面按钮事件
		$('#_base_resource_list_edit_button').bind('click', function(){ 
			var data=$('#_base_resource_list_table').datagrid('getSelections');
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
			$('#_base_resource_list_edit_window').window('open');
			$('#_base_resource_list_edit_form').form('reset');
			BaseType.initSelectBox('_base_resource_list_edit_status','SDZT', false, false, 60);
			initFormData($('#_base_resource_list_edit_form'),data[0]);
			setDefaultFormData($('#_base_resource_list_edit_form'));
			$('#_base_resource_list_edit_menuname').combotree({
				url:"../main/selectMenuTree",
				idField:'basemenuid',
	            textField:'menuname',
	            parentField:'basemenupid',
	            method:'GET',
	            editable:false,
	            onChange:function(newValue, oldValue){
	            	if(oldValue != ""){
		            	var name = $('#_base_resource_list_edit_menuname').combotree("getText");
		            	$('#_edit_menuname').val(name);
		            	$('#_edit_basemenuid').val(newValue);
	            	}
	            }
			})
			$('#_base_resource_list_edit_menuname').combotree('setValue', { id: data[0].basemenuid, text: data[0].menuname });
			
		});
		 
		//编辑页面取消按钮事件
		$('#_base_resource_list_edit_cancel_button').bind('click', function(){
			$('#_base_resource_list_edit_window').window('close');
		});  
		 
		//编辑页面保存按钮事件
		$('#_base_resource_list_edit_save_button').bind('click', function(){
			$('#_base_resource_list_edit_form').form('submit', {   
				url:"../TblBaseResourceAction/edit",
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_base_resource_list_edit_window').window('close');
						$('#_base_resource_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','编辑失败！'); 
					}
		   	    } 
			});  
		}); 

		//删除按钮
		$('#_base_resource_list_delete_button').bind('click', function(){
			var data=$('#_base_resource_list_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length; i++)
					str += "&idArray=" + data[i].baseresourceid;
				var url = "../TblBaseResourceAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_base_resource_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});  
		
		//授权按钮
		$('#_base_resource_list_linkRole_button').bind('click', function(){
			var data=$('#_base_resource_list_table').datagrid('getSelections');
			if(data.length < 1)
			{
				$.messager.alert('警告','请选择一条数据！');
				return;
			}
			if(data.length > 1)
			{
				$.messager.alert('警告','一次只能选择一条数据进行授权！');
				return;
			} 
			$('#_base_resource_list_linkRole_window').window('open');
			BaseResourceList.initRoleGrid(data[0].baseresourceid);
		});
		
		//授权页面取消按钮
		$('#_base_resource_list_linkRole_cancel').bind('click', function(){   
			$('#_base_resource_list_linkRole_window').window('close');
		}); 
		
		//授权页面确认按钮
		$('#_base_resource_list_linkRole_comfirm').bind('click', function(){  
			var baseresourceid=$('#_base_resource_list_table').datagrid('getSelections')[0].baseresourceid;
			var data=$('#_base_resource_list_linkRole_table').datagrid('getSelections');
			if(data.length>0){
				var str ="";
				for(var i = 0 ; i < data.length;i++)
					str += "&idArray=" + data[i].baseroleid;
				var url = "../TblBaseRoleResourceMappingAction/add" + "?" + str.substring(1) + "&baseresourceid=" + baseresourceid;	
			}else{
				$.messager.alert('警告','请选择授权角色！');
			}
			$.ajax({
				url:url,
				type:"post",
				dataType:"json",
				async: false,
				success: function(result,textStatus){ 
					if(result.rows > 0 && result.sec){
						$.messager.show({title:'系统消息',msg:'授权成功！',showType:'slide'});
						$('#_base_resource_list_linkRole_table').treegrid('reload');
						$('#_base_resource_list_linkRole_window').window('close');
					}else{
						$.messager.alert('警告','授权失败！');
					}
				}	
			});
		}); 
		
	},
	loadDataGird:function(){
		initToken();
		$('#_base_resource_list_table').datagrid({
			url:"../TblBaseResourceAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:120, field:'resourceno', title:'资源编号'},
				{hidden:false, align:'center', width:120, field:'name', title:'资源名称'},
				{hidden:false, align:'center', width:120, field:'menuname', title:'对应模块'},
				{hidden:false, align:'center', width:120, field:'updater', title:'操作人'},
				{hidden:false, align:'center', width:80, field:'status', title:'有效状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SDZT", value);
					}
				},
				{hidden:false, align:'center', width:300, field:'remark', title:'描述'},
				{hidden:true, align:'center', width:100, field:'baseresourceid', title:'资源ID'},
				{hidden:true, align:'center', width:100, field:'basemenuid', title:'菜单ID'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	initCombotree:function(){
		initToken();
		$('#_base_resource_list_add_menuname').combotree({
			url:"../main/selectMenuTree",
			idField:'basemenuid',
            textField:'menuname',
            parentField:'basemenupid',
            method:'GET',
            editable:false,
            onChange:function(){
            	var name = $('#_base_resource_list_add_menuname').combotree("getText");;
            	$('#_add_menuname').val(name);
            }
		})
		
	},
	initRoleGrid:function(baseresourceid){
		initToken();
		$('#_base_resource_list_linkRole_table').treegrid({
			url:"../TblBaseRoleAction/selectRoleTree",
			idField:'baseroleid',
		    treeField:'rolename',
		    parentField:'baserolepid',
		    singleSelect:false,
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'left', width:300, field:'rolename', title:'角色名称'},
				{hidden:true, align:'center', width:100, field:'baseroleid', title:'角色编号'},
				{hidden:true, align:'center', width:100, field:'baserolepid', title:'角色父编号'},
				{hidden:true, align:'center', width:100, field:'updatetime', title:'更新时间'},
				{hidden:true, align:'center', width:100, field:'updateuserid', title:'更新人'}
			]],
			rownumbers:true,
			nowrap:true,
			fit:true,
			onLoadSuccess:function(data){
				$('#_base_resource_list_linkRole_table').treegrid("uncheckAll");
				$.post('../TblBaseRoleResourceMappingAction/selectByResourceId',{'baseresourceid':baseresourceid},function(result){
					if(result.length>0){
						$(result).each(function(){
							debugger
							$('#_base_resource_list_linkRole_table').datagrid('checkRow', this.baseroleid);
						});
					}
				});
			}
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
}
$(function(){
	BaseResourceList.init();
});
