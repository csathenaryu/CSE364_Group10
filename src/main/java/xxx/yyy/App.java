package xxx.yyy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class App
{

    static String path_of_movies = "C:\\Users\\ajm69\\Desktop\\maven\\CSE364_Group10\\data\\movies.dat";
    static String path_of_users = "C:\\Users\\ajm69\\Desktop\\maven\\CSE364_Group10\\data\\users.dat";
    static String path_of_ratings = "C:\\Users\\ajm69\\Desktop\\maven\\CSE364_Group10\\data\\ratings.dat";


    /* file로 받아온 users.dat를 가공해서 해당 직업에 맞는 user의 리스트를 가져오기*/
    public String[] Parse_Data_User(String Occupation){

        String[] arr={};
        try{

            File file = new File(path_of_users);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                System.out.println(line); // 지울것
                //직업이 일치하는 UserID 다 뽑아와서 arr에 삽입
                //Arraylist
                //
            }
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("There is no users.dat file in data");
        }catch(IOException e){
            System.out.println(e);
        }

        return arr;
    }



    /* file로 받아온 movies.dat를 가공해서 해당 장르에 맞는 movie의 리스트를 가져오기*/
    public String[] Parse_Data_Movie(String Genres){

        String[] arr = {};

        try{

            File file = new File(path_of_movies);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                System.out.println(line); // 지울것
                //장르가 같은 movieID를 전부 뽑아와서 arr에다가 삽입
                //ArrayList
                //
            }
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("There is no movies.dat file in data");
        }catch(IOException e){
            System.out.println(e);
        }

        return arr;
    }

    /* UserID list에서 movie를 본게 있으면 평점을 average에 더하고, average_count++ */
    public int Get_Average(String[] MovieID, String[] UserID) throws IOException{

        int average = 0;
        int average_count = 0;
        try{

            File file = new File(path_of_ratings);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                System.out.println(line); // 지울것
                //
                //
                //
            }
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("There is no movies.dat file in data");
        }catch(IOException e){
            System.out.println(e);
        }




        if(average_count == 0){
            return -1;
        }
        else {
            return average / average_count;
        }
    }

    /* Milestone1의 기능 구현 함수 */
    public int Milestone1(String Occupation, String Genres) throws IOException{

        String[] MovieID = Parse_Data_Movie(Genres);
        String[] UserID = Parse_Data_User(Occupation);

        int sum = Get_Average(MovieID, UserID);

        return sum;
    }


    /* Main function */
    public static void main(String[] args) throws IOException{
        App app = new App();
//        System.out.println(app.Milestone1(args[0], args[1]));
        System.out.println(app.Milestone1("a", "b"));

   }

}
