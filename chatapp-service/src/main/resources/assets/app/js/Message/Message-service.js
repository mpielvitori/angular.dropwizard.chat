'use strict';

angular.module('chatapp')
  .factory('Message', ['$resource', function ($resource) {
    return $resource('chatapp/Messages/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
