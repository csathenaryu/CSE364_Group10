package group10.CSE364project;

import java.util.Objects;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class MovieData {
    //private String id;
    private @Id String title;
    private String genres;
    private String imdb;

    MovieData(){}

    MovieData(String title, String genres, String imdb){
        //this.id = id;
        this.title = title;
        this.genres = genres;
        this.imdb = imdb;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenres() {
        return this.genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public String getImdb() {
        return this.imdb;
    }
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

}
