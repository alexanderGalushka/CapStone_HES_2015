'use strict';
/**
 * @ngdoc function
 * @name plateeditor.controller:PlateeditorCtrl
 * @description
 * # PlateeditorCtrl
 * Controller of the plateeditor page
 *
 */

(function() {


  angular.module('plateeditor', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .controller('PlateeditorCtrl',PlateeditorCtrl)


  PlateeditorCtrl.$inject = ["$scope", "activeProject", "activePlate", "resetSelection", "filterColorFilter", "filterBorder3Filter",
    "filterHoverFilter", "filterControlFilter", "rangeFilter", "wellBoxSize"];

  function PlateeditorCtrl($scope, activeProject, activePlate, resetSelection, filterColor, filterBorder3, filterHover, filterControl, range, wellBoxSize) {
    var pleditVm = this;


    pleditVm.leftTable = leftTable;
    pleditVm.mouseDownWell = mouseDownWell;
    pleditVm.mouseUpWell = mouseUpWell;
    pleditVm.mouseOverWell = mouseOverWell;

    pleditVm.boxsz = "30";                                // initial size of label for wells
    pleditVm.tooltipdel = "500";                          // delay to show tooltip, otherwise it's unpleasantly fast
    pleditVm.dropWellGroup = {checked:"true"};            // status indicator when user copy value from one well to other with mouse click
    pleditVm.filterPlateEditor = {};                      // object used in plate map in individual well
    pleditVm.uniqueLabelValues = [];                      // list of label values
    pleditVm.uniqueLabelValuesColors = [];                // list of label colors
    pleditVm.selectedLabel = {};                          // selected label from list of available labels in plate
    pleditVm.selectedUniqValue = {};                      // selected color
    pleditVm.selectedLabelColor = {"color":"#FFFFFF"};
    pleditVm.itemsByPage=10;                              // item numbers in page for list of label values
    pleditVm.aside = false;                               // indicator that hides or shows panels
    // parameters used for wells selection, everything between first and last well will be selected
    pleditVm.multiselectWell = {"firstwell_row":"","firstwell_column":"","secondwell_row":"","secondwell_column":"","mode":false};

    $scope.ActiveProject = activeProject.project;
    $scope.ActivePlate = activePlate;
    $scope.WellBoxSize = wellBoxSize;

    pleditVm.filterPlateEditor.plotLabelName = "";
    pleditVm.filterPlateEditor.labelValueColors = {};
    pleditVm.filterPlateEditor.wellgroup = [];
    pleditVm.filterPlateEditor.labels = {};

    // slider used for plate map size
    pleditVm.boxsizerange = {
      from: 25,
      to: 100,
      floor: true,
      step: 1,
      dimension: " px",
      vertical: false,
      callback: function(value, elt) {
        //console.log(value);
      }
    };

    /**
     * @ngdoc function
     * @name leftTable
     * @description
     * Mouse left table so we need to update variables for wells selection
     *
     */
    function leftTable() {
      if(pleditVm.multiselectWell.mode) {
        pleditVm.multiselectWell.secondwell_row = "";
        pleditVm.multiselectWell.secondwell_column = "";
      }
      pleditVm.multiselectWell.mode = false;
    }

    /**
     * @ngdoc function
     * @name mouseDownWell
     * @description
     * Mouse down starts wells selection
     *
     */
    function mouseDownWell(well,labels) {
      pleditVm.multiselectWell.mode = true;
      pleditVm.multiselectWell.firstwell_row = well.row;
      pleditVm.multiselectWell.firstwell_column = well.col;

    }

    /**
     * @ngdoc function
     * @name mouseUpWell
     * @description
     * Mouse up stops wells selection
     *
     */
    function mouseUpWell(well,labels) {
      if(pleditVm.multiselectWell.mode) {
        pleditVm.multiselectWell.secondwell_row = "";
        pleditVm.multiselectWell.secondwell_column = "";
      }
      pleditVm.multiselectWell.mode = false;

    }

    /**
     * @ngdoc function
     * @name mouseOverWell
     * @description
     * Mouse over calculates affected wells when user holds mouse down and go over wells to select them
     *
     */
    function mouseOverWell(singleWell,wells){

      if(pleditVm.multiselectWell.mode) {                             // Multi select mode start with mouse pressed down
        if (pleditVm.multiselectWell.secondwell_row === "")
          resetSelection(wells, pleditVm.dropWellGroup);              // Reset selection because mouse moved to new well
        pleditVm.multiselectWell.secondwell_row = singleWell.row;     // Set parameters
        pleditVm.multiselectWell.secondwell_column = singleWell.col;

        var bigrow, smallrow, bigcolumn, smallcolumn;

        // Calculates coordinates for selected space, top left well and bottom right well
        if(parseInt(pleditVm.multiselectWell.firstwell_row,10) >= parseInt(pleditVm.multiselectWell.secondwell_row,10)){
          bigrow = pleditVm.multiselectWell.firstwell_row;
          smallrow = pleditVm.multiselectWell.secondwell_row;
        }else{
          bigrow = pleditVm.multiselectWell.secondwell_row;
          smallrow = pleditVm.multiselectWell.firstwell_row;
        }
        if(parseInt(pleditVm.multiselectWell.firstwell_column,10) >= parseInt(pleditVm.multiselectWell.secondwell_column,10)){
          bigcolumn = pleditVm.multiselectWell.firstwell_column;
          smallcolumn = pleditVm.multiselectWell.secondwell_column;
        }else{
          bigcolumn = pleditVm.multiselectWell.secondwell_column;
          smallcolumn = pleditVm.multiselectWell.firstwell_column;
        }

        // Marks all well in space selected
        for (var i = 0; i < wells.length; i++) {
          wells[i].condSelected = false;
          if (((parseInt(wells[i].row,10) >=  parseInt(smallrow,10) )&&(parseInt(wells[i].row,10) <=  parseInt(bigrow,10) )) &&
            ((parseInt(wells[i].col,10) >=  parseInt(smallcolumn,10))&&(parseInt(wells[i].col,10) <=  parseInt(bigcolumn,10))))
          {
            wells[i].condSelected = true;
          }

        }
      }
    }

  }

})();
