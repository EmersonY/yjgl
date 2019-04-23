var dataLength = 0;
var isExecuteChecked = true;
var urlData = null;

var BaseMenuList={
	
	//初始化启动
	init:function(){
		BaseMenuList.bindEvent();
		BaseMenuList.loadData();
	},
	
	//绑定事件
	bindEvent:function(){
		
		//菜单信息添加按钮
		$('#_west_menu_add').bind('click', function(){
			var data=$('#_base_menu_tree').tree('getSelected');
			if(data==null||data.basemenupid==null||data.basemenupid=='-'){
				$('#_base_menu_menu_add').window('open');
				BaseType.initSelectBox('_base_menu_add_state','SDZT', false, false, 60);
				$('#_base_menu_add_state').combobox('setValues','1');
				if(data != null){
					$('#_base_menu_add_pid').val(data.basemenuid);
				}
			}else{
				$.messager.alert('警告','不可添加三级目录！');   
				return false ;
			}
			
		});
		
		//增加页面保存按钮
		$('#_base_menu_add_save').bind('click', function(){   
			$('#_base_menu_addForm').form('submit', {
				url:"../main/insert",
				onSubmit: function(){
					var _add_name = $("#_base_menu_add_name").val();
					return $(this).form('validate');
				},   
		   	    success:function(r){
					var data = eval('(' + r + ')');
					if(data.valid){
						if(data.sec && data.rows > 0){
			   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
			   	    		$(this).form('reset');
			   	    		$('#_base_menu_menu_add').window('close');
			   	    		$('#_base_menu_tree').tree('reload');
			   	    	}else{
			   	    		$.messager.alert('警告','保存失败!'); 
			   	    	}
					}else{
						baseUtil.v(r);
					}
		  	    }   
			});
		}); 
		
		//增加页面关闭按钮
		$('#_base_menu_add_cancel').bind('click', function(){   
			$('#_base_menu_menu_add').window('close');
		});   
		
		//编辑页面关闭按钮
		$('#_base_menu_edit_cancel').bind('click', function(){   
			$('#_base_menu_menu_edit').window('close');
		});
		
		//菜单信息编辑按钮
		$('#_west_menu_edit').bind('click', function(){
			var data=$('#_base_menu_tree').tree('getSelected');
			if(data == null){
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			$('#_base_menu_menu_edit').window('open');
		    $("#_base_menu_edit_id").val(data.basemenuid);
			$("#_base_menu_edit_name").textbox('setValue',data.text);
			$("#_base_menu_edit_url").textbox('setValue',data.src);
			BaseType.initSelectBox('_base_menu_edit_state','SDZT', false, false, 60);
			$("#_base_menu_edit_state").combobox('setValue',data.attributes.state);

		});
		
		//编辑页面保存按钮
		$('#_base_menu_edit_save').bind('click', function(){
			$('#_base_menu_editForm').form('submit', {   
				url:"../main/update",
				onSubmit: function(){
					var _edit_name = $("#_base_menu_edit_name").val();
					return $(this).form('validate');
				},   
		   	    success:function(r){
		   	    	var data = eval('(' + r + ')');
		   	    	if(data.sec && data.rows > 0){
		   	    		$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
		   	    		$(this).form('reset');
			   	    	$('#_base_menu_menu_edit').window('close');
			   	    	$('#_base_menu_tree').tree('reload');
		   	    	}else{
		   	    		$.messager.alert('警告','保存失败!'); 
		   	    	}
		  	    }
			});
		}); 
		
		//编辑页面移除按钮
		$('#_west_menu_remove').bind('click', function(){
			initToken();
			var data=$('#_base_menu_tree').tree('getSelected');
			if(data == null)
			{
				$.messager.alert('系统消息','请选择一条数据！');
				return;
			} 
			var message="你确定要删除选中节点？";
			if(!$('#_base_menu_tree').tree('isLeaf', data.target))
				message = "该节点含有子节点，若确定删除该节点将同时删除该节点的子节点？";
			$.messager.confirm('确认删除',message,function(r){   
				if(r){
					 $.ajax({
						url:"../main/delete", 
						data: {"basemenuid":data.basemenuid,"menuname":data.menuname},
						type:"post",
						dataType:"json",
						success: function(data){ 
							if(data.sec && data.rows > 0){
								$.messager.show({title:'系统消息',msg:'删除节点成功！',showType:'slide'});
								$('#_base_menu_tree').tree('reload');
							}else{
								$.messager.alert({title:'系统消息',msg:'删除节点失败！',showType:'slide'});
							}
						}	
					 });
				}
			});  
		}); 
	},
	
	//加载数据
	loadData:function(){
		$('#_base_menu_tree').tree({
			url:"../main/selectMenuTree", 
			method: 'GET', 
			idField:'basemenuid',
		    textField: 'menuname',
		    parentField: 'basemenupid',
		    attributes: ['basemenuid','menuname','basemenupid','state'],
		    dnd:true,
		    onClick: function(node){
		    	BaseMenuList.initRole("_base_menu_base_role",node.basemenuid);
			},
			onBeforeDrop:function(target,source,point){
				if($('#_base_menu_tree').tree('getNode', target).basemenupid != null){
					$.messager.alert('警告','不可添加三级目录！');   
					return false;
		    	}
			},
		    onDrop:function(target,source,point){
		    	var targetId = $('#_base_menu_tree').tree('getNode', target).basemenuid;
				var _pid = targetId;
				var _id = source.basemenuid;
				initToken();
				$.ajax({
					type: "POST",
					dataType: "json",
					url:"../main/move",
					data: {"basemenuid":_id,"basemenupid":_pid},
					success: function(data){
						if(data == 1){
			   	    		$.messager.show({title:'系统消息',msg:'移动节点成功！',showType:'slide'});
				   	    	$('#_base_menu_tree').tree('reload');
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
				$('#_base_menu_tree').tree('select', node.target);
				// 显示上下文菜单
				$('#_base_menu_base_menu_menu').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}

		});
	},
	
	//菜单上移
	baseMenuMoveUp:function(){
		var data=$('#_base_menu_tree').tree('getSelected');
		if(data == null)
		{
			$.messager.alert('系统消息','请选择一个节点！');
			return false ;
		} 
		var root = $('#_base_menu_tree').tree('getRoot');
		var parent = $('#_base_menu_tree').tree('getParent', data.target);
		if(parent!=null){
			var childrens = $('#_base_menu_tree').tree('getChildren', parent.target);
			var levels = [];
			for(var i = 0;i<childrens.length;i++){
				if(childrens[i].basemenuid == data.basemenuid)
					break;
				if(childrens[i].basemenupid == data.basemenupid)
					levels.push(childrens[i]);
			}
			if(levels.length == 0){
				$.messager.alert('警告','该节点已为最顶层节点，无法移动。');   
				return;
			}
			BaseMenuList.baseMenuMoveUpOrDown(data,levels[levels.length-1]);
		}else{
			BaseMenuList.baseTopMenuMoveUpOrDown(data.basemenuid,data.sequ,1);
		}
	},
	
	//菜单下移
	baseMenuMoveDown:function(){
		var data=$('#_base_menu_tree').tree('getSelected');
		if(data == null)
		{
			$.messager.alert('系统消息','请选择一个节点！');
			return false ;
		} 
		var parent = $('#_base_menu_tree').tree('getParent', data.target);
		if(parent!=null){
			var childrens = $('#_base_menu_tree').tree('getChildren', parent.target);
			var levels = [];
			for(var i = childrens.length-1;i>=0;i--){
				if(childrens[i].basemenuid == data.basemenuid)
					break;
				if(childrens[i].basemenupid == data.basemenupid)
					levels.push(childrens[i]);
			}
			if(levels.length == 0){
				$.messager.alert('警告','该节点已为最底层节点，无法移动。');   
				return;
			}
			BaseMenuList.baseMenuMoveUpOrDown(data,levels[levels.length-1]);
		}else{
			BaseMenuList.baseTopMenuMoveUpOrDown(data.basemenuid,data.sequ,0);
		}

	},
	
	// 移动子节点
	baseMenuMoveUpOrDown:function(current,np){
		initToken();
		var json = {"currentBasemenuid":current.basemenuid,"currentSequ":current.sequ,"npBasemenuid":np.basemenuid,"npSequ":np.sequ};
		$.ajax({
			url:"../main/moveUpOrDown",
			data:json,
			type:"post",
			dataType:"json",
			async: false,
			success: function(data){
				if(data == 2){
	   	    		$.messager.show({title:'系统消息',msg:'移动节点成功！',showType:'slide'});
		   	    	$('#_base_menu_tree').tree('reload');
	   	    	}else{
	   	    		$.messager.alert('警告','移动节点失败!'); 
	   	    	}
			}
		});
	},
	
	//移动顶级节点
	baseTopMenuMoveUpOrDown:function(basemenuid,sequ,state){
		initToken();
		var json = {"currentBasemenuid":basemenuid,"currentSequ":sequ,"state":state};
		$.ajax({
			url:"../main/moveTopUpOrDown",
			data:json,
			type:"post",
			dataType:"json",
			async: false,
			success: function(data){
				if(data.success){
	   	    		$.messager.show({title:'系统消息',msg:'移动节点成功！',showType:'slide'});
		   	    	$('#_base_menu_tree').tree('reload');
	   	    	}else{
	   	    		$.messager.alert('警告',data.msg); 
	   	    	}
		   	    	
			}
		});
	},
	
	initRole:function(treeid,menuid){
		initToken();
		$('#'+treeid).tree({
			url:"../TblBaseRoleAction/selectRoleTree",
			idField:'baseroleid',
		    textField: 'rolename',
		    parentField: 'baserolepid',
	        attributes: ['baseroleid','rolename','baserolepid','id'],
	        cascadeCheck:false,
	        checkbox:true,
	        onLoadSuccess:function(node, data){
	        	if(menuid != null&&menuid != ""){
		        	$.ajax({
						type: "POST",
						dataType: "json",
						url:"../TblBaseRoleAction/selectAddedMenuRoles",
						data: {"menuid":menuid},
						success: function(data){	
							if(data !=null && data.length>0){
								for(var i=0;i<data.length;i++){ 
									var node = $('#'+treeid).tree('find',data[i].baseroleid);  
									isExecuteChecked =false;
									$('#'+treeid).tree('check',node.target);  
									isExecuteChecked = true;
								}  
							}
						}
					});
	        	}
	        },
	        onCheck:function(node, checked){
	        	var nodes = $('#_base_menu_tree').tree('getSelected');
	        	if (nodes== null){ 
	        		$.messager.alert('警告','请先选择菜单信息!');
	        		return false;
	        	}
	        	initToken();
	        	if(isExecuteChecked){
		        		if(checked){
		        			$.ajax({
								type: "POST",
								dataType: "json",
								url:"../TblBaseRoleMenuMappingAction/insert",
								data: {"basemenuid":menuid,"baseroleid":node.baseroleid},
								success: function(data){
	    							if(data.sec && data.rows > 0){
	    				   	    		$.messager.show({title:'系统消息',msg:'添加角色成功！',showType:'slide'});
	    				   	    		$('#_base_menu_base_role').tree("reload");
	    							}else{
	    				   	    		$.messager.alert('警告','添加角色失败!'); 
	    				   	    		$('#'+treeid).tree('uncheck',node.target);
	    				   	    	}
								}
							});
		        		}else{
		        			$.ajax({
		    					type: "POST",
		    					dataType: "json",
		    					url:"../TblBaseRoleMenuMappingAction/deleteByRoleidAndMenuid",
								data: {"basemenuid":menuid,"baseroleid":node.baseroleid},
		    					success: function(data){
	    							if(data.sec && data.rows > 0){
	    				   	    		$.messager.show({title:'系统消息',msg:'删除角色成功！',showType:'slide'});
	    				   	    		$('#_base_menu_base_role').tree("reload");
	    							}else{
	    				   	    		$.messager.alert('警告','删除角色失败!'); 
	    				   	    		$('#'+treeid).tree('check',node.target);
	    				   	    	}
		    					}
		    				});
		        		}	
	        	}
	        }      
		});
	}

}
	
$(function(){
	BaseMenuList.init();
});