package modelo;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Disparar {
    static Random rand = new Random();
    
    static boolean modoAtaque = false;
    static boolean direccionDefinida = false;
    static int ultimaFila, ultimaColumna, direccion;

    public static void dispararMaquina(JButton[][] botonesJugador) {
        boolean disparoRealizado = false;
        int f = 0, c = 0;
        int intentosEmergencia = 0;

        while (!disparoRealizado && intentosEmergencia < 100) {
            intentosEmergencia++;

            if (!modoAtaque) {
                f = rand.nextInt(10);
                c = rand.nextInt(10);
            } else {
                f = ultimaFila;
                c = ultimaColumna;
                if (!direccionDefinida) {
                    direccion = rand.nextInt(4);
                }
                switch (direccion) {
                    case 0: f--; break;
                    case 1: f++; break;
                    case 2: c--; break;
                    case 3: c++; break;
                }
            }

            if (f >= 0 && f < 10 && c >= 0 && c < 10 && Tablero.tableroj[f][c] < 3) {
                procesarDisparoVisual(f, c, botonesJugador);
                disparoRealizado = true;
                break;
            } else {
                direccionDefinida = false;
                if (intentosEmergencia > 20) modoAtaque = false;
            }
        }
    }

    private static void procesarDisparoVisual(int f, int c, JButton[][] botones) {
        boolean fueImpacto = procesarDisparo(f, c, botones);
        
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        
        if (fueImpacto && Tablero.impactosMaquina < 17) {
            try { Thread.sleep(300); } catch (InterruptedException e) {}
            dispararMaquina(botones);
        }
    }

    private static boolean procesarDisparo(int f, int c, JButton[][] botones) {
        if (Tablero.tableroj[f][c] == 1) {
            Tablero.tableroj[f][c] = 4;
            Tablero.impactosMaquina++;
            
            SwingUtilities.invokeLater(() -> {
                ImageIcon explosion = escalar("/iconos/boton.png", botones[f][c]);
                botones[f][c].setIcon(explosion);
                botones[f][c].setDisabledIcon(explosion);
                botones[f][c].setEnabled(false);
                botones[f][c].revalidate();
                botones[f][c].repaint();
            });
            
            modoAtaque = true;
            direccionDefinida = true;
            ultimaFila = f;
            ultimaColumna = c;

            for (Barco b : barcos.flotaJugador) {
                if (b.contienePunto(f, c)) {
                    b.registrarImpacto();
                    if (b.estaHundido()) {
                        JOptionPane.showMessageDialog(null, "¡La IA ha HUNDIDO un barco! Tu turno.");
                        return false; // **SIN TURNO EXTRA**
                    }
                    break;
                }
            }
            return true; // Turno extra solo en acierto normal
        } else {
            Tablero.tableroj[f][c] = 3;
            SwingUtilities.invokeLater(() -> {
                ImageIcon equis = escalar("/iconos/equis.png", botones[f][c]);
                botones[f][c].setIcon(equis);
                botones[f][c].setDisabledIcon(equis);
                botones[f][c].setEnabled(false);
                botones[f][c].revalidate();
                botones[f][c].repaint();
            });
            direccionDefinida = false;
            return false;
        }
    }

    private static ImageIcon escalar(String ruta, JButton boton) {
        try {
            ImageIcon icon = new ImageIcon(Disparar.class.getResource(ruta));
            Image img = icon.getImage().getScaledInstance(
                boton.getWidth()-4, boton.getHeight()-4, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.out.println("Error imagen: " + ruta);
            return null;
        }
    }
}