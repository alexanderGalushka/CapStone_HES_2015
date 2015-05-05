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
          measurementType:"@"
        },
        templateUrl: 'qc/qcsinglewell.html',
        controller: QcSingleWellCtrl,
        controllerAs: 'qcsinglewellVm'

      };
    });

  QcSingleWellCtrl.$inject = ["filterQcColorFilter", "filterQcControlFilter", "WellInvalidate", "transformActiveResult"];
  function QcSingleWellCtrl(filterQcColor, filterQcControl, WellInvalidate, transformActiveResult) {
    var qcsinglewellVm = this;

    qcsinglewellVm.toggleIfValid = toggleIfValid;

    function toggleIfValid(well, projectid, plateid, measurementType) {
      var plateres;

      console.log(JSON.stringify(well, null, 4));
      console.log(projectid);
      console.log(plateid);

      plateres = WellInvalidate.save({"projectId": projectid,
        "plateId":plateid,
        "rowNum":well.row,
        "colNum":well.col,
        "ifValid":!well.ifValid});
      transformActiveResult(plateres);
      //well.ifValid = !well.ifValid;

    }


  }

})();
