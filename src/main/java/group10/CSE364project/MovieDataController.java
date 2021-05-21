package group10.CSE364project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class MovieDataController {

    @Autowired
    private MoviedataRepository repository;

    MovieDataController(MoviedataRepository repository) {
        this.repository = repository;
    }
    @ResponseBody
    @GetMapping("/users/recommendations")
    List<MovieData> all(@RequestBody UserData userdata){

        repository.deleteAll();
        Milestone2 milestone2 = new Milestone2();
        //System.out.println(userdata.getUserData()[0]);
        //System.out.println(userdata.getUserData()[1]);
        //System.out.println(userdata.getUserData()[2]);
        //System.out.println(userdata.getUserData()[3]);
        repository = milestone2.milestone2_loadRepository(userdata.getUserData(), repository);
        return repository.findAll();
    }

    @ResponseBody
    @GetMapping("/movies/recommendations")
    List<MovieData> all(@RequestBody InputMovieData inputmoviedata){

        repository.deleteAll();
        Milestone3 milestone3 = new Milestone3();
        //System.out.println(userdata.getUserData()[0]);
        //System.out.println(userdata.getUserData()[1]);
        //System.out.println(userdata.getUserData()[2]);
        //System.out.println(userdata.getUserData()[3]);
        repository = milestone3.milestone3_loadRepository(inputmoviedata.getTitle(), inputmoviedata.getLimit(), repository);
        return repository.findAll();
    }

}
