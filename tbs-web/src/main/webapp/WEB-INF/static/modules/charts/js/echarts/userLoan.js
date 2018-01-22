$(function () {
    var barChart = echarts.init(document.getElementById("echarts-userLoan-chart"));
    var baroption = {
        title : {
            text: '成功贷款用户统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        grid:{
            x:41,
            x2:20,
            y2:24
        },
        calculable : true,
        yAxis : [
            {
                type : 'category',
                data : []
            }
        ],
        xAxis : [
            {
            	
                type : 'value'
            }
        ],
        series :[]
    };
    
    
    beginShow();
    
    
    var channel = $("#serviceSelect select[name='channelComment']");
   
    
    
    channel.change(function(){
    	
    	if(channel.val()!="null"&&channel.val()!=null){
    		
    		baroption.series.splice(0,baroption.series.length);//清空数组
    		baroption.yAxis[0].data.splice(0,baroption.yAxis[0].data.length);//清空数组
    	    /*	if(baroption.series.length>1){
    	    		for(var i=0;i<baroption.series.length;i++){
    	        		
    	        		baroption.series.splice(0,baroption.series.length);//清空数组
    	        	}
    	    	}else{
    	    		baroption.series[0].data.splice(0,baroption.series[0].data.length);//清空数组
    	    	}*/
    	    	
    	    	baroption.series.push({name:'用户数',type:'bar',data:[]});
    	    	
    	    	
    	    	 $.get(adminPath+"/tbs/statistics/getPreHalfMonth", function(sdata){
    	     		sdata = eval('(' + sdata + ')');
    	     		//alert(sdata);
    	     		$.get(adminPath+"/tbs/statistics/getOneUserLoanStatistics/"+channel.val()+"/", function(stadata){
    	     			
    	     			stadata = eval('(' + stadata + ')');
    	     			for(var i=0;i<sdata.length;i++){
    	     				//alert(sdata[i].split("-")[2]-1);
    	     				baroption.yAxis[0].data.push(sdata[i].split("-")[1]+"/"+sdata[i].split("-")[2]);
    	     				//baroption.series.push("{name:'用户数',type:'bar',data:[],}");
    	     				
    	     				baroption.series[0].data.push(stadata[sdata[i].split("-")[2]-1]);
    	     			}
    	     			console.log(baroption.series[0].data);
    	     			barChart.setOption(baroption,true);
    	     		});
    	     		
    	     		
    	     	});
    		
    		
    	}else{
    		beginShow();
    	}
    	
    	
		
		});
    
    
    
    
    function beginShow(){
    	$.get(adminPath+"/tbs/statistics/getPreHalfMonth", function(sdata){
    		baroption.yAxis[0].data.splice(0,baroption.yAxis[0].data.length);//清空数组
    		sdata = eval('(' + sdata + ')');
    		for(var i=0;i<sdata.length;i++){
    			baroption.yAxis[0].data.push(sdata[i].split("-")[1]+"/"+sdata[i].split("-")[2]);
    		}
    		$.get(adminPath+"/tbs/dmwhchannel/getAllChannel",function(data){
        		data = eval('(' + data + ')');
        		baroption.series.splice(0,baroption.series.length);//清空数组
        		for(var j in data){
        			
        				baroption.series.push({
         				   name:data[j].channelComment,
                            type:'bar',
                           stack: '总量',
                           itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                           data:[]
                           });
        				$.ajax({
        					url:adminPath+"/tbs/statistics/getUserLoanStatistics/"+data[j].id,
        					type:"get",
        					async:false,//同步
        					success:function(stadata){
        						stadata = eval('(' + stadata + ')');
                					for(var i=0;i<sdata.length;i++){
                    					baroption.series[j].data.push(stadata[sdata[i].split("-")[2]-1]);
                        			}
        				}});	
        				
                	}
        		barChart.setOption(baroption,true);
        	});
    		
    		
    					
        
    	});
    }
    
    
    
    
    

    window.onresize = barChart.resize;

    

});
