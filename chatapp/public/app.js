var app = angular.module('ffffrk', ['ngRoute', 'btford.socket-io']);

app.config(function($routeProvider){
	$routeProvider.otherwise({templateUrl: 'views/chat.html'});
});

app.factory('socket', ['$rootScope', function($rootScope) {
  var socket = io.connect('http://localhost:3000/psmonitor');

  return {
    on: function(eventName, callback){
      socket.on(eventName, callback);
    },
    emit: function(eventName, data) {
      socket.emit(eventName, data);
    }
  };
}])

;
