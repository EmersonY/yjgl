var YjgSjdjList={
	//初始化启动
	init:function(){
		YjgSjdjList.initStyle();
		YjgSjdjList.initType();
		YjgSjdjList.loadData();
		YjgSjdjList.bindEvent();
	},
	//初始化样式
	initStyle:function(){
		$("#_yjg_sjdj_list_view_window").css("width",$(window).width());
		$("#_yjg_sjdj_list_view_window").css("height",$(window).height());
		$("#_yjg_sjdj_list_merge_window").css("width",$(window).width()/1.7);
		$("#_view_north").css("height",$(window).height()/2.5);
		$("#_view_center").css("height",$(window).height()/4);
		$("#_view_south").css("height",$(window).height()/3);
	},
	
	//数据字典初始化
	initType:function(){
		BaseType.initSelectBox('_yjg_sjdj_list_sqzt','SQZT', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_sjlx','SJXZ', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_xxly','XXLY', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_yzjb','YZJB', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_jgxz','JGXZ', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_jgzt','JGZT', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_jgcz','JGCZ', true, false, 105);
		BaseType.initSelectBox('_yjg_sjdj_list_sfzw','SFZW', true, false, 105);
		BaseType.initSelectEditBox('_yjg_sjdj_list_ssdl','SSDL', false, true, 155, true);
		BaseType.initSelectEditBox('_yjg_sjdj_list_jglx','JGLX', true, false , 155, true);
		
		BaseType.init('JGLX');
		BaseType.init('YZJB');
		BaseType.init('SJXZ');
		BaseType.init('XXLY');
		BaseType.init('SQZT');
		BaseType.init('SFBS');
	},
	
	//绑定事件
	bindEvent:function(){
		$('#_sjgl_tabs').tabs({
		    border:false,
		    onSelect:function(title){
				var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
				if(data.length > 0){
					YjgSjdjList.loadApplyDatagrid(data);
					YjgSjdjList.loadAssignDatagrid(data);
				}
		    }
		});
		//查看处置进度页面查看附件
		$('#_yjg_sjdj_list_view_enclosure_button').bind('click', function(){
			var data=$('#_yjg_sjcz_list_view_east_table').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=1&&sjczid='+data[0].sjczid);
		});
		
		$('#_yjg_sjdj_list_view_cancel_button').bind('click', function(){
			$('#_yjg_sjdj_czjd_list_view_window').window('close');
		});
		
		//查看页面查看上报附件
		$('#_yjg_sjdj_list_view_center_czsb_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			if(data[0].sjlx ==1){
				window.open('../TblBaseFileAction/enclosureView?type=1&&sjdjid='+data[0].sjdjid);
			}else{
				window.open('../TblBaseFileAction/enclosureView?type=1&&fyjsjdjid='+data[0].fyjsjdjid);
			}
		});
		
		//查看页面查看处置前附件
		$('#_yjg_sjdj_list_view_center_cz_before_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=2&&sjdjid='+data[0].sjdjid);
		});
		
		//查看页面查看处置后附件
		$('#_yjg_sjdj_list_view_center_cz_after_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			window.open('../TblBaseFileAction/enclosureView?type=3&&sjdjid='+data[0].sjdjid);
		});
		
		//非窨井选择文件
		$('#_fyj_choose_file').filebox({ 
		    buttonText: '请选择', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_fyj_choose_file").textbox("getValue");//获取图片的url路径
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
	    	    $('#_fyj_choose_file_form').ajaxSubmit({
	    			url:'../TblBaseFileAction/addVedio',
	    			type: 'POST',
	    			success:function(result){
	    			    $.messager.progress('close');  
	    				if(result.sec){
	    					$('#_yjg_fyj_add_vedio').val(result.fileid);
	    					$.messager.alert('警告','上传成功！');
	    				}else{
	    					$.messager.alert('警告','上传视频出错');
	    				}
	    			}
	    		});
	    	}
		});
		//窨井选择文件
		$('#_yj_choose_file').filebox({ 
		    buttonText: '请选择', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_yj_choose_file").textbox("getValue");//获取图片的url路径
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
	    	    $('#_yj_choose_file_form').ajaxSubmit({
	    			url:'../TblBaseFileAction/addYjVedio',
	    			type: 'POST',
	    			success:function(result){
	    			    $.messager.progress('close');  
	    				if(result.sec){
	    					$('#_yjg_yj_add_vedio').val(result.fileid);
	    					$.messager.alert('警告','上传成功！');
	    				}else{
	    					$.messager.alert('警告','上传视频出错');
	    				}
	    			}
	    		});
	    	}
		});
		//条件查询按钮事件
		$('#_yjg_sjdj_list_search_button').bind('click', function(){ 
			var ksTimeStart = $("#_yjg_sjdj_list_ksTimeStart").datebox("getValue");
			var ksTimeEnd = $("#_yjg_sjdj_list_ksTimeEnd").datebox("getValue");
			var jsTimeStart = $("#_yjg_sjdj_list_jsTimeStart").datebox("getValue");
			var jsTimeEnd = $("#_yjg_sjdj_list_jsTimeEnd").datebox("getValue");
			if(ksTimeStart != "" && ksTimeEnd != "" && ksTimeStart >= ksTimeEnd){
				$.messager.alert('系统消息','生成时间中查询开始时间不能晚于或等于查询结束时间！'); 
				return false;
			}
			if(jsTimeStart != "" && jsTimeEnd != "" && jsTimeStart >= jsTimeEnd){
				$.messager.alert('系统消息','结束时间中查询开始时间不能晚于或等于查询结束时间！'); 
				return false;
			}
			var queryParams ={
				"jngj":$("#_yjg_sjdj_list_jngj").val(),
				"jggg":$("#_yjg_sjdj_list_jggg").val(),
				"sjdjdh":$("#_yjg_sjdj_list_sjdjdh").val(),
				"sbrdh":$("#_yjg_sjdj_list_sbrdh").val(),
				"sbrxm":$("#_yjg_sjdj_list_sbrxm").val(),
				"jgid":$("#_yjg_sjdj_list_jgbh").val(),
				"ssdl":$("#_yjg_sjdj_list_ssdl").combobox('getValues').toString(), 
				"jglx":$("#_yjg_sjdj_list_jglx").combobox('getValue'), 
				"jgxz":$("#_yjg_sjdj_list_jgxz").combobox('getValue'), 
				"sfzw":$("#_yjg_sjdj_list_sfzw").combobox('getValue'), 
				"jgzt":$("#_yjg_sjdj_list_jgzt").combobox('getValue'), 
				"jgcz":$("#_yjg_sjdj_list_jgcz").combobox('getValue'), 
				"sqzt":$("#_yjg_sjdj_list_sqzt").combobox('getValue'), 
				"sjlx":$("#_yjg_sjdj_list_sjlx").combobox('getValue'),
				"xxly":$("#_yjg_sjdj_list_xxly").combobox('getValue'), 
				"yzjb":$("#_yjg_sjdj_list_yzjb").combobox('getValue'), 
				"timeStart":ksTimeStart,
				"timeEnd":ksTimeEnd,
				"timeStartCopy":jsTimeStart,
				"timeEndCopy":jsTimeEnd
			};
			$('#_yjg_sjdj_list_datagrid').datagrid('load',queryParams);
		});
		
		//重置按钮
		$('#_yjg_sjdj_list_reset_button').bind('click', function(){
			$('#_yjg_sjdj_list_search_form').form('clear');
			$("#_yjg_sjdj_list_sqzt").combobox('setValue','-1');
			$("#_yjg_sjdj_list_sjlx").combobox('setValue','-1');
			$("#_yjg_sjdj_list_xxly").combobox('setValue','-1');
			$("#_yjg_sjdj_list_jglx").combobox('setValue','-1');
			$("#_yjg_sjdj_list_yzjb").combobox('setValue','-1');
			$("#_yjg_sjdj_list_jgxz").combobox('setValue','-1');
			$("#_yjg_sjdj_list_sfzw").combobox('setValue','-1');
			$("#_yjg_sjdj_list_jgzt").combobox('setValue','-1');
			$("#_yjg_sjdj_list_jgcz").combobox('setValue','-1');
		}); 
		//导出按钮
		$('#_yjg_sjdj_list_export_button').bind('click',function(){
			var flag = $('#_yjg_sjdj_list_export_form').form('validate');
			if(flag){
				var year=$('#_yjg_sjdj_list_export_year').combobox('getValue');
				var month=$('#_yjg_sjdj_list_export_month').combobox('getValue');	
					var str = "";			
					str += "&idArray=" + year ;
					if(month !=null && month !=""){
						str += "&idArray=" + month;
					}
				window.open("../YjgSjdjAction/exportExcelData" + "?" + str.substring(1),'导出Excel');
			}else{
				return $('#_yjg_sjdj_list_export_form').form('validate');
			}
		});
		//导出转账单按钮
		$('#_yjg_sjdj_list_export_word_button').bind('click',function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			if(validateMoreRecord(data)){
				for(var i = 0 ; i < data.length; i++){
					if(data[i].sjlx != 1){
						$.messager.alert('系统消息',"选择列表中包含'非窨井'性质事件！"); 
						return false;
					}
				}
				debugger
				for(var i = 0 ; i < data.length; i++){
					var str = "";
					for(var i=0; i < data.length; i++){
						str += "&idArray=" + data[i].sjdjid;
					}
					window.open("../YjgSjdjAction/exportTransferOrder"+ "?" + str.substring(1),'转办单导出');
				}
			}
		});
		//导出窨井事件取消按钮
		$('#_yjg_sjdj_list_cancel_button').bind('click',function(){			
			$('#_yjg_sjdj_list_export_window').window('close');
		});
		
		//导出窨井事件 选择时间窗口
		$('#_yjg_sjdj_list_export').bind('click',function(){
			var _option=[];
			var myDate = new Date();
			var year=myDate.getFullYear();    //获取完整的年份
			_option.push({name:year,value:year,selected:true});
			year=year-1;
			for(year;year>2017;year-- ){
				_option.push({name:year,value:year});	
			}
			$("#_yjg_sjdj_list_export_year").combobox({
				valueField: 'value',
				textField: 'name',
				data:_option
			});
			var _option2=[];
			
			for(var i=1;i<=12;i++){
				_option2.push({name:i,value:i});	
			}
			$("#_yjg_sjdj_list_export_month").combobox({
				valueField: 'value',
				textField: 'name',
				data:_option2,
			});
			$('#_yjg_sjdj_list_export_window').window('open');
			
		});
		//导出窨井事件取消按钮
		$('#_yjg_sjdj_list_cancel_button').bind('click',function(){			
			$('#_yjg_sjdj_list_export_window').window('close');
		});
		
		//增加页面按钮事件
		$('#_yjg_sjdj_list_add_button').bind('click', function(){ 
			if(typeof($(".yj_add_userTools")[1])!="undefined"){
				$(".yj_add_userTools")[1].remove();
			}
			if(typeof($(".fyj_add_userTools")[1])!="undefined"){
				$(".fyj_add_userTools")[1].remove();
			}
			BaseType.initCkqx("#_yjg_sjdj_list_add_ckqx", "#yj_add_userTools");
			BaseType.searQsRole("#_yjg_sjdj_list_add_ckqx", "#_yj_add_baseroletype_sear");
			BaseType.initSelectBox('_yjg_sjdj_list_add_xxly','XXLY', false, false, 80);
			BaseType.initSelectBox('_yjg_sjdj_list_add_yzjb','YZJB', false, false, 80);
			BaseType.initSelectEditBox('_yjg_sjdj_list_add_input_ssdl','SSDL', false, false, 155, true);
			BaseType.initSelectBox('_yjg_sjdj_list_add_input_xzqh','XZQH', false, false, 155);
			BaseType.initSelectBox('_yjg_sjdj_list_add_xxly','XXLY', true, false, 105);
			BaseType.initSelectEditBox('_yjg_sjdj_list_add_input_jglx','JGLX', false, false , 155, true);

			BaseType.searQsRole("#_yjg_fyjSjdj_list_add_ckqx", "#_fyj_add_baseroletype_sear");
			BaseType.initCkqx("#_yjg_fyjSjdj_list_add_ckqx", "#fyj_add_userTools");
			BaseType.initSelectBox('_yjg_fyjSjdj_list_add_sjlx','FYJSJXZ', false, false, 80);
			BaseType.initSelectBox('_yjg_fyjSjdj_list_add_xxly','XXLY', false, false, 105);
			BaseType.initSelectBox('_yjg_fyjSjdj_list_add_input_xzqh','XZQH', false, false, 155);
			
			$('#_yjg_sjdj_list_add_xxly').combobox('setValues','1');
			$('#_yjg_sjdj_list_add_yzjb').combobox('setValues','1');
			$('#_yjg_sjdj_list_add_input_xzqh').combobox('setValues','思明区');
			$('#_yjg_sjdj_list_add_window').window('open');
			$('#_yjg_fyjSjdj_list_add_sjlx').combobox('setValues','2');
			$('#_yjg_fyjSjdj_list_add_xxly').combobox('setValues','1');
			$('#_yjg_fyjSjdj_list_add_input_xzqh').combobox('setValues','思明区');
		});

		//增加窨井页面取消按钮事件
		$('#_yjg_sjdj_list_add_cancel_button').bind('click', function(){
			$('#_yjg_sjdj_list_add_window').window('close');	
		}); 
		
		//增加非窨井页面取消按钮事件
		$('#_yjg_fyjSjdj_list_add_cancel_button').bind('click', function(){
			$('#_yjg_sjdj_list_add_window').window('close');	
		}); 
		
		//增加页面保存按钮事件
		$('#_yjg_sjdj_list_add_save_button').bind('click', function(){
			$.messager.progress({ // 显示进度条  
		       title:"正在处理",  
	           text:"正在处理...",  
	           interval:100  
			}); 
			$('#_yjg_sjdj_list_add_form').form('submit', {   
				url:"../YjgSjdjAction/add",
				onSubmit: function(){
					
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_yjg_yj_add_imageTime').val(0);
						$('#_yjg_yj_add_imageIds').val("");
						$('#_yjg_yj_add_vedio').val("");
						$('#_yjg_sjdj_list_add_xzqh').val("");
						$('#_yjg_sjdj_list_add_xzb').val("");
						$('#_yjg_sjdj_list_add_yzb').val("");
						$('#_yj_choose_file_form').form('reset');
						$('#_yjg_sjdj_add_img_form').form('reset');
						$('.apply_footer_upload_new').remove();
						$('#_yjg_sjdj_list_add_window').window('close');
						$('#_yjg_sjdj_list_datagrid').datagrid('reload');
					}else{
						$.messager.alert('警告','保存失败！'); 
					}
					$.messager.progress('close');  
		   	    } 
			});  
		}); 
		
		//增加页面查看位置
		$('#_yjg_fyjsjdj_list_add_wz_button').bind('click',function(){
			$('#_yjg_sjdj_list_location_window').window('open');
			var x = $('#_yjg_fyjSjdj_list_add_xzb').val();
			var y = $('#_yjg_fyjSjdj_list_add_yzb').val();
			if(x != "" && y != ""){
				YjgSjdjList.InitLocation(x,y,1,"_yjg_fyjSjdj_list_add",0);
			}else{
				YjgSjdjList.InitLocation(null,null,1,"_yjg_fyjSjdj_list_add",0);
			}
		});
		
		$('#_yjg_sjdj_list_add_wz_button').bind('click',function(){
			$('#_yjg_sjdj_list_location_window').window('open');
			var x = $('#_yjg_sjdj_list_add_xzb').val();
			var y = $('#_yjg_sjdj_list_add_yzb').val();
			if(x != "" && y != ""){
				YjgSjdjList.InitLocation(x,y,1,"_yjg_sjdj_list_add",1);
			}else{
				YjgSjdjList.InitLocation(null,null,1,"_yjg_sjdj_list_add",1);
			}
			
		})
		
		$('#_yjg_sjdj_list_add_jglx').bind('click',function(){
			$('#_yjg_sjdj_list_add_input_jglx').combobox("setValue",$('#_yjg_sjdj_list_add_jglx').val());
		})
		
		$('#_yjg_sjdj_list_add_ssdl').bind('click',function(){
			$('#_yjg_sjdj_list_add_input_ssdl').combobox("setValue",$('#_yjg_sjdj_list_add_ssdl').val());
		})
		
		$('#_yjg_sjdj_list_add_jgid').bind('click',function(){
			$('#_yjg_sjdj_list_add_input_jgid').textbox("setValue",$('#_yjg_sjdj_list_add_jgid').val());
		})
		
		$('#_yjg_sjdj_list_add_xzqh').bind('click',function(){
			$('#_yjg_sjdj_list_add_input_xzqh').textbox("setValue",$('#_yjg_sjdj_list_add_xzqh').val());
		})
		
		$('#_yjg_fyjSjdj_list_add_xzqh').bind('click',function(){
			$('#_yjg_fyjSjdj_list_add_input_xzqh').textbox("setValue",$('#_yjg_fyjSjdj_list_add_xzqh').val());
		})
		
		//增加非窨井页面保存按钮事件
		$('#_yjg_fyjSjdj_list_add_save_button').bind('click', function(){  
			$.messager.progress({ // 显示进度条  
		       title:"正在处理",  
	           text:"正在处理...",  
	           interval:100  
			}); 
			$('#_yjg_fyjSjdj_list_add_form').form('submit', {   
				url:"../YjgFyjsjdjAction/add",
				onSubmit: function(){
					
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_yjg_fyj_add_imageTime').val(0);
						$('#_yjg_fyj_add_imageIds').val("");
						$('#_yjg_fyj_add_vedio').val("");
						$('#_yjg_fyjSjdj_list_add_xzqh').val("");
						$('#_yjg_fyjSjdj_list_add_xzb').val("");
						$('#_yjg_fyjSjdj_list_add_yzb').val("");
						$('#_fyj_choose_file_form').form('reset');
						$('#_yjg_fyjSjdj_add_img_form').form('reset');
						$('.apply_footer_upload_new').remove();
						$('#_yjg_sjdj_list_add_window').window('close');
						$('#_yjg_sjdj_list_datagrid').datagrid('reload');
					}else{
						$.messager.alert('警告','保存失败！'); 
					}
					$.messager.progress('close');  
		   	    } 
			});  
		}); 
		
		//编辑页面按钮事件
		$('#_yjg_sjdj_list_edit_button').bind('click', function(){ 
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
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
			if(data[0].sjlx ==1){
				$("<div></div>").dialog({
					id : '_yjg_sjdj_list_edit_window',
					title:'编辑事件',
					width : 500,
					height: 600,
					modal:true,
					href : '../YjgSjdjAction/editView?sjdjid='+data[0].sjdjid,
					onClose : function() {
                        $(this).dialog('destroy');
                    }
				});
			}else{
				$("<div></div>").dialog({
					id : '_yjg_fyjSjdj_list_edit_window',
					title:'编辑事件',
					width : 500,
					height: 600,
					modal:true,
					href : '../YjgFyjsjdjAction/editView?fyjsjdjid='+data[0].fyjsjdjid,
					onClose : function() {
                        $(this).dialog('destroy');
                    }
				});
			}
		});
		 
		//合并按钮
		$('#_yjg_sjdj_list_merge_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			if(data.length < 2){
				$.messager.alert('警告','请选择至少两条数据合并！');
				return;
			}
			$('#_yjg_sjdj_list_merge_window').window('open');
			$('#_yjg_sjdj_list_merge_table').datagrid('loadData', data);  
		});  

		//删除按钮
		$('#_yjg_sjdj_list_delete_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			if(validateMoreRecord(data)){
				$.messager.confirm('确认删除',"您确定要删除选中记录吗?",function(r){  
					if(r){
						var arr=[]; 
						for(var i = 0 ; i < data.length; i++){
							arr.push(data[i]);
						}
						$.ajax({  
			                type:"POST",  
			                url: "../YjgSjdjAction/deleteBatch",  
			                dataType:"json",  
			                contentType:"application/json",
			                data:JSON.stringify(arr),  
			                success:function(result){  
			                	if(result.sec && result.rows > 0){
									$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
									$('#_yjg_sjdj_list_datagrid').datagrid('reload');
								}else{
									$.messager.alert('警告','删除记录失败！');
								}
			                }  
		                }); 
					}
				})
			}
		});  
		
		//查看按钮
		$('#_yjg_sjdj_list_view_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			if(validateOneRecord(data)){
				$('#_yjg_sjdj_list_view_sqzt').parents('tr').show();
				$('#_yjg_sjdj_list_view_jjr').parents('tr').show();
				$('#_yjg_sjdj_list_view_jjbz').parents('tr').show();
				$('#_yjg_sjdj_list_view_center_czsb_button').show();
				$('#_yjg_sjdj_list_view_ssdl').parents('tr').show();
				$('#_yjg_sjdj_list_view_jgid').parents('tr').show();
				$('#_yjg_sjdj_list_designate_button').show();
				$('#_yjg_sjdj_list_view_center_cz_before_button').show();
				$('#_yjg_sjdj_list_view_center_cz_after_button').show();
				$('#_th_jssj').show();
				$('#_td_jssj').show();
				$('#_yjg_sjdj_list_view_center_cz_before_button').show();
				$('#_yjg_sjdj_list_view_center_cz_after_button').show();
				$('#_yjg_sjdj_list_designate_button').show();
				BaseType.initSelectBox('_yjg_sjdj_list_view_xxly','XXLY', false, false, 80);
				BaseType.initSelectBox('_yjg_sjdj_list_view_yzjb','YZJB', false, false, 80);
				BaseType.initSelectBox('_yjg_sjdj_list_view_sqzt','SQZT', false, false, 80);
				BaseType.initSelectBox('_yjg_sjdj_list_view_sjlx','SJXZ', false, false, 80);
				BaseType.initSelectBox('_yjg_sjdj_list_view_xxly','XXLY', false, false, 55);
				BaseType.initSelectBox('_yjg_sjdj_list_view_yzjb','YZJB', false, false, 55);
				BaseType.initSelectBox('_yjg_sjdj_list_view_sqzt','SQZT', false, false, 55);
				BaseType.initSelectBox('_yjg_sjdj_list_view_sjlx','SJXZ', false, false, 55);
				BaseType.initSelectEditBox('_yjg_sjdj_list_view_jglx','JGLX', true, false , 155, true);
				$('#_yjg_sjdj_list_view_window').window('open');
				initFormData($('#_yjg_sjdj_list_view_form'),data[0]);
				setDefaultFormData($('#_yjg_sjdj_list_view_form'));
				$('#_sjlx').val(data[0].sjlx);
				if(data[0].sjlx==1){
					//0:未处理1:已派单，待确认管辖2:已认领，待确认权责3:无认领，待重新派单（兜底）4:待上报处置情况5:处置完成，待审核6:已兜底7:已解决
					if(data[0].sqzt ==0 || data[0].sqzt ==1 || data[0].sqzt ==2 || data[0].sqzt ==3 || data[0].sqzt ==6){
						$('#_yjg_sjdj_list_view_center_cz_before_button').hide();
					}
					if(data[0].sqzt ==0 || data[0].sqzt ==1 || data[0].sqzt ==2 || data[0].sqzt ==3 || data[0].sqzt ==4 || data[0].sqzt ==6){
						$('#_yjg_sjdj_list_view_center_cz_after_button').hide();
					}
					if(data[0].sqzt ==0){
						$('#_yjg_sjdj_list_designate_button').hide();
					}
					var url="../YjgSjdjAction/listChildSjdj";
					YjgSjdjList.loadYjDatagrid(data[0].sjdjid,url);
					$('#_yjg_sjdj_list_view_jjr').textbox('setValue',"");
					$('#_yjg_sjdj_list_view_jjbz').textbox('setValue',"");
					$.ajax({
						url:"../YjgSjqqAction/findSjqqBySjdjid",
						data:{'sjdjid':data[0].sjdjid},
						type:"post",
						dataType:"json",
						async: false,
						success: function(result,textStatus){
							$('#_yjg_sjdj_list_view_jjr').textbox('setValue',result.qsmc);
							$('#_yjg_sjdj_list_view_jjbz').textbox('setValue',result.bz);
						}	
					});
					YjgSjdjList.loadSjczDatagrid(data[0].sjdjid,"../YjgSjczAction/listBySjdjId");
				}else{
					//非窨井详情信息隐藏
					$('#_th_jssj').hide();
					$('#_td_jssj').hide();
					$('#_yjg_sjdj_list_view_center_cz_before_button').hide();
					$('#_yjg_sjdj_list_view_center_cz_after_button').hide();
					$('#_yjg_sjdj_list_designate_button').hide();
					$('#_yjg_sjdj_list_view_jjbz').parents('tr').hide();
					$('#_yjg_sjdj_list_view_jjr').parents('tr').hide();
					$('#_yjg_sjdj_list_view_jgid').parents('tr').hide();
					YjgSjdjList.loadYjDatagrid(data[0].fyjsjdjid,"../YjgFyjsjdjAction/listChildSjdj");
					YjgSjdjList.loadSjczDatagrid(data[0].sjdjid,"");
				}
			}
		}); 
		
		//查看页面脱离按钮
		$('#_yjg_sjdj_list_view_center_separate_button').bind('click', function(){
			var sjdata=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			var sjlx = $('#_sjlx').val();
			var data=$('#_yjg_sjdj_list_view_center_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
			    $.messager.progress({ // 显示进度条  
			        title:"上传图片",  
			            text:"正在处理...",  
			            interval:100  
			    });
				str ="";
				if(sjlx==1){
					for(var i = 0 ; i < data.length; i++){
						str += "&idArray=" + data[i].sjdjid;
					}
					var url = "../YjgSjdjAction/separate" + "?" + str.substring(1);	
				}else{
					for(var i = 0 ; i < data.length; i++){
						str += "&idArray=" + data[i].fyjsjdjid;
					}
					var url = "../YjgFyjsjdjAction/separate" + "?" + str.substring(1);	
				}
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
									$('#_yjg_sjdj_list_view_center_table').datagrid('reload');
									$('#_yjg_sjdj_list_datagrid').datagrid('reload');
									$('#_yjg_sjdj_list_view_window').window('close');
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
		$('#_yjg_sjdj_list_view_east_view_button').bind('click', function(){
			var data=$('#_yjg_sjcz_list_view_east_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				$('#_yjg_sjdj_czjd_list_view_window').window('open');
				initFormData($('#_yjg_sjdj_czjd_list_view_form'),data[0]);
				setDefaultFormData($('#_yjg_sjdj_czjd_list_view_form'));
			}
		});
		
		//查看页面查看事件处置按钮
		$('#_yjg_sjdj_list_view_east_viewAll_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			YjgSjdjList.loadSjczDatagrid(data[0].sjdjid);
		});
		
		//查看页面查看位置按钮
		$('#_yjg_sjdj_list_view_wz_button').bind('click',function(){
			if($('#_yjg_sjdj_list_view_xzb').val() == ""){
				$.messager.alert('警告','该事件没有位置记录');
			}else{
				$('#_yjg_sjdj_list_location_window').window('open');
				if($('#_yjg_sjdj_list_view_sjlx').val() == 1){
					YjgSjdjList.InitLocation($('#_yjg_sjdj_list_view_xzb').val(),$('#_yjg_sjdj_list_view_yzb').val(),3,"_yjg_sjdj_list_view",1);
				}else{
					YjgSjdjList.InitLocation($('#_yjg_sjdj_list_view_xzb').val(),$('#_yjg_sjdj_list_view_yzb').val(),3,"_yjg_sjdj_list_view",0);
				}
				
			}
		});
		
		//查看认领详情
		$('#_yjg_sjdj_list_designate_button').bind('click', function(){
			var data=$('#_yjg_sjdj_list_datagrid').datagrid('getSelections');
			if(data[0].sjlx != 1)
			{
				$.messager.alert('警告','窨井事件才可以查看！');
				return;
			} 
			$('#_yjg_sjdj_list_designate_window').window('open');
			YjgSjdjList.loadApplyDatagrid(data);
			YjgSjdjList.loadAssignDatagrid(data);

		});
		
	},
	
	//加载数据
	loadData:function(){
		initToken();
		$('#_yjg_sjdj_list_datagrid').datagrid({
			url:"../YjgSjdjAction/list",
			queryParams:{},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:100, field:'sbrxm', title:'上报人姓名'},
				{hidden:false, align:'center', width:150, field:'sbrdh', title:'上报人电话'},
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
				{hidden:false, align:'center', width:150, field:'jgid', title:'井盖编号'},
				{hidden:false, align:'center', width:100, field:'jglx', title:'井盖类型',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("JGLX", value);
					}	
				},
				{hidden:false, align:'center', width:100, field:'sjlx', title:'事件性质',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SJXZ", value);
					}
				},
				{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("YZJB", value);
					}
				},
				{hidden:false, align:'center', width:80, field:'isline', title:'转办单',
					formatter:function(value,row,index){
						if(value == 0){
							return "是";
						}else{
							return "否";
						}
					}
				},
				{hidden:false, align:'center', width:200, field:'scsj', title:'生成时间'},
				{hidden:false, align:'center', width:200, field:'jssj', title:'结束时间'},
				{hidden:false, align:'center', width:200, field:'updatetime', title:'更新时间'},
				{hidden:true, align:'center', width:100, field:'updator', title:'更新人'},
				{hidden:true, align:'center', width:100, field:'instanceid', title:'流程实例编号'},
				{hidden:true, align:'center', width:100, field:'fyjsjdjid', title:'非窨井事件登记编号'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'},
				{hidden:true, align:'center', width:100, field:'wzms', title:'位置描述'},
				{hidden:true, align:'center', width:100, field:'ssdl', title:'所属道路'},
				{hidden:true, align:'center', width:100, field:'xzqh', title:'行政区划'},
				{hidden:true, align:'center', width:100, field:'cssjdjpid', title:'从属事件登记编号'},
				{hidden:true, align:'center', width:100, field:'bz', title:'基本情况'},
				{hidden:true, align:'center', width:100, field:'baseuserid', title:'上报人编号'},
				{hidden:true, align:'center', width:100, field:'isdel', title:'删除标识'},
				{hidden:true, align:'center', width:100, field:'taskid'},
				{hidden:true, align:'center', width:100, field:'taskkey'},
				{hidden:true, align:'center', width:100, field:'taskname'},
				{hidden:true, align:'center', width:100, field:'definitionid'},
				{hidden:true, align:'center', width:100, field:'yzb', title:'Y坐标'},
				{hidden:true, align:'center', width:100, field:'xzb', title:'X坐标'}				
			]],
			rownumbers:true,
			pagination:true,
			pageSize:20,
			view: detailview,  
	  		detailFormatter:function(index,row){    
	        	return '<div id="_sjgl_list_bg_' + index + '" style="padding:5px 0"></div>';    
	  		},  
	  		onExpandRow: function(index,row){  
	  			debugger
	    		var o=$('#_sjgl_list_bg_'+index);
	    		var instanceid= $(this).datagrid('getRows')[index].instanceid; 
	    		var sqzt= $(this).datagrid('getRows')[index].sqzt; 
				$('#_sjgl_list_bg_'+index).datagrid({    
					url:"../YjgSjdjAction/listHisFlow",
					queryParams:{'instanceid':instanceid,'sqzt':sqzt},
					fitColumns:true,
					columns:[[
						{hidden:false, align:'center', width:100, field:'taskname', title:'流程节点名称'},
						{hidden:false, align:'center', width:100, field:'taskendtime', title:'操作完成时间'},
						{hidden:false, align:'center', width:100, field:'username', title:'操作人'},
					]], 
					onLoadSuccess:function(data){   
						$('#_yjg_sjdj_list_datagrid').datagrid('fixDetailRowHeight',index);    
						setTimeout(function () {
							var tr=o.closest('tr');
							id = tr.prev().attr('id'); //此子表格父行所在行的id
							id = id.replace(/-2-(\d+)$/, '-1-$1'); //detailview没有展开的前部分的id是有规则的
							$('#' + id).next().css('height', tr.height());//设置没展开的前部分的高度，由于启用了计时器，会闪一下
						}, 0);
					}
				})
			}
		});
		
		$('#_yjg_sjdj_list_merge_table').datagrid({
			url:"",
			queryParams:{},
			method:'POST',
			columns:[[
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'},
				{hidden:true, align:'center', width:100, field:'fyjsjdjid', title:'非窨井事件登记编号'},
				{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
				{hidden:false, align:'center', width:100, field:'sbrxm', title:'上报人姓名'},
				{hidden:false, align:'center', width:100, field:'sbrdh', title:'上报人电话'},
				{hidden:false, align:'center', width:100, field:'sqzt', title:'申请状态',
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
				{hidden:false, align:'center', width:100, field:'sjlx', title:'事件性质',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SJXZ", value);
					}
				},
				{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("YZJB", value);
					}
				},
				{hidden:false, align:'center', width:200, field:'scsj', title:'生成时间'},
				{hidden:true, align:'left', width:100, field:'updator', title:'更新人'}
			]],
			singleSelect:true,
			rownumbers:true,
			onDblClickRow: function(rowIndex,rowData){
				var data = $('#_yjg_sjdj_list_merge_table').datagrid('getData').rows;
				for(var i = 0 ; i < data.length; i++){
					if(data[i].sjlx!=rowData.sjlx){
						$.messager.alert('警告','合并事件类型必须一致！');
						return;
					}
					if(data[i].sjlx == 1 && rowData.sjlx == 1 && (data[i].sqzt == 6 || data[i].sqzt ==7)){
						$.messager.alert('警告',"合并列表中不能含有'已解决'或者'兜底'类型事件");
						return;
					}
				}
				$.messager.progress({ // 显示进度条  
			        title:"上传图片",  
			            text:"正在处理...",  
			            interval:100  
			    });
				$.messager.confirm('温馨提示','确认选择单号'+rowData.sjdjdh+'为主事件?',function(r){
					if(r){
						var arr=[]; 
						var url="";
						for(var i = 0 ; i < data.length; i++){
							arr.push(data[i]);
						}
						if(rowData.sjlx == 1){
							url="../YjgSjdjAction/merge?yjgSjdjId="+rowData.sjdjid;
						}else{
							url="../YjgFyjsjdjAction/merge?fyjsjdjid="+rowData.fyjsjdjid;
						}
						$.ajax({  
			                type:"POST",  
			                url: url,  
			                dataType:"json",  
			                contentType:"application/json",
			                data:JSON.stringify(arr),  
			                success:function(result){  
			                	if(result.sec && result.rows > 0){
									$.messager.show({title:'系统消息',msg:'合并成功！',showType:'slide'});
									$('#_yjg_sjdj_list_merge_window').window('close');
									$('#_yjg_sjdj_list_datagrid').datagrid('reload');
								}else{
									$.messager.alert('警告','合并失败！');
								}
			                }  
		                });
					}
				})
				$.messager.progress('close');  
			},
		});
	},
	//加载重属表格
	loadYjDatagrid:function(sjdjid,url){
		$('#_yjg_sjdj_list_view_center_table').datagrid({
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
			]],
			fit:true,	
			singleSelect:false,
			rownumbers:true,
			onClickRow:function(index,row){
				YjgSjdjList.loadSjczDatagrid(row.sjdjid);
			}
		});
	},
	//加载认领详情
	loadApplyDatagrid:function(data){
		$('#_designate_user').datagrid({
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
		$('#_assign_user').datagrid({
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
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	//加载事件处置详情
	loadSjczDatagrid:function(sjdjid,url){
		$("#_yjg_sjcz_list_view_east_table").datagrid({
			url:url,
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
							return 'color:ff0033';
						}
					}	
				},
				{hidden:true, align:'center', width:100, field:'bz', title:'处置备注'},
				{hidden:true, align:'center', width:100, field:'isdel', title:'删除标识'},
				{hidden:true, align:'center', width:100, field:'sjdjid', title:'事件登记编号'}
			]],
			singleSelect:false,
			rownumbers:true,
			pagination:false,
			nowrap:true,
		});
		
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	},
	
	//非窨井上传图片
	insertImg:function(ele){
		var imageTime=$('#_yjg_fyj_add_imageTime').val();
		if(imageTime>2){
			$.messager.alert('警告','图片最多只能三张！');
			return false;
		}
		var form=$(ele).parent().parent().parent();
		var imgUrl = form.find("#_yjg_fyjSjdj_add_imageFile").val();//获取图片的url路径
		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
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
			url:'../TblBaseFileAction/addImg',
			type: 'POST',
			success:function(result){
			    $.messager.progress('close');  
				if(result.sec){
					var imageIds = $('#_yjg_fyj_add_imageIds').val();
					var html = $("#fyjSjdj_img").render(result);
					form.append(html);
					$('#_yjg_fyj_add_imageIds').val(imageIds+result.fileid+",");
					imageTime++;
					$('#_yjg_fyj_add_imageTime').val(imageTime);
				}else{
					$.messager.alert('警告','图片保存出错');
				}
			}
		});
	},
	
	//窨井上传图片
	yjInsertImg:function(ele){
		var imageTime=$('#_yjg_yj_add_imageTime').val();
		if(imageTime>2){
			$.messager.alert('警告','图片最多只能三张！');
			return false;
		}
		var form=$(ele).parent().parent().parent();
		var imgUrl = form.find("#_yjg_yjSjdj_add_imageFile").val();//获取图片的url路径
		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
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
			url:'../TblBaseFileAction/yjAddImg',
			type: 'POST',
			success:function(result){
			    $.messager.progress('close');  
				if(result.sec){
					var imageIds = $('#_yjg_yj_add_imageIds').val();
					var html = $("#sjdj_img").render(result);
					form.append(html);
					imageTime++;
					$('#_yjg_yj_add_imageIds').val(imageIds+result.fileid+",");
					$('#_yjg_yj_add_imageTime').val(imageTime);
				}else{
					$.messager.alert('警告','图片保存出错');
				}
			}
		});
	},
	
	//非窨井删除照片
	deleteImg:function(ele,fileid){
		var imageTime=$('#_yjg_fyj_add_imageTime').val();
		var imageIds = $('#_yjg_fyj_add_imageIds').val();
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
	    					$('#_yjg_fyj_add_imageIds').val(imageIds);
	    					$('#_yjg_fyj_add_imageTime').val(imageTime);
						}else{
							$.messager.alert('警告','删除图片失败！');
						}
	                }  
                });
			}
		});
	},
	
	//窨井删除照片
	yjDeleteImg:function(ele,fileid){
		var imageTime=$('#_yjg_yj_add_imageTime').val();
		var imageIds = $('#_yjg_yj_add_imageIds').val();
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
	    					$('#_yjg_yj_add_imageIds').val(imageIds);
	    					$('#_yjg_yj_add_imageTime').val(imageTime);
						}else{
							$.messager.alert('警告','删除图片失败！');
						}
	                }  
                });
			}
		});
	},

	//初始化事件位置窗口
	//x,y经度纬度，type操作类型1新增2编辑查看,winID窗口id去掉window,sjlx事件类型1非窨井2窨井;
	InitLocation:function(x,y,type,winID,sjlx){
		switch (type){
		case 1:
			$('#_yjg_sjdj_list_location_iframe').attr("src","../webgis/location/location.html?type=1&x="+ x +"&y="+ y +"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		case 2:
			$('#_yjg_sjdj_list_location_iframe').attr("src","../webgis/location/location.html?type=2&x="+x+"&y="+y+"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		case 3:
			$('#_yjg_sjdj_list_location_iframe').attr("src","../webgis/location/location.html?type=3&x="+x+"&y="+y+"&winID=" + winID +"&sjlx="+ sjlx +"");
			break;
		}
	},
}

$(function(){
	YjgSjdjList.init();
});