package group10.CSE364project.repository;

import group10.CSE364project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String>{
    List<User> findByUserId(int id);
}
