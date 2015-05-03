'use strict';

(function() {
  angular.module('project.data.model',[])

    .factory("Project", function ProjectFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/project/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
