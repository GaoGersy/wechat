require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global','dropload'],function(z,Vue,resource,layer,g,dropload){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					Vue.filter('timeFomat', function (value) {
					  
					})
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#message',
 						// 数据
	                    data: {
	                    	currentPage:1,//当前页码
	                    	pageSize:20,//每页的数量
	                    	list:[],
	                    	time:'',//日期
	                    	iswork:false,//请求是否出错	true 请求出错 ，false请求不出错
	                    	isnull:false,//请求是否有数据 true 无数据，false有数据
	                    	isloading:false,//是否在请求 ,true 显示，false不显示

	                    },
	                    // 方法
	                    methods: {
	                    	getList:function(me){
	                    		var vm=this;
	                    		vm.isloading = true;
							    var url=g.baseUrlgch+"notice/getAllNotice?currentPage="+vm.currentPage+"&pageSize="+vm.pageSize;
							    vm.$http.get(url).then(function(res){
							    	// 网络请求出错 请求返回500
						    		if (res.data.code!=200) {
						    			// $('.loading-data').hide();
						    			vm.isloading = false;
						    			vm.iswork = true;
						    			return false;
						    		}
									if(res.data.code==200){
										var data = res.data.data
									 	if(data.list.length!=0){
									 		$.each(data.list,function(i,e){
									 			vm.list.push(e);
									 		})
										 	if(data.hasNextPage){
										 		console.log("有数据")
										 		// 解锁
						                        me.unlock();
						                        // 有数据
						                        me.noData(false);
						                    }else{
						                    	console.log("无数据")
						                        // 锁定
						                        me.lock();
						                        // 无数据
						                        me.noData();
						                    }
						                    vm.currentPage++;//当前页码
						                    // 为了测试，延迟1秒加载
						                    setTimeout(function(){
						                        // 每次数据插入，必须重置
						                        me.resetload();
						                    },1000);
									 	}else{
									 		console.log("无数据")
									 	}
									}else{
									  	g.popupMobile.msg(res.data.error);
									  	me.resetload();//重置，会重新加载一次
									}
							    },function(){
							        g.popupMobile.msg('获取失败'); //失败处理
							    });
	                    	},
	                    	getDetail:function(item){
	                    		window.location.href='./detail.html?id='+item.id;
	                    	}
	                    },
	                    filters:{
	                    	// 时间过滤器
			                timeFomat:function(timeFomat){
	                    		var date = new Date(timeFomat).getTime();
	                    		date=g.dateFormat(date,"yyyy-MM-dd");
	                    		return date;
	                    	}
			            },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	// vm.getList();
	                    	// 上拉加载插件
						    $('.content').dropload({
						        scrollArea : window,
						        threshold:100,
						        domDown : {
						            domClass   : 'dropload-down',
						            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
						            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
						            domNoData  : '<div class="dropload-noData">已经到底了！</div>'
						        },
						        loadDownFn : function(me){
						        	vm.getList(me);
							    }
						    });
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




