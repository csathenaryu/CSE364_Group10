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
            if(aaa[0] == "\"\""){
                throw new Exception();
            }
            gender = new ParsingGender().parseProperty(aaa[0], "\\|");
        } catch (Exception e){
            gender = new ParsingGender().getAllProperty();
        }
        // Args[1]: ages
        try{
            if(aaa[1] == "\"\""){
                throw new Exception();
            }
            age = new ParsingAge().parseProperty(aaa[1], "\\|");
        } catch (Exception e){
            age = new ParsingAge().getAllProperty();
        }
        // Args[2]: occupation
        try{
            if(aaa[2] == "\"\""){
                throw new Exception();
            }
            occupation = new ParsingOccupation().parseProperty(aaa[2], "\\|");
        } catch (Exception e){
            occupation = new ParsingOccupation().getAllProperty();
        }
        // Args[3]: movie genres
        try{
            if(aaa[3] == "\"\""){
                throw new Exception();
            }
            genres = new ParsingGenres().parseProperty(aaa[3], "\\|");
        } catch (Exception e){
            genres = new ParsingGenres().getAllProperty();
        }

        System.out.println("Gender : " + Arrays.toString(gender));
        System.out.println("Age : " + Arrays.toString(age));
        System.out.println("Genres : "+ Arrays.toString(genres));
        System.out.println("Occupation Code : " + Arrays.toString(occupation));
        System.out.println();
    }
}
