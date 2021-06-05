package group10.CSE364project;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import group10.CSE364project.model.*;
import group10.CSE364project.repository.*;
import org.testng.annotations.BeforeMethod;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin //(origins = "http://localhost:8080", allowedHeaders = "*")
public class ApiController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private PosterRepository posterRepository;

    @PostConstruct
    public void dropDB(){
        System.out.println("wpqkf..b");
        mongoTemplate.getDb().drop();
    }

    @ResponseBody
    @GetMapping("/users/recommendations")
    ArrayList<MovieData> recommendByUsers(@RequestParam String gender, String age, String occupation, String genres){
        System.out.println("GET users recommendations");
        String[] args = {gender, age, occupation, genres};
        for (String arg:args) {
            System.out.println(arg);
        }
        return Milestone2.milestone2(args, "data/ratings.dat");
    }

    @ResponseBody
    @GetMapping("/movies/recommendations")
    ArrayList<MovieData> recommendByMovies(@RequestParam String title, int limit){
        System.out.println("GET movies recommendations");
        System.out.println(title);
        System.out.println(limit);
        return Milestone3.milestone3(title, limit, "data/ratings.dat");
    }

    @ResponseBody
    @GetMapping("/movies/title")
    ArrayList<String> getMovieTitleList(){
        System.out.println("GET movie title list");

        List<Movie> list = movieRepository.findAll();
        ArrayList<String> movieList = new ArrayList<>();
        for(Movie d : list){
            movieList.add(d.getTitle());
        }
        return movieList;
    }

    @ResponseBody
    @GetMapping("/movies")
    List<Movie> getMovieList(){
        System.out.println("GET movie title list");
        List<Movie> list = movieRepository.findAll();
        return list;
    }

    // MovieData class 에 poster link 필드 추가하기
}
