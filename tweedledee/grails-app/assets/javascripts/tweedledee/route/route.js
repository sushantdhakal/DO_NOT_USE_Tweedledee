angular.module("app").config(function($routeProvider) {
    $routeProvider
        .when('/newAccount', {
            templateUrl: 'assets/partials/createAccount.html',
            controller: 'loginController'
        })
});
