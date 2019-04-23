var YjgRwzxList={
	init:function(){
		YjgRwzxList.initType();
		YjgRwzxList.bindEvent();
	},
	//数据字典初始化
	initType:function(){
		BaseType.init('XXLY');
		BaseType.init('SFBS');
	},
	//绑定事件
	bindEvent:function(){
		
		//事件派单
		$('#_yjg_rwzx_list_designate_button').bind('click', function(){
			debugger
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=0){
					$.messager.alert("警告","该事件不处于'未处理'状态！");
					return false;
				}
				YjgRwzxList.loadForm(0);
			}
		})
		
		//管辖事件
		$('#_yjg_rwzx_list_access_designate_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=1){
					$.messager.alert("警告","该事件不处于'已派单，待确认管辖'状态！");
					return false;
				}
				YjgRwzxList.loadForm(0);
			}
		})
		//拒绝管辖
		$('#_yjg_rwzx_list_refuse_designate_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=1){
					$.messager.alert("警告","该事件不处于'已派单，待确认管辖'状态！");
					return false;
				}
				$('#_rwzx_bz_div').show();
				YjgRwzxList.loadForm(1);
				$.messager.alert("温馨提示","如果没有权属单位接件，流程则进入'无认领，待重新派单（兜底）'状态");
			}
			$('#_rwzx_assignee_div').hide();
			$('#_first_deny').val(1);
		})
		//确认权责
		$('#_yjg_rwzx_list_access_claim_button').bind('click', function(){
			debugger
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=2){
					$.messager.alert("警告","该事件不处于'已认领，待确认权责'状态！");
					return false;
				}
				$('#_rwzx_assignee_div').hide();
				$('#_rwzx_bz_div').show();
				YjgRwzxList.nextTaskKey($('#_yjg_rwzx_list_table'),0);
				YjgRwzxList.openWin();
			}
		})
		//否认权责
		$('#_yjg_rwzx_list_refuse_claim_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=2){
					$.messager.alert("警告","该事件不处于'已认领，待确认权责'状态！");
					return false;
				}
				$('#_rwzx_bz_div').show();
				YjgRwzxList.nextTaskKey($('#_yjg_rwzx_list_table'),1);
				YjgRwzxList.openWin();
				$('#_rwzx_assignee_div').hide();
				$('#_first_deny').val(2);
			}
		})
		
		//现场处置
		$('#_yjg_rwzx_list_repair_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=4){
					$.messager.alert("警告","该事件不处于'待上报处置情况'状态！");
					return false;
				}
				$.ajax({
        		    url:"../YjgSjczAction/selectByCzzt",
   					data:{'sjdjid':data[0].sjdjid},
   					type:"post",
   					dataType:"json",
   					async: false,
   					success: function(result){ 
   						if(!result){
   							$.messager.alert("警告","请先到'已接事件'模块中的'上报处置情况'完善事件进度信息！");
   							return false;
   						}else{
   							$.messager.confirm('确认上报',"您确定上报处置进度？",function(r){ 
   								if(r){
   									data = {'sjdjid':data[0].sjdjid,'taskid':data[0].taskid};
   	   	   							url = "../YjgSjqqAction/repair";
   	   	   							$.ajax({
   	   	   								url:url,
   	   	   								data:data,
   	   	   								type:"post",
   	   	   								dataType:"json",
   	   	   								async: false,
   	   	   								success: function(result){ 
   	   	   									if(result.sec && result.rows > 0){
   	   	   										$.messager.show({title:'系统消息',msg:'处理成功！',showType:'slide'});
   	   	   										$('#_yjg_rwzx_sjdj_list_view_window').window('close');
   	   	   										$('#_yjg_rwzx_list_table').datagrid('reload');
   	   	   									}else{
   	   	   										$.messager.alert('警告','处理失败！');
   	   	   									}
   	   	   								}	
   	   	   							});
   								}
   							});
   						}
   					}	
        	   });
			}
		})
		
		//现场确认
		$('#_yjg_rwzx_list_comfirm_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=5){
					$.messager.alert("警告","该事件不处于'处置完成，待审核'状态！");
					return false;
				}
				BaseType.initSelectBox('_rwzx_dbzt','SFDBZT', false, false, 55);
				$('#_rwzx_dbzt').combobox('setValue', '1');
				$('#_rwzx_assignee_div').hide();
				$('#_rwzx_dbzt_div').show();
				YjgRwzxList.loadForm(0);
			}
		})
		
		//现场确认---达标按钮
		$("#_rwzx_dbzt").combobox({  
	       onSelect: function (n,o) {
	           if (n.name == "达标") {  
	        	   YjgRwzxList.nextTaskKey($('#_yjg_rwzx_list_table'),0);
	        	   $('#_rwzx_assignee_div').hide();
	        	   $('#_rwzx_bz_div').hide();
	           }  
	           else {  
	        	   $('#_rwzx_bz_div').show();
	        	   YjgRwzxList.nextTaskKey($('#_yjg_rwzx_list_table'),1);
	           }  
	       }  
		}) 
		
		//明确权属派单
		$('#_yjg_rwzx_list_yesproperty_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].sqzt!=3){
					$.messager.alert("警告","该事件不处于'无认领，待重新派单（兜底）'状态！");
					return false;
				}
				$('#_rwzx_qsrl').val(1);
				YjgRwzxList.loadForm(1);
			}
		})
		
		//不明确权属兜底
		$('#_yjg_rwzx_list_fallback_button').bind('click', function(){
			var sjdata=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(sjdata)){
				var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
				if(validateOneRecord(data)){
					if(data[0].sqzt!=3){
						$.messager.alert("警告","该事件不处于'无认领，待重新派单（兜底）'状态！");
						return false;
					}
					$.messager.confirm('确认兜底',"您确定要兜底选中记录吗?",function(r){
						if(r){
							$.messager.progress({ // 显示进度条  
								title:"兜底",  
								text:"正在处理...",  
								interval:100  
							});
							$.ajax({
								url:"../YjgSjdjAction/fallback",
								data:{'sjdjid':sjdata[0].sjdjid,'taskid':sjdata[0].taskid},
								type:"post",
								dataType:"json",
								async: false,
								success: function(result){ 
									if(result.sec && result.rows > 0){
										$.messager.show({title:'系统消息',msg:'处理成功！',showType:'slide'});
										$('#_rwzx_bz').textbox('reset');
										$('#_yjg_rwzx_sjdj_list_view_window').window('close');
										$('#_yjg_rwzx_list_table').datagrid('reload');
										$('#_rwzx_dbzt_div').hide();
									}else{
										if(result.msg!=null){
											$.messager.alert('警告',result.msg);
										}else{
											$.messager.alert('警告','处理失败！');
										}
									}
								}	
							});
							$.messager.progress('close');
						}
					})
				}
			}
		}) 
		
		//关闭按钮
		$('#_rollback_cancel_button').bind('click', function(){
			$('#_rollback_window').window('close');
		}) 
		
		//提交
		$('#_rwzx_save_button').bind('click', function(){
			debugger
			var sjdata=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			var assignee = $('#_rwzx_assignee').combobox('getValues');
			var opinion = $('#_rwzx_bz').textbox('getValue'); 
			var taskkey = $('#_rwzx_taskkey').val();
			if(assignee.length == 0 && taskkey != 'czqksb' && taskkey != 'cxpd' && taskkey != 'czqkqr' && taskkey != 'endevent1'){
				$.messager.alert('警告','请选择接收人！');
				return false;
			}
			$.messager.progress({ // 显示进度条  
		        title:"提交",  
		            text:"正在处理...",  
		            interval:100  
		    });
			
			var	url;
			var	data;
			var str="";
			
			if(taskkey == 'qzqr'){
				data = {"sjdjid":sjdata[0].sjdjid,"taskid":sjdata[0].taskid};
				for(var i = 0 ; i < assignee.length;i++){
					str += "&idArray=" + assignee[i];
				}
				url = "../YjgSjdjAction/taskAssign" + "?" + str.substring(1);
			}else if(taskkey == 'sjrl'){
				var qsrl = $('#_rwzx_qsrl').val();
				if(qsrl == 1){
					data = {'sjdjid':sjdata[0].sjdjid,'taskid':sjdata[0].taskid};
					for(var i = 0 ; i < assignee.length;i++){
						str += "&idArray=" + assignee[i];
					}
					url = "../YjgSjdjUserMappingAction/yesproperty"+ "?" + str.substring(1);
					$('#_rwzx_qsrl').val(0);
				}else{
					data = {"sjdjid":sjdata[0].sjdjid,"taskid":sjdata[0].taskid};
					for(var i = 0 ; i < assignee.length;i++){
						str += "&idArray=" + assignee[i];
					}
					url = "../YjgSjdjUserMappingAction/firstYesproperty" + "?" + str.substring(1);
				}
			}else if(taskkey == 'cxpd'){
				var firstDeny = $('#_first_deny').val();
				data = {"sjdjid":sjdata[0].sjdjid,"taskid":sjdata[0].taskid,"opinion":opinion};
				url = "../YjgSjdjAction/sendAgain?firstDeny="+firstDeny;
			}else if(taskkey == 'czqksb'){
				var dbzt = $("#_rwzx_dbzt").combobox('getValue');
				if(dbzt == "2"){
					data = {"opinion":opinion,'sjdjid':sjdata[0].sjdjid,'taskid':sjdata[0].taskid,'taskEntitys[0].taskkey':taskkey,'taskEntitys[0].assignee':assignee[0]};
					url = "../YjgSjdjAction/comfirmRefuse";
				}else{
					data = {'bz':opinion,'baseuserid':assignee[0],'sjdjid':sjdata[0].sjdjid,'taskid':sjdata[0].taskid,'taskEntitys[0].assignee':assignee[0],'taskEntitys[0].taskkey':taskkey};
					url = "../YjgSjqqAction/accesssj";
				}
			}else if(taskkey == 'endevent1'){
				data = {'sjdjid':sjdata[0].sjdjid,'taskid':sjdata[0].taskid};
				url = "../YjgSjdjAction/comfirmPass";
			}
			
			$.ajax({
				url:url,
				data:data,
				type:"post",
				dataType:"json",
				async: false,
				success: function(result){ 
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'处理成功！',showType:'slide'});
						$('#_rwzx_bz').textbox('reset');
						$('#_rwzx_assignee').textbox('reset');
						$('#_rwzx_dbzt_div').hide();
						$('#_rwzx_form').form('reset');
						$('#_yjg_rwzx_sjdj_list_view_window').window('close');
						$('#_rwzx_window').window('close');
						$('#_yjg_rwzx_list_table').datagrid('reload');
					}else{
						if(result.msg!=null){
							$.messager.alert('警告',result.msg);
						}else{
							$.messager.alert('警告','处理失败！');
						}
					}
				}	
			});
			$.messager.progress('close');
		})
		//关闭按钮
		$('#_rwzx_cancel_button').bind('click', function(){
			$('#_rwzx_window').window('close');
		})
		
		//选项卡切换
		$('#_tasktab').tabs({
		    onSelect:function(title){
		    	YjgRwzxList.loadData();
		    }
		});
		
	},
	//加载表格数据
	loadData:function(){
		debugger
		initToken();
		$('#_yjg_rwzx_list_table').datagrid({
			url:"../YjgSjdjAction/listTask",
			queryParams:{},
			method:'POST',
			columns:[[
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:200, field:'scsj', title:'事件生成时间'},
				{hidden:false, align:'center', width:200, field:'updatetime', title:'事件更新时间'},
				{hidden:false, align:'center', width:120, field:'sbrxm', title:'上报人姓名'},
				{hidden:false, align:'center', width:220, field:'sqzt', title:'事件状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SQZT", value);
					},
					styler: function (value){
						if('0'==value ){
							return 'color:red';
						}else if('6'==value){
							return 'color:lightGray';
						}else if('7'==value){
							return 'color:green';
						}
					}
				},
				{hidden:false, align:'center', width:100, field:'xxly', title:'信息来源',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("XXLY", value);
					}
				},
				{hidden:false, align:'center', width:100, field:'jglx', title:'井盖类型'},
				{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("YZJB", value);
					}
				},
				{hidden:false, align:'center', width:100, field:'ssdl', title:'所属道路'},
				{hidden:false, align:'center', width:80, field:'isline', title:'转办单',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SFBS", value);
					}
				},
				{hidden:false, align:'center', width:300, field:'wzms', title:'位置描述'},
				{hidden:false, align:'center', width:300, field:'bz', title:'基本情况'},
				{hidden:true, align:'center', width:100, field:'taskid'},
				{hidden:true, align:'center', width:100, field:'taskkey'},
				{hidden:true, align:'center', width:100, field:'taskname'},
				{hidden:true, align:'center', width:100, field:'definitionid'},
				{hidden:true, align:'center', width:100, field:'assignee'},
				{hidden:true, align:'center', width:100, field:'instanceid'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'},
				{hidden:true, align:'center', width:100, field:'xzb', title:'X坐标'},
				{hidden:true, align:'center', width:100, field:'yzb', title:'Y坐标'}
			]],
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		
		$('#_yjg_ybrwzx_list_table').datagrid({
			url:"../YjgSjdjAction/listTasked",
			queryParams:{},
			method:'POST',
			columns:[[
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:100, field:'taskname',title:'节点名称'},
				{hidden:false, align:'left', width:200, field:'taskstarttime', title:'环节开始时间'},
				{hidden:false, align:'left', width:200, field:'taskendtime', title:'环节结束时间'},
				{hidden:false, align:'center', width:200, field:'scsj', title:'事件生成时间'},
				{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("YZJB", value);
					}
				},
				{hidden:false, align:'center', width:220, field:'sqzt', title:'事件状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SQZT", value);
					},
					styler: function (value){
						if('0'==value ){
							return 'color:red';
						}else if('6'==value){
							return 'color:lightGray';
						}else if('7'==value){
							return 'color:green';
						}
					}
				},
				{hidden:false, align:'center', width:80, field:'isline', title:'转办单',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SFBS", value);
					}
				},
				{hidden:true, align:'center', width:100, field:'taskid'},
				{hidden:true, align:'center', width:100, field:'taskkey'},
				{hidden:true, align:'center', width:100, field:'definitionid'},
				{hidden:true, align:'center', width:100, field:'assignee'},
				{hidden:true, align:'center', width:100, field:'instanceid'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		
		$('#_yjg_bjrwzx_list_table').datagrid({
			url:"../YjgSjdjAction/listBjtask",
			queryParams:{},
			method:'POST',
			columns:[[
				/*{field:'ck',checkbox:true},*/
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:100, field:'taskname',title:'节点名称'},
				{hidden:false, align:'left', width:200, field:'taskstarttime', title:'环节开始时间'},
				{hidden:false, align:'left', width:200, field:'taskendtime', title:'环节结束时间'},
				{hidden:false, align:'center', width:200, field:'scsj', title:'事件生成时间'},
				{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("YZJB", value);
					}
				},
				{hidden:false, align:'center', width:220, field:'sqzt', title:'事件状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SQZT", value);
					},
					styler: function (value){
						if('0'==value ){
							return 'color:red';
						}else if('6'==value){
							return 'color:lightGray';
						}else if('7'==value){
							return 'color:green';
						}
					}
				},
				{hidden:true, align:'center', width:100, field:'taskid'},
				{hidden:true, align:'center', width:100, field:'taskkey'},
				{hidden:true, align:'center', width:100, field:'definitionid'},
				{hidden:true, align:'center', width:100, field:'assignee'},
				{hidden:true, align:'center', width:100, field:'instanceid'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		
		$('#_yjg_rwzx_assigen_table_selected').datagrid({
			url:"",
			columns:[[
			    {field:'ck',checkbox:true},
			    {hidden:true, align:'center', width:200, field:'baseuserid', title:'接件人ID'},
				{hidden:false, align:'center', width:200, field:'username', title:'接件人名称'}
			]],
			rownumbers:true,
			pagination:false,
			nowrap:true,
			singleSelect:false,
			border:false,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	//加载表单
	loadForm:function(type){
		YjgRwzxList.nextTaskKey($('#_yjg_rwzx_list_table'),type);
		if(typeof($("._yjg_rwzx_assignTools")[1])!="undefined"){
			$("._yjg_rwzx_assignTools")[1].remove();
		}
		BaseType.initAssign("#_rwzx_assignee", "#_yjg_rwzx_assignTools");
		BaseType.searAssign("#_rwzx_assignee", "#_yjg_rwzx_assign_sear");
		YjgRwzxList.openWin();
	},
	//查询流程下一步
	nextTaskKey:function(targetDatagrid,type){
		var str = "";
		var data=targetDatagrid.datagrid('getSelections');
		var json = {"taskid":data[0].taskid,"sjdjid":data[0].sjdjid,"type":type};
		var url = "../ActTaskAction/selectNextKey";
		ajax(url,json,function(result){
			$('#_rwzx_taskkey').val(result[type].taskkey);
		})
	},
	//打开窗口
	openWin:function(){
		$('#_rwzx_window').window({
			onBeforeClose:YjgRwzxList.onBeforeClose,
		}).window('open');
	},
	//关闭事件
	onBeforeClose:function(){
		$('#_rwzx_assignee_div').show();
		$('#_rwzx_dbzt_div').hide();
		$('#_rwzx_bz_div').hide();
	}
}

$(function(){
	YjgRwzxList.init();
});