require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global','dropload'],function(z,Vue,resource,layer,g,dropload){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#searchResult',
 						// 数据
	                    data: {
	                    	beginTime:g.getUrl("beginTime"),//开始时间
	                    	endTime:g.getUrl("endTime"),//结束时间
	                    	bounds:g.getUrl("bounds"),//区域
	                    	cloud:g.getUrl("cloud"),//云盖量
	                    	time:'',//展示的时间
	                    	satelliList:[],//影像数据列表
	                    	satelliteId:'',//卫星的名字
	                    	src:'',//影像的路径
	                    	type:'',
	                    	iswork:false,//请求是否出错	true 请求出错 ，false请求不出错
	                    	isnull:false,//请求是否有数据 true 无数据，false有数据
	                    	isloading:false,//是否在请求 ,true 显示，false不显示
	                    	gfName:'',//卫星名称
	                    	sensorId:'',//传感器
	                    },
	                    // 当前过滤器
	                    filters:{
	                    	cloudFormat:function(value){
	                    		return	value+"%";
	                    	}
	                    },
	                    // 方法
	                    methods: {	

						    // 查询数据
						    getData:function(param,me){
						    	var vm = this;
						    	vm.isloading = true;
						        var url=g.baseUrlgch+"getImageList";
						    	vm.$http.post(url,param).then(function(res){
						    		// 网络请求出错 请求返回500
						    		if (res.data.code!=200) {
						    			// $('.loading-data').hide();
						    			vm.isloading = false;
						    			vm.iswork = true;
						    			return false;
						    		}
						    		if(res.data.code==200){
						    		 vm.iswork = false;
								 	// g.popupMobile.msg("成功");
								 	var dataList = res.data.data.data;
								 	if(dataList.length!=0){
								 		$('.content').show();
								 		// $('.null').hide();
								 		vm.isnull=false;
									 	$.each(dataList,function(i,e){
									 		if(e.satelliteId == "GF1"){
									 			e.gfName = "高分一号";
										 	}else if(e.satelliteId == "GF2"){
												e.gfName = "高分二号";
										 	}else if(e.satelliteId == "GF3"){
										 		e.gfName = "高分三号";
										 	}else if(e.satelliteId== "GF4"){
										 		e.gfName = "高分四号";
										 	}
										 	var data = g.getPx(e.satelliteId,e.sensorId);
										 	e.sensorId = data.resolving;
										 	// e.sensorId = e.describe;
										 	// if(e.sensorId == "PMS1" || e.sensorId == "PMS2"){
										 	// 	e.sensorId = "全色/多光谱";
										 	// }else{
										 	// 	e.sensorId = "多光谱"
										 	// }
										 	e.sceneDate = g.dateFormat(e.sceneDate,"yyyy-MM-dd");
										 	e.src = g.imgUrl+"getThumb?metaId="+e.dataId;//图片的路径
										 	e.cloudPercent = e.cloudPercent+"%";
										 	vm.satelliList.push(e);
									 	})
									 	var pageCount =res.data.data.pageCount;//总页数
								    	var currentpage = res.data.data.currentPage;//当前页码
									 	if(pageCount>currentpage){
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
					                    param.currentPage++;//当前页码
					                    // 为了测试，延迟1秒加载
					                    setTimeout(function(){
					                        // 每次数据插入，必须重置
					                        me.resetload();
					                    },1000);
									}else{
										setTimeout(function(){
											// $('.null').show();
											// $('.null').css('display',"block");
											vm.isnull=true;
										},1000)
									}
								}else{
								  	g.popupMobile.msg("请求失败");
								  	me.resetload();//重置，会重新加载一次
								} 
								setTimeout(function(){
						         	// $('.loading-data').hide();
						         	vm.isloading = false;
						        },1000)
								});
						    },
						    // 查看影像详情
						    getDetail:function(item){
						    	var type = item.satelliteId;
						    	var satelliteType;
						    	if(type == "高分一号"){
						    		satelliteType = 1;
						    	}else if(type == "高分二号"){
						    		satelliteType = 2;
						    	}else if(type == "高分三号"){
						    		satelliteType = 3;
						    	}else if(type == "高分四号"){
						    		satelliteType = 4;
						    	}
						    	window.location.href="./detail.html?dataId="+item.dataId+"&satelliteType="+satelliteType;
						    },
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	var data = g.getUrl("data");
	                    	// vm.getData(data);
	                    	var data = $.parseJSON(data)
	                    	
	                    	// 上拉加载插件
						    $('.content').dropload({
						        scrollArea : window,
						        domDown : {
						            domClass   : 'dropload-down',
						            domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
						            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
						            domNoData  : '<div class="dropload-noData">已经到底了！</div>'
						        },
						        loadDownFn : function(me){
						        	vm.getData(data,me);
							    }
						    });
	                    	
	                    }
 					})
 					// 全局的过滤器
 					Vue.filter("cloudFormat",function(value,type){
 						return value+type;
 					})

 				}
 			}
 			index.init();
 		})
		
 })




