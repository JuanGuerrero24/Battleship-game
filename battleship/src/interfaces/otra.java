/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import javax.swing.*;
import logica.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author danyb
 */
public class otra extends javax.swing.JFrame {
    
    boolean control2=true, control3=true,control4 =true,control5 = true;
    int fila_R=0;
    int columna_R=1;
    boolean rotar = true;
    int [] orientacion = new int [4];
    int [][] barco2 = new int [2][2];
    int [][] barco3 = new int [3][2];
    int [][] barco4 = new int [4][2];
    int [][] barco5 = new int [5][2];
    int [][] tablero = new int [10][10];
    JButton[][] tablero1 = new JButton[10][10];
    usuario user= new usuario();
    TableroE table = new TableroE ();
    boolean elegir_casilla = false;
    boolean elegir_barco = true;
    
    int tamanoBarcoSeleccionado = 0;
    ImageIcon imagenBarcoSeleccionado = null;
    JLabel preview = new JLabel();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(otra.class.getName());

    /**
     * Creates new form otra
     */
    public otra() {
        
        initComponents();
        inicializarTablero();
        agregarlisteners();
        this.setLocationRelativeTo(null);
        imagenes im2 = new imagenes (panel1,"fondo2");
        imagenes im = new imagenes (panelb2,"barco");
        imagenes im3 = new imagenes (panelb6,"barco");
        imagenes im4 = new imagenes (panelb4,"barco");
        imagenes im5 = new imagenes (panelb5,"barco");
        
        panel1.add(im2);  
        panel5.setOpaque(false);
        panel5.setBorder(null);
        panel5.setBackground(new Color (0,0,0,0));
        
        preview.setSize(100, 30);
        preview.setVisible(false);
        panel5.add(preview);
        panel5.setComponentZOrder(preview, 0);
        
        panelb2.setOpaque(false);
        panelb2.setBorder(null);
        panelb2.setBackground(new Color (0,0,0,0));
        panelb2.add(im);
        
        panelb6.setOpaque(false);
        panelb6.setBorder(null);
        panelb6.setBackground(new Color (0,0,0,0));
        panelb6.add(im3);
       
        panelb4.setOpaque(false);
        panelb4.setBorder(null);
        panelb4.setBackground(new Color (0,0,0,0));
        panelb4.add(im4);
        
        panelb5.setOpaque(false);
        panelb5.setBorder(null);
        panelb5.setBackground(new Color (0,0,0,0));
        panelb5.add(im5);
        //panelb2.add(im).repaint();
        
        InputMap imm = panel5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel5.getActionMap();

        imm.put(KeyStroke.getKeyStroke("R"), "rotar");

        am.put("rotar", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            rotarBarco();
            System.out.println("rotar esta en" +rotar);
    }
});
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    private void rotarBarco(){
        if (rotar == true){
            rotar = false;
            fila_R=1;
            columna_R=0;
            preview.setSize(30, 100);
                 
    ImageIcon iconoOriginal = new ImageIcon(
        getClass().getResource("/iconos/barcoR.png")
    );
    
    int ancho = tablero1[0][0].getWidth();
    int alto = tablero1[0][0].getHeight();

    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
        ancho+ (48 * ((tamanoBarcoSeleccionado + tamanoBarcoSeleccionado)*columna_R)),
        alto + (48 * ((tamanoBarcoSeleccionado + tamanoBarcoSeleccionado)*fila_R)),
        Image.SCALE_SMOOTH
    );

    imagenBarcoSeleccionado = new ImageIcon(imagenEscalada);

    preview.setIcon(imagenBarcoSeleccionado);
    preview.setVisible(true);
                   
        }
        else {
            rotar=true;
            fila_R=0;
            columna_R=1;
                 
    ImageIcon iconoOriginal = new ImageIcon(
        getClass().getResource("/iconos/barco.png")
    );
    
    int ancho = tablero1[0][0].getWidth();
    int alto = tablero1[0][0].getHeight();

    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
        ancho+ (48 * ((tamanoBarcoSeleccionado + tamanoBarcoSeleccionado)*columna_R)),
        alto + (48 * ((tamanoBarcoSeleccionado + tamanoBarcoSeleccionado)*fila_R)),
        Image.SCALE_SMOOTH
    );

    imagenBarcoSeleccionado = new ImageIcon(imagenEscalada);

    preview.setIcon(imagenBarcoSeleccionado);
    preview.setVisible(true);
    
        }
        
    }
   private void inicializarTablero() {
    try {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                String nombre = "b" + i + j;

                java.lang.reflect.Field field = this.getClass().getDeclaredField(nombre);
                field.setAccessible(true);

                tablero1[i][j] = (JButton) field.get(this);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   private void agregarlisteners() {

    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {

            int fila = i;
            int col = j;

            
            tablero1[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    colocarBarco(fila, col);
                }
            });
            tablero1[i][j].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseMoved(java.awt.event.MouseEvent evt) {
                    moverPreview(fila, col);
                }
            });
        }
    }
}
   
   private void colocarBarco(int fila, int col) {

    if (tamanoBarcoSeleccionado == 0) return;

    //validar que no se salga
    if (col + (tamanoBarcoSeleccionado*columna_R) > 10 | fila+ (tamanoBarcoSeleccionado*fila_R)> 10) {
        System.out.println("No cabe");
        return;
    }

    //validar que no se monte
    for (int i = 0; i < tamanoBarcoSeleccionado; i++) {
        if (tablero[fila+ (fila_R*i)][col + (columna_R*i)] == 1) {
            System.out.println("Espacio ocupado");
            return;
        }
    }

    // colocar barco
    if (rotar == true){orientacion [tamanoBarcoSeleccionado-2]=0;
            }
            else if (rotar == false){orientacion [tamanoBarcoSeleccionado-2]=1;
            }
    
    for (int i = 0; i < tamanoBarcoSeleccionado; i++) {

        tablero[fila + (fila_R*i)][col + (columna_R*i)] = 1;

        tablero1[fila + (fila_R*i)][col + (columna_R*i)].setBackground(java.awt.Color.GRAY);
        tablero1[fila + (fila_R*i)][col + (columna_R*i)].setEnabled(false);
        
        
        JButton boton =tablero1 [fila + (fila_R*i)][col + (columna_R*i)];
        
        boton.setOpaque(false);
        boton.setBorder(null);
        boton.setBackground(new Color (0,0,0,0));
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        
        
        System.out.println("rotar es   "+rotar);
        
        System.out.println(fila + (fila_R*i)+" "+ (col + (columna_R*i)));
        
             if (tamanoBarcoSeleccionado==2)   {
                barco2 [i][0] = fila + (fila_R*i); barco2 [i][1]=col + (columna_R*i);
                control2 =false;
             user.pintar_direccion(boton, 2, barco2, orientacion[0], i);}         
            
            if (tamanoBarcoSeleccionado==3) {
                
                barco3 [i][0] = fila + (fila_R*i); barco3 [i][1]=col + (columna_R*i);
                control3 =false;
                 user.pintar_direccion(boton, 3, barco3, orientacion[1], i);     
            }
            if (tamanoBarcoSeleccionado==4){
                barco4 [i][0] = fila + (fila_R*i); barco4 [i][1]=col + (columna_R*i);
                control4 =false;
                user.pintar_direccion(boton, 4, barco4, orientacion[2], i);  
}
            
           
             if (tamanoBarcoSeleccionado==5)   {
                barco5 [i][0] = fila + (fila_R*i); barco5 [i][1]=col + (columna_R*i);
                control5 =false;
                user.pintar_direccion(boton, 5, barco5, orientacion[3], i); 
                }   
                   
            
        
    }

    //  ocultar preview
    preview.setVisible(false);

    //  reset selección
    tamanoBarcoSeleccionado = 0;
    imagenBarcoSeleccionado = null;
    rotar=true;
         fila_R=0;
         columna_R=1;
}
   private void moverPreview(int fila, int col) {

    if (imagenBarcoSeleccionado == null) return;

    JButton btn = tablero1[fila][col];
    if (btn == null) return;

    // convertir coordenadas correctamente
    Point p = SwingUtilities.convertPoint(
        btn.getParent(),
        btn.getLocation(),
        panel5
    );

    preview.setIcon(imagenBarcoSeleccionado);

    preview.setBounds(
        p.x,
        p.y,
        btn.getWidth()+47 * ((tamanoBarcoSeleccionado+tamanoBarcoSeleccionado)*columna_R),
        btn.getHeight() +47 * ((tamanoBarcoSeleccionado+tamanoBarcoSeleccionado)*fila_R)
    );

    preview.setVisible(true);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        panel5 = new javax.swing.JPanel();
        b11 = new javax.swing.JButton();
        b01 = new javax.swing.JButton();
        b02 = new javax.swing.JButton();
        b03 = new javax.swing.JButton();
        b04 = new javax.swing.JButton();
        b05 = new javax.swing.JButton();
        b06 = new javax.swing.JButton();
        b07 = new javax.swing.JButton();
        b08 = new javax.swing.JButton();
        b09 = new javax.swing.JButton();
        b00 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b17 = new javax.swing.JButton();
        b18 = new javax.swing.JButton();
        b19 = new javax.swing.JButton();
        b20 = new javax.swing.JButton();
        b21 = new javax.swing.JButton();
        b22 = new javax.swing.JButton();
        b23 = new javax.swing.JButton();
        b24 = new javax.swing.JButton();
        b25 = new javax.swing.JButton();
        b26 = new javax.swing.JButton();
        b27 = new javax.swing.JButton();
        b28 = new javax.swing.JButton();
        b29 = new javax.swing.JButton();
        b30 = new javax.swing.JButton();
        b31 = new javax.swing.JButton();
        b32 = new javax.swing.JButton();
        b33 = new javax.swing.JButton();
        b34 = new javax.swing.JButton();
        b35 = new javax.swing.JButton();
        b36 = new javax.swing.JButton();
        b37 = new javax.swing.JButton();
        b38 = new javax.swing.JButton();
        b39 = new javax.swing.JButton();
        b40 = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        b42 = new javax.swing.JButton();
        b43 = new javax.swing.JButton();
        b44 = new javax.swing.JButton();
        b45 = new javax.swing.JButton();
        b46 = new javax.swing.JButton();
        b47 = new javax.swing.JButton();
        b48 = new javax.swing.JButton();
        b49 = new javax.swing.JButton();
        b50 = new javax.swing.JButton();
        b51 = new javax.swing.JButton();
        b52 = new javax.swing.JButton();
        b53 = new javax.swing.JButton();
        b54 = new javax.swing.JButton();
        b55 = new javax.swing.JButton();
        b56 = new javax.swing.JButton();
        b57 = new javax.swing.JButton();
        b58 = new javax.swing.JButton();
        b59 = new javax.swing.JButton();
        b60 = new javax.swing.JButton();
        b61 = new javax.swing.JButton();
        b62 = new javax.swing.JButton();
        b63 = new javax.swing.JButton();
        b64 = new javax.swing.JButton();
        b65 = new javax.swing.JButton();
        b66 = new javax.swing.JButton();
        b67 = new javax.swing.JButton();
        b68 = new javax.swing.JButton();
        b69 = new javax.swing.JButton();
        b70 = new javax.swing.JButton();
        b71 = new javax.swing.JButton();
        b73 = new javax.swing.JButton();
        b72 = new javax.swing.JButton();
        b74 = new javax.swing.JButton();
        b75 = new javax.swing.JButton();
        b76 = new javax.swing.JButton();
        b77 = new javax.swing.JButton();
        b79 = new javax.swing.JButton();
        b78 = new javax.swing.JButton();
        b80 = new javax.swing.JButton();
        b81 = new javax.swing.JButton();
        b82 = new javax.swing.JButton();
        b83 = new javax.swing.JButton();
        b84 = new javax.swing.JButton();
        b86 = new javax.swing.JButton();
        b85 = new javax.swing.JButton();
        b87 = new javax.swing.JButton();
        b88 = new javax.swing.JButton();
        b89 = new javax.swing.JButton();
        b90 = new javax.swing.JButton();
        b91 = new javax.swing.JButton();
        b92 = new javax.swing.JButton();
        b93 = new javax.swing.JButton();
        b94 = new javax.swing.JButton();
        b95 = new javax.swing.JButton();
        b96 = new javax.swing.JButton();
        b97 = new javax.swing.JButton();
        b98 = new javax.swing.JButton();
        b99 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelb2 = new javax.swing.JPanel();
        panelb4 = new javax.swing.JPanel();
        panelb5 = new javax.swing.JPanel();
        panelb6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11b11ActionPerformed(evt);
            }
        });

        b01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b01b01ActionPerformed(evt);
            }
        });

        b02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b02b02ActionPerformed(evt);
            }
        });

        b03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b03b03ActionPerformed(evt);
            }
        });

        b04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b04b04ActionPerformed(evt);
            }
        });

        b05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b05b05ActionPerformed(evt);
            }
        });

        b06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b06b06ActionPerformed(evt);
            }
        });

        b07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b07b07ActionPerformed(evt);
            }
        });

        b08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b08b08ActionPerformed(evt);
            }
        });

        b09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b09b09ActionPerformed(evt);
            }
        });

        b00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b00b00ActionPerformed(evt);
            }
        });

        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10b10ActionPerformed(evt);
            }
        });

        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12b12ActionPerformed(evt);
            }
        });

        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13b13ActionPerformed(evt);
            }
        });

        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14b14ActionPerformed(evt);
            }
        });

        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15b15ActionPerformed(evt);
            }
        });

        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16b16ActionPerformed(evt);
            }
        });

        b17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b17b17ActionPerformed(evt);
            }
        });

        b18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b18b18ActionPerformed(evt);
            }
        });

        b19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b19b19ActionPerformed(evt);
            }
        });

        b20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b20b20ActionPerformed(evt);
            }
        });

        b21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b21b21ActionPerformed(evt);
            }
        });

        b22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b22b22ActionPerformed(evt);
            }
        });

        b23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b23b23ActionPerformed(evt);
            }
        });

        b24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b24b24ActionPerformed(evt);
            }
        });

        b25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b25b25ActionPerformed(evt);
            }
        });

        b26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b26b26ActionPerformed(evt);
            }
        });

        b27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b27b27ActionPerformed(evt);
            }
        });

        b28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b28b28ActionPerformed(evt);
            }
        });

        b29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b29b29ActionPerformed(evt);
            }
        });

        b30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b30b30ActionPerformed(evt);
            }
        });

        b31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b31b31ActionPerformed(evt);
            }
        });

        b32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b32b32ActionPerformed(evt);
            }
        });

        b33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b33b33ActionPerformed(evt);
            }
        });

        b34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b34b34ActionPerformed(evt);
            }
        });

        b35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b35b35ActionPerformed(evt);
            }
        });

        b36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b36b36ActionPerformed(evt);
            }
        });

        b37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b37b37ActionPerformed(evt);
            }
        });

        b38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b38b38ActionPerformed(evt);
            }
        });

        b39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b39b39ActionPerformed(evt);
            }
        });

        b40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b40b40ActionPerformed(evt);
            }
        });

        b41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b41b41ActionPerformed(evt);
            }
        });

        b42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b42b42ActionPerformed(evt);
            }
        });

        b43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b43b43ActionPerformed(evt);
            }
        });

        b44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b44b44ActionPerformed(evt);
            }
        });

        b45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b45b45ActionPerformed(evt);
            }
        });

        b46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b46b46ActionPerformed(evt);
            }
        });

        b47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b47b47ActionPerformed(evt);
            }
        });

        b48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b48b48ActionPerformed(evt);
            }
        });

        b49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b49b49ActionPerformed(evt);
            }
        });

        b50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b50b50ActionPerformed(evt);
            }
        });

        b51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b51b51ActionPerformed(evt);
            }
        });

        b52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b52b52ActionPerformed(evt);
            }
        });

        b53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b53b53ActionPerformed(evt);
            }
        });

        b54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b54b54ActionPerformed(evt);
            }
        });

        b55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b55b55ActionPerformed(evt);
            }
        });

        b56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b56b56ActionPerformed(evt);
            }
        });

        b57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b57b57ActionPerformed(evt);
            }
        });

        b58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b58b58ActionPerformed(evt);
            }
        });

        b59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b59b59ActionPerformed(evt);
            }
        });

        b60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b60b60ActionPerformed(evt);
            }
        });

        b61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b61b61ActionPerformed(evt);
            }
        });

        b62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b62b62ActionPerformed(evt);
            }
        });

        b63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b63b63ActionPerformed(evt);
            }
        });

        b64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b64b64ActionPerformed(evt);
            }
        });

        b65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b65b65ActionPerformed(evt);
            }
        });

        b66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b66b66ActionPerformed(evt);
            }
        });

        b67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b67b67ActionPerformed(evt);
            }
        });

        b68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b68b68ActionPerformed(evt);
            }
        });

        b69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b69b69ActionPerformed(evt);
            }
        });

        b70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b70b70ActionPerformed(evt);
            }
        });

        b71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b71b71ActionPerformed(evt);
            }
        });

        b73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b73b72ActionPerformed(evt);
            }
        });

        b72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b72b73ActionPerformed(evt);
            }
        });

        b74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b74b74ActionPerformed(evt);
            }
        });

        b75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b75b75ActionPerformed(evt);
            }
        });

        b76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b76b76ActionPerformed(evt);
            }
        });

        b77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b77b77ActionPerformed(evt);
            }
        });

        b79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b79b78ActionPerformed(evt);
            }
        });

        b78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b78b79ActionPerformed(evt);
            }
        });

        b80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b80b80ActionPerformed(evt);
            }
        });

        b81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b81b81ActionPerformed(evt);
            }
        });

        b82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b82b82ActionPerformed(evt);
            }
        });

        b83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b83b83ActionPerformed(evt);
            }
        });

        b84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b84b84ActionPerformed(evt);
            }
        });

        b86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b86b86ActionPerformed(evt);
            }
        });

        b85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b85b85ActionPerformed(evt);
            }
        });

        b87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b87b87ActionPerformed(evt);
            }
        });

        b88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b88b88ActionPerformed(evt);
            }
        });

        b89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b89b89ActionPerformed(evt);
            }
        });

        b90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b90b90ActionPerformed(evt);
            }
        });

        b91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b91b91ActionPerformed(evt);
            }
        });

        b92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b92b92ActionPerformed(evt);
            }
        });

        b93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b93b93ActionPerformed(evt);
            }
        });

        b94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b94b94ActionPerformed(evt);
            }
        });

        b95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b95b95ActionPerformed(evt);
            }
        });

        b96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b96b96ActionPerformed(evt);
            }
        });

        b97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b97b97ActionPerformed(evt);
            }
        });

        b98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b98b98ActionPerformed(evt);
            }
        });

        b99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b99b99ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b00, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(b01, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b02, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b03, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b04, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b21, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(b22, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b24, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(b20, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b05, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b06, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b07, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b08, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b09, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b18, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b26, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b28, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b29, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(b30, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b31, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b32, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b33, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b34, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b35, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b36, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b37, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b38, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b39, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b40, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b41, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b42, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b43, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b50, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b51, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b52, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b53, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b44, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b45, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b46, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b47, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b48, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b49, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b54, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b55, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b56, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b57, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b58, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b59, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel5Layout.createSequentialGroup()
                                        .addComponent(b70, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b71, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b72, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(b73, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel5Layout.createSequentialGroup()
                                        .addComponent(b60, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b61, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b62, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b63, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(b64, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b65, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b66, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b67, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(b68, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b74, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b75, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b84, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b85, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b76, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b77, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b78, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel5Layout.createSequentialGroup()
                                                .addComponent(b86, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b87, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(b88, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(b80, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b81, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b82, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b83, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b79, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b69, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b89, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(b90, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b91, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b92, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b93, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b94, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b95, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b96, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b97, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b98, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b99, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(b09, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b08, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b07, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b06, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b05, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b04, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b03, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b02, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b01, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b00, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(b11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(b12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(b18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(b19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b22, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(b21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b30, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b31, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b32, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b33, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b35, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b36, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b37, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b38, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b39, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b40, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b41, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b42, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b43, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b44, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b45, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b46, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b47, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b48, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b49, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b50, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b51, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b52, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(b53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(b54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b63, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b62, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b61, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b60, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b64, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b65, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b66, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b67, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b68, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b69, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b70, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b71, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b73, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b72, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b74, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b75, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b76, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b77, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b79, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b78, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b80, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b81, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b82, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b83, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b84, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b86, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b85, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b87, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b88, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b89, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b91, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b90, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b92, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b93, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b94, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b95, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b96, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b97, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b98, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b99, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel1.setText("elija como poner cada uno de sus barcos ");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelb2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelb2Layout = new javax.swing.GroupLayout(panelb2);
        panelb2.setLayout(panelb2Layout);
        panelb2Layout.setHorizontalGroup(
            panelb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        panelb2Layout.setVerticalGroup(
            panelb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        panelb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelb4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelb4Layout = new javax.swing.GroupLayout(panelb4);
        panelb4.setLayout(panelb4Layout);
        panelb4Layout.setHorizontalGroup(
            panelb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );
        panelb4Layout.setVerticalGroup(
            panelb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        panelb5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelb5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelb5Layout = new javax.swing.GroupLayout(panelb5);
        panelb5.setLayout(panelb5Layout);
        panelb5Layout.setHorizontalGroup(
            panelb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        panelb5Layout.setVerticalGroup(
            panelb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        panelb6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelb6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelb6Layout = new javax.swing.GroupLayout(panelb6);
        panelb6.setLayout(panelb6Layout);
        panelb6Layout.setHorizontalGroup(
            panelb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        panelb6Layout.setVerticalGroup(
            panelb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panelb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(panelb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(panelb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
   
    
    private void b11b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11b11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b11b11ActionPerformed

    private void b01b01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b01b01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b01b01ActionPerformed

    private void b02b02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b02b02ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b02b02ActionPerformed

    private void b03b03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b03b03ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b03b03ActionPerformed

    private void b04b04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b04b04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b04b04ActionPerformed

    private void b05b05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b05b05ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b05b05ActionPerformed

    private void b06b06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b06b06ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b06b06ActionPerformed

    private void b07b07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b07b07ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b07b07ActionPerformed

    private void b08b08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b08b08ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b08b08ActionPerformed

    private void b09b09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b09b09ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b09b09ActionPerformed

    private void b00b00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b00b00ActionPerformed
        this.getName();
    }//GEN-LAST:event_b00b00ActionPerformed

    private void b10b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10b10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b10b10ActionPerformed

    private void b12b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12b12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b12b12ActionPerformed

    private void b13b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13b13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b13b13ActionPerformed

    private void b14b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14b14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b14b14ActionPerformed

    private void b15b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15b15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b15b15ActionPerformed

    private void b16b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16b16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b16b16ActionPerformed

    private void b17b17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b17b17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b17b17ActionPerformed

    private void b18b18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b18b18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b18b18ActionPerformed

    private void b19b19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b19b19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b19b19ActionPerformed

    private void b20b20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b20b20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b20b20ActionPerformed

    private void b21b21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b21b21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b21b21ActionPerformed

    private void b22b22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b22b22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b22b22ActionPerformed

    private void b23b23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b23b23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b23b23ActionPerformed

    private void b24b24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b24b24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b24b24ActionPerformed

    private void b25b25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b25b25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b25b25ActionPerformed

    private void b26b26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b26b26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b26b26ActionPerformed

    private void b27b27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b27b27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b27b27ActionPerformed

    private void b28b28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b28b28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b28b28ActionPerformed

    private void b29b29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b29b29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b29b29ActionPerformed

    private void b30b30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b30b30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b30b30ActionPerformed

    private void b31b31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b31b31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b31b31ActionPerformed

    private void b32b32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b32b32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b32b32ActionPerformed

    private void b33b33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b33b33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b33b33ActionPerformed

    private void b34b34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b34b34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b34b34ActionPerformed

    private void b35b35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b35b35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b35b35ActionPerformed

    private void b36b36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b36b36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b36b36ActionPerformed

    private void b37b37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b37b37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b37b37ActionPerformed

    private void b38b38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b38b38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b38b38ActionPerformed

    private void b39b39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b39b39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b39b39ActionPerformed

    private void b40b40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b40b40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b40b40ActionPerformed

    private void b41b41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b41b41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b41b41ActionPerformed

    private void b42b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42b42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b42b42ActionPerformed

    private void b43b43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b43b43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b43b43ActionPerformed

    private void b44b44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b44b44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b44b44ActionPerformed

    private void b45b45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b45b45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b45b45ActionPerformed

    private void b46b46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b46b46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b46b46ActionPerformed

    private void b47b47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b47b47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b47b47ActionPerformed

    private void b48b48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b48b48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b48b48ActionPerformed

    private void b49b49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b49b49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b49b49ActionPerformed

    private void b50b50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b50b50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b50b50ActionPerformed

    private void b51b51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b51b51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b51b51ActionPerformed

    private void b52b52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b52b52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b52b52ActionPerformed

    private void b53b53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b53b53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b53b53ActionPerformed

    private void b54b54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b54b54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b54b54ActionPerformed

    private void b55b55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b55b55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b55b55ActionPerformed

    private void b56b56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b56b56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b56b56ActionPerformed

    private void b57b57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b57b57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b57b57ActionPerformed

    private void b58b58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b58b58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b58b58ActionPerformed

    private void b59b59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b59b59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b59b59ActionPerformed

    private void b60b60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b60b60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b60b60ActionPerformed

    private void b61b61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b61b61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b61b61ActionPerformed

    private void b62b62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b62b62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b62b62ActionPerformed

    private void b63b63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b63b63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b63b63ActionPerformed

    private void b64b64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b64b64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b64b64ActionPerformed

    private void b65b65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b65b65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b65b65ActionPerformed

    private void b66b66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b66b66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b66b66ActionPerformed

    private void b67b67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b67b67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b67b67ActionPerformed

    private void b68b68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b68b68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b68b68ActionPerformed

    private void b69b69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b69b69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b69b69ActionPerformed

    private void b70b70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b70b70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b70b70ActionPerformed

    private void b71b71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b71b71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b71b71ActionPerformed

    private void b73b72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b73b72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b73b72ActionPerformed

    private void b72b73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b72b73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b72b73ActionPerformed

    private void b74b74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b74b74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b74b74ActionPerformed

    private void b75b75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b75b75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b75b75ActionPerformed

    private void b76b76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b76b76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b76b76ActionPerformed

    private void b77b77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b77b77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b77b77ActionPerformed

    private void b79b78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b79b78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b79b78ActionPerformed

    private void b78b79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b78b79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b78b79ActionPerformed

    private void b80b80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b80b80ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b80b80ActionPerformed

    private void b81b81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b81b81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b81b81ActionPerformed

    private void b82b82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b82b82ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b82b82ActionPerformed

    private void b83b83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b83b83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b83b83ActionPerformed

    private void b84b84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b84b84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b84b84ActionPerformed

    private void b86b86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b86b86ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b86b86ActionPerformed

    private void b85b85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b85b85ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b85b85ActionPerformed

    private void b87b87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b87b87ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b87b87ActionPerformed

    private void b88b88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b88b88ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b88b88ActionPerformed

    private void b89b89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b89b89ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b89b89ActionPerformed

    private void b90b90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b90b90ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b90b90ActionPerformed

    private void b91b91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b91b91ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b91b91ActionPerformed

    private void b92b92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b92b92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b92b92ActionPerformed

    private void b93b93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b93b93ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b93b93ActionPerformed

    private void b94b94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b94b94ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b94b94ActionPerformed

    private void b95b95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b95b95ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b95b95ActionPerformed

    private void b96b96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b96b96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b96b96ActionPerformed

    private void b97b97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b97b97ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b97b97ActionPerformed

    private void b98b98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b98b98ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b98b98ActionPerformed

    private void b99b99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b99b99ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b99b99ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int [][] mbarco2 = new int [2][2];
        int [][] mbarco3 = new int [3][2];
        int [][] mbarco4 = new int [4][2];
        int [][] mbarco5 = new int [5][2];

        mbarco2=table.generadordebarcosramdom(2);
        mbarco3=table.generadordebarcosramdom(3);
        mbarco4=table.generadordebarcosramdom(4);
        mbarco5=table.generadordebarcosramdom(5);
        table.ColocarbarcoPersona(barco2, barco3, barco4, barco5);

        partida p = new partida (table.gettablero(), mbarco2, mbarco3, mbarco4 ,mbarco5,table.gettableroj(),barco2, barco3, barco4 ,barco5, orientacion);
        dispose ();
        p.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void panelb2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelb2MouseClicked
      if (!control2){return;}
        tamanoBarcoSeleccionado =2;
      rotar=true;
       fila_R=0;
         columna_R=1;

    ImageIcon iconoOriginal = new ImageIcon(
        getClass().getResource("/iconos/barco.png")
    );

    int ancho = tablero1[0][0].getWidth();
    int alto = tablero1[0][0].getHeight();

    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
        ancho+48 * tamanoBarcoSeleccionado + tamanoBarcoSeleccionado,
        alto,
        Image.SCALE_SMOOTH
    );

    imagenBarcoSeleccionado = new ImageIcon(imagenEscalada);

    preview.setIcon(imagenBarcoSeleccionado);
    preview.setVisible(true);
    }//GEN-LAST:event_panelb2MouseClicked

    private void panelb4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelb4MouseClicked
        if (!control4){return;}
        tamanoBarcoSeleccionado =4;
        rotar=true;
         fila_R=0;
         columna_R=1;

    ImageIcon iconoOriginal = new ImageIcon(
        getClass().getResource("/iconos/barco.png")
    );

    int ancho = tablero1[0][0].getWidth();
    int alto = tablero1[0][0].getHeight();

    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
        ancho+48 * tamanoBarcoSeleccionado + tamanoBarcoSeleccionado,
        alto,
        Image.SCALE_SMOOTH
    );

    imagenBarcoSeleccionado = new ImageIcon(imagenEscalada);

    preview.setIcon(imagenBarcoSeleccionado);
    preview.setVisible(true);
                            
    }//GEN-LAST:event_panelb4MouseClicked

    private void panelb5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelb5MouseClicked
        if (!control5){return;} 
        tamanoBarcoSeleccionado =5;
         rotar=true;
          fila_R=0;
         columna_R=1;
         
    ImageIcon iconoOriginal = new ImageIcon(
        getClass().getResource("/iconos/barco.png")
    );
    
    int ancho = tablero1[0][0].getWidth();
    int alto = tablero1[0][0].getHeight();

    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
        ancho+ 48 * tamanoBarcoSeleccionado + tamanoBarcoSeleccionado,
        alto,
        Image.SCALE_SMOOTH
    );

    imagenBarcoSeleccionado = new ImageIcon(imagenEscalada);

    preview.setIcon(imagenBarcoSeleccionado);
    preview.setVisible(true);
    }//GEN-LAST:event_panelb5MouseClicked

    private void panelb6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelb6MouseClicked
        if (!control3){return;} 
        tamanoBarcoSeleccionado =3;
         rotar=true;
         fila_R=0;
         columna_R=1;

    ImageIcon iconoOriginal = new ImageIcon(
        getClass().getResource("/iconos/barco.png")
    );

    int ancho = tablero1[0][0].getWidth();
    int alto = tablero1[0][0].getHeight();

    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
        ancho+48 * tamanoBarcoSeleccionado + tamanoBarcoSeleccionado,
        alto,
        Image.SCALE_SMOOTH
    );

    imagenBarcoSeleccionado = new ImageIcon(imagenEscalada);

    preview.setIcon(imagenBarcoSeleccionado);
    preview.setVisible(true);
    }//GEN-LAST:event_panelb6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b00;
    private javax.swing.JButton b01;
    private javax.swing.JButton b02;
    private javax.swing.JButton b03;
    private javax.swing.JButton b04;
    private javax.swing.JButton b05;
    private javax.swing.JButton b06;
    private javax.swing.JButton b07;
    private javax.swing.JButton b08;
    private javax.swing.JButton b09;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b18;
    private javax.swing.JButton b19;
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b25;
    private javax.swing.JButton b26;
    private javax.swing.JButton b27;
    private javax.swing.JButton b28;
    private javax.swing.JButton b29;
    private javax.swing.JButton b30;
    private javax.swing.JButton b31;
    private javax.swing.JButton b32;
    private javax.swing.JButton b33;
    private javax.swing.JButton b34;
    private javax.swing.JButton b35;
    private javax.swing.JButton b36;
    private javax.swing.JButton b37;
    private javax.swing.JButton b38;
    private javax.swing.JButton b39;
    private javax.swing.JButton b40;
    private javax.swing.JButton b41;
    private javax.swing.JButton b42;
    private javax.swing.JButton b43;
    private javax.swing.JButton b44;
    private javax.swing.JButton b45;
    private javax.swing.JButton b46;
    private javax.swing.JButton b47;
    private javax.swing.JButton b48;
    private javax.swing.JButton b49;
    private javax.swing.JButton b50;
    private javax.swing.JButton b51;
    private javax.swing.JButton b52;
    private javax.swing.JButton b53;
    private javax.swing.JButton b54;
    private javax.swing.JButton b55;
    private javax.swing.JButton b56;
    private javax.swing.JButton b57;
    private javax.swing.JButton b58;
    private javax.swing.JButton b59;
    private javax.swing.JButton b60;
    private javax.swing.JButton b61;
    private javax.swing.JButton b62;
    private javax.swing.JButton b63;
    private javax.swing.JButton b64;
    private javax.swing.JButton b65;
    private javax.swing.JButton b66;
    private javax.swing.JButton b67;
    private javax.swing.JButton b68;
    private javax.swing.JButton b69;
    private javax.swing.JButton b70;
    private javax.swing.JButton b71;
    private javax.swing.JButton b72;
    private javax.swing.JButton b73;
    private javax.swing.JButton b74;
    private javax.swing.JButton b75;
    private javax.swing.JButton b76;
    private javax.swing.JButton b77;
    private javax.swing.JButton b78;
    private javax.swing.JButton b79;
    private javax.swing.JButton b80;
    private javax.swing.JButton b81;
    private javax.swing.JButton b82;
    private javax.swing.JButton b83;
    private javax.swing.JButton b84;
    private javax.swing.JButton b85;
    private javax.swing.JButton b86;
    private javax.swing.JButton b87;
    private javax.swing.JButton b88;
    private javax.swing.JButton b89;
    private javax.swing.JButton b90;
    private javax.swing.JButton b91;
    private javax.swing.JButton b92;
    private javax.swing.JButton b93;
    private javax.swing.JButton b94;
    private javax.swing.JButton b95;
    private javax.swing.JButton b96;
    private javax.swing.JButton b97;
    private javax.swing.JButton b98;
    private javax.swing.JButton b99;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panelb2;
    private javax.swing.JPanel panelb3;
    private javax.swing.JPanel panelb4;
    private javax.swing.JPanel panelb5;
    private javax.swing.JPanel panelb6;
    // End of variables declaration//GEN-END:variables
}
