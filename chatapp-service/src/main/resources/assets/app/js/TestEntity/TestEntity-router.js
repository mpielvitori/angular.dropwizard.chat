'use strict';

angular.module('chatapp')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/Testentities', {
        templateUrl: 'views/TestEntity/Testentities.html',
        controller: 'TestEntityController',
        resolve:{
          resolvedTestEntity: ['TestEntity', function (TestEntity) {
            return TestEntity.query();
          }]
        }
      })
    }]);
