'use strict';

// Declare app level module which depends on views, and components
angular.module('hesAdam', [
    'ngRoute',
    'smart-table',
    'mgcrea.ngStrap',
    'ngAnimate',
    'ngSanitize',
    'projects',
    'plates',
    'plateeditor',
    'version',
    'adamServices'
])


    .config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/projects', {
                    templateUrl: 'projects/projects.html',
                    controller: 'ProjectsCtrl'
                }).
                when('/plates', {
                    templateUrl: 'plates/plates.html',
                    controller: 'PlatesCtrl'
                }).
                when('/plateeditor', {
                    templateUrl: 'plateeditor/plateeditor.html',
                    controller: 'PlateeditorCtrl'
                }).
                when('/dataanalysis', {
                    templateUrl: 'dataanalysis/data_analysis.html',
                    controller: 'DataAnalysisController'
                }).
                otherwise({
                    redirectTo: '/projects'
                });
        }]);