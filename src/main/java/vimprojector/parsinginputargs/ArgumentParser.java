package vimprojector.parsinginputargs;

public class ArgumentParser {
    public static String[] parseByDelimiter(String rawData, String delimiter){
        return rawData.split(delimiter);
    }
}
