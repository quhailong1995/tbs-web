<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>系统服务授权列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="系统服务授权">

<grid:grid id="tbAuthServiceGridId" url="${adminPath}/tbs/tbauthservice/getAllAuthService" multiSort="false"  sortable="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/> --%>
	<%-- <grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/tbs/tbauthservice/delete" /> --%>
    <grid:column label="银行名称"  name="dmWhChannel.channelComment"  query="true"  condition="eq" sortable="false"/>
    <grid:column label="服务名称"  name="dmWhService.serviceComment"  query="true"  condition="eq" sortable="false"/>
    <grid:column label="服务类型"  name="dmWhService.serviceType"  dict="fwlx"  sortable="false"/>
    <grid:column label="服务类名"  name="dmWhService.methodPath" />
	<grid:toolbar function="create"/>
	<%-- <grid:toolbar function="update"/> --%>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">





	 $(window).load(function(){
	 
	 		/* var htmlStr = '<select class="form-control" dict="fwlx" condition="eq" name="dmWhService.serviceComment">
			 	               <option value="">请选择</option>
					             <option value="2">动作型</option>
					             <option value="1">资源型</option>
			 	          </select>'; */
	 		
	 		//alert($("#tbAuthServiceGridIdGridQuery input").length);
	 		$("#tbAuthServiceGridIdGridQuery input").each(function(){
	 			$(this).remove();
	 		});
	 		$("#tbAuthServiceGridIdGridQuery div div:eq(0)").append('<select class="form-control"'+
						 		' condition="eq"'+
						 		' name="dmWhChannel.channelComment">'+
			 	               '<option value="">请选择</option>'+
			 	          '</select>');
			 $("#tbAuthServiceGridIdGridQuery div div:eq(1)").append('<select class="form-control"'+
						 		' condition="eq"'+
						 		' name="dmWhService.serviceComment">'+
			 	               '<option value="">请选择</option>'+
			 	          '</select>');	         
		
		
		
		var $this = $("#tbAuthServiceGridIdGridQuery select[name='dmWhChannel.channelComment']");
		var $this2 = $("#tbAuthServiceGridIdGridQuery select[name='dmWhService.serviceComment']");	          

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

	 
	 })
</script>



</body>



</html>