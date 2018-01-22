<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>税银产品管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="dmWhProductForm">
    <form:form id="dmWhProductForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>银行名称:</label>
		            </td>
					<td class="width-35">
						<form:select path="channelId" htmlEscape="false" class="form-control"  ><option>请选择</option></form:select>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>数据服务:</label>
		            </td>
					<td class="width-35">
						<form:select path="serviceId" htmlEscape="false" class="form-control"  ><option>请选择</option></form:select>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>产品代码:</label>
		            </td>
					<td class="width-35">
						<form:input path="productName" htmlEscape="false" class="form-control"     dataType="*"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>产品名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="productComment" htmlEscape="false" class="form-control"   dataType="*"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>贷前月份:</label>
		            </td>
		            <td class="width-35">
						<form:input path="loanBeforeYfs" htmlEscape="false" class="form-control"   dataType="n"   />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>贷后月份:</label>
		            </td>
		            <td class="width-35">
						<form:input path="loanAfterYfs" htmlEscape="false" class="form-control"   dataType="n"   />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>产品简介:</label>
		            </td>
					<td class="width-35">
						<form:textarea path="cpjj" rows="2" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>详细介绍:</label>
		            </td>
					<td class="width-35">
						<form:textarea path="remark" rows="2" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
		  		</tr>
		  		<tr>
		  			<td  class="width-15 active text-right">	
		              <label>授权缓冲期限:</label>
		            </td>
					<td class="width-35">
						<div>
							<form:input path="authBufferTerm" htmlEscape="false" class="form-control"   dataType="n"   style="float:left;width:75%"/>
							<form:select path="authBufferUnit" htmlEscape="false" class="form-control"   dict="dayOrM"  style="height:34px;float:right;width:22%"/>
							<label class="Validform_checktip"></label>
						</div>
						
					</td>
		  		</tr>
		  		<tr>
					<td  class="width-15 active text-right">	
		              <label>有效标志:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="yxbz"  cssClass="required"  defaultvalue="Y" dict="yxbzz"  />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>选用标志:</label>
		            </td>
					<td class="width-35">
						<form:radiobuttons path="xybz"  cssClass="required" defaultvalue="Y" dict="xybzz"   />
						<label class="Validform_checktip"></label>
					</td>

		  		</tr>
		      
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript">
$(window).load(function(){
		var $this = $("#dmWhProductForm select[name='channelId']");
		var $this2 = $("#dmWhProductForm select[name='serviceId']");
		//debugger;	
/* 		$.ajax({
			url    : "${adminPath}/tbs/dmwhchannel/getAllChannel",
			type   : "get",
			async  : false,
			success: function(data){
				data = eval('(' + data + ')');
					for ( var i in data) {
						$this.append('<option value="'+data[i].id+'">'
								+ data[i].channelComment + '</option>');
					}
			}
		}); */
 	$.get("${adminPath}/tbs/dmwhchannel/getAllChannel", function(
						data, status) {
					//alert(status);
					data = eval('(' + data + ')');
					for ( var i in data) {
						$this.append('<option value="'+data[i].id+'">'
								+ data[i].channelComment + '</option>');
					}
					
						//更新准备
					if("${data.channelId}"!=null&&"${data.channelId}"!=""){
						$this.val("${data.channelId}");//出事化渠道
						$this.attr("disabled","true");//不可编辑
						$("#dmWhProductForm").append("<input type='hidden' value='${data.channelId}' name='channelId' />");										
					}		

				}); 
				
	 	$.get("${adminPath}/tbs/dmwhservice/getAllService", function(
						data, status) {
					//alert(status);
					data = eval('(' + data + ')');
					for ( var i in data) {
						$this2.append('<option value="'+data[i].id+'">'
								+ data[i].serviceComment + '</option>');
					}
					
						//更新准备
					if("${data.serviceId}"!=null&&"${data.serviceId}"!=""){
						$this2.val("${data.serviceId}");//出事化渠道
						$this2.attr("disabled","true");//不可编辑
						$("#dmWhProductForm").append("<input type='hidden' value='${data.serviceId}' name='serviceId' />");			
					}		

				}); 
	


})
$(document).ready(function(){
			
		
			
	
})
</script>
<script src="${staticPath}/common/js/common_create.js"></script>
</body>
</html>