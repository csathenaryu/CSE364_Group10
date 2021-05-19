package vimprojector.customdatastructure;

public class OneToMany {
    String argument;
    String[] property;

    public OneToMany(String arg, String[] prop) {
        argument = arg;
        property = prop;
    }

    public int getPropertyLength(){
        return property.length;
    }

    public String getArgument(){
        return argument;
    }

    // 주어진 inputProperty 가 targetProperty 에 포함되어 있는지
    public Boolean contains(String inputProperty){
        boolean isIn = false;
        for(String prop : property){
            if (prop.equals(inputProperty)){
                isIn = true;
                break;
            }
        }
        return isIn;
    }

    // 주어진 inputProperties 를 targetProperty 가 모두 포함하고 있는지
    public Boolean containsAll(String[] inputProperty){
        boolean isIn = true;
        for(String inputProp : inputProperty){
            if(!contains(inputProp)){
                isIn = false;
                break;
            }
        }
        return isIn;
    }

    // 주어진 inputProperties 를 targetProperty 가 하나라도 포함하고 있는지
    public Boolean containsAtLeastOne(String[] inputProperty){
        boolean isIn = false;
        for(String inputProp : inputProperty){
            if(contains(inputProp)){
                isIn = true;
                break;
            }
        }
        return isIn;
    }
}
