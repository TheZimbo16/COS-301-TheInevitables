angular.module('app.controllers', [])

  .controller('navUPCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('loginCtrl', ['$scope', '$http', '$timeout', '$location', function ($scope, $http, $timeout, $location) {
    console.log("Hello World from login controller");

    $scope.loginData = "";
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
          //$scope.modal3.show();
        }
      });

      // $timeout(function () {
      //   $location.path("app/login");
      // }, 1000);
    };


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

  .controller('mapCtrl', function ($scope, $ionicLoading) {
    console.log("Hello World from map controller");
    $scope.mapCreated = function (map) {
      $scope.map = map;
      var directionsService = new google.maps.DirectionsService;
      var directionsDisplay = new google.maps.DirectionsRenderer;
      directionsDisplay.setMap(map);
      // var marker1 = new google.maps.LatLng(-25.755360, 28.232476);
      // google.maps.event.addListenerOnce($scope.map, 'idle', function(){
      //
      //   var center = new google.maps.Marker({
      //     map: $scope.map,
      //     animation: google.maps.Animation.DROP,
      //     position: marker1
      //   });
      //
      //   var infoWindow = new google.maps.InfoWindow({
      //     content: "Map Center!"
      //   });
      //
      //   var marker2 = new google.maps.LatLng(-25.755470, 28.233476);
      //
      //     var dest = new google.maps.Marker({
      //       map: $scope.map,
      //       animation: google.maps.Animation.DROP,
      //       position: marker2
      //     });
      //
      //     var infoWindow2 = new google.maps.InfoWindow({
      //       content: "destination!"
      //     });
      //
      //   google.maps.event.addListener(center, 'click', function () {
      //     infoWindow.open($scope.map, center);
      //   });
      //   google.maps.event.addListener(dest, 'click', function () {
      //     infoWindow2.open($scope.map, dest);
      //   });
      //
      // });
      var onChangeHandler = function() {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
      };
      document.getElementById('end').addEventListener('change', onChangeHandler);
    };

    function calculateAndDisplayRoute(directionsService, directionsDisplay){
      console.log("clicked");
      var start = navigator.geolocation.getCurrentPosition();
      var finish = new google.maps.LatLng(-25.756370, 28.232680);

      directionsService.route({
        origin: start,
        destination: finish,
        travelMode: 'WALKING'
      }, function(response, status) {
        if (status === 'OK') {
          directionsDisplay.setDirections(response);
        } else {
          window.alert('Directions request failed due to ' + status);
        }
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

  .controller('adminLocationsCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


    }])

  .controller('adminUsersCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
    function ($scope, $stateParams) {


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
