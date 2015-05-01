'use strict';

(function() {
  angular.module('qc.data.model',[])

    .factory("Qc", function QcFactory($resource){
      return $resource("http://54.149.197.234/adam/rest/qc/plate/:id", {}, {
          'update': {method: 'PUT'}
        }
      );
    });

})();
