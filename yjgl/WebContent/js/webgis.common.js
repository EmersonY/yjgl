   /**
	 * 根据空间关键字获取对应信息
	 * add by zcr 2015-10-22 9:34
	 */
	
function TestSelectById(urlAddress){
		var result="";
		$.ajaxSetup({async : false}); 
		$.ajax({
			url:urlAddress,
			success:function(data){
				result=data;
				}
		});
		result=eval(result);
		return result;
	}
	
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
	$.ajax({
		 url:"../bsPoint/selectPointByLayerid.action?&layer_id="+layerid,
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
		data:_option
//		onSelect:function(record){
//			if(record.value == "-1"){
//				$("#"+id).combobox('setValue','-1');
//			}else{
//				$("#"+id).combobox('unselect','-1');
//			}
//		}
	});  
}


///视图生成方法
function common_stringArr(fields, frozen, rowData, checkName){
	var stringArray = [];
    stringArray.push('<td colspan="' + fields.length + '" style="width:314px;">');
    if (!frozen && rowData.image_id){
        stringArray.push('<img src="'+ ("../tImageSrc/viewImageByPKey.action?e.image_id="+rowData.image_id) + '" style="width:32px;height:32px;margin-top:14px;margin-left:10px;float:left" />');
        stringArray.push('<div id="map_'+rowData.layer_id+'" markatt="'+checkName+'" style="float:left;margin-left:10px;">');
        stringArray.push('<p><span>图层：</span>'+rowData.layer_name+'</p>');
        var bb = rowData.service_time;
        stringArray.push('<p><span>版本：</span>' + bb + '&nbsp;<a  markatt='+rowData.service_type+'  href="'+rowData.service_url+'" style="width:30px;float:right;">更多</a></p>');
        stringArray.push('</div>');
        
        stringArray.push('<div style="float:right;margin:20px 0px;">');
        stringArray.push('<input type="checkbox" name="'+checkName+'" title="'+rowData.service_type+'" id="'+rowData.service_url+'" value="'+rowData.layer_id+'" style="width:28px;float:right;"/>');
   	 	stringArray.push('</div>');
    }
    stringArray.push('</td>');
    return stringArray;
}

function common_stringArray(fields, frozen, rowData, rowIndex,checkName){
	var stringArray = [];
   	stringArray.push('<div id="div_'+rowData.layer_id+'" markatt="'+checkName+'" style="width: 240px;height: 60px;float: left; border:1px dashed #C3D9E0;border-left:0;border-top:0;">');
   	if(!frozen){
		stringArray.push('<div style="width:60px;float:left"><img src="../images/webgis/icons/icon-map-dzdt.png" style="width:48px;height:48px;margin-top:4px;margin-left:4px;float:center;" /></div>');
		stringArray.push('<div id="map_'+rowData.layer_id+'" markatt="'+checkName+'" style="width:180px;float:left"><div style="height: 30px;">图层：'+rowData.layer_name+'<input type="checkbox" name="'+checkName+'" title="'+rowData.service_type+'" id="'+rowData.service_url+'" value="'+rowData.layer_id+'"  style="width:28px;float:right;" ></div>');
		stringArray.push('<div style="height: 30px;">');
		stringArray.push('<span id="'+ rowData.layer_id + '" style="width:145px;" data-options="showTip:true" class="easyui-slider" title="' + rowData.service_url + '"></span>');
		stringArray.push('</div>');
		stringArray.push('</div>');
    }
    stringArray.push('</div>');	
//    stringArray.push('<div uid="_open_detaillayer_win" pid="'+rowData.pid+'" style="width:10px;height:60px;float:right;background-image:url(\'../images/webgis/right_layer.png\');"></div>');
    return stringArray;
    }

function common_playlayer(fields, frozen, rowData, rowIndex,checkName){
	var stringArray = [];
	   	stringArray.push('<div  style="width: 180px;height: 60px;float: left; border:1px dashed #C3D9E0;border-left:0;border-top:0;margin-left:8px;margin-top:5px;">');
	   	if(!frozen){
			stringArray.push('<div id="map_'+rowData.layer_id+'" markatt="'+checkName+'" style="width:180px;float:center"><div style="height: 30px;">图层：'+rowData.layer_name+'</div>');
			stringArray.push('<div style="height: 30px;">版本：'+rowData.service_time+'<input type="checkbox" name="'+checkName+'" title="'+rowData.service_type+'" id="'+rowData.service_url+'" value="'+rowData.layer_id+'"  style="width:28px;float:right;" >');
			stringArray.push('</div>');
			stringArray.push('</div>');
	    }
	    stringArray.push('</div>');	
	    return stringArray;
}


function common_FormatDate (strTime) {
	if(strTime!=null&&strTime!=''){
		if(strTime.length>10){
			strTime=strTime.substring(0,10); 
			return strTime;
	    }
	}else{
		return;
	}
}
/**
 * 选中复选框后的添加图层事件。
 */
function common_addLayer(url,type){
	var agoLayer;
	if(type == "tiled")
		agoLayer = new esri.layers.ArcGISTiledMapServiceLayer(url);
	else if(type == "image")
		agoLayer = new esri.layers.ArcGISImageServiceLayer(url);
	else
		agoLayer = new esri.layers.ArcGISDynamicMapServiceLayer(url);
	map.addLayer(agoLayer);
	
	//获取当前可显示的图层
	layers = map.getLayersVisibleAtScale();
	/*var common_length=layers.length;
	if(common_length!=0 && layers[common_length-1].url==null){
		layers[common_length-1].url=url;
	}*/
}

/**
 * 选中复选框后删除图层事件
 */
function common_deleteLayer(url){
	for(var i = 0; i < layers.length; i++){
		if(url == layers[i].url){
			var targetLayer=map.getLayer(layers[i].id);
			if(targetLayer==null){return;}
			map.removeLayer(targetLayer);
			
			//获取当前可显示的图层
			layers = map.getLayersVisibleAtScale();
			return;
		}
	}
}

/**
 * 把删除的图层从列表中移除。
 */
function common_deleteRow(url){
	var datas = $('#yjz').datagrid('getData').rows;
	for(var i=0; i<datas.length; i++){
		if(url == datas[i].service_url){
			$('#yjz').datagrid('deleteRow',i);
			if(document.getElementById(url) != null)
				document.getElementById(url).checked = false;
			return;
		}
	}
}