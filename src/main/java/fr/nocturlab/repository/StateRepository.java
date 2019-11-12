package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.State;

@RepositoryRestResource
public interface StateRepository extends CrudRepository<State, Integer> {
  public Optional<State> findByName(String name);
}
