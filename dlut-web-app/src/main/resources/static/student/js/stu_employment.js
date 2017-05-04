$(document).ready(function(){
	
	if($("#status").val()!="00"){
		$("input[type=submit]").prop({"disabled":true})
	}else{
		$("input[type=submit]").prop({"disabled":false})
	};
	
	var onOff=$("#val").attr("value");
	/*为空，则就没有申请过，所以*/
	if(onOff){
		//如果申请过
		$(".look").css({"display":"block"})
		$(".apply").css({"display":"none"})
	}else{
		//没有申请过
		$(".look").css({"display":"none"})
		$(".apply").css({"display":"block"})
	}
	
	$(".look").on("click",function(){
		//点击查看信息
		window.location.href="/app/studentHTML/stu_employment_look.html";
	})
	$(".apply").on("click",function(){
		//点击查看信息
		window.location.href="/app/studentHTML/stu_employment_apply.html";
	})
})