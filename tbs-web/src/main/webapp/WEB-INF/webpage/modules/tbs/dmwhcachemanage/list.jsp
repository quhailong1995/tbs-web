<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>系统配置-缓存管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="系统配置-缓存管理">
<grid:grid id="dmWhCachemanageGridId" url="${adminPath}/tbs/dmwhcachemanage/ajaxList" sortname="cacheDm">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
    <grid:column label="缓存代码"  name="cacheDm"  query="true" condition="like"/>
    <grid:column label="缓存名称"  name="cacheMc"  query="true" condition="like"/>
    <grid:column label="命名空间"  name="cacheNamespace" />
    <grid:column label="刷新方法"  name="refeshMethod" />
    <grid:column label="查看方法"  name="viewMethod" />
    <grid:column label="超时时间"  name="timeout" />
    
    <grid:query label="缓存状态"  name="timeout" queryMode="select" dict="xybz" condition="eq"/>
    
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	<grid:toolbar title="缓存操作" function="cacheOperate"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>