package fr.nocturlab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.nocturlab.entities.Resource;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Integer> {
    public Optional<Resource> findByName(String name);
}