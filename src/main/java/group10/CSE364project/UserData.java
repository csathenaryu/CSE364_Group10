package group10.CSE364project;

import java.util.Objects;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class UserData {
    private @Id String gender;
    private String age;
    private String occupation;
    private String genres;

    UserData(){}

    UserData (String gender, String age, String occupation, String genres){
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.genres = genres;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getOccupation() {
        return this.occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getGenres() {
        return this.genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String[] getUserData (){
        String userData[] = new String[4];
        userData[0] = this.gender;
        userData[1] = this.age;
        userData[2] = this.occupation;
        userData[3] = this.genres;

        return userData;
    }
}
