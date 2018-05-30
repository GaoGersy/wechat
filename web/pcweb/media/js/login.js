var Login = function () {
    
    return {
        //main function to initiate the module
        init: function (baseUrl) {
        	
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "请输入用户名"
	                },
	                password: {
	                    required: "请输入密码"
	                }
	            },

//	            invalidHandler: function (event, validator) { //display error alert on form submit   
//	                $('.alert-error', $('.login-form')).show();
//	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	                
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	                adminLogin(baseUrl);
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                  adminLogin(baseUrl);
	                }
	                return false;
	            }
	        });

        }
        
    };

}();
function adminLogin(baseUrl) {
	var username = $("#username").val();
	var password = $("#password").val();
	if(username != "" && username != null && password != "" && password != null){
		var param = {};
		param.userName = username;
		param.passWord = password;
		$.ajax({
			url: baseUrl + "rest/adminuser/adminLogin",
			dataType: "JSON",
			data: JSON.stringify(param),
			type: "POST",
			contentType: "application/json;charset=utf-8",
			success: function(res) {
				if(res.result == 1) {
					window.location.href = "../../index.html";
				}else{
					 $('#error_1', $('.login-form')).show();
				}
			},
			error: function() {
				 $('#error_2', $('.login-form')).show();
			}
		})
	}

};
