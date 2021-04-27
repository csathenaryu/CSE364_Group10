package main.java;

import java.util.Arrays;
import vimprojector.parsinginputargs.*;

public class ArgumentParsingTest {
    public static void main(String[] args) {

        String[] genres;
        String[] occupation;
        String[] age;
        String[] gender;

        String[] aaa = new String[4];
        aaa[3] = "\"Animation|Drama|hardware\"";
        aaa[2] = "\"Retired\"";
        aaa[1] = "\"36\"";
        aaa[0] = "\"M\"";



        // Args[0]: genders
        try{
            if(aaa[0] == "\"\"")
                throw new Exception();
            gender = new ParsingGender().parseProperty(aaa[0], "\\|");
        } catch (Exception e){
            ParsingGender parsingGender = new ParsingGender();
            gender = new ParsingGender().getAllProperty();
        }
        // Args[1]: ages
        try{
            age = ArgsPreprocessing.preprocess(Age.list, aaa[1], "\\|", new Age());
        } catch (Exception e){
            age = Age.list.getAll().toArray(new String[0]);
        }
        // Args[2]: occupation
        try{
            if(aaa[2] == "\"\"")
                throw new Exception();
            occupation = ArgsPreprocessing.preprocess(Occupation.list, aaa[2], "\\|", new Occupation());
        } catch (Exception e){
            occupation = Occupation.list.getAll().toArray(new String[0]);
        }
        // Args[3]: movie genres
        try{
            if(aaa[3] == "\"\"")
                throw new Exception();
            genres = ArgsPreprocessing.preprocess(MovieGenres.list, aaa[3], "\\|", new MovieGenres());
        } catch (Exception e){
            genres = MovieGenres.list.getAll().toArray(new String[0]);
        }

        System.out.println("Gender : " + Arrays.toString(gender));
        System.out.println("Age : " + Arrays.toString(age));
        System.out.println("Genres : "+ Arrays.toString(genres));
        System.out.println("Occupation Code : " + Arrays.toString(occupation));
        System.out.println();
    }
}
