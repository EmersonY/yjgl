var deploymentId = getParameter("deploymentId");
var processid = getParameter("definitionId");
var isEvent = true;//防止点击多次触发oncheck事件
$(function(){
	FlowSet.init();
});
var FlowSet={
	//初始化启动
	init:function(){
		FlowSet.bindEvent();
		FlowSet.loadImg();
		FlowSet.initStyle();
	},
	
	initStyle:function(){
		
	},
	bindEvent:function(){
		//获取热点坐标
		$("#flowmap").on("click","._area",function() {
			debugger
			$('#_lccd').show();
			$('#_lcsq').show();
			//流程菜单
			FlowSet.initMenu(this.id);
			//流程角色
			FlowSet.initRole(this.id);
			$('#_node_form').form('reset');
			var _node_id = this.id;
			$('#_node_id').val(this.id);
			$('#_node_name').val(this.title);
			$('#_process_id').val(processid);
			BaseType.initSelectBox('_node_last_time_action','CSCZ', false, false, 60);
			if(processid !=null && processid !="" && _node_id != null && _node_id != ""){
				ajax("../TblFlowTaskConfigureAction/selectByPidAndTid" ,{"processid":processid,"taskid":_node_id},function(data){
					//t_flow_task_configure 表里面配置的信息要保持唯一性
					$('#_node_maxtime').val(data.maxtimes);
					$('#_configure_id').val(data.flowtaskconfigureid);
										
					if(data != null && data != ""){
						$("#_node_max_time").numberspinner("setValue",(data.maxtimes)/24);
					}else{
						$("#_node_max_time").numberspinner("setValue",0);
					}
				});
			}
			FlowSet.initSelectBoxGLJD(_node_id);
			//流程时长
			$("#_node_maxtime").numberspinner({min:0});
			ajax("../TblFlowTaskTimeAction/selectByProcessid",{"processid":processid},function(data){
				if(data !=null){
					$("#_node_maxtime").numberspinner("setValue",data.time);
					$("#_flow_id").val(data.id);
				}
			});
			//按钮权限
			var values = [];
			ajax("../TblFlowButtonMappingAction/selectByProcessidAndTaskid",{"processid":processid,"taskid":_node_id},function(data){
				if(data != null && data.length >0){
					for(var i=0;i<data.length;i++){
						values.push(data[i].buttonid);
					}
				}
			});
			
			$("#_buttonSelect").combobox('setValues',values);
			
		});
	},
	//加载数据
	loadImg:function(){
		initToken();
		//读取文件夹文件进行显示图片
		ajax("../TblBaseFileAction/selectByOperatypeAndOperaid" ,{"operatype":"BPMN_PNG","operaid":deploymentId},function(result){
			if(result != null && result.length > 0){
				$('#_image').attr("src",result[0].filepath);
			}
		});
		//根据图片对应的bpmn文件对应位置进行读取信息  图片位置与xml文件一一对应
		ajax("../TblProcessDiagramAction/location" ,{"operatype":"BPMN_BPMN","operaid":deploymentId},function(data){
			if(data != null){
				var str = "";
				$.each(data,function (n,value){
					str += "<area  class='_area' id='"+value.id+"' title='" + value.name + "' shape='rect' coords='"+value.sx+","+value.sy+","+value.ex+","+value.ey+"' href ='#' alt='' />";
				});
				$("#flowmap").html(str);
			}
		});
		
		FlowSet.initMenu(null);//初始化菜单
		FlowSet.initRole(null);//初始化角色
		FlowSet.initSelectBoxButton();//初始化按钮
	},
	
	//初始化菜单树
	initMenu:function(taskid){	
		var isExecuteChecked = true;
		$('#_base_process_menu').tree({
			url:"../TblFlowMenuAction/select",
		    idField:'id',
	        textField: 'name',
	        parentField: 'pid',
	        async: false,
	        cascadeCheck:false,
	        checkbox:true,
	        onBeforeLoad:function(node, param){ 
	        	isEvent=true; 
	        },
	        onLoadSuccess:function(node, data){
	        	
	        	$.ajax({
					type: "POST",
					dataType: "json",
					url:"../TblFlowTaskMenuMappingAction/selectByProcessidAndTaskid",
					data: {"taskid":taskid,"processid":processid},
					async: false,
					success: function(data){
						isEvent=false;
						if(data !=null && data.length>0){
								for(var i=0;i<data.length;i++){  
									var node = $('#_base_process_menu').tree('find',data[i].flowmenuid);  
									isExecuteChecked =false;
									$('#_base_process_menu').tree('check',node.target);  
									isExecuteChecked = true;
								}  
						}
					}
				});
	        },
	        onCheck:function(node, checked){
	        	if(isEvent){
	        		return;
	        	}else{
	        	if(isExecuteChecked){
		        		if(checked){
		        			$.ajax({
		    					type: "POST",
		    					dataType: "json",
		    					async: false,
		    					url:"../TblFlowTaskMenuMappingAction/add",
		    					data: {"flowmenuid":node.id,"taskid":taskid,"processid":processid},
		    					success: function(data){
		    						if(data.sec && data.rows > 0){
		    							$.messager.show({title:'系统消息',msg:'添加菜单成功！',showType:'slide'});
		    				   	    }else{
	    				   	    		$.messager.alert('警告','添加菜单失败!'); 
	    				   	    		$('#base_process_role').tree('uncheck',node.target);	
		    				   	    }
		    					}
		    				});
		        		}else{
		        			$.ajax({
		    					type: "POST",
		    					dataType: "json",
		    					url:"../TblFlowTaskMenuMappingAction/delete",
		    					async: false,
		    					data: {"flowmenuid":node.id,"taskid":taskid,"processid":processid},
		    					success: function(data){
		    						if(data.sec && data.rows > 0){
	    				   	    		$.messager.show({title:'系统消息',msg:'删除菜单成功！',showType:'slide'});
	    				   	    	}else{
	    				   	    		$.messager.alert('警告','删除菜单失败!'); 
	    				   	    		$('#base_process_role').tree('check',node.target);
	    				   	    	}
		    					}
		    				});
		        		}	
	        	    }
	        	}
	        }
		});
	},
	//初始化角色树
	initRole:function(taskid){	
		var isExecuteChecked = true;
		$('#base_process_role').tree({
			url:"../TblBaseRoleAction/select",
		    idField:'id',
		    async: false,
	        textField: 'rolename',
	        parentField: 'pid',
	        cascadeCheck:false,
	        checkbox:true,
	        onBeforeLoad:function(node, param){ 
	        	isEvent=true; 
	        },
	        onLoadSuccess:function(node, data){
	        	isEvent=false;
	        	$.ajax({
					type: "POST",
					dataType: "json",
					url:"../TblFlowTaskRoleMappingAction/selectByProcessidAndTaskid",
					data: {"taskid":taskid,"processid":processid},
					async: false,
					success: function(data){		
						if(data !=null && data.length>0){
							for(var i=0;i<data.length;i++){  
								var node = $('#base_process_role').tree('find',data[i].roleid);  
								if(node != null){
									isExecuteChecked =false;
									$('#base_process_role').tree('check',node.target);  
									isExecuteChecked = true;
								}
							}  
						}
					}
				});
	        },
	        onCheck:function(node, checked){
	        	if(isEvent){
	        		return;
	        	}else{
	        		debugger
	        	if(isExecuteChecked){
		        		if(checked){
		        			$.ajax({
		    					type: "POST",
		    					dataType: "json",
		    					url:"../TblFlowTaskRoleMappingAction/add",
		    					data: {"roleid":node.id,"taskid":taskid,"processid":processid},
		    					async: false,
		    					success: function(data){
		    						if(data.sec && data.rows > 0){
	    				   	    		$.messager.show({title:'系统消息',msg:'添加角色成功！',showType:'slide'});
	    				   	    	}else{
	    				   	    		$.messager.alert('警告','添加角色失败!'); 
	    				   	    		$('#base_process_role').tree('uncheck',node.target);
	    				   	    	}
		    					}
		    				});
		        		}else{
		        			$.ajax({
		    					type: "POST",
		    					dataType: "json",
		    					url:"../TblFlowTaskRoleMappingAction/delete",
		    					data: {"roleid":node.id,"taskid":taskid,"processid":processid},
		    					success: function(data){
		    						if(data.sec && data.rows > 0){
	    				   	    		$.messager.show({title:'系统消息',msg:'删除角色成功！',showType:'slide'});
	    				   	    	}else{
	    				   	    		$.messager.alert('警告','删除角色失败!'); 
	    				   	    		$('#base_process_role').tree('check',node.target);
	    				   	    	}
		    					}
		    				});
		        		}	
	        	    }
	        	}
	        },onClick: function(node){
	        	$('#_user_tree').window('open');
	    		$('#_user_table').datagrid({
	    			url:"../TblBaseRoleAction/list",
	    			queryParams: {'baseroleid':node.id},
	    			columns:[[
	    			         {field:'account',title:'帐号',width:120,align:'center'}
	    					,{field:'username',title:'用户名',width:120,align:'center'}
	    			]],
	    			height:260,
	    			nowrap:true,
	    			pagination:true,
	        		pageSize:10,
	    		});
	        }
		});
	},

	//节点属性保存事件
	doSave:function(){
		$('#_node_form').form('submit', {   
			url:"../TblFlowTaskConfigureAction/save",
			onSubmit: function(){
				//判断按钮是否有选择
				var _buttonSelect = $('#_buttonSelect').combobox("getValues");
				if(_buttonSelect == null || _buttonSelect.length == 0 ){
					$.messager.alert('警告','请选择按钮！');
					return false;
				}
				return $(this).form('validate');
			},   
			success:function(r){
				var data = eval('(' + r + ')');
				if(data.sec && data.row > 0){
					$("#_configure_id").val(data.id);
					$("#_flow_id").val(data.flowid);
					$.messager.show({title:'系统消息',msg:'保存记录成功！',showType:'slide'});
	   	    	}else{
	   	    		$.messager.alert('系统消息','保存记录失败！');
	   	    	}  
	   	    } 
		});  
	},
	//初始化按钮下拉框
	initSelectBoxButton:function(){
		var buttons = $("#_buttonJson").val();
		var buttons = eval('(' + buttons + ')');
		$("#_buttonSelect").combobox({
			valueField: 'id',
			textField: 'name',
			editable: false,
			data:buttons,
			multiple:true,
		});  
	},
	
	//初始化关联节点
	initSelectBoxGLJD:function (taskid){
		var json = [{name:"--请选择--",id:-1,selected:true}];
		ajax("../TblProcessDiagramAction/selectAllNodes",{"operatype":"BPMN_BPMN","operaid":deploymentId,"taskid":taskid},function(result){
			if(result != null && result.length > 0){
				for(var i=0;i<result.length;i++){
					json.push({name:result[i].name,id:result[i].id});
				}
			}		
		});
	}
		
}