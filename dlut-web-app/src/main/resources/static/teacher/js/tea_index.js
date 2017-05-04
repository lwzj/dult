$(function(){
	fn();
	$("#year").on("change",fn);
	function fn(){
		console.log($("#year").val())
		$.ajax({
			type:"get",
			url:"/app/stat/schTeacher",
			data:{
				graduateDate:$("#year").val()
			},
			success:function(data){
				var array=[], array2=[], inner="";
				//各个院系就业百分比
				for(var acade  in data.academyEmplo){
					data.academyEmplo[acade]/data.academyTotal[acade]
					array.push(acade);
					array2.push(Math.round(data.academyEmplo[acade]/data.academyTotal[acade]*100)+"%");
				}
				for(var i=0;i<array.length;i++){
					inner+=`<span>${array[i]}</span>
						<div class="progress">
						  <div class="progress-bar progress-bar-info progress-bar-striped" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: ${array2[i]}">
						    <span>${array2[i]}</span>
						  </div>
						</div>`;
				}
				$('.percentage').html(inner);
				//饼图
				cakes("emploNature","就业单位性质分布",data.emploNature);
				cakes("emploCategory","就业单位行业分布",data.emploCategory);
				
				//就业率
				$(".emploRate").html(Math.round(data.emploRate*100)+"%");
				
				//待办事件
				for(var list in data.todoList){
					$("."+list).html(data.todoList[list])
				}
			},
			error:function(err){
				console.log(err)
			}
		})
	}
	
	//---------------------------------------------------------------------------------
	   var emploArea = echarts.init(document.getElementById('emploArea'));
        emploAreadata = {
    title : {
        text: '就业地区分布',
        x:'center'
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['就业地区分布']
    },
    dataRange: {
        x: 'left',
        y: 'bottom',
        splitList: [
            {start: 1500},
            {start: 900, end: 1500},
            {start: 310, end: 1000},
            {start: 200, end: 300},
            {start: 10, end: 200, label: '10 到 200（自定义label）'},
            {start: 5, end: 5, label: '5（自定义特殊颜色）', color: 'black'},
            {end: 10}
        ],
        color: ['#E0022B', '#E09107', '#A3E00B']
    },
    series : [
        {
            name: '就业地区分布',
            type: 'map',
            mapType: 'china',
            roam: false,
            itemStyle:{
                normal:{
                    label:{
                        show:true,
                        textStyle: {
                           color: "rgb(249, 249, 249)"
                        }
                    }
                },
                emphasis:{label:{show:true}}
            },
            data:[
                {name: '北京',value: Math.round(Math.random()*2000)},
                {name: '天津',value: Math.round(Math.random()*2000)},
                {name: '上海',value: Math.round(Math.random()*2000)},
                {name: '重庆',value: Math.round(Math.random()*2000)},
                {name: '河北',value: 0},
                {name: '河南',value: Math.round(Math.random()*2000)},
                {name: '云南',value: 5},
                {name: '辽宁',value: 305},
                {name: '黑龙江',value: Math.round(Math.random()*2000)},
                {name: '湖南',value: 200},
                {name: '安徽',value: Math.round(Math.random()*2000)},
                {name: '山东',value: Math.round(Math.random()*2000)},
                {name: '新疆',value: Math.round(Math.random()*2000)},
                {name: '江苏',value: Math.round(Math.random()*2000)},
                {name: '浙江',value: Math.round(Math.random()*2000)},
                {name: '江西',value: Math.round(Math.random()*2000)},
                {name: '湖北',value: Math.round(Math.random()*2000)},
                {name: '广西',value: Math.round(Math.random()*2000)},
                {name: '甘肃',value: Math.round(Math.random()*2000)},
                {name: '山西',value: Math.round(Math.random()*2000)},
                {name: '内蒙古',value: Math.round(Math.random()*2000)},
                {name: '陕西',value: Math.round(Math.random()*2000)},
                {name: '吉林',value: Math.round(Math.random()*2000)},
                {name: '福建',value: Math.round(Math.random()*2000)},
                {name: '贵州',value: Math.round(Math.random()*2000)},
                {name: '广东',value: Math.round(Math.random()*2000)},
                {name: '青海',value: Math.round(Math.random()*2000)},
                {name: '西藏',value: Math.round(Math.random()*2000)},
                {name: '四川',value: Math.round(Math.random()*2000)},
                {name: '宁夏',value: Math.round(Math.random()*2000)},
                {name: '海南',value: Math.round(Math.random()*2000)},
                {name: '台湾',value: Math.round(Math.random()*2000)},
                {name: '香港',value: Math.round(Math.random()*2000)},
                {name: '澳门',value: Math.round(Math.random()*2000)}
            ]
        }
    ]
};
emploArea.setOption(emploAreadata);
})