angular.module('app', []);


// Define a controller called 'welcomeController'
angular.module('app').controller('loginController', ['$scope', '$http', '$window', function($scope, $http, $window) {

        $scope.username = "";
        $scope.badUser = "";
        $scope.currentUser = "jhdfjhsdf";
        $scope.login = function() {
            var data = $.param({
                t_uname: $scope.t_username,
                t_password: $scope.t_password
            });
            $http.post('/login', data).success(function(data, status) {
                $scope.hello = data;
            })
                .error(function () {
                    //We need to change it to just a message
                    $scope.badUser = "Wrong username/password";
                    $window.alert("Wrong username/password");
                });
        }
}]);
