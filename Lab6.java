public class Lab6 implements Comparable<Lab6> {
    private String type;
    private String country;
    private int size;
    private String unit;

    public Lab6(String type, String country, int size, String unit) {
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

    // Implementera compareTo för att sortera först efter grönsakstyp, sedan storlek (fallande), och slutligen land
    @Override
    public int compareTo(Lab6 other) {
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

    @Override
    public String toString() {
        return type + " " + country + " " + size + " " + unit;
    }
}
