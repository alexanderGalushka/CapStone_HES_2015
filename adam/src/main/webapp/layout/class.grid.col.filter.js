'use strict';

(function() {

  angular.module('classgridfilter', [])

    .filter("classgrid",  function() {
      return function(input) {


        if(input == null) {
          return "col-md-8";
        }else if(input){
          return "col-md-12";
        }else{
          return "col-md-8";
        }

      }
    })

})();
