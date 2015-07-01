'use strict';

angular.module('chatapp')
  .factory('ChatRoom', ['$resource', function ($resource) {
    return $resource('chatapp/test/:id', {}, {
      'getRoomMessages': { method: 'GET', isArray: true}
    });
  }]);
