<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统服务管理</title>
    <meta name="decorator" content="form"/>
    <script src="${staticPath}/common/js/common_create.js"></script>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,jqgrid" />
    


      <link href="${staticPath}/vendors/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
      <style type="text/css">
      	.showli1{
      		width:10%;
      		float:left;
      		list-style: none;
      		text-align:left;
      		height: 28px;
      		 line-height:30px;
      	}
      	.showli2{
      		width:20%;
      		float:left;
      		list-style: none;
      		text-align:left;
      	height: 28px;
      	 line-height:30px;
      	 border: 1px solid #efefef;
      	 margin-right:10px;
      	}
    .showli3{
      		width:20%;
      		float:left;
      		list-style: none;
      		text-align:center;
      	}
      </style>
      
    <script type="text/javascript">
		$(function(){
		
		$("a.textService").click(function(){
			var serviceName = $("input[name='serviceName']").val();
			var requestMessage ="<message>"+$("textarea[name='testRequest']").val().split("<message>")[1];
			var toplayer ;
			$.ajax({
    					url:"${adminPath}/tbs/dmwhservice/"+serviceName+"/testService",
    					type:"post",
    					data:{requestMessage:requestMessage},
    					async:true,
    					dataType: 'text',
    					beforeSend:function(){
					            toplayer = top.layer.load();
							},
    					success:function(data){
    						top.layer.close(toplayer);
	    					$("textarea[name='testResponse']").text(formatXml(data));
	    					
	    					$("#tab_pageinfo").addClass("active");
	    					$("#tab_dbinfo").removeClass("active");
	    					$(".tabs-container ul li:eq(1)").addClass("active");
	    					$(".tabs-container ul li:eq(0)").removeClass("active");
	    					
    				},
    				});	
		});
		
		})
	</script>
</head>

<body class="white-bg" >
 		<div style="overflow:hidden;margin-bottom:12px;" class="layui-layer-btn">
 		<input type="hidden" value="${data.serviceName}" name="serviceName">
	 		<div style="width:100%;overflow:hidden;margin-bottom:15px;margin-top:15px;">
	 			<ul>
	 			<li class="showli1">服务ID:</li><li class="showli2">${data.serviceName}</li>
				<li class="showli1">服务名称:</li><li class="showli2">${data.serviceComment}</li>
				<li class="showlis3"><a class="layui-layer-btn0 textService">测试验证</a></li>
	 			</ul>
	 		</div>
	 		
	 		<ul>
	 			<li class="showli1">请求Url:</li><li style="width:75%" class="showli2">${requestUrl}</li>
				
	 		</ul>
 			
 			
 		</div>
 		
 		
 		<div class="tabs-container" style="margin-left:20px;margin-right:20px">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab_dbinfo" aria-expanded="true">请求</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfo" aria-expanded="false">响应</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab_dbinfo" class="tab-pane active">
                            <div class="panel-body">
                                <div class="jqGrid_wrapper">
		                         <textarea name="testRequest" style="border:none" rows="23" cols="85%" ><c:out value="${requestMessage}" escapeXml="true"/></textarea>
		                        </div>
                            </div>
                        </div>
                        <div id="tab_pageinfo" class="tab-pane">
                            <div class="panel-body">
                                 <div class="jqGrid_wrapper">
		                          <textarea style="border:none" name="testResponse" rows="23" cols="85%" readonly="true"></textarea>
		                        </div>
                            </div>
                        </div>
                       
                    </div>
                </div>
 		
	<%-- 	<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>

				<tr>
					<td style="width:7%"  class=" active text-right">	
		              <label>请求:</label>
		            </td>
					<td style="width:43%">
						<textarea name="testRequest" style="border:none" rows="30" cols="57%" ><c:out value="${requestMessage}" escapeXml="true"/></textarea>
					</td>
					<td style="width:7%"  class="active text-right">	
		              <label>响应:</label>
		            </td>
					<td style="width:43%">
						<textarea style="border:none" name="testResponse" rows="30" cols="57%" readonly="true"></textarea>
					</td>
				</tr>

		   </tbody>
		</table>    --%>

<html:js name="bootstrap-fileinput" />
<html:js name="simditor,jqgrid,jqGrid_curdtools,jqGrid_curdtools_inline" />


<script type="text/javascript">

$(function(){
	
	$("textarea[name='testRequest']").val(formatXml($("textarea[name='testRequest']").val()));

})


</script>

</body>

</html>