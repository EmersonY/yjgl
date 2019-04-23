var YjgTxlList={
		//初始化启动
		init:function(){
			YjgTxlList.initType();
			YjgTxlList.bindEvent();
			YjgTxlList.loadData();
		},
		initType:function(){
			 
		},
		bindEvent:function(){
			

				//导入保存
				$('#_xtgl_txl_list_import_comfirm_button').bind('click', function(){
					var flag = $('#_xtgl_choose_file_form').form('validate');
					if(!flag){
						$.messager.alert('警告','请选择导入文件');
						return $('#_xtgl_choose_file_form').form('validate');
					}
					$.messager.progress({ // 显示进度条  
						title:"导入文件",  
						text:"正在处理...",  
						interval:100  
					});
		    	    $('#_xtgl_choose_file_form').ajaxSubmit({
		    			url:'../YjgTxlAction/importTxlExcelData',
		    			type: 'POST',
		    			success:function(result){
		    			    $.messager.progress('close');  
		    				if(result.sec && result.rows>0){
		    					$.messager.alert('警告','导入成功！');
		    					$("#_xtgl_import_choose_file").textbox('setValue',"");
		    					$('#_xtgl_txl_list_import_window').window('close');
		    					$('#_xtgl_txl_table').datagrid('reload');
		    				}else{
		    					$.messager.alert('警告','导入失败');
		    				}
		    			}
		    		});
				});
			
				//上传Excel文件
				$('#_xtgl_import_choose_file').filebox({ 
				    buttonText: '请选择Excel文件', 
				    buttonAlign: 'left', 
				    multiple:'true',
				    accept : [ 'application/vnd.ms-excel','application/vnd.openxmlformats-officedocument.spreadsheetml.sheet']  
				});
				
				//导入界面
				$('#_xtgl_txl_list_import_button').bind('click', function(){ 
					$('#_xtgl_txl_list_import_window').window('open');
				});
				
				//导入取消界面
				$('#_xtgl_txl_list_import_cancel_button').bind('click', function(){ 
					$('#_xtgl_txl_list_import_window').window('close');
				});
				
				//条件查询按钮事件
				$('#_xtgl_txl_search_button').bind('click', function(){ 
					var queryParams ={
							'txlxm':$('#_xtgl_txl_txlxm').val(),
							'txlgs':$('#_xtgl_txl_txlgs').val(),
							'txljob':$('#_xtgl_txl_txljob').val(),
							'txldh':$('#_xtgl_txl_txldh').val()
					};
					$('#_xtgl_txl_table').datagrid('load',queryParams);
				});
				
				//重置按钮
				$('#_xtgl_txl_reset_button').bind('click', function(){
					$('#_xtgl_txl_search_form').form('reset');	
				}); 
				
				//增加页面按钮事件
				$('#_xtgl_txl_add_button').bind('click', function(){ 
					$('#_xtgl_txl_add_window').window('open');
				});
			
				//增加页面取消按钮事件
				$('#_xtgl_txl_add_cancel_button').bind('click', function(){
					$('#_xtgl_txl_add_window').window('close');	
				});  
				
				//增加页面保存按钮事件
				$('#_xtgl_txl_add_save_button').bind('click', function(){   
					$('#_xtgl_txl_add_form').form('submit', {   
						url:"../YjgTxlAction/add",
						onSubmit: function(){
							return $(this).form('validate');
						},   
						success:function(r){   
							var result = eval('(' + r + ')');
							if(result.sec && result.rows > 0){
								$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
								$(this).form('reset');
								$('#_xtgl_txl_add_window').window('close');
								$('#_xtgl_txl_table').datagrid('reload');
							}else{
								$.messager.alert('警告','保存失败！'); 
							}
				   	    } 
					});  
				}); 
				
				//编辑页面按钮事件
				$('#_xtgl_txl_edit_button').bind('click', function(){ 
					var data=$('#_xtgl_txl_table').datagrid('getSelections');
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
					$('#_xtgl_txl_edit_window').window('open');
					$('#_xtgl_txl_edit_form').form('reset');
					$("#_edit_txlid").val(data[0].txlid);
					initFormData($('#_xtgl_txl_edit_form'),data[0]);
					setDefaultFormData($('#_xtgl_txl_edit_form'));
				});
				 
				//编辑页面取消按钮事件
				$('#_xtgl_txl_edit_cancel_button').bind('click', function(){
					$('#_xtgl_txl_edit_window').window('close');
				});  
				 
				//编辑页面保存按钮事件
				$('#_xtgl_txl_edit_save_button').bind('click', function(){
					$('#_xtgl_txl_edit_form').form('submit', {   
						url:"../YjgTxlAction/edit",
						onSubmit: function(){
							return $(this).form('validate');
						},   
						success:function(r){   
							var result = eval('(' + r + ')');
							if(result.sec && result.rows > 0){
								$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
								$(this).form('reset');
								$('#_xtgl_txl_edit_window').window('close');
								$('#_xtgl_txl_table').datagrid('reload');
							}else{
								$.messager.alert('警告','编辑失败！'); 
							}
				   	    } 
					});  
				}); 
			
				//删除按钮
				$('#_xtgl_txl_delete_button').bind('click', function(){
					var data=$('#_xtgl_txl_table').datagrid('getSelections');
					if(validateMoreRecord(data)){
						var str ="";
						for(var i = 0 ; i < data.length; i++)
							str += "&idArray=" + data[i].txlid;
						var url = "../YjgTxlAction/deleteBatch" + "?" + str.substring(1);	
						deleteBatch(url,null,function(result){
							if(result.sec && result.rows > 0){
								$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
								$('#_xtgl_txl_table').datagrid('reload');
							}else{
								$.messager.alert('警告','删除记录失败！');
							}
						});
					}
				});  
				
				//查看按钮
				$('#_xtgl_txl_view_button').bind('click', function(){
					var data=$('#_xtgl_txl_table').datagrid('getSelections');
					if(validateOneRecord(data)){
					$('#_xtgl_txl_view_window').window('open');
					initFormData($('#_xtgl_txl_view_form'),data[0]);
					setDefaultFormData($('#_xtgl_txl_view_form'));
					}
				}); 
		},
		loadData:function(){
			initToken();
			$('#_xtgl_txl_table').datagrid({
				url:"../YjgTxlAction/list",
				queryParams:{},
				method:'POST',
				columns:[[
					{field:'ck',checkbox:true},
					{hidden:false, align:'center', width:100, field:'txlxm', title:'姓名'},
					{hidden:false, align:'center', width:150, field:'txlgs', title:'单位名称'},
					{hidden:false, align:'center', width:150, field:'txljob', title:'单位职务'},
					{hidden:false, align:'center', width:100, field:'txlxzqh', title:'邮编'},
					{hidden:false, align:'center', width:150, field:'txldh', title:'电话'},
					{hidden:false, align:'center', width:220, field:'txlemail', title:'邮箱'},
					{hidden:false, align:'center', width:200, field:'txlbz', title:'备注'}
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
	YjgTxlList.init();
});
