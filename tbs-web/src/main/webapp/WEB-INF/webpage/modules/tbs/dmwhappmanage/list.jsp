<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>系统配置-应用管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="系统配置-应用管理">
<grid:grid id="dmWhAppmanageGridId" url="${adminPath}/tbs/dmwhappmanage/ajaxList" sortname="appDm">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
    <grid:column label="应用代码"  name="appDm" query="true" condition="like"/>
    <grid:column label="应用名称"  name="appMc"  query="true" condition="like"/>
    <grid:column label="服务器类型"  name="serverType" />
    <grid:column label="应用状态"  name="appState"  query="true" queryMode="select" dict="xybz" condition="eq"/>
     <grid:column label="备注"  name="bz" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>