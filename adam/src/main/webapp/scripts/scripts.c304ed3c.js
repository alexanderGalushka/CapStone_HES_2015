"use strict";!function(){function a(a,b){a.when("/projects",{templateUrl:"project/project.html",controller:"ProjectsCtrl",controllerAs:"projVm"}).when("/plates",{templateUrl:"plate/plate.html",controller:"PlateCtrl",controllerAs:"plateVm"}).when("/plateeditor",{templateUrl:"plateeditor/plateeditor.html",controller:"PlateeditorCtrl",controllerAs:"pleditVm"}).when("/plateresults",{templateUrl:"plateresult/plateresult.html",controller:"PlateResultsCtrl",controllerAs:"plresVm"}).when("/qc",{templateUrl:"qc/qc.html",controller:"QcCtrl",controllerAs:"qcVm"}).otherwise({redirectTo:"/projects"})}angular.module("adamApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","smart-table","mgcrea.ngStrap","ngSlider","adamServices","navbarAdam","project","projectpanel","project.data.model","plate","plate.data.model","collaborator.data.model"]).config(a),a.$inject=["$routeProvider","$resourceProvider"]}(),function(){function a(a,b,c,d,e,f){function g(a,b){e["delete"]({id:b.id});var c=a.indexOf(b);a.splice(c,1)}function h(){o.projectAction="new",o.newproject={name:"",description:"",label:"",tags:[],collaborators:[]}}function i(a){o.projectAction="edit",o.newproject=JSON.parse(JSON.stringify(a))}function j(a,b){if("new"==a){var c=e.save(o.newproject);o.projects=o.projects.concat(c)}else e.update({id:o.newproject.id},o.newproject),b.name=o.newproject.name,b.description=o.newproject.description,b.label=o.newproject.label,b.tags=o.newproject.tags,b.collaborators=o.newproject.collaborators}function k(b){a.ActiveProject.project=b,a.ActivePlate.plate="",a.activePlateResult.plate=""}function l(a,b){a.push({description:b})}function m(a){o.filterowner=a?"ivan":""}function n(a,b){a.push(b)}var o=this;a.ActiveProject=b,a.ActivePlate=c,a.activePlateResult=d,o.filterowner="",o.editProject=i,o.addNewProject=h,o.saveChangesProject=j,o.setActiveProject=k,o.addTag=l,o.checkedOwner=m,o.addCollaborator=n,o.deletePr=g,o.projects=e.query(),o.projectsDisplay=[].concat(o.projects),o.collaborators=e.query()}angular.module("project",["smart-table","mgcrea.ngStrap"]).controller("ProjectsCtrl",a),a.$inject=["$scope","activeProject","activePlate","activePlateResult","Project","$filter"]}(),function(){function a(a,b,c,d,e){function f(b){a.ActiveProject.project=b,a.ActivePlate.plate="",a.activePlateResult.plate=""}var g=this;a.ActiveProject=b,a.ActivePlate=c,a.activePlateResult=d,g.setActiveProject=f,g.projects=e.query(),g.projectsDisplay=[].concat(g.projects)}angular.module("projectpanel",["ngAnimate","ngSanitize","smart-table","mgcrea.ngStrap"]).directive("admahesProjectpanel",function(){return{restrict:"E",scope:{asideind:"="},controller:"ProjectsPanelCtrl",controllerAs:"projpanVm",templateUrl:"project/project.panel.html"}}).controller("ProjectsPanelCtrl",a),a.$inject=["$scope","activeProject","activePlate","activePlateResult","Project"]}(),function(){function a(a,b,c,d,e,f){function g(b){a.ActivePlate.plate=b,a.activePlateResult.plate=""}function h(a){n.plateAction="new",n.newplate={project:"",name:"",label:"L",numberOfRows:"",numberOfColumns:"",barcode:"",protocolId:"",wellLabels:[{name:"good"},{name:"start"}]},n.newplate.projectId=a.id}function i(a){n.plateAction="edit",n.newplate=JSON.parse(JSON.stringify(a))}function j(a,b){if("new"==a){var c=e.save(n.newplate);n.plates=n.plates.concat(c)}else e.update({id:n.newplate.id},n.newplate),b.project=n.newplate.project,b.name=n.newplate.name,b.label=n.newplate.label,b.numberOfRows=n.newplate.numberOfRows,b.numberOfColumns=n.newplate.numberOfColumns,b.barcode=n.newplate.barcode,b.protocolId=n.newplate.protocolId,b.wellLabels=n.newplate.wellLabels}function k(a,b){a.push({name:b})}function l(a){e["delete"]({id:a.id});var b=n.plates.indexOf(a);n.plates.splice(b,1)}function m(){a.ActiveProject.project="",a.ActivePlate.plate="",a.activePlateResult.plate=""}var n=this;a.ActiveProject=b,a.ActivePlate=c,a.activePlateResult=d,n.aside=!0,n.setActivePlate=g,n.addNewPlate=h,n.editPlate=i,n.saveChangesPlate=j,n.addLabel=k,n.deletePlate=l,n.clearActiveProject=m,n.plates=e.query(),n.platesDisplay=[].concat(n.plates)}angular.module("plate",["smart-table","mgcrea.ngStrap"]).controller("PlateCtrl",a),a.$inject=["$scope","activeProject","activePlate","activePlateResult","Plate","$filter"]}(),angular.module("adamServices",["ngResource"]).service("activeProject",function(){var a=this;a.project=""}).service("activePlate",function(){var a=this;a.plate=""}).service("activePlateResult",function(){var a=this;a.name=""}),function(){angular.module("navbarAdam",[]).directive("admahesNavbar",function(){return{restrict:"E",scope:{},templateUrl:"layout/navbaradam.html"}})}(),function(){angular.module("project.data.model",[]).factory("Project",["$resource",function(a){return a("http://54.149.197.234/adam/rest/project/:id",{},{update:{method:"PUT"}})}])}(),function(){angular.module("plate.data.model",[]).factory("Plate",["$resource",function(a){return a("http://54.149.197.234/adam/rest/plate/:id",{},{update:{method:"PUT"}})}])}();