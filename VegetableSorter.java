import java.io.File;
import java.util.*;

public class VegetableSorter {
    public static void main(String[] args) throws Exception {
        Map<String, Lab6> recordsMap = new HashMap<>();
        Scanner scanner = new Scanner(new File("vegetables.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String type = parts[0];
            String unit = parts[parts.length - 1];
            int size = Integer.parseInt(parts[parts.length - 2]);
            // Kombinera alla delar mellan grönsakstyp och storlek till landets namn
            String country = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 2));
        
            String key = type + "_" + country;
            Lab6 record = new Lab6(type, country, size, unit);
        
            // Uppdatera posten om den aktuella är större än den redan sparade
            if (!recordsMap.containsKey(key) || recordsMap.get(key).getSize() < size) {
                recordsMap.put(key, record);
            }
        }

        scanner.close();

        // Skapa en lista av poster och sortera den
        List<Lab6> recordsList = new ArrayList<>(recordsMap.values());
        Collections.sort(recordsList);

        // Skriv ut de sorterade posterna
        for (Lab6 record : recordsList) {
            System.out.println(record);
        }
    }
}
