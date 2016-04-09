angular.module("app").config(function($routeProvider) {
    $routeProvider
        .when('/app/login', {
            templateUrl: '/app/createAccount.html',
            //controller: 'loginController'
        })
});
