angular.module('app.controllers', [])

  .controller('navUPCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('loginCtrl', ['$scope', '$http', '$ionicModal', '$timeout', '$location', function ($scope, $http, $ionicModal, $timeout, $location) {
    console.log("Hello World from login controller");

    $scope.loginData = "";
    $ionicModal.fromTemplateUrl('templates/loginWrong.html', {
      scope: $scope
    }).then(function (modal3) {
      $scope.modal3 = modal3;
    });
    $scope.doLogin = function () {
      //console.log("login clicked");
      console.log('Doing login', $scope.loginData);

      $http.post('/employees_rest/api/user/verify', $scope.loginData).success(function (response) {
        console.log(response);

        if (response == true) {
          console.log("login correct");
          $location.path("/menu");
        }
        else {
          $scope.modal3.show();
        }
      });

      // $timeout(function () {
      //   $location.path("app/login");
      // }, 1000);
    };

    $scope.closeWrong = function()
    {
      $scope.modal3.hide();
    }

  }])

  .controller('signupCtrl', ['$scope', '$http', '$timeout', '$location', function ($scope, $http, $timeout, $location) {
    console.log("Hello World from register controller");

    $scope.registerData = "";
    $scope.doRegister = function () {
      console.log('Doing register', $scope.registerData);

      $http.post('/employees_rest/api/user/', $scope.registerData).success(function (response) {
        console.log(response);
      });

      $timeout(function () {
        $location.path("app/login");
      }, 1000);
    };
  }])

  .controller('menuCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('mapCtrl', function ($scope,$http, $ionicLoading) {
    $scope.coordinatesData = "";
    $scope.coordinatesData2 = "";
    $scope.start ="";
    $scope.end = "";
    console.log("Hello World from map controller");
    $scope.mapCreated = function (map) {
      $scope.map = map;
      //$scope.start = new google.maps.LatLng(getMyLocation());
      $scope.directionsService = new google.maps.DirectionsService;
      $scope.directionsDisplay = new google.maps.DirectionsRenderer;
      $scope.directionsDisplay.setMap(map);
    };

    $scope.calculateAndDisplayRoute =function(directionsService, directionsDisplay){
      //we use angular promises to make sure the data gets retrieved, something to do with asynchronous requests that makes the data not appear. Angular promises handles that
      $scope.coords1 = "";
      $scope.coords2 = "";
      //we make nested promise to use data from both promises together otherwise only data of one will load
      var $promise =$http.post('/employees_rest/api/navigation/get', $scope.coordinatesData);
      $promise.then(function(response){
        var $promise1 =$http.post('/employees_rest/api/navigation/get', $scope.coordinatesData2);
        $promise1.then(function(response1){
          
                $scope.coords = response.data.locationCoordinates;//data promise
                $scope.coords1 = response1.data.locationCoordinates; //data promise1

                $scope.res = $scope.coords.split(",");//split string
                $scope.res1 = $scope.coords1.split(",");//split string

                //parse coordinates using the array res and res1
                var parsedLat = parseFloat($scope.res[0]);
                var parsedLng = parseFloat($scope.res[1]);
                var parsedLat1 = parseFloat($scope.res1[0]);
                var parsedLng1 = parseFloat($scope.res1[1]);

                $scope.directionsService.route({
                  origin:  new google.maps.LatLng(parsedLng,parsedLat),//somehow its inverted i do not know why
                  destination:  new google.maps.LatLng(parsedLng1,parsedLat1),
                  travelMode: 'WALKING'
                }, function(response, status) {
                  if (status === 'OK') {
                    $scope.directionsDisplay.setDirections(response);
                  } else {
                    window.alert('Directions request failed due to ' + status);
                  }
                });
          });
      });
    }

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

  .controller('userProfileCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('timetableCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('pointOfInterestsCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('recentlyVisitedCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminMenuCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminPOICtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminLocationsCtrl', ['$scope', '$http', function ($scope, $http) {
    console.log("Hello World from admin locations controller");

    var refresh = function () {
      $http.get('/employees_rest/api/locations/get').success(function (response) {
        console.log("I got the data I requested");
        $scope.contactlist = response;
        $scope.contact = "";
      });
    };

    refresh();
  }])

  .controller('adminUsersCtrl', ['$scope', '$http', function ($scope, $http) {
    console.log("Hello World from users controller");

    var refresh = function () {
      $http.get('/employees_rest/api/user/get').success(function (response) {
        console.log("I got the data I requested");
        $scope.contactlist = response;
        $scope.contact = "";
      });
    };

    refresh();
  }])

  .controller('adminAddLocationCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('guestMenuCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminEditLocationCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminAddPointOfInterestCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminEditPointOfInterestCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])
