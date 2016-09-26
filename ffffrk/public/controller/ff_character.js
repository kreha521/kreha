angular.module('ffffrk')

.service('Characters', function($resource) {
	return $resource('http://localhost:3000/characters');
})

.service('Character', function($resource) {
	return $resource('http://localhost:3000/characters/26');
})

.controller('CharacterCtrl', function($scope, $http, Characters, Character){
	$scope.characters = Characters.query();
//	$scope.character = Character.get();
//	console.log($scope.character.m.id);

	$scope.register = function(){
		var character = {
			id:$scope.id
			, job:$scope.job
			, name:$scope.name
		};

        var config = {
                headers : {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                }
            }
        
        $http.post('http://localhost:10000/characters', character, config)
        .success(function (data, status, headers, config) {
            alert("suc")
        })
        .error(function (data, status, header, config) {
            alert("fail")
        });
		$scope.characters.push();
	};
})

;
