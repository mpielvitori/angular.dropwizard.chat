'use strict';

angular.module('chatapp')
  .controller('MessageController', ['$scope', '$modal', 'resolvedMessage', 'Message',
    function ($scope, $modal, resolvedMessage, Message) {

      $scope.Messages = resolvedMessage;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.update = function (id) {
        $scope.Message = Message.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        Message.delete({id: id},
          function () {
            $scope.Messages = Message.query();
          });
      };

      $scope.save = function (id) {
        if (id) {
          Message.update({id: id}, $scope.Message,
            function () {
              $scope.Messages = Message.query();
              $scope.clear();
            });
        } else {
          Message.save($scope.Message,
            function () {
              $scope.Messages = Message.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.Message = {

          "message": "",

          "nickname": "",

          "room": "",

          "time": "",

          "id": ""
        };
      };

      $scope.open = function (id) {
        var MessageSave = $modal.open({
          templateUrl: 'Message-save.html',
          controller: 'MessageSaveController',
          resolve: {
            Message: function () {
              return $scope.Message;
            }
          }
        });

        MessageSave.result.then(function (entity) {
          $scope.Message = entity;
          $scope.save(id);
        });
      };
    }])
  .controller('MessageSaveController', ['$scope', '$modalInstance', 'Message',
    function ($scope, $modalInstance, Message) {
      $scope.Message = Message;


      $scope.timeDateOptions = {
        dateFormat: 'yy-mm-dd',


      };

      $scope.ok = function () {
        $modalInstance.close($scope.Message);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }]);
