'use strict';

angular.module('chatapp')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/Messages', {
        templateUrl: 'views/Message/Messages.html',
        controller: 'MessageController',
        resolve:{
          resolvedMessage: ['Message', function (Message) {
            return Message.query();
          }]
        }
      })
    }]);
