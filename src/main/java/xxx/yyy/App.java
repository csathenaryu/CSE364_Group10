package xxx.yyy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App
{

    public void Parse_Input(){

    }


    public void Load_Data(){

        // 어제 말했던 list들을 여기서 선언
        // list 두개

        try{

            File file = new File("C:\\Users\\ajm69\\Desktop\\maven_test\\maven_test\\data\\movies.dat");

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                System.out.println(line);
                // 여기에 Parse_data를 가져오는 건 어떤가요?
                // 여기서 새로운 함수 만들어서 list들을 채워나가는거죠
                //
            }
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("There is no file in data");
        }catch(IOException e){
            System.out.println(e);
        }




    }

    public void Parse_Data(String line){
        //Load Data에서 받아온 line값을 처리해서 정보를 뱉는 함수
        //return array(각정보들의 집합)


    }

    public void Average(){
        //


    }



    public void Get_Averaged_Data(){




    }


    public static void main(String[] args){

    }

}
