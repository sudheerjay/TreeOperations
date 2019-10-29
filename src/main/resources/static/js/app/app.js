'use strict'
var treeoperationsApp = angular.module('treeoperations', ['ui.bootstrap', 'treeoperations.resources',
    'treeoperations.services'
]);
treeoperationsApp.constant("CONSTANTS", {
    getDescendantsUrl: "/tree/node/getDescendants",
    changeParentUrl: "/tree/node/changeParent"
});