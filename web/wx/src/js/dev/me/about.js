require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global'],function(z,Vue,resource,layer,g){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#about',
 						// 数据
	                    data: {
	                    	content:'',
	                    },
	                    // 方法
	                    methods: {
	                    	getAbout:function(){
	                    		var vm=this;
							    var url=g.baseUrlgch+"/notice/getContactUsContent";
							    vm.$http.get(url).then(function(res){
									if(res.data.code==200){
									 	console.log("success")
									 	vm.content = res.data.data;
									 	console.log(res.data.data.content)
									}else{
									  	g.popupMobile.msg(res.data.error);
									}
							    },function(){
							        g.popupMobile.msg('获取失败'); //失败处理
							    });
	                    	}
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	vm.getAbout();
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




