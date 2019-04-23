$(document).ready(function(){
	BaseType.init('SFBS');
	BaseType.initSelectBox('_base_bbgl_add_sfqzgx','SFBS', false, false, 60);
	initToken();
	$('#_base_bbgl_table').datagrid({
		url:"../YjgAppversionAction/list",
		queryParams:{},
		method:'POST',
		columns:[[
			{hidden:true, align:'center', width:100, field:'id', title:'编号'},
			{hidden:false, align:'center', width:100, field:'versionname', title:'版本名称'},
			{hidden:false, align:'center', width:100, field:'versioncode', title:'版本号'},
			{hidden:false, align:'center', width:100, field:'sfqzgx', title:'是否强制更新',
				formatter:function(value,row,index){
					return BaseType.getNameByValue("SFBS", value);
				}	
			},
			{hidden:false, align:'center', width:100, field:'updateperson', title:'更新人'},
			{hidden:false, align:'center', width:200, field:'updatetime', title:'更新时间'},
			{hidden:false, align:'center', width:200, field:'versioncontent', title:'版本更新内容'}
		]],
		singleSelect:true,
		rownumbers:true,
		pagination:true,
		nowrap:true,
		pageSize:20,
	});
	$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
	$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	
	//条件查询按钮事件
	$('#_base_bbgl_search_button').bind('click', function(){ 
		var queryParams ={};
		$('#_base_bbgl_table').datagrid('load',queryParams);
	});
	
	//条件查询按钮事件
	$('#_base_bbgl_download_button').bind('click', function(){ 
		var data=$('#_base_bbgl_table').datagrid('getSelections');
		if(data.length < 1)
		{
			$.messager.alert('警告','请选择一条数据！');
			return;
		}
		window.open("../YjgAppversionAction/downloadApp?id=" + data[0].id,'下载APP');
	});
	
	$('#_base_bbgl_file').filebox({ 
	    buttonText: '请选择', 
	    buttonAlign: 'left', 
    	onChange:function(){
    		var url = $("#_base_bbgl_file").textbox("getValue");//获取图片的url路径
    		var fileType = url.substring(url.lastIndexOf("."), url.length);
    		if(fileType != ""){
    			fileType = fileType.toLowerCase();
	    		if(fileType!=".apk"){//验证文件格式
					$.messager.alert('警告','文件仅支持apk格式！');
	    			$("#_base_bbgl_file").textbox('setValue',"");
					return false;
				}
	    		var fileSzie = $(this).next().find('input[id^="filebox_file_id_"]')[0].files[0].size;
	    		if(fileSzie > 50971520){
	    			$.messager.alert('警告','上传文件不可以超过50M！');
	    			$("#_base_bbgl_file").textbox('setValue',"");
	    			return false;
	    		}
    		}
    	}
	});
	
	//重置按钮
	$('#_base_bbgl_reset_button').bind('click', function(){
		$('#_base_bbgl_search_form').form('reset');	
	}); 
	
	//增加页面按钮事件
	$('#_base_bbgl_add_button').bind('click', function(){ 
		$("#_base_bbgl_add_sfqzgx").combobox('setValue','1');
		$('#_base_bbgl_add_window').window('open');
	});

	//增加页面取消按钮事件
	$('#_base_bbgl_add_cancel_button').bind('click', function(){
		$('#_base_bbgl_add_window').window('close');	
	});  
	
	//增加页面保存按钮事件
	$('#_base_bbgl_add_save_button').bind('click', function(){  
	    $.messager.progress({ // 显示进度条  
	        title:"上传APP",  
	            text:"正在处理...",  
	            interval:100  
	    }); 
		$('#_base_bbgl_add_form').form('submit', {   
			onSubmit: function(){
				if(!$(this).form('validate')){
					$.messager.progress('close');  
					return $(this).form('validate');
				}
			},   
			success:function(r){   
				var result = eval('(' + r + ')');
				if(result.sec && result.rows > 0){
					$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					$(this).form('reset');
					$('#_base_bbgl_add_window').window('close');
					$('#_base_bbgl_table').datagrid('reload');
				}else{
					$.messager.alert('警告','保存失败！'); 
				}
				$.messager.progress('close');  
	   	    } 
		});  
	}); 
	
	//编辑页面按钮事件
	$('#_base_bbgl_edit_button').bind('click', function(){ 
		var data=$('#_base_bbgl_table').datagrid('getSelections');
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
		$('#_base_bbgl_edit_window').window('open');
		$('#_base_bbgl_edit_form').form('reset');
		initFormData($('#_base_bbgl_edit_form'),data[0]);
		setDefaultFormData($('#_base_bbgl_edit_form'));
	});
	 
	//编辑页面取消按钮事件
	$('#_base_bbgl_edit_cancel_button').bind('click', function(){
		$('#_base_bbgl_edit_window').window('close');
	});  
	 
	//编辑页面保存按钮事件
	$('#_base_bbgl_edit_save_button').bind('click', function(){
		$('#_base_bbgl_edit_form').form('submit', {   
			url:"../YjgAppversionAction/edit",
			onSubmit: function(){
				return $(this).form('validate');
			},   
			success:function(r){   
				var result = eval('(' + r + ')');
				if(result.sec && result.rows > 0){
					$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
					$(this).form('reset');
					$('#_base_bbgl_edit_window').window('close');
					$('#_base_bbgl_table').datagrid('reload');
				}else{
					$.messager.alert('警告','编辑失败！'); 
				}
	   	    } 
		});  
	}); 

	//删除按钮
	$('#_base_bbgl_delete_button').bind('click', function(){
		var data=$('#_base_bbgl_table').datagrid('getSelections');
		if(validateMoreRecord(data)){
			var str ="";
			for(var i = 0 ; i < data.length; i++)
				str += "&idArray=" + data[i].id;
			var url = "../YjgAppversionAction/deleteBatch" + "?" + str.substring(1);	
			deleteBatch(url,null,function(result){
				if(result.sec && result.rows > 0){
					$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
					$('#_base_bbgl_table').datagrid('reload');
				}else{
					$.messager.alert('警告','删除记录失败！');
				}
			});
		}
	});  
	
	//查看按钮
	$('#_base_bbgl_view_button').bind('click', function(){
		var data=$('#_base_bbgl_table').datagrid('getSelections');
		if(validateOneRecord(data)){
		$('#_base_bbgl_view_window').window('open');
		initFormData($('#_base_bbgl_view_form'),data[0]);
		setDefaultFormData($('#_base_bbgl_view_form'));
		}
	}); 
});


