package fr.nocturlab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Account;
import fr.nocturlab.entities.Question;
import fr.nocturlab.entities.Resultat;

@RepositoryRestResource
public interface ResultatRepository extends CrudRepository<Resultat, Integer> {
	List<Resultat> findByAccountOrderByCreationDate(Account account);
	default List<Resultat> findByAccount(Account account){
		return findByAccountOrderByCreationDate(account);
	}

	Optional<Resultat> findByAccountAndQuestionOrderByCreationDate(Account account, Question question);
	default Optional<Resultat> findByAccountAndQuestion(Account account, Question question){
		return findByAccountAndQuestionOrderByCreationDate(account, question);
	}
}