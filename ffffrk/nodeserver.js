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
		url: 'http://localhost:10000/characters',
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
		url: 'http://localhost:10000/characters/' + req.params.id,
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

// 追加・更新
app.post("/characters", function(req, res) {
	console.log(req);
	var request = require('request');
	var options = {
		url: "http://localhost:10000/characters",
	  headers: {
	    "Content-type": "application/json",
	  },
	  json: req.body
	};
	request.post(options, function(error, response, body){
		if (!error && response.statusCode == 200) {
			res.send(body)
		} else {
			console.log('error: '+ response.statusCode);
		}
	});
});
//
//// 削除
//app.delete("/api/users/:_id", function(req, res) {
//  users.remove({_id: mongodb.ObjectID(req.params._id)}, function() {
//    res.send("delete");
//  });
//});
