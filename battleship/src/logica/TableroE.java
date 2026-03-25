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
	
	public void ColocarbarcoPersona(int [][] barco2,int [][] barco3, int [][] barco4, int [][] barco5){
            
            
            for (int n=2;n<=5;n++){
                for (int k=0;k<n;k++){
                        
                        if(n==2){
                         int x=barco2 [k][0];
                         int y=barco2 [k][1];
                         tableroj[x][y]=1;
                         System.out.println(x+"a "+y);
                        }
                        else if (n==3){
                         int x=barco3 [k][0];
                         int y=barco3 [k][1];
                         tableroj[x][y]=1;
                         System.out.println(x+"b "+y);
                        }
                        else if(n==4){
                        int x=barco4 [k][0];
                         int y=barco4 [k][1];
                         tableroj[x][y]=1;
                         System.out.println(x+"c "+y);
                        }
                        else if(n==5){
                        int x=barco5 [k][0];
                         int y=barco5 [k][1];
                         tableroj[x][y]=1;
                         //System.out.println(x+" "+y);
                        }
                          
                      }
                System.out.println("n es: "+n);
            }
            for (int i = 0; i<5;i++){
                for (int j=0; j<2;j++){
                    System.out.print(barco5[i][j]+"--");
                }
                System.out.println();
            }
	}
        
        public int [][] gettablero (){
            
            return tablero;
        }
        
        public int [][] gettableroj (){
            return tableroj;
        }
}
