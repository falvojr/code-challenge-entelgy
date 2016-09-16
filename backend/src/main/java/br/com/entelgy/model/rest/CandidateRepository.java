package br.com.entelgy.model.rest;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.entelgy.model.Candidate;

/**
 * REST resource with your default end-points.
 * 
 * @author falvojr
 */
@RepositoryRestResource(collectionResourceRel = "candidates", path = "candidates")
public interface CandidateRepository extends MongoRepository<Candidate, ObjectId> {

}
