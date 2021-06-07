package group10.CSE364project.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Document(collection = "rating")
public class Rating {

    @Id
    private int userId;
    private int movieId;
    private int rating;
    private String timestamp;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String[] getRatingInformation() { return new String[] {String.valueOf(userId), String.valueOf(movieId), String.valueOf(rating), timestamp}; }
}
