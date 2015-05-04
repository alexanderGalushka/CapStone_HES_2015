'use strict';

(function() {
  angular.module('projectpanel', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .directive('admahesProjectpanel', function() {
      return {
        restrict: 'E',
        scope: {
          asideind: '='
        },
        controller:"ProjectsPanelCtrl",
        controllerAs: 'projpanVm',
        templateUrl: 'project/project.panel.html'
      };
    })

    .controller('ProjectsPanelCtrl', ProjectsPanelCtrl);

  ProjectsPanelCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult", "Project"];
  function ProjectsPanelCtrl($scope, activeProject, activePlate, activePlateResult, Project){

    var projpanVm = this;

    $scope.ActiveProject = activeProject;
    $scope.ActivePlate = activePlate;
    $scope.activePlateResult = activePlateResult;

    projpanVm.setActiveProject = setActiveProject;


    projpanVm.projects = Project.query();
    projpanVm.projectsDisplay = [].concat(projpanVm.projects);

    function setActiveProject (proj){
      $scope.ActiveProject.project = proj;
      $scope.ActivePlate.plate  = null;
      $scope.activePlateResult.plateResult  = null;
      activePlateResult.resultExists = false;
    }

  }
})();
