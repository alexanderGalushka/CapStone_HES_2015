'use strict';

angular.module('plateeditor', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])


    .controller('PlateeditorCtrl', ["$scope",function($scope) {
        $scope.boxszs = ["10","20","30","40","50"];
        $scope.boxsz = "25";
        $scope.tooltipdel = "500";


        $scope.rows = [
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }
            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }


            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }
            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }
            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }
            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }
            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 1",
                    "row":"2",
                    "column":"1",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 2",
                    "row":"2",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 2 12",
                    "row":"2",
                    "column":"12",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ff0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ],
            [
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 1",
                    "row":"3",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 2",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 3",
                    "row":"3",
                    "column":"3",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 4",
                    "row":"3",
                    "column":"4",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 5",
                    "row":"3",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 6",
                    "row":"3",
                    "column":"6",
                    "dosage":"1.5",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 3 12",
                    "row":"3",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 1",
                    "row":"1",
                    "column":"1",
                    "dosage":"1.5",
                    "color":"#FFFF00",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 2",
                    "row":"1",
                    "column":"2",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 3",
                    "row":"1",
                    "column":"3",
                    "dosage":"1.5",
                    "color":"#FFFFE0",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 4",
                    "row":"1",
                    "column":"4",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 5",
                    "row":"1",
                    "column":"5",
                    "dosage":"1.5",
                    "color":"#FF0000",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },
                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 6",
                    "row":"1",
                    "column":"6",
                    "dosage":"1.5",
                    "color":"#90EE90",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                },

                {
                    "plate": "Plate 123 a",
                    "label":"penicillin 1 12",
                    "row":"1",
                    "column":"12",
                    "dosage":"1.5",
                    "color":"#ADD8E6",
                    "substrate":"substr 345","control":"positive","date": "1/2/2015"
                }

            ]

        ];
        $scope.rowsDisplay = [].concat($scope.rows);

        $scope.compounds = [
            {
                "name": "Glucomine ",
                "date": "1/2/2015"
            },
            {
                "name":"Glucoze",
                "date": "2/2/2015"
            },
            {
                "name": "XX 88",
                "date": "3/2/2015"
            },
            {
                "name": "PBB 44",
                "date": "4/2/2015"
            },
            {
                "name": "ZZ v3",
                "date": "2/2/2015"
            },
            {
                "name": "AA 78",
                "date": "2/2/2015"
            }
        ];
        $scope.compoundsDisplay = [].concat($scope.compounds);

        $scope.substrates = [
            {
                "name": "D substrate ",
                "date": "1/2/2015"
            },
            {
                "name":"E substrate",
                "date": "2/2/2015"
            },
            {
                "name": "X substrate",
                "date": "3/2/2015"
            },
            {
                "name": "A substrate",
                "date": "4/2/2015"
            },
            {
                "name": "Z substrate",
                "date": "2/2/2015"
            },
            {
                "name": "B substrate",
                "date": "2/2/2015"
            }
        ];
        $scope.substratesDisplay = [].concat($scope.substrates);
    }])

    .directive('singleWell', function() {
        return {
            restrict: 'E',
            scope: {
                well: '=',
                boxsize:"@",
                tooltipdelay:"@"
            },
            templateUrl: 'plateeditor/singlewell.html'
        };
    });