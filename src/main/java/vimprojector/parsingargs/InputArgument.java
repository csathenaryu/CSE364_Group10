package vimprojector.parsingargs;

import java.util.ArrayList;

public interface InputArgument {
    void filter(ArrayList<String> mappedArg, String lowerArg);
}
