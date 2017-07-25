angular.module('myApp.controllers', [])

.controller('MapCtrl', function($scope, $ionicLoading) {
  console.log("Hello World from map controller");
  $scope.mapCreated = function(map) {
    $scope.map = map;
  };

  $scope.centerOnMe = function () {
    console.log("Centering");
    if (!$scope.map) {
      return;
    }

    $scope.loading = $ionicLoading.show({
      content: 'Getting current location...',
      showBackdrop: false
    });

    navigator.geolocation.getCurrentPosition(function (pos) {
      console.log('Got pos', pos);
      $scope.map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
      $scope.loading.hide();
    }, function (error) {
      alert('Unable to get location: ' + error.message);
    });
  };
})

.controller('LoginCtrl', function($scope, $ionicModal, $timeout) {
  console.log("Hello World from login controller");

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };
})

.controller('RegisterCtrl', function($scope, $ionicModal, $timeout) {
  console.log("Hello World from register controller");

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.RegisterData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/register.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeRegister = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.register = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doRegister = function() {
    console.log('Doing registration', $scope.registerData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeRegister();
    }, 1000);
  };
})

.controller('usersCtrl', ['$scope', '$http', function($scope, $http) {
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

}])

.controller('PlacesCtrl', ['$scope', '$http', function($scope, $ionicModal, $http) {
  console.log("Hello World from places controller");

  // myApp.config(function($httpProvider) {
  //   /**
  //    * make delete type json
  //    */
  //   $httpProvider.defaults.headers["delete"] = {
  //     'Content-Type': 'application/json;charset=utf-8'
  //   };
  // })

  var refresh = function() {
    $http.get('/employees_rest/api/places/get').success(function(response) {
      console.log("I got the data I requested");
      $scope.placeslist = response;
      $scope.place = "";
    });
  };

  refresh();

  	$scope.addPlace = function() {
  	  console.log($scope.place);
  	  $http.post('/employees_rest/api/places/', $scope.place).success(function(response) {
  	    console.log(response);
  	    refresh();
  	  });
  	};

  	$scope.remove = function() {
  		$http.defaults.headers["delete"] = {
  			     'Content-Type':'application/json'
  	};

  	  $http.delete('/employees_rest/api/places/delete',$scope.place).success(function(data, status, headers, config) {
  	    refresh();
  	  }).error(function(data, status, headers, config) {
      });
  	};

  	$scope.edit = function(id) {
  	  console.log(id);
  	  $http.get('/employees_rest/webapi/places/' + id).success(function(response) {
  	    $scope.contact = response;
  	  });
  	};

  	$scope.update = function() {
  	  console.log($scope.place.name);
  	  $http.put('/employees_rest/webapi/places/' + $scope.place.name, $scope.place).success(function(response) {
  	    refresh();
  	  })
  	};

  	$scope.deselect = function() {
  	  $scope.place = "";
  	}

  }])

// .controller('PlacesCtrl', function($scope, $stateParams) {
//   console.log("Hello World from place controller");
// })
