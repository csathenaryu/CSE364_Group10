package xxx.yyy;

import java.io.*;

public class Function extends Parsing{


    /*fileName = file path, targetsProp = parsed input data, targetIndex = */
    public CustomList makeTargetTable(String fileName, String[] targetsProp, int targetIndex) {
        CustomList a = new CustomList(1);

        try {

            File file = new File(fileName);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                String[] string_array_line = parseByDelimiter(line, "::");  // delimiter를 parameter로 받으면 안되나
                int to = Integer.parseInt(string_array_line[0]);
                a.dataList.set(to, return_true_or_false(string_array_line, targetsProp, targetIndex));
            }
            bufReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
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

    public boolean return_true_or_false(String[] line, String[] targetsProp, int targetIndex)
    {
        for (String str : targetsProp) {
            if(str == line[targetIndex])
            {
                return true;
            }
        }
        return false;
    }

}
