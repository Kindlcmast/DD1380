import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.util.*;

public class Lab6 {
    // Huvudmetoden som körs när programmet startar
    public static void main(String[] args) throws Exception {
        // Använder en HashMap för att lagra unika poster
        Map<String, Vegetable> recordsMap = new HashMap<>();
        
        // Läser in filen med grönsaker med UTF-8-teckenkodning
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(new File("vegetables.txt")), "UTF-8"
            )
        );

        String line;
        // Läser filen rad för rad
        while ((line = reader.readLine()) != null) {
            // Delar upp raden i delar baserat på mellanslag
            String[] parts = line.split("\\s+");
            // Första delen är grönsakstyp
            String type = parts[0];
            // Sista delen är enheten (cm, hg etc.)
            String unit = parts[parts.length - 1];
            // Näst sista delen är storleken
            int size = Integer.parseInt(parts[parts.length - 2]);
            // Samlar ihop landets namn som kan vara uppdelat på flera delar
            String country = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 2));

            // Skapar en nyckel för HashMap baserat på typ och land
            String key = type + "_" + country;
            // Skapar ett nytt Lab6-objekt
            Vegetable record = new Vegetable(type, country, size, unit);

            // Uppdaterar HashMap med den största storleken av grönsak som hittills hittats
            recordsMap.merge(key, record, Vegetable::max);
        }

        // Stänger filinläsaren
        reader.close();

        // Konverterar map-värdena till en lista och sorterar den
        List<Vegetable> recordsList = new ArrayList<>(recordsMap.values());
        Collections.sort(recordsList);

        // Skriver ut de sorterade posterna
        for (Vegetable record : recordsList) {
            System.out.println(record);
        }
    }
}

// Vegetable-klassen som representerar en poster av en grönsak
class Vegetable implements Comparable<Vegetable> {
    private String type;
    private String country;
    private int size;
    private String unit;

    // Konstruktor
    public Vegetable(String type, String country, int size, String unit) {
        this.type = type;
        this.country = country;
        this.size = size;
        this.unit = unit;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public int getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }
    public static Vegetable max(Vegetable a, Vegetable b) {
        return a.size > b.size ? a : b;
    }
    // Implementerar compareTo för att sortera poster
    public int compareTo(Vegetable other) {
        int typeCompare = this.type.compareTo(other.type);
        if (typeCompare != 0) {
            return typeCompare;
        } else {
            int sizeCompare = Integer.compare(other.size, this.size); // Storlek i fallande ordning
            if (sizeCompare != 0) {
                return sizeCompare;
            } else {
                return this.country.compareTo(other.country); // Land i stigande ordning
            }
        }
    }

    // toString-metod för utskrift
    public String toString() {
        return type + " " + country + " " + size + " " + unit;
    }
}
