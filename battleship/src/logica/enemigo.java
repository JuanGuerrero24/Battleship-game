
package logica;
import java.awt.Color;
import javax.swing.JButton;
import interfaces.imagenes;
import java.awt.Image;
import javax.swing.ImageIcon;
public class enemigo {
    
    public enemigo (){
    
        
    }
    //metodo que valida si una casilla seleccionada es 1 o 0
    public boolean correcto (int x, int y, int tablero[][]){
        
        boolean validar = true;
        if (tablero [x][y] == 0){validar=false;}
        else if (tablero [x][y]==1){validar = true;}
            return validar;
    }
    
    //metodo que retorna la fila de la matriz barco en la que esta guardada la coordenada de la casilla seleciconada
    public int puntos (int x, int y, int n, int barco [][]){
        
        int retorno = -2;
        
        for (int i=0; i < n;i++){
           
            if (barco [i][0]==x && barco [i][1]==y){
                
                retorno = i;
                break;
            }
        }
        return retorno;
    }
    //metodo que mira si la casilla seleccionada es la ultima del barco, si es asi retorna true
    public boolean hundir ( int n, int barco[][]){
                
                boolean retorno = false;
                int count =0;
                for (int i =0; i<n; i++){
                    if (barco [i][0]==-1){
                    count++;
                    
                    }
                }
                if (count == n){
                    retorno = true;
                }
                return retorno;
    }
    
    //metodo que une todas las funciones que se deben hacer al oprimir un boton
    public boolean ataque (int x, int y, JButton boton, int barco2 [][], int barco3 [][], int barco4 [][], int barco5 [][], int tablero [][]){
           
        //al seleccionar el boton se vuelve transparente y se vuelve inutilizable
            boton.setOpaque(false);
            boton.setBorder(null);
            boton.setBackground(new Color (0,0,0,0));
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setFocusPainted(false);
            
            boolean validar = correcto (x,y,tablero);
            
        //si validar es falso significa que la casilla es 0, se pone en la casilla una "x" roja    
        if (validar == false){
            
            ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/equis.png"));
            Image img = icono.getImage();
            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(newimg);
            
            boton.setEnabled(false);
            boton.setDisabledIcon(iconoEscalado);
        
        //si por el contrario, es verdadero, o sea la casilla es 1 y hay un barco allí, se pone otra imagen que referencia un estallido de una bomba    
        }
        else { 
            ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/boton.png"));
            Image img = icono.getImage();
            Image newimg = img.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(newimg);
            
            boton.setEnabled(false);
            boton.setDisabledIcon(iconoEscalado);
            
        //aqui se utiliza el método puntos con cada barco para saber en cual barco y en que parte de ese barco esta ese 1
            int a =puntos (x,y,2,barco2);
            int b =puntos (x,y,3,barco3);
            int c =puntos (x,y,4,barco4);
            int d =puntos (x,y,5,barco5);
            
        //los siguientes if sirven para seleccionar unicamente el barco que contiene la coordenada
        //al barco seleccionado se pone de parametro para el metodo hundir, esto retornará falso si aún hay partes del barco y verdader si el barco se destruyó por completo
            if (a>= 0){
                barco2 [a][0] = -1;
                return hundir (2,barco2);
            }
            
            if (b>= 0){
                barco3 [b][0] = -1;
                return hundir (3,barco3);
            }
            if (c>= 0){
                barco4 [c][0] = -1;
                return hundir (4,barco4);
            }
            
            if (d>= 0){
                barco5 [d][0] = -1;
                return hundir (5,barco5);
            }
            
        }
        //si ningun barco se ha hundido retorna falso
        return false;
    }
    
    
}
