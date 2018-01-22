<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>系统服务管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="系统服务管理">
<grid:grid id="dmWhServiceGridId" url="${adminPath}/tbs/dmwhservice/ajaxList"  multiSort="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/tbs/dmwhservice/delete" /> --%>
    <grid:column label="服务ID"  name="serviceName"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="服务名称"  name="serviceComment"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="服务类型"  name="serviceType" dict="fwlx" />
    <grid:column label="服务类名"  name="methodPath" />
    <grid:column label="备注"  name="remarks" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	<grid:toolbar title="服务测试" function="testService"/>
	
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
$(function() {
	
})

</script>
</body>
</html>