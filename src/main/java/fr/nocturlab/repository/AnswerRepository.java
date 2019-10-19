package fr.nocturlab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.Answer;
import fr.nocturlab.entities.Question;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
	List<Answer> findByQuestion(Question p);
}