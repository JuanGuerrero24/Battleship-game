package vista;

import javax.swing.*;
import java.awt.*;
import controlador.PartidaController;
import modelo.*;

public class VentanaPartida extends JFrame {

    private JButton[][] botonesMaquina = new JButton[10][10];
    private JButton[][] botonesJugador = new JButton[10][10];
    private JLabel lblEstado = new JLabel("Cargando...", SwingConstants.CENTER);

    private PartidaController controller;

    // Fondo
    class PanelFondo extends JPanel {
        private Image img;
        public PanelFondo() {
            try {
                img = new ImageIcon(getClass().getResource("/iconos/fondo2.png")).getImage();
            } catch(Exception e){}
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(img != null)
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public VentanaPartida() {

        setTitle("Batalla Naval - En Combate");
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        barcos.generarBarcosMaquina();

        PanelFondo panelPrincipal = new PanelFondo();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel centro = new JPanel(new GridLayout(1, 2, 40, 0));
        centro.setOpaque(false);

        centro.add(crearPanel(botonesJugador, "TU FLOTA", false));
        centro.add(crearPanel(botonesMaquina, "RADAR ENEMIGO", true));

        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 22));

        panelPrincipal.add(lblEstado, BorderLayout.NORTH);
        panelPrincipal.add(centro, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        barcos.mostrarFlotaDefensora(botonesJugador);

        // 🔥 Controller
        controller = new PartidaController(this);
    }

    private JPanel crearPanel(JButton[][] mat, String titulo, boolean enemigo) {

        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);

        JLabel l = new JLabel(titulo, SwingConstants.CENTER);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 16));

        p.add(l, BorderLayout.NORTH);

        JPanel g = new JPanel(new GridLayout(10, 10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                mat[i][j] = new JButton();
                mat[i][j].setBackground(new Color(20, 40, 60));
                mat[i][j].setPreferredSize(new Dimension(40, 40));

                if (enemigo) {
                    final int f = i, c = j;
                    mat[i][j].addActionListener(e -> controller.disparar(f, c));
                }

                g.add(mat[i][j]);
            }
        }

        p.add(g, BorderLayout.CENTER);
        return p;
    }

    // ================== UTILIDADES ==================

    public void bloquearRadar(boolean estado) {
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
                if(botonesMaquina[i][j].getIcon() == null)
                    botonesMaquina[i][j].setEnabled(estado);
    }

    public ImageIcon escalar(String ruta, JButton boton) {
        try {
            Image img = new ImageIcon(getClass().getResource(ruta)).getImage();
            return new ImageIcon(img.getScaledInstance(
                boton.getWidth()-4,
                boton.getHeight()-4,
                Image.SCALE_SMOOTH));
        } catch(Exception e) {
            return null;
        }
    }

    // ================== GETTERS ==================

    public JButton[][] getBotonesMaquina() { return botonesMaquina; }
    public JButton[][] getBotonesJugador() { return botonesJugador; }
    public JLabel getLblEstado() { return lblEstado; }
}