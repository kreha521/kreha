angular.module('ffffrk')

.service('Characters', function($resource) {
	return $resource('http://localhost:3000/characters');
})

.controller('CharacterCtrl', function($scope, $http, Characters){
	$scope.characters = Characters.query();
	$scope.register = function(){
		var character = {
				id:$scope.id
			, job:$scope.job
			, name:$scope.name
		};
		$scope.characters.push(character);
	};
})

;
