require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global','dropload'],function(z,Vue,resource,layer,g,dropload){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					// 是否审核 0未审核，1审核完成 2未通过
 					Vue.filter('isOk', function(input) {
	                    var vm =this;
	                    console.log(input)
                		if(input==0){
                			input = "未审核";
                		}else if(input==1){
                			input="审核通过";
                		}else {
                			input="未通过";
                		}
                		return input;		
	                    		
	                });
	                Vue.filter('isShow', function(input) {
	                    var vm =this;
                		if(input == 0){
                			vm.input=1;
                		}else {
                			vm.input = 0;
                		}
                		return vm.input;		
	                    		
	                });

 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#orderList',
 						// 数据
	                    data: {
	                    	orderList:[],//订单列表
	                    	orderdate1:'',//订单时间
	                    	isCancel:false,//是否可以取消订单 
	                    	token:'',
	                    	loginState:0,//0:没有登陆，1：已经登陆
	                    	dropload:'',
	                    	currentPage:1,//当前页码
	                    	me:'',
	                    	iswork:false,//请求是否出错	true 请求出错 ，false请求不出错
	                    	isnull:false,//请求是否有数据 true 无数据，false有数据
	                    	isloading:false,//是否在请求 ,true 显示，false不显示
	                    	openID:g.getUrl("openId"),//openid
	                    	userName:'',//用户名字
	                    	applyState:''//审核状态
	                    },
	                    // 方法
	                    methods: {	
	                    	// 获取用户信息
	                    	getUserInfo:function(itemIndex,me){
	                    		var vm=this;
	                    		if(vm.userName==""){
	                    			console.log("空")
	                    			//发送post请求
		                    		var param = {
										"openId":g.getUrl("openId"),
										// "openId":"omx2jwWpGV5BflmEoXRpMvcDN3hs",
										// "openId":"omx2jwcA3jcpugP9KBDqIpCaPgKU",
			                    	};
								    var url=g.baseUrlfjh+"user/getUserInfo";
								    vm.$http.post(url,param).then(function(res){
										if(res.data.code==200){
										 	vm.userName = res.data.data.userName;
										 	vm.getList(itemIndex,me);
										}else{
										  	g.popupMobile.msg(res.data.error);
										}
								    },function(){
								        g.popupMobile.msg('获取用户信息失败'); //失败处理
								    });
	                    		}else{
	                    			vm.getList(itemIndex,me);
	                    		}
	                    	},
	                    	// 查看影像详情
						    getOrder:function(item){
						    	window.location.href="./order.html?orderId="+item.id+"&dataapplication="+item.dataapplication+"&disstate="+item.disstate;
						    },
						    // 取消订单
						    cancelOrder:function(item,e){
						    	var vm = this;
						    	var el = $(e.currentTarget);
						    	g.popupMobile.dialog2({
						    		className:'order-dialog'
								    ,content: '是否确定取消订单'
								    ,btn: ['是', '否']
								    ,yes: function(index){
								      //取消订单
									    var url=g.baseUrlgch+"order/cancelOrderById?id="+item.id;
									    vm.$http.get(url).then(function(res){
									    	g.popupMobile.msg(res.data.data);
									    	el.parent().parent().hide();
									    },function(){
									      g.popupMobile.msg("取消失败"); //失败处理
									    });
								      layer.close(index);
								    }
							  	});
						    },
						    getList:function(itemIndex,me){
						    	var vm =this;
						    	vm.isloading = true;
						    	vm.orderdate1=[];
						    	var timestamp = (new Date()).getTime();
						    	var oneMonthDate = timestamp - 30*24*60*60*1000;
						    	var threeMonthDate = timestamp - 3*30*24*60*60*1000;
						    	if(itemIndex == '0'){//查询一个月
						    		var orderdate = g.dateFormat(oneMonthDate,"yyyy-MM-dd hh:mm:ss");
						    	}else if(itemIndex == '1'){//查询三个月
						    		var orderdate = g.dateFormat(threeMonthDate,"yyyy-MM-dd hh:mm:ss");
						    	}else{
						    		var orderdate;//查询所有列表
						    	}
	                    		var param = {
								  // "userId" :"ye",
								  "userId" :vm.userName,//正式上线打开注释
								  "ORDERDATE":orderdate,
								  "currentPage" :vm.currentPage,
								  "pageSize" :5,
								};
								var url=g.baseUrlfjh+"order/getOrderByUserName";
							    vm.$http.post(url,param).then(function(res){
							    	vm.currentPage++;//当前页码
							    	var dataList = res.data.data.list;
							    	// 网络请求出错 请求返回500
						    		if (res.data.code !=200) {
						    			vm.isloading = false;
						    			vm.iswork = true;
						    			return false;
						    		}
							    	if(dataList.length!=0){
							    		$(".content").show();
							    		vm.isnull=false;
						    			$('.dropload-down').show();
								    	$.each(dataList, function(i,e){
	                                       e.orderdate = g.dateFormat(e.orderdate,"yyyy-MM-dd");
	                                       console.log(vm.orderdate1)
	                                       if(e.disstate==0){
	                                       	 e.a=1;
	                                       } else{
	                                       	 e.a=0;
	                                       }
	                                       // if(e.disstate==1){//0未审核，1审核
	                                       // 		vm.isCancel = false;
	                                       // }else{
	                                       // 		vm.isCancel = true;
	                                       // }

	                                       console.log(vm.isCancel)
	                                       vm.orderList.push(e);
	                                    })
	                                    console.log("数据")
	                                    console.log(vm.orderList)
	                                    var pageCount =res.data.data.pageCount;//总页数
								    	var currentpage = res.data.data.currentPage;//当前页码
									 	if(pageCount>currentpage){
									 		// 解锁
					                        me.unlock();
					                        // 有数据
					                        me.noData(false);
					                    }else{
					                        // 锁定
					                        me.lock();
					                        // 无数据
					                        me.noData();
					                    }
					                    // 为了测试，延迟1秒加载
					                    setTimeout(function(){
					                        // 每次数据插入，必须重置
					                        me.resetload();
					                    },1000);
							    	}else{
							    		setTimeout(function(){
							    			vm.isnull=true;
							    		},600)
							    	}
							    	if(itemIndex == '0'){
							    		tab1LoadEnd = true;
							    	}else if(itemIndex == '2'){
							    		tab2LoadEnd = true;
							    	}else if(itemIndex == '3'){
							    		tab3LoadEnd = true;
							    	}
							    	
							    },function(){
							      g.popupMobile.msg("请求失败"); //失败处理
							      me.resetload();
							    });
							    setTimeout(function(){
							     vm.isloading = false;
							 	},500)
						    }
	                    },
	                    filters:{
	                    	
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	// 上拉加载插件
			                   	$(function(){
								    var itemIndex = 0;
								    var tab1LoadEnd = false;
								    var tab2LoadEnd = false;
								    var tab3LoadEnd = false;
								    // tab
								    $('.tab .item').on('click',function(){
								        var $this = $(this);
								        itemIndex = $this.index();
								        $this.addClass('on').siblings('.item').removeClass('on');
								        console.log("itemIndex:"+itemIndex)
								        $('.dropload-down').hide();
								        // 如果选中菜单一
								        if(itemIndex == '0'){
								        	vm.currentPage=1;
								        	vm.orderList=[];
								            // 如果数据没有加载完
								            if(!tab1LoadEnd){
								                // 解锁
								                dropload.unlock();
								                dropload.noData(false);
								                vm.getList(itemIndex,vm.me);
								            }else{
								                // 锁定
								                dropload.lock('down');
								                dropload.noData();
								            }
								            
								        // 如果选中菜单二
								        }else if(itemIndex == '1'){
								        	vm.currentPage=1;
								        	vm.orderList=[];
								            if(!tab2LoadEnd){
								                // 解锁
								                dropload.unlock();
								                dropload.noData(false);
								                vm.getList(itemIndex,vm.me);
								            }else{
								                // 锁定
								                dropload.lock('down');
								                dropload.noData();
								            }
								            
								            // 菜单三
								        }else if(itemIndex == '2'){
								        	vm.currentPage=1;
								        	vm.orderList=[];
								            if(!tab3LoadEnd){
								                // 解锁
								                dropload.unlock();
								                dropload.noData(false);
								                vm.getList(itemIndex,vm.me);

								            }else{
								                // 锁定
								                dropload.lock('down');
								                dropload.noData();
								            }
								            
								        }
								        // 重置
								        // dropload.resetload();
								    });
								    var dropload = $('.content').dropload({
								        scrollArea : window,
									    domDown : {
								            domClass   : 'dropload-down',
								            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
								            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
								            domNoData  : '<div class="dropload-noData">已经到底了！</div>'
								        },
								        loadDownFn : function(me){
								        	vm.me = me;
								            // 加载菜单一的数据
								            if(itemIndex == '0'){
											 	// vm.getList(itemIndex,me);
											 	vm.getUserInfo(itemIndex,me)
								            // 加载菜单二的数据
								            }else if(itemIndex == '1'){
											 	// vm.getList(itemIndex,me);
											 	vm.getUserInfo(itemIndex,me)
											 // 加载菜单三的数据
								            }else if(itemIndex == '2'){
								            	vm.getUserInfo(itemIndex,me)
											 	// vm.getList(itemIndex,me);
								            }
								        }
								    });
								});
							// }
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




