(function(){

  angular
   .module('candidates')
   .controller('CandidateController', CandidateController);

CandidateController.$inject = ['candidateService', 'vcRecaptchaService', '$mdSidenav', '$mdToast', '$mdDialog', '$log'];

  /**
   * Candidate Controller for the Angular App.

   * @param candidateService
   * @param vcRecaptchaService
   * @param $mdSidenav
   * @param $log
   * @constructor
   */
  function CandidateController(candidateService, vcRecaptchaService, $mdSidenav, $mdToast, $mdDialog, $log) {
    var vm = this;

    vm.selected             = null;
    vm.candidates           = [ ];
    vm.selectCandidate      = selectCandidate;
    vm.toggleList           = toggleCandidatesList;
    vm.patchCandidate       = patchCandidate;
    vm.reCaptchaResponse    = null;
    vm.reCaptchaSetWidgetId = reCaptchaSetWidgetId;
    vm.reCaptchaSubmit      = reCaptchaSubmit;
    vm.reCaptchaReload      = reCaptchaReload;

    // Load all registered candidates

    getCandidates();

    // *********************************
    // Internal methods
    // *********************************

    function getCandidates() {
      candidateService
        .getCandidates()
        .then(function(candidates) {
          if(candidates) {
            vm.candidates = candidates;
            vm.selected = candidates[0];
          }
        })
        .catch(function () {
          showToast('Falha ao buscar os candidatos!');
        });
    }

    function showToast(msg) {
      $mdToast.show(
        $mdToast.simple()
          .textContent(msg)
          .hideDelay(3000)
      );
    }

    function getCandidatesSummary() {
      return candidateService.getCandidatesSummary();
    }

    /**
     * Hide or Show the 'left' sideNav area
     */
    function toggleCandidatesList() {
      $mdSidenav('left').toggle();
      if (vm.widgetId != null) {
        reCaptchaReload();
      }
    }

    function selectCandidate (candidate) {
      vm.selected = angular.isNumber(candidate) ? self.candidates[candidate] : candidate;
    }

    function patchCandidate(candidate, ev) {
      var vote = {
        reCaptchaResponse: vm.reCaptchaResponse
      };
      candidate.votes.push(vote);
      candidateService
        .patchCandidate(candidate)
        .then(function() {
            reCaptchaReload();
            $mdDialog.show({
              controller: DialogController,
              controllerAs: 'vmDialog',
              templateUrl: 'templates/dialog.html',
              parent: angular.element(document.body),
              targetEvent: ev,
              locals: {
                reCaptchaResponse: vote.reCaptchaResponse
              }
            });
        })
        .catch(function () {
          showToast('Falha ao registrar o voto!');
        });
    }

    function reCaptchaSetWidgetId(widgetId) {
        $log.info('Created widget ID: %s', widgetId);
        vm.widgetId = widgetId;
    }

    function reCaptchaSubmit(response) {
      $log.info('Response available');
      vm.reCaptchaResponse = response;
    }

    function reCaptchaReload() {
      $log.info('Captcha expired. Resetting response object');
      vcRecaptchaService.reload(self.widgetId);
      vm.reCaptchaResponse = null;
    }

    function DialogController($mdDialog, reCaptchaResponse) {
      var vmDialog = this;
      vmDialog.reCaptchaResponse = reCaptchaResponse;
      vmDialog.close = close;
      vmDialog.chart = { };

      getCandidatesSummary()
        .then(function(chartData) {
          vmDialog.chart = chartData;
        })
        .catch(function () {
          showToast('Falha ao buscar os resultados parciais!');
        });

      function close() {
        $mdDialog.hide();
      }
    }
  }

})();
