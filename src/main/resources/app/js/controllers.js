var cricforceApp = angular.module('cricforce');

cricforceApp.controller('IndexController', ['$scope', '$http', function ($scope, $http) {
    $scope.origin = 'main/src/resources';

    $http.get('/players').success(function (data) {
        $scope.players = data;
    });
}]);