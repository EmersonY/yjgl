var dataLength = 0;
var urlData = null;
$(document).ready(function(){
	initToken();
	$('#_flow_menu_tree').tree({
			url:"../TblFlowMenuAction/select", /** 查询所有的菜单 **/
			idField:'id',
	        textField: 'name',
	        parentField: 'pid',
	        attributes: ['id','name','url','state','sequ','pid','updatetime'],
	        dnd:true,
	        onDrop:function(target,source,point){
				var targetId = $('#_flow_menu_tree').tree('getNode', target).id;
				var _pid = targetId;
				var _id = source.id;
				$.ajax({
					type: "POST",
					dataType: "json",
					url:"../tFlowMenu/move.action",
					data: {"e.id":_id,"e.pid":_pid},
					success: function(data){
						if(data == 1){
			   	    		$.messager.show({title:'系统消息',msg:'移动节点成功！',showType:'slide'});
				   	    	$('#_flow_menu_tree').tree('reload');
			   	    	}else{
			   	    		$.messager.alert('警告','移动节点失败!'); 
			   	    	}
					}
				});
	        },
	        // 右键点击节点然后显示上下文菜单 
	    	onContextMenu: function(e, node){
	    		e.preventDefault();
	    		// 选择节点
	    		$('#_flow_menu_tree').tree('select', node.target);
	    		// 显示上下文菜单
	    		$('#_flow_menu_flow_menu_menu').menu('show', {
	    			left: e.pageX,
	    			top: e.pageY
	    		});
	    	}
	    
    });
	
	//“新增”按钮点击事件
	$('#_flow_menu_tree_add_button').click(function(){
		var data=$('#_flow_menu_tree').tree('getSelected');
		$('#_flow_menu_menu_add').window('open');
		$('#_flow_menu_add_state').val(1);
		$('#_flow_menu_add_type').val('MENU');
		if(data != null)
			$('#_flow_menu_add_pid').val(data.id);
	});
	//“编辑”按钮点击事件
	$('#_flow_menu_tree_edit_button').click(function(){
		var data=$('#_flow_menu_tree').tree('getSelected');
		if(data == null) {
			$.messager.alert('系统消息','请选择一条数据！');
			return;
		} 
		$('#_flow_menu_menu_edit').window('open');
	    $("#_flow_menu_edit_id").val(data.id);
		$("#_flow_menu_edit_name").textbox('setValue',data.text);
		$("#_flow_menu_edit_url").textbox('setValue',data.attributes.url);
		
	});
	
	$('#_flow_menu_tree_del_button').click(function(){
		var data=$('#_flow_menu_tree').tree('getSelected');
		 if(data == null)
		 {
			 $.messager.alert('系统消息','请选择一条数据！');
			 return;
		 } 
		 var message="你确定要删除选中节点？";
		 if(!$('#_flow_menu_tree').tree('isLeaf', data.target))
			 message = "该节点含有子节点，若确定删除该节点将同时删除该节点的子节点？";
		 $.messager.confirm('确认删除',message,function(r){   
			 if(r){
				 	//删除的同时，流程关联的菜单表也要删除掉
				 	$.ajax({
						url:"../TblFlowMenuAction/delete?id="+data.id, /** 删除 **/
						type:"post",
						dataType:"json",
						success: function(result){ 
							 if(result != null && result > 0){
								 $.messager.show({title:'系统消息',msg:'删除节点成功！',showType:'slide'});
								 $('#_flow_menu_tree').tree('reload');
							 }else{
								 $.messager.alert({title:'系统消息',msg:'删除节点失败！',showType:'slide'});
							 }
						}	
					});
			 }
		 });  
	});

	
	//增加页面保存按钮事件
	$('#_flow_menu_add_save').bind('click', function(){   
		var _type = $("#_flow_menu_add_type").val();
		$('#_flow_menu_addForm').form('submit', {
			url:"../TblFlowMenuAction/add",
			onSubmit: function(){
				return $(this).form('validate');
			},   
	   	    success:function(r){   
	   	    	var data = eval('(' + r + ')');
	   	    	if(data.sec && data.rows > 0){
	   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
	   	    		$('#_flow_menu_addForm').form('reset');
	   	    		$('#_flow_menu_menu_add').window('close');
   	    			$('#_flow_menu_tree').tree('reload');
	   	    	}else{
	   	    		$.messager.alert('警告','保存失败!'); 
	   	    	}
	  	    }   
		});
	});   
	
	//编辑页面保存按钮事件
	$('#_flow_menu_edit_save').bind('click', function(){
		$('#_flow_menu_editForm').form('submit', {   
			url:"../TblFlowMenuAction/edit",
			onSubmit: function(){
				return $(this).form('validate');
			},   
	   	    success:function(r){
	   	    	var data = eval('(' + r + ')');
	   	    	if(data.sec && data.rows > 0){
	   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
	   	    		$('#_flow_menu_editForm').form('reset');
		   	    	$('#_flow_menu_menu_edit').window('close');
	   	    		$('#_flow_menu_tree').tree('reload');
	   	    	}else{
	   	    		$.messager.alert('警告','保存失败!'); 
	   	    	}
	  	    }
		});
	});     
	
	//增加页面关闭按钮事件
	$('#_flow_menu_add_cancel').bind('click', function(){   
		$('#_flow_menu_menu_add').window('close');
	});   
	
	//编辑页面关闭按钮事件
	$('#_flow_menu_edit_cancel').bind('click', function(){   
		$('#_flow_menu_menu_edit').window('close');
	});   
	
});

