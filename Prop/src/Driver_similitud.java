import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Driver_similitud {
	
	
	public static void main (String[] args) throws IOException{
		 
		//Palabra p = new Palabra("el");
		// devuelve true si la palabra es funcional
		//System.out.println(p.esfuncional());
		/*
		Frase f = new Frase("Estamos en clase de prop");
		ArrayList<Frase> x = new ArrayList<Frase>();
		Documento d = new Documento(f,f,f,x);
		System.out.println(d.get_fecha());
		Date fecha = new Date();
		*/
		
		Frase f = new Frase("An essay is essentially a collection of paragraphs, and as has already been said, a pargraph is essentially a mini-essay.");
		Frase e = new Frase("An essay is essentially a collection of paragraphs, and as has already been said, a pargraph is essentially a mini-essay.");
		Frase g = new Frase("An essay contains an introduction, a body, and a conclusion."); 
		
		ArrayList<Frase> y = new ArrayList<Frase>();
		ArrayList<Frase> x = new ArrayList<Frase>();
		ArrayList<Frase> z = new ArrayList<Frase>();
		//x.add(f);
		z.add(f);
		y.add(e);
		x.add(g);
		Documento d1 = new Documento(f,f,f,y);
		Documento d2 = new Documento(f,f,f,x);
		Documento d3 = new Documento(f,f,f,z);
		Similitud test = new Similitud();
		System.out.println(d1.get_pesos());
		System.out.println(d2.get_pesos());
		System.out.println("la similitud es:");
		System.out.println(test.calculaSimilitud(d1, d2));
		System.out.println(d1.get_pesos());
		//d1 y d3 son iguales
		System.out.println(test.calculaSimilitud(d1, d3));
		
	}

}
