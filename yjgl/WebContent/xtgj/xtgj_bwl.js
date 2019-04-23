function getDateArray(_year,_morth) {		
	var arr = new Array(0);
	_morth = _morth -1;
	var d = new Date(_year,_morth);
	var _w = d.getDay()-1;
	for(var i=-_w; i<35-_w; i++){
		var d2 = new Date(_year,_morth,i);
		arr.push(d2);
	}
	return arr;
}

var currentDate = new Date();
var year = currentDate.getFullYear();
var month = currentDate.getMonth() + 1;
var arr = getDateArray(year,month);

var YjgBwlList={
	init:function(){
		YjgBwlList.initType();
		YjgBwlList.initData();
		YjgBwlList.bindEvent();
	},
	
	initType:function(){
		BaseType.init('SFBS');
	},
	
	initData:function(){
		YjgBwlList.setLayout();
		$(window).resize(function() {
			YjgBwlList.closeAllWindow();
			YjgBwlList.setLayout();
		});
		YjgBwlList.initDivData(formatDate(arr[0]),month);
	},
	
	setLayout:function(){
		var _w = $("#_main").width()/7.1;
		var _h = ($("#_main").height()-80)/5;
		for(var i=0;i<7;i++){
			$("#q"+i).width(_w-1);
			$("#q"+i).height("40px");
		}
		for(var y=0; y<5; y++){
			for(var x=0; x<7; x++){
				var _q = x+''+y;
				$('#p'+_q).width(_w);
				$('#p'+_q).height(_h);
			}
		}
		$("#but").width($("#_main").width()-20);
		$("#but").height("40px");
	},
	
	bindEvent:function(){
		$('#_pre_month').bind('click', function(){
			month = month-1;
			if(month == 0){
				month = 12;
				year = year - 1;
			}
			arr = getDateArray(year,month);
			YjgBwlList.initDivData(formatDate(arr[0]),month);
		});
		$('#_next_month').bind('click', function(){
			month = month + 1;
			if(month == 13){
				month = 1;
				year = year + 1;
			}
			arr = getDateArray(year,month);
			YjgBwlList.initDivData(formatDate(arr[0]),month);
		}); 	
		$('#p00').bind('click', function(){
			YjgBwlList.openBwlList($('#p00').panel('options').title.substring(0,10),"p00");
		}); 
		$('#p10').bind('click', function(){
			YjgBwlList.openBwlList($('#p10').panel('options').title.substring(0,10),"p10");
		}); 
		$('#p20').bind('click', function(){
			YjgBwlList.openBwlList($('#p20').panel('options').title.substring(0,10),"p20");
		}); 
		$('#p30').bind('click', function(){
			YjgBwlList.openBwlList($('#p30').panel('options').title.substring(0,10),"p30");
		}); 
		$('#p40').bind('click', function(){
			YjgBwlList.openBwlList($('#p40').panel('options').title.substring(0,10),"p40");
		}); 
		$('#p50').bind('click', function(){
			YjgBwlList.openBwlList($('#p50').panel('options').title.substring(0,10),"p50");
		}); 
		$('#p60').bind('click', function(){
			YjgBwlList.openBwlList($('#p60').panel('options').title.substring(0,10),"p60");
		}); 
		$('#p01').bind('click', function(){
			YjgBwlList.openBwlList($('#p01').panel('options').title.substring(0,10),"p01");
		}); 
		$('#p11').bind('click', function(){
			YjgBwlList.openBwlList($('#p11').panel('options').title.substring(0,10),"p11");
		}); 
		$('#p21').bind('click', function(){
			YjgBwlList.openBwlList($('#p21').panel('options').title.substring(0,10),"p21");
		}); 
		$('#p31').bind('click', function(){
			YjgBwlList.openBwlList($('#p31').panel('options').title.substring(0,10),"p31");
		}); 
		$('#p41').bind('click', function(){
			YjgBwlList.openBwlList($('#p41').panel('options').title.substring(0,10),"p41");
		}); 
		$('#p51').bind('click', function(){
			YjgBwlList.openBwlList($('#p51').panel('options').title.substring(0,10),"p51");
		}); 
		$('#p61').bind('click', function(){
			YjgBwlList.openBwlList($('#p61').panel('options').title.substring(0,10),"p61");
		}); 
		$('#p02').bind('click', function(){
			YjgBwlList.openBwlList($('#p02').panel('options').title.substring(0,10),"p02");
		}); 
		$('#p12').bind('click', function(){
			YjgBwlList.openBwlList($('#p12').panel('options').title.substring(0,10),"p12");
		}); 
		$('#p22').bind('click', function(){
			YjgBwlList.openBwlList($('#p22').panel('options').title.substring(0,10),"p22");
		}); 
		$('#p32').bind('click', function(){
			YjgBwlList.openBwlList($('#p32').panel('options').title.substring(0,10),"p32");
		}); 
		$('#p42').bind('click', function(){
			YjgBwlList.openBwlList($('#p42').panel('options').title.substring(0,10),"p42");
		}); 
		$('#p52').bind('click', function(){
			YjgBwlList.openBwlList($('#p52').panel('options').title.substring(0,10),"p52");
		}); 
		$('#p62').bind('click', function(){
			YjgBwlList.openBwlList($('#p62').panel('options').title.substring(0,10),"p62");
		}); 
		$('#p03').bind('click', function(){
			YjgBwlList.openBwlList($('#p03').panel('options').title.substring(0,10),"p03");
		}); 
		$('#p13').bind('click', function(){
			YjgBwlList.openBwlList($('#p13').panel('options').title.substring(0,10),"p13");
		}); 
		$('#p23').bind('click', function(){
			YjgBwlList.openBwlList($('#p23').panel('options').title.substring(0,10),"p23");
		}); 
		$('#p33').bind('click', function(){
			YjgBwlList.openBwlList($('#p33').panel('options').title.substring(0,10),"p33");
		}); 
		$('#p43').bind('click', function(){
			YjgBwlList.openBwlList($('#p43').panel('options').title.substring(0,10),"p43");
		}); 
		$('#p53').bind('click', function(){
			YjgBwlList.openBwlList($('#p53').panel('options').title.substring(0,10),"p53");
		}); 
		$('#p63').bind('click', function(){
			YjgBwlList.openBwlList($('#p63').panel('options').title.substring(0,10),"p63");
		}); 
		$('#p04').bind('click', function(){
			YjgBwlList.openBwlList($('#p04').panel('options').title.substring(0,10),"p04");
		}); 
		$('#p14').bind('click', function(){
			YjgBwlList.openBwlList($('#p14').panel('options').title.substring(0,10),"p14");
		}); 
		$('#p24').bind('click', function(){
			YjgBwlList.openBwlList($('#p24').panel('options').title.substring(0,10),"p24");
		}); 
		$('#p34').bind('click', function(){
			YjgBwlList.openBwlList($('#p34').panel('options').title.substring(0,10),"p34");
		}); 
		$('#p44').bind('click', function(){
			YjgBwlList.openBwlList($('#p44').panel('options').title.substring(0,10),"p44");
		}); 
		$('#p54').bind('click', function(){
			YjgBwlList.openBwlList($('#p54').panel('options').title.substring(0,10),"p54");
		}); 
		$('#p64').bind('click', function(){
			YjgBwlList.openBwlList($('#p64').panel('options').title.substring(0,10),"p64");
		}); 
		//条件查询按钮事件
		$('#_xtgl_bwl_search_button').bind('click', function(){ 
			var bwltimeStart = $("#xtgl_bwlTimeStart").val();
			var bwltimeEnd = $("#xtgl_bwlTimeEnd").val(); 
			if(bwltimeStart != "" && bwltimeEnd != "" && bwltimeStart >= bwltimeEnd){
				$.messager.alert('系统消息','开始时间不能晚于或等于结束时间！'); 
				return false;
			}
			var queryParams ={
					'bwlnr':$("#xtgl_bwl_bwlnr").val(),
					'bwlwz':$("#xtgl_bwl_bwlwz").val(),
					'startDate':$("#_bwlDateHidden").val(),
					'bwltimeStart':bwltimeStart,
					'bwltimeEnd':bwltimeEnd
			};
			$('#_xtgj_bwl_table').datagrid('load',queryParams);
		});
		//重置按钮
		$('#_xtgl_bwl_reset_button').bind('click', function(){
			$('#_xtgl_bwl_search_form').form('reset');	
		}); 
		//增加备忘录
		$('#_xtgl_bwl_add_button').bind('click', function(){ 
			$('#_xtgl_bwl_add_window').window('open');
			var time = $("#_bwlDateHidden").val()+ " 08:00:00";
			$('#_xtgl_bwl_add_bwldate').datetimebox('setValue', time);
		});
		
		//增加页面取消按钮事件
		$('#_xtgl_bwl_add_cancel_button').bind('click', function(){
			$('#_xtgl_bwl_add_window').window('close');	
		});  
		//增加页面保存按钮事件
		$('#_xtgl_bwl_add_save_button').bind('click', function(){   
			$('#_xtgl_bwl_add_form').form('submit', {   
				url:"../YjgBwlAction/add",
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_xtgl_bwl_add_window').window('close');
						$('#_xtgj_bwl_table').datagrid('reload');
					}else{
						$.messager.alert('警告','保存失败！'); 
					}
		   	    } 
			});  
		});
		//编辑页面按钮事件
		$('#_xtgl_bwl_edit_button').bind('click', function(){ 
			var data=$('#_xtgj_bwl_table').datagrid('getSelections');
			if(data.length < 1)
			{
				$.messager.alert('警告','请选择一条数据！');
				return;
			}
			if(data.length > 1)
			{
				$.messager.alert('警告','一次只能选择一条数据进行编辑！');
				return;
			} 
			$('#_xtgl_bwl_edit_window').window('open');
			$('#_xtgl_bwl_edit_form').form('reset');
			$("#_xtgl_bwl_edit_bwljb").val(data[0].bwljb);
			initFormData($('#_xtgl_bwl_edit_form'),data[0]);
			setDefaultFormData($('#_xtgl_bwl_edit_form'));
		});
		 
		//编辑页面取消按钮事件
		$('#_xtgl_bwl_edit_cancel_button').bind('click', function(){
			$('#_xtgl_bwl_edit_window').window('close');
		});  
		 
		//编辑页面保存按钮事件
		$('#_xtgl_bwl_edit_save_button').bind('click', function(){
			$('#_xtgl_bwl_edit_form').form('submit', {   
				url:"../YjgBwlAction/edit",
				onSubmit: function(){
					return $(this).form('validate');
				},   
				success:function(r){   
					var result = eval('(' + r + ')');
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'保存数据成功！',showType:'slide'});
						$(this).form('reset');
						$('#_xtgl_bwl_edit_window').window('close');
						$('#_xtgj_bwl_table').datagrid('reload');
					}else{
						$.messager.alert('警告','编辑失败！'); 
					}
		   	    } 
			});  
		}); 
		
		//编辑页面取消按钮事件
		$('#_xtgl_bwl_read_button').bind('click', function(){
			var data=$('#_xtgj_bwl_table').datagrid('getSelections');
			if(validateOneRecord(data)){
				if(data[0].bwlzt != 0){
					$.messager.alert('警告',"请选择'未读'备忘录进行操作!");
					return false;
				}
				$.messager.confirm('确认已读','确认进行已读操作？',function(r){ 
					if(r){
						initToken();
						$.ajax({
							url:"../YjgBwlAction/read",
							type: "POST",
							dataType: "json",
							data:{"bwlid":data[0].bwlid},  
							success: function(result){
								if(result.rows>0){
									$.messager.alert('警告',"操作成功!");
									$('#_xtgj_bwl_table').datagrid('reload');
								}
							}
						});
					}
				})
			}
		}); 
		
		//查看按钮
		$('#_xtgj_bwl_view_button').bind('click', function(){
			var data=$('#_xtgj_bwl_table').datagrid('getSelections');
			if(validateOneRecord(data)){
			$('#_xtgj_bwl_view_window').window('open');
			initFormData($('#_xtgj_bwl_view_form'),data[0]);
			setDefaultFormData($('#_xtgj_bwl_view_form'));
			}
		}); 
		
		//删除按钮
		$('#_xtgl_bwl_delete_button').bind('click', function(){
			var data=$('#_xtgj_bwl_table').datagrid('getSelections');
			if(validateMoreRecord(data)){
				var str ="";
				for(var i = 0 ; i < data.length; i++)
					str += "&idArray=" + data[i].bwlid;
				var url = "../YjgBwlAction/deleteBatch" + "?" + str.substring(1);	
				deleteBatch(url,null,function(result){
					if(result.sec && result.rows > 0){
						$.messager.show({title:'系统消息',msg:'删除记录成功！',showType:'slide'});
						$('#_xtgj_bwl_table').datagrid('reload');
					}else{
						$.messager.alert('警告','删除记录失败！');
					}
				});
			}
		});  
		
	},
	
	initDivData:function(startDateJson,searchMonth){
		var rbs = null;
			$.ajax({
				url:"../YjgBwlAction/selectByDate",
				data:{"startDate":startDateJson,"searchMonth":searchMonth},
				type:"post",
				dataType:"json",
				async: false,
				success: function(result){ 
					rbs=result.list;
					var index = 0;
					for(var y=0; y<5; y++){
						for(var x=0; x<7; x++){
							var _q = x+''+y;
							var map = rbs[index];
							$('#p'+_q).css('background-image','');
							$('#p'+_q).panel({title:map.bt});
							$('#p'+_q).parent().css('float','left');
							$('#p61').parent().css('float','left');
							$('#p62').parent().css('float','left');
							$('#p63').parent().css('float','left');
							$('#p64').parent().css('float','left');
							$('#p65').parent().css('float','left');
							$('#p66').parent().css('float','left');
							$('#p'+_q).html("");
							if(map.dateList.length>0){ 
								$('#p'+_q).html("");
								var htmlStr = "";
								for (var i=0;i<map.dateList.length;i++) {
									htmlStr = "时间："+map.dateList[i].bwltimeStart+"      标题："+ map.dateList[i].bwlbt+ "<br>" + htmlStr ;
								}
								$('#p'+_q).append('<div style="width: 100%;height: 100%;"><span style="font-size: 12px;">'+ htmlStr +'<br></span></div>');
							} 
							if(map.isCurrentDate == 1){//当前天
								$('#p'+_q).css('background-image','url("../images/bwl/b-5.png")');
							}else{
								if(map.hasholiday == 1){ //有法定安排
									$('#p'+_q).css('background-image','url("../images/bwl/b-4.png")');
								}else{
									if(map.isWeekend == 1){//周末
										$('#p'+_q).css('background-image','url("../images/bwl/b-1.png")');
									}else{
										if(map.isSearchMonth == 0){
											$('#p'+_q).css('background-image','url("../images/bwl/b-3.png")');
										}else{
											$('#p'+_q).css('background-image','');
										}
									}
								}
							}
							index ++;
						}
					}
				}	
			});
	}, 
	
	closeAllWindow:function(){
		for(var y=0; y<5; y++){
			for(var x=0; x<7; x++){
				var _q = x+''+y;
				$('#p'+_q).window("close");	
			}
			for(var i=0;i<7;i++){
				$("#q"+i).window("close");
			}
			$("#but").window("close");
		}
	},
	
	openBwlList:function (date,id){
		$("#_bwlDateHidden").val(date);
		$("#_xtgl_bwl_list_window").window({
			title:"备忘录    "+date,
			onClose:YjgBwlList.onClose
		}).window('open');
		YjgBwlList.loadBwlData();
	},
	onClose:function(){
    	$('#_main').panel({
    	    href:"../xtgj/xtgj_bwl.jsp"
    	}); 
	},
	
	loadBwlData:function(){
		initToken();
		$('#_xtgj_bwl_table').datagrid({
			url:"../YjgBwlAction/list",
			queryParams:{"startDate":$("#_bwlDateHidden").val()},
			method:'POST',
			columns:[[
				{field:'ck',checkbox:true},
				{hidden:false, align:'center', width:200, field:'bwlbt', title:'标题'},
				{hidden:false, align:'center', width:200, field:'bwlnr', title:'内容'}, 
				{hidden:false, align:'center', width:300, field:'bwlwz', title:'地点'},
				{hidden:false, align:'center', width:250, field:'bwldate', title:'时间'},
				{hidden:false, align:'center', width:250, field:'bwlzt', title:'已读',
					formatter:function(value,row,index){
						return BaseType.getNameByValue("SFBS", value);
					}	
				},
				{hidden:false, align:'center', width:100, field:'bwljb', title:'级别'}
			]],
			rownumbers:true,
			pagination:true,
			nowrap:true,
			pageSize:20,
		});
		$('.datagrid-header-inner .datagrid-cell').css("text-align","center");
		$('.datagrid-header-inner .datagrid-cell').css("height","auto");
	}
}
$(function(){
	YjgBwlList.init();
});
function parsedate(value){  
	debugger
    var date = new Date(value);  
	var year = date.getFullYear();  
	var month = date.getMonth()+1; //月份+1     
	var day = date.getDate();   
	var hour = date.getHours();   
	var minutes = date.getMinutes();   
	var second = date.getSeconds();  
	return  day+"/"+month+"/"+year+" "+hour+":"+minutes +":"+second;  
} 