package xxx.yyy;

import java.util.ArrayList;
import java.util.Collections;





public class CustomList {
    // dataList: arrayList type
    ArrayList<Boolean> dataList;

    // Constructor: How to use template?
    public CustomList(int a) {
        dataList = new ArrayList<Boolean>(Collections.nCopies(10000,false));
    }

    // get length
    public int getLength(){
        return dataList.size();
    }

    // get element
    public boolean getAt(int index){
        return dataList.get(index);
    }

    // push element
    // data 넣을 때 sorting 필요?
    public void push(int index, boolean value){
        dataList.add(index, value);
    }


}
