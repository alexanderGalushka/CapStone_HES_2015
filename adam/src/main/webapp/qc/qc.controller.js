'use strict';

(function() {


  angular.module('qc', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap','ngSlider'])

    .controller('QcCtrl',QcCtrl)


  QcCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult", "rangeFilter"];

  function QcCtrl($scope, activeProject, activePlate, activePlateResult, range) {
    var qcVm = this;

    $scope.ActiveProject = activeProject.project;
    $scope.ActivePlate = activePlate;
    $scope.ActivePlateResult = activePlateResult;
    $scope.ActivePlateResult.plateResult.valueslider = 1;

    qcVm.boxsz = "35";
    qcVm.wellcollors = {bckgColorH:"0",colorText:"#FFFF00"};

    qcVm.setActiveMeasurement = setActiveMeasurement;

    function setActiveMeasurement(type, sliderIndex, plateres) {
      var minValue = 0;
      var maxValue = 0;

      console.log(type);
      console.log(sliderIndex);
      for (var i = 0; i < plateres.measurements.length; i++) {
        if(plateres.measurements[i].measurementType === type &&
          plateres.measurements[i].timeStamp === plateres.options.scale[sliderIndex - 1]) {
          plateres.activeMeasurement = plateres.measurements[i];
          console.log("Found IT!");

          for (var j = 0; j < plateres.measurements[i].wells.length; j++) {
            if(j === 0){
              minValue = parseFloat(plateres.measurements[i].wells[0].value);
              maxValue = parseFloat(plateres.measurements[i].wells[0].value);
            }else{
              if(minValue > parseFloat(plateres.measurements[i].wells[j].value))
                minValue = parseFloat(plateres.measurements[i].wells[j].value);
              else if(maxValue < parseFloat(plateres.measurements[i].wells[j].value))
                maxValue = parseFloat(plateres.measurements[i].wells[j].value);
            }

          }

          plateres.valuerange  = {"minvalue":minValue, "maxvalue":maxValue};
          break;
        }
      }



    }
  }

})();
