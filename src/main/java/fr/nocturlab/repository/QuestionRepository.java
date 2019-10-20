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
    @Query(value = "SELECT count(*) FROM question q WHERE q.id not in (SELECT distinct r.question_id FROM resultat r WHERE r.account_id == $1)", nativeQuery = true)
    Integer countByNotAlreadyAnswer(Account account);

    @Query(value = "SELECT * FROM question q WHERE q.id not in (SELECT distinct r.question_id FROM resultat r WHERE r.account_id == $1)", nativeQuery = true)
    List<Question> findByNotAlreadyAnswer(Account account);
}