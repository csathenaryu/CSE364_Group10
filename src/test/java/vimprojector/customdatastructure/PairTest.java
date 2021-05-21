package vimprojector.customdatastructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    int size = 7;
    String alphabet = "A:a,B:b,C:c,D:d,E:e,F:f,G:g";
    String[] Uppercase = {"A", "B", "C", "D", "E", "F", "G"};
    String[] Lowercase = {"a", "b", "c", "d", "e", "f", "g"};
    String[] NotInAlphabet = {"1", "2", "3", "X", "Y", "Z"};

    @Test
    public void has_RightElement_True() {
        Pair pair = new Pair(alphabet);
        for(String uppercase : Uppercase){
            assertEquals(true, pair.has(uppercase));
        }
    }

    @Test
    public void has_InvalidElement_False() {
        Pair pair = new Pair(alphabet);
        for(String lowercase : Lowercase){
            assertEquals(false, pair.has(lowercase));
        }
        for(String notInAlphabet : NotInAlphabet){
            assertEquals(false, pair.has(notInAlphabet));
        }
    }

    @Test
    public void has_EmptyPair_False() {
        Pair pair = new Pair("");
        for(String uppercase : Uppercase){
            assertEquals(false, pair.has(uppercase));
        }
        for(String lowercase : Lowercase){
            assertEquals(false, pair.has(lowercase));
        }
        for(String notInAlphabet : NotInAlphabet){
            assertEquals(false, pair.has(notInAlphabet));
        }
    }

    @Test
    public void has_RightElement_Lowercase() {
        Pair pair = new Pair(alphabet);
        for(int i=0; i<size; i++){
            assertEquals(Lowercase[i], pair.get(Uppercase[i]));
        }
    }

    @Test
    public void has_InvalidElement_Null() {
        Pair pair = new Pair(alphabet);
        for(String lowercase : Lowercase){
            assertEquals(null, pair.get(lowercase));
        }
        for(String notInAlphabet : NotInAlphabet){
            assertEquals(null, pair.get(notInAlphabet));
        }
    }

    @Test
    public void get_EmptyPair_Null() {
        Pair pair = new Pair("");
        for(String uppercase : Uppercase){
            assertEquals(null, pair.get(uppercase));
        }
        for(String lowercase : Lowercase){
            assertEquals(null, pair.get(lowercase));
        }
        for(String notInAlphabet : NotInAlphabet){
            assertEquals(null, pair.get(notInAlphabet));
        }
    }

    @Test
    public void getAll_FullPair_Lowercase() {
        Pair pair = new Pair(alphabet);
        String[] all = pair.getAll().toArray(new String[0]);
        assertArrayEquals(Lowercase, all);
    }

    @Test
    public void getAll_EmptyPair_EmptyArray() {
        Pair pair = new Pair("");
        String[] all = pair.getAll().toArray(new String[0]);
        String[] answer = {};
        assertArrayEquals(answer, all);
    }


}