'use strict';

/**
 * @ngdoc filter
 * @name qcwellattr.filter:filterQcColor
 * @description
 * # filterQcColor
 * Filters for single well in plate map in qc page
 *
 */

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

          // '777' is returned from server for invalid well, no idea why is that necessary
          // calculate well color using linear interpolation
          if(valueRange.minvalue != valueRange.maxvalue && parseFloat(well.value) != 777) {
            bckgColorS = (( parseFloat(well.value) - parseFloat(valueRange.minvalue)) / (parseFloat(valueRange.maxvalue) - parseFloat(valueRange.minvalue))) * 100;
          }
          else
            bckgColorS = 0;

          bckgColor = "hsl(" + wellColors.bckgColorH + ",100%, " + bckgColorS + "%)";

          // if it's too bright switch tect color to background color
          if(bckgColorS > 80)
            textColor = "hsl(" + wellColors.bckgColorH + ",100%, 0%)";

          // For valid well check whether is control well and check if there is display char for control type
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

            // It should not happpen but..
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

  /**
   * @ngdoc filter
   * @name qcwellattr.filter:filterQcControl
   * @description
   * # filterQcControl
   * Filter that display control type
   *
   */

    .filter("filterQcControl",  function() {
      return function(well, controltypes) {
        var returnVal = '.';

        // if ifValid is false and that well is invalid so display  'X'
        if(well.ifValid == false)
          returnVal = 'X';
        else {
          // if there is nothing to display set '.' so labels can be aligned properly
          if (well.controlType === null || well.controlType === '')
            returnVal = '.';
          else {
            // if well is control well display character used to display that control type
            // if it is not found '.' wil lbe used so labels can be aligned properly
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
