	$(document).ready(function() {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
           /* valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'*/
        },
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: '单位名称不能为空'
                    }
                }
            },
            orgCode:{
                validators: {
                    notEmpty: {
                        message: '组织机构代码不能为空'
                    }
                }
            },
            department: {
                validators: {
                    notEmpty: {
                        message: '主管部门不能为空'
                    }
                }
            },
            industry: {
                validators: {
                    notEmpty: {
                        message: '单位行业不能为空'
                    }
                }
            },
            nature: {
                validators: {
                    notEmpty: {
                        message: '单位类型不能为空'
                    }
                }
            },
            size: {
                validators: {
                    notEmpty: {
                        message: '单位规模不能为空'
                    }
                }
            },
            capital: {
                validators: {
                    notEmpty: {
                        message: '注册资金不能为空'
                    }
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: '单位地址不能为空'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '详细地址不能为空'
                    }
                }
            },
            describe: {
                validators: {
                    notEmpty: {
                        message: '单位简介不能为空'
                    }
                }
            },
            contacts: {
                validators: {
                    notEmpty: {
                        message: '联系人不能为空'
                    }
                }
            },
            zipCode: {
                validators: {
                    notEmpty: {
                        message: '单位邮编不能为空'
                    }
                }
            },
            acceptTerms: {
                validators: {
                    notEmpty: {
                        message: '请接受条款和政策'
                    }
                }
            },
			telphone: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    regexp: {
                        regexp: /1[34578]\d{9}/,
                        message: '请输入正确的格式'
                    }
                }
            },
 			fixedTelphone: {
                validators: {
                    notEmpty: {
                        message: '固定电话不能为空'
                    },
                    regexp: {
                        regexp: /\d{3}-\d{8}|\d{4}-\d{7}/,
                        message: '请输入正确的格式'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '电子邮箱不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮箱格式'
                    }
                }
            },
            website: {
                validators: {
                    uri: {
                        allowLocal: true,
                        message: '请输入正确的网址格式'
                    }
                }
            },
//          phoneNumberUS: {
//              validators: {
//                  phone: {
//                      message: 'The input is not a valid US phone number'
//                  }
//              }
//          },
//          phoneNumberUK: {
//          	validators: {
//          		phone: {
//          			message: 'The input is not a valid UK phone number',
//          			country: 'GB'
//          		}
//          	}
//          },
          /*  color: {
                validators: {
                    hexColor: {
                        message: 'The input is not a valid hex color'
                    }
                }
            },*/
           /* zipCode: {
                validators: {
                    zipCode: {
                        country: 'US',
                        message: 'The input is not a valid US zip code'
                    }
                }
            },*/
            pwd: {
                validators: {
                    notEmpty: {
                        message: '登录密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码长度为6-18'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'pwd',
                        message: '登录密码要和确认登录密码要一致'
                    }
                }
            },
            file:{
            	 validators: {
                    notEmpty: {
                        message: '请上传组织机构代码证或社会新红图片'
                    }
                }
            }
//          ages: {
//              validators: {
//                  lessThan: {
//                      value: 100,
//                      inclusive: true,
//                      message: 'The ages has to be less than 100'
//                  },
//                  greaterThan: {
//                      value: 10,
//                      inclusive: false,
//                      message: 'The ages has to be greater than or equals to 10'
//                  }
//              }
//          }
        }
    });
   var u = $.upload({
		fileInpit:$('#file'),//选择图片的file标签
		url:'php/post_file.php',//上传时候的url
		btn:$('#btn'),//点击提交那个按钮
		viewBox:$('.preview'),//显示被选择的列表
		viewFn:function(json){//打算一个可以添加的数据
			u.fileReader(json,function(data){//data:一个图片源码
				$('.preview').find('ul').append('<li><img src="'+data+'"><button name="'+json.name+'">更换图片</button></li>');
				$('.preview').find('ul').off().click(function(ev){
					if(ev.target.tagName === 'BUTTON'){
						u.removeData($(ev.target).attr('name'));
						$(ev.target).parent('li').remove();
					}
				});
			});
		},
		onLoading:function(num){
			;(function(i){
				$('#loading div').stop().animate({
					width:(i/u.obj.length)*600
				},function(){
					$('#loading div').stop().animate({
						opacity:0
					},function(){
						u.settings.clear();
						$('#loading div').css({
							opacity:1,
							width:0
						});
					});
				});
			})(num);
		},
		clear:function(){
			u.deleteDate();
			$('.preview').find('ul').html('');
			console.log('成功');
		},
		drag:$('.drag')
	});

	//验证邮箱
	$("input[type='email']").eq(0).on("blur",function(){
		var emailVal=$("input[type='email']").eq(0).val();
		console.log(1);
		$.ajax({
			type:"get",
			url:"/register/company/findByEmail",
			async:true,
			dataType:"json",
			data:{
				"email":emailVal
			},
			success:function(data){
				console.log(data)
				if(data.status==403){
					$(".small").css("display","block")
					$("input[type='email']").eq(0).parent().parent().removeClass("has-feedback")
					$("input[type='email']").eq(0).parent().parent().removeClass("has-success")
					$("input[type='email']").eq(0).parent().parent().addClass("has-error")
				}else{
					$(".small").css("display","none")
				}
			}
		});
	})

});
