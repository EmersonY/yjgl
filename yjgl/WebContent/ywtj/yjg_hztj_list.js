var YjgHztjList={
	//初始化启动
	init:function(){
		YjgHztjList.bindEvent();
		YjgHztjList.loadSjlxBar();
		YjgHztjList.loadJglxGrid();
		YjgHztjList.loadSjlxlyBar();
	},
	
	//绑定事件
	bindEvent:function(){
		$('#yjg_xxly_search_button').bind('click',function(){
			timeXxly =$("#yjg_xxly_search_timeStart").datebox("getValue");
	    	YjgHztjList.loadSjlxlyBar(timeXxly);
	    	YjgHztjList.loadSjlxBar(timeXxly);
		});
		
		$('#yjg_hztj_reset_button').bind('click',function(){
	    	$('#_yjg_hztj_search_form').form('clear');	
		});
		
		$('#yjg_hztj_search_button').bind('click',function(){
			YjgHztjList.startCavans();
		});
		
		$('#yjg_xxly_reset_button').bind('click',function(){
	    	$('#_yjg_xxly_search_form').form('clear');	
		});
		
	},
	
	startCavans:function(){
		timeStart = $("#yjg_hztj_search_timeStart").datebox("getValue");
		timeEnd = $("#yjg_hztj_search_timeEnd").datebox("getValue");
		if(timeStart != "" && timeEnd != "" && timeStart > timeEnd){
			$.messager.alert('系统消息','开始时间不能晚于结束时间！'); 
			return false;
		}
		YjgHztjList.loadJglxGrid(timeStart,timeEnd);
    },
    
	
	//事件类型统计
	loadSjlxBar:function(timeStart){
		var initTextStyle = {
			fontFamily: 'Arial',
			fontSize: 20,
			color:'red',
			fontStyle: 'normal',
			fontWeight: 'bolder'
		}
    	initToken();
		var psLineChar = echarts.init(document.getElementById('_yjg_hztj_bar'),'macarons');
		psLineChar.clear();
		psLineChar.showLoading(
			{
				text:'正在努力的读取数据中...',
				effect: 'bubble'
			}
		);
		$.ajax({
			url:"../YjgYwtjAction/loadSjlxBar",
			type:"post",
			data:{			
				'timeStartNoDay':timeStart
			},
			dataType:"json",
			success:function(result){
				if(result != null){
					var optionData = result.option;
					option = {
						    title: {
						        x: 'center',
						        text: '按月各类型事件数量统计',
						        subtext:'受理'+result.cs+'件'
						    },
						    tooltip: {
						        trigger: 'item'
						    },
						    calculable: true,
						    grid: {
						        borderWidth: 0,
						        y: 100,
						        y2: 80,
						        x:40,
						        x2:40
						    },
						    legend: {
						    	x: 'right',
						    	orient:'vertical',
						        data:optionData.legend.data,
						    },
						    xAxis: [
						        {
						            type: 'category',
						            show: true,
						            data: optionData.xAxis[0].data
						        }
						    ],
						    yAxis: [
						        {
						            type: 'value',
						            show: true,
						            name:'件'
						        }
						    ],
						    series: [
						        {
						            name: optionData.series[0].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                	
						                }
						            },
						            data: optionData.series[0].data
						        },
						        {
						            name: optionData.series[1].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                }
						            },
						            data: optionData.series[1].data
						        },
						        {
						            name: optionData.series[2].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                }
						            },
						            data: optionData.series[2].data
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
		})
    },
  //事件110类型统计
	loadSjlxlyBar:function(timeXxly){
		var initTextStyle = {
			fontFamily: 'Arial',
			fontSize: 20,
			color:'red',
			fontStyle: 'normal',
			fontWeight: 'bolder'
		}
    	initToken();
		var psLineChar = echarts.init(document.getElementById('_yjg_hztj_xxly_bar'),'macarons');
		psLineChar.clear();
		psLineChar.showLoading(
			{
				text:'正在努力的读取数据中...',
				effect: 'bubble'
			}
		);
		$.ajax({
			url:"../YjgYwtjAction/loadSjlxlyBar",
			type:"post",
			data:{			
				'timeStartNoDay':timeXxly
			},
			dataType:"json",
			success:function(result){
				if(result != null){
					var optionData = result.option;
					option = {
						    title: {
						        x: 'center',
						        text: '按月110/非110事件数量统计',
						        subtext:'受理'+result.cs+'件'
						    },
						    tooltip: {
						        trigger: 'item'
						    },
						    calculable: true,
						    grid: {
						        borderWidth: 0,
						        y: 100,
						        y2: 80,
						        x:40,
						        x2:40
						    },
						    legend: {
						    	x: 'right',
						    	orient:'vertical',
						        data:optionData.legend.data,
						    },
						    xAxis: [
						        {
						            type: 'category',
						            show: true,
						            data: optionData.xAxis[0].data
						        }
						    ],
						    yAxis: [
						        {
						            type: 'value',
						            show: true,
						            name:'件'
						        }
						    ],
						    series: [
						        {
						            name: optionData.series[0].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                	
						                }
						            },
						            data: optionData.series[0].data
						        },
						        {
						            name: optionData.series[1].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                }
						            },
						            data: optionData.series[1].data
						        },
						        {
						            name: optionData.series[2].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                }
						            },
						            data: optionData.series[2].data
						        }/*,
						        {
						            name: optionData.series[3].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                }
						            },
						            data: optionData.series[3].data
						        },
						        {
						            name: optionData.series[4].name,
						            type: 'bar',
						            itemStyle: {
						                normal: {
						                }
						            },
						            data: optionData.series[4].data
						        }*/
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
		})
    },
    
    tjDetail:function(value,ele,baseType){
    	debugger
    	var lastSearchtimeStart  = $("#yjg_hztj_search_timeStart").val();
		var lastSearchtimeEnd = $("#yjg_hztj_search_timeEnd").val();
		var queryParams = {"baseType":baseType,"timeStart":lastSearchtimeStart,"timeEnd":lastSearchtimeEnd,"jglx":value};
		ele.style.color = '#699';
		$('#_yjg_hztj_jgtj_detail_window').window('open');
		initToken();
		$('#_yjg_hztj_jgtj_detail_table').datagrid({
			url:"../YjgYwtjAction/loadsjczqkDetailGrid",
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
    },
	
	//窨井类型统计
	loadJglxGrid:function(timeStart,timeEnd){
		var psLineChar = echarts.init(document.getElementById('_yjg_hztj_jglxtj_grid'),'macarons');
		psLineChar.clear();
		psLineChar.showLoading(
			{
				text:'正在努力的读取数据中...',
				effect: 'bubble'
			}
		);
    	$.ajax({
			url:"../YjgYwtjAction/loadJglxGrid",
			type:"post",
			data:{
				'timeStart':timeStart,
				'timeEnd':timeEnd
			},
			dataType:"json",
			success:function(result){
				if(result != null){
					var data = result.list;
					psLineChar.hideLoading();
					
					$('#_yjg_hztj_jglxtj_grid').datagrid({
						data:data.slice(0,5),
						columns:[[
		                    {hidden:false, align:'center', width:120, field:'jglx', title:'井盖类型'},
		                    {hidden:false, align:'center', width:120, field:'jjsl', title:'接件数量',
		                    	formatter: function(value,row,index){
		                		    var btn = "<a onclick=YjgHztjList.tjDetail('"+row.jglx+"',this,1); href=javascript:void(0)>"+row.jjsl+"</a>";  
		                		    return btn;
		                	    }
		                    },
		                    {hidden:false, align:'center', width:120, field:'wbhCount', title:'未闭合数量',
		                    	formatter: function(value,row,index){
		                		    var btn = "<a onclick=YjgHztjList.tjDetail('"+row.jglx+"',this,2); href=javascript:void(0)>"+row.wbhCount+"</a>";  
		                		    return btn;
		                	    }
		                    },
		                    {hidden:false, align:'center', width:120, field:'bhCount', title:'闭合数量',
		                    	formatter: function(value,row,index){
		                		    var btn = "<a onclick=YjgHztjList.tjDetail('"+row.jglx+"',this,3); href=javascript:void(0)>"+row.bhCount+"</a>";  
		                		    return btn;
		                	    }
		                    },
		                    {hidden:false, align:'center', width:120, field:'bhPercentage', title:'闭合率'},
		                    {hidden:false, align:'center', width:120, field:'clCount', title:'处理数量',
		                    	formatter: function(value,row,index){
		                		    var btn = "<a onclick=YjgHztjList.tjDetail('"+row.jglx+"',this,4); href=javascript:void(0)>"+row.clCount+"</a>";  
		                		    return btn;
		                	    }	
		                    },
		                    {hidden:false, align:'center', width:120, field:'wclCount', title:'未处理数量',
		                    	formatter: function(value,row,index){
		                		    var btn = "<a onclick=YjgHztjList.tjDetail('"+row.jglx+"',this,5); href=javascript:void(0)>"+row.wclCount+"</a>";  
		                		    return btn;
		                	    }
		                    },
		                    {hidden:false, align:'center', width:120, field:'clPercentage', title:'处理率'}
					    ]],
						rownumbers:false,
						pagination:true,
						nowrap:true,
						pageSize:5,
						singleSelect:true,
					});
				    var pg = $('#_yjg_hztj_jglxtj_grid').datagrid('getPager');
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
					          $('#_yjg_hztj_jglxtj_grid').datagrid("loadData", data.slice(start, end)); 
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
	
}

$(function(){
	YjgHztjList.init();
});

