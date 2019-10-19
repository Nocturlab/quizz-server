package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.TypeResource;

@Repository
public interface TypeResourceRepository extends CrudRepository<TypeResource, Integer> {
    public Optional<TypeResource> findByName(String name);
}