var SjwhJgxlglList={
	//初始化启动
	init:function(){
		SjwhJgxlglList.initType();
		SjwhJgxlglList.bindEvent();
		SjwhJgxlglList.loadData();
	},
	//初始化事件位置窗口
	//x,y经度纬度，type操作类型1新增2编辑查看,winID窗口id去掉window,sjlx事件类型1非窨井2窨井;
	InitLocation:function(x,y,type,winID,sjlx){
		switch (type){
		case 1:
			$('#_sjwh_jgxlgl_list_location_iframe').attr("src","../webgis/location/location.html?type=1&x="+ x +"&y="+ y +"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		case 2:
			$('#_sjwh_jgxlgl_list_location_iframe').attr("src","../webgis/location/location.html?type=2&x="+x+"&y="+y+"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		case 3:
			$('#_sjwh_jgxlgl_list_location_iframe').attr("src","../webgis/location/location.html?type=3&x="+x+"&y="+y+"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		}
	},
	//数据字典初始化
	initType:function(){
		BaseType.initSelectEditBox('_sjwh_jgxlgl_list_search_jglx','JGLX', true, false , 155, true);
		BaseType.initSelectEditBox('_sjwh_jgxlgl_list_search_ssdl','SSDL', false, true, 130, true);
		BaseType.initSelectBox('_sjwh_jgxlgl_list_search_jgxz','JGXZ', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgxlgl_list_search_jgcz','JGCZ', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgxlgl_list_search_sfzw','SFZW', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgxlgl_list_search_jgzt','JGZT', true, false, 130);
	},
	//绑定事件
	bindEvent:function(){
		
		//导出excel
		$('#_sjwh_jgxlgl_list_export_button').bind('click', function(){
			var data=$('#_sjwh_jgxlgl_list_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str = "";
				for(var i=0; i < data.length; i++){
					str += "&idArray=" + data[i].jgid;
				}
				window.open("../YjgJgxxAction/exportExcelData" + "?" + str.substring(1),'导出Excel');
			}
		});
		
		//上传Excel文件
		$('#_sjwh_import_choose_file').filebox({ 
		    buttonText: '请选择Excel文件', 
		    buttonAlign: 'left', 
		    multiple:'true',
		    accept : [ 'application/vnd.ms-excel','application/vnd.openxmlformats-officedocument.spreadsheetml.sheet']  
		});
		
		//导入取消界面
		$('#_sjwh_jgxlgl_list_import_cancel_button').bind('click', function(){ 
			$('#_sjwh_jgxlgl_list_import_window').window('close');
		});
		
		//导入保存
		$('#_sjwh_jgxlgl_list_import_comfirm_button').bind('click', function(){
			var flag = $('#_sjwh_choose_file_form').form('validate');
			if(!flag){
				$.messager.alert('警告','请选择导入文件');
				return $('#_sjwh_choose_file_form').form('validate');
			}
			$.messager.progress({ // 显示进度条  
				title:"导入文件",  
				text:"正在处理...",  
				interval:100  
			});
    	    $('#_sjwh_choose_file_form').ajaxSubmit({
    			url:'../YjgJgxxAction/importExcelData',
    			type: 'POST',
    			success:function(result){
    			    $.messager.progress('close');  
    				if(result.sec && result.rows>0){
    					$.messager.alert('警告','导入成功！');
    					$("#_sjwh_import_choose_file").textbox('setValue',"");
    					$('#_sjwh_jgxlgl_list_import_window').window('close');
    					$('#_sjwh_jgxlgl_list_table').datagrid('reload');
    				}else{
    					$.messager.alert('警告','导入失败');
    				}
    			}
    		});
		});
		
		//导入界面
		$('#_sjwh_jgxlgl_list_import_button').bind('click', function(){ 
			$('#_sjwh_jgxlgl_list_import_window').window('open');
		});
		
		//条件查询按钮事件
		$('#_sjwh_jgxlgl_list_search_button').bind('click', function(){ 
			var queryParams ={
				"jglx":$("#_sjwh_jgxlgl_list_search_jglx").combobox('getValue'), 
				"jgxz":$("#_sjwh_jgxlgl_list_search_jgxz").combobox('getValue'), 
				"jgcz":$("#_sjwh_jgxlgl_list_search_jgcz").combobox('getValue'), 
				"sfzw":$("#_sjwh_jgxlgl_list_search_sfzw").combobox('getValue'),
				"jgzt":$("#_sjwh_jgxlgl_list_search_jgzt").combobox('getValue'),
				"jggg":$("#_sjwh_jgxlgl_list_search_jggg").val(),
				"jngj":$("#_sjwh_jgxlgl_list_search_jngj").val(),
				"jgbh":$("#_sjwh_jgxlgl_list_search_jgbh").val(),
				"js":$("#_sjwh_jgxlgl_list_search_js").val(),
				"jgsl":$("#_sjwh_jgxlgl_list_search_jgsl").val(),
				"gldw":$("#_sjwh_jgxlgl_list_search_gldw").val(),
				"qsdw":$("#_sjwh_jgxlgl_list_search_qsdw").val(),
				"ssdl":$("#_sjwh_jgxlgl_list_search_ssdl").combobox('getValues').toString()
			};
			$('#_sjwh_jgxlgl_list_table').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_sjwh_jgxlgl_list_reset_button').bind('click', function(){
			$('#_sjwh_jgxlgl_list_search_form').form('reset');	
			$("#_sjwh_jgxlgl_list_search_jglx").combobox('setValue','-1');
			$("#_sjwh_jgxlgl_list_search_jgxz").combobox('setValue','-1');
			$("#_sjwh_jgxlgl_list_search_jgcz").combobox('setValue','-1');
			$("#_sjwh_jgxlgl_list_search_sfzw").combobox('setValue','-1');
			$("#_sjwh_jgxlgl_list_search_jgzt").combobox('setValue','-1');
		}); 
		

		//删除按钮
		$('#_sjwh_jgxlgl_list_delete_button').bind('click', function(){
			var data=$('#_sjwh_jgxlgl_list_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length; i++)
					str += "&idArray=" + data[i].jgid;
				var url = "../YjgJgxxAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_sjwh_jgxlgl_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});  
		
		//查看按钮
		$('#_sjwh_jgxlgl_list_view_button').bind('click', function(){
			$("#_sjwh_jgxlgl_list_view_nbtp_div").show();
			$("#_sjwh_jgxlgl_list_view_jjtp_div").show();
			$("#_sjwh_jgxlgl_list_view_yjtp_div").show();
			$("#_sjwh_jgxlgl_list_view_nbtp_no_div").show();
			$("#_sjwh_jgxlgl_list_view_jjtp_no_div").show();
			$("#_sjwh_jgxlgl_list_view_yjtp_no_div").show();
			var data=$('#_sjwh_jgxlgl_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				$('#_sjwh_jgxlgl_list_view_window').window('open');
				initFormData($('#_sjwh_jgxlgl_list_view_form'),data[0]);
				setDefaultFormData($('#_sjwh_jgxlgl_list_view_form'));
			}
			$.ajax({
 				url:"../YjgJgxxAction/findJgImg",     
 				data:{'jgid':data[0].jgid},
 				type:"post",
 				dataType:"json",
 				async: false,
 				success: function(result){ 
 					if(typeof(result.nbPathName)=="undefined"){
 						$("#_sjwh_jgxlgl_list_view_nbtp_div").hide();
 					}else{
 						$("#_sjwh_jgxlgl_list_view_nbtp").attr("src",result.nbPathName);
 						$("#_sjwh_jgxlgl_list_view_nbtp_no_div").hide();
 					}
 					if(typeof(result.jjPathName)=="undefined"){
 						$("#_sjwh_jgxlgl_list_view_jjtp_div").hide();
 					}else{
 						$("#_sjwh_jgxlgl_list_view_jjtp").attr("src",result.jjPathName);
 						$("#_sjwh_jgxlgl_list_view_jjtp_no_div").hide();
 					}
 					if(typeof(result.yjPathName)=="undefined"){
 						$("#_sjwh_jgxlgl_list_view_yjtp_div").hide();
 					}else{
 						$("#_sjwh_jgxlgl_list_view_yjtp").attr("src",result.yjPathName);
 						$("#_sjwh_jgxlgl_list_view_yjtp_no_div").hide();
 					}
 				}	
 			});
		}); 
		
		//详情查看位置按钮
		$('#_sjwh_jgxlgl_list_view_wz_button').bind('click', function(){ 
			$('#_sjwh_jgxlgl_list_location_window').window('open');
			var x = $('#_sjwh_jgxlgl_list_view_xzb').val();
			var y = $('#_sjwh_jgxlgl_list_view_yzb').val();
			if(x == "" || y == ""){
				$.messager.alert('警告','井盖未登记坐标，请先登记'); 
			}else{
				SjwhJgxlglList.InitLocation(x,y,3,"_sjwh_jgxlgl_list_view",1);
			}
		});
		
		//详情取消按钮
		$('#_sjwh_jgxlgl_list_view_cancel_button').bind('click', function(){ 
			$('#_sjwh_jgxlgl_list_view_window').window('close');
		});	
	},
	//加载表格数据
	loadData:function(){
		initToken();
		$('#_sjwh_jgxlgl_list_table').datagrid({
			url:"../YjgJgxxAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:150, field:'jgbh', title:'井盖编号'},
				{hidden:false, align:'center', width:100, field:'jglx', title:'井盖类型'},
				{hidden:false, align:'center', width:100, field:'jngj', title:'井内管径'},
				{hidden:false, align:'center', width:100, field:'jgcz', title:'井盖材质'},
				{hidden:false, align:'center', width:100, field:'jggg', title:'井盖规格'},
				{hidden:false, align:'center', width:100, field:'jgxz', title:'井盖形状'},
				{hidden:false, align:'center', width:100, field:'js', title:'井盖深度'},
				{hidden:false, align:'center', width:100, field:'jgsl', title:'井盖数量'},
				{hidden:false, align:'center', width:100, field:'jgzt', title:'井盖状态'},
				{hidden:false, align:'center', width:100, field:'sfzw', title:'防坠网状态'},
				{hidden:false, align:'center', width:200, field:'gldw', title:'管理单位'},
				{hidden:false, align:'center', width:200, field:'qsdw', title:'权属单位'},
				{hidden:false, align:'center', width:100, field:'ssdl', title:'所属道路'},
				{hidden:false, align:'center', width:200, field:'dljssj', title:'道路建设时间'},
				{hidden:false, align:'center', width:100, field:'czr', title:'操作人'},
				{hidden:false, align:'center', width:200, field:'czsj', title:'操作时间'},
				{hidden:true, align:'center', width:100, field:'xzqh', title:'行政区划'},
				{hidden:true, align:'center', width:100, field:'jgid', title:'井盖ID'},
				{hidden:true, align:'center', width:100, field:'rtzt', title:'是否导入图层0:否1:是'},
				{hidden:true, align:'center', width:100, field:'yzb', title:'Y坐标'},
				{hidden:true, align:'center', width:100, field:'xzb', title:'X坐标'}		          
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	//查看高清图片
	findHighDefinition:function(type){
		openWindow('_sjwh_list_look_img_window',"图片查看");
		$("#_sjwh_list_look_img").attr("src",""); //清除缓存
		if(type==1){
			$("#_sjwh_list_look_img").attr("src",$("#_sjwh_jgxlgl_list_view_nbtp")[0].src);
		}else if(type==2){
			$("#_sjwh_list_look_img").attr("src",$("#_sjwh_jgxlgl_list_view_jjtp")[0].src);
		}else if(type==3){
			$("#_sjwh_list_look_img").attr("src",$("#_sjwh_jgxlgl_list_view_yjtp")[0].src);
		}
	}
}
$(function(){
	SjwhJgxlglList.init();
});