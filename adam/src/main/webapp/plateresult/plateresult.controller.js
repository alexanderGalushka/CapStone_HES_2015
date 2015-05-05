'use strict';

(function() {


  angular.module('plateresults', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap', 'ngFileUpload'])

    .controller('PlateResultsCtrl',PlateResultsCtrl)


  PlateResultsCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult" , "Upload", "classgridFilter", "Qc", "transformActiveResult" ];

  function PlateResultsCtrl($scope, activeProject, activePlate, activePlateResult, Upload, classgrid, Qc, transformActiveResult) {
    var plresVm = this;

    $scope.ActiveProject = activeProject.project;
    $scope.ActivePlate = activePlate;
    $scope.ActivePlateResult = activePlateResult;
    plresVm.aside = false;

    plresVm.upload = upload;
    plresVm.deleteResult = deleteResult;

    plresVm.log = '';

    function upload(files, plateid) {
      console.log(JSON.stringify(files, null, 4));
      if (files && files.length) {
        for (var i = 0; i < files.length; i++) {
          var file = files[i];
          Upload.upload({
            url: 'http://54.149.197.234/adam/upload_result',
            fields: {
              'plate_id': plateid
            },
            file: file
          }).progress(function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            plresVm.log = 'progress: ' + progressPercentage + '% ' +
            evt.config.file.name + '\n' + plresVm.log;
          }).success(function (data, status, headers, config) {
            //plresVm.log = 'file ' + config.file.name + 'uploaded. Response: ' + JSON.stringify(data) + '\n' + plresVm.log;
            transformActiveResult(data);
            //$scope.$apply();
          });
        }
      }
    }

    function deleteResult(plateResult){
      Qc.delete({"id":plateResult.plateId});
      plateResult.measurements = null;
      plateResult = null;
    }

  }

})();
