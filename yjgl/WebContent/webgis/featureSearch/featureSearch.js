require(["esri/tasks/IdentifyParameters","esri/geometry/Circle","esri/tasks/IdentifyTask","esri/graphic","esri/layers/GraphicsLayer","dojo/_base/lang","dojo/topic","dojo/domReady!" ],
	function(IdentifyParameters,Circle,IdentifyTask,Graphic,GraphicsLayer,lang,topic) {
	
	var featureSearch_mapClickHandler;
	var featureSearch_graphicsLayer;
	var featureSearch_clickpoint;
	var featureSearch_graphics;
	var featureSearch_startClickFlag=false;
	var map = mapManager.map;
	var IdentifyTaskfea;
	var IdentifyTasknum = 0;

	var FeaLyr = new esri.layers.FeatureLayer( _INIT_QPNL+"/0" , {
		id : _QPNL_ID,
		outFields : ["*"]
	});
	
	var featureSearch_spfClickHandler = null;
//	featureSearch_open();
	featureSearch_init();
	identifyTask_bindEvent();

	topic.subscribe(global.APP_FEATURESEARCH_OPEN, lang.hitch(this, featureSearch_init));
	topic.subscribe(global.APP_FEATURESEARCH_CLOSE, lang.hitch(this, featureSearch_close));

	
	function featureSearch_open() {
		debugger;
//		map.addLayer(FeaLyr);
		map.reorderLayer(global.queryHightLayer, map.getLayersVisibleAtScale().length - 1);
		featureSearch_spfClickHandler = dojo.connect(FeaLyr,"onClick",lang.hitch(this,featureSearch_ClickEvent));
	}
	
	function featureSearch_ClickEvent(evt){
		//阻止冒泡事件。
		evt.cancelBubble = true;
//		if(featureSearch_graphics){
//			global.queryHightLayer.remove(featureSearch_graphics);
//		}
		global.mapHightLayer.clear();
		var graphic = evt.graphic;
		debugger;
		switch (graphic.geometry.type){
			case "point":
				mapManager.centerAndZoom(new esri.geometry.Point(graphic.geometry.x,graphic.geometry.y,new esri.SpatialReference(4490)));
				featureSearch_graphics = new Graphic(graphic.geometry,symbolManager.baseSearch_pointSymbol);
				break;
			case "polyline":
				map.setExtent(graphic.geometry.getExtent().expand(2));
//				map.centerAt(new esri.geometry.Point(graphic.geometry.x,graphic.geometry.y,new esri.SpatialReference(4490)));
				featureSearch_graphics = new Graphic(graphic.geometry,symbolManager.baseSearch_lineSymbol);
			case "polygon":
				map.setExtent(graphic.geometry.getExtent().expand(2));
//				map.centerAt(new esri.geometry.Point(graphic.geometry.x,graphic.geometry.y,new esri.SpatialReference(4490)));
				featureSearch_graphics = new Graphic(graphic.geometry,symbolManager.baseSearch_fillSymbol);
		}
        $('#_base_layer_view_window').window({
		    top:evt.clientY,
			width:400,   
			height:600,
		    left:evt.clientX,	
		    shadow:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false,
		    collapsible:false,
		    title:"详情",
		    onBeforeClose:function(){
		    }
		});
		$('#_base_layer_view_window').attr("style","display:block");
		global.mapHightLayer.add(featureSearch_graphics);
		$('#search_detailTable').propertygrid({   
			 columns:[[
		            { title: "名称", field: "name",width:120 },
		            { title: "值", field: "value",width:180 }
			        ]],
		     data:[  
				    {"name":"编号：","value":graphic.attributes.BH,"editor":"text"},   
				    {"name":"井盖类型：","value":graphic.attributes.JGLX,"editor":"text"},
				    {"name":"井盖规格：","value":graphic.attributes.JGGG,"editor":"text"},
				    {"name":"所属道路：","value":graphic.attributes.SSDL,"editor":"text"},
				    {"name":"井盖状态：","value":graphic.attributes.JGZT,"editor":"text"},
				    {"name":"井内管径：","value":graphic.attributes.JNGJ,"editor":"text"},
				    {"name":"是否开盖：","value":graphic.attributes.SFKG,"editor":"text"},
				    {"name":"井盖形状：","value":graphic.attributes.JGXZ,"editor":"text"},
				    {"name":"井盖材质：","value":graphic.attributes.JGCZ,"editor":"text"},
				], 
		     showGroup:true
		  }); 
	}
	
	function featureSearch_close() {
		featureSearch_mapClickHandler.remove();
		global.mapClickHightLayer.clear();
	}

	function featureSearch_init() {
		debugger;
		featureSearch_mapClickHandler = dojo.connect(map,"onClick",lang.hitch(this,featureSearch_mapClickEvent));
		
	}
	
	function identifyTask_bindEvent(){
		$('#_image_zoom_container').bind('click',function(evt){
			debugger;
			var div = $('#_image_zoom_center')[0];
			var x=evt.clientX;  
            var y=evt.clientY;  
            var divx1 = div.offsetLeft;  
            var divy1 = div.offsetTop;  
            var divx2 = div.offsetLeft + div.offsetWidth;  
            var divy2 = div.offsetTop + div.offsetHeight;   
            if( x < divx1 || x > divx2 || y < divy1 || y > divy2){  
            	$('#_image_zoom_container').hide();
               }  
		});
		$('#_base_layer_view_nbdiv').bind('click',function(){
			debugger
			if(isHasImg($('#_base_layer_view_nb').attr('src'))){
				var top =  $(window).height()/2 - 400;
				var right = $(window).width()/2 - 400;
				$("#_image_zoom_center").css("top",top+"px").css("right",right+"px");
				$("#_image_zoom_center").attr("src",$('#_base_layer_view_nb').attr('src'));	
				$("#_image_zoom_container").css({"width":$(window).width() + "px","height":$($(window)).height()+"px","display":"block"});
				$("#_image_zoom_close").css("top",(top-20)+"px").css("right",(right-20)+"px");
			}
		});
		
		$('#_base_layer_view_jjdiv').bind('click',function(){
			debugger
			if(isHasImg($('#_base_layer_view_jj').attr('src'))){
				var top = $(window).height()/2 - 400 ;
				var right = $(window).width()/2 - 400;
				$("#_image_zoom_center").css("top",top+"px").css("right",right+"px");
				$("#_image_zoom_center").attr("src",$('#_base_layer_view_jj').attr('src'));
				$("#_image_zoom_container").css({"width":$(window).width() + "px","height":$($(window)).height()+"px","display":"block"});
				$("#_image_zoom_close").css("top",(top-20)+"px").css("right",(right-20)+"px");
			}
		});
		
		$('#_base_layer_view_yjdiv').bind('click',function(){
			debugger
			if(isHasImg($('#_base_layer_view_yj').attr('src'))){
				var top = $(window).height()/2 - 400 ;
				var right = $(window).width()/2 - 400;
				$("#_image_zoom_center").css("top",top+"px").css("right",right+"px");
				$("#_image_zoom_center").attr("src",$('#_base_layer_view_yj').attr('src'));
				$("#_image_zoom_container").css({"width":$(window).width() + "px","height":$($(window)).height()+"px","display":"block"});
				$("#_image_zoom_close").css("top",(top-20)+"px").css("right",(right-20)+"px");
			}
		});
		
		$("#_image_zoom_close").bind("click",function(){
			$("#_image_zoom_container").hide();
		});
		
		$('#_base_layer_view_next').bind('click',function(){
			if(IdentifyTaskfea != null){
				if(IdentifyTasknum + 1 != IdentifyTaskfea.length){
					if(IdentifyTasknum == 0){
						$('#_base_layer_view_last').linkbutton('enable');
					}
					IdentifyTasknum++;
					identifyTask_showwindow(IdentifyTasknum);
					global.mapClickHightLayer.clear();
		        	var graphic = new Graphic(IdentifyTaskfea[IdentifyTasknum].feature.geometry,symbolManager.baseSearch_pointSymbol);
		        	global.mapClickHightLayer.add(graphic);
					$('#_base_layer_view_window').window('setTitle',"详情(" + IdentifyTaskfea.length + "-" + (IdentifyTasknum+1) + ")");
					if(IdentifyTasknum + 1 == IdentifyTaskfea.length){
						$('#_base_layer_view_next').linkbutton('disable');
					} 
				}
			}
		});
		
		$('#_base_layer_view_last').bind('click',function(){
			if(IdentifyTaskfea != null){
				if(IdentifyTasknum + 1 == IdentifyTaskfea.length){
					$('#_base_layer_view_next').linkbutton('enable');
				}
				IdentifyTasknum--;
				identifyTask_showwindow(IdentifyTasknum);
				global.mapClickHightLayer.clear();
	        	var graphic = new Graphic(IdentifyTaskfea[IdentifyTasknum].feature.geometry,symbolManager.baseSearch_pointSymbol);
	        	global.mapClickHightLayer.add(graphic);
				$('#_base_layer_view_window').window('setTitle',"详情(" + IdentifyTaskfea.length + "-" + (IdentifyTasknum+1) + ")");
				if(IdentifyTasknum == 0){
					$('#_base_layer_view_last').linkbutton('disable');
				}
			}
		});
	}
	
	function featureSearch_close() {
		if (featureSearch_mapClickHandler != null) {
			featureSearch_mapClickHandler.remove();
		}
	}
	
	//地图点击事件，执行查询。
	function featureSearch_mapClickEvent(evt){
		if(global.isEditStatus){
			return;
		}else{
			debugger;
			identifyTask_params = new IdentifyParameters();
		    identifyTask_params.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
		    identifyTask_params.returnGeometry = true;
		    identifyTask_params.geometry = evt.mapPoint;
		    identifyTask_params.tolerance = 1;
		    identifyTask_params.layerIds = [1];
			
			var identifyTask = new IdentifyTask( _INIT_QPNL );
	    	identifyTask_params.mapExtent = map.extent;
			identifyTask.execute(identifyTask_params, identifyTask_showResults);
		}	
	}
	
	//结果展示。
	function identifyTask_showResults(results){
		console.log("results:",results);
        if(results.length > 0){
			IdentifyTasknum = 0;
        	IdentifyTaskfea = results;
        	global.mapClickHightLayer.clear();
        	var graphic = new Graphic(results[0].feature.geometry,symbolManager.baseSearch_pointSymbol);
        	global.mapClickHightLayer.add(graphic);
        	$('#_base_layer_view_window').window({
    		    top:300,
    			width:400,   
    			height:600,
    		    left:500,	
    		    shadow:false,
    		    minimizable:false,
    		    maximizable:false,
    		    resizable:false,
    		    collapsible:false,
    		    title:"详情(" + results.length + "-" + (IdentifyTasknum+1) + ")",
    		    onBeforeClose:function(){
    		    }
    		});
        	if(results.length == 1){
        		$('#_base_layer_view_next').linkbutton('disable');
        		$('#_base_layer_view_last').linkbutton('disable');
        	}else{
        		$('#_base_layer_view_next').linkbutton('enable');
        		$('#_base_layer_view_last').linkbutton('disable');
        	}
    		$('#_base_layer_view_window').attr("style","display:block");
        	identifyTask_showwindow(IdentifyTasknum);
        }else{
        	global.mapClickHightLayer.clear();
        }
   }
	
	function identifyTask_showwindow(num){
		var graphic = IdentifyTaskfea[num].feature;
		$('#search_detailTable').propertygrid({   
			 columns:[[
		            { title: "名称", field: "name",width:120 },
		            { title: "值", field: "value",width:180,formatter: 
				    	function(value,row,index){
		            	debugger
				    	return value == "Null" ? "" : value;
				    }}
			        ]],
		     data:[  
				    {"name":"井盖编码：","value":graphic.attributes.JGBM},   
				    {"name":"井盖类型：","value":graphic.attributes.JGLX},
				    {"name":"井盖规格：","value":graphic.attributes.JGGG},
				    {"name":"所属道路：","value":graphic.attributes.SSDL},
				    {"name":"井盖状态：","value":graphic.attributes.JGZT},
				    {"name":"井内管径：","value":graphic.attributes.JNGJ},
				    {"name":"井盖形状：","value":graphic.attributes.JGXZ},
				    {"name":"井盖材质：","value":graphic.attributes.JGCZ},
				], 
		     showGroup:true
		  }); 
		debugger;
		$('#_base_layer_view_nb').attr("src","../files/sjwh/" + graphic.attributes.NBPIC.replace("JPG","jpg") +"");
		$('#_base_layer_view_jj').attr("src","../files/sjwh/" + graphic.attributes.JJPIC.replace("JPG","jpg") +"");
		$('#_base_layer_view_yj').attr("src","../files/sjwh/" + graphic.attributes.YJPIC.replace("JPG","jpg") +"");
	}
	
	function isHasImg(pathImg){
		debugger;
	    var ImgObj=new Image();
	    ImgObj.src= pathImg;
	    if(ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0))
	    {
	       return true;
	    } else {
	       return false;
	   }
	}
	
	function processResultData(results) {
		var data = [];
		if (results.length == 1) {
			return results;
		}else{
			data[0] = results[0];
		}
		for (var i = 1; i < results.length; i++) {
			for (var j = 0; j < data.length; j++) {
				if (data[j].layerId == results[i].layerId) {  //如果layerId相同，则表示大地块包含小地块的问题。
					
				}
			}
		}
	}
	
});
