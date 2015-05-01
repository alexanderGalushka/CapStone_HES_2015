'use strict';

/* Services */

angular.module('adamServices', ['ngResource'])

  .service('activeProject', function() {
    var activeProject = this;
    activeProject.project = "";
  })

  .service('activePlate', function() {
    var activePlate = this;
    activePlate.plate = "";
  })

  .service('activePlateResult', function() {
    var activePlateResult = this;
    activePlateResult.plateResult = "";
  })

;

