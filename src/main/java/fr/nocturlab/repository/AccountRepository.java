package fr.nocturlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Account;

@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Integer> {
	Account getByPseudoAndPass(String pseudo, byte[] password);
}