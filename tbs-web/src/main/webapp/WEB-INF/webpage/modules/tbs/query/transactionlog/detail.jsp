<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统服务管理</title>
    <meta name="decorator" content="form"/>

</head>




<body class="white-bg" >

                <div class="tabs-container" style="height:100%">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab_dbinfo" aria-expanded="true">请求</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfo" aria-expanded="false">响应</a></li>
                    </ul>
                    <div class="tab-content" >
                        <div id="tab_dbinfo" class="tab-pane active">
                            <div class="panel-body">
                                <div class="jqGrid_wrapper">
		                           <textarea name="testRequest" style="border:none" rows="15" cols="100%" readonly="true">${transactionLog.recData}</textarea>
		                        </div>
                            </div>
                        </div>
                        <div id="tab_pageinfo" class="tab-pane">
                            <div class="panel-body">
                                 <div class="jqGrid_wrapper">
		                           <textarea style="border:none" name="testResponse" rows="15" cols="100%" readonly="true">${transactionLog.retData}</textarea>
		                        </div>
                            </div>
                        </div>
                       
                    </div>
                </div>
<%-- 		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td style="width:15%"  class=" active text-right">	
		              <label>请求:</label>
		            </td>
					<td class="width-35">
						<textarea name="testRequest" style="border:none" rows="18" cols="40" readonly="true">${transactionLog.recData}</textarea>
					</td>
					<td style="width:15%"  class="active text-right">	
		              <label>响应:</label>
		            </td>
					<td class="width-35">
						<textarea style="border:none" name="testResponse" rows="18" cols="40" readonly="true">${transactionLog.retData}</textarea>
					</td>
				</tr>
		   </tbody>
		</table>    --%>
		

<script src="${staticPath}/common/js/common_create.js"></script>
<script type="text/javascript">

$(function(){
	
	$("textarea[name='testRequest']").val(formatXml($("textarea[name='testRequest']").val()));
	$("textarea[name='testResponse']").val(formatXml($("textarea[name='testResponse']").val()));
})


</script>

</body>

</html>