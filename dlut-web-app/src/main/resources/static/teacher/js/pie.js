function cakes(id,title,datas){
		var area=[];
		for(var lx  in datas){
			area.push(lx);
		}
		console.log(area)
		cake(id,title,area,datas);
	}
	function cake(id,title,name,datas){
		var el = echarts.init(document.getElementById(id));
	    elData = {
		    title : {
		        text: title,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:name
		    },
		    calculable : true,
		    series : [
		        {
		            name:title,
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:datas
		        }
		    ]
		};             
		el.setOption(elData);
	}
	
     