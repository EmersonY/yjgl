var BaseType = {
	init:function(code){//初始化数据字典
		initToken();
		$.ajax({
			 url:"../TblBaseTypeAction/selectVerByCode",
			 data:{'code':code,'type':0},
			 type:"post",
			 dataType:"json",
			 success: function(data){
				var version = data.ver;
				var ver  = store.get(code+'_ver');
				if(ver != version){	//比较本地代码版本与服务器是否一致，不一致则请求服务器的数据初始本地数据
					$.ajax({
						 url:"../TblBaseTypeAction/selectByCode",
						 data:{'code':code},
						 type:"post",
						 dataType:"json",
						 success: function(data){ 
							if(data != null){
								for(var i=0;i<data.length;i++){
									store.set(code+'_'+data[i].value, data[i].name);	//更新本地代码
								}
								store.set(code+'_ver', version);	//更新本地版本
							}	
						 }	
					 });
				}
			 }	
		 });
		
	},
	getNameByValue:function(code,value){//格式化数据字典
		return store.get(code+'_'+value);
	},
	/**
	 * 初始化数字字典
	 * @param id   需要初始化的input的id (必须参数)
	 * @param code 数字字典类型的编码 (必须参数)
	 * @param isSetDefault (是否显示:--请选择--，--请选择--默认值为-1)
	 * @param isMultiple (是否多选:true，否 默认值为false)
	 */
	initSelectBox:function (id,code,isSetDefault,isMultiple,panelHeight){
		initToken();
		var _option=[];
		if(isMultiple){
		}else{
			isMultiple=false;
		}
		if(isSetDefault){
			_option.push({name:"--请选择--",value:-1,selected:true});
		}
		$.ajax({
		     url:'../TblBaseTypeAction/selectByCode?code='+code,
			 type:"post",
			 dataType:"json",
			 async: false,
			 success: function(data){ 
				if(data != null){
					for(var i=0;i<data.length;i++){
						_option.push({name:data[i].name,value:data[i].value});
					}
				}	
			 }	
		 });
		$("#"+id).combobox({
			valueField: 'value',
			textField: 'name',
			editable: false,
			data:_option,
			multiple:isMultiple,
			panelHeight:panelHeight,
		});  
	},
	
	initCkqx:function(id,toolbar){
		$(id).combogrid({ 
		    idField:'baseroletype',    
		    textField:'rolename',    
		    editable:false,
		    toolbar:toolbar, 
		    panelHeight:300,
		    columns:[[    
		        {field:'rolename',title:'权属单位',width:200}  
		    ]],
		}); 
	},
	
	searQsRole:function(grid,sear,searRes){
		var getSelections = $(grid).combogrid("grid").datagrid('getSelections');
		var baseroletype = $(sear).val();
		var ckqxRes =$(searRes).val();
		$.post('../TblBaseRoleAction/selectSecondRole',{'baseroletype':baseroletype},function(result){
			//新的数组--放要展示出来的行数
			var newRows=[];
			if(getSelections!=null && getSelections.length>0){
				for(var j=0;j<getSelections.length;j++){           //将选中的放入新的数组
					newRows.push(getSelections[j]);
				}
			}
			if(result.rows!=null && result.rows.length>0){  
				for(var i=0;i<result.rows.length;i++){    //将通过字符串查询出来的对象  放入新的数组
					newRows.push(result.rows[i]);
				}
			}
			//////////////////// 数组去重开始/////////////////////// 
			var tempIds = []; //一个新的临时数组  放行id用于比较  判断
			var tempRows =[];  //临时  放 行 对象
			for(var i = 0; i < newRows.length; i++) //遍历当前数组
			{
				//如果当前数组的第i已经保存进了临时数组，那么跳过，
				//否则把当前项push到临时数组里面
				if (tempIds.indexOf(newRows[i].baseroletype) == -1){
					
					tempIds.push(newRows[i].baseroletype);
					tempRows.push(newRows[i]);
					
				} 
			}
			////////////////// 数组去重结束////////////////
				
			newRows = tempRows;
			$(grid).combogrid("grid").datagrid("loadData", newRows);   //加载新数据
	
			if(getSelections!=null && getSelections.length>0){ //放入新选中的数据
				for(var index=0; index<getSelections.length; index++){
					$(grid).combogrid("grid").datagrid("selectRow", index);// 为要选中的索引号
				}
			}
			var strs= new Array(); //定义一数组 
			if(ckqxRes!="" && typeof(ckqxRes)!="undefined"){
				strs=ckqxRes.split(",");
				for(var i=0;i<strs.length>0;i++){
					for(var j = 0; j<newRows.length;j++){
						if(strs[i] == (newRows[j].baseroletype))
						{
							$(grid).combogrid("grid").datagrid("selectRow", j);// 为要选中的索引号
							break;
						}
					}
				}
			}
		});
	},
	
	initAssign:function(id,toolbar){
		$(id).combogrid({ 
		    idField:'baseuserid',    
		    textField:'username',    
		    editable:false,
		    toolbar:toolbar, 
		    panelHeight:300,
		    columns:[[    
				{hidden:true, align:'center', width:200, field:'baseuserid', title:'接件人ID'},
				{hidden:false, align:'center', width:200, field:'username', title:'接件人名称'}
		    ]],
		}); 
	},
	
	searAssign:function(grid,sear){
		debugger
		var data=$('#_yjg_rwzx_list_table').datagrid('getSelections');
		var getSelections = $(grid).combogrid("grid").datagrid('getSelections');
		var username = $(sear).val();
		$.post('../ActTaskAction/selectNextTasksTable',{'username':username,'taskid':data[0].taskid,'taskkey':data[0].taskkey,"sjdjid":data[0].sjdjid},function(result){
			//新的数组--放要展示出来的行数
			var newRows=[];
			if(getSelections!=null && getSelections.length>0){
				for(var j=0;j<getSelections.length;j++){           //将选中的放入新的数组
					newRows.push(getSelections[j]);
				}
			}
			if(result.rows!=null && result.rows.length>0){  
				for(var i=0;i<result.rows.length;i++){    //将通过字符串查询出来的对象  放入新的数组
					newRows.push(result.rows[i]);
				}
			}
			//////////////////// 数组去重开始/////////////////////// 
			var tempIds = []; //一个新的临时数组  放行id用于比较  判断
			var tempRows =[];  //临时  放 行 对象
			for(var i = 0; i < newRows.length; i++) //遍历当前数组
			{
				//如果当前数组的第i已经保存进了临时数组，那么跳过，
				//否则把当前项push到临时数组里面
				if (tempIds.indexOf(newRows[i].username) == -1){
					
					tempIds.push(newRows[i].username);
					tempRows.push(newRows[i]);
					
				} 
			}
			////////////////// 数组去重结束////////////////
				
			newRows = tempRows;
			$(grid).combogrid("grid").datagrid("loadData", newRows);   //加载新数据
	
			if(getSelections!=null && getSelections.length>0){ //放入新选中的数据
				for(var index=0; index<getSelections.length; index++){
					$(grid).combogrid("grid").datagrid("selectRow", index);// 为要选中的索引号
				}
			}
		});
	},
	
	/**
	 * 初始化数字字典
	 * @param id   需要初始化的input的id (必须参数)
	 * @param code 数字字典类型的编码 (必须参数)
	 * @param isSetDefault (是否显示:--请选择--，--请选择--默认值为-1)
	 * @param isMultiple (是否多选:true，否 默认值为false)
	 */
	initSelectEditBox:function (id,code,isSetDefault,isMultiple,panelHeight,editAble){
		initToken();
		var _option=[];
		if(isMultiple){
		}else{
			isMultiple=false;
		}
		if(isSetDefault){
			_option.push({name:"请输入信息",value:-1,selected:true});
		}
		$.ajax({
		     url:'../TblBaseTypeAction/selectByCode?code='+code,
			 type:"post",
			 dataType:"json",
			 async: false,
			 success: function(data){ 
				if(data != null){
					for(var i=0;i<data.length;i++){
						_option.push({name:data[i].name,value:data[i].value});
					}
				}	
			 }	
		 });
		$("#"+id).combobox({
			valueField: 'value',
			textField: 'name',
			editable: editAble,
			data:_option,
			multiple:isMultiple,
			panelHeight:panelHeight,
		});  
	},
	
	initJGLX:function(jglx){
		var fhdm = "";
		$.ajax({
		     url:'../TblBaseTypeAction/selectByCode?code=FHDM',
			 type:"post",
			 dataType:"json",
			 async: false,
			 success: function(data){ 
				if(data != null){
					for(var i=0;i<data.length;i++){
						if(data[i].name == jglx){
							fhdm = data[i].value;
						}
					}
				}	
			 }	
		 });
		
		return fhdm;
	},
	
	initDLJSSJ:function(ssdl){
		var dljssj = "";
		$.ajax({
		     url:'../TblBaseTypeAction/selectByCode?code=DLJSSJ',
			 type:"post",
			 dataType:"json",
			 async: false,
			 success: function(data){ 
				if(data != null){
					for(var i=0;i<data.length;i++){
						if(data[i].name == ssdl){
							dljssj = data[i].value;
						}
					}
				}	
			 }	
		 });
		
		return dljssj;
	}
}