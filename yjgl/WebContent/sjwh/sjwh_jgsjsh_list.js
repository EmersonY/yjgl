var SjwhJgsjshList={
	//初始化启动
	init:function(){
		SjwhJgsjshList.initType();
		SjwhJgsjshList.bindEvent();
		SjwhJgsjshList.loadData();
	},
	initType:function(){
		BaseType.init('SHZT');
		BaseType.initSelectBox('_sjwh_jgsjsh_list_search_jgxz','JGXZ', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgsjsh_list_search_jgcz','JGCZ', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgsjsh_list_search_sfzw','SFZW', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgsjsh_list_search_shzt','SHZT', true, false, 80);
		BaseType.initSelectBox('_sjwh_jgsjsh_list_view_shzt','SHZT', true, false, 80);
		BaseType.initSelectEditBox('_sjwh_jgsjsh_list_search_ssdl','SSDL', false, true, 155, true);
		BaseType.initSelectEditBox('_sjwh_jgsjsh_list_search_jglx','JGLX', true, false , 155, true);
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
	bindEvent:function(){
		//审核通过
		$('#_sjwh_jgsjsh_list_pass_button').bind('click', function(){ 
			var data=$('#_sjwh_jgsjsh_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].shzt != 0){
					$.messager.alert('警告',"'未确认'状态才可进行审核通过操作");
					return false;
				}
				$.messager.confirm('确认审核通过',"您确定要审核通过选中记录吗?",function(r){  
					if(r){
			    	    $.messager.progress({ // 显示进度条  
			    	        title:"审核通过",  
			    	            text:"正在处理...",  
			    	            interval:100  
			    	    }); 
						ajax("../YjgLsjgxxAction/pass",{'lsjgid':data[0].lsjgid},function(result){
							if(result.sec && result.rows > 0){
								$.messager.show({title:'系统消息',msg:'操作成功！',showType:'slide'});
								$('#_sjwh_jgsjsh_list_table').datagrid('reload');
							}else{
								if("undefined" != typeof(result.errorMsg)){
									$.messager.alert('警告',result.errorMsg);
								}else{
									$.messager.alert('警告','操作失败！');
								}
							}
						})
						$.messager.progress('close'); 
					}
				});
			}
		});
		
		//审核不通过
		$('#_sjwh_jgsjsh_list_refuse_button').bind('click', function(){ 
			var data=$('#_sjwh_jgsjsh_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].shzt != 0){
					$.messager.alert('警告',"'未确认'状态才可进行审核不通过操作");
					return false;
				}
				$.messager.confirm('确认审核不通过',"您确定要审核不通过选中记录吗?",function(r){  
					if(r){
			    	    $.messager.progress({ // 显示进度条  
			    	        title:"审核不通过",  
		    	            text:"正在处理...",  
		    	            interval:100  
			    	    }); 
						ajax("../YjgLsjgxxAction/refuse",{'lsjgid':data[0].lsjgid},function(result){
							if(result.sec && result.rows > 0){
								$.messager.show({title:'系统消息',msg:'操作成功！',showType:'slide'});
								$('#_sjwh_jgsjsh_list_table').datagrid('reload');
							}else{
								$.messager.alert('警告','操作失败！');
							}
						})
						$.messager.progress('close'); 
					}
				});
			}
		});
		
		$('#_sjwh_jgsjsh_list_view_cancel_button').bind('click', function(){ 
			$('#_sjwh_jgsjsh_list_view_window').window('close');
		});
		
		//详情查看位置按钮
		$('#_sjwh_jgsjsh_list_view_wz_button').bind('click', function(){ 
			$('#_sjwh_jgxlgl_list_location_window').window('open');
			var x = $('#_sjwh_jgsjsh_list_view_lsxzb').val();
			var y = $('#_sjwh_jgsjsh_list_view_lsyzb').val();
			if(x == "" || y == "" || x == undefined || y == undefined){
				$.messager.alert('警告','井盖未登记坐标，请先登记'); 
			}else{
				SjwhJgsjshList.InitLocation(x,y,3,"_sjwh_jgsjsh_list_view",1);
			}
		});
		
		//条件查询按钮事件
		$('#_sjwh_jgsjsh_list_search_button').bind('click', function(){ 
			var timeStart = $("#_sjwh_search_sbsj_timeStart").datebox("getValue");
			var timeEnd = $("#_sjwh_search_sbsj_timeEnd").datebox("getValue");
			var timeStartCopy = $("#_sjwh_search_shsj_timeStart").datebox("getValue");
			var timeEndCopy = $("#_sjwh_search_shsj_timeEnd").datebox("getValue");
			if(timeStart != "" && timeEnd != "" && timeStart >= timeEnd){
				$.messager.alert('系统消息','上报时间的开始时间不能晚于或等于结束时间！'); 
				return false;
			}
			if(timeStartCopy != "" && timeEndCopy != "" && timeStartCopy >= timeEndCopy){
				$.messager.alert('系统消息','审核时间的开始时间不能晚于或等于结束时间！'); 
				return false;
			}
			var queryParams ={
				"lsjglx":$("#_sjwh_jgsjsh_list_search_jglx").combobox('getValue'), 
				"lsjgxz":$("#_sjwh_jgsjsh_list_search_jgxz").combobox('getValue'), 
				"lsjgcz":$("#_sjwh_jgsjsh_list_search_jgcz").combobox('getValue'), 
				"lssfzw":$("#_sjwh_jgsjsh_list_search_sfzw").combobox('getValue'),
				"shzt":$("#_sjwh_jgsjsh_list_search_shzt").combobox('getValue'),
				"lsjngj":$("#_sjwh_jgsjsh_list_search_jngj").val(),
				"lsjggg":$("#_sjwh_jgsjsh_list_search_jggg").val(), 
				"sbr":$("#_sjwh_jgsjsh_list_search_sbr").val(),
				"shrxm":$("#_sjwh_jgsjsh_list_search_shr").val(),
				"lsjgsl":$("#_sjwh_jgsjsh_list_search_lsjgsl").val(),
				"lsjs":$("#_sjwh_jgsjsh_list_search_lsjs").val(),
				"timeStart":timeStart,
				"timeEnd":timeEnd,
				"timeStartCopy":timeStartCopy,
				"timeEndCopy":timeEndCopy,
				"lsssdl":$("#_sjwh_jgsjsh_list_search_ssdl").combobox('getValues').toString()
			};
			$('#_sjwh_jgsjsh_list_table').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_sjwh_jgsjsh_list_reset_button').bind('click', function(){
			$('#_sjwh_jgsjsh_list_search_form').form('clear');	
			$("#_sjwh_jgsjsh_list_search_jglx").combobox('setValue','-1');
			$("#_sjwh_jgsjsh_list_search_jgxz").combobox('setValue','-1');
			$("#_sjwh_jgsjsh_list_search_jgcz").combobox('setValue','-1');
			$("#_sjwh_jgsjsh_list_search_sfzw").combobox('setValue','-1');
			$("#_sjwh_jgsjsh_list_search_shzt").combobox('setValue','-1');
		}); 
		
		//删除按钮
		$('#_sjwh_jgsjsh_list_delete_button').bind('click', function(){
			var data=$('#_sjwh_jgsjsh_list_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length; i++)
					str += "&idArray=" + data[i].lsjgid;
				var url = "../YjgLsjgxxAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_sjwh_jgsjsh_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});  
		
		//查看按钮
		$('#_sjwh_jgsjsh_list_view_button').bind('click', function(){
			debugger
			var data=$('#_sjwh_jgsjsh_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				$('#_sjwh_jgsjsh_list_view_window').window('open');
				initFormData($('#_sjwh_jgsjsh_list_view_form'),data[0]);
				setDefaultFormData($('#_sjwh_jgsjsh_list_view_form'));
				$("#_sjwh_jgsjsh_list_view_nbtp_div").show();
				$("#_sjwh_jgsjsh_list_view_jjtp_div").show();
				$("#_sjwh_jgsjsh_list_view_yjtp_div").show();
				$("#_sjwh_jgsjsh_list_view_nbtp_no_div").show();
				$("#_sjwh_jgsjsh_list_view_jjtp_no_div").show();
				$("#_sjwh_jgsjsh_list_view_yjtp_no_div").show();
				$.ajax({
     				url:"../YjgLsjgxxAction/findLsjgImg",     
     				data:{'lsjgid':data[0].lsjgid},
     				type:"post",
     				dataType:"json",
     				async: false,
     				success: function(result){ 
     					if(typeof(result.nbPathName)=="undefined"){
     						$("#_sjwh_jgsjsh_list_view_nbtp_div").hide();
     					}else{
     						$("#_sjwh_jgsjsh_list_view_nbtp").attr("src",result.nbPathName);
     						$("#_sjwh_jgsjsh_list_view_nbtp_no_div").hide();
     					}
     					if(typeof(result.jjPathName)=="undefined"){
     						$("#_sjwh_jgsjsh_list_view_jjtp_div").hide();
     					}else{
     						$("#_sjwh_jgsjsh_list_view_jjtp").attr("src",result.jjPathName);
     						$("#_sjwh_jgsjsh_list_view_jjtp_no_div").hide();
     					}
     					if(typeof(result.yjPathName)=="undefined"){
     						$("#_sjwh_jgsjsh_list_view_yjtp_div").hide();
     					}else{
     						$("#_sjwh_jgsjsh_list_view_yjtp").attr("src",result.yjPathName);
     						$("#_sjwh_jgsjsh_list_view_yjtp_no_div").hide();
     					}
     				}	
     			});
			}
		}); 
	},
	//加载表格数据
	loadData:function(){
		initToken();
		$('#_sjwh_jgsjsh_list_table').datagrid({
			url:"../YjgLsjgxxAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:true, align:'center', width:150, field:'lsjgbh', title:'井盖编号'},
				{hidden:false, align:'center', width:100, field:'sbr', title:'上报人'},
				{hidden:false, align:'center', width:200, field:'sbsj', title:'上报时间'},
				{hidden:false, align:'center', width:100, field:'shzt', title:'审核状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SHZT", value);
					}
				},
				{hidden:false, align:'center', width:100, field:'shrxm', title:'审核人'},
				{hidden:false, align:'center', width:200, field:'shsj', title:'审核时间'},
				{hidden:false, align:'center', width:100, field:'lsjglx', title:'井盖类型'},
				{hidden:false, align:'center', width:100, field:'lsjngj', title:'井内管径'},
				{hidden:false, align:'center', width:100, field:'lsjgcz', title:'井盖材质'},
				{hidden:false, align:'center', width:100, field:'lsjggg', title:'井盖规格'},
				{hidden:false, align:'center', width:100, field:'lsjgxz', title:'井盖形状'},
				{hidden:false, align:'center', width:100, field:'lsjs', title:'井盖深度'},
				{hidden:false, align:'center', width:100, field:'lsjgsl', title:'井盖数量'},
				{hidden:false, align:'center', width:100, field:'lsjgzt', title:'井盖状态'},
				{hidden:false, align:'center', width:100, field:'lssfzw', title:'防坠网状态'},
				{hidden:false, align:'center', width:200, field:'lsgldw', title:'管理单位'},
				{hidden:false, align:'center', width:200, field:'lsqsdw', title:'权属单位'},
				{hidden:false, align:'center', width:100, field:'lsssdl', title:'所属道路'},
				{hidden:true, align:'center', width:200, field:'lsdljssj', title:'道路建设时间'},
				{hidden:true, align:'center', width:100, field:'lsxzqh', title:'行政区划'},
				{hidden:true, align:'center', width:100, field:'lsyzb', title:'Y坐标'},
				{hidden:true, align:'center', width:100, field:'lsxzb', title:'X坐标'},
				{hidden:true, align:'center', width:100, field:'isdel', title:'删除状态'},
				{hidden:true, align:'center', width:100, field:'lsjgid', title:'井盖ID'}
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
			$("#_sjwh_list_look_img").attr("src",$("#_sjwh_jgsjsh_list_view_nbtp")[0].src);
		}else if(type==2){
			$("#_sjwh_list_look_img").attr("src",$("#_sjwh_jgsjsh_list_view_jjtp")[0].src);
		}else if(type==3){
			$("#_sjwh_list_look_img").attr("src",$("#_sjwh_jgsjsh_list_view_yjtp")[0].src);
		}
	}
}
$(function(){
	SjwhJgsjshList.init();
});