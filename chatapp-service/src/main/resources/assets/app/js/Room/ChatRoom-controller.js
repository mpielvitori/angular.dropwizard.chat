'use strict';

angular.module('chatapp')
  .controller('ChatRoomController', ['$scope', '$modal', 'Room', '$routeParams','Message', 'ChatRoom', 'TestEntity', '$interval',
    function ($scope, $modal, Room, $routeParams, Message, ChatRoom, TestEntity, $interval) {

        $scope.init = function() {
            $scope.room = Room.get({id: $routeParams.idRoom});
            $scope.messages = TestEntity.get({id: $routeParams.idRoom});
            $scope.nickname = $routeParams.nickname;
            $scope.login = Date.now();
        };


            $interval(function () {
                $scope.messages = TestEntity.get({id: $routeParams.idRoom});
            }, 2000);

        $scope.sendMessage = function (message) {
            var newMessage = {

                "message": message,

                "nickname": $scope.nickname,

                "room": $scope.room.id,

                "time": Date.now()
            };
            Message.save(newMessage,
                function () {
                    $scope.messages = TestEntity.get({id: $scope.room.id});
                });
        };
    }]);
