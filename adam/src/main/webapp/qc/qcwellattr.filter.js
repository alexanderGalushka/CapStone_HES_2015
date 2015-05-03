'use strict';

(function() {

  angular.module('qcwellattrfilter', [])

    .filter("filterQcColor",  function() {
      return function(well, wellColors, valueRange) {
        var textColor;
        var bckgColor;
        var bckgColorS;

        if(wellColors != null && well != null && valueRange != null) {
          textColor = wellColors.colorText;

          if(valueRange.minvalue != valueRange.maxvalue) {
            bckgColorS = (( parseFloat(well.value) - parseFloat(valueRange.minvalue)) / (parseFloat(valueRange.maxvalue) - parseFloat(valueRange.minvalue))) * 100;
          }
          else
            bckgColorS = 0;

          bckgColor = "hsl(" + wellColors.bckgColorH + ",100%, " + bckgColorS + "%)";

          if(bckgColorS > 80)
            textColor = "hsl(" + wellColors.bckgColorH + ",100%, 0%)";

          if((well.ifValid === "false") && (well.controlType === "" || well.controlType === null) )
            textColor = bckgColor;

          return "color:" + textColor + ";background-color:" + bckgColor;

        }else {
          return "color:#FFFFFF;background-color:#FFFFFF";
        }

      }
    })

    .filter("filterQcControl",  function() {
      return function(well, controltypes) {
        var returnVal = '.';

        if(well.ifValid === "false")
          returnVal = 'X';
        else {
          if (well.controlType === null || well.controlType === '')
            returnVal = '.';
          else {
            for (var i = 0; i < controltypes.length; i++) {
              if (well.controlType === controltypes[i].name)
                returnVal = controltypes[i].displayChar;
            }
          }
        }
        return returnVal;
      }
    })

})();
