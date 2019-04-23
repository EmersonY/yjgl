		var map;
require(["dojo/_base/lang","esri/config","esri/map","esri/dijit/editing/Editor","esri/tasks/query","esri/tasks/QueryTask","esri/layers/FeatureLayer",
         "esri/layers/GraphicsLayer","esri/geometry/Point","esri/graphic","esri/SpatialReference","dojo/_base/array","dojo/topic","dojo/domReady!"
        ], function (
        		lang,esriConfig, Map,Editor, Query,QueryTask,FeatureLayer,GraphicsLayer,Point,Graphic,SpatialReference, arrayUtils,topic
        ) {
        topic.subscribe(global.APP_JGXXEDIT_OPEN, lang.hitch(this, jgxxEditInit));
 	    topic.subscribe(global.APP_JGXXEDIT_CLOSE, lang.hitch(this, jgxxEdit_Close));

        esri.config.defaults.io.proxyUrl = "http://syseng.kingtopinfo.com:8110/arcgis_proxy/proxy.jsp";
        esri.config.defaults.io.alwaysUseProxy= false;
        
        map = mapManager.map;
        var mapGraLayer = new GraphicsLayer();
        var operationsPointLayer = new FeatureLayer(XMSZFEA + "/0", {
            mode: FeatureLayer.MODE_ONDEMAND,
            outFields : ["*"],
            className : "Feature"
          });
    	initBindEvent();
        function jgxxEditInit(){
        	mapManager.map.addLayer(mapGraLayer);
        	$('#_base_layer_jgxx_window').window({
			    top:300,
				width:600,   
				height:610,
			    left:900,	
			    shadow:false,
			    minimizable:false,
			    maximizable:false,
			    resizable:false,
			    collapsible:true,
			    title:"井盖信息",
			    onBeforeClose:function(){
			    }
    		});
        	loadData();
    		$('#_base_layer_jgxx_window').attr("style","display:block");
        }
        
        function loadData(){
        	initToken();
    		$('#_base_layer_jgxx_list_datagrid').datagrid({
    			url:"../YjgJgxxAction/listByrtzt?rtzt=0",
    			method:'POST',
    			columns:[[
    				{field:'ck',checkbox:true},
    				{hidden:false, align:'center', width:200, field:'jgbh', title:'井盖编号'},
    				{hidden:false, align:'center', width:100, field:'jgcz', title:'井盖材质'},
    				{hidden:false, align:'center', width:100, field:'jngj', title:'井盖材质'},
    				{hidden:false, align:'center', width:150, field:'jglx', title:'井盖类型'},
    				{hidden:false, align:'center', width:200, field:'jgxz', title:'井盖形状'},
    				{hidden:false, align:'center', width:200, field:'jgzt', title:'井盖状态'},
    				{hidden:false, align:'center', width:200, field:'jggg', title:'井盖规格'},
    				{hidden:false, align:'center', width:200, field:'sfzw', title:'是否含有防坠网'},
//    				{hidden:false, align:'center', width:100, field:'xzqh', title:'行政区划'},
    				{hidden:false, align:'center', width:100, field:'ssdl', title:'所属道路'},
    				{hidden:false, align:'center', width:100, field:'gldw', title:'管理单位'},
    				{hidden:false, align:'center', width:100, field:'qsdw', title:'权属单位'},
    				{hidden:false, align:'center', width:100, field:'dljssj', title:'道路建设时间'},
    				{hidden:true, align:'center', width:100, field:'xzb', title:'x坐标'},
    				{hidden:true, align:'center', width:100, field:'yzb', title:'y坐标'},
//    				{hidden:true, align:'center', width:100, field:'jgid', title:'井盖ID'},		
    			]],
    			singleSelect:false,
    			rownumbers:true,
    			pagination:true,
    			pageSize:10,
    			onDblClickRow: function(rowIndex,rowData){
    				if(rowData.xzb != null && rowData.yzb != null){
    					jglocation(rowData.xzb,rowData.yzb);
    				}
    			}
    		});
        }
        
        function jglocation(x,y){
        	var point = new Point(x,y,new SpatialReference({ wkid: 4490 }));
        	var graphic = new Graphic(point,symbolManager.rangeSearch_picSymbol);
        	mapManager.centerAndZoom(point);
        	global.queryHightLayer.clear();
        	global.queryHightLayer.add(graphic);
        }
        
        function initBindEvent(){
        	$('#_base_layer_list_add_button').bind('click',function(){
        		var rows = $('#_base_layer_jgxx_list_datagrid').datagrid('getSelections');
        		if(validateMoreRecord(rows)){
        			var graphics = []
        			for(var i = 0;i < rows.length;i++){
        				var point = new Point(rows[i].xzb,rows[i].yzb,new SpatialReference({ wkid: 4490 }));
        				var att = {
        						"JGBM":rows[i].jgbh,
                    			"JGLX":rows[i].jglx,
                    			"JGGG":rows[i].jggg,
                    			"SSDL":rows[i].ssdl,
                    			"JGZT":rows[i].jgzt,
                    			"JGCZ":rows[i].jgcz,
                    			"JNGJ":rows[i].jngj,
                    			"JGXZ":rows[i].jgxz,
                    			"QSDW":rows[i].qsdw,
                    			"GLDW":rows[i].gldw,
                    			"JGSL":rows[i].jgsl,
                    			"JS":rows[i].js,
                    			"SFZW":rows[i].sfzw,
                    			"DLJSSJ":rows[i].dljssj,
                    			"FHDM":BaseType.initJGLX(rows[i].jglx),
                    			"X":rows[i].xzb,
                    			"Y":rows[i].yzb
        				}
        				var graphic = new Graphic(point,null,att);
        				graphics.push(graphic);
        			}
        			operationsPointLayer.applyEdits(graphics,null,null,function(){
        				mapManager.layerRefreshByUrl(_INIT_QPNL);
        				$.messager.alert("提示","导入图层成功");
        				global.queryHightLayer.clear();
        				debugger;
        				var arr=[]; 
						for(var i = 0 ; i < rows.length; i++){
							rows[i].rtzt = "1";
							arr.push(rows[i]);
						}
    					$.ajax({  
    		                type:"POST",  
    		                url: "../YjgJgxxAction/updatertztBatch",  
    		                dataType:"json",  
    		                contentType:"application/json",
    		                data:JSON.stringify(arr),   
    		                success:function(result){  	
    		                	if(result.sec && result.rows > 0){
    								$('#_base_layer_jgxx_list_datagrid').datagrid('reload');
    							}else{
    								$.messager.alert('警告','数据更新失败！');
    							}
    		                }  
    	                }); 
        			});
        		}
        	});
        	
        	$('#_base_layer_list_addall_button').bind('click',function(){
        		var data = $('#_base_layer_jgxx_list_datagrid').datagrid('getData').rows;
        		debugger;
        		if(data != null){
        			debugger;
        			var graphics = []
        			for(var i = 0;i < data.length;i++){
        				var point = new Point(data[i].xzb,data[i].yzb,new SpatialReference({ wkid: 4490 }));
        				var att = {
        						"JGBM":rows[i].jgbh,
                    			"JGLX":rows[i].jglx,
                    			"JGGG":rows[i].jggg,
                    			"SSDL":rows[i].ssdl,
                    			"JGZT":rows[i].jgzt,
                    			"JGCZ":rows[i].jgcz,
                    			"JNGJ":rows[i].jngj,
                    			"JGXZ":rows[i].jgxz,
                    			"JGSL":rows[i].jgsl,
                    			"JS":rows[i].js,
                    			"QSDW":rows[i].qsdw,
                    			"GLDW":rows[i].gldw,
                    			"SFZW":rows[i].sfzw,
                    			"DLJSSJ":rows[i].dljssj,
                    			"FHDM":BaseType.initJGLX(rows[i].jglx),
                    			"X":rows[i].xzb,
                    			"Y":rows[i].yzb
        				}
        				var graphic = new Graphic(point,null,att);
        				graphics.push(graphic);
        			}
        			operationsPointLayer.applyEdits(graphics,null,null,function(){
        				mapManager.layerRefreshByUrl(_INIT_QPNL);
        				$.messager.alert("提示","导入图层成功");
        				global.queryHightLayer.clear();
        				var arr=[]; 
						for(var i = 0 ; i < data.length; i++){
							data[i].rtzt = "1";
							arr.push(data[i]);
						}
    					$.ajax({  
    		                type:"POST",  
    		                url: "../YjgJgxxAction/updatertztBatch",  
    		                dataType:"json",  
    		                contentType:"application/json",
    		                data:JSON.stringify(arr),   
    		                success:function(result){  
    		                	if(result.sec && result.rows > 0){
    								$('#_base_layer_jgxx_list_datagrid').datagrid('reload');
    							}else{
    								$.messager.alert('警告','数据更新失败！');
    							}
    		                }  
    	                }); 
        			});
        		
        		}
        	});
        	
        	$('#_base_layer_list_location_button').bind('click',function(){
        		var data = $('#_base_layer_jgxx_list_datagrid').datagrid('getData').rows;
        		debugger;
        		global.queryHightLayer.clear(); 
        		for(var i = 0;i < data.length;i++){
        			var point = new Point(data[i].xzb,data[i].yzb,new SpatialReference({ wkid: 4490 }));
                	var graphic = new Graphic(point,symbolManager.rangeSearch_picSymbol);
                	global.queryHightLayer.add(graphic);
        		}
        	});
        	
        	$('#_base_layer_list_clear_button').bind('click',function(){
        		global.queryHightLayer.clear();
        	});
        	
        	$('#_base_layer_list_search_button').bind('click',function(){
        		var queryParams = {
        				"jgbh":$('#_base_layer_list_jgbh').textbox('getValue'),
        				"qsdw":$('#_base_layer_list_qsdw').textbox('getValue')
        		}
        		$('#_base_layer_jgxx_list_datagrid').datagrid('load',queryParams);
        	});
        	
        	$('#_base_layer_list_reset_button').bind('click', function(){
    			$('#_base_layer_list_search_form').form('reset');
    			var queryParams = {
        				"jgbh":"",
        				"qsdw":""
        		};
    			$('#_base_layer_jgxx_list_datagrid').datagrid('load',queryParams);
        	});
        }
        
        function jgxxEdit_Close(){
        	$('#_base_layer_jgxx_window').attr("style","display:none");
        }
        initBtnEvent();
        
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
            	 var json = {"hisjgcz":gra.attributes.JGCZ,"hisjgzt":gra.attributes.JGZT,"hisssdl":gra.attributes.SSDL,
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
            	 var json = {"hisjgcz":addgraphic.attributes.JGCZ,"hisjgzt":addgraphic.attributes.JGZT,"hisssdl":addgraphic.attributes.SSDL,
              			"hisjggg":addgraphic.attributes.JGGG,"hisjglx":addgraphic.attributes.JGLX,"hisjgbh":addgraphic.attributes.BH};
            	 debugger;
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
  								global.queryHightLayer.remove(addgraphic);
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