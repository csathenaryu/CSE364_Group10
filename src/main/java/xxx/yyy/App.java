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
        try{
            //파일 객체 생성
            File file = new File("C:\\Users\\ajm69\\Desktop\\maven_test\\maven_test\\data\\movies.dat");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                System.out.println(line);
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("There is no file in data");
        }catch(IOException e){
            System.out.println(e);
        }

    }


    public void Parse_Data(){



    }

    public void Average(){



    }



    public void Get_Averaged_Data(){




    }


    public static void main(String[] args){

    }

}
