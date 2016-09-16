(function(){

  angular
       .module('candidates')
       .controller('CandidateController', CandidateController);

CandidateController.$inject = ['candidateService', 'vcRecaptchaService', '$mdSidenav', '$log'];

  /**
   * Candidate Controller for the Angular App.

   * @param candidateService
   * @param vcRecaptchaService
   * @param $mdSidenav
   * @param $log
   * @constructor
   */
  function CandidateController(candidateService, vcRecaptchaService, $mdSidenav, $log) {
    var vm = this;

    vm.selected             = null;
    vm.candidates           = [ ];
    vm.selectCandidate      = selectCandidate;
    vm.toggleList           = toggleCandidatesList;
    vm.recaptchaResponse    = null;
    vm.recaptchaSetWidgetId = recaptchaSetWidgetId;
    vm.recaptchaSubmit      = recaptchaSubmit;
    vm.recaptchaReload      = recaptchaReload;

    // Load all registered candidates

    loadAllCandidates();

    // *********************************
    // Internal methods
    // *********************************

    function loadAllCandidates() {
      candidateService
            .getCandidates()
            .then(function(candidates) {
              vm.candidates = candidates;
              vm.selected = candidates[0];
            });
    }

    /**
     * Hide or Show the 'left' sideNav area
     */
    function toggleCandidatesList() {
      $mdSidenav('left').toggle();
    }

    function selectCandidate (candidate) {
      vm.selected = angular.isNumber(candidate) ? self.candidates[candidate] : candidate;
    }

    function recaptchaSetWidgetId(widgetId) {
        console.info('Created widget ID: %s', widgetId);
        vm.widgetId = widgetId;
    }

    function recaptchaSubmit(response) {
      console.info('Response available');
      vm.recaptchaResponse = response;
    }

    function recaptchaReload() {
      console.info('Captcha expired. Resetting response object');
      vcRecaptchaService.reload(self.widgetId);
      vm.recaptchaResponse = null;
    };
  }

})();
