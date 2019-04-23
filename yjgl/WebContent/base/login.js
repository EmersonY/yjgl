var login = getParameter("login");
$(document).ready(function(){
	
	$(window).keydown(function(event){
		if(event.keyCode==13){
			$("#_submit").click();
		}
	});
	
	if(login == "-1"){
		$("#check_zp").show();
		$('#_password').val("");
		$('#_username').val("");
	}else if(login == "-2"){
		$.messager.alert('系统消息','登陆超时或没有权限访问此资源, 请重新登录！');
	}else{
		$("#check_zp").hide();
		$("#check_empty").hide();	
	}
	
	layout();
	$(window).resize(function() {
		layout();
	});
	
	var account = $.cookie('_account');
	if(null != account){
		$('#_username').val(account);
	}
	if($.cookie('_rememberpassword') == 1){
		$("#_rememberpassword").attr("checked",'true');
		$('#_password').val($.cookie('_password'));
	}
	
	$('#_submit').bind('click', function(){
		var _pw = $('#_password').val();
		var _user = $('#_username').val();
		if(trim(_user) == "" || trim(_user) == null){	
			$("#check_active").hide();
			$("#check_zp").hide();
			$("#check_empty").show();
			return false;
		}
		if(trim(_pw) == "" || trim(_pw) == null){	
			$("#check_active").hide();
			$("#check_zp").hide();
			$("#check_empty").show();
			return false;
		}
		$.ajax({
			url:"../TblBaseUserAction/checkUserAcitve",
			type:"post",
			dataType:"json",
			method:'POST',
			data: {"account": _user},
			success: function(result){ 
				if(result.tip != "success"){
					$("#check_zp").hide();
					$("#check_empty").hide();
					$("#check_active").show();
					$("#_active_tip").html(result.tip);
				}else{
					if($('#_rememberpassword').is(':checked')){
						$.cookie("_rememberpassword", 1, {expires:30,path:'/'});
						$.cookie("_password", _pw, {expires:30,path:'/'});
					}else{
						$.cookie("_rememberpassword", 0, {expires:30,path:'/'});
						$.cookie("_password", '', {expires:30,path:'/'});
					}
					$('#_password').val($.md5(_pw));
					$.cookie("_account", $('#_username').val(), {expires:30,path:'/'});
					$('#_login').submit()
				}
			}	
		});
	})
});


//根据窗口大小动态加载底部div背景色
function layout() {
	$("#_width_div").css("marginLeft",($(window).width() - 960) / 2);
	$("#_width_div").css("marginTop",($(window).height() - 579) / 2);
}

