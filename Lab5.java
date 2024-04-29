

import java.util.TreeSet;
import java.util.Set;

public class Lab5 {
    static int leftAndTopEndOfMartix=0;
    static int rightEndOfMartix;
    static int bottomEndOfMatrix;
    static Kattio io = new Kattio(System.in, System.out);
    static char[][] matrix; //Hela matrisen
    static boolean[][] visited; //vilka element som blivit besökta
    static Set<Character> charsToPrint = new TreeSet<>(); //Litet innhåll, bara alfabetet, tidkomplexiteten är Log n men praktiskt taget samma som hashset O(1), pluss slipper sortera. 
    static int rowsInMatrix;
    static int collumsInMatrix;
    static char currentChar;
    public static void main(String[] args) {
        
        rowsInMatrix = io.getInt();
        collumsInMatrix = io.getInt();
        rightEndOfMartix= collumsInMatrix - 1; //för att inte gå utanför 
        bottomEndOfMatrix= rowsInMatrix - 1;
        matrix = new char[rowsInMatrix][collumsInMatrix];
        visited = new boolean[rowsInMatrix][collumsInMatrix];
        MakeMarix(rowsInMatrix, collumsInMatrix);
        // Utför sökning från varje position i den första raden, förutsatt att tecknet inte redan har en väg bekräftad
        for (int currentColumn = 0; currentColumn < collumsInMatrix; currentColumn++) {
                depthFirst(0, currentColumn,matrix[leftAndTopEndOfMartix][currentColumn] );
        }
        printFromSet(charsToPrint);
        io.close();
    }

    /*
     * Tar in måtten på matrisen och klassen för att läsa in matrisen 
     */
    public static void MakeMarix(int rowsInMatrix, int collumsInMatrix){        
        for (int i = 0; i < rowsInMatrix; i++) {
            String row = io.getWord();
            for (int j = 0; j < collumsInMatrix; j++) {
                matrix[i][j] = row.charAt(j);
            }}}


            /*
             * Djupet först sökning
             * Tar in en kosrinar och vilket tecken vi söker en bana för 
             * söker rekursivt fram en väg till botten
             * om det redan finns en väg kommer ingen sökning att påbörjas
             */
    static void depthFirst(int row, int collum, char currentChar) {

        if (row < leftAndTopEndOfMartix || collum < leftAndTopEndOfMartix || row >= rowsInMatrix || collum >= collumsInMatrix || visited[row][collum] || charsToPrint.contains(matrix[row][collum]) || 
        matrix[row][collum] != currentChar) //Många krav, men i princip bara en koll om man är utanför marisens gränser eller om det är ett besökt element alternativt ett tecken som har väg redan
            return; 
    
        visited[row][collum] = true;

    
        if (row == bottomEndOfMatrix) { //Hittat en väg!
            charsToPrint.add(currentChar);
            return;
        }
        depthFirst(row, collum+1, currentChar);  // höger
        depthFirst(row+1, collum, currentChar);  // ner
        depthFirst(row, collum-1, currentChar);  // vänster
        depthFirst(row-1, collum, currentChar);  // Upp
    }


    /*i princip årtervunnen från tidigasre labb
     * skriver ut alla element i trädet
     * om det inte finns några element skrivs en nolla ut
     */
    public static void printFromSet(Set<Character> elements){
        if (!elements.isEmpty()){
        for (Character element : elements) {
            io.print(element);
        } return;}
    else {io.println('0');
}}}
