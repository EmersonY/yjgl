define([
          "dojo/_base/declare","dojo/on","dojo/_base/lang","esri/map","esri/layers/ImageParameters","esri/layers/ArcGISImageServiceLayer","esri/layers/ArcGISTiledMapServiceLayer",
          "esri/layers/ArcGISDynamicMapServiceLayer","esri/toolbars/navigation","esri/dijit/OverviewMap","dojo/domReady!"
        ], function (declare,on,lang,Map,ImageParameters,ArcGISImageServiceLayer,ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer,Navigation,OverviewMap) {
	return declare("MapManager", null, { 
		
		//管理地图：添加或移除图层、定义或解除地图点击事件、获取当前可显示的图层、修改指定图层的透明度、
		//设置添加或移除图层后事件，更新全局对象global的当前图层组的值
		
		map:null,//地图
		
		navigation:null,//导航工具

		currentLayers:[],//当前图层组
		overviewMapDijit:null,
		Search_button:false,
		Search_field:[],
		Search_url:null,
		
		/**
		 * 创建地图
		 * @param mapId 地图id
		 * @param logoTF 是否显示logo，默认值为false
		 * @param sliderTF 是否显示导航条，默认值为false
		 */
		constructor: function (mapId,logoTF,sliderTF) {
			if(logoTF == undefined)
				logoTF = false;
			if(sliderTF == undefined)
				sliderTF = false;
			this.map = new Map(mapId,{
				logo:logoTF,
			    slider:sliderTF
			});
			this.currentLayers = [];
			this.navigation = new Navigation(this.map);
		},
		
		/**
		 * 放大
		 */
		zoomIn:function(){
			this.navigation.activate(Navigation.ZOOM_IN);
		},
		
		/**
		 * 缩小
		 */
		zoomOut:function(){
			this.navigation.activate(Navigation.ZOOM_OUT);
		},
		
		/**
		 * 漫游
		 */
		pan:function(){
			this.navigation.activate(Navigation.PAN);
		},
		
		/**
		 * 还原
		 */
		fullExtent:function(){
			this.navigation.zoomToFullExtent();
		},
		
		/**
	     * 开启鹰眼
	     */
		setOverviewMap:function (location,width,height,showOrHide){
			if(location == undefined)
				location = "bottom-right";
			if(width == undefined)
				width = 280;
			if(height == undefined)
				height = 200;
			if(showOrHide == undefined)
				showOrHide = false;
			overviewMapDijit = new OverviewMap({
				map:this.map,
			    attachTo:location,
			    width:width,
			    height:height,
			    visible:true
			});	
			overviewMapDijit.startup();	
			if(!showOrHide)
				overviewMapDijit.hide();
		},
		
		/**
	     * 根据地图比例尺获得放大级别
	     */
		centerAndZoom:function(point){
			var currentScale = this.map.getScale();
			console.log('Scale: ' + currentScale);
			debugger
			if(currentScale > 10000){
				this.map.setScale(9017.870881919862);
				this.map.centerAt(point);
			}else if(currentScale > 5000 && currentScale <= 10000){
				this.map.setScale(2254.4677204799655);
				this.map.centerAt(point);
				
			}else if(currentScale > 2000 && currentScale <= 5000){
				this.map.setScale(1127.2338602399827);
				this.map.centerAt(point);
				
			}else if(currentScale > 1000 && currentScale <= 2000){
				this.map.setScale(563.6169301199914);
				this.map.centerAt(point);
				
			}else if(currentScale > 500 && currentScale <= 1000){
				this.map.centerAt(point);
			}else{
				this.map.centerAt(point);
			}
			
		},
		
		/**
		 * 设置添加图层后事件
		 */
		addLayerAfterEvent:function(func){
			//设置添加图层后事件
			on(mapManager.map, 'layer-add', lang.hitch(this, function(evt) {
				func();
			}));
		},
		
		/**
		 * 设置移除图层后事件
		 */
		removeLayerAfterEvent:function(func){
			//设置添加图层后事件
			on(mapManager.map, 'layer-remove', lang.hitch(this, function(evt) {
				func();
			}));
		},
		
		/**
		 * 添加图层到地图上
		 * @param type 图层类型
		 * @param url 图层地址
		 * @param id 图层id
		 */
		addLayerToMap:function(type,url,id,opactity,index){
			if(id == undefined || id == null || id == ""){
				var time = new Date();
				id = time.getTime();
			}
			var agoLayer;
			if(type == "image"){
				agoLayer = new ArcGISImageServiceLayer(url,{id:id.toString()});
			    agoLayer.className=type;
			}
    		else if(type == "tiled"){
    			agoLayer = new ArcGISTiledMapServiceLayer(url,{id:id.toString()});
    			agoLayer.imageFormat = "png24";
    			agoLayer.className=type;
    		}
    		else{
    			debugger;
    			agoLayer = new ArcGISDynamicMapServiceLayer(url,{id:id.toString()});
    			agoLayer.imageFormat = "png24";
    			agoLayer.className=type;
    		}
			if(opactity != undefined && opactity != null && opactity != ""){
				agoLayer.setOpacity(opactity);
			}
			
			if(index != undefined && index != null){
				this.map.addLayer(agoLayer,index);
			}else{
				this.map.addLayer(agoLayer);
			}
		},
		
		/**
		 * 判断是否已存在指定id图层，若有则移除
		 * @param id 图层id
		 */
		removeLayerById:function(id){
			var layer = this.map.getLayer(id);
			if(layer != undefined && layer != null)
				this.map.removeLayer(layer);
		},
		
		/**
		 * 移除所有图层
		 */
		removeAllLayer:function(){
			if(this.map != null )
				this.map.removeAllLayers();
		},
		
		/**
		 * 通过图层id得到对应图层
		 */
		getLayerById:function(id){
			return this.map.getLayer(id);
		},
		
		/**
		 * 获取当前可显示的图层
		 * @return 返回当前可显示图层
		 */
		getCurrentLayers:function(){
			this.currentLayers = this.map.getLayersVisibleAtScale();
			return this.currentLayers;
		},
		
		/**
		 * url：指定图层url
		 * value：透明度
		 */
		layerOpacityByUrl:function(url,value){
			//获取当前可显示的图层
			var layers = this.map.getLayersVisibleAtScale();
			if(layers.length != 0){
				//判断当前地图上是否已加载指定图层
				for(var i = 0; i < layers.length; i++){
					if(layers[i].url == undefined || layers[i].url == null){
						break;
					}else if(layers[i].url.indexOf(url) != -1){
						layers[i].setOpacity(value);
						break;
					}
				}
			}
		},
		
		/**
		 * id：指定图层id
		 * Visible：可见性
		 */
		layerVisibleByUrl:function(id,Visible){
			//获取当前可显示的图层
			var layer = this.map.getLayer(id);
			layer.setVisibility(Visible);
		},
		
		/**
		 * url：指定图层url
		 */
		layerRefreshByUrl:function(url){
			//获取当前可显示的图层
			var layers = this.map.getLayersVisibleAtScale();
			if(layers.length != 0){
				//判断当前地图上是否已加载指定图层
				for(var i = 0; i < layers.length; i++){
					if(layers[i].url == undefined || layers[i].url == null){
						break;
					}else if(layers[i].url.indexOf(url) != -1){
						layers[i].refresh();
						break;
					}
				}
			}
		}		
		
	});
});
