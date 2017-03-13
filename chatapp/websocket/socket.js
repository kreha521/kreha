/*
 * Serve content over a socket
 */

var mysql = require('mysql');

//SQL文を書く
const sql = 'SELECT * FROM characters WHERE id = ?;';

module.exports = {
	onSocket: function (socket) {
	    console.log("socket start");

	    socket.on('startChat', function (data) {
            console.log(data.name);
            socket.emit('reply', {
                voice: 'Hello, ' + data.name
            });
	    });

        socket.on('say', function (data) {
            console.log(data.name + ":" + data.voice);
            socket.emit('reply', {
                voice: data.name + ":" + data.voice
            });
	    });

//	  setInterval(function () {
//	  	var connection = mysql.createConnection({
//			  host     : 'localhost', //接続先ホスト
//			  user     : 'root',      //ユーザー名
//			  password : '4you6so',  //パスワード
//			  database : 'testdatabase',    //DB名
//		      charset : 'utf8'
//			});
//	
//		//接続します
//		connection.connect();
//	
//		//プレースホルダー使ってSQL発行
//		var query = connection.query(sql, ['26']);
//	    var record;
//		//あとはイベント発生したらそれぞれよろしくねっ
//		query
//		  //エラー用
//		  .on('error', function(err) {
//		  })
//	
//		  //結果用
//		  .on('result', function(rows) {
//            console.log(rows.name);
//            socket.emit('say', {
//              voice: rows.name
//            });
//		  })
//	
//		  //終わったよう～
//		  .on('end', function() {
//		    connection.destroy(); //終了
//		  });
//	  }, 5000);
	}
};
