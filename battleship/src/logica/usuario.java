package logica;

import java.awt.Color;
import javax.swing.JButton;
import interfaces.imagenes;
import java.awt.Image;
import javax.swing.ImageIcon;
public class usuario {
    
    enemigo enemi = new enemigo();
    
    public void casilla_barco(JButton [][]boton, int [][]tableroj, int [][] barco2, int [][]barco3,int [][] barco4, int [][] barco5, int []ori){
    
        for (int i = 0; i<10;i++){
            for (int j=0;j<10;j++){
            
                if (tableroj[i][j]==1){
                    
                    JButton boton1 = boton[i][j];
                    boton1.setOpaque(false);
                    boton1.setBorder(null);
                    boton1.setBackground(new Color (0,0,0,0));
                    boton1.setBorderPainted(false);
                    boton1.setContentAreaFilled(false);
                    boton1.setFocusPainted(false);
                    
                    if (i == barco2 [0][0] &&  j== barco2[0][1] && ori [0]==0|| i == barco3 [0][0] && j == barco3 [0][1] && ori[1]==0 || i == barco4 [0][0] && j==barco4[0][1] && ori[2]==0 ||i == barco5 [0][0] && j==barco5[0][1] && ori[3]==0 ) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barco1.png"));
                    Image img = icono.getImage();
                    Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(newimg);          
                    boton1.setIcon(iconoEscalado);
                    }
                    else if (i == barco2 [1][0] && j== barco2[1][1] && ori[0] == 0 || i == barco3 [2][0] && j == barco3 [2][1] && ori [1]==0 || i == barco4 [3][0] && j==barco4[3][1] && ori [2]==0 ||i == barco5 [4][0] && j==barco5[4][1] && ori [3]==0 ) { 
                    ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barco3.png"));
                    Image img = icono.getImage();
                    Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(newimg);          
                    boton1.setIcon(iconoEscalado);}
                    
                    
                    else if (i == barco2 [0][0] &&  j== barco2[0][1] && ori [0]==1|| i == barco3 [0][0] && j == barco3 [0][1] && ori[1]==1 || i == barco4 [0][0] && j==barco4[0][1] && ori[2]==1 ||i == barco5 [0][0] && j==barco5[0][1] && ori[3]==1 ) {
                    ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barcoR1.png"));
                    Image img = icono.getImage();
                    Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(newimg);          
                    boton1.setIcon(iconoEscalado);
                    }
                    else if (i == barco2 [1][0] && j== barco2[1][1] && ori[0] == 1 || i == barco3 [2][0] && j == barco3 [2][1] && ori [1]==1 || i == barco4 [3][0] && j==barco4[3][1] && ori [2]==1 ||i == barco5 [4][0] && j==barco5[4][1] && ori [3]==1 ) { 
                    ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barcoR3.png"));
                    Image img = icono.getImage();
                    Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(newimg);          
                    boton1.setIcon(iconoEscalado);}
                    
                    else if (ori [0]==0||ori[1]==0||ori [2]==0||ori[2]==0){
                        
                    ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barco2.png"));
                    Image img = icono.getImage();
                    Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(newimg);          
                    boton1.setIcon(iconoEscalado);}
                    
                    else if (ori [0]==1||ori[1]==1||ori [2]==1||ori[2]==1){
                        
                    ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barcoR2.png"));
                    Image img = icono.getImage();
                    Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                    ImageIcon iconoEscalado = new ImageIcon(newimg);          
                    boton1.setIcon(iconoEscalado);}
                        
                    }
                    
            
                }
            
            }
        
        }
    
        public void pintar_direccion (JButton boton,int tamano, int[][]barco, int ori, int iteracion){
                       
                      if (ori == 0){
                        if (iteracion==0){
                          ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barco1.png"));
                            Image img = icono.getImage();
                            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg);          
                            boton.setDisabledIcon(iconoEscalado);}
                        if (iteracion== tamano-1 ){
                            ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barco3.png"));
                            Image img = icono.getImage();
                            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg);          
                            boton.setDisabledIcon(iconoEscalado);}
                        if (iteracion>0 && iteracion< tamano-1){
                            ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barco2.png"));
                            Image img = icono.getImage();
                            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg);          
                            boton.setDisabledIcon(iconoEscalado);}
                        
                        }
                      else {
                          if (iteracion==0){
                          ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barcoR1.png"));
                            Image img = icono.getImage();
                            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg);          
                            boton.setDisabledIcon(iconoEscalado);}
                        if  (iteracion== tamano-1 ){
                            ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barcoR3.png"));
                            Image img = icono.getImage();
                            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg);          
                            boton.setDisabledIcon(iconoEscalado);}
                        if (iteracion>0 && iteracion< tamano-1){
                            ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/barcoR2.png"));
                            Image img = icono.getImage();
                            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg);          
                            boton.setDisabledIcon(iconoEscalado);}
                      }
                      
                      }
                        
        public void ataque (int bomba,int x,int y, JButton boton){
          
          if (bomba ==3){ //agua
              boton.setOpaque(false);
              boton.setBorder(null);
                boton.setBackground(new Color (0,0,0,0));
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                boton.setFocusPainted(false);
                ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/equis.png"));
                Image img = icono.getImage();
                Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
                            ImageIcon iconoEscalado = new ImageIcon(newimg); 
                            boton.setEnabled(false);
                            boton.setDisabledIcon(iconoEscalado);}
          if (bomba ==4){ //barco
              boton.setOpaque(false);
              boton.setBorder(null);
                boton.setBackground(new Color (0,0,0,0));
                boton.setBorderPainted(false);
                boton.setContentAreaFilled(false);
                boton.setFocusPainted(false);
                 boton.setEnabled(false);
          }
              
          }
          public boolean muerte (int x,int y,int[][] barco2,int[][] barco3,int[][] barco4,int[][] barco5 ){
              
              if (enemi.puntos(x, y, 2, barco2) >-1){
                  return enemi.hundir(2, barco2);
                }
              if (enemi.puntos(x, y, 3, barco3) >-1){
                  return enemi.hundir(3, barco3);
                }
              if (enemi.puntos(x, y, 4, barco4) >-1){
                  return enemi.hundir(4, barco4);
                }
              if (enemi.puntos(x, y, 5, barco5) >-1){
                  return enemi.hundir(5, barco5);
                }
              return false;
          
          }
            
        }
     

