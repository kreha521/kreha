var app = angular.module('ffffrk', ['ngRoute', 'btford.socket-io']);

app.config(function($routeProvider){
	$routeProvider.otherwise({templateUrl: 'views/chat.html'});
});

;
