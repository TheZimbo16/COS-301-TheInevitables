var myApp = angular.module('myApp', []);
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

}]);
