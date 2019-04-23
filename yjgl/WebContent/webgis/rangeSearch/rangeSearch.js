require(["esri/tasks/IdentifyParameters","esri/tasks/IdentifyTask",
         "esri/tasks/FindTask","esri/tasks/FindParameters",
         "esri/tasks/query","esri/tasks/QueryTask","esri/graphic",
         "esri/layers/GraphicsLayer","dojo/_base/lang","dojo/topic","dojo/domReady!" ],
	function(IdentifyParameters,IdentifyTask,FindTask,FindParameters,Query,QueryTask,Graphic,GraphicsLayer,lang,topic) {
	
	var rangeSearch_query = new Query();
	var rangeSearch_queryTask;
	debugger;
    topic.subscribe(global.APP_RANGESEARCH_OPEN, lang.hitch(this, queryFeaBysps));
//    topic.subscribe(global.APP_RANGESEARCH_CLOSE, lang.hitch(this, baseSearch_closeWin));
	
	//部署到服务器上面使用这个地址
//	esri.config.defaults.io.proxyUrl= "http://syseng.kingtopinfo.com:8110/arcgis_proxy/proxy.jsp";
	//在本地使用这个地址。
//	esri.config.defaults.io.proxyUrl = "http://localhost:8080/arcgis_proxy/proxy.jsp";
//	esri.config.defaults.io.alwaysUseProxy = false;
	
	rangeSearch_init();
	
	function rangeSearch_init(){
		
		symbolManager.rangeSymbol();
		
		initSelect();
		
		//查询按钮的点击事件
		$("#_base_layer_rangeSearch_search_button1").bind("click",function(){
			global.queryHightLayer.clear();
			queryFeaByjgxx();
		});
		
		//查询按钮的点击事件
		$("#_base_layer_rangeSearch_search_button2").bind("click",function(){
			var sjlx = $("#_base_layer_rangeSearch_search_sjlx").combobox('getText');
			if (sjlx == "--请选择--") {
				$.messager.alert('提示','请先选择事件类型');
			}else{
				global.queryHightLayer.clear();
				queryFeaBysjxx();
			}
		});
		
		//清空按钮的点击事件
		$("#_base_layer_rangeSearch_reset_button").bind("click",function(){
//			initSelect();
			global.queryHightLayer.clear();
			$('#_base_layer_rangeSearch_list_search_form').form('reset');
			$("#_base_layer_rangeSearch_search_jglx").combobox('setValue','-1');
			$("#_base_layer_rangeSearch_search_jgzt").combobox('setValue','-1');
			$("#_base_layer_rangeSearch_search_jgxz").combobox('setValue','-1');
			$("#_base_layer_rangeSearch_search_jgcz").combobox('setValue','-1');
			$("#_base_layer_rangeSearch_search_sfzw").combobox('setValue','-1');
			$("#_base_layer_rangeSearch_search_ssdl").combobox('setValue','-1');
			$("#_base_layer_rangeSearch_search_sjlx").combobox('setValue','-1');
//			$("#_base_layer_rangeSearch_search_gldw").text("setValue","");
//			$("#_base_layer_rangeSearch_search_qsdw").text("setValue","");
//			$("#_base_layer_rangeSearch_search_sbrxm").text("setValue","");
//			$("#_base_layer_rangeSearch_search_jgbh").text("setValue","");
		});
	}
	function queryFeaBysps(){
		$("#_base_layer_rangeSearch_search_button1").trigger('click');
	}
	function initSelect(){
		BaseType.initSelectEditBox('_base_layer_rangeSearch_search_jglx','JGLX', true, false , 155, true);
		BaseType.initSelectBox('_base_layer_rangeSearch_search_jgxz','JGXZ', true, false, 80);
		BaseType.initSelectBox('_base_layer_rangeSearch_search_jgzt','JGZT', true, false, 80);
		BaseType.initSelectBox('_base_layer_rangeSearch_search_jgcz','JGCZ', true, false, 80);
		BaseType.initSelectBox('_base_layer_rangeSearch_search_sfzw','SFZW', true, false, 80);
		BaseType.initSelectBox('_base_layer_rangeSearch_search_ssdl','SSDL', true, false, 80);
		BaseType.initSelectBox('_base_layer_rangeSearch_search_sjlx','SJXZ', true, false, 80);
	}
	
	function contains(arr, obj) {
		  var index = arr.length;
		  while (index--) {
		    if (arr[index] === obj) {
		      return true;
		    }
		  }
		  return false;
		}
	
	function queryFeaByjgxx(){
		debugger;
		if(global.mapHightLayer.graphics.length != 0){
			rangeSearch_query.geometry = global.mapHightLayer.graphics[0].geometry;
		}else{
			rangeSearch_query.geometry = null;
		}
		rangeSearch_query.returnGeometry=true;
		rangeSearch_query.outFields=["*"];
		rangeSearch_queryTask = new QueryTask(_INIT_QPNL + "/" + 1);
		var _where = "";
			var jgxz = $("#_base_layer_rangeSearch_search_jgxz").combobox('getText');
			var sfzw = $("#_base_layer_rangeSearch_search_sfzw").combobox('getText');
			var jgzt = $("#_base_layer_rangeSearch_search_jgzt").combobox('getText');
			var jgcz = $("#_base_layer_rangeSearch_search_jgcz").combobox('getText');
			var jglx = $("#_base_layer_rangeSearch_search_jglx").combobox('getText');
			var ssdl = $("#_base_layer_rangeSearch_search_ssdl").combobox('getText');
			var jggg = $("#_base_layer_rangeSearch_search_jggg").textbox('getText');
			var jgsl = $("#_base_layer_rangeSearch_search_jgsl").textbox('getText');
			var jngj = $("#_base_layer_rangeSearch_search_jngj").textbox('getText');
			var qsdw = $("#_base_layer_rangeSearch_search_qsdw").textbox('getValue');
			var gldw = $("#_base_layer_rangeSearch_search_gldw").textbox('getValue');
			var jgbh = $("#_base_layer_rangeSearch_search_jgbh").textbox('getValue');
			if (jgcz != "--请选择--") {
				_where += " JGCZ = '" + jgcz + "'";
			}
			if (jglx != "请输入信息") {
				if (_where != "") {
					_where += " and JGLX = '" + jglx + "'";
				}else{
					_where += " JGLX = '" + jglx + "'";
				}
			}
			if (jngj != "") {
				if (_where != "") {
					_where += " and JNGJ = '" + jngj + "'";
				}else{
					_where += " JNGJ = '" + jngj + "'";
				}
			}
			if (jgsl != "") {
				if (_where != "") {
					_where += " and JGSL = '" + jgsl + "'";
				}else{
					_where += " JGSL = '" + jgsl + "'";
				}
			}
			if (jgzt != "--请选择--") {
				if (_where != "") {
					_where += " and JGZT = '" + jgzt + "'";
				}else{
					_where += " JGZT = '" + jgzt + "'";
				}
			}
			if (jgxz != "--请选择--") {
				if (_where != "") {
					_where += " and JGXZ = '" + jgxz + "'";
				}else{
					_where += " JGXZ = '" + jgxz + "'";
				}
			}
			if (jggg != "") {
				if (_where != "") {
					_where += " and JGGG = '" + jggg + "'";
				}else{
					_where += " JGGG = '" + jggg + "'";
				}
			}
			if (sfzw != "--请选择--") {
				if (_where != "") {
					_where += " and SFZW = '" + sfzw + "'";
				}else{
					_where += " SFZW = '" + sfzw + "'";
				}
			}
			if (ssdl != "--请选择--") {
				if (_where != "") {
					_where += " and SSDL = '" + ssdl + "'";
				}else{
					_where += " SSDL = '" + ssdl + "'";
				}				
			}
			if (jgbh != "") {
				if (_where != "") {
					_where += " and JGBM like '%" + jgbh + "%'";
				}else{
					_where += " JGBM like '%" + jgbh + "%'";
				}
			}
			if (qsdw != "") {
				if (_where != "") {
					_where += " and QSDW like '%" + qsdw + "%'";
				}else{
					_where += " QSDW like '%" + qsdw + "%'";
				}
			}
			if (gldw != "") {
				if (_where != "") {
					_where += " and GLDW like '%" + gldw + "%'";
				}else{
					_where += " GLDW like '%" + gldw + "%'";
				}
			}
		
		if (_where == "" && rangeSearch_query.geometry == null) {
			$.messager.alert('提示','请选择查询范围和条件');
			return;
		}else if(_where == "" && rangeSearch_query.geometry != null){
			_where = "1=1";
		}
		console.log(_where);
		rangeSearch_query.where = _where;
		rangeSearch_queryTask.execute(rangeSearch_query,lang.hitch(this,rangeSearch_identifySuccess),function(){
		});
	}
	
	function queryFeaBysjxx(){
		debugger
		var timeStart = $("#_base_layer_rangeSearch_search_timeStart").datebox("getValue");
		var timeEnd = $("#_base_layer_rangeSearch_search_timeEnd").datebox("getValue");
		if(timeStart != "" && timeEnd != "" && timeStart > timeEnd){
			$.messager.alert('系统消息','开始时间不能早于结束时间！'); 
			return false;
		}
//		var datetimeStart = new Date(timeStart.replace(/-/g,"/"));
//		var datetimeEnd = new Date(timeEnd.replace(/-/g,"/"));
		var jgxz = $("#_base_layer_rangeSearch_search_jgxz").combobox('getText');
		if (jgxz == "--请选择--") {
			jgxz = "";
		}
		var sfzw = $("#_base_layer_rangeSearch_search_sfzw").combobox('getText');
		if (sfzw == "--请选择--") {
			sfzw = "";
		}
		var jgzt = $("#_base_layer_rangeSearch_search_jgzt").combobox('getText');
		if (jgzt == "--请选择--") {
			jgzt = "";
		}
		var jgcz = $("#_base_layer_rangeSearch_search_jgcz").combobox('getText');
		if (jgcz == "--请选择--") {
			jgcz = "";
		}
		var jglx = $("#_base_layer_rangeSearch_search_jglx").combobox('getText');
		if (jglx == "--请选择--" || jglx == "请输入信息") {
			jglx = "";
		}
		var ssdl = $("#_base_layer_rangeSearch_search_ssdl").combobox('getText');
		if (ssdl == "--请选择--") {
			ssdl = "";
		}
		var sjlx = $("#_base_layer_rangeSearch_search_sjlx").combobox('getValue');
		if (sjlx == -1) {
			sjlx = "";
		}
		var jgsl = $("#_base_layer_rangeSearch_search_jgsl").textbox('getValue');
		var jngj = $("#_base_layer_rangeSearch_search_jngj").textbox('getValue');
		var jggg = $("#_base_layer_rangeSearch_search_jggg").textbox('getValue');
		var qsdw = $("#_base_layer_rangeSearch_search_qsdw").textbox('getValue');
		var gldw = $("#_base_layer_rangeSearch_search_gldw").textbox('getValue');
		var jgbh = $("#_base_layer_rangeSearch_search_jgbh").textbox('getValue');
		var sbrxm = $("#_base_layer_rangeSearch_search_sbrxm").textbox('getValue');
		var queryParams ={
				"jgsl":jgsl,
				"jgzt":jgzt,
				"jggg":jggg,
				"jgcz":jgcz,
				"ssdl":ssdl,
				"sfzw":sfzw,
				"jngj":jngj,
				"jgxz":jgxz,
				"jgbh":jgbh,
				"sbrxm":sbrxm, 
				"sjlx":sjlx,
				"jglx":jglx,  
				"gldw":gldw,
				"qsdw":qsdw,
				"timeStart":timeStart,
				"timeEnd":timeEnd
			};
		url = "../YjgSjdjAction/listbyjgxx";
		ajax(url,queryParams,function(result){
			debugger;
			if(result != null && result.length >0){
				for(var i = 0;i < result.length;i++){
					var point = new esri.geometry.Point(result[i].xzb,result[i].yzb,new esri.SpatialReference(4490));
					global.queryHightLayer.add(new Graphic(point,symbolManager.rangeSearch_picSymbol));
				}
//				mapManager.centerAndZoom(new esri.geometry.Point(result[0].xzb,result[0].yzb,new esri.SpatialReference(4490)));
			}else{
				$.messager.alert("提示","没有查询到数据,请重新输入条件");
			}
		})	
		
	}
	
	//结果的显示
	function rangeSearch_identifySuccess(results) {
		debugger;
		console.log(results);
		if (results.features.length > 0) {
			for (var i = 0; i < results.features.length; i++) {
				var feature = results.features[i];
				if(feature.geometry.type == "point"){
					var centerGra = new Graphic(feature.geometry,symbolManager.rangeSearch_picSymbol);
					global.queryHightLayer.add(centerGra);
				}else{
					var centerPoint = feature.geometry.getExtent().getCenter();
					var centerGra = new Graphic(centerPoint,symbolManager.rangeSearch_picSymbol);
					global.queryHightLayer.add(centerGra);
				}
			}
//			mapManager.centerAndZoom(results.features[0].geometry);
		}else{
			$.messager.alert('提示','没有符合条件的数据,请重新输入');
		}
	}
	
	//格式化单位。
	function formatAreaUnit(area){
		if (area == "") {
			return null;
		}
		area = parseFloat(area);
		var unit = $('#rangeSearch_areaUnits option:selected').val();
		switch(unit){
			case "平方千米":
				area = area * 1000000;
				break;
			case "公顷":
				area = area * 10000;
				break;
			case "亩":
				area = area * 666.6667;
				break;
			default :
				area = area;
				break;
		}
		return area;
	}
	
});
