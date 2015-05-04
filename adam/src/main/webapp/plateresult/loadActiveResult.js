'use strict';

(function() {

  angular.module('loadresult', [])

    .factory('loadActiveResult', loadActiveResult);

  loadActiveResult.$inject = ["activePlateResult", "Qc"];
  function loadActiveResult(activePlateResult, Qc) {
    return function (plateid){
      var plateres;
      var minValue = 0;
      var maxValue = 0;
      var foundFirstValidWell = false;

      plateres = Qc.get({"id": plateid},function(){
        activePlateResult.plateResult = plateres;
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
        activePlateResult.plateResult.activeMeasurement = plateres.measurements[0];

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
        activePlateResult.resultExists = true;
      }, function(error) {
        console.log(JSON.stringify(error, null, 4));
        activePlateResult.plateResult = null;
        activePlateResult.resultExists = false;
      });
    }

  }

})();
