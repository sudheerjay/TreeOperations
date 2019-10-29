'use strict'
angular.module('treeoperations.services', []).factory('TreeOperationsService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.getDescendants = function(nodeId) {
        var url = CONSTANTS.getDescendantsUrl +'?nodeId='+ nodeId;
        return $http.get(url);
    }
    service.getAllUsers = function() {
        return $http.get(CONSTANTS.getAllUsers);
    }
    service.saveUser = function(userDto) {
        return $http.post(CONSTANTS.saveUser, userDto);
    }
    return service;
}]);