<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>支付订单管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="支付订单管理">
<grid:grid id="tbPayOrderGridId" url="${adminPath}/tbs/tbpayorder/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	
    
    <grid:column label="订单号"  name="orderDm"  query="true"  queryMode="input"  condition="like"/>
    <grid:column label="商品名称"  name="productDm" />
    <grid:column label="金额"  name="oderAmount" />
    <grid:column label="下单时间"  name="orderDate" query="true" queryMode="date" condition="between" />
    <grid:column label="支付方式"  name="wayDm" />
    <grid:column label="订单状态"  name="orderState" query="true" dict="ddzt"  queryMode="select" condition="eq"/>
    <grid:column label="备注"  name="remarks" />
     
    
<%-- 	<grid:toolbar function="create"/> --%>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>