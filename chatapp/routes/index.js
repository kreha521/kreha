var express = require('express');
var router = express.Router();

console.log("index inlclude");

/* GET home page. */
router.get('/', function(req, res, next) {
	console.log("index kitaaa");
  res.render('index', { title: 'Express' });
});

module.exports = router;
