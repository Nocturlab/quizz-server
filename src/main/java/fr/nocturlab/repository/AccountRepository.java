package fr.nocturlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	Account getByPseudoAndPass(String pseudo, byte[] password);
}