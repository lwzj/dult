$(document).ready(function(){
	var lastValue="";
	$(".val").each(function(i,e){
		$(".val").eq(i).html($(e).parent().parent().parent().find("input").val());
		$(".vals").eq(i).html($(".zhi").eq(i).text());
	})
	//编辑按钮
	$(".edit").on("click",function(){
		$(this).parent().css({"display":"none"})
		$(this).parent().parent().find(".show2").css({"display":"block"});
		lastValue=$(this).parent().next().find(".form-control").val();
	});
	//取消按钮
	$(".cancel").on("click",function(){
		$(this).parent().parent().css({"display":"none"})
		$(this).parent().parent().parent().find(".show1").css({"display":"block"});
		$(this).parent().prev().find(".form-control").val(lastValue);
		return false;
	})
	$(".ok").on("click",function(){
		$.ajax({//请求数据
		type:"post",
		url:"/app/student/updateInformation",
		data:{
			allupdateStatus:"1"
		},
		success:function(data){
            window.location.href="/app/student/stuInformation.html";
		},
		error:function(err){
			console.log(err)
		}
	});
	})

})
