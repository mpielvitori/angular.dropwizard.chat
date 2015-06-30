'use strict';

angular.module('chatapp')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/ChatRoom/:idRoom/:nickname', {
        templateUrl: 'views/Room/ChatRoom.html',
        controller: 'ChatRoomController'
      })
    }]);
