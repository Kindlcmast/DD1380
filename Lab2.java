import java.util.HashMap;
import java.util.Map;

public class Lab2 {
    /*
     * Main funktion
     * Tar  in antal n och sträng sting
     * skriver ut den delsträng som förekommer n antal gånger i string
     */
    public static void main(String[] args) {
        
       Kattio io = new Kattio(System.in, System.out);
       int n = io.getInt();
       String string = io.getWord();
       String Thestring = findMostCommonSubstring(string, n);
       io.println(Thestring);
       io.close();
    }
    /*
     * Funktionen taR  in en sträng och en int
     * skapar en hashmap för att spara förekomsten av varje delsträng med längden n
     * loopar igenom strängen för att se förekomsten
     * returnerar den sträng som förekommer oftast/lika men kommer före alfabetiskt
     */
    public static String findMostCommonSubstring(String string, int n) {
        Map<String, Integer> dictionary = new HashMap<>();
        // Loopar igenom  hela strängen,
        /*
         * om strängen ser ut såhär:
         * asdfghjk
         * och n=3
         * Kommer loopningen se ut såhär: [asd]fghjk,  a[sdf]ghjk,  as[dfgh]jk,  asd[fgh]jk,  asdf[ghj]k,  asdfg[hjk]
         * i detta fall kommer funktionen returnara 1
         *
         */
        for (int i = 0; i <= string.length() - n; i++) {
            String substring = string.substring(i, i + n);
            int number = dictionary.getOrDefault(substring, 0) + 1; //getOrDefault ger värdet för nyckeln(strängen), defult 0 för de strängar som inte har något värde tillskrivit
            dictionary.put(substring, number);//Uppdaterar  Hashmapen
        }
        
        /*
         * loopar över hela hashmapen för att kolla vilken sträng som hade mäst förekomst
         */
        String theSubsrtring=""; //För att få sista if att funka
        int TempMaxNumber = 0; //Initerar med 0 för att få första loopen att funka
        for (Map.Entry<String, Integer> dictionaryEntry : dictionary.entrySet()) {
            String substring = dictionaryEntry.getKey(); //Hämtar sträng från hashmap
            int number = dictionaryEntry.getValue(); //Hämtar antal förekomster

            //uppdaterar vilken substräng som är vanligast om den aktruella är vanligast 
            if (number > TempMaxNumber) {
                theSubsrtring = substring;
                TempMaxNumber = number;
            }
            //Om den aktuella är lika förekomlig och ligger före alfabetiskt
            if (number == TempMaxNumber && substring.compareTo(theSubsrtring) == -1){ //compareTo ger 1 om current är större än moast common, 0 om de är lika , -1 om current om mindre
                theSubsrtring = substring;
                TempMaxNumber = number;
            }
    }
        return theSubsrtring;
    }}