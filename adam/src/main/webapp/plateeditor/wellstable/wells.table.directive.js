'use strict';

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

    function toogleRowSelection(well, labels, wellArray, dropwellgroup){
      dropwellgroup.checked = "true";

      var wellSelected;
      var numSameWellsSelected = 0;

      for (var i = 0; i < wellArray.length; i++) {
        wellSelected = "true";
        for (var j = 0; j < labels.length && wellSelected; j++) {
          //if (wellArray[i][labels[j].name] != null && well[labels[j].name] != wellArray[i][labels[j].name]) {
          if (well[labels[j].name] != wellArray[i][labels[j].name]) {
            wellSelected = false;
          }
          //console.log("label " + cond.labels[i].name);
        }
        if(well.controltype != wellArray[i].controltype)
          wellSelected = false;

        if(wellSelected) {
          if (wellArray[i].isSelected)
            numSameWellsSelected++;
        }
      }

      if((well.isSelected && numSameWellsSelected == 1)||(!well.isSelected && numSameWellsSelected == 0)) {
        for (var i = 0; i < wellArray.length; i++) {
          wellSelected = "true";
          for (var j = 0; j < labels.length && wellSelected; j++) {
            //if (wellArray[i][labels[j].name] != null && well[labels[j].name] != wellArray[i][labels[j].name]) {
            if (well[labels[j].name] != wellArray[i][labels[j].name]) {
              wellSelected = false;
            }
            //console.log("label " + cond.labels[i].name);
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
