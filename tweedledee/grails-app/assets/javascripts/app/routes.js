/*angular.module("app").config(function($routeProvider) {
    $routeProvider
        .when('/newAccount', {
            templateUrl: '/app/createAcc.html'
            //controller: 'loginController'
        })
        .otherwise({
            redirectTo: '/app/login'
        })
});*/


angular.module("app")

    // configure the routes
    .config(function ($routeProvider) {

        $routeProvider
            .when('/login', {
                templateUrl: '/app/login.html'

            })
            .otherwise({
                redirectTo: '/app/login.html'
            })
    })

    // Protect all routes other than login
    .run(function ($rootScope, $location, securityService) {
        $rootScope.$on('$routeChangeStart', function (event, next) {
            if (next.$$route.originalPath != '/login') {
                if (!securityService.currentUser()) {
                    $location.path('/login');
                }
            }
        });
    });

