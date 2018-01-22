<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>模拟授权</title>
    <meta name="decorator" content="form"/> 
</head>

<body class="white-bg">
	<form:form id="analogAuthForm"  method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active text-right">
						<label><font color="red">*</font>证件类型:</label>
					</td>
					<td class="width-35">
						<select class='form-control' name='card_type'>
			 	               <option value ="">请选择</option>
			 	               <option value ="201">身份证</option>
			 	         </select>
					</td>
					<td class="width-15 active text-right">
						<label><font color="red">*</font>证件号码:</label>
					</td>
					<td class="width-35">
						<input name="card_no" class="form-control" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active text-right">
						<label><font color="red">*</font>姓名:</label>
					</td>
					<td class="width-35">
						<input name="full_name" class="form-control" />
					</td>
					<td class="width-15 active text-right">
						<label><font color="red">*</font>产品名称:</label>
					</td>
					<td class="width-35">
						<select  class='form-control' name='product_name'>
			 	              <option value ="">请选择</option>
			 	         </select>
					</td>
					<!-- <td class="width-15 active text-right">
						<label><font color="red">*</font>业务系统userid:</label>
					</td>
					<td class="width-35">
						<input name="ywxt_userid" class="form-control" />
					</td> -->
				</tr>
				
<!-- 				<tr>
					<td class="width-15 active text-right">
						<label><font color="red">*</font>到期时间:</label>
					</td>
					<td class="width-35">
						<input name="expired_date" type="date" class="form-control input-group date" />
					</td>
					<td class="width-15 active text-right">
						<label><font color="red">*</font>银行名称:</label>
					</td>
					<td class="width-35">
						<select  class='form-control' name='channel_name'>
			 	               <option value ="">请选择</option>
			 	         </select>
					</td>
				</tr> -->

			</tbody>
		</table>
	</form:form>
	
<script type="text/javascript">
	
	$(function(){
		//设置银行类型
		setSelectContent("channel_name","${adminPath}/tbs/dmwhchannel/getAllChannel","channelName","channelComment");
		setSelectContent("product_name","${adminPath}/tbs/dmwhproduct/getAllProduct","productName","productComment");
	});
	

	function confirm(){
		var nodes = $(".form-control");
		var data = {};
		for(var i=0;i < nodes.length;i++){
			var node = nodes[i];
			var name = node.name;
			var value = $(node).val();
			data[name] = value;
		}
		return data;
	}
</script>

<script src="/tbs-web/static/common/js/curdtools_jqgrid.js"></script>

</body>
</html>