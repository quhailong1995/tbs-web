<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>授权内容管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
    
    
    
</head>

<body class="white-bg"  formid="tbAuthContentForm">
    <form:form id="tbAuthContentForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>银行名称:</label>
		            </td>
					<td class="width-35" id="dmWhChannel_init">
						<form:select path="channelId" htmlEscape="false" class="form-control"      >
							<c:forEach items="${dmWhChannelList}" var="dmWhChannel">
							 	<option value="${dmWhChannel.id}">${dmWhChannel.channelComment}</option>
							</c:forEach>
						</form:select>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>服务名称:</label>
		            </td>
					<td class="width-35" id="dmWhService_init">
						<form:select path="serviceId" htmlEscape="false" class="form-control" flag="1" >
							<c:forEach items="${dmWhServiceList}" var="dmWhService">
							 	<option value="${dmWhService.id}">${dmWhService.serviceComment}</option>
							</c:forEach>
						</form:select>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
<%-- 				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>授权场景:</label>
		            </td>
					<td class="width-35" id="tbAuthContentExt_type">
						<form:select path="scontentId" htmlEscape="false" class="form-control" dict="sqcj"     >
						</form:select>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>授权期限:</label>
		            </td>
					<td class="width-35"  id="tbAuthContentExt_term">
						<form:input path="scontentId" htmlEscape="false" class="form-control" dataType="n"   />
						<label class="Validform_checktip"></label>
					</td>
		  		</tr> --%>
		  		
		  		<tr >
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>授权内容:</label>
		            </td>
					<td class="width-35" id="dmWhSerContent_checkbox">
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
    	//更新准备
    	if("${dmWhChannel}"!=null&&"${dmWhChannel}"!=""){
    		$("#tbAuthContentExt_type select").val(${tbAuthContentExt.authType});//初始化类型
	    	$("#tbAuthContentExt_term input").val(${tbAuthContentExt.authTerm});//月数
	    	//alert("${dmWhService.id}");
	    	$("#dmWhChannel_init select").val("${dmWhChannel.id}");//出事化渠道
	    	$("#dmWhChannel_init select").attr("disabled","true");//不可编辑
	    	$("#dmWhChannel_init").append("<input type='hidden' value='${dmWhChannel.id}' name='channelId' />");
	    	//alert($("#dmWhChannel_init select").val());
	    	$("#dmWhService_init select").val("${dmWhService.id}");//服务
	    	$("#dmWhService_init select").attr("disabled","true");//不可编辑
	    	
	    	
	    	$("#dmWhService_init").append("<input type='hidden' value='${dmWhService.id}' name='serviceId' />");
	    	
	    	
	    	$.get("${adminPath}/tbs/tbauthcontent/changeAuthContentByService/"+$("#dmWhService_init input").val(),function(data,status){
    			data = eval('(' + data + ')'); 
    			$("#dmWhSerContent_checkbox div").remove();
    			var htmlStr = "";
    			var j = 1;
				for(var i in data){
					htmlStr = '<div style="width:50%;float:left">'
						  +'<span>'
							+'<input id="scontentId'+j+'" name="scontentId" type="checkbox" value="'+data[i].id+'" datatype="*">'
							+'  <label for="scontentId'+j+'">'+data[i].columnComment+'</label>'
						  +'</span>'
						+'</div>';
					$("#dmWhSerContent_checkbox").append(htmlStr);	
					
					//标记为选中
			    	var scontentIds = {};//定义授权内容id数组
			    	scontentIds = "${scontentIdStr}".split(",");
			    	//alert(scontentIds);
			    	$("#dmWhSerContent_checkbox").find("input").each(function(){
			    		for(var i=0;i<scontentIds.length;i++){
			    			if($(this).val()==scontentIds[i]){
			    			//初始化授权内容
			    			     $(this).attr("checked","checked");
			    			}
			    		}
		    	});
					
				j++;	
					
				}
	    	});
	    	
    		return;
    	}
    	
    	
    	
    	//创建时数据初始化
    	$.get("${adminPath}/tbs/tbauthcontent/changeAuthContentByService/${firstServiceId}",function(data){
    		dataHandle(data);
    	});
    	//根据服务动态改变服务内容
    	$("#dmWhService_init select").change(function(){
    		$.get("${adminPath}/tbs/tbauthcontent/changeAuthContentByService/"+$(this).val(),function(data,status){
    			//console.log(data);
    			$("#dmWhSerContent_checkbox div").remove();
    				dataHandle(data);			
    		});
    	});
    	
    	})
    	
    	
    	function dataHandle(data){
    		var htmlStr = "";
    		data = eval('(' + data + ')'); 
    		var j = 1;
				for(var i in data){
					htmlStr = '<div style="width:50%;float:left">'
						  +'<span>'
							+'<input id="scontentId'+j+'" name="scontentId" type="checkbox" value="'+data[i].id+'" datatype="*">'
							+'  <label for="scontentId'+j+'">'+data[i].columnComment+'</label>'
						  +'</span>'
						+'</div>';
					$("#dmWhSerContent_checkbox").append(htmlStr);	
					j++;
			}
    	}
    	
    	
    	
    
    </script>



</body>



</html>