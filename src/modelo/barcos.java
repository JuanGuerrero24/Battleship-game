package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Point;

public class barcos {

    public static List<Barco> flotaMaquina = new ArrayList<>();
    public static List<Barco> flotaJugador = new ArrayList<>();

    public static void generarBarcosMaquina() {
        flotaMaquina.clear();
        Tablero.generadordebarcosramdom(5);
        Tablero.generadordebarcosramdom(4);
        Tablero.generadordebarcosramdom(3);
        Tablero.generadordebarcosramdom(3);
        Tablero.generadordebarcosramdom(2);
    }

    public static void mostrarFlotaDefensora(JButton[][] botones) {
        for (Barco b : flotaJugador) {
            for (Point p : b.getPuntos()) {
                int f = (int)p.x;
                int c = (int)p.y;
                botones[f][c].setBackground(Color.DARK_GRAY);
                botones[f][c].setOpaque(true);
                botones[f][c].setContentAreaFilled(true);
            }
        }
    }

    public static void registrarBarcoJugador(Barco b) {
        flotaJugador.add(b);
    }
}