'use strict';

angular.module('chatapp')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/ChatRoom', {
        templateUrl: 'views/Room/ChatRoom.html',
        controller: 'ChatRoomController',
        resolve:{
          resolvedRoom: ['Room', function (Room) {
            return Room.get({id: 101});
          }]
        }
      })
    }]);
