package vimprojector.customdatastructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitmapTest {

    @Test
    public void getLength_EmptyBitmap_0() {
        Bitmap bitmap = new Bitmap();
        assertEquals(0, bitmap.getLength());
    }

    @Test
    public void getLength_NotEmptyBitmap_10() {
        Bitmap bitmap = new Bitmap();
        for(int i=0; i<10; i++){
            bitmap.push(i, true);
        }
        assertEquals(10, bitmap.getLength());
    }

    @Test
    public void getAt_RightIndex_FalseIfEvenTrueIfOdd() {
        Bitmap bitmap = new Bitmap();
        for(int i=0; i<10; i++){
            if (i%2 == 0) {
                bitmap.push(i, false);
            }
            else{
                bitmap.push(i, true);
            }
        }
        for(int i=0; i<10; i++){
            if (i%2 == 0) {
                assertEquals(bitmap.getAt(i), false);
            }
            else{
                assertEquals(bitmap.getAt(i), true);
            }
        }
    }

    @Test
    public void getAt_EmptyIndex_False() {
        Bitmap bitmap = new Bitmap();
        for (int i=0; i<100; i++){
            assertEquals(false, bitmap.getAt(i));
        }
    }

    @Test
    public void push_PushTrueAndFalse_FalseIfEvenTrueIfOdd() {
        Bitmap bitmap = new Bitmap();
        for(int i=0; i<100; i++){
            if(i%2 == 0){
                bitmap.push(i, false);
                assertEquals(false, bitmap.getAt(i));
            }
            else{
                bitmap.push(i, true);
                assertEquals(true, bitmap.getAt(i));
            }
        }
    }

    @Test
    public void setAt_InputTrue_SizeAndTrue() {
        int size = 10;

        Bitmap bitmap = new Bitmap();
        for(int i=0; i<size; i++){
            bitmap.setAt(i, true);
        }
        assertEquals(size, bitmap.getLength());
        for(int i=0; i<bitmap.getLength(); i++){
            assertEquals(true, bitmap.getAt(i));
        }
    }

    @Test
    public void setAt_InputFalse_SizeAndFalse() {
        int size = 10;

        Bitmap bitmap = new Bitmap();
        for(int i=0; i<size; i++){
            bitmap.setAt(i, false);
        }
        assertEquals(size, bitmap.getLength());
        for(int i=0; i<bitmap.getLength(); i++){
            assertEquals(false, bitmap.getAt(i));
        }
    }

    @Test
    public void print_PrintAllElements_FalseIfEvenTrueIfOdd() {
        int size = 10;
        Bitmap bitmap = new Bitmap();
        for(int i=0; i<size; i++){
            if (i%2 == 0) {
                bitmap.push(i, false);
            }
            else{
                bitmap.push(i, true);
            }
        }
        bitmap.print();
        assertEquals(size, bitmap.getLength());
    }
}