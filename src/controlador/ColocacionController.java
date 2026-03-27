package controlador;

import vista.VentanaColocacion;
import vista.VentanaPartida;

public class ColocacionController {
    private VentanaColocacion vista;

    public ColocacionController(VentanaColocacion vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnListo().addActionListener(e -> abrirPartida());
    }

    private void abrirPartida() {
        new VentanaPartida().setVisible(true);
        vista.dispose();
    }
}