require.config({
                   map: {
                       '*': {
                           'css': 'lib/requirejs/css.min'
                       }
                   },
                   // 调试
                   baseUrl: '/js/',
                   // 服务器
                    // baseUrl:'/weixin/js/',
                   paths: {
                       'vue': 'lib/vue/vue',
                       'zepto': 'lib/zepto/zepto.min',
                       'vue-resource': 'lib/vue/vue-resource.min',
                       'layer': 'lib/layer_mobile/layer',//弹框
                       'YMDClass': 'lib/plugin/YMDClass',//日历
                       'global': 'lib/webapp/global',
                       'air':'lib/map/getscript',//天地图
                       'LArea': 'lib/plugin/area/js/LArea',//省市区三级联动
                       'LAreaData1': 'lib/plugin/area/js/LAreaData1',
                       'LAreaData2': 'lib/plugin/area/js/LAreaData2',
                       'dropload': 'lib/plugin/listLoading/dropload.min',//上拉加载
                       'vue-scroller': 'lib/vue/vue-scroller.min',//vue-scroller
                   },
                   // 依赖
                   shim: {
                       'LArea': {
                           deps: ['css!lib/plugin/area/css/LArea.css']
                       },
                       'layer': {
                           deps: ['css!lib/layer_mobile/need/layer.css']
                       },
                       'dropload': {
                           deps: ['css!lib/plugin/listLoading/dropload.css']
                       }
                       
                   }
               })