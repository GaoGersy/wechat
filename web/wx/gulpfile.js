/*引入gulp及相关插件 require('node_modules里对应模块')*/
var gulp = require('gulp');
var minifyCss = require("gulp-minify-css");
var uglify = require('gulp-uglify');
var browserSync = require('browser-sync').create();
var minifyHtml= require("gulp-minify-html");
var runSequence = require('run-sequence');
var imagemin = require('gulp-imagemin');
var concat = require('gulp-concat');
var sass= require("gulp-sass");
var connect = require('gulp-connect');
var proxy = require('http-proxy-middleware');

//css压缩
gulp.task('minify-css', function () {
    gulp.src('src/**/*.css')
        .pipe(minifyCss())
        .pipe(gulp.dest('dist'));
});
//压缩js(开发的代码，不包含框架，库，插件)
gulp.task('javascript', function () {
    gulp.src('src/js/dev/**/*.js')
        .pipe(uglify())//压缩
        .pipe(gulp.dest('./dist/js/dev/'));
});
//压缩requireconfig配置文件
gulp.task('config', function () {
    gulp.src('src/js/requireconfig.js')
        .pipe(uglify())//压缩
        .pipe(gulp.dest('./dist/js/'));
});
//压缩global和fix.js
gulp.task('global', function () {
    gulp.src('src/js/lib/webapp/*.js')
        .pipe(uglify())//压缩
        .pipe(gulp.dest('./dist/js/lib/webapp/'));
});
//html压缩
gulp.task('minify-html',function() {

    return  gulp.src('src/**/*.html')//要压缩的html文件

      .pipe(minifyHtml())//压缩

     .pipe(gulp.dest('./dist'));

});
//images压缩
gulp.task('uglify-imagemin',function() {
    return gulp.src('src/images/*') 
      .pipe(imagemin())

      .pipe(gulp.dest('./dist/images'));

});

// sass编译 scss---css 编译到dist
gulp.task('compile-sass',function() {
       gulp.src('src/sass/*.scss')
       // .pipe(sass())
       .pipe(sass({outputStyle: 'expanded'}).on('error', sass.logError))
       .pipe(gulp.dest('dist/css'));
       

});
// sass编译 scss---css 编译到src
gulp.task('compile-css',function() {
       gulp.src('src/sass/*.scss')
       // .pipe(sass())
       .pipe(sass({outputStyle: 'expanded'}).on('error', sass.logError))
      
       .pipe(gulp.dest('src/css'));

});

// css，js，html，images压缩都集合到build中
gulp.task('build', function(callback){
    //return runSequence(['minify-css','minify-html','uglify-imagemin','compile-sass','compile-css','javascript','config','global'],callback);
	return runSequence(['minify-html','uglify-imagemin','javascript','minify-css','config','global'],callback);
})

//server(开启服务)(打包模式)
gulp.task('serve', function () {
    browserSync.init({
        server: './dist',
    	port: 8888,
        browser: "chrome.exe" 
    });
});
//开发模式
gulp.task('develop', function () {
    browserSync.init({
        server: './src',
        port: 8999,
        browser: "chrome.exe"
    });
});

//同步刷新页面
gulp.task('reload',function() {
	return browserSync.reload();
});


// 监听页面是否修改，如果修改浏览器自动同步（刷新），用于开发模式
gulp.task('watch',function() {
    return gulp.watch([
        './src/**/*'
        ], function(){
            return runSequence(['compile-css','reload']);
        })
});

// 打包模式(执行命令 gulp package)
gulp.task('package',function(){
    return runSequence(['build'],['serve']);
})

// 开发模式(执行命令 gulp)
gulp.task('default',function(){
    return runSequence(['develop','watch','compile-css']);
})
