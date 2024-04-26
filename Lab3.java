import java.math.BigInteger;
/*
*Lab 3, voi ska i denna labb konvertera två tal i en bas till deras produkt i en annan bas
* användr classen BigInteger, detta pga att det fanns smidiga metoder att genomföra basbytena
* https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html
*/

public class Lab3 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);        
        int initialBase = io.getInt();
        int outputBase = io.getInt();
        String firstFactor = io.getWord();
        String secondFactor = io.getWord();
        String resultInBaseM = convertAndCalculate(initialBase, outputBase, firstFactor, secondFactor);
        io.println(resultInBaseM);
        io.close();
    }
//Denna funktion konverterar (number) från valfri bas (base)till number i bas 10
// Tar in nummer och bas, retunerar nummer i bas 10
    public static BigInteger ToBase10(String number, int base) {
        return new BigInteger(number, base);
    }
    
//Denna funktion konverterar (number) från bas 10 till bas NewBase
//Tar in nummer och bas, retiranerar i nummer i den basen
    public static String from10ToNewBase(BigInteger number, int newBase) {
        return number.toString(newBase).toUpperCase();
    }   
//Denna funktion sköter själa multiplikationen och i vilken orning som konverteringen sker
//Tar in två baser, inbas och utbas, faktor1, faktor2, returnerar produkt i utbas
    public static String convertAndCalculate(int initialBase, int outputBase, String firstFactor, String secondFactor) {
        BigInteger factor1 = ToBase10(firstFactor, initialBase);
        BigInteger factor2 = ToBase10(secondFactor, initialBase);
        BigInteger product = factor2.multiply(factor1);
        return  from10ToNewBase(product, outputBase);
    }
}
