'use strict';

angular.module('chatapp')
  .factory('TestEntity', ['$resource', function ($resource) {
    return $resource('chatapp/Testentities/:id', {}, {
      'get': { method: 'GET', isArray: true}
    });
  }]);
