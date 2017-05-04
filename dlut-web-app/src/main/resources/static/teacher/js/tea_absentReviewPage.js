    $(document).ready(function(){
	var onOff=true;
	getContent(1);
	function getContent(now){
		$("#page").attr({"pagelistcount":$("#number").val()||10});
		var size = $("#number").val()||10;
			$.ajax({//请求数据
			type:"get",
			url:"/app/teacher/findVio?page="+(now-1)+"&size="+size+"&sort=stuNo,asc",
			data:{principal:$('#principal').val(),
            stuNo:$('#stuNo').val(),
            stuName:$('#stuName').val(),
            departmentId:$('#departmentId').val(),
            majorName:$('#majorName').val(),
            applicationTime:$('#applicationTime').val(),
            auditStatus:$('#auditStatus').val()
            },
			success:function(obj){
			console.log(obj)
				$("#checkAll").prop({"checked":false});
				var inner="";

				$.each(obj.content, function(i,e) {

				var at = $(e)[0].applicationTime;
                var minute = at.minute;
                if(minute<10){ minute = '0'+minute}
                var date = at.year+'-'+at.monthValue+'-'+at.dayOfMonth+' '+at.hour+':'+minute;
                var status = $(e)[0].auditStatusName;
				inner +='<tr><td><input type="checkbox" value="'+$(e)[0].id+'" class="checkbox" /></td><td>'+$(e)[0].stuNo+'</td><td>'+ $(e)[0].stuName+'</td><td>'+$(e)[0].recName+'</td><td>'+$(e)[0].reasonName+'</td><td>'+status+'</td><td>'+date+'</td><td>'+$(e)[0].department+'</td><td>'+$(e)[0].majorName+'</td><td><a href="/app/teacher/teaAbsent.html?vid='+$(e)[0].id+'" class="btn btn-default">详情</a></td><td><a class="a btn btn-default" data-val="'+$(e)[0].id+'">审核通过</a></td></tr>';


				});
				$("tbody").html(inner);
				if(onOff){
					$("#page").initPage(obj.totalElements,1,getContent);
				}
				onOff=false;
			},
			error:function(err) {
			    alert("请求不成功！")
			}
		});
	}

$("#btn").on("click",function(){
	onOff=true;
	getContent(1);
})
$("#number").on("change",function(){
	onOff=true;
	getContent(1);
})
//-------全选按钮-------------------
var box=[];
$("#checkAll").on("click",function(){//点击全选按钮
	var isTrue= $(this).prop("checked")//
	$(".checkbox").each(function(i,e){
		$(this).prop("checked",isTrue)//所有的跟全选按钮保持一致
	})
})
$("tbody").on("click",$(".checkbox"),function(){
	var j=0;
	$(".checkbox").each(function(i,e){
		if($(".checkbox").eq(i).prop("checked")==false){
			j++;
		}
	})
	if(j>0){
		$("#checkAll").prop({"checked":false})
	}else{
		$("#checkAll").prop({"checked":true})
	}
})
//===批量通过===============
$("#batch").on("click",function(){
	box=[];
	$(".checkbox").each(function(i,e){
		if($(e).prop("checked")){
			box.push($(e).val())
		}
	})
	$.ajax({
		type:"post",
		url:"/app/teacher/updateMoreVioAudit",
		data:{
			"ids":box
		},
		traditional: true,
		success:function(data){
			console.log(data);
			alert(data);
			onOff=true;
			getContent($(".pageItemActive").html());
		},
		error:function(data){
			alert("失败！");
		}
	})
})
//--审核通过按钮----------------------------------------
$("tbody").on("click",".a",function(){
	var arr=$(this).attr("data-val");
	agree(arr);
})
function agree(id){
    $.ajax({
        type:"post",
        url:"/app/teacher/updateVioAudit2",
        data:{id:id},
        success:function(data){
            alert(data);
            onOff=true;
			getContent($(".pageItemActive").html());
        },
        error:function(err){
            alert("请求不成功！");
        }
    });
}

})


