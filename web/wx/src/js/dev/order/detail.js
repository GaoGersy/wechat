require(['../../requireconfig'],function(){
 		require(['zepto','vue','vue-resource','layer','global','air'],function(z,Vue,resource,layer,g,Map){
 			
 			var index={
 				init:function(){
 					var t = this;
 					Vue.use(resource);
 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#app',
 						// 数据
	                    data: {
	                    	map:'',//地图实例
	                    	zoom:12,//缩放级别
	                    	marker:'',//标注
	                    	infoWin:'',//信息框
	                    	bounds:'',//可视区域坐标
	                    	SouthWestX:'',//左上角x
	                    	SouthWestY:'',//左上角Y
	                    	NorthEastX:'',//右下角x
	                    	NorthEastY:'',//右下角u
	                    	dataId:g.getUrl("dataId"),//子订单详情（影像）
	                    	orderDetail:[],//订单详情数据
	                    	satelliteId:'',//卫星名称
	                    	sensorId:'',//传感器
	                    	productId:'',//产品序列号
	                    	sceneId:'',//景序列号
	                    	productType:'',//数据类型 0：原始 1：正射 2：匀色 3 ：镶嵌
	                    	applyState:'',//0未审核，1审核
	                    	disstate:g.getUrl("disstate"),//订单状态，0未审核，1审核
	                    	bottomLeftLatitude:'',//左下角纬度
	                    	bottomLeftLongitude:'',//左下角经度
	                    	bottomRightLatitude:'',//右下角纬度
							bottomRightLongitude:'',//右下角经度
	                    	topRightLatitude:'',//右上角纬度
	                    	topRightLongitude:'',//右上角经度
							topLeftLatitude:'',//左上角纬度
							topLeftLongitude:'',//左上角经度
							LonAndLat:'',//所有的经纬度
	                    },
	                    // 方法
	                    methods: {	
	                    	
						    onLoad:function(x,y){
						    	var vm = this;
						    	//初始化地图对象
						         vm.map=new T.Map("mapDiv");
						         //设置显示地图的中心点和级别
						         // vm.map.centerAndZoom(new T.LngLat(116.40969,39.89945),12);
						         // vm.map.centerAndZoom(new T.LngLat(113.25723,23.12842),12);
						         vm.map.centerAndZoom(new T.LngLat(x,y),12);

						    },
						    // 获取订单详情
						    getOrderDetail:function(){
						    	var vm = this;
						    	var param = {
									"dataid":vm.dataId,
		                    	};
							    var url=g.baseUrlfjh+"order/getBasSateliteTerm";
							    vm.$http.post(url,param).then(function(res){
									if(res.data.code==200){
										$('.bottom-data').show();
									 	// g.popupMobile.msg("成功");
									 	console.log("vm.isShow"+vm.isShow)
									 	vm.orderDetail = res.data.data;
									 	vm.satelliteId = vm.orderDetail.satelliteid;
									 	// getPx获取传感器和像素
									 	var data = g.getPx(vm.orderDetail.satelliteid,vm.orderDetail.sensorid);
									 	vm.sensorId = data.resolving;

									 	// vm.sensorId = vm.orderDetail.sensorid;
									 	// if(vm.sensorId=="PMS1" || vm.sensorId=="PMS2"){
									 	// 	vm.sensorId = "全色/多光谱";
									 	// }else{
									 	// 	vm.sensorId = "多光谱";
									 	// }
									 	
									 	vm.productId = vm.orderDetail.productid;
									 	vm.sceneId = vm.orderDetail.sceneid;
									 	//产品类型：0：原始 1：正射 2：匀色 3 ：镶嵌
										if(vm.orderDetail.producttype=="0"){
											vm.productType = "原始";
										}else if(vm.orderDetail.producttype=="1"){
											vm.productType = "正射";
										}else if(vm.orderDetail.producttype=="2"){
											vm.productType = "匀色";
										}else if(vm.orderDetail.producttype=="3"){
											vm.productType = "镶嵌";
										}
									 	// vm.applyState = vm.disstate;
									 	// vm.disstate 0 未审核，1，审核通过，2未通过
								 	    if(vm.disstate==0){
			                    			vm.applyState="未审核";
			                    		}else if(vm.disstate==1){
			                    			vm.applyState = "审核通过";
			                    		}else {
			                    			vm.applyState="未通过";
			                    		}	
									 	if(vm.satelliteId=="GF1"){
                                       		vm.satelliteId = "高分一号";
	                                    }else if(vm.satelliteId=="GF2"){
	                                       	vm.satelliteId = "高分二号";
	                                    }else if(vm.satelliteId=="GF3"){
	                                       	vm.satelliteId = "高分三号";
	                                    }else if(vm.satelliteId=="GF4"){
	                                       	vm.satelliteId = "高分四号";
	                                    }

									 	vm.bottomLeftLatitude = vm.orderDetail.bottomleftlatitude;//左下角纬度
				                    	vm.bottomLeftLongitude = vm.orderDetail.bottomleftlongitude;//左下角经度
				                    	vm.bottomRightLatitude = vm.orderDetail.bottomrightlatitude;//右下角纬度
										vm.bottomRightLongitude = vm.orderDetail.bottomrightlongitude;//右下角经度
				                    	vm.topRightLatitude = vm.orderDetail.toprightlatitude;//右上角纬度
				                    	vm.topRightLongitude = vm.orderDetail.toprightlongitude;//右上角经度
										vm.topLeftLatitude = vm.orderDetail.topleftlatitude;//左上角纬度
										vm.topLeftLongitude = vm.orderDetail.topleftlongitude;//左上角经度
										console.log(vm.orderDetail.topleftLongitude)
										
										// 初始化地图
										vm.onLoad(vm.bottomLeftLatitude,vm.bottomLeftLongitude);
										
										var points = [];
										points.push(new T.LngLat(vm.bottomRightLongitude, vm.bottomRightLatitude));//右下角
								        points.push(new T.LngLat(vm.bottomLeftLongitude, vm.bottomLeftLatitude));//左下角
								        points.push(new T.LngLat(vm.topLeftLongitude, vm.topLeftLatitude));//左上角
								        points.push(new T.LngLat(vm.topRightLongitude,vm.topRightLatitude));//右上角

								        vm.LonAndLat = '('+vm.bottomRightLongitude+','+vm.bottomRightLatitude+'),('+vm.bottomLeftLongitude+','+vm.bottomLeftLatitude+'),('+vm.topLeftLongitude+','+vm.topLeftLatitude+'),('+vm.topRightLongitude+','+vm.topRightLatitude+')';
								        console.log(vm.LonAndLat)
								        vm.map.panTo(new T.LngLat(vm.bottomRightLongitude,vm.bottomRightLatitude));
								        vm.map.setZoom(9);
								        //创建面对象
								        var polygon = new T.Polygon(points,{
								            color: "red", weight: 2, opacity: 0.5, fillColor: "#FFFFFF", fillOpacity: 0.5
								        });
								        //向地图上添加面
								        vm.map.addOverLay(polygon);
									}else{
									  	g.popupMobile.msg("请求失败1");
									  	
									}
							    },function(){
							    	g.popupMobile.msg("请求失败2");
							    });
							    setTimeout(function(){
							    	$('.loading').hide();
							    },500)
							    
						    },
						    upAndDown:function(event){
						    	var vm =this;
						    	var current = $(event.currentTarget);
						    	var isshow = current.is(".down");
						    	// 隐藏
						    	if(isshow){
						    		current.addClass("up").removeClass('down');
						    		$('.bottom-data').css('bottom',-120);
						    	}else{//显示
						    		current.addClass("down").removeClass('up');
						    		$('.bottom-data').css('bottom',0);
						    	}
						    	console.log(($(event.currentTarget).is(".down")))//获取当前对象是否有某类名
						    },
						   
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	// vm.onLoad();
	                    	vm.getOrderDetail();
	      //               	$('.up').on('click', function(event) {
							//    $('.bottom-data').css('bottom',-120);
							//    $('.up').removeClass('up').addClass('down');
							// });
							// $('.down').on('click', function(event) {
							// 	console.log("down")
							//    $('.bottom-data').css('bottom',0);
							//    $('.down').removeClass('down').addClass('up');
							// });

	                    	$('.detail').on('touchstart', function(event) {
							    // event.preventDefault();
							    // g.popupMobile.msg("start");
							    vm.map.disableDrag();	
							    event.stopPropagation(); 
							});
							$('.detail').on('touchmove', function(event) {
							    // g.popupMobile.msg("move");
							    vm.map.disableDrag();
							     event.stopPropagation(); 
							});
							$('.detail').on('touchend', function(event) {
							    // event.preventDefault();
							     // g.popupMobile.msg("end");
							    setTimeout(function(){
									vm.map.enableDrag();
							    },2000)	
							});

							$('.detail').on('swipe', function(event) {
							    // g.popupMobile.msg("swipe");
							    vm.map.disableDrag();
							     event.stopPropagation(); 
							});
							$('.detail').on('swipeLeft', function(event) {
							    // g.popupMobile.msg("swipeLeft");
							    vm.map.disableDrag();
							     event.stopPropagation(); 
							});
							$('.detail').on('swipeRight', function(event) {
							    // g.popupMobile.msg("swipeRight");
							    vm.map.disableDrag();
							     event.stopPropagation(); 
							});
							$('.detail').on('swipeUp', function(event) {
							    // g.popupMobile.msg("swipeUp");
							    vm.map.disableDrag();
							     event.stopPropagation(); 
							});
							$('.detail').on('swipeDown', function(event) {
							    // g.popupMobile.msg("swipeDown");
							    vm.map.disableDrag();
							     event.stopPropagation(); 
							});
	      //               	$('.detail').on('touchmove', function(event) {
							//     event.preventDefault();
							// });

	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




