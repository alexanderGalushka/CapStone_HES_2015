'use strict';

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
          measurementType:"@",
          sliderIndex:"@"
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

    function toggleIfValid(well, projectid, plateid, measurementType, sliderIndex) {
      var plateres;

      console.log(JSON.stringify(well, null, 4));
      console.log(projectid);
      console.log(plateid);
      console.log(measurementType);
      console.log(sliderIndex);

      plateres = WellInvalidate.save({"projectId": projectid,
        "plateId":plateid,
        "rowNum":well.row,
        "colNum":well.col,
        "ifValid":!well.ifValid},function(){
        /* load returned result into activePlateResult service for sharing between pages */
        transformActiveResult(plateres);
        setActiveMeasurement(measurementType, sliderIndex, plateres);
      }, function(error) {
        /*  web service threw error */
        console.log(JSON.stringify(error, null, 4));
      });


      //well.ifValid = !well.ifValid;

    }


  }

})();
