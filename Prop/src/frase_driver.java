import java.io.IOException;
import java.util.Scanner;


public class frase_driver {
	
	private static Scanner opcion;
	private static Scanner texto;
	public static void main (String[] args) throws IOException{
		Frase f = new Frase();
		texto = new Scanner(System.in);
		opcion = new Scanner(System.in);
		Palabra pal = new Palabra();
		String s = null,p = null;
		int var;
		int aux;
		do{
			System.out.println("1. Añadir frase.");
			System.out.println("2. Añadir palabra a frase");
			System.out.println("3. Borrar palabra por posicion");
			System.out.println("4. Medida de una frase");
			System.out.println("5. Medida sin palabras funcionales.");
			System.out.println("6. Posicion de una palabra.");
			System.out.println("7. La palabra en una posicion.");
			System.out.println("8. Devolver frase que ese esta tratando.");
			System.out.println("0. Salir del driver.");
			var = opcion.nextInt();
			switch(var){

			case 1:
				System.out.println("Introduce una frase a continuacion");
				s = texto.nextLine();
				f = new Frase(s);
				break;
			case 2:
				System.out.println("Escribe la palabra que quieres añadir");
				s = texto.nextLine();
				pal = new Palabra(s);
				System.out.println("Escribe en la posicion que la quieres añadir");
				aux = texto.nextInt();
				f.anyadirpalabra(pal,aux);
				break;
			case 3:
				System.out.println("Escribe la posicion de la palabra que quieres borrar");
				aux = texto.nextInt();
				f.borrarpalabra(aux);
				break;
			case 4:
				System.out.println("La medida de la frase es: "+ f.midafrase());
				break;
			case 5:	
				System.out.println("La medida de la frase sin palabras funcionales es: "+ f.midafrase_significativa())                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
				break;
			case 6:	
				System.out.println("Escribe la palabra que quieres consultar su posicion.");
				p = texto.nextLine();
				pal = new Palabra(p);
				int i = f.posfrase(pal);
				if(i != -1)System.out.println("La posicion de la palabra : \"" + pal.palabra() +"\" es " + i);
				else System.out.println("no se encuentra la palabra");
				break;
			case 7:	
				System.out.println("Escribe la posicion de la palabra que quieres consultar.");
				aux = texto.nextInt();
				System.out.println("En la posicion  : " + aux + "esta la palabra  \"" + f.posfrase(aux).palabra()+"\"");
				break;
			case 8:	
				f.escribirfrase();
				break;
			}

		}while(var!= 0);


		//switch


	}

}
