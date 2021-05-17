package group10.CSE364project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MoviedataRepository extends JpaRepository<MovieData, String> {

}

