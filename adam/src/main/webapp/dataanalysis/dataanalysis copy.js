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
        "nmbrPlates": 8,
        "selected": false
      }, {
        "title": "Project a",
        "description": "Amazing new medicine",
        "owner": "Nik",
        "date": "2/12/3015",
        "nmbrPlates": 8,
        "selected": false
      }];




      var currentWellCollection = [];

      var searchForProject = function(projectTitle) {
        for (var i = 0, iLen = projects.length; i < iLen; i++) {

          if (projects[i].title == projectTitle) return projects[i];
        }
      };

      var grid_labels = [];
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
          measurement2: meas2
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
      var getWells = function(projectTitle) {
        var project = searchForProject(projectTitle);
        if (project.selected == true) {

          /*if (project.title == "Project b") {
                var resultArray = [];
                for (var i = 0, iLen = 100; i < iLen; i++) {
                  resultArray.push(generateRandomItem2());
                }
                return resultArray;
              } 
              else 
              {
                var resultArray = [];
                for (var i = 0, iLen = 100; i < iLen; i++) {
                  resultArray.push(generateRandomItem());
                }
              }*/
          if (project.title == "Project a") {
            return [{
              "Plate": "plate1",
              wellLabel: "DataFlex Data",
              "Well": "A01",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "12332",
              "p-cJun ": "23847",
              "p-CREB ": "887",
              "p-ERK1/2": "5632",
              "p-GSK-3a/b": "11625.5",
              "p-IkBa": "2736",
              "p-JNK": "1848.5",
              "p-MEK1": "19930.5",
              measurement2: "445",
              "p-p70-S6K": "16378",
              "p-p90-RSK": "4236",
              "p-STAT2": "601",
              "p-STAT3 (Tyr705)": "171",
              "p-IRS-1": "2307.5",
              "p-Histone H3": "1941",
              "p-Hsp27": "916",
              "p-p53 (Ser37)": "1112",
              "p-Tyk2": "3068",
              "p-ATF-2": "3728.5",
              "p-NFkB": "137.5",
              "p-p53 (Ser46)": "60.5",
              "p-STAT3 (Ser727)": "1127.5",
              measurementType: "p-p38", "p-STAT6": "240"
            }, {
              "Plate": "",
              wellLabel: "DataFlex Data",
              "Well": "A02",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "11031.5",
              "p-cJun ": "24916.5",
              "p-CREB ": "1371",
              "p-ERK1/2": "5885",
              "p-GSK-3a/b": "16443",
              "p-IkBa": "2623.5",
              "p-JNK": "2840",
              "p-MEK1": "23195",
              measurement2: "483",
              "p-p70-S6K": "18465",
              "p-p90-RSK": "5514.5",
              "p-STAT2": "803",
              "p-STAT3 (Tyr705)": "218",
              "p-IRS-1": "1812.5",
              "p-Histone H3": "2476",
              "p-Hsp27": "1732",
              "p-p53 (Ser37)": "861",
              "p-Tyk2": "3378",
              "p-ATF-2": "3710",
              "p-NFkB": "134",
              "p-p53 (Ser46)": "62",
              "p-STAT3 (Ser727)": "1040.5",
              measurementType: "p-p38", "p-STAT6": "223"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "A03",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "12249",
              "p-cJun ": "24292.5",
              "p-CREB ": "1403",
              "p-ERK1/2": "5449",
              "p-GSK-3a/b": "13547",
              "p-IkBa": "3263.5",
              "p-JNK": "3054",
              "p-MEK1": "24151.5",
              measurement2: "349",
              "p-p70-S6K": "16530.5",
              "p-p90-RSK": "5213",
              "p-STAT2": "755.5",
              "p-STAT3 (Tyr705)": "212",
              "p-IRS-1": "1672",
              "p-Histone H3": "1484",
              "p-Hsp27": "905",
              "p-p53 (Ser37)": "848",
              "p-Tyk2": "2798.5",
              "p-ATF-2": "4067",
              "p-NFkB": "134",
              "p-p53 (Ser46)": "58",
              "p-STAT3 (Ser727)": "913.5",
              measurementType: "p-p38", "p-STAT6": "238"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": ".",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "12681",
              "p-cJun ": "24782",
              "p-CREB ": "1172",
              "p-ERK1/2": "5983",
              "p-GSK-3a/b": "13588.5",
              "p-IkBa": "2875.5",
              "p-JNK": "2583",
              "p-MEK1": "25000.5",
              measurement2: "300",
              "p-p70-S6K": "16456.5",
              "p-p90-RSK": "4626",
              "p-STAT2": "895",
              "p-STAT3 (Tyr705)": "212",
              "p-IRS-1": "1490.5",
              "p-Histone H3": "1494",
              "p-Hsp27": "866",
              "p-p53 (Ser37)": "705",
              "p-Tyk2": "2433",
              "p-ATF-2": "3858",
              "p-NFkB": "118",
              "p-p53 (Ser46)": "47",
              "p-STAT3 (Ser727)": "939",
              measurementType: "p-p38", "p-STAT6": "222.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "11953",
              "p-cJun ": "23490",
              "p-CREB ": "1115",
              "p-ERK1/2": "5593",
              "p-GSK-3a/b": "14516",
              "p-IkBa": "2088",
              "p-JNK": "2133.5",
              "p-MEK1": "25055",
              measurement2: "257",
              "p-p70-S6K": "16113",
              "p-p90-RSK": "6590",
              "p-STAT2": "676",
              "p-STAT3 (Tyr705)": "209",
              "p-IRS-1": "1712.5",
              "p-Histone H3": "1808",
              "p-Hsp27": "978",
              "p-p53 (Ser37)": "1091",
              "p-Tyk2": "3275",
              "p-ATF-2": "3850",
              "p-NFkB": "132",
              "p-p53 (Ser46)": "40",
              "p-STAT3 (Ser727)": "1000.5",
              measurementType: "p-p38", "p-STAT6": "208"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "12431",
              "p-cJun ": "20249",
              "p-CREB ": "1366",
              "p-ERK1/2": "6410.5",
              "p-GSK-3a/b": "14813",
              "p-IkBa": "1827",
              "p-JNK": "2010",
              "p-MEK1": "25040.5",
              measurement2: "247",
              "p-p70-S6K": "15559.5",
              "p-p90-RSK": "5731",
              "p-STAT2": "1116",
              "p-STAT3 (Tyr705)": "258",
              "p-IRS-1": "1364",
              "p-Histone H3": "1449",
              "p-Hsp27": "936",
              "p-p53 (Ser37)": "933",
              "p-Tyk2": "3017.5",
              "p-ATF-2": "3405",
              "p-NFkB": "138",
              "p-p53 (Ser46)": "48",
              "p-STAT3 (Ser727)": "915",
              measurementType: "p-p38", "p-STAT6": "215"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "13354",
              "p-cJun ": "20575.5",
              "p-CREB ": "1314",
              "p-ERK1/2": "5136.5",
              "p-GSK-3a/b": "16092",
              "p-IkBa": "2209",
              "p-JNK": "2122",
              "p-MEK1": "24230",
              measurement2: "281",
              "p-p70-S6K": "13299.5",
              "p-p90-RSK": "4355",
              "p-STAT2": "741.5",
              "p-STAT3 (Tyr705)": "217",
              "p-IRS-1": "1768",
              "p-Histone H3": "1260",
              "p-Hsp27": "859",
              "p-p53 (Ser37)": "838",
              "p-Tyk2": "2547.5",
              "p-ATF-2": "3855",
              "p-NFkB": "168",
              "p-p53 (Ser46)": "45",
              "p-STAT3 (Ser727)": "1009.5",
              measurementType: "p-p38", "p-STAT6": "191"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1a",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "8816",
              "p-cJun ": "14669",
              "p-CREB ": "1114",
              "p-ERK1/2": "5347",
              "p-GSK-3a/b": "9981",
              "p-IkBa": "4588",
              "p-JNK": "1796",
              "p-MEK1": "23715.5",
              measurement2: "220",
              "p-p70-S6K": "11489",
              "p-p90-RSK": "3704.5",
              "p-STAT2": "450",
              "p-STAT3 (Tyr705)": "233",
              "p-IRS-1": "1132.5",
              "p-Histone H3": "1101",
              "p-Hsp27": "886",
              "p-p53 (Ser37)": "508.5",
              "p-Tyk2": "1767",
              "p-ATF-2": "2943.5",
              "p-NFkB": "201",
              "p-p53 (Ser46)": "44",
              "p-STAT3 (Ser727)": "1088.5",
              measurementType: "p-p38", "p-STAT6": "254.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "6110",
              "p-cJun ": "15687",
              "p-CREB ": "562",
              "p-ERK1/2": "3944",
              "p-GSK-3a/b": "9620",
              "p-IkBa": "1409",
              "p-JNK": "1140",
              "p-MEK1": "18505",
              measurement2: "303.5",
              "p-p70-S6K": "10039",
              "p-p90-RSK": "3020.5",
              "p-STAT2": "473.5",
              "p-STAT3 (Tyr705)": "124",
              "p-IRS-1": "819",
              "p-Histone H3": "50.5",
              "p-Hsp27": "46.5",
              "p-p53 (Ser37)": "426",
              "p-Tyk2": "1973",
              "p-ATF-2": "4548",
              "p-NFkB": "164",
              "p-p53 (Ser46)": "59.5",
              "p-STAT3 (Ser727)": "1248",
              measurementType: "p-p38", "p-STAT6": "250"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "9455",
              "p-cJun ": "24217",
              "p-CREB ": "1108.5",
              "p-ERK1/2": "4869",
              "p-GSK-3a/b": "14039",
              "p-IkBa": "2402",
              "p-JNK": "2151",
              "p-MEK1": "25002.5",
              measurement2: "362",
              "p-p70-S6K": "15092.5",
              "p-p90-RSK": "4394",
              "p-STAT2": "886.5",
              "p-STAT3 (Tyr705)": "198",
              "p-IRS-1": "1527.5",
              "p-Histone H3": "1477",
              "p-Hsp27": "930.5",
              "p-p53 (Ser37)": "857.5",
              "p-Tyk2": "2950",
              "p-ATF-2": "4459",
              "p-NFkB": "146",
              "p-p53 (Ser46)": "50",
              "p-STAT3 (Ser727)": "1158",
              measurementType: "p-p38", "p-STAT6": "260"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "12397",
              "p-cJun ": "25159",
              "p-CREB ": "1339",
              "p-ERK1/2": "5802",
              "p-GSK-3a/b": "18696",
              "p-IkBa": "2254",
              "p-JNK": "2769",
              "p-MEK1": "19850.5",
              measurement2: "363",
              "p-p70-S6K": "16220",
              "p-p90-RSK": "5839",
              "p-STAT2": "1028",
              "p-STAT3 (Tyr705)": "215",
              "p-IRS-1": "1696",
              "p-Histone H3": "1518",
              "p-Hsp27": "1144",
              "p-p53 (Ser37)": "996",
              "p-Tyk2": "2750",
              "p-ATF-2": "3981",
              "p-NFkB": "121",
              "p-p53 (Ser46)": "51",
              "p-STAT3 (Ser727)": "1093",
              measurementType: "p-p38", "p-STAT6": "237"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "6317",
              "p-cJun ": "15383",
              "p-CREB ": "679.5",
              "p-ERK1/2": "3318",
              "p-GSK-3a/b": "6991",
              "p-IkBa": "1265.5",
              "p-JNK": "1372",
              "p-MEK1": "14728",
              measurement2: "262",
              "p-p70-S6K": "8173.5",
              "p-p90-RSK": "2359",
              "p-STAT2": "964.5",
              "p-STAT3 (Tyr705)": "131",
              "p-IRS-1": "1247.5",
              "p-Histone H3": "613",
              "p-Hsp27": "693",
              "p-p53 (Ser37)": "401",
              "p-Tyk2": "1468.5",
              "p-ATF-2": "3853",
              "p-NFkB": "112.5",
              "p-p53 (Ser46)": "49.5",
              "p-STAT3 (Ser727)": "1039.5",
              measurementType: "p-p38", "p-STAT6": "208"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "8302",
              "p-cJun ": "22217",
              "p-CREB ": "1030",
              "p-ERK1/2": "4731",
              "p-GSK-3a/b": "11362.5",
              "p-IkBa": "1836",
              "p-JNK": "2256",
              "p-MEK1": "19839",
              measurement2: "284",
              "p-p70-S6K": "13566.5",
              "p-p90-RSK": "4237",
              "p-STAT2": "535.5",
              "p-STAT3 (Tyr705)": "173.5",
              "p-IRS-1": "1353",
              "p-Histone H3": "1903.5",
              "p-Hsp27": "1511.5",
              "p-p53 (Ser37)": "538",
              "p-Tyk2": "2546",
              "p-ATF-2": "3739",
              "p-NFkB": "159",
              "p-p53 (Ser46)": "50.5",
              "p-STAT3 (Ser727)": "1232.5",
              measurementType: "p-p38", "p-STAT6": "208"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "13752.5",
              "p-cJun ": "20233.5",
              "p-CREB ": "1233",
              "p-ERK1/2": "5516",
              "p-GSK-3a/b": "15650",
              "p-IkBa": "1839",
              "p-JNK": "2597",
              "p-MEK1": "23180.5",
              measurement2: "300",
              "p-p70-S6K": "17762",
              "p-p90-RSK": "4691",
              "p-STAT2": "651",
              "p-STAT3 (Tyr705)": "228.5",
              "p-IRS-1": "1757",
              "p-Histone H3": "1563",
              "p-Hsp27": "957",
              "p-p53 (Ser37)": "1162",
              "p-Tyk2": "3122",
              "p-ATF-2": "3724",
              "p-NFkB": "184.5",
              "p-p53 (Ser46)": "43",
              "p-STAT3 (Ser727)": "940.5",
              measurementType: "p-p38", "p-STAT6": "219.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "8625.5",
              "p-cJun ": "16335",
              "p-CREB ": "1018",
              "p-ERK1/2": "5342",
              "p-GSK-3a/b": "13455",
              "p-IkBa": "1769",
              "p-JNK": "2260",
              "p-MEK1": "23102.5",
              measurement2: "273",
              "p-p70-S6K": "14047",
              "p-p90-RSK": "3067",
              "p-STAT2": "526",
              "p-STAT3 (Tyr705)": "198",
              "p-IRS-1": "1288",
              "p-Histone H3": "1292",
              "p-Hsp27": "874",
              "p-p53 (Ser37)": "713",
              "p-Tyk2": "2865",
              "p-ATF-2": "3307",
              "p-NFkB": "149",
              "p-p53 (Ser46)": "46",
              "p-STAT3 (Ser727)": "1082",
              measurementType: "p-p38", "p-STAT6": "193.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-1b",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "9500",
              "p-cJun ": "15312",
              "p-CREB ": "1116",
              "p-ERK1/2": "4858",
              "p-GSK-3a/b": "14824",
              "p-IkBa": "4712",
              "p-JNK": "1804.5",
              "p-MEK1": "24131.5",
              measurement2: "231",
              "p-p70-S6K": "15434",
              "p-p90-RSK": "5044.5",
              "p-STAT2": "606.5",
              "p-STAT3 (Tyr705)": "284",
              "p-IRS-1": "1332",
              "p-Histone H3": "1252",
              "p-Hsp27": "879",
              "p-p53 (Ser37)": "520",
              "p-Tyk2": "2013",
              "p-ATF-2": "2986",
              "p-NFkB": "198",
              "p-p53 (Ser46)": "47",
              "p-STAT3 (Ser727)": "1173.5",
              measurementType: "p-p38", "p-STAT6": "216"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "15479.5",
              "p-cJun ": "23962",
              "p-CREB ": "1301",
              "p-ERK1/2": "8469.5",
              "p-GSK-3a/b": "15719",
              "p-IkBa": "1631.5",
              "p-JNK": "1808",
              "p-MEK1": "27384",
              measurement2: "250",
              "p-p70-S6K": "20041.5",
              "p-p90-RSK": "7769.5",
              "p-STAT2": "718",
              "p-STAT3 (Tyr705)": "90",
              "p-IRS-1": "1358",
              "p-Histone H3": "1760.5",
              "p-Hsp27": "1122.5",
              "p-p53 (Ser37)": "544",
              "p-Tyk2": "2478",
              "p-ATF-2": "2443",
              "p-NFkB": "128",
              "p-p53 (Ser46)": "44",
              "p-STAT3 (Ser727)": "1435",
              measurementType: "p-p38", "p-STAT6": "218"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "13138.5",
              "p-cJun ": "23990",
              "p-CREB ": "1450",
              "p-ERK1/2": "8179.5",
              "p-GSK-3a/b": "20377",
              "p-IkBa": "2225.5",
              "p-JNK": "2274.5",
              "p-MEK1": "28088",
              measurement2: "261",
              "p-p70-S6K": "21542.5",
              "p-p90-RSK": "8314",
              "p-STAT2": "1755",
              "p-STAT3 (Tyr705)": "193.5",
              "p-IRS-1": "1787.5",
              "p-Histone H3": "1849",
              "p-Hsp27": "1269",
              "p-p53 (Ser37)": "889.5",
              "p-Tyk2": "3204",
              "p-ATF-2": "2482",
              "p-NFkB": "144.5",
              "p-p53 (Ser46)": "36",
              "p-STAT3 (Ser727)": "1346",
              measurementType: "p-p38", "p-STAT6": "232.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "14729",
              "p-cJun ": "24052",
              "p-CREB ": "1663",
              "p-ERK1/2": "12745",
              "p-GSK-3a/b": "24037",
              "p-IkBa": "3049",
              "p-JNK": "2772",
              "p-MEK1": "24825.5",
              measurement2: "322",
              "p-p70-S6K": "23840",
              "p-p90-RSK": "9117",
              "p-STAT2": "1070",
              "p-STAT3 (Tyr705)": "242",
              "p-IRS-1": "2170",
              "p-Histone H3": "1771.5",
              "p-Hsp27": "1294",
              "p-p53 (Ser37)": "1249",
              "p-Tyk2": "3416",
              "p-ATF-2": "1979",
              "p-NFkB": "167",
              "p-p53 (Ser46)": "43",
              "p-STAT3 (Ser727)": "1339",
              measurementType: "p-p38", "p-STAT6": "217"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "12590.5",
              "p-cJun ": "23488.5",
              "p-CREB ": "1308",
              "p-ERK1/2": "9897.5",
              "p-GSK-3a/b": "16881",
              "p-IkBa": "2227",
              "p-JNK": "2444",
              "p-MEK1": "27647",
              measurement2: "360",
              "p-p70-S6K": "20730",
              "p-p90-RSK": "7854.5",
              "p-STAT2": "737.5",
              "p-STAT3 (Tyr705)": "224",
              "p-IRS-1": "1723",
              "p-Histone H3": "1549",
              "p-Hsp27": "1009",
              "p-p53 (Ser37)": "849",
              "p-Tyk2": "2972",
              "p-ATF-2": "2599",
              "p-NFkB": "151",
              "p-p53 (Ser46)": "39",
              "p-STAT3 (Ser727)": "1236",
              measurementType: "p-p38", "p-STAT6": "198"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "10276.5",
              "p-cJun ": "16285",
              "p-CREB ": "1454",
              "p-ERK1/2": "12855",
              "p-GSK-3a/b": "16998",
              "p-IkBa": "1430",
              "p-JNK": "1922",
              "p-MEK1": "25271",
              measurement2: "250",
              "p-p70-S6K": "17126.5",
              "p-p90-RSK": "9602",
              "p-STAT2": "505",
              "p-STAT3 (Tyr705)": "208.5",
              "p-IRS-1": "1134",
              "p-Histone H3": "1296",
              "p-Hsp27": "775",
              "p-p53 (Ser37)": "538",
              "p-Tyk2": "2325",
              "p-ATF-2": "3792",
              "p-NFkB": "113",
              "p-p53 (Ser46)": "40",
              "p-STAT3 (Ser727)": "1375",
              measurementType: "p-p38", "p-STAT6": "200"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "14629",
              "p-cJun ": "15830",
              "p-CREB ": "1960",
              "p-ERK1/2": "13579.5",
              "p-GSK-3a/b": "17656.5",
              "p-IkBa": "927",
              "p-JNK": "1626",
              "p-MEK1": "27124",
              measurement2: "239",
              "p-p70-S6K": "15970.5",
              "p-p90-RSK": "10685",
              "p-STAT2": "925.5",
              "p-STAT3 (Tyr705)": "273.5",
              "p-IRS-1": "1751",
              "p-Histone H3": "1361",
              "p-Hsp27": "841",
              "p-p53 (Ser37)": "481",
              "p-Tyk2": "2595",
              "p-ATF-2": "3699.5",
              "p-NFkB": "117",
              "p-p53 (Ser46)": "51.5",
              "p-STAT3 (Ser727)": "1492.5",
              measurementType: "p-p38", "p-STAT6": "231"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "11159",
              "p-cJun ": "9629",
              "p-CREB ": "1403",
              "p-ERK1/2": "9865.5",
              "p-GSK-3a/b": "8658",
              "p-IkBa": "718",
              "p-JNK": "936",
              "p-MEK1": "24502.5",
              measurement2: "184",
              "p-p70-S6K": "14756.5",
              "p-p90-RSK": "4496",
              "p-STAT2": "859",
              "p-STAT3 (Tyr705)": "311",
              "p-IRS-1": "1403",
              "p-Histone H3": "1168",
              "p-Hsp27": "803",
              "p-p53 (Ser37)": "445",
              "p-Tyk2": "1958",
              "p-ATF-2": "3243",
              "p-NFkB": "113",
              "p-p53 (Ser46)": "47",
              "p-STAT3 (Ser727)": "1475",
              measurementType: "p-p38", "p-STAT6": "228"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "20422",
              "p-cJun ": "12931.5",
              "p-CREB ": "1537",
              "p-ERK1/2": "13698",
              "p-GSK-3a/b": "17357",
              "p-IkBa": "1138",
              "p-JNK": "1298",
              "p-MEK1": "28040.5",
              measurement2: "136",
              "p-p70-S6K": "18349",
              "p-p90-RSK": "7068.5",
              "p-STAT2": "896.5",
              "p-STAT3 (Tyr705)": "380",
              "p-IRS-1": "1879.5",
              "p-Histone H3": "1300",
              "p-Hsp27": "987.5",
              "p-p53 (Ser37)": "516",
              "p-Tyk2": "3066",
              "p-ATF-2": "2402",
              "p-NFkB": "124",
              "p-p53 (Ser46)": "38",
              "p-STAT3 (Ser727)": "1266.5",
              measurementType: "p-p38", "p-STAT6": "259"
            }, {
              "Plate": "plate2",
              "Well": "A01",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "12375",
              "p-cJun ": "22637",
              "p-CREB ": "1006",
              "p-ERK1/2": "3711",
              "p-GSK-3a/b": "11236",
              "p-IkBa": "906",
              "p-JNK": "1193",
              "p-MEK1": "22940",
              measurement2: "333",
              "p-p70-S6K": "11780",
              "p-p90-RSK": "4216",
              "p-STAT2": "1876",
              "p-STAT3 (Tyr705)": "235.5",
              "p-IRS-1": "2356",
              "p-Histone H3": "1433",
              "p-Hsp27": "745.5",
              "p-p53 (Ser37)": "718",
              "p-Tyk2": "2338.5",
              "p-ATF-2": "3895",
              "p-NFkB": "123",
              "p-p53 (Ser46)": "46",
              "p-STAT3 (Ser727)": "2025.5",
              measurementType: "p-p38", "p-STAT6": "210.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "7779.5",
              "p-cJun ": "26441.5",
              "p-CREB ": "1078",
              "p-ERK1/2": "3882",
              "p-GSK-3a/b": "13550",
              "p-IkBa": "1128.5",
              "p-JNK": "2070",
              "p-MEK1": "24162.5",
              measurement2: "317.5",
              "p-p70-S6K": "16682",
              "p-p90-RSK": "4660",
              "p-STAT2": "3350",
              "p-STAT3 (Tyr705)": "419",
              "p-IRS-1": "2108",
              "p-Histone H3": "1340",
              "p-Hsp27": "820",
              "p-p53 (Ser37)": "677",
              "p-Tyk2": "2751",
              "p-ATF-2": "4872.5",
              "p-NFkB": "116",
              "p-p53 (Ser46)": "60",
              "p-STAT3 (Ser727)": "1482.5",
              measurementType: "p-p38", "p-STAT6": "208"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "8548",
              "p-cJun ": "25918",
              "p-CREB ": "1247",
              "p-ERK1/2": "5037.5",
              "p-GSK-3a/b": "17637.5",
              "p-IkBa": "1485",
              "p-JNK": "3249",
              "p-MEK1": "21257",
              measurement2: "563",
              "p-p70-S6K": "20178",
              "p-p90-RSK": "5418",
              "p-STAT2": "5742.5",
              "p-STAT3 (Tyr705)": "981",
              "p-IRS-1": "2141",
              "p-Histone H3": "1674.5",
              "p-Hsp27": "980",
              "p-p53 (Ser37)": "710",
              "p-Tyk2": "3291.5",
              "p-ATF-2": "4469.5",
              "p-NFkB": "126.5",
              "p-p53 (Ser46)": "47",
              "p-STAT3 (Ser727)": "997",
              measurementType: "p-p38", "p-STAT6": "204.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "11620",
              "p-cJun ": "25415",
              "p-CREB ": "1280",
              "p-ERK1/2": "6185",
              "p-GSK-3a/b": "20623",
              "p-IkBa": "1149",
              "p-JNK": "3324",
              "p-MEK1": "12805",
              measurement2: "441",
              "p-p70-S6K": "23395",
              "p-p90-RSK": "5870",
              "p-STAT2": "10329.5",
              "p-STAT3 (Tyr705)": "1336",
              "p-IRS-1": "2222",
              "p-Histone H3": "1604.5",
              "p-Hsp27": "1094",
              "p-p53 (Ser37)": "833.5",
              "p-Tyk2": "3804.5",
              "p-ATF-2": "6009",
              "p-NFkB": "106",
              "p-p53 (Ser46)": "60.5",
              "p-STAT3 (Ser727)": "1183.5",
              measurementType: "p-p38", "p-STAT6": "254"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "GM-CSF",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "",
              "p-cJun ": "",
              "p-CREB ": "",
              "p-ERK1/2": "",
              "p-GSK-3a/b": "",
              "p-IkBa": "",
              "p-JNK": "",
              "p-MEK1": "",
              measurement2: "",
              "p-p70-S6K": "",
              "p-p90-RSK": "",
              "p-STAT2": "",
              "p-STAT3 (Tyr705)": "",
              "p-IRS-1": "",
              "p-Histone H3": "",
              "p-Hsp27": "",
              "p-p53 (Ser37)": "",
              "p-Tyk2": "",
              "p-ATF-2": "5475",
              "p-NFkB": "97",
              "p-p53 (Ser46)": "50.5",
              "p-STAT3 (Ser727)": "1331",
              measurementType: "p-p38", "p-STAT6": "247"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "14550",
              "p-cJun ": "11871",
              "p-CREB ": "1262.5",
              "p-ERK1/2": "7544",
              "p-GSK-3a/b": "16046",
              "p-IkBa": "972",
              "p-JNK": "1775",
              "p-MEK1": "19213.5",
              measurement2: "197.5",
              "p-p70-S6K": "15440",
              "p-p90-RSK": "6296",
              "p-STAT2": "7383",
              "p-STAT3 (Tyr705)": "1188",
              "p-IRS-1": "1812.5",
              "p-Histone H3": "1385",
              "p-Hsp27": "1075",
              "p-p53 (Ser37)": "993",
              "p-Tyk2": "3481",
              "p-ATF-2": "3116.5",
              "p-NFkB": "114",
              "p-p53 (Ser46)": "40",
              "p-STAT3 (Ser727)": "1033.5",
              measurementType: "p-p38", "p-STAT6": "302.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "15188.5",
              "p-cJun ": "9625",
              "p-CREB ": "840",
              "p-ERK1/2": "6370.5",
              "p-GSK-3a/b": "12374",
              "p-IkBa": "1068",
              "p-JNK": "1442",
              "p-MEK1": "24601.5",
              measurement2: "167",
              "p-p70-S6K": "14515",
              "p-p90-RSK": "4852.5",
              "p-STAT2": "8703",
              "p-STAT3 (Tyr705)": "1239",
              "p-IRS-1": "1883",
              "p-Histone H3": "1512",
              "p-Hsp27": "802",
              "p-p53 (Ser37)": "796",
              "p-Tyk2": "3498",
              "p-ATF-2": "1608",
              "p-NFkB": "75",
              "p-p53 (Ser46)": "41",
              "p-STAT3 (Ser727)": "824",
              measurementType: "p-p38", "p-STAT6": "211"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IFN-g",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "10900",
              "p-cJun ": "9563",
              "p-CREB ": "1330",
              "p-ERK1/2": "5681.5",
              "p-GSK-3a/b": "15288",
              "p-IkBa": "1177",
              "p-JNK": "1107",
              "p-MEK1": "23333",
              measurement2: "130",
              "p-p70-S6K": "16841",
              "p-p90-RSK": "5197.5",
              "p-STAT2": "6059",
              "p-STAT3 (Tyr705)": "845",
              "p-IRS-1": "1245",
              "p-Histone H3": "1323.5",
              "p-Hsp27": "879",
              "p-p53 (Ser37)": "662",
              "p-Tyk2": "3061",
              "p-ATF-2": "2098.5",
              "p-NFkB": "88",
              "p-p53 (Ser46)": "33",
              "p-STAT3 (Ser727)": "1134",
              measurementType: "p-p38", "p-STAT6": "228"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "12690",
              "p-cJun ": "22831",
              "p-CREB ": "1056",
              "p-ERK1/2": "6495",
              "p-GSK-3a/b": "16213",
              "p-IkBa": "1128",
              "p-JNK": "1635.5",
              "p-MEK1": "14682",
              measurement2: "455",
              "p-p70-S6K": "12311",
              "p-p90-RSK": "4937",
              "p-STAT2": "342.5",
              "p-STAT3 (Tyr705)": "1705.5",
              "p-IRS-1": "2103",
              "p-Histone H3": "1267",
              "p-Hsp27": "869",
              "p-p53 (Ser37)": "431.5",
              "p-Tyk2": "3477",
              "p-ATF-2": "3628",
              "p-NFkB": "102.5",
              "p-p53 (Ser46)": "50.5",
              "p-STAT3 (Ser727)": "1574",
              measurementType: "p-p38", "p-STAT6": "207"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "12348",
              "p-cJun ": "24557.5",
              "p-CREB ": "1630.5",
              "p-ERK1/2": "7386",
              "p-GSK-3a/b": "19895",
              "p-IkBa": "1026",
              "p-JNK": "2514",
              "p-MEK1": "26209.5",
              measurement2: "285",
              "p-p70-S6K": "17981",
              "p-p90-RSK": "6832",
              "p-STAT2": "926",
              "p-STAT3 (Tyr705)": "1667.5",
              "p-IRS-1": "2244",
              "p-Histone H3": "2132",
              "p-Hsp27": "1430.5",
              "p-p53 (Ser37)": "905.5",
              "p-Tyk2": "4585",
              "p-ATF-2": "3986",
              "p-NFkB": "100",
              "p-p53 (Ser46)": "44",
              "p-STAT3 (Ser727)": "1217.5",
              measurementType: "p-p38", "p-STAT6": "222"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "9359.5",
              "p-cJun ": "21197",
              "p-CREB ": "801",
              "p-ERK1/2": "4803.5",
              "p-GSK-3a/b": "15319",
              "p-IkBa": "835",
              "p-JNK": "1594",
              "p-MEK1": "9544",
              measurement2: "216",
              "p-p70-S6K": "15660",
              "p-p90-RSK": "4574.5",
              "p-STAT2": "938",
              "p-STAT3 (Tyr705)": "1296",
              "p-IRS-1": "1368",
              "p-Histone H3": "1386.5",
              "p-Hsp27": "919",
              "p-p53 (Ser37)": "781",
              "p-Tyk2": "2806.5",
              "p-ATF-2": "3207",
              "p-NFkB": "76.5",
              "p-p53 (Ser46)": "37",
              "p-STAT3 (Ser727)": "902",
              measurementType: "p-p38", "p-STAT6": "167"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "11760",
              "p-cJun ": "23735",
              "p-CREB ": "541.5",
              "p-ERK1/2": "2903",
              "p-GSK-3a/b": "13119",
              "p-IkBa": "1374.5",
              "p-JNK": "2428",
              "p-MEK1": "12499",
              measurement2: "186",
              "p-p70-S6K": "13328",
              "p-p90-RSK": "4745",
              "p-STAT2": "397.5",
              "p-STAT3 (Tyr705)": "1249",
              "p-IRS-1": "1852",
              "p-Histone H3": "1070.5",
              "p-Hsp27": "1374",
              "p-p53 (Ser37)": "244",
              "p-Tyk2": "2466",
              "p-ATF-2": "4526",
              "p-NFkB": "114",
              "p-p53 (Ser46)": "51.5",
              "p-STAT3 (Ser727)": "1096.5",
              measurementType: "p-p38", "p-STAT6": "178"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "8940",
              "p-cJun ": "23656",
              "p-CREB ": "1059",
              "p-ERK1/2": "6028",
              "p-GSK-3a/b": "17505",
              "p-IkBa": "579.5",
              "p-JNK": "2502",
              "p-MEK1": "24220",
              measurement2: "306",
              "p-p70-S6K": "16858.5",
              "p-p90-RSK": "3607.5",
              "p-STAT2": "650",
              "p-STAT3 (Tyr705)": "1738",
              "p-IRS-1": "1794.5",
              "p-Histone H3": "1616",
              "p-Hsp27": "895",
              "p-p53 (Ser37)": "958",
              "p-Tyk2": "1754.5",
              "p-ATF-2": "5537",
              "p-NFkB": "110",
              "p-p53 (Ser46)": "53",
              "p-STAT3 (Ser727)": "1405",
              measurementType: "p-p38", "p-STAT6": "200.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "10969.5",
              "p-cJun ": "6754",
              "p-CREB ": "1086.5",
              "p-ERK1/2": "6495",
              "p-GSK-3a/b": "12959",
              "p-IkBa": "831.5",
              "p-JNK": "1101",
              "p-MEK1": "16683.5",
              measurement2: "133",
              "p-p70-S6K": "13242",
              "p-p90-RSK": "2284.5",
              "p-STAT2": "569.5",
              "p-STAT3 (Tyr705)": "8560",
              "p-IRS-1": "1191",
              "p-Histone H3": "972",
              "p-Hsp27": "580",
              "p-p53 (Ser37)": "327.5",
              "p-Tyk2": "1791",
              "p-ATF-2": "2539",
              "p-NFkB": "94.5",
              "p-p53 (Ser46)": "32",
              "p-STAT3 (Ser727)": "1451.5",
              measurementType: "p-p38", "p-STAT6": "206"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "8497.5",
              "p-cJun ": "6456",
              "p-CREB ": "683",
              "p-ERK1/2": "4877",
              "p-GSK-3a/b": "12362.5",
              "p-IkBa": "817",
              "p-JNK": "1141.5",
              "p-MEK1": "",
              measurement2: "91",
              "p-p70-S6K": "12132",
              "p-p90-RSK": "1387.5",
              "p-STAT2": "629",
              "p-STAT3 (Tyr705)": "5102",
              "p-IRS-1": "1249",
              "p-Histone H3": "681",
              "p-Hsp27": "366",
              "p-p53 (Ser37)": "641",
              "p-Tyk2": "2412.5",
              "p-ATF-2": "2353",
              "p-NFkB": "115",
              "p-p53 (Ser46)": "37",
              "p-STAT3 (Ser727)": "1439",
              measurementType: "p-p38", "p-STAT6": "233"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-6",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "12964.5",
              "p-cJun ": "9821",
              "p-CREB ": "1121",
              "p-ERK1/2": "4708",
              "p-GSK-3a/b": "11388",
              "p-IkBa": "1013",
              "p-JNK": "1261",
              "p-MEK1": "19208",
              measurement2: "122.5",
              "p-p70-S6K": "16889",
              "p-p90-RSK": "5476.5",
              "p-STAT2": "677",
              "p-STAT3 (Tyr705)": "6082",
              "p-IRS-1": "1417.5",
              "p-Histone H3": "1376.5",
              "p-Hsp27": "1029",
              "p-p53 (Ser37)": "643.5",
              "p-Tyk2": "3211",
              "p-ATF-2": "2275",
              "p-NFkB": "109",
              "p-p53 (Ser46)": "37",
              "p-STAT3 (Ser727)": "1362",
              measurementType: "p-p38", "p-STAT6": "223"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "11250.5",
              "p-cJun ": "23983",
              "p-CREB ": "1038",
              "p-ERK1/2": "5521",
              "p-GSK-3a/b": "14007.5",
              "p-IkBa": "1159",
              "p-JNK": "1822",
              "p-MEK1": "24033.5",
              measurement2: "483",
              "p-p70-S6K": "18120",
              "p-p90-RSK": "5131",
              "p-STAT2": "730",
              "p-STAT3 (Tyr705)": "3657",
              "p-IRS-1": "1426.5",
              "p-Histone H3": "949.5",
              "p-Hsp27": "729",
              "p-p53 (Ser37)": "479",
              "p-Tyk2": "2282",
              "p-ATF-2": "3953",
              "p-NFkB": "99",
              "p-p53 (Ser46)": "49",
              "p-STAT3 (Ser727)": "1723",
              measurementType: "p-p38", "p-STAT6": "165"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "10093",
              "p-cJun ": "23599",
              "p-CREB ": "1214",
              "p-ERK1/2": "4566.5",
              "p-GSK-3a/b": "15489",
              "p-IkBa": "945",
              "p-JNK": "2014",
              "p-MEK1": "23982",
              measurement2: "302",
              "p-p70-S6K": "17560.5",
              "p-p90-RSK": "5226",
              "p-STAT2": "612.5",
              "p-STAT3 (Tyr705)": "4604",
              "p-IRS-1": "1713",
              "p-Histone H3": "1588",
              "p-Hsp27": "1085",
              "p-p53 (Ser37)": "730",
              "p-Tyk2": "2487",
              "p-ATF-2": "3793",
              "p-NFkB": "60",
              "p-p53 (Ser46)": "26",
              "p-STAT3 (Ser727)": "1147",
              measurementType: "p-p38", "p-STAT6": "168.5"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "9615.5",
              "p-cJun ": "24686",
              "p-CREB ": "1052",
              "p-ERK1/2": "4238.5",
              "p-GSK-3a/b": "18675.5",
              "p-IkBa": "1218",
              "p-JNK": "3115",
              "p-MEK1": "24307.5",
              measurement2: "361.5",
              "p-p70-S6K": "20976",
              "p-p90-RSK": "5770",
              "p-STAT2": "850",
              "p-STAT3 (Tyr705)": "5241.5",
              "p-IRS-1": "2111",
              "p-Histone H3": "1674",
              "p-Hsp27": "1277",
              "p-p53 (Ser37)": "931",
              "p-Tyk2": "3081",
              "p-ATF-2": "4148",
              "p-NFkB": "94",
              "p-p53 (Ser46)": "45.5",
              "p-STAT3 (Ser727)": "1447",
              measurementType: "p-p38", "p-STAT6": "195"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "9018",
              "p-cJun ": "24547.5",
              "p-CREB ": "870",
              "p-ERK1/2": "4779",
              "p-GSK-3a/b": "16884",
              "p-IkBa": "1188",
              "p-JNK": "3014",
              "p-MEK1": "24368.5",
              measurement2: "446",
              "p-p70-S6K": "19848",
              "p-p90-RSK": "5361",
              "p-STAT2": "694.5",
              "p-STAT3 (Tyr705)": "5027.5",
              "p-IRS-1": "2415",
              "p-Histone H3": "1782",
              "p-Hsp27": "1190",
              "p-p53 (Ser37)": "1175.5",
              "p-Tyk2": "3197",
              "p-ATF-2": "4846",
              "p-NFkB": "79.5",
              "p-p53 (Ser46)": "53.5",
              "p-STAT3 (Ser727)": "1298",
              measurementType: "p-p38", "p-STAT6": "139"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "5492",
              "p-cJun ": "16442",
              "p-CREB ": "779",
              "p-ERK1/2": "3506",
              "p-GSK-3a/b": "10942",
              "p-IkBa": "717",
              "p-JNK": "2631",
              "p-MEK1": "",
              measurement2: "285",
              "p-p70-S6K": "13219.5",
              "p-p90-RSK": "3273",
              "p-STAT2": "488",
              "p-STAT3 (Tyr705)": "7476",
              "p-IRS-1": "1393",
              "p-Histone H3": "1284",
              "p-Hsp27": "709",
              "p-p53 (Ser37)": "670",
              "p-Tyk2": "2618.5",
              "p-ATF-2": "6607",
              "p-NFkB": "98",
              "p-p53 (Ser46)": "39.5",
              "p-STAT3 (Ser727)": "1562",
              measurementType: "p-p38", "p-STAT6": "174"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "8733",
              "p-cJun ": "6567.5",
              "p-CREB ": "923.5",
              "p-ERK1/2": "5162.5",
              "p-GSK-3a/b": "14654",
              "p-IkBa": "858.5",
              "p-JNK": "1372.5",
              "p-MEK1": "22283.5",
              measurement2: "177",
              "p-p70-S6K": "12377",
              "p-p90-RSK": "5140",
              "p-STAT2": "767",
              "p-STAT3 (Tyr705)": "12171",
              "p-IRS-1": "1386",
              "p-Histone H3": "1407",
              "p-Hsp27": "691.5",
              "p-p53 (Ser37)": "615.5",
              "p-Tyk2": "2705",
              "p-ATF-2": "2475",
              "p-NFkB": "90.5",
              "p-p53 (Ser46)": "31",
              "p-STAT3 (Ser727)": "1741.5",
              measurementType: "p-p38", "p-STAT6": "204"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "10126",
              "p-cJun ": "10137.5",
              "p-CREB ": "1023",
              "p-ERK1/2": "5595",
              "p-GSK-3a/b": "15163.5",
              "p-IkBa": "1086",
              "p-JNK": "1594",
              "p-MEK1": "23371.5",
              measurement2: "131",
              "p-p70-S6K": "14246.5",
              "p-p90-RSK": "4483",
              "p-STAT2": "727.5",
              "p-STAT3 (Tyr705)": "11592.5",
              "p-IRS-1": "1257",
              "p-Histone H3": "1246.5",
              "p-Hsp27": "758",
              "p-p53 (Ser37)": "534",
              "p-Tyk2": "2543",
              "p-ATF-2": "2165",
              "p-NFkB": "117.5",
              "p-p53 (Ser46)": "39.5",
              "p-STAT3 (Ser727)": "1740.5",
              measurementType: "p-p38", "p-STAT6": "216"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "IL-10",
              substrate: "p38i",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "11055",
              "p-cJun ": "10703",
              "p-CREB ": "933.5",
              "p-ERK1/2": "4842.5",
              "p-GSK-3a/b": "13075",
              "p-IkBa": "1134",
              "p-JNK": "1361",
              "p-MEK1": "23994",
              measurement2: "128.5",
              "p-p70-S6K": "15063",
              "p-p90-RSK": "4509",
              "p-STAT2": "810",
              "p-STAT3 (Tyr705)": "8583",
              "p-IRS-1": "1467",
              "p-Histone H3": "1305",
              "p-Hsp27": "774.5",
              "p-p53 (Ser37)": "610",
              "p-Tyk2": "2408.5",
              "p-ATF-2": "1859",
              "p-NFkB": "105.5",
              "p-p53 (Ser46)": "35",
              "p-STAT3 (Ser727)": "891",
              measurementType: "p-p38", "p-STAT6": "211"
            }];
          }

          if (project.title == "Project b") {
            return [{
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "10794",
              "p-cJun ": "14516",
              "p-CREB ": "883",
              "p-ERK1/2": "2844",
              "p-GSK-3a/b": "13759",
              "p-IkBa": "5342",
              "p-JNK": "2294.5",
              "p-MEK1": "20444.5",
              measurement2: "1207",
              "p-p70-S6K": "17580",
              "p-p90-RSK": "3481",
              "p-STAT2": "2347",
              "p-STAT3 (Tyr705)": "1715",
              "p-IRS-1": "1826.5",
              "p-Histone H3": "1526",
              "p-Hsp27": "8174",
              "p-p53 (Ser37)": "1812.5",
              "p-Tyk2": "3127",
              "p-ATF-2": "2230.5",
              "p-NFkB": "133",
              "p-p53 (Ser46)": "35.5",
              "p-STAT3 (Ser727)": "816.5",
              measurementType: "p-p38", "p-STAT6": "164"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "10251",
              "p-cJun ": "18604",
              "p-CREB ": "1587.5",
              "p-ERK1/2": "5316",
              "p-GSK-3a/b": "16383.5",
              "p-IkBa": "5916",
              "p-JNK": "2990",
              "p-MEK1": "20144",
              measurement2: "1836",
              "p-p70-S6K": "17217",
              "p-p90-RSK": "4651",
              "p-STAT2": "1093",
              "p-STAT3 (Tyr705)": "4585.5",
              "p-IRS-1": "1562",
              "p-Histone H3": "1204",
              "p-Hsp27": "13585",
              "p-p53 (Ser37)": "1661",
              "p-Tyk2": "3238",
              "p-ATF-2": "1799",
              "p-NFkB": "172",
              "p-p53 (Ser46)": "26.5",
              "p-STAT3 (Ser727)": "1047",
              measurementType: "p-p38", "p-STAT6": "170"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "5393.5",
              "p-cJun ": "15518",
              "p-CREB ": "1653",
              "p-ERK1/2": "2780",
              "p-GSK-3a/b": "12050",
              "p-IkBa": "13808",
              "p-JNK": "3830",
              "p-MEK1": "25007",
              measurement2: "2029",
              "p-p70-S6K": "14323.5",
              "p-p90-RSK": "2807",
              "p-STAT2": "1152.5",
              "p-STAT3 (Tyr705)": "1323.5",
              "p-IRS-1": "1464",
              "p-Histone H3": "935",
              "p-Hsp27": "23429.5",
              "p-p53 (Ser37)": "971",
              "p-Tyk2": "2162.5",
              "p-ATF-2": "2683",
              "p-NFkB": "238.5",
              "p-p53 (Ser46)": "33.5",
              "p-STAT3 (Ser727)": "719.5",
              measurementType: "p-p38", "p-STAT6": "134.5"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "6478",
              "p-cJun ": "16432",
              "p-CREB ": "2249.5",
              "p-ERK1/2": "",
              "p-GSK-3a/b": "12399.5",
              "p-IkBa": "8030",
              "p-JNK": "4877",
              "p-MEK1": "22284.5",
              measurement2: "5659.5",
              "p-p70-S6K": "18336.5",
              "p-p90-RSK": "1666",
              "p-STAT2": "261.5",
              "p-STAT3 (Tyr705)": "248",
              "p-IRS-1": "1176.5",
              "p-Histone H3": "921.5",
              "p-Hsp27": "25148",
              "p-p53 (Ser37)": "1338",
              "p-Tyk2": "2379.5",
              "p-ATF-2": "4438",
              "p-NFkB": "605.5",
              "p-p53 (Ser46)": "31",
              "p-STAT3 (Ser727)": "749",
              measurementType: "p-p38", "p-STAT6": "148"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "6508",
              "p-cJun ": "9568.5",
              "p-CREB ": "1884",
              "p-ERK1/2": "2246.5",
              "p-GSK-3a/b": "12177",
              "p-IkBa": "3338",
              "p-JNK": "3160",
              "p-MEK1": "16284",
              measurement2: "3111.5",
              "p-p70-S6K": "14795.5",
              "p-p90-RSK": "2776",
              "p-STAT2": "261.5",
              "p-STAT3 (Tyr705)": "150",
              "p-IRS-1": "1276",
              "p-Histone H3": "1057",
              "p-Hsp27": "26151",
              "p-p53 (Ser37)": "958",
              "p-Tyk2": "1618",
              "p-ATF-2": "3550.5",
              "p-NFkB": "561",
              "p-p53 (Ser46)": "31",
              "p-STAT3 (Ser727)": "731",
              measurementType: "p-p38", "p-STAT6": "143"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "5306",
              "p-cJun ": "6373",
              "p-CREB ": "1072",
              "p-ERK1/2": "2597",
              "p-GSK-3a/b": "8969",
              "p-IkBa": "2543",
              "p-JNK": "1270.5",
              "p-MEK1": "17780",
              measurement2: "1917.5",
              "p-p70-S6K": "13382.5",
              "p-p90-RSK": "2372.5",
              "p-STAT2": "399.5",
              "p-STAT3 (Tyr705)": "207.5",
              "p-IRS-1": "1164.5",
              "p-Histone H3": "935",
              "p-Hsp27": "23795.5",
              "p-p53 (Ser37)": "511",
              "p-Tyk2": "1507",
              "p-ATF-2": "2744",
              "p-NFkB": "374",
              "p-p53 (Ser46)": "27",
              "p-STAT3 (Ser727)": "738.5",
              measurementType: "p-p38", "p-STAT6": "145.5"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-1b",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "5123",
              "p-cJun ": "4009",
              "p-CREB ": "886",
              "p-ERK1/2": "3055.5",
              "p-GSK-3a/b": "8558.5",
              "p-IkBa": "4577",
              "p-JNK": "1106",
              "p-MEK1": "20144",
              measurement2: "1704.5",
              "p-p70-S6K": "13148.5",
              "p-p90-RSK": "3342.5",
              "p-STAT2": "580.5",
              "p-STAT3 (Tyr705)": "249",
              "p-IRS-1": "1141",
              "p-Histone H3": "970",
              "p-Hsp27": "4338",
              "p-p53 (Ser37)": "450",
              "p-Tyk2": "1828",
              "p-ATF-2": "1766",
              "p-NFkB": "159.5",
              "p-p53 (Ser46)": "37",
              "p-STAT3 (Ser727)": "808",
              measurementType: "p-p38", "p-STAT6": "173"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "19760",
              "p-cJun ": "19251",
              "p-CREB ": "1397",
              "p-ERK1/2": "6812",
              "p-GSK-3a/b": "16112.5",
              "p-IkBa": "4682.5",
              "p-JNK": "1693",
              "p-MEK1": "27057",
              measurement2: "1757.5",
              "p-p70-S6K": "18206.5",
              "p-p90-RSK": "5744",
              "p-STAT2": "4107",
              "p-STAT3 (Tyr705)": "2433",
              "p-IRS-1": "1836",
              "p-Histone H3": "1633",
              "p-Hsp27": "7309",
              "p-p53 (Ser37)": "2293.5",
              "p-Tyk2": "3786.5",
              "p-ATF-2": "1261",
              "p-NFkB": "142.5",
              "p-p53 (Ser46)": "31",
              "p-STAT3 (Ser727)": "1036.5",
              measurementType: "p-p38", "p-STAT6": "161"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "10640.5",
              "p-cJun ": "11585",
              "p-CREB ": "666",
              "p-ERK1/2": "4572",
              "p-GSK-3a/b": "15802",
              "p-IkBa": "3073",
              "p-JNK": "1392.5",
              "p-MEK1": "24440",
              measurement2: "878",
              "p-p70-S6K": "16617",
              "p-p90-RSK": "5406.5",
              "p-STAT2": "1130",
              "p-STAT3 (Tyr705)": "1789.5",
              "p-IRS-1": "1536",
              "p-Histone H3": "1335",
              "p-Hsp27": "6675",
              "p-p53 (Ser37)": "1576.5",
              "p-Tyk2": "3141",
              "p-ATF-2": "1712",
              "p-NFkB": "154",
              "p-p53 (Ser46)": "40.5",
              "p-STAT3 (Ser727)": "920",
              measurementType: "p-p38", "p-STAT6": "183"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "1530.5",
              "p-cJun ": "",
              "p-CREB ": "843",
              "p-ERK1/2": "386.5",
              "p-GSK-3a/b": "",
              "p-IkBa": "1305.5",
              "p-JNK": "1951",
              "p-MEK1": "16624",
              measurement2: "1080",
              "p-p70-S6K": "",
              "p-p90-RSK": "",
              "p-STAT2": "",
              "p-STAT3 (Tyr705)": "2663",
              "p-IRS-1": "720",
              "p-Histone H3": "1014.5",
              "p-Hsp27": "6641",
              "p-p53 (Ser37)": "1916",
              "p-Tyk2": "984.5",
              "p-ATF-2": "1583",
              "p-NFkB": "178",
              "p-p53 (Ser46)": "40",
              "p-STAT3 (Ser727)": "1050",
              measurementType: "p-p38", "p-STAT6": "149"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "8620.5",
              "p-cJun ": "18040.5",
              "p-CREB ": "1555",
              "p-ERK1/2": "3842.5",
              "p-GSK-3a/b": "12969",
              "p-IkBa": "11563",
              "p-JNK": "3140",
              "p-MEK1": "25849.5",
              measurement2: "2386",
              "p-p70-S6K": "18811",
              "p-p90-RSK": "3448",
              "p-STAT2": "864.5",
              "p-STAT3 (Tyr705)": "1519",
              "p-IRS-1": "1681",
              "p-Histone H3": "1416",
              "p-Hsp27": "23778",
              "p-p53 (Ser37)": "1249",
              "p-Tyk2": "2509.5",
              "p-ATF-2": "2426",
              "p-NFkB": "626",
              "p-p53 (Ser46)": "42",
              "p-STAT3 (Ser727)": "1478.5",
              measurementType: "p-p38", "p-STAT6": "226"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "9900.5",
              "p-cJun ": "23864",
              "p-CREB ": "3636",
              "p-ERK1/2": "6531.5",
              "p-GSK-3a/b": "16106",
              "p-IkBa": "11584",
              "p-JNK": "5426",
              "p-MEK1": "22225",
              measurement2: "5642",
              "p-p70-S6K": "13923",
              "p-p90-RSK": "6661",
              "p-STAT2": "1156",
              "p-STAT3 (Tyr705)": "349",
              "p-IRS-1": "1943.5",
              "p-Histone H3": "1120",
              "p-Hsp27": "26695",
              "p-p53 (Ser37)": "2277",
              "p-Tyk2": "1838.5",
              "p-ATF-2": "3871",
              "p-NFkB": "702",
              "p-p53 (Ser46)": "44",
              "p-STAT3 (Ser727)": "1129",
              measurementType: "p-p38", "p-STAT6": "185"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "",
              "p-cJun ": "",
              "p-CREB ": "",
              "p-ERK1/2": "",
              "p-GSK-3a/b": "",
              "p-IkBa": "",
              "p-JNK": "",
              "p-MEK1": "",
              measurement2: "",
              "p-p70-S6K": "",
              "p-p90-RSK": "",
              "p-STAT2": "",
              "p-STAT3 (Tyr705)": "",
              "p-IRS-1": "",
              "p-Histone H3": "",
              "p-Hsp27": "",
              "p-p53 (Ser37)": "",
              "p-Tyk2": "",
              "p-ATF-2": "3376",
              "p-NFkB": "535",
              "p-p53 (Ser46)": "42",
              "p-STAT3 (Ser727)": "1027.5",
              measurementType: "p-p38", "p-STAT6": "200"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "12289",
              "p-cJun ": "4037",
              "p-CREB ": "3528",
              "p-ERK1/2": "11663",
              "p-GSK-3a/b": "15650",
              "p-IkBa": "4289",
              "p-JNK": "1818",
              "p-MEK1": "27184",
              measurement2: "2715",
              "p-p70-S6K": "19189",
              "p-p90-RSK": "6470",
              "p-STAT2": "",
              "p-STAT3 (Tyr705)": "269.5",
              "p-IRS-1": "1355",
              "p-Histone H3": "988.5",
              "p-Hsp27": "24135",
              "p-p53 (Ser37)": "754",
              "p-Tyk2": "2718.5",
              "p-ATF-2": "2911",
              "p-NFkB": "298",
              "p-p53 (Ser46)": "35",
              "p-STAT3 (Ser727)": "1210",
              measurementType: "p-p38", "p-STAT6": "223"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + GM-CSF",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "10294",
              "p-cJun ": "4072",
              "p-CREB ": "1379",
              "p-ERK1/2": "10338",
              "p-GSK-3a/b": "10485.5",
              "p-IkBa": "1783.5",
              "p-JNK": "843",
              "p-MEK1": "26459.5",
              measurement2: "1013",
              "p-p70-S6K": "15400",
              "p-p90-RSK": "3863.5",
              "p-STAT2": "1750.5",
              "p-STAT3 (Tyr705)": "395",
              "p-IRS-1": "1135",
              "p-Histone H3": "1299",
              "p-Hsp27": "2259",
              "p-p53 (Ser37)": "465",
              "p-Tyk2": "2638",
              "p-ATF-2": "1238",
              "p-NFkB": "148",
              "p-p53 (Ser46)": "39",
              "p-STAT3 (Ser727)": "975.5",
              measurementType: "p-p38", "p-STAT6": "242.5"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "7488",
              "p-cJun ": "11647",
              "p-CREB ": "960.5",
              "p-ERK1/2": "2520",
              "p-GSK-3a/b": "12899.5",
              "p-IkBa": "2730.5",
              "p-JNK": "1053.5",
              "p-MEK1": "22337",
              measurement2: "1657.5",
              "p-p70-S6K": "11515",
              "p-p90-RSK": "2448.5",
              "p-STAT2": "3006.5",
              "p-STAT3 (Tyr705)": "604",
              "p-IRS-1": "2291",
              "p-Histone H3": "1678",
              "p-Hsp27": "12942",
              "p-p53 (Ser37)": "2775",
              "p-Tyk2": "5241",
              "p-ATF-2": "1966.5",
              "p-NFkB": "222",
              "p-p53 (Ser46)": "43",
              "p-STAT3 (Ser727)": "1540",
              measurementType: "p-p38", "p-STAT6": "204"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "6589.5",
              "p-cJun ": "11281",
              "p-CREB ": "972",
              "p-ERK1/2": "2328",
              "p-GSK-3a/b": "13125.5",
              "p-IkBa": "4233",
              "p-JNK": "2500",
              "p-MEK1": "24182.5",
              measurement2: "1434",
              "p-p70-S6K": "15379.5",
              "p-p90-RSK": "3400",
              "p-STAT2": "2491",
              "p-STAT3 (Tyr705)": "1702",
              "p-IRS-1": "1698.5",
              "p-Histone H3": "1339",
              "p-Hsp27": "8585",
              "p-p53 (Ser37)": "1309",
              "p-Tyk2": "2992",
              "p-ATF-2": "2737",
              "p-NFkB": "200",
              "p-p53 (Ser46)": "45",
              "p-STAT3 (Ser727)": "899",
              measurementType: "p-p38", "p-STAT6": "153"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "4950",
              "p-cJun ": "11762",
              "p-CREB ": "1343.5",
              "p-ERK1/2": "2733",
              "p-GSK-3a/b": "12705",
              "p-IkBa": "7473",
              "p-JNK": "2381.5",
              "p-MEK1": "16049.5",
              measurement2: "2219.5",
              "p-p70-S6K": "15945.5",
              "p-p90-RSK": "4255.5",
              "p-STAT2": "3350.5",
              "p-STAT3 (Tyr705)": "3294",
              "p-IRS-1": "1452",
              "p-Histone H3": "963",
              "p-Hsp27": "12416",
              "p-p53 (Ser37)": "1549",
              "p-Tyk2": "3172",
              "p-ATF-2": "1959",
              "p-NFkB": "260",
              "p-p53 (Ser46)": "40.5",
              "p-STAT3 (Ser727)": "1136.5",
              measurementType: "p-p38", "p-STAT6": "164"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "6656.5",
              "p-cJun ": "23326",
              "p-CREB ": "2326.5",
              "p-ERK1/2": "2545",
              "p-GSK-3a/b": "12989.5",
              "p-IkBa": "17729",
              "p-JNK": "3859",
              "p-MEK1": "23690",
              measurement2: "3278",
              "p-p70-S6K": "21305",
              "p-p90-RSK": "2843.5",
              "p-STAT2": "5120.5",
              "p-STAT3 (Tyr705)": "2777",
              "p-IRS-1": "1633",
              "p-Histone H3": "1060",
              "p-Hsp27": "24664",
              "p-p53 (Ser37)": "1226",
              "p-Tyk2": "3097",
              "p-ATF-2": "4962.5",
              "p-NFkB": "1006",
              "p-p53 (Ser46)": "43",
              "p-STAT3 (Ser727)": "1642",
              measurementType: "p-p38", "p-STAT6": "341"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "8887",
              "p-cJun ": "14981",
              "p-CREB ": "3477",
              "p-ERK1/2": "3470",
              "p-GSK-3a/b": "14122",
              "p-IkBa": "10061",
              "p-JNK": "7144",
              "p-MEK1": "23892",
              measurement2: "5820.5",
              "p-p70-S6K": "19674",
              "p-p90-RSK": "4390.5",
              "p-STAT2": "6116.5",
              "p-STAT3 (Tyr705)": "1108",
              "p-IRS-1": "2073",
              "p-Histone H3": "1163",
              "p-Hsp27": "26336",
              "p-p53 (Ser37)": "2067",
              "p-Tyk2": "3124",
              "p-ATF-2": "4933",
              "p-NFkB": "630",
              "p-p53 (Ser46)": "47",
              "p-STAT3 (Ser727)": "938.5",
              measurementType: "p-p38", "p-STAT6": "220"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "30",
              
              "p-Akt": "6900.5",
              "p-cJun ": "7216",
              "p-CREB ": "2424",
              "p-ERK1/2": "2601",
              "p-GSK-3a/b": "9946",
              "p-IkBa": "4617",
              "p-JNK": "2292",
              "p-MEK1": "24076.5",
              measurement2: "4269",
              "p-p70-S6K": "15440.5",
              "p-p90-RSK": "3102",
              "p-STAT2": "4240",
              "p-STAT3 (Tyr705)": "1257",
              "p-IRS-1": "1180",
              "p-Histone H3": "919",
              "p-Hsp27": "26394",
              "p-p53 (Ser37)": "741",
              "p-Tyk2": "1290.5",
              "p-ATF-2": "4077.5",
              "p-NFkB": "533",
              "p-p53 (Ser46)": "27",
              "p-STAT3 (Ser727)": "884.5",
              measurementType: "p-p38", "p-STAT6": "212"
            }, {
              "Plate": "", wellLabel: "IEEE Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "15",
              
              "p-Akt": "6201.5",
              "p-cJun ": "4165",
              "p-CREB ": "975",
              "p-ERK1/2": "2851",
              "p-GSK-3a/b": "9757.5",
              "p-IkBa": "4566",
              "p-JNK": "991",
              "p-MEK1": "23173",
              measurement2: "2277.5",
              "p-p70-S6K": "15147.5",
              "p-p90-RSK": "2965.5",
              "p-STAT2": "6448",
              "p-STAT3 (Tyr705)": "879",
              "p-IRS-1": "1118",
              "p-Histone H3": "1006",
              "p-Hsp27": "21443",
              "p-p53 (Ser37)": "344",
              "p-Tyk2": "1985.5",
              "p-ATF-2": "2568",
              "p-NFkB": "305.5",
              "p-p53 (Ser46)": "30",
              "p-STAT3 (Ser727)": "803",
              measurementType: "p-p38", "p-STAT6": "221"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IFN-g",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "5",
              
              "p-Akt": "6396",
              "p-cJun ": "4212",
              "p-CREB ": "1048",
              "p-ERK1/2": "3455",
              "p-GSK-3a/b": "8001",
              "p-IkBa": "2272.5",
              "p-JNK": "580",
              "p-MEK1": "19035",
              measurement2: "740",
              "p-p70-S6K": "11315",
              "p-p90-RSK": "2758.5",
              "p-STAT2": "4172",
              "p-STAT3 (Tyr705)": "600.5",
              "p-IRS-1": "1204.5",
              "p-Histone H3": "819",
              "p-Hsp27": "1466",
              "p-p53 (Ser37)": "360",
              "p-Tyk2": "1830",
              "p-ATF-2": "866",
              "p-NFkB": "104",
              "p-p53 (Ser46)": "30",
              "p-STAT3 (Ser727)": "714.5",
              measurementType: "p-p38", "p-STAT6": "217"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-6",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "1440",
              
              "p-Akt": "14997",
              "p-cJun ": "10797",
              "p-CREB ": "1956",
              "p-ERK1/2": "4049",
              "p-GSK-3a/b": "24795",
              "p-IkBa": "4106",
              "p-JNK": "2529.5",
              "p-MEK1": "25139",
              measurement2: "2434",
              "p-p70-S6K": "24440",
              "p-p90-RSK": "10771",
              "p-STAT2": "3417",
              "p-STAT3 (Tyr705)": "4012.5",
              "p-IRS-1": "6371",
              "p-Histone H3": "4323",
              "p-Hsp27": "7499.5",
              "p-p53 (Ser37)": "9214",
              "p-Tyk2": "17121.5",
              "p-ATF-2": "2745",
              "p-NFkB": "372.5",
              "p-p53 (Ser46)": "57.5",
              "p-STAT3 (Ser727)": "3265",
              measurementType: "p-p38", "p-STAT6": "359"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-6",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "480",
              
              "p-Akt": "8538",
              "p-cJun ": "12609",
              "p-CREB ": "1063.5",
              "p-ERK1/2": "3137",
              "p-GSK-3a/b": "11993",
              "p-IkBa": "4392.5",
              "p-JNK": "2126",
              "p-MEK1": "22177.5",
              measurement2: "1521",
              "p-p70-S6K": "14951.5",
              "p-p90-RSK": "3324",
              "p-STAT2": "1941",
              "p-STAT3 (Tyr705)": "2914",
              "p-IRS-1": "1880.5",
              "p-Histone H3": "1556",
              "p-Hsp27": "8207",
              "p-p53 (Ser37)": "1505",
              "p-Tyk2": "3499",
              "p-ATF-2": "1907",
              "p-NFkB": "143.5",
              "p-p53 (Ser46)": "33",
              "p-STAT3 (Ser727)": "764.5",
              measurementType: "p-p38", "p-STAT6": "174"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-6",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "240",
              
              "p-Akt": "8334",
              "p-cJun ": "16750",
              "p-CREB ": "1522",
              "p-ERK1/2": "3475",
              "p-GSK-3a/b": "18315",
              "p-IkBa": "8351",
              "p-JNK": "3313.5",
              "p-MEK1": "19986",
              measurement2: "2022",
              "p-p70-S6K": "17961",
              "p-p90-RSK": "3865.5",
              "p-STAT2": "926.5",
              "p-STAT3 (Tyr705)": "4826.5",
              "p-IRS-1": "1909.5",
              "p-Histone H3": "1258",
              "p-Hsp27": "14287",
              "p-p53 (Ser37)": "2188",
              "p-Tyk2": "3180",
              "p-ATF-2": "1762",
              "p-NFkB": "165",
              "p-p53 (Ser46)": "48",
              "p-STAT3 (Ser727)": "1066",
              measurementType: "p-p38", "p-STAT6": "179"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-6",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "120",
              
              "p-Akt": "",
              "p-cJun ": "21173.5",
              "p-CREB ": "",
              "p-ERK1/2": "",
              "p-GSK-3a/b": "",
              "p-IkBa": "16932",
              "p-JNK": "",
              "p-MEK1": "24529.5",
              measurement2: "2656",
              "p-p70-S6K": "20641",
              "p-p90-RSK": "3298",
              "p-STAT2": "",
              "p-STAT3 (Tyr705)": "4220",
              "p-IRS-1": "",
              "p-Histone H3": "1304",
              "p-Hsp27": "23812",
              "p-p53 (Ser37)": "",
              "p-Tyk2": "",
              "p-ATF-2": "2200",
              "p-NFkB": "490",
              "p-p53 (Ser46)": "39",
              "p-STAT3 (Ser727)": "1002.5",
              measurementType: "p-p38", "p-STAT6": "229"
            }, {
              "Plate": "", wellLabel: "DataFlex Data",
              "Well": "",
              "Cell Type": "U937",
              compound : "LPS + IL-6",
              substrate: "JNKi",
              "Inhibitor Conc=uM": "1",
              time: "60",
              
              "p-Akt": "8656",
              "p-cJun ": "23626.5",
              "p-CREB ": "3027.5",
              "p-ERK1/2": "3173",
              "p-GSK-3a/b": "13356.5",
              "p-IkBa": "11205.5",
              "p-JNK": "6353.5",
              "p-MEK1": "24388",
              measurement2: "6242.5",
              "p-p70-S6K": "21825.5",
              "p-p90-RSK": "4303",
              "p-STAT2": "702",
              "p-STAT3 (Tyr705)": "2917.5",
              "p-IRS-1": "2154",
              "p-Histone H3": "1609",
              "p-Hsp27": "27069.5",
              "p-p53 (Ser37)": "2571",
              "p-Tyk2": "3156",
              "p-ATF-2": "3846",
              "p-NFkB": "772",
              "p-p53 (Ser46)": "46.5",
              "p-STAT3 (Ser727)": "1069.5",
              measurementType: "p-p38", "p-STAT6": "170"
            }];
          }
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
        "labelName": "Measurement2",
        "labelData": [1, 8, 3, 3, 6]
      }, {
        "labelName": "Measurement3",
        "labelData": [5, 2, 3, 4, 5]
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

        dummyData[0] = generateSequence(1, maxlen, 1);

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
        var i;
        switch (action) {
          case 'setX':
            if (arrIndices.length === 0) {
              console.log('Warning: attempted to call DAService.updateData() with empty first argument');
            } else {
              if (arrIndices.length > 1) {
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
            if (arrIndices.length != 2) {
              console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'replace\' option');
            } else {
              dataIndices[dataIndices.indexOf(arrIndices[0])] = arrIndices[1];
            }
            break;
          case 'add':
            for (i in arrIndices) {
              dataIndices.push(arrIndices[i]);
            }
            break;
          case 'remove':
            if (arrIndices.length === 0) {
              console.log('Warning: attempted to call DAService.updateData() with empty first argument');
            } else {
              if (arrIndices.length > 1) {
                console.log('Warning: attempted to call DAService.updateData() with incorrect first argument for \'remove\' option');
              }
              if (dataIndices.indexOf(arrIndices[0]) >= 0) {
                // need to check for valid index, because splice() allows negative indices (similar to python)
                dataIndices.splice(dataIndices.indexOf(arrIndices[0]), 1);
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
          y: {}
        },
        series: {},
      };

      var graphIt = function() {
        console.log('graphIt: options.series --');
        console.log(options.series);
        g = new Dygraph(document.getElementById('graph'), plotData, options);
        return g;
      };


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
        'initializeData': initializeData
      };
    }
  ]);

  var dataAnalysisModule = angular.module('dataAnalysis', ['ui.bootstrap']);

  var DUMMYDEFAULTDATASERIESTOPLOT = [7, 8, 9];

  app.controller('DataAnalysisController', ['$scope', '$routeParams', 'DAService',
    function($scope, $routeParams, DAService) {}
  ]);



  app.controller('DropdownCtrl', ['$scope', '$log', 'DAService', "deleteProject", "activeProject", "setActiveProject",
    function($scope, $log, DAService, deleteProject, activeProject, setActiveProject) {
      DAService.initializeData();
      $scope.labels = DAService.labels.slice(1); // remove default x-axis label, don't want that in the dropdown menu
      $scope.graphTypes = ['scatter', 'line'];
      var yCount = 1;

      $scope.ActiveProject = activeProject.activeId;
      $scope.filterowner = '';

      $scope.projects = DAService.projects;
      $scope.projectsDisplay = [].concat($scope.projects);

      $scope.wellCollection = [];

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
        $scope.labels = DAService.labels.slice(1);
        $scope.ySeries.splice(0, $scope.ySeries.length);
        addDataSeries($scope.yCount);
        DAService.updateData(DAService.genSeq(1, $scope.labels.length, 1), 'setY');
        DAService.options.series = {};
        DAService.graphIt();
      };

      resetDataSeries();

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

          DAService.graphIt();
        } else {
          resetDataSeries();
        }
      };

      $scope.setYAxisData = function(ID, label) {
        index = ID.split('-')[1] - 1;

        if (ID == 'menu-1') { // if it's the first menu, never add data
          if ($scope.ySeries[index].label == dsPLACEHOLDER) { // if first menu and never previously set, completely replace data
            DAService.updateData([DAService.labels.indexOf(label)], 'setY');
            DAService.options.series = {};
          } else { // if first menu has data series already, just replace it
            DAService.updateData(
              [
                DAService.labels.indexOf($scope.ySeries[index].label), // current index
                DAService.labels.indexOf(label) // new index
              ],
              'replace');
            delete DAService.options.series[$scope.ySeries[index].label];
          }
        } else { // if not first menu, never completely replace data
          if ($scope.ySeries[index].label == dsPLACEHOLDER) { // if never previously set, just add data
            DAService.updateData([DAService.labels.indexOf(label)], 'add');
          } else { // if has data series already, just replace it
            DAService.updateData(
              [
                DAService.labels.indexOf($scope.ySeries[index].label), // current index
                DAService.labels.indexOf(label) // new index
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

      $scope.setYAxisGraphType = function(ID, type) {
        index = ID.split('-')[1] - 1;

        thisLabel = $scope.ySeries[index].label;
        if (DAService.options.series.hasOwnProperty(thisLabel)) {
          $scope.ySeries[index].type = type;

          switch (type) {
            case 'scatter':
              //DAService.options.strokeWidth = 0.0;
              //DAService.options.pointSize = 3;
              DAService.options.series[thisLabel].strokeWidth = 0.0;
              DAService.options.series[thisLabel].pointSize = 3;
              break;
            case 'bar':
            case 'curve':
            case 'line':
              //DAService.options.strokeWidth = 1.0;
              //DAService.options.pointSize = 2;
              DAService.options.series[thisLabel].strokeWidth = 1.0;
              DAService.options.series[thisLabel].pointSize = 2;
              break;
            default:
          }

          DAService.graphIt();
        }
      };

      $scope.setXAxisData = function(label) {
        $scope.selectedXAxisLabel = label;
        DAService.updateData([DAService.labels.indexOf(label)], 'setX');
        DAService.graphIt();
      };

      $scope.deletePr = function(coll1, indx1) {
        deleteProject(coll1, indx1);
      };
      $scope.makeActive = function(proj) {
        $scope.ActiveProject = proj;
        setActiveProject(activeProject, proj);
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
              wellLabel = wells[w].wellLabel;
              wellCompound = wells[w].compound;
              wellSubstrate = wells[w].substrate;
              if (DAService.labelFilter(wellLabel) && DAService.compoundFilter(wellCompound) && DAService.substrateFilter(wellSubstrate) && DAService.measurementTypeFilter(wells[w].measurementType))
                $scope.wellCollection.push(wells[w]);
            }
          }
        }

        $scope.wellCollectionDisplay = [].concat($scope.wellCollection);

        DAService.currentWellCollection = $scope.wellCollectionDisplay;
      };

      $scope.$watch('wellCollection', function() {
        //alert('hey, wellCollection has changed!');
        var collection = $scope.wellCollection;
        var length = collection.length;
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

        $scope.compoundCollection = DAService.compounds;
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

      $scope.updateGraphData = function() {
        //update the graph data

        DAService.rawData.splice(0, DAService.rawData.length);

        for (var w = 0, wLen = DAService.currentWellCollection.length; w < wLen; w++) {
          var well = DAService.currentWellCollection[w];

          if (DAService.rawData.length === 0) {
            var emptyArray = [];
            emptyArray.push(parseInt(well.measurement2));
            var object = {
              "labelName": well.measurementType,
              "labelData": emptyArray
            };

            DAService.rawData.push(object);
          } else {
            var noSuchLabel = true;
            for (var p = 0, pLen = DAService.rawData.length; p < pLen; p++) {
              var obj = DAService.rawData[p];
              if (obj.labelName == well.measurementType) {
                noSuchLabel = false;
                obj.labelData.push(parseInt(well.measurement2));
                break;
              }

            }
            if (noSuchLabel === true) {
              var emptyArray = [];
              emptyArray.push(parseInt(well.measurement2));
              var object = {
                "labelName": well.measurementType,
                "labelData": emptyArray
              };

              DAService.rawData.push(object);
            }

          }
        }

        DAService.initializeData();

        //$scope.ySeries.splice(1, $scope.ySeries.length);

        resetDataSeries();


      }

      $scope.updateTable = function(projectTitle) {
        $scope.updateWells();
        $scope.updateLabels();
        $scope.updateCompounds();
        $scope.updateSubstrates();
        $scope.updateMeasurementTypes();
        $scope.updateGraphData();
      };

      $scope.updateBar = function(foo) {
        $scope.updateGraphData();
      };


    }
  ]); // end of DropdownCtrl


}());