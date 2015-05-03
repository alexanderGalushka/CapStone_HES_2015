'use strict';

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
