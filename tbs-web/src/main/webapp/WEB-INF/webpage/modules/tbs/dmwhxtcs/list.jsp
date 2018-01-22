<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>系统参数维护列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="系统参数维护">
<grid:grid id="dmWhXtcsGridId" url="${adminPath}/tbs/dmwhxtcs/ajaxList"  multiSort="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
    <grid:column label="参数代码"  name="xtcsDm" />
    <grid:column label="参数名称"  name="xtcsMc"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="参数内容"  name="xtcsNr" />
    <grid:column label="状态"  name="xybz"  query="true"  queryMode="select"  condition="eq"  dict="xybz"/>
    <grid:column label="参数说明"  name="bz" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>