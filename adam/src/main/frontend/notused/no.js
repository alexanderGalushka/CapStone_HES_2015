(function() {

  //TODO: maybe refactor everything to use just an array of labels instead of dataIndices
  //TODO: get changing graph type by series working
  //TODO: remove displayed labels from menus?

  var dataAnalysisModule = angular.module('dataAnalysis', ['ui.bootstrap']);

  var DUMMYDEFAULTDATASERIESTOPLOT = [7,8,9];

  dataAnalysisModule.controller('DataAnalysisController', ['$scope', '$routeParams', 'DAService', function($scope, $routeParams, DAService) {
/* -- WAS TESTING DYGRAPHS TO SEE IF IT'S MY BUG OR A DYGRAPHS ISSUE --
   -- http://stackoverflow.com/questions/29712968/setting-per-series-options-when-using-native-array-data-input-format --
    g = new Dygraph(document.getElementById('graph'),
      [
        [1,2,1],
        [2,4,4],
        [3,6,9],
        [4,8,16],
        [5,10,25],
        [6,12,36],
        [7,14,49],
        [8,16,64],
        [9,18,81],
        [10,20,100]
      ],
      {
        legend: 'always',
        title: 'Data Chart',
        drawPoints: true,
        labels: ['index', 'doubleX', 'xSquared'],
        series: {
          doubleX: {
            strokeWidth: 0.5
          },
          xSquared: {
            strokeWidth: 2.0
          }
        }
      }
    );
*/
  }]);

  
  dataAnalysisModule.controller('DropdownCtrl', ['$scope', '$log', 'DAService', function ($scope, $log, DAService) {

    $scope.labels = DAService.labels.slice(1); // remove default x-axis label, don't want that in the dropdown menu
    $scope.graphTypes = ['scatter', 'line'];
    var yCount = 1;
  
    dsPLACEHOLDER = 'Select data series';
    gtPLACEHOLDER = 'Select graph type';
    xPLACEHOLDER = 'Select x-axis variable';
    $scope.selectedXAxisLabel = xPLACEHOLDER;
    DAService.updateData([0], 'setX');

    // $scope.toggleDropdown = function($event) {
    //   $event.preventDefault();
    //   $event.stopPropagation();
    // };

    $scope.ySeries = [];
    $scope.yCount = 1;

    addDataSeries = function(dsIndex) {
      currID = 'menu-' + dsIndex;
      $scope.ySeries.push({
        id: currID,
        label: dsPLACEHOLDER,
        type: gtPLACEHOLDER,
      });
    };

    resetDataSeries = function() {
      $scope.yCount = 1;
      $scope.ySeries = [];
      addDataSeries($scope.yCount);
      DAService.updateData(DUMMYDEFAULTDATASERIESTOPLOT, 'setY');
      DAService.options.series = {};
      DAService.graphIt();
    };

    resetDataSeries();

    $scope.addNewYAxisSelectionButton = function() {
      $scope.yCount = $scope.yCount + 1;
      addDataSeries($scope.yCount);
    };

    $scope.removeYAxisSelectionButton = function(IDofButtonToRemove) {
      if($scope.yCount > 1) {
        $scope.yCount = $scope.yCount - 1;
        spliceIndex = -1;
        labelToRemove = '';
        for(var i in $scope.ySeries) {
          if($scope.ySeries[i].id == IDofButtonToRemove) {
            spliceIndex = i;
            labelToRemove = $scope.ySeries[i].label;
          }
          // ** IMPORTANT: THIS RE-NUMBERS THE BUTTONS SO THEIR IDS ARE ALWAYS UNIQUE AND IN SEQUENTIAL ORDER
          if((spliceIndex > -1) && (i > spliceIndex)) {
            $scope.ySeries[i].id = 'menu-' + i;
          }
        }
        if(spliceIndex > -1) {
          DAService.updateData([DAService.labels.indexOf($scope.ySeries[spliceIndex].label)],'remove');
          $scope.ySeries.splice(spliceIndex,1);
        }
        delete DAService.options.series[labelToRemove];

        DAService.graphIt();
      }
      else {
        resetDataSeries();
      }
    };
    
    $scope.setYAxisData = function(ID,label) {
      index = ID.split('-')[1] - 1;

      if(ID == 'menu-1') { // if it's the first menu, never add data
        if($scope.ySeries[index].label == dsPLACEHOLDER) { // if first menu and never previously set, completely replace data
          DAService.updateData([DAService.labels.indexOf(label)],'setY');
          DAService.options.series = {};
        }
        else { // if first menu has data series already, just replace it
          DAService.updateData(
            [
              DAService.labels.indexOf($scope.ySeries[index].label), // current index
              DAService.labels.indexOf(label)                        // new index
            ],
            'replace');
          delete DAService.options.series[$scope.ySeries[index].label];
        }
      }
      else { // if not first menu, never completely replace data
        if($scope.ySeries[index].label == dsPLACEHOLDER) { // if never previously set, just add data
          DAService.updateData([DAService.labels.indexOf(label)],'add');
        }
        else { // if has data series already, just replace it
          DAService.updateData(
            [
              DAService.labels.indexOf($scope.ySeries[index].label), // current index
              DAService.labels.indexOf(label)                        // new index
            ],
            'replace');
          delete DAService.options.series[$scope.ySeries[index].label];
        }        
      }

      // update display label and make sure options series object contains this label as a key
      $scope.ySeries[index].label = label;
      DAService.options.series[label] = {};
      
      DAService.graphIt();
    };

    $scope.setYAxisGraphType = function(ID,type) {
      index = ID.split('-')[1] - 1;

      // thisLabel = $scope.ySeries[index].label;
      // if(DAService.options.series.hasOwnProperty(thisLabel)) {
        $scope.ySeries[index].type = type;

        switch(type) {
          case 'scatter':
              DAService.options.strokeWidth = 0.0;
              DAService.options.pointSize = 3;
              // DAService.options.series[thisLabel].strokeWidth = 0.0;
              // DAService.options.series[thisLabel].pointSize = 3;
            break;
          case 'bar':
          case 'curve':
          case 'line':
              DAService.options.strokeWidth = 1.0;
              DAService.options.pointSize = 2;
              // DAService.options.series[thisLabel].strokeWidth = 1.0;
              // DAService.options.series[thisLabel].pointSize = 2;
            break;
          default:
        }

        DAService.graphIt();
      // }
    };
    
    $scope.setXAxisData = function(label) {
      $scope.selectedXAxisLabel = label;
      DAService.updateData([DAService.labels.indexOf(label)],'setX');
      DAService.graphIt();
    };
  
  }]); // end of DropdownCtrl

}());