package fr.nocturlab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.Account;
import fr.nocturlab.entities.Answer;
import fr.nocturlab.entities.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query(value = "SELECT count(*) FROM question q WHERE q not in (SELECT FROM resultat r WHERE r.account == $1)", nativeQuery = false)
    Integer countByNotAlreadyAnswer(Account account);

    @Query(value = "SELECT * FROM question q WHERE q not in (SELECT id FROM resultat r WHERE r.account == $1)", nativeQuery = false)
    List<Question> findByNotAlreadyAnswer(Account account);

    boolean existsByIdAndValidAnswer(Integer id, Answer answer);
    default boolean validate(Question question, Answer answer){
        return existsByIdAndValidAnswer(question.getId(), answer);
    }
}