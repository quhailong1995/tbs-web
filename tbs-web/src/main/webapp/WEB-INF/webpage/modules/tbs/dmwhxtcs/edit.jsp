<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统参数维护</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="dmWhXtcsForm">
    <form:form id="dmWhXtcsForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>参数代码:</label>
		            </td>
					<td class="width-35">
						<form:input path="xtcsDm" htmlEscape="false" class="form-control"    dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>参数名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="xtcsMc" htmlEscape="false" class="form-control"  dataType="*"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>执行内容:</label>
		            </td>
					<td class="width-35">
						<form:input path="xtcsNr" htmlEscape="false" class="form-control"   dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>是否开启:</label>
		            </td>
					<td class="width-35">
						<form:select path="xybz" htmlEscape="false" class="form-control"   dict="xybz"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				
					<td  class="width-15 active text-right">	
		              <label>参数描述:</label>
		            </td>
					<td class="width-35">
						<form:textarea row="2" path="bz" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>有效标志:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="yxbz"  cssClass="required"  defaultvalue="Y" dict="yxbzz"  />
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