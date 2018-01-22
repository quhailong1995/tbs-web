<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>用户授信预测列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="用户授信预测">
<grid:grid id="tbAuthquerylogAniGridId" url="${adminPath}/tbs/tbauthquerylogani/ajaxList" multiSort="false"  multiselect="false">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	 <grid:column label="证件号码"  name="zjhm"  query="true" queryModel="input" condition="like" width="150"/>
	 <grid:column label="姓名"  name="fullName"  query="true" queryModel="input" condition="like"/>
	  <grid:column label="产品"  name="productComment"  query="true" queryModel="input" condition="like"/>
	<grid:column label="工资发放月份数"  name="totalMonth" />
	 <grid:column label="工资发放是否中断"  name="isZd" dict="sf" />
	 <grid:column label="最大工资金额"  name="maxSalary" />
	  <grid:column label="预测是否授信"  name="isSx" dict="sfsx"/>
	 <grid:column label="查询时间"  name="lrrq"  query="false" queryModel="date" condition="between"/>
	
	
    
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="授权数据追溯" groupname="opt" onclick="authDataDetail(this)" outclass="btn-info" />
	<grid:column hidden="true" label="产品id" name="productId" />
		<grid:column hidden="true" label="户籍id" name="hjid" />
		<grid:column hidden="true" label="userid" name="userid" />
	
	
	
 	<grid:query label="查询时间 " name="lrrq" queryMode="date" condition="between" /> 
	<grid:query label="工资发放是否中断" name="isZd"  queryMode="select" dict="sf" condition="eq" />
	<grid:query label="工资发放>=月份数" name="totalMonth"  condition="ge" datatype="n"/>
	<grid:query label="预测是否授信" name="isSx"  queryMode="select" dict="sfsx" condition="eq" />
    
    

	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
function authDataDetail(node){
	
	var userFullName = $(node).parent().parent().find("td[aria-describedby='tbAuthquerylogAniGridIdGrid_fullName']").text();
	//alert(userFullName);
	var userId = $(node).parent().next().next().next().text();
	var productId = $(node).parent().next().text();
	var hjid = $(node).parent().next().next().text();
	openDialog2("授权数据追溯","${adminPath}/tbs/query/userAuth/authDataDetailPage?userId="+userId+"&productId="+productId+"&userFullName="+userFullName+"&hjid="+hjid,"","80%","600px");
}

</script>
</body>
</html>