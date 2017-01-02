package britionary.logic;

public class Converter {

    private String word;
    
    /*
     TODO:
     Replace loops with String.replace()
     */
    
    public String getWord() {
        return this.word;
    }

    public void convert(String word) {

        StringBuilder str = new StringBuilder(word.toLowerCase());

        // Replace underscores
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '_') {
                str.setCharAt(i, ' ');
            }
        }
        // Remove digits
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                str.deleteCharAt(i);
                i--;
            }
        }
        // Remove leading space
        if (Character.isSpaceChar(str.charAt(0))) {
            str.deleteCharAt(0);
        }
        // Remove tracing space
        if (Character.isSpaceChar(str.charAt(str.length() - 1))) {
            str.deleteCharAt(str.length() - 1);
        }
        
        this.word = str.toString();

    }

}
