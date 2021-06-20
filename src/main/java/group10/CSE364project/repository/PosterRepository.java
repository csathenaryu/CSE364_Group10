package group10.CSE364project.repository;

import group10.CSE364project.model.Poster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PosterRepository extends MongoRepository<Poster, String>{
    List<Poster> findByPosterId(int id);
    List<Poster> findAll();
}
