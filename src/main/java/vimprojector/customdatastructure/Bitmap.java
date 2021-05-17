package vimprojector.customdatastructure;

import java.util.HashMap;

// 존재하지 않는 key 에 접근할 경우 false 를 반환
// length 가 필요한지는 잘 모르겠음
public class Bitmap {
    // Key: movie id 등
    HashMap<Integer, Boolean> bitmap;
    int length;

    public Bitmap() {
        bitmap = new HashMap<>();
        length = 0;
    }

    public int getLength(){    // get length
        return length;
    }

    public boolean getAt(int index){    // get element
        return bitmap.getOrDefault(index, false);
    }

    public void push(int index, boolean value){    // push element
        setAt(index, value);
    }

    public void setAt(int index, boolean value) {    // set element
        bitmap.put(index, value);
        length++;
    }

    public void print(){
        System.out.println(bitmap);
    }
}
