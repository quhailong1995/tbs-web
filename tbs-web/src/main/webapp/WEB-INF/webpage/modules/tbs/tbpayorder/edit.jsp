<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>支付订单管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="tbPayOrderForm">
    <form:form id="tbPayOrderForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   
		  		 <tr>
		  			 <td  class="width-15 active text-right">	
		              <label>订单号:</label>
		            </td>
					<td class="width-35">
						<form:input path="orderDm" htmlEscape="false" class="form-control"     dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>商品名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="productDm" htmlEscape="false" class="form-control"     dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>金额:</label>
		            </td>
					<td class="width-35">
						<form:input path="oderAmount" htmlEscape="false" class="form-control"    dataType="n"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>下单时间:</label>
		            </td>
					<td class="width-35">
						<form:input path="orderDate" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>支付方式:</label>
		            </td>
					<td class="width-35">
						<form:input path="wayDm" htmlEscape="false" class="form-control"     dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>订单状态:</label>
		            </td>
					<td class="width-35">
						<form:input path="orderState" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				
				
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>备注:</label>
		            </td>
					<td class="width-35">
						<form:input path="remarks" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				
		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
</body>
</html>