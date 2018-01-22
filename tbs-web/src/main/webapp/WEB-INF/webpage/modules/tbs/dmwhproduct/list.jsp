<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>税银产品管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="税银产品管理">
<grid:grid id="dmWhProductGridId" url="${adminPath}/tbs/dmwhproduct/ajaxList"  multiSort="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
	 <grid:column label="sys.common.key"  name="channelId"  hidden="true"/>
	 <grid:column label="产品代码"  name="productName" />
	 <grid:column label="产品名称"  name="productComment"  query="false"  queryMode="input"  condition="eq" />
    <grid:column label="所属银行"  name="dmWhChannel.channelComment"  query="true"  queryMode="select"  condition="eq" dict="qxz"/>
    <grid:column label="数据服务"  name="dmWhService.serviceComment" />
    <grid:column label="授权缓冲期限"  name="authBuffer" />
    <grid:column label="产品简介"  name="cpjj" width="200"/>
    <grid:column label="详细介绍"  name="remark" width="220"/>
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
$(window).load(function(){
		var $this = $("#dmWhProductGridIdGridQuery select[name='dmWhChannel.channelComment']");
		$this.attr("name","channelId");
		$.get("${adminPath}/tbs/dmwhchannel/getAllChannel", function(
						data, status) {
					//alert(status);
					data = eval('(' + data + ')');
					for ( var i in data) {
						$this.append('<option value="'+data[i].id+'">'
								+ data[i].channelComment + '</option>');
					}

				});

		$("#dmWhProductGridIdGrid tr td").each(function(){
	    	$(this).css("white-space","inherit");
	    	$(this).css("vertical-align", "middle"); 
	    });


})

 	$(document).ready(function(){
 		//alert($("#tbAuthContentGridIdGrid tr").length);
 		//alert($("#gview_tbAuthContentGridIdGrid").length);
 		
 		$("#gview_dmWhProductGridIdGrid").bind('DOMNodeInserted', function(e) {  
		     //有变化后执行的内容
		    $("#gview_dmWhProductGridIdGrid tr td").each(function(){
			    
		    	$(this).css("white-space","inherit");
		    	$(this).css("vertical-align", "middle");
		    });
			});  
 	})


</script>
</body>
</html>