'use strict';

/**
 * @ngdoc directive
 * @name singlewell.directive:adamhesSinglewell
 * @description
 * # adamhesSinglewell
 * Directive for single well
 *
 */

(function() {
  angular.module('singlewell', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .directive('adamhesSinglewell', function() {
      return {
        restrict: 'E',
        scope: {
          well: '=',
          boxsize:"@",
          tooltipdelay:"@",
          filterarg:"=",
          dropgroup:"=",
          "labels":"=",
          "rows":"=",
          "controltypes":"=",
          "plate":"="
        },
        templateUrl: 'plateeditor/singlewell/singlewell.html',
        controller: SingleWellCtrl,
        controllerAs: 'singlewellVm'

      };
    });

  SingleWellCtrl.$inject = ["filterColorFilter", "filterBorder3Filter",  "filterHoverFilter", "filterControlFilter", "Plate"];
  function SingleWellCtrl(filterColor, filterBorder3, filterHover, filterControl, Plate) {
    var singlewellVm = this;

    singlewellVm.updateWell = updateWell;
    singlewellVm.saveChangesWell = saveChangesWell;


    function updateWell(well, cond, rows, plate) {
      if(cond.plotLabelName != null && well != null && cond.copyLabel != null) {
        well.color = cond.copyLabel.color;
        well[cond.plotLabelName] = cond.copyLabel.name;
        if(well.condSelected){
          for (var i = 0; i < rows.length; i++) {
            if(rows[i].condSelected) {
              rows[i].color = cond.copyLabel.color;
              rows[i][cond.plotLabelName] = cond.copyLabel.name;
            }
          }
        }
      }
      Plate.update({"id":plate.id},plate);
    }

    function saveChangesWell(plate) {
      Plate.update({"id":plate.id},plate);
    }


  }

})();
