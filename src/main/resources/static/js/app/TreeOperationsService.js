'use strict'
angular.module('treeoperations.services', []).factory('TreeOperationsService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.getDescendants = function(nodeId) {
        var url = CONSTANTS.getDescendantsUrl +'?nodeId='+ nodeId;
        return $http.get(url);
    }
    service.changeParent = function(nodeId,parentId) {
        return $http.put(CONSTANTS.changeParentUrl +'?nodeId='+nodeId+'&parentId='+parentId);
    }
    return service;
}]);