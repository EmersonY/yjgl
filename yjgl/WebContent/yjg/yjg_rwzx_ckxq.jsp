<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib prefix="fns" uri="../tld/fns.tld" %>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
	</head>
	<body>
		<script type="text/javascript" src="../yjg/yjg_rwzx_ckxq.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="../css/sjgl.css">
   		<div id="_yjg_rwzx_sjdj_list_view_window" class="easyui-window" title="查看事件" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true">
			<div class="easyui-layout" data-options="fit:true">
				<div region="north" data-options="collapsible:false" id="_rwzx_view_north" style="padding: 5px;overflow:auto" title="从属主事件详情">
					<input type="hidden" id="_sjlx">
					<div style="padding: 5px;">
			        	<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_center_position_button">查看位置信息</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_center_czsb_button">查看上报附件</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_cz_before_button">查看处置前附件</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_cz_after_button">查看处置后附件</a>
  						<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_designate_button">认领详情</a>
						<c:if test="${fns:getAdminPath('rwzx_sjpd')}">
							<a id="_yjg_rwzx_list_designate_button" href="javascript:void(0);" class="easyui-linkbutton">事件派单</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_rlsj')}">
							<a id="_yjg_rwzx_list_access_designate_button" href="javascript:void(0);" class="easyui-linkbutton" >管辖</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_jjrl')}">
							<a id="_yjg_rwzx_list_refuse_designate_button"  href="javascript:void(0);" class="easyui-linkbutton" >非管辖</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_qrqz')}">
							<a id="_yjg_rwzx_list_access_claim_button" href="javascript:void(0);" class="easyui-linkbutton" >确认权责</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_frqz')}">
							<a id="_yjg_rwzx_list_refuse_claim_button" href="javascript:void(0);" class="easyui-linkbutton" >否认权责</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_ccpd')}">
							<a id="_yjg_rwzx_list_yesproperty_button" href="javascript:void(0);" class="easyui-linkbutton">重新派单</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_dd')}">
							<a id="_yjg_rwzx_list_fallback_button" href="javascript:void(0);" class="easyui-linkbutton">兜底</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_xccz')}">
							<a id="_yjg_rwzx_list_repair_button" href="javascript:void(0);" class="easyui-linkbutton">处置完成上报</a>
						</c:if>
						<c:if test="${fns:getAdminPath('rwzx_xcqr')}">
							<a id="_yjg_rwzx_list_comfirm_button" href="javascript:void(0);" class="easyui-linkbutton">现场确认</a>
						</c:if>
  					</div>
					<form action="" method="post" id="_yjg_rwzx_sjdj_list_view_form" >
						<input name="xzb" type="hidden" id="_yjg_rwzx_sjdj_list_view_xzb">
						<input name="yzb" type="hidden" id="_yjg_rwzx_sjdj_list_view_yzb">
						<table width="100%" class="table-view">
							<tr>
								<th>登记单号：</th>
								<td>
									<input name="sjdjdh" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_sjdjdh"/>
								</td>
								<th>信息来源：</th>
								<td>
									<input name="xxly" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_xxly"/>
								</td>
								<th>上报人姓名：</th>
								<td>
									<input name="sbrxm" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_sbrxm"/>
								</td>
								<th>上报人电话：</th>
								<td>
									<input name="sbrdh" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_sbrdh"/>
								</td>
							</tr>
							<tr>
								<th>井盖编号：</th>
								<td>
									<input name="jgid" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_jgid"/>
								</td> 
								<th>井盖类型：</th>
								<td>
									<input name="jglx" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_jglx"/>
								</td>
								<th>处理状态 ：</th>
								<td>
									<input name="sqzt" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_sqzt"/>
								</td>
								<th>严重级别：</th>
								<td>
									<input name="yzjb" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_yzjb"/>
								</td>
							</tr>
							<tr>
								<th>事件性质：</th>
								<td>
									<input name="sjlx" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_sjlx"/>
								</td>
								<th>行政区划：</th>
								<td>
									<input name="xzqh" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_xzqh"/>
								</td>
								<th>生成时间：</th>
								<td>
									<input name="scsj" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_scsj"/>
								</td>
								<th>结束时间：</th>
								<td>
									<input name="jssj" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_jssj"/>
								</td>
							<tr>
							<tr>
								<th>接件人：</th>
								<td>
									<input type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_jjr"/>
								</td>
								<th>所属道路：</th>
								<td>
									<input name="ssdl" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_sjdj_list_view_ssdl"/>
								</td>
								<th>更新人姓名：</th>
								<td>
									<input name="updator" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_updator"/>
								</td>
								<th>更新时间：</th>
								<td>
									<input name="updatetime" type="text" class="easyui-textbox" readonly="readonly" style="width:200px" data-options="required:false" id="_yjg_rwzx_sjdj_list_view_updatetime"/>
								</td>
							</tr>
							<tr>
								<th>基本情况：</th>
								<td colspan="3">
									<input name="bz" type="text" class="easyui-textbox" readonly="readonly" style="width:80%;height: 100px;" data-options="multiline:true,required:false" id="_yjg_rwzx_sjdj_list_view_bz"/>
								</td>
							</tr>
							<tr>
								<th>位置描述：</th>
								<td colspan="3">
									<input type="text" class="easyui-textbox" readonly="readonly" name="wzms" style="width:80%;height: 100px;" data-options="multiline:true,required:false" id="_yjg_rwzx_sjdj_list_view_wzms"/>
								</td>
							</tr>
							<tr>
								<th>接件备注：</th>
								<td colspan="3">
									<input type="text" class="easyui-textbox" readonly="readonly" style="width:80%;height: 100px;" data-options="multiline:true,required:false" id="_yjg_rwzx_sjdj_list_view_jjbz"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div region="center" id="_rwzx_view_center" style="padding: 5px;overflow:auto" title="从属子事件">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'north',border:false" style="height: 45px;overflow:auto">
							<div style="padding: 5px;">
						        <a href="#" class="easyui-linkbutton l-btn-danger" id="_yjg_rwzx_sjdj_list_view_center_separate_button">脱离事件</a>
			  				</div>
						</div>
						<div data-options="region:'center',fit:true,border:false" style="padding: 5px;overflow:auto">
							<table id="_yjg_rwzx_sjdj_list_view_center_table" data-options="fit:true,border:false"></table>
						</div>
					</div>
				</div>
				<div region="south" data-options="collapsible:false" id="_rwzx_view_south" style="padding: 5px;overflow:auto" title="事件处置进度">
					<div class="easyui-layout" data-options="fit:true,border:false">
						<div data-options="region:'north',border:false" style="height: 45px;overflow:auto">
							<div style="padding: 5px;">
								<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_east_view_button">查看详情</a>
					  			<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_east_viewAll_button">查看全部</a>
			  				</div>
						</div>
						<div data-options="region:'center',fit:true,border:false" style="padding: 5px;overflow:auto">
							<table id="_yjg_rwzx_sjcz_list_view_east_table" data-options="fit:true,border:false"></table>
						</div>
					</div>
				</div>
		 </div>
		 
		 <div id="_yjg_rwzx_sjdj_list_designate_window" class="easyui-window" title="派单用户" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:700px;padding:10px;height: 500px">
			<div class="easyui-tabs" id="_rwzx_tabs">  
		    	<div title="认领详情">
					<table id="_rwzx_designate_user" style="width:auto;"></table>
				</div>
				<div title="确认权责详情">
					<table id="_rwzx_assign_user" style="width:auto;"></table>
				</div>
			</div>
			<div region="center">
				<table id="" style="width:auto;"></table>
			</div>
		</div>
		
	    <div id="_yjg_rwzx_sjcz_list_view_window" class="easyui-window" title="查看处置进度" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true">
			<form action="" method="post" id="_yjg_rwzx_sjcz_list_view_form" >
				<table width="100%" class="table-view">
					<tr>
						<th>操作人：</th>
						<td>
							<input name="czr" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_rwzx_sjcz_list_view_czr"/>
						</td>
						<th>处置时间：</th>
						<td>
							<input name="czsj" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_yjg_rwzx_sjcz_list_view_czsj"/>
						</td>
					</tr>
					<tr>
						<th>处置详情：</th>
						<td colspan="3">
							<input name="czgcms" type="text" class="easyui-textbox" style="width:80%;height:200px" data-options="required:false" id="_yjg_rwzx_sjcz_list_view_czgcms"/>
						</td>
					</tr>
					<tr>
						<th>处置备注：</th>
						<td colspan="3">
							<input name="bz" type="text" class="easyui-textbox" style="width:80%;height:200px" data-options="required:false" id="_yjg_rwzx_sjcz_list_view_bz"/>
						</td>
					</tr>
				</table>
				<div style="padding: 5px;text-align: center"">
					<a href="#" class="easyui-linkbutton" id="_yjg_rwzx_sjdj_list_view_enclosure_button">查看附件</a>
		  			<a href="#" class="easyui-linkbutton" class="easyui-linkbutton l-btn-warm" id="_yjg_rwzx_sjdj_list_view_cancel_button">取消</a>
			  	</div>
			</form>
		</div>
		
	</body>
</html>