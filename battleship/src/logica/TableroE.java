package logica;
import java.util.Random;
public class TableroE {
	public static int [][] tablero = new int[10][10];
	public static int [][] tableroj= new int[10][10];
        
	public static void llenartablero() {
		for (int i=0; i<10;i++) {
			for (int j=0; j<10;j++) {
				tablero[i][j]=0;
				tableroj[i][j]=0;
			}
			}
		}
	public  int [][] generadordebarcosramdom(int n) {
                int [][]barco = new int[n][2];
		int aux;
		Random rand=new Random();
		
		int Aleatorio1 = rand.nextInt(10);
		int Aleatorio2 = rand.nextInt(10);
		if (tablero [Aleatorio1][Aleatorio2]==1) {
			generadordebarcosramdom(n);
			return barco;
		}
		tablero [Aleatorio1][Aleatorio2] = 1;
                barco [0][0] = Aleatorio1;
                barco [0][1]= Aleatorio2;
		aux = horizontalovertical();
		switch (aux) {
		case 1:
			//Arriba
			for(int i=1;i<n;i++) {
				if (Aleatorio2-i < 0 || tablero[Aleatorio1][Aleatorio2-i] == 1) {
					tablero [Aleatorio1][Aleatorio2]=0;
					generadordebarcosramdom(n);	
					return barco;
				}
			}
			for(int i=1;i<n;i++) {
				tablero [Aleatorio1][Aleatorio2-i]=1;
                                barco[i][0] = Aleatorio1;
                                barco[i][1] = Aleatorio2-i;
			}
			break;
		case 2:
			//Abajo
			for(int i=1;i<n;i++) {
				if (Aleatorio2+i >=10 || tablero[Aleatorio1][Aleatorio2+i] == 1) {
					tablero [Aleatorio1][Aleatorio2]=0;
					generadordebarcosramdom(n);	
					return barco;
				}
			}
			for(int i=1;i<n;i++) {
				tablero [Aleatorio1][Aleatorio2+i]=1;
                                barco[i][0] = Aleatorio1;
                                barco[i][1] = Aleatorio2+i;
			}	
			break;
		case 3:
			//Derecha
			for(int i=1;i<n;i++) {
				if (Aleatorio1+i >=10 || tablero[Aleatorio1+i][Aleatorio2] == 1) {
					tablero [Aleatorio1][Aleatorio2]=0;
					generadordebarcosramdom(n);	
					return barco;
				}
			}
			for(int i=1;i<n;i++) {
				tablero [Aleatorio1+i][Aleatorio2]=1;
                                barco[i][0] = Aleatorio1+i;
                                barco[i][1] = Aleatorio2;
			}
			break;
		case 4:
			//Izquierda
			for(int i=1;i<n;i++) {
				if (Aleatorio1-i < 0 || tablero[Aleatorio1-i][Aleatorio2] == 1) {
					tablero [Aleatorio1][Aleatorio2]=0;
					generadordebarcosramdom(n);	
					return barco;
				}
			}
			for (int i=1;i<n;i++) {
				tablero [Aleatorio1-i][Aleatorio2]=1;
                                barco[i][0] = Aleatorio1-i;
                                barco[i][1] = Aleatorio2;
				}	
			break;
		}	
		//imprimir tablero
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				System.out.print(tablero[i][j]+ " ");
			}
			System.out.println();
			}
		System.out.println("---------------------------------");
                
                for (int i =0; i<n;i++){
                    for (int j = 0; j<2;j++){
                        System.out.print(barco [i][j]+"  ");
                    }
                    System.out.println();
                }
                return barco;
	}
	public static int horizontalovertical() {
		Random rand=new Random();
		int direccion = rand.nextInt(4) + 1;
		return direccion;
	}
	
	//tablero jugador
	
	public void ColocarbarcoPersona(int n,int coor1, int coor2, int Escuchador){
		int aux=Escuchador;
		switch (aux) {
		case 1:
			//Arriba
			for(int i=1;i<n;i++) {
				if (coor2-i < 0 || tableroj[coor1][coor2-i] == 1) {
					tableroj [coor1][coor2]=0;
					generadordebarcosramdom(n);	
					return;
				}
			}
			for(int i=1;i<n;i++) {
				tableroj [coor1][coor2-i]=1;
			}
			break;
		case 2:
			//Abajo
			for(int i=1;i<n;i++) {
				if (coor2+i >=10 || tableroj[coor1][coor2+i] == 1) {
					tableroj [coor1][coor2]=0;
					generadordebarcosramdom(n);	
					return;
				}
			}
			for(int i=1;i<n;i++) {
				tableroj [coor1][coor2+i]=1;
			}	
			break;
		case 3:
			//Derecha
			for(int i=1;i<n;i++) {
				if (coor1+i >=10 || tableroj[coor1+i][coor2] == 1) {
					tableroj [coor1][coor2]=0;
					generadordebarcosramdom(n);	
					return;
				}
			}
			for(int i=1;i<n;i++) {
				tableroj [coor1+i][coor2]=1;
			}
			break;
		case 4:
			//Izquierda
			for(int i=1;i<n;i++) {
				if (coor1-i < 0 || tableroj[coor1-i][coor2] == 1) {
					tableroj [coor1][coor2]=0;
					generadordebarcosramdom(n);	
					return;
				}
			}
			for (int i=1;i<n;i++) {
				tableroj [coor1-i][coor2]=1;
				}	
			break;
		}	
		
	}
        
        public int [][] gettablero (){
            
            return tablero;
        }
        
        public int [][] gettableroj (){
            return tableroj;
        }
}
