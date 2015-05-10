'use strict';

/**
 * @ngdoc directive
 * @name wells.table.directive:adamhesWellstable
 * @description
 * # adamhesWellstable
 * Directive to wells table
 *
 */

(function() {
  angular.module('wellstable', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .directive('adamhesWellstable', function() {
      return {
        restrict: 'E',
        scope: {
          rowsonedimw:'=',
          rowsonedimdisplayw:'=',
          labelsdisplayw:"=",
          dropwellgroup:"="
        },
        templateUrl: 'plateeditor/wellstable/wells.table.html',
        controller:WellsTableCtrl,
        controllerAs: 'wellstabVm'
      };
    });

  WellsTableCtrl.$inject = ['resetSelection'];
  function WellsTableCtrl(resetSelection){

    var wellstabVm = this;

    wellstabVm.resetSelection = resetSelection;
    wellstabVm.toogleRowSelection = toogleRowSelection;
    wellstabVm.updateWellUniqLabelValues = updateWellUniqLabelValues;

    /**
     * @ngdoc function
     * @name toogleRowSelection
     * @description
     * Switch row selection
     *
     */

    function toogleRowSelection(well, labels, wellArray, dropwellgroup){
      dropwellgroup.checked = "true";

      var wellSelected;             // indicator whether well have the same values as clicked well
      var numSameWellsSelected = 0; // how many already selected wells with the same values

      /* find all wells with the same values in fields and that are selected */
      for (var i = 0; i < wellArray.length; i++) {
        wellSelected = "true";
        for (var j = 0; j < labels.length && wellSelected; j++) {
          //if (wellArray[i][labels[j].name] != null && well[labels[j].name] != wellArray[i][labels[j].name]) {
          if (well[labels[j].name] != wellArray[i][labels[j].name]) {
            wellSelected = false;
          }
        }
        if(well.controltype != wellArray[i].controltype)
          wellSelected = false;

        if(wellSelected) {
          if (wellArray[i].isSelected)
            numSameWellsSelected++;
        }
      }

      /* if well is selected or unselected there is work to do only if there is none of the same already selected */
      if((well.isSelected && numSameWellsSelected == 1)||(!well.isSelected && numSameWellsSelected == 0)) {
        for (var i = 0; i < wellArray.length; i++) {
          wellSelected = "true";
          for (var j = 0; j < labels.length && wellSelected; j++) {
            //if (wellArray[i][labels[j].name] != null && well[labels[j].name] != wellArray[i][labels[j].name]) {
            if (well[labels[j].name] != wellArray[i][labels[j].name]) {
              wellSelected = false;
            }
          }
          if (well.controltype != wellArray[i].controltype)
            wellSelected = false;

          if (wellSelected) {
            if (well.isSelected) {
              wellArray[i].condSelected = true;
            } else
              wellArray[i].condSelected = false;
          }
        }
      }
    }


    /**
     * @ngdoc function
     * @name updateWellUniqLabelValues
     * @description
     * Update color for label - it is disabled to simplify user experience
     *
     */

    function updateWellUniqLabelValues(well, newColor){
      console.log(JSON.stringify(well, null, 4));
      console.log(newColor);

      /*            $scope.uniqueLabelValuesColors[well[$scope.filterPlateEditor.plotLabelName]] = newColor;
       for (var i = 0; i < $scope.uniqueLabelValues.length; i++) {
       if($scope.uniqueLabelValues[i].name === well[$scope.filterPlateEditor.plotLabelName]){
       $scope.uniqueLabelValues[i].color = newColor;
       }
       }
       */
    }

  }
})();
