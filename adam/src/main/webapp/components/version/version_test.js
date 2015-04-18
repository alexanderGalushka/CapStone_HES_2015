'use strict';

describe('hesAdam.version module', function() {
  beforeEach(module('hesAdam.version'));

  describe('version service', function() {
    it('should return current version', inject(function(version) {
      expect(version).toEqual('0.1');
    }));
  });
});
