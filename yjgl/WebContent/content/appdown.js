$(document).ready(function(){
	var height = $(window).height();
	var topheight = (height-1280);
	if(topheight >0){
		$("#_yjgl_top").css("height",topheight);
		$("#_yjgl_bottom").css("height",topheight);
	}else{
		$("#_yjgl_top").css("height",0);
		$("#_yjgl_bottom").css("height",0);
	}
	
	var width = $(window).width();
	var leftwidth = (width-720)/2;
	if(leftwidth >0){
		$("#_yjgl_left").css("width",leftwidth);
		$("#_yjgl_right").css("width",leftwidth);
	}else{
		$("#_yjgl_left").css("width",0);
		$("#_yjgl_right").css("width",0);
	}
		
});	