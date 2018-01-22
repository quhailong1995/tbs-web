<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统配置-服务器管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="dmWhServermanageForm">
    <form:form id="dmWhServermanageForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>服务器名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="serverMc" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>ip:</label>
		            </td>
					<td class="width-35">
						<form:input path="serverIp" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					
					<td  class="width-15 active text-right">	
		              <label>端口:</label>
		            </td>
					<td class="width-35">
						<form:input path="serverPort" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>应用代码:</label>
		            </td>
					<td class="width-35">
						<form:select path="appDm" htmlEscape="false" class="form-control"  ><option>请选择</option></form:select>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>环境:</label>
		            </td>
					<td class="width-35">
						<form:select path="serverEnvironment" htmlEscape="false" class="form-control"   dict="hj"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>是否启用:</label>
		            </td>
					<td class="width-35">
						<form:select path="xybz" htmlEscape="false" class="form-control"   dict="xybz"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				
		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script src="${staticPath}/common/js/common_create.js"></script>
<script type="text/javascript">
$(window).load(function(){
	var $this = $("#dmWhServermanageForm select[name='appDm']");
		
 	$.get("${adminPath}/tbs/dmwhappmanage/getAllApp", function(
						data, status) {
					//alert(status);
					data = eval('(' + data + ')');
					$this.find("option").remove();
					for ( var i in data) {
						$this.append('<option value="'+data[i].appDm+'">'
								+ data[i].appDm + '</option>');
					}
					
						//更新准备
					if("${data.appDm}"!=null&&"${data.appDm}"!=""){
						$this.val("${data.appDm}");//出事化渠道
						//$this.attr("disabled","true");//不可编辑
						//$("#dmWhServermanageForm").append("<input type='hidden' value='${data.appDm}' name='appDm' />");										
					}		

				}); 


})
</script>
</body>
</html>