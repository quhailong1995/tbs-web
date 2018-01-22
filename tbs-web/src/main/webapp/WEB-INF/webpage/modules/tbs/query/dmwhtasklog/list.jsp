<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>定时任务日志查询</title>
  <meta name="decorator" content="list"/>
</head>
<body title="定时任务日志查询">
<grid:grid id="dmWhTaskLogGridId" multiselect="false" url="${adminPath}/tbs/query/dmwhtaskLog/ajaxList"  multiSort="false" sortname="startDate" sortorder="desc">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" /> --%>
    <grid:column label="任务代码"  name="taskDm" />
    <grid:column label="任务名称"  name="taskMc"   query="true"  queryMode="input"  condition="like" />
    <grid:column label="执行时间"  name="startDate" />
    <grid:column label="任务结束时间"  name="endDate" />
    <grid:column label="返回结果 " name="resultCode" dict="taskresult" query="true" queryMode="select" condition="eq"/>
    <grid:column label="返回消息"  name="resultMessage" />
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>