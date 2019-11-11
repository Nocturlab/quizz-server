package fr.nocturlab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Account;
import fr.nocturlab.entities.Question;

@RepositoryRestResource
public interface QuestionRepository extends CrudRepository<Question, Integer> {
    // Count the number of questions you still need to answer.
    @Query(value = "SELECT count(*) FROM question q WHERE q.id not in (SELECT distinct r.question_id FROM resultat r WHERE r.account_id = :account)", nativeQuery = true)
    Integer countByNotAlreadyAnswer(@Param("account") Account account);

    // get the 5 question that was closest of the difficulty of the player
    @Query(value = "SELECT * FROM question q WHERE q.id IN (SELECT q.id FROM question q WHERE q.id not in (SELECT distinct r.question_id FROM resultat r WHERE r.account_id = :account) ORDER BY abs(q.difficulty - (SELECT difficulty FROM account WHERE id=:account)) LIMIT 10)", nativeQuery = true)
    List<Question> findByNotAlreadyAnswer(@Param("account") Account account);
}
