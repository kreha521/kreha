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
			"id":$scope.id
			, "job":$scope.job
			, "name":$scope.name
		};

        var config = {
                headers : {
                    'Content-Type': 'application/json;'
                }
            }
        

        $http({
            method: 'POST',
            url: 'http://localhost:3000/characters',
            data: character
          })
          // 成功時の処理（ページにあいさつメッセージを反映）
          .success(function(data, status, headers, config){
          	$scope.characters = Characters.query();
          })
          // 失敗時の処理（ページにエラーメッセージを反映）
          .error(function(data, status, headers, config){
            $scope.result = '通信失敗！';
          });
	};
})

.directive('singleByte',function(){
    return{
        restrict: "A",
        require: "ngModel",
        link: function(scope,element,attrs,ngModel){
            ngModel.$validators.singleByte = function(modelValue,viewValue){
                var value = modelValue || viewValue;
                if  (!value) {
                	return true;
                }
                return /^[\x20-\x7E]*$/.test(value);
            }
        }
    }
})

.directive('number',function(){
    return{
        restrict: "A",
        require: "ngModel",
        link: function(scope,element,attrs,ngModel){
            ngModel.$validators.number = function(modelValue,viewValue){
                var value = modelValue || viewValue;
                if  (!value) {
                	return true;
                }
                return /^[0-9]*$/.test(value);
            }
        }
    }
})


.directive('alphanumber',function(){
    return{
        restrict: "A",
        require: "ngModel",
        link: function(scope,element,attrs,ngModel){
            ngModel.$validators.alphanumber = function(modelValue,viewValue){
                var value = modelValue || viewValue;
                if  (!value) {
                	return true;
                }
                return /^[0-9a-zA-Z]*$/.test(value);
            }
        }
    }
})


;
