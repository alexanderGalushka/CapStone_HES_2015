'use strict';

/**
 * @ngdoc function
 * @name well.invalidate.data.model:WellInvalidate
 * @description
 * # WellInvalidate
 * Factory that works with rest/qc/single_well_validation web service
 * WellInvalidate object is only used to invalidate well in qc page
 * Only PUT id implemented on server and return updated qc object (result set)
 *
 */

(function() {
  angular.module('well.invalidate.data.model',[])

    .factory("WellInvalidate", function QcFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/qc/single_well_validation", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
