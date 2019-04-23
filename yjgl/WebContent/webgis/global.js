define([
  "dojo/_base/declare","dojo/_base/lang","dojo/topic","esri/layers/GraphicsLayer","dojo/domReady!"
        ], function (declare,lang,topic,GraphicsLayer) {
	return declare("Global", null, { 
		
		//定义一些公用变量、常量
		
		//服务IP前缀
		SERVICEIP:"http://syseng.kingtopinfo.com:9080/arcgis/rest/services",
		
		defaultUrlArray:[],//查询显示url数组
		
		mapClick:null,//地图点击事件

		mapHightLayer:null,//地图高亮图层
		queryHightLayer:null,//查询结果高亮图层
		editHightLayer:null,//查询结果高亮图层
		clickSearchLayer:null,//点击查询图层
		mapClickHightLayer:null,//地图点击高亮图层
		
		mapDrawLayer:null,   //地图绘制图层
		mapGraLayer:null,    //图形图层。
		mapGraLayers:null,   //所有图形图层。
		mapCurrentGraphic:null,  //当前的图形。
		
		mapCorrectDrawLayer:null, //数据纠错点图层
		mapCorrectGraLayer:null,   //数据纠错标注图层
		mapCorrectGraLayers:null,   //数据纠错所有图层。
		mapCorrectCurrentGraphic:null, //数据纠错当前图形。
		
		mapCustomGraphicsIds: new Array(),
		
		businessLayerLoaded:new Array(),
		
		mapWkid : 4490,
		mapSpatialReference:null,
		
		//判断是否处于查询状态中
		baseSearchFlag:false,
		//当前窗口
		currentWindow:"layerFirst",
		
		//当前背景底图
		currentBackgroundMap:null,
		
		//判断是否进入过对比
		isFirstMultiSwipe:false,
		
		isCorrectErrorFlag:false,
		
		//判断是否定位
		isMapLocationFlag:false,
		
		//图层是否进行拖动
		layer_isDraggable:false,
		
		//是否打开工具条。
		isOpenToolBar:false,
		
		//地图点击查询的判断。
		isStartClickSearch:false,
		
		open_detaillayer_count:true,
		
		//判断是否处于编辑状态
		isEditStatus:false,
		
		backgroundMap:"",
		
		//地图编辑状态打开与关闭
		APP_EDITLAYER_OPEN:"APP_EDITLAYER_OPEN",
		APP_EDITLAYER_CLOSE:"APP_EDITLAYER_CLOSE",
		
		//井盖信息编辑打开与关闭
		APP_JGXXEDIT_OPEN:"APP_JGXXEDIT_OPEN",
		APP_JGXXEDIT_CLOSE:"APP_JGXXEDIT_CLOSE",
		
		//地图点击查询的打开与关闭。
		APP_CLICKSEARCH_OPEN:"APP_CLICKSEARCH_OPEN",
		APP_CLICKSEARCH_CLOSE:"APP_CLICKSEARCH_CLOSE",
		
		//地图点击查询的打开与关闭。
		APP_FEATURESEARCH_OPEN:"APP_FEATURESEARCH_OPEN",
		APP_FEATURESEARCH_CLOSE:"APP_FEATURESEARCH_CLOSE",
		
		//执行模糊查询
		APP_RANGESEARCH_OPEN:"APP_RANGESEARCH_OPEN",
		
		//自定义工具条显示事件。
		APP_TOOLBAR_OPEN:"APP_TOOLBAR_OPEN",
		APP_TOOLBAR_CLOSE:"APP_TOOLBAR_CLOSE",
		
		//自定义图层打开：APP_LAYER_OPEN
		APP_LAYER_OPEN:"APP_LAYER_OPEN",
		
		//自定义基础图层打开：APP_BASELAYER_OPEN
		APP_BASELAYER_OPEN:"APP_BASELAYER_OPEN",
		
		//自定义查询打开事件：APP_BASESEARCH_OPEN
		APP_BASESEARCH_OPEN:"APP_BASESEARCH_OPEN",
		
		//自定义查询关闭事件：APP_BASESEARCH_CLOSE
		APP_BASESEARCH_CLOSE:"APP_BASESEARCH_CLOSE",
		
		//自定义空间查询关闭事件：APP_SPACESEARCH_CLOSE
		APP_SPACESEARCH_CLOSE:"APP_SPACESEARCH_CLOSE",
		
		//自定义测量关闭事件：APP_MEASURE_CLOSE
		APP_MEASURE_CLOSE:"APP_MEASURE_CLOSE",
		//自定义测量和数据纠错冲突处理。
		APP_MEASURE_CORRECT_CLOSE:"APP_MEASURE_CORRECT_CLOSE",
		
		//自定义多窗口/卷帘关闭事件：APP_MULTISWIPE_CLOSE
		APP_MULTISWIPE_CLOSE:"APP_MULTISWIPE_CLOSE",
		
		//数据纠错事件的关闭的打开。
		APP_CORRECTERROR_OPEN:"APP_CORRECTERROR_OPEN",
		APP_CORRECTERROR_CLOSE:"APP_CORRECTERROR_CLOSE",
		
		//地图定位事件的关闭的打开。
		MAP_LOCATION_OPEN:"MAP_LOCATION_OPEN",
		MAP_LOCATION_CLOSE:"MAP_LOCATION_CLOSE",
		
		
		constructor: function () {
			this.defaultUrlArray = [{url:this.SERVICEIP+"/XM_MAP/MapServer",type:"tiled"},{url:this.SERVICEIP+"/XMSLFH/XMSLFH/MapServer",type:"dynamic"}];//查询显示url数组
			this.mapHightLayer = new GraphicsLayer();//地图高亮图层
			this.mapClickHightLayer = new GraphicsLayer();//地图点击高亮图层
			this.queryHightLayer = new GraphicsLayer();//查询结果高亮图层
			this.editHightLayer = new GraphicsLayer();//查询结果高亮图层
			
			this.mapSpatialReference = new esri.SpatialReference(this.mapWkid);
			this.mapGraLayers = new Array();
			this.mapCorrectGraLayers = new Array();
		},
		
		/**
		 * 设置图形样式
		 * @param graphic 图形
		 * @returns 样式
		 */
		setHightSymbol:function(graphic){
			var symbol = null;
		    switch (graphic.geometry.type)
		    {
		       case "point":
		     	    symbol = symbolManager.mapPointSymbol;
		            break;
		       case "polyline":
		            symbol = symbolManager.mapLineSymbol;
		            break;
		       case "polygon":
		            symbol = symbolManager.mapFillSymbol;
		            break;
		    }
		    return symbol;
		},

		/**
		 * 显示值
		 */
		mapShowRemark : function(measureText,pmsTextBgSymbol,textSymbol,type,graphicType) {
			// 测量显示结果背景样式
			var _geometry = global["map"+type+"CurrentGraphic"].geometry;
			if (graphicType != undefined && graphicType != null && graphicType == "polyline") {
				var path_len = _geometry.paths[0].length;
				_geometry = new esri.geometry.Point(_geometry.paths[0][path_len - 1][0],_geometry.paths[0][path_len -1][1],_geometry.spatialReference);
			}
			
			var pmsText = new esri.Graphic(_geometry, pmsTextBgSymbol);
			var textGra = new esri.Graphic(_geometry, textSymbol);
			for(var i =0 ; i < global["map"+type+"GraLayers"].length;i+=2){
				if(global["map"+type+"GraLayers"][i].graphics[0]!=undefined){
					var layerGraphic = global["map"+type+"GraLayers"][i].graphics[0];
					if (typeof(layerGraphic.id) != undefined && layerGraphic.id == global["map"+type+"CurrentGraphic"].id) {
						if (global["map"+type+"GraLayers"][i+1].graphics.length == 1) {
							global["map"+type+"GraLayers"][i+1].add(pmsText);
							global["map"+type+"GraLayers"][i+1].add(textGra);
						}else{
							global["map"+type+"GraLayers"][i+1].graphics[1].setSymbol(pmsTextBgSymbol);
							global["map"+type+"GraLayers"][i+1].graphics[2].setSymbol(textSymbol);
						}
					}
				}
			}
		},
		/** 
		 * 显示属性编辑窗口。
		 */
		mapShowAttrWin:function(){
			$("#_measure_win_form").form("clear");
			$("#_measure_color").val("-1");
			$("#_measure_color").attr("style","width:100px;");
			$('#_measure_win').window({
			    top:150,
				width:300,   
				height:220,
			    right:150,
			    shadow:false,
			    minimizable:false,
			    maximizable:false,
			    resizable:false,
			    title:"属性编辑",
			    onBeforeClose:function(){
			    }
			});
			$('#_measure_win').attr("style","display:block");
			
			$("#_measure_opacity").numberspinner({   
			    min:0,   
			    precision:0,
			    max:100,
			    value:60
			});
		},
		
		mapShowCorrectWin:function(x,y){
			$("#_correctError_win_form").form("clear");
			$('#_correctError_win').window({
			    top : y + 20,
				width : 300,   
				height : 220,
			    left : x + 20,
			    shadow:false,
			    minimizable:false,
			    maximizable:false,
			    resizable:false,
			    title:"数据纠错",
			    onBeforeClose:function(){
			    }
			});
			$('#_correctError_win').attr("style","display:block");
		},
		
		mapInitGraphicLayer : function(type,map) {
			global["map"+type+"DrawLayer"] = new GraphicsLayer();
			global["map"+type+"GraLayer"] = new GraphicsLayer();
			global["map"+type+"GraLayer"].add(global["map"+type+"DrawLayer"]);

			global["map"+type+"GraLayers"].push(global["map"+type+"DrawLayer"]);
			global["map"+type+"GraLayers"].push(global["map"+type+"GraLayer"]);

			map.addLayer(global["map"+type+"DrawLayer"]);
			map.addLayer(global["map"+type+"GraLayer"]);

		},
		
		mapDeleteGraphic:function(toolid,url,type,map){
			var _id = $("#"+toolid+"_id").val();
			if (_id != null && _id != "") {
				ajax(url, {"idArray":_id}, function(data) {
					if (data == 1) {
						for (var i = 0; i < global["map"+type+"GraLayers"].length; i++) {
							var gArray = global["map"+type+"GraLayers"][i].graphics;
							if(gArray.length>0){
								if (_id == gArray[gArray.length - 1].id) {
									map.removeLayer(global["map"+type+"GraLayers"][i + 1]);
									map.removeLayer(global["map"+type+"GraLayers"][i]);
									global["map"+type+"GraLayers"].remove(i + 1);
									global["map"+type+"GraLayers"].remove(i);
								}
							}
						}
						global.mapCustomGraphicsIds.remove(_id);
					}else{
						$.messager.alert("警告", "删除失败！！", "warning");
					}
				}, false);
			}else{
				for (var i = 0; i < global["map"+type+"GraLayers"].length; i++) {
					var gArray = global["map"+type+"GraLayers"][i].graphics;
					if(gArray.length>0){
						if (this.mapCurrentGraphic._graphicsLayer.id == gArray[gArray.length - 1].id) {
							map.removeLayer(global["map"+type+"GraLayers"][i]);
							map.removeLayer(global["map"+type+"GraLayers"][i - 1]);
							global["map"+type+"GraLayers"].remove(i);
							global["map"+type+"GraLayers"].remove(i - 1);
						}
					}
				}
//				map.removeLayer(global["map"+type+"DrawLayer"]);
//				map.removeLayer(global["map"+type+"GraLayer"]);
			}
			$("#"+toolid+"_win").window("close");
		},
		
		closeCorrectError:function(){
			if (this.isCorrectErrorFlag) {
				topic.publish(global.APP_CORRECTERROR_CLOSE);
				this.isCorrectErrorFlag = false;
			}
		}
		
	});
});
