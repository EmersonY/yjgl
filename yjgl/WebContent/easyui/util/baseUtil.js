baseUtil = {
		/**
		 * 解析返回的字符串（1、服务器端返回的参数验证信息 2、服务器端返回的业务状态）
		 * 返回的可能示例：
		 * 1、{"valid":{"password":"密码不能为空","account":"账户不能为空"}}
		 * 2、{"success":1}
		 * 3、{"success":0}
		**/
		v:function(results){
			var json = $.parseJSON(results);
			if(typeof(json.success) != "undefined"){
				if(json.success > 0){
					return true;
				}else{
					return false;
				}
			}
			if(typeof(json.valid) != "undefined"){
				var str = "";
				$.each(json.valid,function(i,n){
					str += "字段："+i+" 信息："+n+"</br>";
				});
				$.messager.alert('系统消息',str);
				return false;
			}
		}
}