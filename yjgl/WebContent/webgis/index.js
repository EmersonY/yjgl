define.amd.jQuery = true;
var mapManager,symbolManager,global;
var map,isDrag;
var isFirstClickOpen = true;
browserTip();
require(["mapManager","symbolManager","global","dojo/topic","dojo/on","esri/geometry/Extent","esri/toolbars/edit","esri/layers/GraphicsLayer","esri/geometry/Point","esri/graphic","esri/SpatialReference",
         "dojo/_base/lang","esri/dijit/LayerSwipe","esri/map","esri/geometry/Extent","dojo/domReady!" ],
	function( MapManager, SymbolManager, Global,topic,on,Extent,Edit,GraphicsLayer,Point,Graphic,SpatialReference,lang,LayerSwipe,Map,Extent) {
	
	
	init();
	
	function init() {
		global = new Global();
		
		mapManager = new MapManager("mapDiv",false,false);
		map = mapManager.map;
		symbolManager = new SymbolManager();
		
		//初始化样式
		symbolManager.mapSymbol();
		symbolManager.querySymbol();
		symbolManager.measureSymbol();
		symbolManager.correctSymbol();
		//初始化图层
		initMap();
		
		addMenuEvent();

		loadJsFiles();
		
		mapManager.map.addLayer(global.queryHightLayer);
		mapManager.map.addLayer(global.mapHightLayer);
		mapManager.map.addLayer(global.editHightLayer);
		mapManager.map.addLayer(global.mapClickHightLayer);
	    
	    addMeasureBindEvent();
	}

	//初始化数据库中默认加载的地图
	function initMap()
	{
		mapManager.addLayerToMap("tiled",_INIT_DZDT,_DZDT_ID,null,0);
		mapManager.addLayerToMap("tiled",_INIT_DL,_DL_ID,null,1);
		mapManager.addLayerToMap("tiled",_DZDT_ZJ,_DZDT_ZJID,null,2);
		mapManager.addLayerToMap("dynamic",_INIT_QPNL,_QPNL_ID,null,3);
//		var extent = new Extent(118.163143899772,24.4590757501466,118.168342740226,24.4751646602218,new SpatialReference({ wkid: 4490 }));
//		var extent = new Extent(118.087042930088,24.4590757501466,118.168342740226,24.4751646602218,new SpatialReference({ wkid: 4490 }));
//		map.setExtent(extent);
//		mapManager.addLayerToMap("dynamic",_DZDT_ZJ,_DZDT_ZJID,null,1);
//		mapManager.addLayerToMap("dynamic",_INIT_XZQH,_DZDT_ZJID,null,1);
	}
	
	//中间菜单栏点击事件
	function addMenuEvent(){
		//菜单栏点击事件
		$(".toolbutton").click(function(){
			var toolID = $(this).attr("id");
			if (toolID == "map_toolbar") {
				if (global.isOpenToolBar) {
					global.isOpenToolBar = false;
					$("#_toolbar").css("display","none");
					topic.publish(global.APP_TOOLBAR_CLOSE);
				}else{
					global.isOpenToolBar = true;
					$("#_toolbar").css("display","block");
					topic.publish(global.APP_TOOLBAR_OPEN);
				}
				return;
			}
			
			if (toolID == "glyrk") { 
				window.open('../main/index');
				return;
			}
			
			//测量和图层管理。
			if(toolID == "rangeSearch"){
				$('#_base_layer_rangeSearch_window').window({
				    top:300,
					width:580,   
					height:515,
				    left:900,	
				    shadow:false,
				    minimizable:false,
				    maximizable:false,
				    resizable:false,
				    collapsible:true,
				    title:"模糊查询",
				    onBeforeClose:function(){
				    }
	    		});
	    		$('#_base_layer_rangeSearch_window').attr("style","display:block");
//				if ($("#"+toolID+"_win").css("right") != "0px") {
//					$("#_hide_rangeSearch_win").click();
//					$("#"+toolID+"_win").animate({right:'+0px'},500);
//				}
				return;
			}
			
			if(toolID=="EditLayer"){
				if(!global.isEditStatus){
					debugger;
					topic.publish(global.APP_EDITLAYER_OPEN);
					$(this).parent().css("background","rgb(90, 159, 241)");
					global.isEditStatus=true;
				}else{
					topic.publish(global.APP_EDITLAYER_CLOSE);
					$(this).parent().css("background","");
					global.isEditStatus=false;
				}
				return;
			}
			
			if(toolID=="jgxxEdit"){
				topic.publish(global.APP_JGXXEDIT_OPEN);
			}
		});
		
	}
	
	//测量窗体的按钮点击事件的绑定。
	function addMeasureBindEvent(){
		$('#_open_baseLayer_win').bind('click',function(){
			if(global.open_detaillayer_count){
				if (isFirstClickOpen) {
					$.getScript("loadLayer/loadLayer.js");
					isFirstClickOpen = false;
				}
				$("#_detaileLayer_win").css("display","block");
				$("#_detaileLayer_win").animate({left:'+0px'},500,function(){
					global.open_detaillayer_count = false;
				});
				$("#_open_baseLayer_win").css("background-image","url('../images/webgis/451.png')");
				$("#_open_baseLayer_win").animate({left:'+200px'},500);
			}else{
				$("#_detaileLayer_win").animate({left:'-200px'},500,function(){
					global.open_detaillayer_count = true;
					$("#_detaileLayer_win").css("display","block");
				});
				$("#_open_baseLayer_win").animate({left:'+0px'},500);
				$("#_open_baseLayer_win").css("background-image","url('../images/webgis/_451.png')");
			}
			
		});
	}
	
	//加载js脚本文件。
	function loadJsFiles(){
		$.getScript("baseSearch/baseSearch.js");
//		$.getScript("loadLayer/loadLayer.js");
		$.getScript("rangeSearch/rangeSearch.js");
		$.getScript("featureSearch/featureSearch.js");
		$.getScript("featureEdit/featureEdit.js");
		$.getScript("jgxxEdit/jgxxEdit.js");
	}

});

//获取浏览器及版本信息
function getBrowserInfo(){
	var agent = navigator.userAgent.toLowerCase();
	//IE 
	rMsie = /(msie\s|trident\/7)([\w.]+)/;
	rTrident = /(trident)\/([\w.]+)/;
	matchBS = rMsie.exec(agent);
	if (matchBS != null) {
	    matchBS2 = rTrident.exec(agent);  
	    if (matchBS2 != null){  
	    	switch (matchBS2[2]){  
	    		case "4.0": return { browser : "IE", version : "8" };break;  
	    		case "5.0": return { browser : "IE", version : "9" };break;  
	    		case "6.0": return { browser : "IE", version : "10" };break;  
	    		case "7.0": return { browser : "IE", version : "11" };break;  
	    		default:return { browser : "IE", version : "undefined" };
	    	}
	    }else 	
	    	return { browser : "IE", version : matchBS[2] || "0" };
	}
	//firefox
	var regStr_ff = /firefox\/[\d.]+/gi;
	if(agent.indexOf("firefox") > 0){
		var version = (agent.match(regStr_ff)+"").replace(/[^0-9.]/ig,""); 
		return {browser : "firefox", version : version};
	}
	//Chrome
	var regStr_chrome = /chrome\/[\d.]+/gi ;
	if(agent.indexOf("chrome") > 0){
		var version = (agent.match(regStr_chrome)+"").replace(/[^0-9.]/ig,"");
		return {browser : "chrome", version : version};
	}
	//Safari
	var regStr_saf = /safari\/[\d.]+/gi ;
	if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0){
		var version = (agent.match(regStr_saf)+"").replace(/[^0-9.]/ig,"");
		return {browser : "safari", version : version};
	}
}
//根据浏览器版本做相应的提醒
function browserTip(){
	var bs = getBrowserInfo();
	var browser = bs.browser;
	var version = bs.version;
//	var tip = "当前浏览器可能与本系统不兼容，";
//	var downloadURL = "建议下载<a href='../document/downHelpFile.action?filename=ChromeStandaloneSetup.1409816011.exe'>Chrome浏览器</a>";
	if(browser == "IE"){
		if(version < 9){
//			tip += downloadURL;
//			$.messager.alert("提示", tip);
			window.location.href = "download.html";
		}
	}else if(browser=="firefox"){
	}else if(browser=="chrome"){
	}else{
//		tip += downloadURL;
//		$.messager.alert("提示", tip);
		window.location.href = "download.html";
	}
}

/**
* 删除数组指定下标或指定对象
*/
Array.prototype.remove = function(obj) {
	for (var i = 0; i < this.length; i++) {
		var temp = this[i];
		if (!isNaN(obj)) {
			temp = i;
		}
		if (temp == obj) {
			for (var j = i; j < this.length; j++) {
				this[j] = this[j + 1];
			}
			this.length = this.length - 1;
		}
	}
};

