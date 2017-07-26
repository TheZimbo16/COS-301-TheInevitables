angular.module('myApp.directives', [])

    .directive('map', function () {
        return {
            restrict: 'E',
            scope: {
                onCreate: '&'
            },
            link: function ($scope, $element, $attr) {
                function initialize() {
                    var mapOptions = {
                        center: new google.maps.LatLng(-25.755360, 28.232476),
                        zoom: 17,
                        mapTypeId: google.maps.MapTypeId.ROADMAP
                    };
                    var map = new google.maps.Map($element[0], mapOptions);

                    $scope.onCreate({map: map});

                    // Stop the side bar from dragging when mousedown/tapdown on the map
                    google.maps.event.addDomListener($element[0], 'mousedown', function (e) {
                        e.preventDefault();
                        return false;
                    });
                }

                if (document.readyState === "complete") {
                    initialize();
                } else {
                    google.maps.event.addDomListener(window, 'load', initialize);
                }
            }
        }
    })

    .directive('searchBar', [function () {
        return {
            scope: {
                ngModel: '='
            },
            require: ['^ionNavBar', '?ngModel'],
            restrict: 'E',
            replace: true,
            template: '<ion-nav-buttons side="right">' +
            '<div class="searchBar">' +
            '<div class="searchTxt" ng-show="ngModel.show">' +
            '<div class="bgdiv"></div>' +
            '<div class="bgtxt">' +
            '<input type="text" placeholder="Procurar..." ng-model="ngModel.txt">' +
            '</div>' +
            '</div>' +
            '<i class="icon placeholder-icon" ng-click="ngModel.txt=\'\';ngModel.show=!ngModel.show"></i>' +
            '</div>' +
            '</ion-nav-buttons>',

            compile: function (element, attrs) {
                var icon = attrs.icon
                    || (ionic.Platform.isAndroid() && 'ion-android-search')
                    || (ionic.Platform.isIOS() && 'ion-ios7-search')
                    || 'ion-search';
                angular.element(element[0].querySelector('.icon')).addClass(icon);

                return function ($scope, $element, $attrs, ctrls) {
                    var navBarCtrl = ctrls[0];
                    $scope.navElement = $attrs.side === 'right' ? navBarCtrl.rightButtonsElement : navBarCtrl.leftButtonsElement;

                };
            },
            controller: ['$scope', '$ionicNavBarDelegate', function ($scope, $ionicNavBarDelegate) {
                var title, definedClass;
                $scope.$watch('ngModel.show', function (showing, oldVal, scope) {
                    if (showing !== oldVal) {
                        if (showing) {
                            if (!definedClass) {
                                var numicons = $scope.navElement.children().length;
                                angular.element($scope.navElement[0].querySelector('.searchBar')).addClass('numicons' + numicons);
                            }

                            title = $ionicNavBarDelegate.getTitle();
                            $ionicNavBarDelegate.setTitle('');
                        } else {
                            $ionicNavBarDelegate.setTitle(title);
                        }
                    } else if (!title) {
                        title = $ionicNavBarDelegate.getTitle();
                    }
                });
            }]
        };
    }]);
