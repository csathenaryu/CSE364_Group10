package vimprojector.parsinginputargs;

import java.util.ArrayList;
import vimprojector.customdatastructure.Pair;

public class MovieGenres implements InputArgument{
    static Pair list = new Pair(
            "action:action,animation:animation,children's:children's,comedy:comedy,crime:crime,documentary:documentary,drama:drama,fantasy:fantasy,film-noir:film-noir,horror:horror,musical:musical,mystery:mystery,romance:romance,scifi:scifi,thriller:thriller,war:war,western:western");

    @Override
    public void filter(ArrayList<String> mappedArg, String lowerArg){
        if (list.has(lowerArg) && (!mappedArg.contains(lowerArg))){
            mappedArg.add(list.get(lowerArg));
        }
        else {
            System.out.println(lowerArg + " is invalid or already exist. Try using another genres.");
        }
    }
}
