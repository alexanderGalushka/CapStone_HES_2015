'use strict';

(function() {


  angular.module('plateresults', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .controller('PlateResultsCtrl',PlateResultsCtrl)


  PlateResultsCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult" ];

  function PlateResultsCtrl($scope, activeProject, activePlate, activePlateResult) {
    var plresVm = this;

    $scope.ActiveProject = activeProject.project;
    $scope.ActivePlate = activePlate;
    $scope.ActivePlateResult = activePlateResult;

  }

})();
