var app = angular.module('app', ['ngRoute']);

//after you pull the router, look at the state that you inject within the controller, so that you can redirect the page
app.controller('loginController', ['$scope', '$http', '$window', '$location', function($scope, $http, $window, $location) {

    $scope.badUser = "";

    $scope.login = function() {

        var req = {
            method: 'POST',
            url: '/api/login',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                "username":$scope.t_username,
                "password":$scope.t_password
            }
        }

        $http(req).success(function(data, status) {
                $scope.goodUser = "Hellooooooo";
            console.log(data);

            //TODO: Redirect to User Details Page
            })
            .error(function () {
                //We need to change it to just a message
                $scope.badUser = "Wrong username/password";
                $window.alert("Wrong username/password");
            });
    }

    $scope.createNewAccount = function(){
        var req = {
            method: 'GET',
            url: 'newAccount',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        $location.path( 'app/createAccount.html' );
      //  $window.location.href = "";
       // $window.location.href = 'partials/createAccount.html';
        console.log("Going to create a new account");
    }
}]);
