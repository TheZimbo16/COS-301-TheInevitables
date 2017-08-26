angular.module('app.routes', [])

  .config(function ($stateProvider, $urlRouterProvider) {

    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    $stateProvider


      .state('navUP', {
        url: '/page1',
        templateUrl: 'templates/navUP.html',
        controller: 'navUPCtrl'
      })

      .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'loginCtrl'
      })

      .state('signup', {
        url: '/signup',
        templateUrl: 'templates/signup.html',
        controller: 'signupCtrl'
      })

      .state('menu', {
        url: '/menu',
        templateUrl: 'templates/menu.html',
        controller: 'menuCtrl'
      })

      .state('map', {
        url: '/map',
        templateUrl: 'templates/map.html',
        controller: 'mapCtrl'
      })

      .state('userProfile', {
        url: '/userprofile',
        templateUrl: 'templates/userProfile.html',
        controller: 'userProfileCtrl'
      })

      .state('timetable', {
        url: '/timetable',
        templateUrl: 'templates/timetable.html',
        controller: 'timetableCtrl'
      })

      .state('pointOfInterests', {
        url: '/poi',
        templateUrl: 'templates/pointOfInterests.html',
        controller: 'pointOfInterestsCtrl'
      })

      .state('recentlyVisited', {
        url: '/recentvisit',
        templateUrl: 'templates/recentlyVisited.html',
        controller: 'recentlyVisitedCtrl'
      })

      .state('adminMenu', {
        url: '/adminMenu',
        templateUrl: 'templates/adminMenu.html',
        controller: 'adminMenuCtrl'
      })

      .state('adminPOI', {
        url: '/adminPOI',
        templateUrl: 'templates/adminPOI.html',
        controller: 'adminPOICtrl'
      })

      .state('adminLocations', {
        url: '/adminLocations',
        templateUrl: 'templates/adminLocations.html',
        controller: 'adminLocationsCtrl'
      })

      .state('adminUsers', {
        url: '/adminUsers',
        templateUrl: 'templates/adminUsers.html',
        controller: 'adminUsersCtrl'
      })

      .state('adminAddLocation', {
        url: '/adminAddLocation',
        templateUrl: 'templates/adminAddLocation.html',
        controller: 'adminAddLocationCtrl'
      })

      .state('guestMenu', {
        url: '/guestMenu',
        templateUrl: 'templates/guestMenu.html',
        controller: 'guestMenuCtrl'
      })

      .state('adminEditLocation', {
        url: '/adminEditLocation',
        templateUrl: 'templates/adminEditLocation.html',
        controller: 'adminEditLocationCtrl'
      })

      .state('adminAddPointOfInterest', {
        url: '/adminAddPoi',
        templateUrl: 'templates/adminAddPointOfInterest.html',
        controller: 'adminAddPointOfInterestCtrl'
      })

      .state('adminEditPointOfInterest', {
        url: '/adminEditPoi',
        templateUrl: 'templates/adminEditPointOfInterest.html',
        controller: 'adminEditPointOfInterestCtrl'
      })

    $urlRouterProvider.otherwise('/page1')


  });
