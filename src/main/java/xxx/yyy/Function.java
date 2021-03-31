package xxx.yyy;

import java.io.*;
import org.junit.*;

import static xxx.yyy.App.path_of_movies;

public class Function extends Parsing{


    /*fileName = file path, targetsProp = parsed input data, targetIndex = */
    public CustomList makeTargetTable(String fileName, String[] targetsProp, int targetIndex) {



        CustomList a = new CustomList(10000);
        a.dataList.add(0, false);


        try {

            File file = new File(fileName);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                String[] string_array_line = parseByDelimiter(line, "::");  // delimiter를 parameter로 받으면 안되나
                int to = Integer.parseInt(string_array_line[0]);
                a.setAt(to, return_true_or_false(string_array_line, targetsProp, targetIndex));
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println("There is no file");
        }


        return a;
    }

    public boolean return_true_or_false(String[] line, String[] targetsProp, int targetIndex)
    {

        if(targetsProp.length == 0)
            return false;


        for (String str : targetsProp) {
            String a = line[targetIndex];
            a = a.toLowerCase();
            if(!a.contains(str))
            {
                return false;
            }
        }
        return true;
    }
}


