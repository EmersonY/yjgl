var YjgFyjSjdjEdit={
	//初始化启动
	init:function(){
		YjgFyjSjdjEdit.initStyle();
		YjgFyjSjdjEdit.initType();
		YjgFyjSjdjEdit.bindEvent();
	},
	//初始化样式
	initStyle:function(){
		
	},
	
	//数据字典初始化
	initType:function(){
		BaseType.initSelectBox('_yjg_fyjSjdj_list_edit_sjlx','FYJSJXZ', false, false, 80);
		BaseType.initSelectBox('_yjg_fyjSjdj_list_edit_xxly','XXLY', false, false, 80);
		BaseType.initCkqx("#_yjg_fyjSjdj_list_edit_ckqx", "#fyj_editUserTools");
		BaseType.searQsRole("#_yjg_fyjSjdj_list_edit_ckqx", "#_fyj_baseroletype_sear", "#_yjg_fyjSjdj_list_edit_ckqx_res");
	},
	
	//绑定事件
	bindEvent:function(){
		//窨井选择文件
		$('#_fyj_choose_edit_file').filebox({ 
		    buttonText: '重选视频', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_fyj_choose_edit_file").textbox("getValue");//获取图片的url路径
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
	    	    $('#_fyj_choose_file_edit_form').ajaxSubmit({
	    			url:'../TblBaseFileAction/updateVedio',
	    			type: 'POST',
	    			success:function(result){
	    				$.messager.progress('close');  
	    				if(result.sec){
	    					$("#_fyj_choose_edit_img").attr('src',result.vedioFilePath); 
	    					$("#_fyj_choose_edit_operaid").val(result.fileid); 
	    					$.messager.alert('警告','上传成功！');
	    				}else{
	    					$.messager.alert('警告','上传视频出错');
	    				}
	    			}
	    		});
	    	}
		});
		//编辑页面查看位置
		$('#_yjg_fyjSjdj_list_edit_wz_button').bind('click',function(){
			$('#_yjg_sjdj_list_location_window').window('open');
			var x = $('#_yjg_fyjSjdj_list_edit_xzb').val();
			var y = $('#_yjg_fyjSjdj_list_edit_yzb').val();
			if(x != "" && y != ""){
				YjgSjdjList.InitLocation(x,y,2,"_yjg_fyjSjdj_list_edit",0);
			}else{
				YjgSjdjList.InitLocation(null,null,2,"_yjg_fyjSjdj_list_edit",0);
			}
		});
		
		//编辑页面保存按钮事件
		$('#_yjg_fyjSjdj_list_edit_save_button').bind('click', function(){
			$.messager.progress({ // 显示进度条  
		        title:"上传图片",  
		            text:"正在处理...",  
		            interval:100  
		    });
			$('#_yjg_fyjSjdj_list_edit_form').form('submit', {   
				url:"../YjgFyjsjdjAction/edit",
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_yjg_fyjSjdj_list_edit_window').dialog('close');
						$('#_yjg_sjdj_list_datagrid').datagrid('reload');
					}else{
						$.messager.alert('警告','编辑失败！'); 
					}
		   	    } 
			}); 
			$.messager.progress('close');
		}); 
		
		//编辑页面取消按钮事件
		$('#_yjg_fyjSjdj_list_edit_cancel_button').bind('click', function(){
			$('#_yjg_fyjSjdj_list_edit_window').dialog('close');
		});  
		
		
		$("#_yjg_fyjSjdj_list_edit_sjlx").combobox({
			onChange: function (n,o) {
				$('#sjlxVal').val(n);
			}
		});
		
		$("#_yjg_fyjSjdj_list_edit_xxly").combobox({
			onChange: function (n,o) {
				$('#xxlyVal').val(n);
			}
		});
		
		$('#_yjg_fyjSjdj_list_edit_xzqh').bind('click',function(){
			$('#_yjg_fyjSjdj_list_edit_input_xzqh').textbox("setValue",$('#_yjg_fyjSjdj_list_edit_xzqh').val());
		})
	},
	//上传图片
	insertImg:function(ele){
		var list = $('.apply_footer_upload_edit_new');
		if(list.length>2){
			$.messager.alert('警告','图片最多只能三张！');
			return false;
		}
		var form=$(ele).parent().parent().parent();
		var imgUrl = form.find("#_yjg_fyjSjdj_edit_imageFile").val();//获取图片的url路径
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
					var html = $("#_yjg_edit_fyjSjdj_render").render(result);
					form.append(html);
				}else{
					$.messager.alert('警告','图片保存出错');
				}
			}
		});
	},
	//删除照片
	deleteImg:function(ele,fileid){
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
						}else{
							$.messager.alert('警告','删除记录失败！');
						}
	                }  
                });
			}
		});
	}
}

$(function(){
	YjgFyjSjdjEdit.init();
});