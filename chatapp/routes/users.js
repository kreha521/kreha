var express = require('express');
var router = express.Router();

console.log("users kitaaa");

/* GET users listing. */
router.get('/users', function(req, res, next) {
	console.log("users gets");
  res.send('respond with a resource');
});

module.exports = router;
