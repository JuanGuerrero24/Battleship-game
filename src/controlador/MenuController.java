package controlador;

import vista.MenuPrincipal;
import vista.VentanaColocacion;

public class MenuController {
    private MenuPrincipal vista;

    public MenuController(MenuPrincipal vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnJugar().addActionListener(e -> abrirColocacion());
        vista.getBtnSalir().addActionListener(e -> System.exit(0));
    }

    private void abrirColocacion() {
        new VentanaColocacion().setVisible(true);
        vista.dispose();
    }
}