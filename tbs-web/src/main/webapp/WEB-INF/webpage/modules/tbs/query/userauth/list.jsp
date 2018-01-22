<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>用户授权查询</title>
<meta name="decorator" content="list" />
<style>
.ui-jqgrid tr.jqgrow td {
	white-space: normal !important;
	height: auto;
	vertical-align: middle;
	padding-top: 2px;
}
</style>
</head>
<body title="用户授权查询">
	<grid:grid id="userauthId" multiselect="false" url="${adminPath}/tbs/query/userAuth/ajaxList" multiSort="false">
		<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
		<grid:column label="证件号码" name="cardNo" query="true" queryModel="input" condition="like" width="150" />
		<grid:column label="姓名" name="fullName" />
		<grid:column label="银行名称" name="channelName" dict=" " query="true" queryMode="select" condition="eq" />
		<grid:column label="授权时间" name="authDate" />
		<grid:column label="授权期限" name="term" />
		<grid:column label="授权方式" name="authType" dict="sqfs" query="true" queryMode="select" condition="eq" />
		<grid:column label="授权内容" name="authContent" width="200" />
		<grid:column hidden="true" label="备注" name="status" />
		
		<!-- ↓↓ 这下面的相对位置不能动 ↓↓ -->
		<grid:column hidden="true" label="授权码" name="authCode" />
		<grid:column label="sys.common.opt"  name="opts" formatter="button"/>
		<grid:button title="授权码"  groupname="opts" onclick="showAuthCode(this)" outclass="btn-info" />
		<grid:button title="签名验证" groupname="opts" onclick="signVerify(this)" outclass="btn-info" />
		<grid:button title="重导授权数据" groupname="opts" onclick="importAuthDataAgain(this)" outclass="btn-info" />
		<grid:column hidden="true" label="签名验证用的签名值" name="signData" />
		<grid:column hidden="true" label="用户id" name="userId" />
		<grid:button title="授权数据追溯" groupname="opts" onclick="authDataDetail(this)" outclass="btn-info" />
		<grid:column hidden="true" label="产品id" name="productId" />
		<grid:column hidden="true" label="户籍id" name="hjid" />
		<!-- ↑↑ 这上面的相对位置不能动 ↑↑ -->
		
		<grid:query label="授权日期起" name="beginDate1" queryMode="date" condition="ge" />
		<grid:query label="授权日期止" name="beginDate2" queryMode="date" condition="le" />
		<grid:query label="是否查询历史" name="history" queryMode="switch" />
		
		<grid:toolbar title="导出当前页" icon="fa-external-link" function="exportExcelAll(false)" />
		<grid:toolbar title="导出全部" icon="fa-external-link" function="exportExcelAll(true)" />
		
	<%-- 	<grid:toolbar title="模拟授权" icon="fa-search" layout="right" function="analogAuth" /> --%>
		<grid:toolbar title="搜索" icon="fa-search" layout="right" function="searchAll" />
		<grid:toolbar function="reset" />
	</grid:grid>
	<script type="text/javascript">
	
		document.isHistory = false;
	
		$(function() {
			$("input[name='beginDate1']").css({
				"width" : "120px"
			});
			$("input[name='beginDate2']").css({
				"width" : "120px"
			});
			$("input[type='checkbox']").css({
				"opacity" : 1
			});
			//var node = $("input[name='history']").next();
			$("input[name='history']").click(isHistory);
			$("input[name='history']").next().click(isHistory);
			setChannelNames();
		});
		
		//显示授权码
		function showAuthCode(node){
			//获得授权码
			var authCode = $(node).parent().prev().text();
			top.layer.alert("授权码：" + authCode, {title:"信息"});
		}
		
		//签名验证
		function signVerify(node){
			//获得授权码
			var authCode = $(node).parent().prev().text();
			//获得签名值
			var signData = $(node).parent().next().text();
			
			var flag = authCode == signData;
			
			if(flag){
				top.layer.alert("验证成功！", {title:"提示",icon: 1});
			}else{
				top.layer.alert("验证失败！", {title:"提示",icon: 2});
			}
		}
		
		
		function authDataDetail(node){
			
			var userFullName = $(node).parent().parent().find("td[aria-describedby='userauthIdGrid_fullName']").text();
			//alert(userFullName);
			var userId = $(node).parent().next().next().text();
			var productId = $(node).parent().next().next().next().text();
			var hjid = $(node).parent().next().next().next().next().text();
			var zjhm = $(node).parent().parent().find("td[aria-describedby='userauthIdGrid_cardNo']").text();//alert(zjhm);
			openDialog2("授权数据追溯","${adminPath}/tbs/query/userAuth/authDataDetailPage?userId="+userId+"&productId="+productId+"&userFullName="+userFullName+"&hjid="+hjid+"&zjhm="+zjhm,"","80%","600px");
		}
		
		
		function importAuthDataAgain(node){
			
			//获得授权码
			var authCode = $(node).parent().prev().text();
			
			var userId = $(node).parent().next().next().text();


			
	
			
			swal({
	            title: "提示",
	            text: "确定要重导授权数据吗？",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "确定",
	            closeOnConfirm: false,
	            cancelButtonText: "取消",
	        }, function () {
	        	 $.get("${adminPath}/tbs/query/userAuth/importAuthDataAgain?userId="+userId+"&authCode="+authCode, function(data,status) {
						data =  eval('(' + data + ')');
						 swal("提示！", data.msg, "");
						//top.layer.alert(, {title:"提示"});
			        });
	        	/*  
	        	 if (d.ret=="-1") {
						var msg = d.msg;
					    swal("提示！", msg, "success");
					    //刷新表单
			            refreshTable(gridId);
					}else{
						var msg = d.msg;
					    swal("提示！", msg, "error");
					} */
	        	 
	        });
/* 				
				
				layer.confirm("确定要重导授权数据吗？", {
					  btn: ['确定','取消'], //按钮
					  closeBtn:0
					}, function(){
					  //layer.msg('的确很重要', {icon: 1});
						/* var index = layer.getFrameIndex(window.name); 

				        layer.close(index); */
				       
						
						/* 			}, function(){}); */

				
				
			
			
		}
		
		//设置下拉框-银行名称的内容
		function setChannelNames() {
			var channelName = $("select[name='channelName']");
			if (channelName.find("option").length > 1) {
				return;
			}
			$.post("${adminPath}/tbs/dmwhchannel/getAllChannel", function(data,
					status) {
				var channelList = eval('(' + data + ')');
				for ( var i in channelList) {
					channelName.append('<option value="'+channelList[i].id+'">'
							+ channelList[i].channelComment + '</option>');
				}
			});
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
			var exportInfo = getParams("userauthIdGrid");
			//加入是否查询历史信息标记
			var checkbox = $("input[name='history']").parent();
			exportInfo.history = checkbox.hasClass("checked") ? "1" : "0";
			exportInfo["query.history||"] = exportInfo.history;
			
			exportInfo["info"] = JSON.stringify(data);
			return exportInfo;
		}
		
		
		function analogAuth(o){			
			top.layer.open({
			    type: 2,  
			    area: ["800px", "500px"],
			    title: "模拟授权信息",
		        maxmin: true, //开启最大化最小化按钮
			    content: "/tbs-web/admin/tbs/query/userAuth/create" ,
			    btn: ['确定','关闭'],
			    yes: function(index,layero){ 
			    	 var ifr = layero.find("iframe")[0].contentWindow;
			    	 var data = ifr.confirm();
			    	 var dataStr = JSON.stringify(data);
			    	 $(".layui-layer-btn1").click();
			    	 $.post("${adminPath}/tbs/query/userAuth/analogAuth",
			    			 {"dataStr":dataStr},
			    			 function(result){
			    				 var resultStr = $(result).find("return").text();
			    				 
			    				 var parser = new DOMParser(); 
			    				 var msgXml = parser.parseFromString(resultStr, "text/xml"); 

			    				 var code = $(msgXml).find("rtn_code").text();
			    				 var msg = $(msgXml).find("rtn_msg").text();
			    				 if(code=="0"){
			    					 top.layer.alert("模拟授权成功！", {title:"提示",icon: 1});
			    				 }else{
			    					 top.layer.alert(msg, {title:"提示",icon: 2}); 
			    				 }
			    				 
			    			 },"xml");
			    },
			    cancel: function(index){ 
			    	 
			    }
			});
			
		}
		
		/**
		*点击是否查询历史信息框
		*/
		function isHistory(){
			document.isHistory = !document.isHistory;
			searchAll();
		}
		
		/**
		 *搜索方法
		 */
		function searchAll() {
			var params = getParams("userauthIdGrid");//获得查询条件
			//var checkbox = $("input[name='history']").parent();
			params.history = document.isHistory ? "1" : "0";
			params["query.history||"] = params.history;
			
			var width = $("#gbox_userauthIdGrid").width();
			//重新设置grid的一些参数
			var grid = $("#userauthIdGrid").jqGrid('setGridParam', {
				datatype : 'json',
				postData : params, //发送数据  
				page : 1,
				//autowidth : false,
				shrinkToFit : false,
				loadComplete: function(){
					//$("#gbox_userauthIdGrid").width(width);
				},
			});
			
			if (document.isHistory) {
				grid.showCol("status").trigger("reloadGrid"); //显示备注列并重新载入 
			} else {
				grid.hideCol("status").trigger("reloadGrid"); //隐藏备注列并重新载入 
			}
			
		}
	</script>
</body>
</html>