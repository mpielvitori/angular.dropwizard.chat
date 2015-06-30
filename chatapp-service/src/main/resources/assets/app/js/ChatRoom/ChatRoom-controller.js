'use strict';

angular.module('chatapp')
  .controller('ChatRoomController', ['$scope', '$modal', 'Room', '$routeParams',
    function ($scope, $modal, Room, $routeParams) {
        $scope.Room = Room.get({id: $routeParams.idRoom});
        $scope.nickname = $routeParams.nickname;
    }]);
