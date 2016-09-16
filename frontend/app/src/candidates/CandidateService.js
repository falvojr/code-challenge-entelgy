(function(){
  'use strict';

  angular.module('candidates')
         .factory('candidateService', candidateService);

candidateService.$inject = ['$http', '$log', 'ENV'];

  /**
   * Candidates data service.
   * Following the John Papa guidelines.
   *
   * @see https://github.com/johnpapa/angular-styleguide
   * @returns {{getCandidates: Function}}
   * @constructor
   */
  function candidateService($http, $log, ENV){
    var service = {
        getCandidates: getCandidates
    };

    return service;

    ////////////

    function getCandidates() {
      return $http.get(ENV.API_HOST + '/candidates')
                  .then(getCandidatesComplete)
                  .catch(getCandidatesFailed);

      function getCandidatesComplete(response) {
          return response.data._embedded.candidates;
      }

      function getCandidatesFailed(error) {
          $log.error('XHR Failed to getCandidates: ' + error.data);
      }
    }
  }

})();
