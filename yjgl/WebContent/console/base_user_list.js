var BaseUserList={
		
	//初始化启动
	init:function(){
		BaseUserList.initType();
		BaseUserList.bindEvent();
		BaseUserList.loadDataGird();
	},
		
	//数据字典初始化
	initType:function(){
		BaseType.init('SDZT');
	},
	//绑定事件
	bindEvent:function(){
		
		$('#_baseuser_import').bind('click', function(){ 
			$('#_baseuser_import_window').window('open');
		});
		
		$('#_baseuser_import_cancel_button').bind('click', function(){ 
			$('#_baseuser_import_window').window('close');
		});
		
		$('#_baseuser_import_comfirm_button').bind('click', function(){ 
			var flag = $('#_baseuser_import_form').form('validate');
			if(!flag){
				$.messager.alert('警告','请选择导入文件');
				return $('#_baseuser_import_form').form('validate');
			}
			$.messager.progress({ // 显示进度条  
				title:"导入文件",  
				text:"正在处理...",  
				interval:100  
			});
    	    $('#_baseuser_import_form').ajaxSubmit({
				url:"../TblBaseUserAction/importUserExcelData",
    			type: 'POST',
    			success:function(result){
    			    $.messager.progress('close');  
    				if(result.sec && result.rows>0){
    					$.messager.alert('警告','导入成功！');
    					$('#_baseuser_import_window').window('close');
    					$('#_base_user_list').datagrid('reload');
    				}else{
    					$.messager.alert('警告','导入失败');
    				}
    			}
    		});
			
			
			
			$.ajax({
				type:"POST",
				dataType:"json",
				async: false,
				success: function(result){ 
					if(result.sec){
						$.messager.show({title:'系统消息',msg:'导入成功！',showType:'slide'});
					}
				}	
			});
		});
		
		//查询按钮
		$('#_user_search_button').bind('click', function(){
			var queryParams = {
				'account': $('#_user_search_account').val(),
				'username': $('#_user_search_username').val()
			};
			$('#_base_user_list').datagrid('load',queryParams);
		});
		
		//新增按钮
		$('#_add').bind('click', function(){
			BaseType.initSelectBox('_user_add_state','SDZT', false, false, 60);
			$('#_user_add_state').combobox('setValues','1');
			$('#_add_w').window('open');
		});
		
		//编辑按钮
		$('#_edit').bind('click', function(){
			
			var data=$('#_base_user_list').datagrid('getSelections');
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
			$('#_edit_w').window('open');
			$('#_edit_from').form('clear');
			
			$.ajax({
				url:"../TblBaseUserAction/getByPkey",
				data:{"baseuserid":data[0].baseuserid},
				type:"GET",
				dataType:"json",
				async: false,
				success: function(result,textStatus){ 
					$("#_user_edit_baseuserid").textbox("setValue", result.baseuserid);
					$("#_user_edit_username").textbox("setValue", result.username);
					BaseType.initSelectBox('_user_edit_state','SDZT', false, false, 60);
					$('#_user_edit_state').combobox('setValues',result.state.toString());
				}	
			});
			
		});
		
		//重置按钮
		$('#_user_reset_button').bind('click', function(){
			$("#_user_search_form").form('clear');
		});
		
		//删除按钮
		$('#_delete').bind('click', function(){
			var data=$('#_base_user_list').datagrid('getSelections');
			if(validateMoreRecord(data)){
				debugger
				var str ="";
				for(var i = 0 ; i < data.length;i++)
					str += "&idArray=" + data[i].baseuserid + "&accountArray=" + data[i].account;
				var url = "../TblBaseUserAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.rows > 0 && result.sec){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_base_user_list').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});
		
		//重置密码
		$('#_resertPassword').bind('click', function(){
			var data=$('#_base_user_list').datagrid('getSelections');
			 if(data.length < 1 )
			 {
				 $.messager.alert('警告','请选择一条数据！');
				 return;
			 } else if(data.length > 1 ){
				 $.messager.alert('警告','一次只能重置一条记录！');
				 return;
			 } else{
				 initToken();
				 $.messager.confirm('确认重置','你确定要重置该用户密码为：1?',function(r){ 
					 if(r){
						 $.ajax({
							url:"../TblBaseUserAction/updatePassword",
							data:{"baseuserid":data[0].baseuserid,'password':'1'},
							type:"post",
							dataType:"json",
							async: false,
							success: function(result){ 
								if(result.rows > 0 && result.sec){
									$.messager.show({title:'系统消息',msg:'重置密码成功！',showType:'slide'});
									$('#base_user').datagrid('reload');
								}else{
									$.messager.alert('警告','重置密码失败！');
								}
							}	
						});
					 }
				 });
			 }
		});
		
		//新增取消按钮
		$('#_add_reset').bind('click', function(){
			$('#_add_w').window('close');	
		});
		
		//编辑取消按钮
		$('#_edit_reset').bind('click', function(){
			$('#_edit_w').window('close');	
		});
		
		//修改提交按钮
		$('#_edit_submit').bind('click', function(){
			$('#_edit_form').form('submit',{
				onSubmit: function(param){
					return $(this).form('validate');
			    },
				success:function(r){
					var result = eval('(' + r + ')');
					if(result.sec){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_edit_w').window('close');
						$('#_base_user_list').datagrid('reload');
					}else{
						$.messager.alert('警告','编辑失败！'); 
					}
				}   
			});
		});
		
		//新增提交按钮
		$('#_add_submit').bind('click', function(){
			$('#_add_form').form('submit',{
				onSubmit: function(param){
					var password = $("#password").val();
					var password_again = $("#password_again").val();
					if(password != password_again){
						$.messager.alert('警告','两次输入密码不一致！'); 
						return false;
					}
					return $(this).form('validate');
			    },
				success:function(r){
					var result = eval('(' + r + ')');
					if(result.unique){
						if(result.valid){
							if(result.sec){
								$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
								$(this).form('reset');
								$('#_add_w').window('close');
								$('#_base_user_list').datagrid('reload');
							}else{
								$.messager.alert('警告','保存失败！'); 
							}
						}else{
							baseUtil.v(r);
						}
					}else{
						$.messager.alert('警告','该账号已被使用过了！');   
					}
				}   
			});
		});
	},
	
	loadDataGird:function(){
		initToken();
		$('#_base_user_list').datagrid({
			url:"../TblBaseUserAction/list",
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{field:'account',title:'账号',width:120},
				{field:'username',title:'用户名',width:220},
				{field:'state',title:'有效状态',width:100,
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SDZT", value);
					}
				}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:10
		});
	},
	
}
$(function(){
	BaseUserList.init();
});
