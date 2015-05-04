'use strict';

(function() {


  angular.module('plateresults', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap', 'ngFileUpload'])

    .controller('PlateResultsCtrl',PlateResultsCtrl)


  PlateResultsCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult" , "Upload", "classgridFilter", "Qc"];

  function PlateResultsCtrl($scope, activeProject, activePlate, activePlateResult, Upload, classgrid, Qc) {
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
            $scope.log = 'progress: ' + progressPercentage + '% ' +
            evt.config.file.name + '\n' + $scope.log;
          }).success(function (data, status, headers, config) {
            $scope.log = 'file ' + config.file.name + 'uploaded. Response: ' + JSON.stringify(data) + '\n' + $scope.log;
            $scope.$apply();
          });
        }
      }
    }

    function deleteResult(plateResult){
      console.log(JSON.stringify(plateResult, null, 4));
      Qc.delete({"id":plateResult.plateid});
      plateResult = null;
    }

  }

})();
