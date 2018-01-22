<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>定时任务维护列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="定时任务维护">
<grid:grid id="dmWhTaskGridId" url="${adminPath}/tbs/dmwhtask/ajaxList"  multiSort="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<%--<grid:button groupname="opt" function="delete" /> --%>
	<grid:button title="开启"  exp="row.xybz=='N'" tipMsg="你确定要启动该定时任务么?"  groupname="opt" function="rowConfirm" outclass="btn-primary" innerclass="fa-hourglass-start" url="${adminPath}/tbs/dmwhtask/changeJobStatus?cmd=start" />
	<grid:button title="停止" exp="row.xybz=='Y'" tipMsg="你确定要停止该定时任务么?" groupname="opt" function="rowConfirm" outclass="btn-danger"  innerclass="fa-square-o" url="${adminPath}/tbs/dmwhtask/changeJobStatus?cmd=stop" />
	<grid:button title="执行一次"   tipMsg="你确定要执行任务么?"  groupname="opt" function="rowConfirm" outclass="btn-primary" innerclass="fa-hourglass-start" url="${adminPath}/tbs/dmwhtask/runTaskNow" />
    <grid:column label="任务名称"  name="taskMc"  query="true"  queryMode="input"  condition="like"  />
    <grid:column label="任务代码"  name="taskDm" />
    <grid:column label="执行类型"  name="taskType"  dict="rwlx"/>
    <grid:column label="是否开启"  name="xybz"  hidden="true"/>
    <grid:column label="任务状态"  name="taskState"   query="true" hidden="false"  queryMode="select"  condition="eq"  dict="rwzt"/>
    <grid:column label="执行时间"  name="cronExpression" />
    <grid:column label="类名"  name="beanClass" />
    <grid:column label="方法名"  name="methodName" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>