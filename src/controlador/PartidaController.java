package controlador;

import modelo.*;
import vista.VentanaPartida;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PartidaController {
    private VentanaPartida vista;
    private boolean miTurno = true;

    public PartidaController(VentanaPartida vista) {
        this.vista = vista;
        inicializarEventos();
        barcos.mostrarFlotaDefensora(vista.getBotonesJugador());
    }

    private void inicializarEventos() {
        vista.getLblEstado().setText("¡TU TURNO! Ataca al enemigo");
    }

    public void disparar(int f, int c) {
        if (!vista.getBotonesMaquina()[f][c].isEnabled() || !miTurno) return;

        if (Tablero.tablero[f][c] == 1) {
            Tablero.tablero[f][c] = 4;
            Tablero.impactosJugador++;
            vista.getBotonesMaquina()[f][c].setIcon(vista.escalar("/iconos/boton.png", vista.getBotonesMaquina()[f][c]));
            
            boolean barcoHundido = false;
            for (Barco b : barcos.flotaMaquina) {
                if (b.contienePunto(f, c)) {
                    b.registrarImpacto();
                    if (b.estaHundido()) {
                        javax.swing.JOptionPane.showMessageDialog(vista, "¡BARCO ENEMIGO HUNDIDO! Turno de la IA.");
                        barcoHundido = true;
                    }
                    break;
                }
            }
            
            vista.getBotonesMaquina()[f][c].setEnabled(false);
            
            if (barcoHundido) {
                cambiarTurnoIA();
            } else {
                vista.getLblEstado().setText("¡ACIERTO! Turno extra.");
                verificarVictoria();
            }
        } else {
            vista.getBotonesMaquina()[f][c].setIcon(vista.escalar("/iconos/equis.png", vista.getBotonesMaquina()[f][c]));
            vista.getLblEstado().setText("¡AGUA! Turno de la IA.");
            vista.getBotonesMaquina()[f][c].setEnabled(false);
            cambiarTurnoIA();
        }
    }

    private void cambiarTurnoIA() {
        miTurno = false;
        vista.bloquearRadar(false);
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Disparar.dispararMaquina(vista.getBotonesJugador());
                vista.getLblEstado().setText("Tu turno.");
                vista.bloquearRadar(true);
                miTurno = true;
                verificarVictoria();
                ((Timer)e.getSource()).stop();
            }
        });
        t.setRepeats(false);
        t.start();
    }

    private void verificarVictoria() {
        if (Tablero.impactosJugador == 17) {
            javax.swing.JOptionPane.showMessageDialog(vista, "¡VICTORIA!");
            vista.dispose();
        } else if (Tablero.impactosMaquina == 17) {
            javax.swing.JOptionPane.showMessageDialog(vista, "HAS SIDO DERROTADO");
            vista.dispose();
        }
    }
}