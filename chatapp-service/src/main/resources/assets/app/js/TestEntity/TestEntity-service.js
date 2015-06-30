'use strict';

angular.module('chatapp')
  .factory('TestEntity', ['$resource', function ($resource) {
    return $resource('chatapp/Testentities/:id', {}, {
      'query': { method: 'GET', isArray: true},
      'get': { method: 'GET'},
      'update': { method: 'PUT'}
    });
  }]);
