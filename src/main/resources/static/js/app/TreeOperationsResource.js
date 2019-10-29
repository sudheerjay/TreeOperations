'use strict'
var module = angular.module('treeoperations.resources', []);
module.controller("TreeOperationsResource", ["$scope", "TreeOperationsService",
    function($scope, TreeOperationsService) {

        $scope.descendantId = null;
        
        $scope.getDescendants = function() {
            TreeOperationsService.getDescendants($scope.descendantId).then(function(value) {
                $scope.allDescendants = value.data;
                $scope.descendantId = null;
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
        
        $scope.changeParent = function() {
            TreeOperationsService.changeParent($scope.nodeId,$scope.parentId).then(function(value) {
                console.log("works");
                $scope.changeInParent = "Parent node has been changed for nodeId: "+$scope.nodeId;
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
    }
]);