'use strict';

(function() {
  angular.module('platepanel', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .directive('admahesPlatepanel', function() {
      return {
        restrict: 'E',
        scope: {
          asideind: '='
        },
        controller:"PlatesPanelCtrl",
        controllerAs: 'platepanVm',
        templateUrl: 'plate/plate.panel.html'
      };
    })

    .controller('PlatesPanelCtrl', PlatesPanelCtrl);

  PlatesPanelCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult", "Plate", "Qc"];
  function PlatesPanelCtrl($scope, activeProject, activePlate, activePlateResult, Plate, Qc){

    var platepanVm = this;

    $scope.ActiveProject = activeProject;
    $scope.ActivePlate = activePlate;
    //$scope.ActivePlateResult = activePlateResult;

    platepanVm.setActivePlate = setActivePlate;


    platepanVm.plates = Plate.query();
    platepanVm.platesDisplay = [].concat(platepanVm.plates);

    function setActivePlate (plate){
      var plateres;
      var minValue = 0;
      var maxValue = 0;

      activePlate.plate  = plate;
      activePlate.plate.wellsDisplay = [].concat(activePlate.plate.wells);

      if(plate !== null) {
        plateres = Qc.get({"id": plate.id},function(){
          activePlateResult.plateResult = plateres;
          activePlateResult.plateResult.options = {
            from: 1,
            to: 5,
            floor: true,
            step: 1,
            dimension: "",
            vertical: false,
            scale: [],
            callback: function(value, elt) {
              /*
               for (var i = 0; i < activePlateResult.plateResult.measurements.length; i++) {
               if(activePlateResult.plateResult.measurements[i].timeStamp === activePlateResult.plateResult.options.scale[value-1] &&
               activePlateResult.plateResult.measurements[i].measurementType === activePlateResult.plateResult.activeMeasurement.measurementType) {
               activePlateResult.plateResult.activeMeasurement = activePlateResult.plateResult.measurements[i];

               for (var j = 0; j < activePlateResult.plateResult.measurements[i].wells.length; j++) {
               if(j === 0){
               minValue = parseFloat(activePlateResult.plateResult.measurements[i].wells[0].value);
               maxValue = parseFloat(activePlateResult.plateResult.measurements[i].wells[0].value);
               }else{
               if(minValue > parseFloat(activePlateResult.plateResult.measurements[i].wells[j].value))
               minValue = parseFloat(activePlateResult.plateResult.measurements[i].wells[j].value);
               else if(maxValue < parseFloat(activePlateResult.plateResult.measurements[i].wells[j].value))
               maxValue = parseFloat(activePlateResult.plateResult.measurements[i].wells[j].value);
               }

               }

               activePlateResult.plateResult.valuerange  = {"minvalue":minValue, "maxvalue":maxValue};

               break;
               }
               }*/

            }
          };
          activePlateResult.plateResult.options.to = plateres.timeStamps.length;
          for (var i = 0; i < plateres.timeStamps.length; i++) {
            activePlateResult.plateResult.options.scale.push(plateres.timeStamps[i].value);
          }
          activePlateResult.plateResult.valueslider = plateres.timeStamps[0].value;


          activePlateResult.plateResult.activeMeasurement = plateres.measurements[0];

          for (var i = 0; i < plateres.measurements[0].wells.length; i++) {
            if(i === 0){
              minValue = parseFloat(plateres.measurements[0].wells[0].value);
              maxValue = parseFloat(plateres.measurements[0].wells[0].value);
            }else{
              if(minValue > parseFloat(plateres.measurements[0].wells[i].value))
                minValue = parseFloat(plateres.measurements[0].wells[i].value);
              else if(maxValue < parseFloat(plateres.measurements[0].wells[i].value))
                maxValue = parseFloat(plateres.measurements[0].wells[i].value);
            }

          }
          activePlateResult.plateResult.valuerange = {"minvalue":minValue, "maxvalue":maxValue};

        });
      }
    }

  }
})();
