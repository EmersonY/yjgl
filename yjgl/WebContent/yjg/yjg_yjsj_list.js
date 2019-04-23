var YjgYjsjList={
	//初始化启动
	init:function(){
		YjgYjsjList.initType();
		YjgYjsjList.bindEvent();
		YjgYjsjList.loadData();
	},
	//数据字典初始化
	initType:function(){
		BaseType.initSelectBox('_yjg_yjsj_list_dbzt','DBZT', true, false, 105);
		BaseType.init('CZZT');
		BaseType.init('DBZT');
	},
	loadData:function(){
		initToken();
		$('#_yjg_sjqq_list_table').datagrid({
			url:"../YjgSjqqAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:200, field:'sqzt', title:'处理状态',
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
				{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("YZJB", value);
					}
				},
				{hidden:false, align:'center', width:100, field:'qssgmc', title:'施工人'},
				{hidden:false, align:'center', width:200, field:'czsj', title:'接件时间'},
				{hidden:false, align:'center', width:200, field:'dbzt', title:'达标状态',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("DBZT", value);
					}
				},
				{hidden:true, align:'center', width:100, field:'qsmc', title:'权属名称'},
				{hidden:true, align:'center', width:100, field:'czr', title:'操作人'},
				{hidden:true, align:'center', width:100, field:'isdel', title:'删除标识 0：是 1：否'},
				{hidden:true, align:'center', width:100, field:'sjqsid', title:'事件权属编号'},
				{hidden:true, align:'center', width:100, field:'baseuserid', title:'用户信息编号'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'},
				{hidden:true, align:'center', width:100, field:'qsid', title:'权属ID'},
				{hidden:true, align:'center', width:100, field:'taskid'},
				{hidden:true, align:'center', width:100, field:'taskkey'},
				{hidden:true, align:'center', width:100, field:'taskname'},
				{hidden:true, align:'center', width:100, field:'bz', title:'接件备注'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	
	bindEvent:function(){
		//现场处置
		$('#_yjg_sjcz_list_repair_button').bind('click', function(){
			var data=$('#_yjg_sjqq_list_table').datagrid('getSelections');
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
   							$.messager.alert("警告","请先完善事件进度信息！");
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
   	   	   										$('#_yjg_sjcz_list_window').window('close');
   	   	   										$('#_yjg_sjqq_list_table').datagrid('reload');
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
		//查看按钮
		$('#_yjg_yjsj_list_view_button').bind('click', function(){
			var data=$('#_yjg_sjqq_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
			$('#_yjg_yjsj_list_view_window').window('open');
			initFormData($('#_yjg_yjsj_list_view_form'),data[0]);
			setDefaultFormData($('#_yjg_yjsj_list_view_form'));
			}
		}), 
		//选择文件
		$('#_sjcz_choose_file').filebox({ 
		    buttonText: '请选择', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_sjcz_choose_file").textbox("getValue");
	    		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
	    		if(fileType!=".rm" && fileType!=".rmvb" && fileType!=".wmv" && fileType!=".mp4"&& fileType!=".3gp"&& fileType!=".mkv"&& fileType!=".avi"){//验证文件格式
	    			$.messager.alert('警告','文件仅支持rm,rmvb,wmv,mp4,3gp,mkv,avi格式！');
	    			return false;
	    		}
	    		var fileSzie = $(this).next().find('input[id^="filebox_file_id_"]')[0].files[0].size;
	    		if(fileSzie > 20971520){
	    			$.messager.alert('警告','上传文件不可以超过20M！');
	    			return false;
	    		}
	    	    $.messager.progress({ // 显示进度条  
	    	        title:"上传视频",  
	    	            text:"正在处理...",  
	    	            interval:100  
	    	    });  
	    	    $('#_sjcz_choose_file_form').ajaxSubmit({
	    			url:'../TblBaseFileAction/addVedio',
	    			type: 'POST',
	    			success:function(result){
	    			    $.messager.progress('close');  
	    				if(result.sec){
	    					$('#_yjg_sjcz_add_vedio').val(result.fileid);
	    					$.messager.alert('警告','上传成功！');
	    				}else{
	    					$.messager.alert('警告','上传视频出错');
	    				}
	    			}
	    		});
	    	}
		});
		
		//取消
		$('#_yjg_sjcz_list_view_cancle_button').bind('click', function(){ 
			$('#_yjg_sjcz_list_view_window').window('close');	
		});
		
		//附件
		$('#_yjg_sjcz_list_view_file_button').bind('click', function(){ 
			var data=$('#_yjg_sjcz_list_table').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=1&&sjczid='+data[0].sjczid);
		});
		//条件查询按钮事件
		$('#_yjg_sjqq_list_search_button').bind('click', function(){ 
			var timeStart = $("#_yjg_sjqq_list_logtimeStart").datebox("getValue");
			var timeEnd = $("#_yjg_sjqq_list_logtimeEnd").datebox("getValue");
			if(timeStart != "" && timeEnd != "" && timeStart >= timeEnd){
				$.messager.alert('系统消息','开始时间不能晚于或等于结束时间！'); 
				return false;
			}
			var queryParams ={
				"timeStart":timeStart,
				"timeEnd":timeEnd,
				"dbzt":$("#_yjg_yjsj_list_dbzt").combobox('getValue'), 
				"sjdjdh":$("#_yjg_sjqq_list_sjdjdh").val()
			};
			$('#_yjg_sjqq_list_table').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_yjg_sjqq_list_reset_button').bind('click', function(){
			$('#_yjg_sjqq_list_search_form').form('reset');	
			$("#_yjg_yjsj_list_dbzt").combobox('setValue','-1');
		}); 
		
		//查看事件处置按钮
		$('#_yjg_sjqq_list_sjcz_view_button').bind('click', function(){
			var data=$('#_yjg_sjqq_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				$('#_yjg_sjcz_list_repair_button').show();
				if(data[0].sqzt != 4){
					$('#_yjg_sjcz_list_repair_button').hide();
				}
				$('#_yjg_sjcz_list_window').window('open');
				initToken();
				$('#_yjg_sjcz_list_table').datagrid({
					url:"../YjgSjczAction/list",
					queryParams:{'sjdjid':data[0].sjdjid},
					method:'POST',
					columns:[[
						{field:'ck',checkbox:true},
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
									return 'color:#5EA26B';
								}else if('3'==value){
									return 'color:#ff0033';
								}
							}
						},
						{hidden:true, align:'center', width:100, field:'czrbh', title:'处置人编号'},
						{hidden:true, align:'center', width:100, field:'sjczid', title:'事件处置编号'},
						{hidden:true, align:'center', width:100, field:'czgcms', title:'处置过程描述'},
						{hidden:true, align:'center', width:100, field:'baseuserid', title:'处置人编号'},
						{hidden:true, align:'center', width:100, field:'bz', title:'备注'},
						{hidden:true, align:'center', width:100, field:'isdel', title:'删除标识'},
						{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'}
					]],
					rownumbers:true,
					pagination:true,
					nowrap:true,
					pageSize:20,
					onLoadSuccess:function(data){
						if(data.total==0){
							$('#_datagrid_total').val(0);
						}else{
							$('#_datagrid_total').val(1);
						}
					}
				});
				$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
				$('.datagrid-header-inner .datagrid-cell').css("height","auto");
			}
		}); 
		
		//增加页面按钮事件
		$('#_yjg_sjcz_list_add_button').bind('click', function(){
			var data=$('#_yjg_sjqq_list_table').datagrid('getSelections');
			$('#_yjg_sjcz_sjdjid').val(data[0].sjdjid);
			$('#_yjg_sjcz_list_add_window').window('open');
			var total = $('#_datagrid_total').val();
			if(total == 0){
				BaseType.initSelectBox('_yjg_sjcz_list_add_czzt','CZZT', false, false, 55);
				$('#_yjg_sjcz_list_add_czzt').combobox('setValue','0');
				$('#_yjg_sjcz_list_add_czzt').combobox('disable');
			}else{
				BaseType.initSelectBox('_yjg_sjcz_list_add_czzt','CZGC', false, false, 55);
				$('#_yjg_sjcz_list_add_czzt').combobox('setValue','1');
				$('#_yjg_sjcz_list_add_czzt').combobox('enable');
			}
		});

		//增加页面取消按钮事件
		$('#_yjg_sjcz_list_add_cancel_button').bind('click', function(){
			$('#_yjg_sjcz_list_add_window').window('close');	
		});  
		
		//增加页面保存按钮事件
		$('#_yjg_sjcz_list_add_save_button').bind('click', function(){
			$.messager.progress({ // 显示进度条  
		        title:"上传图片",  
		            text:"正在处理...",  
		            interval:100  
		    });
			$('#_yjg_sjcz_list_add_form').form('submit', {   
				url:"../YjgSjczAction/add",
				onSubmit: function(){
					var imageTime = $('#_yjg_sjcz_add_imageTime').val();
					var vedioId = $('#_yjg_sjcz_add_vedio').val();
					var czzt = $('#_yjg_sjcz_list_add_czzt').combobox('getValue');
					var czgcms = $('#_yjg_sjcz_list_add_czgcms').val();
					if(imageTime == 0 && czzt == 0){
						$.messager.alert('警告','处置前状态必须上报图片！'); 
						$.messager.progress('close');
						return false;
					}
					if(imageTime == 0 && czzt == 2){
						$.messager.alert('警告','处置完成状态必须上报图片！');
						$.messager.progress('close');
						return false;
					}
					var flag = $(this).form('validate');
					if(flag){
						$('#_yjg_sjcz_list_add_czzt').combobox('enable');
					}else{
						$.messager.progress('close');
						return $(this).form('validate');
					}
					
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_yjg_sjcz_add_vedio').val("");
						$('#_yjg_sjcz_add_imageIds').val("");
						$('#_yjg_sjcz_add_imageTime').val(0);
						$('.apply_footer_upload_new').remove();
						$('#_sjcz_choose_file_form').form('reset');
						$('#_yjg_sjcz_list_add_window').window('close');
						$('#_yjg_sjcz_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','保存失败！'); 
					}
					$.messager.progress('close');
		   	    } 
			});  
		}); 
		
		//编辑页面按钮事件
		$('#_yjg_sjcz_list_edit_button').bind('click', function(){
			var data=$('#_yjg_sjcz_list_table').datagrid('getSelections');
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
			$("<div></div>").dialog({
				id : '_yjg_sjcz_list_edit_window',
				title:'编辑',
				width : 500,
				height: 550,
				modal:true,
				href : '../YjgSjczAction/editView?sjczid='+data[0].sjczid+'&sjdjid='+data[0].sjdjid,
				onBeforeClose:function(){
					var imgTime = $('.apply_footer_upload_sjcz_edit_new').length;
					var czzt = $('#_czztVal').val();
					if(imgTime == 0 && czzt == 0){
						$.messager.alert('警告','处置前状态必须上报图片！'); 
						return false;
					}
					if(imgTime == 0 && czzt == 2){
						$.messager.alert('警告','已完成状态必须上报图片！'); 
						return false;
					}
				},
				onClose : function() {
                    $(this).dialog('destroy');
                }
			});
		});
		 
		//删除按钮
		$('#_yjg_sjcz_list_delete_button').bind('click', function(){
			var data=$('#_yjg_sjcz_list_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				if(data[0].czzt == 0){
					$.messager.alert("警告","'处置前'进度不可删除！");
					return false;
				}
				var str ="";
				for(var i = 0 ; i < data.length; i++)
					str += "&idArray=" + data[i].sjczid;
				var url = "../YjgSjczAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_yjg_sjcz_list_table').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		}); 
		
		//查看按钮
		$('#_yjg_sjcz_list_view_button').bind('click', function(){
			var data=$('#_yjg_sjcz_list_table').datagrid('getSelections');
			if(validateOneRecord(data)){
			$('#_yjg_sjcz_list_view_window').window('open');
			initFormData($('#_yjg_sjcz_list_view_form'),data[0]);
			setDefaultFormData($('#_yjg_sjcz_list_view_form'));
			}
		}); 
	},
	//现场上传图片
	insertImg:function(ele){
		var imageTime=$('#_yjg_sjcz_add_imageTime').val();
		if(imageTime>2){
			$.messager.alert('警告','图片最多只能三张！');
			return false;
		}
		var form=$(ele).parent().parent().parent();
		var imgUrl = form.find("#_yjg_sjcz_add_imageFile").val();//获取图片的url路径
		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
		fileType = fileType.toLowerCase();
		fileType = fileType.toLowerCase();
		if(fileType!=".jpg" && fileType!=".jpeg" && fileType!=".gif" && fileType!=".png"){//验证文件格式
			$.messager.alert('警告','文件仅支持jpg,gif,png格式！');
			return false;
		}
	    $.messager.progress({ // 显示进度条  
	        title:"上传图片",  
	            text:"正在处理...",  
	            interval:100  
	    });  
		form.ajaxSubmit({
			url:'../TblBaseFileAction/czAddImg',
			type: 'POST',
			success:function(result){
			    $.messager.progress('close');  
				if(result.sec){
					var imageIds = $('#_yjg_sjcz_add_imageIds').val();
					var html = $("#_sjcz_sjdj_img").render(result);
					form.append(html);
					imageTime++;
					$('#_yjg_sjcz_add_imageIds').val(imageIds+result.fileid+",");
					$('#_yjg_sjcz_add_imageTime').val(imageTime);
				}else{
					$.messager.alert('警告','图片保存出错');
				}
			}
		});
	},
	//删除照片
	deleteImg:function(ele,fileid){
		var imageTime=$('#_yjg_sjcz_add_imageTime').val();
		var imageIds = $('#_yjg_sjcz_add_imageIds').val();
		$.messager.confirm('确认删除',"您确定要删除选中图片吗?",function(r){  
			if(r){
				$.ajax({  
					type:"POST",  
	                url: "../TblBaseFileAction/deleteImg",  
	                data:{"fileid":fileid},  
	                success:function(result){  
	                	if(result.sec && result.rows > 0){
							$.messager.show({title:'系统消息',msg:'删除图片成功！',showType:'slide'});
	                		$(ele).parent().parent().remove();
    						imageIds = imageIds.replace(fileid+",","");
	    					imageTime--;
	    					$('#_yjg_sjcz_add_imageIds').val(imageIds);
	    					$('#_yjg_sjcz_add_imageTime').val(imageTime);
						}else{
							$.messager.alert('警告','删除图片失败！');
						}
	                }  
                });
			}
		});
	},
	//加载指派申请接件人
	loadApplyDatagrid:function(data){
		$('#_list_designate_user').datagrid({
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
			rownumbers:true,
			nowrap:true,
		});
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	}
}

$(function(){
	YjgYjsjList.init();
});