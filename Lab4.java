import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

public class Lab4 {
    /*
    I den här uppgiten ska du läsa in en sträng S, 
    och skriva ut en radbruten lista med alla unika permutationer som kan göras av strängen, 
    i alfabetisk ordning. Med unika avses att din lista inte ska innehålla några dubletter.

    Exempel: S = "ABA". De unika möjliga permutationerna är "AAB", "ABA" och "BAA".
    */
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        String S = io.getWord();
/**
 * splittar upp S, strägnen,  i en litsta [S,T,R,Ä,N,...] 
 */
        List<Character> charList = new ArrayList<>();
        for (char c : S.toCharArray()) {
            charList.add(c);}
        
        Set<String> permutations = new TreeSet<>(); // set, endast unika premutationer, tree sparar i rätt ordning med liten tidskomplexitet

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
