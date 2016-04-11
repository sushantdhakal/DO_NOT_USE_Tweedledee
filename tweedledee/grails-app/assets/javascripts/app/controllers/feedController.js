angular.module('app').controller('feedController', function ($resource, $scope, $http) {

  $scope.userMessages = [];

  $http({
    method: 'GET',
    url: '/message/Admin/messages'
  }).then(function successCallback(response) {
    var temp="";
    console.log(response);
    temp = response.data;
    for (var i = 0; i < temp.length; i++) {
      var tempArr = [];
      tempArr.push(temp[i].dateCreated, temp[i].text)
      $scope.userMessages.push(tempArr);
    }
    cosole.log($scope.userMessages)
    // this callback will be called asynchronously
    // when the response is available
  }, function errorCallback(response) {
    // called asynchronously if an error occurs
    // or server returns response with an error status.
  });
});