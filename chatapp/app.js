var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
//
var routes = require('./routes/index.js');
//var users = require('./routes/users');
//
var app = express();
//
//// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
//
// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//////catch 404 and forward to error handler
//app.use(function(req, res, next) {
//    console.log("app use");
////  var err = new Error('Not Found');
////  err.status = 404;
//    next();
//});

//
app.use('/', routes);
//app.use('/users', users);
//
//// catch 404 and forward to error handler
//app.use(function(req, res, next) {
//  var err = new Error('Not Found');
//  err.status = 404;
//  next(err);
//});
//
//// error handlers
//
//// development error handler
//// will print stacktrace
//if (app.get('env') === 'development') {
//  app.use(function(err, req, res, next) {
//    res.status(err.status || 500);
//    res.render('error', {
//      message: err.message,
//      error: err
//    });
//  });
//}
//
//// production error handler
//// no stacktraces leaked to user
//app.use(function(err, req, res, next) {
//  res.status(err.status || 500);
//  res.render('error', {
//    message: err.message,
//    error: {}
//  });
//});
//
//






//// 1.モジュールオブジェクトの初期化
//var fs = require("fs");
//var server = require("http").createServer(function(req, res) {
//     res.writeHead(200, {"Content-Type":"text/html"});
//     var output = fs.readFileSync("./public/index.html", "utf-8");
//     res.end(output);
//}).listen(8080);
//var io = require("socket.io").listen(server);
//
//// ユーザ管理ハッシュ
//var userHash = {};
//
//// 2.イベントの定義
//io.sockets.on("connection", function (socket) {
//
//  // 接続開始カスタムイベント(接続元ユーザを保存し、他ユーザへ通知)
//  socket.on("connected", function (name) {
//    var msg = "まーふが入室しました" + "(" + name + ")";
//    userHash[socket.id] = name;
//    io.sockets.emit("publish", {value: msg});
//  });
//
//  // メッセージ送信カスタムイベント
//  socket.on("publish", function (data) {
//    io.sockets.emit("publish", {value:data.value});
//  });
//
//  // 接続終了組み込みイベント(接続元ユーザを削除し、他ユーザへ通知)
//  socket.on("disconnect", function () {
//    if (userHash[socket.id]) {
//      var msg = userHash[socket.id] + "が退出しました";
//      delete userHash[socket.id];
//      io.sockets.emit("publish", {value: msg});
//    }
//  });
//});

module.exports = app;
