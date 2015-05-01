'use strict';

(function() {
  angular.module('plate.data.model',[])

    .factory("Plate", function PlateFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/plate/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
