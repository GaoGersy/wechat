require(['../../requireconfig'],function(){
 		require(['air','zepto','vue','vue-resource','layer','global','LArea','LAreaData1','LAreaData2'],function(Map,z,Vue,resource,layer,g,LArea,LAreaData1,LAreaData2){
 			
 			var index={
 				init:function(){
 					var t = this;
 					
 					Vue.use(resource);
 					// $('.marking').hide();

 					// 创建vue实例
 					t.index = new Vue({
 						// 元素
 						el: '#app',
 						// 数据
	                    data: {
	                    	localsearch:'',
	                    	map:'',//地图实例
	                    	zoom:12,//缩放级别
	                    	marker:'',//标注
	                    	infoWin:'',//信息框
	                    	bounds:'',//可视区域坐标,
	                    	config:{},//搜索结果的配置文件
	                    	areaStatus:'',//0：当前屏幕，1：选择区域
	                    	isArea:false,//false当前屏幕，true选择区域
	                    	code:'',//选中地区编号
	                    	isAllProvince:'',//是否查询全省
	                    	isAllCity:'',//是否查询全市
	                    	satellite:[],//卫星
	                    	secondGf:[],//卫星数组中的第二个卫星（高分为第一个）
	                    	satelliteIds:[],//用户选中的所有的卫星
	                    	percent:'60',//云盖量（无%）
	                    },
	                    // 方法
	                    methods: {	
						    onLoad:function(){
						    	var vm = this;
						    	//初始化地图对象
						        vm.map=new T.Map("mapDiv");
						        
						         //设置显示地图的中心点和级别
						         // vm.map.centerAndZoom(new T.LngLat(116.40969,39.89945),vm.zoom);
						         vm.map.centerAndZoom(new T.LngLat(113.25723,23.12842),vm.zoom);
						         setTimeout(function(){
						         	$('.loading').hide();
						         },500)
						         vm.config = {
						             pageCapacity:10,    //每页显示的数量
						             onSearchComplete:vm.localSearchResult  //接收数据的回调函数
						         };
						         //创建搜索对象
						         vm.localsearch = new T.LocalSearch(vm.map,vm.config); 
						    },
						    search:function(){
						    	var vm = this;
						    	console.log(document.getElementById('keyWord').value)
						    	vm.localsearch.search(document.getElementById('keyWord').value,7);
						    },
						     localSearchResult:function(result)
						     {
						     	 var vm = this;
						         //清空地图及搜索列表
						         vm.clearAll();

						         //添加提示词
						         vm.prompt(result);

						         //根据返回类型解析搜索结果
						         switch(parseInt(result.getResultType()))

						         {
						             case 1:
						                 //解析点数据结果
						                 vm.pois(result.getPois());
						                 break;
						             case 2:
						                 //解析推荐城市
						                 vm.statistics(result.getStatistics());
						                 break;
						             case 3:
						                 //解析行政区划边界
						                 vm.area(result.getArea());
						             	   break;
						         }
						     },

						     //解析提示词

						     prompt:function(obj)

						     {
						     	 var vm = this;
						         var prompts = obj.getPrompt();
						         if(prompts)
						         {
						             var promptHtml = "";
						             for(var i=0;i<prompts.length;i++)
						             {
						                 var prompt = prompts[i];
						                 var promptType = prompt.type;
						                 var promptAdmins = prompt.admins;
						                 var meanprompt = prompt.DidYouMean;
						                 if(promptType == 1)
						                 {
						                     //promptHtml += "<p>您是否要在"+promptAdmins[0].name+"</strong>搜索更多包含<strong>"+obj.getKeyword()+"</strong>的相关内容？<p>";
						                 }

						                 else if(promptType == 2)
						                 {
						                     promptHtml += "<p>在<strong>"+promptAdmins[0].name+"</strong>没有搜索到与<strong>"+obj.getKeyword()+"</strong>相关的结果。<p>";
						                     if(meanprompt)
						                     {
						                         promptHtml += "<p>您是否要找：<font weight='bold' color='#035fbe'><strong>"+meanprompt+"</strong></font><p>";
						                     }

						                 }

						                 else if(promptType == 3)

						                 {

						                     promptHtml += "<p style='margin-bottom:3px;'>有以下相关结果，您是否要找：</p>"

						                     for(i=0;i<promptAdmins.length;i++)

						                     {

						                         promptHtml += "<p>"+promptAdmins[i].name+"</p>";

						                     }

						                 }

						             }

						             if(promptHtml != "")

						             {

						                 document.getElementById("promptDiv").style.display = "block";

						                 document.getElementById("promptDiv").innerHTML = promptHtml;

						             }

						         }

						     },

						     //解析点数据结果

						     pois:function(obj)

						     {
						     	var vm = this;
						         if(obj)

						         {

						             //显示搜索列表

						             var divMarker = document.createElement("div");

						             //坐标数组，设置最佳比例尺时会用到

						             var zoomArr = [];

						             for(var i=0;i<obj.length;i++)

						             {

						                 //闭包

						                 (function(i){

						                     //名称

						                     var name = obj[i].name;

						                     //地址

						                     var address = obj[i].address;

						                     //坐标

						                     var lnglatArr = obj[i].lonlat.split(" ");

						                     var lnglat = new T.LngLat(lnglatArr[0],lnglatArr[1]);



						                     var winHtml = "地址:"+address;



						                     //创建标注对象

						                     var marker = new T.Marker(lnglat);

						                     // 获取icon对象
								            var icon = marker.getIcon();
								            // 设置icon的路径
								            // icon.setIconUrl("http://gaofen.iask.in/weixin/images/mark.png");
								            icon.setIconUrl(g.markerUrl);
								            // 创建point
								            var point = new T.Point(35,35);
								            // 设置icon的大小（point类型）
								            icon.setIconSize(point);
						                     //地图上添加标注点

						                     vm.map.addOverLay(marker);

						                     //注册标注点的点击事件

						                     marker.addEventListener("click", function () {

						                         // this.showPosition(marker, name, winHtml);
													vm.showPosition(marker, name, winHtml);


						                     }, this);

						                     zoomArr.push(lnglat);



						                     //在页面上显示搜索的列表

						                     var a = document.createElement("a");

						                     a.href = "javascript://";

						                     a.innerHTML = name;
						                     vm.map.removeOverLay(marker)//删除
						                     a.onclick = function(){
						                         document.getElementById("resultDiv").style.display = "none";
												 vm.map.addOverLay(marker)//重新添加目标mark
												 vm.showPosition(marker,name,winHtml);
												 vm.map.panTo(lnglat,12);//把目标位置移动到中心位置
						                     }
						                     
						                     
						                     // 省略序号
						                    // divMarker.appendChild(document.createTextNode((i+1)+"."));

						                     divMarker.appendChild(a);

						                    // divMarker.appendChild(document.createElement("br"));

						                 })(i);

						             }

						             //显示地图的最佳级别

						             vm.map.setViewport(zoomArr);

						             //显示搜索结果

						             //divMarker.appendChild(document.createTextNode('共'+vm.localsearch.getCountNumber()+'条记录，分'+vm.localsearch.getCountPage()+'页,当前第'+vm.localsearch.getPageIndex()+'页'));

						             document.getElementById("searchDiv").appendChild(divMarker);

						             document.getElementById("resultDiv").style.display = "block";

						         }

						     },

						     //显示信息框

						     showPosition:function (marker, name) {
						     	var vm = this;
						         if (vm.infoWin) {

						             vm.map.removeOverLay(vm.infoWin);

						             vm.infoWin = null;

						         }

						         var html = "<h5>" + name + "</h5>";

						         vm.infoWin = new T.InfoWindow(html, new T.Point([0, 0]));

						          marker.openInfoWindow(vm.infoWin);

						     },

						     //解析推荐城市

						     statistics:function (obj)

						     {
						     	var vm = this;
						         if(obj)

						         {

						             //坐标数组，设置最佳比例尺时会用到

						             var pointsArr = [];

						             var priorityCitysHtml = "";

						             var allAdminsHtml = "";

						             var priorityCitys = obj.priorityCitys;

						             if(priorityCitys)

						             {

						                 //推荐城市显示

						                 priorityCitysHtml += "在中国以下城市有结果<ul>";

						                 for(var i=0;i<priorityCitys.length;i++)

						                 {

						                     priorityCitysHtml += "<li>" + priorityCitys[i].name + "("+priorityCitys[i].count+")</li>";

						                 }

						                 priorityCitysHtml += "</ul>";

						             }



						             var allAdmins = obj.allAdmins;

						             if(allAdmins)

						             {

						                 allAdminsHtml += "更多城市<ul>";

						                 for(var i=0;i<allAdmins.length;i++)

						                 {

						                     allAdminsHtml += "<li>" + allAdmins[i].name + "("+allAdmins[i].count+")";

						                     var childAdmins = allAdmins[i].childAdmins;

						                     if(childAdmins)

						                     {

						                         for(var m=0;m<childAdmins.length;m++)

						                         {

						                             allAdminsHtml += "<blockquote>" + childAdmins[m].name + "("+childAdmins[m].count+")</blockquote>";

						                         }

						                     }

						                     allAdminsHtml += "</li>"

						                 }

						                 allAdminsHtml += "</ul>";

						             }

						             document.getElementById("statisticsDiv").style.display = "block";

						             document.getElementById("statisticsDiv").innerHTML = priorityCitysHtml + allAdminsHtml;

						         }

						     },

						     //解析行政区划边界

						     area:function (obj)

						     {
						     	var vm = this;
						         if(obj)

						         {

						             //坐标数组，设置最佳比例尺时会用到

						             var pointsArr = [];

						             var points = obj.points;

						             for(var i=0;i<points.length;i++)

						             {

						                 var regionLngLats = [];

						                 var regionArr = points[i].region.split(",");

						                 for(var m=0;m<regionArr.length;m++)

						                 {

						                     var lnglatArr = regionArr[m].split(" ");

						                     var lnglat = new T.LngLat(lnglatArr[0],lnglatArr[1]);

						                     regionLngLats.push(lnglat);

						                     pointsArr.push(lnglat);

						                 }

						                 //创建线对象

						                 var line = new T.Polyline(regionLngLats,{strokeColor:"blue", strokeWeight:3, strokeOpacity:1, strokeStyle:"dashed"});

						                 //向地图上添加线

						                 vm.map.addOverLay(line);

						             }

						             //显示最佳比例尺

						             vm.map.setViewport(pointsArr);

						         }

						     },

						     //清空地图及搜索列表

						    clearAll:function (){
						    	var vm = this;
						         vm.map.clearOverLays();
						         document.getElementById("searchDiv").innerHTML = "";
						         document.getElementById("resultDiv").style.display = "none";
						         document.getElementById("statisticsDiv").innerHTML = "";
						         document.getElementById("statisticsDiv").style.display = "none";
						         document.getElementById("promptDiv").innerHTML = "";
						         document.getElementById("promptDiv").style.display = "none";
						         document.getElementById("suggestsDiv").innerHTML = "";
						         document.getElementById("suggestsDiv").style.display = "none";
						         document.getElementById("lineDataDiv").innerHTML = "";
						         document.getElementById("lineDataDiv").style.display = "none";
						    },

						    // 获取可视区域的坐标
						    getMapBounds:function ()
							 {	
							 	var vm = this;
							    var bs = vm.map.getBounds();       //获取可视区域
							    var bssw = bs.getSouthWest();   //可视区域左下角
							    var bsne = bs.getNorthEast();   //可视区域右上角
							    console.log(bssw)
							    // Longitude经度，Latitude纬度，getLng()获取经度，getLat()获取纬度

							    // 左上角
							    var topLeftLatitude=bsne.getLat();
							    var topLeftLongitude=bssw.getLng();
							    // 右下角
							    var bottomRightLatitude=bssw.getLat();
							    var bottomRightLongitude=bsne.getLng();
							    
							    // 右上角
							    var topRightLatitude=bsne.getLat();
							    var topRightLongitude=bsne.getLng();
							    // 左下角
							    var bottomLeftLatitude=bssw.getLat();
							    var bottomLeftLongitude=bssw.getLng();
							    
							    vm.bounds={
							    	'topLeftLatitude':topLeftLatitude,
							    	'topLeftLongitude':topLeftLongitude,
									'topRightLatitude':topRightLatitude,
									'topRightLongitude':topRightLongitude,
									'bottomRightLatitude':bottomRightLatitude,
									'bottomRightLongitude':bottomRightLongitude,
									'bottomLeftLatitude':bottomLeftLatitude,
									'bottomLeftLongitude':bottomLeftLongitude,
						   		};
						   		console.log("screen")
						   		console.log(vm.bounds)
							  },

							
						    // 选择地区
						    getArea1:function(event){
						    	var vm = this;
						    	event.stopPropagation();
						    	// 获取当前点击对象的jq对象
						    	console.log(event.currentTarget.className);
            					console.log(event.target);
            					// 给当前对象添加on类名并删除其他对象的on类名
            					$(event.currentTarget).addClass("on").siblings().removeClass('on');
            					console.log($(event.currentTarget)[0].className)//获取当前对象的所有类名
            					console.log(($(event.currentTarget).is(".current-area")))//获取当前对象是否有某类名
            					var isCurrent = $(event.currentTarget).is(".current-area");//判断是否是当前屏幕还是选择区域
            					if(isCurrent){
									vm.areaStatus=0;
									vm.isArea = false;
									vm.getMapBounds();
									$("#areaName").hide();
            					}else{
            						vm.areaStatus=1;
            						vm.isArea = true;
            						$("#areaName").show();
									$("#demo2").click();
									// 三级改二级联动
									$('.area_roll').children("div:first-child").css('display','none');
	            				}
						    },
						    // 获取用户输入的信息
						    getData:function(){
						    	var vm = this;
						    	var binginTime = $('.begin').val();
						    	var endTime = $('.end').val();
						    	if(vm.areaStatus==0){//当前屏幕
									var bounds = vm.bounds;
						    	}else{
						    		var code = $("#value2").val();//城市编号
						    	}
						    	// var cloud = $("#cloud").val();
						    	if(binginTime==null || binginTime.length==0){
						    		g.popupMobile.msg("请选择开始日期");
						    		return false;
						    	}
						    	if(endTime==null || endTime.length==0){
						    		g.popupMobile.msg("请选择截止日期");
						    		return false;
						    	}
						    },
						    // 查询数据
						    submit:function(event){
						    	var vm = this;
						    	event.stopPropagation();
						    	var province,city,area;
						    	// vm.getData();
						    	var beginTime = $('.begin').val();//开始的时间
						    	var endTime = $('.end').val();//结束的时间
						    	if(vm.areaStatus==0){//当前屏幕
						    		vm.isArea = false;
									var bounds = vm.bounds;

						    	}else{
						    		// var bounds = $("#areaName").html();
						    		vm.isArea = true;
						    		var code = $("#value2").val();//城市编号
						    		console.log(code)
						    		// vm.code = code.substring(14,23);
						    		console.log(vm.code)
						    		
						    		province  = code.substring(0,6);
						    		city = code.substring(7,13);
						    		area = code.substring(14,23);
						    		// "" 或者000000，代表全部
						    		if(area!='' && area!='000000'){
						    			vm.code=area;
						    		}else if(city!=''&& city !='000000'){
						    			vm.code = city;
						    		}else{
						    			vm.code = province;
						    		}
						    	}
						    	// var cloud = $("#cloud").val();//云量
						    	var cloud = $('#sliderValue').text();
						    	console.log(cloud.length)
						    	if(cloud.length==2){
						    		cloud = cloud.substring(-1,1)
						    	}else{
						    		cloud = cloud.substring(-1,2)
						    	}
						    	
						    	// 选着卫星
						    	var fatherVal='';//父级的值(卫星)
						    	// 高分卫星是否被选中
						    	var gf1_enable = false;
							    var gf2_enable = false;
							    var gf3_enable = false;
							    var gf4_enable = false;
						    	var gf1childrenVal=[], gf2childrenVal=[], gf3childrenVal=[], gf4childrenVal=[];
						    	var father = $('.tree_node_parent_checkbox:checked');
						    	
						    	for(var i = 0;i<father.length;i++){
					    			fatherVal = $(father[i]).attr("value");
					    			vm.satelliteIds.push(fatherVal);
					    			if(fatherVal=="GF1"){
					    				gf1_enable = true;
					    				var children = $(father[i]).parents('.tree_node_parent1').siblings('.tree_node_child1').find(".tree_node_child_checkbox:checked");
					    				var resultString='';
					    				for(var j = 0;j<children.length;j++){
					    					resultString += $(children[j]).attr("value")+",";
					    					// gf1childrenVal.push($(children[j]).attr("value"));
					    				}
					    				gf1childrenVal = resultString.split(",");
					    				gf1childrenVal.pop();
					    				console.log(gf1childrenVal)
					    			}else if(fatherVal=="GF2"){
					    				gf2_enable = true;
					    			}else if(fatherVal=="GF3"){
					    				gf3_enable = true;
					    			}else if(fatherVal=="GF4"){
					    				gf4_enable = true;
					    			}
						    	}  
						    	console.log("所有的卫星");
						    	console.log(vm.satelliteIds)

						    	if(beginTime==null || beginTime.length==0){
						    		g.popupMobile.msg("请选择开始日期");
						    		return false;
						    	}
						    	if(endTime==null || endTime.length==0){
						    		g.popupMobile.msg("请选择截止日期");
						    		return false;
						    	}
						    	if(fatherVal==null || fatherVal.length==0){
						    		g.popupMobile.msg("请选择卫星及传感器");
						    		return false;
						    	}
						    	
						    	
						    	var data = vm.bounds;
						    	// data.cloudPercent = cloud;
						    	data.cloudPercent = vm.percent;
						    	data.startTime = beginTime+" 00:00:00";
						    	data.endTime = endTime+" 24:00:00";
						    	data.gf1_enable = gf1_enable;
						    	data.gf2_enable = gf2_enable;
						    	data.gf3_enable = gf3_enable;
						    	data.gf4_enable = gf4_enable;
						    	data.sensorIds_1 = gf1childrenVal;//gf的传感器
						    	data.satelliteIds = vm.satelliteIds;//所有的卫星
						    	data.currentPage = 1;
						    	data.pageSize = 10;
						    	data.isArea = vm.isArea;
						    	data.code = vm.code;
						    	if(vm.code == city){
						    		data.isAllCity= true;
						    	}else if(vm.code == province){
						    		data.isAllProvince=true;
						    	}
						    	console.log("code:"+vm.code)
						    	console.log(encodeURI(JSON.stringify(data)))
						    	window.location.href="./resultList.html?data="+encodeURI(JSON.stringify(data));
						    	
						    	// window.location.href="./resultList.html?data="+encodeURI(JSON.stringify(data));
						   
						    },
						    // 判断用户是否已经登陆
						    isLogin:function(){
						    	var vm = this;
	                    		//发送post请求
	                    		var param = {
									"OPENID":"asasa11a1ss223412"
		                    	};
							    var url=g.baseUrlfjh+"user/getUserInfo";
							    vm.$http.post(url,param).then(function(res){
									if(res.data.code==200){
									 	// g.popupMobile.msg("登陆成功");
									 	vm.userInfo = res.data;
									 	if (vm.userInfo.statu==0) {
									 		g.popupMobile.msg("您还未登陆");
										 	setTimeout(function(){
										 		window.location.href="../login.html"
										 		// window.location.href="gaofen.iask.in/gaofen/user/getUser?url=http://gaofen.iask.in/gaofen/html/order/orderList.html"
					
										 	},1000)
									 	}else{
									 		g.popupMobile.msg("登陆成功");
									 	}
									}else{
									  	g.popupMobile.msg("登陆失败");
									}
							    },function(){
							      g.popupMobile.msg("登陆失败"); //失败处理
							    });
						    },
						    // 是否隐藏搜索结果列表
						    isShowResult:function(){
						    	 document.getElementById("resultDiv").style.display = "none";
						    },
						    // 获取卫星
						    getSatellite:function(){
						    	var vm = this;
							    var url=g.baseUrlgch+"getAllSatelliteType";
							    vm.$http.get(url).then(function(res){
									if(res.data.code==200){
										var dataList = res.data.data;
										var isHasGf1 = $.inArray("GF1", dataList);
										if(isHasGf1!=-1){
											// GF1固定写死，删除GF1
											dataList.splice(isHasGf1,1)
										}
										// 获取到高分卫星的第一卫星
										vm.secondGf = dataList.splice(0,1);
										vm.satellite = dataList;
								 	}
							    },function(){
							      g.popupMobile.msg("获取失败"); //失败处理
							    });
						    }
	                    },
	                    filters:{
	                    	formateMoney:function(value){
	                    		return "￥ "+value.toFiexed(2);
	                    	}
	                    },
	                    // 钩子函数
	                    mounted:function(){
	                    	var vm=this;
	                    	vm.onLoad();
						    vm.getMapBounds();
						    vm.getSatellite();
						    $(".tree_node_child1").hide();
						    
						    // 重新加载天地图js文件
						    // var url = "http://api.tianditu.com/api?v=4.0";
						    // var head = document.getElementsByTagName('head')[0],
					     //    js = document.createElement('script');
					     //    js.setAttribute('type', 'text/javascript'); 
					     //    js.setAttribute('src', url); 
					     //    head.appendChild(js);

							var elButtonWith = $('.push');
							var elAside = $('#aside');

							// 显示侧边栏
							elButtonWith.on('click', function() {
								elAside.addClass('active');
								$('html').addClass('noscroll');
							});
							// 隐藏侧边栏
							$('.hideAside').on('click', function () {
								$('html').removeClass('noscroll');
								elAside.removeClass('active');
							});

							

						    // 设置默认的时间范围
						    var date = new Date();
						    var newDate = g.dateFormat(date.getTime(),"yyyy-MM-dd");
						    var threeMonth = 3*30*24*60*60*1000;
						    var threeMonthDate = g.dateFormat(date.getTime()-threeMonth,"yyyy-MM-dd");
						    $("#begin").attr("value",threeMonthDate);
						    $("#end").attr("value",newDate);
						    


						    //缩放地图时，修改可视范围的经纬度
							vm.map.addEventListener("zoomend",vm.getMapBounds);

							//拖拽地图时，修改可视范围的经纬度
							vm.map.addEventListener("moveend",vm.getMapBounds);

							
							// 获取云盖量
							$(function(){
						        var $sliderTrack = $('#sliderTrack'),
						            $sliderHandler = $('#sliderHandler'),
						            $sliderValue = $('#sliderValue');
						            console.log($sliderTrack)
						        var totalLen = $('#sliderInner').width(),
						            startLeft = 0,
						            startX = 0;

						        $sliderHandler
						            .on('touchstart', function (e) {
						                startLeft = parseInt($sliderHandler.css('left')) * totalLen / 100;
						                startX = e.changedTouches[0].clientX;
						            })
						            .on('touchmove', function(e){
						                var dist = startLeft + e.changedTouches[0].clientX - startX,
						                    percent;
						                dist = dist < 0 ? 0 : dist > totalLen ? totalLen : dist;
						                percent =  parseInt(dist / totalLen * 100);
						                $sliderTrack.css('width', percent + '%');
						                $sliderHandler.css('left', percent + '%');
						                $sliderValue.text(percent+'%');
						                console.log("percent:"+percent)
						                var cloud = $sliderValue.text();
						                vm.percent=percent;
						                e.preventDefault();
						            })
						        ;
						    });
							// 省市区
							var area1 = new LArea();
						      area1.init({
						          'trigger': '#demo1', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
						          'valueTo': '#value1', //选择完毕后id属性输出到该位置
						          'keys': {
						              id: 'id',
						              name: 'name'
						          }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
						          'type': 1, //数据源类型
						          'data': LAreaData //数据源
						      });
						      // 获取到数据类型2的城市编码
						      area1.value=[1,13,3];//控制初始位置，注意：该方法并不会影响到input的value
						     
						      var area2 = new LArea();
						      area2.init({
						          'trigger': '#demo2',
						          'valueTo': '#value2',
						          'keys': {
						              id: 'value',
						              name: 'text'
						          },
						          'type': 2,
						          // 'data': [provs_data, citys_data, dists_data]
						          'data': [provs_data, citys_data, dists_data]
						      });
						       area2.value=[18,0,4];//控制初始位置，注意：该方法并不会影响到input的value(广东广州天河)
						 	// 初始化日历插件
						 	!function(){
							  laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
							  laydate({elem: '#demo'});//绑定元素
							}();


							//日期范围限制
							// var start = {
							//     elem: '#start',
							//     format: 'YYYY-MM-DD',
							//     min: laydate.now(), //设定最小日期为当前日期
							//     max: '2099-06-16', //最大日期
							//     istime: true,
							//     istoday: false,
							//     choose: function(datas){
							//          end.min = datas; //开始日选好后，重置结束日的最小日期
							//          end.start = datas //将结束日的初始值设定为开始日
							//     }
							// };

							// var end = {
							//     elem: '#end',
							//     format: 'YYYY-MM-DD',
							//     min: laydate.now(),
							//     max: '2099-06-16',
							//     istime: true,
							//     istoday: false,
							//     choose: function(datas){
							//         start.max = datas; //结束日选好后，充值开始日的最大日期
							//     }
							// };
							// 默认启动日历
							// laydate(start);
							// laydate(end);


							//自定义日期格式
							laydate({
							    elem: '#test1',
							    format: 'YYYY年MM月DD日',
							    festival: true, //显示节日
							    choose: function(datas){ //选择日期完毕的回调
							        alert('得到：'+datas);
							    }
							});

							//日期范围限定在昨天到明天
							laydate({
							    elem: '#hello3',
							    min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
							    max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
							});
							
							
							// 页面加载完成后调用
					       	$(function(){
					            // 为所有的父节点添加点击事件
					            $(".tree_node_parent_checkbox").click(function(){
					                // 获取父节点是否选中
					                var isChange = $(this).prop("checked");
					                var _this = $(this);
					                if(isChange){ // 如果选中,则父节点下的所有的子节点都选中
					                   // 获取当前checkbox节点的兄弟节点下的所有的checkbox子节点选中
					                    var children = $(this).parents('.tree_node_parent1').siblings(".tree_node_child1");
					                   	children.show();
					                    children.find(".tree_node_child_checkbox").prop("checked",true);
					                }else{ // 未选中，取消全选
					                    $(this).parents('.tree_node_parent1').siblings(".tree_node_child1").hide();
					                    _this.parents('.tree_node_parent1').siblings(".tree_node_child1").find(".tree_node_child_checkbox").prop("checked",false);
					                }
					            });
					            // 为所有的子节点添加点击事件
					            $(".tree_node_child_checkbox").click(function () {
					                // 获取选中的节点的父节点下的所有子节点选中的数量
					                var _this = $(this);
					                var length = _this.parents('.tree_node_child1').find(".tree_node_child_checkbox:checked").length;
					                // 判断当前结点是否选中
					                if(_this.prop("checked")){ // 选中
					                    // 如果当前节点选中后,所有的选中节点数量1，选中父节点
					                    if(length == 1){
					                        // 选中父节点
					                        _this.parents(".tree_node_child1").siblings(".tree_node_parent1").find(".tree_node_parent_checkbox").prop("checked", true);
					                    }
					                }else{ // 节点未选中
					                    if(length == 0){
					                        // 取消父节点的选中状态
					                        _this.parents(".tree_node_child1").siblings(".tree_node_parent1").find(".tree_node_parent_checkbox").prop("checked", false);
					                    }
					                }
					            });
		        			});


							// 卫星复选框
							// 页面加载完成后调用
					        $(function(){
					            // 为所有的父节点添加点击事件
					            $(".tree_node_parent_checkbox").click(function(){
					                // 获取父节点是否选中
					                var _this= $(this);
					                var isChange = $(this).prop("checked");
					                var ischeck = $(this).hasClass("check");
					                var ischeck1 = $(this).hasClass("weui-icon-success")
					                $(this).removeClass("weui-icon-circle");
					                $(this).addClass("weui-icon-success");
					                console.log("ischeck1:"+ischeck1)
					                console.log("ischeck:"+ischeck)
					                var $toggle_node = $(this).siblings(".tree_node_child");
					                if(ischeck){ // 如果选中,则父节点下的所有的子节点都选中
					                    // 获取当前checkbox节点的兄弟节点下的所有的checkbox子节点选中
					                    _this.removeClass("check");
					                    // $(this).next().find(".tree_node_child_checkbox").prop("checked", true);
					                     $(this).next().find(".tree_node_child_checkbox").removeClass("check");
					                    // $toggle_node.hide();
					                }else{ // 未选中，取消全选
					                    // 获取当前checkbox节点的兄弟节点下的所有的checkbox子节点选中
					                     // $(this).next().find(".tree_node_child_checkbox").prop("checked",false);
					                     _this.addClass("check");
					                     $(this).next().find(".tree_node_child_checkbox").addClass("check");
					                  	 $toggle_node.show();
					                }
					            });
					            // 为所有的子节点添加点击事件
					            $(".tree_node_child_checkbox").click(function () {
					                // 获取选中的节点的父节点下的所有子节点选中的数量
					                // var length = $(this).parent().find(".tree_node_child_checkbox:checked").length;
					                var length = $(this).parent().find(".check").length;
					                // 判断当前结点是否选中
					                var _this = $(this);
					                var ischeck = $(this).hasClass("check");
					                if(ischeck){
					                	_this.removeClass("check");
					                }else{
					                	_this.addClass("check");
					                }
					                var length = _this.parent().find(".check").length;
					                if(length>=1){
					                	_this.parent().prev().addClass("check");
					                }else{
					                	_this.parent().prev().removeClass("check");
					                }
					            });
					        });
      
	                    }
 					})
 				}
 			}
 			index.init();
 		})
		
 })




