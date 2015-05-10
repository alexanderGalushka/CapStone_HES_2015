'use strict';
/**
 * @ngdoc filter
 * @name class.grid.filter:classgrid
 * @description
 * # classgrid
 * Filter that update div class to adjust layout according to open left panels
 *
 */

(function() {

  angular.module('classgridfilter', [])

    .filter("classgrid",  function() {
      return function(input) {


        if(input == null) {
          return "col-lg-8";
        }else if(input){
          return "col-lg-12";
        }else{
          return "col-lg-8";
        }

      }
    })

})();
