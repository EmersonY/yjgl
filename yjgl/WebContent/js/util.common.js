/**
 * 格式化数字
 * @param value   数字数值（必须参数）
 * @param decimal 小数点 （非必须参数：若没有，则默认为2）
 * @returns 数字
 */
function formatNumber(value,decimal) {
	if(isNaN(value))
		return value;
	var re =  /^[0-9]+[0-9]*]*$/;
	if (!re.test(decimal)){
		decimal = 2;
	}
	var n = value.toFixed(decimal);
	var a = n.split(".");
	if(a[0].length > 3){
		var str = "";
		for(var i = a[0].length-1;i>=0;i=i-3){
			if(i  >= 3)
				str = ","+ a[0].substring(i-2,i+1) +str;
			else
				str = a[0].substring(i-2,i+1) +str;
		}
		if(decimal > 0)
			return str+"."+a[1];
		else
			return str;
	}else{
		return n;
	}
}

/**
 * 接收url传的参数
 * @param parName 参数名称（必须参数）
 * @returns 接收到参数
 */
function getParameter(parName){
	var str = parName.toLowerCase() + "=";
	var gvalue = "";
	var HREF = location.href;
	var upperHREF = location.href.toLowerCase();
	if(upperHREF.indexOf(str)>0){
	gvalue = HREF.substring(upperHREF.indexOf(str) + str.length,upperHREF.length);
	if(gvalue.indexOf('&')>0) gvalue = gvalue.substring(0,gvalue.indexOf('&'));
	if(gvalue.indexOf("#")>0) gvalue = gvalue.split("#")[0];
	}
	return gvalue;
}

/**
 * 去掉左右空格
 * @param str 字符（必须参数）
 * @returns
 */
function trim(str){   
	return str.replace(/(^\s*)|(\s*$)/g, "");   
}

/**
 * 
 * @param value 日期（必须参数）
 * @param formatter 格式(非必须参数：默认为：yyyy-MM-dd)
 * 目前支持的格式有：①: yyyy-MM-dd; ②: yyyy/MM/dd;  ③:yyyy.MM.dd; ④:yyyy-MM; ⑤: yyyy/MM;  ⑥: yyyy.MM
 * @returns {String}
 */
function formatDate(value,formatter) {
	if (value == null || value == '') {
		return '';
	}
	if(formatter == null || formatter == "undefined" || formatter == ""){
		formatter = "yyyy-MM-dd";
	}
	var year = value.getFullYear();
	var month = value.getMonth()+1;
	var day = value.getDate();
	if(month < 10)
		month = "0"+month;
	if(day < 10)
		day = "0" + day;
	if(formatter == "yyyy-MM-dd"){
		return year + "-" + month + "-" + day;
	}else if(formatter == "yyyy/MM/dd"){
		return year + "/" + month + "/" + day;
	}else if(formatter == "yyyy.MM.dd"){
		return year + "." + month + "." + day;
	}else if(formatter == "yyyy-MM"){
		return year + "-" + month;
	}else if(formatter == "yyyy/MM"){
		return year + "/" + month;
	}else if(formatter == "yyyy.MM"){
		return year + "." + month;
	}else
		return ""; 
}

/**
 * 格式化日期时间
 * @param value 日期时间（必须参数）
 * @param formatter 格式(非必须参数：默认为：yyyy-MM-dd HH:mm:ss)
 * 目前支持的格式有：①: yyyy-MM-dd HH:mm:ss; ②: yyyy/MM/dd HH:mm:ss;  ③:yyyy.MM.dd HH:mm:ss; ④:yyyy-MM-dd hh:mm:ss; ⑤: yyyy/MM/dd hh:mm:ss;  ⑥: yyyy.MM.dd hh:mm:ss
 * @returns 
 */
function formatDateTime(value,formatter) {
	alert(formatter);
	if (value == null || value == '') {
		return '';
	}
	if(formatter == null || formatter == "undefined" || formatter == ""){
		formatter = "yyyy-MM-dd HH:mm:ss";
	}
	var year = value.getFullYear();
	var month = value.getMonth()+1;
	var day = value.getDate();
	var hours = value.getHours();
	var minutes = value.getMinutes(); 
	var seconds = value.getSeconds();
	if((formatter == "yyyy-MM-dd hh:mm:ss" || formatter == "yyyy/MM/dd hh:mm:ss" || formatter == "yyyy.MM.dd hh:mm:ss") && hours >=13 && hours <=23){
		hours = hours-12;
	}
	if(month < 10)
		month = "0"+month;
	if(day < 10)
		day = "0" + day;
	if(hours < 10)
		hours = "0"+hours;
	if(minutes < 10)
		minutes = "0" + minutes;
	if(seconds < 10)
		seconds = "0"+seconds;
	if(formatter == "yyyy-MM-dd HH:mm:ss" || formatter == "yyyy-MM-dd hh:mm:ss"){
		return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
	}else if(formatter == "yyyy/MM/dd HH:mm:ss" || formatter == "yyyy/MM/dd hh:mm:ss"){
		return year + "/" + month + "/" + day + " " + hours + ":" + minutes + ":" + seconds;
	}else if(formatter == "yyyy.MM.dd HH:mm:ss" || formatter == "yyyy.MM.dd hh:mm:ss"){
		return year + "." + month + "." + day + " " + hours + ":" + minutes + ":" + seconds;
	}else
		return ""; 
}

/**
 * 将字符串转成日期
 * @param value 日期时间字符串（必须参数）
 * @param formatter 格式(非必须参数：默认为：yyyy-MM-dd)
 * 目前支持的格式有：①: yyyy-MM-dd; ②: yyyy/MM/dd;  ③:yyyy.MM.dd; ④:yyyy-MM; ⑤: yyyy/MM;  ⑥: yyyy.MM
 * @returns 
 */
function parseDate(value,formatter){ 
	if(value == null)
		return "";
	if(formatter == null || formatter == "undefined" || formatter == ""){
		formatter = "yyyy-MM-dd";
	} 
	if(formatter == "yyyy-MM-dd"){
		var a =  value.split("-");
		return new Date(a[0],parseInt(a[1],10)-1,a[2]);
	}else if(formatter == "yyyy/MM/dd"){
		var a =  value.split("/");		
		return new Date(a[0],parseInt(a[1],10)-1,a[2]);
	}else if(formatter == "yyyy.MM.dd"){
		var a =  value.split(".");
		return new Date(a[0],parseInt(a[1],10)-1,a[2]);
	}else if(formatter == "yyyy-MM"){
		var a =  value.split("-"); 
		return new Date(a[0],parseInt(a[1],10)-1,1);
	}else if(formatter == "yyyy/MM"){
		var a =  value.split("/");		
		return new Date(a[0],parseInt(a[1],10)-1,1);
	}else if(formatter == "yyyy.MM"){
		var a =  value.split(".");
		return new Date(a[0],parseInt(a[1],10)-1,1);
	}else
		return "";
}

function formatFileSize(value){
	return (value/1024).toFixed(2)+"KB";
}

/**
 * ajax重装
 * @param url (必须参数)
 * @param data 传递参数(必须参数,没有请填写null)
 * @param success 成功操作(非必须参数)
 * @param async是否异步（true为异步，false为同步，默认为同步）
 */
function ajax(url,data,success,async){
	if(url == null || url == "" || url == "undefined")
		return false;
	if(success == null || success == "undefined")
		success = function(data,textStatus){};
	if(async != false && async != true)
		async = false;
	$.ajax({
		url:url,
		data:data,
		type:"post",
		dataType:"json",
		async: async,
		success: function(result,textStatus){ 
			success(result,textStatus);
		}	
	});
}

/**
 * 检测客户端浏览器是否安装flash控件
 */
function flashChecker(){ 
	var hasFlash = false;
	if(document.all){
		try{
			hasFlash = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
		}catch(e){
			hasFlash = false;
	    }
	}else{ 
		if(navigator.plugins && navigator.plugins.length > 0){ 
			var swf = navigator.plugins["Shockwave Flash"]; 
			if(swf){ 
				hasFlash = true; 
			} 
		} 
	} 
	return hasFlash; 
} 

/**
 * 判断表单时候被改变，必须和setDefaultFormData(formid)方法联用
 * @param formTarget (必须参数) 表单节点
 */
function isFormChanged(formTarget){
	var isChanged = false;
	formTarget.find(":input").each(function(){
		var type = this.type;
		if(type == "select-one"){
			for (var j = 0; j < this.options.length; j++){  
	            if (this.options[j].selected != this.options[j].defaultSelected){  
	                isChanged = true;
	                return false;  
	            }  
			}  
		}else if(type == "checkbox" || type == "radio"){
			if(this.checked != this.defaultChecked){
				 isChanged = true; 
	             return false; 
			}
		}else if(type == "textarea" || type == "text" || type == "hidden" || type == "image" || type == "password"){
			if(this.value != this.defaultValue){
				isChanged = true;  
				return false; 
			}
		}else{}

	});
	return isChanged;
}

/**
 * 为表单设置默认的值（默认将当前表单中各元素的值设为默认值，后常和isFormChanged(formid)联用）
 * @param formTarget (必须参数) 表单节点
 */
function setDefaultFormData(formTarget){
	formTarget.find(":input").each(function(){
		var type = this.type;
		if(type == "select-one"){
			for (var j = 0; j < this.options.length; j++){  
				this.options[j].defaultSelected = this.options[j].selected;
			}  
		}else if(type == "checkbox" || type == "radio"){
			this.defaultChecked = this.checked;
		}else if(type == "textarea" || type == "text" || type == "hidden" || type == "image" || type == "password"){
			this.defaultValue = this.value;
		}
	});
}

/**
 * 将字符串转成json
 * @param str 字符串 
 */
function stringToJson(str){
	return  eval('(' + str + ')'); 
}

/**
 * 将json转成字符串
 * @param json 对象
 */
function jsonToString(json){
	return JSON.stringify(json);
}

//换行和换空格
function to_text(str){
	if(str == null)
		return null;
	else
		return str.split("\r\n").join('<br>').split(" ").join('&nbsp;&nbsp;');
}
//小写数字转大写金额
function atoc(numberValue){
	var numberValue=new String(Math.round(numberValue*100)); // 数字金额
	var chineseValue=""; // 转换后的汉字金额
	var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字
	var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位
	var len=numberValue.length; // numberValue 的字符串长度
	var Ch1; // 数字的汉语读法
	var Ch2; // 数字位的汉字读法
	var nZero=0; // 用来计算连续的零值的个数
	var String3; // 指定位置的数值
	if(len>15){
		alert("超出计算范围");
		return "";
	}
	if (numberValue==0){
		chineseValue = "零元整";
		return chineseValue;
	}
	String2 = String2.substr(String2.length-len, len); // 取出对应位数的STRING2的值
	for(var i=0; i<len; i++){
		String3 = parseInt(numberValue.substr(i, 1),10); // 取出需转换的某一位的值
		if ( i != (len - 3) && i != (len - 7) && i != (len - 11) && i !=(len - 15) ){
			if ( String3 == 0 ){
				Ch1 = "";
				Ch2 = "";
				nZero = nZero + 1;
			}
			else if ( String3 != 0 && nZero != 0 ){
				Ch1 = "零" + String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			}
			else{
				Ch1 = String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			}
		}
		else{ // 该位是万亿，亿，万，元位等关键位
			if( String3 != 0 && nZero != 0 ){
				Ch1 = "零" + String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			}
			else if ( String3 != 0 && nZero == 0 ){
				Ch1 = String1.substr(String3, 1);
				Ch2 = String2.substr(i, 1);
				nZero = 0;
			}
			else if( String3 == 0 && nZero >= 3 ){
				Ch1 = "";
				Ch2 = "";
				nZero = nZero + 1;
			}
			else{
				Ch1 = "";
				Ch2 = String2.substr(i, 1);
				nZero = nZero + 1;
			}
			if( i == (len - 11) || i == (len - 3)){ // 如果该位是亿位或元位，则必须写上
				Ch2 = String2.substr(i, 1);
			}
		}
		chineseValue = chineseValue + Ch1 + Ch2;
	}
	if ( String3 == 0 ){ // 最后一位（分）为0时，加上“整”
		chineseValue = chineseValue + "整";
	}
	return chineseValue;
}


/**字符串数字相加
 * 
 * @param idstr  id的字符串 用,隔开
 */
function numAdd(idstr){
	var ids = idstr.split(",");
	var total = 0;
	for(var i = 0;i<ids.length;i++){
		
		total += Number($("#"+ids[i]).val());
	}
	return total;
}

//验证邮编  onchange="checkYb(this.id)"   var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;  checkGdDh
function checkYb(object){
	var yb = document.getElementById(object.id).value; 
    var pattern =/^[0-9]{6}$/;
        if(yb!="")
        {
            if(!pattern.exec(yb))
            {
             $.messager.alert('警告','请输入正确的邮政编码！');
             object.value = "";
            }
        }
}

//验证以13，14，15，18开头的    /^0?1[3|4|5|8][0-9]\d{8}$/; 下面验证以1开头的11位数字
function checkMbPhone(object){
	var sjhm = document.getElementById(object.id).value; 
    var pattern =/^0?1[0-9]\d{9}$/;
        if(sjhm!="")
        {
            if(!pattern.exec(sjhm))
            {
             $.messager.alert('警告','请输入正确的手机号码！');
             object.value = "";
            }
        }
}

//电话号码验证  区号(3到4位)-电话号码(7到8位)(分机号(3位)或者以上)
function checkGdDh(object){
	var GdDh = document.getElementById(object.id).value; 
    var pattern = /^((0\d{2,3})-)(\d{7,8})(\((\d{3,})\))?$/;
        if(GdDh!="")
        {
            if(!pattern.exec(GdDh))
            {
             $.messager.alert('警告','请输入正确的电话号码！');
             object.value = "";
            }
        }
}

//将ip地址转换为数据库中t_base_ipinfo表字段numip一样的形式；
function ipToNumip(ip){
	var numip='';
	var array=ip.split(".");
	for(var i=1;i<array.length;i++){
		if(array[i].length==1){
			array[i]="00"+array[i];
		}
        if(array[i].length==2){
			array[i]="0"+array[i];
		}
	}
	for(var i=0;i<array.length;i++){
		numip=numip+array[i];
	}
	return numip;
}

function initview(formTarget,e){ 
	if(e == null)
		return;
	str = "{";
	for (var key in e) {  
		str += "'e."+key +"':" + "e."+key + ",";
    } 
	if(str != "{")
		str  = str.substring(0, str.length-1);
	str += "}";
	var json = eval('(' + str + ')'); 
	formTarget.form('load',json);
}
