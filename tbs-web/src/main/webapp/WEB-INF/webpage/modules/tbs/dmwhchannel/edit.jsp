<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>银行接入管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
    
    <script type="text/javascript">
    	$(document).ready(function(){
    	//alert($("#yesOrNoIp input").val());
    	if($("#yesOrNoIp input").val()!=""){
    		$("#yesOrNoIp").show();
    		$("#isOrNo select").find("option[value =1]").attr("selected","selected");
    	}
    	
    	
    	
    	function _getRandomString(len) {  
		    len = len || 32;  
		    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; // 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1  
		    var maxPos = $chars.length;  
		    var pwd = '';  
		    for (i = 0; i < len; i++) {  
		        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));  
		    }  
		    return pwd;  
		    
		}
    	
    	$("#sjscmy").click(function(){
    			var randomStr =  _getRandomString();
			  $("#accessKey").val(randomStr);
    	});
    	
    	
    	$("#isOrNo select").click(function(){
    	
    	if($("#isOrNo select option:selected").val()==1){
    		$("#yesOrNoIp").show();
    		return;
    	}
    	$("#yesOrNoIp").hide();
    	
    	
    	})
  
    	//渠道号码唯一性检测
    	$("#dmWhChannelForm input[name='channelName']").change(function(){
    		$this = $(this);
    		$.get("${adminPath}/tbs/dmwhchannel/checkChannelName?channelName="+$(this).val(), function(data, status) {  
			     if(data=="0"){
			     	$this.parent().append('<label class="Validform_checktip Validform_wrong">已存在，请换一个</label>');
			     }
				});
    	});
    	
    	})
    </script>
    
</head>

<body class="white-bg"  formid="dmWhChannelForm">
    <form:form id="dmWhChannelForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active text-right"><label><font color="red">*</font>银行名称:</label>
					</td>
					<td class="width-35">
					<form:input path="channelComment" htmlEscape="false" class="form-control" datatype="*"    /> 
						<!-- <option value="宁波银行">宁波银行</option>
						<option value="中信银行">中信银行</option> -->
					
					<label class="Validform_checktip"></label>
					</td>
				</tr>

				<tr>

					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>渠道号码:</label>
		            </td>
					<td class="width-35">
						<form:input path="channelName" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>

				</tr>


				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>访问秘钥:</label>
		            </td>
					<td class="width-35">
						<form:input path="accessKey" htmlEscape="false" class="form-control"  datatype="*"       />
						<label class="Validform_checktip"></label>
						
					</td>
					<td><a id="sjscmy" href="javascript:;">自动生成</a></td>

				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>数据源:</label>
		            </td>
					<td class="width-35">
						<form:input path="datasourceName" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
					
				</tr>
				
				<tr id="isOrNo">
					<td  class="width-15 active text-right">	
		              <label>是否限制ip:</label>
		            </td>
					<td class="width-35">
						<form:select path="" htmlEscape="false" class="form-control">
							<option value="0">否</option>
							<option value="1">是</option>
						</form:select>
						<label class="Validform_checktip"></label>
					</td>
				
				</tr>
				
				
				<tr id="yesOrNoIp" style="display:none">
				
					<td  class="width-15 active text-right">	
		              <label>IP列表:</label>
		            </td>
					<td class="width-35">
						<form:input path="limitipList" htmlEscape="false" class="form-control"   />	
					</td>
				
				
				</tr>
				
				
				<tr>
					<td  class="width-15 active text-right">	
		              <label>备注:</label>
		            </td>
					<td class="width-35">
						<form:input path="remarks" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
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

		  		</tr>
		  		<tr>
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
<script src="${staticPath}/common/js/common_create.js"></script>
</body>
</html>