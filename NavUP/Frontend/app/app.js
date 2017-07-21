'use strict';

// Declare app level module which depends on views, and components
var myApp = angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2',
  'myApp.version'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/view1'});
}]);

myApp.controller('AppCtrl', ['$scope', '$http', function($scope, $http) {
	console.log("Hello World from controller");


	var refresh = function() {
	  $http.get('/employees_rest/api/user/get').success(function(response) {
	    console.log("I got the data I requested");
	    $scope.contactlist = response;
	    $scope.contact = "";
	  });
	};

	refresh();

}]);
