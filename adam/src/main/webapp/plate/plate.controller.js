'use strict';

(function() {
  angular.module('plate', ['smart-table','mgcrea.ngStrap'])

    .controller('PlateCtrl', PlateCtrl);

  PlateCtrl.$inject = ["$scope","activeProject", "activePlate", "activePlateResult", "Plate", "$filter" ,"loadActiveResult", "classgridFilter"];
  function PlateCtrl($scope, activeProject, activePlate, activePlateResult, Plate, $filter, loadActiveResult, classgrid) {
    var plateVm = this;

    $scope.ActiveProject = activeProject;
    $scope.ActivePlate = activePlate;
    $scope.activePlateResult = activePlateResult;

    plateVm.aside = true;
    plateVm.setActivePlate = setActivePlate;
    plateVm.addNewPlate = addNewPlate;
    plateVm.editPlate = editPlate;
    plateVm.saveChangesPlate = saveChangesPlate;
    plateVm.addLabel = addLabel;
    plateVm.addControlType = addControlType;
    plateVm.deletePlate = deletePlate;
    plateVm.clearActiveProject = clearActiveProject;

    plateVm.plates = Plate.query();
    plateVm.platesDisplay = [].concat(plateVm.plates);



    function setActivePlate(plate){
      activePlate.plate  = plate;
      activePlate.plate.wellsDisplay = [].concat(activePlate.plate.wells);

      if(plate !== null) {
        loadActiveResult(plate.id);
      }else
        activePlateResult = null;

    }

    function addNewPlate(project){
      plateVm.plateAction = "new";
      plateVm.newplate = {
        "name": "",
        "label": "",
        "numberOfRows":"",
        "numberOfColumns":"",
        "barcode":"",
        "protocolId":"",
        //"date": $filter('date')(new Date(),'MM/dd/yyyy'),
        "wellLabels":[],
        controlTypes:[{name:"positive", displayChar:"p"},{name:"negative", displayChar:"n"}]
      };
      plateVm.newplate.projectId = project.id;
    }

    function editPlate(plate) {
      plateVm.plateAction = "edit";
      plateVm.newplate = JSON.parse(JSON.stringify(plate));
    }

    function saveChangesPlate(act,plate) {
      if (act == "new") {
        var savedplate = Plate.save(plateVm.newplate);
        plateVm.plates = plateVm.plates.concat(savedplate);
      }
      else {
        Plate.update({"id":plateVm.newplate.id},plateVm.newplate);
        plate.project = plateVm.newplate.project;
        plate.name = plateVm.newplate.name;
        plate.label = plateVm.newplate.label;
        plate.numberOfRows = plateVm.newplate.numberOfRows;
        plate.numberOfColumns = plateVm.newplate.numberOfColumns;
        plate.barcode = plateVm.newplate.barcode;
        plate.protocolId = plateVm.newplate.protocolId;
        plate.wellLabels = plateVm.newplate.wellLabels;

      }
    }

    function addLabel(labels,newLabel){
      labels.push({name:newLabel});
    }

    function addControlType(controlTypes,newcontrolType,newdisplayChar){
      controlTypes.push({name:newcontrolType, displayChar:newdisplayChar});
    }

    function deletePlate(plate){
      Plate.delete({"id":plate.id} ,function(error) {
          var index = plateVm.plates.indexOf(plate);
          plateVm.plates.splice(index, 1);
        } ,
        function(error) {
          alert("Plate can not eb deleted - Server error");
          console.log(JSON.stringify(error, null, 4));
        });

    }

    function clearActiveProject(){
      $scope.ActiveProject.project  = null;
      $scope.ActivePlate.plate = null;
      $scope.activePlateResult.plateResult  = null;
    }
  }
})();
