<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>万能查询工具</title>
<meta name="decorator" content="list" />

<script src="${staticPath}/vendors/sql-formatter-master/sql-formatter.min.js"></script>
<script type="text/javascript">
//${staticPath}/vendors/sqlFormatte/
/* $(document).ready(function(){
	//format();
}) */


	
</script>

<style>


		/* ECHARTS  */
		.echarts {
		    height: 250px!important;
		}
		.portlet2 {
	    clear:none!important;
	    }
	    .form-group {
		    display: inline-block;
		    margin-bottom: 0;
		    vertical-align: middle;
		}
		.control-label {
		    margin-bottom: 0;
		    vertical-align: middle;
		}
		.form-control {
		    display: inline-block;
		    width: auto;
		    vertical-align: middle;
		}
			
	</style>


</head>

<body class="undefined pace-done" title="万能查询工具">


			<div  style="width:100%;float:left;margin-bottom:20px;padding-left:10px;padding-right:60px">	
					<div style="min-width:600px" class="pull-left">
						   <div class="form-group" id="serviceSelect">
				 	          <label class="control-label">数据源：</label>
				 	    	 	<select class="form-control" name="dataSource">
				 	    	 		<option value="null">默认</option>
				 	    	 		<option value="db_qz">前置</option>
				 	    	 		<option value="db_dm">仓库</option>
				 	    	 	</select>
				 	    	 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	    	 	<label class="control-label">结果集：</label>
				 	    	 	<select class="form-control" name="resultCount">
				 	    	 		<option value="50">50条</option>
				 	    	 		<option value="100">100条</option>
				 	    	 		<option value="150">150条</option>
				 	    	 		<option value="200">200条</option>
				 	    	 		<option value="500">500条</option>
				 	    	 	</select>
				 	    	 	
				 	     	</div>
					</div>
					<div class="pull-right">
					<button id="executeQueryButton" class="btn btn-sm btn-info"><i class="fa "></i> 查询</button>
					
					</div>
			</div>

					
 <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#tab_dbinfo" aria-expanded="true">SQL语句</a></li>
                        <li class=""><a data-toggle="tab" href="#tab_pageinfo" aria-expanded="false">结果集</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab_dbinfo" class="tab-pane active">
                            <div class="panel-body">
                                <div class="jqGrid_wrapper">
		                          <textarea id="testRequesttextarea" name="testRequest" style="border:none" rows="25" cols="100%" ></textarea>
		                        </div>
                            </div>
                        </div>
                        <div id="tab_pageinfo" class="tab-pane">
                            <div class="panel-body" style="padding:0">
                                 <div class="jqGrid_wrapper">
		                        	 <grid:grid id="universalQueryUtilGridId" multiselect="false" pageable="false" sortable="false" url=""></grid:grid>
					
		                        </div>
                            </div>
                        </div>
                       
                    </div>
                </div>


	

<script type="text/javascript">
$(window).load(function(){
	
	$("#universalQueryUtilGridIdGridQuery").remove();
	$(".jqGrid_wrapper.uadmin-grid-margin").css("margin-top","0");
	$("#gbox_universalQueryUtilGridIdGrid").css("border","none");
	$("textarea[name='testRequest']").css("focus","disable");
	
		
	$("#gbox_universalQueryUtilGridIdGrid").css("width","inherit");
	$("#gview_universalQueryUtilGridIdGrid").css("width","100%");
	$(".ui-jqgrid-hdiv").css("width","100%");
	$(".ui-jqgrid-hbox").css("width","100%");
	//$(".ui-jqgrid-hbox").css("padding-right","0");
	
	$("table").css("width","100%");
	
		
	$(".ui-jqgrid-bdiv").css("width","100%");
	
	$("#executeQueryButton").click(function(){
		executeQuery();
	});
	
	

})

	function executeQuery(){
	$("#tab_pageinfo").addClass("active");
	$("#tab_dbinfo").removeClass("active");
	$(".tabs-container ul li:eq(1)").addClass("active");
	$(".tabs-container ul li:eq(0)").removeClass("active");
	
	$("table thead tr th").remove();
	$("table tbody tr").remove();
	
		var sqlStatement = "select * from ("+$("textarea[name='testRequest']").val()+")  WHERE ROWNUM<="+ $("select[name='resultCount']").val();
		var dataSource = $("select[name='dataSource']").val();
		
		 $.get("${adminPath}/tbs/universalqueryutil/excuteQuery",{sqlStatement:sqlStatement,dataSource:dataSource},function(data,status){
			 
			 $("span#reponseMessage").remove();
			 
			 if(data.code=="-1"){
				 $(".jqGrid_wrapper.uadmin-grid-margin").prepend("<span id='reponseMessage'>"+data.message+"</span>");
				 return;
			 }
			 
			 
			// alert(data.results);
			 var results = eval('(' + data.results + ')');
			 
			
			// data = eval('(' + data + ')');
			 var aa = data.head.split(","); 
			 var aaWidth = $(".ui-jqgrid-hdiv").width()/aa.length;
			 //var aaWidth = 180;
			// alert(aaWidth);
			 for(var i=0;i<aa.length;i++){
				 $("table tbody tr.jqgfirstrow").append('<td role="gridcell" style="height:0px;width:'+aaWidth+'px;"></td>');
				 
				 var htmlHead = '<th id="cacheDataGridIdGrid_serverMc" role="columnheader"'
						+'class="ui-th-column ui-th-ltr " style="width: '+aaWidth+'px;"><span'
						+'class="ui-jqgrid-resize ui-jqgrid-resize-ltr"'
						+'style="cursor: col-resize;">&nbsp;</span>'
						+'<div class="ui-th-div ui-jqgrid-sortable"'
						+'id="jqgh_cacheDataGridIdGrid_serverMc">'
						+aa[i]+'<span class="s-ico" style="display:none"><span sort="asc"'
						+'class="ui-grid-ico-sort ui-icon-asc ui-sort-ltr ui-disabled glyphicon glyphicon-triangle-top"></span><span'
						+'sort="desc"'
						+'class="ui-grid-ico-sort ui-icon-desc ui-sort-ltr ui-disabled glyphicon glyphicon-triangle-bottom"></span></span>'
						+'</div></th>';
				 
				 
				 $("table thead tr").append(htmlHead);
				
			 }
			
			 for(var i=0;i<data.total;i++){
				
				 var trHtml = '<tr role="row"  tabindex="-1" style="pointer-events:none" class="jqgrow ">';
				 	for(var j=0;j<aa.length;j++){
				 		
				 		var temaa = aa[j];
				 		// alert(results[i][temaa]);
				 		if(results[i][temaa]==undefined){
				 			results[i][temaa] ="";
				 		}
				 		
				 		trHtml = trHtml+ '<td role="gridcell" style="text-align:left">'+results[i][temaa]+'</td>';
				 	}
				 
				 trHtml = trHtml+'</tr>';
				// alert(trHtml);
				 $("table tbody").append(trHtml);
			 }
			 
			 
			 
		 });
		 
		 
		 

		
	}
	
	
	
(function() {
   
    var textarea = document.getElementById('testRequesttextarea');
    var output = document.getElementById('testRequesttextarea');
    
    
    textarea.addEventListener('blur', format);
    
    $("#testRequesttextarea").keypress(function(event){
    	// alert("keypress");
   
 
   /*  	
        if(event.keyCode == "32") {
        	textarea.removeEventListener('input', format);
        	//$("#testRequesttextarea").unbind("input");
        }else{
        	textarea.addEventListener('input', format);
        } */
        	
    }); 
    
    
    
    
    //language.addEventListener('change', format);
   
    function format() {
    	
        console.time('formatting');

        output.value = sqlFormatter.format(textarea.value, {language: 'SQL'});

        console.timeEnd('formatting');
    }
    format();
})();
	
		
</script>
</body>
</html>
