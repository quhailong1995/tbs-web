<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>定时任务维护</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="taskForm">
    <form:form id="taskForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>任务名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="taskMc" htmlEscape="false" class="form-control"    dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>任务代码:</label>
		            </td>
					<td class="width-35">
						<form:input path="taskDm" htmlEscape="false" class="form-control"    dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>执行类型:</label>
		            </td>
					<td class="width-35">
						<form:select path="taskType" htmlEscape="false" class="form-control"  dict="rwlx"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>执行时间:</label>
		            </td>
					<td class="width-35">
						<form:input path="cronExpression" htmlEscape="false" class="form-control"   dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				

				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>类名:</label>
		            </td>
					<td class="width-35">
						<form:input path="beanClass" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>方法名:</label>
		            </td>
					<td class="width-35">
						<form:input path="methodName" htmlEscape="false" class="form-control"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
					<td  class="width-15 active text-right">	
		              <label>任务描述:</label>
		            </td>
					<td class="width-35">
						<form:textarea row="2" path="taskRemark" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>备注:</label>
		            </td>
					<td class="width-35">
						<form:input path="bz" htmlEscape="false" class="form-control"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>

		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script src="${staticPath}/common/js/common_create.js"></script>
</body>
</html>