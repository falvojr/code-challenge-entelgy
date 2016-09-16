(function(){

  angular
       .module('candidates')
       .controller('CandidateController', CandidateController);

CandidateController.$inject = ['candidateService', 'vcRecaptchaService', '$mdSidenav', '$mdToast', '$log'];

  /**
   * Candidate Controller for the Angular App.

   * @param candidateService
   * @param vcRecaptchaService
   * @param $mdSidenav
   * @param $log
   * @constructor
   */
  function CandidateController(candidateService, vcRecaptchaService, $mdSidenav, $mdToast, $log) {
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
            })
            .catch(function () {
              $mdToast.show(
                $mdToast.simple()
                  .textContent('Falha ao buscar os candidatos!')
                  .hideDelay(3000)
              );
            });
    }

    /**
     * Hide or Show the 'left' sideNav area
     */
    function toggleCandidatesList() {
      $mdSidenav('left').toggle();
      if (vm.widgetId != null) {
        recaptchaReload();
      }
    }

    function selectCandidate (candidate) {
      vm.selected = angular.isNumber(candidate) ? self.candidates[candidate] : candidate;
    }

    function recaptchaSetWidgetId(widgetId) {
        $log.info('Created widget ID: %s', widgetId);
        vm.widgetId = widgetId;
    }

    function recaptchaSubmit(response) {
      $log.info('Response available');
      vm.recaptchaResponse = response;
    }

    function recaptchaReload() {
      $log.info('Captcha expired. Resetting response object');
      vcRecaptchaService.reload(self.widgetId);
      vm.recaptchaResponse = null;
    };
  }

})();
