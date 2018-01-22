<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>用户贷款查询</title>
  <meta name="decorator" content="list"/>
</head>
<body title="用户贷款查询">
<grid:grid id="userauthId" multiselect="false" url="${adminPath}/tbs/query/userLoan/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="证件号码"  name="cardNo"  query="true" queryModel="input" condition="like"   />
	<grid:column label="姓名"  name="fullName"  />
	<grid:column label="贷款银行"  name="channelName"  dict=" " query="true"  queryMode="select" condition="eq" />
	<grid:column label="开始日期" name="loanBegin" query="true" queryMode="date" condition="ge" />
	<grid:query label="截止日期" name="loanEnd" queryMode="date" condition="le" />
	<grid:column label="贷款期限" name="term" />
    
    <grid:toolbar title="导出当前页" icon="fa-external-link" function="exportExcelAll(false)"  />
    <grid:toolbar title="导出全部" icon="fa-external-link" function="exportExcelAll(true)"  />
	<grid:toolbar title="搜索" icon="fa-search" layout="right" function="search"  />
	<grid:toolbar function="reset" />
</grid:grid>
<script type="text/javascript">
	
	
	$(function(){
		setChannelNames();
	});
	
	//设置下拉框-银行名称的内容
	function setChannelNames(){
		var channelName = $("select[name='channelName']");
		if(channelName.find("option").length > 1){
			return;
		}
		$.post("${adminPath}/tbs/dmwhchannel/getAllChannel",function(data,status){
			var channelList = eval('(' + data + ')');
			for(var i in channelList){
				channelName.append('<option value="'+channelList[i].id+'">'+channelList[i].channelComment+'</option>');
			}
		});
	}
	
	
	/**
	*导出excel方法
	*isAll:true为全部，false为当前页
	*/
	function exportExcelAll(isAll){
		exportExcel(getExportInfo(isAll),"${adminPath}/tbs/query/userLoan/exportExcelAll");
	}
	
	/**
	*组装导出excel需要的信息
	*/
	function getExportInfo(isAll){
		var data = {
				"isAll":isAll,
				"fileName":"用户贷款信息",
				"tableName":"用户贷款信息列表",
				"sheetName":"贷款",
				"columnKeys":"cardNo,fullName,channelName,loanBegin,term",
				"columnValues":"证件号码,姓名,贷款银行,开始日期,贷款期限",
		};
		var exportInfo = getParams("userauthIdGrid");//获得查询条件
		exportInfo["info"] = JSON.stringify(data);
		return exportInfo;
	}
	
	
	/**
	*搜索方法
	*/
/* 	function searchAll(){
		var cardNo = $("input[name='cardNo']").val();
		var channelId = $("select[name='channelName']").val();
		if((cardNo == null ||cardNo == "")&&(channelId == null ||channelId == "")){
			top.layer.alert("证件号码与贷款银行不能同时为空！", {icon: 0, title:'警告'});
			return;
		}
		search('userauthIdGrid');
	} */
</script>
</body>
</html>