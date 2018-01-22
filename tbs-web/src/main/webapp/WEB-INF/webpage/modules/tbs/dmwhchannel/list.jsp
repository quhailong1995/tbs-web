<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>银行接入管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="银行接入管理">
<grid:grid id="dmWhChannelGridId" url="${adminPath}/tbs/dmwhchannel/ajaxList" multiSort="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<%-- <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/> 
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/tbs/dmwhchannel/toDelete" /> --%>
    
    <grid:column label="银行名称"  name="channelComment" queryMode="input"  query="true" condition="like"  />
    <grid:column label="渠道号码"  name="channelName" />
    <grid:column label="访问秘钥"  name="accessKey" width="180"/>
    <grid:column label="数据源"  name="datasourceName" />
    <grid:column label="是否限制IP"  name="limitipList" width="160" />
    <grid:column label="备注"  name="remarks" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>