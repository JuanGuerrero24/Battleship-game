package vista;

import controlador.MenuController;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    private JButton btnJugar, btnSalir;

    public MenuPrincipal() {
        inicializarVentana();
        inicializarComponentes();
        new MenuController(this); // Conecta controlador
    }

    private void inicializarVentana() {
        setTitle("Batalla Naval - Menú");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel panelFondo = crearPanelFondo();
        
        
        inicializarBotones();
        
        GridBagConstraints gbc = new GridBagConstraints();

        
        // ESPACIADOR 
        gbc.gridy = 1;
        gbc.weighty = 1.0; // Se come todo el espacio del centro
        panelFondo.add(Box.createGlue(), gbc);
        
        // 3. BOTÓN JUGAR 
        gbc.gridy = 2;
        gbc.weighty = 0; 
        gbc.insets = new Insets(0, 0, 0, 0); // Separación entre Jugar y Salir
        gbc.anchor = GridBagConstraints.SOUTH;
        panelFondo.add(btnJugar, gbc);
        
        // 4. BOTÓN SALIR 
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0); // Margen final con el borde de la ventana
        gbc.anchor = GridBagConstraints.SOUTH;
        panelFondo.add(btnSalir, gbc);

        add(panelFondo);
    }

    private JPanel crearPanelFondo() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    //Fondo
                    ImageIcon img = new ImageIcon(getClass().getResource("/iconos/fondo1.png"));
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch(Exception e) {
                    System.out.println("Error al cargar imagen de fondo");
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    private void inicializarBotones() {
        ImageIcon iconoJugar = escalarImagen("/iconos/jugar.png", 100, 100);
        ImageIcon iconoSalir = escalarImagen("/iconos/salir.png", 100, 100);
        
        btnJugar = new JButton(iconoJugar);
        btnSalir = new JButton(iconoSalir);
        
        // Estilo invisible para los botones
        btnJugar.setContentAreaFilled(false); 
        btnJugar.setBorderPainted(false); 
        btnJugar.setFocusPainted(false);
        
        btnSalir.setContentAreaFilled(false); 
        btnSalir.setBorderPainted(false); 
        btnSalir.setFocusPainted(false);
        
        btnJugar.setPreferredSize(new Dimension(120, 120));
        btnSalir.setPreferredSize(new Dimension(120, 120));
    }

    private ImageIcon escalarImagen(String ruta, int ancho, int alto) {
        try {
            ImageIcon original = new ImageIcon(getClass().getResource(ruta));
            Image img = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
    }

    // *** GETTERS para el Controlador ***
    public JButton getBtnJugar() { return btnJugar; }
    public JButton getBtnSalir() { return btnSalir; }
}