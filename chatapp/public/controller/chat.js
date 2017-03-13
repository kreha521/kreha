angular.module('ffffrk')

.controller('ChatCtrl', function ($scope, socket) {	
	$scope.time='init';
    
	socket.on('send:time', function (data) {
		console.log(data.time);
    });
    
    $scope.startChat = () => {
	   socket.emit('send:time', {"time":"fromAngular"});
    }
  })

;
