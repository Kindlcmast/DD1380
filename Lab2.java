import java.util.*;
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
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(dictionary.entrySet());
        
        // Sortera listan baserat på antalet förekomster där flest förekomster ligger först
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        // Returnerar första elementets delsträng i den sorterade listan
        return list.get(0).getKey();
    }}