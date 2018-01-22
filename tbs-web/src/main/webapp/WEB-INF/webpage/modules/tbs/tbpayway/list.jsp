<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>支付方式管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="支付方式管理">
<grid:grid id="tbPayWayGridId" url="${adminPath}/tbs/tbpayway/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	
    
    <grid:column label="支付方式代码"  name="wayDm"   query="true"  condition="like"/>
    <grid:column label="支付方式名称"  name="wayMc"  query="true"  condition="like"/>
    <grid:column label="支付类型 "  name="wayType" dict="zflx"/>
    <grid:column label="支付key"  name="payKey" />
    <grid:column label="支付秘钥"  name="paySecretkey" />
    <grid:column label="状态"  name="xybz" dict="xybz" queryMode="select"  query="true"  condition="eq"/>
     <grid:column label="备注"  name="remarks" />
    
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>