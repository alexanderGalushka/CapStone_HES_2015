'use strict';

/* Services */

angular.module('adamServices', ['ngResource'])

    .factory('deleteProject', function() {
        return function (coll,item) {
            var index = coll.indexOf(item);
            coll.splice(index, 1);
        }
    })

    .service('activeProject', function() {
        var activeProject = this;
        activeProject.activeId = "";
    })

    .service('setActiveProject', function() {
        return function (serv,act) {
             serv.activeId = act;
        }
    })
;
/*
 phonecatServices.factory('ProjectRest', ['$resource',
 function($resource){
 return $resource('phones/:phoneId.json', {}, {
 query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
 });
 }]);
 */