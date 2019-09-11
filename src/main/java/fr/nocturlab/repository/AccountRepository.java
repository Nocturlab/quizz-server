package fr.nocturlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nocturlab.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account getByPseudoAndPass(String pseudo, byte[] password);
}