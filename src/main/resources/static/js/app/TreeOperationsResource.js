'use strict'
var module = angular.module('treeoperations.controllers', []);
module.controller("TreeOperationsResource", ["$scope", "TreeOperationsService",
    function($scope, TreeOperationsService) {
//        $scope.userDto = {
//            userId: null,
//            userName: null,
//            skillDtos: []
//        };
        $scope.descendantId = null;
        TreeOperationsService.getDescendants(2).then(function(value) {
            console.log(value.data);
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });
        $scope.getDescendants = function() {
            TreeOperationsService.getDescendants($scope.descendantId).then(function(value) {
                console.log("works");
                $scope.allDescendants = value.data;
                $scope.descendantId = null;
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
    }
]);