/**
 * 公用js方法
 * @create by xuehui
 * @date 2017-01-14
 */
Date.prototype.format=function(fmt){
	 var o = {         
			    "M+" : this.getMonth()+1, //月份         
			    "d+" : this.getDate(), //日         
			    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
			    "H+" : this.getHours(), //小时         
			    "m+" : this.getMinutes(), //分         
			    "s+" : this.getSeconds(), //秒         
			    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
			    "S" : this.getMilliseconds() //毫秒         
			    };         
			    var week = {         
			    "0" : "/u65e5",         
			    "1" : "/u4e00",         
			    "2" : "/u4e8c",         
			    "3" : "/u4e09",         
			    "4" : "/u56db",         
			    "5" : "/u4e94",         
			    "6" : "/u516d"        
			    };         
			    if(/(y+)/.test(fmt)){         
			        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
			    }         
			    if(/(E+)/.test(fmt)){         
			        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
			    }         
			    for(var k in o){         
			        if(new RegExp("("+ k +")").test(fmt)){         
			            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
			        }         
			    }         
			    return fmt;        
};

/**
 * 获取url参数
 * @author xuehui
 * @dateb 2017-01-14
 */
function getUrlFn(){
  var querystr = window.location.href.split("?"),
      GETS = "",
      GET = "";
  if (querystr[1]) {
    GETs = querystr[1].split("&");
    GET = [];
    for (i = 0; i < GETs.length; i++) {
      tmp_arr = GETs[i].split("=");
      var key = tmp_arr[0];
      GET[key] = tmp_arr[1];
    }
  }
  return GET;
}


Array.prototype.contains = function ( needle ) {
  for (i in this) {
    if (this[i] == needle) return true;
  }
  return false;
}
/**
 * 日期格式转换
 * @param timestamp 时间戳
 * @param format 数据格式
 */
function dateFormat(timestamp,format){
	var date = timestamp ? new Date( Number(timestamp) ) : new Date();
	var timeFormat = format || "yyyy-MM-dd HH:mm:ss" ;
	return date.format(timeFormat);
}

/**
 * 将时间错转换为日期格式 yyyy-MM-dd
 * @param timestamp 时间戳
 */
function dateStr(timestamp){
	return dateFormat(timestamp,"yyyy-MM-dd");
}

/**
 * 将时间错转换为时间格式 yyyy-MM-dd HH:mm:ss
 * @param timestamp 时间戳
 */
function dateTimeStr(timestamp){
	return dateFormat(timestamp,"yyyy-MM-dd HH:mm:ss");
}

