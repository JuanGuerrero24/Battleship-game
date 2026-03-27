package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelo.*;
import controlador.ColocacionController;

public class VentanaColocacion extends JFrame {

    private JButton[][] botones = new JButton[10][10];
    private JButton btnListo = new JButton("LISTO");
    private JButton btnRotar = new JButton("ROTAR (R)");

    private int[] tamañosBarcos = {5, 4, 3, 3, 2}; 
    private int barcoActual = 0;
    private boolean horizontal = true;
    private Color colorFondoBoton = new Color(20, 40, 60);

    class PanelFondo extends JPanel {
        private Image img;
        public PanelFondo() {
            try { 
                img = new ImageIcon(getClass().getResource("/iconos/fondo2.png")).getImage(); 
            } catch(Exception e){
                System.out.println("Error: No se encontró /iconos/fondo2.png");
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(img != null) g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public VentanaColocacion() {
        setTitle("Configura tu Flota");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Tablero.inicializar();
        modelo.barcos.flotaJugador.clear(); 

        PanelFondo panelPrincipal = new PanelFondo();
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        // --- PANEL CENTRAL (GRILLA) ---
        JPanel grid = new JPanel(new GridLayout(10, 10));
        grid.setOpaque(false);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(colorFondoBoton);
                botones[i][j].setContentAreaFilled(true);
                botones[i][j].setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));

                int f = i, c = j;
                
                // Acción de click
                botones[i][j].addActionListener(e -> colocarBarco(f, c));
                
                // Efecto Fantasma (Mouse Over)
                botones[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) { mostrarFantasma(f, c, true); }
                    @Override
                    public void mouseExited(MouseEvent e) { mostrarFantasma(f, c, false); }
                });

                grid.add(botones[i][j]);
            }
        }

        // --- PANEL LATERAL (CONTROLES) ---
        JPanel panelControles = new JPanel();
        panelControles.setOpaque(false);
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setPreferredSize(new Dimension(150, 0));

        btnRotar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRotar.addActionListener(e -> { horizontal = !horizontal; focusWindow(); });

        btnListo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnListo.setEnabled(false);

        panelControles.add(Box.createVerticalGlue());
        panelControles.add(btnRotar);
        panelControles.add(Box.createVerticalStrut(20));
        panelControles.add(btnListo);
        panelControles.add(Box.createVerticalGlue());

        // --- TITULO ---
        JLabel titulo = new JLabel("Coloca tus barcos", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(grid, BorderLayout.CENTER);
        panelPrincipal.add(panelControles, BorderLayout.EAST);

        setContentPane(panelPrincipal);

        // Tecla R para rotar
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_R){
                    horizontal = !horizontal;
                }
            }
        });

        setFocusable(true);
        focusWindow();

        new ColocacionController(this);
    }

    private void focusWindow() {
        this.requestFocusInWindow();
    }

    private void mostrarFantasma(int f, int c, boolean entrar) {
        if (barcoActual >= tamañosBarcos.length) return;
        int tam = tamañosBarcos[barcoActual];
        
        for (int i = 0; i < tam; i++) {
            int nf = f + (horizontal ? 0 : i);
            int nc = c + (horizontal ? i : 0);

            if (nf < 10 && nc < 10 && Tablero.tableroj[nf][nc] == 0) {
                if (entrar) {
                    botones[nf][nc].setBackground(new Color(0, 200, 255, 150)); // Color Fantasma
                } else {
                    botones[nf][nc].setBackground(colorFondoBoton);
                }
            }
        }
    }

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
                botones[nf][nc].setOpaque(true);
                b.agregarPunto(nf, nc);
            }

            modelo.barcos.registrarBarcoJugador(b);
            barcoActual++;

            if (barcoActual == tamañosBarcos.length) {
                btnListo.setEnabled(true);
                btnListo.setBackground(Color.GREEN);
            }
        }
        focusWindow();
    }

    private boolean puedeColocar(int f, int c, int tamaño) {
        for (int i = 0; i < tamaño; i++) {
            int nf = f + (horizontal ? 0 : i);
            int nc = c + (horizontal ? i : 0);
            if (nf >= 10 || nc >= 10 || Tablero.tableroj[nf][nc] != 0) return false;
        }
        return true;
    }

    public JButton getBtnListo() { return btnListo; }
    public JButton[][] getBotones() { return botones; }
}