require(['../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global'],function(z,Vue,resource,layer,g){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#login',
 						// 数据
	                    data: {
	                    	userInfo:'',//用户信息
	                    	// imgUrl:g.imgUrl+"meta.imagecode",//
	                    	imgUrl:g.imgUrl+"getJpgCode",//
	                    	openID:g.getUrl("openId"),//openID
	                    	redirectUrl:g.getUrl("redirectUrl"),//跳转到链接
	                    },
	                    // 方法
	                    methods: {
	                    	// 授权
	                    	/*
	                    	test:function(){
	                    		var vm =this;
	                    		var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx96c14489fdd0e59a&redirect_uri=http%3a%2f%2fgaofen.iask.in%2fgaofen%2fhtml%2flogin.html&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	                    		// var url = "https://open.weixin.qq.com/connect/oauth2/authorize";
	                    		var params = {
	                    			"appid":"wx96c14489fdd0e59a",
	                    			"redirect_uri":"http%3a%2f%2fgaofen.iask.in%2fgaofen%2fhtml%2flogin.html",
	                    			"response_type":"code",
	                    			"scope":"snsapi_base",
	                    			"state":"123#wechat_redirect"
	                    		}
	                    		var url="https://api.douban.com//v2//book//17604305";
	                    		vm.$http.jsonp(url,params).then(function(res){
									g.popupMobile.msg('成功'); //失败处理
							    },function(){
							       g.popupMobile.msg('请求失败处理'); //失败处理
							    });
	                    		$.ajax({
							        url: url,
			                        type:"GET",
			                        dataType:"jsonp",
			                        cache:false,
			                        // data:params,
			                        success: function(){
			                          console.log("success")
			                        },
			                        error:function () {
			                           console.log("error")
			                        }
			                    });
	                    	},
	                    	*/
	                    	// 验证码
        					reloadCode:function(){
        						var vm = this;
        						var img = document.getElementById("img-code");
								img.src =vm.imgUrl+'?'+new Date();
        					},
	                    	login:function(){
	                    		var vm = this;
	                    		var userName = $('.user-name').val();
	                    		var password = $('.password').val();
	                    		var validation = $('.validation').val();
	                    		var statu = $('#weuiAgree').is(':checked');//是否记住账号
	                    		var autoStatus = 0;
	                    		var randCode=$('.validation').val();//验证码、
	                    		if(userName==null || userName.length==0){
	                    			g.popupMobile.msg("请输入用户名");
	                    			return false;
	                    		}
	                    		if(password==null || password.length==0){
	                    			g.popupMobile.msg("请输入密码");
	                    			return false;
	                    		}
	                    		// g.popupMobile.msg(vm.openID)
	                    		var param = {
	                    			"openId":vm.openID,
	                    			// "openId":"omx2jwcA3jcpugP9KBDqIpCaPgKU",
									"userName" :userName,
									"passWord" :password,
									"autoStatus":1,//1:7日内自动登陆
									"randCode":randCode,
		                    	};
		                    	// 把openid缓存起来
		                    	var storage=window.localStorage;
		                    	storage.setItem("openId",vm.openID);
		                		//发送post请求
							    var url=g.baseUrlfjh+"user/login";
							    vm.$http.post(url,param).then(function(res){
									if(res.data.code==200){
									 	g.popupMobile.msg("登陆成功");
									 	vm.userInfo = res.data.data;
									 	// g.popupMobile.msg("跳转")
									 	// 解除绑定之后从前端跳转，url没有携带redirectUrl
									 	if(vm.redirectUrl==''||vm.redirectUrl==null){
									 		window.location.href='./logout.html?openId='+vm.openID;
									 	}else{
									 		window.location.href=vm.redirectUrl;
									 	}
									}else{
									  	g.popupMobile.msg(res.data.error);
									  	vm.reloadCode();//后台验证码错误后刷新验证码
									}
							    },function(res){
							       g.popupMobile.msg(res.data.error); //失败处理
							       vm.reloadCode();
							    });
	                    	},
	                    	/**
	                    	getCode:function(){
	                    		window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx96c14489fdd0e59a&redirect_uri=http%3a%2f%2fgaofen.iask.in%2fgaofen%2fhtml%2flogin.html&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	                    		
	                    	},
	                    	getOpenId:function(){
	                    		g.popupMobile.msg(g.getUrl("openId"))
	                    		console.log(g.getUrl("openId"))
	                    	},
	                    	**/
	                    	
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	vm.openID = g.getUrl("openId")
	                    	// g.popupMobile.msg(vm.openID)
	                    	// g.popupMobile.msg(vm.redirectUrl)
	                    	// vm.getCode();
	                    	// vm.autoLogin();
	                    	// vm.getOpenId();
	                    	// vm.test1();
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




