package group10.CSE364project.model;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document(collection = "movie")
public class Movie {

    @Id
    private int movieId;
    private String title;
    private String genres;
    /*
    private int posterId;
    private String posterURL;

     */


    public int getMovieId(){
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }

    /*
    public int getPosterId(){
        return posterId;
    }
    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public String getPosterURL() {
        return posterURL;
    }
    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

     */
}
