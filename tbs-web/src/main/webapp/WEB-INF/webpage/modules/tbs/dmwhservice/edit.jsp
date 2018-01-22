<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>系统服务管理</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,jqgrid" />
    
    <script type="text/javascript">
    
   
    $(document).ready(function(){
    
    	//更新初始化
    	if("${data.serviceType}"!=null&&"${data.serviceType}"!=""){
    		if("${data.serviceType}"==1){
    			$("#dmWhSerContent_div").show();
    		}
    		$("#serviceType_td select").val("${data.serviceType}");
    	}
/*     	if($("#serviceType_td select option:selected").val()=="1"){
    		 $("#dmWhSerContent_div").show();
    		//$("#isOrNo select").find("option[value =1]").attr("selected","selected");
    	}*/
    	$("#serviceType_td select").click(function(){
    	//alert($("#serviceType_td select option:selected").val());
    		if($("#serviceType_td select").val()=="1"){
    		
    			 $("#dmWhSerContent_div").show();
    			 return;
    		}
    		$("#dmWhSerContent_div").hide();
    	}) 
    	
    	
    	//服务号码唯一性检测
    	$("#dmWhServiceForm input[name='serviceName']").change(function(){
    		$this = $(this);
    		$.get("${adminPath}/tbs/dmwhservice/checkServiceName?serviceName="+$(this).val(), function(data, status) {
			     if(data=="0"){
			     	$this.parent().append('<label class="Validform_checktip Validform_wrong">已存在，请换一个</label>');
			     }
				});
    	});
    	
  })
    
    
    </script>
    
</head>

<body class="white-bg"  formid="dmWhServiceForm" beforeSubmit="beforeSubmit">
    <form:form id="dmWhServiceForm" modelAttribute="data"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>服务ID:</label>
		            </td>
					<td class="width-35">
						<form:input path="serviceName" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>服务名称:</label>
		            </td>
					<td class="width-35">
						<form:input path="serviceComment" htmlEscape="false" class="form-control"  datatype="*"    />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>服务类型:</label>
		            </td>
					<td class="width-35" id="serviceType_td">
						<form:select path="serviceType" htmlEscape="false" class="form-control" dict="fwlx"    datatype="*"     />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>服务类名:</label>
		            </td>
					<td class="width-35">
						<form:input path="methodPath" htmlEscape="false" class="form-control" datatype="*"       />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
				
					<td  class="width-15 active text-right">	
		              <label>备注:</label>
		            </td>
					<td class="width-35">
						<form:input path="remarks" htmlEscape="false" class="form-control"      />
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
	<div id="dmWhSerContent_div" class="row" style="display:none">
        <div class="tabs-container">
            <ul class="nav nav-tabs">
            	<li class="active"><a data-toggle="tab" href="#tab_dmWhSerContent" aria-expanded="true"></a></li>
            	<li ><a data-toggle="tab" href="#tab_tbAuthContent" aria-expanded="true"></a></li>
            </ul>
            <div class="tab-content">
                 <div id="tab_dmWhSerContent" class="tab-pane active">
                    <div class="panel-body">
                        <grid:grid id="dmWhSerContent"  datas="${dmWhSerContentList}"  gridShowType="form" pageable="false"  editable="true" sortname="sort">
							    <grid:column label="数据表"  name="tableName"  editable="true"       datatype="*"   />
							    <grid:column label="字段名"  name="columnName"  editable="true"       datatype="*"   />
							    <grid:column label="字段解释"  name="columnComment"  editable="true"        />
							    <grid:column label="节点代码"  name="nodeName"  editable="true"       datatype="*"   />
							    <grid:column label="节点中文描述"  name="bz"  editable="true"        />
							    <grid:column label="有效标志"  name="yxbz"  editable="true" datatype="*"   />
							    <grid:column label="排序"  name="sort"  editable="true" datatype="n"   />
						</grid:grid>
						
					</div>
            
          
            </div>
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
	});
	$(function(){
	   $(window).resize(function(){   
		   resizeGrid();
	   });
	});
	function resizeGrid(){
		 $("#dmWhSerContentGrid").setGridWidth($(window).width()-60);
		 $("#tbAuthContentGrid").setGridWidth($(window).width()-60);
	}

	/**
	*提交回调方法
	*/
	function beforeSubmit(curform){
		 //这里最好还是使用JSON提交，控制器改变提交方法，并使用JSON方式来解析数
		 //通过判断，如果有问题不提交
		 if(!initGridFormData(curform,"dmWhSerContent"))return false;
		 if(!initGridFormData(curform,"tbAuthContent"))return false;
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
</script>
<script src="${staticPath}/common/js/common_create.js"></script>
</body>
</html>