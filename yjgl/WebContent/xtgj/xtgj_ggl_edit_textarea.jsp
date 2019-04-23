<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<sec:csrfMetaTags/>
	</head>
	<body>
	    <script type="text/javascript" src="../js/jquery.min.js"></script>
		<script charset="utf-8" src="../js/kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="../js/kindeditor/lang/zh_CN.js"></script>
		<script>  
		    var editor;  
		    $(function() {  
		          editor = KindEditor.create('#_yjg_ggl_edit_gglnr',{ 
	        	  items : [//创建工具栏显示的功能
	        	           'justifyleft', 'justifycenter', 'justifyright',
	       			    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	       			    'superscript', 'formatblock', 'fontname', 'fontsize',  'forecolor', 'hilitecolor', 'bold',
	       			    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat'
                         ],
                  resizeType : 1,width:"98%",height:"260px",
                  afterCreate : function() { 
  		            this.sync(); 
  		          }, 
  		          afterBlur:function(){ 
  		            this.sync(); 
  		          }           
                  });   
		    });  
		</script>  
			 
	  	<textarea  style="width:98%;" id="_yjg_ggl_edit_gglnr" name="gglnr" class="easyui-validatebox" data-options="required:true,validType:'length[1,1000000]'" invalidMessage="最大长度不能超过1000000"">
	  	  ${e.gglnr }
	  	</textarea> 
		
	</body>
</html>