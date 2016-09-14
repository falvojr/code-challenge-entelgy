(function(){

  angular
       .module('candidates')
       .controller('CandidateController', [
          'candidateService', '$mdSidenav', '$mdBottomSheet', '$timeout', '$log',
          CandidateController
       ]);

  /**
   * Main Controller for the Angular Material Starter App
   * @param $mdSidenav
   * @param avatarsService
   * @constructor
   */
  function CandidateController( candidateService, $mdSidenav, $mdBottomSheet, $timeout, $log ) {
    var self = this;

    self.selected     = null;
    self.candidates        = [ ];
    self.selectCandidate   = selectCandidate;
    self.toggleList   = toggleCandidatesList;

    // Load all registered candidates

    candidateService
          .loadAllCandidates()
          .then( function( candidates ) {
            self.candidates    = [].concat(candidates);
            self.selected = candidates[0];
          });

    // *********************************
    // Internal methods
    // *********************************

    /**
     * Hide or Show the 'left' sideNav area
     */
    function toggleCandidatesList() {
      $mdSidenav('left').toggle();
    }

    /**
     * Select the current avatars
     * @param menuId
     */
    function selectCandidate ( candidate ) {
      self.selected = angular.isNumber(candidate) ? self.candidates[candidate] : candidate;
    }

  }

})();
