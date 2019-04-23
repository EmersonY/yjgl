		var map;
		var gra;
require(["dojo/_base/lang","esri/config","esri/map","esri/dijit/editing/TemplatePicker","esri/toolbars/edit","esri/dijit/editing/Editor","esri/tasks/query","esri/tasks/QueryTask","esri/layers/FeatureLayer",
         "esri/toolbars/draw","dojo/keys","dojo/parser","dojo/_base/array","dojo/topic","dojo/domReady!"
        ], function (
		lang,esriConfig, Map,TemplatePicker,Edit,Editor, Query,QueryTask,FeatureLayer,Draw, keys, parser, arrayUtils,topic
        ) {
        topic.subscribe(global.APP_EDITLAYER_OPEN, lang.hitch(this, baseEditInit));
 	    topic.subscribe(global.APP_EDITLAYER_CLOSE, lang.hitch(this, baseEdit_Close));

        esri.config.defaults.io.proxyUrl = "http://syseng.kingtopinfo.com:8110/arcgis_proxy/proxy.jsp";
        esri.config.defaults.io.alwaysUseProxy= false;
        
        map = mapManager.map;
        var editdraw = new Draw(map);
        var editToolbar = new Edit(map);
        var templatePicker;
        var addgraphic;
        var selectedtemp;
        var isDrag = false;
        var editmovestart = false;
		var addnbPathName = "";
		var addyjPathName = "";
		var addjjPathName = "";
        
        var operationsPolygonLayer = new FeatureLayer(XMSZFEA + "/0", {
          outFields: ["*"],
          className : "Feature",
          visible : false
        });
        
        map.addLayers([
    	               operationsPolygonLayer
                ]);
        
        var items = [
    	             { label: "窨井", symbol: symbolManager.baseSearch_pointSymbol, description: "窨井" },
    	           ];
        
        var nbpic_html = "<div class='apply_footer_upload_edit_webgis_nb flex'><div class='bg flex_item'><img id='_nb_edit_img' onclick='FeatureEdit.deleteImg(this,1)' width='70' height='70' style='display: block;background-size:cover;'></div></div>";
    	var jjpic_html = "<div class='apply_footer_upload_edit_webgis_jj flex'><div class='bg flex_item'><img id='_jj_edit_img' onclick='FeatureEdit.deleteImg(this,1)' width='70' height='70' style='display: block;background-size:cover;'></div></div>";
    	var yjpic_html = "<div class='apply_footer_upload_edit_webgis_yj flex'><div class='bg flex_item'><img id='_yj_edit_img' onclick='FeatureEdit.deleteImg(this,1)' width='70' height='70' style='display: block;background-size:cover;'></div></div>";
		templatePicker = new TemplatePicker({
//      		 featureLayers: layers,
//               rows: "auto",
           columns: 1,
           grouping: true,
           items:items,
           style: "height: auto; overflow: auto; width:100px;display:none"
	   	},"editorDiv");
        templatePicker.startup();
        
        function baseEditInit(){
        	mapManager.layerVisibleByUrl(_QPNL_ID,false);
        	operationsPolygonLayer.setVisibility(true);
        	global.queryHightLayer.clear();
        	global.mapClickHightLayer.clear();
        	global.mapHightLayer.clear();
//        	var layer = map.getLayersVisibleAtScale(map.getScale());
//        	var layers = [];
//        	for(var i = 0;i < layer.length;i++)
//        	if(layer[i].className == "Feature"){
//        		layers.push(layer[i]);
//        	}
        	templatePicker.attr("style","display:block");
        	templatePicker.update();
//		   	$('#editorDiv').attr("style","display:block;");
		   	
		   	
    		$('#_base_layer_esriEditor_window').attr("style","display:block");
		   	 templatePicker.on("selection-change",function(){
		        	selectedtemp = templatePicker.getSelected();
		        	if(selectedtemp != null){
//		        		switch (selectedtemp.featureLayer.geometryType){
//		             		case "esriGeometryPolygon":
//		             			editdraw.activate(Draw.POLYGON);
//		             			break;
//		             		case "esriGeometryPolyline":
//		             			editdraw.activate(Draw.POLYLINE);
//		             			break;
//		             		case "esriGeometryPoint":
//		             			editdraw.activate(Draw.POINT);
//		             			break;
//		        		}
		        		editdraw.activate(Draw.POINT);
		        		editdraw.fillSymbol = symbolManager.baseSearch_pointSymbol;
//		        		editdraw.fillSymbol = selectedtemp.featureLayer.renderer.getSymbol(selectedtemp.template.prototype);
		        		editdraw.on("draw-complete",editdrawevent);
		        	}
		        	
		        });
        }
        
        function baseEdit_Close(){
        	mapManager.layerVisibleByUrl(_QPNL_ID,true);
        	mapManager.layerRefreshByUrl(_INIT_QPNL);
        	operationsPolygonLayer.setVisibility(false);
        	global.editHightLayer.clear();
        	global.mapClickHightLayer.clear();
        	map.enablePan();
        	templatePicker.attr("style","display:none;");
        	templatePicker.update();
        }
        initWebBtnEvent();
        
        function editdrawevent(evt){
        	editdraw.deactivate();
        	global.mapClickHightLayer.clear();
        	var selectedtemp = templatePicker.getSelected();
        	addgraphic = new esri.Graphic(evt.geometry,symbolManager.baseSearch_pointSymbol);
//        	addgraphic = new esri.Graphic(evt.geometry,selectedtemp.featureLayer.renderer.getSymbol(selectedtemp.template.prototype));
        	global.editHightLayer.clear();
        	global.editHightLayer.add(addgraphic);
			BaseType.initSelectBox('_webgis_jgxlgl_list_add_jgcz','JGCZ', true, false, 80);
			BaseType.initSelectBox('_webgis_jgxlgl_list_add_jgxz','JGXZ', true, false, 80);
			BaseType.initSelectBox('_webgis_jgxlgl_list_add_sfzw','SFZW', true, false, 80);
			BaseType.initSelectEditBox('_webgis_jgxlgl_list_add_jglx','JGLX', false, false , 155, true);
			BaseType.initSelectBox('_webgis_jgxlgl_list_add_jgzt','JGZT', true, false, 80);
			BaseType.initSelectBox('_webgis_jgxlgl_list_add_ssdl','SSDL', false, false, 150);
			
			$('#_webgis_jgxlgl_list_add_jgcz').combobox('setValues','-1');
			$('#_webgis_jgxlgl_list_add_jgxz').combobox('setValues','-1');
			$('#_webgis_jgxlgl_list_add_sfzw').combobox('setValues','-1');
			$('#_webgis_jgxlgl_list_add_jgzt').combobox('setValues','-1');
			$('#_webgis_jgxlgl_list_add_jglx').combobox('setValues','雨水');
			$('#_webgis_jgxlgl_list_add_ssdl').combobox('setValues','前埔南路');
			$('#_webgis_jgxlgl_list_add_xzb').val(evt.geometry.x);
			$('#_webgis_jgxlgl_list_add_yzb').val(evt.geometry.y);
			addnbPathName = "";
			addyjPathName = "";
			addjjPathName = "";
			$('#_webgis_jgxlgl_list_add_window').window('open');
			templatePicker.clearSelection();
        }
        
        operationsPolygonLayer.on("click",function(evt){
        	var Currenteditgraphic = editToolbar.getCurrentState().graphic;
        	if(evt.graphic){
//        		editToolbar.activate(Edit.MOVE , evt.graphic);
//        		editmovestart = true;
//        		map.disablePan();
        		gra = evt.graphic;
        		global.mapClickHightLayer.clear();
            	var graphic = new esri.Graphic(evt.graphic.geometry,symbolManager.baseSearch_pointSymbol);
            	global.mapClickHightLayer.add(graphic);
    			BaseType.initSelectBox('_webgis_jgxlgl_list_edit_jgcz','JGCZ', true, false, 80);
    			BaseType.initSelectBox('_webgis_jgxlgl_list_edit_jgxz','JGXZ', true, false, 80);
    			BaseType.initSelectBox('_webgis_jgxlgl_list_edit_sfzw','SFZW', true, false, 80);
    			BaseType.initSelectEditBox('_webgis_jgxlgl_list_edit_jglx','JGLX', true, false , 155, true);
    			BaseType.initSelectBox('_webgis_jgxlgl_list_edit_jgzt','JGZT', true, false, 80);
    			BaseType.initSelectBox('_webgis_jgxlgl_list_edit_ssdl','SSDL', false, false, 150);
    			debugger;
    			if(gra.attributes.YJPIC != null && gra.attributes.YJPIC !=""){
    				if($('.apply_footer_upload_edit_webgis_yj').length == 0){
    					$('#apply_footer_upload_edit_webgis_yj_form').append(yjpic_html);
    				}
    				$("#_yj_edit_img").attr("src","../files/sjwh/" + gra.attributes.YJPIC.replace("JPG","jpg") +"");
    			}else{
    				$('.apply_footer_upload_edit_webgis_yj').remove();
    			}
    			if(gra.attributes.JJPIC != null && gra.attributes.JJPIC !=""){
    				if($('.apply_footer_upload_edit_webgis_jj').length == 0){	
    					$('#apply_footer_upload_edit_webgis_jj_form').append(jjpic_html);
    				}
    				$("#_jj_edit_img").attr("src","../files/sjwh/" + gra.attributes.JJPIC.replace("JPG","jpg") +"");
    			}else{
    				$('.apply_footer_upload_edit_webgis_jj').remove();
    			}
    			if(gra.attributes.NBPIC != null && gra.attributes.NBPIC !=""){
    				if($('.apply_footer_upload_edit_webgis_nb').length == 0){
    					$('#apply_footer_upload_edit_webgis_nb_form').append(nbpic_html);
    				}
    				$("#_nb_edit_img").attr("src","../files/sjwh/" + gra.attributes.NBPIC.replace("JPG","jpg") +"");
    			}else{
    				$('.apply_footer_upload_edit_webgis_nb').remove();
    			}
            	
    			if(gra.attributes.JGCZ != null && gra.attributes.JGCZ != ""){
    				$('#_webgis_jgxlgl_list_edit_jgcz').combobox('setValues',gra.attributes.JGCZ);
    			}
    			if(gra.attributes.JGLX != null && gra.attributes.JGLX != ""){
    				$('#_webgis_jgxlgl_list_edit_jglx').combobox('setValues',gra.attributes.JGLX);
    			}
    			if(gra.attributes.JGXZ != null && gra.attributes.JGXZ != ""){
    				$('#_webgis_jgxlgl_list_edit_jgxz').combobox('setValues',gra.attributes.JGXZ);
    			}
    			if(gra.attributes.SFZW != null && gra.attributes.SFZW != ""){
    				$('#_webgis_jgxlgl_list_edit_sfzw').combobox('setValues',gra.attributes.SFZW);
    			}
    			if(gra.attributes.JGZT != null && gra.attributes.JGZT != ""){
    				$('#_webgis_jgxlgl_list_edit_jgzt').combobox('setValues',gra.attributes.JGZT);
    			}
    			if(gra.attributes.SSDL != null && gra.attributes.SSDL != ""){
    				$('#_webgis_jgxlgl_list_edit_ssdl').combobox('setValues',gra.attributes.SSDL);
    			}
    			if(gra.attributes.JGGG != null && $.trim(gra.attributes.JGGG) != ""){
    				$('#_webgis_jgxlgl_list_edit_jggg').textbox('setValue',gra.attributes.JGGG);
    			}
    			if(gra.attributes.JNGJ != null && $.trim(gra.attributes.JNGJ) != ""){
    				$('#_webgis_jgxlgl_list_edit_jngj').textbox('setValue',gra.attributes.JNGJ);
    			}
    			if(gra.attributes.JGSL != null && gra.attributes.JGSL != ""){
    				$('#_webgis_jgxlgl_list_edit_jgsl').textbox('setValue',gra.attributes.JGSL);
    			}
    			if(gra.attributes.JS != null && gra.attributes.JS != ""){
    				$('#_webgis_jgxlgl_list_edit_js').textbox('setValue',gra.attributes.JS);
    			}
    			if(gra.attributes.QSDW != null && gra.attributes.QSDW != ""){
    				$('#_webgis_jgxlgl_list_edit_qsdw').textbox('setValue',gra.attributes.QSDW);
    			}
    			if(gra.attributes.GLDW != null && gra.attributes.GLDW != ""){
    				$('#_webgis_jgxlgl_list_edit_gldw').textbox('setValue',gra.attributes.GLDW);
    			}
//    			if(gra.attributes.DLJSSJ != null && gra.attributes.DLJSSJ != ""){
//    				$('#_webgis_jgxlgl_list_edit_dljssj').textbox('setValues',gra.attributes.DLJSSJ);
//    			}
    			$('#_webgis_jgxlgl_list_edit_jgbh').textbox("setValue",gra.attributes.JGBM);
    			$('#_webgis_jgxlgl_list_edit_jgbhjj').val(gra.attributes.JGBM);
    			$('#_webgis_jgxlgl_list_edit_jgbhyj').val(gra.attributes.JGBM);
    			$('#_webgis_jgxlgl_list_edit_jgbhnb').val(gra.attributes.JGBM);
    			$('#_webgis_jgxlgl_list_edit_xzb').val(evt.mapPoint.x);
    			$('#_webgis_jgxlgl_list_edit_yzb').val(evt.mapPoint.y);
    			$('#_webgis_jgxlgl_list_edit_window').window('open');
        	}else{
        		global.mapClickHightLayer.clear();
        	}
//	    		else if(evt.graphic == Currenteditgraphic){
//        		map.enablePan();
//        		debugger;
//        		editmovestart = false;
//        		operationsPolygonLayer.applyEdits(null,[evt.graphic],null);
//            	function applyEditsmove(){
//            		ajax("../YjgJgxxAction/edit",{'jgbh':evt.graphic.attributes.BH,'xzb':evt.graphic.geometry.X,'yzb':evt.graphic.geometry.Y},function(result){
//    					if(result.sec && result.rows > 0){
//    						$.messager.show({title:'系统消息',msg:'操作成功！',showType:'slide'});
//    					}else{
//    						if("undefined" != typeof(result.errorMsg)){
//    							$.messager.alert('警告',result.errorMsg);
//    						}else{
//    							$.messager.alert('警告','同步失败！');
//    						}
//    					}
//    				});
//            	};
//        	
//        	}
        })
        
        function initWebBtnEvent(){
        	$("#_webgis_jgxlgl_list_edit_save_button").bind('click',function(){
        		$.messager.progress({ // 显示进度条  
        			title:"正在处理",  
        			text:"正在处理...",  
        			interval:100  
        		}); 
				$('#_webgis_jgxlgl_list_edit_form').form('submit', {   
					url:"../YjgJgxxAction/edit",
					onSubmit: function(){
						debugger
						if($("#_webgis_jgxlgl_list_edit_jgzt").combobox('getText') == '-1'){
							$.messager.alert('警告','请选择井盖状态！');
							return false;
						}
						if($("#_webgis_jgxlgl_list_edit_ssdl").combobox('getText') == '-1'){
							$.messager.alert('警告','请选择所属道路！');
							return false;
						}
						if($("#_webgis_jgxlgl_list_edit_jglx").combobox('getText') == '-1'){
							$.messager.alert('警告','请选择井盖类型！');
							return false;
						}
					},   
					success:function(r){  
						debugger
						var result = eval('(' + r + ')');
						if(result.sec && result.rows > 0){
			        		gra.attributes.FHDM = BaseType.initJGLX($("#_webgis_jgxlgl_list_edit_jglx").combobox('getText'));
			        		gra.attributes.QSDW = $("#_webgis_jgxlgl_list_edit_qsdw").textbox('getValue');
			        		gra.attributes.GLDW = $("#_webgis_jgxlgl_list_edit_gldw").textbox('getValue');
			        		gra.attributes.JNGJ = $("#_webgis_jgxlgl_list_edit_jngj").textbox('getValue');
			        		gra.attributes.JGGG = $("#_webgis_jgxlgl_list_edit_jggg").textbox('getValue');
//			        		gra.attributes.JGSL = $("#_webgis_jgxlgl_list_edit_jgsl").textbox('getValue');
			        		gra.attributes.JS = $("#_webgis_jgxlgl_list_edit_js").textbox('getValue');
			        		gra.attributes.DLJSSJ = $("#_webgis_jgxlgl_list_edit_dljssj").textbox('getValue');
			        		gra.attributes.JGLX = $("#_webgis_jgxlgl_list_edit_jglx").combobox('getText');
			        		gra.attributes.SSDL = $("#_webgis_jgxlgl_list_edit_ssdl").combobox('getText');
			        		if($("#_webgis_jgxlgl_list_edit_jgxz").combobox('getText') == '--请选择--'){
			        			gra.attributes.JGXZ = "";
			        		}else{
			        			gra.attributes.JGXZ = $("#_webgis_jgxlgl_list_edit_jgxz").combobox('getText');
			        		};
			        		if($("#_webgis_jgxlgl_list_edit_jgzt").combobox('getText') == '--请选择--'){
			        			gra.attributes.JGZT = "";
			        		}else{
			        			gra.attributes.JGZT = $("#_webgis_jgxlgl_list_edit_jgzt").combobox('getText');
			        		};
			        		if($("#_webgis_jgxlgl_list_edit_jgcz").combobox('getText') == '--请选择--'){
			        			gra.attributes.JGCZ = "";
			        		}else{
			        			gra.attributes.JGCZ = $("#_webgis_jgxlgl_list_edit_jgcz").combobox('getText');
			        		};
			        		if($("#_webgis_jgxlgl_list_edit_sfzw").combobox('getText') == '--请选择--'){
			        			gra.attributes.SFZW = "";
			        		}else{
			        			gra.attributes.SFZW = $("#_webgis_jgxlgl_list_edit_sfzw").combobox('getText');
			        		}
			        		if($("#_webgis_jgxlgl_list_edit_jgsl").textbox('getText') == ''){
			        			gra.attributes.JGSL = null;
			        		}else{
			        			gra.attributes.JGSL = $("#_webgis_jgxlgl_list_edit_jgsl").textbox('getText');
			        		}
			        		var graphic = new esri.Graphic(null,null,gra.attributes);
							operationsPolygonLayer.applyEdits(null,[graphic],null,function(){
								global.editHightLayer.remove(gra);
							},function(e){
								$.messager.alert('警告','落图失败！'); 
							});
							$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
							$(this).form('reset');
							$('#_webgis_jgxlgl_list_edit_window').window('close');
						}else{
							$.messager.alert('警告','编辑失败！'); 
						}
						$.messager.progress('close');  	 
			   	    } 
				});  
        	});
        	
        	$("#_webgis_jgxlgl_list_add_save_button").bind('click',function(){
        		debugger;
        		$.messager.progress({ // 显示进度条  
        			title:"正在处理",  
        			text:"正在处理...",  
        			interval:100  
        		}); 
				$('#_webgis_jgxlgl_list_add_form').form('submit', {   
					onSubmit: function(){
						return $(this).form('validate');
					},   
					success:function(result){   
						result = eval('(' + result + ')');
						debugger;
						var nbPathName = result.nbPathName;
						var jjPathName = result.jjPathName;
						var yjPathName = result.yjPathName;
						var jgbh = result.jgbh;
						var jngj,jgzt,sfzw,jgcz,jggg,jgxz;
						if($("#_webgis_jgxlgl_list_edit_jgzt").combobox('getText') == '--请选择--'){
							jgzt = "";
						}else{
							jgzt = $("#_webgis_jgxlgl_list_add_jgzt").combobox('getText');
						};
						if($("#_webgis_jgxlgl_list_edit_sfzw").combobox('getText') == '--请选择--'){
							sfzw = "";
						}else{
							sfzw = $("#_webgis_jgxlgl_list_add_sfzw").combobox('getText');
						};
						if($("#_webgis_jgxlgl_list_edit_jgcz").combobox('getText') == '--请选择--'){
							jgcz = "";
						}else{
							jgcz = $("#_webgis_jgxlgl_list_add_jgcz").combobox('getText');
						};
						if($("#_webgis_jgxlgl_list_edit_jgsl").textbox('getText') == ''){
							jgsl = null;
						}else{
							jgsl = $("#_webgis_jgxlgl_list_add_jgsl").combobox('getText');
						};
						if($("#_webgis_jgxlgl_list_edit_jgxz").combobox('getText') == '--请选择--'){
							jgxz = "";
						}else{
							jgxz = $("#_webgis_jgxlgl_list_add_jgxz").combobox('getText');
						}
						if(yjPathName == "" || yjPathName == undefined || yjPathName == null){
							yjPathName = "";
						}
						if(jjPathName == "" || jjPathName == undefined || jjPathName == null){
							jjPathName = "";
						}
						if(nbPathName == "" || nbPathName == undefined || nbPathName == null){
							nbPathName = "";
						}
						if(result.sec && result.rows > 0){
							var att = {
			        				"X":addgraphic.geometry.x,
			        				"Y":addgraphic.geometry.y,
			        				"JGBM":jgbh,
			        				"YJPIC":yjPathName,
			        				"JJPIC":jjPathName,
			        				"NBPIC":nbPathName,
			        				"JGCZ":jgcz,
			        				"SFZW":sfzw,
			        				"JGXZ":jgxz,
			        				"JGZT":jgzt,
			        				"JGGG":$("#_webgis_jgxlgl_list_add_jggg").textbox('getValue'),
			        				"JNGJ":$("#_webgis_jgxlgl_list_add_jngj").textbox('getValue'),
			        				"JGSL":jgsl,
			        				"JS":$("#_webgis_jgxlgl_list_add_js").textbox('getValue'),
			        				"FHDM":BaseType.initJGLX($("#_webgis_jgxlgl_list_add_jglx").combobox('getText')),
			        				"JGLX":$("#_webgis_jgxlgl_list_add_jglx").combobox('getText'),
			        				"SSDL":$("#_webgis_jgxlgl_list_add_ssdl").combobox('getText'),
			        				"QSDW":$("#_webgis_jgxlgl_list_add_qsdw").textbox('getValue'),
			        				"GLDW":$("#_webgis_jgxlgl_list_add_gldw").textbox('getValue')
			        		}
			        		var graphic = addgraphic;
			        		graphic.attributes = att;
			        		graphic.symbol = null;
							operationsPolygonLayer.applyEdits([graphic],null,null,function(){},function(e){
								$.messager.alert("提示","操作失败");
							 });
							$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
							$(this).form('reset');
							$('#_webgis_jgxlgl_list_add_xzb').val("");
							$('#_webgis_jgxlgl_list_add_yzb').val("");
							$('#_webgis_jgxlgl_list_add_form').form('reset');
							$('#_webgis_jgxlgl_list_add_window').window('close');
							global.editHightLayer.remove(addgraphic);
							addnbPathName = "";
							addyjPathName = "";
							addjjPathName = "";
						}else{
							$.messager.alert('警告','保存失败！'); 
						}
						$.messager.progress('close');  	 
			   	    } 
				});  
        	});
        	
        	$('#_webgis_jgxlgl_list_edit_delete_button').bind('click',function(){
        		$.messager.progress({ // 显示进度条  
        			title:"正在处理",  
        			text:"正在处理...",  
        			interval:100  
        		}); 
    			var jgbh = gra.attributes.JGBM;
				var url = "../YjgJgxxAction/deleteBatch?idArray=" + jgbh;	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						operationsPolygonLayer.applyEdits(null,null,[gra],function(){
							global.mapClickHightLayer.clear();
							$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						});
						$('#_webgis_jgxlgl_list_edit_window').window('close');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
				$.messager.progress('close');  
        	});
        	
        	$('#_webgis_jgxlgl_list_add_cancel_button').bind('click',function(){
        		global.editHightLayer.clear();
//        		global.editHightLayer.remove(addgraphic);
        		$('#_webgis_jgxlgl_list_add_window').window('close');
        	});
        	
        }
        
        function initBtnEvent(){
        	 $("#_base_layer_edit_save").bind('click',function(){
             	debugger;
             	var rows = $('#edit_detailTable').propertygrid("getData").rows;
             		gra.attributes.BH = rows[1].value;
             		gra.attributes.JGLX = rows[2].value;
             		gra.attributes.JGGG = rows[3].value;
             		gra.attributes.SSDL = rows[4].value;
             		gra.attributes.JGZT = rows[5].value;
             		gra.attributes.JGCZ = rows[6].value;
             		gra.attributes.BZ = rows[7].value;
             		var graphic = new esri.Graphic(null,null,gra.attributes);
             	var json = {"hisjgcz":gra.attributes.JGCZ,"hisjgzt":gra.attributes.JGZT,"hisssdl":gra.attributes.SSDL,
             			"hisjggg":gra.attributes.JGGG,"hisjglx":gra.attributes.JGLX,"hisjgbh":gra.attributes.BH};
             	$.ajax({
     				url:"../YjgHisjgxxAction/add",     
     				data:json,
     				type:"post",
     				dataType:"json",
     				async: false,
     				success: function(result){ 
     					 if(result.rows == 1){
     						$.messager.alert("提示","删除成功");
     						$('#_base_layer_edit_window').window('close');
     					 }else{
     						$.messager.alert("提示","操作失败");
     					 }
     				}	
     			});
             	operationsPolygonLayer.applyEdits(null,[graphic],null);
             	
             })
             
             $("#_base_layer_edit_delete").bind('click',function(){
            	 var json = {"hisjgid":Math.floor(Math.random()*100000000),"hisjgcz":gra.attributes.JGCZ,"hisjgzt":gra.attributes.JGZT,"hisssdl":gra.attributes.SSDL,
              			"hisjggg":gra.attributes.JGGG,"hisjglx":gra.attributes.JGLX,"hisjgbh":gra.attributes.BH};
            	 debugger;
            	 $.ajax({
      				url:"../YjgHisjgxxAction/add",     
      				data:json,
      				type:"post",
      				dataType:"json",
      				async: false,
      				success: function(result){ 
      					 if(result.rows == 1){
      						 operationsPolygonLayer.applyEdits(null,null,[gra],function(){
  								$.messager.alert("提示","删除成功");
  								$('#_base_layer_edit_window').window('close');
      						 },function(e){
      							$.messager.alert("提示","操作失败");
      						 });
      					 }else{
      						$.messager.alert("提示","操作失败");
      					 }
      				}	
      			});
             })
             
             $("#_base_layer_add_save").bind('click',function(){ 
            	debugger;
            	var rows = $('#add_detailTable').propertygrid("getData").rows;
            	var att = {
            			"BH":rows[1].value,
            			"JGLX":rows[2].value,
            			"JGGG":rows[3].value,
            			"SSDL":rows[4].value,
            			"JGZT":rows[5].value,
            			"JGCZ":rows[6].value,
            			"BZ":rows[7].value
            	}
            	addgraphic.attributes = att;
            	 var json = {"hisjgid":Math.floor(Math.random()*100000000),"hisjgcz":addgraphic.attributes.JGCZ,"hisjgzt":addgraphic.attributes.JGZT,"hisssdl":addgraphic.attributes.SSDL,
              			"hisjggg":addgraphic.attributes.JGGG,"hisjglx":addgraphic.attributes.JGLX,"hisjgbh":addgraphic.attributes.BH};
            	 $.ajax({
      				url:"../YjgHisjgxxAction/add",     
      				data:json,
      				type:"post",
      				dataType:"json",
      				async: false,
      				success: function(result){ 
      					 if(result.rows == 1){
      						 operationsPolygonLayer.applyEdits([addgraphic],null,null,function(){
  								$.messager.alert("提示","增加成功");
  								$('#_base_layer_add_window').window('close');
  								global.editHightLayer.remove(addgraphic);
      						 },function(e){
      							$.messager.alert("提示","操作失败");
      						 });
      					 }else{
      						$.messager.alert("提示","操作失败");
      					 }
      				}	
      			});
             })
        }
      });

var FeatureEdit={
	
	bindEvent:function(){
		$('._nb_choose_file').filebox({ 
		    buttonText: '请选择', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_nb_choose_file").textbox("getValue");//获取图片的url路径
	    		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
	    		if(fileType != ""){
	    			fileType = fileType.toLowerCase();
		    		if(fileType!=".jpg" && fileType!=".jpeg" && fileType!=".gif" && fileType!=".png"){//验证文件格式
						$.messager.alert('警告','文件仅支持jpg,gif,png格式！');
		    			$("#_nb_choose_file").textbox('setValue',"");
						return false;
					}
		    		var fileSzie = $(this).next().find('input[id^="filebox_file_id_"]')[0].files[0].size;
		    		if(fileSzie > 20971520){
		    			$.messager.alert('警告','上传文件不可以超过20M！');
		    			$("#_nb_choose_file").textbox('setValue',"");
		    			return false;
		    		}
	    		}
	    	}
		});
		
		$('._jj_choose_file').filebox({ 
		    buttonText: '请选择', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_jj_choose_file").textbox("getValue");//获取图片的url路径
	    		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
	    		if(fileType != ""){
	    			fileType = fileType.toLowerCase();
	    			if(fileType!=".jpg" && fileType!=".jpeg" && fileType!=".gif" && fileType!=".png"){//验证文件格式
						$.messager.alert('警告','文件仅支持jpg,gif,png格式！');
		    			$("#_jj_choose_file").textbox('setValue',"");
						return false;
					}
		    		var fileSzie = $(this).next().find('input[id^="filebox_file_id_"]')[0].files[0].size;
		    		if(fileSzie > 20971520){
		    			$.messager.alert('警告','上传文件不可以超过20M！');
		    			$("#_jj_choose_file").textbox('setValue',"");
		    			return false;
		    		}
	    		}
	    	}
		});
		
		$('._yj_choose_file').filebox({ 
		    buttonText: '请选择', 
		    buttonAlign: 'left', 
	    	onChange:function(){
	    		var imgUrl = $("#_yj_choose_file").textbox("getValue");//获取图片的url路径
	    		var fileType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length);
	    		if(fileType != ""){
	    			fileType = fileType.toLowerCase();
	    			if(fileType!=".jpg" && fileType!=".jpeg" && fileType!=".gif" && fileType!=".png"){//验证文件格式
						$.messager.alert('警告','文件仅支持jpg,gif,png格式！');
		    			$("#_yj_choose_file").textbox('setValue',"");
						return false;
					}
		    		var fileSzie = $(this).next().find('input[id^="filebox_file_id_"]')[0].files[0].size;
		    		if(fileSzie > 20971520){
		    			$.messager.alert('警告','上传文件不可以超过20M！');
		    			$("#_yj_choose_file").textbox('setValue',"");
		    			return false;
		    		}
	    		}
	    	}
		});
		
    	$('#_webgis_jgxlgl_list_add_cancel_button').bind('click',function(){
    		$('#_webgis_jgxlgl_list_add_window').window('close');
    	});
    	
    	$("#_webgis_jgxlgl_list_edit_ssdl").combobox({  

 	       onSelect: function (n,o) {
 	    	 initToken();
 	 		 $.ajax({  
 	 		 	 type:"POST", 
 	 		 	 data:{"code":"SSDL","name":n.name},
 	 	         url: "../TblBaseTypeAction/selectValueByCodeAndName",  
 	 	         success:function(result){ 
 	 	    		debugger;
 	 	         	if(result){
 	 	         		$("#_webgis_jgxlgl_list_edit_dljssj").textbox('setValue',result.attribute);
 	 	        	 }
 	 	         }  
 	 	     });
 	       }  
 		});
    	
    	$("#_webgis_jgxlgl_list_add_ssdl").combobox({  
  	       onSelect: function (n,o) {
  	    	 initToken();
  	 		 $.ajax({  
  	 		 	 type:"POST", 
  	 		 	 data:{"code":"SSDL","name":n.name},
  	 	         url: "../TblBaseTypeAction/selectValueByCodeAndName",  
  	 	         success:function(result){  
  	 	         	if(result){
  	 	         		$("#_webgis_jgxlgl_list_add_dljssj").textbox('setValue',result.attribute);
  	 	        	 }
  	 	         }  
  	 	     });
  	       }  
  		});
		
	},
	
	checkTimeOut:function(){
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
	},
	
	//删除照片
	deleteImg:function(ele,type){
		var jgbh = $("#_webgis_jgxlgl_list_edit_jgbhnb").val();
		$.messager.confirm('确认删除',"您确定要删除选中图片吗?",function(r){  
			if(r){
				$.ajax({  
	                type:"POST",  
	                data:{"type":type,"jgbh":jgbh},  
	                url: "../YjgJgxxAction/deleteImg",  
	                success:function(result){ 
	                	if(result.sec && result.rows > 0){
	                		if(type == 1){
	    						gra.attributes.NBPIC = "";
	    					}else if(type == 2){
	    						gra.attributes.JJPIC = "";
	    					}else if(type == 3){
	    						gra.attributes.YJPIC = "";
	    					}
							$.messager.show({title:'系统消息',msg:'删除图片成功！',showType:'slide'});
							$(ele).parent().parent().remove();
						}else{
							$.messager.alert('警告','删除记录失败！');
						}
	                }  
                });
			}
		});
	},
	
	//上传图片
	insertImg:function(ele,type){
		debugger
		var list = "";
		if(type == 1){
			list = $('.apply_footer_upload_edit_webgis_nb');
		}else if(type == 2){
			list = $('.apply_footer_upload_edit_webgis_jj');
		}else if(type == 3){
			list = $('.apply_footer_upload_edit_webgis_yj');
		}
		if(list.length>0){
			$.messager.alert('警告','图片最多只能一张！');
			return false;
		}
		var form=$(ele).parent().parent().parent();
		var imgUrl = form.find("#_choose_edit_file").val();//获取图片的url路径
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
			url:'../YjgJgxxAction/addImg',
			type: 'POST',
			success:function(result){
				$.messager.progress('close'); 
				if(result.sec){
					var html = "";
					debugger;
					if(type == 1){
						html = $("#_choose_edit_nb_render").render(result);
						var nbPathName = result.nbPathName;
						if(nbPathName != null && nbPathName != undefined){gra.attributes.NBPIC = nbPathName};
					}else if(type == 2){
						html = $("#_choose_edit_jj_render").render(result);
						var jjPathName = result.jjPathName;
						if(jjPathName != null && jjPathName != undefined){gra.attributes.JJPIC = jjPathName};
					}else if(type == 3){
						html = $("#_choose_edit_yj_render").render(result);
						var yjPathName = result.yjPathName;
						if(yjPathName != null && yjPathName != undefined){gra.attributes.YJPIC = yjPathName};
					}
					form.append(html);
					var jgbh = result.jgbh;
				}else{
					$.messager.alert('警告','图片保存出错');
				}
			}
		});
	}
	
}
	
$(function(){
	FeatureEdit.checkTimeOut();
	FeatureEdit.bindEvent();
});
