package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Answer;

@RepositoryRestResource
public interface AnswerRepository extends CrudRepository<Answer, Integer> {

	@Query(value = "SELECT * FROM Answer a WHERE a.id IN (SELECT distinct answers_id FROM question_answers qa WHERE qa.question_id = :question) AND a.value = :answer", nativeQuery = true)
	Optional<Answer> getByValueAndQuestionId(@Param("answer") String answer, @Param("question") Integer question);
	
}