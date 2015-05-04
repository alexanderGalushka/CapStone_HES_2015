'use strict';

(function() {


  angular.module('qc', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap','ngSlider'])

    .controller('QcCtrl',QcCtrl)


  QcCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult", "rangeFilter", "classgridFilter", "wellBoxSize" ];

  function QcCtrl($scope, activeProject, activePlate, activePlateResult, range, classgrid, wellBoxSize) {
    var qcVm = this;

    $scope.ActiveProject = activeProject.project;
    $scope.ActivePlate = activePlate;
    $scope.ActivePlateResult = activePlateResult;
    $scope.WellBoxSize = wellBoxSize;
    qcVm.aside = false;

    if($scope.ActivePlateResult != null && $scope.ActivePlateResult.plateResult != null && $scope.ActivePlateResult.plateResult != "" &&
      $scope.ActivePlateResult.resultExists === true) {
      $scope.ActivePlateResult.plateResult.valueslider = 1;
      $scope.ActivePlateResult.resultExists = true;
    }
    else
      $scope.ActivePlateResult.resultExists = false;

    //qcVm.boxsz = "35";
    qcVm.wellcollors = {bckgColorH:"0",colorText:"#FFFF00"};

    qcVm.setActiveMeasurement = setActiveMeasurement;

    qcVm.boxsizerange = {
      from: 25,
      to: 100,
      floor: true,
      step: 1,
      dimension: " px",
      vertical: false,
      callback: function(value, elt) {
        //console.log(value);
      }
    };

    function setActiveMeasurement(type, sliderIndex, plateres) {
      var minValue = 0;
      var maxValue = 0;
      var foundFirstValidWell = false;

      if(sliderIndex === null){
        sliderIndex = 1;
      }
      console.log(type);
      console.log(sliderIndex);


      for (var i = 0; i < plateres.measurements.length; i++) {
        if(plateres.measurements[i].measurementType === type &&
          plateres.measurements[i].timeStamp === plateres.options.scale[sliderIndex - 1]) {
          plateres.activeMeasurement = plateres.measurements[i];
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
