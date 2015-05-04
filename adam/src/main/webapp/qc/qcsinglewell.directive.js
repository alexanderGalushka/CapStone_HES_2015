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
          plateid:"@"
        },
        templateUrl: 'qc/qcsinglewell.html',
        controller: QcSingleWellCtrl,
        controllerAs: 'qcsinglewellVm'

      };
    });

  QcSingleWellCtrl.$inject = ["filterQcColorFilter", "filterQcControlFilter", "WellInvalidate"];
  function QcSingleWellCtrl(filterQcColor, filterQcControl, WellInvalidate) {
    var qcsinglewellVm = this;

    qcsinglewellVm.toggleIfValid = toggleIfValid;

    function toggleIfValid(well, projectid, plateid) {
      console.log(JSON.stringify(well, null, 4));
      console.log(projectid);
      console.log(plateid);

      WellInvalidate.save({"projectId": projectid,
        "plateId":plateid,
        "rowNum":well.row,
        "colNum":well.col,
        "ifValid":!well.ifValid});

      well.ifValid = !well.ifValid;
    }


  }

})();
