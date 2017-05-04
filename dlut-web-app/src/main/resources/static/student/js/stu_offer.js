$(document).ready(function(){
	var onOff=true;
	getContent("06");
	function getContent(n){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
		var size = $("#number").val()||10;
			$.ajax({//请求数据
			type:"get",
			url:"/app/student/RecOffer",
			data:{
            stuNo:$("input[type=hidden]").val(),
            stuReceivingStatus:n,
            },
			success:function(obj){
				var sta=-1
				var arr=["否","是"];
				var inner="";
				$.each(obj, function(i,e) {
				if($(e)[0].stuReceivingStatus=="01"||$(e)[0].stuReceivingStatus=="04"){
					sta=1;
				};
				var at = $(e)[0].closingDate;
                var date = at.year+'-'+at.monthValue+'-'+at.dayOfMonth;
				inner +='<tr class="text-center"><td>'+$(e)[0].recName+'</td><td>'+ date+'</td><td>'+$(e)[0].categoryName+'</td><td>'+arr[$(e)[0].hasReportCard]+'</td><td>'+arr[$(e)[0].isPfile]+'</td><td>'+arr[$(e)[0].isSolveHukou]+'</td><td>'+$(e)[0].stuReceivingStatusName+'</td><td>'+$(e)[0].auditStatusName+'</td><td><a class="btn btn-default" href="/app/student/myoffer?id='+$(e)[0].id+'">详情</a></td><td class="operation"><a class="btn btn-default" href="/app/student/updateOffer1?id='+$(e)[0].id+'">接受</a> <a class="btn btn-default" href="/app/student/updateOffer2?id='+$(e)[0].id+'">拒绝</a></td></tr>';
				});
				$("tbody").html(inner);
				if(onOff){
					$("#page").initPage(obj.totalElements,1,getContent);
				}
				onOff=false;
				if(sta==1){
					$(".operation").css({"display":"none"});
				}else{
					$(".operation").css({"display":"block"});
				}
			},
			error:function(err) {
			    alert("请求不成功！")
			}
		});
	}

$("#btn").on("click",function(){
	onOff=true;
	getContent($("[name=stuReceivingStatus]").val());
})

})
