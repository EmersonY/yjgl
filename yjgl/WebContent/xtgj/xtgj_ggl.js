var YjggglList={
		//初始化启动
		init:function(){
			YjggglList.initType();
			YjggglList.bindEvent();
			YjggglList.loadData();
		},
		initType:function(){
			 
		},
		bindEvent:function(){
		
			//条件查询按钮事件
			$('#_yjg_ggl_search_button').bind('click', function(){ 
				var ggltimeStart = $("#yjg_ggltimeStart").val();
				var ggltimeEnd = $("#yjg_ggltimeEnd").val(); 
				var queryParams ={
					'gglnr':$("#yjg_ggl_gglnr").val(),
					'czrxm':$("#yjg_ggl_czrxm").val(),
					'ggltimeStart':ggltimeStart,
					'ggltimeEnd':ggltimeEnd
				};
				$('#_yjg_ggl_table').datagrid('load',queryParams);
			});
			
			//重置按钮
			$('#_yjg_ggl_reset_button').bind('click', function(){
				$('#_yjg_ggl_search_form').form('reset');	
			}); 
			
			//增加页面按钮事件
			$('#_yjg_ggl_add_button').bind('click', function(){ 
				$("#_yjg_ggl_add_iframe").attr("src","../xtgj/xtgj_ggl_add_textarea.jsp");
				$('#_yjg_ggl_add_window').window('open');
			});
		
			//增加页面取消按钮事件
			$('#_yjg_ggl_add_cancel_button').bind('click', function(){
				$("#_yjg_ggl_add_iframe").contents().find("#_yjg_ggl_add_gglnr").html("");
				$("#_yjg_ggl_add_iframe").contents().find(".ke-edit-iframe").contents().find(".ke-content").html("");
				$('#_yjg_ggl_add_window').window('close');	
				$("#_yjg_ggl_add_iframe").att("src","");
			});  
			
			//增加页面保存按钮事件
			$('#_yjg_ggl_add_save_button').bind('click', function(){   
				var add_context = $("#_yjg_ggl_add_iframe").contents().find("#_yjg_ggl_add_gglnr");
				$("#_yjg_ggl_add_gglnr").val(add_context.val());
				$('#_yjg_ggl_add_form').form('submit', {   
					url:"../YjgGglAction/add",
					onSubmit: function(){
						return $(this).form('validate');
					},   
					success:function(r){   
						var result = eval('(' + r + ')');
						if(result.sec && result.rows > 0){
							$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
							$(this).form('reset');
							$('#_yjg_ggl_add_window').window('close');
							$('#_yjg_ggl_table').datagrid('reload');
							$("#_yjg_ggl_add_iframe").contents().find("#_yjg_ggl_add_gglnr").html("");
							$("#_yjg_ggl_add_iframe").contents().find(".ke-edit-iframe").contents().find(".ke-content").html("");
							$("#_yjg_ggl_add_iframe").att("src","");
						}else{
							$.messager.alert('警告','保存失败！'); 
						}
			   	    } 
				});  
			}); 
			
			//编辑页面按钮事件
			$('#_yjg_ggl_edit_button').bind('click', function(){ 
				var data=$('#_yjg_ggl_table').datagrid('getSelections');
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
				$('#_yjg_ggl_edit_window').window('open');
				$('#_yjg_ggl_edit_form').form('reset');
				$("#_edit_gglid").val(data[0].gglid);
				initFormData($('#_yjg_ggl_edit_form'),data[0]);
				setDefaultFormData($('#_yjg_ggl_edit_form'));
				$('#_yjg_ggl_edit_iframe').attr("src","../YjgGglAction/showedit?gglid="+data[0].gglid);
			});
			 
			//编辑页面取消按钮事件
			$('#_yjg_ggl_edit_cancel_button').bind('click', function(){
				$('#_yjg_ggl_edit_iframe').attr("src","");
				$('#_yjg_ggl_edit_window').window('close');
			});  
			 
			//编辑页面保存按钮事件
			$('#_yjg_ggl_edit_save_button').bind('click', function(){
				var edit_context = $("#_yjg_ggl_edit_iframe").contents().find("#_yjg_ggl_edit_gglnr");
				$("#_yjg_ggl_edit_gglnr").val(edit_context.val());
				$('#_yjg_ggl_edit_form').form('submit', {   
					url:"../YjgGglAction/edit",
					onSubmit: function(){
						return $(this).form('validate');
					},   
					success:function(r){   
						var result = eval('(' + r + ')');
						if(result.sec && result.rows > 0){
							$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
							$(this).form('reset');
							$('#_yjg_ggl_edit_window').window('close');
							$('#_yjg_ggl_table').datagrid('reload');
							$("#_yjg_ggl_edit_iframe").contents().find("#_yjg_ggl_edit_gglnr").html("");
							$("#_yjg_ggl_edit_iframe").contents().find(".ke-edit-iframe").contents().find(".ke-content").html("");
							$("#_yjg_ggl_edit_iframe").att("src","");
						}else{
							$.messager.alert('警告','编辑失败！'); 
						}
			   	    } 
				});  
			}); 
		
			//删除按钮
			$('#_yjg_ggl_delete_button').bind('click', function(){
				var data=$('#_yjg_ggl_table').datagrid('getSelections');
				if(validateMoreRecord(data)){
					var str ="";
					for(var i = 0 ; i < data.length; i++)
						str += "&idArray=" + data[i].gglid;
					var url = "../YjgGglAction/deleteBatch" + "?" + str.substring(1);	
					deleteBatch(url,null,function(result){
						if(result.sec && result.rows > 0){
							$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
							$('#_yjg_ggl_table').datagrid('reload');
						}else{
							$.messager.alert('警告','删除记录失败！');
						}
					});
				}
			});  
			
			//查看按钮
			$('#_yjg_ggl_view_button').bind('click', function(){
				var data=$('#_yjg_ggl_table').datagrid('getSelections');
				if(validateOneRecord(data)){
				$('#_yjg_ggl_view_window').window('open');
				initFormData($('#_yjg_ggl_view_form'),data[0]);
				setDefaultFormData($('#_yjg_ggl_view_form'));
				}
			});
		},
		loadData:function(){
			initToken();
			$('#_yjg_ggl_table').datagrid({
				url:"../YjgGglAction/list",
				queryParams:{},
				method:'POST',
				columns:[[
					{field:'ck',checkbox:true},
					{hidden:false, align:'center', width:300, field:'gglbt', title:'公告标题'},
					{hidden:false, align:'center', width:300,MaxHeight :50, field:'gglnr', title:'公告内容'},
					{hidden:false, align:'center', width:150, field:'czrxm', title:'操作人姓名'},
					{hidden:false, align:'center', width:200, field:'czsj', title:'操作时间'},
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
	YjggglList.init();
});

