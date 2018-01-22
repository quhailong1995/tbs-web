var lineChart;
	var lineoption;

$(function () {


	
	
		lineChart = echarts.init(document.getElementById("echarts-serviceCall-chart"));
//		lineChart.showLoading({
//			text: "图表数据正在努力加载..."
//			});
		
		lineoption = {
				//itemStyle: {normal: {areaStyle: {type: 'default'}}},
				//normal: {areaStyle: {type: 'default'}},
				 backgroundColor: '#fff',//背景色
			       title : {
			            text: '',
			            x: 'right',
			            textStyle:{
			                //文字颜色
			                color:'#777777',
			                //字体风格,'normal','italic','oblique'
			                fontStyle:'normal',
			                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
			                fontWeight:'bold',
			                //字体系列
			               // fontFamily:'sans-serif',
			                //字体大小
			        　　　　 fontSize:13
			            }
			           
			        },
			        /*
			        toolbox: {
			            feature: {
			                myTool1: {
			                    show: true,
			                    title: '总调用量：',
			                   
			                    onclick: function (){
			                        alert('myToolHandler1')
			                    }
			                },
			                myTool2: {
			                    show: true,
			                    title: '自定义扩展方法',
			                    icon: 'image://http://echarts.baidu.com/images/favicon.png',
			                    onclick: function (){
			                        alert('myToolHandler2')
			                    }
			                }
			            }
			        },*/
			        
				
/*				title:{
					text:'echarts入门',
					link:'http://www.baidu.com',//主标题超链接
					target:'blank',//主标题超链接打开方式
					textStyle:{ //设置主标题风格
					Color:'green',//设置主标题字体颜色
					fontStyle:'',//主标题文字风格

					},
					subtext:'副标题',
					sublink:'http://www.baidu.com',//副标题超链接
					subtarget:'blank',//副标题超链接打开方式
					padding:[5,10,5,5],//设置标题内边距,上，右，下，左
					itemGap:10,//主副标题之间的间距

					left:'left',//组件的位置,center,left,right
					top:'top',//组件离上边的距离middle,top,bottom //此二者的优先级高于x吗?答案：是
					x:'center',
					backgroundColor:'white',//标题背景色
					borderColor:'gray',//标题边框颜色
					borderWidth:5,//标签线框
					borderRadius:5,//边框切圆角
					shadowBlur:10,//图形阴影模糊大小,该属性配合 shadowColor,shadowOffsetX(阴影水平方向上的偏移距离), shadowOffsetY(阴影垂直方向上的偏移距离。) 一起设置图形的阴影效果。
					shadowColor:'rgba(0,0,0,0.5)'//阴影颜色
					},*/
				
				
				/*
			        toolbox: { //可视化的工具箱
		                show: false,
		                feature: {
		                    dataView: { //数据视图
		                        show: true
		                    },
		                    restore: { //重置
		                        show: true
		                    },
		                    dataZoom: { //数据缩放视图
		                        show: true
		                    },
		                    saveAsImage: {//保存图片
		                        show: true
		                    },
		                    magicType: {//动态类型切换
		                        type: ['bar', 'line']
		                    }
		                }
		            },*/
			        tooltip : {
			            trigger: 'axis'
			        },
			        legend: {
			            data:['合计']
			        },
			        grid:{
			            x:45,
			            x2:40,
			            y2:24
			        },
			        calculable : true,
			        xAxis : [
			            {
			                type : 'category',
			                boundaryGap : false,
			                data : []
			            }
			        ],
			        yAxis : [
			            {
			                type : 'value',
			                axisLabel : {
			                    formatter: '{value}'
			                }
			            }
			        ],
			        series : [
			                  {
			                	  name:'交易量',
			                      type:'line',
			                      data:[]}
			                  ]
			    };
			    
	lineChart.setOption(lineoption,true);
	


	
	
    $(window).resize(lineChart.resize);
    
    
    
    
	 //获取完整的日期
	 var date=new Date;
	 var year=date.getFullYear(); 
	 var month=date.getMonth()+1;
	 month =(month<10 ? "0"+month:month); 
	 var mydate = (year.toString()+"-"+month.toString());
	
	
	 
	$("#serviceSelect input").val(getPreMonth(mydate));
	
	//选择年月的    startView: 3,   minView: 3, format: 'yyyymm',  
	$(".datepicker").datepicker({
		  autoclose: true,
		  format: "yyyy-mm",
		  minViewMode: 1,
		  todayBtn: false,
		  setDate:getPreMonth(mydate)
		});
    

	var channel = $("#serviceSelect select[name='channelId']");
	var dateStr = $("#serviceSelect input[name='dateStr']");

	
	
	// loadData(channel.val(),dateStr.val());
	
	
	
	/*channel.change(function(){

		lineoption.series[0].data.splice(0,lineoption.series[0].data.length);//清空数组
		loadData(channel.val(),dateStr.val());
		
		});

	dateStr.change(function(){

		lineoption.series[0].data.splice(0,lineoption.series[0].data.length);//清空数组
		alert(lineoption.title.text);
		loadData(channel.val(),dateStr.val());
		
		});*/

   
	
	

	
	
	function getDaysInMonth(year,month){ 
		month = parseInt(month,10); //parseInt(number,type)这个函数后面如果不跟第2个参数来表示进制的话，默认是10进制。 
		var temp = new Date(year,month,0); 
		return temp.getDate(); 
		}

  
    
});


function beginStatistics(channelId,dateStr){
	lineoption.series[0].data.splice(0,lineoption.series[0].data.length);//清空数组
	lineoption.title.text='';
	
	loadData(channelId,dateStr);
	
}



function loadData(channelId,dateStr){
	$.get(adminPath+"/tbs/query/report/queryServiceCallReport/"+channelId+"/"+dateStr, function(data) {
//alert(data);
//data = eval('(' + data + ')');
//alert(data[09]);
//lineoption.series[0].data.splice(0,lineoption.series[0].data.length);//清空数组

var totalCall=0;
	
	lineoption.xAxis[0].data.splice(0,lineoption.xAxis[0].data.length);//清空数组
	//var monthDay = getDaysInMonth(myDate.getYear(),myDate.getMonth());
	$.get(adminPath+"/tbs/query/report/getAllDays/"+dateStr, function(sdata){
		sdata = eval('(' + sdata + ')');
		
		for(var i=0;i<sdata.length;i++){
			//alert(sdata[i].split("-")[2]);
			lineoption.xAxis[0].data.push(sdata[i]);	
			var count = data[sdata[i].split("-")[2]-1];
			totalCall = totalCall+count;
			lineoption.series[0].data.push(count);
		}
		
		lineoption.title.text = "总调用量："+lineoption.title.text+totalCall+"次";
		
		lineChart.setOption(lineoption,true);
	});
	


});

}


