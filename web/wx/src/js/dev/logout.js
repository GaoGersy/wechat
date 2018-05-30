require(['../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global'],function(z,Vue,resource,layer,g){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#logout',
 						// 数据
	                    data: {
	                    	userName:'',//账号
	                    	openID:g.getUrl("openId"),//openid
	                    	redirectUrl:g.getUrl("redirectUrl"),//跳转的页面
	                    	isLogin:false,
	                    	noLogin:false,
	                    },
	                    // 方法
	                    methods: {	
	                    	// 解绑
	                    	logout:function(){
	                    		var vm = this;
	                    		// g.popupMobile.msg(vm.openID)
	                    		var param = {
									// "openId":"omx2jwWpGV5BflmEoXRpMvcDN3hs",
									// "openId":"omx2jwf81VtvaCk17L-LYPZwy4vE",
									"openId":vm.openID,
									// "token":localStorage.getItem("token"),
		                    	};
							    var url=g.baseUrlgch+"user/unLock";
							    vm.$http.post(url,param).then(function(res){
									if(res.data.code==200){
									 	g.popupMobile.msg("解绑成功");
									 	vm.userName = res.data.data.admin;
									 	setTimeout(function(){
									 		window.location.href="./login.html?openId="+vm.openID;
									 	},1000)
									}else{
									  	g.popupMobile.msg("解绑失败");
									}
							    },function(){
							    	g.popupMobile.msg("解绑失败");
							    });
	                    	},
	                    	// 获取用户信息
	                    	getUserInfo:function(){
	                    		var vm=this;
	                    		// g.popupMobile.msg(vm.openID)
	                    		//发送post请求
	                    		var param = {
									// "openId":"omx2jwWpGV5BflmEoXRpMvcDN3hs",
									// "openId":"omx2jwf81VtvaCk17L-LYPZwy4vE",
									"openId":vm.openID,
		                    	};
							    var url=g.baseUrlgch+"user/getUserInfo";
							    vm.$http.post(url,param).then(function(res){
									if(res.data.code==200){
									 	// g.popupMobile.msg("成功获取用户信息");
									 	vm.userName = res.data.data.userName;
									 	console.log(vm.userName)
									}else{
									  	g.popupMobile.msg(res.data.error);
									}
							    },function(){
							        g.popupMobile.msg('获取信息失败'); //失败处理
							    });
	                    	},
	                    	/**
	                    	login:function(){
	                    		var vm =this;
	                    		window.location.href="./login.html?openId="+vm.openID+"&redirectUrl="+vm.redirectUrl;
	                    	},
	                    	**/
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	// g.popupMobile.msg(vm.openID)
	                    	vm.getUserInfo();

	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




