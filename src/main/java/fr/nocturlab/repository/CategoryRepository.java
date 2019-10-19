package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.nocturlab.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Optional<Category> findByName(String name);
}