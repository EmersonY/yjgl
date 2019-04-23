   require(["dojo/_base/lang","esri/layers/FeatureLayer","esri/toolbars/draw","esri/layers/GraphicsLayer","esri/graphic",
      "esri/geometry/Point","esri/tasks/query","esri/tasks/QueryTask","esri/tasks/FindTask",
      "esri/tasks/FindParameters","dojo/topic","dojo/domReady!"],
    function(lang,FeatureLayer,Draw,GraphicsLayer,Graphic,Point,Query,QueryTask,FindTask,FindParameters,topic){
	   
	   var map = null;
	   var basesearch_toolbar = null;measure_drawEnd = null;
	   var basesearch_isStartDraw = false;
	   var basesearch_pointSymbol = null, basesearch_lineSymbol = null, basesearch_fillSymbol = null;
	   var querytask = null;
	   
	   topic.subscribe(global.APP_TOOLBAR_OPEN, lang.hitch(this, baseSearchInit));
	   topic.subscribe(global.APP_TOOLBAR_CLOSE, lang.hitch(this, baseSearch_closeWin));
	   
	   map = mapManager.map;
	   basesearch_toolbar = new Draw(map);
	   var findTask = new FindTask(_INIT_QPNL);
	   //初始化测量样式
	   symbolManager.measureSymbol();
	   symbolManager.querySymbol();
	   basesearch_fillSymbol = symbolManager.baseSearch_fillSymbol;
	   
	   querySearchInit();
	   
	   function querySearchInit(){
		   $('#_baseSearch_clearLayer').bind('click', function() {
			    $('#_baseSearch_addname').val('');
				mapManager.map.graphics.clear();
				global.queryHightLayer.clear();
			});
			
			$('#_baseSearch_go').bind('click',function(){
				debugger;
				var jgbh = $('#_baseSearch_addname').val();
				if(jgbh == null || jgbh == ""){
					$.messager.alert("提示","请先输入井盖编码");
				}else{
					baseSearch_searchAddress(jgbh);
				}
			});
			
			//输入并匹配结果。
	    	  $( "#_baseSearch_addname" ).autocomplete({
	    	      source: function( request, response ) {
	    	    	  queryData = [];
	    	          $.ajax({
	    	              url: _INIT_QPNL + "/find",
	    	              dataType: "jsonp",
	    	              type:"post",
	    	              data: {
	    	            	 f: "json", 
	    	            	 returnGeometry: false ,
	    	            	 outFields : "*",
		            		 searchText : request.term,
		            		 contains : true,
		            		 layers : "1",
		            		 searchFields : "JGBM,SSDL,JGLX"
	    	              },
	    	              success: function( data ) {
	    	            	  debugger;
	    	            	 if(data.results.length > 50){
  	     	            		data.results = data.results.slice(0,50);
  	     	            	 }
	    	            	 queryData = queryData.concat(data.results);
	    	            	 $.ajax({
	    	     	              url: "http://mapapi.xmtfj.gov.cn:8399/arcgis/rest/services/DMPOIDZ/MapServer/1/query",
	    	     	              dataType: "jsonp",
	    	     	              type:"post",
	    	     	              async:false,
	    	     	              data: {
	    	     	            	 f: "json", 
	    	     	            	 where: "NAME like '%" + request.term + "%'", 
	    	     	            	 returnGeometry: false,
	    	     	            	 outFields : "*"
	    	     	              },
	    	     	              success: function( data ) {
	    	     	            	 if(data.features.length > 50){
	    	     	            		data.features = data.features.slice(0,50);
	    	     	            	 }
	    	     	            	 queryData = queryData.concat(data.features);
	    	     	            	 response( $.map( queryData , function( item ) {
	    	     	            		 if (item.value) {
	    	     	            			 return {
	    	     	            				 label: item.attributes.JGBM,
	    	     	            				 value: item.layerId + "," + item.attributes.OBJECTID
	    	     	            			 }
										}else{
											return {
												label: item.attributes.NAME,
												value: item.attributes.OBJECTID+""
											}
										}
	    	    	                 }));
	    	     	              }
	    	     	            });
	    	              }
	    	            });
	    	          },
	    	      appendTo: "#baseSearch_result",
	    	      scrollHeight : 500,
	    	      delay:500,
	    	      focus: function( event, ui ) {
	    	        return false;
	    	      },
	    	      select: function( event, ui ) {
	    	    	  debugger
	    	        $( "#_baseSearch_addname" ).val( ui.item.label );
	    	        console.log(ui.item.value);
	    	        global.queryHightLayer.clear();
	    	        var selValue = ui.item.value.split(',');
	    	        if (selValue.length == 1) {
	    	        	$.ajax({
		     	              url: "http://mapapi.xmtfj.gov.cn:8399/arcgis/rest/services/DMPOIDZ/MapServer/1/query",
		     	              dataType: "jsonp",
		     	              type:"post",
		     	              async:false,
		     	              data: {
		     	            	 f: "json", 
		     	            	 objectIds: selValue[0], 
		     	            	 returnGeometry: true,
		     	            	 outFields : "*"
		     	              },
		     	              success: function( data ) {
		     	            	  data.features[0].geometry.spatialReference = {"wkid":4490};
		     	            	  var point = new Point(data.features[0].geometry);
		     	            	  var graphic = new esri.Graphic(point,symbolManager.rangeSearch_picSymbol);
		     	            	  global.queryHightLayer.add(graphic);
		     	            	  mapManager.centerAndZoom(point);
		     	              }
		     	            });
					}else{
						$.ajax({
							url: _INIT_QPNL + "/" + selValue[0]+"/query",
							dataType: "jsonp",
							type:"post",
							async:false,
							data: {
								f: "json", 
								objectIds: selValue[1], 
								returnGeometry: true ,
								outFields : "*"
							},
							success: function( data ) {
								data.features[0].geometry.spatialReference = {"wkid":4490};
		     	            	var point = new Point(data.features[0].geometry);
								var graphic = new esri.Graphic(point,symbolManager.rangeSearch_picSymbol);
								global.queryHightLayer.add(graphic);
								mapManager.centerAndZoom(point);
							}
						});
					}
	    	        return false;
	    	      }
	        });
	   }
	   
	   function baseSearchInit(){
		   
		   $('#basesearch_clear').bind('click',function(){
			   debugger;
			   global.mapHightLayer.clear();
			   global.queryHightLayer.clear();
			   topic.publish(global.APP_FEATURESEARCH_OPEN);
		   });
		   
		   $('#basesearch_point').bind('click', function() {
//				map.setMapCursor("url('../images/cursor/cursor.png'),auto");
			   basesearch_startDrawCircle();
		   });
			
		   $('#basesearch_rectangle').bind('click', function() {
			   debugger;
	//				map.setMapCursor("url('../images/cursor/cursor.png'),auto");
			   basesearch_startDrawRectangle();
		   });
			
		   $('#basesearch_polygon').bind('click', function() {
//				map.setMapCursor("url('../images/cursor/cursor.png'),auto");
			   basesearch_startDrawPolygon();
		   });
			
			

	   }
	   
	 //点击查询按钮进行查询
      function baseSearch_searchAddress(name) {
    	  findParams = new FindParameters();
    	  if(global.mapHightLayer.graphics.length != 0){
    		  findParams.geometry = global.mapHightLayer.graphics[0].geometry;
  		  }
          findParams.returnGeometry = true;
          findParams.layerIds = [1];
          findParams.searchFields = ["JGBM","SSDL","JGLX"];
          findParams.searchText = name;
    	  findTask.execute(findParams, baseSearch_showResults_click);
      }
	   
	   //结果的展示。
      function baseSearch_showResults_click(results) {
    	  if(results.length > 0){
    		  console.log(results);
    		  global.queryHightLayer.clear();
    		  $.each(results,function(i,row){
    			  if(i < 999){
    				  var graphic = row.feature;
            		  graphic.setSymbol(symbolManager.rangeSearch_picSymbol); 
        			  global.queryHightLayer.add(graphic);
    			  }
    		  });
    		  debugger;
    		  var currentScale = map.getScale();
    		  console.log('Scale: ' + currentScale);
    		  mapManager.centerAndZoom(results[0].feature.geometry);
    	  }else{
    		  $.messager.alert("提示","没有查询到数据！请重新输入");
    	  }
      };
	   
	   /**
		 * 开始绘制点
		 */
		function basesearch_startDrawCircle() {
			debugger;
			basesearch_clearOtherEvent();
			basesearch_initLayer();//初始化图层
			map.hideZoomSlider();
			map.graphics.clear();
			basesearch_toolbar.activate(Draw.CIRCLE);
			basesearch_toolbar.fillSymbol = basesearch_fillSymbol;
			topic.publish(global.APP_FEATURESEARCH_CLOSE);
		}
		/**
		 * 开始线测量
		 */
		function basesearch_startDrawRectangle() {
			basesearch_clearOtherEvent();
			basesearch_initLayer();//初始化图层
			map.hideZoomSlider();
			map.graphics.clear();
			basesearch_toolbar.activate(Draw.RECTANGLE);
			basesearch_toolbar.fillSymbol = basesearch_fillSymbol;
			topic.publish(global.APP_FEATURESEARCH_CLOSE);
			// 地图的点击事件
//			measure_mapClick = dojo.connect(map, "onClick", lang.hitch(this, measure_mapClickHandler));
		}

		/**
		 * 开始面测量
		 */
		function basesearch_startDrawPolygon() {
			basesearch_clearOtherEvent();
			basesearch_initLayer();//初始化图层
			map.hideZoomSlider();
			map.graphics.clear();
			basesearch_toolbar.activate(Draw.POLYGON);
			basesearch_toolbar.fillSymbol = basesearch_fillSymbol;
			topic.publish(global.APP_FEATURESEARCH_CLOSE);
		}
		
		/**
		 * 初始化图层
		 */
		function basesearch_initLayer() {
//			global.mapInitGraphicLayer("",map);
			// 添加toolbar画图完成后事件监听调用doMeasure
			basesearch_drawEnd = dojo.connect(basesearch_toolbar, "onDrawEnd",basesearch_doSearch);
		}
		
		/**
		 * 绘制完成后，执行查询
		 */
		function basesearch_doSearch(geometry) {
			debugger;
			basesearch_toolbar.deactivate();
			global.queryHightLayer.clear();
			global.mapHightLayer.clear();
			if (basesearch_drawEnd != null) {
				basesearch_drawEnd.remove();
			}
			switch (geometry.type) {
			case "point":
				var graphics = new Graphic(geometry,symbolManager.baseSearch_pointSymbol);
				global.mapHightLayer.add(graphics);
				break;
			case "polygon":
				var graphics = new Graphic(geometry,symbolManager.baseSearch_fillSymbol);
				global.mapHightLayer.add(graphics);
				break;
			}
			topic.publish(global.APP_RANGESEARCH_OPEN);
			topic.publish(global.APP_FEATURESEARCH_OPEN);
		}
		
		/**
		 * 避免多次点击的事件冲突。
		 */
		function basesearch_clearOtherEvent() {
			if (global.isStartClickSearch) {
				topic.publish(global.APP_CLICKSEARCH_CLOSE);
				measure_isCloseClickEvent = true;
			}
			if (basesearch_isStartDraw) {
				basesearch_toolbar.deactivate();
				measure_drawEnd.remove();
			}
			measure_isStartDraw = true;
		}
		
		
		/**
		 * 关闭窗口前事件
		 */
		function baseSearch_closeWin(){
			measure_resolveConflict();
			//移除点击事件的绑定。
			unbindClickEvent();
			
			//清除全部测量图层
//			measure_clearLayer();
			
			$("#measure_tag_toolbar").css("display","none");
		}
		
		/**
		 * 解除事件，避免和其他事件的冲突问题。
		 */
		function measure_resolveConflict() {
			if (basesearch_toolbar != null)
				basesearch_toolbar.deactivate();
			// 移除绘制结束事件
			if (measure_drawEnd != undefined || measure_drawEnd != null) 
				measure_drawEnd.remove();
			// 移除地图点击事件
			/*if (measure_current == "line") 
				measure_mapClick.remove();*/
		}
		
		/**
		 * 移除点击事件的绑定。
		 */
		function unbindClickEvent(){
			$('#basesearch_point').unbind('click');
			$('#basesearch_rectangle').unbind('click');
			$('#measure_clear').unbind('click');
			$('#basesearch_polygon').unbind('click');
		}
   });