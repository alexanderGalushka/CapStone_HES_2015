'use strict';

(function() {

  angular.module('qcwellattrfilter', [])

    .filter("filterQcColor",  function() {
      return function(well, wellColors, valueRange, controltypes) {
        var textColor;
        var bckgColor;
        var bckgColorS;
        var missingControlType = true;

        if(wellColors != null && well != null && valueRange != null) {
          textColor = wellColors.colorText;

          if(valueRange.minvalue != valueRange.maxvalue && parseFloat(well.value) != 777) {
            bckgColorS = (( parseFloat(well.value) - parseFloat(valueRange.minvalue)) / (parseFloat(valueRange.maxvalue) - parseFloat(valueRange.minvalue))) * 100;
          }
          else
            bckgColorS = 0;

          bckgColor = "hsl(" + wellColors.bckgColorH + ",100%, " + bckgColorS + "%)";

          if(bckgColorS > 80)
            textColor = "hsl(" + wellColors.bckgColorH + ",100%, 0%)";

          if(well.ifValid === true) {
            if ((well.controlType == " ") || (well.controlType == '') || (well.controlType == null)) {
              textColor = bckgColor;
              missingControlType = false;
            }
            else {
              for (var i = 0; i < controltypes.length; i++) {
                if (well.controlType === controltypes[i].name) {
                  missingControlType = false;
                  break;
                }
              }
            }
            if (missingControlType) {
              textColor = bckgColor;
              console.log("Not found well control type in array of control types: " + well.controlType);
            }
          }

          return "color:" + textColor + ";background-color:" + bckgColor;

        }else {
          return "color:#FFFFFF;background-color:#FFFFFF";
        }

      }
    })

    .filter("filterQcControl",  function() {
      return function(well, controltypes) {
        var returnVal = '.';

        if(well.ifValid == false)
          returnVal = 'X';
        else {
          if (well.controlType === null || well.controlType === '')
            returnVal = '.';
          else {
            for (var i = 0; i < controltypes.length; i++) {
              if (well.controlType === controltypes[i].name) {
                returnVal = controltypes[i].displayChar;
                break;
              }
            }
          }
        }
        return returnVal;
      }
    })

})();
