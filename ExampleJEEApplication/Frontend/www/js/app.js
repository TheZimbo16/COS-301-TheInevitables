// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
var myApp =angular.module('myApp', ['ionic']);

myApp.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})
myApp.controller('myCtrl', ['$scope', '$http', function($scope, $http) {
    console.log("Hello World from controller");

myApp.config(function($httpProvider) {
  /**
   * make delete type json
   */
  $httpProvider.defaults.headers["delete"] = {
    'Content-Type': 'application/json;charset=utf-8'
  };
})

var refresh = function() {
  $http.get('/employees_rest/api/user/get').success(function(response) {
    console.log("I got the data I requested");
    $scope.contactlist = response;
    $scope.contact = "";
  });
};

refresh();

	$scope.addContact = function() {
	  console.log($scope.contact);
	  $http.post('/employees_rest/api/user/', $scope.contact).success(function(response) {
	    console.log(response);
	    refresh();
	  });
	};

	$scope.remove = function() {
		$http.defaults.headers["delete"] = {
			     'Content-Type':'application/json'
	};

	  $http.delete('/employees_rest/api/user/delete',$scope.contact).success(function(data, status, headers, config) {
	    refresh();
	  }).error(function(data, status, headers, config) {
    });
	};

	$scope.edit = function(id) {
	  console.log(id);
	  $http.get('/employees_rest/webapi/users/' + id).success(function(response) {
	    $scope.contact = response;
	  });
	};

	$scope.update = function() {
	  console.log($scope.contact._id);
	  $http.put('/employees_rest/webapi/users/' + $scope.contact._id, $scope.contact).success(function(response) {
	    refresh();
	  })
	};

	$scope.deselect = function() {
	  $scope.contact = "";
	}

}]);;
