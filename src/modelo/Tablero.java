package modelo;

import java.util.Random;

public class Tablero {

    public static int[][] tablero = new int[10][10];   // IA
    public static int[][] tableroj = new int[10][10];  // Jugador

    public static int impactosJugador = 0;
    public static int impactosMaquina = 0;

    static Random rand = new Random();

    // Inicializa ambos tableros
    public static void inicializar() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = 0;
                tableroj[i][j] = 0;
            }
        }
        impactosJugador = 0;
        impactosMaquina = 0;
    }

    // Genera barcos aleatorios para la IA
    public static void generadordebarcosramdom(int tamaño) {
        boolean colocado = false;

        while (!colocado) {
            int f = rand.nextInt(10);
            int c = rand.nextInt(10);
            boolean horizontal = rand.nextBoolean();

            if (puedeColocar(f, c, tamaño, horizontal)) {
                Barco barco = new Barco();

                for (int i = 0; i < tamaño; i++) {
                    int nf = f + (horizontal ? 0 : i);
                    int nc = c + (horizontal ? i : 0);

                    tablero[nf][nc] = 1;
                    barco.agregarPunto(nf, nc);
                }

                barcos.flotaMaquina.add(barco);
                colocado = true;
            }
        }
    }

    // Verifica si se puede colocar barco
    private static boolean puedeColocar(int f, int c, int tamaño, boolean horizontal) {
        for (int i = 0; i < tamaño; i++) {
            int nf = f + (horizontal ? 0 : i);
            int nc = c + (horizontal ? i : 0);

            if (nf >= 10 || nc >= 10) return false;
            if (tablero[nf][nc] != 0) return false;
        }
        return true;
    }
}