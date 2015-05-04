'use strict';

/**
 * @ngdoc overview
 * @name adamApp
 * @description
 * # adamApp
 *
 * Main module of the application.
 */
(function() {
  angular
    .module('adamApp', [
      'ngAnimate',
      'ngCookies',
      'ngResource',
      'ngRoute',
      'ngSanitize',
      'smart-table',
      'mgcrea.ngStrap',
      'ngSlider',
      'ngFileUpload',
      // application modules
      'adamServices',
      'navbarAdam',
      'project',
      'projectpanel',
      'project.data.model',
      'plate',
      'plate.data.model',
      'platepanel',
      'collaborator.data.model',
      'plateeditor',
      'resetsel',
      'plotlabels',
      'singlewell',
      'wellstable',
      'wellattrfilter',
      'plateresults',
      'measurmentsfilter',
      "qc.data.model",
      'qc',
      'qcsinglewell',
      'qcwellattrfilter',
      'loadresult',
      'classgridfilter',
      'well.invalidate.data.model'
    ])

    .config(configAdam);

  configAdam.$inject =['$routeProvider','$resourceProvider'];
  function configAdam($routeProvider,$resourceProvider){
    //$resourceProvider.defaults.useXDomain = true;  not supported?

    $routeProvider.
      when('/projects', {
        templateUrl: 'project/project.html',
        controller: 'ProjectsCtrl',
        controllerAs: 'projVm'
      }).
      when('/plates', {
        templateUrl: 'plate/plate.html',
        controller: 'PlateCtrl',
        controllerAs: 'plateVm'
      }).
      when('/plateeditor', {
        templateUrl: 'plateeditor/plateeditor.html',
        controller: 'PlateeditorCtrl',
        controllerAs: 'pleditVm'
      }).
      when('/plateresults', {
        templateUrl: 'plateresult/plateresult.html',
        controller: 'PlateResultsCtrl',
        controllerAs: 'plresVm'
      }).
      when('/qc', {
        templateUrl: 'qc/qc.html',
        controller: 'QcCtrl',
        controllerAs: 'qcVm'
      }).
      when('/dataanalysis', {
                    templateUrl: 'dataanalysis/data_analysis.html',
                    controller: 'DropdownCtrl'
                }).
      otherwise({
        redirectTo: '/projects'
      });
  }

})();
