<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>节假日安排</title>
		<sec:csrfMetaTags/>
	</head>
		
	<body>
		<script type="text/javascript" src="../console/base_holidays.js" charset="UTF-8"></script>
	
		<div id='loading' class="_loading"></div>
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">节假日安排</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form action="" id="_base_holidays_search_form">
				       	<input value="2018" id="_base_holidays_search_year" type="text" size="6" class="easyui-numberspinner" style="width:200px;height: 34px;" label="年份：" data-options="labelWidth:80,labelAlign:'right',min:2017,max:2099,editable:false">
			        	<select id="_base_holidays_search_holiday" class="easyui-combobox" label="节假日：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:80" style="width:200px;height: 34px;"></select>
						<a id="_base_holidays_search_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查询</a>
						<a id="_base_holidays_reset_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">重置</a>
			        </form>
				</div>
				<div style="padding: 5px;">
					<a id="_base_holidays_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">新增</a>
					<a id="_base_holidays_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑</a>
					<a id="_base_holidays_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看</a>
					<a id="_base_holidays_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除</a>
				</div>
			</div>
			<div region="center" data-options="border:false">
				<table id="_base_holidays_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		<div id="_base_holidays_add_window" class="easyui-window" title="新增" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_base_holidays_add_form" >
        		<sec:csrfInput/>
				<div style="float: left;margin-bottom: 5px">
				    <input name="year" id="_base_holidays_add_year" type="text" size="6" class="easyui-numberspinner" style="width:200px;height: 34px;" label="年份：" data-options="labelWidth:80,labelAlign:'right',min:2017,max:2099">
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
			        <select name="holidayname" id="_base_holidays_add_holiday" class="easyui-combobox" label="节假日：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:80" style="width:200px;height: 34px;"></select>
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="holidaydate" id="_base_holidays_add_holidaydate" label="日期：" prompt="请输入日期", data-options="labelWidth:80,labelAlign:'right',editable:false,required:true" style="width:200px;height: 34px;" class="easyui-datebox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="holidaytype" id="_base_holidays_add_holidaytype" label="类型 ：" prompt="请输入类型 ", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:false" style="width:200px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-left: 20px;margin-bottom: 5px">
					<select name="isholiday" id="_base_holidays_add_isholiday" class="easyui-combobox" label="节假日当天：" data-options="labelWidth:100,labelAlign:'right',editable:false,panelHeight:60" style="width:220px;height: 34px;"></select>
        		</div>
				<div style="float: left;margin-bottom: 5px">
	        		<input name="remark" id="_base_holidays_add_remark" label="备注：" prompt="请输入备注", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
        		 <div style="float: left;padding: 2px;text-align: center;width:450px">
		        	<a id="_base_holidays_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_holidays_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
	    
	    <div id="_base_holidays_edit_window" class="easyui-window" title="编辑" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
			<form action="" method="post" id="_base_holidays_edit_form" >
        		<sec:csrfInput/>
        		<input name="holidaysid" id="_base_holidays_edit_holidaysid" type="hidden" >
        		<div style="float: left;margin-bottom: 5px">
				    <input name="year" id="_base_holidays_edit_year" type="text" size="6" class="easyui-numberspinner" style="width:200px;height: 34px;" label="年份：" data-options="labelWidth:80,labelAlign:'right',min:2017,max:2099">
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
			        <select name="holidayname" id="_base_holidays_edit_holidayname" class="easyui-combobox" label="节假日：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:80" style="width:200px;height: 34px;"></select>
        		</div>
        		<div style="float: left;margin-bottom: 5px">
	        		<input name="holidaydate" id="_base_holidays_edit_holidaydate" label="日期：" prompt="请输入日期", data-options="labelWidth:80,labelAlign:'right',editable:false,required:true" style="width:200px;height: 34px;" class="easyui-datebox" >
	        	</div>
	        	<div style="float: left;margin-left: 20px;margin-bottom: 5px">
	        		<input name="holidaytype" id="_base_holidays_edit_holidaytype" label="类型 ：" prompt="请输入类型 ", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,20]',required:false" style="width:200px;height: 34px;" class="easyui-textbox" >
        		</div>
        		<div style="float: left;margin-left: 20px;margin-bottom: 5px">
					<select name="isholiday" id="_base_holidays_edit_isholiday" class="easyui-combobox" label="节假日当天：" data-options="labelWidth:100,labelAlign:'right',editable:false,panelHeight:60" style="width:220px;height: 34px;"></select>
        		</div>
				<div style="float: left;margin-bottom: 5px">
	        		<input name="remark" id="_base_holidays_edit_remark" label="备注：" prompt="请输入备注", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
	        	</div>
        		 <div style="float: left;padding: 2px;text-align: center;width:450px">
		        	<a id="_base_holidays_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存</a>
		        	<a id="_base_holidays_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
		        </div>
	        </form>
	    </div>
		
	    <div id="_base_holidays_view_window" class="easyui-window" title="查看" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:600px;padding:10px;">
			<form action="" method="post" id="_base_holidays_view_form" >
				<table width="100%" class="table-view">
					<tr>
						<th>节假日名称：</th>
						<td>
							<input name="holidaytype" class="easyui-combobox" readonly="readonly" data-options="labelWidth:100,labelAlign:'right',editable:false,panelHeight:60" style="width:80%" data-options="required:false" id="_base_holidays_view_holidayname"/>
						</td>
						<th>年份：</th>
						<td>
							<input name="year" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_year"/>
						</td>
					</tr>
					<tr>
						<th>是否节假日当天</th>
						<td>
							<input name="isholiday" class="easyui-combobox" readonly="readonly" data-options="labelWidth:100,labelAlign:'right',editable:false,panelHeight:60" style="width:80%" data-options="required:false" id="_base_holidays_view_isholiday"/>
						</td>
						<th>节假日日期：</th>
						<td colspan="3">
							<input name="holidaydate" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_holidaydate"/>
						</td>
					</tr>
					<tr>
						<th>节假日类型</th>
						<td>
							<input name="holidaytype" class="easyui-combobox" readonly="readonly" data-options="labelWidth:100,labelAlign:'right',editable:false,panelHeight:60" style="width:80%" data-options="required:false" id="_base_holidays_view_holidaytype"/>
						</td>
						<th>备注：</th>
						<td>
							<input name="remark" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_remark"/>
						</td>
					</tr>
					<tr>
						<th>创建人：</th>
						<td>
							<input name="createperson" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_createperson"/>
						</td>
						<th>创建时间：</th>
						<td>
							<input name="createtime" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_createtime"/>
						</td>
					</tr>
					<tr>
						<th>更新人：</th>
						<td>
							<input name="updateperson" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_updateperson"/>
						</td>
						<th>更新时间：</th>
						<td>
							<input name="updatetime" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_base_holidays_view_updatetime"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="_edit_window" class="easyui-window" data-options="width:550,draggable:true,shadow:true,modal:true,closed:true,closable:true,minimizable:false,maximizable:false,collapsible:false">
			<form action="" method="post" id="_edit_form">
				<table width="99%" class="table-edit">
					<tr>
						<th width="100px">
							<i class="labelrequired">*</i>&nbsp;年份：
						</th>
						<td width="170px">
							<input type="text" size="6" id="_edit_year" name="e.year" class = "easyui-numberspinner" data-options="min:2000,max:2099"/>
						</td>
						<th width="90px">
							<i class="labelrequired">*</i>&nbsp;节假日：
						</th>
						<td width="150px">
							<input type="text" size="15" name="e.holidayname" id="_edit_holidayname"/>
						</td>
					</tr>
		           <tr>
						<th>
							<i  class="labelrequired">*</i>&nbsp;日期：
						</th>
						<td>
							<input type="text" size="10" name="e.holidaydate" id="_edit_holidaydate" class="easyui-datebox"/>
						</td>
						<th>
							<i  class="labelrequired">*</i>&nbsp;调休类型：
						</th>
						<td>
							<input type="radio" size="10" name="e.holidaytype" value="1"/>休假
							<input type="radio" size="10" name="e.holidaytype" value="2"/>上班
						</td>
					</tr>
					<tr>
						<th>
							<i  class="labelrequired">*</i>&nbsp;节假日当天：
						</th>
						<td>
							<input type="radio" size="10" name="e.isholiday" value="1"/>是
							<input type="radio" size="10" name="e.isholiday" value="0"/>否
						</td>
					</tr>
					<tr>
						<th>
							备注：
						</th>
						<td colspan="3">
							<textarea rows="3" cols="50" name="e.remark"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;">
							<input type="hidden" name = "e.id"/>
							<input type="hidden" name = "e.createperson"/>
							<input type="hidden" name = "e.createtime"/>
							<input type="hidden" name = "e.updateperson"/>
							<input type="hidden" name = "e.updatetime"/>
							<a id="_edit_save" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="_edit_cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">关闭</a> 
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="_view_window" class="easyui-window" data-options="width:600,draggable:true,shadow:true,modal:true,closed:true,closable:true,minimizable:false,maximizable:false,collapsible:false">
			<table width="99%" class="table-view">
				<tr>
					<th width="100px">年份：</th>
					<td id = "_view_year" width="170px"></td>
					<th width="90px">节假日：</th>
					<td  id="_view_holidayname" width="150px"></td>
			   </tr>
	           <tr>
					<th>日期：</th>
					<td id = "_view_holidaydate"></td>
					<th>调休类型：</th>
					<td id = "_view_holidaytype"></td>
				</tr>
				<tr>
					<th>节假日当天：</th>
					<td id = "_view_isholiday"></td>
				</tr>
				<tr>
					<th>备注：</th>
					<td id = "_view_remark" colspan="3"  style="height: 120px; vertical-align: top;"></td>
				</tr>
			</table>
			<div align="center" style="height: 30px">
				<a id="_view_cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">关闭</a> 
			</div>
		</div>
		
	</body>
</html>