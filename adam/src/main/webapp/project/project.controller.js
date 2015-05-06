'use strict';

/**
 * @ngdoc function
 * @name project.controller:ProjectsCtrl
 * @description
 * # ProjectsCtrl
 * Controller of the projects page
 *
 */

(function() {
  angular.module('project', ['smart-table','mgcrea.ngStrap'])

    .controller('ProjectsCtrl', ProjectsCtrl);

  ProjectsCtrl.$inject = ["$scope", "activeProject", "activePlate", "activePlateResult", "Project", "Collaborator", "$filter"];
  function ProjectsCtrl($scope, activeProject, activePlate, activePlateResult, Project, Collaborator, $filter) {
    var projVm = this;

    $scope.ActiveProject = activeProject;
    $scope.ActivePlate = activePlate;
    $scope.activePlateResult = activePlateResult;

    projVm.filterowner = '';

    projVm.editProject = editProject;
    projVm.addNewProject = addNewProject;
    projVm.saveChangesProject = saveChangesProject;
    projVm.setActiveProject = setActiveProject;
    projVm.checkedOwner = checkedOwner;
    projVm.addCollaborator = addCollaborator;
    projVm.deletePr = deletePr;

    projVm.projects = Project.query(
      function() {
        projVm.projectsDisplay = [].concat(projVm.projects);
      },
      function(error) {
        //alert("Data can not be retrieved - Server error");
        console.log(JSON.stringify(error, null, 4));
      });

    projVm.collaborators = Collaborator.query({id:'others'},
      function() {

      },
      function(error) {
        //alert("Data can not be retrieved - Server error");
        console.log(JSON.stringify(error, null, 4));
      });



    /**
     * @ngdoc function
     * @name deletePr
     * @description
     * Delete project from database and updates json collection used for display
     *
     */
    function deletePr(coll,indexinp){
      Project.delete({"id":indexinp.id},
        function() {
          var index = coll.indexOf(indexinp);
          coll.splice(index, 1);

          $scope.ActiveProject.project= null;
          $scope.ActivePlate.plate  = null;
          $scope.activePlateResult.plateResult  = null;
        },
        function(error) {
          alert("Changes can not be saved - Server error");
          console.log(JSON.stringify(error, null, 4));
        });

    }

    /**
     * @ngdoc function
     * @name addNewProject
     * @description
     * Prepares new project json to be used in modal page
     *
     */
    function addNewProject (){
      projVm.projectAction = "new";  // the same page has been used for new and edit project
      projVm.newproject = {
        "name": "" ,
        "description": "",
        "label": "",
        "collaborators":[]
      };
    }

    /**
     * @ngdoc function
     * @name editProject
     * @description
     * Prepares  project json to be used in modal page for edit
     *
     */
    function editProject(proj) {
      projVm.projectAction = "edit";
      projVm.newproject = JSON.parse(JSON.stringify(proj));
    }

    /**
     * @ngdoc function
     * @name saveChangesProject
     * @description
     * Function called from modal page to save changes
     */
    function saveChangesProject(act,proj) {
      if (act == "new") {
        var savedproj = Project.save(projVm.newproject,
          function() {
            projVm.projects = projVm.projects.concat(savedproj);
          },
          function(error) {
            alert("Changes can not be saved - Server error");
            console.log(JSON.stringify(error, null, 4));
          });

      }
      else {
        // Calls web service and update json structure used for display
        Project.update({"id":projVm.newproject.id},projVm.newproject,
          function() {
            proj.name = projVm.newproject.name;
            proj.description = projVm.newproject.description;
            proj.label = projVm.newproject.label;
            proj.tags = projVm.newproject.tags;
            proj.collaborators = projVm.newproject.collaborators;
          },
          function(error) {
            alert("Changes can not be saved - Server error");
            console.log(JSON.stringify(error, null, 4));
          });

      }
    }

    /**
     * @ngdoc function
     * @name setActiveProject
     * @description
     * Function called to set active project variable shared between screens
     */
    function setActiveProject (proj){
      $scope.ActiveProject.project= proj;
      $scope.ActivePlate.plate  = null;
      $scope.activePlateResult.plateResult  = null;
    }

    /**
     * @ngdoc function
     * @name checkedOwner
     * @description
     * Filters table rows
     */
    function checkedOwner(check){
      if (check)
        projVm.filterowner = project.owner;
      else
        projVm.filterowner = '';
    }

    /**
     * @ngdoc function
     * @name addCollaborator
     * @description
     * Add selected user to the list of collaborators. Json list will be saved in database
     */
    function addCollaborator(collaborators,newCollaborator){
      if(collaborators.indexOf(newCollaborator) >= 0){
        alert("User " + newCollaborator.name + " is already added as collaborator");
      }
      else
        collaborators.push(newCollaborator);
    }

  }

})();
