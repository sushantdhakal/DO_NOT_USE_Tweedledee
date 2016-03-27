<!DOCTYPE html>
<html ng-app="gravitasApp" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Tweedledee...."/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
    <asset:stylesheet href="bootstrap.css"/>
    <asset:stylesheet href="tweddledee.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="tweedledee/tweedledee.js"></asset:javascript>
    <g:layoutHead/>
</head>

<body class="loginContainer" ng-controller="sxd123">
<div id="wrap">
    <div class="page-header container">
        <div class="tweedledeeWelcome">
            <div class="span6">
                Welcome to Tweedledee Application
            </div>
        </div>
    </div>

    <div id="login-holder" class="container" style="width: 300px;">


        <!--
        <div id="login-error" class="alert alert-error">
            <button type="button" class="close" onclick="$('#login-error').hide();">&times;</button>
            Email address and/or password incorrect.
        </div>
        -->

        <div id="loginbox">
            <div id="innerLogin" ng-controller="loginController">
                <form name="loginForm" role="form" ng-submit="logIn()" autocomplete="off">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input id="username" class="form-control" type="text"/>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" class="form-control" type="password"/>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Login"/>
                </form>
            </div>

            <div class="clear"></div>
        </div>
    </div>

    <div id="content" class="container">
        <div class="span6" style="text-align: right;" ng-show="isAuthenticated">
            Welcome, {{currentUser}}.
            <a href="" ng-controller="logoutController" ng-click="logout()">[Log out]</a>
        </div>
        <g:layoutBody/>
    </div>

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
