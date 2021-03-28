package xxx.yyy;

import java.io.*;

public class Function extends Parsing{



    public CustomList makeTargetTable(String fileName, String[] targetsProp, int targetIndex) {
        CustomList a = new CustomList(1);

        try {

            File file = new File(fileName);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                String[] string_array_line = parseByDelimiter(line, "::");  // delimiter를 parameter로 받으면 안되나
                return_true_or_false(string_array_line, targetsProp);

            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println(e);
        }


        return a;
        // load data
        // read one line
        // Parsing.parseByDelimiter();
        // containTarget()
        // customLIst -> index: ID
        // CustomList 해당 index를 true로 설정
    }

    public boolean return_true_or_false(String[] line, String[] targetsProp)
    {
        for (String str : targetsProp) {
            if(str == line[1]) // line[1]??
            {
                return true;
            }
        }
        return false;
    }

}
