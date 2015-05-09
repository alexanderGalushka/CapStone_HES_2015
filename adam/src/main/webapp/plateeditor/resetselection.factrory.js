'use strict';

/**
 * @ngdoc function
 * @name resetselection.factory:resetSelection
 * @description
 * # resetSelection
 * Factory to reset all selection, in plate map and table
 * the same well array is used for both displays
 *
 */

(function() {

    angular.module('resetsel', [])

        .factory('resetSelection', function() {
            return function (wells, dropwellstatus){
                //$scope.filterPlateEditor.wellgroup = [];
                for (var i = 0; i < wells.length; i++) {
                    wells[i].isSelected = false;
                    wells[i].condSelected = false;
                }
                dropwellstatus.checked = "true";
            }

        });

})();
