"use strict";!function(){function a(a,b){a.when("/projects",{templateUrl:"project/project.html",controller:"ProjectsCtrl",controllerAs:"projVm"}).when("/plates",{templateUrl:"plate/plate.html",controller:"PlatesCtrl"}).when("/plateeditor",{templateUrl:"plateeditor/plateeditor.html",controller:"PlateeditorCtrl"}).when("/plateresults",{templateUrl:"plateresult/plateresult.html",controller:"PlateResultsCtrl"}).when("/qc",{templateUrl:"qc/qc.html",controller:"QcCtrl"}).otherwise({redirectTo:"/projects"})}angular.module("adamApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","smart-table","mgcrea.ngStrap","ngSlider","project","adamServices","navbarAdam","project.data.model"]).config(a),a.$inject=["$routeProvider","$resourceProvider"]}(),function(){function a(a,b,c,d,e,f,g){function h(a,c){b(a,c)}function i(){p.projectAction="new",p.newproject={name:"",description:"",label:"",owner:"Ivan",creationDate:""},p.newproject.creationDate=g("date")(new Date,"MM/dd/yyyy")}function j(a){p.projectAction="edit",p.newproject=JSON.parse(JSON.stringify(a))}function k(a,b){"new"==a?(e.save(p.newproject),p.projects=p.projects.concat(p.newproject)):(b.name=p.newproject.name,b.description=p.newproject.description,b.label=p.newproject.label,b.owner=p.newproject.owner,b.creationDate=p.newproject.creationDate,b.tags=p.newproject.tags,b.collaborators=p.newproject.collaborators)}function l(b){a.ActiveProject.project=b,a.ActivePlate.plate=""}function m(a,b){a.push({name:b})}function n(a){p.filterowner=a?"Ivan":""}function o(a,b){a.push(b)}var p=this;a.ActiveProject=c,a.ActivePlate=d,p.filterowner="",p.editProject=j,p.addNewProject=i,p.saveChangesProject=k,p.setActiveProject=l,p.addTag=m,p.checkedOwner=n,p.addCollaborator=o,p.deletePr=h,p.projects=e.query(),p.projects2=[{name:"Project b",description:"Cancer research in 2015",owner:"Cindy",tags:[{name:"multi project"},{name:"mouse"}],collaborators:[{name:"Alex"},{name:"Ivan"},{name:"Gerson"}],label:"mouse",creationDate:"2/2/2015"},{name:"Project a",description:"Amazing new medicine",owner:"Nik",tags:[],collaborators:[{name:"Alex"},{name:"Ivan"},{name:"Cindy"}],label:"human",creationDate:"2/12/3015"},{name:"Aiv",description:"Painkiller medicine phase 3",owner:"Alex",tags:[],collaborators:[{name:"Alex"},{name:"Nik"},{name:"Cindy"}],label:"mouse",creationDate:"3/2/2015"},{name:"Zig medicine",description:"Building new research",owner:"Ivan",tags:[],collaborators:[{name:"Cindy"},{name:"Nik"},{name:"Gerson"}],label:"human",creationDate:"2/12/2015"}],p.projectsDisplay=[].concat(p.projects),p.collaborators=[{name:"Alex"},{name:"Cindy"},{name:"Gerson"},{name:"Nik"}]}angular.module("project",["smart-table","mgcrea.ngStrap"]).controller("ProjectsCtrl",a),a.$inject=["$scope","deleteProject","activeProject","activePlate","Project","$http","$filter"]}(),angular.module("adamServices",["ngResource"]).factory("deleteProject",function(){return function(a,b){var c=a.indexOf(b);a.splice(c,1)}}).service("activeProject",function(){var a=this;a.project=""}).service("activePlate",function(){var a=this;a.plate=""}).service("activePlateResult",function(){var a=this;a.name=""}),function(){angular.module("navbarAdam",[]).directive("admahesNavbar",function(){return{restrict:"E",scope:{},templateUrl:"layout/navbaradam.html"}})}(),function(){angular.module("project.data.model",[]).factory("Project",["$resource",function(a){return a("http://54.149.197.234/adam/project",{},{})}])}();