<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>支付产品管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="tbPayProductForm">
    <form:form id="tbPayProductForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
		   			<td  class="width-15 active text-right">	
		              <label>支付产品代码:</label>
		            </td>
					<td class="width-35">
						<form:input path="productDm" htmlEscape="false" class="form-control"     dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>支付产品名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="productMc" htmlEscape="false" class="form-control"    dataType="*"    />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
		   
				<tr>
					<td  class="width-15 active text-right">	
		              <label>支付方式:</label>
		            </td>
					<td class="width-35" id="tbPayWay_checkbox">
						<c:forEach items="${tbPayWays }" var="p">
							<div style="width:45%;float:left">
						  <span>
								<input id="wayDm" name="wayDm" type="checkbox" value="${p.wayDm }" datatype="*">
							    <label for="wayDm">${p.wayMc }</label>
						  </span>
						</div>
						</c:forEach>
						
						<%-- <form:checkbox path="wayDm" htmlEscape="false" class="form-control"    dataType="*"    /> --%>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>状态(是否上架):</label>
		            </td>
					<td class="width-35">
						<form:select path="productState" htmlEscape="false" class="form-control"     dataType="*"   >
							<option value="1">是</option>
							<option value="0">否</option>
						</form:select>
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
<script type="text/javascript">
$(document).ready(function(){
	//标记为选中
	var scontentIds = {};//定义授权内容id数组
	scontentIds = "${data.wayDm}".split(",");
	//alert(scontentIds);
	$("#tbPayWay_checkbox").find("input").each(function(){
		for(var i=0;i<scontentIds.length;i++){
			if($(this).val()==scontentIds[i]){
			//初始化授权内容
			     $(this).attr("checked","checked");
			}
		}
});

	
})
</script>




</body>
</html>