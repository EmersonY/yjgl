<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	
	<body>
		<input type="hidden" id="_last_searchtime_start" >
		<input type="hidden" id="_last_searchtime_end" >
		<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
			<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">事件频次统计</legend>
		</fieldset>
		<div class = "yjg_banner">
			<form action="" method="post" id="_yjg_ywtj_search_form"> 
			    <input class="easyui-datebox" id="yjg_pctj_search_timeStart" label="接件时间：" prompt="请输入开始时间" data-options="elabelWidth:85,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
			       	 &nbsp;至&nbsp;
		        <input class="easyui-datebox" id="yjg_pctj_search_timeEnd" prompt="请输入结束时间" data-options="labelAlign:'right',editable:false" style="width:180px;height: 34px;">
		        <a href="#" id="yjg_pctj_search_button" class="easyui-linkbutton">查询</a>
				<a href="#" id="yjg_pctj_reset_button"  class="easyui-linkbutton">重置</a>
			</form>
		</div>
		<div class="yjg_pctj_west">
			<div class = "yjg_container"> 
				<div class = "yjg_line">
					<div class = "yjg_line_left">
						<div class = "yjg_grid">
							<h4>按窨井编号统计</h4>
							<div id = "_yjg_pctj_jgtj_grid" style = "width:99%;height:85%;"></div>	
						</div>
					</div>
					<div class = "yjg_line_right">
						<div class = "yjg_grid">
							<h4>按道路统计</h4>
							<div id = "_yjg_pctj_dltj_grid" style = "width:99%;height:85%;"></div>	
						</div>
					</div> 
				</div>
				<div class = "yjg_line no_border_bottom">
					<div class = "yjg_line_all" id = "_yjg_ywly_bar"></div>
				</div>	
			</div>
		</div>
		<div class="yjg_pctj_east">
			<div id="mapDiv" style="width:100%;height:100%;">
			</div>
		</div>
		<div id="_yjg_pctj_jgtj_detail_window" class="easyui-window" title="统计详情" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%;padding:10px;height: 100%">
			<div class="easyui-layout" data-options="fit:true">
				<div region="center">
					<table id="_yjg_pctj_jgtj_detail_table" style="width:auto;"></table>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../webgis/init_layers.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../ywtj/yjg_pctj_list.js" charset="UTF-8"></script>
	</body>
</html>