<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>授权查询日志</title>
  <meta name="decorator" content="list"/>
</head>
<body title="授权查询日志">
<grid:grid id="authlogId" multiselect="false" url="${adminPath}/tbs/query/authLog/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="证件号码"  name="cardNo" query="true" queryModel="input" condition="like" width="150" />
	<grid:column label="姓名"  name="fullName" query="true" queryModel="input" condition="like" />
	<grid:column label="授权渠道"  name="channelComment" dict=" " query="true" queryMode="select" condition="eq" />
	<grid:column label="服务名称" name="serviceComment" dict=" " query="true" queryMode="select" condition="eq" />
	<grid:column label="查询时间" name="lrrq" />
	<grid:column label="访问码" name="accessToken" width="200" />
	
	<grid:column hidden="true" label="用户id" name="userid" />
	<grid:column label="sys.common.opt"  name="opt" formatter="button"/>
	<grid:button title="验证"  groupname="opt" onclick="verify(this)" outclass="btn-info" />
	<grid:button title="消息内容"  groupname="opt" onclick="showMessage(this)" outclass="btn-info" />
	<grid:column hidden="true" label="发送键" name="sendSeq" />
	<grid:column hidden="true" label="发送键" name="serviceName" />
    
    <%-- <grid:toolbar title="导出当前页" icon="fa-external-link" function="exportExcelAll(false)"  />
    <grid:toolbar title="导出全部" icon="fa-external-link" function="exportExcelAll(true)"  /> --%>
	<grid:toolbar function="search"  />
	<grid:toolbar function="reset" />
</grid:grid>
<script type="text/javascript">
	
	
	$(function(){
		//setChannelNames();
		//设置银行类型
		setSelectContent("channelComment","${adminPath}/tbs/dmwhchannel/getAllChannel","channelName","channelComment");
		//设置服务类型
		setSelectContent("serviceComment","${adminPath}/tbs/dmwhservice/getAllService","serviceName","serviceComment");
	});
	
	//验证方法
	function verify(node){
		//验证规则没确定
		top.layer.alert("验证成功！", {title:"信息"});
	}
	
	//显示请求响应信息
	function showMessage(node){
		
		var colunms = $(node).parent().siblings();
		var userid = $(node).parent().prev().attr("title");
		var sendSeq = $(node).parent().next().attr("title");
		var serviceName = $(node).parent().next().next().attr("title");
		

		top.layer.open({
		    type: 2,  
		    area: ["700px", "500px"],
		    title: "详细信息",
	        maxmin: true, //开启最大化最小化按钮
		    content: "/tbs-web/admin/tbs/query/authLog/showDetail?userid=" + userid + "&sendSeq=" + sendSeq + "&serviceName=" +serviceName ,
		    btn: ['关闭'],
		    cancel: function(index){ 
		    	 
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
	
	
</script>
</body>
</html>