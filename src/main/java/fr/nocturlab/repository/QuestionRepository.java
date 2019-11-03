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
    @Query(value = "SELECT count(*) FROM question q WHERE q.id not in (SELECT distinct r.question_id FROM resultat r WHERE r.account_id = :account)", nativeQuery = true)
    Integer countByNotAlreadyAnswer(@Param("account") Account account);

    @Query(value = "SELECT * FROM question q WHERE q.id not in (SELECT distinct r.question_id FROM resultat r WHERE r.account_id = :account)", nativeQuery = true)
    List<Question> findByNotAlreadyAnswer(@Param("account") Account account);

    default Integer test(Integer i){
        return i;
    }
}