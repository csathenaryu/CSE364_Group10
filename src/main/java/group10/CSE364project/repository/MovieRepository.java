package group10.CSE364project.repository;

import group10.CSE364project.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String>{
    List<Movie> findByMovieId(int id);
    List<Movie> findByTitle(String title);
    List<Movie> findAll();
}
