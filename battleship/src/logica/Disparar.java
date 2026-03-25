package logica;
import java.util.Random;
import javax.swing.JButton;

public class Disparar {

    static Random rand = new Random();
    int[][] tablero = TableroE.tablero;
    int[][] tableroj = TableroE.tableroj;
    int[][] barco2;
    int[][] barco3;
    int[][] barco4;
    int[][] barco5;
    JButton [][] botones = new JButton[10][10];
    boolean turno;
    //MEMORIA DE LA MAQUINA
    static boolean modoAtaque = false;
    static boolean direccionDefinida = false;

    static int ultimaFila;
    static int ultimaColumna;
    static int direccion; 
    usuario user = new usuario ();
    // 0 = ARRIBA, 1 = ABAJO, 2 = IZQUIERDA, 3 = DERECJHA
    
    public Disparar (JButton botones[][], int [][] tableroj, boolean turno, int[][]barco2, int [][]barco3, int [][] barco4, int [][] barco5){
        this.turno=turno;
        this.botones=botones;
        this.tableroj=tableroj;
        this.barco2=barco2;
        this.barco3=barco3;
        this.barco4=barco4;
        this.barco5=barco5;
    }

    public  boolean dispararjugador(int a) {
        
        turno = true;
        return turno;

    }

    public  int dispararmaquina(int a, int coorV1, int coorV2) {
        System.out.println(coorV1+""+coorV2);
        turno = false;
        int resultado=1;
        if (a == 1) {
            // MODO BUSQUEDA
            int coordR1 = rand.nextInt(10);
            int coordR2 = rand.nextInt(10);

            return verificadorrandom(coordR1, coordR2);
        }

        if (a == 2) {
            // MODO ATAQUE
            return disparoInteligente();
        }
        System.out.println(coorV1+""+coorV2);
        
        return 2;
    }

    //  DISPARO NORMAL
    public  int verificadorrandom(int coordR1, int coordR2) {

        if (tableroj[coordR1][coordR2] == 0) {
            tableroj[coordR1][coordR2] = 3; // AGUA
            user.ataque(3, coordR1, coordR2, botones [coordR1][coordR2]); 
            System.out.println(coordR1+" pri water "+coordR2);
            return 2;
        }

        else if (tableroj[coordR1][coordR2] == 1) {
            tableroj[coordR1][coordR2] = 4; // IMPACTO
            user.ataque(4, coordR1, coordR2, botones [coordR1][coordR2]);
            
            if (user.muerte(coordR1,coordR2, barco2,barco3,barco4,barco5)==true){
                
                return 3;
            }
            
            System.out.println(coordR1+" pri im "+coordR2);
            // ACTIVAR MODO ATAQUE
            modoAtaque = true;
            ultimaFila = coordR1;
            ultimaColumna = coordR2;
            direccionDefinida = false;

            return dispararmaquina(2, coordR1, coordR2);
        }
        return 2;
    }

    // DISPARO INTELIGENTE
    public  int disparoInteligente() {

        int nuevaFila = ultimaFila;
        int nuevaColumna = ultimaColumna;

        // Si no sabemos dirección, elegimos una random
        if (!direccionDefinida) {
            direccion = rand.nextInt(4);
        }

        // Mover según dirección
        switch (direccion) {
            case 0: nuevaFila--; break; // ARRIBA
            case 1: nuevaFila++; break; // ABAJO
            case 2: nuevaColumna--; break; // IZQUIERDA
            case 3: nuevaColumna++; break; // DERECHA
        }

        // evitar salir del tablero
        if (nuevaFila < 0 || nuevaFila >= 10 || nuevaColumna < 0 || nuevaColumna >= 10) {
            direccionDefinida = false;
           
            return disparoInteligente();
        }

        // AGUA
        if (tableroj[nuevaFila][nuevaColumna] == 0) {
            tableroj[nuevaFila][nuevaColumna] = 3;
            System.out.println(nuevaFila+" water "+nuevaColumna);

            if (!direccionDefinida) {
                // probar otra dirección
                direccionDefinida = false;
            } else {
                // invertir dirección
                invertirDireccion();
            }

            return 2;
        }

        // IMPACTO
        else if (tableroj[nuevaFila][nuevaColumna] == 1) {
            tableroj[nuevaFila][nuevaColumna] = 4;
            user.ataque(4, nuevaFila, nuevaColumna, botones [nuevaFila][nuevaColumna]);
            
            if (user.muerte(nuevaFila,nuevaColumna, barco2,barco3,barco4,barco5)==true){
                
                return 3;}
            System.out.println(nuevaFila+" im "+nuevaColumna);

            // ahora ya sabemos dirección
            direccionDefinida = true;

            // actualizar posición
            ultimaFila = nuevaFila;
            ultimaColumna = nuevaColumna;

            // sigue jugando
            return disparoInteligente();
        }

        //LUGAR YA DISPARADO 
        else {
            direccionDefinida = false;
            return disparoInteligente();
        }
    }

    // INVERTIR DIRECCIÓN
    public void invertirDireccion() {
        if (direccion == 0) direccion = 1;
        else if (direccion == 1) direccion = 0;
        else if (direccion == 2) direccion = 3;
        else if (direccion == 3) direccion = 2;
    }
    // CAMBIAR TURNO
    public  void cambiarturno(int JoM) {
    	//TURNO DEL JUGADOR
        if (JoM == 1) {
            dispararjugador(1);
        }
      //TURNO DE LA MAQUINA
        if (JoM == 2) {
            dispararmaquina(1, 0, 0);
        }
    }
}