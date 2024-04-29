

/*
 * 
 * I den här uppgiften ska du skriva ett program som kan läsa in en karta, och sedan hitta stigar som förbinder två sidor av den. Kartan består av en matris med M rader och N kolumner, innehållandes bokstäverna A-Z. Programmet ska skriva ut alla bokstäver (i bokstavsordning) som förbinder kartans översta och understa rad med varandra via en eller flera stigar. Två rutor i kartan anses tillhöra samma stig om de ligger antingen direkt ovanför/under, eller bredvid varandra. Inga diagonaler räknas alltså.

Om det inte existerar några stigar alls, ska programmet skriva ut "0" (en nolla).

Notera att stigar kan förgrena sig, alternativt att det kan finnas flera giltiga stigar med samma bokstav, men svaret ska bara innehålla samma bokstav högst en gång, oavsett hur många giltiga stigar som finns med den bokstaven.

Indata
I indata anges först två heltal, som anger antalet rader (M), resp kolumner (N), i den ordningen. På raden därefter följer själva kartan, som M rader med N tecken i varje rad.

Du kan anta att N och M är heltal, och minst 1 och högst 10 000, samt att kartan består av versaler.

Utdata
Enn sträng av bokstäver i alfabetisk ordning, eller siffran 0.
 */
import java.util.TreeSet;
import java.util.Set;

public class Lab5 {

    
    static char[][] map;
    static boolean[][] visited;
    static Set<Character> validChars = new TreeSet<>();
    static int rowsInMatrix;
    static int collumsInMatrix;
    static int leftAndTopEndOfMartix=0;
    static int rightEndOfMartix;
    static int bottomEndOfMatrix;
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        rowsInMatrix = io.getInt();
        collumsInMatrix = io.getInt();
        rightEndOfMartix= collumsInMatrix - 1;
        bottomEndOfMatrix= rowsInMatrix - 1;
        map = new char[rowsInMatrix][collumsInMatrix];
        visited = new boolean[rowsInMatrix][collumsInMatrix];
        ReadMap(rowsInMatrix, collumsInMatrix, io);
        // Utför sökning från varje position i den första raden
        // om det är ett segmet som är samma bokstäver ska det segmetet endast besökas en gång
        // 
        for (int currentCollum = 0; currentCollum < collumsInMatrix; currentCollum++) {
            dfs(0, currentCollum);
        }

        // Skriv ut bokstäverna i alfabetisk ordning
        printFromSet(validChars, io);
        io.close();
    }

    /*
     * Tar in måtten på matrisen och klassen för att läsa in matrisen 
     * p
     */
    public static void ReadMap(int M, int N, Kattio io){        
        for (int i = 0; i < M; i++) {
            String row = io.getWord();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j);
            }}}



    static void dfs(int r, int c) {
        //om vi har nått utanför matrisen alt redan besökt platsen returnerar vi
        if (r < leftAndTopEndOfMartix || c < leftAndTopEndOfMartix || r >= rowsInMatrix || c >= collumsInMatrix || visited[r][c]) return; 
        
        visited[r][c] = true; //vi har besökt denna plats nu

        if (r == bottomEndOfMatrix) { // Om vi når den sista raden har vi en väg
            validChars.add(map[r][c]);
        }

        char currentChar = map[r][c];
        //Går upp om vi inte är längst upp och om tecknet oven är en del av den akturella stigen
        if (r > 0 && map[r-1][c] == currentChar) {
            dfs(r-1, c);} 
        //Går ner om vi inte är vid botten än och om tecknet oven är en del av den akturella stigen
        if (r < bottomEndOfMatrix && map[r+1][c] == currentChar){ 
            dfs(r+1, c);}
        //Går vänster om vi inte är vid vänsterkanten än och om tecknet oven är en del av den akturella stigen
        if (c > 0 && map[r][c-1] == currentChar) {
            dfs(r, c-1);}
        //Går höger 
        if (c < rightEndOfMartix && map[r][c+1] == currentChar) {
            dfs(r, c+1);}

    }

    public static void printFromSet(Set<Character> elements, Kattio io){
        if (!elements.isEmpty()){
        for (Character element : elements) {
            io.println(element);
        } return;}
    else {io.println('0');}}}
