var DeployHome={
	//初始化启动
	init:function(){
		DeployHome.initType();
		DeployHome.bindEvent();
		DeployHome.loadData();
	},
	initType:function(){
		$("#_deploy_north").css("height",$(window).height()/4.3);
		$("#_deploy_center").css("height",$(window).height()/2.2);
		$("#_deploy_south").css("height",$(window).height()/3.5);
	},
	//绑定事件
	bindEvent:function(){
		$('#_depolymentSubmit').bind('click', function(){
			$('#_depolyment_form').form('submit',{
				onSubmit: function(param){
					return $(this).form('validate');
			    },
				success:function(r){
					var result = eval('(' + r + ')');
					if(result.sec){
						$.messager.show({title:'系统消息',msg:'上传成功！',showType:'slide'});
						$(this).form('reset');
						$('#_act_re_deployment_list').datagrid('reload');
						$('#_act_re_procdef_list').datagrid('reload');
					}else{
						$.messager.alert('警告','上传失败！'); 
					}
				}   
			});
			  
		});
	},
	
	//删除
	deleteDeployment:function(deploymentId){
		$.messager.confirm('确认删除','你确定要删除部署信息?',function(r){ 
			 if(r){
				 $.ajax({
					url:"../ActDeploymentAction/delDeployment",
					data:{"deploymentId":deploymentId},
					type:"post",
					dataType:"json",
					async: false,
					success: function(result){ 
						if(result.sec){
							$.messager.show({title:'系统消息',msg:'删除成功！',showType:'slide'});
							$('#_act_re_deployment_list').datagrid('reload');
							$('#_act_re_procdef_list').datagrid('reload');
						}else{
							$.messager.alert('警告','删除失败！');
						}
					}	
				});
			 }
		})
	},
	
	//配置
	configure:function(deploymentId,definitionId){
		$("#_set_window").window('open');
		$('#_set_iframe').attr("src","../activiti/flow_set.jsp?deploymentId="+deploymentId+"&definitionId="+definitionId);
	},
	
	//加载数据
	loadData:function(){
		initToken();
		$('#_act_re_deployment_list').datagrid({
		url:"../ActDeploymentAction/findDeploymentList",
		queryParams:{},
		columns:[[
            {hidden:false, align:'center', width:130, field:'opt',title:'操作',
        	    formatter: function(value,row,index){
        		    var btn = "<a onclick=DeployHome.deleteDeployment('"+row.id+"') href=javascript:void(0)>删除</a>";  
        		    return btn;
        	    }
            },
			{hidden:false, align:'center', width:110, field:'id', title:'ID'},
			{hidden:false, align:'center', width:130, field:'name', title:'流程名称'},
			{hidden:false, align:'center', width:200, field:'deploymentTime', title:'发布时间'}
		]],
		rownumbers:true,
		pagination:false,
		nowrap:true,
		singleSelect:true,
		});
		
		$('#_depolyment_data').filebox({    
		    buttonText: '选择文件', 
		    buttonAlign: 'left',
		    accept : [ 'application/zip'] , 
	    	onChange:function(){
	    		var zipUrl = $("#_depolyment_data").textbox("getValue");//获取图片的url路径
	    		var fileType = zipUrl.substring(zipUrl.lastIndexOf("."), zipUrl.length);
	    		if(fileType!=".zip"&&fileType!=""){
	    			$.messager.alert('警告','上传类型应该为zip类型！'); 
	    			$("#_depolyment_data").textbox('setValue',"");
	    			return false;
	    		}
	    	}
		})
		
		$('#_act_re_procdef_list').datagrid({
		url:"../ActDeploymentAction/findProcessDefinitionList",
		queryParams:{},
		columns:[[
            {hidden:false, align:'center', width:130, field:'opt',title:'操作',
        	    formatter: function(value,row,index){
        		    var btn = "<a target='_blank' href='../ActDeploymentAction/viewImage?deploymentId="+row.deploymentId+"&imageName="+row.diagramResourceName+"'>查看流程图</a>&nbsp;&nbsp;" +
        		    "<a href='javascript:void(0);' onclick=\"DeployHome.configure("+row.deploymentId+",'"+row.id+"')\">配置</a>";  
        		    return btn;
        	    }
            },
			{hidden:false, align:'center', width:200, field:'id', title:'ID'},
			{hidden:false, align:'center', width:200, field:'name', title:'名称'},
			{hidden:false, align:'center', width:130, field:'key', title:'流程定义的KEY'},
			{hidden:false, align:'center', width:130, field:'version', title:'流程定义的版本'},
			{hidden:false, align:'center', width:130, field:'resourceName', title:'流程定义的规则文件名称'},
			{hidden:false, align:'center', width:130, field:'diagramResourceName', title:'流程定义的规则图片名称'},
			{hidden:false, align:'center', width:130, field:'deploymentId', title:'部署ID'}
		]],
		rownumbers:true,
		pagination:false,
		nowrap:true,
		singleSelect:true,
		});

		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
		
	}
}

$(function(){
	DeployHome.init();
});
