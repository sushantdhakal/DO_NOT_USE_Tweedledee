<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/assets/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/assets/tweddledee.css">

    <script type="application/javascript" src="/assets/jquery-2.2.0.min.js"></script>
    <script type="application/javascript" src="/assets/application.js"></script>
    <script type="application/javascript" src="/assets/tweedledee/controllers/accountHandler.js"></script>
    <script type="application/javascript" src="/assets/angular/angular.js"></script>
    <script type="application/javascript" src="/assets/angular-route/angular-route.js"></script>

</head>

<body ng-app="app">
<div id="wrap">
    <div class="page-header container">
        <div class="tweedledeeWelcome">
            <div class="span6">
                Welcome to Tweedledee Application
            </div>
        </div>
    </div>

    <p>
        {{badUser}}
    </p>

    <div id="login-holder" class="container" style="width: 300px;">


        <!--
        <div id="login-error" class="alert alert-error">
            <button type="button" class="close" onclick="$('#login-error').hide();">&times;</button>
            Email address and/or password incorrect.
        </div>
        -->

        <div id="loginbox">
            <div id="innerLogin">
                <form name="loginForm" role="form" autocomplete="off">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input ng-model="t_username" id="username" class="form-control" type="text"/>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input ng-model="t_password" id="password" class="form-control" type="password"/>
                    </div>
                    <input type="submit" ng-controller="loginController" ng-click="login()" class="btn btn-primary" value="Login"/>
                    &nbsp;&nbsp;&nbsp;
                    <a href="#/newAccount" class="newAccount" ng-controller="loginController" ng-click="createNewAccount()">Create New Account</a>
                </form>
            </div>

            <div class="clear"></div>
        </div>
    </div>
    <!--
    <div id="content" class="container">
        <div class="span6" style="text-align: right;" ng-show="isAuthenticated">
            Welcome, {{currentUser}}.
            <a href="" ng-controller="logoutController" ng-click="logout()">[Log out]</a>
        </div>
    </div>
    -->

    <div class="footer panel-footer">
        <div class="container">
            <p>
                Version: 1.0
            </p>
        </div>
    </div>
</div>
</body>
</html>
