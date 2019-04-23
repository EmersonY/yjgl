var basepara = {
	_basepara:'',
	setPara:function(url){//设置传递的参数
		if(url.indexOf('?') != -1){
			_basepara = url.substring(url.indexOf('?'),url.length);
		}
	},
	getPara:function(paraName){//获取设置的参数
		var str = paraName.toLowerCase() + "=";
		var gvalue = "undefined";
		var upperHREF = _basepara.toLowerCase();
		if(upperHREF.indexOf(str)>0){
			gvalue = _basepara.substring(upperHREF.indexOf(str) + str.length,upperHREF.length);
			if(gvalue.indexOf('&')>0){
				gvalue = gvalue.substring(0,gvalue.indexOf('&'));
			}
			if(gvalue.indexOf("#")>0){
				gvalue = gvalue.split("#")[0];
			}
		}
		return gvalue;
	},
	getEntity:function(){
		var strJSON = _basepara.substring(_basepara.indexOf('?')+1, _basepara.length);
		return eval("("+strJSON+")");
	}
}
