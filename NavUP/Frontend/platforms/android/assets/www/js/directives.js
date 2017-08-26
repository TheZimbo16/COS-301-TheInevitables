angular.module('app.directives', [])

  .directive('map', function () {
    return {
      restrict: 'E',
      scope: {
        onCreate: '&'
      },
      link: function ($scope, $element, $attr) {
        function initialize() {
          // var directionsService = new google.maps.DirectionsService;
          // var directionsDisplay = new google.maps.DirectionsRenderer;
          var mapOptions = {
            center: new google.maps.LatLng(-25.755360, 28.232476),
            zoom: 17,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };
          var map = new google.maps.Map($element[0], mapOptions);
          map.data.loadGeoJson('/employees_rest/api/geoJSON/get');
          // directionsDisplay.setMap(map);

          $scope.onCreate({map: map});

          // Stop the side bar from dragging when mousedown/tapdown on the map
          google.maps.event.addDomListener($element[0], 'mousedown', function (e) {
            e.preventDefault();
            return false;
          });
        }

        // $scope.calculateAndDisplayRoute = function(directionsService, directionsDisplay) {
        //   console.log("Clicked");
        //   var start = LatLng(-25.755360, 28.232476);
        //   var finish = LatLng(-25.775320, 28.262486);
        //   directionsService.route({
        //     origin: document.getElementById('start').value,
        //     destination: document.getElementById('end').value,
        //     travelMode: 'WALKING'
        //   }, function(response, status) {
        //     if (status === 'OK') {
        //       directionsDisplay.setDirections(response);
        //     } else {
        //       window.alert('Directions request failed due to ' + status);
        //     }
        //   });
        // }


        if (document.readyState === "complete") {
          initialize();
        } else {
          google.maps.event.addDomListener(window, 'load', initialize);
        }
      }
    }
  })
