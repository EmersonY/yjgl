var BaseRoleList={
		
	//初始化启动
	init:function(){
		BaseRoleList.initStyle();
		BaseRoleList.bindEvent();
		BaseRoleList.loadData();
	},
	
	initStyle:function(){
		$("#_role_west").css("width",$(window).width()/6);
		$("#_role_east").css("width",$(window).width()/2.8);
		BaseType.initSelectBox('_add_roletype','QSJSLX', true, false, 105);
		BaseType.initSelectBox('_edit_roletype','QSJSLX', true, false, 105);
	},
	
	initResourceGrid:function(baseroleid){
		initToken();
		$('#_base_role_list_linkRole_table').datagrid({
			url:"../TblBaseResourceAction/list",
			queryParams:{},
			method:'POST',
			idField : 'baseresourceid',
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
			pageSize:50,
			onLoadSuccess:function(data){
				$('#_base_role_list_linkRole_table').treegrid("uncheckAll");
				$.post('../TblBaseRoleResourceMappingAction/selectByRoleId',{'baseroleid':baseroleid},function(result){
					if(result.length>0){
						debugger
						var data = $('#_base_role_list_linkRole_table').datagrid('getData').rows;
						for(var i = 0 ; i < data.length; i++){
							for(var j = 0 ; j < result.length; j++){
								if(data[i].baseresourceid == result[j].baseresourceid){
									$('#_base_role_list_linkRole_table').datagrid('checkRow', i);
								}
							}
						}
					}
				});
			}
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	
	//绑定事件
	bindEvent:function(){
		
		//确定按钮
		$('#_base_role_list_linkRole_comfirm').bind('click', function(){
			debugger
			var data=$('#_base_role_list_linkRole_table').datagrid('getSelections');
			var baseroleid = $('#base_role').tree('getSelected').baseroleid;
			if(data.length>0){
				var str ="";
				for(var i = 0 ; i < data.length;i++)
					str += "&idArray=" + data[i].baseresourceid;
				var url = "../TblBaseRoleResourceMappingAction/addByRole" + "?" + str.substring(1) + "&baseroleid=" + baseroleid;	
			}else{
				$.messager.alert('警告','请选择授权资源！');
			}
			$.ajax({
				url:url,
				type:"post",
				dataType:"json",
				async: false,
				success: function(result,textStatus){ 
					if(result.rows > 0 && result.sec){
						$.messager.show({title:'系统消息',msg:'授权成功！',showType:'slide'});
						$('#_base_role_list_linkRole_window').window('close');
					}else{
						$.messager.alert('警告','授权失败！');
					}
				}	
			});
		});
		
		//取消按钮
		$('#_base_role_list_linkRole_cancel').bind('click', function(){
			$('#_base_role_list_linkRole_window').window('close');
		});
		
		//配置按鈕权限
		$('#_west_role_configure_button').bind('click', function(){
			var data=$('#base_role').tree('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			$('#_base_role_list_linkRole_window').window('open');
			BaseRoleList.initResourceGrid(data.baseroleid);
		});
		
		//查询按钮
		$('#_role_user_search_button').bind('click', function(){
			var data=$('#base_role').tree('getSelected');
			var queryParams = {
				'account': $('#_role_user_account').val(),
				'username': $('#_role_user_username').val(),
				'baseroleid': data.baseroleid
			};
			$('#_role_user').datagrid('reload',queryParams);
		});
		
		//二级查询按钮
		$('#_second_role_user_search_button').bind('click', function(){
			var data=$('#_role_user').datagrid('getSelected');
			var queryParams = {
				'account': $('#_second_role_user_account').val(),
				'username': $('#_second_role_user_username').val(),
				'type':2,
				'pbaseuserid': data.baseuserid
			};
			$('#_second_role_user').datagrid('reload',queryParams);
		});
		
		//重置按钮
		$('#_role_user_reset_button').bind('click', function(){
			$("#_role_user_form").form('clear');
		});
		
		//二级重置按钮
		$('#_second_role_user_reset_button').bind('click', function(){
			$("#_second_role_user_form").form('clear');
		});
		
		//删除按钮
		$('#_role_remove').bind('click', function(){
			var data=$('#_role_user').datagrid('getSelections');
			if(validateMoreRecord(data)){
				$.messager.confirm('确认删除','你确定要删除选中记录吗?',function(r){   
					 if(r){
						  var baseuserid ="";
						  for(var i = 0 ; i < data.length;i++){
							  if(i != data.length-1)
								  baseuserid += data[i].baseuserid +",";
							  else
								  baseuserid += data[i].baseuserid;
						  }
						  var baseroleid=$('#base_role').tree('getSelected').baseroleid;
						  var json = {'baseuserid':baseuserid,'baseroleid':baseroleid};
						 	$.ajax({
								url:"../TblBaseUserRoleMappingAction/delete",
								data:json,
								type:"post",
								async: false,
								dataType:"json",
								success: function(result){ 
									 if(result.sec && result.rows > 0){
										 $.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
										 $('#_role_user').datagrid('reload');
										 $('#_second_role_user').datagrid('loadData',{total:0,rows:[]});
									 }else{
										 $.messager.alert('系统消息','删除数据失败!');
									 }
								}	
							});
					 }
				}); 
			}
		});
		
		//二级用户删除按钮
		$('#_second_role_remove').bind('click', function(){
			var data=$('#_second_role_user').datagrid('getSelections');
			if(validateMoreRecord(data)){
				$.messager.confirm('确认删除','你确定要删除选中记录吗?',function(r){   
					 if(r){
						  var baseuserid ="";
						  for(var i = 0 ; i < data.length;i++){
							  if(i != data.length-1)
								  baseuserid += data[i].baseuserid +",";
							  else
								  baseuserid += data[i].baseuserid;
						  }
						  var json = {'idArray':baseuserid,'pbaseuserid':""};
						  $.ajax({
							  url:"../TblBaseUserAction/updatePuserid",
							  data:json,
							  type:"post",
							  async: false,
							  dataType:"json",
							  success: function(result){ 
								  if(result.sec && result.rows > 0){
									  $.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
									  $('#_second_role_user').datagrid('reload');
								  }else{
									  $.messager.alert('系统消息','删除数据失败!');
								  }
							  }	
						  });
					 }
				 });  
			}
		});
		
		//添加用户添加按钮
		$('#_role_add').bind('click', function(){
			var data=$('#base_role').tree('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一个角色！');
				return;
			} 
			var baseroleid = data.baseroleid;
			$('#_role_user_add').window('open');
			BaseRoleList.loadUserData({'baseroleid':baseroleid});
		});
		
		//添加二级用户添加按钮
		$('#_second_role_add').bind('click', function(){
			$('#_second_role_user_add').window('open');
			BaseRoleList.loadSecondUserData("");
		});
		
		//添加用户页面查询
		$('#_role_user_add_search').bind('click', function(){
			var data=$('#base_role').tree('getSelected');
			var queryParams = {
				'account': $('#_role_user_add_account').val(),
				'username': $('#_role_user_add_username').val(),
				'baseroleid': data.baseroleid
			};
			BaseRoleList.loadUserData(queryParams);
			$("#_role_user_add_form").form('clear');
		});
		
		//二级添加用户页面查询
		$('#_second_role_user_add_search').bind('click', function(){
			if($('#_role_user').datagrid('getSelected') == null){
				$.messager.alert('系统消息','请至少选择一个维护单位管理员！'); 
				return;
			}
			var data=$('#_role_user').datagrid('getSelected');
			var queryParams = {
				'account': $('#_second_role_user_add_account').val(),
				'username': $('#_second_role_user_add_username').val(),
				'pbaseuserid': data.baseuserid
			};
			BaseRoleList.loadSecondUserData(queryParams);
			$("#_second_role_user_add_form").form('clear');
		});
		
		//添加用户页面重置按钮
		$('#_role_user_add_reset').bind('click', function(){   
			$('#_role_user_add_form').form('clear');
		}); 
		
		//添加用户页面重置按钮
		$('#_second_role_user_add_reset').bind('click', function(){   
			$('#_second_role_user_add_form').form('clear');
		}); 
		
		//添加用户页面取消按钮
		$('#_role_user_cancel').bind('click', function(){   
			$('#_role_user_add').window('close');
		});
		
		//二级添加用户页面取消按钮
		$('#_second_role_user_cancel').bind('click', function(){
			$('#_second_role_user_add').window('close');
		});
		
		//添加用户页面确定
		$('#_role_user_confirm').bind('click', function(){
			var data=$('#_unadd_user').datagrid('getSelections');
			var baseroleid=$('#base_role').tree('getSelected').baseroleid;
			if(data.length < 1 )
			{
				$.messager.alert('系统消息','请至少选择一个用户！'); 
				return;
			} 
			var baseuserid ="";
			for(var i = 0 ; i < data.length;i++){
				if(i != data.length-1)
					baseuserid += data[i].baseuserid +",";
				else
					baseuserid += data[i].baseuserid;
			}
			var json = {'baseuserid':baseuserid,'baseroleid':baseroleid};
			$.ajax({
				url:"../TblBaseUserRoleMappingAction/insert",
				data:json,
				type:"post",
				async: false,
				dataType:"json",
				success: function(result){ 
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'添加成功！',showType:'slide'});
						$('#_role_user_add').window('close');
						$('#_role_user').datagrid('reload');
					}else{
						$.messager.alert('系统消息','添加失败！'); 
					}
				}	
			});
		});
		
		//添加二级用户页面确定
		$('#_second_role_user_confirm').bind('click', function(){
			var data=$('#_second_unadd_user').datagrid('getSelections');
			if($('#_role_user').datagrid('getSelected') == null){
				$.messager.alert('系统消息','请至少选择一个维护单位管理员！'); 
				return;
			}
			var pbaseuserid=$('#_role_user').datagrid('getSelected').baseuserid;
			if(data.length < 1 )
			{
				$.messager.alert('系统消息','请至少选择一个用户！'); 
				return;
			} 
			var baseuserid ="";
			for(var i = 0 ; i < data.length;i++){
				if(i != data.length-1)
					baseuserid += data[i].baseuserid +",";
				else
					baseuserid += data[i].baseuserid;
			}
			var json = {'idArray':baseuserid,'pbaseuserid':pbaseuserid};
			$.ajax({
				url:"../TblBaseUserAction/updatePuserid",
				data:json,
				type:"post",
				async: false,
				dataType:"json",
				success: function(result){ 
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'添加成功！',showType:'slide'});
						$('#_second_role_user_add').window('close');
						$('#_second_role_user').datagrid('reload');
					}else{
						$.messager.alert('系统消息','添加失败！'); 
					}
				}	
			});
		});
		
		//角色模块新增按钮
		$('#_west_role_add').bind('click', function(){
			var data=$('#base_role').tree('getSelected');
			$('#_role_add_window').window('open');
			if(data != null){
				$("#_add_pid").val(data.baseroleid);
			}else{
				$("#_add_pid").val("");
			}
			$("#_add_roletype").combobox('setValue','-1');
		}); 
		
		//角色模块新增--保存按钮
		$('#_add_save').bind('click', function(){
			var roletype = $('#_add_roletype').combobox('getValues');
			if(roletype == -1){
				$.messager.alert('系统消息','请选择角色类型!'); 
				return false;
			}
			$('#_role_add_form').form('submit', {   
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){ 
					var data = eval('(' + r + ')');
					if(data.valid){
						if(data.rows > 0 && data.sec){
							$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
							$(this).form('reset');
							closeWindow('_role_add_window',true);
							$('#base_role').tree('reload');
						}else{
							$.messager.alert('系统消息','保存失败!'); 
						}
					}else{
						baseUtil.v(r);
					}
				}   
			});
		});
		
		//角色模块新增--关闭按钮
		$('#_add_close').bind('click', function(){
			closeWindow('_role_add_window',true);
		});
		
		//角色模块编辑--关闭按钮
		$('#_edit_close').bind('click', function(){
			closeWindow('_role_edit_window',true);
		});
		
		//角色模块--编辑按钮
		$('#_west_role_edit').bind('click', function(){
			var data=$('#base_role').tree('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			$('#_role_edit_window').window('open');
			initFormData($('#_role_edit_form'),data);
			setDefaultFormData($("#_role_edit_form"));
		});
		
		//角色模块编辑--保存按钮
		$('#_edit_save').bind('click', function(){
			var roletype = $('#_edit_roletype').combobox('getValues');
			if(roletype == -1){
				$.messager.alert('系统消息','请选择角色类型!'); 
				return false;
			}
			$('#_role_edit_form').form('submit', {   
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){ 
					var data = eval('(' + r + ')');
					if(data.valid){
						if(data.rows > 0 && data.sec){
							$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
							$(this).form('reset');
							closeWindow('_role_edit_window',true);
							$('#base_role').tree('reload');
						}else{
							$.messager.alert('系统消息','保存失败!'); 
						}
					}else{
						baseUtil.v(r);
					}
				}
			});
		});
		
		//编辑页面删除按钮
		$('#_west_role_remove').bind('click', function(){
			initToken();
			var data=$('#base_role').tree('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			var message="你确定要删除选中节点？";
			if(!$('#base_role').tree('isLeaf', data.target))
				message = "该节点含有子节点，若确定删除该节点将同时删除该节点的子节点？";
			$.messager.confirm('确认删除',message,function(r){   
				if(r){
					 $.ajax({
						url:"../TblBaseRoleAction/delete",
						data: {"baseroleid":data.baseroleid},
						type:"post",
						dataType:"json",
						success: function(result){ 
								if(result.sec){
									$.messager.show({title:'系统消息',msg:'删除节点成功！',showType:'slide'});
									$('#base_role').tree('reload');
									$('#_role_user').datagrid('loadData',{total:0,rows:[]});
									$('#_second_role_user').datagrid('loadData',{total:0,rows:[]});
								}else{
									$.messager.alert('系统消息','删除节点失败！');
								}
						}	
					});
				}
			});  
		});
	},
	checkRole:function(value,type){
		var data=$('#base_role').tree('getSelected');
    	var panel=$('#_role_layout').layout('panel','east');
		if(data.rolename == '维护单位管理员'){
			panel.show();
			if(type == 1){
				var queryParams = {'type':type};
				BaseRoleList.loadSecondUser(queryParams,type);
				$('#_second_role_user').datagrid('loadData',{total:0,rows:[]});
			}
			if(type == 2){ 
				var queryParams = {'pbaseuserid':value.baseuserid,'type':type};
				BaseRoleList.loadSecondUser(queryParams,type);
			}
		}else{
			panel.hide();
		}
		if(type == 1){
			if(data.rolename == '现场施工巡查员'){
				$('#_role_add').hide();
		    	$('#_role_remove').hide();
			}else{
				$('#_role_add').show();
		    	$('#_role_remove').show();
			}
		}
	},
	loadSecondUser:function(queryParams,type){
		var url = "../TblBaseUserAction/listSecondUser";
		if(type == 1){
			url = "";
		}
		$('#_second_role_user').datagrid({
    		url:url,
    		queryParams: queryParams,
    		columns:[[
    		      	{field:'ck',checkbox:true},
    				{field:'account',title:'帐号',width:240},
    				{field:'username',title:'用户名',width:240}
    		]],
    		height:$(window).height()-67,
    		rownumbers:true,
    		pagination:true,
    		nowrap:true,
    		method:"POST",
    		pageSize:20,
    	});
	},
	//加载角色树
	loadData:function(){
		initToken();
		$('#base_role').tree({
			url:"../TblBaseRoleAction/selectRoleTree",
			idField:'baseroleid',
		    textField: 'rolename',
		    parentField: 'baserolepid',
		    attributes: ['baseroleid','rolename','baserolepid','baseroletype'],
	        dnd:true,
	        onClick: function(node){
	        	BaseRoleList.checkRole(node,1);
	        	$("#_legend").html(node.text);
	        	$('#_role_add').linkbutton({text:'添加'+node.text});
	        	$('#_role_remove').linkbutton({text:'移除'+node.text});
	        	$("#_user_search_div").show();
	        	$('#_role_user').datagrid({
	        		url:"../TblBaseRoleAction/list",
	        		queryParams: {'baseroleid':node.baseroleid},
	        		columns:[[
	        		        {field:'ck',checkbox:true},
	        				{field:'account',title:'帐号',width:240},
	        				{field:'baseuserid',hidden:true},
	        				{field:'username',title:'用户名',width:240}
	        		]],
	        		onSelect: function(index,row){
	        			BaseRoleList.checkRole(row,2);
	    			},
	        		height:$(window).height()-67,
	        		singleSelect:false,
	        		rownumbers:true,
	        		pagination:true,
	        		nowrap:true,
	        		method:"POST",
	        		pageSize:20,
	        	});
			},
			onDrop:function(target,source,point){
				var targetId = $('#base_role').tree('getNode', target).baseroleid;
				var _pid = targetId;
				var _id = source.baseroleid;
				$.ajax({
					type: "POST",
					dataType: "json",
					url:"../TblBaseRoleAction/move",
					data: {"baseroleid":_id,"baserolepid":_pid},
					success: function(data){
						if(data == 1){
			   	    		$.messager.show({title:'系统消息',msg:'移动节点成功！',showType:'slide'});
				   	    	$('#base_role').tree('reload');
			   	    	}else{
			   	    		$.messager.alert('警告','移动节点失败!'); 
			   	    	}
				   	    	
					}
				});
			},
			onLoadSuccess:function(data){
				var panel=$('#_role_layout').layout('panel','east').hide(); 
			},
		});

		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
	},
	
	loadSecondUserData:function(queryParams){
		$('#_second_unadd_user').datagrid({
			url:"../TblBaseUserAction/unsecondlist",
			queryParams:queryParams,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'account',title:'帐号',width:240},
				{field:'username',title:'用户名',width:240}
			]],
			height:$('#_second_role_user_add').height()-80,
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:10,
		});
	},
	
	loadUserData:function(queryParams){	
		$('#_unadd_user').datagrid({
			url:"../TblBaseRoleAction/unList",
			queryParams: queryParams,
			columns:[[
				{field:'ck',checkbox:true},
				{field:'account',title:'帐号',width:240},
				{field:'username',title:'用户名',width:240}
			]],
			height:$('#_role_user_add').height()-80,
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:10,
		});
	}
}
$(function(){
	BaseRoleList.init();
});