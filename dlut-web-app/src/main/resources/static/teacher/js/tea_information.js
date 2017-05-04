$(document).ready(function(){
    console.log($("#principal").val());

    var principal = $("#principal").val();
    var schStatus = $("#schStatus").val();
    if(principal=="ACADEMY"|| principal=="ACADMIN"){
      if(schStatus!="00") {
       $("#role").css({"display":"none"});
      }
    }
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
            url:"/app/teacher/teaUpdateStuInfo",
            data:{
                id: $('#id').val(),
                allupdateStatus:"1",
                status:"01"
            },
            success:function(data){
                window.location.href="/app/teacher/editStuInformation?id="+$('#id').val();
            },
            error:function(err){
                console.log(err)
            }
        });
    })
    $(".false").on("click",function(){
        $.ajax({//请求数据
            type:"post",
            url:"/app/teacher/teaUpdateStuInfo",
            data:{
                id: $('#id').val(),
                allupdateStatus:"1",
                status:"02"
            },
            success:function(data){
                window.location.href="/app/teacher/editStuInformation?id="+$('#id').val();
            },
            error:function(err){
                console.log(err)
            }
        });
    })
})
