package group10.CSE364project.model;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document(collection = "link")
public class Link {

    @Id
    private int movieId;
    private String imdbId;

    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getImdbId() {
        return imdbId;
    }
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String[] getLinkInformation() { return new String[] {String.valueOf(movieId), imdbId}; }
}
