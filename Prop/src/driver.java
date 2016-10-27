import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class driver {
	
	
	public static void main (String[] args) throws IOException{
		 
		//Palabra p = new Palabra("el");
		// devuelve true si la palabra es funcional
		//System.out.println(p.esfuncional());
		Frase f = new Frase("Estamos en clase de prop");
		ArrayList<Frase> x = new ArrayList<Frase>();
		Documento d = new Documento(f,f,f,x);
		System.out.println(d.get_fecha());
		Date fecha = new Date();
	}

}
