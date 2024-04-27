import java.util.HashSet;
import java.util.Set;

public class Lab5 {
    static int M, N;
    static char[][] map;
    static boolean[][] visited;
    static Set<Character> validChars = new HashSet<>();

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        M = io.getInt();
        N = io.getInt();

        map = new char[M][N];
        visited = new boolean[M][N];

        // Läs in kartan
        for (int i = 0; i < M; i++) {
            String row = io.nextLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        // Utför sökning från varje position i den första raden
        for (int col = 0; col < N; col++) {
            dfs(0, col);
        }

        // Skriv ut bokstäverna i alfabetisk ordning
        if (validChars.isEmpty()) {
            System.out.println("0");
        } else {
            validChars.stream().sorted().forEach(System.out::print);
        }
        io.close();
    }

    static void dfs(int r, int c) {
        if (r < 0 || c < 0 || r >= M || c >= N || visited[r][c]) return;
        visited[r][c] = true;

        if (r == M - 1) { // Om vi når den sista raden
            validChars.add(map[r][c]);
        }

        // Gå upp, ner, vänster, höger
        char currentChar = map[r][c];
        if (r > 0 && map[r-1][c] == currentChar) dfs(r-1, c);
        if (r < M - 1 && map[r+1][c] == currentChar) dfs(r+1, c);
        if (c > 0 && map[r][c-1] == currentChar) dfs(r, c-1);
        if (c < N - 1 && map[r][c+1] == currentChar) dfs(r, c+1);
    }
}
