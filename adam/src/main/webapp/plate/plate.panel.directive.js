'use strict';

/**
 * @ngdoc directive
 * @name plate.panel.directive:admahesPlatepanel
 * @description
 * # admahesPlatepanel
 * Panel directive used in other pages to display plates
 *
 */

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

  PlatesPanelCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult", "Plate", "loadActiveResult" ];
  function PlatesPanelCtrl($scope, activeProject, activePlate, activePlateResult, Plate, loadActiveResult){
    var platepanVm = this;

    $scope.ActiveProject = activeProject;
    $scope.ActivePlate = activePlate;
    //$scope.ActivePlateResult = activePlateResult;

    platepanVm.setActivePlate = setActivePlate;

    platepanVm.plates = Plate.query();
    platepanVm.platesDisplay = [].concat(platepanVm.plates);


    function setActivePlate (plate){
      activePlate.plate  = plate;
      activePlate.plate.wellsDisplay = [].concat(activePlate.plate.wells);
      activePlate.plate.wellLabelsAll = [].concat(activePlate.plate.wellLabels);
      activePlate.plate.wellLabelsAll.push({name:"controlType"});
      if(activePlate.plate.uniquelabelvalues === null){
        activePlate.plate.uniquelabelvalues = [];
        activePlate.plate.uniquelabelvaluesdisplay = [];
        activePlate.plate.uniquelabelvaluescolors = [];
      }

      if(plate !== null) {
        loadActiveResult(plate.id);
      }else
        activePlateResult = null;
    }

  }
})();
