import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

public class Lab4 {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        String S = io.getWord();
        
        List<Character> charList = new ArrayList<>();
        for (char c : S.toCharArray()) {
            charList.add(c);
        }
        
        Set<String> permutations = new TreeSet<>(); // A set to store unique permutations
        generateAllPermutations(charList ,"", permutations);
        printFromSet(permutations, io);
        io.close();
    }

    /*
     * Den a funktion tar in en lista med tecken, en sträng, ett binärt träd
     * rekurivt bygger den upp alla sammansättnignar av de tecken som finns i listan som finns
     * returnerar inget, lägger till de olika sammaställningarna i trädet
     */
    public static void generateAllPermutations(List<Character> chars,  String permutation, Set<String> permutations) {
        if (chars.isEmpty()) {
            permutations.add(permutation);
            return;
        }
        for (int i = 0; i < chars.size(); i++) {
            char character = chars.get(i); //plockar ut tecken på index i
            chars.remove(i); //Tar bort tecknet på index i

            generateAllPermutations(new ArrayList<>(chars),  permutation + character, permutations);
            chars.add(i, character);
        }
    }
      /*
    funktion för utskrift, tar Set och utskriftklassen 
    returnerar inget, kriver ut elementetn ur set
    */
    public static void printFromSet(Set<String> elements, Kattio io){
        for (String element : elements) {
            io.println(element);
        }
        return;
    }
}
