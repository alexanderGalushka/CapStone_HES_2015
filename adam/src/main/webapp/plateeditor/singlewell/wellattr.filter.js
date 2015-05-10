'use strict';

/**
 * @ngdoc filter
 * @name singlewell.directive:filterBorder3
 * @description
 * # adamhesSinglewell
 * Set of filters used for drawing single well
 *
 */

(function() {

  angular.module('wellattrfilter', [])

    .filter("filterBorder3",  function() {
      return function(well, cond) {
        var wellSelected = false;


        if(well.condSelected){
          return "border:2px dashed red;";
        }else{
          return "border:2px solid black;";
        }

      }
    })

    .filter("filterColor",  function() {
      return function(well, cond) {
        var textColor;

        if(cond != null && well != null) {
          var wellLabelValue = well[cond.plotLabelName];
          if (wellLabelValue != null && wellLabelValue != '') {
            var colorForWell = cond.labelValueColors[wellLabelValue];
            if (colorForWell != null) {
              well.color = colorForWell;
              if(well.controlType === "" || well.controlType === null)
                textColor = colorForWell;
              else
                textColor = "black";
              return "color:" + textColor + ";background-color:" + colorForWell;
            }
            else {
              if (well.controlType === "" || well.controlType === null)
                return "color:#FF0000;background-color:#FF0000";
              else
                return "color:black;background-color:#FF0000";
              well.color = "#FF0000";
            }
          } else {
            well.color = "#FFFFFF";
            if (well.controlType === "" || well.controlType === null)
              return "color:#FFFFFF;background-color:#FFFFFF";
            else
              return "color:black;background-color:#FFFFFF";
          }
        }else {
          well.color = "#FFFFFF";
          return "color:#FFFFFF;background-color:#FFFFFF";
        }

      }
    })

    .filter("filterHover",  function() {
      return function(well, labels) {
        var returnVal = '';

        if(labels != null && well != null) {
          for (var i = 0; i < labels.length; i++) {
            if(well[labels[i].name] != null)
              returnVal += ' '  + well[labels[i].name];
            else
              returnVal += ' '  + 'n/a';
          }
          returnVal += ' ' + well["controlType"];
        }

        return returnVal



      }
    })

    .filter("filterControl",  function() {
      return function(well, controltypes) {
        var returnVal = '.';

        if(well.controlType === null || well.controlType === '' )
          returnVal = '.';
        else {
          for(var i = 0; i < controltypes.length; i++) {
            if(well.controlType === controltypes[i].name)
              returnVal = controltypes[i].displayChar;
          }
        }

        return returnVal;
      }
    })

    .filter('range', function() {
      return function (input, min, max) {
        min = parseInt(min);
        max = parseInt(max);
        for (var i = min; i <= max; i++)
          input.push(i);
        return input;
      }


    })

})();
