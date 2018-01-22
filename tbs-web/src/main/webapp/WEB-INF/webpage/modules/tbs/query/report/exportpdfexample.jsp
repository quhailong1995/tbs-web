<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="grid-select" />

<style>

body {
	padding: 0;
	margin: 0;
}

		/* ECHARTS  */
		
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

<body class="undefined pace-done" style="background-color:#fff">	

	
	


<div  style="display:none;width:100%;float:left;margin-bottom:20px;padding-left:60px;padding-right:60px">	
				<div style="min-width:600px" class="pull-left">
					   <div class="form-group" id="serviceSelect">
			 	          <label class="control-label">银行名称：</label>
			 	    	 	<select class="form-control" name="channelId" value="null">
			 	    	 		<!-- <option value="null">全部</option> -->
			 	    	 	</select>
			 	    	 	&nbsp;&nbsp;&nbsp;&nbsp;
			 	    	 	<label class="control-label">统计年月：</label>
			 	    	 	<div  class=" datepicker input-group date" style="width:200px;float:right;margin-top:-4px">
	                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                               <input class="form-control" condition="ge" name="dateStr"  style="width: 75%;">
	                         </div>
			 	    	 	
			 	     	</div>
				</div>
				<div class="pull-right">
				<button class="btn btn-sm btn-info" onclick="statistics()"><i class="fa "></i> 统计</button>
				<button class="btn btn-sm btn-info" onclick="exportpdfexample()"><i class="fa "></i> 导出</button>
				</div>
			</div>
				
	
	
	
	
			
<div id="exportDiv" style="height:100%;background-color:#fff;padding-left:50px;padding-right:50px">

<div style="font-size:15px;font-weight: bold;color: #333;margin-top:10px;width:100%;vertical-align:middle;">
<p style=" text-align: center;"><span id="statistics_channel">${channelComment }</span>&nbsp;<span id="statistics_year">${statisticsDate }月
- 税银服务简报
</p>
</div>
<div style="font-size:13px;color: #777777;margin-top:10px;width:100%">统计时间:<span id="statisticsDate">${operateDate }</span></div>





<div id="showgrid" style="margin-bottom:20px">
	<div style="font-size:20px;font-weight: bold;color: #333;margin-top:10px">1、用户授权情况</div>

	<grid:grid id="userAuthReportGridId" multiselect="false" pageable="false"
		sortable="false" url="${adminPath}/tbs/query/report/queryUserAuthReport?channelId=${channelId }&dateStr=${dateStr }" >
		
	<grid:column label="产品"  name="dmWhProduct.productComment" />
    <grid:column label="授权用户(户)"  name="authCount" />
    <grid:column label="环比"  name="ringGrowth" />
    <grid:column label="取消授权(户)"  name="cancelAuthCount" />
    <grid:column label="授权到期"  name="expireAuthCount" />
    
    

	</grid:grid>
</div>



	
	<div id="showgrid" style="margin-bottom:20px">
		<div style="font-size:20px;font-weight: bold;color: #333;margin-top:10px">2、用户授信情况</div>
	
		<grid:grid id="userLoanQueryGridId" multiselect="false" pageable="false"
			sortable="false" url="${adminPath}/tbs/query/report/queryUserLoanReport?channelId=${channelId }&dateStr=${dateStr }">
			<grid:column label="产品"  name="dmWhProduct.productComment" />
		    <grid:column label="授信用户"  name="loanCount" />
		    <grid:column label="授信金额合计"  name="loanTotalPrice" />
		    <grid:column label="授信到期"  name="loanExpire" />
		    <grid:column label="其中提前还请"  name="loanAdvanceRepay" />
		</grid:grid>
	</div>
	
	
	
	<div id="showgrid" style="margin-bottom:20px">
		<div style="font-size:20px;font-weight: bold;color: #333;margin-top:10px">3、用户授信查询情况</div>
		<grid:grid id="userLoanQueryReportGridId" multiselect="false" pageable="false"
			sortable="false" url="${adminPath}/tbs/query/report/queryUserLoanQueryReport?channelId=${channelId }&dateStr=${dateStr }">
			<grid:column label="渠道"  name="dmWhChannel.channelComment" />
		    <grid:column label="纳税明细查询"  name="queryCount_BTS003" />
		    <grid:column label="环比"  name="ringGrowth_BTS003" />
		    <grid:column label="纳税结果查询"  name="queryCount_BTS004" />
		    <grid:column label="环比"  name="ringGrowth_BTS004" />
		</grid:grid>
	</div>
	
	
	<div style="font-size:20px;font-weight: bold;color: #333;margin-top:10px">4、服务调用情况</div>
	<div style="height:300px;width:100%;background-color:#fff" class="echarts" id="echarts-serviceCall-chart"></div>


</div>	

<html:js name="echarts" />
<script src="${staticPath}/modules/charts/js/echarts/serviceCallExport.js"></script>

<script type="text/javascript">

var adminPath = "${adminPath}";


$(document).ready(function(){
	$("#page-wrapper").css("background-color","#fff");
	$(".ui-jqgrid-bdiv").height("");
	$("table").unbind();
	$("th").unbind();
	//alert($(".ui-jqgrid-bdiv").height());
	
	
	})


$(window).load(function(){

	
	$("html").css("overflow","visible");

	
	$("#userAuthReportGridIdGridQuery").remove();
	$("#userLoanQueryGridIdGridQuery").remove();
	$("#userLoanQueryReportGridIdGridQuery").remove();
	
	
	$(".ui-jqgrid ").css("border","none");

	reviseCss($("#gbox_userAuthReportGridIdGrid"));
	reviseCss($("#gbox_userLoanQueryGridIdGrid"));
	reviseCss($("#gbox_userLoanQueryReportGridIdGrid"));
	
	beginStatistics("${channelId}","${dateStr}");

	
})

function reviseCss(obj){

	obj.css("width","100%");
	obj.find("div.table-responsive").css("width","100%");
	obj.find("div.table-responsive div.ui-jqgrid-hdiv").css("width","100%");
	obj.find("div.table-responsive div.ui-jqgrid-hbox").css("padding-right","0")
	obj.find("div.table-responsive div.ui-jqgrid-bdiv").css("width","100%");
	obj.find("table").css("width","100%");
	obj.find("th[role='columnheader']").css("width","20%");
	obj.find("td[role='gridcell']").css("width","20%")
	
	//obj.css("overflow-x","hidden");
	obj.children().css("overflow-x","hidden");
	obj.children().find(".ui-jqgrid-bdiv").css("overflow-x","hidden");
	
	
	obj.find("table tr").last().css("border-bottom","none");
	//$("#gbox_userAuthReportGridIdGrid").css("border-right","none");
	
	obj.css("border-left","1px solid #ddd");
	obj.find("table").css("border-top","1px solid #ddd");
//	obj.find("table").css("border-left","1px solid #ddd");
	
}


/**
 * 获取上一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    //var day = arr[2]; //获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    //var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
   /*  if (day2 > days2) {
        day2 = days2;
    } */
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '-' + month2;
    return t2;
}


</script>
</body>
</html>