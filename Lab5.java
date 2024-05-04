

import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
public class Lab5 {
    static int leftAndTopEndOfMartix=0;
    static int rightEndOfMartix;
    static int bottomEndOfMatrix;
    static Kattio io = new Kattio(System.in, System.out);
    static Set<Byte> charsToPrint = new TreeSet<>(); //Litet innhåll, bara alfabetet, tidkomplexiteten är Log n men praktiskt taget samma som hashset O(1), pluss slipper sortera. 
    static int rowsInMatrix;
    static int collumsInMatrix;
    static char currentChar;
    static byte[][] matrix;
    static Set<Byte> validchar= new HashSet<>();
    public static void main(String[] args) {
        
        rowsInMatrix = io.getInt();
        collumsInMatrix = io.getInt();
        rightEndOfMartix= collumsInMatrix - 1; //för att inte gå utanför 
        bottomEndOfMatrix= rowsInMatrix - 1;
        matrix = new byte[rowsInMatrix][collumsInMatrix];
        MakeMatrix(rowsInMatrix, collumsInMatrix);
        io.flush();
        for (int currentColumn=0;currentColumn < collumsInMatrix; currentColumn++){ //Ser till att hitta alla anslutningar i slutet
            if (matrix[bottomEndOfMatrix][currentColumn]==matrix[bottomEndOfMatrix-1][currentColumn]) validchar.add(matrix[bottomEndOfMatrix][currentColumn]);
        }
        // Utför sökning från varje position i den första raden, förutsatt att tecknet inte redan har en väg bekräftad
        for (int currentColumn = 0; currentColumn < collumsInMatrix; currentColumn++) {
                if (!charsToPrint.contains(matrix[0][currentColumn]) && validchar.contains(matrix[0][currentColumn])){
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
    //if (currentChar == (byte) '1') return;
    Stack<int[]> stk = new Stack<>(); //Skapar en stack, körde tidigare med rekursiv sökning men detta ska flytta belastningen från anropsstacken till heapen vilket kanske löser memory problemet.
    //börjar med att lägga startpositionen i stacken, längst ner i loopen kommer allla potentiella vägar läggas till
    stk.push(new int[] {initialRow, initialCollum});

    while (!stk.isEmpty()) {
        // Tar bort och  toppen av stacken, utforskar denna.
        int[] rowCollum = stk.pop(); //Dettta är positionen som ska utforskas
        int row = rowCollum[0]; //Rad 
        int collum = rowCollum[1]; //collum 
        if (currentChar == 1) continue;
        matrix[row][collum]=1;
       
        if (row == bottomEndOfMatrix) {//Hittat en väg!
            charsToPrint.add((currentChar));
            return;//Går vidare och få en ny start
        }
        // Lägger till alla angränsande celler i stacken som är giltiga, först in sist ut, läser matrisen från vänster till höger, och ska neråt, därav dessa i slutet, så de utforskas först
        if (row > leftAndTopEndOfMartix && matrix[row - 1][collum]== currentChar ){stk.push(new int[] {row - 1, collum});} // upp
        if (collum > leftAndTopEndOfMartix && matrix[row ][collum-1] == currentChar ) {stk.push(new int[] {row, collum - 1});} // vänster
        if (collum < collumsInMatrix - 1 && matrix[row][collum+1] == currentChar ) {stk.push(new int[] {row, collum + 1});} // höger
        if (row < rowsInMatrix - 1  && matrix[row + 1][collum] == currentChar ) {stk.push(new int[] {row + 1, collum});} // ner
        
    }
}
    /*i princip årtervunnen från tidigasre labb
     * skriver ut alla element i trädet
     * om det inte finns några element skrivs en nolla ut
     */
    public static void printFromSet(Set<Byte> elements){
        if (!elements.isEmpty()){
        for (byte element : elements) {
            io.print((char) element);
        } return;}
    else {io.println('0');
}}}
