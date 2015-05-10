'use strict';

/**
 * @ngdoc filter
 * @name measurments.filter:filtermeasurements
 * @description
 * # adamhesQcSingleWell
 * Filter -  not used!
 *
 */

(function() {

  angular.module('measurmentsfilter', [])

    .filter("filtermeasurements", filtermeasurements);

  filtermeasurements.$inject = ["Qc"];
  function filtermeasurements(Qc) {
    return function(resObject, plate) {
      var resObject2;
      if(plate===null){
        return null;
      }

      console.log(JSON.stringify( resObject, null, 4));
      console.log(JSON.stringify( plate.id, null, 4));

      resObject2 = Qc.get({"id":plate.id});

      console.log(JSON.stringify( resObject2, null, 4));
      console.log(JSON.stringify( resObject, null, 4));

      return [{"name":"ok"}];
    }
  }



})();
