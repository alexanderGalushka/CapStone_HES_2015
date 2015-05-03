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
          valuerange:"="
        },
        templateUrl: 'qc/qcsinglewell.html',
        controller: QcSingleWellCtrl,
        controllerAs: 'qcsinglewellVm'

      };
    });

  QcSingleWellCtrl.$inject = ["filterQcColorFilter", "filterQcControlFilter"];
  function QcSingleWellCtrl(filterQcColor, filterQcControl) {
    var qcsinglewellVm = this;



  }

})();
