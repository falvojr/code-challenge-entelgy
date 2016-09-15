(function(){

  angular
       .module('candidates')
       .controller('CandidateController', [
          'candidateService', '$mdSidenav', '$mdBottomSheet', '$timeout', '$log',
          CandidateController
       ]);

  /**
   * Candidate Controller for the Angular App.

   * @param candidateService
   * @param vcRecaptchaService
   * @param $mdSidenav
   * @param $mdBottomSheet
   * @param $timeout
   * @param $$log
   * @constructor
   */
  function CandidateController( candidateService, vcRecaptchaService, $mdSidenav, $mdBottomSheet, $timeout, $log ) {
    var self = this;

    self.selected             = null;
    self.candidates           = [ ];
    self.selectCandidate      = selectCandidate;
    self.toggleList           = toggleCandidatesList;
    self.recaptchaResponse    = null;
    self.recaptchaSetWidgetId = recaptchaSetWidgetId;
    self.recaptchaSubmit      = recaptchaSubmit;
    self.recaptchaReload      = recaptchaReload;

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
     * Select the current candidate
     * @param menuId
     */
    function selectCandidate (candidate) {
      self.selected = angular.isNumber(candidate) ? self.candidates[candidate] : candidate;
    }

    function recaptchaSetWidgetId(widgetId) {
        console.info('Created widget ID: %s', widgetId);
        self.widgetId = widgetId;
    }

    function recaptchaSubmit(response) {
      console.info('Response available');
      self.recaptchaResponse = response;
    }

    function recaptchaReload() {
      console.info('Captcha expired. Resetting response object');
      vcRecaptchaService.reload(self.widgetId);
      $scope.response = null;
    };
  }

})();
