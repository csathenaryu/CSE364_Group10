package vimprojector.parsinginputargs;

import java.util.ArrayList;
import vimprojector.customdatastructure.Pair;

public class Gender implements InputArgument{
    // mapping 정보는 "InputFormat:DataFormat"으로 주어지며, ","로 구분되어 있음
    // mapping 정보를 나열할 때는 lowercase로 작성, 공백 없이 작성
    static Pair list = new Pair(
            "m:m,f:f");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg){
        if (list.has(lowerArg) && !mappedArg.contains(lowerArg)){
            mappedArg.add(list.get(lowerArg));
        }
        else{
            System.out.println(lowerArg + " is invalid or already exist. Try using another gender.");
        }
    }
}
