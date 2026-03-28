package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelo.*;
import controlador.ColocacionController;

public class VentanaColocacion extends JFrame {

    private JButton[][] botones = new JButton[10][10];
    private JButton btnListo = new JButton("LISTO");
    private JButton btnRotar = new JButton("ROTAR");

    private int[] tamañosBarcos = {5, 4, 3, 3, 2}; 
    private int barcoActual = 0;
    private boolean horizontal = true;

    private Color colorFondoBoton = new Color(20, 40, 60);

    private ColocacionController controller;

    // ================= FONDO =================
    class PanelFondo extends JPanel {
        private Image img;
        public PanelFondo() {
            try { 
                img = new ImageIcon(getClass().getResource("/iconos/fondo2.png")).getImage(); 
            } catch(Exception e){
                System.out.println("No se encontró fondo");
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // ================= CONSTRUCTOR =================
    public VentanaColocacion() {

        setTitle("Configura tu Flota");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Tablero.inicializar();
        modelo.barcos.flotaJugador.clear();

        PanelFondo panelPrincipal = new PanelFondo();
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        // ================= GRID =================
        JPanel grid = new JPanel(new GridLayout(10, 10));
        grid.setOpaque(false);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                botones[i][j] = new JButton();
                botones[i][j].setBackground(colorFondoBoton);
                botones[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50)));

                int f = i, c = j;

                // CLICK colocar barco
                botones[i][j].addActionListener(e -> colocarBarco(f, c));

                // 👻 efecto fantasma
                botones[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) { mostrarFantasma(f, c, true); }
                    public void mouseExited(MouseEvent e) { mostrarFantasma(f, c, false); }
                });

                grid.add(botones[i][j]);
            }
        }

        // ================= PANEL CONTROLES =================
        JPanel panelControles = new JPanel();
        panelControles.setOpaque(false);
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setPreferredSize(new Dimension(150, 0));

        // BOTÓN ROTAR
        btnRotar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRotar.addActionListener(e -> {
            horizontal = !horizontal;
        });

        // BOTÓN LISTO
        btnListo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnListo.setEnabled(false);
        btnListo.setBackground(Color.GRAY);

        btnListo.addActionListener(e -> {
            controller.abrirPartida();
        });

        panelControles.add(Box.createVerticalGlue());
        panelControles.add(btnRotar);
        panelControles.add(Box.createVerticalStrut(20));
        panelControles.add(btnListo);
        panelControles.add(Box.createVerticalGlue());

        // ================= TITULO =================
        JLabel titulo = new JLabel("Coloca tus barcos", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(grid, BorderLayout.CENTER);
        panelPrincipal.add(panelControles, BorderLayout.EAST);

        setContentPane(panelPrincipal);

        // 🔥 IMPORTANTE: crear controller al final
        controller = new ColocacionController(this);
    }

    // ================= FANTASMA =================
    private void mostrarFantasma(int f, int c, boolean entrar) {

        if (barcoActual >= tamañosBarcos.length) return;

        int tam = tamañosBarcos[barcoActual];

        for (int i = 0; i < tam; i++) {
            int nf = f + (horizontal ? 0 : i);
            int nc = c + (horizontal ? i : 0);

            if (nf < 10 && nc < 10 && Tablero.tableroj[nf][nc] == 0) {
                botones[nf][nc].setBackground(
                    entrar ? new Color(0, 200, 255, 150) : colorFondoBoton
                );
            }
        }
    }

    // ================= COLOCAR BARCO =================
    private void colocarBarco(int f, int c) {

        if (barcoActual >= tamañosBarcos.length) return;

        int tamaño = tamañosBarcos[barcoActual];

        if (puedeColocar(f, c, tamaño)) {

            Barco b = new Barco();

            for (int i = 0; i < tamaño; i++) {

                int nf = f + (horizontal ? 0 : i);
                int nc = c + (horizontal ? i : 0);

                Tablero.tableroj[nf][nc] = 1;
                botones[nf][nc].setBackground(Color.GRAY);

                b.agregarPunto(nf, nc);
            }

            modelo.barcos.registrarBarcoJugador(b);
            barcoActual++;

            // ACTIVAR LISTO
            if (barcoActual == tamañosBarcos.length) {
                btnListo.setEnabled(true);
                btnListo.setBackground(Color.GREEN);
            }
        }
    }

    // ================= VALIDACIÓN =================
    private boolean puedeColocar(int f, int c, int tamaño) {
        for (int i = 0; i < tamaño; i++) {
            int nf = f + (horizontal ? 0 : i);
            int nc = c + (horizontal ? i : 0);

            if (nf >= 10 || nc >= 10 || Tablero.tableroj[nf][nc] != 0)
                return false;
        }
        return true;
    }

    // ================= GETTERS =================
    public JButton getBtnListo() { return btnListo; }
    public JButton[][] getBotones() { return botones; }
}