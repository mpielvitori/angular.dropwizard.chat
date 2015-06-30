'use strict';

angular.module('chatapp')
  .controller('TestEntityController', ['$scope', '$modal', 'resolvedTestEntity', 'TestEntity',
    function ($scope, $modal, resolvedTestEntity, TestEntity) {

      $scope.Testentities = resolvedTestEntity;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.TestEntity = TestEntity.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        TestEntity.delete({id: id},
          function () {
            $scope.Testentities = TestEntity.query();
          });
      };

      $scope.save = function (id) {
        if (id) {
          TestEntity.update({id: id}, $scope.TestEntity,
            function () {
              $scope.Testentities = TestEntity.query();
              $scope.clear();
            });
        } else {
          TestEntity.save($scope.TestEntity,
            function () {
              $scope.Testentities = TestEntity.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.TestEntity = {
          
          "name": "",
          
          "time": "",
          
          "id": ""
        };
      };

      $scope.open = function (id) {
        var TestEntitySave = $modal.open({
          templateUrl: 'TestEntity-save.html',
          controller: 'TestEntitySaveController',
          resolve: {
            TestEntity: function () {
              return $scope.TestEntity;
            }
          }
        });

        TestEntitySave.result.then(function (entity) {
          $scope.TestEntity = entity;
          $scope.save(id);
        });
      };
    }])
  .controller('TestEntitySaveController', ['$scope', '$modalInstance', 'TestEntity',
    function ($scope, $modalInstance, TestEntity) {
      $scope.TestEntity = TestEntity;

      
      $scope.timeDateOptions = {
        dateFormat: 'yy-mm-dd',
        
        
      };

      $scope.ok = function () {
        $modalInstance.close($scope.TestEntity);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
