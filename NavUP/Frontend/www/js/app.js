angular.module('myApp', ['ionic', 'myApp.controllers', 'myApp.directives'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

    .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'LoginCtrl'
  })

  .state('app.search', {
    url: '/search_map',
    views: {
      'menuContent': {
        templateUrl: 'templates/search.html'
      }
    }
  })

  .state('app.users', {
      url: '/users',
      views: {
        'menuContent': {
          templateUrl: 'templates/users.html',
        }
      }
    })
    .state('app.places', {
      url: '/places',
      views: {
        'menuContent': {
          templateUrl: 'templates/places.html',
          controller: 'PlacesCtrl'
        }
      }
    })

  .state('app.single', {
    url: '/place/:placeId',
    views: {
      'menuContent': {
        templateUrl: 'templates/place.html',
        controller: 'PlacesCtrl'
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/search_map');
});

// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// var myApp = angular.module('myApp', []);
// myApp.controller('appCtrl', ['$scope', '$http', function($scope, $http) {
//     console.log("Hello World from controller");
//
//
// var refresh = function() {
//   $http.get('/contactlist').success(function(response) {
//     console.log("I got the data I requested");
//     $scope.contactlist = response;
//     $scope.contact = "";
//   });
// };
//
// refresh();
//
// $scope.addContact = function() {
//   console.log($scope.contact);
//   $http.post('/contactlist', $scope.contact).success(function(response) {
//     console.log(response);
//     refresh();
//   });
// };
//
// $scope.remove = function(id) {
//   console.log(id);
//   $http.delete('/contactlist/' + id).success(function(response) {
//     refresh();
//   });
// };
//
// $scope.edit = function(id) {
//   console.log(id);
//   $http.get('/contactlist/' + id).success(function(response) {
//     $scope.contact = response;
//   });
// };
//
// $scope.update = function() {
//   console.log($scope.contact._id);
//   $http.put('/contactlist/' + $scope.contact._id, $scope.contact).success(function(response) {
//     refresh();
//   })
// };
//
// $scope.deselect = function() {
//   $scope.contact = "";
// }
//
// }]);﻿
