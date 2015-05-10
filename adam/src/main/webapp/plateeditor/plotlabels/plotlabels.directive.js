'use strict';

/**
 * @ngdoc directive
 * @name plotlabels.directive:adamhesPlotlabels
 * @description
 * # adamhesPlotlabels
 * Directive used to plot labels on plate map
 *
 */

(function() {
  angular.module('plotlabels', ['ngAnimate','ngSanitize', 'smart-table','mgcrea.ngStrap'])

    .directive('adamhesPlotlabels', function() {
      return {
        restrict: 'E',
        scope: {
          asideind:'=',
          labelsdisplay:'=',
          dropwellgroup:"=",
          rows:"=",
          filterplateeditor:"=",
          activeplate:'='
        },
        templateUrl: 'plateeditor/plotlabels/plotlabels.html',
        controller:PlotLabelsCtrl,
        controllerAs: 'plotlbVm'
      };
    });

  PlotLabelsCtrl.$inject = ['resetSelection'];
  function PlotLabelsCtrl(resetSelection) {
    var plotlbVm = this;
    //var uniqueLabelValues;
    //var uniqueLabelValuesDisplay;
    //var uniqueLabelValuesColors;

    plotlbVm.setUniqLabelValues = setUniqLabelValues;
    plotlbVm.setModeCopyLabelValue = setModeCopyLabelValue;
    plotlbVm.updateUniqLabelValues = updateUniqLabelValues;

    //plotlbVm.uniqueLabelValues = [];
    //plotlbVm.uniqueLabelValuesDisplay = [];
    //plotlbVm.uniqueLabelValuesColors = [];


    function setUniqLabelValues(wells,labelName,dropwellgroup,activeplate){
      var argLabel = {};
      var argLabelColor = {};
      /*var hlsColor;
       var hlsColorSat;*/
      var hlsColorStyle;
      var hlsColorHueIncr;
      var rgbColor;

      dropwellgroup.checked = "true";

      activeplate.uniqueLabelValues = [];
      activeplate.uniqueLabelValuesColors = [];

      if(wells != null && labelName != null) {
        var uniq = uniqueMemebers(wells, labelName);

        if(uniq.length != 0 && uniq  != null) {
          hlsColorHueIncr = 300 / (uniq.length);
          console.log(JSON.stringify( hlsColorHueIncr, null, 4));
          argLabelColor = {};
          for (var i = 0; i < uniq.length; i++) {
            rgbColor = hslToRgb(((i * hlsColorHueIncr) + 20)/360,1,0.5);
            console.log(JSON.stringify( uniq[i].name, null, 4));
            console.log(JSON.stringify( ((i * hlsColorHueIncr) + 20)/360, null, 4));
            var r = rgbColor[0].toString(16)!= 0?pad(rgbColor[0].toString(16),2):"00";
            var g = rgbColor[1].toString(16)!= 0?pad(rgbColor[1].toString(16),2):"00";
            var b = rgbColor[2].toString(16)!= 0?pad(rgbColor[2].toString(16),2):"00";
            hlsColorStyle = "#" + r + g + b;
            console.log(JSON.stringify( hlsColorStyle, null, 4));

            argLabel = {};
            argLabel.name = uniq[i].name;
            argLabel.description = uniq[i].name + '&nbsp;&nbsp;<label style="background-color:' + hlsColorStyle + ';">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>';
            argLabel.color = hlsColorStyle;
            activeplate.uniqueLabelValues.push(argLabel);


            argLabelColor[uniq[i].name] = hlsColorStyle;

          }
        }

        activeplate.uniqueLabelValuesDisplay = [].concat(activeplate.uniqueLabelValues);
        activeplate.uniqueLabelValuesColors = argLabelColor;
        activeplate.plotLabelName = labelName;
        activeplate.labelValueColors = activeplate.uniqueLabelValuesColors;
      }

    }

    function pad(num, size) {
      var s = num+"";
      while (s.length < size) s = "0" + s;
      return s;
    }

    /**
     * Converts an RGB color value to HSL. Conversion formula
     * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
     * Assumes r, g, and b are contained in the set [0, 255] and
     * returns h, s, and l in the set [0, 1].
     *
     * @param   Number  r       The red color value
     * @param   Number  g       The green color value
     * @param   Number  b       The blue color value
     * @return  Array           The HSL representation
     */
    function rgbToHsl(r, g, b) {
      r /= 255, g /= 255, b /= 255;
      var max = Math.max(r, g, b), min = Math.min(r, g, b);
      var h, s, l = (max + min) / 2;

      if(max == min){
        h = s = 0; // achromatic
      }else{
        var d = max - min;
        s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
        switch(max){
          case r: h = (g - b) / d + (g < b ? 6 : 0); break;
          case g: h = (b - r) / d + 2; break;
          case b: h = (r - g) / d + 4; break;
        }
        h /= 6;
      }

      return [h, s, l];
    }

    /**
     * Converts an HSL color value to RGB. Conversion formula
     * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
     * Assumes h, s, and l are contained in the set [0, 1] and
     * returns r, g, and b in the set [0, 255].
     *
     * @param   Number  h       The hue
     * @param   Number  s       The saturation
     * @param   Number  l       The lightness
     * @return  Array           The RGB representation
     */
    function hslToRgb(h, s, l) {
      var r, g, b;

      if(s == 0){
        r = g = b = l; // achromatic
      }else{
        var hue2rgb = function hue2rgb(p, q, t){
          if(t < 0) t += 1;
          if(t > 1) t -= 1;
          if(t < 1/6) return p + (q - p) * 6 * t;
          if(t < 1/2) return q;
          if(t < 2/3) return p + (q - p) * (2/3 - t) * 6;
          return p;
        }

        var q = l < 0.5 ? l * (1 + s) : l + s - l * s;
        var p = 2 * l - q;
        r = hue2rgb(p, q, h + 1/3);
        g = hue2rgb(p, q, h);
        b = hue2rgb(p, q, h - 1/3);
      }

      return [Math.round(r * 255), Math.round(g * 255), Math.round(b * 255)];
    }

    function uniqueMemebers(list,field) {
      var arr = [];
      var arrObj = [];

      for(var i = 0; i < list.length; i++) {
            if (list[i][field] != null && !containsMemeber(arr, list[i][field])) {
            arr.push(list[i][field]);
            //var tag = '<label style="background-color:hsl(20,50%,50%);">' + '&nbsp;&nbsp;&nbsp;' + '</label>';
            //arrObj.push({'name':list[i][field],'description': list[i][field] + tag });
            arrObj.push({'name':list[i][field] });
          }
      }
      return arrObj;
    }

    function containsMemeber(arr,member) {
      for(var i = 0; i < arr.length; i++) {
        if(arr[i] === member) return true;
      }
      return false;
    }

    function setModeCopyLabelValue(labelValue, dropwellgroup, activeplate){
      activeplate.copyLabel = labelValue;
      dropwellgroup.checked = "";

    }

    function updateUniqLabelValues(label, newColor, activeplate){
      activeplate.uniqueLabelValuesColors[label] = newColor;
      activeplate.labelValueColors = activeplate.uniqueLabelValuesColors;

    }
  }
})();
