package modelo;
import java.util.Random;

public class Tablero {
	static int [][] tablero = new int[10][10];

	public static void llenartablero() { 
		for (int i=0; i<10;i++) {
			for (int j=0; j<10;j++)
				tablero[i][j]=0;
			}
		}

	public static void generadordebarcosramdom(int n) {
		int aux;
		Random rand=new Random();
		int Aleatorio1 = rand.nextInt(10);
		int Aleatorio2 = rand.nextInt(10);
		tablero [Aleatorio1][Aleatorio2] = 1;
		aux = horizontalovertical();


		switch (aux) {
		case 1:
			//Arriba
			for(int i=1;i<=n;i++) {
				if (Aleatorio2-i < 0 || tablero[Aleatorio1][Aleatorio2-i] == 1) {
					generadordebarcosramdom(n);	
					return;
				}
			}
			for(int i=1;i<=n;i++) {
				tablero [Aleatorio1][Aleatorio2-i]=1;
			}
			break;
		case 2:
			//Abajo
			for(int i=1;i<=n;i++) {
				if (Aleatorio2+i >=10 || tablero[Aleatorio1][Aleatorio2+i] == 1) {
					generadordebarcosramdom(n);	
					return;
				}
			}
			for(int i=1;i<=n;i++) {
				tablero [Aleatorio1][Aleatorio2+i]=1;
			}	
			break;
		case 3:
			//Derecha
			for(int i=1;i<=n;i++) {
				if (Aleatorio1+i >=10 || tablero[Aleatorio1+i][Aleatorio2] == 1) {
					generadordebarcosramdom(n);	
					return;
				}
			}
			for(int i=1;i<=n;i++) {
				tablero [Aleatorio1+i][Aleatorio2]=1;
			}
			break;
		case 4:
			//Izquierda
			for(int i=1;i<=n;i++) {
				if (Aleatorio1-i < 0 || tablero[Aleatorio1-i][Aleatorio2] == 1) {
					generadordebarcosramdom(n);	
					return;
				}
			}
			for (int i=1;i<=n;i++) {
				tablero [Aleatorio1-i][Aleatorio2]=1;
				}	
			break;
		}	
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				System.out.print(tablero[i][j]+ " "); 
			}
			System.out.println();
			}
		System.out.println("---------------------------------");
	}


	public static int horizontalovertical() {
		Random rand=new Random();
		int direccion = rand.nextInt(4) + 1;
		return direccion;
	}
	
	public void Colocarbarco(){

	}
}
