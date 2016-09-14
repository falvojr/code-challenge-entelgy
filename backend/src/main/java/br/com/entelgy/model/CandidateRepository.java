package br.com.entelgy.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "candidate", path = "candidate")
public interface CandidateRepository extends MongoRepository<Candidate, String> {

	List<Candidate> findByLastName(@Param("name") String name);

}
