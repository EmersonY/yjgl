define.amd.jQuery = true;
var mapManager,symbolManager,global,mapGraLayer;
var xzb,yzb,gra;
var map,isDrag;
var type = getParameter("type");
debugger;
var x = getParameter("x");
var y = getParameter("y");
var winID = getParameter("winid");
var sjlx = getParameter("sjlx");
var IdentifyTaskfea;
var IdentifyTasknum = 0;
var featureSearch_mapClickHandler;
var findTask;
require(["mapManager","SymbolManager","esri/tasks/IdentifyParameters","esri/tasks/IdentifyTask","esri/layers/FeatureLayer","global","dojo/topic","dojo/on","esri/toolbars/edit","esri/layers/GraphicsLayer","esri/geometry/Point","esri/graphic","esri/SpatialReference",
         "dojo/_base/lang","esri/dijit/LayerSwipe","esri/map","esri/tasks/FindTask","esri/tasks/FindParameters","esri/geometry/Extent","dojo/domReady!" ],
	function( MapManager, SymbolManager,IdentifyParameters,IdentifyTask,FeatureLayer, Global,topic,on,Edit,GraphicsLayer,Point,Graphic,SpatialReference,lang,LayerSwipe,Map,FindTask,FindParameters,Extent) {
	
	
	init();
	
	function init() {
		global = new Global();
		
		mapManager = new MapManager("mapDiv",false,false);
		map = mapManager.map;
		symbolManager = new SymbolManager();
	    mapGraLayer = new GraphicsLayer();
	    mapSearchLayer = new GraphicsLayer();
		//初始化样式
		symbolManager.mapSymbol();
		symbolManager.querySymbol();
		symbolManager.rangeSymbol();
		mapManager.removeAllLayer();
		mapGraLayer.clear();
		findTask = new FindTask(_INIT_QPNL);
		mapManager.addLayerToMap("tiled",_INIT_DZDT,_DZDT_ID,null,0);
		mapManager.addLayerToMap("tiled",_INIT_DL,_DL_ID,null,1);
		mapManager.addLayerToMap("tiled",_DZDT_ZJ,_DZDT_ZJID,null,2);
		mapManager.map.addLayer(mapSearchLayer);
		mapManager.map.addLayer(mapGraLayer);
		querySearchInit();
		//初始化图层
		if(sjlx == 0){
			initMap();
		}else{
			inityjMap();
		}
		if(type == 3){
			$('#edit_buttonpanel').attr("style","display:none");
		}else{
			$('#edit_buttonpanel').attr("style","width:100%;height:10%;text-align: right;display:block");
		}
	}

	//初始化数据库中默认加载的地图
	function initMap()
	{
		var point;
		if(type == 1){
			if(x == "null" || x == "" || x == undefined){
				point = new Point(117.99136993200011,24.57250794500004,new SpatialReference({ wkid: 4490 }));
			}else{
				point = new Point(x,y,new SpatialReference({ wkid: 4490 }));
			}	
		}else if(type == 2 && x == null ){
			point = new Point(117.99136993200011,24.57250794500004,new SpatialReference({ wkid: 4490 }));
		}else{
			point = new Point(x,y,new SpatialReference({ wkid: 4490 }));
		}
//		var point = new Point(117.99136993200011,24.57250794500004,new SpatialReference({ wkid: 4490 }));
		var graphic = new Graphic(point, symbolManager.mapPointSymbol);
		mapGraLayer.clear(); 
		mapGraLayer.add(graphic);
	    mapManager.map.addLayer(mapGraLayer);
	    map.on('load',function(){
	    	mapManager.centerAndZoom(point);
	    })
	    if(type == 1 || type == 2){
	    	mapGraLayer.on("mouse-down",function(evt){
	    		map.disablePan();
		    	isDrag = true;	
		    });
	    	mapGraLayer.on("mouse-drag",function(evt){
		    	evt.graphic.setGeometry(evt.mapPoint);	
		    });
		    mapGraLayer.on("mouse-up",function(evt){
		    	xzb = evt.mapPoint.x;
		    	yzb = evt.mapPoint.y;
		    })
		    map.on("mouse-down",function(evt){
		    	graphic.setGeometry(evt.mapPoint);
		    	xzb = evt.mapPoint.x;
		    	yzb = evt.mapPoint.y;
		    })
		    map.on("mouse-up",function(evt){
		    	if(isDrag){
		    		isDrag = false;
	                map.enablePan();
	                console.info(evt.mapPoint);
		    	}
		    })
	    }
	    
	}
	
	function inityjMap(){
		gra = null;
		mapManager.addLayerToMap("dynamic",_INIT_QPNL,_QPNL_ID,null,3);
		var point;
		if(x != "null" && x != undefined && x != ""){
			point = new Point(x,y,new SpatialReference({ wkid: 4490 }));
			var graphic = new Graphic(point, symbolManager.mapPointSymbol);
		    mapGraLayer.add(graphic);
		    map.on('load',function(){
		    	mapManager.centerAndZoom(point);
		    })
		}
    	identifyTask_bindEvent();
    	featureSearch_mapClickHandler = dojo.connect(map,"onClick",lang.hitch(this,featureSearch_mapClickEvent));
    	if(type == 3){
    		$('#_base_layer_view_sure').linkbutton('disable');
    	}else{
    		$('#_base_layer_view_sure').linkbutton('enable');
    	}
	    };
	
	function identifyTask_bindEvent(){
		$('#_base_layer_view_next').bind('click',function(){
			if(IdentifyTaskfea != null){
				if(IdentifyTasknum + 1 != IdentifyTaskfea.length){
					if(IdentifyTasknum == 0){
						$('#_base_layer_view_last').linkbutton('enable');
					}
					IdentifyTasknum++;
					identifyTask_showwindow(IdentifyTasknum);
					$('#_base_layer_view_window').window('setTitle',"详情(" + IdentifyTaskfea.length + "-" + (IdentifyTasknum+1) + ")");
					if(IdentifyTasknum + 1 == IdentifyTaskfea.length){
						$('#_base_layer_view_next').linkbutton('disable');
					} 
				}
			}
		});
		
		$('#_base_layer_view_last').bind('click',function(){
			if(IdentifyTaskfea != null){
				if(IdentifyTasknum > 0){
					if(IdentifyTasknum + 1 == IdentifyTaskfea.length){
						$('#_base_layer_view_next').linkbutton('enable');
					}
					IdentifyTasknum--;
					identifyTask_showwindow(IdentifyTasknum);
					$('#_base_layer_view_window').window('setTitle',"详情(" + IdentifyTaskfea.length + "-" + (IdentifyTasknum+1) + ")");
					if(IdentifyTasknum == 0){
						$('#_base_layer_view_last').linkbutton('disable');
					}
				}
			}
		});
		
		$('#_base_layer_view_sure').bind('click',function(){
			if(IdentifyTaskfea != null){
				gra = IdentifyTaskfea[IdentifyTasknum].feature;
				var graphic = new Graphic(gra.geometry, symbolManager.mapPointSymbol);
				mapGraLayer.clear();
			    mapGraLayer.add(graphic);
			    mapSearchLayer.clear();
//			    mapSearchLayer.remove(new Graphic(gra.geometry, symbolManager.rangeSearch_picSymbol,gra.attributes));
			    $('#_base_layer_view_window').window('close');
			}
		});
		
		$('#_base_layer_view_nbdiv').bind('click',function(){
			debugger
			if(isHasImg($('#_base_layer_view_nb').attr('src'))){
				var top =  $(window).height()/2 - 300;
				var right = $(window).width()/2 - 300;
				$("#_image_zoom_center").css("top",top+"px").css("right",right+"px");
				$("#_image_zoom_center").attr("src",$('#_base_layer_view_nb').attr('src'));
				$("#_image_zoom_container").css({"width":$(window).width() + "px","height":$($(window)).height()+"px","display":"block"});
				$("#_image_zoom_close").css("top",(top-20)+"px").css("right",(right-20)+"px");
			}
		});
		
		$('#_base_layer_view_jjdiv').bind('click',function(){
			debugger
			if(isHasImg($('#_base_layer_view_jj').attr('src'))){
				var top = $(window).height()/2 - 300 ;
				var right = $(window).width()/2 - 300;
				$("#_image_zoom_center").css("top",top+"px").css("right",right+"px");
				$("#_image_zoom_center").attr("src",$('#_base_layer_view_jj').attr('src'));
				$("#_image_zoom_container").css({"width":$(window).width() + "px","height":$($(window)).height()+"px","display":"block"});
				$("#_image_zoom_close").css("top",(top-20)+"px").css("right",(right-20)+"px");
			}
		});
		
		$('#_base_layer_view_yjdiv').bind('click',function(){
			debugger
			if(isHasImg($('#_base_layer_view_yj').attr('src'))){
				var top = $(window).height()/2 - 300 ;
				var right = $(window).width()/2 - 300;
				$("#_image_zoom_center").css("top",top+"px").css("right",right+"px");
				$("#_image_zoom_center").attr("src",$('#_base_layer_view_yj').attr('src'));
				$("#_image_zoom_container").css({"width":$(window).width() + "px","height":$($(window)).height()+"px","display":"block"});
				$("#_image_zoom_close").css("top",(top-20)+"px").css("right",(right-20)+"px");
			}
		});
		
		$("#_image_zoom_close").bind("click",function(){
			$("#_image_zoom_container").hide();
		});
	};
	
	//地图点击事件，执行查询。
	function featureSearch_mapClickEvent(evt){
		debugger;
		identifyTask_params = new IdentifyParameters();
	    identifyTask_params.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
	    identifyTask_params.returnGeometry = true;
	    identifyTask_params.geometry = evt.mapPoint;
	    identifyTask_params.tolerance = 1;
	    identifyTask_params.layerIds = [1];
		
		var identifyTask = new IdentifyTask( _INIT_QPNL );
    	identifyTask_params.mapExtent = map.extent;
		identifyTask.execute(identifyTask_params, identify_showResults);	
	};
	
	//结果展示。
	function identify_showResults(results){
		debugger;
		console.log("results:",results);
        if(results.length > 0){
        	IdentifyTaskfea = results;
        	$('#_base_layer_view_window').window({
    		    top:150,
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
        }
   };
   
   function identifyTask_showwindow(num){
		var graphic = IdentifyTaskfea[num].feature;
		$('#search_detailTable').propertygrid({   
			 columns:[[
		            { title: "名称", field: "name",width:120 },
		            { title: "值", field: "value",width:180 }
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
		$('#_base_layer_view_nb').attr("src","../../files/sjwh/" + graphic.attributes.NBPIC.replace("JPG","jpg") +"");
		$('#_base_layer_view_jj').attr("src","../../files/sjwh/" + graphic.attributes.JJPIC.replace("JPG","jpg") +"");
		$('#_base_layer_view_yj').attr("src","../../files/sjwh/" + graphic.attributes.YJPIC.replace("JPG","jpg") +"");
	};
	
	$("#edit_save_button").bind('click',function(){
		if(type == 1 || type == 2){
//			window.window('close');
//			$('.panel-tool-close:last', parent.document)[0].click();
//			var xzb = parent.document.getElementById("_yjg_fyjSjdj_list_edit_xzb");
//			var yzb = parent.document.getElementById("_yjg_fyjSjdj_list_edit_yzb");
//				$('#_yjg_fyjSjdj_list_add_xzb',parent.document).val(xzb);
			if(sjlx == 0 && xzb != null && yzb != null){
				$("#" + winID + "_xzb", parent.document).val(xzb);
				$("#" + winID + "_yzb", parent.document).val(yzb);
				featureSearch_mapidentifyTask(xzb,yzb);
			}else if(sjlx == 1 && gra != null){
				$("#" + winID + "_xzb", parent.document).val(gra.attributes.X);
				$("#" + winID + "_yzb", parent.document).val(gra.attributes.Y);
				$("#" + winID + "_ssdl", parent.document).val(gra.attributes.SSDL);
				$("#" + winID + "_ssdl", parent.document).trigger('click');
				$("#" + winID + "_jglx", parent.document).val(gra.attributes.JGLX);
				$("#" + winID + "_jglx", parent.document).trigger('click');
				$("#" + winID + "_jgid", parent.document).val(gra.attributes.JGBM);
				$("#" + winID + "_jgid", parent.document).trigger('click');
				$("#" + winID + "_xzqh", parent.document).val(featureSearch_mapidentifyTask(gra.attributes.X,gra.attributes.Y));
				$("#" + winID + "_xzqh", parent.document).trigger('click');
			}else{
				
			}
			}
		mapManager.removeAllLayer();
		mapGraLayer.clear();
		var win = $("#_yjg_sjdj_list_location_window",parent.document);
		win.parent().find('.panel-tool-close')[0].click();
	});
	
	$("#edit_cancel_button").bind('click',function(){
		var win = $("#_yjg_sjdj_list_location_window",parent.document);
		win.parent().find('.panel-tool-close')[0].click();
	});

	//地图点击事件，执行查询。
	function featureSearch_mapidentifyTask(xzb,yzb){
		var identifyTask_params = new IdentifyParameters();
	    identifyTask_params.tolerance = 0;
	    identifyTask_params.layerOption = esri.tasks.IdentifyParameters.LAYER_OPTION_ALL;
	    identifyTask_params.returnGeometry = true;
	    identifyTask_params.geometry = new Point(xzb,yzb,new SpatialReference({ wkid: 4490 }));
		
		var identifyTask = new IdentifyTask( _INIT_XZQH );
    	identifyTask_params.mapExtent = map.extent;
		identifyTask.execute(identifyTask_params, identifyTask_showResults);
	};
	
	function identifyTask_showResults(results){
		if(results.length > 0){
			var result = results[0].feature;
			$("#" + winID + "_xzqh", parent.document).val(result.attributes.QHMC);
			$("#" + winID + "_xzqh", parent.document).trigger('click');
		}
	};
	
	//搜索框绑定事件
	function querySearchInit(){
	   	$('#_baseSearch_clearLayer').bind('click', function() {
//			mapManager.map.graphics.clear();
			mapSearchLayer.clear();
		});
		
		$('#_baseSearch_go').bind('click',function(){
			debugger;
			var jgbh = $('#_baseSearch_addname').val();
			if(jgbh == null || jgbh == ""){
				$.messager.alert("提示","请先输入查询条件");	
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
  	        mapSearchLayer.clear();
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
	     	            	  mapSearchLayer.add(graphic);
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
							mapSearchLayer.add(graphic);
							mapManager.centerAndZoom(point);
						}
					});
				}
  	        return false;
  	      }
      });
   };
	
	//点击查询按钮进行查询
    function baseSearch_searchAddress(name) {
  	  	findParams = new FindParameters();
        findParams.returnGeometry = true;
        findParams.layerIds = [1];
        findParams.searchFields = ["JGBM","SSDL","GLDW"];
        findParams.searchText = name;
        findTask.execute(findParams, baseSearch_showResults_click);
    };
    
  //结果的展示。
    function baseSearch_showResults_click(results) {
    	if(results.length > 0){
			console.log(results);
			mapSearchLayer.clear();
			$.each(results,function(i,row){
			var graphic = row.feature;
			graphic.setSymbol(symbolManager.rangeSearch_picSymbol); 
			mapSearchLayer.add(graphic);
		});
			mapManager.centerAndZoom(results[0].feature.geometry);
	  	}else{
	  		alert("没有查询到数据！请重新输入！！");
	  	}
    };
    
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
	   
   
});





