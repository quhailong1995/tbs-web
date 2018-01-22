<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>授权数据追溯</title>
  <meta name="decorator" content="grid-select"/>
  
  <style type="text/css">


</style>
<script type="text/javascript">
$(document).ready(function(){
	
})
</script>
</head>
<body >

	
	
	<div class="tabs-container" style="height:100%">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab_dbinfo" aria-expanded="true">前置数据</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfo" aria-expanded="false">集市数据</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfojinsan" aria-expanded="false">金三核心</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfogss" aria-expanded="false">GSS个税库</a></li>
                    </ul>
                    <div class="tab-content" >
                        <div id="tab_dbinfo" class="tab-pane active">
                            <div class="panel-body">
                                
									<grid:grid id="authDataDetailGridId" rowNum="500" multiselect="false" url="${adminPath}/tbs/query/userAuth/authDataDetail?userId=${userId}&productId=${productId}&userFullName=${userFullName }"   multiSort="false" sortname="skssqq" sortorder="desc">	                           
										                           	
									<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
									
								    
									<grid:column label="用户"  name="userName" />
								    <grid:column label="产品"  name="productComment"  /> 
								    
								    <grid:column label="税款所属期起"  name="skssqq" />
								    <grid:column label="税款所属期止"  name="skssqz"/>
								    <grid:column label="个人所得税所得项目代码"  name="grsdssdxmDm" />
								    <grid:column label="收入额"  name="sre"/>
								    <grid:column label="社保费缴存数"  name="sbfjcs" />
								    <grid:column label="公积金缴存数"  name="gjjjcs" />
								    <grid:column label="应纳税额"  name="ynse" />
								    <grid:column label="实纳税额"  name="snse" />
								    <grid:column label="应纳税所得额"  name="ynssde" />
								    
								    <grid:column label="扣缴义务人名称"  name="kjywrmc" />
								    <grid:column label="扣缴义务人地址"  name="kjywrdz" />
								    
								    <grid:column label="加工时间"  name="etlDate" />
							
								</grid:grid>
		                           
		                    
                            </div>
                        </div>
                        <div id="tab_pageinfo" class="tab-pane">
                            <div class="panel-body">
                                 
		                           <grid:grid id="dmDataDetailGridId" multiselect="false" url="${adminPath}/tbs/query/userAuth/queryJishiData?hjid=${hjid }" rowNum="500"  multiSort="false">	                           
										                           	
									<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
									
								    
									<grid:column label="ID"  name="ID" />
								    <grid:column label="HJID"  name="HJID"  /> 
								    
								    <grid:column label="KJYWRDJXH"  name="KJYWRDJXH" />
								    <grid:column label="SDQJQ"  name="SDQJQ"/>
								    <grid:column label="SDQJZ"  name="SDQJZ" />
								    <grid:column label="SQNY"  name="SQNY"/>
								    <grid:column label="SFZJLX_DM"  name="SFZJLX_DM" />
								    <grid:column label="SFZJHM"  name="SFZJHM" />
								    <grid:column label="XM"  name="XM" />
								    <grid:column label="GRSDSSDXM_DM"  name="GRSDSSDXM_DM" />
								    <grid:column label="ZSPM_DM"  name="ZSPM_DM" />
								    
								    <grid:column label="SRE"  name="SRE" />
								    <grid:column label="MSSDJE"  name="MSSDJE" />
								    
								    <grid:column label="YLBXJE"  name="YLBXJE" />
								    <grid:column label="YILBXJE"  name="YILBXJE" />
								     
								     
								    <grid:column label="SYBXJE"  name="SYBXJE" />
								    <grid:column label="CCYZJE"  name="CCYZJE" />
								       
								       
								    <grid:column label="YXKCDFYE"  name="YXKCDFYE" />
								    <grid:column label="SQKCXMQTJE"  name="SQKCXMQTJE" />
								    <grid:column label="SQKCXMHJ"  name="SQKCXMHJ" />
								    <grid:column label="JCFYE"  name="JCFYE" />
								          
								    <grid:column label="ZYKCDJZE"  name="ZYKCDJZE" />
								    <grid:column label="YNSSDE"  name="YNSSDE" />
								          
								    <grid:column label="SL_1"  name="SL_1" />
								    <grid:column label="SSKCS"  name="SSKCS" />
								          
								    <grid:column label="YNSE"  name="YNSE" />
								    <grid:column label="JMSE"  name="JMSE" />
								    <grid:column label="YINGKJSE"  name="YINGKJSE" />
								          
								    <grid:column label="YKJSE"  name="YKJSE" />
								    <grid:column label="YBTSE"  name="YBTSE" />
								    <grid:column label="SJGSDQ"  name="SJGSDQ" />
								    <grid:column label="BZ"  name="BZ" />
								          
								    <grid:column label="GJHDQSZ_DM"  name="GJHDQSZ_DM" />
								          
								    <grid:column label="BTFDWDJXH"  name="BTFDWDJXH" />
								    <grid:column label="TZDK"  name="TZDK" />
								    <grid:column label="SYJKX"  name="SYJKX" />
								    <grid:column label="NJ"  name="NJ" />
								    <grid:column label="RKRQ"  name="RKRQ" />
								    <grid:column label="ETL_DATE"  name="ETL_DATE" />
								    <grid:column label="Y_YWZJ"  name="Y_YWZJ" />
								                    
								    <grid:column label="JYLSH"  name="JYLSH" />
								    <grid:column label="MXXH"  name="MXXH" />
								                    
								          
								          
								</grid:grid>
		                       
                            </div>
                        </div>
                        
                        
                         <div id="tab_pageinfojinsan" class="tab-pane active">
                            <div class="panel-body">
                            	<grid:grid id="jinSanCoreGridId" rowNum="500" multiselect="false" url="${adminPath}/tbs/query/userAuth/queryJinSanCore?hjid=${hjid}"   multiSort="false" sortname="skssqq" sortorder="desc">	                           
                            		<grid:column label="JYLSH"  name="JYLSH" />
								    <grid:column label="MXXH"  name="MXXH"  /> 
								    
								    <grid:column label="SBXH"  name="SBXH" />
								    <grid:column label="SFMXSB"  name="SFMXSB"/>
								    <grid:column label="SDQJQ"  name="SDQJQ" />
								    <grid:column label="SDQJZ"  name="SDQJZ"/>
								    
								    <grid:column label="SFZJLX_DM"  name="SFZJLX_DM" />
								    <grid:column label="SFZJHM"  name="SFZJHM" />
								    <grid:column label="XM"  name="XM" />
								    <grid:column label="DJXH"  name="DJXH" />
								    <grid:column label="GRSDSSDXM_DM"  name="GRSDSSDXM_DM" />
								    <grid:column label="ZSPM_DM"  name="ZSPM_DM" />
								    <grid:column label="SRE"  name="SRE" />
								      
								    <grid:column label="MSSDJE"  name="MSSDJE" />
								    <grid:column label="YLBXJE"  name="YLBXJE" />
								    <grid:column label="YILBXJE"  name="YILBXJE" />
								    <grid:column label="SYBXJE"  name="SYBXJE" />
								    <grid:column label="ZFGJJJE"  name="ZFGJJJE" />
								    <grid:column label="CCYZJE"  name="CCYZJE" />
								    <grid:column label="YXKCDFYE"  name="YXKCDFYE" />
								       
								    <grid:column label="SQKCXMQTJE"  name="SQKCXMQTJE" />
								    <grid:column label="SQKCXMHJ"  name="SQKCXMHJ" />
								    <grid:column label="JCFYE"  name="JCFYE" />
								    <grid:column label="ZYKCDJZE"  name="ZYKCDJZE" />
								    <grid:column label="YNSSDE"  name="YNSSDE" />
								    <grid:column label="SL_1"  name="SL_1" />
								    <grid:column label="SSKCS"  name="SSKCS" />
								       
								    <grid:column label="YNSE"  name="YNSE" />
								    <grid:column label="JMSE"  name="JMSE" />
								    <grid:column label="YINGKJSE"  name="YINGKJSE" />
								    <grid:column label="YKJSE"  name="YKJSE" />
								    <grid:column label="YBTSE"  name="YBTSE" />
								    <grid:column label="LRRQ"  name="LRRQ" />
								    <grid:column label="LRR_DM"  name="LRR_DM" />
								       
								    <grid:column label="XGRQ"  name="XGRQ" />
								    <grid:column label="XGR_DM"  name="XGR_DM" />
								    <grid:column label="SJGSDQ"  name="SJGSDQ" />
								    <grid:column label="BZ"  name="BZ" />
								    <grid:column label="GJHDQSZ_DM"  name="GJHDQSZ_DM" />
								    <grid:column label="SJTB_SJ"  name="SJTB_SJ" />
								    <grid:column label="BTFDWDJXH"  name="BTFDWDJXH" />
								    <grid:column label="TZDK"  name="TZDK" />
								    <grid:column label="SYJKX"  name="SYJKX" />
								    <grid:column label="NJ"  name="NJ" />
								    <grid:column label="XSDL"  name="XSDL" />
								      
								       
								       
                            	
                            	</grid:grid>
                            </div>
                         </div>
                         
                         <div id="tab_pageinfogss" class="tab-pane active">
                            <div class="panel-body">
                            	<grid:grid id="gssPTaxGridId" rowNum="500" multiselect="false" url="${adminPath}/tbs/query/userAuth/queryGSSPT?zjhm=${zjhm}&userFullName=${userFullName }"   multiSort="false" >	                           
                            	    <grid:column label="PERSONALTAXID"  name="PERSONALTAXID" />
								    <grid:column label="NSRDZDAH"  name="NSRDZDAH"  /> 
								    
								    <grid:column label="TAXPAYERID"  name="TAXPAYERID" />
								    <grid:column label="REVENUE"  name="REVENUE"/>
								    <grid:column label="FIDTYPE"  name="FIDTYPE" />
								    <grid:column label="FIDNUM"  name="FIDNUM"/>
								    <grid:column label="FNAME"  name="FNAME" />
								    <grid:column label="FYEAR"  name="FYEAR" />
								    <grid:column label="FMONTH"  name="FMONTH" />
								    <grid:column label="FTAXITEM"  name="FTAXITEM" />
								    
								    <grid:column label="FCONTAXITEM"  name="FCONTAXITEM" />
								    <grid:column label="TRANNO"  name="TRANNO" />
								    <grid:column label="FINCOME"  name="FINCOME" />
								    <grid:column label="FDERATE"  name="FDERATE" />
								    <grid:column label="FTAXINCOME"  name="FTAXINCOME" />
								    <grid:column label="FTAXRATE"  name="FTAXRATE" />
								    <grid:column label="FDEDUCT"  name="FDEDUCT" />
								    <grid:column label="FTAX"  name="FTAX" />
								    <grid:column label="FFREETAX"  name="FFREETAX" />
								    <grid:column label="FPAIED"  name="FPAIED" />
								    <grid:column label="FPAYTAX"  name="FPAYTAX" />
								    
								    <grid:column label="NTERMDATE"  name="NTERMDATE" />
								    <grid:column label="NDECDATE"  name="NDECDATE" />
								    <grid:column label="NSUCCEED"  name="NSUCCEED" />
								    <grid:column label="NKDATE"  name="NKDATE" />
								    <grid:column label="NTICKET"  name="NTICKET" />
								    <grid:column label="TICKET"  name="TICKET" />
								    
								    <grid:column label="SBXH"  name="SBXH" />
								    <grid:column label="ISXH"  name="ISXH" />
								    <grid:column label="NNATION"  name="NNATION" />
								    <grid:column label="NPUBTAX"  name="NPUBTAX" />
								    <grid:column label="FDECLAREINCOME"  name="FDECLAREINCOME" />
								    <grid:column label="SSSQ"  name="SSSQ" />
                            	
                            	</grid:grid>
                            </div>
                         </div>
                        
                       
                    </div>
                </div>
	
	
	
	

<html:js name="simditor,jqgrid,jqGrid_curdtools,jqGrid_curdtools_inline" />
<script type="text/javascript">

var jinsanTab_flag=0;
var gssptTab_flag=0;
var dmdataTab_flag=0;

$(document).ready(function(){
	basicInit();
	$("#pager_authDataDetailGridId_center").hide();
	$(".ibox-title").remove();
	$(".tabs-container ul li:eq(0)").click(function(){
	  	$("#authDataDetailGridIdGrid").setGridWidth($(window).width()-120);
	 });
 	 $(".tabs-container ul li:eq(1)").click(function(){  
 		 if(dmdataTab_flag==0){//alert();
 			initdmDataDetailGridIdTable();
 			dmdataTab_flag=1;
 		 }
 	  basicInit();
 	  initStyle('dmDataDetailGridId');
		
	 }); 
 	 $(".tabs-container ul li:eq(2)").click(function(){
 		if(jinsanTab_flag==0){//alert();
 			initjinSanCoreGridIdTable();
 			jinsanTab_flag=1;
 		} 
 		basicInit();
 		initStyle('jinSanCoreGridId');
 	
 	 });
 	 $(".tabs-container ul li:eq(3)").click(function(){
  		if(gssptTab_flag==0){//alert($(window).width());
  			initgssPTaxGridIdTable();
  			gssptTab_flag=1;
  		} 
  		basicInit();
  		initStyle('gssPTaxGridId');
  		
  	 });
 	  
 	 /* $("#gbox_dmDataDetailGridIdGrid") */ $(window).resize(function(){
	 	$("#tab_dbinfo").addClass("active");
		$("#tab_pageinfo").removeClass("active");
		$("#tab_pageinfogss").removeClass("active");
		$("#tab_pageinfojinsan").removeClass("active");
		$(".tabs-container ul li:eq(0)").addClass("active");
		$(".tabs-container ul li:eq(1)").removeClass("active");
		$(".tabs-container ul li:eq(2)").removeClass("active");
		$(".tabs-container ul li:eq(3)").removeClass("active");

		
 	 }); 
 	  
 	 $("#gbox_dmDataDetailGridIdGrid").bind('DOMNodeInserted', function(e) {  
	     //有变化后执行的内容
 		
		});  
 	  
 	
	
})


function basicInit(){
	$("table").unbind();
	$("th").unbind("click");
	$(".ui-jqgrid-bdiv").css("overflow-x","hidden");
	$(".ui-jqgrid-bdiv").css("max-height","300px");
}

function initStyle(obj){
	$("#"+obj+"Grid").setGridWidth(5038);
	$("#gbox_"+obj+"Grid").width($("#gbox_authDataDetailGridIdGrid").width());
	$("#gview_"+obj+"Grid").width($("#gbox_authDataDetailGridIdGrid").width());
	$("#pager_"+obj).width($("#gbox_"+obj+"Grid").width());
	$("#pager_"+obj+"_center").hide();
}


$(window).load(function(){

	/*  $(window).resize(function(){   alert("1");
		$("#dmDataDetailGridIdGrid").setGridWidth(5038);
		$("#gbox_dmDataDetailGridIdGrid").width($("#gbox_authDataDetailGridIdGrid").width());
		$("#gview_dmDataDetailGridIdGrid").width($("#gbox_authDataDetailGridIdGrid").width());
		$("#pager_dmDataDetailGridId").width($("#gbox_dmDataDetailGridIdGrid").width());
     }); */
	
	
})

</script>
</body>
</html>