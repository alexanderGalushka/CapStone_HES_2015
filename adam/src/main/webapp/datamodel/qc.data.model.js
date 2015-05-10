'use strict';

/**
 * @ngdoc function
 * @name qc.data.model:Qc
 * @description
 * # Qc
 * Factory that works with rest/qc/plate web service
 * Qc represent result set uploaded from file
 *
 */

(function() {
  angular.module('qc.data.model',[])

    .factory("Qc", function QcFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/qc/plate/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
