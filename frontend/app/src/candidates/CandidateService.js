(function(){
  'use strict';

  angular.module('candidates')
         .factory('candidateService', candidateService);

candidateService.$inject = ['$http', '$log'];

  /**
   * Candidates data service.
   * Following the John Papa guidelines.
   *
   * @see https://github.com/johnpapa/angular-styleguide
   * @returns {{getCandidates: Function}}
   * @constructor
   */
  function candidateService($http, $log){
    var service = {
        getCandidates: getCandidates
    };

    return service;

    ////////////

    function getCandidates() {
      return $http.get('http://localhost:8080/candidates')
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
