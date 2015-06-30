'use strict';

angular.module('chatapp')
  .controller('RoomController', ['$scope', '$modal', 'resolvedRoom', 'Room', '$location',
    function ($scope, $modal, resolvedRoom, Room, $location) {

      $scope.Rooms = resolvedRoom;

      $scope.create = function () {
        $scope.clear();
        $scope.open();
      };

      $scope.join = function (id) {
        $scope.Room = Room.get({id: id});
        $scope.openJoin(id, $location);
      };

      /*$scope.update = function (id) {
        $scope.Room = Room.get({id: id});
        $scope.open(id);
      };

      $scope.delete = function (id) {
        Room.delete({id: id},
          function () {
            $scope.Rooms = Room.query();
          });
      };*/

      $scope.save = function (id) {
        if (id) {
          Room.update({id: id}, $scope.Room,
            function () {
              $scope.Rooms = Room.query();
              $scope.clear();
            });
        } else {
          Room.save($scope.Room,
            function () {
              $scope.Rooms = Room.query();
              $scope.clear();
            });
        }
      };

      $scope.clear = function () {
        $scope.Room = {

          "name": "",

          "messages": "",

          "id": ""
        };
      };

      $scope.open = function (id) {
        var RoomSave = $modal.open({
          templateUrl: 'Room-save.html',
          controller: 'RoomSaveController',
          resolve: {
            Room: function () {
              return $scope.Room;
            }
          }
        });

        RoomSave.result.then(function (entity) {
          $scope.Room = entity;
          $scope.save(id);
        });
      };

        $scope.openJoin = function (id, $location) {
            var RoomJoin =  $modal.open({
                templateUrl: 'Room-join.html',
                controller: 'RoomJoinController',
                resolve: {
                    Room: function () {
                        return $scope.Room;
                    }
                }
            });
            RoomJoin.result.then(function (nickname) {
                //alert("param "+nickname+"-"+id);
                $location.path('/ChatRoom');
            });
        };
    }])
  .controller('RoomSaveController', ['$scope', '$modalInstance', 'Room',
    function ($scope, $modalInstance, Room) {
      $scope.Room = Room;

      $scope.ok = function () {
        $modalInstance.close($scope.Room);
      };

      $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
      };
    }])
    .controller('RoomJoinController', ['$scope', '$modalInstance', 'Room',
        function ($scope, $modalInstance, Room) {
            $scope.Room = Room;

            $scope.ok = function (nickname) {
                $modalInstance.close(nickname);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);
