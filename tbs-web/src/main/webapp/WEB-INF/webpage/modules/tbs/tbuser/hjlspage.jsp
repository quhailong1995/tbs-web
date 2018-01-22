<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>户籍历史</title>
    <meta name="decorator" content="grid-select"/>
          <style type="text/css">
      	.showli1{
      		width:70px;
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
      	 border: 1px solid #B3B3B3;
      	 margin-right:10px;
      	}
    .showli3{
      		width:20%;
      		float:left;
      		list-style: none;
      		text-align:center;
      	}
    /*  .jqGrid_wrapper{
      	width:1500px;
      	} */
      </style>
      <script type="text/javascript">
		 $(function(){
			
			//$("#gbox_authDataDetailGridIdGrid").attr("width","1500px");
			
			
		})



</script>
      
</head>

<body class="white-bg">
			<div style="width:100%;overflow:hidden;margin-bottom:15px;margin-top:15px;border:1px solid #B3B3B3;height:50px;padding-top:10px">
	 			<ul>
	 			<li class="showli1">证件号码:</li><li class="showli2">&nbsp;&nbsp;&nbsp;&nbsp;${tbUser["ZJHM"]}</li>
				<li class="showli1">姓名:</li><li class="showli2">&nbsp;&nbsp;&nbsp;&nbsp;${tbUser["XM"]}</li>
				
	 			</ul>
	 		</div>
 <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab_dbinfo" aria-expanded="true">户籍</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfo" aria-expanded="false">户籍历史</a></li>
                         <li class=""><a data-toggle="tab" href="#tab_page2info" aria-expanded="false">并档用户</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab_dbinfo" class="tab-pane active">
                            <div class="panel-body">
                                <div class="jqGrid_wrapper">
		                          
		                          <grid:grid id="tbuserGridId" multiselect="false" url="${adminPath}/tbs/tbuser/gettbuser?id=${tbUser['ID'] }" rowNum="500"  multiSort="false" sortorder="asc">	                           
										                           	
									<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
									
								    
									<grid:column label="ID"  name="ID" />
								    <grid:column label="HJID"  name="HJID"  /> 
								    
								    <grid:column label="XM"  name="XM" />
								    <grid:column label="ZJHM"  name="ZJHM"/>
								    <grid:column label="ZJLX_DM"  name="ZJLX_DM" />
								    <grid:column label="SJHM"  name="SJHM"/>
								    <grid:column label="CREATE_DATE"  name="CREATE_DATE"/>
								    <grid:column label="UPDATE_DATE"  name="UPDATE_DATE"/>
								    
								          
								</grid:grid>
		                       
		                          
		                        </div>
                            </div>
                        </div>
                        <div id="tab_pageinfo" class="tab-pane" style="display:block">
                            <div class="panel-body">
                                 <div class="jqGrid_wrapper">
			                          <grid:grid id="hjuserGridId" multiselect="false" url="${adminPath}/tbs/tbuser/gethjuser?hjid=${tbUser['HJID'] }" rowNum="500"  multiSort="false" sortorder="asc">	                           
											                           	
										<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
										
									    
										<grid:column label="HJID"  name="HJID" />
									    <grid:column label="HJH"  name="HJH"  /> 
									    
									    <grid:column label="XM"  name="XM" />
									    <grid:column label="ZJHM"  name="ZJHM"/>
									    <grid:column label="ZJLX_DM"  name="ZJLX_DM" />
									    <grid:column label="SJHM"  name="SJHM"/>
									    <grid:column label="Y_YWZJ"  name="Y_YWZJ"/>
									    <grid:column label="LYXT"  name="LYXT"/>
									    <grid:column label="ETL_STAMP"  name="ETL_STAMP"/>
								    
								   
      
	      
									          
									</grid:grid>
		                        </div>
                            </div>
                        </div>
                        
                        <div id="tab_page2info" class="tab-pane" style="display:block">
                            <div class="panel-body">
                                 <div class="jqGrid_wrapper">
			                          <grid:grid id="bduserGridId" multiselect="false" url="${adminPath}/tbs/tbuser/getbduser?hjid=${tbUser['HJID'] }" rowNum="500"  multiSort="false"  sortorder="asc">	                           
											                           	
										<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
										
									    
										<grid:column label="HJBDID"  name="HJBDID" />
									    <grid:column label="Y_HJID"  name="Y_HJID"  /> 
									    
									    <grid:column label="XM"  name="XM" />
									    <grid:column label="ZJHM"  name="ZJHM"/>
									    <grid:column label="ZJLX_DM"  name="ZJLX_DM" />
									    <grid:column label="SJHM"  name="SJHM"/>
									     <grid:column label="ETL_STAMP"  name="ETL_STAMP"/>
								    <grid:column label="BDSJ"  name="BDSJ"/>
	      
									          
									</grid:grid>
		                        </div>
                            </div>
                        </div>
                       
                    </div>
                </div>
                

 <html:js name="simditor,jqgrid,jqGrid_curdtools,jqGrid_curdtools_inline" />
<%-- <script src="${staticPath}/common/js/common_create.js"></script> --%>
<script type="text/javascript">
 $(function(){
	 $("table").unbind();
	 $("th").unbind("click");
		
	/* 	$(".tab-pane:eq(1)").css({'display':'block'});
		$(".tab-pane:eq(2)").css({'display':'block'}); */
		
		$("#pager_tbuserGridId_center").hide();
		$("#pager_hjuserGridId_center").hide();
		$("#pager_bduserGridId_center").hide();
		$(".ibox-title").remove();
		
		
		$(".tabs-container ul li:eq(0)").click(function(){
		  	$("#tbuserGridIdGrid").setGridWidth($(window).width()-120);
		 });
		$(".tabs-container ul li:eq(1)").click(function(){
		  	$("#hjuserGridIdGrid").setGridWidth($(window).width()-120);
		 });
		$(".tabs-container ul li:eq(2)").click(function(){
		  	$("#bduserGridIdGrid").setGridWidth($(window).width()-120);
		 });
	
})
$(window).load(function(){

 	$(".tab-pane:eq(1)").attr("style","");
	$(".tab-pane:eq(2)").attr("style",""); 
	
})


</script>
 
</body>

</html>