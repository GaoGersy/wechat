require.config({map:{"*":{css:"lib/requirejs/css.min"}},baseUrl:"/weixin/js/",paths:{vue:"lib/vue/vue",zepto:"lib/zepto/zepto.min","vue-resource":"lib/vue/vue-resource.min",layer:"lib/layer_mobile/layer",YMDClass:"lib/plugin/YMDClass",global:"lib/webapp/global",air:"lib/map/getscript",LArea:"lib/plugin/area/js/LArea",LAreaData1:"lib/plugin/area/js/LAreaData1",LAreaData2:"lib/plugin/area/js/LAreaData2",dropload:"lib/plugin/listLoading/dropload.min","vue-scroller":"lib/vue/vue-scroller.min"},shim:{LArea:{deps:["css!lib/plugin/area/css/LArea.css"]},layer:{deps:["css!lib/layer_mobile/need/layer.css"]},dropload:{deps:["css!lib/plugin/listLoading/dropload.css"]}}});