(function() {

  var dataAnalysisModule = angular.module('dataAnalysis', ['ui.bootstrap']);

  var app = angular.module('hesAdam');

  app.service('DAService', function() {

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

    var labels = [];
    var compounds = [];
    var substrates = [];
    var currentWellCollection = [];

    var searchForProject = function(projectTitle) {
      for (var i = 0, iLen = projects.length; i < iLen; i++) {

        if (projects[i].title == projectTitle) return projects[i];
      }
    };
    var addLabelIfDoesNotExist = function(label) {
      var result = false;
      for (var i = 0, iLen = labels.length; i < iLen; i++) {
        if (labels[i].title == label) {
          result = true;
          break;
        }
      }
      if (result == false) {
        var object = {
          "title": label,
          "selected": false
        };
        labels.push(object);
      }
    };
    var addCompoundIfDoesNotExist = function(well) {
      var result = false;
      for (var i = 0, iLen = compounds.length; i < iLen; i++) {
        if (compounds[i].title == well.compound) {
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

      if (labels.length == 0)
        return true;

      // in this case we will disregard criteria, if nothing is 
      // selected all labels are ok
      // if at least one selected then only those labels are ok
      var noLabelsSelected = true;
      for (var i = 0, iLen = labels.length; i < iLen; i++) {
        if (labels[i].selected == true) {
          noLabelsSelected = false;
          break;
        }
      }

      if (noLabelsSelected == true) {
        return true;
      }

      for (var i = 0, iLen = labels.length; i < iLen; i++) {
        if (labels[i].selected == true && labels[i].title == label) {
          return true;
        }
      }

      return false;
    };
    var compoundFilter = function(wellCompound) {

      if (compounds.length == 0)
        return true;

      // in this case we will disregard criteria, if nothing is 
      // selected all labels are ok
      // if at least one selected then only those labels are ok
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
      // selected all labels are ok
      // if at least one selected then only those labels are ok
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
      for (var i = 0, iLen = labels.length; i < iLen; i++) {
        if (labels[i].selected == true) {
          wells.push(labels[i].title);
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

      for (var w = 0, wLen = labels.length; w < wLen; w++) {
        for (var i = 0, iLen = oldOnlySelectedLabels.length; i < iLen; i++) {
          if (oldOnlySelectedLabels[i] == labels[w].title) {
            labels[w].selected = true;
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
      return arr.reduce(function(prev, curr) {
        return prev < curr ? prev : curr
      });
    };

    // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
    var max = function(arr) {
      return arr.reduce(function(prev, curr) {
        return prev > curr ? prev : curr
      });
    };

    // simple function to generate an array containing a sequence of integers
    var generateSequence = function(start, end, increment) {
      var array = [];
      var i;
      for (i = start; i <= end; i += increment) {
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
      for (i = 0; i < json.length; i++) {
        if (json[i].labelName == label) {
          return json[i].labelData;
        }
      }
      return null;
    };

    //    var g = new Dygraph(document.getElementById("graph"));

    // I'm assuming this is how we're actually going to get the data
    var dummyAllData = [{
      "labelName": "Drug1",
      "labelData": generateSequence(1, 5, 1)
    }, {
      "labelName": "Drug2",
      "labelData": generateSequence(2, 10, 2)
    }, {
      "labelName": "Drug3",
      "labelData": generateSequence(3, 15, 3)
    }, {
      "labelName": "CellType1",
      "labelData": generateSequence(1, 5, 1)
    }, {
      "labelName": "CellType2",
      "labelData": generateSequence(2, 10, 2)
    }, {
      "labelName": "CellType3",
      "labelData": generateSequence(3, 15, 3)
    }, {
      "labelName": "Measurement1",
      "labelData": [2, 4, 5, 7, 9]
    }, {
      "labelName": "Measurement2",
      "labelData": [1, 8, 3, 3, 6]
    }, {
      "labelName": "Measurement3",
      "labelData": [5, 2, 3, 4, 5]
    }, ];

    // Scrape the actual data out, with a default x-axis of 1,2,3,4....
    // TODO: NEED TO MAKE SURE ALL DATA IS THE SAME LENGTH, EITHER THROUGH VALIDATION OR FILLING IN ENDS
    var dummyData = [];
    dummyData.push([]);
    var maxlen = 0;
    dummyAllData.forEach(function(i) {
      if (i.labelData.length > maxlen) {
        maxlen = i.labelData.length;
      }
      if (i.labelData.length > 0) {
        dummyData.push(i.labelData);
      }
    });
    dummyData[0] = generateSequence(1, maxlen, 1);

    var dummyLabels = [];
    dummyLabels.push('x-index');
    dummyAllData.forEach(function(i) {
      dummyLabels.push(i.labelName);
    });

    var dataIndices = [];
    var data = [];
    var plotLabels = [];
    var plotData = [];

    var updateData = function(arrIndices, action) {
      switch (action) {
        case 'setX':
          if (arrIndices.length > 1) {
            arrIndices = arrIndices[0];
          }
          dataIndices[0] = arrIndices;
          break;
        case 'setY':
          dataIndices = dataIndices[0];
          for (i in arrIndices) {
            dataIndices.push(arrIndices[i]);
          }
          break;
        case 'add':
          for (i in arrIndices) {
            dataIndices.push(arrIndices[i]);
          }
          break;
        case 'remove':
          //TODO
        default:
      }
      data = [];
      plotLabels = [];
      console.log(dataIndices);
      for (i in dataIndices) {
        data.push(dummyData[dataIndices[i]]);
        plotLabels.push(dummyLabels[dataIndices[i]]);
      }
      console.log(data);
      plotData = transposeMatrix(data);
      console.log(plotData);
    }

    // dataIndices = [0,7,8,9];  // when live, this default should be x-index plus all "plottable" data series indices
    // updateData();

    var options = {
      legend: 'always',
      //animatedZoom: true,
      title: 'Data Chart',
      drawPoints: true,
      labels: plotLabels,
      axes: {
        x: {
          labels: plotLabels.length > 0 ? plotLabels[0] : [],
          valueRange: data.length > 0 ? [min(data[0]), max(data[0])] : []
        },
        y: {}
      },
      series: {}
    };

    var graphIt = function() {
      g = new Dygraph(document.getElementById('graph'), plotData, options);
      return g;
    };


    return {
      'rawData': dummyAllData,
      //      'graph': g,
      'dataIndices': dataIndices,
      'data': data,
      'plotData': plotData,
      'labels': dummyLabels,
      'plotLabels': plotLabels,
      'genSeq': generateSequence,
      'transposeMatrix': transposeMatrix,
      'getDataForLabel': getDataForLabel,
      'updateData': updateData,
      'graphIt': graphIt,
      'options': options,
      'compounds': compounds,
      'substrates': substrates,
      'currentWellCollection': currentWellCollection,
      'searchForProject': searchForProject,
      'addLabelIfDoesNotExist': addLabelIfDoesNotExist,
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
      'getMeasurements': getMeasurements
    };
  });

  //TODO: CREATE GRAPH SERVICE TO HOLD CURRENT STATE OF DYGRAPH OBJECT
  //TODO: ^ BUT WILL THAT CAUSE PROBLEMS WHEN WE IMPLEMENT MULTIFIGURE??
  //TODO: ALSO EXTREMELY ANNOYING: DYGRAPHS'S DEFAULT X-AXIS IS THE RANGE
  //TODO:   FROM THE FIRST X-VALUE TO THE LAST X-VALUE, NOT FROM MIN TO MAX
  //TODO:   ^ WE WILL NEED TO IMPLEMENT BETTER DEFAULT OURSELVES, BAH

  app.controller('DataAnalysisController', ['$scope', '$routeParams', 'DAService',
    function($scope, $routeParams, DAService) {

      DAService.updateData([0], 'setX');
      DAService.updateData([7, 8, 9], 'setY');
      DAService.graphIt();

    }
  ]);


  /*

  app.controller('DropdownCtrl', ['$scope', '$log', 'DAService',
    function($scope, $log, DAService) {
      $scope.labels = DAService.labels.slice(1); // remove default x-axis label, don't want that in the dropdown menu
      $scope.graphTypes = ['scatter', 'line'];
      var yCount = 1;

      $scope.selectedYAxisLabel = 'Select y-axis   ';
      $scope.selectedXAxisLabel = 'Select x-axis   ';
      $scope.selectedYAxisGraphType = 'Select graph type   ';
      $scope.yAxisSelectionErrorMessage = '';
      $scope.xAxisSelectionErrorMessage = '';
      $scope.selectedData = [];

      $scope.toggleDropdown = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
      };


      $(document).on('click', '.y-axis-add', function(e) {
        e.preventDefault();

        var controlButtonGroup = $('.y-axis-data-selection');
        var currentButtonGroup = $('.y-axis-control-button:first');
        var newButtonGroup = $(currentButtonGroup.clone()).appendTo(controlButtonGroup);

        yCount = yCount + 1;

        console.log(controlButtonGroup);
        console.log(currentButtonGroup);
        console.log(newButtonGroup);

      }).on('click', '.btn-remove', function(e) {

        if ($(this).parent().hasClass('y-axis-control-button') && yCount == 1) {
          //TODO: reset selection and remove data from graph
        } else {
          $(this).parents('.btn-group:first').remove();
          e.preventDefault();
        }

        return false;
      });


      $scope.setYAxisData = function(label) {
        $scope.selectedYAxisLabel = label;
        // $scope.selectedData = DAService.getDataForLabel(DAService.rawData, $scope.selectedYAxisLabel);
        // //errorchecking/validation
        // if($scope.selectedData === null) {
        //   $scope.yAxisSelectionErrorMessage = 'Error: Selected label not found';
        // }
        // else if($scope.selectedData.length === 0) {
        //   $scope.yAxisSelectionErrorMessage = 'Error: Selected variable has no data';
        // }
        // else {
        DAService.updateData([DAService.labels.indexOf(label)], 'setY');
        g = DAService.graphIt();
        // }
      };

      $scope.setXAxisData = function(label) {
        $scope.selectedXAxisLabel = label;
        // $scope.selectedData = DAService.getDataForLabel(DAService.rawData, $scope.selectedXAxisLabel);
        // //errorchecking/validation
        // if($scope.selectedData === null) {
        //   $scope.xAxisSelectionErrorMessage = 'Error: Selected label not found';
        // }
        // else if($scope.selectedData.length === 0) {
        //   $scope.xAxisSelectionErrorMessage = 'Error: Selected variable has no data';
        // }
        // else {
        DAService.updateData([DAService.labels.indexOf(label)], 'setX');
        g = DAService.graphIt();
        // }
      };

      $scope.setYAxisGraphType = function(type) {
        $scope.selectedYAxisGraphType = type;

        switch (type) {
          case 'scatter':
            DAService.options.strokeWidth = 0.0;
            DAService.options.pointSize = 3;
            break;
          case 'bar':
          case 'curve':
          case 'line':
          default:
            DAService.options.strokeWidth = 1.0;
            DAService.options.pointSize = 2;
            break;
        }

        DAService.graphIt();
      };

    }
  ]);

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
        DAService.labels = [];
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              DAService.addLabelIfDoesNotExist(wells[w].wellLabel);
            }
          }
        }

        DAService.updateNewLabelsFromOld(selectedLabels)

        $scope.labelCollection = DAService.labels;
      };

      $scope.updateCompounds = function() {
        selectedCompounds = DAService.getOnlySelectedCompounds();
        DAService.compounds = [];
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
        DAService.substrates = [];
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
        updateWells();
        updateLabels();
        updateCompounds();
        updateSubstrates();
      };

    }
  ]);

  app.controller('splitGraphController', ['$scope',
    function($scope) {}
  ]);
}());