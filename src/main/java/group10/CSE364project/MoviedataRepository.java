/*package group10.CSE364project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MoviedataRepository extends JpaRepository<MovieData, String> {

}

*/

package group10.CSE364project;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MoviedataRepository extends MongoRepository<MovieData, String> {


}