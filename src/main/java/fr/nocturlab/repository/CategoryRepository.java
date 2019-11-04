package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.entities.Category;

@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Optional<Category> findByName(String name);
}