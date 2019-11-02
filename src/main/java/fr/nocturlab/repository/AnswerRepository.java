package fr.nocturlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Answer;

@RepositoryRestResource
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
	
}