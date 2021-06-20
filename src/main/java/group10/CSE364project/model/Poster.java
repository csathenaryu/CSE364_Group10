package group10.CSE364project.model;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document(collection = "poster")
public class Poster {

    @Id
    private int posterId;
    private String posterURL;

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
}
