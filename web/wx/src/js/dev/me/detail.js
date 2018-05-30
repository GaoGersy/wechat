require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global'],function(z,Vue,resource,layer,g){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#detail',
 						// 数据
	                    data: {
	                    	id:g.getUrl("id"),//通知公告id
	                    	list:[],
	                    	title:'',
	                    },
	                    // 方法
	                    methods: {
	                    	getDetail:function(){
	                    		var vm=this;
							    var url=g.baseUrlgch+"notice/getNoticeDetail?id="+vm.id;
							    vm.$http.get(url).then(function(res){
									if(res.data.code==200){
									 	console.log("success")
									 	vm.list = res.data.data;
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
	                    	vm.getDetail();
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




