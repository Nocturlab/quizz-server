package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.TypeResource;

@RepositoryRestResource
public interface TypeResourceRepository extends CrudRepository<TypeResource, Integer> {
    public Optional<TypeResource> findByName(String name);
}