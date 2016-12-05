angular.module('ffffrk')

.controller('CharacterCtrl', function ($scope, socket) {
	
	$scope.time='init';
	var psmonitor = io('http://localhost:3000/psmonitor');

	psmonitor.emit('send:time', {"time":"fromAngular"});

	psmonitor.on('send:time', function (data) {
		alert(data.time);
    });
  })

;
