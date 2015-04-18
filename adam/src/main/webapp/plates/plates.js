'use strict';

angular.module('plates', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])


    .controller('PlatesCtrl', ["$scope","activeProject", function($scope,activeProject) {
        $scope.activePanel = -1;

        $scope.plates = [
            {
                "project":"Project a",
                "name": "Plate 123 a",
                "date": "1/2/2015"
            },
            {
                "project":"Project b",
                "name": "Plate 44 Project b",
                "date": "2/2/2015"
            },
            {
                "project":"Project a",
                "name": "Plate 663 a",
                "date": "3/2/2015"
            },
            {
                "project":"Project b",
                "name": "Plate 54646 Project b",
                "date": "4/2/2015"
            },
            {
                "project":"Project b",
                "name": "Plate 3344 Project b",
                "date": "2/2/2015"
            },
            {
                "project":"Project a",
                "name": "Plate fgfsd a",
                "date": "2/2/2015"
            }
        ];
        $scope.platesDisplay = [].concat($scope.plates);

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
    }]);