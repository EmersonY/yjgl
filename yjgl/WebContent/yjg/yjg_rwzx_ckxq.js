var YjgRwzxCkxq={
	//初始化启动
	init:function(){
		YjgRwzxCkxq.initStyle();
		YjgRwzxCkxq.initType();
		YjgRwzxCkxq.bindEvent();
	},
	//初始化样式
	initStyle:function(){
		$("#_yjg_rwzx_sjdj_list_view_window").css("width",$(window).width());
		$("#_yjg_rwzx_sjdj_list_view_window").css("height",$(window).height());
		$("#_yjg_rwzx_sjdj_list_merge_window").css("width",$(window).width()/1.7);
		$("#_rwzx_view_north").css("height",$(window).height()/2.5);
		$("#_rwzx_view_center").css("height",$(window).height()/4);
		$("#_rwzx_view_south").css("height",$(window).height()/3);
	},
	
	//数据字典初始化
	initType:function(){
		BaseType.init('JGLX');
		BaseType.init('YZJB');
		BaseType.init('SJXZ');
		BaseType.init('XXLY');
		BaseType.init('SQZT');
	},
	
	//绑定事件
	bindEvent:function(){
		$('#_rwzx_tabs').tabs({
		    border:false,
		    onSelect:function(title){
				var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
				if(data.length > 0){
					YjgRwzxCkxq.loadApplyDatagrid(data);
					YjgRwzxCkxq.loadAssignDatagrid(data);
				}
		    }
		});
		
		//查看认领详情
		$('#_yjg_rwzx_sjdj_list_designate_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			$('#_yjg_rwzx_sjdj_list_designate_window').window('open');
			YjgRwzxCkxq.loadApplyDatagrid(data);
			YjgRwzxCkxq.loadAssignDatagrid(data);
		})
		
		//查看处置进度页面查看附件
		$('#_yjg_rwzx_sjdj_list_view_enclosure_button').bind('click', function(){
			var data=$('#_yjg_rwzx_sjcz_list_view_east_table').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=1&&sjczid='+data[0].sjczid);
		});
		
		//查看页面查看上报附件
		$('#_yjg_rwzx_sjdj_list_view_center_czsb_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=1&&sjdjid='+data[0].sjdjid);
		});
		
		//查看页面查看处理前附件
		$('#_yjg_rwzx_sjdj_list_view_cz_before_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=2&&sjdjid='+data[0].sjdjid);
		});
		
		//查看页面查看处理后附件
		$('#_yjg_rwzx_sjdj_list_view_cz_after_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=3&&sjdjid='+data[0].sjdjid);
		});
	  
		//显示地图信息按钮
		$('#_yjg_rwzx_sjdj_list_view_center_position_button').bind('click', function(){
			$('#_yjg_rwzx_list_location_window').window('open');
			var x = $('#_yjg_rwzx_sjdj_list_view_xzb').val();
			var y = $('#_yjg_rwzx_sjdj_list_view_yzb').val();
			if(x == "" || y == ""){
				$.messager.alert('警告','事件未登记坐标，请先登记'); 
			}else{
				YjgRwzxCkxq.InitLocation(x,y,3,"_yjg_rwzx_sjdj_list",$('#_yjg_rwzx_sjdj_list_view_sjlx').val());
			}
		});
		
		//查看按钮
		$('#_yjg_rwzx_sjdj_list_view_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				BaseType.initSelectBox('_yjg_rwzx_sjdj_list_view_xxly','XXLY', false, false, 80);
				BaseType.initSelectBox('_yjg_rwzx_sjdj_list_view_yzjb','YZJB', false, false, 80);
				BaseType.initSelectEditBox('_yjg_rwzx_sjdj_list_view_jglx','JGLX', true, false , 155, true);
				BaseType.initSelectBox('_yjg_rwzx_sjdj_list_view_sqzt','SQZT', false, false, 80);
				BaseType.initSelectBox('_yjg_rwzx_sjdj_list_view_sjlx','SJXZ', false, false, 80);
				$("#_yjg_rwzx_sjdj_list_view_jglx").combobox({editable: true}); 
				$('#_yjg_rwzx_sjdj_list_view_cz_before_button').show();
				$('#_yjg_rwzx_sjdj_list_view_cz_after_button').show();
				$('#_yjg_rwzx_sjdj_list_designate_button').show();
				$('#_yjg_rwzx_sjdj_list_view_window').window('open');
				initFormData($('#_yjg_rwzx_sjdj_list_view_form'),data[0]);
				setDefaultFormData($('#_yjg_rwzx_sjdj_list_view_form'));
				//0:未处理1:已派单，待确认管辖2:已认领，待确认权责3:无认领，待重新派单（兜底）4:待上报处置情况5:处置完成，待审核6:已兜底7:已解决
				if(data[0].sqzt ==0 || data[0].sqzt ==1 || data[0].sqzt ==2 || data[0].sqzt ==3 || data[0].sqzt ==6){
					$('#_yjg_rwzx_sjdj_list_view_cz_before_button').hide();
				}
				if(data[0].sqzt ==0 || data[0].sqzt ==1 || data[0].sqzt ==2 || data[0].sqzt ==3 || data[0].sqzt ==4 || data[0].sqzt ==6){
					$('#_yjg_rwzx_sjdj_list_view_cz_after_button').hide();
				}
				if(data[0].sqzt ==0){
					$('#_yjg_rwzx_sjdj_list_designate_button').hide();
				}
				$('#_yjg_rwzx_list_designate_button').hide();
				$('#_yjg_rwzx_list_access_designate_button').hide();
				$('#_yjg_rwzx_list_refuse_designate_button').hide();
				$('#_yjg_rwzx_list_access_claim_button').hide();
				$('#_yjg_rwzx_list_refuse_claim_button').hide();
				$('#_yjg_rwzx_list_yesproperty_button').hide();
				$('#_yjg_rwzx_list_fallback_button').hide();
				$('#_yjg_rwzx_list_repair_button').hide();
				$('#_yjg_rwzx_list_comfirm_button').hide();
				
				//权限按钮
				if(data[0].sqzt ==0){
					$('#_yjg_rwzx_list_designate_button').show();
				}
				if(data[0].sqzt ==1){
					$('#_yjg_rwzx_list_access_designate_button').show();
					$('#_yjg_rwzx_list_refuse_designate_button').show();
				}
				if(data[0].sqzt ==2){
					$('#_yjg_rwzx_list_access_claim_button').show();
					$('#_yjg_rwzx_list_refuse_claim_button').show();
				}
				if(data[0].sqzt ==3){
					$('#_yjg_rwzx_list_yesproperty_button').show();
					$('#_yjg_rwzx_list_fallback_button').show();
				}
				if(data[0].sqzt ==4){
					$('#_yjg_rwzx_list_repair_button').show();
				}
				if(data[0].sqzt ==5){
					$('#_yjg_rwzx_list_comfirm_button').show();
				}
				var url="../YjgSjdjAction/listChildSjdj";
				YjgRwzxCkxq.loadYjDatagrid(data[0].sjdjid,url);
				$('#_yjg_rwzx_sjdj_list_view_jjr').textbox('setValue',"");
				$('#_yjg_rwzx_sjdj_list_view_jjbz').textbox('setValue',"");
				$.ajax({
					url:"../YjgSjqqAction/findSjqqBySjdjid",
					data:{'sjdjid':data[0].sjdjid},
					type:"post",
					dataType:"json",
					async: false,
					success: function(result,textStatus){ 
						$('#_yjg_rwzx_sjdj_list_view_jjr').textbox('setValue',result.qsmc);
						$('#_yjg_rwzx_sjdj_list_view_jjbz').textbox('setValue',result.bz);
					}	
				});
				YjgRwzxCkxq.loadSjczDatagrid(data[0].sjdjid);
			}
		}); 
		
		//查看页面脱离按钮
		$('#_yjg_rwzx_sjdj_list_view_center_separate_button').bind('click', function(){
			var sjlx = $('#_sjlx').val();
			var data=$('#_yjg_rwzx_sjdj_list_view_center_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				$.messager.progress({ // 显示进度条  
			        title:"上传图片",  
			            text:"正在处理...",  
			            interval:100  
			    });
				str ="";
				for(var i = 0 ; i < data.length; i++){
					str += "&idArray=" + data[i].sjdjid;
				}
				var url = "../YjgSjdjAction/separate" + "?" + str.substring(1);	
				$.messager.confirm('确认脱离',"您确定要脱离选中事件吗?",function(r){   
					if(r){
						$.ajax({
							url:url,
							data:str,
							type:"post",
							dataType:"json",
							async: false,
							success: function(result,textStatus){ 
								if(result.sec && result.rows > 0){
									$.messager.show({title:'系统消息',msg:'脱离成功！',showType:'slide'});
									$('#_yjg_rwzx_sjdj_list_view_center_table').datagrid('reload');
									$('#_yjg_rwzx_sjdj_list_datagrid').datagrid('reload');
									$('#_yjg_rwzx_sjdj_list_view_window').window('close');
								}else{
									$.messager.alert('警告','脱离失败！');
								}
							}	
						});
					}
				});
				$.messager.progress('close');  
			}
		}); 
		
		//查看页面查看事件处置按钮
		$('#_yjg_rwzx_sjdj_list_view_east_view_button').bind('click', function(){
			var data=$('#_yjg_rwzx_sjcz_list_view_east_table').datagrid('getSelections');
				if(validateOneRecord(data)){
				$('#_yjg_rwzx_sjcz_list_view_window').window('open');
				initFormData($('#_yjg_rwzx_sjcz_list_view_form'),data[0]);
				setDefaultFormData($('#_yjg_rwzx_sjcz_list_view_form'));
			}
		});
		//查看页面取消按钮
		$('#_yjg_rwzx_sjdj_list_view_cancel_button').bind('click', function(){
			$('#_yjg_rwzx_sjcz_list_view_window').window('close');
		});
		
		//查看页面查看事件处置按钮
		$('#_yjg_rwzx_sjdj_list_view_east_viewAll_button').bind('click', function(){
			var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
			YjgRwzxCkxq.loadSjczDatagrid(data[0].sjdjid);
		});
		
	},
	
	//初始化事件位置窗口
	//x,y经度纬度，type操作类型1新增2编辑查看,winID窗口id去掉window,sjlx事件类型1非窨井2窨井;
	InitLocation:function(x,y,type,winID,sjlx){
		switch (type){
		case 1:
			$('#_yjg_rwzx_list_location_iframe').attr("src","../webgis/location/location.html?type=1&x="+ x +"&y="+ y +"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		case 2:
			$('#_yjg_rwzx_list_location_iframe').attr("src","../webgis/location/location.html?type=2&x="+x+"&y="+y+"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		case 3:
			$('#_yjg_rwzx_list_location_iframe').attr("src","../webgis/location/location.html?type=3&x="+x+"&y="+y+"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		}
		
	},
	//加载认领详情
	loadApplyDatagrid:function(data){
		$('#_rwzx_designate_user').datagrid({
			url:"../YjgSjdjUserMappingAction/selectUsered?type=1",
			queryParams: {'sjdjid':data[0].sjdjid,'definitionid':data[0].definitionid},
			method:'POST',
			columns:[[
				{field:'account',title:'帐号',width:150,align:'center'},
				{field:'username',title:'用户名',width:150,align:'center'},
				{field:'sfjd',title:'是否接件',width:100,align:'center',
					formatter:function(value, row, index){
						if(value==0){
							return '未确认';
						}else if(value==1){
							return '是';
						}else{
							return '否';
						}
					}	
				},
				{field:'czsj',title:'操作时间',width:200,align:'center'},
				{field:'jjly',title:'拒绝理由',width:200,align:'center'}
			]],
			singleSelect:false,
			nowrap:true,
		});
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	//加载确认权责详情
	loadAssignDatagrid:function(data){
		$('#_rwzx_assign_user').datagrid({
			url:"../YjgSjdjUserMappingAction/selectUsered?type=2",
			queryParams: {'sjdjid':data[0].sjdjid,'definitionid':data[0].definitionid},
			method:'POST',
			columns:[[
				{field:'account',title:'帐号',width:150,align:'center'},
				{field:'username',title:'用户名',width:150,align:'center'},
				{field:'sfjd',title:'是否接件',width:100,align:'center',
					formatter:function(value, row, index){
						if(value==0){
							return '未确认';
						}else if(value==1){
							return '是';
						}else{
							return '否';
						}
					}	
				},
				{field:'czsj',title:'操作时间',width:200,align:'center'},
				{field:'jjly',title:'拒绝理由',width:200,align:'center'}
			]],
			singleSelect:false,
			nowrap:true,
		});
	},
	//加载重属表格
	loadYjDatagrid:function(sjdjid,url){
		$('#_yjg_rwzx_sjdj_list_view_center_table').datagrid({
			url:url,
			queryParams: {'cssjdjpid':sjdjid },
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:100, field:'sbrxm', title:'上报人姓名'},
				{hidden:false, align:'center', width:100, field:'sbrdh', title:'上报人电话'},
				{hidden:false, align:'center', width:100, field:'xxly', title:'信息来源',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("XXLY", value);
					}
				},
				{hidden:false, align:'center', width:200, field:'scsj', title:'生成时间'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'窨井事件登记编号'},
				{hidden:true, align:'center', width:100, field:'fyjsjdjid', title:'非窨井事件登记编号'},
				{hidden:true, align:'center', width:100, field:'wzms', title:'位置描述'},
				{hidden:true, align:'center', width:100, field:'bz', title:'备注'},
				{hidden:true, align:'center', width:100, field:'xzb', title:'x坐标'},
				{hidden:true, align:'center', width:100, field:'yzb', title:'y坐标'}
			]],
			singleSelect:false,
			rownumbers:true,
			onClickRow:function(index,row){
				YjgRwzxCkxq.loadSjczDatagrid(row.sjdjid);
			}
		});
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	//加载事件处置详情
	loadSjczDatagrid:function(sjdjid){
		$("#_yjg_rwzx_sjcz_list_view_east_table").datagrid({
			url:"../YjgSjczAction/listBySjdjId",
			queryParams: {'sjdjid':sjdjid },
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:true, align:'center', width:100, field:'sjczid', title:'事件处置编号'},
				{hidden:true, align:'center', width:100, field:'czgcms', title:'处置详情'},
				{hidden:true, align:'center', width:100, field:'baseuserid', title:'处置人编号'},
				{hidden:false, align:'center', width:100, field:'czrxm', title:'处置人姓名'},
				{hidden:false, align:'center', width:200, field:'czsj', title:'处置时间'},
				{hidden:false, align:'center', width:100, field:'czr', title:'操作人'},
				{hidden:false, align:'center', width:100, field:'czzt', title:'处置状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("CZZT", value);
					},
					styler: function (value){
						if('0'==value){
							return 'color:#00BFFF';
						}else if('1'==value){
							return 'color:#FF00FF';
						}else if('2'==value){
							return 'color:green';
						}else if('3'==value){
							return 'color:#ff0033';
						}
					}
				},
				{hidden:true, align:'center', width:100, field:'bz', title:'处置备注'},
				{hidden:true, align:'center', width:100, field:'isdel', title:'删除标识'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'},
				{hidden:true, align:'center', width:100, field:'xzb', title:'x坐标'},
				{hidden:true, align:'center', width:100, field:'yzb', title:'y坐标'}
			]],
			singleSelect:false,
			rownumbers:true,
			pagination:false,
			nowrap:true,
		});
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
}

$(function(){
	YjgRwzxCkxq.init();
});