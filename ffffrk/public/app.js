var app = angular.module('ffffrk', ['ngRoute', 'ngResource']);

app.config(function($routeProvider){
	$routeProvider.otherwise({templateUrl: 'views/characters.html'});
});
