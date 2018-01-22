<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统服务授权</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,jqgrid" />
</head>

<body class="white-bg"  formid="tbAuthServiceForm" beforeSubmit="beforeSubmit">
    <form:form id="tbAuthServiceForm" modelAttribute="data"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label>银行名称:</label>
		            </td>
					<td class="width-35">
						
							<form:select path="channelId" htmlEscape="false" class="form-control"       >
								<c:forEach items="${dmWhChannelList}" var="dmWhChannel">
									<option value="${dmWhChannel.id }">${dmWhChannel.channelComment }</option>
								</c:forEach>
							</form:select>
						<label class="Validform_checktip"></label>
				
						
					</td>
					
				</tr>
				
				<tr>
			
					<td  class="width-15 active text-right">	
		              <label>服务名称:</label>
		            </td>
					<td class="width-35">
				<%-- 	<c:out value="${dmWhServiceList}"></c:out> --%>
						<form:select path="serviceId" htmlEscape="false" class="form-control"       >
								<c:forEach items="${dmWhServiceList}" var="dmWhService">
									<option value="${dmWhService.id }">${dmWhService.serviceComment }</option>
								</c:forEach>
						</form:select>
					<%-- <c:forEach items="${dmWhServiceList}" var="dmWhService">
						<span>
							<input name="serviceId" type="checkbox" value="${dmWhService.id}">
							<label for="serviceId1">${dmWhService.serviceComment} </label>
						</span>
					
					</c:forEach> --%>
						<%-- <form:checkboxes path="serviceId" htmlEscape="false" dict="yhlb"   /> --%>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				
		      
		   </tbody>
		</table>   
	</form:form>
	<div class="row">
        <div class="tabs-container">
            <ul class="nav nav-tabs">
            </ul>
            <div class="tab-content">
          
            </div>
        </div>
    </div>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor,jqgrid,jqGrid_curdtools,jqGrid_curdtools_inline" />
<script>
	$(document).ready(function () {
	    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	    	 resizeGrid();
		});
		
	
		var $this = $("#tbAuthServiceForm select[name='channelId']");
		 $this.change(function(){
			//alert();
			var requestUrl = "${adminPath}/tbs/dmwhservice/getServiceByNoAuth/"+ $this.val();
			getDataAndHandle(requestUrl,function(data){
			 	var obj = $("#tbAuthServiceForm select[name='serviceId']");
				obj.find("option").remove();
				for(var i in data){
				//alert(data[i]);
					obj.append('<option value="'+data[i].id+'">'+data[i].serviceComment+'</option>');
				}
			});
		});
		
		
		
		
	});
	$(function(){
	   $(window).resize(function(){   
		   resizeGrid();
	   });
	});
	function resizeGrid(){
	}

	/**
	*提交回调方法
	*/
	function beforeSubmit(curform){
		 //这里最好还是使用JSON提交，控制器改变提交方法，并使用JSON方式来解析数
		 //通过判断，如果有问题不提交
		 return true;
	}
	
	function initGridFormData(curform,gridId){
		 var rowDatas =getRowDatas(gridId+"Grid");
		 var rowJson = JSON.stringify(rowDatas);
		 if(rowJson.indexOf("editable") > 0&&rowJson.indexOf("inline-edit-cell") )
	     {
	    	 return false;
	     }
		 var gridListJson=$('#'+gridId+"ListJson").val();
		 if(gridListJson==undefined||gridListJson==''){
			 var rowInput = $('<input id="'+gridId+'ListJson" type="hidden" name="'+gridId+'ListJson" />');  
			 rowInput.attr('value', rowJson);  
			 // 附加到Form  
			 curform.append(rowInput); 
		 }else{
			 $('#'+gridId+"ListJson").val(rowJson);
		 }
		 return true;
	}
	
	
	function getDataAndHandle(requestUrl,handle){
		$.get(requestUrl,function(data){
			data = eval('(' + data + ')'); 
			handle(data);
		});
	
	}
</script>
</body>
</html>