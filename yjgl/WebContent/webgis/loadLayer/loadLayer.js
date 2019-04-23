require(["dojo/topic","dojo/_base/lang","dojo/domReady!" ],
	function(topic,lang) {
	
	var loadLayer_yjzData;
	var map = mapManager.map;
	
//	topic.subscribe(global.APP_LAYER_OPEN, lang.hitch(this, yjzLayer));
	checkTimeOut();
	function checkTimeOut(){
		debugger
		initToken();
		$.ajax({  
			type:"POST",  
	        url: "../main/timeOut",  
	        success:function(result){  
	        	if(result){
	        		location.href="../base/login.jsp?login=-2"; //跳转到登陆页面
	        	}
	        }  
	    });
	};

	var yjzCardview = $.extend({},$.fn.datagrid.defaults.view,{
		renderRow : function(target, fields, frozen, rowIndex,rowData) {
			loadLayer_yjzData.push(rowData);
			var stringArray = [];
			if(rowData.is_load != 1){
				stringArray.push('<td colspan="' + fields.length + '" style="width:230px;height:60px;">');
				if (!frozen) {
					stringArray.push('<div>');
					stringArray.push('<div><img src="../images/webgis/icons/icon-map-dzdt.png" style="width:48px;height:48px;margin-top:10px;margin-left:10px;float:left;" /></div>');
					stringArray.push('<div style="float:left;top:5px;padding:5px;">');
					stringArray.push(rowData.layer_name + '<br><br>');
					stringArray.push('<div id="'+ rowData.layer_id + '" style="width:130px;display:inline-block;" title="' + rowData.service_url + '"></div><span style="float:right;margin-left:8px;" id="opacity_'+rowData.layer_id+'"></span>');
					stringArray.push('</div>');
					stringArray.push('</td>');
					stringArray.push('<td name="deleteImage'+ rowIndex + '" style="background-color:#E1E1E0;"><div style="float:right;margin:20px 15px;">');
					stringArray.push('<img src="../images/webgis/layer/del.png" onClick="loadLayer_deleteHandler(\''
									+ rowData.layer_id + '\')" style="float:right;" />');
					stringArray.push('</div>');
					stringArray.push('</td>');
				}
			}
			return stringArray.join('');
		}
	});
	
	var jcview = $.extend({}, $.fn.datagrid.defaults.view, {
	    renderRow: function(target, fields, frozen, rowIndex, rowData){
	        var stringArray = common_playlayer(fields, frozen, rowData, rowIndex,"moreLayers");
	        return stringArray.join('');
	    }
	});
	
	baseLayerInit();
	
	function baseLayerInit(){
		debugger;
		$('#_baseLayer_win').datagrid({
			url:"../webgis/loadLayer/init_layer.json",
			view:jcview,
			fit:true,
			onLoadSuccess:function(){
				$("input[name='moreLayers']").bind("click",function(event){
					event.stopPropagation();
					if(this.checked){
						mapManager.layerVisibleByUrl(this.value,true);
					}else{
						mapManager.layerVisibleByUrl(this.value,false);
					}
				});
				var row = $('#_baseLayer_win').datagrid('getData').rows;
				layers = map.getLayersVisibleAtScale();
				for(var n = 0; n < row.length; n++){
					$("input[name='moreLayers'][value='"+row[n].layer_id+"']")[0].checked = false;
//					document.getElementById(row[n].service_url).checked = false;
					for(var m = 0; m < layers.length; m++){
						if(row[n].service_url == layers[m].url){
							$("input[name='moreLayers'][value='"+row[n].layer_id+"']")[0].checked = true;
							break;
						}
					}
				}
			}
		});
	}
//	loadLayer_layerOperation();
//	yjzLayer();
	function yjzLayer(){
		// 获取当前可显示的图层
		layers = map.getLayersVisibleAtScale();
		var layerIds = map.layerIds;
		layerIds = layerIds.concat(map.graphicsLayerIds);
		var urlArray = "";
		if (layers.length != 0) {
			
			for(var i = 0; i < layerIds.length; i++) {
				for(var j = 0; j < layers.length; j++) {
					if(layerIds[i] == layers[j].id && layerIds[i].indexOf("graphicsLayer") == -1){
						urlArray += "&urlArray=" + layers[j].url;
						break;
					}
				}
			}
		} else {
			// 当没有图层时，若已加载页面有数据删除已加载数据
			if ($('#yjz').datagrid('getData').rows.length != 0) {
				$('#yjz').datagrid('deleteRow', 0);
			}
			return;
		}
		loadLayer_yjzData = [];

		// 将地图上的所有图层根据url从数据库中返回对应图层信息
		$('#yjz').datagrid({
			url : "../layer/selectByURL.action?"+ urlArray.substring(1),
			view : yjzCardview,
			onLoadSuccess : function(data) {
				$("#layer_win .datagrid-btable tbody tr").each(function() {
					if($(this).html() == null || $(this).html() == ""){
						$(this).remove();
					}
				});
				for (i = 0; i < data.rows.length; i++) { // add the rows
					var id = data.rows[i].layer_id;
					var opacity = 1;
					for(var j=0; j<layers.length; j++){
						if(id == layers[j].id){
							opacity = layers[j].opacity;
							break;
						}
					}
					console.log(id,opacity);
					 $( "#" + id ).slider({
					      range : "max",
					      min : 0,
					      max : 100,
					      value : opacity * 100,
					      step : 10,
					      slide : function( event, ui ) {
					    	$(this).next().html(ui.value);
					    	var percent = (ui.value / 100).toFixed(1);
							for (var k = 0; k < layers.length; k++) {
								if (this.title == layers[k].url) {
									var targetLayer = map.getLayer(layers[k].id);
									if (targetLayer == null) {
										return;
									}
									targetLayer.setOpacity(percent);
									return;
								}
							}
					      }
					    });
					 $("#" + id).next().html(opacity * 100);
					 
				}
			},
			onClickRow : function(rowIndex, rowData) {
				selectDelImage(rowIndex);
			}
		});
	}
	
	//选中资源列表后颜色设置
	function selectDelImage(rowIndex){
		var table = $("#layer_win table[class='datagrid-btable']")[0].childNodes[0].childNodes;
		if(table == null || table.length == 0 || table.length <= rowIndex)
			return;
		for(var i = 0; i < table.length; i++){
			if(i == rowIndex)
				continue;
			table[i].childNodes[1].style.cssText = "background:#E1E1E0;";
		}
		table[rowIndex].childNodes[1].style.cssText = "background:#1F7DCB;";
	}
	
	/**
	 * 图层操作事件（置顶、上移、下移、置底）
	 */
	function loadLayer_layerOperation() {
		/**
		 * 置顶图层
		 */
		$('#topLayer').click(function() {
			var data = $('#yjz').datagrid('getSelections');
			if (data.length < 1) {
				alert( '请选择一条记录！');
				return;
			}
			if (data.length > 1) {
				alert( '一次只能上移一条记录！');
				return;
			}
			var rowIndex = $('#yjz').datagrid('getRowIndex', data[0]);
			var datas = $('#yjz').datagrid('getData').rows;
			if (rowIndex == 0) {
				alert( '该记录为最顶条，无法上移！');
				return;
			}
	
			var topRow = datas[rowIndex];
			for (var i = rowIndex; i > 0; i--) {
				datas[i] = datas[i - 1];
			}
			datas[0] = topRow;
	
			$('#yjz').datagrid('loadData', datas);
	
			layer_moveUpOrDown(data[0], "top");
			$('#yjz').datagrid('selectRow', 0);
			selectDelImage(0);
		});
	
		/**
		 * 上移图层
		 */
		$('#upLayer').click(function() {
			var data = $('#yjz').datagrid('getSelections');
			if (data.length < 1) {
				alert( '请选择一条记录！');
				return;
			}
			if (data.length > 1) {
				alert( '一次只能上移一条记录！');
				return;
			}
			var rowIndex = $('#yjz').datagrid('getRowIndex', data[0]);
			var datas = $('#yjz').datagrid('getData').rows;
			if (rowIndex == 0) {
				alert( '该记录为最顶条，无法上移！');
				return;
			}
			var upRow = datas[rowIndex];
			datas[rowIndex] = datas[rowIndex - 1];
			datas[rowIndex - 1] = upRow;
	
			$('#yjz').datagrid('loadData', datas);
	
			layer_moveUpOrDown(data[0], "up");
			$('#yjz').datagrid('selectRow', rowIndex - 1);
			selectDelImage(rowIndex-1);
		});
	
		/**
		 * 下移图层
		 */
		$('#downLayer').click(function() {
			var data = $('#yjz').datagrid('getSelections');
			if (data.length < 1) {
				alert( '请选择一条记录！');
				return;
			}
			if (data.length > 1) {
				alert( '一次只能下移一条记录！');
				return;
			}
			var rowIndex = $('#yjz').datagrid('getRowIndex', data[0]);
			var datas = $('#yjz').datagrid('getData').rows;
			if (rowIndex == datas.length - 5) {
				alert( '该记录为最底层，无法下移！');
				return;
			}
	
			var downRow = datas[rowIndex];
			datas[rowIndex] = datas[rowIndex + 1];
			datas[rowIndex + 1] = downRow;
	
			$('#yjz').datagrid('loadData', datas);
	
			layer_moveUpOrDown(data[0], "down");
			$('#yjz').datagrid('selectRow', rowIndex + 1);
			selectDelImage(rowIndex+1);
		});
	
		/**
		 * 置底图层
		 */
		$('#bottomLayer').click(function() {
			var data = $('#yjz').datagrid('getSelections');
			if (data.length < 1) {
				alert( '请选择一条记录！');
				return;
			}
			if (data.length > 1) {
				alert( '一次只能下移一条记录！');
				return;
			}
			var rowIndex = $('#yjz').datagrid('getRowIndex', data[0]);
			var datas = $('#yjz').datagrid('getData').rows;
			if (rowIndex == datas.length - 5) {
				alert( '该记录为最底层，无法下移！');
				return;
			}
	
			var bottomRow = datas[rowIndex];
			for (var i = rowIndex; i < datas.length - 5; i++) {
				datas[i] = datas[i + 1];
			}
			datas[datas.length - 5] = bottomRow;
	
			$('#yjz').datagrid('loadData', datas);
	
			layer_moveUpOrDown(data[0], "bottom");
			$('#yjz').datagrid('selectRow', datas.length - 1);
			selectDelImage(datas.length-1);
		});
	}
	
	/**
	 * 移动图层
	 */
	function layer_moveUpOrDown(current, np) {
		for (var i = 0; i < layers.length; i++) {
			if (current.service_url == layers[i].url) {
				if (np == "top") {
					for (var j = i; j < layers.length - 1; j++) {
						map.reorderLayer(layers[i], j + 1);
					}
				}
				if (np == "up") {
					map.reorderLayer(layers[i], i + 1);
				}
				if (np == "down") {
					map.reorderLayer(layers[i], i - 1);
				}
				if (np == "bottom") {
					for (var j = i; j > 4; j--) {
						map.reorderLayer(layers[i], j - 1);
					}
				}
				// 获取当前可显示的图层
				layers = map.getLayersVisibleAtScale();
				console.log("layers:",layers);
				return;
			}
		}
	}
	
	
});

/**
 * 选中复选框后删除图层事件
 */
function loadLayer_deleteHandler(id) {
	mapManager.removeLayerById(id);
	global.removeSearchEvent(id);
	layers = mapManager.map.getLayersVisibleAtScale();
	var datas = $('#yjz').datagrid('getData').rows;
	for(var i=0; i<datas.length; i++){
		if(id == datas[i].layer_id){
			$('#yjz').datagrid('deleteRow',i);
			$('#yjz').datagrid('clearSelections');
			return;
		}
	}
}

