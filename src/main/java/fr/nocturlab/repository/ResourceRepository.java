package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Resource;

@RepositoryRestResource
public interface ResourceRepository extends CrudRepository<Resource, Integer> {
    public Optional<Resource> findByName(String name);
}