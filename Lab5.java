

import java.util.TreeSet;
import java.util.Set;
import java.util.Stack;
import java.util.BitSet;

public class Lab5 {
    static int leftAndTopEndOfMartix=0;
    static int rightEndOfMartix;
    static int bottomEndOfMatrix;
    static Kattio io = new Kattio(System.in, System.out);
    static BitSet visited; //vilka element som blivit besökta
    static Set<Character> charsToPrint = new TreeSet<>(); //Litet innhåll, bara alfabetet, tidkomplexiteten är Log n men praktiskt taget samma som hashset O(1), pluss slipper sortera. 
    static int rowsInMatrix;
    static int collumsInMatrix;
    static char currentChar;
    static byte[][] matrix;
    public static void main(String[] args) {
        
        rowsInMatrix = io.getInt();
        collumsInMatrix = io.getInt();
        rightEndOfMartix= collumsInMatrix - 1; //för att inte gå utanför 
        bottomEndOfMatrix= rowsInMatrix - 1;
        matrix = new byte[rowsInMatrix][collumsInMatrix];
        visited = new BitSet(rowsInMatrix * collumsInMatrix);
        MakeMatrix(rowsInMatrix, collumsInMatrix);
        // Utför sökning från varje position i den första raden, förutsatt att tecknet inte redan har en väg bekräftad
        for (int currentColumn = 0; currentColumn < collumsInMatrix; currentColumn++) {
                if (!charsToPrint.contains((char) matrix[0][currentColumn])){
                depthFirst(0, currentColumn,matrix[leftAndTopEndOfMartix][currentColumn] );
        }}
        printFromSet(charsToPrint);
        io.close();
    }

    /*
     * Tar in måtten på matrisen och klassen för att läsa in matrisen 
     */
    static void MakeMatrix(int rowsInMatrix, int columnsInMatrix) {
        for (int i = 0; i < rowsInMatrix; i++) {
            String row = io.getWord();
            for (int j = 0; j < columnsInMatrix; j++) {
                matrix[i][j] = (byte) (row.charAt(j)); 
            }
        }
    }


            /*
             * Djupet först sökning
             *
             */
    static void depthFirst(int initialRow, int initialCollum, byte currentChar) {
    
    Stack<int[]> stack = new Stack<>(); //Skapar en stack, körde tidigare med rekursiv sökning men detta ska flytta belastningen från anropsstacken till heapen vilket kanske löser memory problemet.
    //börjar med att lägga startpositionen i stacken, längst ner i loopen kommer allla potentiella vägar läggas till
    stack.push(new int[] {initialRow, initialCollum});

    while (!stack.isEmpty()) {
        // Tar bort och  toppen av stacken, utforskar denna.
        int[] rowCollum = stack.pop();
        int row = rowCollum[0];
        int collum = rowCollum[1];

        
        if (row < 0 || row >= rowsInMatrix || collum < 0 || collum >= collumsInMatrix || 
            visited.get(row * collumsInMatrix + collum) || matrix[row][collum] != currentChar) {//Många krav, men i princip bara en koll om man är utanför marisens gränser eller om det är ett besökt element alternativt ett tecken som har väg redan
            continue;
        }

        visited.set(row * collumsInMatrix + collum);

       
        if (row == bottomEndOfMatrix) {//Hittat en väg!
            charsToPrint.add((char) (currentChar));
            continue;
        }
        // Lägger till alla angränsande celler i stacken 
        stack.push(new int[] {row, collum + 1}); // höger
        stack.push(new int[] {row + 1, collum}); // ner
        stack.push(new int[] {row, collum - 1}); // vänster
        stack.push(new int[] {row - 1, collum}); // upp
    }
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
