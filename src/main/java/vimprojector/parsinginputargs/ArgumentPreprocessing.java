package vimprojector.parsinginputargs;

import java.util.ArrayList;
import vimprojector.parsinginputargs.ArgumentParser;
import vimprojector.customdatastructure.Pair;


// ArgsPreprocessing: input argument 데이터 전처리
// preprocess: inputArg 를 parsing 하여 String[]으로 반환, filter 함수를 이용하여 잘못된 입력값은 걸러내고 제대로 된 입력값만 걸러내어 filtered에 저장
// transform: filtering 후 남은 argument 가 없는 경우, 모든 property 를 반환함
public class ArgumentPreprocessing{

    // inputArgument 랑, targetArgument 의 list 는 사실상 같은 객체임
    // 어떻게 문제를 해결하는 게 좋을까?
    public static String[] preprocess(Pair inputArgument, String inputArg, String delimiter, InputArgument targetArgument){

        String a = inputArg.substring(1, inputArg.length()-1);

        String[] parsedArg = ArgumentParser.parseByDelimiter(a, delimiter);

        ArrayList<String> filtered = new ArrayList<>();
        for (String arg : parsedArg){
            targetArgument.filter(filtered, arg.toLowerCase());
        }
        return transform(filtered, inputArgument);
    }

    public static String[] transform(ArrayList<String> mappedArg, Pair inputArgument){
        ArrayList<String> temp;
        if (mappedArg.size() != 0){
            temp = mappedArg;
        } else{
            temp = inputArgument.getAll();
            System.out.println("There is no valid input. Terminate program.");
            System.exit(0);
        }
        return temp.toArray(new String[0]);
    }
}