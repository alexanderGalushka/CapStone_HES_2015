'use strict';

/**
 * @ngdoc directive
 * @name navbar.directive:admahesNavbar
 * @description
 * # admahesNavbar
 * Directive for top window application navigation bar
 *
 */

(function() {
  angular.module('navbarAdam',[])

    .directive('admahesNavbar', function() {
      return {
        restrict: 'E',
        scope: {

        },
        templateUrl: 'layout/navbaradam.html'
      };
    })

})();
