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
				System.out.println("Introduce una frase no vacía a continuacion");
				s = texto.nextLine();
				if(s.isEmpty())System.out.println("frase no añadida");
				else f = new Frase(s);
				break;
			case 2:
				if(f.frase() == null)System.out.println("Sin frase");
				else {
				System.out.println("Escribe la palabra que quieres añadir");
				p = null;
				p = texto.nextLine();
				pal = new Palabra(s);
				System.out.println("Escribe en la posicion que la quieres añadir");
				aux = opcion.nextInt();
				if(f.frase() != null && aux > f.midafrase()-1) System.out.println("palabra no añadida, posicion incorrecta");
				else f.anyadirpalabra(pal,aux);
				}
				break;
			case 3:
				if(f.frase() == null)System.out.println("Sin frase");
				else {
				System.out.println("Escribe la posicion de la palabra que quieres borrar");
				aux = opcion.nextInt();
				if(aux > f.midafrase()-1 && aux < 0) System.out.println("palabra no borrada, posicion incorrecta");
				else f.borrarpalabra(aux);
				}
				break;
			case 4:
				if(f.frase() != null) System.out.println("La medida de la frase es: "+ f.midafrase());
				else System.out.println("Sin frase");
				break;
			case 5:	
				if(f.frase() == null)System.out.println("Sin frase");
				else System.out.println("La medida de la frase sin palabras funcionales es: "+ f.midafrase_significativa());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ;
				break;
			case 6:	
				if(f.frase() == null)System.out.println("Sin frase");
				else {
				System.out.println("Escribe la palabra que quieres consultar su posicion.");
				p = texto.nextLine();
				pal = new Palabra(p);
				int i = f.posfrase(pal);
				if(i != -1)System.out.println("La posicion de la palabra : \"" + pal.palabra() +"\" es " + i);
				else System.out.println("no se encuentra la palabra");}
				break;
			case 7:	
				if(f.frase() == null)System.out.println("Sin frase");
				else{
				System.out.println("Escribe la posicion de la palabra que quieres consultar.");
				aux = opcion.nextInt();
				if(aux > f.midafrase()-1 && aux < 0) System.out.println("palabra no consultada, posicion incorrecta");
				else System.out.println("En la posicion  : " + aux + "esta la palabra  \"" + f.posfrase(aux).palabra()+"\"");}
				break;
			case 8:	
				if(f.frase() != null) f.escribirfrase();
				else System.out.println("Sin frase");
				break;
			}

		}while(var!= 0);


		//switch


	}

}
