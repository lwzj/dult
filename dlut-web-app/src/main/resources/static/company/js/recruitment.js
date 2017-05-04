$(document).ready(function() {
	$('form.wrap1').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
        },
        fields: {
        	title: {
                validators: {
                    notEmpty: {
                        message: '招聘简章的标题不能为空'
                    }
                }
            },
            content:{
                validators: {
                    notEmpty: {
                        message: '招聘简章的内容不能为空'
                    }
                }
            },
            startTime:{
                validators: {
                    notEmpty: {
                        message: '开始时间不能为空'
                    }
                }
            },
            endTime:{
                validators: {
                    notEmpty: {
                        message: '结束时间不能为空'
                    }
                }
            }
        }
    });
    
	var li=$(".publishNav").find("li");
	//发布简章
    $("#release").on("click",function(){
		window.location.href="/app/company/recruitment-step1.html";
    })
     $(".jump").on("click",function(){
   		window.location.href="/app/company/recruitment-step3.html";
    })
     $(".look").on("click",function(){
     
       	window.location.href="/app/company/recruitment.html";
     })
var div =$(".plus").parents('.form1').clone(true);

	 $(".plus").click(function(){
		 var html=$(this).parents(".form-content").clone(true);
		 html.find('input').val('');
		 html.find('select').val('');
		 html.find('checkbox').prop('checked',false);
		 html.insertAfter($(this).parents(".form-content"));
		 //$(this).parents(".form1").append();
	 })
	 $(".minus").click(function(){
		 if($(this).parents(".form1").find(".form-content").length==1)return;
		 $(this).parents(".form-content").remove();
	 })
     //收缩按钮
     $(".upDown").click(function(){
		
		
		 $(this).parents(".form-content").find(".div").toggle("showOrHide")
	 })
     //删除
     $(".list1").on("click",function(){
	window.location.href="./app/recruitment.html";
})
$(".list2").on("click",function(){
	window.location.href="./app/recruitment2.html";
})
$(".close").on("click",function(){
//	$("#pane").css("display","none");
//	$("#pane").find("[name]").val("");
})
})
