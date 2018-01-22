$(function () {
    var barChart = echarts.init(document.getElementById("echarts-authUser-chart"));
    var baroption = {
        title : {
            text: '授权用户量统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        grid:{
            x:30,
            x2:40,
            y2:24
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'用户数',
                type:'bar',
                /*barWidth : 30,*/
                data:[],
            }
        ]
    };
    
    
    var channel = $("#serviceSelect select[name='channelComment']");
    
    loadData(channel.val());
    
    channel.change(function(){
    	baroption.series[0].data.splice(0,baroption.series[0].data.length);//清空数组
    	baroption.xAxis[0].data.splice(0,baroption.xAxis[0].data.length);//清空数组
    	loadData(channel.val());
		
		});
    
    
    function loadData(channelName){
    	 
        
        $.get(adminPath+"/tbs/statistics/getPreHalfMonth", function(sdata){
    		sdata = eval('(' + sdata + ')');
    		//alert(sdata);
    		$.get(adminPath+"/tbs/statistics/getAuthUserStatistics?channelName="+channelName, function(stadata){
    			stadata = eval('(' + stadata + ')');
    			for(var i=0;i<sdata.length;i++){
    				//alert(sdata[i].split("-")[2]-1);
    				baroption.xAxis[0].data.push(sdata[i]);	
    				baroption.series[0].data.push(stadata[sdata[i].split("-")[2]-1]);
    			}
    			console.log(baroption.series[0].data);
    			barChart.setOption(baroption,true);
    		});
    		
    		
    	});
    }
    
   

    $(window).resize(barChart.resize);
    

});
