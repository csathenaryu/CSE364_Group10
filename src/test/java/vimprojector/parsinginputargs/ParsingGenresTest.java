package vimprojector.parsinginputargs;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingGenresTest {

    String[] mapping = {"children's", "romance", "fantasy", "horror", "musical", "comedy", "war", "thriller", "animation", "adventure", "drama", "mystery", "sci-fi", "action", "crime", "western", "film-noir", "documentary"};

    @Test
    public void parseProperty_NormalGenres_MappedGenres() {
        ParsingGenres parsingGenres = new ParsingGenres();
        String[] test = {"cHildreN's", "RomanCE", "fantasy", "horror", "musical", "comedy", "war", "thriller", "animation", "ADVENTURE", "drama", "mystery", "sci-fi", "action", "crime", "western", "film-noir", "documentary"};
        int size = mapping.length;
        for(int i=0; i<size; i++){
            String[] expected = parsingGenres.parseProperty(test[i],"\\|");
            String[] answer = {mapping[i]};
            assertArrayEquals(expected, answer);
        }
    }

    @Test
    public void parseProperty_MultipleGenres_MappedGenres() {
        ParsingGenres parsingGenres = new ParsingGenres();
        String[] expected = parsingGenres.parseProperty("adventure|animation|documentary","\\|");
        String[] answer = {"adventure", "animation", "documentary"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_RepeatedGenres_MappedGenres() {
        ParsingGenres parsingGenres = new ParsingGenres();
        String[] expected = parsingGenres.parseProperty("adventure|adventure","\\|");
        String[] answer = {"adventure"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_InvalidProperty_MappedGenres() {
        ParsingGenres parsingGenres = new ParsingGenres();
        String[] expected = parsingGenres.parseProperty("sci-fi|board|yesorno","\\|");
        String[] answer = {"sci-fi"};
        assertArrayEquals(expected, answer);
    }

    @Test
    public void parseProperty_NoProperty_MappedGenres() {
        ParsingGenres parsingGenres = new ParsingGenres();
        String[] expected = parsingGenres.parseProperty("board|yesorno","\\|");
        String[] answer = mapping;
        assertArrayEquals(expected, answer);
    }

    @Test
    public void getAllProperty() {
        ParsingGenres parsingGenres = new ParsingGenres();
        String[] expected = parsingGenres.getAllProperty();
        String[] answer = {"children's", "romance", "fantasy", "horror", "musical", "comedy", "war", "thriller", "animation", "adventure", "drama", "mystery", "sci-fi", "action", "crime", "western", "film-noir", "documentary"};
        assertArrayEquals(expected, answer);
    }
}