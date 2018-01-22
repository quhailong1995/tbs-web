<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>系统配置-服务器管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="系统配置-服务器管理">
<grid:grid id="dmWhServermanageGridId" url="${adminPath}/tbs/dmwhservermanage/ajaxList" sortname="serverMc">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
    <grid:column label="服务器名称"  name="serverMc" query="true" condition="like" />
    <grid:column label="ip"  name="serverIp" />
    <grid:column label="端口"  name="serverPort" />
    <grid:column label="应用代码"  name="appDm" />
    <grid:column label="应用名称"  name="app.appMc" />
    <grid:column label="环境"  name="serverEnvironment" dict="hj"/>
    <grid:column label="是否启用"  name="xybz"/>
    <grid:column label="服务器状态"  name="serverState"/>
    
    
    <grid:query label="所属应用"  name="appDm" queryMode="select" dict="qxz" condition="eq"/>
     <grid:query label="是否启用"  name="xybz" queryMode="select" dict="xybz" condition="eq"/>
      <grid:query label="环境"  name="serverEnvironment"  queryMode="select" dict="hj" condition="eq"/>
    
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar title="检测状态" function="testAppState"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
$(window).load(function(){
	var $this = $("#dmWhServermanageGridIdGridQuery select[name='appDm']");
		
 	$.get("${adminPath}/tbs/dmwhappmanage/getAllApp", function(
						data, status) {
					//alert(status);
					data = eval('(' + data + ')');
					for ( var i in data) {
						$this.append('<option value="'+data[i].appDm+'">'
								+ data[i].appMc + '</option>');
					}	

				}); 


})
function testAppState(title,url,gridId){
	url = "dmwhservermanage/testAppState";
	testState(title,url,gridId);
}

</script>
</body>
</html>