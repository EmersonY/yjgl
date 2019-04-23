$(document).ready(function(){
	
	$('.nemu').datalist({ 
		onClickRow:function(index,row){
        	var _url = row.value;
        	var _title = row.text;
        	$('#_main').panel({
        	    href:_url
        	}); 
		}
	});
	
	//用户详情页
	$('#_usermsg').bind('click', function(){
		initToken();
		$.ajax({
			url:"../TblBaseUserAction/selectUserMsgById",
			type:"post",
			dataType:"json",
			method:'GET',
			success: function(data){ 
				initFormData($("#_ext_editForm"),data[0]);
				$('#_user_id').val(data[0].baseuserid);
				$('#_user_ext_edit').window('open');
			}	
		});
	});
	
	//修改用户详情页
	$('#_edit_ext_save').bind('click', function(){
		$('#_ext_editForm').form('submit', {   
			url:"../TblBaseUserAction/updateMsgById", 
			onSubmit: function(){
				return $(this).form('validate');
			},
			success:function(row){   
				if(row != null && row > 0){
					$.messager.show({title:'系统消息',msg:'修改用户信息成功！',showType:'slide'});
					$(this).form('reset');
					closeWindow("_user_ext_edit",true);
	   	    	}else{
	   	    		$.messager.alert('系统消息','修改用户信息失败！');
	   	    	}  
	   	    } 
		});
	});
	
	//修改密码保存
	$('#_edit_password_save').bind('click', function(){
		$('#_password_upate').form('submit', {
			url:"../TblBaseUserAction/updatePassword",
			onSubmit: function(){
				var _new_password = $('#_new_password').val();
				if(trim(_new_password)==""){
					$.messager.alert('警告','新密码不能为空！');   
					return false ;
				}
				var _sure_password = $('#_sure_password').val();
				if(trim(_sure_password) == "" || _sure_password != _new_password){
					$.messager.alert('警告','确认密码与原密码不一致！');   
					return false ;
				}
				return $(this).form('validate');
			},   
	  	    success:function(r){ 
	  	    	var data = eval('(' + r + ')');
	  	    	if(data.sec){
	  	    		$(this).form('reset');
	  	    		$.messager.show({title:'系统消息',msg:'密码修改成功！',showType:'slide'});
	  	    		closeWindow("_user_ext_edit",true);
	  	    		closeWindow("_user_pwd_edit",true);
	  	    	}else{
	  	    		$.messager.alert('警告','密码修改失败!'); 
	  	    	}
	 	    }   
		});
	});
	
	//注销
	$('#_logon_button').bind('click', function(){
		initToken();
		$.ajax({
			type: "POST",
			dataType: "json",
			url:"../main/loginOut",
			success: function(data){
				$('#_logon_form').submit();
			}
		});
	});
	
	//修改密码页面
	$('#_edit_pwd_button').bind('click', function(){
		$('#_user_pwd_edit').window('open');
	});
	
	//查询提醒信息
	initToken();
	$.ajax({
		type: "POST",
		dataType: "json",
		url:"../YjgBwlAction/remindBwl",
		success: function(result){
			if(result.count>0){
				var tip = '今天您有<b>'+result.count+'</b>条备忘待办，请前往"备忘录"查看.';
				$.messager.alert('温馨提示',tip); 
			}
		}
	});
	
	$.ajax({
		type: "POST",
		dataType: "json",
		url:"../YjgSjdjAction/remindTip",
		success: function(result){
			if(result.twentyFour!=0 || result.first!=0 || result.second!=0 || result.thirdly!=0){
				var tip = '<center><b>'+"当前未处理任务：" +'</b></center>'+
						"<center>24小时之内处理任务<b>"+result.twentyFour+"</b>件.</center>"+
						"<center>一级任务<b>"+result.first+"</b>件.</center>"+
						"<center>二级任务<b>"+result.second+"</b>件.</center>"+
						"<center>三级任务<b>"+result.thirdly+"</b>件.</center>";
				$.messager.alert('温馨提示',tip); 
			}
		}
	});
	
	var setTimesss = null;

    $(function(){
    	setTimesss = window.setInterval(remindRecentTip, 600000); 
    });
  
	function remindRecentTip(){
		initToken();
		$.ajax({
			url:"../YjgSjdjAction/remindRecentTip",
			type: "POST",
			dataType: "json",
			success: function(result){
				if(result.count!=0){
					$.messager.show({
						title:'系统消息',
						msg:"您刚收到"+result.count+"条任务，请前往'任务中心'查看",
						timeout:0,
						width:320,
						height: 200,
						showType:'slide'
					});
				}
			}
		});
	}
	
	//下载打印控件
	$('#_print').bind('click', function(){
		var printUrl = "";
	    var isIE = (navigator.userAgent.indexOf('MSIE') >= 0) || (navigator.userAgent.indexOf('Trident') >= 0);
        var is64IE = isIE && (navigator.userAgent.indexOf('x64') >= 0);
		if(needCLodop()){
			printUrl = "../TblBaseHelpAction/down?filepath=CLodop_Setup_for_Win32NT.exe";
		}else if(is64IE){
			printUrl = "../TblBaseHelpAction/down?filepath=install_lodop64.exe";
		}else{
			printUrl = "../TblBaseHelpAction/down?filepath=install_lodop32.exe";
		}
		window.location=printUrl
	});
	
	function needCLodop(){
	    try{
		var ua=navigator.userAgent;
		if (ua.match(/Windows\sPhone/i) !=null) return true;
		if (ua.match(/iPhone|iPod/i) != null) return true;
		if (ua.match(/Android/i) != null) return true;
		if (ua.match(/Edge\D?\d+/i) != null) return true;
		if (ua.match(/QQBrowser/i) != null) return false;
		var verTrident=ua.match(/Trident\D?\d+/i);
		var verIE=ua.match(/MSIE\D?\d+/i);
		var verOPR=ua.match(/OPR\D?\d+/i);
		var verFF=ua.match(/Firefox\D?\d+/i);
		var x64=ua.match(/x64/i);
		if ((verTrident==null)&&(verIE==null)&&(x64!==null)) 
			return true; else
		if ( verFF !== null) {
			verFF = verFF[0].match(/\d+/);
			if ( verFF[0] >= 42 ) return true;
		} else 
		if ( verOPR !== null) {
			verOPR = verOPR[0].match(/\d+/);
			if ( verOPR[0] >= 32 ) return true;
		} else 
		if ((verTrident==null)&&(verIE==null)) {
			var verChrome=ua.match(/Chrome\D?\d+/i);		
			if ( verChrome !== null ) {
				verChrome = verChrome[0].match(/\d+/);
				if (verChrome[0]>=42) return true;
			};
		};
	        return false;
	    } catch(err) {return true;};
	};
	
})
function closes(){
	$("#loading").fadeOut("normal",function(){
		$(this).remove();
	});
};
var delayTime;

$.parser.onComplete = function(){
	if(delayTime){
		clearTimeout(delayTime);
	}
	delayTime = setTimeout(closes, 100);
};