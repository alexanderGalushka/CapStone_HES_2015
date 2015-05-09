'use strict';

(function() {

  angular.module('transformresult', [])

    .factory('transformActiveResult', transformActiveResult);

  transformActiveResult.$inject = ["activePlateResult"];
  function transformActiveResult(activePlateResult) {
    return function (plateres){
      var minValue = 0;
      var maxValue = 0;
      var foundFirstValidWell = false;


      if(plateres != null && plateres.timeStamps != null && plateres.timeStamps.length != 0 &&
        plateres.measurements != null && plateres.measurements.length != 0 && plateres.measurements[0].wells != null){

        /* load result into activePlateResult service for sharing between pages */
        activePlateResult.plateResult = plateres;

        /* load parameters for slider on qc page */
        activePlateResult.plateResult.options = {
          from: 1,
          to: 5,
          floor: true,
          step: 1,
          dimension: "",
          vertical: false,
          scale: [],
          callback: function(value, elt) {
          }
        };
        activePlateResult.plateResult.options.to = plateres.timeStamps.length;
        for (var i = 0; i < plateres.timeStamps.length; i++) {
          activePlateResult.plateResult.options.scale.push(plateres.timeStamps[i].value);
        }

        /* set activeMeasurement to the first member of array for qc page */
        activePlateResult.plateResult.activeMeasurement = plateres.measurements[0];

        /* calculate maximum and minimum for values in activeMeasurement */
        for (var i = 0; i < plateres.measurements[0].wells.length; i++) {
          if(parseFloat(plateres.measurements[0].wells[i].value) != 777) {
            if (!foundFirstValidWell) {
              minValue = parseFloat(plateres.measurements[0].wells[i].value);
              maxValue = parseFloat(plateres.measurements[0].wells[i].value);
              foundFirstValidWell = true;
            } else {

              if (minValue > parseFloat(plateres.measurements[0].wells[i].value))
                minValue = parseFloat(plateres.measurements[0].wells[i].value);
              else if (maxValue < parseFloat(plateres.measurements[0].wells[i].value))
                maxValue = parseFloat(plateres.measurements[0].wells[i].value);

            }
          }
        }
        activePlateResult.plateResult.valuerange = {"minvalue":minValue, "maxvalue":maxValue};
        activePlateResult.plateResult.valueslider = 1;
        activePlateResult.plateResult.displayMeasurements = [].concat(activePlateResult.plateResult.measurements);
        activePlateResult.plateResult.DisplayMeasurementTypes = [].concat(activePlateResult.plateResultmeasurementTypes);

        /* set indicator for qc page */
                activePlateResult.resultExists = true;}
      else{
        console.log("unfinished plateResult returned" + JSON.stringify(plateres, null, 4));
        activePlateResult.plateResult = null;
        activePlateResult.resultExists = false;
      }
    }
  }



})();
