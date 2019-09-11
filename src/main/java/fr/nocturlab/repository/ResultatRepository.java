package fr.nocturlab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nocturlab.entities.Account;
import fr.nocturlab.entities.Question;
import fr.nocturlab.entities.Resultat;

public interface ResultatRepository extends JpaRepository<Resultat, Integer> {
	List<Resultat> findByAccountOrderByCreationDate(Account account);
	default List<Resultat> findByAccount(Account account){
		return findByAccountOrderByCreationDate(account);
	}

	Resultat findByAccountAndQuestionOrderByCreationDate(Account account, Question question);
	default Resultat findByAccountAndQuestion(Account account, Question question){
		return findByAccountAndQuestionOrderByCreationDate(account, question);
	}
}