<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>授权内容管理列表</title>
  <meta name="decorator" content="list"/>
<style>
	td{vertical-align:middle;}
</style>
 
  
</head>
<body title="授权内容管理">
<grid:grid id="tbAuthContentGridId"  url="${adminPath}/tbs/tbauthcontent/getAllAuthContent" gridSettingCallback="" >
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
    
    <grid:column label="银行名称"  name="dmWhChannel.channelComment"   query="true"  condition="eq" />
    <grid:column label="服务名称"  name="dmWhService.serviceComment"   query="true"   condition="eq" />
 <%--    <grid:column label="授权场景"  name="tbAuthContentExt.authType" dict="sqcj" />
    <grid:column label="授权期限（月）"  name="tbAuthContentExt.authTerm"   /> --%>
    <grid:column label="授权内容"  name="serContentStr" width="200"  height="auto"/>
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>

   <script type="text/javascript">
 
 	$(document).ready(function(){
 		//alert($("#tbAuthContentGridIdGrid tr").length);
 		//alert($("#gview_tbAuthContentGridIdGrid").length);
 		
 		$("#gview_tbAuthContentGridIdGrid").bind('DOMNodeInserted', function(e) {  
		     //有变化后执行的内容
		    $("#tbAuthContentGridIdGrid tr td").each(function(){
			    
		    	$(this).css("white-space","inherit");
		    	$(this).css("vertical-align", "middle");
		    });
			});  
 	})
 
	 $(window).load(function(){
	    //要执行的方法体
	   
	   
	    $("#tbAuthContentGridIdGrid tr td").each(function(){
	    	$(this).css("white-space","inherit");
	    	$(this).css("vertical-align", "middle"); 
	    });
	    
	    
	    /* var term = $("#tbAuthContentGridIdGrid tr td[aria-describedby='tbAuthContentGridIdGrid_tbAuthContentExt.authTerm']");//月数
	    term.each(function(){
	    	
		
	    });
	    
	   
	     alert(term.length); */
	     
	     
	     	 		$("#tbAuthContentGridIdGridQuery input").each(function(){
	 			$(this).remove();
	 		});
	 		$("#tbAuthContentGridIdGridQuery div div:eq(0)").append('<select class="form-control"'+
						 		' condition="eq"'+
						 		' name="dmWhChannel.channelComment">'+
			 	               '<option value="">请选择</option>'+
			 	          '</select>');
			 $("#tbAuthContentGridIdGridQuery div div:eq(1)").append('<select class="form-control"'+
						 		' condition="eq"'+
						 		' name="dmWhService.serviceComment">'+
			 	               '<option value="">请选择</option>'+
			 	          '</select>');	         
		
		
		
		var $this = $("#tbAuthContentGridIdGridQuery select[name='dmWhChannel.channelComment']");
		var $this2 = $("#tbAuthContentGridIdGridQuery select[name='dmWhService.serviceComment']");	          
			 	          


			$this.attr("name","channel_id");
			$.get("${adminPath}/tbs/dmwhchannel/getAllChannel",function(data,status){
	    		//alert(status);
	    		data = eval('(' + data + ')');
	    		for(var i in data){
	    			$this.append('<option value="'+data[i].id+'">'+data[i].channelComment+'</option>');
	    		} 
	    		
	    	});

			$this2.attr("name","service_id");

			$.get("${adminPath}/tbs/dmwhservice/getAllService",function(data,status){
	    		//alert(status);
	    		data = eval('(' + data + ')');
	    		for(var i in data){
	    			$this2.append('<option value="'+data[i].id+'">'+data[i].serviceComment+'</option>');
	    		} 
	    		
	    		});
      	          
			 	           
	     
	     
	     	//不排序
	$("table tr.ui-jqgrid-labels").find("th").each(function(){
		$(this).find("div").removeClass("ui-jqgrid-sortable");
	});
	//alert($("table tr.ui-jqgrid-labels").removeAttr('focus'));// 解绑focus事件     
	     
	});
 
 
 </script>

</body>
</html>