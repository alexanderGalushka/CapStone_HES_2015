(function() {

  //TODO: maybe refactor everything to use just an array of labels instead of dataIndices
  //TODO: get changing graph type by series working
  //TODO: remove displayed labels from menus?

  var app = angular.module('hesAdam');

  app.service('DAService', ['$rootScope',
        function($rootScope) {

    var projects = [{
      "title": "Project b",
      "description": "Cancer research in 2015",
      "owner": "Cindy",
      "tags": [{
        name: "multi project"
      }, {
        name: "mouse"
      }],
      "date": "2/2/2015",
      "nmbrPlates": 12,
      "selected": false
    }, {
      "title": "Project a",
      "description": "Amazing new medicine",
      "owner": "Nik",
      "date": "2/12/3015",
      "nmbrPlates": 1,
      "selected": false
    }];

    
    
    
    var currentWellCollection = [];

    var searchForProject = function(projectTitle) {
      for (var i = 0, iLen = projects.length; i < iLen; i++) {

        if (projects[i].title == projectTitle) return projects[i];
      }
    };

    var grid_labels = [];
    var addLabelIfDoesNotExist = function(DAService,label) {
      var result = false;
      for (var i = 0, iLen = grid_labels.length; i < iLen; i++) {
        if (grid_labels[i].title == label) {
          result = true;
          break;
        }
      }
      if (result == false) {
        var object = {
          "title": label,
          "selected": false
        };
        DAService.addLabels(object);
      }
    };

    var compounds = [];
    var addCompoundIfDoesNotExist = function(well) {
      var result = false;
      for (var i = 0, iLen = this.compounds.length; i < iLen; i++) {
        if (this.compounds[i].title == well.compound) {
          result = true;
          break;
        }
      }
      if (result == false) {
        var object = {
          "title": well.compound,
          "selected": false
        };
        compounds.push(object);
      }
    };

    var substrates = [];
    var addSubstrateIfDoesNotExist = function(well) {
      var result = false;
      for (var i = 0, iLen = substrates.length; i < iLen; i++) {
        if (substrates[i].title == well.substrate) {
          result = true;
          break;
        }
      }
      if (result == false) {
        var object = {
          "title": well.substrate,
          "selected": false
        };
        substrates.push(object);
      }
    };
    var labelFilter = function(label) {

      if (grid_labels.length == 0)
        return true;

      // in this case we will disregard criteria, if nothing is 
      // selected all grid_labels are ok
      // if at least one selected then only those grid_labels are ok
      var noLabelsSelected = true;
      for (var i = 0, iLen = grid_labels.length; i < iLen; i++) {
        if (grid_labels[i].selected == true) {
          noLabelsSelected = false;
          break;
        }
      }

      if (noLabelsSelected == true) {
        return true;
      }

      for (var i = 0, iLen = grid_labels.length; i < iLen; i++) {
        if (grid_labels[i].selected == true && grid_labels[i].title == label) {
          return true;
        }
      }

      return false;
    };
    var compoundFilter = function(wellCompound) {

      if (compounds.length == 0)
        return true;

      // in this case we will disregard criteria, if nothing is 
      // selected all grid_labels are ok
      // if at least one selected then only those grid_labels are ok
      var noLabelsSelected = true;
      for (var i = 0, iLen = compounds.length; i < iLen; i++) {
        if (compounds[i].selected == true) {
          noLabelsSelected = false;
          break;
        }
      }

      if (noLabelsSelected == true) {
        return true;
      }

      for (var i = 0, iLen = compounds.length; i < iLen; i++) {
        if (compounds[i].selected === true && (compounds[i].title == wellCompound)) {
          return true;
        }
      }

      return false;
    };
    var substrateFilter = function(wellSubstrate) {

      if (substrates.length == 0)
        return true;

      // in this case we will disregard criteria, if nothing is 
      // selected all grid_labels are ok
      // if at least one selected then only those grid_labels are ok
      var noLabelsSelected = true;
      for (var i = 0, iLen = substrates.length; i < iLen; i++) {
        if (substrates[i].selected == true) {
          noLabelsSelected = false;
          break;
        }
      }

      if (noLabelsSelected == true) {
        return true;
      }

      for (var i = 0, iLen = substrates.length; i < iLen; i++) {
        if (substrates[i].selected === true && (substrates[i].title == wellSubstrate)) {
          return true;
        }
      }

      return false;
    };

    var getOnlySelectedLabels = function() {
      var wells = [];
      var noLabelsSelected = true;
      for (var i = 0, iLen = grid_labels.length; i < iLen; i++) {
        if (grid_labels[i].selected == true) {
          wells.push(grid_labels[i].title);
        }
      }
      return wells;
    };

    var getOnlySelectedCompounds = function() {
      var wells = [];
      var noLabelsSelected = true;
      for (var i = 0, iLen = compounds.length; i < iLen; i++) {
        if (compounds[i].selected == true) {
          wells.push(compounds[i].title);
        }
      }
      return wells;
    };

    var getOnlySelectedSubstrates = function() {
      var subs = [];
      var noLabelsSelected = true;
      for (var i = 0, iLen = substrates.length; i < iLen; i++) {
        if (substrates[i].selected == true) {
          subs.push(substrates[i].title);
        }
      }
      return subs;
    };

    var generateRandomItem = function() {

      var wellLabel = ['Snake Data', 'Mouse Data', 'Cow Data', 'Gimmick Data'];
      var compound = ['verapamil', 'alcohol', 'ph20', 'x30z'];
      var substrate = ['propohol', 'ix30', 'po31', 'mx70'];
      var id = 1;

      var well = wellLabel[Math.floor(Math.random() * 3)];
      var comp = compound[Math.floor(Math.random() * 3)];
      var substr = substrate[Math.floor(Math.random() * 3)];
      var meas1 = Math.floor(Math.random() * 2000);
      var meas2 = Math.floor(Math.random() * 2000);
      var wellid = Math.floor(Math.random() * 1000);

      return {
        id: wellid,
        wellLabel: well,
        compound: comp,
        substrate: substr,
        measurement1: meas1,
        measurement2: meas2
      }
    };

    var updateNewLabelsFromOld = function(oldOnlySelectedLabels) {

      for (var w = 0, wLen = grid_labels.length; w < wLen; w++) {
        for (var i = 0, iLen = oldOnlySelectedLabels.length; i < iLen; i++) {
          if (oldOnlySelectedLabels[i] == grid_labels[w].title) {
            grid_labels[w].selected = true;
          }
        }
      }
    };
    var updateNewCompoundsFromOld = function(oldOnlySelectedCompounds) {

      for (var w = 0, wLen = compounds.length; w < wLen; w++) {
        for (var i = 0, iLen = oldOnlySelectedCompounds.length; i < iLen; i++) {
          if (oldOnlySelectedCompounds[i] == compounds[w].title) {
            compounds[w].selected = true;
          }
        }
      }
    };
    var updateNewSubstratesFromOld = function(oldOnlySelectedSubstrates) {
      for (var w = 0, wLen = substrates.length; w < wLen; w++) {
        for (var i = 0, iLen = oldOnlySelectedSubstrates.length; i < iLen; i++) {
          if (oldOnlySelectedSubstrates[i] == substrates[w].title) {
            substrates[w].selected = true;
          }
        }
      }
    };
    varmakeActive = function(projectTitle) {
      var project = searchForProject(projectTitle);
      if (project.selected == true)
        project.selected = false;
      else
        project.selected = true;
    };
    var getWells = function(projectTitle) {
      var project = searchForProject(projectTitle);
      if (project.selected == true) {
        //if (project.title == "Project b") {
        var resultArray = [];
        for (var i = 0, iLen = 100; i < iLen; i++) {
          resultArray.push(generateRandomItem())
        }
        return resultArray;
        //}
        /*if (project.title == "Project a") {
              return [{
                id: '9',
                wellLabel: 'Snake Data',
                compound: 'propohol',
                substrate: 'masd123',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '10',
                wellLabel: 'Horse Data',
                compound: 'mx123',
                substrate: 'm344w',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '11',
                wellLabel: 'Mouse Data',
                compound: 'mx123',
                substrate: 'alcohol',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '12',
                wellLabel: 'Mouse Data',
                compound: 'hsk1233',
                substrate: 'alcohol',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '13',
                wellLabel: 'Snake Data',
                compound: 'propohol',
                substrate: 'masd123',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '14',
                wellLabel: 'Horse Data',
                compound: 'mx123',
                substrate: 'm344w',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '15',
                wellLabel: 'Mouse Data',
                compound: 'mx123',
                substrate: 'alcohol',
                measurement1: '11.1',
                measurement2: '12.1'
              }, {
                id: '16',
                wellLabel: 'Mouse Data',
                compound: 'hsk1233',
                substrate: 'alcohol',
                measurement1: '11.1',
                measurement2: '12.1'
              }];
            }*/
      }
    };
    var getMeasurements = function() {
      var well;
      var measurementData = [];
      for (var i = 0, iLen = currentWellCollection; i < iLen; i++) {
        well = currentWellCollection[i];
        measurementData.push(well.measurement1);
        measurementData.push(well.measurement2);
      }
      return
    };

    // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
    var min = function(arr) {
        return arr.reduce(function(prev,curr) {
            return prev < curr ? prev : curr;
        });
    };

    // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
    var max = function(arr) {
        return arr.reduce(function(prev,curr) {
            return prev > curr ? prev : curr;
        });
    };

    // unbelievably, this is what is required to sort the goddamn input data to Dygraphs so that the goddamn x-axis works
    // Y U HAVE SUCH STUPID DEFAULT, DYGRAPHS
    // Y U NO HAVE LOGICAL SORT FUNCTION, JAVASCRIPT
    // YYYYYYYYYYYYYYY
    sortFunc = function(a,b) {
        if( !$.isArray(a) || !$.isArray(b) || a.length === 0 || b.length === 0) {
          console.log('Invalid inputs to sortFunc: (' + a + ', ' + b + ')');
          return null;
        }
        i = 0;
        while(((a[i]-b[i]) === 0) && (i < a.length) && (i < b.length)) {
            i = i + 1;
        }
        return a[i] - b[i];
    };

    // simple function to generate an array containing a sequence of integers
    var generateSequence = function(start, end, increment) {
      var array = [];
      var i;
      for(i = start; i <= end; i += increment) {
        array.push(i);
      }
      return array;
    };

  // pulled 3/30/15 from http://stackoverflow.com/questions/17428587/transposing-a-2d-array-in-javascript
  var transposeMatrix = function(matrix) {
    var t = matrix[0].map(function(col, i) { 
      return matrix.map(function(row) { 
        return row[i]; 
      });
    });
    return t;
  };
  
  // used to add data to chart based on selected label
  // RETURNS NULL IF LABEL DOES NOT EXIST (should never happen really)
  var getDataForLabel = function(json, label) {
    var i;
    for(i = 0; i < json.length; i++) {
      if(json[i].labelName == label) {
        return json[i].labelData;
      }
    }
    return null;
  };

    // I'm assuming this is how we're actually going to get the data
    var dummyAllData = [
    { "labelName" : "Drug1",
      "labelData" : generateSequence(1,5,1)
    },
    { "labelName" : "Drug2",
      "labelData" : generateSequence(2,10,2)
    },
    { "labelName" : "Drug3",
      "labelData" : generateSequence(3,15,3)
    },
    { "labelName" : "CellType1",
      "labelData" : generateSequence(1,5,1)
    },
    { "labelName" : "CellType2",
      "labelData" : generateSequence(2,10,2)
    },
    { "labelName" : "CellType3",
      "labelData" : generateSequence(3,15,3)
    },
    { "labelName" : "Measurement1",
      "labelData" : [2,4,5,7,9]
    },
    { "labelName" : "Measurement2",
      "labelData" : [1,8,3,3,6]
    },
    { "labelName" : "Measurement3",
      "labelData" : [5,2,3,4,5]
    },
  ];

  // Scrape the actual data out, with a default x-axis of 1,2,3,4....
  // TODO: NEED TO MAKE SURE ALL DATA IS THE SAME LENGTH, EITHER THROUGH VALIDATION OR FILLING IN ENDS
  var dummyData = [];
  dummyData.push([]);
  var maxlen = 0;
  dummyAllData.forEach(function(i) {
    if(i.labelData.length > maxlen) {
      maxlen = i.labelData.length;
    }
    if(i.labelData.length > 0) {
      dummyData.push(i.labelData);
    }
  });
  dummyData[0] = generateSequence(1,maxlen,1);

  var dummyLabels = [];
  dummyLabels.push('x-index');
  dummyAllData.forEach(function(i) {
    dummyLabels.push(i.labelName);
  });

  var dataIndices = [];
  var data = [];
  var plotLabels = [];
  var plotData =[];

  var tmp;
  var updateData = function(arrIndices, action) {
    var i;
    switch(action) {
      case 'setX':
        if(arrIndices.length === 0) {
          console.log('Warning: attempted to call DAService.updateData() with empty first argument');
        }
        else {
          if(arrIndices.length > 1) {
            console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'setX\' option');
          }
          dataIndices[0] = arrIndices[0];
        }
        break;
      case 'setY':
        tmp = dataIndices;
        dataIndices = [];
        dataIndices.push(tmp.length > 0 ? tmp[0] : 0);
        for (i in arrIndices) {
          dataIndices.push(arrIndices[i]);
        }
        break;
      case 'replace':
        if(arrIndices.length != 2) {
          console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'replace\' option');
        }
        else {
          dataIndices[dataIndices.indexOf(arrIndices[0])] = arrIndices[1];
        }        
        break;
      case 'add':
        for (i in arrIndices) {
          dataIndices.push(arrIndices[i]);
        }
        break;
      case 'remove':
        if(arrIndices.length === 0) {
          console.log('Warning: attempted to call DAService.updateData() with empty first argument');
        }
        else {
          if(arrIndices.length > 1) {
            console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'remove\' option');
          }
          if(dataIndices.indexOf(arrIndices[0]) >= 0) {
            // need to check for valid index, because splice() allows negative indices (similar to python)
            dataIndices.splice(dataIndices.indexOf(arrIndices[0]),1);
          }
        }
        break;
      default:
    }
    data = [];
    plotLabels = [];
    for (i in dataIndices) {
      data.push(dummyData[dataIndices[i]]);
      plotLabels.push(dummyLabels[dataIndices[i]]);
    }
    plotData = transposeMatrix(data);

    // REQUIRED FOR DYGRAPHS X-AXIS TO GENERATE PROPERLY
    plotData.sort(sortFunc);

    // update options
    options.labels = plotLabels;
    options.axes.x.labels = plotLabels.length > 0 ? plotLabels[0] : [];
    options.axes.x.valueRange = data[0].length > 0 ? [min(data[0]), max(data[0])] : [];

  }; // end of updateData()

  var options = {
    legend: 'always',
    //animatedZoom: true,
    title: 'Data Chart',
    drawPoints: true,
    labels: plotLabels,
    axes: {
      x: {
        labels: plotLabels.length > 0 ? plotLabels[0] : [],
        valueRange: (data.length > 0 && data[0].length > 0) ? [min(data[0]), max(data[0])] : []
      },
      y: {
      }
    },
    series: {
    },
  };

  var graphIt = function() {
console.log('graphIt: options.series --');
console.log(options.series);
    g = new Dygraph(document.getElementById('graph'), plotData, options);
    return g;
  };


var clearLabels = function() 
{
  grid_labels = [];
};
  var clearSubstrates = function() 
  {
      substrates = [];
  };
    var clearCompounds = function() 
    {
        compounds = [];
    };

    var addLabels = function(obj) 
{
  grid_labels.push(obj);
};
  var addSubstrates = function(obj) 
  {
      substrates.push(obj);
  };
    var addCompounds = function(obj) 
    {
        compounds.push(obj);
    };


    
    return {
      'grid_labels':grid_labels,
      'projects':projects,
      'rawData' : dummyAllData,
      'data' : data,
      'plotData' : plotData,
      'labels' : dummyLabels,
      'plotLabels' : plotLabels,
      'genSeq' : generateSequence,
      'transposeMatrix' : transposeMatrix,
      'getDataForLabel' : getDataForLabel,
      'updateData' : updateData,
      'graphIt' : graphIt,
      'options' : options,
      'compounds': compounds,
      'substrates': substrates,
      'currentWellCollection': currentWellCollection,
      'searchForProject': searchForProject,
      'addLabelIfDoesNotExist': addLabelIfDoesNotExist,
      'addCompoundIfDoesNotExist': addCompoundIfDoesNotExist,
      'addSubstrateIfDoesNotExist': addSubstrateIfDoesNotExist,
      'labelFilter': labelFilter,
      'compoundFilter': compoundFilter,
      'substrateFilter': substrateFilter,
      'getOnlySelectedLabels': getOnlySelectedLabels,
      'getOnlySelectedCompounds': getOnlySelectedCompounds,
      'getOnlySelectedSubstrates': getOnlySelectedSubstrates,
      'generateRandomItem': generateRandomItem,
      'updateNewLabelsFromOld': updateNewLabelsFromOld,
      'updateNewCompoundsFromOld': updateNewCompoundsFromOld,
      'updateNewSubstratesFromOld': updateNewSubstratesFromOld,
      'makeActive': varmakeActive,
      'getWells': getWells,
      'getMeasurements': getMeasurements,
      'clearLabels':clearLabels,
      'clearSubstrates':clearSubstrates,
      'clearCompounds':clearCompounds,
      'addLabels':addLabels,
      'addSubstrates':addSubstrates,
      'addCompounds':addCompounds
    };
  }]);

  var dataAnalysisModule = angular.module('dataAnalysis', ['ui.bootstrap']);

  var DUMMYDEFAULTDATASERIESTOPLOT = [7,8,9];

  app.controller('DataAnalysisController', ['$scope', '$routeParams', 'DAService', function($scope, $routeParams, DAService) {

  }]);


/*
  
  app.controller('DropdownCtrl', ['$scope', '$log', 'DAService', function ($scope, $log, DAService) {

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

*/

  app.controller('gridsController', ['$scope', 'DAService', "deleteProject", "activeProject", "setActiveProject", "$timeout",
    function($scope, DAService, deleteProject, activeProject, setActiveProject, $timeout) {

      $scope.deletePr = function(coll1, indx1) {
        deleteProject(coll1, indx1);
      };
      $scope.makeActive = function(proj) {
        $scope.ActiveProject = proj;
        setActiveProject(activeProject, proj);
        DAService.makeActive(proj.title);
      };

      $scope.ActiveProject = activeProject.activeId;
      $scope.filterowner = '';

      $scope.projects = DAService.projects;
      $scope.projectsDisplay = [].concat($scope.projects);

      $scope.wellCollection = [];

      $scope.updateWells = function() {
        $scope.wellCollection = [];
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            var wellLabel = "";

            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              wellLabel = wells[w].wellLabel;
              wellCompound = wells[w].compound;
              wellSubstrate = wells[w].substrate;
              if (DAService.labelFilter(wellLabel) && DAService.compoundFilter(wellCompound) && DAService.substrateFilter(wellSubstrate))
                $scope.wellCollection.push(wells[w]);
            }
          }
        }

        $scope.wellCollectionDisplay = [].concat($scope.wellCollection);

        DAService.currentWellCollection = $scope.wellCollectionDisplay;
      };

      $scope.updateLabels = function() {
        selectedLabels = DAService.getOnlySelectedLabels();
        DAService.clearLabels();
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              DAService.addLabelIfDoesNotExist(DAService, wells[w].wellLabel);
            }
          }
        }

        DAService.updateNewLabelsFromOld(selectedLabels)

        $scope.labelCollection = DAService.grid_labels;
      };

      $scope.updateCompounds = function() {
        selectedCompounds = DAService.getOnlySelectedCompounds();
        DAService.clearCompounds();
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              DAService.addCompoundIfDoesNotExist(wells[w]);
            }
          }
        }
        DAService.updateNewCompoundsFromOld(selectedCompounds)

        $scope.compoundCollection = DAService.compounds;
      };

      $scope.updateSubstrates = function() {
        selectedSubstrates = DAService.getOnlySelectedSubstrates();
        DAService.clearSubstrates();
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              DAService.addSubstrateIfDoesNotExist(wells[w]);
            }
          }
        }

        DAService.updateNewSubstratesFromOld(selectedSubstrates)

        $scope.substrateCollection = DAService.substrates;
      };

      $scope.updateTable = function(projectTitle) {
        $scope.updateWells();
        $scope.updateLabels();
        $scope.updateCompounds();
        $scope.updateSubstrates();
      };

    }
  ]);

}());