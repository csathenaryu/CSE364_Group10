package group10.CSE364project;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

        Milestone2 milestone2 = new Milestone2();
        //System.out.println(userdata.getUserData()[0]);
        //System.out.println(userdata.getUserData()[1]);
        //System.out.println(userdata.getUserData()[2]);
        //System.out.println(userdata.getUserData()[3]);
        repository.deleteAll();
        repository = milestone2.milestone2_setrepository(userdata.getUserData(), repository);
        return repository.findAll();

        //String gender = "F";
        //String age = "25";
        //String occupation = "Grad student";
        //String genres = "Action|War";
        //UserData data = new UserData(gender, age, occupation, genres);
        //System.out.println(data);
        //ObjectMapper objectMapper = new ObjectMapper();
        //UserData userdata = objectMapper.readValue(data, UserData.class);
        //UserData userdata = null;
        //try {
        //    userdata = objectMapper.readValue(data, UserData.class);
        //} catch (JsonProcessingException e) {
        //    e.printStackTrace();
        //}
    }

/*
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        ObjectMapper objectMapper = new ObjectMapper();
        binder.registerCustomEditor(UserData.class, new UserDataEditor(objectMapper));
    }
*/

}
