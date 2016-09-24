var path = require('path');
var express = require('express');
var bodyParser = require('body-parser');
var app = express();
 
app.use('/', express.static(path.join(__dirname, 'public')));
app.use(express.static('js'));
app.use(bodyParser.json());

app.listen(3000);
 
console.log('Server started: http://localhost:3000/');




var users;


// 一覧取得
app.get("/characters", function(req, res) {
	console.log('Get characters');

	var request = require('request');
	var options = {
		url: 'http://localhost:8080/characters',
		json: true
	};

	request.get(options, function (error, response, body) {
		if (!error && response.statusCode == 200) {
			res.send(body)
		} else {
			console.log('error: '+ response.statusCode);
		}
	})
});

// 個人取得
app.get("/characters/:id", function(req, res) {
	
	console.log('Get characters id:' + req.params.id);

	var request = require('request');
	var options = {
		url: 'http://localhost:8080/characters/' + req.params.id,
		json: true
	};

	request.get(options, function (error, response, body) {
		if (!error && response.statusCode == 200) {
			res.send(body)
		} else {
			console.log('error: '+ response.statusCode);
		}
	})
});

//// 追加・更新
//app.post("/api/users", function(req, res) {
//  var user = req.body;
//  if (user._id) user._id = mongodb.ObjectID(user._id);
//  users.save(user, function() {
//    res.send("insert or update");
//  });
//});
//
//// 削除
//app.delete("/api/users/:_id", function(req, res) {
//  users.remove({_id: mongodb.ObjectID(req.params._id)}, function() {
//    res.send("delete");
//  });
//});