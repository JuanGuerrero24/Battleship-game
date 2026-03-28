package controlador;

import modelo.*;
import vista.VentanaPartida;

public class PartidaController {

    private VentanaPartida vista;

    // Variable compartida de turnos
    private volatile boolean miTurno = true;

    public PartidaController(VentanaPartida vista) {
        this.vista = vista;
        inicializarEventos();
        iniciarHiloIA(); 
    }

    private void inicializarEventos() {
        vista.getLblEstado().setText("¡TU TURNO!");
    }

    // ================= JUGADOR =================
    public void disparar(int f, int c) {

        if (!miTurno) return;

        if (!vista.getBotonesMaquina()[f][c].isEnabled()) return;

        if (Tablero.tablero[f][c] == 1) {

            Tablero.tablero[f][c] = 4;
            Tablero.impactosJugador++;

            vista.getBotonesMaquina()[f][c].setIcon(
                vista.escalar("/iconos/boton.png", vista.getBotonesMaquina()[f][c])
            );

            vista.getBotonesMaquina()[f][c].setEnabled(false);

            vista.getLblEstado().setText("¡ACIERTO! Dispara otra vez");

        } else {

            vista.getBotonesMaquina()[f][c].setIcon(
                vista.escalar("/iconos/equis.png", vista.getBotonesMaquina()[f][c])
            );

            vista.getBotonesMaquina()[f][c].setEnabled(false);

            vista.getLblEstado().setText("Turno IA...");
            miTurno = false; // CAMBIO DE TURNO
        }

        verificarVictoria();
    }

    // ================= IA =================
    private void iniciarHiloIA() {

        Thread hiloIA = new Thread(() -> {

            while (true) {

                if (!miTurno) {

                    try {
                        Thread.sleep(1000); // pausa IA
                    } catch (InterruptedException e) {}

                    Disparar.dispararMaquina(vista.getBotonesJugador());

                    // Volver al hilo de Swing
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.getLblEstado().setText("Tu turno");
                        vista.bloquearRadar(true);
                    });

                    miTurno = true;

                    verificarVictoria();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        });

        hiloIA.start();
    }

    // ================= VICTORIA =================
    private void verificarVictoria() {
        if (Tablero.impactosJugador == 17) {
            javax.swing.JOptionPane.showMessageDialog(vista, "¡VICTORIA!");
            vista.dispose();
        } else if (Tablero.impactosMaquina == 17) {
            javax.swing.JOptionPane.showMessageDialog(vista, "DERROTA");
            vista.dispose();
        }
    }
}