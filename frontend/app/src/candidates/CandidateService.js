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
        getCandidates: getCandidates,
        patchCandidate: patchCandidate,
        getCandidatesSummary: getCandidatesSummary
    };

    return service;

    ////////////

    function getCandidates() {
      return $http.get(ENV.API_HOST + '/candidates')
        .then(getCandidatesComplete);

      function getCandidatesComplete(response) {
          return response.data._embedded.candidates;
      }
    }

    function patchCandidate(candidate) {
      var data = {
        votes: candidate.votes
      }
      // This is RESTful ;)
      return $http.patch(candidate._links.self.href, data)
        .then(patchCandidateComplete);

      function patchCandidateComplete(response) {
          return response.data;
      }
    }

    function getCandidatesSummary() {
      return $http.get(ENV.API_HOST + '/candidates/summary')
        .then(getCandidatesSummaryComplete);

      function getCandidatesSummaryComplete(response) {
          return response.data;
      }
    }
  }

})();
