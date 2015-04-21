'use strict';

angular.module('projects', ['smart-table','mgcrea.ngStrap'])

    .controller('ProjectsCtrl', ["$scope","deleteProject", "activeProject","setActiveProject",function($scope, deleteProject, activeProject, setActiveProject) {
        $scope.ActiveProject = activeProject.activeId;
        $scope.filterowner = '';


        $scope.projects = [
            {
                "title": "Project b",
                "description": "Cancer research in 2015",
                "owner":"Cindy",
                "tags":[{name:"multi project"},{name:"mouse"}],
                "date": "2/2/2015"
            },
            {
                "title": "Project a",
                "description": "Amazing new medicine",
                "owner":"Nik",
                "date": "2/12/3015"
            },
            {
                "title": "Aiv",
                "description": "Painkiller medicine phase 3",
                "owner":"Alex",
                "date": "3/2/2015"
            },
            {
                "title": "Zig medicine",
                "description": "Building new research",
                "owner":"Ivan",
                "date": "2/12/2015"
            }
        ];
        $scope.projectsDisplay = [].concat($scope.projects);


        $scope.getters={
            title: function (value) {
                //this will sort by the length of the title string
                return value.title;
            }
        };
        $scope.deletePr = function(coll1,indx1){
            deleteProject(coll1,indx1);
        };
        $scope.makeActive = function(proj){
            $scope.ActiveProject = proj;
            setActiveProject(activeProject, proj);
        };
        $scope.addTag = function(tags,newTag){
            tags.push({name:newTag});
        };
        $scope.checkedOwner = function(check){
            if (check)
                $scope.filterowner = 'Ivan';
            else
                $scope.filterowner = '';
        };


    }]);