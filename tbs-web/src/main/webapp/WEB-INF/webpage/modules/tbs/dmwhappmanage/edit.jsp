<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统配置-应用管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="dmWhAppmanageForm">
    <form:form id="dmWhAppmanageForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
			
					<td  class="width-15 active text-right">	
		              <label>应用代码:</label>
		            </td>
					<td class="width-35">
						<form:input path="appDm" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>应用名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="appMc" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>服务器类型:</label>
		            </td>
					<td class="width-35">
						<form:input path="serverType" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>应用状态:</label>
		            </td>
					<td class="width-35">
						<form:select path="appState" htmlEscape="false" class="form-control"   dict="xybz"   />
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