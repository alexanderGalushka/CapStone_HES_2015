'use strict';

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

    //pleditVm.boxszs = ["10","20","30","40","50"];
    pleditVm.boxsz = "30";
    pleditVm.tooltipdel = "500";
    pleditVm.dropWellGroup = {checked:"true"}; // "" for false
    pleditVm.filterPlateEditor = {};
    pleditVm.uniqueLabelValues = [];
    pleditVm.uniqueLabelValuesColors = [];
    pleditVm.selectedLabel = {};
    pleditVm.selectedUniqValue = {};
    pleditVm.selectedLabelColor = {"color":"#FFFFFF"};
    pleditVm.itemsByPage=10;
    pleditVm.aside = false;
    pleditVm.multiselectWell = {"firstwell_row":"","firstwell_column":"","secondwell_row":"","secondwell_column":"","mode":false};

    $scope.ActiveProject = activeProject.project;
    $scope.ActivePlate = activePlate;
    $scope.WellBoxSize = wellBoxSize;

    pleditVm.filterPlateEditor.plotLabelName = "";
    pleditVm.filterPlateEditor.labelValueColors = {};
    pleditVm.filterPlateEditor.wellgroup = [];
    pleditVm.filterPlateEditor.labels = {};

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

    function leftTable() {
      if(pleditVm.multiselectWell.mode) {
        pleditVm.multiselectWell.secondwell_row = "";
        pleditVm.multiselectWell.secondwell_column = "";
      }
      pleditVm.multiselectWell.mode = false;
    }

    function mouseDownWell(well,labels) {
      pleditVm.multiselectWell.mode = true;
      pleditVm.multiselectWell.firstwell_row = well.row;
      pleditVm.multiselectWell.firstwell_column = well.col;

    }

    function mouseUpWell(well,labels) {
      if(pleditVm.multiselectWell.mode) {
        pleditVm.multiselectWell.secondwell_row = "";
        pleditVm.multiselectWell.secondwell_column = "";
      }
      pleditVm.multiselectWell.mode = false;

    }

    function mouseOverWell(singleWell,wells){

      if(pleditVm.multiselectWell.mode) {
        if (pleditVm.multiselectWell.secondwell_row === "")
          resetSelection(wells, pleditVm.dropWellGroup);
        pleditVm.multiselectWell.secondwell_row = singleWell.row;
        pleditVm.multiselectWell.secondwell_column = singleWell.col;

        var bigrow, smallrow, bigcolumn, smallcolumn;
        //var wellSelected;

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
