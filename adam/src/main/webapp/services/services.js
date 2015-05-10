'use strict';

/**
 * @ngdoc service
 * @name service:activeProject activePlate activePlateResult wellBoxSize
 * @description
 * # services
 * Services used to share json objects between pages
 * active* objects represent individual database object displayed on page
 *
 */

angular.module('adamServices', ['ngResource'])

  .service('activeProject', function() {
    var activeProject = this;
    activeProject.project = null;
  })

  .service('activePlate', function() {
    var activePlate = this;
    activePlate.plate = null;
  })

  .service('activePlateResult', function() {
    var activePlateResult = this;
    activePlateResult.plateResult = null;
  })

  .service('wellBoxSize', function() {
    var wellBoxSize = this;
    wellBoxSize.size = 35;
  })

;

