require(["../../requireconfig"],function(){require(["zepto","vue","vue-resource","layer","global","dropload"],function(e,o,t,a,n,r){({init:function(){var e=this;o.use(t),o.filter("isOk",function(e){return console.log(e),e=0==e?"未审核":1==e?"审核通过":"未通过"}),o.filter("isShow",function(e){var o=this;return o.input=0==e?1:0,o.input}),e.index=new o({el:"#orderList",data:{orderList:[],orderdate1:"",isCancel:!1,token:"",loginState:0,dropload:"",currentPage:1,me:"",iswork:!1,isnull:!1,isloading:!1,openID:n.getUrl("openId"),userName:"",applyState:""},methods:{getUserInfo:function(e,o){var t=this;if(""==t.userName){console.log("空");var a={openId:n.getUrl("openId")},r=n.baseUrlfjh+"user/getUserInfo";t.$http.post(r,a).then(function(a){200==a.data.code?(t.userName=a.data.data.userName,t.getList(e,o)):n.popupMobile.msg(a.data.error)},function(){n.popupMobile.msg("获取用户信息失败")})}else t.getList(e,o)},getOrder:function(e){window.location.href="./order.html?orderId="+e.id+"&dataapplication="+e.dataapplication+"&disstate="+e.disstate},cancelOrder:function(e,o){var t=this,r=$(o.currentTarget);n.popupMobile.dialog2({className:"order-dialog",content:"是否确定取消订单",btn:["是","否"],yes:function(o){var d=n.baseUrlgch+"order/cancelOrderById?id="+e.id;t.$http.get(d).then(function(e){n.popupMobile.msg(e.data.data),r.parent().parent().hide()},function(){n.popupMobile.msg("取消失败")}),a.close(o)}})},getList:function(e,o){var t=this;t.isloading=!0,t.orderdate1=[];var a=(new Date).getTime(),r=a-2592e6,d=a-7776e6;if("0"==e)i=n.dateFormat(r,"yyyy-MM-dd hh:mm:ss");else if("1"==e)i=n.dateFormat(d,"yyyy-MM-dd hh:mm:ss");else var i;var s={userId:t.userName,ORDERDATE:i,currentPage:t.currentPage,pageSize:5},l=n.baseUrlfjh+"order/getOrderByUserName";t.$http.post(l,s).then(function(a){t.currentPage++;var r=a.data.data.list;if(200!=a.data.code)return t.isloading=!1,t.iswork=!0,!1;0!=r.length?($(".content").show(),t.isnull=!1,$(".dropload-down").show(),$.each(r,function(e,o){o.orderdate=n.dateFormat(o.orderdate,"yyyy-MM-dd"),console.log(t.orderdate1),0==o.disstate?o.a=1:o.a=0,console.log(t.isCancel),t.orderList.push(o)}),console.log("数据"),console.log(t.orderList),a.data.data.pageCount>a.data.data.currentPage?(o.unlock(),o.noData(!1)):(o.lock(),o.noData()),setTimeout(function(){o.resetload()},1e3)):setTimeout(function(){t.isnull=!0},600),"0"==e?tab1LoadEnd=!0:"2"==e?tab2LoadEnd=!0:"3"==e&&(tab3LoadEnd=!0)},function(){n.popupMobile.msg("请求失败"),o.resetload()}),setTimeout(function(){t.isloading=!1},500)}},filters:{},mounted:function(){var e=this;$(function(){var o=0;$(".tab .item").on("click",function(){var a=$(this);o=a.index(),a.addClass("on").siblings(".item").removeClass("on"),console.log("itemIndex:"+o),$(".dropload-down").hide(),"0"==o?(e.currentPage=1,e.orderList=[],t.unlock(),t.noData(!1),e.getList(o,e.me)):"1"==o?(e.currentPage=1,e.orderList=[],t.unlock(),t.noData(!1),e.getList(o,e.me)):"2"==o&&(e.currentPage=1,e.orderList=[],t.unlock(),t.noData(!1),e.getList(o,e.me))});var t=$(".content").dropload({scrollArea:window,domDown:{domClass:"dropload-down",domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',domNoData:'<div class="dropload-noData">已经到底了！</div>'},loadDownFn:function(t){e.me=t,"0"==o?e.getUserInfo(o,t):"1"==o?e.getUserInfo(o,t):"2"==o&&e.getUserInfo(o,t)}})})}})}}).init()})});