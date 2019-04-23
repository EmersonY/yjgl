<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 

<!DOCTYPE html>
<html>
	<head>
		<title>备忘录</title>
	</head>

<body>
	<div id='loading' class="_loading"></div>

	<input id="_bwlDateHidden" type="hidden">
 	<div id="q0" style="text-align: center;float: left;border-right:  1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>日</span></div>
	<div id="q1" style="text-align: center;float: left;border-right: 1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>一</span></div>
	<div id="q2" style="text-align: center;float: left;border-right: 1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>二</span></div>
	<div id="q3" style="text-align: center;float: left;border-right: 1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>三</span></div>
	<div id="q4" style="text-align: center;float: left;border-right: 1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>四</span></div>
	<div id="q5" style="text-align: center;float: left;border-right: 1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>五</span></div>
	<div id="q6" style="text-align: center;float: left;border-right: 1px solid #ddd"><span style='font-size: 18px;color: red;font-weight: bold;'>六</span></div>
	<div id="p00" style="overflow: hidden;"></div>
	<div id="p10" style="overflow: hidden;"></div>
	<div id="p20" style="overflow: hidden;"></div>
	<div id="p30" style="overflow: hidden;"></div>
	<div id="p40" style="overflow: hidden;"></div>
	<div id="p50" style="overflow: hidden;"></div>
	<div id="p60" style="overflow: hidden;"></div>
	<div id="p01" style="overflow: hidden;"></div>
	<div id="p11" style="overflow: hidden;"></div>
	<div id="p21" style="overflow: hidden;"></div>
	<div id="p31" style="overflow: hidden;"></div>
	<div id="p41" style="overflow: hidden;"></div>
	<div id="p51" style="overflow: hidden;"></div>
	<div id="p61" style="overflow: hidden;"></div>
	<div id="p02" style="overflow: hidden;"></div>
	<div id="p12" style="overflow: hidden;"></div>
	<div id="p22" style="overflow: hidden;"></div>
	<div id="p32" style="overflow: hidden;"></div>
	<div id="p42" style="overflow: hidden;"></div>
	<div id="p52" style="overflow: hidden;"></div>
	<div id="p62" style="overflow: hidden;"></div>
	<div id="p03" style="overflow: hidden;"></div>
	<div id="p13" style="overflow: hidden;"></div>
	<div id="p23" style="overflow: hidden;"></div>
	<div id="p33" style="overflow: hidden;"></div>
	<div id="p43" style="overflow: hidden;"></div>
	<div id="p53" style="overflow: hidden;"></div>
	<div id="p63" style="overflow: hidden;"></div>
	<div id="p04" style="overflow: hidden;"></div>
	<div id="p14" style="overflow: hidden;"></div>
	<div id="p24" style="overflow: hidden;"></div>
	<div id="p34" style="overflow: hidden;"></div>
	<div id="p44" style="overflow: hidden;"></div>
	<div id="p54" style="overflow: hidden;"></div>
	<div id="p64" style="overflow: hidden;"></div> 
 	<div id="but" style="text-align: center;float:left;">
		<a id="_pre_month" href="javascript:void(0);" class="easyui-linkbutton" data-options="">上月</a> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a id="_next_month" href="javascript:void(0);" class="easyui-linkbutton" data-options="">下月</a> 
	</div> 
	
	<div id="_xtgl_bwl_list_window" class="easyui-window" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:100%;height:100%;">
		<div class="easyui-layout" data-options="fit:true">
			<div region="north" style="background-color: #FAFAFA;padding: 5px;overflow:hidden;height:150px">
				<fieldset style="border: none;border-top: 1px solid #e2e2e2;">
					<legend style="margin-left: 20px;padding: 0 10px;font-size: 20px;font-weight: 300;">备忘录</legend>
				</fieldset>
				<div style="padding: 5px;">
					<form id="_xtgl_bwl_search_form">
				       	<input class="easyui-textbox" id="xtgl_bwl_bwlnr" label="内容：" prompt="请输入内容" data-options="labelWidth:50,labelAlign:'right'" style="width:250px;height: 34px;">
				       	<input class="easyui-textbox" id="xtgl_bwl_bwlwz" label="地点：" prompt="请输入地点" data-options="labelWidth:50,labelAlign:'right'" style="width:250px;height: 34px;">
				        <a href="#" class="easyui-linkbutton" id="_xtgl_bwl_search_button">查询</a>
				        <a href="#" class="easyui-linkbutton" id="_xtgl_bwl_reset_button">重置</a>
			        </form>
			    </div>
			    <div style="padding: 5px;">
					<a id="_xtgl_bwl_add_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">新增备忘录</a>
					<a id="_xtgl_bwl_edit_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">编辑备忘录</a>
					<a id="_xtgj_bwl_view_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">查看备忘录</a>
					<a id="_xtgl_bwl_read_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">已读</a>
					<a id="_xtgl_bwl_delete_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-danger" data-options="">删除备忘录</a>
				</div>
			</div>
			<div region="center">
				<table id="_xtgj_bwl_table" data-options="fit:true,border:false"></table>
			</div>
		</div>
	</div>
	
	<div id="_xtgl_bwl_add_window" class="easyui-window" title="新增备忘录" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
		<form action="" method="post" id="_xtgl_bwl_add_form" >
       		<sec:csrfInput/>
       		<div style="padding: 2px;">
        		<input name="bwlbt" id="_xtgl_bwl_add_bwlbt" label="标题：" prompt="请输入标题", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:true" style="width:400px;height: 60px;" class="easyui-textbox" >
        	</div>
			<div style="padding: 2px;">
        		<input name="bwlnr" id="_xtgl_bwl_add_bwlnr" label="内容：" prompt="请输入内容", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 100px;" class="easyui-textbox" >
        	</div>
			<div style="padding: 2px;">
        		<input name="bwlwz" id="_xtgl_bwl_add_bwlwz" label="地点：" prompt="请输入地点", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
        	</div>
			<div style="padding: 2px;">
        		<input name="bwldate" id="_xtgl_bwl_add_bwldate" label="时间：" prompt="请输入时间", data-options="labelWidth:80,labelAlign:'right',editable:false,required:true" style="width:400px;height: 34px;" class="easyui-datetimebox"y>
        	</div>
			<div style="padding: 2px;">
				<select id="_xtgl_bwl_add_bwljb" name="bwljb" class="easyui-combobox" label="级别：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:80" style="width:400px;height: 34px;">
				     <option value="一级">一级</option>
				     <option value="二级">二级</option>
				     <option value="三级">三级</option>
				</select>
        	</div>
	        <div style="padding: 2px;text-align: center;">
	        	<a id="_xtgl_bwl_add_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存备忘录</a>
	        	<a id="_xtgl_bwl_add_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
	        </div>
        </form>
    </div>
    
    <div id="_xtgl_bwl_edit_window" class="easyui-window" title="编辑备忘录" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:500px;padding:10px;">
		<form action="" method="post" id="_xtgl_bwl_edit_form" >
       		<sec:csrfInput/>
       		<div style="padding: 2px;">
        		<input name="bwlbt" id="_xtgl_bwl_edit_bwlbt" label="标题：" prompt="请输入标题", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:true" style="width:400px;height: 60px;" class="easyui-textbox" >
        	</div>
	        <div style="padding: 2px;">
        		<input name="bwlnr" id="_xtgl_bwl_edit_bwlnr" label="内容：" prompt="请输入内容", data-options="multiline:true,labelWidth:80,labelAlign:'right',validType:'length[1,2000]',required:false" style="width:400px;height: 60px;" class="easyui-textbox" >
        	</div>
	        <div style="padding: 2px;">
        		<input name="bwlwz" id="_xtgl_bwl_edit_bwlwz" label="地点：" prompt="请输入地点", data-options="labelWidth:80,labelAlign:'right',validType:'length[1,32]',required:false" style="width:400px;height: 34px;" class="easyui-textbox" >
        	</div>
	        <div style="padding: 2px;">
        		<input name="bwldate" id="_xtgl_bwl_edit_bwldate" label="时间：" prompt="请输入时间", data-options="labelWidth:80,labelAlign:'right',editable:false,required:false" style="width:400px;height: 34px;" class="easyui-datetimebox" >
        	</div>
        	<div style="padding: 2px;">
	        	<select id="_xtgl_bwl_edit_bwlzt" name="bwlzt" class="easyui-combobox" label="已读：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:60" style="width:400px;height: 34px;">
				     <option value="0">否</option>
				     <option value="1">是</option>
				</select>
        	</div>
	        <div style="padding: 2px;">
	        	<select id="_xtgl_bwl_edit_bwljb" name="bwljb" class="easyui-combobox" label="级别：" data-options="labelWidth:80,labelAlign:'right',editable:false,panelHeight:80" style="width:400px;height: 34px;">
				     <option value="一级">一级</option>
				     <option value="二级">二级</option>
				     <option value="三级">三级</option>
				</select>
        	</div>
	        <div style="padding: 2px;text-align: center;">
	            <input type="hidden" id="_edit_bwlid" name="bwlid"/>
	        	<a id="_xtgl_bwl_edit_save_button" href="javascript:void(0);" class="easyui-linkbutton" data-options="">保存备忘录</a>
	        	<a id="_xtgl_bwl_edit_cancel_button" href="javascript:void(0);" class="easyui-linkbutton l-btn-warm" data-options="">取消</a>
	        </div>
        </form>
    </div>
    
    <div id="_xtgj_bwl_view_window" class="easyui-window" title="查看备忘录" data-options="closed:true,minimizable:false,collapsible:false,maximizable:false,modal:true" style="width:600px;padding:10px;">
		<form action="" method="post" id="_xtgj_bwl_view_form" >
			<table width="100%" class="table-view">
				<tr>
					<th>备忘录标题：</th>
					<td>
						<input name="bwlbt" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_xtgj_bwl_view_bwlbt"/>
					</td>
					<th>备忘录内容：</th>
					<td>
						<input name="bwlnr" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_xtgj_bwl_view_bwlnr"/>
					</td>
				</tr>
				<tr>
					<th>已读：</th>
					<td>
						<select id="_xtgj_bwl_view_bwlzt" readonly="readonly" name="bwlzt" class="easyui-combobox" data-options="labelAlign:'right',editable:false,panelHeight:60" style="width:80%">
					    	<option value="0">否</option>
					    	<option value="1">是</option>
						</select>
					</td>
					<th>位置：</th>
					<td>
						<input name="bwlwz" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_xtgj_bwl_view_bwlwz"/>
					</td>
				</tr>
				<tr>
					<th>备忘录级别：</th>
					<td>
						<input name="bwljb" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_xtgj_bwl_view_bwljb"/>
					</td>
					<th>日期：</th>
					<td>
						<input name="bwldate" type="text" class="easyui-textbox" style="width:80%" data-options="required:false" id="_xtgj_bwl_view_bwldate"/>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript" src="../xtgj/xtgj_bwl.js" charset="UTF-8"></script>	
	<style type="text/css">
		body,html{
			width: 100%;
			height: 100%;
			overflow: hidden;
			margin: 0;
			font-family: "微软雅黑";
		}
	</style>
</body>
</html>