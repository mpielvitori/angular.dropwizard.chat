'use strict';

angular.module('chatapp')
  .controller('ChatRoomController', ['$scope', '$modal', 'resolvedRoom',
    function ($scope, $modal, resolvedRoom) {
        $scope.Room = resolvedRoom;
    }]);
