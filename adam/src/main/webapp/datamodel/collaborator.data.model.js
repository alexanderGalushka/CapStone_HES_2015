'use strict';

(function() {
  angular.module('collaborator.data.model',[])

    .factory("Collaborator", function PlateFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/user/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
