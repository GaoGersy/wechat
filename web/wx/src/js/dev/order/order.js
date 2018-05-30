require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global','dropload'],function(z,Vue,resource,layer,g,dropload){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#order',
 						// 数据
	                    data: {
	                    	orderId:g.getUrl("orderId"),//订单id
	                    	disstate:g.getUrl("disstate"),//订单状态，0未审核，1审核
	                    	applyState:'',//订单状态（文本）
	                    	childorderList:[],//子订单列表
	                    	orderStatu:'',//订单状态
	                    	orderdate:'',//订单日期
	                    	satelliteId:'',//卫星的名称
	                    	orderId:g.getUrl("orderId"),//订单id
	                    	dataapplication:g.getUrl("dataapplication"),//订单名称
	                    	currentPage:1,//当前页码
	                    	iswork:false,//请求是否出错	true 请求出错 ，false请求不出错
	                    	isnull:false,//请求是否有数据 true 无数据，false有数据
	                    	isloading:false,//是否在请求 ,true 显示，false不显示
	                    },
	                    // 方法
	                    methods: {	
	                    	// 查看影像详情
						    getDetail:function(item){
						    	var vm =this;
						    	window.location.href="./detail.html?dataId="+item.productId+"&disstate="+vm.disstate;
						    },
						    // 获取子订单列表
						    getChildOrder:function(me){
						    	var vm = this;
						    	vm.isloading = true;
						    	var param = {
									"orderId":vm.orderId,
									"currentPage" :vm.currentPage,
								  	"pageSize" :10,
		                    	};
							    var url=g.baseUrlgch+"order/getChildOrderByOrderId";
							    vm.$http.post(url,param).then(function(res){
							    	// g.popupMobile.msg("成功");
							    	// 网络请求出错 请求返回500
						    		if (res.data.code !=200) {
						    			vm.isloading = false;
						    			vm.iswork = true;
						    			return false;
						    		}
							    	var dataList = res.data.data.list;
							    	if(dataList.length!=0){
								    	$('.order-list').show();
								    	vm.isnull = false;
								    	setTimeout(function(){
								    		$('.dropload-down').show();
								    	},1000)
										$.each(dataList, function(i,e){
	                                       e.backTime = g.dateFormat(e.backTime,"yyyy-MM-dd");
	                                       e.src = g.imgUrl+"getThumb?metaId="+e.productId;//图片的路径
	                                       e.disstate = vm.disstate;//0未审核，1审核 ，2未通过
	                                       if(vm.disstate==0){
				                    			vm.applyState="未审核";
				                    		}else if(vm.disstate==1){
				                    			vm.applyState = "审核通过";
				                    		}else {
				                    			vm.applyState='未通过'
				                    		}
	                                       if(e.satelliteId=="GF1"){
	                                       		e.satelliteId = "高分一号";
	                                       }else if(e.satelliteId=="GF2"){
	                                       		e.satelliteId = "高分二号";
	                                       }else if(e.satelliteId=="GF3"){
	                                       		e.satelliteId = "高分三号";
	                                       }else if(e.satelliteId=="GF4"){
	                                       		e.satelliteId = "高分四号";
	                                       }
	                                       vm.childorderList.push(e);
	                                    })
	                                    var pageCount =res.data.data.pageCount;//总页数
								    	var currentpage = res.data.data.currentPage;//当前页码
								    	console.log("pageCount:"+pageCount)
									 	if(pageCount>currentpage){
									 		// 解锁
					                        me.unlock();
					                        // 有数据
					                        me.noData(false);
					                    }else{
					                    	// console.log("无数据")
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
					                }
					                else{
					                	setTimeout(function(){
							    			vm.isnull = true;
							    		},1000)
					                }
							    },function(){
							    	g.popupMobile.msg("请求失败");
							    	me.resetload();
							    });
							    setTimeout(function(){
							    	vm.isloading = false;
							    },1000)
							    
						    }
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	vm.token = localStorage.getItem("token");
	                    	// vm.getChildOrder();

	                    	// 上拉加载
	                    	$('.content').dropload({
						        scrollArea : window,
						        domDown : {
						            domClass   : 'dropload-down',
						            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
						            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
						            domNoData  : '<div class="dropload-noData">已经到底了！</div>'
						        },
						        loadDownFn : function(me){
									vm.getChildOrder(me);
							    }
						    });
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




