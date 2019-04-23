<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<head>
		<title>厦门市政窨井管理信息系统</title>
		<sec:csrfMetaTags/>
		<link rel="stylesheet" id="_skin" type="text/css" href="../easyui/themes/material/easyui.css">
		<link rel="stylesheet" href="http://syseng.kingtopinfo.com:9081/arcgis_js_3.14/esri/css/esri.css">
		<link rel="stylesheet" type="text/css" href="../css/webgis.css">
		<link rel="stylesheet" type="text/css" href="../css/claro.css">
		<link rel="stylesheet" type="text/css" href="../css/sjgl.css">
		<link rel="stylesheet" href="../js/jquery-ui/jquery-ui.min.css">
		<link rel="stylesheet" href="index.css">
		<%@ include file="/base/common.jspf"%>
		<link rel="stylesheet" href="../webgis/baseSearch/baseSearch.css">
		<script type="text/javascript" src="../js/basepara.js" charset="UTF-8"></script>
		<script type="text/javascript" src="init_layers.js" charset="UTF-8"></script>
		<script type="text/javascript" src="dojoconfig.js"charset="utf-8"></script>
		<script type="text/javascript" src="../js/easyui.common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../js/webgis.common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../js/util.common.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../js/baseType.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>	
		<script type="text/javascript" src="../js/jquery-ui/jquery-ui.js"></script>
		<script src="http://syseng.kingtopinfo.com:9081/arcgis_js_3.14/init.js"></script>
		<script type="text/javascript" src="index.js" charset="UTF-8"></script>
	</head>
	<body style="width:100%;height:100%;">
	
		<div id="layout_north">
			<div id="layout_north_login"></div>
        	<div id="layout_north_login_main">
				<div class="layout_north_toolbar">
        			<img id="rangeSearch" class="toolbutton" title="模糊查询" alt="模糊查询" src="../images/webgis/xxcx.png" >
        		</div>
        		<div class="layout_north_toolbar">
        			<img id="map_toolbar" class="toolbutton" title="地图工具条" alt="地图工具条" src="../images/webgis/dtgjt.png" >
        		</div>
        		<div class="layout_north_toolbar">
        			<img id="EditLayer" class="toolbutton" title="地图编辑" alt="地图编辑" src="../images/webgis/sjjc.png" >
        		</div>
        		<div class="layout_north_toolbar">
        			<img id="jgxxEdit" class="toolbutton" title="井盖入图" alt="井盖入图" src="../images/webgis/dlsj.png" >
        		</div>
<!--         		<div class="layout_north_toolbar"> -->
<!--         			<img id="saveAllGraphics" class="toolbutton" title="保存工作空间" alt="保存工作空间" src="../images/webgis/save.png" > -->
<!--         		</div> -->
        		<div class="layout_north_toolbar">
        			<img id="glyrk" class="toolbutton" title="管理员入口" alt="管理员入口" src="../images/webgis/glyrk.png" >
        		</div>
        	</div>
		</div>
		<div class="partition_m">
			<div id="mapDiv" style="width:100%;height:100%;">
				<div id="swipeDiv"></div>
				<label id="BasemapToggle2"   style="position:absolute; z-index:100;right:62px; top:2px;"></label>
			</div>
		</div>
		
		<div id="_baseSearch" class="clearfix" style="z-index:99;position:absolute;top:90px;left:50px;">
			<div id="searchbox-container">
				<div id="sole-searchbox-content" class="searchbox-content">
					<input id="_baseSearch_addname" class="searchbox-content-common" name="word" autocomplete="off" maxlength="19" placeholder="搜编码、搜类型、搜道路" value="" type="text">
					<div style="display: none;" id="_baseSearch_clear" class="input-clear" title="清空"></div>
					<div data-tooltip="2" id="_baseSearch_clearLayer" class="searchbox-content-button right-button cancel-button loading-button" title="清除图层"></div>
					<div id="baseSearch_result">
<!-- 						<ul class="ui-autocomplete ui-front ui-menu ui-widget ui-widget-content" id="ui-id-1" tabindex="0" style="display: none; width: 327px; top: 38px; left: 0px;"> -->
<!-- 						</ul> -->
					</div>
				</div>
			</div>
<!-- 			<div id="baseSearch_account" title="门牌号查询"><input type="checkbox" id="baseSearch_houseNumber" style="position:absolute;right:60px; top:8px; width:15px;height:15px;" ></div> -->
			<button data-tooltip="1" id="_baseSearch_go" data-title="搜索"></button>
		</div>
		
		<div id="_toolbar" class="toolscontainer" style="position:absolute;top:80px;right:60px;display:none;">
			<div class="ui3-control-wrap clearfixs" id="ui3_control_wrap">        
				<div class="left float-l">                       
					<div class="trafficopt"  id="basesearch_point">                
						<span id="traffic_control" class="last traffic"></span><i>圆查询</i>            
					</div>             
					<div class="trafficopt"  id="basesearch_rectangle">                
						<span id="traffic_control" class="last traffic"></span><i>拉框查询</i>            
					</div>  
					<div class="trafficopt"  id="basesearch_polygon">                
						<span id="traffic_control" class="last traffic"></span><i>面查询</i>            
					</div>  
					<div class="trafficopt"  id="basesearch_clear">                
						<span id="traffic_control" class="last traffic"></span><i>清除</i>            
					</div>             
				</div>
			</div>
		</div>
		
		<div id="layer_win" style="position:fixed;top:0px;right:-310px;width:300px;height:100%;background-color:#ffffff;z-index:9998;box-shadow: 0 -1px 24px rgba(0,0,0,0.4);">
			<div id="_hide_layer_win" style="background: url('../images/webgis/index/settings.png') no-repeat 0 -50px;position: absolute;top: 20px;width: 24px;left: 10px;height: 25px;cursor:pointer"></div>
			
			<div style="background-color:#dddddd;margin-top:60px;height:2px;"></div>
			<div class="layer_control" region="north" data-options="border:false" style="height:60px;padding-top: 5px;padding-left: 5px;">
					<img style="cursor:pointer;" alt="置顶" src="../images/loadLayer/top.png" id="topLayer">
					<img style="cursor:pointer;" alt="上移" src="../images/loadLayer/up.png" id="upLayer">
					<img style="cursor:pointer;" alt="下移" src="../images/loadLayer/down.png" id="downLayer">
					<img style="cursor:pointer;" alt="置底" src="../images/loadLayer/bottom.png" id="bottomLayer">
<!-- 					<img style="cursor:pointer;" alt="删除" src="../images/loadLayer/delLayer.png" id="delLayer"> -->
			</div>
<!-- 			<div id="jc"></div> -->
			<table id="yjz"></table>
		</div>
		
		<div id="editorDiv" style="top:200px;left:100px;"></div>
		
		<div id="_base_layer_view_window" style="display:none;height:510px">
			<div style="width:99.91%;height:350px;">
				<table id="search_detailTable"  class="easyui-propertygrid" style="width:99.91%;height:99%;display:none;" data-options="fit:true,showGroup:true,showHeader:false,">
	       		</table>
       		</div>
       		<div style="width:99.91%;height:100px;">
       			<div id= "_base_layer_view_nbdiv" style="float:left;width:28%;height:99%;padding-left: 30px;padding-top: 20px;"><img alt="未上传内部照片" id="_base_layer_view_nb" width="80%" height="80%" src=""></div>
       			<div id= "_base_layer_view_jjdiv" style="float:left;width:28%;height:99%;padding-left: 10px;padding-top: 20px;"><img alt="未上传近景照片" id="_base_layer_view_jj" width="80%" height="80%" src=""></div>
       			<div id= "_base_layer_view_yjdiv" style="float:left;width:28%;height:99%;padding-left: 10px;padding-top: 20px;"><img alt="未上传远景照片" id="_base_layer_view_yj" width="80%" height="80%" src=""></div>
       		</div>
       		<div style="padding: 2px;text-align: center;">
       	 		<input type="hidden" id="_base_layer_edit_pid" name="_base_layer_edit_pid" />
       	 		<a id="_base_layer_view_last" href="javascript:;" class="easyui-linkbutton" >上一个</a>
	        	<a id="_base_layer_view_next" href="javascript:;" class="easyui-linkbutton" >下一个</a>
        	</div>
		</div>		
		
		<div id="_base_layer_edit_window" style="display:none;height:510px">
			<div style="width:99.91%;height:400px;">
				<table id="edit_detailTable"  class="easyui-propertygrid" style="width:99.91%;height:99%;display:none;" data-options="fit:true,showGroup:true,showHeader:false,">
	       		</table>
       		</div>
<!--        		<div style="width:99.91%;height:100px;"> -->
<!--        			<div style="float:left;width:50%;height:99%;left:10px;"><img alt="pic1" id="featureedit_img1" width="90%" height="80%" src="../images/jgpic/A1001-NB.JPG"></div> -->
<!--        			<div style="float:left;width:50%;height:99%;left:10px;"><img alt="pic2" id="featureedit_img2" width="90%" height="80%" src="../images/jgpic/A1002-JJ.JPG"></div> -->
<!--        		</div> -->
       		<div style="padding: 2px;text-align: center;">
       	 		<input type="hidden" id="_base_layer_edit_pid" name="_base_layer_edit_pid" />
	        	<a id="_base_layer_edit_save" href="javascript:;" class="easyui-linkbutton" >保存</a>
	        	<a id="_base_layer_edit_delete" href="javascript:;" class="easyui-linkbutton l-btn-warm" >删除</a>
        	</div>
		</div>		
		
		<div id="_base_layer_add_window" style="display:none;height:510px">
			<div style="width:99.91%;height:400px;">
				<table id="add_detailTable"  class="easyui-propertygrid" style="width:99.91%;height:99%;display:none;" data-options="fit:true,showGroup:true,showHeader:false,">
	       		</table>
       		</div>
<!--        		<div style="width:99.91%;height:100px;"> -->
<!--        			<div style="float:left;width:50%;height:99%;left:10px;"><img alt="pic1" id="featureedit_img1" width="90%" height="80%" src="../images/jgpic/A1001-NB.JPG"></div> -->
<!--        			<div style="float:left;width:50%;height:99%;left:10px;"><img alt="pic2" id="featureedit_img2" width="90%" height="80%" src="../images/jgpic/A1002-JJ.JPG"></div> -->
<!--        		</div> -->
       		<div style="padding: 2px;text-align: center;">
       	 		<input type="hidden" id="_base_layer_edit_pid" name="_base_layer_add_pid" />
	        	<a id="_base_layer_add_save" href="javascript:;" class="easyui-linkbutton" >增加</a>
	        	<a id="_base_layer_add_delete" href="javascript:;" class="easyui-linkbutton l-btn-warm" >删除</a>
        	</div>
		</div>		
		
		<div id="_image_zoom_container" style="z-index:9999;position:absolute;top:0px;left:0px;display:none;background-color:rgba(10, 10, 10, 0.52);">
			<span id="_image_zoom_close" style="z-index:9999;position:absolute;cursor:pointer;background-image:url('../images/loadLayer/del.png');width:20px;height:20px;top:0px;right:0px;background-color:rgba(128, 128, 128, 0.68);"></span>
			<img src="" id="_image_zoom_center"  style="z-index:9998;cursor:pointer;position:absolute;width:800px;height:800px;" />
		</div>
		
		<div id="_detaileLayer_win" class="easyui-tabs" style="z-index:101;position:absolute;width: 200px; height:500px;left:-200px;top:200px;background-color: #eeeeee;display:none;">	
			<div title="管理图层">
				<div id="_baseLayer_win"></div>
			</div>
		</div>
		<div id="_open_baseLayer_win" style="z-index:101;position:absolute;width: 48px;height: 48px;cursor:pointer;left:0px;top:200px;background-image: url('../images/webgis/_451.png');">
		</div>
		
		<div id="_base_layer_jgxx_window" style="display:none;height:530px">
			<div class="easyui-layout" data-options="fit:true">
				<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;height: 100px">
					<div style="padding: 5px;">
						<form action="" id="_base_layer_list_search_form">
					       	<input class="easyui-textbox" id="_base_layer_list_jgbh" label="井盖编号：" prompt="请输入井盖编号" data-options="labelWidth:90,labelAlign:'right'" style="width:220px;height: 34px;">	
							<input class="easyui-textbox" id="_base_layer_list_qsdw" label="权属单位：" prompt="请输入权属单位" data-options="labelWidth:90,labelAlign:'right'" style="width:220px;height: 34px;">	
					        <a href="#" class="easyui-linkbutton" id="_base_layer_list_search_button">查询</a>
					        <a href="#" class="easyui-linkbutton" id="_base_layer_list_reset_button">重置</a>
				        </form>
				    </div>
				    <div style="padding: 5px;">
						<a id="_base_layer_list_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">入图</a>
						<a id="_base_layer_list_addall_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">全部入图</a>
						<a id="_base_layer_list_location_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看位置</a>
						<a id="_base_layer_list_clear_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">清空</a>
<!-- 						<a id="_base_layer_list_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a> -->
					</div>
				</div>
				<div region="center">
					<table id="_base_layer_jgxx_list_datagrid" data-options="fit:true,border:false"></table>
				</div>
			</div>
		</div>
		
		<div id="_base_layer_rangeSearch_window" style="display:none;height:490px">
			<div class="easyui-layout" data-options="fit:true">
				<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;height: 480px">
					<div style="padding: 5px;">
						<form action="" id="_base_layer_rangeSearch_list_search_form">
							<select id="_base_layer_rangeSearch_search_jglx" class="easyui-combobox" label="井盖类型：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<select id="_base_layer_rangeSearch_search_sfzw" class="easyui-combobox" label="防坠网状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<br><br>
							<select id="_base_layer_rangeSearch_search_jgxz" class="easyui-combobox" label="井盖形状：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<select id="_base_layer_rangeSearch_search_jgcz" class="easyui-combobox" label="井盖材质：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<br><br>
							<select id="_base_layer_rangeSearch_search_sjlx" class="easyui-combobox" label="事件类型：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<select id="_base_layer_rangeSearch_search_ssdl" class="easyui-combobox" label="所属道路：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<br><br>
							<select id="_base_layer_rangeSearch_search_jgzt" class="easyui-combobox" label="井盖状态：" data-options="labelWidth:90,labelAlign:'right',editable:false,panelHeight:60" style="width:250px;height: 34px;"></select>
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_jgsl" label="井盖数量：" prompt="请输入井盖数量" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
							<br><br>
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_gldw" label="管理单位：" prompt="请输入管理单位" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_qsdw" label="权属单位：" prompt="请输入权属单位" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
							<br><br>
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_jngj" label="井内管径：" prompt="请输入井内管径" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_jggg" label="井盖规格：" prompt="请输入井盖规格" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
							<br><br>
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_jgbh" label="井盖编号：" prompt="请输入井盖编号" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">	
							<input class="easyui-textbox" id="_base_layer_rangeSearch_search_sbrxm" label="上报人姓名：" prompt="请输入上报人姓名" data-options="labelWidth:90,labelAlign:'right'" style="width:250px;height: 34px;">
							<br><br>
							<input class="easyui-datebox" id="_base_layer_rangeSearch_search_timeStart" label="开始时间：" prompt="请输入开始时间" data-options="labelWidth:90,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
					       	 
				        	<input class="easyui-datebox" id="_base_layer_rangeSearch_search_timeEnd" label="结束时间：" prompt="请输入结束时间" data-options="labelWidth:90,labelAlign:'right',editable:false" style="width:250px;height: 34px;">
					        <br><br>
					        <a href="#" class="easyui-linkbutton" style="margin-left: 180px" id="_base_layer_rangeSearch_search_button1">井盖查询</a>
					        <a href="#" class="easyui-linkbutton" id="_base_layer_rangeSearch_search_button2">事件查询</a>
					        <a href="#" class="easyui-linkbutton" id="_base_layer_rangeSearch_reset_button">重置</a>
			        	</form>
				    </div>
				</div>
			</div>
		</div>
		
		<div id="_webgis_jgxlgl_list_add_window" class="easyui-window" title="新增" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:false" style="width:700px;padding:10px;float: left">
			<form action="../YjgJgxxAction/add?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data" id="_webgis_jgxlgl_list_add_form" >
        		<sec:csrfInput/>
        		<div style="float: left;margin-bottom: 5px">
        			<input name="jgcz" id="_webgis_jgxlgl_list_add_jgcz" label="井盖材质：" prompt="请输入井盖材质", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,10]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
        			<input name="jgzt" id="_webgis_jgxlgl_list_add_jgzt" label="井盖状态：" prompt="请输入井盖状态", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,32]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-bottom: 5px">
        			<input name="jgxz" id="_webgis_jgxlgl_list_add_jgxz" label="井盖形状：" prompt="请输入井盖形状", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,10]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
        			<input name="sfzw" id="_webgis_jgxlgl_list_add_sfzw" label="防坠网状态：" data-options="labelWidth:110,labelAlign:'right',validType:'length[1,10]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-bottom: 5px">
        			<input name="ssdl" id="_webgis_jgxlgl_list_add_ssdl" label="所属道路：" prompt="请输入所属道路", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,50]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="dljssj" id="_webgis_jgxlgl_list_add_dljssj" label="道路建设时间：" prompt="请输入道路建设时间", data-options="labelWidth:110,labelAlign:'right',editable:false,required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-bottom: 5px">
	        		<input name="jngj" id="_webgis_jgxlgl_list_add_jngj" label="井内管径：" prompt="请输入井内管径", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,22]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
        			<input name="jgsl" id="_webgis_jgxlgl_list_add_jgsl" label="井盖数量：" prompt="请输入井盖数量", data-options="min:1,max:100,labelWidth:110,labelAlign:'right',validType:'length[1,32]',required:false" style="width:300px;height: 34px;" class="easyui-numberbox" >
	        	</div>
	        	<div style="float: left;margin-bottom: 5px">
        			<input name="js" id="_webgis_jgxlgl_list_add_js" label="井深(毫米)：" prompt="请输入井深", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,32]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
        			<input name="jggg" id="_webgis_jgxlgl_list_add_jggg" label="井盖规格：" prompt="请输入井盖规格", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,20]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-bottom: 5px">
        			<input name="gldw" id="_webgis_jgxlgl_list_add_gldw" label="管理单位：" prompt="请输入管理单位", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,100]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        	    <input name="qsdw" id="_webgis_jgxlgl_list_add_qsdw" label="权属单位：" prompt="请输入权属单位", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,100]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	       		<div style="float: left;margin-bottom: 5px;padding: 2px">
        			<input name="jglx" id="_webgis_jgxlgl_list_add_jglx" label="井盖类型：" prompt="请输入井盖类型", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,50]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
				</div>
		        <div style="float: left;margin-left: 17px;margin-bottom: 5px;padding: 2px">
					<span style="width:105px;display: inline-block;float: left;padding-right: 5px;text-align:right">内部图片：</span>
					<input class="_nb_choose_file" id="_nb_choose_file" name="nbImageFile" style="width:188px;height: 34px;">
		        </div>
	       		<div style="float: left;margin-bottom: 5px;padding: 2px">
					<span style="width:105px;display: inline-block;float: left;padding-right: 5px;text-align:right">近景图片：</span>
					<input class="_jj_choose_file" id="_jj_choose_file" name="jjImageFile" style="width:188px;height: 34px;">
		        </div>
		        <div style="float: left;margin-left: 17px;margin-bottom: 5px;padding: 2px">
					<span style="width:105px;display: inline-block;float: left;padding-right: 5px;text-align:right">远景图片：</span>
					<input class="_yj_choose_file" id="_yj_choose_file" name="yjImageFile" style="width:188px;height: 34px;">
	        	</div>
	        	<input type="hidden" name="xzb" id="_webgis_jgxlgl_list_add_xzb">
	       		<input type="hidden" name="yzb" id="_webgis_jgxlgl_list_add_yzb">
	       		<input type="hidden" name="xzqh" id="_webgis_jgxlgl_list_add_xzqh">
	       		<input type="hidden" name="rtzt" value="1" id="_webgis_jgxlgl_list_add_rtzt">
	        </form>
			<div style="padding: 2px;text-align: center;">
	        	<a id="_webgis_jgxlgl_list_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">添加</a>
	        	<a id="_webgis_jgxlgl_list_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		    </div>
	    </div>
	    
 	    <div id="_webgis_jgxlgl_list_edit_window" class="easyui-window" title="编辑" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:false" style="width:700px;padding:10px;">
			<form action="" method="post" id="_webgis_jgxlgl_list_edit_form" >
        		<sec:csrfInput/>
        		<div style="float: left;margin-bottom: 5px">
        			<input name="jgbh" id="_webgis_jgxlgl_list_edit_jgbh" label="井盖编号：" prompt="请输入井盖编号", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,50]',required:false,readonly:true" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="jngj" id="_webgis_jgxlgl_list_edit_jngj" label="井内管径：" prompt="请输入井内管径", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,22]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="jgsl" id="_webgis_jgxlgl_list_edit_jgsl" label="井盖数量：" prompt="请输入井盖数量", data-options="min:1,max:100,labelWidth:110,labelAlign:'right',validType:'length[1,22]',required:false" style="width:300px;height: 34px;" class="easyui-numberbox" >
        		</div>
        		<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="js" id="_webgis_jgxlgl_list_edit_js" label="井深(毫米)：" prompt="请输入井深", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,22]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="jgzt" id="_webgis_jgxlgl_list_edit_jgzt" label="井盖状态：" prompt="请输入井盖状态", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,32]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="jggg" id="_webgis_jgxlgl_list_edit_jggg" label="井盖规格：" prompt="请输入井盖规格", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,20]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-bottom: 5px">
        			<input name="jglx" id="_webgis_jgxlgl_list_edit_jglx" label="井盖类型：" data-options="labelWidth:110,labelAlign:'right',validType:'length[1,50]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="sfzw" id="_webgis_jgxlgl_list_edit_sfzw" label="防坠网状态：" data-options="labelWidth:110,labelAlign:'right',validType:'length[1,10]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="jgxz" id="_webgis_jgxlgl_list_edit_jgxz" label="井盖形状：" prompt="请输入井盖形状", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,10]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="jgcz" id="_webgis_jgxlgl_list_edit_jgcz" label="井盖材质：" prompt="请输入井盖材质", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,10]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="qsdw" id="_webgis_jgxlgl_list_edit_qsdw" label="权属单位：" prompt="请输入权属单位", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,100]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="gldw" id="_webgis_jgxlgl_list_edit_gldw" label="管理单位：" prompt="请输入管理单位", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,100]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="ssdl" id="_webgis_jgxlgl_list_edit_ssdl" label="所属道路：" prompt="请输入所属道路", data-options="labelWidth:110,labelAlign:'right',validType:'length[1,50]',required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="dljssj" id="_webgis_jgxlgl_list_edit_dljssj" label="道路建设时间：" prompt="请输入道路建设时间", data-options="labelWidth:110,labelAlign:'right',editable:false,required:false" style="width:300px;height: 34px;" class="easyui-textbox" >
        		</div>
        	</form>
        	<div class="apply_footer_img">
				<span class="apply_footer_select">
					<span style="width:105px;display: inline-block;float: left;padding-right: 5px;text-align:right">井盖内部图片：</span>
					<form id="apply_footer_upload_edit_webgis_nb_form" enctype="multipart/form-data" method="POST">
						<span class="apply_footer_upload flex">
				    		<span class="bg flex_item">
		                    	<img width="150" onclick="$(this).next().click()" height="180" style="display: block; width: 100%; height: 100%;">
		        				<input style="display: none" type="file" id="_choose_edit_file" name="nbImageFile" onchange="FeatureEdit.insertImg(this,1)">
		        				<input type="text" id="_webgis_jgxlgl_list_edit_jgbhnb" name="jgbh">
		        				<input type="text" name="type" value="1">
	                		</span>
	            		</span>
 				       	<div class="apply_footer_upload_edit_webgis_nb flex">
					    	<div class="bg flex_item">
		                		<img id="_nb_edit_img" onclick="FeatureEdit.deleteImg(this,1)" width="70" height="70" style="display: block;background-size:cover;">
							</div>
						</div>
            		</form>
            		<script type="text/x-jsrender" id="_choose_edit_nb_render">
						<span class="apply_footer_upload_edit_webgis_nb flex">
							<span class="bg flex_item">
	           					<img id="_nb_edit_img" onclick="FeatureEdit.deleteImg(this,1)" src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
	            			</span>
						</span>
					</script>
				</span>
			</div>
			<div class="apply_footer_img">
				<span class="apply_footer_select">
					<span style="width:105px;display: inline-block;float: left;padding-right: 5px;text-align:right">井盖近景图片：</span>
					<form id="apply_footer_upload_edit_webgis_jj_form" enctype="multipart/form-data" method="POST">
						<span class="apply_footer_upload flex">
				    		<span class="bg flex_item">
		                    	<img width="150" onclick="$(this).next().click()" height="180" style="display: block; width: 100%; height: 100%;">
		        				<input style="display: none" type="file" id="_choose_edit_file" name="jjImageFile" onchange="FeatureEdit.insertImg(this,2)">
		        				<input type="text" id="_webgis_jgxlgl_list_edit_jgbhjj" name="jgbh" value="">
		        				<input type="text" name="type" value="2">
	                		</span>
	            		</span>
 				       	<div class="apply_footer_upload_edit_webgis_jj flex">
					    	<div class="bg flex_item">
		                		<img id="_jj_edit_img" onclick="FeatureEdit.deleteImg(this,2)" width="70" height="70" style="display: block;background-size:cover;">
							</div>
						</div>
            		</form>
            		<script type="text/x-jsrender" id="_choose_edit_jj_render">
						<span class="apply_footer_upload_edit_webgis_jj flex">
							<span class="bg flex_item">
	           					<img id="_jj_edit_img" onclick="FeatureEdit.deleteImg(this,2)" src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
	            			</span>
						</span>
					</script>
				</span>
			</div>
			<div class="apply_footer_img">
				<span class="apply_footer_select">
					<span style="width:105px;display: inline-block;float: left;padding-right: 5px;text-align:right">井盖远景图片：</span>
					<form id="apply_footer_upload_edit_webgis_yj_form" enctype="multipart/form-data" method="POST">
						<span class="apply_footer_upload flex">
				    		<span class="bg flex_item">
		                    	<img width="150" onclick="$(this).next().click()" height="180" style="display: block; width: 100%; height: 100%;">
		        				<input style="display: none" type="file" id="_choose_edit_file" name="yjImageFile" onchange="FeatureEdit.insertImg(this,3)">
		        				<input type="text" id="_webgis_jgxlgl_list_edit_jgbhyj" name="jgbh" >
		        				<input type="text" name="type" value="3">
	                		</span>
	            		</span>
 				       	<div class="apply_footer_upload_edit_webgis_yj flex">
					    	<div class="bg flex_item">
		                		<img id="_yj_edit_img" onclick="FeatureEdit.deleteImg(this,3)" width="70" height="70" style="display: block;background-size:cover;">
							</div>
						</div>
            		</form>
            		<script type="text/x-jsrender" id="_choose_edit_yj_render">
						<span class="apply_footer_upload_edit_webgis_yj flex">
							<span class="bg flex_item">
	           					<img id="_yj_edit_img" onclick="FeatureEdit.deleteImg(this,3)" src="{{:filePath}}" width="70" height="70" style="display: block;background-size:cover;">
	            			</span>
						</span>
					</script>
				</span>
			</div>
        	<div style="padding: 2px;">
        		<input type="hidden" name="xzb" id="_webgis_jgxlgl_list_edit_xzb"  >
        		<input type="hidden" name="yzb" id="_webgis_jgxlgl_list_edit_yzb"  >
        		<input type="hidden" name="xzqh" id="_webgis_jgxlgl_list_edit_xzqh"  >
        		<input type="hidden" name="rtzt" value="1" id="_webgis_jgxlgl_list_edit_rtzt">
        	</div>
	        <div style="padding: 2px;text-align: center;">
	        	<a id="_webgis_jgxlgl_list_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">更新</a>
	        	<a id="_webgis_jgxlgl_list_edit_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">删除</a>
	        </div>
	    </div>
		
	</body>
</html>