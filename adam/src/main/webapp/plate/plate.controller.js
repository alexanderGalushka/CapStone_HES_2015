'use strict';
/**
 * @ngdoc function
 * @name plate.controller:PlateCtrl
 * @description
 * # PlateCtrl
 * Controller of the plate page
 *
 */

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


    /**
     * @ngdoc function
     * @name setActivePlate
     * @description
     * Set active plate variable and loads result data for that plate
     * Set wellsDisplay for table view
     *
     */
    function setActivePlate(plate){
      activePlate.plate  = plate;
      activePlate.plate.wellsDisplay = [].concat(activePlate.plate.wells);

      if(plate !== null) {
        loadActiveResult(plate.id);
      }else
        activePlateResult = null;

    }

    /**
     * @ngdoc function
     * @name addNewPlate
     * @description
     * Prepares new plate json to be used in modal page
     *
     */
    function addNewPlate(project){
      plateVm.plateAction = "new";
      plateVm.newplate = {
        "name": "",
        "label": "",
        "numberOfRows":"",
        "numberOfColumns":"",
        "barcode":"",
        "protocolId":"",
        "wellLabels":[],
        controlTypes:[{name:"positive", displayChar:"p"},{name:"negative", displayChar:"n"}]
      };
      plateVm.newplate.projectId = project.id;
    }

    /**
     * @ngdoc function
     * @name editPlate
     * @description
     * Prepares  plate json to be used in modal page for edit
     *
     */
    function editPlate(plate) {
      plateVm.plateAction = "edit";
      plateVm.newplate = JSON.parse(JSON.stringify(plate));
    }

    /**
     * @ngdoc function
     * @name saveChangesPlate
     * @description
     * Function called from modal page to save changes
     */
    function saveChangesPlate(act,plate) {
      if (act == "new") {
        var savedplate = Plate.save(plateVm.newplate,function() {
          plateVm.plates = plateVm.plates.concat(savedplate);
        } ,function(error) {
          alert("Changes can not be saved - Server error");
          console.log(JSON.stringify(error, null, 4))
        })
      }
      else {
        Plate.update({"id":plateVm.newplate.id},plateVm.newplate,function() {
            plate.project = plateVm.newplate.project;
            plate.name = plateVm.newplate.name;
            plate.label = plateVm.newplate.label;
            plate.numberOfRows = plateVm.newplate.numberOfRows;
            plate.numberOfColumns = plateVm.newplate.numberOfColumns;
            plate.barcode = plateVm.newplate.barcode;
            plate.protocolId = plateVm.newplate.protocolId;
            plate.wellLabels = plateVm.newplate.wellLabels;
          },
          function(error) {
            alert("Changes can not be saved - Server error");
            console.log(JSON.stringify(error, null, 4));
          });
      }
    }

    /**
     * @ngdoc function
     * @name addLabel
     * @description
     * Add label to the list of labels. Json list will be saved in database
     */
    function addLabel(labels,newLabel){
      if(labels.indexOf(newLabel) < 0)
        labels.push({name:newLabel});
    }

    /**
     * @ngdoc function
     * @name addControlType
     * @description
     * Add control type to the list of control types. Json list will be saved in database
     */
    function addControlType(controlTypes,newcontrolType,newdisplayChar){
      if(controlTypes.indexOf(newcontrolType) < 0)
        controlTypes.push({name:newcontrolType, displayChar:newdisplayChar});
    }

    /**
     * @ngdoc function
     * @name deletePlate
     * @description
     * Delete plate from database and updates json collection used for display
     *
     */
    function deletePlate(plate){
      Plate.delete({"id":plate.id} ,function() {
          var index = plateVm.plates.indexOf(plate);
          plateVm.plates.splice(index, 1);
        } ,
        function(error) {
          alert("Plate can not be deleted - Server error");
          console.log(JSON.stringify(error, null, 4));
        });

    }

    /**
     * @ngdoc function
     * @name clearActiveProject
     * @description
     * Clear all varibles
     *
     */
    function clearActiveProject(){
      $scope.ActiveProject.project  = null;
      $scope.ActivePlate.plate = null;
      $scope.activePlateResult.plateResult  = null;
    }
  }
})();
