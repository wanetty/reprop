package Prop;
//driver creado por Eduard Gonzalez Moreno
//En este driver se prueba la clase Bool_expresion.
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class driver_boleano
{	
	private static Scanner opcion;
	private static Scanner texto;

	public static void main (String[] args) throws IOException{

		System.out.println("Bienvenido al Driver de Expresion Booleana.");
		Bool_expresion test = new Bool_expresion();
		String expresion;
		int var;
		texto = new Scanner(System.in);
		opcion = new Scanner(System.in);
		boolean primero = true;
		while(test.isnull() || primero){
			if(primero)primero = false;
			System.out.println("Primero para el debido funcionamiento del driver debes introducir una expresion booleana valida: ");
			expresion = texto.nextLine();
			test = new Bool_expresion(expresion);
			if(test.isnull())System.out.println("Mal escrito introducir de nuevo.");
		}
		do{
			System.out.println("Escoge una opci�n: ");
			System.out.println("1. Introducir expresion booleana.");
			System.out.println("2. Imprimir estructura en Post_Orden");
			System.out.println("3. Consultar Valor raiz");
			System.out.println("4. Consultar cantidad de nodos del arbol");
			System.out.println("5. Consultar altura del arbol");
			System.out.println("6. Consultar si es nulo");
			System.out.println("7. Consultar separador ");
			System.out.println("0. Salir del driver.");
			var = opcion.nextInt();
			switch(var){

			case 1:
				primero = true;
				while(test.isnull() || primero){
					if(primero) primero = false;
					System.out.println("Introduce nueva expresion booleana: ");
					expresion = texto.nextLine();
					test = new Bool_expresion(expresion);
					if(test.isnull())System.out.println("Mal escrito introducir de nuevo.");

				}
				if(!test.isnull())	System.out.println("Introducido correctamente.");

				break;
			case 2:
				ArrayList<String> postorden = test.PostOrden();
				if(!postorden.isEmpty()){
				for(int i = 0;i < postorden.size();++i){
					System.out.println(postorden.get(i));
				}
				}
				else System.out.println("Arbol vacio.");
				break;
			case 3:
				System.out.println("El valor de la ra�z es: "+ test.valor_raiz());
				break;
			case 4:
				System.out.println("El tama�o en numero de nodo es: "+test.cantidad()+ " .");
				break;
			case 5:
				System.out.println("La altura del arbol es: "+test.altura()+ " .");
				break;
			case 6:	
				if (test.isnull()) System.out.println("El arbol es nulo");
				else System.out.println("El arbol no es nulo");
				break;
			}

		}while(var!= 0);


		//switch

	}
}