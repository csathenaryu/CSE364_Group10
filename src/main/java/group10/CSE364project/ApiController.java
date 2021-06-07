package group10.CSE364project;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import group10.CSE364project.model.*;
import group10.CSE364project.repository.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        return Milestone2.milestone2(args, movieRepository, userRepository, ratingRepository, linkRepository, posterRepository);
    }

    @ResponseBody
    @GetMapping("/movies/recommendations")
    ArrayList<MovieData> recommendByMovies(@RequestParam String title, int limit){
        System.out.println("GET movies recommendations");
        System.out.println(title);
        System.out.println(limit);
        return Milestone3.milestone3(title, limit, movieRepository, userRepository, ratingRepository, linkRepository, posterRepository);
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

    @ResponseBody
    @GetMapping("/movies/ids")
    List<Movie> getMovieByID(@RequestParam int movieID){
        System.out.println("GET movie by ID");

        List<Movie> list = movieRepository.findByMovieId(movieID);
        return list;
    }
    // MovieData class 에 poster link 필드 추가하기

    // Rating 관련 함수 테스트
    @ResponseBody
    @GetMapping("/ratings/users")
    List<Rating> getRatingByUSER(@RequestParam int userID){
        System.out.println("GET rating by USER");
        return ratingRepository.findByUserId(userID);
    }

    @ResponseBody
    @GetMapping("/ratings/movies")
    List<Rating> getRatingByMOVIE(@RequestParam int movieID){
        System.out.println("GET rating by MOVIE");
        return ratingRepository.findByMovieId(movieID);
    }

    @ResponseBody
    @GetMapping("/ratings/targets")
    List<Rating> getRatingByTARGET(@RequestParam int userID, int movieID){
        System.out.println("Get rating by TARGET");
        return ratingRepository.findByUserIdAndMovieId(userID, movieID);
    }

}
