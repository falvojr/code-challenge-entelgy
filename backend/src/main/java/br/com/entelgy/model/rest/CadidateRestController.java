package br.com.entelgy.model.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.entelgy.model.Candidate;

/**
 * REST resource with custom end-point.
 * 
 * @author falvojr
 */
@RepositoryRestController
public class CadidateRestController {

	private static final String KEY_LABELS = "labels";
	private static final String KEY_DATA = "data";

	@Autowired
	private CandidateRepository candidateRepository;

	@RequestMapping(value = "/candidates/summary", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Object>>> getCandidatesSummary() {
		try {
			final List<Candidate> candidates = candidateRepository.findAll();
			final Map<String, List<Object>> map = new ConcurrentHashMap<>();
			map.put(KEY_LABELS, new ArrayList<>());
			map.put(KEY_DATA, new ArrayList<>());
			candidates.stream().parallel().forEach((candidate) -> {
				map.get(KEY_LABELS).add(candidate.getName());
				map.get(KEY_DATA).add(candidate.getVotes().size());
			});
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
