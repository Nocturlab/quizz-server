package fr.nocturlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
	
}