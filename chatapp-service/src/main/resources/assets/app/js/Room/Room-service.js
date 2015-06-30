'use strict';

angular.module('chatapp')
  .factory('Room', ['$resource', function ($resource) {
    return $resource('chatapp/Rooms/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
