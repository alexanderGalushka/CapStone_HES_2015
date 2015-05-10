'use strict';

/**
 * @ngdoc function
 * @name loadactiveresult.factory:loadActiveResult
 * @description
 * # loadActiveResult
 * Load result file from server
 *
 */


(function() {

  angular.module('loadresult', [])

    .factory('loadActiveResult', loadActiveResult);

  loadActiveResult.$inject = ["activePlateResult","transformActiveResult", "Qc"];
  function loadActiveResult(activePlateResult, transformActiveResult, Qc) {
    return function (plateid){
      var plateres;

      // call web service
      plateres = Qc.get({"id": plateid},function(){
          /* load result into activePlateResult service for sharing between pages */
          transformActiveResult(plateres);

      }, function(error) {
        /*  web service threw error */
        console.log(JSON.stringify(error, null, 4));
        activePlateResult.plateResult = null;
        activePlateResult.resultExists = false;
      });
    }

  }

})();
