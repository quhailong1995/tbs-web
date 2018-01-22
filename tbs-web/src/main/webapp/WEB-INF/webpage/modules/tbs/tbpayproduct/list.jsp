<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>支付产品管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="支付产品管理">
<grid:grid id="tbPayProductGridId" url="${adminPath}/tbs/tbpayproduct/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    
    
    <grid:column label="支付产品代码"  name="productDm" query="true"  condition="like"/>
    <grid:column label="支付产品名称"  name="productMc" query="true"  condition="like"/>
    <grid:column label="支付方式"  name="wayDm"  />
    <grid:column label="状态(是否上架)"  name="productState" dict="sf"  queryMode="select"  query="true"  condition="eq"/>
    
    <grid:column label="备注"  name="remarks" />
    
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>