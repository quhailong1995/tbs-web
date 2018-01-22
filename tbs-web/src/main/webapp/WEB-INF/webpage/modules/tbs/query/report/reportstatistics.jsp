<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>报表统计</title>
<!-- <meta http-equiv=”X-UA-Compatible” content=”IE=EmulateIE7″ > -->
<meta name="decorator" content="list" />


<script src="${staticPath}/vendors/jspdf/html2canvas.js"></script>
<script src="${staticPath}/vendors/jspdf/jspdf.min.js"></script>

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
		    width:100%;
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
	
<script type="text/javascript">
	
</script>

</head>

<body class="undefined pace-done">	

					


<div  style="width:100%;float:left;margin-bottom:20px;padding-left:60px;padding-right:60px">	
				<div style="min-width:600px" class="pull-left">
					   <div class="form-group" id="serviceSelect">
					   
							   
							   <div style="float:left;">
							   		<label class="control-label">银行名称：</label>
					 	    	 	<select class="form-control" name="channelId">
					 	    	 		<option value="null">所有渠道</option>
					 	    	 	</select>
					 	    	 	&nbsp;&nbsp;&nbsp;&nbsp;
					 	    	 	<label class="control-label">统计年月：</label>
							   </div>
					 	          
					 	       <div  class=" datepicker input-group date" style="width:200px;float:left;margin-top:-4px">
			                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			                        <input class="form-control" condition="ge" name="dateStr" style="width: 75%;">
			                   </div>
	                   
	                   
			 	    	 	
			 	     	</div>
				</div>
				<div class="pull-right">
				<button class="btn btn-sm btn-info" onclick="statistics()"><i class="fa "></i> 统计</button>
				<button  id="exportpdfexampleButton" class="btn btn-sm btn-info" ><i class="fa "></i> 导出</button>
				</div>
			</div>
			
			
<div id="exportDiv" style="height:100%;background-color:#fff;padding-left:50px;padding-right:50px">

<div style="font-size:15px;font-weight: bold;color: #333;margin-top:10px;width:100%;vertical-align:middle;">
<p style=" text-align: center;"><span id="statistics_channel"></span>&nbsp;<span id="statistics_year"></span>年<span id="statistics_month"></span>月
- 税银服务简报
</p>
</div>
<div style="font-size:13px;color: #777777;margin-top:10px;width:100%">统计时间:<span id="statisticsDate"></span></div>





<div id="showgrid" style="margin-bottom:20px">
	<div style="font-size:20px;font-weight: bold;color: #333;margin-top:10px">1、用户授权情况</div>

	<grid:grid id="userAuthReportGridId" multiselect="false" pageable="false"
		sortable="false" url="${adminPath}/tbs/query/report/queryUserAuthReport" >
		
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
			sortable="false" url="${adminPath}/tbs/query/report/queryUserLoanReport">
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
			sortable="false" url="${adminPath}/tbs/query/report/queryUserLoanQueryReport">
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





	<script type="text/javascript">


function statistics(){
	var channelId=$("#serviceSelect select[name='channelId']").val();
	var dateStr= $("#serviceSelect input").val();
	
	
	if(channelId=='null'){
		$("#statistics_channel").text("");
		
	}else{
		$("#statistics_channel").text($("#serviceSelect select[name='channelId'] option:checked").text());
	}
	
	$("#statisticsDate").text(getNowFormatDate());
	$("#statistics_year").text(dateStr.split("-")[0]);
	$("#statistics_month").text(dateStr.split("-")[1]);
	
	
	
	 beginStatistics(channelId,dateStr);
	 
	 aasearch("userAuthReportGridIdGrid",channelId,dateStr); 
	
	 aasearch("userLoanQueryGridIdGrid",channelId,dateStr);
	 aasearch("userLoanQueryReportGridIdGrid",channelId,dateStr);
	 
	
	 
}


function aasearch(gridId,channelId,dateStr) {
    //刷新
    //传入查询条件参数  
   // alert(channelId);
    
    var reqUrl = "${adminPath}/tbs/query/report/";
    
  if(gridId=="userAuthReportGridIdGrid"){
	  reqUrl = reqUrl+"queryUserAuthReport";
	  $("#"+gridId+" tr").remove();
	   $.get(reqUrl+"?channelId="+channelId+"&&dateStr="+dateStr,function(data){
		   console.log(data);
		   
		   var trHtml = '';
		   
		   for(var i in data){
			   
				 trHtml = trHtml+ '<tr class="jqgrow ui-row-ltr" style="border-left: 1px solid rgb(221, 221, 221);">'
					+'<td  style="text-align:left;" title="'+data[i].dmWhProduct.productComment+'">'+data[i].dmWhProduct.productComment+'</td>'
					+'<td  style="text-align:left;" title="'+data[i].authCount+'">'+data[i].authCount+'</td>'
					+'<td style="text-align:left;" title="'+data[i].ringGrowth+'">'+data[i].ringGrowth+'</td>'
					+'<td style="text-align:left;" title="'+data[i].cancelAuthCount+'">'+data[i].cancelAuthCount+'</td>'
					+'<td  style="text-align:left;" title="'+data[i].expireAuthCount+'">'+data[i].expireAuthCount+'</td>'
				+'</tr>';
			}
				 
			//alert($("#"+gridId).find("table tbody").text());
		   $("#"+gridId +" tbody").append(trHtml);
		   $("table").unbind();
	   });
	 
  }
 if(gridId=="userLoanQueryGridIdGrid"){
	 reqUrl = reqUrl+"queryUserLoanReport";
	 $("#"+gridId+" tr").remove();
	   $.get(reqUrl+"?channelId="+channelId+"&&dateStr="+dateStr,function(data){
		   console.log(data);
		   
		   var trHtml = '';
		   
		   for(var i in data){
			   
				 trHtml = trHtml+'<tr class="jqgrow ui-row-ltr" style="border-left: 1px solid rgb(221, 221, 221);">'
						+'<td  style="text-align:left;" title="'+data[i].dmWhProduct.productComment+'">'+data[i].dmWhProduct.productComment+'</td>'
						+'<td  style="text-align:left;" title="'+data[i].loanCount+'">'+data[i].loanCount+'</td>'
						+'<td style="text-align:left;" title="'+data[i].loanTotalPrice+'">'+data[i].loanTotalPrice+'</td>'
						+'<td style="text-align:left;" title="'+data[i].loanExpire+'">'+data[i].loanExpire+'</td>'
						+'<td  style="text-align:left;" title="'+data[i].loanAdvanceRepay+'">'+data[i].loanAdvanceRepay+'</td>'
					+'</tr>';
		   }
			 
			//alert($("#"+gridId).find("table tbody").text());
		   $("#"+gridId +" tbody").append(trHtml);
		   $("table").unbind();
	   });
	 
	
  }
 if(gridId=="userLoanQueryReportGridIdGrid"){
	 reqUrl = reqUrl+"queryUserLoanQueryReport";
	 $("#"+gridId+" tr").remove();
	   $.get(reqUrl+"?channelId="+channelId+"&&dateStr="+dateStr,function(data){
		   console.log(data);
		   
		   var trHtml = '';
		   
		   for(var i in data){
			   
				 trHtml =trHtml+'<tr class="jqgrow ui-row-ltr" style="border-left: 1px solid rgb(221, 221, 221);">'
						+'<td  style="text-align:left;" title="'+data[i].dmWhChannel.channelComment+'">'+data[i].dmWhChannel.channelComment+'</td>'
						+'<td  style="text-align:left;" title="'+data[i].queryCount_BTS003+'">'+data[i].queryCount_BTS003+'</td>'
						+'<td style="text-align:left;" title="'+data[i].ringGrowth_BTS003+'">'+data[i].ringGrowth_BTS003+'</td>'
						+'<td style="text-align:left;" title="'+data[i].queryCount_BTS004+'">'+data[i].queryCount_BTS004+'</td>'
						+'<td  style="text-align:left;" title="'+data[i].ringGrowth_BTS004+'">'+data[i].ringGrowth_BTS004+'</td>'
					+'</tr>';
		   }
			 
			//alert($("#"+gridId).find("table tbody").text());
		   $("#"+gridId +" tbody").append(trHtml);
		   $("table").unbind();
		   
	   });

 }
    

   
   
   
   
		 
		 
    
}


</script>

<html:js name="echarts" />
<script src="${staticPath}/modules/charts/js/echarts/serviceCall.js"></script>




<script type="text/javascript">

var adminPath = "${adminPath}";

$(document).ready(function(){
	$(".ui-jqgrid-bdiv").height("");
	$("table").unbind();
	$("th").unbind();
	
	//alert($("#serviceSelect input").val());
	
	$("#statisticsDate").text(getNowFormatDate());
	$("#statistics_year").text($("#serviceSelect input").val().split("-")[0]);
	$("#statistics_month").text($("#serviceSelect input").val().split("-")[1]);
	$("#statistics_channel").text("");
	
	/* $(".datepicker").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    }); */
	
	

	
	
	
		var channel = $("#serviceSelect select[name='channelId']");
	
	
	$.get(adminPath+"/tbs/dmwhchannel/getAllChannel", function(
			data, status) {
		//alert(status);
		data = eval('(' + data + ')');
		for ( var i in data) {
			channel.append('<option value="'+data[i].id+'">'
					+ data[i].channelComment + '</option>');
		}
		 
	});
	
	$("#exportpdfexampleButton").click(function(){
		exportpdfexample();
	});
	
	
	
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



function downloadPDF(){  
    
        html2canvas(document.getElementById("exportDiv"), {
            onrendered:function(canvas) { 

                //返回图片dataURL，参数：图片格式和清晰度(0-1)
                 var pageData = canvas.toDataURL('image/jpeg', 1.0);
                console.log();
                console.log(pageData);
                //方向默认竖直，尺寸ponits，格式a4[595.28,841.89]
                var pdf = new jsPDF('', 'pt', 'a4');
                
              

                //addImage后两个参数控制添加图片的尺寸，此处将页面高度按照a4纸宽高比列进行压缩
                pdf.addImage(pageData, 'jpeg', 0, 0, 595.28, 592.28/canvas.width * canvas.height );

                pdf.save('税银服务简报'+$("#serviceSelect input").val()+'.pdf');
                

            }
        })
    

    
}  


function exportpdfexample(){
	var channelId=$("#serviceSelect select[name='channelId']").val();
	var channelComment=$("#serviceSelect select[name='channelId'] option:selected").text();
	var dateStr= $("#serviceSelect input").val();
	var operateDate = $("#statisticsDate").text();
	//alert(channelComment);
	
	var toplayer ;
		$.ajax({
			url:"${adminPath}/tbs/query/report/phantomjsExport",
			type:"post",
			data:{channelId:channelId,channelComment:channelComment,dateStr:dateStr,operateDate:operateDate},
			async:true,
			/* dataType: 'text', */
			beforeSend:function(){
		            toplayer = top.layer.load();
				},
			success:function(data){
				top.layer.close(toplayer);
				var pdf = new jsPDF('', 'pt', 'a4');
		        //addImage后两个参数控制添加图片的尺寸，此处将页面高度按照a4纸宽高比列进行压缩
		        pdf.addImage(data, 'jpeg', 0, 0, 595.28, 592.28 );

		        pdf.save('税银服务简报'+$("#serviceSelect input").val()+'.pdf');
				
		},
				
		});	
	
	
/* 	 $.post("${adminPath}/tbs/query/report/phantomjsExport",{channelId:channelId,channelComment:channelComment,dateStr:dateStr,operateDate:operateDate},function(data,status){
		//alert(status);
		var pdf = new jsPDF('', 'pt', 'a4');
        //addImage后两个参数控制添加图片的尺寸，此处将页面高度按照a4纸宽高比列进行压缩
        pdf.addImage(data, 'jpeg', 0, 0, 595.28, 592.28 );

        pdf.save('税银服务简报'+$("#serviceSelect input").val()+'.pdf');
	}); 
	 */
	
}





function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}





</script>


</body>
</html>