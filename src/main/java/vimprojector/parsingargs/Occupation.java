package vimprojector.parsingargs;

import java.util.ArrayList;
import vimprojector.customdatastructure.Pair;

public class Occupation implements InputArgument{
    // mapping 정보는 "InputFormat:DataFormat"으로 주어지며, ","로 구분되어 있음
    // mapping 정보를 나열할 때는 lowercase로 작성, 공백 없이 작성
    static Pair list = new Pair(
            "other:0,academic:1,educator:1,artist:2,clerical:3,admin:3,collegestudent:4,gradstudent:4,customerservice:5,doctor:6,healthcare:6,executive:7,managerial:7,farmer:8,homemaker:9,k-12student:10,lawyer:11,programmer:12,retired:13,sales:14,marketing:14,scientist:15,self-employed:16,technician:17,engineer:17,tradesman:18,craftsman:18,unemployed:19,writer:20");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg){
        if (list.has(lowerArg) && !mappedArg.contains(lowerArg)){
            mappedArg.add(list.get(lowerArg));
        } else if(!mappedArg.contains("0")){
            System.out.println(lowerArg + " is invalid or already exist. Try using another occupation.");
            mappedArg.add("0");
        }
    }
}
