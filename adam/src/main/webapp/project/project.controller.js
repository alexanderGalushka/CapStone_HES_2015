'use strict';

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

    projVm.projects = Project.query();
    projVm.projectsDisplay = [].concat(projVm.projects);

    projVm.collaborators = Collaborator.query({id:'others'});

    function deletePr(coll,indexinp){
      //deleteProject(coll,indexinp);

      Project.delete({"id":indexinp.id});
      var index = coll.indexOf(indexinp);
      coll.splice(index, 1);

      $scope.ActiveProject.project= null;
      $scope.ActivePlate.plate  = null;
      $scope.activePlateResult.plateResult  = null;
    }

    function addNewProject (){
      projVm.projectAction = "new";
      projVm.newproject = {
        "name": "" ,
        "description": "",
        "label": "",
        "tags":[],
        "collaborators":[]
      };
    }

    function editProject(proj) {
      projVm.projectAction = "edit";
      projVm.newproject = JSON.parse(JSON.stringify(proj));
    }

    function saveChangesProject(act,proj) {
      if (act == "new") {
        var savedproj = Project.save(projVm.newproject);
        projVm.projects = projVm.projects.concat(savedproj);
      }
      else {

        Project.update({"id":projVm.newproject.id},projVm.newproject);
        proj.name = projVm.newproject.name;
        proj.description = projVm.newproject.description;
        proj.label = projVm.newproject.label;
        //proj.owner = projVm.newproject.owner;
        //proj.creationDate = projVm.newproject.creationDate  ;
        proj.tags = projVm.newproject.tags;
        proj.collaborators = projVm.newproject.collaborators;
      }
    }


    function setActiveProject (proj){
      $scope.ActiveProject.project= proj;
      $scope.ActivePlate.plate  = null;
      $scope.activePlateResult.plateResult  = null;
    }

    function checkedOwner(check){
      if (check)
        projVm.filterowner = 'ivan';
      else
        projVm.filterowner = '';
    }

    function addCollaborator(collaborators,newCollaborator){
      if(collaborators.indexOf(newCollaborator) >= 0){
        alert("User " + newCollaborator.name + " is already added as collaborator");
      }
      else
        collaborators.push(newCollaborator);
    }

  }



})();
