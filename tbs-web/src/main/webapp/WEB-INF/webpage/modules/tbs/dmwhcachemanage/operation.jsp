<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>缓存操作</title>
  <meta name="decorator" content="grid-select"/>
</head>
<body title="缓存操作">
 <input type="hidden" name="cacheId" value="${dmWhCachemanage.id }"/>
    <input type="hidden" name="cacheDm" value="${dmWhCachemanage.cacheDm }"/>
<grid:grid id="dmWhServermanageGridId"  url="${adminPath}/tbs/dmwhservermanage/availableServer" sortname="serverMc">
   
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="服务器名称"  name="serverMc"/>
    <grid:column label="ip"  name="serverIp" />
    <grid:column label="端口"  name="serverPort" />
    <grid:column label="应用代码"  name="appDm" />
    <grid:column label="所属应用"  name="app.appMc" />
    
    
    
    <grid:query label="所属应用"  name="appDm" queryMode="select" dict="qxz" condition="eq"/>
    <grid:query label="所属环境"  name="serverEnvironment" queryMode="select" dict="hj" condition="eq"/>
    
<%-- 	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/> --%>
	<grid:toolbar title="刷新缓存" function="refreshCache" />
	<grid:toolbar title="查看缓存" function="viewCache"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">
$(window).load(function(){
	var $this = $("#dmWhServermanageGridIdGridQuery select[name='appDm']");
		
	$.get("${adminPath}/tbs/dmwhappmanage/getAllApp",function(data,status){
		//alert(status);
		data = eval('(' + data + ')');
		for(var i in data){
			$this.append('<option value="'+data[i].appDm+'">'+data[i].appMc+'</option>');
		} 
		
		});	  


})


$(function(){
	
	
	
	
})

function refreshCache(title,url,gridId){
	
	
	testState(title,"refreshCache",gridId);
}



//var cacheData;


function viewCache(title,url,gridId){
	
	var ids;
	var rowsData = $("#"+gridId).jqGrid('getGridParam','selarrrow');
	var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');
	var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
	
	if(!multiselect)
	{
		if(rowData)
		{
			  rowsData[0]=rowData;
			  
		}
		
	}
    if (!rowsData || rowsData.length==0) {
	    top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
		return; 
	}
    if (rowsData.length>1) {
    	top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
		return;
	}
    
    var id = rowsData[0];
    ids=rowData;
    url=url.replace("{id}",id);   
    $.get("viewCache?ids="+ids,function(data,status){
    	localStorage.setItem("cacheData",data);
    	
		data = eval('(' + data + ')');
		//console.log(data);
		if(data.code=="0"){
			
			showCacheContentPage(data);
			return;
		}
		top.layer.alert(data.msg, {icon: 0, title:'提示'});
		
		});	           	          
}

function showCacheContentPage(data){
/* 	 var aa = data.data;
	var html = '<br><br>';
	
	for(var i in aa){
		html = html+JSON.stringify(aa[i])+'<br><br>';
	} */
	 
	
	var width = width?width:'70%';
	var height = height?height:'60%';
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: data.msg,
        maxmin: true, //开启最大化最小化按钮
	  content: "dmwhcachemanage/showCacheContentPage" ,
	  // content:html,
	    btn: ['关闭'],
		 cancel: function(index){ }
	}); 	
}

</script>

</body>
</html>