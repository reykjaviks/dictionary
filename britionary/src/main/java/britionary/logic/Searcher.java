package britionary.logic;

import britionary.rig.ParseException;
import britionary.rig.Target;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Luokka tarjoaa metodeita sanojen hakemiseen, sekä erottaa kutsujan
 * sovelluslogiikan alemmasta tasosta.
 */
public class Searcher {

    private final Fetcher fetcher;
    private static Searcher instance = null;

    private Searcher() {
        fetcher = new Fetcher();
    }

    /**
     * Metodi varmistaa, että luokasta voi tehdä vain yhden ilmentymän
     * kerrallaan.
     * 
     * @return          ilmentymä
     */
    public static Searcher getInstance() {
        if (instance == null) {
            instance = new Searcher();
        }
        return instance;
    }
    
    /**
     * Metodi hakee syötettyä sanaa vastaavat synonyymit.
     * 
     * @param   word    hakusana
     * @param   target  BRITS tai ALL
     * @return          merkkijono löydetyistä synonyymeistä
     */
    public String search(String word, Target target) {
        String cleanWord;
        try {
            cleanWord = Converter.convert(word);
        } catch (StringIndexOutOfBoundsException e) {
            return "Index out of bounds: " + e;
        } catch (Exception e) {
            return "Cannot convert word: " + e;
        }

        String json;
        try {
            json = fetcher.fetchJSON(cleanWord);
        } catch (MalformedURLException e) {
            return "Invalid URL: " + e;
        } catch (IOException e) {
            return "No results for \"" + cleanWord + "\"";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Cannot fetch JSON-file: " + e;
        }

        try {
            return Parser.parseJSON(json, target);
        } catch (ParseException e) {
            return e.getMessage() + " for \"" + cleanWord + "\"";
        } catch (Exception e) {
            return "Cannot parse JSON-file: " + e;
        }
    }

}
