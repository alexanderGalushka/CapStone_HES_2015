'use strict';

(function() {

  angular.module('setactivemeasurement', [])

    .factory('setActiveMeasurement', setActiveMeasurement);

  setActiveMeasurement.$inject = [];
  function setActiveMeasurement() {
    return function (type, sliderIndex, plateres) {
      var minValue = 0;
      var maxValue = 0;
      var foundFirstValidWell = false;

      if(sliderIndex === null){
        sliderIndex = 1;
      }
      console.log(type);
      console.log(sliderIndex);
      console.log(plateres);


      for (var i = 0; i < plateres.measurements.length; i++) {
        if(plateres.measurements[i].measurementType === type &&
          plateres.measurements[i].timeStamp === plateres.options.scale[sliderIndex - 1]) {
          /* Set active measurement to display in qc well map */
          plateres.activeMeasurement = plateres.measurements[i];
          plateres.activeMeasurementType = type;
          //plateres.activeMeasurementSliderIndex = sliderIndex;
          plateres.valueslider = sliderIndex;

          console.log("Found IT!");

          for (var j = 0; j < plateres.measurements[i].wells.length; j++) {
            if(parseFloat(plateres.measurements[i].wells[j].value) != 777) {
              if (!foundFirstValidWell) {
                minValue = parseFloat(plateres.measurements[i].wells[j].value);
                maxValue = parseFloat(plateres.measurements[i].wells[j].value);
                foundFirstValidWell = true;
              } else {
                if (minValue > parseFloat(plateres.measurements[i].wells[j].value))
                  minValue = parseFloat(plateres.measurements[i].wells[j].value);
                else if (maxValue < parseFloat(plateres.measurements[i].wells[j].value))
                  maxValue = parseFloat(plateres.measurements[i].wells[j].value);
              }

            }
          }

          plateres.valuerange  = {"minvalue":minValue, "maxvalue":maxValue};
          break;
        }
      }
    }
  }



})();
