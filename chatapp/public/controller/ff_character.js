angular.module('ffffrk')

.controller('CharacterCtrl', function ($scope, socket) {
    socket.on('send:time', function (data) {
      $scope.time = data.time;
    });
  })

;
