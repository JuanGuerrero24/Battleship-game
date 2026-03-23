
package interfaces;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class imagenes extends JLabel{
    
    private int x,y;
    private final String path;
   
    
    public imagenes (JPanel panel, String path){
        this.path = path;
        this.x = panel.getWidth();
        this.y = panel.getHeight();
        this.setSize(x,y);
    }
    
    
    @Override
    public void paint (Graphics g){
         File archivo = new File("repo/"+path+".png");
        ImageIcon img = new ImageIcon(archivo.getAbsolutePath());
        g.drawImage(img.getImage(),0,0,x, y, null);
    
    }
    
     public ImageIcon imagenbtn (String path, int x, int y){
    
        ImageIcon icono = new ImageIcon(getClass().getResource("/iconos/"+path+".png"));
        Image img = icono.getImage();
        Image newimg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(newimg);
        
        return iconoEscalado;
    }
    
}   