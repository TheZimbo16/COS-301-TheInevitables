var services = angular.module('myApp', ['ngResource']);

services.factory('UserCrudFactory', function ($resource) {
    return $resource('/Employees/webapi/users', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    })
});