package fr.nocturlab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nocturlab.entities.Answer;
import fr.nocturlab.entities.Question;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	List<Answer> findByQuestion(Question p);
}