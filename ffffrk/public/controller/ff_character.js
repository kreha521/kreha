angular.module('ffffrk')

.factory('Characters', function($resource) {
	return $resource('http://localhost:3000/characters');
})

//.service('Character', function($resource) {
//	return $resource('http://localhost:3000/characters/26');
//})

.controller('CharacterCtrl', function($scope, $http, Characters){
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
                    'Content-Type': 'application/json;'
                }
            }
        
        $http.post('http://localhost:3000/characters', character, config)
        .success(function (data, status, headers, config) {
        })
        .error(function (data, status, header, config) {
        });
		$scope.characters.push(character);
	};
})

.directive('singleByteValidator',function(){
    return{
        restrict: "A",
        require: "ngModel",
        link: function(scope,element,attrs,ngModel){
            ngModel.$validators.singleByte = function(modelValue,viewValue){
                var value = modelValue || viewValue;
                return /^[\x20-\x7E]+$/.test(value);
            }
        }
    }
})

;
