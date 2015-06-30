'use strict';

angular.module('chatapp')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/Rooms', {
        templateUrl: 'views/Room/Rooms.html',
        controller: 'RoomController',
        resolve:{
          resolvedRoom: ['Room', function (Room) {
            return Room.query();
          }]
        }
      })
    }]);
