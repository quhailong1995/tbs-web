<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>缓存操作</title>
<meta name="decorator" content="grid-select" />
<script type="text/javascript">
	
</script>
</head>

<body class="undefined pace-done">
	<%-- <c:forEach items="${headName } " var="h">
		<div>${h }</div>
	</c:forEach> --%>

	<grid:grid id="cacheDataGridId" multiselect="false" pageable="false"
		sortable="false" url="">





	</grid:grid>


	<tr role="row"  tabindex="-1" class="jqgrow ui-row-ltr success">
		<td role="gridcell" style="text-align:left"></td>
		
	</tr>



<script type="text/javascript">
$(window).load(function(){
	
	
	
		
		
		
		 $.get("showCacheContent",function(data,status){
			
			 
			 var results = eval('(' + data.results + ')');
			
			// data = eval('(' + data + ')');
			 var aa = data.head.split(","); 
			 var aaWidth = $(".ui-jqgrid-hdiv").width()/aa.length;
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
				 		trHtml = trHtml+ '<td role="gridcell" style="text-align:left">'+results[i][temaa]+'</td>';
				 	}
				 
				 trHtml = trHtml+'</tr>';
				// alert(trHtml);
				 $("table tbody").append(trHtml);
			 }
			 
			 
			 
		 });
		
		
		
		
	
	

})
</script>
</body>
</html>