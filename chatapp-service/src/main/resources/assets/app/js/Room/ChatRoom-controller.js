'use strict';

angular.module('chatapp')
  .controller('ChatRoomController', ['$scope', '$modal', 'Room', '$routeParams','Message',
    function ($scope, $modal, Room, $routeParams, Message) {
        $scope.room = Room.get({id: $routeParams.idRoom});
        $scope.messages = Message.query();//{room: $routeParams.idRoom});
        $scope.nickname = $routeParams.nickname;

        $scope.sendMessage = function (message) {
            alert("ahora "+Date.now());
            var newMessage = {

                "message": message,

                "nickname": $scope.nickname,

                "room": $scope.room.id,

                "time": Date.now()
            };
            Message.save(newMessage,
                function () {
                    $scope.messages = Message.query();//{room: $routeParams.idRoom});
                });
        };
    }]);
