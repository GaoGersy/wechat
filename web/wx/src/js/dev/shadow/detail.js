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
	                    	dataId:g.getUrl("dataId"),//影像id
	                    	satelliteType:g.getUrl("satelliteType"),//1：高分一号，2高分二号，3：高分三号，4：高分四号
	                    	imgDetail:[],//影像的详情
	                    	satelliteId:'',//卫星的名字
	                    	time:'',//采集时间
	                    	sensorId:'',//传感器
	                    	cloundPercent:'',//云盖量
	                    	heightInPixels:'',//分辨率
	                    	productId:'',//产品号
	                    	sceneId:'',//景号
	                    	productType:'',//产品类型
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
						         vm.map.centerAndZoom(new T.LngLat(x,y),12);
						    },
						    showSensor:function(){
						    	 $('.sensor1').toggleClass('hideSensor').blur();
						    },
						     // 获取影像的详情
						    getDetail:function(){
						    	var vm = this;
							    var url=g.baseUrlgch+"getImageByDataId?dataId="+vm.dataId+"&satelliteType="+vm.satelliteType;
							    vm.$http.get(url).then(function(res){
									if(res.data.code==200){
									 	// g.popupMobile.msg("成功");
									 	setTimeout(function(){
									 		// $(".detail").show();
									 		$(".detail-list").show();
									 		// $('.detail').css('display','block');
									 		// $(".detail").css("opacity",0.7);
									 	},800)
									 	
									 	vm.imgDetail = res.data.data;
									 	var data = g.getPx(vm.imgDetail.satelliteId,vm.imgDetail.sensorId);
									 	vm.heightInPixels = data.resolvingNum;//分辨率
									 	vm.sensorId = data.resolving;//传感器
									 	var gfName= vm.imgDetail.satelliteId;
									 	if(gfName == "GF1"){
									 		vm.satelliteId = "高分一号";
									 	}else if(gfName == "GF2"){
											vm.satelliteId = "高分二号";
									 	}else if(gfName == "GF3"){
									 		vm.satelliteId = "高分三号";
									 	}else if(gfName == "GF4"){
									 		vm.satelliteId = "高分四号";
									 	}
									 	vm.time = g.dateFormat(vm.imgDetail.sceneDate,"yyyy-MM-dd");
									 	vm.productId = vm.imgDetail.productId
										vm.sceneId= vm.imgDetail.sceneId
										//产品类型：0：原始 1：正射 2：匀色 3 ：镶嵌
										if(vm.imgDetail.productType==0){
											vm.productType = "原始";
										}else if(vm.imgDetail.productType==1){
											vm.productType = "正射";
										}else if(vm.imgDetail.productType==2){
											vm.productType = "匀色";
										}else if(vm.imgDetail.productType==3){
											vm.productType = "镶嵌";
										}
										
									 	// vm.sensorId = vm.imgDetail.describe;
									 	// 传感器
									 	// vm.sensorId = vm.imgDetail.sensorId;
									 	// if(vm.sensorId=="PMS1" || vm.sensorId=="PMS2" ){
									 	// 	vm.sensorId = "全色/多光谱";
									 	// }else{
									 	// 	vm.sensorId = "多光谱";
									 	// }

										vm.cloundPercent = vm.imgDetail.cloudPercent+"%";
										// vm.heightInPixels = vm.imgDetail.heightInPixels;

										vm.bottomLeftLatitude = vm.imgDetail.bottomLeftLatitude;//左下角纬度
				                    	vm.bottomLeftLongitude = vm.imgDetail.bottomLeftLongitude;//左下角经度
				                    	vm.bottomRightLatitude = vm.imgDetail.bottomRightLatitude;//右下角纬度
										vm.bottomRightLongitude = vm.imgDetail.bottomRightLongitude;//右下角经度
				                    	vm.topRightLatitude = vm.imgDetail.topRightLatitude;//右上角纬度
				                    	vm.topRightLongitude = vm.imgDetail.topRightLongitude;//右上角经度
										vm.topLeftLatitude = vm.imgDetail.topLeftLatitude;//左上角纬度
										vm.topLeftLongitude = vm.imgDetail.topLeftLongitude;//左上角经度

										vm.onLoad(vm.bottomLeftLatitude,vm.bottomLeftLongitude)
										
										var points = [];
										points.push(new T.LngLat(vm.bottomRightLongitude, vm.bottomRightLatitude));//右下角
								        points.push(new T.LngLat(vm.bottomLeftLongitude, vm.bottomLeftLatitude));//左下角
								        points.push(new T.LngLat(vm.topLeftLongitude, vm.topLeftLatitude));//左上角
								        points.push(new T.LngLat(vm.topRightLongitude,vm.topRightLatitude));//右上角

								        vm.LonAndLat = '('+vm.bottomRightLongitude+','+vm.bottomRightLatitude+'),('+vm.bottomLeftLongitude+','+vm.bottomLeftLatitude+'),('+vm.topLeftLongitude+','+vm.topLeftLatitude+'),('+vm.topRightLongitude+','+vm.topRightLatitude+')';
								        console.log(vm.LonAndLat)
								        vm.map.panTo(new T.LngLat(vm.bottomRightLongitude,vm.bottomRightLatitude));
								        vm.map.setZoom(6);
								        //创建面对象
								        var polygon = new T.Polygon(points,{
								            color: "red", weight: 2, opacity: 0.3, fillColor: "#FFFFFF", fillOpacity: 0.5
								        });
								        //向地图上添加面
								        vm.map.addOverLay(polygon);
									}else{
									  	g.popupMobile.msg("请求失败");
									}
							    },function(){
							    	g.popupMobile.msg("请求失败");
							    });
							    setTimeout(function(){
							    	$('.loading').hide();
							    },1000)
						    },
						    // 影像详情的介绍
						    showList:function(){
						    	// $(".detail").toggle();
						    	// $('.detail').fadeToggle();
						    	$('.detail').fadeToggle();
						    	$(".detail").css("opacity",0.7);
						    	console.log($(".detail")[0].className);
						    },
						    
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	// vm.onLoad(116.40969,39.89945);
	                    	vm.getDetail();
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




