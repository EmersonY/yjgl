//add by zcr 2015-10-29 9:13
function initSelectLayer(tid,id){
	var _option=[];
	_option.push({name:"--请选择--",value:-1,selected:true});
	$.ajax({
		 url:"../layer/layerAction-selectLayerByType.action",
		 type:"post",
		 dataType:"json",
		 async: false,
		 success: function(data){ 
			if(data != null){
				for(var i=0;i<data.length;i++){
					var paras= new Array(); 
					paras[0]=data[i].layer_id;
					paras[1]=data[i].service_url;
					paras[2]=data[i].query_type;
					_option.push({name:data[i].layer_name,value:paras});
				}
			}	
		 }	
	 });
	$("#"+id).combobox({
		valueField: 'value',
		textField: 'name',
		editable: false,
		data:_option,
		onSelect:function(record){
			if(id == "layerSelect"){
				listSearchChangeLayer(tid,id);
				initSearchMapLoad();
			}
//			else if(id == "editSelectLayer")
//				{
//				listEditChangeLayer(tid,id);
//				initEditMapLoad();
//				}
			if(record.value == "-1"){
				$("#"+id).combobox('setValue','-1');
			}else{
				$("#"+id).combobox('unselect','-1');
			}
		}
	});  
}



//add by zcr 2015-10-30 11:22
function initSelectFeature(id,layerid,isDefault){
	var _option=[];
	if(isDefault)
		_option.push({name:"--请选择--",value:-1,selected:true});
//	var uuuu="../layer/layerAction-selectFeatureByLayerid.action&layer_id="+layerid;
	$.ajax({
		 url:"../layer/layerAction-selectFeatureByLayerid.action?&layer_id="+layerid,
		 type:"post",
		 dataType:"json",
		 async: false,
		 success: function(data){ 
			if(data != null){
				for(var i=0;i<data.length;i++){
					_option.push({name:data[i].point_name,value:data[i].layer_no});
				}
			}	
		 }	
	 });
	$("#"+id).combobox({
		valueField: 'value',
		textField: 'name',
		editable: false,
		data:_option,
		onSelect:function(record){
//			if(id =='editFeatureSelect'){
//				listEditChangeFeature(id);
//			}else{
				listSearchChangeFeature(id);
//			}
			if(record.value == "-1"){
				$("#"+id).combobox('setValue','-1');
			}else{
				$("#"+id).combobox('unselect','-1');
			}
		}
	});  
}

//格式化时间
function FormatDate (strTime) {
	if(strTime.length>10){
		strTime=strTime.substring(0,10); 
		return strTime;
	}else{
		return;
	}	
}

/**
 * js获取项目根路径，如： http://localhost:8083/uimcardprj
 * @returns
 */
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}


/**
 * 初始化区级行政区划数字字典
 * @param id   需要初始化的input的id (必须参数)
 * @param isSetDefault (是否显示:--请选择--，--请选择--默认值为-1)
 * @param isMultiple (是否多选:true，否 默认值为false)
 */
function initQjxzqhSelectBox(id,isSetDefault,isMultiple,pid){
	var _option=[];
	if(isMultiple){
	}else{
		isMultiple=false;
	}
	if(isSetDefault)
		_option.push({name:"--请选择--",value:-1,selected:true});
	$.ajax({
		 url:"../tBaseType/tBaseTypeAction-selectByQjxzqh.action?tBaseTypeEntity.code=XZQH&tBaseTypeEntity.pid="+pid,
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
		onSelect:function(record){
			if(record.value == "-1"){
				$("#"+id).combobox('setValue','-1');
			}else{
				$("#"+id).combobox('unselect','-1');
			}
		}
	}); 
}

/**
 * 初始化数字字典
 * @param target   需要初始化的input的节点 (必须参数)
 * @param json 数据json
 * @param isSetDefault (是否显示:--请选择--，--请选择--默认值为-1)
 * @param isMultiple (是否多选:true，否 默认值为false)
 */
function initSelectBoxByJson(target,json,isSetDefault,isMultiple){
	if(!(json instanceof Array))
		json = [];	
	if(isSetDefault)
		json.unshift({name:"--请选择--",value:-1,selected:true});
	target.combobox({
		valueField: 'value',
		textField: 'name',
		editable: false,
		multiple: isMultiple,
		data:json
	});  
}

/**
 * 初始化下拉树
 * @param id   需要初始化的input的id (必须参数)
 * @param code 对应类型的编码 (必须参数)
 * @param isSetDefault (是否显示:--请选择--)
 * @param checkbox (是否显示:复选框)
 */
function initTreeSelectBox(id,code,isSetDefault,checkbox){
	var _option=[];
	if(isSetDefault)
		_option.push({code:code,id:"-1",name:"--请选择--",pid:-1,sequ:-99,status:1,updateTime:"1900-01-01 00:00:00",value:"-1"});
	$.ajax({
		 url:"../tBaseType/tBaseTypeAction-selectByCode.action?tBaseTypeEntity.code="+code,
		 type:"post",
		 dataType:"json",
		 async: false,
		 success: function(data){ 
			if(data != null){
				for(var i=0;i<data.length;i++){
					_option.push({code:data[i].code,id:data[i].id,name:data[i].name,pid:data[i].pid,sequ:data[i].sequ,status:data[i].status,updateTime:data[i].updateTime,value:data[i].value});
				}
			}	
		 }	
	 });
	$("#"+id).combotree({
		data:_option,
		idField:'id',
        textField: 'name',
        parentField: 'pid',
        valueField: 'value',
        checkbox:checkbox,
        attributes: ['id','code','name','value','pid','type','updateTime','status']
	});  
	if(isSetDefault)
		$("#"+id).combotree("setValue",-1);
}


/**
 * 设置下拉树所选择的value
 * @param id
 */
function setTreeValue(id){
	var t = $("#"+id).combotree("tree").tree('getSelected');
	ajax("../tBaseType/tBaseTypeAction-selectByKey.action",{"tBaseTypeEntity.id":t.id},function(e){
		$("#"+id).combotree('setValue',e.value);
	});
}
/**
 * 设置下拉树所选择的id
 * @param id
 * @param code
 * @param value
 */
function setTreeId(id,code,value){
	ajax("../tBaseType/tBaseTypeAction-selectByCodeAndValue.action",{"tBaseTypeEntity.code":code,"tBaseTypeEntity.value":value},function(e){
		$("#"+id).combotree('setValue',e.id);
	});
}
/**
 * 隐藏datagrid按钮
 * @param sequ 按钮的索引（必须参数）
 */
function hideToolButton(sequ){
	$('div.datagrid-toolbar a').eq(sequ).hide();
}

/**
 * 显示datagrid按钮
 * @param sequ 按钮的索引（必须参数）
 */
function showToolButton(sequ){
	$('div.datagrid-toolbar a').eq(sequ).hide();
}

function formatType(code,value) {
		var name = "";
		if(value == null)
			return null;
		var valueArray = value.split(",");
		if(valueArray != null && valueArray.length>0){
			for(var i = 0 ;i < valueArray.length;i++){
				$.ajax({
					 url:"../tBaseType/tBaseTypeAction-selectByCodeAndValue.action?tBaseTypeEntity.code="+code+"&tBaseTypeEntity.value="+trim(valueArray[i]),
					 type:"post",
					 dataType:"json",
					 async:false,
					 success: function(result){
						if(result != null){
							name += "," + result.name;
						}
					 }	
				 });
			}
			if(name != null && name.length > 0)
				name = name.substring(1, name.length);
		}else{
			return "";
		}
	
	return name;
}

	
	
	
/**
 * 格式化数字字典(树形)
 * @param value 字典的值（必须参数）
 */
function formatTreeType(value) {
	var name = "";
	if(value == null)
		return null;
	$.ajax({
		 url:"../tBaseType/tBaseTypeAction-selectByKey.action?tBaseTypeEntity.id="+value,
		 type:"post",
		 dataType:"json",
		 async:false,
		 success: function(result){
			if(result != null){
				name = result.name;
			}
		 }	
	 });
	return name;
}	

/**
 * 验证是否至少一条记录
 * @param json 需要判断的json数组（必须参数） 
 */
function validateMoreRecord(json,noMessage) {
	if(noMessage == null || noMessage == "undefined" || noMessage == ""){
		noMessage = "请至少选择一条记录！";
	}
	if(json.length < 1){
		$.messager.alert('警告',noMessage);
		return false;
	}else
		return true;
}

/**
 * 验证记录是否为一条，若不为一条，并弹出警告窗口
 * @param json 需要判断的json数组（必须参数） 
 */
function validateOneRecord(json,noMessage,moreMessage) {
	if(noMessage == null || noMessage == "undefined" || noMessage == ""){
		noMessage = "请选择一条记录！";
	}
	if(moreMessage == null || moreMessage == "undefined" || moreMessage == ""){
		moreMessage = "最多只能选择一条记录！";
	}
	if(json.length < 1){
		$.messager.alert('警告',noMessage);
		return false;
	}if(json.length > 1){
		$.messager.alert('警告',moreMessage);
		return false;
	}else
		return true;
}

/**
 * 弹出窗口
 * @param id 窗口id（必须参数）
 * @param title 窗口的标题(必须参数)
 * @param onClose 关闭行为（非必须参数：默认为：清空该id下所有form中的数据）
 * @param formTargets 要验证表单的改变的表单数组节点（非必须参数：默认为：不验证,若formTargets中有节点值时，则将重构onBeforeClose函数，表现为：表单数据被改变，关闭前提示是否保存数据后关闭）
 */
function openWindow(id,title,formTargets,onClose,href){
	var parentWidth = $(window).width();
	var width = $("#"+id).parent().outerWidth();
	if(width > parentWidth)
		width = parentWidth;
	var parentHeight = $(window).height();
	var height = $("#"+id).parent().outerHeight();
	if(height > parentHeight)
		height = parentHeight;
	var left = (parentWidth - width)/2;
	var top = (parentHeight - height)/2;
	//关闭后
	if(onClose == null || onClose == "" || onClose == "undefined"){
		onClose = function(){};
	}
	//关闭前
	var onBeforeClose = function(){return true;};
	if(formTargets instanceof Array && formTargets.length > 0){
		onBeforeClose = function(){};
	}

	if(href == null || href == "" || href == "undefined"){
		$("#"+id).window({
			width:width,
			height:height,
			top:top,
			left:left,
			title:title,
			onBeforeClose:onBeforeClose,
			onClose:onClose
		}).window('open');
	}else{

		basepara.setPara(href);//把参数设置为全局变量，向下一个页面传递，需要引入basepara.js
		$("#"+id).window({
			width:width,
			height:height,
			top:top,
			left:left,
			title:title,
			onBeforeClose:onBeforeClose,
			onClose:onClose,
			href:href
		}).window('open');
	}

}

/**
 * 初始化编辑数据
 * @param formid 表单的id（必须参数）
 * @param e 数据map
 */
function initFormData(formTarget,e){
	if(e == null)
		return;
	str = "{";
	for (var key in e) {  
		str += "'"+key +"':" + "e."+key + ",";
    } 
	if(str != "{")
		str  = str.substring(0, str.length-1);
	str += "}";
	var json = eval('(' + str + ')'); 
	formTarget.form('load',json);
}

/**
 * 批量删除
 * @param url 删除的url (必须参数)
 * @param data 传递参数(必须参数,没有请填写null)
 * @param success 成功操作(非必须参数,若为，default时，默认判断返回时是否大于0，若>则弹出成功标志，并刷新页面)
 * @param message 删除的提示信息(非必须参数,默认提示信息：您确定要删除选中记录吗?)
	注：若后非必须参数有需要写上时，必须将该参数之前的参数一一补齐，可用null补上
 */
function deleteBatch(url,data,success,message){

	initToken();
	
	if(url == null || url == "" || url == "undefined")
		return false;
	if(success == null || success == "undefined")
		success = function(data,textStatus){};
	else if(success == "default"){
		success = function(data,textStatus){
			if(data != null && data > 0){
				$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
				$('#_list').datagrid('reload');	
			}
		};
	}	
	if(message == null || message == "" || message == "undefined")
		message = "您确定要删除选中记录吗?";
	$.messager.confirm('确认删除',message,function(r){   
		if(r){
			$.ajax({
				url:url,
				data:data,
				type:"post",
				dataType:"json",
				async: false,
				success: function(result,textStatus){ 
					success(result,textStatus);
				}	
			});
		}
	}); 
}

/**
 * 关闭窗口
 * @param id 窗口id（必须参数）
 * @param isBeforeColse 是否执行关闭前事件 (true 表示不执行关闭事件，其他的表示执行关闭事件，默认为执行)
 */
function closeWindow(id,isBeforeColse){
	if(isBeforeColse == true)
		$("#"+id).window('close',true);
	else
		$("#"+id).window('close');
}

function operateBatch(url,data,success,message){
	if(url == null || url == "" || url == "undefined")
		return false;
	if(success == null || success == "undefined")
		success = function(data,textStatus){};
	else if(success == "default"){
		success = function(data,textStatus){
			if(data != null && data > 0){
				$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
				$('#_list').datagrid('reload');	
			}
		};
	}	
	if(message == null || message == "" || message == "undefined")
		message = "您确定要删除选中记录吗?";
	$.messager.confirm('确认删除',message,function(r){   
		if(r){
			$.ajax({
				url:url,
				data:data,
				type:"post",
				dataType:"json",
				async: false,
				success: function(result,textStatus){ 
					success(result,textStatus);
				}	
			});
		}
	}); 
}

function validateFormChanged(formTarget){
	var isChanged = isFormChanged(formTarget);
	if(!isChanged){
		$.messager.alert('警告',"数据未做任何修改，若要关闭此窗口，请点击关闭按钮！");
		return false;
	}
	return true;
}
/**
 * 验证单元非空
 * @param json   json格式[{"id":id,"msg":"弹出消息","type":(表单类型:select、date等)}]
 * @returns {Boolean}
 */
function validateRequired(json){
	if(json instanceof Array && json.length > 0){
		for(var i = 0; i<json.length; i++){
			if(json[i].type == "select"){	//下拉框
				var value = $("#"+json[i].id).combobox("getValue");
				if(trim(value) == null || trim(value) == "" || trim(value) == -1){
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}else if(json[i].type == "date"){	//datebox
				var value = $("#"+json[i].id).datebox("getValue");
				if(trim(value) == null || trim(value) == "" ){ //!isDate(value)){判断是否为日期格式
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}else if(json[i].type == "datetime"){	//datetimebox
				var value = $("#"+json[i].id).datetimebox("getValue");
				if(trim(value) == null || trim(value) == "" ){
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}else if(json[i].type == "number"){	//numberbox
				var value = $("#"+json[i].id).numberbox("getValue");
				if(trim(value) == null || trim(value) == ""){
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}else if(json[i].type == "numberspinner"){	//numberspinner
				var value = $("#"+json[i].id).numberspinner("getValue");
				if(trim(value) == null || trim(value) == ""){
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}else if(json[i].type == "timespinner"){	//timespinner
				var value = $("#"+json[i].id).timespinner("getValue");
				if(trim(value) == null || trim(value) == ""){
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}else{
				var value = $("#"+json[i].id).val();
				if(trim(value) == null || trim(value) == ""){
					$.messager.alert('警告',json[i].msg);
					return false;
				}
			}
			
		}
	}
	return true;
}

/**
 * 获取当前tab名称
 * @param id
 * @returns title
 */
function getSelectedTabName(id){
	return $('#'+id).tabs('getSelected').panel('options').title;
}


/**
 * 批量导出
 * @param url 导出的url (必须参数)
 * @param data 传递参数(必须参数,没有请填写null)
 * @param success 成功操作(非必须参数,若为，default时，默认判断返回时是否大于0，若>则弹出成功标志，并刷新页面)
 * @param message 导出的提示信息(非必须参数,默认提示信息：您确定要导出选中记录吗?)
	注：若后非必须参数有需要写上时，必须将该参数之前的参数一一补齐，可用null补上
 */
function importBatch(url,data,success,message){
	if(url == null || url == "" || url == "undefined")
		return false;
	if(success == null || success == "undefined")
		success = function(data,textStatus){};
		else if(success == "default"){
			success = function(data,textStatus){
				if(data != null && data > 0){
					$.messager.show({title:'系统消息',msg:'导出记录成功！',showType:'slide'});
					$('#_list').datagrid('reload');	
				}
			};
		}	
	if(message == null || message == "" || message == "undefined")
		message = "您确定要导出吗?";
	$.messager.confirm('确认导出',message,function(r){   
		if(r){
			$.ajax({
				url:url,
				data:data,
				type:"post",
				dataType:"json",
				async: false,
				success: function(result,textStatus){ 
					success(result,textStatus);
				}	
			});
		}
	}); 
}

/**
 * 令牌初始化
 */
function initToken(){
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
});

/**
 * 进度条加载
 */
function closes(){
		$("#loading").fadeOut("normal",function(){
			$(this).remove();
		});
	};
	var delayTime;

	$.parser.onComplete = function(){
		if(delayTime){
			clearTimeout(delayTime);
		}
		delayTime = setTimeout(closes, 100);
};
}