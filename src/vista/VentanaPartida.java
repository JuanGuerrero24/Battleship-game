package vista;

import javax.swing.*;
import java.awt.*;
import modelo.*;

public class VentanaPartida extends JFrame {
    private JButton[][] botonesMaquina = new JButton[10][10];
    private JButton[][] botonesJugador = new JButton[10][10];
    private JLabel lblEstado = new JLabel("¡TU TURNO! Ataca al enemigo", SwingConstants.CENTER);
    private boolean miTurno = true;

    // Panel para dibujar el fondo2.png
    class PanelFondo extends JPanel {
        private Image img;
        public PanelFondo() {
            try { img = new ImageIcon(getClass().getResource("/iconos/fondo2.png")).getImage(); } catch(Exception e){}
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
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
        centro.add(crearPanel(botonesJugador, "TU FLOTA (Defensa)", false));
        centro.add(crearPanel(botonesMaquina, "RADAR ENEMIGO (Ataque)", true));

        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(new Font("Arial", Font.BOLD, 22));

        panelPrincipal.add(lblEstado, BorderLayout.NORTH);
        panelPrincipal.add(centro, BorderLayout.CENTER);
        setContentPane(panelPrincipal);

        barcos.mostrarFlotaDefensora(botonesJugador);
    }

    private JPanel crearPanel(JButton[][] mat, String tit, boolean enemigo) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel l = new JLabel(tit, SwingConstants.CENTER);
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
                    mat[i][j].addActionListener(e -> disparar(f, c));
                }
                g.add(mat[i][j]);
            }
        }
        p.add(g, BorderLayout.CENTER);
        return p;
    }

    private void disparar(int f, int c) {
        if (!miTurno || !botonesMaquina[f][c].isEnabled()) return;

        if (Tablero.tablero[f][c] == 1) {
            Tablero.tablero[f][c] = 4;
            Tablero.impactosJugador++;
            botonesMaquina[f][c].setIcon(escalar("/iconos/boton.png", botonesMaquina[f][c]));

            boolean barcoHundido = false;
            for (Barco b : barcos.flotaMaquina) {
                if (b.contienePunto(f, c)) {
                    b.registrarImpacto();
                    if (b.estaHundido()) {
                        JOptionPane.showMessageDialog(this, "¡BARCO ENEMIGO HUNDIDO! Turno de la IA.");
                        barcoHundido = true;
                    }
                    break;
                }
            }

            botonesMaquina[f][c].setEnabled(false);

            if (barcoHundido) {
                lblEstado.setText("¡BARCO HUNDIDO! Turno IA.");
                miTurno = false;
                Timer t = new Timer(1500, e -> {
                    Disparar.dispararMaquina(botonesJugador);
                    lblEstado.setText("Tu turno.");
                    bloquearRadar(true);
                    miTurno = true;
                    verificarVictoria();
                });
                t.setRepeats(false); t.start();
            } else {
                lblEstado.setText("¡ACIERTO! Turno extra.");
                verificarVictoria();
            }

        } else {
            botonesMaquina[f][c].setIcon(escalar("/iconos/equis.png", botonesMaquina[f][c]));
            lblEstado.setText("¡AGUA! Turno de la IA.");
            botonesMaquina[f][c].setEnabled(false);
            miTurno = false;
            bloquearRadar(false);

            Timer t = new Timer(1000, e -> {
                Disparar.dispararMaquina(botonesJugador);
                lblEstado.setText("Tu turno.");
                bloquearRadar(true);
                miTurno = true;
                verificarVictoria();
            });
            t.setRepeats(false); t.start();
        }
    }

    public void bloquearRadar(boolean b) {
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
                if(botonesMaquina[i][j].getIcon() == null)
                    botonesMaquina[i][j].setEnabled(b);
    }

    private void verificarVictoria() {
        if (Tablero.impactosJugador == 17) {
            JOptionPane.showMessageDialog(this, "¡VICTORIA!");
            dispose();
        } else if (Tablero.impactosMaquina == 17) {
            JOptionPane.showMessageDialog(this, "HAS SIDO DERROTADO");
            dispose();
        }
    }

    public ImageIcon escalar(String r, JButton b) {
        try {
            Image i = new ImageIcon(getClass().getResource(r)).getImage();
            return new ImageIcon(i.getScaledInstance(b.getWidth()-4, b.getHeight()-4, Image.SCALE_SMOOTH));
        } catch(Exception e) { return null; }
    }

    // =================== GETTERS para lógica externa ===================
    public JButton[][] getBotonesMaquina() { return botonesMaquina; }
    public JButton[][] getBotonesJugador() { return botonesJugador; }
    public JLabel getLblEstado() { return lblEstado; }
    public void setMiTurno(boolean turno) { this.miTurno = turno; }
}