define([
          "dojo/_base/declare","dojo/_base/lang","esri/symbols/PictureMarkerSymbol","esri/symbols/PictureFillSymbol","esri/symbols/SimpleMarkerSymbol",
          "esri/symbols/SimpleLineSymbol","esri/symbols/SimpleFillSymbol","esri/Color","esri/symbols/Font","esri/symbols/TextSymbol","dojo/domReady!"
        ], function (declare,lang,PictureMarkerSymbol,PictureFillSymbol,SimpleMarkerSymbol,SimpleLineSymbol,SimpleFillSymbol,Color,Font,TextSymbol) {
	return declare("SymbolManager", null, { 
		
		//样式的创建、初始化
		
		measure_pointSymbol:null,// 定义线测量时，点的样式
		measure_lineSymbol:null,// 定义线测量时，线的样式
		measure_fillSymbol:null,// 定义面测量时，线和面的样式
		measure_pmsTextBg:null,// 测量显示结果背景样式
		measure_textSymbol:null,// 测量显示结果字体样式
		measure_deleteImg:null,// 测量显示结果删除图片样式
		
		mapPointSymbol:null,//地图高亮点样式
		mapLineSymbol:null,//地图高亮线样式
		mapFillSymbol:null,//地图高亮面样式

		baseSearch_fillSymbol:null,//查询结果高亮面样式
		baseSearch_markSymbol:null,
		rangeSearch_picSymbol:null,
		
		fillSymbol:null,//框选面样式
		
		correctError_picSymbol:null, //纠错样式。
		
		createGraphic_Symbol:null,
		/**
		 * 测量样式
		 */
		measureSymbol:function(){
			// 定义线测量时，点的样式
			this.measure_pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 8,
					new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0 ]), 1), new Color("#FFFF66"));
		
			// 定义线测量时，线的样式
			this.measure_lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([ 255, 0,0 ]), 2);
			
			// 定义面测量时，线和面的样式
			this.measure_fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,
					new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT, new Color([ 255, 0, 0 ]), 2), new Color([ 255, 0, 0,0.25 ]));
			
			// 测量显示结果背景样式
			this.measure_pmsTextBg = new PictureMarkerSymbol(getRootPath() + '/image/measure/background.png', 120, 18);
			
			// 测量显示结果删除图片样式
			this.measure_deleteImg = new PictureMarkerSymbol(getRootPath() + '/image/measure/cancel_red.png', 10, 10);
		},
	
		/**
		 * 测量显示结果字体样式背景。
		 */
		measureTextBgSymbol:function(measureText){
			var positionX = 48;
			if ("起点" == measureText) {
				positionX = 36;
			}
			var gra_picMarkerSymbol = new PictureMarkerSymbol(getRootPath() + '/image/measure/background.png', 150, 25);
			gra_picMarkerSymbol.setWidth((measureText.length) * 11 + 38);
			gra_picMarkerSymbol.setOffset(positionX, 16);
			return gra_picMarkerSymbol;
		},
		
		/**
		 * 测量显示结果字体样式
		 * @param measureText 测量值
		 */
		measureTextSymbol:function(measureText){
			var positionX = 48;
			if ("起点" == measureText) {
				positionX = 36;
			}
			// 测量显示结果字体样式
			var font = new Font("18px", Font.STYLE_NORMAL, Font.VARIANT_NORMAL, Font.WEIGHT_NORMAL);
			var textSymbol = new TextSymbol(measureText, font, new Color([ 50, 50, 50 ]));
			textSymbol.setOffset(positionX, 12);
			return textSymbol;
		},
		
		/**
		 * 地图高亮点线面样式
		 */
		mapSymbol:function(){
			this.mapPointSymbol = new PictureMarkerSymbol('../../images/webgis/symbol/locate_red.png',30, 50);//地图高亮点样式
			this.mapLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255,0,0]), 10);//地图高亮线样式
			this.mapFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
							new Color([ 255,0,0 ]), 1), new Color([ 255, 255, 0,1 ]));//地图高亮点样式
		},
		
		rangeSymbol:function(){
			this.rangeSearch_picSymbol = new PictureMarkerSymbol({
				"url":"../../images/webgis/symbol/red.gif",
			    "height":22,
			    "width":22
			});
		},
		
		/**
		 * 查询结果高亮点线面样式
		 */
		querySymbol:function(){
			this.baseSearch_fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,
					new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
							new Color([ 255,0,0 ]), 1), new Color([ 255, 255, 0,1 ]));
			this.baseSearch_markSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 8,
					new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0 ]), 1), new Color([ 255, 255, 0,1 ]));
		},
		
		correctSymbol:function(){
			this.correctError_picSymbol = new PictureMarkerSymbol('../image/correctError/RedShinyPin.png',30, 30);
		},
		
		createTagSymbol:function(colorArr,type){
			switch (type){
				case "point":
					return new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 8,
							new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 0, 0 ]), 1), new Color(colorArr));
				case "polyline":
					return new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color(colorArr), 2);
				case "polygon":
					return new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,
							new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT, new Color([ 255, 0, 0 ]), 2), new Color(colorArr));
				default:
					return null;
			}
					
		}
		
	});
});
