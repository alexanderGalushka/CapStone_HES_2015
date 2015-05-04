'use strict';

(function() {
  angular.module('well.invalidate.data.model',[])

    .factory("WellInvalidate", function QcFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/qc/single_well_validation", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
