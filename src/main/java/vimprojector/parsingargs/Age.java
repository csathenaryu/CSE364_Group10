package vimprojector.parsingargs;

import java.util.ArrayList;
import vimprojector.customdatastructure.Pair;

public class Age implements InputArgument{
    static Pair list = new Pair(
            "under18:1,18-24:18,25-34:25,35-44:35,45-49:45,50-55:50,56+:56");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg) {
        int intLowerArg = Integer.parseInt(lowerArg);
        if(intLowerArg < 18)
            mappedArg.add(list.get("under18"));
        else if(intLowerArg <= 24)
            mappedArg.add(list.get("18-24"));
        else if(intLowerArg <= 34)
            mappedArg.add(list.get("25-34"));
        else if(intLowerArg <= 44)
            mappedArg.add(list.get("35-44"));
        else if(intLowerArg <= 49)
            mappedArg.add(list.get("45-49"));
        else if(intLowerArg <= 55)
            mappedArg.add(list.get("50-55"));
        else if(56 <= intLowerArg)
            mappedArg.add(list.get("56+"));
        else {
            System.out.println(lowerArg + " is invalid or already exist. Try using another ages.");
        }
    }
}
