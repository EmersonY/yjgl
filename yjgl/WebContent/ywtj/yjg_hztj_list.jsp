<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	
	<body>
		<script>
		 $(function () {
            $('#yjg_xxly_search_timeStart').datebox({
                onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                    span.trigger('click'); //触发click事件弹出月份层
                    if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                        tds = p.find('div.calendar-menu-month-inner td');
                        tds.click(function (e) {
                            e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                            var year = /\d{4}/.exec(span.html())[0]//得到年份
                            , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                            $('#yjg_xxly_search_timeStart').datebox('hidePanel')//隐藏日期对象
                            .datebox('setValue', year + '-' + month); //设置日期的值
                        });
                    }, 0);
                    yearIpt.unbind();//解绑年份输入框中任何事件
                },
                parser: function (s) {
                    if (!s) return new Date();
                    var arr = s.split('-');
                    return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
                },
                formatter: function (d) {
                var a = parseInt(d.getMonth())<parseInt('9')?'0'+parseInt(d.getMonth()+ 1):d.getMonth() + 1;
                return d.getFullYear() + '-' +a; }
            });
            var p = $('#yjg_xxly_search_timeStart').datebox('panel'), //日期选择对象
                tds = false, //日期选择对象中月份
                yearIpt = p.find('input.calendar-menu-year'),//年份输入框
                span = p.find('span.calendar-text'); //显示月份层的触发控件
            console.log(yearIpt)
           
        });
		</script>
		<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
			<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">事件处置情况统计</legend>
		</fieldset>
		<div class = "yjg_banner">
			<form style="float:left;" action="" method="post" id="_yjg_hztj_search_form"> 
			    <input class="easyui-datebox" id="yjg_hztj_search_timeStart" label="接件时间：" prompt="请输入开始时间" data-options="elabelWidth:85,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
			       	 &nbsp;至&nbsp;
		        <input class="easyui-datebox" id="yjg_hztj_search_timeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
		        <a href="#" id="yjg_hztj_search_button" class="easyui-linkbutton">查询</a>
				<a href="#" id="yjg_hztj_reset_button"  class="easyui-linkbutton">重置</a>
			</form>		
		
		<form style="float:right;margin-right:120px;" action="" method="post" id="_yjg_xxly_search_form"> 
			    前三个月的统计：<input class="easyui-datebox" value="${makedate}" id="yjg_xxly_search_timeStart" data-options="elabelWidth:85,labelAlign:'right',editable:false" style="width:150px;height: 34px;">		      
		        <a href="#" id="yjg_xxly_search_button" class="easyui-linkbutton">查询</a>
				<a href="#" id="yjg_xxly_reset_button"  class="easyui-linkbutton">重置</a>
			</form>
		</div>
		<div class = "yjg_container"> 
			<div class = "yjg_line">
				<div class = "yjg_line_left">
					<div class = "yjg_grid">
						<h4>按窨井类型事件处置情况统计</h4>
						<div id = "_yjg_hztj_jglxtj_grid" style = "width:99%;height:85%;"></div>	
					</div>
				</div>
				<div class = "yjg_line_right">
					<div class = "yjg_line_all" id = "_yjg_hztj_xxly_bar"></div>
				</div> 
			</div>
			<div class = "yjg_line">
				<div class = "yjg_line_all" id = "_yjg_hztj_bar"></div>
			</div>	
		</div>
		<div id="_yjg_hztj_jgtj_detail_window" class="easyui-window" title="统计详情" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%;padding:10px;height: 100%">
			<div class="easyui-layout" data-options="fit:true">
				<div region="center">
					<table id="_yjg_hztj_jgtj_detail_table" style="width:auto;"></table>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../ywtj/yjg_hztj_list.js" charset="UTF-8"></script>
	</body>
</html>