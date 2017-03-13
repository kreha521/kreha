angular.module('ffffrk')

.factory('socket', ['$rootScope', function($rootScope) {
  var socket = io.connect('http://localhost:3000/psmonitor',  { transports: [ 'websocket' ] });

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
