package group10.CSE364project.repository;

import group10.CSE364project.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LinkRepository extends MongoRepository<Link, String>{
    List<Link> findByMovieId(int movieId);
}
