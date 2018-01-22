<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>平台交互日志查询</title>
<meta name="decorator" content="list" />
<style>
.ui-jqgrid tr.jqgrow td {
	white-space: normal !important;
	height: auto;
	vertical-align: middle;
}
</style>
</head>
<body title="平台交互日志查询">
	<grid:grid id="transactionlogId" multiselect="false" url="${adminPath}/tbs/query/transactionLog/ajaxList">
		
		<grid:query label="证件号码" name="cardNo" condition="like" />
		<grid:query label="姓名" name="fullName" condition="like" />
		<grid:query label="银行名称" name="channelName" dict=" " queryMode="select" condition="eq" />
		<grid:query label="服务类型" name="serviceName" dict=" " queryMode="select" condition="eq" />
		<grid:query label="交易日期起" name="transactionDate1" queryMode="date" condition="ge" />
		<grid:query label="交易日期止" name="transactionDate2" queryMode="date" condition="le" />
		<grid:query label="交易状态" name="code" dict=" " queryMode="select" condition="eqOrNot" />
		
		<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		<grid:column label="银行名称" name="channelComment" />
		<grid:column label="服务名称" name="serviceComment" />
		<grid:column label="发生时间" name="lrrq" width="150" />
		<grid:column label="状态" name="state" />
		<grid:column hidden="true" label="返回消息内容" name="massage" />
		<grid:column label="用户" name="fullName" />
		<grid:column label="证件号码" name="cardNo" width="150" />
		<grid:column hidden="true" label="用户id" name="userid" />
		<grid:column label="消息键" name="sendSeq" width="240" />
		
		<!-- ↓↓ 这下面的相对位置不能动 ↓↓ -->
		<grid:column hidden="true" label="请求（接收消息）" name="recData" />
		<grid:column label="sys.common.opt"  name="opt" formatter="button"/>
		<grid:button title="消息内容"  groupname="opt" onclick="showMessage(this)" outclass="btn-info" />
		<grid:column hidden="true" label="响应（返回消息）" name="retData" />
		<!-- ↑↑ 这上面的相对位置不能动 ↑↑ -->
		
		
		
		
		<%-- <grid:toolbar title="导出当前页" icon="fa-external-link" function="exportExcelAll(false)" />
		<grid:toolbar title="导出全部" icon="fa-external-link" function="exportExcelAll(true)" /> --%>
		
		<grid:toolbar function="search" />
		<grid:toolbar function="reset" />
	</grid:grid>
	<script type="text/javascript">

		$(function() {
			//设置银行类型
			setSelectContent("channelName","${adminPath}/tbs/dmwhchannel/getAllChannel","channelName","channelComment");
			//设置服务类型
			setSelectContent("serviceName","${adminPath}/tbs/dmwhservice/getAllService","serviceName","serviceComment");
			//设置状态类型(先写死)
			//setSelectContent("channelName","${adminPath}/tbs/dmwhchannel/getAllChannel","id","channelComment");
			var codeNode = $("select[name='code']");
			if (codeNode.find("option").length <= 1) {
				var htmlstr = '<option value="eq0">成功</option>' + '<option value="ne0">失败</option>'
				codeNode.append(htmlstr);
			}
			
			setTimeout("setStateMsg()",1000);			
			
		});
		
		//显示请求响应信息
		function showMessage(node){
			
			var colunms = $(node).parent().siblings();
			var id = colunms[0].title;
			
			top.layer.open({
			    type: 2,  
			    area: ["700px", "500px"],
			    title: "详细信息",
		        maxmin: true, //开启最大化最小化按钮
			    content: ["/tbs-web/admin/tbs/query/transactionLog/showDetail?xh=" + id /* ,"no" */],
			    btn: ['关闭'],
			    cancel: function(index){ 
			    	 
			    }
			});
			
		}
		
		//状态一栏，鼠标放上去展示详细信息
		function setStateMsg(){
			var stateNodes = $("td[aria-describedby='transactionlogIdGrid_state']");
			var length = stateNodes.length;
			if(length > 0){
				for(var i = 0;i < length;i++){
					var node = $(stateNodes[i]);
					node.attr("title",node.next().text());
				}
			}
			setTimeout("setStateMsg()",1000);
		}
		
		/**
		 *导出excel方法
		 *isAll:true为全部，false为当前页
		 */
		function exportExcelAll(isAll) {
			exportExcel(getExportInfo(isAll),"${adminPath}/tbs/query/userAuth/exportExcelAll");
		}

		/**
		 *组装导出excel需要的信息
		 */
		function getExportInfo(isAll) {
			var data = {
				"isAll" : isAll,
				"fileName" : "用户授权信息",
				"tableName" : "用户授权信息列表",
				"sheetName" : "授权",
				"columnKeys" : "cardNo,fullName,channelName,authDate,term,authType,authContent",
				"columnValues" : "证件号码,姓名,渠道名称,授权日期,授权期限,授权方式,授权内容",
			};
			//获得查询条件
			var exportInfo = getParams("transactionlogIdGrid");
			//加入是否查询历史信息标记
			var checkbox = $("input[name='history']").parent();
			exportInfo.history = checkbox.hasClass("checked") ? "1" : "0";
			exportInfo["query.history||"] = exportInfo.history;
			
			exportInfo["info"] = JSON.stringify(data);
			return exportInfo;
		}
		
		
/* 		function searchAll(id){
			search(id);
			setTimeout("setStateMsg()",1000);
		} */
		
		
	</script>
</body>
</html>