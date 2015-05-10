'use strict';
/**
 * @ngdoc function
 * @name collaborator.data.model:Collaborator
 * @description
 * # Collaborator
 * Factory that works with rest/user web service
 *
 */

(function() {
  angular.module('collaborator.data.model',[])

    .factory("Collaborator", function PlateFactory($resource){
      return $resource("/adam/rest/user/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
