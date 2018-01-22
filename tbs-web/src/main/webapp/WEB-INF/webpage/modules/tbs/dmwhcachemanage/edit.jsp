<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统配置-缓存管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="dmWhCachemanageForm">
    <form:form id="dmWhCachemanageForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>缓存代码:</label>
		            </td>
					<td class="width-35">
						<form:input path="cacheDm" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>缓存名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="cacheMc" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>命名空间:</label>
		            </td>
					<td class="width-35">
						<form:input path="cacheNamespace" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>刷新方法:</label>
		            </td>
					<td class="width-35">
						<form:input path="refeshMethod" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>查看方法:</label>
		            </td>
					<td class="width-35">
						<form:input path="viewMethod" htmlEscape="false" class="form-control"      />
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