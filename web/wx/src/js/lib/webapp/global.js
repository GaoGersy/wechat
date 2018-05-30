define(['layer','zepto'],function(layer,zepto){
	var global = {
		// 基本路径
        // 调试
        baseUrl:"http://localhost:8001/",//
        baseUrlfjh:"http://localhost:8001/"+"api/",//冯建煌ip（192.168.242）
		baseUrlgch:"http://localhost:8001/"+"gf/",//高春虎ip（192.168.1.166）
        imgUrl:"http://192.168.1.166/api/image/",//影像和订单图片路径
        markerUrl:"../../../images/mark.png",//搜索定位大头针路径

        // 服务器（打包）
        // baseUrl:"http://gaofen.iask.in/api/",//
        // baseUrlfjh:"http://gaofen.iask.in/api/",//
        // baseUrlgch:"http://gaofen.iask.in/api/",
        // imgUrl:"http://gaofen.iask.in/api/image/",//影像和订单图片路径
        // markerUrl:"http://gaofen.iask.in/weixin/images/mark.png",//搜索定位大头针路径 
      
		/**
         * 移动端弹窗，样式文件已写在配置依赖里
         * 使用方法 请参照 http://layer.layui.com/mobile/
         */
		popupMobile:{
			// 关闭所有气泡
			close: function () {
                layer.closeAll();
            },
            //提示气泡
			msg: function (content,time) {
                var t = this;
                t.close();
                layer.open({
                    content: content
                    , skin: 'msg' // footer（即底部对话框风格）、msg（普通提示）
                    , time: 2 || time//2秒后自动关闭
                });
            },
            // 加载中
            loading:function(){
                var t = this;
                t.close();
                layer.open({
                    type: 2
                    ,content: '加载中'
                });
            },
            // 询问框
            dialog:function(opt){
            	var t = this;
                t.close();
                // layer.open(opt);
                //opt调用时输入以下内容
      			   layer.open({
				    content: '是否确定取消订单'
				    ,btn: ['是', '否']
				    ,yes: function(index){
				      location.reload();
				      layer.close(index);
				    }
			  	});
            },
            dialog2:function(opt){
            	var t = this;
                t.close();
                layer.open(opt);
            	//自定义标题风格
				// layer.open({
				//     title: [
				//       '我是标题',
				//       'background-color: #FF4351; color:#fff;'
				//     ]
				//     ,content: '标题风格任你定义。'
				// });
            },
            // 加载中
            loading:function(){
            	//loading带文字
				layer.open({
				  type: 2
				   ,content: '加载中'
				});
            }

		},
        /**
         * 获取url data参数。aa.html?data={}
         */
        getUrlData: function () {
            var purl = window.location.href;
            purl = purl.substr(purl.indexOf("?") + 1);
            var urlData = JSON.parse(decodeURI(purl.substr(purl.indexOf("=") + 1)));
            return urlData;
        },
        /**
         * http://localhost:8999/html/user/find-result.html?department=2&technical=1&time=d&area=dd%E5%8D%95%E7%8B%AC
         * 获取url参数
         *  g.getUrl("beginTime")
         */
        getUrl:function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return decodeURI(r[2]);
            }
            return null;
        },
         /**
         * 返回格式化后的时间格式
         * @param timestamp { Number } -可选 时间戳 该值如果不传则返回的是当前时间
         * @param format { Number } - 可选 返回的格式字符串，默认 ： yyyy-MM-dd hh:mm:ss
         */
        dateFormat:function(timestamp,format){
            var date = timestamp ? new Date( Number(timestamp) ) : new Date();
            // var timeFormat = format || "yyyy-MM-dd hh:mm:ss" ;
            var timeFormat = format || "yyyy-MM-dd hh:mm" ;
            return date.format(timeFormat);
        },
         // 计算分辨率和传感器
        getPx:function(satelliteid,sensorid){
            var vm =this;
            var resolving = "";
            var resolvingNum = "";
            switch(satelliteid){
                case "GF1":
                    if(sensorid.indexOf("WFV")!=-1){
                        resolving = "16米宽视场";
                        resolvingNum = "16米";
                    }else if(sensorid.indexOf("PMS")!=-1){
                        resolving = "2米全色/8米多光谱";
                        resolvingNum = "2米/8米";
                    }break;
                case "GF2":
                    if(sensorid.indexOf("PMS")!=-1){
                        resolving = "1米全色/4米多光谱";
                        resolvingNum = "1米/4米";
                    }else if(sensorid.indexOf("MSS")!=-1){
                        resolving = "4米多光谱";
                        resolvingNum = "4米";
                    }break;
                // case "GF3":"1米";break;
                case "GF3":
                        resolving = "SAR";
                        resolvingNum = "1米";
                    break;
                case "GF4":
                    switch(sensorid){
                        case "PMI":resolving ="50米全色/50米多光谱/400米中红外波";resolvingNum ="50米/400米";break;
                        case "PMS":resolving ="50米全色/50米多光谱/400米中红外波";resolvingNum ="50米/400米";break;
                        case "IRS":resolving ="50米全色/50米多光谱/400米中红外波";resolvingNum ="50米/400米";break;
                        case "B1":resolving ="50米全色";resolvingNum ="50米";break;
                        case "B2":resolving ="50米蓝色谱段";resolvingNum ="50米";break;
                        case "B3":resolving ="50米绿色谱段";resolvingNum ="50米";break;
                        case "B4":resolving ="50米红色谱段";resolvingNum ="50米";break;
                        case "B5":resolving ="50米近红外谱段";resolvingNum ="50米";break;
                        case "PI":resolving ="50米全色/400米中红外波";resolvingNum ="50米/400米";break;
                    }
                    break;
            }
            return {
                resolving:resolving,//传感器
                resolvingNum:resolvingNum//分辨率
            }
        },
        init:function(){

            var t = this ;
            if(!Date.prototype.format){
                //增加时间格式化方法
                Date.prototype.format = function(format){
                    var o = { "M+" : this.getMonth()+1, //month
                        "d+" : this.getDate(), //day
                        "h+" : this.getHours(), //hour
                        "m+" : this.getMinutes(), //minute
                        "s+" : this.getSeconds(), //second
                        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
                        "S" : this.getMilliseconds() //millisecond
                    };
                    if(/(y+)/.test(format)) format=format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); for(var k in o){ if(new RegExp("("+ k +")").test(format)) format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length)); } return format;
                }
            }
        }
	};
	global.init();
    window._g_ = global;
    return global;
})
