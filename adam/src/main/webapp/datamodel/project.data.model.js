'use strict';

/**
 * @ngdoc function
 * @name project.data.model:Project
 * @description
 * # Project
 * Factory that works with rest/project web service
 *
 */

(function() {
  angular.module('project.data.model',[])

    .factory("Project", function ProjectFactory($resource){
      return $resource("/adam/rest/project/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
