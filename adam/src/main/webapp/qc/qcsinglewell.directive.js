'use strict';

/**
 * @ngdoc directive
 * @name qcsinglewell.directive:adamhesQcSingleWell
 * @description
 * # adamhesQcSingleWell
 * Directive for single well in plate map in qc page
 *
 */

(function() {
  angular.module('qcsinglewell', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .directive('adamhesQcSingleWell', function() {
      return {
        restrict: 'E',
        scope: {
          well: '=',
          boxsize:"@",
          controltypes:"=",
          wellcollors:"=",
          valuerange:"=",
          projectid:"@",
          plateid:"@",
          measurementtype:"@",
          sliderindex:"@"
        },
        templateUrl: 'qc/qcsinglewell.html',
        controller: QcSingleWellCtrl,
        controllerAs: 'qcsinglewellVm'

      };
    });

  QcSingleWellCtrl.$inject = ["filterQcColorFilter", "filterQcControlFilter", "WellInvalidate", "transformActiveResult", "setActiveMeasurement"];
  function QcSingleWellCtrl(filterQcColor, filterQcControl, WellInvalidate, transformActiveResult, setActiveMeasurement) {
    var qcsinglewellVm = this;

    qcsinglewellVm.toggleIfValid = toggleIfValid;

    /**
     * @ngdoc function
     * @name toggleIfValid
     * @description
     * Switch ifValid field in plate result
     *
     */
    function toggleIfValid(well, projectid, plateid, measurementtype, sliderindex) {
      var plateres;

      console.log(JSON.stringify(well, null, 4));
      console.log(projectid);
      console.log(plateid);
      console.log(measurementtype);
      console.log(sliderindex);

      plateres = WellInvalidate.save({"projectId": projectid,
        "plateId":plateid,
        "rowNum":well.row,
        "colNum":well.col,
        "ifValid":!well.ifValid},function(){
        /* load returned result into activePlateResult service for sharing between pages */
        transformActiveResult(plateres);
        setActiveMeasurement(measurementtype, sliderindex, plateres);
      }, function(error) {
        /*  web service threw error */
        console.log(JSON.stringify(error, null, 4));
      });

    }


  }

})();
