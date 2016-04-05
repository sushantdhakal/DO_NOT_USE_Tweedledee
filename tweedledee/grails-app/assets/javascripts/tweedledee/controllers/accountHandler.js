var app = angular.module('app', []);

//after you pull the router, look at the state that you inject within the controller, so that you can redirect the page
app.controller('loginController', ['$scope', '$http', '$window', '$location', function($scope, $http, $window, $location) {

    $scope.badUser = "";

    $scope.login = function() {

        var req = {
            method: 'POST',
            url: '/login',
            headers: {
                'Content-Type': 'application/json'
            },
            data: { ui_userName: $scope.t_username,
                    ui_password: $scope.t_password
            }
        }

        $http(req).success(function(data, status) {
                $scope.goodUser = "Hellooooooo";

            //TODO: Redirect to User Details Page
            })
            .error(function () {
                //We need to change it to just a message
                $scope.badUser = "Wrong username/password";
                $window.alert("Wrong username/password");
            });
    }

    /*
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
        */

    $scope.createNewAccount = function(){
      //  $window.location.href = "";
       // $window.location.href = 'partials/createAccount.html';
        console.log("Going to create a new account");
        $location.path("/partials/createAccount.html");
    }
}]);
