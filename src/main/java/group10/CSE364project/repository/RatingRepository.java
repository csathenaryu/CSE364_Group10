package group10.CSE364project.repository;

import group10.CSE364project.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String>{
    List<Rating> findByRating(int rating);
}
