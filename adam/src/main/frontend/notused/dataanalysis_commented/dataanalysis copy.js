(function() {

  var dataAnalysisModule = angular.module('dataAnalysis', ['ui.bootstrap']);

  var app = angular.module('hesAdam');

  app.service('DAService', ['$rootScope',
    function($rootScope) {

      var service = {

        projects: [{
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
        }],

        labels: [],
        compounds: [],
        substrates: [],
        currentWellCollection: [],
        dataIndices: [],
        dummyData: [],
        data: [],
        plotData: [],
        xData: [],
        yData: [],
        plotLabels: [],

        plotData: [],

        // I'm assuming this is how we're actually going to get the data
        rawData: [{
          "labelName": "Drug1",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "Drug2",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "Drug3",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "CellType1",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "CellType2",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "CellType3",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "Measurement1",
          "labelData": [2, 4, 5, 7, 9]
        }, {
          "labelName": "Measurement2",
          "labelData": [1, 8, 3, 3, 6]
        }, {
          "labelName": "Measurement3",
          "labelData": [5, 2, 3, 4, 5]
        }, ],
        // Scrape the actual data out, with a default x-axis of 1,2,3,4....
        // TODO: NEED TO MAKE SURE ALL DATA IS THE SAME LENGTH, EITHER THROUGH VALIDATION OR FILLING IN ENDS


        options: {
          legend: 'always',
          animatedZoom: true,
          title: 'Data Chart',
          drawPoints: true,
          labels: this.plotLabels,
          axes: {
            x: {
              labels: [],
              valueRange: []
            },
            y: {}
          },
          series: {}
        },

        graphIt: function() {
          g = new Dygraph(document.getElementById('graph'), this.plotData, this.setOptions(this.options));
          return g;
        },

        updateData: function(arrIndices, action) {
          switch (action) {
            case 'setX':
              if (arrIndices.length > 1) {
                arrIndices = arrIndices[0];
              }
              this.dataIndices[0] = arrIndices;
              break;
            case 'setY':
              this.dataIndices = this.dataIndices[0];
              for (i in arrIndices) {
                this.dataIndices.push(arrIndices[i]);
              }
              break;
            case 'add':
              for (i in arrIndices) {
                this.dataIndices.push(arrIndices[i]);
              }
              break;
            case 'remove':
              //TODO
            default:
          }
          this.data = [];
          this.plotLabels = [];
          for (i in this.dataIndices) {
            this.data.push(this.dummyData[this.dataIndices[i]]);
            this.plotLabels.push(this.labels[this.dataIndices[i]]);
          }
          this.plotData = this.transposeMatrix(this.data);
        },

        // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
        min: function(arr) {
          return arr.reduce(function(prev, curr) {
            return prev < curr ? prev : curr
          });
        },

        // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
        max: function(arr) {
          return arr.reduce(function(prev, curr) {
            return prev > curr ? prev : curr
          });
        },

        getDataForLabel: function(json, label) {
          var i;
          for (i = 0; i < json.length; i++) {
            if (json[i].labelName == label) {
              return json[i].labelData;
            }
          }
          return null;
        },

        searchForProject: function(projectTitle) {
          for (var i = 0, iLen = this.projects.length; i < iLen; i++) {

            if (this.projects[i].title == projectTitle) return this.projects[i];
          }
        },
        addLabelIfDoesNotExist: function(label) {
          var result = false;
          for (var i = 0, iLen = this.labels.length; i < iLen; i++) {
            if (this.labels[i].title == label) {
              result = true;
              break;
            }
          }
          if (result == false) {
            var object = {
              "title": label,
              "selected": false
            };
            this.labels.push(object);
          }
        },
        addCompoundIfDoesNotExist: function(well) {
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
            this.compounds.push(object);
          }
        },
        addSubstrateIfDoesNotExist: function(well) {
          var result = false;
          for (var i = 0, iLen = this.substrates.length; i < iLen; i++) {
            if (this.substrates[i].title == well.substrate) {
              result = true;
              break;
            }
          }
          if (result == false) {
            var object = {
              "title": well.substrate,
              "selected": false
            };
            this.substrates.push(object);
          }
        },
        labelFilter: function(label) {

          if (this.labels.length == 0)
            return true;

          // in this case we will disregard criteria, if nothing is 
          // selected all labels are ok
          // if at least one selected then only those labels are ok
          var noLabelsSelected = true;
          for (var i = 0, iLen = this.labels.length; i < iLen; i++) {
            if (this.labels[i].selected == true) {
              noLabelsSelected = false;
              break;
            }
          }

          if (noLabelsSelected == true) {
            return true;
          }

          for (var i = 0, iLen = this.labels.length; i < iLen; i++) {
            if (this.labels[i].selected == true && this.labels[i].title == label) {
              return true;
            }
          }

          return false;
        },
        compoundFilter: function(wellCompound) {

          if (this.compounds.length == 0)
            return true;

          // in this case we will disregard criteria, if nothing is 
          // selected all labels are ok
          // if at least one selected then only those labels are ok
          var noLabelsSelected = true;
          for (var i = 0, iLen = this.compounds.length; i < iLen; i++) {
            if (this.compounds[i].selected == true) {
              noLabelsSelected = false;
              break;
            }
          }

          if (noLabelsSelected == true) {
            return true;
          }

          for (var i = 0, iLen = this.compounds.length; i < iLen; i++) {
            if (this.compounds[i].selected === true && (this.compounds[i].title == wellCompound)) {
              return true;
            }
          }

          return false;
        },
        substrateFilter: function(wellSubstrate) {

          if (this.substrates.length == 0)
            return true;

          // in this case we will disregard criteria, if nothing is 
          // selected all labels are ok
          // if at least one selected then only those labels are ok
          var noLabelsSelected = true;
          for (var i = 0, iLen = this.substrates.length; i < iLen; i++) {
            if (this.substrates[i].selected == true) {
              noLabelsSelected = false;
              break;
            }
          }

          if (noLabelsSelected == true) {
            return true;
          }

          for (var i = 0, iLen = this.substrates.length; i < iLen; i++) {
            if (this.substrates[i].selected === true && (this.substrates[i].title == wellSubstrate)) {
              return true;
            }
          }

          return false;
        },

        getOnlySelectedLabels: function() {
          var wells = [];
          var noLabelsSelected = true;
          for (var i = 0, iLen = this.labels.length; i < iLen; i++) {
            if (this.labels[i].selected == true) {
              wells.push(this.labels[i].title);
            }
          }
          return wells;
        },

        getOnlySelectedCompounds: function() {
          var wells = [];
          var noLabelsSelected = true;
          for (var i = 0, iLen = this.compounds.length; i < iLen; i++) {
            if (this.compounds[i].selected == true) {
              wells.push(this.compounds[i].title);
            }
          }
          return wells;
        },

        getOnlySelectedSubstrates: function() {
          var subs = [];
          var noLabelsSelected = true;
          for (var i = 0, iLen = this.substrates.length; i < iLen; i++) {
            if (this.substrates[i].selected == true) {
              subs.push(this.substrates[i].title);
            }
          }
          return subs;
        },

        generateRandomItem: function() {

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
        },

        updateNewLabelsFromOld: function(oldOnlySelectedLabels) {

          for (var w = 0, wLen = this.labels.length; w < wLen; w++) {
            for (var i = 0, iLen = oldOnlySelectedLabels.length; i < iLen; i++) {
              if (oldOnlySelectedLabels[i] == this.labels[w].title) {
                this.labels[w].selected = true;
              }
            }
          }
        },
        updateNewCompoundsFromOld: function(oldOnlySelectedCompounds) {

          for (var w = 0, wLen = this.compounds.length; w < wLen; w++) {
            for (var i = 0, iLen = oldOnlySelectedCompounds.length; i < iLen; i++) {
              if (oldOnlySelectedCompounds[i] == this.compounds[w].title) {
                this.compounds[w].selected = true;
              }
            }
          }
        },
        updateNewSubstratesFromOld: function(oldOnlySelectedSubstrates) {
          for (var w = 0, wLen = this.substrates.length; w < wLen; w++) {
            for (var i = 0, iLen = oldOnlySelectedSubstrates.length; i < iLen; i++) {
              if (oldOnlySelectedSubstrates[i] == this.substrates[w].title) {
                this.substrates[w].selected = true;
              }
            }
          }
        },
        makeActive: function(projectTitle) {
          var project = this.searchForProject(projectTitle);
          if (project.selected == true)
            project.selected = false;
          else
            project.selected = true;
        },
        getWells: function(projectTitle) {
          var project = this.searchForProject(projectTitle);
          if (project.selected == true) {
            //if (project.title == "Project b") {
            var resultArray = [];
            for (var i = 0, iLen = 100; i < iLen; i++) {
              resultArray.push(this.generateRandomItem())
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
        },
        getMeasurements: function() {
          var well;
          var measurementData = [];
          for (var i = 0, iLen = this.currentWellCollection; i < iLen; i++) {
            well = this.currentWellCollection[i];
            measurementData.push(well.measurement1);
            measurementData.push(well.measurement2);
          }
          return
        },

        // simple function to generate an array containing a sequence of integers
        genSeq: function(start, end, increment) {
          var array = [];
          var i;
          for (i = start; i <= end; i += increment) {
            array.push(i);
          }
          return array;
        },

        transposeMatrix: function(matrix) {
          if (matrix == null)
            return;
          if (matrix[0] == undefined)
            return;
          var t = matrix[0].map(function(col, i) {
            return matrix.map(function(row) {
              return row[i];
            });
          });
          return t;
        },

        // used to add data to chart based on selected label
        // RETURNS NULL IF LABEL DOES NOT EXIST (should never happen really)
        getDataForLabel: function(json, label) {
          var i;
          for (i = 0; i < json.length; i++) {
            if (json[i].labelName == label) {
              return json[i].labelData;
            }
          }
          return null;
        },



        setOptions: function(options) {
          options.axes.x.labels = [this.plotLabels.length > 0 ? this.plotLabels[0] : []];
          options.axes.x.valueRange = this.data.length > 0 ? [this.min(this.data[0]), this.max(this.data[0])] : [];
          return options;
        },

        initializeDummyData: function() {
          this.dummyData = [];
          this.dummyData.push([]);
          var maxlen = 0;
          var dataVar = this.data;
          this.rawData.forEach(function(i) {
            if (i.labelData.length > maxlen) {
              maxlen = i.labelData.length;
            }
            if (i.labelData.length > 0) {
              dataVar.push(i.labelData);
            }
          });
          this.dummyData[0] = this.genSeq(1, maxlen, 1);

          this.labels = [];
          this.labels.push('x-index');
          labelsAnchor = this.labels;
          this.rawData.forEach(function(i) {
            labelsAnchor.push(i.labelName);
          })
        }

        /*return {
	      'rawData': dummyAllData,
	      'data': dummyData,
	      'labels': dummyLabels,
	      'genSeq': generateSequence,
	      'plotData': plotData,
	      'transposeMatrix': transposeMatrix,
	      'getDataForLabel': getDataForLabel,
	      'xData': xData,
	      'yData': yData
	    };*/
      }
      return service;
    }
  ]);

  //TODO: CREATE GRAPH SERVICE TO HOLD CURRENT STATE OF DYGRAPH OBJECT
  //TODO: ^ BUT WILL THAT CAUSE PROBLEMS WHEN WE IMPLEMENT MULTIFIGURE??
  //TODO: ALSO EXTREMELY ANNOYING: DYGRAPHS'S DEFAULT X-AXIS IS THE RANGE
  //TODO:   FROM THE FIRST X-VALUE TO THE LAST X-VALUE, NOT FROM MIN TO MAX
  //TODO:   ^ WE WILL NEED TO IMPLEMENT BETTER DEFAULT OURSELVES, BAH

  app.controller('DataAnalysisController', ['$scope', '$routeParams', 'DAService',
    function($scope, $routeParams, DAService) {

      DAService.initializeDummyData();
      DAService.updateData([0], 'setX');
      DAService.updateData([7, 8, 9], 'setY');
      DAService.graphIt();

      /*DAService.initializeDummyData();
          
          $scope.measurementData = [];

          $scope.measurementData = DAService.rawData;

          temp = DAService.data;
          DAService.data = [];
          DAService.data.push(temp[0]);
          DAService.data.push(temp[1]);
          DAService.plotData = DAService.transposeMatrix(DAService.data);

          graphIt(DAService.plotData);
          /*.updateOptions({
        labels: [DAService.labels[7], DAService.labels[8]]
      });

          console.log(document.getElementById('graph'));

          // This adds more data to the existing graph
          var newData = [3, 9, 9, 9, 5];
          var newLabel = 'newData';
          DAService.data.push(newData);
          //g.updateOptions({file: plotData, labels: dummyLabels});
          DAService.plotData = DAService.transposeMatrix(DAService.data);

          graphIt(DAService.plotData);*/

    }
  ]);

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

      /*$scope.labels = DAService.labels;

          $scope.selectedYAxisLabel = '';
          $scope.selectedXAxisLabel = '';
          $scope.yAxisSelectionErrorMessage = '';
          $scope.xAxisSelectionErrorMessage = '';
          $scope.selectedData = [];

          $scope.status = {
            isopen: false,
          };

          $scope.toggled = function(open) {
            //$log.log('Dropdown is now: ', open);
          };

          $scope.toggleDropdown = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.status.isopen = !$scope.status.isopen;
          };

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
	        DAService.updateData([DAService.labels.indexOf(label)],'setY');
	        g = DAService.graphIt();
	      // }
	    };*/

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

  /*$scope.setYAxisData = function(label) {

            if (label.labelData === null) {
              $scope.yAxisSelectionErrorMessage = 'Error: Selected label not found';
            } else if (label.labelData.length === 0) {
              $scope.yAxisSelectionErrorMessage = 'Error: Selected variable has no data';
            } else {
              DAService.yData = label.labelData;
              if (DAService.xData === null || DAService.xData.length === 0) {
                $scope.yAxisSelectionErrorMessage = 'Please select data for x axis';
                return;
              }
              DAService.data = [];
              DAService.data.push(DAService.xData);
              DAService.data.push(DAService.yData);
              DAService.plotData = DAService.transposeMatrix(DAService.data);
              g = graphIt(DAService.plotData);
              $scope.xAxisSelectionErrorMessage = '';
              $scope.yAxisSelectionErrorMessage = '';
              console.log(g.getLabels());
            }
          };

          $scope.setXAxisData = function(label) {
            DAService.xData = label.labelData;
            //errorchecking/validation
            if (label.labelData === null) {
              $scope.xAxisSelectionErrorMessage = 'Error: Selected label not found';
            } else if (label.labelData.length === 0) {
              $scope.xAxisSelectionErrorMessage = 'Error: Selected variable has no data';
            } else {
              if (DAService.yData === null || DAService.yData.length === 0) {
                $scope.xAxisSelectionErrorMessage = 'Please select data for x axis';
                return;
              }
              DAService.data = [];
              DAService.data.push(DAService.xData);
              DAService.data.push(DAService.yData);
              DAService.plotData = DAService.transposeMatrix(DAService.data);
              g = graphIt(DAService.plotData);
              $scope.xAxisSelectionErrorMessage = '';
              $scope.yAxisSelectionErrorMessage = '';
            }
          };*/






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
        this.updateWells();
        this.updateLabels();
        this.updateCompounds();
        this.updateSubstrates();
      };

    }
  ]);

  app.controller('splitGraphController', ['$scope',
    function($scope) {}
  ]);
}());