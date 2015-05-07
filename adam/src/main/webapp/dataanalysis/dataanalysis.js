(function() {

  //TODO: remove displayed labels from menus?
  //TODO: separate x-axis labels from y-axis labels

  var app = angular.module('adamApp');

  app.service('DAService', ['$rootScope', '$http', '$q',
    function($rootScope, $http, $q) {

      //      var projects = [{
      //        "title": "Project b",
      //        "description": "Cancer research in 2015",
      //        "owner": "Cindy",
      //        "tags": [{
      //          name: "multi project"
      //        }, {
      //          name: "mouse"
      //        }],
      //        "date": "2/2/2015",
      //        "nmbrPlates": 8,
      //        "selected": false
      //      }, {
      //        "title": "Project a",
      //        "description": "Amazing new medicine",
      //        "owner": "Nik",
      //        "date": "2/12/3015",
      //        "nmbrPlates": 8,
      //        "selected": false
      //      }];

      var projects = []

      /*jQuery.ajax({
          url:    '/adam/rest/project/',
          success: function(data) {

            for (var index in data){

              var new_project = {
                "title" : data[index].name,
                "description" : data[index].description,
                "owner" : data[index].owner,
                "date" : data[index].creationDate,
                "id" : data[index].id,
                "selected" : false
              }
              projects.push(new_project);
             }
          },
          async: false
     });*/


      /*var onPrjectRequestComplete = function () {
        for (var index in responce.data)
         {

              var new_project = 
              {
                "title" : data[index].name,
                "description" : data[index].description,
                "owner" : data[index].owner,
                "date" : data[index].creationDate,
                "id" : data[index].id,
                "selected" : false
              }
              projects.push(new_project);
          }
    }
    
    $http.get("http://54.149.197.234/adam/rest/project/").then(onPrjectRequestComplete);*/




      var currentWellCollection = [];

      var searchForProject = function(projectTitle) {
        for (var i = 0, iLen = projects.length; i < iLen; i++) {

          if (projects[i].title == projectTitle) return projects[i];
        }
      }; 

      var grid_labels = [];
      //var grid_labels = {};
      var addLabelIfDoesNotExist = function(DAService, label) {
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

      var measurementTypes = [];
      var addMeasurementTypeIfDoesNotExist = function(well) {
        var result = false;
        for (var i = 0, iLen = measurementTypes.length; i < iLen; i++) {
          if (measurementTypes[i].title == well.measurementType) {
            result = true;
            break;
          }
        }
        if (result == false) {
          var object = {
            "title": well.measurementType,
            "selected": false
          };
          measurementTypes.push(object);
        }
      };


      var labelFilter = function(label) {

        if (grid_labels.length == 0)
          return true;

        if (label.length === 0)
          return false;

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

      var measurementTypeFilter = function(wellSubstrate) {

        if (measurementTypes.length == 0)
          return true;

        // in this case we will disregard criteria, if nothing is
        // selected all grid_labels are ok
        // if at least one selected then only those grid_labels are ok
        var noLabelsSelected = true;
        for (var i = 0, iLen = measurementTypes.length; i < iLen; i++) {
          if (measurementTypes[i].selected == true) {
            noLabelsSelected = false;
            break;
          }
        }

        if (noLabelsSelected == true) {
          return true;
        }

        for (var i = 0, iLen = measurementTypes.length; i < iLen; i++) {
          if (measurementTypes[i].selected === true && (measurementTypes[i].title == wellSubstrate)) {
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

      var getOnlySelectedMeasurementTypes = function() {
        var subs = [];
        var noLabelsSelected = true;
        for (var i = 0, iLen = measurementTypes.length; i < iLen; i++) {
          if (measurementTypes[i].selected == true) {
            subs.push(measurementTypes[i].title);
          }
        }
        return subs;
      };




      var generateRandomItem = function() {

        var wellLabel = ['Snake Data', 'Mouse Data', 'Cow Data', 'Gimmick Data'];
        var compound = ['verapamil', 'alcohol', 'ph20', 'x30z'];
        var substrate = ['propophol', 'ix30', 'po31', 'mx70'];
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
          measurementValue: meas2
        }
      };

      var generateRandomItem2 = function() {

        var wellLabel = ['ASdasd', 'asdsad', 'asdasd', 'asdasd'];
        var compound = ['123123', '122222', '1111', '2222'];
        var substrate = ['zzzzz', 'xxxxx', 'porrrrrr31', 'mxhhhhhhh70'];
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
          measurementValue: meas2
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

      var updateNewMeasurementTypesFromOld = function(oldOnlySelectedSubstrates) {
        for (var w = 0, wLen = measurementTypes.length; w < wLen; w++) {
          for (var i = 0, iLen = oldOnlySelectedSubstrates.length; i < iLen; i++) {
            if (oldOnlySelectedSubstrates[i] == measurementTypes[w].title) {
              measurementTypes[w].selected = true;
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

      var map_project_wells;
      var getWells = function(projectTitle) {
        var project = searchForProject(projectTitle);
        return this.map_project_wells[project.id];
      };

      var getMeasurements = function() {
        var well;
        var measurementData = [];
        for (var i = 0, iLen = currentWellCollection; i < iLen; i++) {
          well = currentWellCollection[i];
          measurementData.push(well.measurement1);
          measurementData.push(well.measurementValue);
        }
        return
      };

      // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
      var min = function(arr) {
        return arr.reduce(function(prev, curr) {
          return prev < curr ? prev : curr;
        });
      };

      // pulled 4/13/15 from http://stackoverflow.com/questions/1669190/javascript-min-max-array-values
      var max = function(arr) {
        return arr.reduce(function(prev, curr) {
          return prev > curr ? prev : curr;
        });
      };

      // unbelievably, this is what is required to sort the goddamn input data to Dygraphs so that the goddamn x-axis works
      // Y U HAVE SUCH STUPID DEFAULT, DYGRAPHS
      // Y U NO HAVE LOGICAL SORT FUNCTION, JAVASCRIPT
      // YYYYYYYYYYYYYYY
      sortFunc = function(a, b) {
        if (!$.isArray(a) || !$.isArray(b) || a.length === 0 || b.length === 0) {
          console.log('Invalid inputs to sortFunc: (' + a + ', ' + b + ')');
          return null;
        }
        i = 0;
        while (((a[i] - b[i]) === 0) && (i < a.length) && (i < b.length)) {
          i = i + 1;
        }
        return a[i] - b[i];
      };

      // simple function to generate an array containing a sequence of integers
      var generateSequence = function(start, end, increment) {
        var array = [];
        var i;
        for (var i = start; i <= end; i += increment) {
          array.push(i);
        }
        return array;
      };

      // pulled 3/30/15 from http://stackoverflow.com/questions/17428587/transposing-a-2d-array-in-javascript
      var transposeMatrix = function(matrix) {
        if (matrix.length > 0) {
          var t = matrix[0].map(function(col, i) {
            return matrix.map(function(row) {
              return row[i];
            });
          });
          return t;
        } else {
          console.log('transposeMatrix: invalid argument: ' + matrix);
        }
        return -1;
      };

      // used to add data to chart based on selected label
      // RETURNS NULL IF LABEL DOES NOT EXIST (should never happen really)
      var getDataForLabel = function(json, label) {
        var i;
        for (var i = 0; i < json.length; i++) {
          if (json[i].labelName == label) {
            return json[i].labelData;
          }
        }
        return null;
      };


      var dummyData = [];

      var dummyLabels = [];

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
        "labelName": "measurementValue",
        "labelData": [1, 8, 3, 3, 6]
      }, {
        "labelName": "Measurement3",
        "labelData": [5, 2, 3, 4, 5]
      }];

      var initializeData = function() {
        // Scrape the actual data out, with a default x-axis of 1,2,3,4....
        // TODO: NEED TO MAKE SURE ALL DATA IS THE SAME LENGTH, EITHER THROUGH VALIDATION OR FILLING IN ENDS

        dummyData.splice(0, dummyData.length);
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

        if (dummyAllData[0].hasOwnProperty("time"))
        {
          dummyData[0] = dummyAllData[0].time;
        }
        else
          dummyData[0] = generateSequence(1, maxlen, 1);

        if (dummyData[0].length === 0)
          dummyData[0] = generateSequence(1, maxlen, 1);

        //dummyData[0] = generateSequence(1, maxlen, 1);

        dummyLabels.splice(0, dummyLabels.length);;
        dummyLabels.push('x-index');
        dummyAllData.forEach(function(i) {
          dummyLabels.push(i.labelName);
        });



      };





      var dataIndices = [];
      var data = [];
      var plotLabels = [];
      var plotData = [];

      var tmp;
      var updateData = function(arrIndices, action) {

        if (arrIndices.length === 0) {
          console.log('Warning: attempted to call DAService.updateData() with empty first argument');
        } else {
          var i;
          switch (action) {
            case 'setX':
              if (arrIndices.length > 1) {
                console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'setX\' option');
              }
              dataIndices[0] = arrIndices[0];
              break;
            case 'setY':
              tmp = dataIndices;
              dataIndices = [];
              dataIndices.push(tmp.length > 0 ? tmp[0] : 0);
              for (var i in arrIndices) {
                dataIndices.push(arrIndices[i]);
              }
              break;
            case 'replace':
              if (arrIndices.length != 2) {
                console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'replace\' option');
              } else {
                dataIndices[dataIndices.indexOf(arrIndices[0])] = arrIndices[1];
              }
              break;
            case 'add':
              for (var i in arrIndices) {
                dataIndices.push(arrIndices[i]);
              }
              break;
            case 'remove':
              if (arrIndices.length > 1) {
                console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'remove\' option');
              }
              if (dataIndices.indexOf(arrIndices[0]) >= 0) {
                // need to check for valid index, because splice() allows negative indices (similar to python)
                dataIndices.splice(dataIndices.indexOf(arrIndices[0]), 1);
              }
              break;
            default:
          }

          setPlotData();
        }
      }; // end of updateData()

      var setPlotData = function() {
        data = [];
        plotLabels = [];
        for (var i in dataIndices) {
          data.push(dummyData[dataIndices[i]]);
          plotLabels.push(dummyLabels[dataIndices[i]]);
        }
        plotCurveFit(); // add any curve fit lines currently on the graph
        plotData = transposeMatrix(data);

        // REQUIRED FOR DYGRAPHS X-AXIS TO GENERATE PROPERLY
        plotData.sort(sortFunc);

        // update options
        options.labels = plotLabels;
        options.axes.x.labels = plotLabels.length > 0 ? plotLabels[0] : [];
        options.axes.x.valueRange = data[0].length > 0 ? [min(data[0]), max(data[0])] : [];
      } // end of setPlotData();

      // TEMPORARY, WILL BE REPLACED BY CALL TO BACKEND
      // pulled from http://trentrichardson.com/2010/04/06/compute-linear-regressions-in-javascript/ on 4/26/15
        function linearRegression(y, x) {
          var lr = {};
          var n = y.length;
          var sum_x = 0;
          var sum_y = 0;
          var sum_xy = 0;
          var sum_xx = 0;
          var sum_yy = 0;

          for (var i = 0; i < y.length; i++) {

            sum_x += x[i];
            sum_y += y[i];
            sum_xy += (x[i] * y[i]);
            sum_xx += (x[i] * x[i]);
            sum_yy += (y[i] * y[i]);
          }

          lr['slope'] = (n * sum_xy - sum_x * sum_y) / (n * sum_xx - sum_x * sum_x);
          lr['intercept'] = (sum_y - lr.slope * sum_x) / n;
          lr['r2'] = Math.pow((n * sum_xy - sum_x * sum_y) / Math.sqrt((n * sum_xx - sum_x * sum_x) * (n * sum_yy - sum_y * sum_y)), 2);

          return lr;
        }

      var curveFitLines = [];
      var curveFitEquations = [];
      for (var i in dummyLabels) {
        curveFitLines.push([]);
        curveFitEquations.push('');
      }
      var addCurveFit = function(thisLabel) {
        // get slope and intercept from backend?
        index = dummyLabels.indexOf(thisLabel);
        dataIndex = dataIndices.indexOf(index);
        xData = data[0];
        yData = data[dataIndex];
        lr = linearRegression(yData, xData);
        equation = 'y = ' + lr.slope + 'x';
        if (lr.intercept > 0) {
          equation = equation + ' + ' + lr.intercept;
        }
        if (lr.intercept < 0) {
          equation = equation + ' - ' + Math.abs(lr.intercept);
        }
        curveFitEquations[index] = equation;
        for (var i in xData) {
          curveFitLines[index].push(lr.slope * xData[i] + lr.intercept);
        }
        plotCurveFit();
      }; // end of addCurveFit()

      var removeCurveFit = function(thisLabel) {
        index = dummyLabels.indexOf(thisLabel);
        curveFitLines[index].splice(0, curveFitLines[index].length);
        curveFitEquations[index] = '';
        setPlotData();
      }; // end of removeCurveFit()

      var plotCurveFit = function() {
        for (var i in curveFitLines) {
          if (i.length > 0) {
            data.push(curveFitLines[i]);
            plotLabels.push(curveFitEquations[i]);
            options.series[curveFitEquations[i]].strokeWidth = 1.5;
            options.series[curveFitEquations[i]].drawPoints = false;
            options.series[curveFitEquations[i]].color = options.series[dummyLabels[i]].color;
          }
        }
      }; // end of plotCurveFit()

      var hasCurveFit = function(thisLabel) {
        index = dummyLabels.indexOf(thisLabel);
        if (curveFitLines[index].length > 0) {
          return true;
        }
        return false;
      } // end of hasCurveFit()

      var options = {
        legend: 'always',
        //animatedZoom: true,
        title: '',
        drawPoints: true,
        labels: plotLabels,
        axes: {
          x: {
            labels: plotLabels.length > 0 ? plotLabels[0] : [],
            valueRange: (data.length > 0 && data[0].length > 0) ? [min(data[0]), max(data[0])] : []
          },
          y: {}
        },
        series: {},
      };

      var graphIt = function() {
        //options.xlabel = "Time (minutes)";
        if (plotData.length != 0) {
              g = new Dygraph(document.getElementById('graph'), plotData, options);
            var arr1 = [];
            var arr2 = [];
            var arr3 = [];
            var object= {plot: arr1, opts: arr2, graph: g, wells: arr3};
            object.plot = angular.copy(plotData);
            object.opts = angular.copy(options);
            return object;
          };
      }

      var clearLabels = function() {
        grid_labels.splice(0, grid_labels.length);
      };
      var clearSubstrates = function() {
        substrates.splice(0, substrates.length);
      };

      var clearCompounds = function() {
        compounds.splice(0, compounds.length);
      };

      var addLabels = function(obj) {
        grid_labels.push(obj);
      };
      var addSubstrates = function(obj) {
        substrates.push(obj);
      };
      var addCompounds = function(obj) {
        compounds.push(obj);
      };

      var createStructures = function() {
        grid_labels = [];
        substrate = [];
        compounds = [];
      };

      var currentGraph = [];

      var curentImageSetId = 0;

      var mapGraphs = new Object(); // or var map = {};
      var savedPlotData = [];


      return {
        'grid_labels': grid_labels,
        'projects': projects,
        'rawData': dummyAllData,
        'data': data,
        'plotData': plotData,
        'labels': dummyLabels,
        'plotLabels': plotLabels,
        'genSeq': generateSequence,
        'transposeMatrix': transposeMatrix,
        'getDataForLabel': getDataForLabel,
        'updateData': updateData,
        'addCurveFit': addCurveFit,
        'removeCurveFit': removeCurveFit,
        'hasCurveFit': hasCurveFit,
        'graphIt': graphIt,
        'options': options,
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
        'clearLabels': clearLabels,
        'clearSubstrates': clearSubstrates,
        'clearCompounds': clearCompounds,
        'addLabels': addLabels,
        'addSubstrates': addSubstrates,
        'addCompounds': addCompounds,
        'createStructures': createStructures,
        'getOnlySelectedMeasurementTypes': getOnlySelectedMeasurementTypes,
        'updateNewMeasurementTypesFromOld': updateNewMeasurementTypesFromOld,
        'measurementTypeFilter': measurementTypeFilter,
        'addMeasurementTypeIfDoesNotExist': addMeasurementTypeIfDoesNotExist,
        'measurementTypes': measurementTypes,
        'initializeData': initializeData,
        'currentGraph':currentGraph,
        'curentImageSetId' : curentImageSetId,
        'mapGraphs' : mapGraphs,
        'savedPlotData':savedPlotData,
         "map_project_wells": map_project_wells
      };
    }
  ]);

  //var dataAnalysisModule = angular.module('dataAnalysis', ['ui.bootstrap']);

  var DUMMYDEFAULTDATASERIESTOPLOT = [7, 8, 9];

  app.controller('DropdownCtrl', ['$scope', '$log', 'DAService', '$http', '$q',
    function($scope, $log, DAService, $http, $q) {

      dsPLACEHOLDER = 'Select data series';
      gtPLACEHOLDER = 'Select graph type';
      xPLACEHOLDER = 'Select x-axis variable';
      
      onError = function(reason) {
        var dAasd = 123;
      }

      getProjectsFromDB = function() {
        $http.get("/adam/rest/project").then(onPrjectRequestComplete, onError);
      }

      getProjectsWells = function() {
        $http.get("/adam/rest/get_all_data").then(onPrjectWellsInfo, onError);
      }

      onPrjectWellsInfo = function(response) {
        DAService.map_project_wells = response.data;

        for (var projectIndex in DAService.map_project_wells )
        {
              for (var measurementIndex in DAService.map_project_wells[projectIndex]) {
                var measurment = DAService.map_project_wells[projectIndex][measurementIndex];
                var measurmentLabels = DAService.map_project_wells[projectIndex][measurementIndex].wellLabels;
                for (var lableIndex in measurmentLabels ) 
                {
                  var individualMeasurment =  measurmentLabels[lableIndex];
                  if ("wellLabel" in individualMeasurment)
                  {
                      measurment.wellLabel = individualMeasurment.wellLabel;
                  }
                  if ("compound" in individualMeasurment)
                  {
                      measurment.compound = individualMeasurment.compound;
                  }
                  if ("substrate" in individualMeasurment)
                  {
                      measurment.substrate = individualMeasurment.substrate;
                  }
                }
                
            }   
        }
        
        $scope.labels = DAService.labels.slice(1); // remove default x-axis label, don't want that in the dropdown menu
        document.getElementById('saved').style.visibility = "hidden";
        $scope.showLabels = [];
        for(i in $scope.labels) {
          $scope.showLabels.push(true);
        }
        $scope.graphTypes = ['scatter', 'line', 'curve fit'];
        $scope.xLabels = ['time', 'dosage'] //TODO: NEED TO POPULATE THIS FROM DATA
        var yCount = 1;

        //$scope.ActiveProject = activeProject.activeId;
        $scope.filterowner = '';

        for (var w = 0, wLen = DAService.projects.length; w < wLen; w++) {
          var project = DAService.projects[w];
          project.selected = true;
          project.nmbrPlates = DAService.getWells(project.title).length;
          project.selected = false;
        }

        $scope.projects = DAService.projects;

        $scope.projectsDisplay = [].concat($scope.projects);

        $scope.wellCollection = [];

        dsPLACEHOLDER = 'Select data series';
        gtPLACEHOLDER = 'Select graph type';
        xPLACEHOLDER = 'Select x-axis variable';
        $scope.selectedXAxisLabel = xPLACEHOLDER;
        //DAService.updateData([0], 'setX');
        DAService.options.labels = ['x-axis','y-axis'];
        var g = new Dygraph(document.getElementById('graph'), [[0,0],[0,0]],  DAService.options);

        $scope.ySeries = [];
        $scope.yCount = 1;
        
        DAService.initializeData();

        resetDataSeries();
      }

      onPrjectRequestComplete = function(response) 
      {

          var data = response.data;
          DAService.projects.splice(0, DAService.projects.length);
          for (var index in response.data) 
          {
            var project = 
            {
              "title": data[index].name,
              "description": data[index].description,
              "owner": data[index].owner,
              "date": data[index].creationDate,
              "id": data[index].id,
              "selected": false
            }
            DAService.projects.push(project);
          }
          getProjectsWells();
       }

      getProjectsFromDB();

      addDataSeries = function(dsIndex) {
        currID = 'menu-' + dsIndex;
        $scope.ySeries.push({
          id: currID,
          label: dsPLACEHOLDER,
          type: gtPLACEHOLDER
        });
      };

      resetDataSeries = function() {
        $scope.yCount = 1;
        $scope.labels = DAService.labels.slice(1);
        $scope.ySeries.splice(0, $scope.ySeries.length);
        addDataSeries($scope.yCount);
        DAService.updateData(DAService.genSeq(1, $scope.labels.length, 1), 'setY');
        DAService.options.series = {};
        object = DAService.graphIt();
        object.wells = DAService.currentWellCollection;
        DAService.mapGraphs[DAService.curentImageSetId] = object;
      };

      $scope.addNewYAxisSelectionButton = function() {
        $scope.yCount = $scope.yCount + 1;
        addDataSeries($scope.yCount);
      };

      $scope.removeYAxisSelectionButton = function(IDofButtonToRemove) {
        if ($scope.yCount > 1) {
          $scope.yCount = $scope.yCount - 1;
          spliceIndex = -1;
          labelToRemove = '';
          for (var i in $scope.ySeries) {
            if ($scope.ySeries[i].id == IDofButtonToRemove) {
              spliceIndex = i;
              labelToRemove = $scope.ySeries[i].label;
            }
            // ** IMPORTANT: THIS RE-NUMBERS THE BUTTONS SO THEIR IDS ARE ALWAYS UNIQUE AND IN SEQUENTIAL ORDER
            if ((spliceIndex > -1) && (i > spliceIndex)) {
              $scope.ySeries[i].id = 'menu-' + i;
            }
          }
          if (spliceIndex > -1) {
            DAService.updateData([DAService.labels.indexOf($scope.ySeries[spliceIndex].label)], 'remove');
            $scope.ySeries.splice(spliceIndex, 1);
          }
          delete DAService.options.series[labelToRemove];

          DAService.currentGraph = DAService.graphIt();
        } else {
          resetDataSeries();
        }
      };

      $scope.setYAxisData = function(ID, label) {
        index = ID.split('-')[1] - 1;
        labelIndex = $scope.labels.indexOf(label);

        if (ID == 'menu-1') { // if it's the first menu, never add data
          if ($scope.ySeries[index].label == dsPLACEHOLDER) { // if first menu and never previously set, completely replace data
            DAService.updateData([DAService.labels.indexOf(label)], 'setY');
            DAService.options.series = {};
          } else { // if first menu has data series already, just replace it
            currLabel = $scope.ySeries[index].label;
            DAService.updateData(
              [
                DAService.labels.indexOf(currLabel), // current index
                DAService.labels.indexOf(label) // new index
              ],
              'replace');
            delete DAService.options.series[currLabel];
            currLabelIndex = $scope.labels.indexOf(currLabel);
            $scope.showLabels[currLabelIndex] = true;
          }
        } else { // if not first menu, never completely replace data
          if ($scope.ySeries[index].label == dsPLACEHOLDER) { // if never previously set, just add data
            DAService.updateData([DAService.labels.indexOf(label)], 'add');
          } else { // if has data series already, just replace it
            currLabel = $scope.ySeries[index].label;
            DAService.updateData(
              [
                DAService.labels.indexOf(currLabel), // current index
                DAService.labels.indexOf(label) // new index
              ],
              'replace');
            delete DAService.options.series[currLabel];
            currLabelIndex = $scope.labels.indexOf(currLabel);
            $scope.showLabels[currLabelIndex] = true;
          }
        }

        // update display label and make sure options series object contains this label as a key
        $scope.ySeries[index].label = label;
        $scope.showLabels[labelIndex] = false;
        DAService.options.series[label] = {};

        DAService.currentGraph = DAService.graphIt();
      };

      $scope.setYAxisGraphType = function(ID, type) {
        index = ID.split('-')[1] - 1;

        // do not set graph type unless there's a data series for this menu first
        if ($scope.ySeries[index].label != dsPLACEHOLDER) {
          thisLabel = $scope.ySeries[index].label;
          $scope.ySeries[index].type = type;
          if (DAService.options.series.hasOwnProperty(thisLabel)) 
          {

            switch (type) 
            {
              case 'scatter':
                if (DAService.hasCurveFit(thisLabel)) {
                  DAService.removeCurveFit(thisLabel);
                }
                DAService.options.series[thisLabel].strokeWidth = 0.0;
                DAService.options.series[thisLabel].pointSize = 3;
                break;
              case 'bar':
                if (DAService.hasCurveFit(thisLabel)) {
                  DAService.removeCurveFit(thisLabel);
                }
                break;
              case 'curve fit':
                if (!DAService.hasCurveFit(thisLabel)) {
                  DAService.options.series[thisLabel].strokeWidth = 0.0;
                  DAService.options.series[thisLabel].pointSize = 4;
                  DAService.addCurveFit(thisLabel);
                }
                break;
              case 'line':
                if (DAService.hasCurveFit(thisLabel)) {
                  DAService.removeCurveFit(thisLabel);
                }
                DAService.options.series[thisLabel].strokeWidth = 1.0;
                DAService.options.series[thisLabel].pointSize = 2;
                break;
              default:
            }

          DAService.currentGraph = DAService.graphIt();
        }
        }
      };
     

      $scope.setXAxisData = function(label) {
        $scope.selectedXAxisLabel = label;
        DAService.updateData([DAService.labels.indexOf(label)], 'setX');
        DAService.currentGraph = DAService.graphIt();
      };

      $scope.deletePr = function(coll1, indx1) {
        //deleteProject(coll1, indx1);
      };
      $scope.makeActive = function(proj) {
        //$scope.ActiveProject = proj;
        //setActiveProject(activeProject, proj);
        DAService.makeActive(proj.title);
      };

      $scope.updateWells = function() {
        $scope.wellCollection = [];
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            var wellLabel = "";

            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              var wellLabel = wells[w].wellLabel;
              wellCompound = wells[w].compound;
              wellSubstrate = wells[w].substrate;
              if ( DAService.labelFilter(wellLabel) && DAService.compoundFilter(wellCompound) && DAService.substrateFilter(wellSubstrate) && DAService.measurementTypeFilter(wells[w].measurementType))
                $scope.wellCollection.push(wells[w]);
            }
          }
        }

        $scope.wellCollectionDisplay = [].concat($scope.wellCollection);

        DAService.currentWellCollection = $scope.wellCollectionDisplay;
      };

      $scope.$watch('wellCollection', function() {
        //alert('hey, wellCollection has changed!');
        //var collection = $scope.wellCollection;
        //var length = collection.length;
      });

      $scope.updateLabels = function() {
        var selectedLabels = DAService.getOnlySelectedLabels();
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
        $scope.compoundCollection = [];
        var selectedCompounds = DAService.getOnlySelectedCompounds();
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

        $scope.compoundCollection = [].concat(DAService.compounds);

        $scope.compoundCollectionDisplay = [].concat($scope.compoundCollection);

      };

      $scope.updateSubstrates = function() {
        var selectedSubstrates = DAService.getOnlySelectedSubstrates();
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

      $scope.updateMeasurementTypes = function() {
        var selectedMTypes = DAService.getOnlySelectedMeasurementTypes();
        DAService.measurementTypes.splice(0, DAService.measurementTypes.length);
        for (var i = 0, iLen = DAService.projects.length; i < iLen; i++) {
          var project = DAService.projects[i];
          if (project.selected == true) {
            var wells = DAService.getWells(project.title);
            for (var w = 0, wLen = wells.length; w < wLen; w++) {
              DAService.addMeasurementTypeIfDoesNotExist(wells[w]);
            }
          }
        }

        DAService.updateNewMeasurementTypesFromOld(selectedMTypes)

        $scope.measurementTypeCollection = DAService.measurementTypes;
      };

      $scope.updateGraphData = function(scope) {
        //update the graph data

        DAService.rawData.splice(0, DAService.rawData.length);

        for (var w = 0, wLen = DAService.currentWellCollection.length; w < wLen; w++) {
          var well = DAService.currentWellCollection[w];

          if (DAService.rawData.length === 0) {
            var emptyArray = [];
            var timeArray = [];
            emptyArray.push(parseInt(well.measurementValue));
            timeArray.push(parseInt(well.time));
            var object = {
              "labelName": well.measurementType,
              "labelData": emptyArray,
              "time": timeArray

            };

            DAService.rawData.push(object);
          } else {
            var noSuchLabel = true;
            for (var p = 0, pLen = DAService.rawData.length; p < pLen; p++) {
              var obj = DAService.rawData[p];
              if (obj.labelName == well.measurementType) {
                noSuchLabel = false;
                obj.labelData.push(parseInt(well.measurementValue));
                obj.time.push(parseInt(well.time));
                break;
              }

            }
            if (noSuchLabel === true) {
              var emptyArray = [];
              var timeArray = [];
              emptyArray.push(parseInt(well.measurementValue));
              timeArray.push(parseInt(well.time));
              var object = {
                "labelName": well.measurementType,
                "labelData": emptyArray,
                "time": timeArray
              };

              DAService.rawData.push(object);
            }

          }
        }

        DAService.initializeData();

        //$scope.ySeries.splice(1, $scope.ySeries.length);

        resetDataSeries();

        scope.savedPlotData = DAService.plotData;

      }

      $scope.updateTable = function(projectTitle) {
        $scope.updateWells();
        $scope.updateLabels();
        $scope.updateCompounds();
        $scope.updateSubstrates();
        $scope.updateMeasurementTypes();
      };

      $scope.updateTable2 = function(projectTitle) {
        $scope.updateWells();
        $scope.updateLabels();
        $scope.updateCompounds();
        $scope.updateSubstrates();
        $scope.updateMeasurementTypes();
        $scope.updateGraphData($scope);

      };

      $scope.updateBar = function(foo) {
        //$scope.updateGraphData();
      };

      $scope.clearGraph = function(foo) {

        // These are the default options
        var options = {
            //Texts displayed below the chart's x-axis and to the left of the y-axis 
            titleFont: "bold 12px serif",
            titleFontColor: "black",

            //Texts displayed below the chart's x-axis and to the left of the y-axis 
            axisLabelFont: "bold 12px serif",
            axisLabelFontColor: "black",

            // Texts for the axis ticks
            labelFont: "normal 12px serif",
            labelFontColor: "black",

            // Text for the chart legend
            legendFont: "bold 12px serif",
            legendFontColor: "black",

            legendHeight: 1000    // Height of the legend area
        }; 
        

        /*
        document.getElementById('saved').style.visibility = "visible";
        var newId = "id" + Math.floor(Math.random() * 1000).toString();
        
        $('#saved').prepend("<img id='fakeId' ng-click='toggleImage(" + "1" + ")' src='' /></a>");
        
        //var newImgLink = document.getElementById('fakeIdLink');
        var newImg = document.getElementById('fakeId');
        //newImg.style.width='500px';
        //newImg.style.height='300px';
        newImg.style.width='300px';
        newImg.style.height='200px';
        newImg.id = newId;
        newImg.src = "";

        var firstRow=document.getElementById("inventory").rows[0];
        var x=firstRow.insertCell(-1);
        x.innerHTML="";    
        
        x.appendChild(newImg);
        //newImgLink.appendChild(newImg);

        */
        
        var imageIds = ['image1', 'image2', 'image3', 'image4'];
        document.getElementById('saved').style.visibility = "visible";
        var id = imageIds[DAService.curentImageSetId];
        var image = document.getElementById(id);
        image.style.visibility = "visible";
        image.style.width='300px';
        image.style.height='200px';
        var obj =  DAService.mapGraphs[DAService.curentImageSetId];

        g = new Dygraph(document.getElementById('graph'), obj.plot, obj.opts);

        Dygraph.Export.asPNG(g, image, obj.opts);

        //var arr = [];
        //var saveObject = {options: DAService.options, plotData: arr};
        //saveObject.plotData = angular.copy(DAService.savedPlotData);

        //DAService.mapGraphs[DAService.curentImageSetId] = saveObject;
        
        DAService.curentImageSetId ++;

        if (DAService.curentImageSetId > 3)
        {
            DAService.curentImageSetId = 0;
        }

        var id = $location.hash();
          $location.hash('verybottom');
          $anchorScroll();
          $location.hash(id);
      };

      $scope.clearGraph2Disabled = function(foo) {
        var g = new Dygraph(document.getElementById('graph'), [[0,0],[0,0]],  DAService.options);
        $scope.ySeries.splice(0, $scope.ySeries.length);
      };

      $scope.toggleImage = function(id) {
          saveObject = DAService.mapGraphs[id];
          g = new Dygraph(document.getElementById('graph'), saveObject.plot, saveObject.opts);
          
          //  $scope.wellCollectionDisplay.splice(0, $scope.wellCollectionDisplay.length);
          
          $scope.wellCollectionDisplay = angular.copy(saveObject.wells);

          $scope.wellCollection = angular.copy(saveObject.wells);
        
          $scope.wellCollectionDisplay = [].concat($scope.wellCollection);

          var id = $location.hash();
          $location.hash('verytop');
          $anchorScroll();
          $location.hash(id);

          
          //DAService.currentWellCollection = $scope.wellCollectionDisplay;

      };


    } 

  ]); // end of DropdownCtrl


}());