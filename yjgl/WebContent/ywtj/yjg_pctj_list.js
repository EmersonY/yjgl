require(["dojo/_base/lang","dojo/topic","esri/toolbars/draw","symbolManager","mapManager","esri/SpatialReference","esri/layers/GraphicsLayer", "esri/graphic","esri/geometry/Point",
          "esri/geometry/Circle","esri/geometry/Extent","esri/tasks/IdentifyTask","esri/tasks/IdentifyParameters","esri/tasks/FindTask",
          "esri/tasks/FindParameters","esri/tasks/query","esri/tasks/QueryTask","esri/layers/ArcGISTiledMapServiceLayer",
          "esri/layers/ArcGISDynamicMapServiceLayer","esri/layers/FeatureLayer","dojo/domReady!"],
		function(lang,topic,Draw,SymbolManager,mapManager,SpatialReference,GraphicsLayer,Graphic,Point,Circle,Extent,IdentifyTask,IdentifyParameters,
				FindTask,FindParameters,Query,QueryTask,ArcGISTiledMapServiceLayer,ArcGISDynamicMapServiceLayer,FeatureLayer) {
		var mapManager = new MapManager("mapDiv",false,false);
		var	symbolManager = new SymbolManager();
		symbolManager.querySymbol();
		var graphiclayer = new GraphicsLayer();
		var find = new FindTask(_INIT_QPNL);
	    var map = mapManager.map;
	    var sps =  new SpatialReference({"wkid":4490});
		//样式
	    var initTextStyle = {
			fontFamily: 'Arial',
			fontSize: 20,
			color:'red',
			fontStyle: 'normal',
			fontWeight: 'bolder'
	    }
	    
	    var timeStart = "";
	    var timeEnd = "";
		
		loadYwlyBar(timeStart,timeEnd);
		loadDltjGrid(timeStart,timeEnd);
		loadJgtjGrid(timeStart,timeEnd);
		
		//加载地图相关事件
		InitMapEvent();
		
		
		$('#yjg_pctj_search_button').bind('click',function(){
			startCavans();
		});
		
	    $('#yjg_pctj_reset_button').bind('click',function(){
	    	$('#_yjg_ywtj_search_form').form('clear');	
	    });
	    
	    function startCavans(){
			timeStart = $("#yjg_pctj_search_timeStart").datebox("getValue");
			timeEnd = $("#yjg_pctj_search_timeEnd").datebox("getValue");
			if(timeStart != "" && timeEnd != "" && timeStart > timeEnd){
				$.messager.alert('系统消息','开始时间不能晚于结束时间！'); 
				return false;
			}
			$("#_last_searchtime_start").val(timeStart);
			$("#_last_searchtime_end").val(timeEnd);
			loadYwlyBar(timeStart,timeEnd);
			loadJgtjGrid(timeStart,timeEnd);
			loadDltjGrid(timeStart,timeEnd);
	    }
		//查询地图显示
		function queryMapInit(){
			var initPoint = new Point([118.12556881364476,24.453894765898402],mapManager.map.spatialReference);
			mapManager.centerAndZoom(initPoint);
		}
		
		//查询错误提示
		function queryTask_errors(e){
			$.messager.alert('',e);
		}  
		
		//窨井业务来源统计
	    function loadYwlyBar(timeStart,timeEnd){
			initToken();
			var psLineChar = echarts.init(document.getElementById('_yjg_ywly_bar'),'macarons');
			psLineChar.clear();
			psLineChar.showLoading(
				{
					text:'正在努力的读取数据中...',
					effect: 'bubble'
				}
			);
			$.ajax({
				url:"../YjgYwtjAction/loadYwlyBar",
				async: false,
				type:"post",
				data:{	
					'code':'XXLY',
					'timeStart':timeStart,
					'timeEnd':timeEnd,
				},
				dataType:"json",
				success:function(result){
					if(result != null){
						var optionData = result.option;
						option = {
							    title: {
							        x: 'center',
							        text: '按上报来源统计'
							    },
							    tooltip: {
							        trigger: 'item'
							    },
							    calculable: true,
							    grid: {
							        borderWidth: 0,
							        y: 80,
							        y2: 60,
							        x:22,
							        x2:22
							    },
							    xAxis: [
							        {
							            type: 'category',
							            show: false,
							            data: optionData.xAxis[0].data
							        }
							    ],
							    yAxis: [
							        {
							            type: 'value',
							            show: false,
							            name:'件'
							        }
							    ],
							    series: [
							        {
							            name: '按上报来源统计',
							            type: 'bar',
							            itemStyle: {
							                normal: {
							                    color: function(params) {
							                        // build a color map as your need.
							                        var colorList = [
							                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
							                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
							                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
							                        ];
							                        return colorList[params.dataIndex]
							                    },
							                    label: {
							                        show: true,
							                        position: 'top',
							                        formatter: '{b}\n{c}件'
							                    }
							                }
							            },
							            data: optionData.series[0].data
							        }
							    ]
							};
						
						psLineChar.setOption(option,true);
						psLineChar.hideLoading();
					}else{
						psLineChar.hideLoading();
						psLineChar.showLoading(
								{
									text:'提示,暂无数据！',
									effect: 'bubble',
								    textStyle : initTextStyle
								}
					   	);
					}
				},
				error:function(){
					psLineChar.hideLoading();
					psLineChar.showLoading(
							{
								text:'抱歉,暂无数据！',
								effect: 'bubble',
							    textStyle : initTextStyle
							}
				   	);
				}
			});
			
		}
	    
	    //窨井业务井盖统计
		function loadJgtjGrid(timeStart,timeEnd){
	    	var psLineChar = echarts.init(document.getElementById('_yjg_pctj_jgtj_grid'),'macarons');
			psLineChar.clear();
			psLineChar.showLoading(
				{
					text:'正在努力的读取数据中...',
					effect: 'bubble'
				}
			);
	    	$.ajax({
				url:"../YjgYwtjAction/loadJgtjGrid",
				async: false,
				type:"post",
				data:{
					'timeStart':timeStart,
					'timeEnd':timeEnd,
				},
				dataType:"json",
				success:function(result){
					debugger
					if(result != null){
						var data = result.sjdjList;
						psLineChar.hideLoading();
						
						$('#_yjg_pctj_jgtj_grid').datagrid({
							data:data.slice(0,5),
							columns:[[
			                    {hidden:false, align:'center',  field:'jgid', title:'井盖编号',fitColumns:true},
			                    {hidden:false, align:'center',  field:'count', title:'接件数量',fitColumns:true},
			                    {hidden:false, align:'center', width:130,field:'opt',title:'查看窨井位置',
			                	    formatter: function(value,row,index){
			                		    var btn = "<a onclick=jglocation('JGBM','"+row.jgid+"',this) href=javascript:void(0)>查看窨井位置</a>";  
			                		    return btn;
			                	    }
			                    },
			                    {hidden:false, align:'center', width:130,field:'optone',title:'查看事件详情',
			                	    formatter: function(value,row,index){
			                		    var btn = "<a onclick=tjDetail('"+row.jgid+"',this,1); href=javascript:void(0)>查看事件详情</a>";  
			                		    return btn;
			                	    }
			                    }
						    ]],
							rownumbers:false,
							pagination:true,
							nowrap:true,
							pageSize:5,
							singleSelect:true,
						});
					    var pg = $('#_yjg_pctj_jgtj_grid').datagrid('getPager');
					    pg.pagination({ 
					    	  pageSize: 5,//每页显示的记录条数，默认为10 
					    	  pageList: [5,10,15],//可以设置每页记录条数的列表 
					    	  beforePageText: '第',//页数文本框前显示的汉字 
					    	  afterPageText: '页', 
					    	  displayMsg: ' 当前显示 {from} - {to} 条记录   共 {total} 条记录',
					    	  total:data.length,
					          onSelectPage:function (pageNo, pageSize) { 
						          var start = (pageNo - 1) * pageSize; 
						          var end = start + pageSize; 
						          $('#_yjg_pctj_jgtj_grid').datagrid("loadData", data.slice(start, end)); 
						          pg.pagination('refresh', { 
						              total:data.length, 
						              pageNumber:pageNo 
						          }); 		          
						      } 
					    });
					}else{
						psLineChar.hideLoading();
						psLineChar.showLoading(
								{
									text:'提示,暂无数据！',
									effect: 'bubble',
								    textStyle : initTextStyle
								}
					   	);
					}
				},
				error:function(){
					psLineChar.hideLoading();
					psLineChar.showLoading(
							{
								text:'提示,暂无数据！',
								effect: 'bubble',
							    textStyle : initTextStyle
							}
				   	);
				}
			});
		}
		
		 //窨井业务道路统计
		function loadDltjGrid(timeStart,timeEnd){
			var psLineChar = echarts.init(document.getElementById('_yjg_pctj_dltj_grid'),'macarons');
			psLineChar.clear();
			psLineChar.showLoading(
				{
					text:'正在努力的读取数据中...',
					effect: 'bubble'
				}
			);
	    	$.ajax({
				url:"../YjgYwtjAction/loadDltjGrid",
				async: false,
				type:"post",
				data:{
					'timeStart':timeStart,
					'timeEnd':timeEnd,
				},
				dataType:"json",
				success:function(result){
					if(result != null){
						var data = result.sjdjList;
						psLineChar.hideLoading();
						
						$('#_yjg_pctj_dltj_grid').datagrid({
							data:data.slice(0,5),
							columns:[[
			                    {hidden:false, align:'center', field:'ssdl', title:'道路名称',fitColumns:true},
			                    {hidden:false, align:'center', field:'count', title:'接件数量',fitColumns:true},
			                    {hidden:false, align:'center', width:130,field:'opt',title:'查看道路位置',
			                	    formatter: function(value,row,index){
			                		    var btn = "<a onclick=jglocation('SSDL','"+row.ssdl+"',this) href=javascript:void(0)>查看道路位置</a>";  
			                		    return btn;
			                	    }
			                    },
			                    {hidden:false, align:'center', width:130,field:'optone',title:'查看事件详情',
			                	    formatter: function(value,row,index){
			                		    var btn = "<a onclick=tjDetail('"+row.ssdl+"',this,2); href=javascript:void(0)>查看事件详情</a>";  
			                		    return btn;
			                	    }
			                    }
						    ]],
							rownumbers:false,
							pagination:true,
							nowrap:true,
							pageSize:5,
							singleSelect:true,
						});
					    var pg = $('#_yjg_pctj_dltj_grid').datagrid('getPager');
					    pg.pagination({ 
					    	  pageSize: 5,//每页显示的记录条数，默认为10 
					    	  pageList: [5,10,15],//可以设置每页记录条数的列表 
					    	  beforePageText: '第',//页数文本框前显示的汉字 
					    	  afterPageText: '页', 
					    	  displayMsg: ' 当前显示 {from} - {to} 条记录   共 {total} 条记录',
					    	  total:data.length,
					          onSelectPage:function (pageNo, pageSize) { 
						          var start = (pageNo - 1) * pageSize; 
						          var end = start + pageSize; 
						          $('#_yjg_pctj_dltj_grid').datagrid("loadData", data.slice(start, end)); 
						          pg.pagination('refresh', { 
						              total:data.length, 
						              pageNumber:pageNo 
						          }); 		          
						      } 
					    });
					}else{
						psLineChar.hideLoading();
						psLineChar.showLoading(
								{
									text:'提示,暂无数据！',
									effect: 'bubble',
								    textStyle : initTextStyle
								}
					   	);
					}
				},
				error:function(){
					psLineChar.hideLoading();
					psLineChar.showLoading(
						{
							text:'提示,暂无数据！',
							effect: 'bubble',
						    textStyle : initTextStyle
						}
				   	);
				}
			});
		}
		function InitMapEvent(){
			initMap();
		}
		
		//初始化图层
		function initMap(){
			mapManager.addLayerToMap("tiled",_INIT_DZDT,_DZDT_ID,null,0);
			mapManager.addLayerToMap("tiled",_INIT_DL,_DL_ID,null,1);
			mapManager.addLayerToMap("tiled",_DZDT_ZJ,_DZDT_ZJID,null,2);
			mapManager.addLayerToMap("dynamic",_INIT_QPNL,_QPNL_ID,null,3);
			mapManager.map.addLayer(graphiclayer);
		}
		
		jglocation = function (filed,value,ele){
			ele.style.color = '#699';
			if(value != null){
				  var params = new FindParameters();
				  params.returnGeometry = true;
				  params.layerIds = [1];
				  params.searchFields = [filed];
				  params.searchText = value;
				  if(filed == "SSDL"){
					  find.execute(params, showRoadResults);
				  }else{
					  find.execute(params, showResults);
				  }
				  
			}
		}
		
		tjDetail = function(value,ele,type){
			var lastSearchtimeStart  = $("#_last_searchtime_start").val();
			var lastSearchtimeEnd = $("#_last_searchtime_end").val();
			var queryParams = "";
			if(type == 1){
				queryParams = {"jgid":value,"timeStart":lastSearchtimeStart,"timeEnd":lastSearchtimeEnd};
				ele.style.color = '#699';
			}else if(type == 2){
				queryParams = {"ssdl":value,"timeStart":lastSearchtimeStart,"timeEnd":lastSearchtimeEnd};
				ele.style.color = '#699';
			}
			$('#_yjg_pctj_jgtj_detail_window').window('open');
			initToken();
			$('#_yjg_pctj_jgtj_detail_table').datagrid({
				url:"../YjgYwtjAction/loadJgtjDetailGrid",
				queryParams:queryParams,
				method:'POST',
				columns:[[
					{field:'ck',checkbox:true},
					{hidden:false, align:'center', width:220, field:'sjdjdh', title:'事件单号'},
					{hidden:false, align:'center', width:100, field:'sbrxm', title:'上报人姓名'},
					{hidden:false, align:'center', width:150, field:'sbrdh', title:'上报人电话'},
					{hidden:false, align:'center', width:220, field:'sqzt', title:'事件状态',
						formatter:function(value,row,index){
							return BaseType.getNameByValue("SQZT", value);
						},
						styler: function (value){
							if('0'==value ){
								return 'color:red';
							}else if('6'==value){
								return 'color:lightGray';
							}else if('7'==value){
								return 'color:green';
							}
						}
					},
					{hidden:false, align:'center', width:100, field:'xxly', title:'信息来源',
						formatter:function(value,row,index){
							return BaseType.getNameByValue("XXLY", value);
						}
					},
					{hidden:false, align:'center', width:150, field:'jgid', title:'井盖编号'},
					{hidden:false, align:'center', width:100, field:'jglx', title:'井盖类型',
						formatter:function(value,row,index){
							return BaseType.getNameByValue("JGLX", value);
						}	
					},
					{hidden:false, align:'center', width:100, field:'sjlx', title:'事件性质',
						formatter:function(value,row,index){
							return BaseType.getNameByValue("SJXZ", value);
						}
					},
					{hidden:false, align:'center', width:200, field:'yzjb', title:'严重级别',
						formatter:function(value,row,index){
							return BaseType.getNameByValue("YZJB", value);
						}
					},
					{hidden:false, align:'center', width:200, field:'scsj', title:'生成时间'},
					{hidden:false, align:'center', width:200, field:'jssj', title:'结束时间'},
					{hidden:false, align:'center', width:200, field:'updatetime', title:'更新时间'},
					{hidden:true, align:'center', width:200, field:'instanceid'},
					{hidden:false, align:'center', width:100, field:'updator', title:'更新人'}
				]],
				fit:true,
				rownumbers:true,
				pagination:true,
				pageSize:20,
				view: detailview,  
		  		detailFormatter:function(index,row){    
		        	return '<div id="_pctj_list_bg_' + index + '" style="padding:5px 0"></div>';    
		  		},  
		  		onExpandRow: function(index,row){
		  			debugger
		    		var o=$('#_pctj_list_bg_'+index);
		    		var instanceid= $(this).datagrid('getRows')[index].instanceid;
		    		var sqzt= $(this).datagrid('getRows')[index].sqzt; 
					$('#_pctj_list_bg_'+index).datagrid({    
						url:"../YjgSjdjAction/listHisFlow",
						queryParams:{'instanceid':instanceid,'sqzt':sqzt},
						fitColumns:true,
						columns:[[
							{hidden:false, align:'center', width:100, field:'taskname', title:'流程节点名称'},
							{hidden:false, align:'center', width:100, field:'taskendtime', title:'操作完成时间'},
							{hidden:false, align:'center', width:100, field:'username', title:'操作人'},
						]], 
						onLoadSuccess:function(data){   
							$('#_yjg_pctj_jgtj_detail_table').datagrid('fixDetailRowHeight',index);    
							setTimeout(function () {
								var tr=o.closest('tr');
								id = tr.prev().attr('id'); //此子表格父行所在行的id
								id = id.replace(/-2-(\d+)$/, '-1-$1'); //detailview没有展开的前部分的id是有规则的
								$('#' + id).next().css('height', tr.height());//设置没展开的前部分的高度，由于启用了计时器，会闪一下
							}, 0);
						}
					})
				}
			});
		}
		
		function showResults(findResult){
			debugger;
			if(findResult.length > 0){
				var point = new Point(findResult[0].feature.geometry.x,findResult[0].feature.geometry.y,sps);
				graphiclayer.clear();
				var graphic = new Graphic(point,symbolManager.baseSearch_pointSymbol);
				graphiclayer.add(graphic);
				mapManager.centerAndZoom(point);
			}else{
				$.messager.alert("提示","该井盖并未入图");
			}
		}
		
		function showRoadResults(findResult){
			if(findResult != null){
				graphiclayer.clear();
				var point = new Point(findResult[0].feature.geometry.x,findResult[0].feature.geometry.y,sps);
				mapManager.centerAndZoom(point);
			}else{
				$.messager.alert("提示","找不到数据");
			}
		}
		
	}
);