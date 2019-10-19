package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Optional<Category> findByName(String name);
}