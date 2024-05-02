import java.io.FileInputStream;
import java.util.*;

public class Lab6 {
    public static void main(String[] args) throws Exception {
        Map<String, Vegetable> records = new HashMap<>();
        
        Kattio io = new Kattio(new FileInputStream("vegetables.txt"), System.out);
        int j=0;
        while (io.hasMoreTokens() && j<=1000000) { //Max 1000000 rader i filen 
           j++;
                String type = io.getWord(); // Första ordet är vilken typ av grönask

                ArrayList<String> parts = new ArrayList<>(); //Skapar en arraylist för att hantera resten av orden
                
                //Hittar nummer
                String part = io.getWord();
                int i=0;
                while (true && i<100) { //Sätter en gräns för max antal itteratiner
                    i++; 
                    try {
                        Integer.parseInt(part); //Testar on det är en siffra
                        break; // on siffra, går vi vidare, då har vid urskillt enhet och land
                    } catch (NumberFormatException err) {
                        parts.add(part); // del av namn
                        part = io.getWord(); // nästa ord
                    }
                }

                int size = Integer.parseInt(part);
                String unit = io.getWord(); 
                String country = String.join(" ", parts);

                String key = type + "-" + country;

                Vegetable record = new Vegetable(type, country, size, unit);

                records.merge(key, record, Vegetable::max); //Lägger in den största grönsaken per land, om redan finns, uppdaaterasden om den nya är större

        }
        io.flush();
        List<Vegetable> listToPrint = new ArrayList<>(records.values()); //Lägger alla grejer från hshmapen till en lista för sortering
        Collections.sort(listToPrint); //sortering

        for (Vegetable record : listToPrint) { //skriver ut
            io.println(record);
        }
        io.close();
}}

class Vegetable implements Comparable<Vegetable> {
    private String type;
    private String country;
    private int size;
    private String unit;

    /*
     * initierar en gönsak
     */
    public Vegetable(String type, String country, int size, String unit) {
        this.type = type;
        this.country = country;
        this.size = size;
        this.unit = unit;
    }
/*
 * Returnerar den grönsak som är störst
 */
    public static Vegetable max(Vegetable a, Vegetable b) {
        if (a.size > b.size) {
            return a;
        } else {
            return b;
        }
    }

/*
 * Hur grönsaker ska sorteras
 */
    public int compareTo(Vegetable other) {
        /*
         * Negativt heltal: Det aktuella objektet (this) anses vara mindre än det andra objektet (other), vilket innebär att det bör komma före other i en sorterad sekvens.
                Noll: Det aktuella objektet anses vara lika med det andra objektet, vilket innebär att det inte finns någon preferens för ordningen mellan de två vid sortering.
                    Positivt heltal: Det aktuella objektet anses vara större än det andra objektet, vilket innebär att det bör komma efter other i en sorterad sekvens.
         */

    // avgör först på vilken typ av grönska
    int typeCompare = this.type.compareTo(other.type);
    if (typeCompare != 0) {
        return typeCompare;
    }

    // om samma typ av grönsak, avgör på storlek
    int sizeCompare = Integer.compare(other.size, this.size);
    if (sizeCompare != 0) {
        return sizeCompare;
    }

    // om allt annat är samma, jämför vi landets namn
    return this.country.compareTo(other.country);
}
/*
 * För utskrift
 */
    public String toString() {
        return type + " " + country + " " + size + " " + unit;
    }
}
