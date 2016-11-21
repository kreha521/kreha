angular.module('ffffrk')

.controller('CharacterCtrl', function ($scope, socket) {
    socket.on('send:time', function (data) {
      alert(data.time);
    });
  })

;
