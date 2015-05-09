'use strict';

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
