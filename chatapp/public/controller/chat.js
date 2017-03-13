angular.module('ffffrk')

.controller('ChatCtrl', function ($scope, socket) {	
    $scope.voices = [];
    $scope.name = "";

    socket.on('say', function (data) {
        $scope.voices.push(data.voice);
		console.log(data.voice);
    });

    socket.on('reply', function (data) {
        $scope.voices.push(data.voice);
		console.log(data.voice);
    });

    $scope.startChat = () => {
	   socket.emit('startChat', {
           "name": $scope.name
       });
    };

   $scope.say = () => {
	   socket.emit('say', {
           "name": $scope.name,
           "voice": $scope.voice
       });
    };
    
    $scope.startChat();
})

;
